package com.trace.controller.webcol;

import com.trace.service.health.MarkService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/7
 */
@RestController
@RequestMapping("/webapi")
public class HealthController {

    @Autowired
    MarkService service;

    @GetMapping("/mknormal")
    public Result markNormal(@RequestParam List<Integer> list) {
        if(list == null || list.size() == 0) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        service.mark(list,false);
        return Result.success();
    }

    @GetMapping("/mkbad")
    public Result markBad(@RequestParam List<Integer> list) {
        if(list == null || list.size() == 0) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        service.mark(list,true);
        return Result.success();
    }
}
