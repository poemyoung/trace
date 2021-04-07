package com.trace.service.health;

import com.trace.dao.entity.*;
import com.trace.dao.repository.AddressMapper;
import com.trace.dao.repository.UserDetailMapper;
import com.trace.dao.repository.UserMapper;
import com.trace.service.entity.recentity.ConditionEntity;
import com.trace.service.entity.retentity.Person;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xzp
 * Created on 2021/4/1
 */
@Service
@CacheConfig(cacheNames = "search")
public class SearchService {

    @Autowired
    SearchFilterService sfService;

    @Autowired
    UserDetailMapper detailMapper;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    UserMapper userMapper;

    public List<Person> search(ConditionEntity conditions) {
        if(conditions == null) {
            return new ArrayList<>();
        }
        if(this.isAllEmpty(conditions)) {
            // 全量查询
            List<Integer> list = userMapper.selectByCondition(new User());
            return this.findPersons(list);
        }
        List<List<Integer>> lists = new ArrayList<>();
        if(StringUtils.isNotBlank(conditions.getName())|| StringUtils.isNotBlank(conditions.getCardId())) {
            // 姓名身份证号过滤
            lists.add(sfService.searchByNameIdCard(conditions.getName(),conditions.getCardId()));
        }
        if(conditions.getSymptom()) {
            lists.add(sfService.searchBySymtom(conditions.getSymptom()));
        }

        if(StringUtils.isNotBlank(conditions.getLivePlace())) {
            lists.add(sfService.searchByLivePlace(conditions.getLivePlace()));
        }
        List<Integer> interSeac = findInterSeac(lists);
        // 数据库反查Person列表
        if(interSeac == null || interSeac.isEmpty()){
           // 无条件查询
            return new ArrayList<>();
        }
        return this.findPersons(interSeac);
    }

    // 判断是否条件全为空
    private boolean isAllEmpty(ConditionEntity entity) {
        if(entity == null) {
            return true;
        }
        boolean f1 = StringUtils.isBlank(entity.getCardId())
                && StringUtils.isBlank(entity.getName())
                && StringUtils.isBlank(entity.getLivePlace());
        boolean f2 = entity.getSymptom() == null || !entity.getSymptom();
        return f1 && f2;
    }


    public List<Integer> findInterSeac(List<List<Integer>> gather) {
        // 排除null
        List<Integer> res = new ArrayList<>();
        if(gather == null || gather.size() == 0) {
            return new ArrayList<>();
        }
        gather.removeIf(Objects::isNull);
        if(gather.size() == 1) {
            return gather.get(0);
        }
        int[] pointers = new int[gather.size()];
        int minPos = 0;
        while (pointers[minPos] < gather.get(minPos).size()) {
            minPos = this.findMinPos(gather, pointers);
            if (minPos == -1) {
                res.add(gather.get(0).get(pointers[0]));
                minPos = 0;
            }
            pointers[minPos]++;
        }
        return res;
    }
    private int findMinPos(List<List<Integer>> gather,int[] pointers) {
        List<Integer> now = new ArrayList<>();
        for (int i = 0;i < pointers.length;i++) {
            now.add(gather.get(i).get(pointers[i]));
        }
        int minPos = -1;
        int maxValue = Collections.max(now,Comparator.comparingInt(o -> o));
        for (int i = 0;i < pointers.length;i++) {
            if(now.get(i) < maxValue) {
                minPos = i;
                maxValue = now.get(i);
            }
        }
        return minPos;
    }

    private List<Person> findPersons(List<Integer> list) {
        List<Person> persons = new ArrayList<>();
        for (Integer i : list) {
           Person p = sfService.findSinglePerson(i);
           if(p == null)continue;
           persons.add(p);
        }
        return persons;
    }

    @Cacheable(value = "search",key = "'addr'+#uid")
    public Integer findLivePlace(Integer uid) {
        UserDetail detail = detailMapper.selectByPrimaryKey(uid);
        if(detail == null) {
            return 243863;
        }
        Integer addrId = detail.getAddrId();
        if(addrId == null || addrId == 0){
            return 243863;
        }
        return addrId;
    }

    @Cacheable(value = "search",key = "'liveaddr'")
    public List<Address> getLiveAddress() {
        List<UserDetail> userDetails = detailMapper.selectByExample(new UserDetailExample());
        List<Address> list = new ArrayList<>();
        for (UserDetail detail : userDetails) {
            Address address = addressMapper.selectByPrimaryKey(detail.getAddrId());
            if(address != null) {
                list.add(address);
            }
        }
        return list;
    }

}
