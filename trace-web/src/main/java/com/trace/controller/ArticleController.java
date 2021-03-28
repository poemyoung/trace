package com.trace.controller;

import com.trace.service.article.ArticleService;
import com.trace.service.article.SingleArticleService;
import com.trace.service.entity.commentity.ImagePosEnum;
import com.trace.service.entity.commentity.WhomEnum;
import com.trace.service.entity.recentity.ArticleRecEntity;
import com.trace.service.entity.recentity.EndWorkOrderEntity;
import com.trace.service.entity.recentity.WOReplyRec;
import com.trace.service.entity.retentity.WorkOrderSingleRet;
import com.trace.util.Result;
import com.trace.util.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

/**
 * @author xzp
 * Created on 2021/3/22
 */
@RestController
@RequestMapping("/miniapi")
public class ArticleController {

    private final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;

    @Autowired
    SingleArticleService singleService;

    @PostMapping("/wosubmit")
    public Result submitWorkFlow(@RequestBody ArticleRecEntity article) {
        boolean f = articleService.workOrderSubFirst(article);
        if(f) {
            return Result.success();
        }else {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
    }

    @GetMapping("/artobtain")
    public Result obtainArticles(@RequestParam Integer userId) {
        if(userId == null || userId == 0) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        return Result.success(articleService.getArticlesByUserId(userId));
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

    @PostMapping("/endwo")
    public Result endWorkOrder(@RequestBody EndWorkOrderEntity woEnd) {
        if(woEnd == null || woEnd.getAid() == null || woEnd.getEva() == null) {
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        boolean b = singleService.endWorkOrder(woEnd.getAid(), woEnd.getEva());
        return b ? Result.success() : Result.fail(ResultCode.PARAM_IS_INVALID);
    }
    @PostMapping("/newreply")
    public Result newReply(@RequestBody WOReplyRec newReply) {

        boolean f = articleService.workOrderNewReply(newReply, WhomEnum.ADMIN, ImagePosEnum.WEAPP);
        if(f) {
            return Result.success();
        }else {
            return Result.fail(ResultCode.WORKORDER_NOT_EXIST);
        }
    }

    @GetMapping("/delarticle")
    public Result delArticle(@RequestParam String aid) {
        Integer a = 0;
        try{
            a = Integer.parseInt(aid);
        }catch (Exception e) {
            LOGGER.error("参数解析失败！aid=" + aid);
            return Result.fail(ResultCode.PARAM_IS_INVALID);
        }
        boolean b = singleService.delArticle(a);
        return b ? Result.success() : Result.fail(ResultCode.WORKORDER_NOT_EXIST);
    }
}
