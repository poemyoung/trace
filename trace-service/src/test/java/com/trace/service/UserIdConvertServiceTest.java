package com.trace.service;

import com.trace.dao.entity.User;
import com.trace.dao.entity.UserExample;
import com.trace.dao.repository.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserIdConvertServiceTest {
    @Autowired
    UserIdConvertService convert;

    @Autowired
    UserMapper mapper;

    @Test
    @Rollback(false)
    public void createDefaultUser() {
        int id = convert.openIdConvert("test_open_id"+Math.random());
        User user = mapper.selectByPrimaryKey(id);
        Assert.assertEquals(id,user.getId().intValue());
    }
    @Test
    public void doTest() {
        System.out.println(mapper.countByExample(new UserExample()));
    }
}