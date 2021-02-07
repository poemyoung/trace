package com.trace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@RestController
public class UserLoginController {



    @GetMapping("/login")
    public String loginIn(String openId){
        return "hhh";
    }
}
