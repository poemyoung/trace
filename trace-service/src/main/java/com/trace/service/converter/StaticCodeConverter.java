package com.trace.service.converter;

import com.trace.dao.entity.CodeManage;
import com.trace.service.entity.retentity.UserStaticCode;
import org.springframework.stereotype.Service;

/**
 * @author xzp
 * Created on 2021/3/11
 */
@Service
public class StaticCodeConverter {

    public static UserStaticCode convertToUserStaticCode(CodeManage manage) {
        UserStaticCode code = new UserStaticCode();
        code.setUserId(manage.getUserid()+"");
        code.setIdCard(manage.getIdCard());
        code.setUserName(manage.getName());
        code.setQrCode(manage.getQrcode());
        return code;
    }
}
