package com.trace.service.article;

import com.common.utils.TimeFormatUtil;
import com.google.common.collect.Lists;
import com.trace.dao.entity.Article;
import com.trace.dao.entity.ArticleImage;
import com.trace.dao.entity.ArticleImageExample;
import com.trace.dao.repository.ArticleImageMapper;
import com.trace.dao.repository.ArticleMapper;
import com.trace.service.converter.StatusConverter;
import com.trace.service.entity.commentity.StatusEnum;
import com.trace.service.entity.retentity.WorkOrderRetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // 获取评分,未结单返回null
    public Float workOrderRate(Integer aid) {
        Article article = articleMapper.selectByPrimaryKey(aid);
        if(StatusConverter.covertInt(article.getStatus()) == StatusEnum.READED_HANDLE) {
            // 已结单，拉取评分
            return Float.parseFloat(article.getEvaluate());
        }
        return null;
    }

    // 获取全部文章list
    public List<WorkOrderRetEntity> workOrdersById(Integer aid) {
        List<WorkOrderRetEntity> wos = new LinkedList<>();
        Article article = articleMapper.selectByPrimaryKey(aid);
        wos.add(covertArticle(article));
        Integer next = article.getNextAid();
        while (next != null) {
            article = articleMapper.selectByPrimaryKey(article.getNextAid());
            wos.add(this.covertArticle(article));
            next = article.getNextAid();
        }
        return wos;
    }

    public WorkOrderRetEntity covertArticle(Article article) {
        WorkOrderRetEntity wo = new WorkOrderRetEntity();
        wo.setAid(article.getAid());
        wo.setHeadLine(article.getHeadline());
        wo.setContent(article.getContent());
        wo.setWhom(article.getWhom());
        wo.setTime(TimeFormatUtil.formatTime(article.getTime()));
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
