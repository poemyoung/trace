package com.trace.api.reverseaddrprs;

import com.trace.api.addrpentity.AdInfo;
import com.trace.api.addrpentity.AddrComponents;

/**
 * @author xzp
 * Created on 2021/3/6
 */
public class RevResult {
    private String address;
    private FormattedAddr formatted_addresses;
    private AddrComponents address_component;
    private AdInfo ad_info;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public FormattedAddr getFormatted_addresses() {
        return formatted_addresses;
    }

    public void setFormatted_addresses(FormattedAddr formatted_addresses) {
        this.formatted_addresses = formatted_addresses;
    }

    public AddrComponents getAddress_component() {
        return address_component;
    }

    public void setAddress_component(AddrComponents address_component) {
        this.address_component = address_component;
    }

    public AdInfo getAd_info() {
        return ad_info;
    }

    public void setAd_info(AdInfo ad_info) {
        this.ad_info = ad_info;
    }
}
