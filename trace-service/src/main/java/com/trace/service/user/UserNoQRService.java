package com.trace.service.user;

import com.trace.dao.entity.User;
import com.trace.dao.repository.UserMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/10
 */
@Service
public class UserNoQRService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserInfoService infoService;

    public int createDefaultNoPhoneUser() {
        User user = new User();
        int id = new Date().hashCode();
        if(id < 0)id += Integer.MAX_VALUE;
        // hash冲突解决,线性探查法解决
        for (;userMapper.selectByPrimaryKey(id) != null;id++);
        user.setId(id);
        userMapper.insertSelective(user);
        return id;
    }

    public boolean isUserExist(Integer userId) {
        return true;
    }
}
