package com.ttgis.education.mapper;

import com.ttgis.education.entity.Coursepush;

public interface CoursepushMapper {
    int deleteByPrimaryKey(String coursepushId);

    int insert(Coursepush record);

    int insertSelective(Coursepush record);

    Coursepush selectByPrimaryKey(String coursepushId);

    int updateByPrimaryKeySelective(Coursepush record);

    int updateByPrimaryKey(Coursepush record);
}