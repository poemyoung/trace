package com.trace.service.entity.retentity;

import java.util.Date;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/27
 */
public class WorkOrderSingleRet {
    List<WorkOrderRet> wos;
    String statusDesc;
    Date lastTime;
    Float eva;

    public List<WorkOrderRet> getWos() {
        return wos;
    }

    public void setWos(List<WorkOrderRet> wos) {
        this.wos = wos;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Float getEva() {
        return eva;
    }

    public void setEva(Float eva) {
        this.eva = eva;
    }
}
