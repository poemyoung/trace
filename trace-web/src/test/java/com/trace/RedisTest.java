package com.trace;

import com.trace.controller.TestMysqlUsableController;
import com.trace.dao.entity.TestMysqlUsable;
import com.trace.util.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

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
        Calendar calendar = Calendar.getInstance();
        String value1 = tmu.testRedis("2020-03-20", "value1");
        Assert.assertEquals("value1",value1);
        String value2 = tmu.testRedis("2020-03-20", "value2");
        Assert.assertEquals("value1",value2);
    }

    @Test
    public void spELTest() {
        ExpressionParser parser = new SpelExpressionParser();
        String exp ="20 eq T(java.util.Calendar).getInstance().get(T(java.util.Calendar).DAY_OF_MONTH)";
        String exp2 = "new String('测试')";
        String exp3 = "T(java.util.Calendar).getInstance().get(T(java.util.Calendar).DAY_OF_MONTH)";
        boolean f = parser.parseExpression(exp).getValue(Boolean.class);
        Assert.assertTrue(f);
        String s = parser.parseExpression(exp2).getValue(String.class);
        Assert.assertEquals("测试",s);
        String s1 = parser.parseExpression(exp3).getValue(String.class);
        System.out.println(s1);
    }


}
