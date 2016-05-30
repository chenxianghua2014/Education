package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Studentone;
import com.ttgis.education.mapper.StudentoneMapper;

@Repository
@Service
public class StudentoneService {
	
	@Resource
	private StudentoneMapper studentoneMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return studentoneMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Studentone record){
		return studentoneMapper.insert(record);
	}

	public int insertSelective(Studentone record){
		return studentoneMapper.insertSelective(record);
	}

	public Studentone selectByPrimaryKey(String courseId){
		return studentoneMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Studentone record){
		return studentoneMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Studentone record){
		return studentoneMapper.updateByPrimaryKey(record);
	}
	public Studentone queryStudentByStudentoneId(String studentoneId){
		return studentoneMapper.queryStudentByStudentoneId(studentoneId);
	}
	
	/**
     * 查询身份证号是否重复
     * @param record
     * @return
     */
	public List<Studentone> queryStudentBySFZH(Studentone record) {
		return studentoneMapper.queryStudentBySFZH(record);
	}
	
}