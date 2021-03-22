package com.trace.service.article;

import com.common.utils.SnowflakeIdUtil;
import com.trace.dao.entity.Article;
import com.trace.dao.entity.ArticleImage;
import com.trace.dao.repository.ArticleImageMapper;
import com.trace.dao.repository.ArticleMapper;
import com.trace.service.converter.ImagePosConverter;
import com.trace.service.converter.StatusConverter;
import com.trace.service.converter.WhomConverter;
import com.trace.service.entity.commentity.ImagePosEnum;
import com.trace.service.entity.commentity.StatusEnum;
import com.trace.service.entity.commentity.WhomEnum;
import com.trace.service.entity.recentity.ArticleRecEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/22
 */
@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleImageMapper aiMapper;

    SnowflakeIdUtil snow = new SnowflakeIdUtil();

    public boolean workOrderSubFirst(ArticleRecEntity article) {
        // 参数校验
        if (!this.checkParams(article)) {
            return false;
        }
        // 实体类转换
        Article article1 = this.createGeneralArticle(article);
        // 新建工单
        article1.setFirst(true);
        article1.setStatus(StatusConverter.convertStatus(StatusEnum.UNHANDLE));
        article1.setWhom(WhomConverter.convertWhom(WhomEnum.USER));
        int i = articleMapper.insertSelective(article1);
        boolean f = this.imageListInsert(article1.getAid(),ImagePosEnum.WEAPP,article.getImagePaths());
        return i > 0 && f;
    }

    private boolean imageListInsert(int aid, ImagePosEnum imagePos,String[] imagePaths) {
        if(imagePaths == null || imagePaths.length == 0) {
            return true;
        }
        for (String path : imagePaths) {
            ArticleImage articleImage = new ArticleImage();
            articleImage.setFlag(ImagePosConverter.convertImagePos(imagePos));
            articleImage.setAid(aid);
            articleImage.setPath(path);
            int i = aiMapper.insertSelective(articleImage);
            if(i <= 0) return false;
        }
        return true;
    }

    private Article createGeneralArticle(ArticleRecEntity article) {
        int aid = snow.nextIntId();
        for (; articleMapper.selectByPrimaryKey(aid) != null; aid++) ;
        Article articleDB = new Article();
        articleDB.setAid(aid);
        articleDB.setContent(article.getContent());
        articleDB.setHeadline(article.getHeadLine());
        articleDB.setTime(new Date());
        articleDB.setUid(article.getUserId());
        return articleDB;
    }

    private boolean checkParams(ArticleRecEntity article) {
        Integer userId = article.getUserId();
        if (userId == null || userId == 0) {
            return false;
        }
        if (!this.checkHeadLine(article.getHeadLine()) || !this.checkContent(article.getContent())) {
            return false;
        }
        return true;
    }

    private boolean checkHeadLine(String headline) {
        if (StringUtils.isBlank(headline)) {
            return false;
        }
        return headline.length() >= 4;
    }

    private boolean checkContent(String content) {
        if (StringUtils.isBlank(content)) {
            return false;
        }
        return content.length() <= 500;
    }
}
