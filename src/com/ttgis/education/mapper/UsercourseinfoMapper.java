package com.ttgis.education.mapper;

import com.ttgis.education.entity.Usercourseinfo;

public interface UsercourseinfoMapper {
    int insert(Usercourseinfo record);

    int insertSelective(Usercourseinfo record);
    
    int deleteByPrimaryKey(String userId);

    Usercourseinfo selectByPrimaryKey(String userId);

	int updateByPrimaryKeySelective(Usercourseinfo news);

//	int updateByPrimaryKeyWithBLOBs(Usercourseinfo record);
//
//	int updateByPrimaryKey(Usercourseinfo record);
}