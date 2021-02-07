package com.trace.dao.repository;

import com.trace.dao.entity.UserConvert;
import com.trace.dao.entity.UserConvertExample;
import java.util.List;

public interface UserConvertMapper {
    long countByExample(UserConvertExample example);

    int insert(UserConvert record);

    int insertSelective(UserConvert record);

    List<UserConvert> selectByExample(UserConvertExample example);

    Integer selectByOpenId(String openId);
}