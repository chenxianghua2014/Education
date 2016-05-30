package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.KnowledgeResource;

public interface KnowledgeResourceMapper {
    int deleteByPrimaryKey(String knowledgeResourceId);

    int insert(KnowledgeResource record);

    int insertSelective(KnowledgeResource record);

    KnowledgeResource selectByPrimaryKey(String knowledgeResourceId);

    int updateByPrimaryKeySelective(KnowledgeResource record);

    int updateByPrimaryKey(KnowledgeResource record);

	List<KnowledgeResource> selectknowledgeResource(KnowledgeResource s);
}