package com.trace.controller;

import com.trace.service.user.UserInfoService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author xzp
 * Created on 2021/2/16
 */
@RestController
@RequestMapping("/miniapi")
public class UserInfoController {


    @Autowired
    UserInfoService service;

    @GetMapping("/isfill")
    public Result isFill(String userid) {
        Result result = null;
        int i = 0;
        try {
            i = Integer.parseInt(userid);
        }catch (NumberFormatException e) {
            e.printStackTrace();
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        boolean f = service.isInfoAccessible(i);
        if (f) {
            return Result.success();
        }else {
            return Result.fail(ResultCode.USER_INFO_NOT_COMPLETE);
        }
    }
}
