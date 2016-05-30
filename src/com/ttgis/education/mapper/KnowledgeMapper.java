package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Knowledge;
import com.ttgis.education.entity.KnowledgeResource;

public interface KnowledgeMapper {
    int deleteByPrimaryKey(String knowledgeId);

    int insert(Knowledge record);

    int insertSelective(Knowledge record);

    Knowledge selectByPrimaryKey(String knowledgeId);

    int updateByPrimaryKeySelective(Knowledge record);

    int updateByPrimaryKey(Knowledge record);

	List<Knowledge> selectBywaiKey(Knowledge knowledge);

	List<Knowledge> selectQTknowledge(KnowledgeResource k);

	Knowledge selectResourcesOne(Knowledge k);

	int selectBywaiKeyCount(Knowledge knowledge);

	int selectQTknowledgeCount(KnowledgeResource k);
}