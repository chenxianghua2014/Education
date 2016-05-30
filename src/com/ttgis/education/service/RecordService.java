package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Coursestudy;
import com.ttgis.education.entity.Record;
import com.ttgis.education.mapper.RecordMapper;

@Repository
@Service
public class RecordService {

	@Resource
	private RecordMapper recordMapper;

	public int deleteByPrimaryKey(String recordId) {
		return recordMapper.deleteByPrimaryKey(recordId);
	}

	public int insert(Record record) {
		return recordMapper.insert(record);
	}

	public int insertSelective(Record record) {
		return recordMapper.insertSelective(record);
	}

	public Record selectByPrimaryKey(String recordId) {
		return recordMapper.selectByPrimaryKey(recordId);
	}

	public int updateByPrimaryKeySelective(Record record) {
		return recordMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKeyWithBLOBs(Record record) {
		return recordMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	public int updateByPrimaryKey(Record record) {
		return recordMapper.updateByPrimaryKey(record);
	}
	
	/**
     * 上传记录  分页查询
     * @return
     */
	public List<Record> uploadReportsTopAll(Record p) {
		return recordMapper.uploadReportsTopAll(p);
	}
	
	/**
     * 上传记录    总数
     * @return
     */
	public int uploadReportsTop(Record p) {
		return recordMapper.uploadReportsTop(p);
	}

}
