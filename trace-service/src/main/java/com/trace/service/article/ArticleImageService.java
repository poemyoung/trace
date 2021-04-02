package com.trace.service.article;

import com.common.utils.GsonUtils;
import com.common.utils.ImageUtil;
import com.trace.api.oss.ImageDeal;
import com.trace.service.entity.commentity.ImagePosEnum;
import com.trace.service.entity.commentity.WhomEnum;
import com.trace.service.entity.recentity.WOReplyRec;
import com.trace.service.entity.recentity.WoRec;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xzp
 * Created on 2021/3/30
 */
@Service
@CacheConfig(cacheNames = "img_cache")
public class ArticleImageService {
    @Autowired
    ArticleService articleService;

    private ImageDeal deal = new ImageDeal();
    private final Logger LOGGER = LoggerFactory.getLogger(ArticleImageService.class);

    private List<String> imgTmpPaths = new ArrayList<>();

    @Cacheable(value = "img_cache",key = "#list")
    public String getImageDownLoadAddr(List<String> list) {
        Map<String, String> map = deal.imageDownloadDefault(list);
        return GsonUtils.toJson(map.values());
    }

    public boolean storeImage(MultipartFile file, WoRec wo) {
        if(wo.getImgNum() == null){
            return false;
        }
        String path = ImageUtil.imageStore(file);
        if(StringUtils.isNotBlank(path)) {
            imgTmpPaths.add(path);
        }
        // 如果大于等于size就插入
        if(imgTmpPaths.size() >= wo.getImgNum()) {
            if(wo.isWo()) {
                WOReplyRec reply = new WOReplyRec();
                reply.setAid(wo.getAid());
                reply.setContent(wo.getContent());
                reply.setImagePaths(imgTmpPaths.toArray(String[]::new));
                reply.setHeadLine(wo.getHeadline());
                boolean f = articleService.workOrderNewReply(reply, WhomEnum.ADMIN, ImagePosEnum.SERVER);
                imgTmpPaths.clear();
                return f;
            }else {
                // 文章插入
            }
        }
        return true;
    }
}
