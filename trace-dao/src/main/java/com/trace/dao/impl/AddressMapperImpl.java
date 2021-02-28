package com.trace.dao.impl;

import com.trace.dao.SqlSessionGet;
import com.trace.dao.entity.Address;
import com.trace.dao.entity.AddressExample;
import com.trace.dao.repository.AddressMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author xzp
 * Created on 2021/2/28
 */
@Repository
public class AddressMapperImpl implements AddressMapper {
    final AddressMapper mapper = Objects.requireNonNull(SqlSessionGet.getSqlSession()).getMapper(AddressMapper.class);


    @Override
    public long countByExample(AddressExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByExample(AddressExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer idaddress) {
        return mapper.deleteByPrimaryKey(idaddress);
    }

    @Override
    public int insert(Address record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Address record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<Address> selectByExample(AddressExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public Address selectByPrimaryKey(Integer idaddress) {
        return mapper.selectByPrimaryKey(idaddress);
    }

    @Override
    public int updateByExampleSelective(Address record, AddressExample example) {
        return mapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Address record, AddressExample example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Address record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Address record) {
        return mapper.updateByPrimaryKey(record);
    }
}
