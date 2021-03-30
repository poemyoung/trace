package com.trace.api.oss;

import com.common.utils.GsonUtils;
import com.common.utils.HttpGetUtil;
import com.common.utils.HttpPostUtil;
import com.trace.api.entity.AFile;
import com.trace.api.entity.ImageReq;
import com.trace.api.entity.TokenEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public String getAccessToken(){
        Map<String,String> map = new HashMap<>();
        map.put("grant_type",GRANT_TYPE);
        map.put("appid",APP_ID);
        map.put("secret",APP_SECRET);
        String token = "";
        try {
            String s = httpUtil.doGet("https://api.weixin.qq.com/cgi-bin/token", map);
            TokenEntity tokenEntity = GsonUtils.fromJson(s,TokenEntity.class);
            token = tokenEntity.getAccess_token();
        }catch (Exception e) {
            LOGGER.error("小程序token获取失败");
        }
       return token;
    }

   public String imageDownloadAddr(ImageReq req) throws Exception {
        String s = GsonUtils.toJson(req);
       System.out.println(s);
       String post = HttpPostUtil.post("https://api.weixin.qq.com/tcb/batchdownloadfile",
               this.getAccessToken(), s);
       return post;
   }
}
