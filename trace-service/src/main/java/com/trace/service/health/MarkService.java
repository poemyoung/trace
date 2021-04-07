package com.trace.service.health;

import com.trace.dao.entity.User;
import com.trace.dao.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/7
 */
@Service
public class MarkService {

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
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

}
