package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Studentone;

public interface StudentoneMapper {
    int deleteByPrimaryKey(String studentoneId);

    int insert(Studentone record);

    int insertSelective(Studentone record);

    Studentone selectByPrimaryKey(String studentoneId);

    int updateByPrimaryKeySelective(Studentone record);

    int updateByPrimaryKey(Studentone record);
    
    Studentone queryStudentByStudentoneId(String studentoneId);
    
    /**
     * 查询身份证号是否重复
     * @param record
     * @return
     */
    List<Studentone> queryStudentBySFZH(Studentone record);
}