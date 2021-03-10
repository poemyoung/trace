package com.trace.dao.repository;

import com.trace.dao.entity.User;
import com.trace.dao.entity.UserExample;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    Integer selectUserExists(User record);
}