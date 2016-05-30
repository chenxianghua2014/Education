package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Coursestudy;
import com.ttgis.education.entity.Record;

public interface RecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(Record record);

    int insertSelective(Record record);

    Record selectByPrimaryKey(String recordId);

    int updateByPrimaryKeySelective(Record record);

    int updateByPrimaryKeyWithBLOBs(Record record);

    int updateByPrimaryKey(Record record);
    
    /**
     * 上传记录      分页
     * @param record
     * @return
     */
    List<Record> uploadReportsTopAll(Record record);
    
    /**
     * 上传记录       总数
     * @param record
     * @return
     */
    int uploadReportsTop(Record record);
}