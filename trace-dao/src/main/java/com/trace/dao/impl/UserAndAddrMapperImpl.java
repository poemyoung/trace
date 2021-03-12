package com.trace.dao.impl;

import com.trace.dao.entity.UserAndAddr;
import com.trace.dao.entity.UserAndAddrExample;
import com.trace.dao.repository.UserAndAddrMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/12
 */
@Repository
public class UserAndAddrMapperImpl implements UserAndAddrMapper {

    UserAndAddrMapper mapper;

    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public UserAndAddrMapperImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.mapper = sqlSessionTemplate.getMapper(UserAndAddrMapper.class);
    }

    @Override
    public long countByExample(UserAndAddrExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserAndAddrExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer aid) {
        return mapper.deleteByPrimaryKey(aid);
    }

    @Override
    public int insert(UserAndAddr record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(UserAndAddr record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<UserAndAddr> selectByExample(UserAndAddrExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UserAndAddr selectByPrimaryKey(Integer aid) {
        return mapper.selectByPrimaryKey(aid);
    }

    @Override
    public int updateByExampleSelective(UserAndAddr record, UserAndAddrExample example) {
        return mapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(UserAndAddr record, UserAndAddrExample example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(UserAndAddr record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserAndAddr record) {
        return mapper.updateByPrimaryKey(record);
    }
}
