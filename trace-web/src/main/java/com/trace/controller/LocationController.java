package com.trace.controller;

import com.trace.service.entity.recentity.Pos;
import com.trace.service.address.AddrService;
import com.trace.service.address.UserAndAddrService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/6
 */
@RestController
@RequestMapping("/miniapi")
public class LocationController {

    @Autowired
    AddrService service;

    @Autowired
    UserAndAddrService uaaService;

    @PostMapping("/loadlocation")
    public Result upLoadLocation(@RequestBody Pos location) {
        int a = 0;
        try{
           a =  Integer.parseInt(location.getUserId());
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        int addrId = service.addrInsert(location.getLatitude(),location.getLongitude(),a,new Date());
        if(addrId == 0){
            return Result.fail(ResultCode.SYSTEM_INNER_ERROR);
        }
        // 将addrId 插入进用户-地址id 对应表中
        boolean f = uaaService.addUARelate(a,addrId);
        if(!f) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }
}
