package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(String teacherId);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String teacherId);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
    
    /**
     * 通过组织ID查询
     * @param record
     * @return
     */
    List<Teacher> selectByOId(Teacher record);
    
    /**
     * 总数
     * @return
     */
	int teacherCount(Teacher teacher);
	/**
     * 分页查询
     * @return
     */
	List<Teacher> selectPageAll(Teacher t);
	
}