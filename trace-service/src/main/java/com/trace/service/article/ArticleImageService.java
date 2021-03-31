package com.trace.service.article;

import com.common.utils.GsonUtils;
import com.trace.api.oss.ImageDeal;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author xzp
 * Created on 2021/3/30
 */
@Service
public class ArticleImageService {
    private ImageDeal deal = new ImageDeal();

    @Cacheable(value = "img_cache",key = "#list")
    public String getImageDownLoadAddr(List<String> list) {
        Map<String, String> map = deal.imageDownloadDefault(list);
        return GsonUtils.toJson(map.values());
    }
}
