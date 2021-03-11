package com.trace.service.converter;

import com.common.utils.SnowflakeIdUtil;
import com.trace.dao.entity.UserDetail;
import com.trace.dao.entity.UserDetailExample;
import com.trace.dao.repository.UserDetailMapper;
import com.trace.service.entity.UserBaseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xzp
 * Created on 2021/2/27
 */
@Service
public class DetailConverter {

    @Autowired
    UserDetailMapper mapper;

    public UserDetail convertDetail(UserBaseMsg msg) {
        UserDetail detail = new UserDetail();
        detail.setTemp(msg.getBodyHeat().toString());
        detail.setPhone(msg.getPhone());
        detail.setIduserDetail(Integer.parseInt(msg.getUserId()));
        SnowflakeIdUtil util = new SnowflakeIdUtil();
        int addrId = util.nextIntId();
        if(addrId < 0)addrId += Integer.MAX_VALUE;
        // hash 冲突解决
        UserDetailExample example = new UserDetailExample();
        example.createCriteria().andAddrIdEqualTo(addrId);
        for (;mapper.selectByExample(example).size() > 0;example.createCriteria().andAddrIdEqualTo(addrId+1));
        detail.setAddrId(addrId);
        if(msg.getSymptom() != null && msg.getSymptom().isIsSymptom()) {
            detail.setRiskFlag(riskFlagConvert(msg));
        }else {
            detail.setRiskFlag(0);
        }
        return detail;
    }

    public Integer riskFlagConvert(UserBaseMsg msg) {
        int flag = 0;
        //前六位 症状
        if(msg.getSymptom().isIsSymptom()) {
            String type = msg.getSymptom().getType();
            for(int i = 0;i < type.length();i++) {
                char c = type.charAt(i);
                switch (c) {
                    case '0':
                        flag = flag << 1;
                        break;
                    case '1':
                        flag = flag << 1;
                        flag |= 1;
                        break;
                    default:
                        break;
                }
            }
        }
        //第七位 是否到过国外
        flag <<= 1;
        flag |= (msg.isForeign() ? 1 : 0);
        //第八位 是否到过高风险地区
        flag <<= 1;
        flag |= (msg.isHighRisk() ? 1 : 0);
        // 第九位 是否接触过疑似病人
        flag <<= 1;
        flag |= (msg.isContactPatient() ? 1 : 0);
        return flag;
    }
}
