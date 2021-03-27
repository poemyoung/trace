package com.trace.service.entity.retentity;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/27
 */
public class WorkOrderRetEntity {
    Integer aid;
    String headLine;
    String content;
    Boolean whom;
    String time;
    List<String> images;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getWhom() {
        return whom;
    }

    public void setWhom(Boolean whom) {
        this.whom = whom;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
