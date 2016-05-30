package com.ttgis.education.mapper;

import com.ttgis.education.entity.Statistical;

public interface StatisticalMapper
{
	int deleteByPrimaryKey(String statisticsId);

	int insert(Statistical record);

	int insertSelective(Statistical record);

	Statistical selectByPrimaryKey(String statisticsId);

	int updateByPrimaryKeySelective(Statistical record);

	int updateByPrimaryKey(Statistical record);

	int cancelPraise(Statistical statistics);
}