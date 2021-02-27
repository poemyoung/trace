package com.trace.api.addrpentity;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author xzp
 * Created on 2021/2/27
 */
public class AddrResult {
    private String title;
    private Position location;
    private AddrComponents address_components;
    private AdInfo ad_info;
    private Number similarity;
    private Number deviation;
    private Number reliability;
    private Number level;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Position getLocation() {
        return location;
    }

    public void setLocation(Position location) {
        this.location = location;
    }

    public AddrComponents getAddress_components() {
        return address_components;
    }

    public void setAddress_components(AddrComponents address_components) {
        this.address_components = address_components;
    }

    public AdInfo getAd_info() {
        return ad_info;
    }

    public void setAd_info(AdInfo ad_info) {
        this.ad_info = ad_info;
    }

    public Number getSimilarity() {
        return similarity;
    }

    public void setSimilarity(Number similarity) {
        this.similarity = similarity;
    }

    public Number getDeviation() {
        return deviation;
    }

    public void setDeviation(Number deviation) {
        this.deviation = deviation;
    }

    public Number getReliability() {
        return reliability;
    }

    public void setReliability(Number reliability) {
        this.reliability = reliability;
    }

    public Number getLevel() {
        return level;
    }

    public void setLevel(Number level) {
        this.level = level;
    }
}
