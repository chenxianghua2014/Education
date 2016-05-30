package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Log;

public interface LogMapper {
    int deleteByPrimaryKey(String logId);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(String logId);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
    
    /**
     * 总数
     * @return
     */
	int logCount(Log log);
	/**
     * 分页查询
     * @return
     */
	List<Log> queryLogByPage(Log log);
	/**
     * 导出list
     * @param record
     * @return
     */
    List<Log> queryLogList(Log log);
    
}