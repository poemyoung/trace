package com.trace.service.health;

import com.trace.dao.entity.Address;
import com.trace.dao.entity.AddressExample;
import com.trace.dao.entity.User;
import com.trace.dao.entity.UserDetail;
import com.trace.dao.repository.AddressMapper;
import com.trace.dao.repository.UserDetailMapper;
import com.trace.dao.repository.UserMapper;
import com.trace.service.entity.retentity.Person;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author xzp
 * Created on 2021/4/1
 */
@Service
@CacheConfig(cacheNames = "search")
public class SearchFilterService {

    @Autowired
    PatricialTrieService ptService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDetailMapper detailMapper;

    @Autowired
    SearchService searchService;

    @Autowired
    AddressMapper addressMapper;

    @Cacheable(value = "search",key = "#name+'-'+#cardId")
    public List<Integer> searchByNameIdCard(String name, String cardId){
        User user = new User();
        if(StringUtils.isNotBlank(name)){
            user.setName(name + "%");
        }
        if(StringUtils.isNotBlank(cardId)) {
            user.setCardId("%"+cardId+"%");
        }
        List<Integer> list = userMapper.selectByCondition(user);
        if(list == null) {
            return new ArrayList<>();
        }
        return list;
    }

    @Cacheable(value = "search",key = "'person' + #uid")
    public Person findSinglePerson(Integer uid) {
        if(uid == null) return null;
        User user = userMapper.selectByPrimaryKey(uid);
        if(user == null) {
            return null;
        }
        Person person = new Person();
        person.setUid(uid);
        person.setCardId(user.getCardId());
        person.setName(user.getName());
        UserDetail detail = detailMapper.selectByPrimaryKey(uid);
        if(detail == null){
            person.setSymptom(false);
        }else {
            person.setSymptom(detail.getRiskFlag() != 0);
        }

        // 查找居住地id
        Integer lid = searchService.findLivePlace(uid);
        Address address = addressMapper.selectByPrimaryKey(lid);
        if(StringUtils.isBlank(address.getProvince())) {
            address.setProvince("");
        }
        if(StringUtils.isBlank(address.getCity())) {
            address.setCity("");
        }
        if(StringUtils.isBlank(address.getCounty())) {
            address.setCounty("");
        }
        if(StringUtils.isBlank(address.getDetail())) {
            address.setDetail("");
        }
        person.setLivePlace(address.getProvince()+address.getCity()
                +address.getCounty()+address.getDetail());
        return person;
    }

    @Cacheable(value = "search",key = "'live'+#place")
    public List<Integer> searchByLivePlace(String place) {
        List<Address> addresses = searchService.getLiveAddress();
        // 搜索地址集合
        ptService.isUsable();
        PatricialTrieService.buildAddressTree(addresses);
        List<Integer> list = PatricialTrieService.searchInPatree(place);
        list.sort(Comparator.comparingInt(o -> o));
        return list;
    }
}
