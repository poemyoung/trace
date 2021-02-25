package com.trace.dao.impl;

import com.trace.dao.SqlSessionGet;
import com.trace.dao.entity.User;
import com.trace.dao.entity.UserExample;
import com.trace.dao.repository.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.Objects;

/**
 * @author xzp
 * Created on 2021/2/7
 */
@Repository
public class UserMapperImpl implements UserMapper {
    final UserMapper mapper = Objects.requireNonNull(SqlSessionGet.getSqlSession()).getMapper(UserMapper.class);

    @Override
    public long countByExample(UserExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return mapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return mapper.updateByPrimaryKey(record);
    }

}