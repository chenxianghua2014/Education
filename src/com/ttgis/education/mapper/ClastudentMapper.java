package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Clastudent;
import com.ttgis.education.entity.News;

public interface ClastudentMapper {
    int deleteByPrimaryKey(String clastudentId);

    int insert(Clastudent record);

    int insertSelective(Clastudent record);

    Clastudent selectByPrimaryKey(String clastudentId);

    int updateByPrimaryKeySelective(Clastudent record);

    int updateByPrimaryKey(Clastudent record);
    /**
     * 我的课程 前台  培训班
     * @param record
     * @return
     */
    List<Clastudent> selectAllKey(Clastudent record);
    /**
     * 判断重复
     * @param record
     * @return
     */
    List<Clastudent> selectByCourseId(Clastudent record);
	/**
     * 分页查询
     * @return
     */
	List<Clastudent> classStudentPageAll(Clastudent p);
	
	/**
     * 总数
     * @return
     */
	int classStudentCount(Clastudent p);
	/**
     * 前台我的课程  培训班        分页查询
     * @return
     */
	List<Clastudent> classStudentPageAllIn(Clastudent p);
	
	/**
     * 前台我的课程  培训班        条数  总数
     * @return
     */
	int selectAllInKey(Clastudent p);
	/**
     * 根据学生Id查培训班次
     * @param record
     * @return
     */
    List<Clastudent> selectStudentClaId(String id);
	/**
     * 根据培训班Id查审核通过的培训班学生
     * @param record
     * @return
     */
    List<Clastudent> selectClasId(String id);
    
}