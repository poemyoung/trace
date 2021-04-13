package com.trace.service.coldchain;

import com.common.enums.Colors;
import com.common.utils.GsonUtils;
import com.common.utils.QRCode;
import com.common.utils.SnowflakeIdUtil;
import com.trace.dao.entity.ColdChain;
import com.trace.dao.repository.ColdChainMapper;
import com.trace.service.entity.recentity.ColdChainBaseMsg;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@Service
public class ColdChainService {
    private final Logger LOGGER = LoggerFactory.getLogger(ColdChainService.class);

    @Autowired
    ColdChainMapper ccMapper;

    @Value("${qrcode.path}")
    private String path = "/Users/xzp/Desktop/upload";

    SnowflakeIdUtil snow = new SnowflakeIdUtil();

    public String declareAColdChain(ColdChainBaseMsg msg) {
        // 插入冷链，生成冷链二维码、插入地址信息（单独分出来）
        ColdChain coldChain = new ColdChain();
        int id = snow.nextIntId();
        if(id < 0) {
            id += Integer.MAX_VALUE;
        }
        while (ccMapper.selectByPrimaryKey(id) != null) {
            id += 1;
        }
        coldChain.setIdcoldchain(id);
        coldChain.setClassify(msg.getClassify());
        coldChain.setRemark(msg.getRemark());
        coldChain.setSource(msg.getCompany());
        // 生成二维码返回路径
        String s = this.generateChainQrCode(coldChain);
        if(StringUtils.isBlank(s)) {
            return "";
        }
        coldChain.setQrpath(s);
        // 插入数据库中
        int i = ccMapper.insertSelective(coldChain);
        return s;
    }

    private String generateChainQrCode(ColdChain coldChain) {
        String s = GsonUtils.toJson(coldChain);
        int id = coldChain.getIdcoldchain();
        BufferedImage image = QRCode.createImageByColor(s, Colors.DEFAULT);
        String tag = "/" + id + ".jpg";
        try {
            File f = new File(path + tag);
            ImageIO.write(image,"jpg",f);
        }catch (Exception e) {
            LOGGER.error("图片写出错误！");
            return "";
        }
        return tag;
    }
}
