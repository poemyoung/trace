package com.trace.dao.impl;

import com.trace.dao.SqlSessionGet;
import com.trace.dao.entity.UserDetail;
import com.trace.dao.entity.UserDetailExample;
import com.trace.dao.repository.UserConvertMapper;
import com.trace.dao.repository.UserDetailMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author xzp
 * Created on 2021/2/27
 */
@Repository
public class UserDetailMapperImpl implements UserDetailMapper {
    final UserDetailMapper mapper = Objects.requireNonNull(SqlSessionGet.getSqlSession()).getMapper(UserDetailMapper.class);


    @Override
    public int deleteByPrimaryKey(Integer iduserDetail) {
        return mapper.deleteByPrimaryKey(iduserDetail);
    }

    @Override
    public int insert(UserDetail record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(UserDetail record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<UserDetail> selectByExample(UserDetailExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public UserDetail selectByPrimaryKey(Integer iduserDetail) {
        return mapper.selectByPrimaryKey(iduserDetail);
    }

    @Override
    public int updateByPrimaryKeySelective(UserDetail record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserDetail record) {
        return mapper.updateByPrimaryKey(record);
    }
}
