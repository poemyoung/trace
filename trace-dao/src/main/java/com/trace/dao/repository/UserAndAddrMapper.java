package com.trace.dao.repository;

import com.trace.dao.entity.UserAndAddr;
import com.trace.dao.entity.UserAndAddrExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAndAddrMapper {
    long countByExample(UserAndAddrExample example);

    int deleteByExample(UserAndAddrExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(UserAndAddr record);

    int insertSelective(UserAndAddr record);

    List<UserAndAddr> selectByExample(UserAndAddrExample example);

    UserAndAddr selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") UserAndAddr record, @Param("example") UserAndAddrExample example);

    int updateByExample(@Param("record") UserAndAddr record, @Param("example") UserAndAddrExample example);

    int updateByPrimaryKeySelective(UserAndAddr record);

    int updateByPrimaryKey(UserAndAddr record);
}