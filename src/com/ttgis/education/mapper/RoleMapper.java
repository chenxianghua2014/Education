package com.ttgis.education.mapper;

import com.ttgis.education.entity.Role;

public interface RoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}