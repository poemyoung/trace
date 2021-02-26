package com.trace.dao.repository;

import com.trace.dao.entity.TestMysqlUsable;
import com.trace.dao.entity.TestMysqlUsableExample;
import java.util.List;

public interface TestMysqlUsableMapper {
    int deleteByPrimaryKey(Integer idtestTable);

    int insert(TestMysqlUsable record);

    int insertSelective(TestMysqlUsable record);

    List<TestMysqlUsable> selectByExample(TestMysqlUsableExample example);

    TestMysqlUsable selectByPrimaryKey(Integer idtestTable);

    int updateByPrimaryKeySelective(TestMysqlUsable record);

    int updateByPrimaryKey(TestMysqlUsable record);
}