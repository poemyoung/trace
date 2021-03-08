package com.trace.service.qrcode;

import com.common.enums.Colors;
import com.common.utils.AESUtil;
import com.common.utils.GsonUtils;
import com.common.utils.QRCode;
import com.trace.service.entity.QREntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xzp
 * Created on 2021/3/7
 */
// unfinished
@Service
public class QRCodeService {
    private final Logger logger = LoggerFactory.getLogger(QRCodeService.class);

    public String generateQRCode(Integer userId) {
        if (userId == null || userId == 0) {
            return "";
        }
        // 判断健康状况
        int i = this.howHealth(userId);
        // 根据健康状况生成二维码
        String s = this.generateByHealthDyn(userId,i);
        return s;
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
        String json = GsonUtils.toJson(entity);
        return this.generate(json,hClass);
    }

    private String generateByHealthStatic(int userId, int hClass) {
       // 生成静态健康码
        return "";
    }

    private String generate(String json,int hClass) {
        Colors colors;
        switch (hClass) {
            case 1:
                colors = Colors.GREEN;
                break;
            case 2:
                colors = Colors.BLUE;
                break;
            case 3:
                colors = Colors.YELLOW;
                break;
            case 4:
                colors = Colors.RED;
                break;
            default:
                colors = Colors.DEFAULT;
                break;
        }
        // json 字符串加密
        byte[] bytes = null;
        try {
            bytes = AESUtil.aesEncodeDef(json);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("aes 加密失败");
        }
        String qrStr = Base64.getEncoder().encodeToString(bytes);
        return  generateQRPath(qrStr,colors);
    }

    private String generateQRPath(String s,Colors color) {
        BufferedImage image = QRCode.createImageByColor(s, color);
        ClassPathResource classPathResource = new ClassPathResource("/public/qrcode");

        try {
            File f = classPathResource.getFile();
            ImageIO.write(image,"jpg",f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "hh";
    }
}
