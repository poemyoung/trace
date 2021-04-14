package com.trace.service.coldchain;

import com.common.enums.Colors;
import com.common.utils.GsonUtils;
import com.common.utils.QRCode;
import com.common.utils.SnowflakeIdUtil;
import com.trace.dao.entity.Address;
import com.trace.dao.entity.ChainPass;
import com.trace.dao.entity.ChainPassExample;
import com.trace.dao.entity.ColdChain;
import com.trace.dao.repository.AddressMapper;
import com.trace.dao.repository.ChainPassMapper;
import com.trace.dao.repository.ColdChainMapper;
import com.trace.service.address.AddrService;
import com.trace.service.entity.commentity.DateEnum;
import com.trace.service.entity.recentity.ColdChainBaseMsg;
import com.trace.service.entity.recentity.Pos;
import com.trace.service.entity.retentity.ChainQrRet;
import com.trace.service.entity.retentity.ChargoRet;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@Service
@Cacheable(cacheNames = "chain")
public class ColdChainService {
    private final Logger LOGGER = LoggerFactory.getLogger(ColdChainService.class);

    @Autowired
    AddressMapper addrMapper;

    @Autowired
    ColdChainMapper ccMapper;

    @Autowired
    AddrService adService;

    @Autowired
    ChainPassMapper cpMapper;

    @Value("${qrcode.path}")
    private String path = "/Users/xzp/Desktop/upload";

    SnowflakeIdUtil snow = new SnowflakeIdUtil();

    public ChainQrRet declareAColdChain(ColdChainBaseMsg msg) {
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
            return null;
        }
        coldChain.setQrpath(s);
        // 插入数据库中
        int i = ccMapper.insertSelective(coldChain);
        ChainQrRet ret = new ChainQrRet();
        ret.setPath(s);
        ret.setId(id+"");
        return ret;
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

    public boolean insertCharGoAddress(Pos pos) {
        if(pos == null || pos.getLongitude() == null || pos.getLatitude() == null) {
            return false;
        }
        if(StringUtils.isBlank(pos.getUserId())){
            return false;
        }
        int a = 0;
        try {
            a = Integer.parseInt(pos.getUserId());
        }catch (Exception e) {
            LOGGER.error("冷链id解析错误");
            return false;
        }
        final Integer userId = -1;
        int addrId = adService.addrInsert(pos.getLatitude(),pos.getLongitude(),userId, DateEnum.FRO_EVER.getDate());
        ChainPass cp = new ChainPass();
        cp.setAddrid(addrId);
        cp.setChainid(a);
        cp.setTime(new Date());
        int i = cpMapper.insertSelective(cp);
        return i > 0;
    }

    @Cacheable(cacheNames = "chain",key = "#json")
    public ChargoRet parseCharGoInfo(String json) {
        ColdChain cc = null;
        try {
             cc = GsonUtils.fromJson(json,ColdChain.class);
        }catch (Exception e) {
            LOGGER.error("");
            return null;
        }
        if(cc == null) {
            return null;
        }
        // 获取冷链id进行查找
        ChargoRet ret = this.findByChainId(cc.getIdcoldchain());
        return ret;
    }

    @Cacheable(cacheNames = "chain",key = "#chainId")
    public ChargoRet findByChainId(Integer chainId) {
        if(chainId == null || chainId == 0) {
            return null;
        }
        ChargoRet ret = new ChargoRet();
        ColdChain coldChain = ccMapper.selectByPrimaryKey(chainId);
        if(coldChain == null) {
            return null;
        }
        ret.setClassify(coldChain.getClassify());
        ret.setId(coldChain.getIdcoldchain()+"");
        ret.setRemark(coldChain.getRemark());
        ret.setSource(coldChain.getSource());
        // 选取地址信息
        ChainPassExample example = new ChainPassExample();
        example.createCriteria().andChainidEqualTo(chainId);
        List<ChainPass> chainPasses = cpMapper.selectByExample(example);
        if(chainPasses == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        for (ChainPass cp : chainPasses) {
            int aid = cp.getAddrid();
            Address address = addrMapper.selectByPrimaryKey(aid);
            if(address == null) {
                return null;
            }
            String detail = (StringUtils.isBlank(address.getProvince()) ? "" : address.getProvince())
                    + (StringUtils.isBlank(address.getCity()) ? "" : address.getCity())
                    + (StringUtils.isBlank(address.getCounty()) ? "" : address.getCounty())
                    + (StringUtils.isBlank(address.getDetail()) ? "" : address.getDetail());
            list.add(detail);
        }
        ret.setPlaces(list);
        return ret;
    }

}
