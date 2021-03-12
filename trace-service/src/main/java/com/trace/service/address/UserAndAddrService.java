package com.trace.service.address;

import com.trace.dao.entity.UserAndAddr;
import com.trace.dao.repository.UserAndAddrMapper;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/12
 */
@Service
public class UserAndAddrService {
    @Autowired
    UserAndAddrMapper uaaMapper;

    public boolean addUARelate(Integer userId,Integer addrId) {
        if(userId == null || addrId == null ||userId == 0 || addrId == 0){
            return false;
        }
        UserAndAddr uaa = new UserAndAddr();
        uaa.setAid(addrId);
        uaa.setUid(userId);
        uaa.setTime(new Date());
        int i = uaaMapper.insertSelective(uaa);
        return i > 0;
    }
}
