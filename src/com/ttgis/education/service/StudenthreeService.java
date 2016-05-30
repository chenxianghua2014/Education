package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Studenthree;
import com.ttgis.education.entity.Studentone;
import com.ttgis.education.mapper.StudenthreeMapper;

@Repository
@Service
public class StudenthreeService {
	
	@Resource
	private StudenthreeMapper studenthreeMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return studenthreeMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Studenthree record){
		return studenthreeMapper.insert(record);
	}

	public int insertSelective(Studenthree record){
		return studenthreeMapper.insertSelective(record);
	}

	public Studenthree selectByPrimaryKey(String courseId){
		return studenthreeMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Studenthree record){
		return studenthreeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Studenthree record){
		return studenthreeMapper.updateByPrimaryKey(record);
	}
	public Studenthree queryStudentByStudenthreeId(String studenthreeId){
		return studenthreeMapper.queryStudentByStudenthreeId(studenthreeId);
	}
}