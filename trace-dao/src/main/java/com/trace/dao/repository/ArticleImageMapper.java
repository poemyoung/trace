package com.trace.dao.repository;

import com.trace.dao.entity.ArticleImage;
import com.trace.dao.entity.ArticleImageExample;
import java.util.List;

public interface ArticleImageMapper {
    long countByExample(ArticleImageExample example);

    int deleteByExample(ArticleImageExample example);

    int insert(ArticleImage record);

    int insertSelective(ArticleImage record);

    List<ArticleImage> selectByExample(ArticleImageExample example);
}