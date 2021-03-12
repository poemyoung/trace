package com.trace.controller;

import com.trace.service.entity.recentity.UserCardName;
import com.trace.service.entity.recentity.UserBaseMsg;
import com.trace.service.entity.recentity.UserRelateEntity;
import com.trace.service.qrcode.QRCodeMagService;
import com.trace.service.user.UserInfoService;
import com.trace.service.user.UserInfoUpdateService;
import com.trace.service.user.UserNoQRService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    UserInfoService infoService;

    @Autowired
    UserInfoUpdateService updateService;

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
    
    @PostMapping("/exeuserfill")
    @Transactional
    public Result existUserFill(@RequestBody UserBaseMsg msg, @RequestParam String id) {
        int a = 0;
        try {
            a = Integer.parseInt(id);
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        Integer userId = noQRService.isUserExist(msg.getName(),msg.getIdCard());
        boolean f = false;
        if(userId == null || userId == 0) {
            // 走fill 接口生成新用户
            userId = noQRService.createDefaultNoPhoneUser();
            msg.setUserId(userId + "");
            // 手机号未填默认使用填报者的手机号
            if(StringUtils.isBlank(msg.getPhone())) {
                String p = infoService.getUserPhone(a);
                if(StringUtils.isBlank(p)) {
                    return Result.fail(ResultCode.USER_NOT_EXIST);
                }
                msg.setPhone(p);
            }
            f = infoService.fillUserInfo(msg,userId);
        }else {
            // 不生成新用户,更新用户信息
            f = updateService.updateExistMsg(userId,msg);
        }
        if(!f) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        // 将管理信息插入数据库中
        boolean res = magService.addRelate(userId,a,msg.getName(),msg.getIdCard());
        if(res) {
            return Result.success();
        }else {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
    }

    @PostMapping("/addrelate")
    public Result addRelate(@RequestBody UserRelateEntity relate) {
        int a = 0;
        try{
            a = Integer.parseInt(relate.getUserId());
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        int b = noQRService.isUserExist(relate.getUserName(),relate.getIdCard());
        boolean f =  magService.addRelate(b,a,relate.getUserName(),relate.getIdCard());
        if(f) {
            return Result.success();
        }else {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
    }
}
