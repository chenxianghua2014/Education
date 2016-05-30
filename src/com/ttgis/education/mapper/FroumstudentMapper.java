package com.ttgis.education.mapper;

import com.ttgis.education.entity.Froumstudent;

public interface FroumstudentMapper {
    int deleteByPrimaryKey(String froumstudentId);

    int insert(Froumstudent record);

    int insertSelective(Froumstudent record);

    Froumstudent selectByPrimaryKey(String froumstudentId);

    int updateByPrimaryKeySelective(Froumstudent record);

    int updateByPrimaryKey(Froumstudent record);
}