package com.trace.service.health;

import com.trace.dao.entity.Address;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/6
 */
@Service
@Cacheable(cacheNames = "search")
public class PatricialTrieService {

    private static final Logger logger = LoggerFactory.getLogger(PatricialTrieService.class);

    private static volatile PatriciaTrie<Integer> trie;

    @Cacheable(cacheNames = "search",key = "'ptTreeExpitre'")
    public PatriciaTrie<Integer> getTrie(List<Address> list) {
        // 判断字典树是否初始化
        if (trie == null) {
            PatricialTrieService.buildAddressTree(list);
        }
        return trie;
    }

    public List<Integer> searchInPatree(String s,PatriciaTrie<Integer> trie1) {
        Collection<Integer> values;
        if(trie1 == null){
            return new ArrayList<>();
        }
        values = trie1.prefixMap(s).values();
        List<Integer> list = new ArrayList<>(values);
        return list;
    }


    public static PatriciaTrie<Integer> buildAddressTree(List<Address> list) {
           // 重新构造
        if(trie == null) {
            synchronized (PatricialTrieService.class) {
                if (trie == null) {
                    trie = new PatriciaTrie<>();
                    for (Address a : list) {
                        trie.put(getDetail(a), a.getUserId());
                    }
                }
            }
        }
        return trie;
    }

    private static String getDetail(Address address) {
        if(address == null) {
            return "";
        }
        if(StringUtils.isBlank(address.getProvince())){
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
        return address.getProvince() + address.getCity() +address.getCounty() + address.getDetail();
    }

}
