package com.trace.controller;

import com.trace.service.qrcode.QRCodeService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// unfinished
@RestController
@RequestMapping("/miniapi")
public class QRController {

    @Autowired
    QRCodeService service;

    @PostMapping("/qrupload")
    public Result isSafety(@RequestBody String res) {
        // 解析出结果

        // 交给service 处理结果


        // 返回码的颜色 1 2 3 4 绿 蓝 黄 红 安全
        // 未见异常 上次填报时间过久 居家隔离 集中观察
        System.out.println(res);

        return Result.success(3);
    }

    @PostMapping
    public Result getQRCode(@RequestBody String userId) {
        // 将userId 交给 service生成二维码，返回当前生成二维码的链接
        int a = 0;
        try {
            a = Integer.parseInt(userId);
        }catch (Exception e) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        String s = service.generateQRCode(a);
        if (StringUtils.isBlank(s)) {
            return Result.fail(ResultCode.PARAM_IS_BLANK);
        }
        return Result.success(s);
    }

}
