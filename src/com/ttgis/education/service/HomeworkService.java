package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Homework;
import com.ttgis.education.mapper.HomeworkMapper;

@Repository
@Service
public class HomeworkService {
	
	@Resource
	private HomeworkMapper homeworkMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return homeworkMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Homework record){
		return homeworkMapper.insert(record);
	}

	public int insertSelective(Homework record){
		return homeworkMapper.insertSelective(record);
	}

	public Homework selectByPrimaryKey(String courseId){
		return homeworkMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Homework record){
		return homeworkMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Homework record){
		return homeworkMapper.updateByPrimaryKey(record);
	}
	
	public Homework selectByCourseId(String courseId){
		return homeworkMapper.selectByCourseId(courseId);
	}
	/**
     * 总数
     * @return
     */
	public int homeworkCount(Homework homework){
		return homeworkMapper.homeworkCount(homework);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Homework> selectPageAll(Homework homework){
		return homeworkMapper.selectPageAll(homework);
	}
	public String selectIdByHomeworkPath(String homeworkPath){
		return homeworkMapper.selectIdByHomeworkPath(homeworkPath);
	}
	
}