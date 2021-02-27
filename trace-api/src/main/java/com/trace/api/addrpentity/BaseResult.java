package com.trace.api.addrpentity;

/**
 * @author xzp
 * Created on 2021/2/27
 */
public class BaseResult {
    private Number status;
    private String message;
    private AddrResult result;

    public Number getStatus() {
        return status;
    }

    public void setStatus(Number status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AddrResult getResult() {
        return result;
    }

    public void setResult(AddrResult result) {
        this.result = result;
    }
}
