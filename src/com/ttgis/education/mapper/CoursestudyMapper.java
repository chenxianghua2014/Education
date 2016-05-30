package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Coursestudy;

public interface CoursestudyMapper {


	int deleteByPrimaryKey(String coursestudyId);

    int insert(Coursestudy record);

    int insertSelective(Coursestudy record);

    Coursestudy selectByPrimaryKey(String coursestudyId);

    int updateByPrimaryKeySelective(Coursestudy record);

    int updateByPrimaryKey(Coursestudy record);
    
    /**
     * 判断是否重复
     * @param record
     * @return
     */
    List<Coursestudy> selectPanDuanKey(Coursestudy record);
    
    /**
     * 通过学院查询推�?课程
     * @param record
     * @return
     */
    List<Coursestudy> CoursestudyAll(Coursestudy record);
    
    /**
     * 前台课程分页
     * @param record
     * @return
     */
    List<Coursestudy> selectPageWebAll(Coursestudy record);
    
    /**
     * 前台课程总数
     * @param record
     * @return
     */
    int CoursestudyWebCount(Coursestudy record);
    
    
    
    /**
     * 我的课程前台课程分页
     * @param record
     * @return
     */
    List<Coursestudy> selectPageWebTypeAll(Coursestudy record);
    
    /**
     * 我的课程前台课程总数
     * @param record
     * @return
     */
    int CoursestudyWebType(Coursestudy record);
    
    /**
     * 后台培训报告      分页
     * @param record
     * @return
     */
    List<Coursestudy> TrainingReportTopAll(Coursestudy record);
    
    /**
     * 后台培训报告       总数
     * @param record
     * @return
     */
    int TrainingReportTop(Coursestudy record);
}