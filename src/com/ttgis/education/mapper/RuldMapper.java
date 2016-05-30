package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Ruld;

public interface RuldMapper {
   
	int deleteByPrimaryKey(String ruldId);

	int insert(Ruld record);

	int insertSelective(Ruld record);

	Ruld selectByPrimaryKey(String ruldId);

	int updateByPrimaryKeySelective(Ruld record);

	int updateByPrimaryKeyWithBLOBs(Ruld record);

	int updateByPrimaryKey(Ruld record);

	List<Ruld> selectCountByWhere(Ruld record);

	List<Ruld> selectByWhere(Ruld record);
}