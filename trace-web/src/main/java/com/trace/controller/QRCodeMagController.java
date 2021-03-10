package com.trace.controller;

import com.trace.entity.UserCardName;
import com.trace.service.entity.UserBaseMsg;
import com.trace.service.qrcode.QRCodeMagService;
import com.trace.service.user.UserNoQRService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/3/10
 */
@RestController
@RequestMapping("/miniapi")
public class QRCodeMagController {

    @Autowired
    QRCodeMagService magService;

    @Autowired
    UserNoQRService noQRService;

    @PostMapping("/userexists")
    public Result isUserExists(@RequestBody UserCardName user) {
        String name = user.getName();
        String idCard = user.getIdCard();
        Integer f = noQRService.isUserExist(name,idCard);
        if(f == null) {
            return Result.fail(ResultCode.USER_NOT_EXIST);
        }else {
            return Result.success(f);
        }
    }
    
    @PostMapping("exeuserfill")
    public Result existUserFill(@RequestBody UserBaseMsg msg) {
        Integer userId = noQRService.isUserExist(msg.getName(),msg.getIdCard());
        if(userId == null) {
            // 走fill 接口生成新用户
        }else {
            // 不生成新用户
        }
        // 将管理信息插入数据库中

        return null;
    }
}
