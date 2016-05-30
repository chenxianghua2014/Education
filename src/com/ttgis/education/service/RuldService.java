package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Ruld;
import com.ttgis.education.mapper.RuldMapper;

@Repository
@Service
public class RuldService
{

	@Resource
	private RuldMapper ruldMapper;

	public int deleteByPrimaryKey(String courseId)
	{
		return ruldMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Ruld record)
	{
		return ruldMapper.insert(record);
	}

	public int insertSelective(Ruld record)
	{
		return ruldMapper.insertSelective(record);
	}

	public Ruld selectByPrimaryKey(String courseId)
	{
		return ruldMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Ruld record)
	{
		return ruldMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Ruld record)
	{
		return ruldMapper.updateByPrimaryKey(record);
	}

	public List<Ruld> selectCountByWhere(Ruld subject)
	{
		return ruldMapper.selectCountByWhere(subject);
	}

	public List<Ruld> selectByWhere(Ruld subject)
	{
		return ruldMapper.selectByWhere(subject);
	}



}