package com.trace.service.entity;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/11
 */
public enum  DateEnum {
    FRO_EVER(new Date(253392455349L));

    DateEnum(Date date) {
        this.date = date;
    }

    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
