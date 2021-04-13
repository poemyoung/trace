package com.trace.dao.entity;

import java.util.Date;

public class ChainPass {
    private Integer chainid;

    private Integer addrid;

    private Date time;

    public Integer getChainid() {
        return chainid;
    }

    public void setChainid(Integer chainid) {
        this.chainid = chainid;
    }

    public Integer getAddrid() {
        return addrid;
    }

    public void setAddrid(Integer addrid) {
        this.addrid = addrid;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}