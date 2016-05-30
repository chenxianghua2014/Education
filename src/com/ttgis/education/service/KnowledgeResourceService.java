package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.KnowledgeResource;
import com.ttgis.education.mapper.KnowledgeResourceMapper;

@Repository
@Service
public class KnowledgeResourceService {
	
	@Resource
	private KnowledgeResourceMapper knowledgeResourceMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return knowledgeResourceMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(KnowledgeResource record){
		return knowledgeResourceMapper.insert(record);
	}

	public int insertSelective(KnowledgeResource record){
		return knowledgeResourceMapper.insertSelective(record);
	}

	public KnowledgeResource selectByPrimaryKey(String courseId){
		return knowledgeResourceMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(KnowledgeResource record){
		return knowledgeResourceMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(KnowledgeResource record){
		return knowledgeResourceMapper.updateByPrimaryKey(record);
	}

	public List<KnowledgeResource> selectknowledgeResource(KnowledgeResource s) {
		return knowledgeResourceMapper.selectknowledgeResource(s);
	}

}