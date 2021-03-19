package com.trace;

import com.trace.controller.TestMysqlUsableController;
import com.trace.dao.entity.TestMysqlUsable;
import com.trace.util.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author xzp
 * Created on 2021/3/19
 */
@SpringBootTest(classes = MainApp.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RedisTest {
    @Autowired
    TestMysqlUsableController tmu;

    @Test
    public void test() {
        System.out.println("test suc");
        Assert.assertNotNull(tmu);
        Result result = tmu.testInsertAva();
        Assert.assertEquals((Integer) 1,result.getCode());

    }

    @Test
    public void test1() {
        String value = tmu.testRedis("2020-03-19", "value");
        Assert.assertEquals("value",value);

    }

}
