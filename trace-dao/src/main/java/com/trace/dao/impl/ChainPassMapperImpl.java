package com.trace.dao.impl;

import com.trace.dao.entity.ChainPass;
import com.trace.dao.entity.ChainPassExample;
import com.trace.dao.repository.ChainPassMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@Repository
public class ChainPassMapperImpl implements ChainPassMapper {

    ChainPassMapper mapper;

    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public ChainPassMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.mapper = sqlSessionTemplate.getMapper(ChainPassMapper.class);
    }

    @Override
    public int insert(ChainPass record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(ChainPass record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<ChainPass> selectByExample(ChainPassExample example) {
        return mapper.selectByExample(example);
    }
}
