package com.trace.dao.impl;

import com.trace.dao.entity.Article;
import com.trace.dao.entity.ArticleExample;
import com.trace.dao.repository.ArticleMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xzp
 * Created on 2021/3/22
 */
@Repository
public class ArticleMapperImpl implements ArticleMapper {

    ArticleMapper mapper;

    SqlSession sqlSession;

    @Autowired
    public ArticleMapperImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.mapper = sqlSession.getMapper(ArticleMapper.class);
    }

    @Override
    public long countByExample(ArticleExample example) {
        return mapper.countByExample(example);
    }

    @Override
    public int deleteByExample(ArticleExample example) {
        return mapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer aid) {
        return mapper.deleteByPrimaryKey(aid);
    }

    @Override
    public int insert(Article record) {
        return mapper.insert(record);
    }

    @Override
    public int insertSelective(Article record) {
        return mapper.insertSelective(record);
    }

    @Override
    public List<Article> selectByExample(ArticleExample example) {
        return mapper.selectByExample(example);
    }

    @Override
    public Article selectByPrimaryKey(Integer aid) {
        return mapper.selectByPrimaryKey(aid);
    }

    @Override
    public int updateByExampleSelective(Article record, ArticleExample example) {
        return mapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(Article record, ArticleExample example) {
        return mapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Article record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Article record) {
        return mapper.updateByPrimaryKey(record);
    }
}
