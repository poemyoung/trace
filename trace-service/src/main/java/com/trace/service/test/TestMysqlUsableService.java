package com.trace.service.test;

import com.trace.dao.entity.TestMysqlUsable;
import com.trace.dao.entity.TestMysqlUsableExample;
import com.trace.dao.repository.TestMysqlUsableMapper;
import com.trace.service.redis.CacheNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/2/26
 */
@Service
@CacheConfig(cacheNames = "trace")
public class TestMysqlUsableService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    TestMysqlUsableMapper mapper;
    String sData = Calendar.getInstance().get(Calendar.MONTH) + "月" +
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + "日";
    Integer iData = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

    public boolean isMysqlAvaInsert() {
        TestMysqlUsable usable = new TestMysqlUsable();
        usable.setDataVarchar(sData);
        usable.setDataInt(iData);
        mapper.insert(usable);
        TestMysqlUsableExample example = new TestMysqlUsableExample();
        TestMysqlUsableExample.Criteria criteria = example.createCriteria();
        criteria.andDataIntIsNotNull().andDataVarcharIsNotNull();
        List<TestMysqlUsable> testMysqlUsables = mapper.selectByExample(example);
        return !testMysqlUsables.isEmpty();
    }

    @Cacheable(value = "addr_cache",key="#key")
    public boolean setRedisKey(String key,String value) {
        System.out.println("执行了一次");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key,value);
        return true;
    }
    public String getRedisKey(String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        return (String)valueOperations.get(key);

    }


}
