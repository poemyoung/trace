package com.trace.controller;

import com.trace.service.test.TestMysqlUsableService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
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
}
