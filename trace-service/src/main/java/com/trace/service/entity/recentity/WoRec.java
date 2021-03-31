package com.trace.service.entity.recentity;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author xzp
 * Created on 2021/3/31
 */
public class WoRec {
    private String content;
    private String aid;
    private boolean wo;

    @Override
    public String toString() {
        return "WoRec{" +
                "content='" + content + '\'' +
                ", aid='" + aid + '\'' +
                ", wo=" + wo +
                '}';
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public boolean isWo() {
        return wo;
    }

    public void setWo(boolean wo) {
        this.wo = wo;
    }
}
