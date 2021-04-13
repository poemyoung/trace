package com.trace.dao.repository;

import com.trace.dao.entity.ColdChain;
import com.trace.dao.entity.ColdChainExample;
import java.util.List;

public interface ColdChainMapper {
    int deleteByPrimaryKey(Integer idcoldchain);

    int insert(ColdChain record);

    int insertSelective(ColdChain record);

    List<ColdChain> selectByExample(ColdChainExample example);

    ColdChain selectByPrimaryKey(Integer idcoldchain);

    int updateByPrimaryKeySelective(ColdChain record);

    int updateByPrimaryKey(ColdChain record);
}