package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Permission;
import com.ttgis.education.mapper.PermissionMapper;

@Repository
@Service
public class PermissionService {
	
	@Resource
	private PermissionMapper permissionMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return permissionMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Permission record){
		return permissionMapper.insert(record);
	}

	public int insertSelective(Permission record){
		return permissionMapper.insertSelective(record);
	}

	public Permission selectByPrimaryKey(String courseId){
		return permissionMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Permission record){
		return permissionMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Permission record){
		return permissionMapper.updateByPrimaryKey(record);
	}

}