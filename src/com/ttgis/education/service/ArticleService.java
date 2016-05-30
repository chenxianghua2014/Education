package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Article;
import com.ttgis.education.mapper.ArticleMapper;

@Repository
@Service
public class ArticleService {
	@Resource
	private ArticleMapper articleMapper;
	
	public int deleteByPrimaryKey(String courseInfoId){
		return articleMapper.deleteByPrimaryKey(courseInfoId);
	}

	public int insert(Article record){
		return articleMapper.insert(record);
	}

	public int insertSelective(Article record){
		return articleMapper.insertSelective(record);
	}

	public Article selectByPrimaryKey(String courseInfoId){
		return articleMapper.selectByPrimaryKey(courseInfoId);
	}

	public int updateByPrimaryKeySelective(Article record){
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Article record){
		return articleMapper.updateByPrimaryKey(record);
	}
}