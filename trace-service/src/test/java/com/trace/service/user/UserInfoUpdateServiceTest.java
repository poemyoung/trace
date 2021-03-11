package com.trace.service.user;

import com.trace.dao.impl.UserDetailMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * @author xzp
 * Created on 2021/3/11
 */
public class UserInfoUpdateServiceTest {
    UserInfoUpdateService service = new UserInfoUpdateService();
    private Integer MY_ID = 1505084195;

    @Before
    public void before() throws Exception{
        Class<UserInfoUpdateService> cls = UserInfoUpdateService.class;
        Field detailMapper = cls.getDeclaredField("detailMapper");
        detailMapper.set(service,new UserDetailMapperImpl());
        Field infoService = cls.getDeclaredField("infoService");
        infoService.set(service,new UserInfoService());
    }


    @Test
    public void testPhone() throws Exception {
        String phone = "13032855166";
        boolean f = service.updatePhone(123,phone);
        assertTrue(f);
    }


}