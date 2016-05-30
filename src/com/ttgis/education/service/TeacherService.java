package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Teacher;
import com.ttgis.education.mapper.TeacherMapper;

@Repository
@Service
public class TeacherService {
	
	@Resource
	private TeacherMapper teacherMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return teacherMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Teacher record){
		return teacherMapper.insert(record);
	}

	public int insertSelective(Teacher record){
		return teacherMapper.insertSelective(record);
	}

	public Teacher selectByPrimaryKey(String courseId){
		return teacherMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Teacher record){
		return teacherMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Teacher record){
		return teacherMapper.updateByPrimaryKey(record);
	}
	
	/**
     * 通过组织ID查询
     * @param record
     * @return
     */
	public List<Teacher> selectByOId(Teacher record) {
		return teacherMapper.selectByOId(record);
	}
	 /**
     * 总数
     * @return
     */
	public int teacherCount(Teacher teacher){
		return teacherMapper.teacherCount(teacher);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Teacher> selectPageAll(Teacher t){
		return teacherMapper.selectPageAll(t);
	}
}