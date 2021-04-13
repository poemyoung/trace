package com.trace.controller;

import com.trace.service.coldchain.ColdChainService;
import com.trace.service.entity.recentity.ColdChainBaseMsg;
import com.trace.service.entity.recentity.Pos;
import com.trace.service.entity.retentity.ChainQrRet;
import com.trace.service.entity.retentity.ChargoRet;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@RestController
@RequestMapping("/miniapi")
public class ColdChainController {

    @Autowired
    ColdChainService service;

    @PostMapping("/upchain")
    public Result upChain(@RequestBody ColdChainBaseMsg baseMsg) {
        // 参数校验
        if(baseMsg == null || StringUtils.isBlank(baseMsg.getCompany())) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        ChainQrRet ret = service.declareAColdChain(baseMsg);
        if(ret == null) {
            return Result.fail(ResultCode.MYSQL_ERROR);
        }else {
            return Result.success(ret);
        }
    }

    @PostMapping("/chargolocate")
    public Result locateChargo(@RequestBody Pos position) {
        // 参数校验
        if(position == null || position.getLatitude() == null || position.getLongitude() == null) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        boolean b = service.insertCharGoAddress(position);
        return b ? Result.success() : Result.fail(ResultCode.MYSQL_ERROR);
    }

    @GetMapping("/chargoinfojson")
    public Result getChargoInfo(@RequestParam String chargo) {
        if(StringUtils.isBlank(chargo)) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        ChargoRet chargoRet = service.parseCharGoInfo(chargo);
        if(chargoRet == null) {
            return Result.fail(ResultCode.QRCODE_NOT_REC);
        }
        return Result.success(chargoRet);
    }
}
