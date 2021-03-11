package com.trace.dao.impl;

import com.trace.dao.SqlSessionGet;
import com.trace.dao.entity.UserConvert;
import com.trace.dao.entity.UserConvertExample;
import com.trace.dao.repository.UserConvertMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@Repository
public class UserConvertMapperImpl implements UserConvertMapper {
    UserConvertMapper mapper;

    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public UserConvertMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.mapper = sqlSessionTemplate.getMapper(UserConvertMapper.class);
    }

    @Override
    public long countByExample(UserConvertExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int insert(UserConvert record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(UserConvert record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<UserConvert> selectByExample(UserConvertExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public Integer selectByOpenId(String openId) {
        return mapper.selectByOpenId(openId);
    }
}
