package com.trace.service.user;

import com.common.utils.SnowflakeIdUtil;
import com.trace.dao.entity.Address;
import com.trace.dao.entity.UserDetail;
import com.trace.dao.repository.AddressMapper;
import com.trace.dao.repository.UserDetailMapper;
import com.trace.service.converter.AddressConverter;
import com.trace.service.converter.DetailConverter;
import com.trace.service.entity.recentity.UserBaseMsg;
import com.trace.service.entity.commentity.UserLiveLocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xzp
 * Created on 2021/3/11
 */
@Service
public class UserInfoUpdateService {
    @Autowired
    UserDetailMapper detailMapper;

    @Autowired
    UserInfoService infoService;

    @Autowired
    DetailConverter converter;

    @Autowired
    AddressMapper addrMapper;

    @Autowired
    AddressConverter addressConverter;

    SnowflakeIdUtil snow = new SnowflakeIdUtil();

    public boolean updateExistMsg(Integer userId,UserBaseMsg msg) {
        boolean res = true;
        if(!StringUtils.isBlank(msg.getPhone())) {
            res &= updatePhone(userId,msg.getPhone());
        }
        if(msg.getBodyHeat() != null) {
            res &= updateTemp(userId,msg.getBodyHeat());
        }
        if(msg.getSymptom() != null) {
            res &= updateRisk(userId,msg);
        }
        if(msg.getLocation() != null) {
            res &= updateAddr(userId,msg.getLocation());
        }
        return res;
    }

    public boolean updatePhone(Integer userId,String phone) {
        if(userId == null || userId == 0){
            return false;
        }
        if(!infoService.checkPhone(phone)){
            return false;
        }
        UserDetail detail = new UserDetail();
        detail.setIduserDetail(userId);
        detail.setPhone(phone);
        int i = detailMapper.updateByPrimaryKeySelective(detail);
        return i > 0;
    }

    public boolean updateTemp(Integer userId,Number temp) {
        if(userId == null || userId == 0){
            return false;
        }
        if(!infoService.checkTemp(temp)){
            return false;
        }
        UserDetail detail = new UserDetail();
        detail.setTemp(temp.toString());
        detail.setIduserDetail(userId);
        int i = detailMapper.updateByPrimaryKeySelective(detail);
        return i > 0;
    }

    public boolean updateRisk(Integer userId, UserBaseMsg msg) {
        if(userId == null || userId == 0){
            return false;
        }
        if(msg.getSymptom() == null){
            return false;
        }
        int res = converter.riskFlagConvert(msg);
        UserDetail detail = new UserDetail();
        detail.setIduserDetail(userId);
        detail.setRiskFlag(res);
        int i = detailMapper.updateByPrimaryKeySelective(detail);
        return i > 0;
    }

    public boolean updateAddr(Integer userId, UserLiveLocation location) {
        UserDetail detail = detailMapper.selectByPrimaryKey(userId);
        boolean exe = true;
        Integer addrId = detail.getAddrId();
        if(addrId == null) {
            addrId = snow.nextIntId();
            detail.setAddrId(addrId);
            exe = false;
        }
        Address address = addressConverter.convertAddree(location,addrId,userId);
        if(address == null) {
            return false;
        }
        int i = 0;
        if(exe) {
            // 记录存在
            i = addrMapper.updateByPrimaryKeySelective(address);
        }else {
            i = addrMapper.insertSelective(address);
        }
        return i > 0;
    }



}
