package com.common.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xzp
 * Created on 2021/3/11
 */
public class SnowflakeIdUtilTest {
    SnowflakeIdUtil util = new SnowflakeIdUtil(20,20);
    @Test
    public void test() {
        for (int i = 0;i < 10;i++) {
            System.out.println(util.nextId());
        }
    }

}