package com.trace.dao.entity;

public class ColdChain {
    private Integer idcoldchain;

    private String classify;

    private String source;

    private String remark;

    public Integer getIdcoldchain() {
        return idcoldchain;
    }

    public void setIdcoldchain(Integer idcoldchain) {
        this.idcoldchain = idcoldchain;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify == null ? null : classify.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}