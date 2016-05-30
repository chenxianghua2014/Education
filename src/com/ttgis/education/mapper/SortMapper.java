package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Sort;

public interface SortMapper {
    int insert(Sort record);

    int insertSelective(Sort record);
    
    int deleteByPrimaryKey(String id);
    
    /**
     * 查询用户下信息
     * @param record
     * @return
     */
    List <Sort> sortMyID(Sort record);
}