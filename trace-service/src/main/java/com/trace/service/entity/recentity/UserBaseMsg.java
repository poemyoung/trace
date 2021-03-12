package com.trace.service.entity.recentity;

import com.trace.service.entity.commentity.UserLiveLocation;
import com.trace.service.entity.commentity.UserSymptom;

/**
 * @author xzp
 * Created on 2021/2/25
 */
public class UserBaseMsg {

    private String userId;
    private String name;
    private String idCard;
    private String phone;
    private UserLiveLocation location;
    private UserSymptom symptom;
    private boolean foreign;
    private boolean highRisk;
    private boolean contactPatient;
    private Number bodyHeat;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserLiveLocation getLocation() {
        return location;
    }

    public void setLocation(UserLiveLocation location) {
        this.location = location;
    }

    public UserSymptom getSymptom() {
        return symptom;
    }

    public void setSymptom(UserSymptom symptom) {
        this.symptom = symptom;
    }

    public boolean isForeign() {
        return foreign;
    }

    public void setForeign(boolean foreign) {
        this.foreign = foreign;
    }

    public boolean isHighRisk() {
        return highRisk;
    }

    public void setHighRisk(boolean highRisk) {
        this.highRisk = highRisk;
    }

    public boolean isContactPatient() {
        return contactPatient;
    }

    public void setContactPatient(boolean contactPatient) {
        this.contactPatient = contactPatient;
    }

    public Number getBodyHeat() {
        return bodyHeat;
    }

    public void setBodyHeat(Number bodyHeat) {
        this.bodyHeat = bodyHeat;
    }
}
