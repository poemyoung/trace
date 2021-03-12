package com.trace.controller;

import com.trace.service.entity.retentity.UserBaseBinding;
import com.trace.service.entity.recentity.UserBaseMsg;
import com.trace.service.user.UserInfoService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/fill")
    public Result fillUserInfo(@RequestBody UserBaseMsg msg) {
        String userId = msg.getUserId();
        int i = 0;
        try {
            i = Integer.parseInt(userId);
        }catch (NumberFormatException e) {
            e.printStackTrace();
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        boolean f = service.isInfoAccessible(i);
        if(f) {
            return Result.fail(ResultCode.USER_REP_FILL);
        }else {
            return service.fillUserInfo(msg,i) ?
                    Result.success() : Result.fail(ResultCode.PARAM_IS_INVALID);
        }
    }

    @GetMapping("/getuserbaseinfo")
    public Result getInfo(String userId) {
        int a = 0;
        try {
            a = Integer.parseInt(userId);
        }catch (Exception e){
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        UserBaseBinding msg = service.getUserBaseMsg(a);
        if(msg == null) {
            return Result.fail(ResultCode.USER_NOT_EXIST);
        }
        return Result.success(msg);
    }
}
