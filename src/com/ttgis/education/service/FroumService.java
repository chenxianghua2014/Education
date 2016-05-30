package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Froum;
import com.ttgis.education.mapper.FroumMapper;

@Repository
@Service
public class FroumService {
	
	@Resource
	private FroumMapper froumMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return froumMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Froum record){
		return froumMapper.insert(record);
	}

	public int insertSelective(Froum record){
		return froumMapper.insertSelective(record);
	}

	public Froum selectByPrimaryKey(String courseId){
		return froumMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Froum record){
		return froumMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Froum record){
		return froumMapper.updateByPrimaryKey(record);
	}

}