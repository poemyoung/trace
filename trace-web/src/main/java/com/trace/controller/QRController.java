package com.trace.controller;

import com.common.utils.AESUtil;
import com.common.utils.GsonUtils;
import com.trace.service.entity.recentity.Pos;
import com.trace.service.entity.recentity.StaticCodeMag;
import com.trace.service.entity.recentity.UserId;
import com.trace.service.entity.retentity.QRHealthyEntity;
import com.trace.service.entity.retentity.UserStaticCode;
import com.trace.service.health.HealthyService;
import com.trace.service.qrcode.QRCodeMagService;
import com.trace.service.qrcode.QRCodeService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/miniapi")
public class QRController {
    final Logger logger = LoggerFactory.getLogger(QRController.class);

    @Autowired
    QRCodeMagService qrCodeMagService;

    @Autowired
    QRCodeService service;

    @Autowired
    HealthyService hService;

    @Autowired
    QRCodeMagService magService;


    @PostMapping("/qrupload")
    public Result isSafety(@RequestBody UserId res) {
        // 解析出结果
        String uid = "";
        byte[] decode = Base64.getDecoder().decode(res.getUserId());
        try {
            String json = AESUtil.aesDeCodeDef(decode);
            logger.info(json);
            res = GsonUtils.fromJson(json,UserId.class);
            if(res == null){
                return Result.fail(ResultCode.QRCODE_NOT_REC);
            }
            uid = res.getUserId();
        }catch (Exception e) {
            logger.error("ase解密失败");
            return Result.fail(ResultCode.QRCODE_NOT_REC);
        }
        int a = 0;
        try {
             a = Integer.parseInt(uid);
        }catch (Exception e) {
            return Result.fail(ResultCode.USER_NOT_EXIST);
        }
        // 交给service 处理结果
        int i = hService.howHealth(a);
        if(i <= 0 ){
            return Result.fail(ResultCode.QRCODE_NOT_REC);
        }
        return Result.success(i);
    }

    @PostMapping("/qrdyn")
    public Result getQRCode(@RequestBody Pos pos) {
        // 将userId 交给 service生成二维码，返回当前生成二维码的链接、状态、上次定位时间
        int a = 0;
        try {
            a = Integer.parseInt(pos.getUserId());
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        QRHealthyEntity qrEntity = service.generateQRCode(a);
        if (qrEntity == null) {
            return Result.fail(ResultCode.PARAM_IS_BLANK);
        }
        return Result.success(qrEntity);
    }

    @GetMapping("/qrcodemanage")
    public Result getManageStaticCode(@RequestParam String userId) {
        int a = 0;
        try {
            a = Integer.parseInt(userId);
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        // service 处理生成静态码实体类
        List<UserStaticCode> staticCodes = magService.getUserStaticCode(a);
        if(staticCodes == null) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success(staticCodes);
    }
    @PostMapping("/delqrcodemanage")
    public Result delStaticCode(@RequestBody StaticCodeMag mag) {
        int a = 0,b = 0;
        try {
            a = Integer.parseInt(mag.getUserId());
            b = Integer.parseInt(mag.getUserMagId());
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        // service 删除相关联的关系
        boolean f = qrCodeMagService.deleteRelate(b,a);
        if(!f) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success();
    }
}
