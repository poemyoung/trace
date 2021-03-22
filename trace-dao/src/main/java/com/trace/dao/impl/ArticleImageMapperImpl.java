package com.trace.dao.impl;

import com.trace.dao.entity.ArticleImage;
import com.trace.dao.entity.ArticleImageExample;
import com.trace.dao.repository.ArticleImageMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/22
 */
@Repository
public class ArticleImageMapperImpl implements ArticleImageMapper {

    ArticleImageMapper mapper;

    SqlSession sqlSession;

    @Autowired
    public ArticleImageMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.mapper = sqlSession.getMapper(ArticleImageMapper.class);
    }

    @Override
    public long countByExample(ArticleImageExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ArticleImageExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int insert(ArticleImage record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(ArticleImage record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<ArticleImage> selectByExample(ArticleImageExample example) {
        return mapper.selectByExample(example);
    }
}
