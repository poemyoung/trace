package com.trace.service.qrcode;

import org.springframework.stereotype.Service;

/**
 * @author xzp
 * Created on 2021/3/10
 */
@Service
public class QRCodeMagService {


    public boolean deleteRelate(Integer userId,Integer reaUserId) {
        if(userId == null || userId == 0 || reaUserId == null || reaUserId == 0) {
            return false;
        }
        return true;
    }
}
