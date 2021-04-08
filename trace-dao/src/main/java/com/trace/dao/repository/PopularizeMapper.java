package com.trace.dao.repository;

import com.trace.dao.entity.Popularize;

public interface PopularizeMapper {
    int deleteByPrimaryKey(Integer idpop);

    int insert(Popularize record);

    int insertSelective(Popularize record);

    Popularize selectByPrimaryKey(Integer idpop);

    int updateByPrimaryKeySelective(Popularize record);

    int updateByPrimaryKey(Popularize record);
}