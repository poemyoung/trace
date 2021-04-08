package com.trace.dao.entity;

public class Popularize {
    private Integer idpop;

    private String headline;

    private String content;

    public Integer getIdpop() {
        return idpop;
    }

    public void setIdpop(Integer idpop) {
        this.idpop = idpop;
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
}