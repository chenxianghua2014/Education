package com.ttgis.education.mapper;

import java.util.List;
import java.util.Map;

import com.ttgis.education.entity.Bbs;
import com.ttgis.education.plugin.PageEntity;

public interface BbsMapper
{
	int deleteByPrimaryKey(String bbsId);

	int insert(Bbs record);

	int insertSelective(Bbs record);

	Bbs selectDetailsByPrimaryKey(Map<String, String> map);

	Bbs selectByPrimaryKey(String bbsId);

	int updateByPrimaryKeySelective(Bbs record);

	int updateByPrimaryKey(Bbs record);

	List<Bbs> listPageInfo(PageEntity page);
	
	/**
     * 总数
     * @return
     */
	int bbsCount(Bbs bbs);
	/**
     * 分页查询
     * @return
     */
	List<Bbs> queryBbsByPage(Bbs bbs);
}