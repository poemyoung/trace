package com.common.utils;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @author xzp
 * Created on 2021/3/11
 */
public class SnowflakeIdUtilTest {
    SnowflakeIdUtil util = new SnowflakeIdUtil(20,20);
    @Test
    public void test() {
        Set<Integer> sets = new HashSet<>();
        for (int i = 0;i < 1000;i++) {
           sets.add(util.nextIntId());
        }
        assertEquals(1000,sets.size());
    }

}