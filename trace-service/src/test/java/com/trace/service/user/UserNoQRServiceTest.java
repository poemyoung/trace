package com.trace.service.user;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xzp
 * Created on 2021/3/10
 */
public class UserNoQRServiceTest {

    UserNoQRService service = new UserNoQRService();

    @Test
    public void test() {
        Integer i = service.isUserExist("徐朋","513030199505106516");
        assertEquals((Integer) 1505084195,i);
    }
}