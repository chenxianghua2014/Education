package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Shift;

public interface ShiftMapper {
    int insert(Shift record);

    int insertSelective(Shift record);
    
    int deleteByPrimaryKey(String accountId);
    
    List<Shift>ShiftByOId(Shift record);
}