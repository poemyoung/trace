package com.trace.dao.impl;

import com.trace.dao.SqlSessionGet;
import com.trace.dao.entity.TestMysqlUsable;
import com.trace.dao.entity.TestMysqlUsableExample;
import com.trace.dao.repository.TestMysqlUsableMapper;
import com.trace.dao.repository.UserConvertMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author xzp
 * Created on 2021/2/26
 */
@Repository
public class TestMysqlUsableMapperImpl implements TestMysqlUsableMapper {
    final TestMysqlUsableMapper mapper = Objects.requireNonNull(SqlSessionGet.getSqlSession()).getMapper(TestMysqlUsableMapper.class);


    @Override
    public int deleteByPrimaryKey(Integer idtestTable) {
        return mapper.deleteByPrimaryKey(idtestTable);
    }

    @Override
    public int insert(TestMysqlUsable record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(TestMysqlUsable record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<TestMysqlUsable> selectByExample(TestMysqlUsableExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public TestMysqlUsable selectByPrimaryKey(Integer idtestTable) {
        return mapper.selectByPrimaryKey(idtestTable);
    }

    @Override
    public int updateByPrimaryKeySelective(TestMysqlUsable record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TestMysqlUsable record) {
        return mapper.updateByPrimaryKey(record);
    }
}
