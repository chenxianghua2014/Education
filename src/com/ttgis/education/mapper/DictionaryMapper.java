package com.ttgis.education.mapper;

import java.util.List;
import java.util.Map;

import com.ttgis.education.entity.Dictionary;

public interface DictionaryMapper {
    int deleteByPrimaryKey(String dictionaryId);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(String dictionaryId);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
    
    /**
	  * 查部代码描述
	  * @param d
	  * @return
	  */
	 List<Dictionary> selectDataKey(String dictionaryCodeset);
	 /**
	  * 转译查部
	  * @param d
	  * @return
	  */
	 Dictionary selectDataZyKey(Map<String, String> map);
	  /**
	  * 查部代码描述（头部）
	  * @param d
	  * @return
	  */
	 List<Dictionary> selectDataTopKey(String dictionaryCodeset);
	  /**
	  * 查部代码描述（尾部）
	  * @param d
	  * @return
	  */
	 List<Dictionary> selectDataTailKey(String dictionaryCodeset);
	 
	 /**
	  * 通过明文查询加密
	  * @param dictionaryDescribe
	  * @return
	  */
	 Dictionary selectDescribe(Dictionary dd);
}