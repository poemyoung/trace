package com.trace.dao.repository;

import com.trace.dao.entity.UserDetail;
import com.trace.dao.entity.UserDetailExample;
import java.util.List;

public interface UserDetailMapper {
    int deleteByPrimaryKey(Integer iduserDetail);

    int insert(UserDetail record);

    int insertSelective(UserDetail record);

    List<UserDetail> selectByExample(UserDetailExample example);

    UserDetail selectByPrimaryKey(Integer iduserDetail);

    int updateByPrimaryKeySelective(UserDetail record);

    int updateByPrimaryKey(UserDetail record);
}