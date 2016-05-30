package com.ttgis.education.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Dictionary;
import com.ttgis.education.mapper.DictionaryMapper;

@Repository
@Service
public class DictionaryService {
	
	@Resource
	private DictionaryMapper dictionaryMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return dictionaryMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Dictionary record){
		return dictionaryMapper.insert(record);
	}

	public int insertSelective(Dictionary record){
		return dictionaryMapper.insertSelective(record);
	}

	public Dictionary selectByPrimaryKey(String courseId){
		return dictionaryMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Dictionary record){
		return dictionaryMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Dictionary record){
		return dictionaryMapper.updateByPrimaryKey(record);
	}
	/**
	 * 查部代码描述（头部）
	 * @param dictionaryCodeset
	 * @return
	 */
	public  List<Dictionary>  selectDataKey(String dictionaryCodeset){
		return dictionaryMapper.selectDataKey(dictionaryCodeset);
	}
	/**
	 * 查部转译
	 * @param dictionaryCodeset
	 * @return
	 */
	public  Dictionary  selectDataZyKey(Map<String, String> map){
		return dictionaryMapper.selectDataZyKey(map);
	}
	/**
	 * 查部代码描述（头部）
	 * @param dictionaryCodeset
	 * @return
	 */
	public  List<Dictionary>  selectDataTopKey(String dictionaryCodeset){
		return dictionaryMapper.selectDataTopKey(dictionaryCodeset);
	}
	/**
	 * 查部代码描述（尾部）
	 * @param dictionaryCodeset
	 * @return
	 */
	public  List<Dictionary>  selectDataTailKey(String dictionaryCodeset){
		return dictionaryMapper.selectDataTailKey(dictionaryCodeset);
	}
	
	 /**
	  * 通过明文查询加密
	  * @param dictionaryDescribe
	  * @return
	  */
	public Dictionary selectDescribe(Dictionary dd) {
		return dictionaryMapper.selectDescribe(dd);
	}


}