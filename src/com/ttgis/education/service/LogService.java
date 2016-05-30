package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Log;
import com.ttgis.education.mapper.LogMapper;

@Repository
@Service
public class LogService {
	
	@Resource
	private LogMapper logMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return logMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Log record){
		return logMapper.insert(record);
	}

	public int insertSelective(Log record){
		return logMapper.insertSelective(record);
	}

	public Log selectByPrimaryKey(String courseId){
		return logMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Log record){
		return logMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Log record){
		return logMapper.updateByPrimaryKey(record);
	}
	/**
     * 总数
     * @return
     */
	public int logCount(Log log)
	{
		return logMapper.logCount(log);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Log> queryLogByPage(Log log)
	{
		return logMapper.queryLogByPage(log);
	}
	/**
     * 通过组织ID查询
     * @param record
     * @return
     */
	public List<Log> queryLogList(Log log) {
		return logMapper.queryLogList(log);
	}

}