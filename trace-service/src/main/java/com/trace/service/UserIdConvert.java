package com.trace.service;

import com.trace.dao.entity.UserConvert;
import com.trace.dao.entity.UserConvertExample;
import com.trace.dao.repository.UserConvertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@Service
public class UserIdConvert {

    @Autowired
    UserConvertMapper convertMapper;

    public Integer openIdConvert(String openId){
        Integer i = convertMapper.selectByOpenId(openId);
        return i;
    }
}
