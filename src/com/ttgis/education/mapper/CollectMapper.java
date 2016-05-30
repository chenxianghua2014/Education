package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Collect;

public interface CollectMapper {
    int deleteByPrimaryKey(String collectId);

    int insert(Collect record);

    int insertSelective(Collect record);

    Collect selectByPrimaryKey(String collectId);

    int updateByPrimaryKeySelective(Collect record);

    int updateByPrimaryKey(Collect record);
    
    List<Collect> selectScKey(Collect record);
    
    int deleteScKey(Collect record);
}