package com.trace.dao.repository;

import com.trace.dao.entity.ChainPass;
import com.trace.dao.entity.ChainPassExample;
import java.util.List;

public interface ChainPassMapper {
    int insert(ChainPass record);

    int insertSelective(ChainPass record);

    List<ChainPass> selectByExample(ChainPassExample example);
}