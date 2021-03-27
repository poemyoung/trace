package com.trace.service.article;

import com.trace.dao.entity.Article;
import com.trace.dao.entity.ArticleImage;
import com.trace.dao.entity.ArticleImageExample;
import com.trace.dao.repository.ArticleImageMapper;
import com.trace.dao.repository.ArticleMapper;
import com.trace.service.converter.StatusConverter;
import com.trace.service.entity.commentity.StatusEnum;
import com.trace.service.entity.retentity.WorkOrderRet;
import com.trace.service.entity.retentity.WorkOrderSingleRet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/27
 */
@Service
public class SingleArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleImageMapper imageMapper;

    public boolean endWorkOrder(Integer aid,Float eva) {
        if(aid == null || aid == 0) {
            return false;
        }
        List<Integer> workOrderIds = this.findWorkOrderIds(aid);
        if(workOrderIds == null || workOrderIds.size() == 0){
            return false;
        }
        for (Integer id : workOrderIds) {
            Article article = new Article();
            article.setAid(id);
            article.setEvaluate(eva+"");
            article.setStatus(StatusConverter.convertStatus(StatusEnum.READED_HANDLE));
            int i = articleMapper.updateByPrimaryKeySelective(article);
            if(i <= 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findWorkOrderIds(Integer aid) {
        List<Integer> aidList = new LinkedList<>();
        Article article = articleMapper.selectByPrimaryKey(aid);
        if(article!=null) {
            aidList.add(aid);
        }else {
            return new ArrayList<>();
        }
        Integer nextAid = article.getNextAid();
        while (nextAid != null) {
            article = articleMapper.selectByPrimaryKey(nextAid);
            aidList.add(nextAid);
            nextAid = article.getNextAid();
        }
        return aidList;
    }

    public WorkOrderSingleRet dealSingleWorkOrder(Integer aid) {
        if (aid == null || aid == 0) {
            return null;
        }
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (article == null) {
            return null;
        }
        List<WorkOrderRet> wos = this.workOrdersById(aid);
        Date lastTime = this.worOrderLastTime(aid);
        Float eva = ((Integer) 0).floatValue();
        String stausDesc = this.workOrderStatusDesc(aid);
        if (stausDesc.equals("已结单")) {
            eva = this.workOrderRate(aid);
        }
        WorkOrderSingleRet ret = new WorkOrderSingleRet();
        ret.setEva(eva);
        ret.setStatusDesc(stausDesc);
        ret.setLastTime(lastTime);
        ret.setWos(wos);
        return ret;
    }

    // 获取上次回复时间
    public Date worOrderLastTime(Integer aid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (article == null) {
            return null;
        }
        int count = 0;
        Integer next = article.getNextAid();
        while (next != null && count++ < 15) {
            article = articleMapper.selectByPrimaryKey(article.getNextAid());
            next = article.getNextAid();
        }
        return article.getTime();
    }

    // 获取状态描述
    public String workOrderStatusDesc(Integer aid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (article == null) {
            return "";
        }
        StatusEnum status = StatusConverter.covertInt(article.getStatus());
        switch (status) {
            case READED_HANDLE:
                return "已结单";
            case UNHANDLE:
                return "未处理";
            case UNREAD_MEUNHANDLE:
                return "待我处理";
        }
        return "未处理";
    }


    // 获取评分,未结单返回null
    public Float workOrderRate(Integer aid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (article == null) {
            return null;
        }
        if (article.getEvaluate() == null) {
            return null;
        }
        if (StatusConverter.covertInt(article.getStatus()) == StatusEnum.READED_HANDLE) {
            // 已结单，拉取评分
            return Float.parseFloat(article.getEvaluate());
        }
        return null;
    }

    // 获取全部文章list
    public List<WorkOrderRet> workOrdersById(Integer aid) {
        List<WorkOrderRet> wos = new LinkedList<>();
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (article == null) {
            return new ArrayList<>();
        }
        wos.add(covertArticle(article));
        Integer next = article.getNextAid();
        int count = 0;
        while (next != null && count++ < 15) {
            article = articleMapper.selectByPrimaryKey(article.getNextAid());
            wos.add(this.covertArticle(article));
            next = article.getNextAid();
        }
        return wos;
    }

    public WorkOrderRet covertArticle(Article article) {
        if (article == null) {
            return null;
        }
        WorkOrderRet wo = new WorkOrderRet();
        wo.setAid(article.getAid());
        wo.setHeadLine(article.getHeadline());
        wo.setContent(article.getContent());
        wo.setWhom(article.getWhom());
        wo.setTime(article.getTime());
        // find images;
        ArticleImageExample example = new ArticleImageExample();
        example.createCriteria().andAidEqualTo(article.getAid());
        List<ArticleImage> articleImages = imageMapper.selectByExample(example);
        List<String> images = new ArrayList<>();
        for (ArticleImage img : articleImages) {
            images.add(img.getPath());
        }
        wo.setImages(images);
        return wo;
    }

}
