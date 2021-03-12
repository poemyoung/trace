package com.trace.service.entity.recentity;

/**
 * @author xzp
 * Created on 2021/3/6
 */
public class Pos {
    private Number longitude;
    private Number latitude;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }

    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }
}
