package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Homework;

public interface HomeworkMapper {
    int deleteByPrimaryKey(String homeworkId);

    int insert(Homework record);

    int insertSelective(Homework record);

    Homework selectByPrimaryKey(String homeworkId);

    int updateByPrimaryKeySelective(Homework record);

    int updateByPrimaryKey(Homework record);
    
    Homework selectByCourseId(String courseId);
    
    String selectIdByHomeworkPath(String homeworkPath);
    /**
     * 总数
     * @return
     */
	int homeworkCount(Homework homework);
	/**
     * 分页查询
     * @return
     */
	List<Homework> selectPageAll(Homework homework);
    
}