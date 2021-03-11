package com.trace.service.qrcode;

import com.trace.dao.entity.CodeManage;
import com.trace.dao.repository.CodeManageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xzp
 * Created on 2021/3/10
 */
@Service
public class QRCodeMagService {
    @Autowired
    CodeManageMapper manageMapper;

    public boolean addRelate(Integer userId,Integer magId,String name,String idCard) {
        CodeManage manage = new CodeManage();
        manage.setIdCard(idCard);
        manage.setName(name);
        manage.setMagUser(magId);
        manage.setUserid(userId);

        return true;
    }

    public boolean deleteRelate(Integer userId,Integer reaUserId) {
        if(userId == null || userId == 0 || reaUserId == null || reaUserId == 0) {
            return false;
        }
        return true;
    }
}
