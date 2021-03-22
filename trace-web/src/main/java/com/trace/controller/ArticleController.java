package com.trace.controller;

import com.trace.service.article.ArticleService;
import com.trace.service.entity.recentity.ArticleRecEntity;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xzp
 * Created on 2021/3/22
 */
@RestController
@RequestMapping("/miniapi")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @PostMapping("/wosubmit")
    public Result submitWorkFlow(@RequestBody ArticleRecEntity article) {
        boolean f = articleService.workOrderSubFirst(article);
        if(f) {
            return Result.success();
        }else {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
    }

}
