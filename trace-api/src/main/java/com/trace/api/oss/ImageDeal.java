package com.trace.api.oss;

import com.common.utils.GsonUtils;
import com.common.utils.HttpGetUtil;
import com.common.utils.HttpPostUtil;
import com.trace.api.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import java.util.*;

/**
 * @author xzp
 * Created on 2021/3/30
 */
public class ImageDeal {
    private HttpGetUtil httpUtil = new HttpGetUtil();
    private final String APP_ID = "wx4a8e2e034ebf0b4a";
    private final String APP_SECRET = "0ff750461d0a9d8e7eef87809c74eed5";
    private final String GRANT_TYPE = "client_credential";

    private final Logger LOGGER = LoggerFactory.getLogger(ImageDeal.class);

    public String getAccessToken() {
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", GRANT_TYPE);
        map.put("appid", APP_ID);
        map.put("secret", APP_SECRET);
        String token = "";
        try {
            String s = httpUtil.doGet("https://api.weixin.qq.com/cgi-bin/token", map);
            TokenEntity tokenEntity = GsonUtils.fromJson(s, TokenEntity.class);
            token = tokenEntity.getAccess_token();
        } catch (Exception e) {
            LOGGER.error("小程序token获取失败");
        }
        return token;
    }
    public Map<String,String> imageDownloadDefault(List<String> fileIds) {
        ImageReq req = new ImageReq();
        List<AFile> list = new ArrayList<>();
        for (String fileid : fileIds) {
            AFile f = new AFile();
            f.setFileid(fileid);
            f.setMax_age(72000);
            list.add(f);
        }
        req.setList(list);
        req.setEnv("wenrun-book-6666");
        Map<String, String> map = new HashMap<>();
        try {
            map = this.imageDownloadAddr(req);
        }catch (Exception e) {
            LOGGER.error("云开发图片下载失败" + e.getMessage());
        }
        return map;
    }

    private Map<String,String> imageDownloadAddr(ImageReq req) throws Exception {
        Map<String,String> map = new HashMap<>();
        String s = GsonUtils.toJson(req);
        String res = HttpPostUtil.post("https://api.weixin.qq.com/tcb/batchdownloadfile",
                this.getAccessToken(), s);
        ImageRet image = GsonUtils.fromJson(res, ImageRet.class);
        if (!(image.getErrcode().intValue() == 0)) {
            LOGGER.error("云开发图片下载失败" + image.getErrmsg());
            return new HashMap<>();
        }
        for (AImageRet img : image.getFile_list()) {
            if(!(img.getStatus().intValue() == 0)) {
                LOGGER.error("云开发图片下载失败" + image.getErrmsg());
                return new HashMap<>();
            }
            map.put(img.getFileid(),img.getDownload_url());
        }
        return map;
    }

}
