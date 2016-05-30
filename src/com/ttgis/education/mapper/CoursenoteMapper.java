package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Coursenote;


public interface CoursenoteMapper {
    int deleteByPrimaryKey(String coursenoteId);

    int insert(Coursenote record);

    int insertSelective(Coursenote record);

    Coursenote selectByPrimaryKey(String coursenoteId);

    int updateByPrimaryKeySelective(Coursenote record);

    int updateByPrimaryKeyWithBLOBs(Coursenote record);

    int updateByPrimaryKey(Coursenote record);
    
    /**
     * 总数
     * @return
     */
	int coursenoteCount(Coursenote coursenote);
	/**
     * 分页查询
     * @return
     */
	List<Coursenote> selectPageAll(Coursenote coursenote);
	/**
     * 前台 根据视频id查询
     * @return
     */
	List<Coursenote> selectAllList(Coursenote coursenote);
	
	
}