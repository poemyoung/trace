package com.trace.service.redis;

/**
 * @author xzp
 * Created on 2021/3/20
 */
public enum CacheNames {
    ADDR_CACHE("addr_cache");

    CacheNames(String s) {
        this.cacheName = s;
    }
    private String cacheName;

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }
}
