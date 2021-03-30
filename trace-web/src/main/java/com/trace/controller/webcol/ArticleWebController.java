package com.trace.controller.webcol;

import com.trace.dao.entity.Article;
import com.trace.service.article.ArticleImageService;
import com.trace.service.article.ArticleService;
import com.trace.service.article.SingleArticleService;
import com.trace.service.entity.recentity.ImageRecEntity;
import com.trace.service.entity.retentity.WorkOrderSingleRet;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ArticleImageService imageService;

    @Autowired
    SingleArticleService singleService;

    @GetMapping("/allwo")
    public Result allWorkOrder(){
        List<Article> articles = articleService.allWorkArticle();
        return Result.success(articles);
    }

    @PostMapping("/getimgs")
    public Result imagesAll(@RequestBody ImageRecEntity fileList) {
        if(fileList.getFileList() == null || fileList.getFileList().size() == 0) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        String s = imageService.getImageDownLoadAddr(fileList.getFileList());
        return Result.success(s);
    }

    @GetMapping("/singlewo")
    public Result obtainSingleWo(@RequestParam String aid) {
        Integer a = 0;
        try{
            a = Integer.parseInt(aid);
        }catch (Exception e) {
            Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        if(a == 0) {
            return  Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        WorkOrderSingleRet ret = singleService.dealSingleWorkOrder(a);
        if(ret == null) {
            return Result.fail(ResultCode.WORKORDER_NOT_EXIST);
        }
        return Result.success(ret);
    }
}
