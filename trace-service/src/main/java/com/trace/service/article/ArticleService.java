package com.trace.service.article;

import com.common.utils.SnowflakeIdUtil;
import com.trace.dao.entity.Article;
import com.trace.dao.entity.ArticleExample;
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
import com.trace.service.entity.recentity.WOReplyRec;
import com.trace.service.entity.retentity.ArticleRetEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    SingleArticleService singleArticleService;

    private final Logger LOGGER = LoggerFactory.getLogger(ArticleService.class);

    SnowflakeIdUtil snow = new SnowflakeIdUtil();

    public boolean workOrderNewReply(WOReplyRec wo,WhomEnum whom,ImagePosEnum imagePos) {
        // 参数校验
        if(StringUtils.isBlank(wo.getAid())) {
            return false;
        }
        // 获取文章最后id
        Integer aid = 0;
        try {
            aid = Integer.parseInt(wo.getAid());
        }catch (Exception e) {
            LOGGER.error("aid转整数解析失败--"+wo.getAid());
            return false;
        }
        List<Integer> workOrderIds = singleArticleService.findWorkOrderIds(aid);
        if(workOrderIds == null || workOrderIds.size() == 0) {
            return false;
        }
        Integer last = workOrderIds.get(workOrderIds.size()-1);
        Article oldArticle = articleMapper.selectByPrimaryKey(last);
        // 插入到last尾部
        // 更新原来最后一篇文章
        ArticleRecEntity newArticle = new ArticleRecEntity();
        newArticle.setUserId(oldArticle.getUid());
        newArticle.setContent(wo.getContent());
        newArticle.setHeadLine(wo.getHeadLine());
        newArticle.setImagePaths(wo.getImagePaths());
        Article generalArticle = this.createGeneralArticle(newArticle);
        oldArticle.setNextAid(generalArticle.getAid());
        int i = articleMapper.updateByPrimaryKeySelective(oldArticle);
        if(i <= 0)return false;
        generalArticle.setFirst(false);
        generalArticle.setStatus(oldArticle.getStatus());
        generalArticle.setWhom(WhomConverter.convertWhom(whom));
        generalArticle.setIsArticle(false);
        i = articleMapper.insertSelective(generalArticle);
        boolean f = this.imageListInsert(generalArticle.getAid(),imagePos,wo.getImagePaths());
        return i > 0 && f;
    }

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
        article1.setIsArticle(false);
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
        if(aid < 0) {
            aid += Integer.MAX_VALUE;
        }
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

    public List<ArticleRetEntity> getArticlesByUserId(Integer userId) {
        if(userId == null || userId == 0){
            return new ArrayList<>();
        }
        // 数据库中获取全部文章列表
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andUidEqualTo(userId);
        List<Article> articles = articleMapper.selectByExample(example);
        // 遍历，装入first = 1 的所有文章列表
        if(articles == null || articles.size() == 0) {
            return new ArrayList<>();
        }
        List<ArticleRetEntity> articlesRes = new ArrayList<>();
        for (Article article : articles) {
            if(article.getFirst() != null && article.getFirst()) {
                ArticleRetEntity retEntity = new ArticleRetEntity();
                retEntity.setContent(article.getContent());
                retEntity.setHeadLine(article.getHeadline());
                retEntity.setId(article.getAid());
                retEntity.setTime(article.getTime());
                retEntity.setArticle(article.getIsArticle());
                retEntity.setStatus(article.getStatus());
                articlesRes.add(retEntity);
            }
        }
        // 排序，按照时间反向排序
        this.sortByTime(articlesRes);
        return articlesRes;
    }

    private void sortByTime(List<ArticleRetEntity> ens) {
        ens.sort((o1, o2) -> {
            if (o1.getTime().after(o2.getTime())) {
                return -1;
            } else if (o1.getTime().before(o2.getTime())) {
                return 1;
            } else {
                return 0;
            }
        });
    }

}
