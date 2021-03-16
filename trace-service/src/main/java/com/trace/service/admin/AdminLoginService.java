package com.trace.service.admin;

import com.trace.dao.entity.Admin;
import com.trace.dao.entity.AdminExample;
import com.trace.dao.repository.AdminMapper;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/16
 */
@Service
public class AdminLoginService {

    @Autowired
    AdminMapper adminMapper;

    public boolean login(String name,String password) {
        if(StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            return false;
        }
        AdminExample example = new AdminExample();
        example.createCriteria().andNameEqualTo(name).andPasswordEqualTo(password);
        List<Admin> admins = adminMapper.selectByExample(example);
        return admins.size() > 0;
    }
}
