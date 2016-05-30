package com.ttgis.education.mapper;

import com.ttgis.education.entity.Froumreply;

public interface FroumreplyMapper {
    int deleteByPrimaryKey(String froumreplyId);

    int insert(Froumreply record);

    int insertSelective(Froumreply record);

    Froumreply selectByPrimaryKey(String froumreplyId);

    int updateByPrimaryKeySelective(Froumreply record);

    int updateByPrimaryKeyWithBLOBs(Froumreply record);

    int updateByPrimaryKey(Froumreply record);
}