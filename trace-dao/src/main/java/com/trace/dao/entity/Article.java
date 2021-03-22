package com.trace.dao.entity;

import java.util.Date;

public class Article {
    private Integer aid;

    private Integer uid;

    private String headline;

    private String content;

    private Date time;

    private Integer status;

    private Integer nextAid;

    private Boolean whom;

    private Boolean first;

    private String evaluate;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline == null ? null : headline.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getNextAid() {
        return nextAid;
    }

    public void setNextAid(Integer nextAid) {
        this.nextAid = nextAid;
    }

    public Boolean getWhom() {
        return whom;
    }

    public void setWhom(Boolean whom) {
        this.whom = whom;
    }

    public Boolean getFirst() {
        return first;
    }

    public void setFirst(Boolean first) {
        this.first = first;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate == null ? null : evaluate.trim();
    }
}