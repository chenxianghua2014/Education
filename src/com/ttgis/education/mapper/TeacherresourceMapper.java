package com.ttgis.education.mapper;

import com.ttgis.education.entity.Teacherresource;

public interface TeacherresourceMapper {
    int deleteByPrimaryKey(String teacherresourceId);

    int insert(Teacherresource record);

    int insertSelective(Teacherresource record);

    Teacherresource selectByPrimaryKey(String teacherresourceId);

    int updateByPrimaryKeySelective(Teacherresource record);

    int updateByPrimaryKey(Teacherresource record);
}