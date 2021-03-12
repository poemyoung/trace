package com.trace.user;

import com.common.utils.GsonUtils;
import com.trace.dao.entity.User;
import com.trace.dao.entity.UserExample;
import com.trace.dao.repository.UserMapper;
import com.trace.service.entity.recentity.UserId;
import com.trace.service.user.UserIdConvertService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

/**
 * @author xzp
 * Created on 2021/2/7
 */
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

    @Test
    public void doTest1() {
        String json = "{\"userId\" : \"123456\"}";
        UserId userId = GsonUtils.fromJson(json, UserId.class);
        Assert.assertNotNull(userId);
        Assert.assertEquals("123456",userId.getUserId());

    }
}