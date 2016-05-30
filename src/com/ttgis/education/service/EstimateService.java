package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Estimate;
import com.ttgis.education.mapper.EstimateMapper;

@Repository
@Service
public class EstimateService {
	
	@Resource
	private EstimateMapper estimateMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return estimateMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Estimate record){
		return estimateMapper.insert(record);
	}

	public int insertSelective(Estimate record){
		return estimateMapper.insertSelective(record);
	}

	public Estimate selectByPrimaryKey(String courseId){
		return estimateMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Estimate record){
		return estimateMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Estimate record){
		return estimateMapper.updateByPrimaryKey(record);
	}

	public List<Estimate> estimatepage(Course ncourse) {
		return estimateMapper.estimatepage(ncourse);
	}

	public int estimateCount(Course ncourse) {
		return estimateMapper.estimateCount(ncourse);
	}

	public Estimate selectEstimatesfcz(Estimate e) {
		return estimateMapper.selectEstimatesfcz(e);
	}

}