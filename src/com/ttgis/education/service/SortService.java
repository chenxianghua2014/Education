package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Sort;
import com.ttgis.education.mapper.SortMapper;

@Repository
@Service
public class SortService {
	
	@Resource
	private SortMapper sortMapper;
	
	public int insert(Sort record) {
		return sortMapper.insert(record);
	}

	public int insertSelective(Sort record) {
		return sortMapper.insertSelective(record);
	}
	
	public int deleteByPrimaryKey(String id) {
		return sortMapper.deleteByPrimaryKey(id);
	}
	
	 /**
     * 查询用户下信息
     * @param record
     * @return
     */
	public List <Sort> sortMyID(Sort record) {
		return sortMapper.sortMyID(record);
	}
	
	
}