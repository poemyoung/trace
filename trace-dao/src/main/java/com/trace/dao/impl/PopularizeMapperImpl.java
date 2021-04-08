package com.trace.dao.impl;

import com.trace.dao.entity.Popularize;
import com.trace.dao.repository.PopularizeMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author xzp
 * Created on 2021/4/8
 */
@Repository
public class PopularizeMapperImpl implements PopularizeMapper {

    PopularizeMapper mapper;

    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public PopularizeMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.mapper = sqlSessionTemplate.getMapper(PopularizeMapper.class);
    }

    @Override
    public int deleteByPrimaryKey(Integer idpop) {
        return mapper.deleteByPrimaryKey(idpop);
    }

    @Override
    public int insert(Popularize record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Popularize record) {
        return mapper.insertSelective(record);
    }

    @Override
    public Popularize selectByPrimaryKey(Integer idpop) {
        return mapper.selectByPrimaryKey(idpop);
    }

    @Override
    public int updateByPrimaryKeySelective(Popularize record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Popularize record) {
        return mapper.updateByPrimaryKey(record);
    }
}
