package com.trace.service.qrcode;

import com.common.enums.Colors;
import com.common.utils.AESUtil;
import com.common.utils.GsonUtils;
import com.common.utils.QRCode;
import com.trace.service.address.AddrService;
import com.trace.service.entity.commentity.QREntity;
import com.trace.service.entity.retentity.QRHealthyEntity;
import com.trace.service.entity.recentity.UserId;
import com.trace.service.health.HealthyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@Service
public class QRCodeService {
    private final Logger logger = LoggerFactory.getLogger(QRCodeService.class);

    @Autowired
    HealthyService hService;

    @Autowired
    AddrService addrService;

    @Value("${qrcode.path}")
    private String path = "/Users/xzp/Desktop/upload";

    public String generateStaticCode(Integer userId) {
        if (userId == null || userId == 0) {
            return null;
        }
        // 判断健康状况
        int i = hService.howHealth(userId);
        String s = this.generateByHealthStatic(userId,i);
        return s;
    }

    public QRHealthyEntity generateQRCode(Integer userId) {
        if (userId == null || userId == 0) {
            return null;
        }
        // 判断健康状况
        int i = hService.howHealth(userId);
        // 根据健康状况生成二维码
        String s = this.generateByHealthDyn(userId,i);
        // 查找上次定位时间
        Date lastLoc = addrService.getLastLocateTime(userId);
        QRHealthyEntity entity = new QRHealthyEntity();
        entity.setQrUrl(s);
        entity.setStatus(i);
        entity.setLastLocate(lastLoc);
        return entity;
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
        return this.generate(json,hClass,userId);
    }

    private String generateByHealthStatic(int userId, int hClass) {
       // 生成静态健康码
        UserId userId1 = new UserId();
        userId1.setUserId(userId + "");
        String json = GsonUtils.toJson(userId1);
        return this.generate(json,hClass,userId);
    }

    private String generate(String json,int hClass,int userId) {
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
        return  generateQRPath(qrStr,colors,userId);
    }

    private String generateQRPath(String s,Colors color,Integer userId) {
        BufferedImage image = QRCode.createImageByColor(s, color);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String tag = userId + "_" + calendar.get(Calendar.YEAR)+(calendar.get(Calendar.MONTH)
                +1)+
                +calendar.get(Calendar.DAY_OF_MONTH)+calendar.get(Calendar.HOUR_OF_DAY)
                +calendar.get(Calendar.MINUTE)+calendar.get(Calendar.SECOND);
        String resPath = "/" + tag + ".jpg";
        try {
            File f = new File(path + resPath);
            ImageIO.write(image,"jpg",f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  resPath;
    }
}
