package com.trace.api.entity;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/30
 */
public class ImageReq {
    List<AFile> file_list;
    private String env;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public List<AFile> getList() {
        return file_list;
    }

    public void setList(List<AFile> file_list) {
        this.file_list = file_list;
    }
}
