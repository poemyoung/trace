package com.trace.controller.webcol;

import com.trace.service.admin.AdminLoginService;
import com.trace.service.entity.recentity.AdminLogin;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/3/16
 */
@RestController
@RequestMapping("/webapi")
public class LoginWebController {

    private final Logger logger = LoggerFactory.getLogger(LoginWebController.class);

    @Autowired
    AdminLoginService adminLoginService;

    @PostMapping("/login")
    public Result login(@RequestBody AdminLogin admin) {
        if(admin == null) {
            return Result.fail(ResultCode.PARAM_IS_BLANK);
        }
        boolean f = adminLoginService.login(admin.getName(),admin.getPassword());
        return f ? Result.success() : Result.fail(ResultCode.USER_LOGIN_ERROR);
    }
}
