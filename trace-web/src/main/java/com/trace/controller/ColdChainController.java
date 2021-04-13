package com.trace.controller;

import com.trace.service.coldchain.ColdChainService;
import com.trace.service.entity.recentity.ColdChainBaseMsg;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@RestController
@RequestMapping("/miniapi")
public class ColdChainController {

    @Autowired
    ColdChainService service;

    @GetMapping("/upchain")
    public Result upChain(ColdChainBaseMsg baseMsg) {
        // 参数校验
        if(baseMsg == null || baseMsg.getCompany() == null) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        String s = service.declareAColdChain(baseMsg);
        if(StringUtils.isBlank(s)) {
            return Result.fail(ResultCode.MYSQL_ERROR);
        }else {
            return Result.success(s);
        }
    }
}
