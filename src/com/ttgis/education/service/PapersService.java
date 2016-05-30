package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Papers;
import com.ttgis.education.mapper.PapersMapper;

@Repository
@Service
public class PapersService
{

	@Resource
	private PapersMapper papersMapper;

	public int deleteByPrimaryKey(String courseId)
	{
		return papersMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Papers record)
	{
		return papersMapper.insert(record);
	}

	public int insertSelective(Papers record)
	{
		return papersMapper.insertSelective(record);
	}

	public Papers selectByPrimaryKey(String papersId)
	{
		return papersMapper.selectByPrimaryKey(papersId);
	}

	public int updateByPrimaryKeySelective(Papers record)
	{
		return papersMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Papers record)
	{
		return papersMapper.updateByPrimaryKey(record);
	}
	
    /**
     * 试卷套数 
     * @param record
     * @return
     */
	public List<Integer> selectCountByWhere(Papers record) {
		return papersMapper.selectCountByWhere(record);
	}
	
	 /**
     * 随机抽取试题
     * @param record
     * @return
     */
	public Papers thePapers(Papers record) {
		return papersMapper.thePapers(record);
	}
	
	/**
     * 获取试题
     * @param record
     * @return
     */
	public List<Papers> MyPapersQuery(Papers record) {
		return papersMapper.MyPapersQuery(record);
	}
	
	/**
     * 删除试卷
     * @param record
     * @return
     */
	public int updatePid(Papers record) {
		return papersMapper.updatePid(record);
	}
	
	/**
     * 获取最后一个试卷组号
     * @param record
     * @return
     */
	public Papers PapersMySum(Papers record) {
		return papersMapper.PapersMySum(record);
	}

	   /**
     * 根据课程ID查全部
     * @param record
     * @return
     */
	public List<Papers> thePapersCourseId(Papers record) {
		return papersMapper.thePapersCourseId(record);
	}



}