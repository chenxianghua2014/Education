package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Statistical;
import com.ttgis.education.mapper.StatisticalMapper;

@Repository
@Service
public class StatisticalService
{

	@Resource
	private StatisticalMapper statisticsMapper;

	public int insertSelective(Statistical statistics)
	{
		return statisticsMapper.insertSelective(statistics);
	}

	public int updateByPrimaryKeySelective(Statistical statistics)
	{
		return statisticsMapper.updateByPrimaryKeySelective(statistics);
	}

	public int deleteByPrimaryKey(String replyId)
	{
		return statisticsMapper.deleteByPrimaryKey(replyId);
	}

	public Statistical selectByPrimaryKey(String replyId)
	{
		return statisticsMapper.selectByPrimaryKey(replyId);
	}

	public int cancelPraise(Statistical statistics)
	{
		return statisticsMapper.cancelPraise(statistics);
	}
}
