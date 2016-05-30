package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.News;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.trainCroursePage;

public interface TranclassMapper {
   

	int deleteByPrimaryKey(String tranclassId);

    int insert(Tranclass record);

    int insertSelective(Tranclass record);

    Tranclass selectByPrimaryKey(String tranclassId);

    int updateByPrimaryKeySelective(Tranclass record);

    int updateByPrimaryKey(Tranclass record);
    
    
    /**
	 * 查询指定条数的数�?
	 */
    List<Tranclass> selectByNum(int num);
    /**
     * 分页查询
     * @return
     */
	List<Tranclass> selectShiftPageAll(Tranclass p);
	
	/**
     * 总数
     * @return
     */
	int ShiftCount(Tranclass p);
    
    /**
     * 培训班ID查询
     * @param t
     * @return
     */
    List<Tranclass> TranclassByOId(Tranclass t);
    
    /**
     * 分页查询  党校页面
     * @return
     */
	List<Tranclass> selectShiftDxPage(Tranclass p);
	
	/**
     * 总数  党校页面
     * @return
     */
	int ShiftCountPage(Tranclass p);
	
	 /**
     * 查询  党校页面前六条
     * @return
     */
	List<Tranclass> selectShiftDxSix(Tranclass p);
    
}