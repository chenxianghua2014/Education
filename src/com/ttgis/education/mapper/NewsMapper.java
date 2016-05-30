package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.News;

public interface NewsMapper {
   


	int deleteByPrimaryKey(String newsId);

	int insert(News record);

	int insertSelective(News news);

	News selectByPrimaryKey(String newsId);

	int updateByPrimaryKeySelective(News news);

	int updateByPrimaryKeyWithBLOBs(News record);

	int updateByPrimaryKey(News record);

	
    
    /**
     * 查询指定条数的数�?
     * @param num
     * @return
     */
    List<News> selectNewsByNUmber(int num); 
	/**
	 * 查询全部数据
	 * @return
	 */
	List<News> selectAllNews(); 
	
	/**
     * 分页查询
     * @return
     */
	List<News> selectPageAll(News p);
	
	/**
     * 总数
     * @return
     */
	int NewsCount(News p);
    /**
     * �?本单位新闻稿序号 
     * @return
     */
    News newsNumber(String organizationId);
    
    /**
	 * 收藏信息分页
	 * @param p
	 * @return
	 */
	List<News> collectionPageAll(News p);
	
	/**
	 * 收藏信息总数
	 * @param p
	 * @return
	 */
	int collectionNewsCount(News p);
	
	
	/**
	 * 前台查询
	 * @param n
	 * @return
	 */
	List<News> NewsByOIdAll(News n);
	
	/**
	 * 前台分页
	 * @param n
	 * @return
	 */
	List<News> webPageAll(News n);
	
	/**
	 * 前台总条数
	 * @param n
	 * @return
	 */
	int webNewsCount(News n);
	
	/**
	 * 查询过期置顶
	 * @param n
	 * @return
	 */
	List<News> webNewsTOP(News n);
	
	/**
	 * 站内模糊查询搜索
	 * @param n
	 * @return
	 */
	List<News> newsMyName(News n);
}