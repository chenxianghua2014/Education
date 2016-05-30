package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Studenttwo;
import com.ttgis.education.mapper.StudenttwoMapper;

@Repository
@Service
public class StudenttwoService {
	
	@Resource
	private StudenttwoMapper studenttwoMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return studenttwoMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Studenttwo record){
		return studenttwoMapper.insert(record);
	}

	public int insertSelective(Studenttwo record){
		return studenttwoMapper.insertSelective(record);
	}

	public Studenttwo selectByPrimaryKey(String courseId){
		return studenttwoMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Studenttwo record){
		return studenttwoMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Studenttwo record){
		return studenttwoMapper.updateByPrimaryKey(record);
	}
	
	public Studenttwo queryStudentByStudenttwoId(String studenttwoId){
		return studenttwoMapper.queryStudentByStudenttwoId(studenttwoId);
	}
	 
}