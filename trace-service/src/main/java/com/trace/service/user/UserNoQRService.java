package com.trace.service.user;

import com.common.utils.SnowflakeIdUtil;
import com.trace.dao.entity.User;
import com.trace.dao.entity.UserExample;
import com.trace.dao.impl.UserMapperImpl;
import com.trace.dao.repository.UserMapper;
import org.apache.commons.lang3.StringUtils;
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

    SnowflakeIdUtil snow = new SnowflakeIdUtil();

    public int createDefaultNoPhoneUser() {
        User user = new User();
        int id = snow.nextIntId();
        if(id < 0)id += Integer.MAX_VALUE;
        // hash冲突解决,线性探查法解决
        for (;userMapper.selectByPrimaryKey(id) != null;id++);
        user.setId(id);
        int r = userMapper.insertSelective(user);
        return r > 0 ? id : 0;
    }

    public Integer isUserExist(String name,String idCard) {
        if(StringUtils.isBlank(name) || StringUtils.isBlank(idCard)) {
            return null;
        }
        User user = new User();
        user.setCardId(idCard);
        user.setName(name);
        Integer userId = userMapper.selectUserExists(user);
        return userId;
    }
}
