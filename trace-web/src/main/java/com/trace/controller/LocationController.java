package com.trace.controller;

import com.trace.entity.Pos;
import com.trace.service.address.AddrService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/3/6
 */
@RestController
@RequestMapping("/miniapi")
public class LocationController {

    @Autowired
    AddrService service;

    @PostMapping("/loadlocation")
    public Result upLoadLocation(@RequestBody Pos location) {
        int a = 0;
        try{
           a =  Integer.parseInt(location.getUserid());
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        int addrId = service.addrInsert(location.getLatitude(),location.getLongitude(),a);
        if(addrId == 0){
            return Result.fail(ResultCode.SYSTEM_INNER_ERROR);
        }
        // 将addrId 插入进用户-地址id 对应表中


        return Result.success();
    }
}
