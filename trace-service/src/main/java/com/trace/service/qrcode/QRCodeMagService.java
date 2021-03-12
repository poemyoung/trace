package com.trace.service.qrcode;

import com.trace.dao.entity.CodeManage;
import com.trace.dao.entity.CodeManageExample;
import com.trace.dao.repository.CodeManageMapper;
import com.trace.service.converter.StaticCodeConverter;
import com.trace.service.entity.retentity.UserStaticCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/10
 */
@Service
public class QRCodeMagService {
    @Autowired
    CodeManageMapper manageMapper;

    @Autowired
    QRCodeService service;

    public UserStaticCode updateQRCode(Integer userId) {
        if(userId == null || userId == 0) {
            return null;
        }
        String s = service.generateStaticCode(userId);
        CodeManage manage = new CodeManage();
        manage.setUserid(userId);
        manage.setQrcode(s);
        int i = manageMapper.updateByPrimaryKeySelective(manage);
        if(i > 0) {
            manage = manageMapper.selectByPrimaryKey(userId);
        }
        return StaticCodeConverter.convertToUserStaticCode(manage);
    }

    public List<UserStaticCode> getUserStaticCode(Integer userId) {
        if (userId == null || userId == 0) {
            return null;
        }
        // 查数据库获取user列表
        CodeManageExample example = new CodeManageExample();
        CodeManageExample.Criteria criteria = example.createCriteria();
        criteria.andMagUserEqualTo(userId);
        List<CodeManage> codeManages = manageMapper.selectByExample(example);
        List<UserStaticCode> res = new ArrayList<>();
        for (CodeManage manage : codeManages) {
            res.add(StaticCodeConverter.convertToUserStaticCode(manage));
        }
        return res;
    }

    public boolean addRelate(Integer userId,Integer magId,String name,String idCard) {
        CodeManage manage = new CodeManage();
        manage.setIdCard(idCard);
        manage.setName(name);
        manage.setMagUser(magId);
        manage.setUserid(userId);
        String s = service.generateStaticCode(userId);
        if(StringUtils.isNotBlank(s)){
            manage.setQrcode(s);
        }
        int i = 0;
        if(manageMapper.selectByPrimaryKey(userId) != null) {
            i = manageMapper.updateByPrimaryKeySelective(manage);
        }
        else {
            i = manageMapper.insertSelective(manage);
        }
        return i > 0;
    }

    public boolean deleteRelate(Integer userId,Integer reaUserId) {
        if(userId == null || userId == 0 || reaUserId == null || reaUserId == 0) {
            return false;
        }
        int i = manageMapper.deleteByPrimaryKey(userId);
        return i > 0;
    }
}
