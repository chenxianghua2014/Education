package com.ttgis.education.mapper;

import com.ttgis.education.entity.AccountRoleKey;

public interface AccountRoleMapper {
    int deleteByPrimaryKey(AccountRoleKey key);

    int insert(AccountRoleKey record);

    int insertSelective(AccountRoleKey record);
}