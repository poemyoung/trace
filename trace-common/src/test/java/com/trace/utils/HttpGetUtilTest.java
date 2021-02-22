package com.trace.utils;

import com.common.utils.HttpGetUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xzp
 * Created on 2021/2/11
 */
public class HttpGetUtilTest {
    HttpGetUtil getUtil = new HttpGetUtil();

    @Test
    public void testGet() throws Exception {
        Map<String,String> params = new HashMap<>();
        String openIdUrl = "https://api.weixin.qq.com/sns/jscode2session";
        params.put("appid","wx4a8e2e034ebf0b4a");
        params.put("secret","0ff750461d0a9d8e7eef87809c74eed5");
        params.put("js_code","0915Zs000nCaaL1B9C300MePH045Zs0V");
        params.put("grant_type","authorization_code");
        String res = getUtil.doGet(openIdUrl,params);
        System.out.println(res);
    }
}