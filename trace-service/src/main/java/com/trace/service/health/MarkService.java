package com.trace.service.health;

import com.trace.dao.entity.User;
import com.trace.dao.repository.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/7
 */
@Service
@CacheEvict(cacheNames = "search")
public class MarkService {
    private final Logger logger = LoggerFactory.getLogger(MarkService.class);

    @Autowired
    UserMapper userMapper;

    @CacheEvict(cacheNames = "search",allEntries = true)
    public void mark(List<Integer> list,boolean isNormal) {
        if(list == null || list.size() == 0) {
            return;
        }
        for (int i : list) {
            User user = new User();
            user.setId(i);
            if(isNormal) {
                user.setUserType(0);
            }else {
                user.setUserType(1);
            }
           int c = userMapper.updateByPrimaryKeySelective(user);
            if(c <= 0) {
                logger.error("更新"+i+"信息失败");
            }
        }
    }

}
