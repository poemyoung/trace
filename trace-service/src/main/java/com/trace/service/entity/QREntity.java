package com.trace.service.entity;

import java.sql.Timestamp;

/**
 * @author xzp
 * Created on 2021/3/7
 */
public class QREntity {
    private Timestamp createTime;
    private Timestamp expireTime;
    private Integer userId;

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
