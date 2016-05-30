package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Coursestudy;
import com.ttgis.education.mapper.CoursestudyMapper;

@Repository
@Service
public class CoursestudyService {
	
	@Resource
	private CoursestudyMapper coursestudyMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return coursestudyMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Coursestudy record){
		return coursestudyMapper.insert(record);
	}

	public int insertSelective(Coursestudy record){
		return coursestudyMapper.insertSelective(record);
	}

	public Coursestudy selectByPrimaryKey(String courseId){
		return coursestudyMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Coursestudy record){
		return coursestudyMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Coursestudy record){
		return coursestudyMapper.updateByPrimaryKey(record);
	}
	
	/**
     * 判断是否重复
     * @param record
     * @return
     */
	public List<Coursestudy> selectPanDuanKey(Coursestudy record) {
		return coursestudyMapper.selectPanDuanKey(record);
	}
	
	/**
     * 通过学院查询推送课程
     * @param record
     * @return
     */
	public List<Coursestudy> CoursestudyAll(Coursestudy record) {
		return coursestudyMapper.CoursestudyAll(record);
	}
	
	  /**
     * 前台课程分页
     * @param record
     * @return
     */
	public List<Coursestudy> selectPageWebAll(Coursestudy record) {
		return coursestudyMapper.selectPageWebAll(record);
	}
    
    /**
     * 前台课程总数
     * @param record
     * @return
     */
	public int CoursestudyWebCount(Coursestudy record) {
		return coursestudyMapper.CoursestudyWebCount(record);
	}
	
	  /**
     * 我的课程前台课程分页
     * @param record
     * @return
     */
	public List<Coursestudy> selectPageWebTypeAll(Coursestudy record) {
		return coursestudyMapper.selectPageWebTypeAll(record);
	}
    
    /**
     * 我的课程前台课程总数
     * @param record
     * @return
     */
	public int CoursestudyWebType(Coursestudy record) {
		return coursestudyMapper.CoursestudyWebType(record);
	}
	/**
     * 后台培训报告  分页查询
     * @return
     */
	public List<Coursestudy> TrainingReportTopAll(Coursestudy p) {
		return coursestudyMapper.TrainingReportTopAll(p);
	}
	
	/**
     * 后台培训报告    总数
     * @return
     */
	public int TrainingReportTop(Coursestudy p) {
		return coursestudyMapper.TrainingReportTop(p);
	}
	
}