package com.trace.controller.webcol;

import com.trace.dao.entity.Article;
import com.trace.service.article.ArticleService;
import com.trace.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/29
 */
@RestController
@RequestMapping("/webapi")
public class ArticleWebController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/allwo")
    public Result allWorkOrder(){
        List<Article> articles = articleService.allWorkArticle();
        return Result.success(articles);
    }
}
