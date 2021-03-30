package com.trace.api.entity;

/**
 * @author xzp
 * Created on 2021/3/30
 */
public class AFile {
    private String fileid;
    private Number max_age;

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }

    public Number getMax_age() {
        return max_age;
    }

    public void setMax_age(Number max_age) {
        this.max_age = max_age;
    }
}
