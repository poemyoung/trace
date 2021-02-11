package com.trace.api.resp;

/**
 * @author xzp
 * Created on 2021/2/11
 */
public class WxErrorRep {
    String errcode;

    String errmsg;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
