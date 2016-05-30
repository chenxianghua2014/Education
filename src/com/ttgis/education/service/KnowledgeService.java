package com.ttgis.education.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Knowledge;
import com.ttgis.education.entity.KnowledgeResource;
import com.ttgis.education.mapper.KnowledgeMapper;
import com.ttgis.education.utils.RandomGUIDUtil;

@Repository
@Service
public class KnowledgeService {
	
	@Resource
	private KnowledgeMapper knowledgeMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return knowledgeMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Knowledge record){
		record.setKnowledgeType(0);
		record.setKnowledgeClick(0);
		record.setKnowledgeCollectnum(0);
		record.setKnowledgeAt(new Date());
		record.setKnowledgeDel(1);
		return knowledgeMapper.insert(record);
	}

	public int insertSelective(Knowledge record){
		return knowledgeMapper.insertSelective(record);
	}

	public Knowledge selectByPrimaryKey(String courseId){
		return knowledgeMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Knowledge record){
		return knowledgeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Knowledge record){
		return knowledgeMapper.updateByPrimaryKey(record);
	}

	public List<Knowledge> selectBywaiKey(Knowledge knowledge) {
		return knowledgeMapper.selectBywaiKey(knowledge);
	}

	public List<Knowledge> selectQTknowledge(KnowledgeResource k) {
		return knowledgeMapper.selectQTknowledge(k);
	}

	public Knowledge selectResourcesOne(Knowledge k) {
		Knowledge kk=knowledgeMapper.selectByPrimaryKey(k.getKnowledgeId());
		if(kk!=null){
			k.setKnowledgeClick(kk.getKnowledgeClick()+1);
			knowledgeMapper.updateByPrimaryKeySelective(k);
		}
		return kk;
	}

	public int selectBywaiKeyCount(Knowledge knowledge) {
		return knowledgeMapper.selectBywaiKeyCount(knowledge);
	}

	public int selectQTknowledgeCount(KnowledgeResource k) {
		return knowledgeMapper.selectQTknowledgeCount(k);
	}

}