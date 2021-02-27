package com.trace.dao.entity;

public class UserDetail {
    private Integer iduserDetail;

    private String phone;

    private Integer addrId;

    private Integer riskFlag;

    private String temp;

    public Integer getIduserDetail() {
        return iduserDetail;
    }

    public void setIduserDetail(Integer iduserDetail) {
        this.iduserDetail = iduserDetail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public Integer getRiskFlag() {
        return riskFlag;
    }

    public void setRiskFlag(Integer riskFlag) {
        this.riskFlag = riskFlag;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp == null ? null : temp.trim();
    }
}