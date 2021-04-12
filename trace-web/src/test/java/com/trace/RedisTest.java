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

    @Test
    public void randomData() {
        tmu.addRandomData();
    }

    @Test
    public void washData() {
        tmu.washData();
    }

    @Test
    public void washData2() {
        tmu.washData2();
    }

    @Test
    public void ineert() {
        String headline = "治疗方法";
        String c = "在接触感染了 COVID-19 的人员后，请采取以下措施：\n" +
                "致电医疗服务提供方或拨打 COVID-19 热线，了解何时何地接受检测。\n" +
                "配合接触者追踪程序，阻止病毒传播。\n" +
                "如果无法进行检测，请居家隔离 14 天，并在此期间避免与他人接触。\n" +
                "隔离期间，不要去上班、上学或前往公共场所。让他人帮您带来生活必需品。\n" +
                "与他人保持至少 1 米的距离，即使是与家人也应如此。\n" +
                "佩戴医用口罩以保护他人，需要就医时亦不例外。\n" +
                "勤洗手。\n" +
                "在单独的房间内隔离，避免接触家人。如果无法做到，请佩戴医用口罩。\n" +
                "保持房间通风良好。\n" +
                "如果与他人合住一个房间，请保持至少 1 米的床间距离。\n" +
                "连续 14 天监测自己是否有任何症状。\n" +
                "如果有以下任何危险征兆，请立即致电医疗服务提供方：呼吸困难、出现语言或行动障碍、神志不清或胸痛。\n" +
                "通过电话或网络与亲友保持联系，在家中锻炼身体，保持积极的心态。";
        tmu.insertSomething(headline,c);
    }
}
