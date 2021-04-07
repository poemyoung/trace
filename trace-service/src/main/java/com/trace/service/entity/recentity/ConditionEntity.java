package com.trace.service.entity.recentity;

import java.util.Date;

/**
 * @author xzp
 * Created on 2021/4/1
 */
public class ConditionEntity {
    private String livePlace;
    private String name;
    private String cardId;
    private Boolean symptom;

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
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

    public Boolean getSymptom() {
        return symptom;
    }

    public void setSymptom(Boolean symptom) {
        this.symptom = symptom;
    }
}
