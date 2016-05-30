package com.ttgis.education.service;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Shift;
import com.ttgis.education.mapper.ShiftMapper;


@Repository
@Service
public class ShiftService {
	
	@Resource
	private ShiftMapper shiftMapper;
	
	public int insert(Shift record) {
		return shiftMapper.insert(record);
	}

	public int insertSelective(Shift record) {
		return shiftMapper.insertSelective(record);
	}
	
	public int deleteByPrimaryKey(String accountId) {
		return shiftMapper.deleteByPrimaryKey(accountId);
	}
	
	 public List<Shift>ShiftByOId(Shift record) {
		return shiftMapper.ShiftByOId(record);
	}
	
	
}