package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Courseresource;

public interface CourseresourceMapper {
    int deleteByPrimaryKey(String courseresourceId);

    int insert(Courseresource record);

    int insertSelective(Courseresource record);

    Courseresource selectByPrimaryKey(String courseresourceId);

    int updateByPrimaryKeySelective(Courseresource record);

    int updateByPrimaryKey(Courseresource record);
    
    /**
     * 上传资源总数
     * @param record
     * @return
     */
    int CourseresourceCount(Courseresource record);
    
    /**
     * 上传资源分页
     * @param record
     * @return
     */
    List<Courseresource> selectPageAll(Courseresource record);
    
    /**
     * 前台显示
     * @param record
     * @return
     */
    List<Courseresource> CourseresourceAll(Courseresource record);
}