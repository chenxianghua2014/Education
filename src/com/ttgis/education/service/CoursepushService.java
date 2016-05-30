package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Coursepush;
import com.ttgis.education.mapper.CoursepushMapper;

@Repository
@Service
public class CoursepushService {
	
	@Resource
	private CoursepushMapper coursepushMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return coursepushMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Coursepush record){
		return coursepushMapper.insert(record);
	}

	public int insertSelective(Coursepush record){
		return coursepushMapper.insertSelective(record);
	}

	public Coursepush selectByPrimaryKey(String courseId){
		return coursepushMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Coursepush record){
		return coursepushMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Coursepush record){
		return coursepushMapper.updateByPrimaryKey(record);
	}

}