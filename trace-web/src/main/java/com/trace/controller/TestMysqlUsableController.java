package com.trace.controller;

import com.trace.service.test.TestMysqlUsableService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/2/26
 */

@RestController
public class TestMysqlUsableController {
    @Autowired
    TestMysqlUsableService service;

    @GetMapping("/testinsert")
    public Result testInsertAva() {
        boolean f = service.isMysqlAvaInsert();
        if(f) {
            return Result.success();
        }else {
            return Result.fail(ResultCode.MYSQL_ERROR);
        }
    }
    public String testRedis(String key,String value) {
        service.setRedisKey(key,value);
        return service.getRedisKey(key);
    }
    public void addRandomData() {
        for (int i = 256;i <=400;i++) {
            service.addRandom(i);
        }
    }
}
