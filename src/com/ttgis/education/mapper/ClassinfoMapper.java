package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Classinfo;

public interface ClassinfoMapper {
    int deleteByPrimaryKey(String classinfoId);

	int insert(Classinfo record);

	int insertSelective(Classinfo record);

	Classinfo selectByPrimaryKey(String classinfoId);

	int updateByPrimaryKeySelective(Classinfo record);

	int updateByPrimaryKey(Classinfo record);
	/**
     * 推送课程与培训班课程对比
     * @param record
     * @return
     */
    List<Classinfo> selectByXinPanIdKey(Classinfo in);
	/**
     * 
     * @param record
     * @return
     */
    List<Classinfo> selectByTranclassIdKey(String id);
    /**
     * 判断重复
     * @param record
     * @return
     */
    List<Classinfo> selectByClasId(Classinfo record);
    /**
     * 分页查询
     * @return
     */
	List<Classinfo> classInfoPageAll(Classinfo p);
	
	/**
     * 总数
     * @return
     */
	int classInfoCount(Classinfo p);
	
	 /**
     * 培训报告   培训班名称    可到培训班/班次 
     * @return
     */
	Classinfo selectTrainingName(String p);

}