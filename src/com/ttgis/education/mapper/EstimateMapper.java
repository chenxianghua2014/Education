package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Estimate;

public interface EstimateMapper {
    int deleteByPrimaryKey(String estimateId);

	int insert(Estimate record);

	int insertSelective(Estimate record);

	Estimate selectByPrimaryKey(String estimateId);

	int updateByPrimaryKeySelective(Estimate record);

	int updateByPrimaryKey(Estimate record);

	List<Estimate> estimatepage(Course ncourse);

	int estimateCount(Course ncourse);

	Estimate selectEstimatesfcz(Estimate e);

	
}