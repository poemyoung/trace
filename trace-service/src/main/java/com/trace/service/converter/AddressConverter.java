package com.trace.service.converter;

import com.trace.api.addrpentity.AddrResult;
import com.trace.api.addrpentity.BaseResult;
import com.trace.api.openid.TencentPosService;
import com.trace.dao.entity.Address;
import com.trace.service.entity.UserLiveLocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.TimeZone;

/**
 * @author xzp
 * Created on 2021/3/11
 */
@Service
public class AddressConverter {
    @Autowired
    TencentPosService posService;

    public Address convertAddree(UserLiveLocation location,Integer addrId,Integer userId) {
        if(StringUtils.isBlank(location.getCity()) || StringUtils.isBlank(location.getProvince())){
            return null;
        }
        String addrReqStr = location.getProvince() + location.getCity()
                + location.getCounty() + location.getDetailAddr();
        String region = StringUtils.isBlank(location.getCounty()) ? location.getCity() : location.getCounty();
        BaseResult result = posService.descParseAddr(addrReqStr,region);
        AddrResult result1 = result.getResult();
        Address address = new Address();
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
        address.setAdcode(Integer.parseInt(result1.getAd_info().getAdcode()));
        address.setCity(location.getCity());
        address.setCounty(location.getCounty());
        address.setDetail(location.getDetailAddr());
        address.setIdaddress(addrId);
        address.setLat(result1.getLocation().getLat().toString());
        address.setLng(result1.getLocation().getLng().toString());
        address.setProvince(location.getProvince());
        address.setTime(new Date());
        address.setUserId(userId);
        return address;
    }
}
