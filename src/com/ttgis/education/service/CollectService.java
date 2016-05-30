package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Collect;
import com.ttgis.education.mapper.CollectMapper;

@Repository
@Service
public class CollectService {
	
	@Resource
	private CollectMapper collectMapper;
	
	public int deleteByPrimaryKey(String collectId){
		return collectMapper.deleteByPrimaryKey(collectId);
	}

	public int insert(Collect record){
		return collectMapper.insert(record);
	}

	public int insertSelective(Collect record){
		return collectMapper.insertSelective(record);
	}

	public Collect selectByPrimaryKey(String collectId){
		return collectMapper.selectByPrimaryKey(collectId);
	}

	public int updateByPrimaryKeySelective(Collect record){
		return collectMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Collect record){
		return collectMapper.updateByPrimaryKey(record);
	}
	
	/**
	 * 是否重复收藏课程
	 * @param record
	 * @return
	 */
	public List<Collect> selectScKey(Collect record){
		return collectMapper.selectScKey(record);
	}
	/**
	 * 删除收藏课程
	 * @param record
	 * @return
	 */
	public int deleteScKey(Collect record){
		return collectMapper.deleteScKey(record);
	}
	
	
}