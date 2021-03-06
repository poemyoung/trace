package com.trace.api.reverseaddrprs;

/**
 * @author xzp
 * Created on 2021/3/6
 */
public class FormattedAddr {
    private String recommend;
    private String rough;

    public String getRough() {
        return rough;
    }

    public void setRough(String rough) {
        this.rough = rough;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
