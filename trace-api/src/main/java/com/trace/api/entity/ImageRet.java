package com.trace.api.entity;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/30
 */
public class ImageRet {
    private Number errcode;
    private String errmsg;
    private List<AImageRet> file_list;

    public Number getErrcode() {
        return errcode;
    }

    public void setErrcode(Number errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public List<AImageRet> getFile_list() {
        return file_list;
    }

    public void setFile_list(List<AImageRet> file_list) {
        this.file_list = file_list;
    }
}
