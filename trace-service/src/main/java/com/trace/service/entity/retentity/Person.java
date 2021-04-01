package com.trace.service.entity.retentity;

/**
 * @author xzp
 * Created on 2021/4/1
 */
public class Person {
    private Integer uid;
    private String name;
    private String cardId;
    private String livePlace;
    private Boolean symptom;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public Boolean getSymptom() {
        return symptom;
    }

    public void setSymptom(Boolean symptom) {
        this.symptom = symptom;
    }
}
