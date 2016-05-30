package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Role;
import com.ttgis.education.mapper.RoleMapper;

@Repository
@Service
public class RoleService {
	
	@Resource
	private RoleMapper roleMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return roleMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Role record){
		return roleMapper.insert(record);
	}

	public int insertSelective(Role record){
		return roleMapper.insertSelective(record);
	}

	public Role selectByPrimaryKey(String courseId){
		return roleMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Role record){
		return roleMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Role record){
		return roleMapper.updateByPrimaryKey(record);
	}

}