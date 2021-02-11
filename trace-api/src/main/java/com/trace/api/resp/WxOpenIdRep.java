package com.trace.api.resp;

/**
 * @author xzp
 * Created on 2021/2/11
 */
public class WxOpenIdRep {
    String session_key;

    String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
