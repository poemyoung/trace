package com.trace.dao.impl;

import com.trace.dao.entity.ColdChain;
import com.trace.dao.entity.ColdChainExample;
import com.trace.dao.repository.ColdChainMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/4/13
 */
@Repository
public class ColdChainMapperImpl implements ColdChainMapper {

    SqlSessionTemplate sqlSessionTemplate;

    ColdChainMapper mapper;

    @Autowired
    public ColdChainMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.mapper = sqlSessionTemplate.getMapper(ColdChainMapper.class);
    }

    @Override
    public int deleteByPrimaryKey(Integer idcoldchain) {
        return mapper.deleteByPrimaryKey(idcoldchain);
    }

    @Override
    public int insert(ColdChain record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(ColdChain record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<ColdChain> selectByExample(ColdChainExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public ColdChain selectByPrimaryKey(Integer idcoldchain) {
        return mapper.selectByPrimaryKey(idcoldchain);
    }

    @Override
    public int updateByPrimaryKeySelective(ColdChain record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(ColdChain record) {
        return mapper.updateByPrimaryKey(record);
    }
}
