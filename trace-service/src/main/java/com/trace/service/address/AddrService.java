package com.trace.service.address;

import com.common.utils.SnowflakeIdUtil;
import com.common.utils.TimeFormatUtil;
import com.trace.api.addrpentity.AddrComponents;
import com.trace.api.openid.TencentPosService;
import com.trace.api.reverseaddrprs.RevBaseMsg;
import com.trace.api.reverseaddrprs.RevResult;
import com.trace.dao.entity.*;
import com.trace.dao.repository.AddressMapper;
import com.trace.dao.repository.UserAndAddrMapper;
import com.trace.service.entity.commentity.DateEnum;
import com.trace.service.entity.retentity.AddrRetEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author xzp
 * Created on 2021/3/6
 */
@Service
public class AddrService {
    @Autowired
    TencentPosService posService;

    @Autowired
    AddressMapper mapper;

    @Autowired
    UserAndAddrMapper uaaMapper;


    public List<AddrRetEntity> obtainAddrs(Integer userId) {
        if(userId == null || userId == 0) {
            return null;
        }
        List<AddrRetEntity> list = new LinkedList<>();
        UserAndAddrExample example = new UserAndAddrExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserAndAddr> userAndAddrs = uaaMapper.selectByExample(example);
        if(userAndAddrs == null){
            return null;
        }
        userAndAddrs.sort((o1, o2) -> {
            if(o1.getTime().before(o2.getTime())) {
                return -1;
            }else if(o2.getTime().before(o1.getTime())) {
                return 1;
            }else {
                return 0;
            }
        });
        for (UserAndAddr uaa : userAndAddrs) {
            Integer i = uaa.getAid();
            if(i == null) {
                return null;
            }
            Address address = mapper.selectByPrimaryKey(i);
            if(address == null) {
                continue;
            }
            String desc = (StringUtils.isBlank(address.getProvince()) ? "" : address.getProvince())
                    + (StringUtils.isBlank(address.getCity()) ? "" : address.getCity())
                    + (StringUtils.isBlank(address.getCounty()) ? "" : address.getCounty())
                    + (StringUtils.isBlank(address.getDetail()) ? "" : address.getDetail());
            String time = TimeFormatUtil.formatTime(uaa.getTime());
            AddrRetEntity aret = new AddrRetEntity();
            aret.setPlace(desc);
            aret.setTime(time);
            list.add(aret);
        }
        return list;
    }

    public int addrInsert(Number lat,Number lng,Integer userId,Date date) {
        // 参数校验
        if(lat == null || lng == null || userId == null) {
            return 0;
        }
        // addrId 生成
        RevBaseMsg baseMsg = posService.latLongToAddr(lat,lng);
        RevResult res = baseMsg.getResult();
        AddrComponents cps = res.getAddress_component();
        SnowflakeIdUtil snow = new SnowflakeIdUtil();
        int addrId = snow.nextIntId();
        if(addrId < 0)addrId += Integer.MAX_VALUE;
        AddressExample example = new AddressExample();
        example.createCriteria().andIdaddressEqualTo(addrId);
        for (;mapper.selectByExample(example).size() > 0;example.createCriteria().andIdaddressEqualTo(addrId+1));
        Address address = new Address();
        address.setUserId(userId);
        address.setTime(date);
        address.setProvince(cps.getProvince());
        address.setLng(lng.toString());
        address.setLat(lat.toString());
        address.setIdaddress(addrId);
        if(res.getFormatted_addresses() == null) {
            address.setDetail("");
        }else {
            address.setDetail(res.getFormatted_addresses().getRecommend());
        }
        address.setCounty(cps.getDistrict());
        if(res.getAd_info().getAdcode() != null) {
            address.setAdcode(Integer.parseInt(res.getAd_info().getAdcode()));
        }
        address.setCity(cps.getCity());
        int i = mapper.insertSelective(address);
        if (i > 0){
            return addrId;
        }else {
            return 0;
        }
    }

    public Date getLastLocateTime(Integer userId) {
        // 根据userId 获取用户上一次定位的时间
        if(userId == null || userId == 0) {
            return DateEnum.FRO_EVER.getDate();
        }
        UserAndAddrExample example = new UserAndAddrExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserAndAddr> userAndAddrs = uaaMapper.selectByExample(example);
        // 选出最后的时间
        if(userAndAddrs.size() == 0) {
            return DateEnum.FRO_EVER.getDate();
        }
        UserAndAddr max = Collections.max(userAndAddrs, (o1, o2) -> {
            Date d1 = o1.getTime();
            Date d2 = o2.getTime();
            if (d1 == null || d1.before(d2)) {
                return -1;
            } else if (d1.after(d2)) {
                return 1;
            } else {
                return 0;
            }
        });
        return max.getTime();
    }
}
