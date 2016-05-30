package com.ttgis.education.mapper;

import com.ttgis.education.entity.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(String permissionId);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(String permissionId);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}