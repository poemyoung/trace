package com.trace.dao.entity;

public class CodeManage {
    private Integer userid;

    private Integer magUser;

    private String name;

    private String idCard;

    private String qrcode;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMagUser() {
        return magUser;
    }

    public void setMagUser(Integer magUser) {
        this.magUser = magUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode == null ? null : qrcode.trim();
    }
}