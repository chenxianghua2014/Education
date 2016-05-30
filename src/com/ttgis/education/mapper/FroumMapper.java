package com.ttgis.education.mapper;

import com.ttgis.education.entity.Froum;

public interface FroumMapper {
    int deleteByPrimaryKey(String froumId);

    int insert(Froum record);

    int insertSelective(Froum record);

    Froum selectByPrimaryKey(String froumId);

    int updateByPrimaryKeySelective(Froum record);

    int updateByPrimaryKeyWithBLOBs(Froum record);

    int updateByPrimaryKey(Froum record);
}