package com.trace.controller;

import com.trace.service.UserIdConvertService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@RestController
@RequestMapping("/miniapi")
public class UserLoginController {

    @Autowired
    UserIdConvertService service;

    @GetMapping("/login")
    public Result loginIn(String openId){
        Integer id = service.codeCovert(openId);
        if(id != null){
            return Result.success(id);
        }else {
            return Result.fail(ResultCode.PARAM_IS_BLANK);
        }
    }
}
