package com.trace.controller.webcol;

import com.trace.service.entity.recentity.ConditionEntity;
import com.trace.service.entity.retentity.Person;
import com.trace.service.health.SearchService;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/1
 */
@RestController
@RequestMapping("/webapi")
public class SearchController {
    @Autowired
    SearchService searchService;

    @PostMapping("/search")
    public Result searchByConditions(@RequestBody ConditionEntity conditions) {
        if(conditions == null) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        List<Person> search = searchService.search(conditions);
        return Result.success(search);
    }
}