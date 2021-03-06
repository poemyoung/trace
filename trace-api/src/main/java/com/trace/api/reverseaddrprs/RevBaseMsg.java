package com.trace.api.reverseaddrprs;

import com.trace.api.addrpentity.AddrResult;

/**
 * @author xzp
 * Created on 2021/3/6
 */
public class RevBaseMsg {
    private Number status;
    private String message;
    private RevResult result;
    private String request_id;

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

    public RevResult getResult() {
        return result;
    }

    public void setResult(RevResult result) {
        this.result = result;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }
}
