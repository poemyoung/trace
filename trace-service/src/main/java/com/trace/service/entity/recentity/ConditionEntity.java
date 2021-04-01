package com.trace.service.entity.recentity;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/4/1
 */
public class ConditionEntity {
    private Date startDate;
    private Date endDate;
    private String livePlace;
    private String passPlace;
    private String name;
    private String cardId;
    private String symptom;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public String getPassPlace() {
        return passPlace;
    }

    public void setPassPlace(String passPlace) {
        this.passPlace = passPlace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    @Override
    public String toString() {
        return "ConditionEntity{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", livePlace='" + livePlace + '\'' +
                ", passPlace='" + passPlace + '\'' +
                ", name='" + name + '\'' +
                ", cardId='" + cardId + '\'' +
                ", symptom='" + symptom + '\'' +
                '}';
    }
}
