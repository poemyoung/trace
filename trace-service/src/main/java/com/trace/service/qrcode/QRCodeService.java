package com.trace.service.qrcode;

import com.common.utils.GsonUtils;
import com.trace.service.entity.QREntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/7
 */
// unfinished
@Service
public class QRCodeService {

    public String generateQRCode(Integer userId) {
        if (userId == null || userId == 0) {
            return "";
        }
        // 判断健康状况
        int i = this.howHealth(userId);
        // 根据健康状况生成二维码
        String s = this.generateByHealthDyn(userId,i);
        return "";
    }

    private int howHealth(Integer userId) {
        // 查数据库判断健康状况 1 2 3 4

        return 1;
    }

    private String generateByHealthDyn(int userId, int hClass) {
        Timestamp now = new Timestamp(new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,14);
        Timestamp expire = new Timestamp(calendar.getTimeInMillis());
        QREntity entity = new QREntity();
        entity.setCreateTime(now);
        entity.setExpireTime(expire);
        entity.setUserId(userId);
    }

    private String generateByHealthStatic(int userId, int hClass) {
       // 生成静态健康码
        return "";
    }

    private String generate(String json) {

    }
}
