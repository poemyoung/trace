package com.trace.service.entity;

/**
 * @author xzp
 * Created on 2021/2/25
 */
public class UserSymptom {
    private boolean isSymptom;
    private String type;

    public boolean isIsSymptom() {
        return isSymptom;
    }

    public void setIsSymptom(boolean symptom) {
        isSymptom = symptom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
