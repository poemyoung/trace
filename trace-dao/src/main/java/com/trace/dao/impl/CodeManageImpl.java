package com.trace.dao.impl;

import com.trace.dao.SqlSessionGet;
import com.trace.dao.entity.CodeManage;
import com.trace.dao.entity.CodeManageExample;
import com.trace.dao.repository.CodeManageMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author xzp
 * Created on 2021/3/10
 */
@Repository
public class CodeManageImpl implements CodeManageMapper {
    CodeManageMapper mapper;

    SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public CodeManageImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
        this.mapper = sqlSessionTemplate.getMapper(CodeManageMapper.class);
    }

    @Override
    public int deleteByExample(CodeManageExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer userid) {
        return mapper.deleteByPrimaryKey(userid);
    }

    @Override
    public int insert(CodeManage record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(CodeManage record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<CodeManage> selectByExample(CodeManageExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public CodeManage selectByPrimaryKey(Integer userid) {
        return mapper.selectByPrimaryKey(userid);
    }

    @Override
    public int updateByExampleSelective(CodeManage record, CodeManageExample example) {
        return mapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(CodeManage record, CodeManageExample example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(CodeManage record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CodeManage record) {
        return mapper.updateByPrimaryKeySelective(record);
    }
}
