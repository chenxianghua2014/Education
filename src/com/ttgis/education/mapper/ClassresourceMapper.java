package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.trainCroursePage;

public interface ClassresourceMapper {
    int deleteByPrimaryKey(String classresourceId);

    int insert(Classresource record);

    int insertSelective(Classresource record);

    Classresource selectByPrimaryKey(String classresourceId);

    int updateByPrimaryKeySelective(Classresource record);

    int updateByPrimaryKey(Classresource record);
    /**
     * 
     * 查询给定节点ID下的直接子节点
     * @param syllabsid
     * @return
     */
    List<String> selectByUpperId(String classresourceUpid);
    /**
     * 通过ID查询下级数据
     * @param record
     * @return
     */
    List<Classresource> classresourceMyID (Classresource record);
    
    /**
     * 通过组织机构ID查询
     * @param record
     * @return
     */
    List<Classresource> classresourceByOID (Classresource record);
    
    /**
     * 本身ID查询
     * @param record
     * @return
     */
    Classresource classresourceByID(Classresource record);
    
    
    /**
     * 分页查询
     * @return
     */
	List<Classresource> selectClassresourcePageAll(Classresource p);
	
	/**
     * 总数
     * @return
     */
	int ClassresourceCount(Classresource p);
	/**
     * 查询培训班
     * @return
     */
	 List<Classresource> selectByAllKey(Classresource p);
	 /**
     * 党校培训班  查询
     * @return
     */
	 List<Classresource> selectPartyTrain(Classresource p);
}