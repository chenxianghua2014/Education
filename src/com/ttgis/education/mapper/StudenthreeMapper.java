package com.ttgis.education.mapper;

import com.ttgis.education.entity.Studenthree;

public interface StudenthreeMapper {
    int deleteByPrimaryKey(String studenthreeId);

    int insert(Studenthree record);

    int insertSelective(Studenthree record);

    Studenthree selectByPrimaryKey(String studenthreeId);

    int updateByPrimaryKeySelective(Studenthree record);

    int updateByPrimaryKey(Studenthree record);
    
    Studenthree queryStudentByStudenthreeId(String studenthreeId);
}