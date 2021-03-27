package com.trace.service.entity.recentity;

/**
 * @author xzp
 * Created on 2021/3/27
 */
public class WOReplyRec {
    private Integer aid;
    private  String headLine;
    private String content;
    private String[] imagePaths;

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

    public String[] getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String[] imagePaths) {
        this.imagePaths = imagePaths;
    }
}
