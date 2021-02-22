package com.trace.api.openid;

import com.common.utils.GsonUtils;
import com.trace.api.resp.WxErrorRep;
import com.trace.api.resp.WxOpenIdRep;
import org.junit.Test;

/**
 * @author xzp
 * Created on 2021/2/11
 */
public class OpenIdApiTest {
    OpenIdApi api = new OpenIdApi();
    @Test
    public void doTest() throws Exception {
        String s = api.getOpenIdByCode("031s66Ga1LWdvA09eKIa1RrlDx4s66GM");
        WxOpenIdRep rep = new WxOpenIdRep();
        WxErrorRep errorRep = new WxErrorRep();
        rep = GsonUtils.fromJson(s, WxOpenIdRep.class);
        errorRep = GsonUtils.fromJson(s,WxErrorRep.class);
        System.out.println(rep.getOpenid());
        System.out.println(errorRep.getErrmsg());
    }


}