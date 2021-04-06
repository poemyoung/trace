package com.trace.service.health;

import com.trace.dao.entity.Address;
import org.apache.commons.collections4.trie.PatriciaTrie;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
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

    private static volatile boolean f = false;

    public static List<Integer> searchInPatree(String s) {
        Collection<Integer> values = new ArrayList<>();
        if (trie == null) {
            synchronized (PatricialTrieService.class) {
                if (trie == null) {
                    logger.error("线程处理出现问题");
                } else {
                    values = trie.prefixMap(s).values();
                }
            }
        }else {
            values = trie.prefixMap(s).values();
        }
        List<Integer> list = new ArrayList<>(values);
        return list;
    }

    public static void buildAddressTree(List<Address> list) {
       if(f) {
           // 重新构造
           synchronized (PatricialTrieService.class) {
               if(f) {
                   trie = new PatriciaTrie<>();
                   for (Address a : list) {
                       trie.put(getDetail(a),a.getUserId());
                   }
                   f = false;
               }
           }
       }
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

    @Cacheable(cacheNames = "search",key = "'paTreeExpire'")
    public void isUsable() {
        synchronized (PatricialTrieService.class) {
            f = true;
        }
    }

}
