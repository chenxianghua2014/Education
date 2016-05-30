package com.ttgis.education.mapper;

import com.ttgis.education.entity.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(String articleId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String articleId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}