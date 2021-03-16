package com.trace.dao.impl;

import com.trace.dao.entity.Admin;
import com.trace.dao.entity.AdminExample;
import com.trace.dao.repository.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/16
 */
@Repository
public class AdminMapperImpl implements AdminMapper {
    AdminMapper mapper;

    SqlSession sqlSession;

    @Autowired
    public AdminMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.mapper = sqlSession.getMapper(AdminMapper.class);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Admin record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Admin record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<Admin> selectByExample(AdminExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public Admin selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Admin record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Admin record) {
        return mapper.updateByPrimaryKey(record);
    }
}
