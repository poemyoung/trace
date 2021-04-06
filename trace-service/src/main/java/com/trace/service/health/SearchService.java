package com.trace.service.health;

import com.trace.dao.entity.User;
import com.trace.dao.entity.UserDetail;
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
        List<List<Integer>> lists = new ArrayList<>();
        if(StringUtils.isNotBlank(conditions.getName())|| StringUtils.isNotBlank(conditions.getCardId())) {
            // 姓名身份证号过滤
            lists.add(sfService.searchByNameIdCard(conditions.getName(),conditions.getCardId()));
        }
        if(conditions.getSymptom()) {
            lists.add(this.searchBySymtom(conditions.getSymptom()));
        }
        if (StringUtils.isNotBlank(conditions.getPassPlace())||
        conditions.getStartDate() != null ||
        conditions.getEndDate() != null) {
            lists.add(this.searchByPassPlace(conditions.getPassPlace()
            ,conditions.getStartDate(),conditions.getEndDate()));
        }
        if(StringUtils.isNotBlank(conditions.getLivePlace())) {
            lists.add(this.searchByLivePlace(conditions.getLivePlace()));
        }
        List<Integer> interSeac = findInterSeac(lists);
        // 数据库反查Person列表
        if(interSeac == null || interSeac.isEmpty()){
           // 无条件查询
            interSeac = userMapper.selectByCondition(new User());
        }
        return this.findPersons(interSeac);
    }


    private List<Integer> searchBySymtom(boolean symptom) {
        return null;
    }
    private List<Integer> searchByPassPlace(String place, Date startDate,Date endDate) {
        return null;
    }
    private List<Integer> searchByLivePlace(String place) {
        return null;
    }

    public List<Integer> findInterSeac(List<List<Integer>> gather) {
        List<Integer> res = new ArrayList<>();
        if(gather == null || gather.size() == 0) {
            return new ArrayList<>();
        }
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


}
