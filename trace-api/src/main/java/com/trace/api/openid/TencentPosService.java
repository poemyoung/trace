package com.trace.api.openid;

import com.common.utils.GsonUtils;
import com.common.utils.HttpGetUtil;
import com.trace.api.addrpentity.BaseResult;
import com.trace.api.addrpentity.Position;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xzp
 * Created on 2021/2/27
 */
@Service
public class TencentPosService {
    private final String KEY = "RCEBZ-A3PK2-LGHU2-CWQKI-DVM23-NYBQ6";
    private final String output = "json";
    private HttpGetUtil getUtil = new HttpGetUtil();

    public BaseResult descParseAddr(String address, String region) {
        Map<String,String> map = new HashMap<>();
        map.put("address",address);
        map.put("region",region);
        map.put("key",this.KEY);
        final String baseUrl = "https://apis.map.qq.com/ws/geocoder/v1/";
        String s = "";
        try {
            s = getUtil.doGet(baseUrl,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.parseAddr(s);
    }

    private BaseResult parseAddr(String s) {
       BaseResult result =  GsonUtils.fromJson(s, BaseResult.class);
       return result;
    }
}
