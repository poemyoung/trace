package com.trace.service.address;

import com.trace.api.addrpentity.AddrComponents;
import com.trace.api.openid.TencentPosService;
import com.trace.api.reverseaddrprs.RevBaseMsg;
import com.trace.api.reverseaddrprs.RevResult;
import com.trace.dao.entity.Address;
import com.trace.dao.entity.AddressExample;
import com.trace.dao.entity.UserDetailExample;
import com.trace.dao.repository.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author xzp
 * Created on 2021/3/6
 */
// unfinished
@Service
public class AddrService {
    @Autowired
    TencentPosService posService;

    @Autowired
    AddressMapper mapper;


    public int addrInsert(Number lat,Number lng,Integer userId) {
        // 参数校验
        if(lat == null || lng == null || userId == null) {
            return 0;
        }
        // addrId 生成
        RevBaseMsg baseMsg = posService.latLongToAddr(lat,lng);
        RevResult res = baseMsg.getResult();
        AddrComponents cps = res.getAddress_component();
        int addrId = (cps.getCity()+cps.getProvince()
                +res.getFormatted_addresses().getRecommend()+ UUID.randomUUID()).hashCode();
        if(addrId < 0)addrId += Integer.MAX_VALUE;
        AddressExample example = new AddressExample();
        example.createCriteria().andIdaddressEqualTo(addrId);
        for (;mapper.selectByExample(example).size() > 0;example.createCriteria().andIdaddressEqualTo(addrId+1));
        Address address = new Address();
        address.setUserId(userId);
        address.setTime(new Date());
        address.setProvince(cps.getProvince());
        address.setLng(lng.toString());
        address.setLat(lat.toString());
        address.setIdaddress(addrId);
        address.setDetail(res.getFormatted_addresses().getRecommend());
        address.setCounty(cps.getDistrict());
        address.setAdcode(Integer.parseInt(res.getAd_info().getAdcode()));
        address.setCity(cps.getCity());
        int i = mapper.insertSelective(address);
        if (i > 0){
            return userId;
        }else {
            return 0;
        }
    }

    public Date getLastLocateTime(Integer userId) {
        // 根据userId 获取用户上一次定位的时间
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE,10);
        calendar.set(Calendar.MONTH,2);
        calendar.set(Calendar.SECOND,10);
        calendar.set(Calendar.HOUR,2);
        return new Date(calendar.getTimeInMillis());
    }
}
