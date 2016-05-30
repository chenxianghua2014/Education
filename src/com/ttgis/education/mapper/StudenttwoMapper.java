package com.ttgis.education.mapper;

import com.ttgis.education.entity.Studenttwo;

public interface StudenttwoMapper {
    int deleteByPrimaryKey(String studenttwoId);

    int insert(Studenttwo record);

    int insertSelective(Studenttwo record);

    Studenttwo selectByPrimaryKey(String studenttwoId);

    int updateByPrimaryKeySelective(Studenttwo record);

    int updateByPrimaryKey(Studenttwo record);
    
    Studenttwo queryStudentByStudenttwoId(String studenttwoId);
    
}