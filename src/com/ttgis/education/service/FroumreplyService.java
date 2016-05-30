package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Froumreply;
import com.ttgis.education.mapper.FroumreplyMapper;

@Repository
@Service
public class FroumreplyService {
	
	@Resource
	private FroumreplyMapper froumreplyMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return froumreplyMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Froumreply record){
		return froumreplyMapper.insert(record);
	}

	public int insertSelective(Froumreply record){
		return froumreplyMapper.insertSelective(record);
	}

	public Froumreply selectByPrimaryKey(String courseId){
		return froumreplyMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Froumreply record){
		return froumreplyMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Froumreply record){
		return froumreplyMapper.updateByPrimaryKey(record);
	}

}