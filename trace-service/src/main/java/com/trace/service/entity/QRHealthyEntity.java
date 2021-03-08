package com.trace.service.entity;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/8
 */
public class QRHealthyEntity {
    private String qrUrl;
    private int status;
    private Date lastLocate;

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLastLocate() {
        return lastLocate;
    }

    public void setLastLocate(Date lastLocate) {
        this.lastLocate = lastLocate;
    }
}
