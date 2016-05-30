package com.ttgis.education.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Bbs;
import com.ttgis.education.mapper.BbsMapper;
import com.ttgis.education.plugin.PageEntity;

/***
 * 
 * @author SJG
 * 
 */
@Repository
@Service
public class BbsService
{
	@Resource
	private BbsMapper bbsMapper;

	public int insertSelective(Bbs bbs)
	{
		return bbsMapper.insertSelective(bbs);
	}

	public int updateByPrimaryKeySelective(Bbs bbs)
	{
		return bbsMapper.updateByPrimaryKeySelective(bbs);
	}

	public int deleteByPrimaryKey(String bbsId)
	{
		return bbsMapper.deleteByPrimaryKey(bbsId);
	}

	public Bbs selectDetailsByPrimaryKey(Map<String, String> map)
	{
		return bbsMapper.selectDetailsByPrimaryKey(map);
	}

	public Bbs selectByPrimaryKey(String bbsId)
	{
		return bbsMapper.selectByPrimaryKey(bbsId);
	}

	public List<Bbs> listPageInfo(PageEntity page)
	{
		return bbsMapper.listPageInfo(page);
	}
	
	/**
     * 总数
     * @return
     */
	public int bbsCount(Bbs bbs)
	{
		return bbsMapper.bbsCount(bbs);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Bbs> queryBbsByPage(Bbs bbs)
	{
		return bbsMapper.queryBbsByPage(bbs);
	}

}
