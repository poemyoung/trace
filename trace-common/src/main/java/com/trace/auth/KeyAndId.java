package com.trace.auth;

public enum  KeyAndId {
    QR_SCAN("e3M0uDo9KmcxUrKtYQemkdGZ","dGgd7RA2VGuXS6qXfwzdBNdZBGoTwtpL");
    //api id and secret key
    private String id;
    private String key;
    KeyAndId(String id,String key){
        this.id = id;
        this.key = key;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }


}
