package com.trace.api.openid;

import com.trace.api.resp.WxOpenIdRep;
import com.trace.utils.GsonUtils;
import com.trace.utils.HttpGetUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xzp
 * Created on 2021/2/11
 */
@Service
public class OpenIdApi {
    HttpGetUtil httpUtil = new HttpGetUtil();
    private final String APP_ID = "wx4a8e2e034ebf0b4a";
    private final String APP_SECRET = "0ff750461d0a9d8e7eef87809c74eed5";
    private final String GRANT_TYPE = "authorization_code";

    public String getOpenIdByCode(String code) throws Exception {
        Map<String,String> params = new HashMap<>();
        String openIdUrl = "https://api.weixin.qq.com/sns/jscode2session";
        params.put("appid",APP_ID);
        params.put("secret",APP_SECRET);
        params.put("js_code",code);
        params.put("grant_type",GRANT_TYPE);
        String res = httpUtil.doGet(openIdUrl,params);
        // JSON 转换为字符串
        return this.parseOpenId(res);
    }

    private String parseOpenId(String s) {
        WxOpenIdRep rep = new WxOpenIdRep();
        rep.setOpenid("0");
        try {
            rep = GsonUtils.fromJson(s, WxOpenIdRep.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rep.getOpenid();
    }
}
