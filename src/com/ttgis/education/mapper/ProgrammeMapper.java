package com.ttgis.education.mapper;

import java.util.List;


import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Programme;

public interface ProgrammeMapper {
    


	int deleteByPrimaryKey(String programmeId);

	int insert(Programme record);

	int insertSelective(Programme record);

	Programme selectByPrimaryKey(String programmeId);

	int updateByPrimaryKeySelective(Programme record);

	int updateByPrimaryKeyWithBLOBs(Programme record);

	int updateByPrimaryKey(Programme record);

	
    
    /**
     * 查询指定条数的数�?
     * @param num
     * @return
     */
    List<Programme> selectProgrammeByNumber(int num);
    /**
     * 查询全部数据
     * @return
     */
	List<Programme> selectAllProgramme();
	
	/**
     * 分页查询
     * @return
     */
	List<Programme> selectPageAll(Programme p);
	
	/**
     * 总数
     * @return
     */
	int ProgrammeCount(Programme p);
	
	/**
	 * 通过组织结构ID处理计划ID
	 * @param p
	 * @return
	 */
	Programme ProgrammeByOId(Programme p);
	
	/**
	 * ID查询
	 * @param p
	 * @return
	 */
	Programme ProgrammeById(Programme p);
	
	/**
	 * 收藏信息分页
	 * @param p
	 * @return
	 */
	List<Programme> collectionPageAll(Programme p);
	
	/**
	 * 收藏信息总数
	 * @param p
	 * @return
	 */
	int collectionProgrammeCount(Programme p);
	
	/**
	 * 前台显示
	 * @param p
	 * @return
	 */
	List<Programme> ProgrammeByOIdAll(Programme p);
	
	/**
	 * 前台信息分页
	 * @param p
	 * @return
	 */
	List<Programme> webPageAll(Programme p);
	
	/**
	 * 前台信息总数
	 * @param p
	 * @return
	 */
	int webProgrammeCount(Programme p);
	
	
	/**
	 * 查询置顶
	 * @param p
	 * @return
	 */
	List<Programme> webProgrammeTOP(Programme p);
	
	/**
	 * 站内模糊查询计划
	 * @param p
	 * @return
	 */
	List<Programme> programmeMyName(Programme p);
}