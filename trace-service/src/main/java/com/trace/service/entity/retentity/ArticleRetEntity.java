package com.trace.service.entity.retentity;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/24
 */
public class ArticleRetEntity {
    Integer id;
    String headLine;
    String content;
    Date time;
    Boolean isArticle;
    Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getArticle() {
        return isArticle;
    }

    public void setArticle(Boolean article) {
        isArticle = article;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
