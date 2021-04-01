package com.trace.controller.webcol;

import com.trace.service.entity.recentity.ConditionEntity;
import com.trace.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/4/1
 */
@RestController
@RequestMapping("/webapi")
public class SearchController {

    @PostMapping("/search")
    public Result searchByConditions(@RequestBody ConditionEntity conditions) {
        System.out.println(conditions);
        return Result.success();
    }
}
