package com.trace.service.user;

import com.trace.api.openid.OpenIdApi;
import com.trace.dao.entity.User;
import com.trace.dao.entity.UserConvert;
import com.trace.dao.entity.UserConvertExample;
import com.trace.dao.repository.UserConvertMapper;
import com.trace.dao.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@Service
public class UserIdConvertService {

    @Autowired
    UserConvertMapper convertMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    OpenIdApi openIdApi;

    public Integer codeCovert(String code) {
        Integer a = 0;
        try {
            String openId = openIdApi.getOpenIdByCode(code);
            a = this.openIdConvert(openId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return a;
    }

    @Transactional
    public Integer openIdConvert(String openId){
        if(openId == null)return null;
        if(isUserExists(openId)){
            return convertMapper.selectByOpenId(openId);
        }else {
            return createDefaultUser(openId);
        }
    }

    private int createDefaultUser(String openId){
        User user = new User();
        int id = openId.hashCode();
        if(id < 0)id += Integer.MAX_VALUE;
        // hash冲突解决,线性探查法解决
        for (;userMapper.selectByPrimaryKey(id) != null;id++);
        user.setId(id);
        userMapper.insertSelective(user);
        UserConvert convert = new UserConvert();
        convert.setOpenId(openId);
        convert.setUserId(id);
        convertMapper.insertSelective(convert);
        return id;
    }

    private boolean isUserExists(String openId) {
        UserConvertExample example = new UserConvertExample();
        UserConvertExample.Criteria criteria = example.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        List<UserConvert> users = convertMapper.selectByExample(example);
        return users != null && !users.isEmpty();
    }
}
