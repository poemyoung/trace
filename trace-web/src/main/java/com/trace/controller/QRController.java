package com.trace.controller;

import com.trace.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miniapi")
public class QRController {

    @PostMapping("/qrupload")
    public Result isSafety(@RequestBody String res) {
        // 解析出结果

        // 交给service 处理结果


        // 返回码的颜色 1 2 3 4 绿 蓝 黄 红 安全
        // 未见异常 上次填报时间过久 居家隔离 集中观察
        System.out.println(res);
        
        return Result.success(1);
    }
}
