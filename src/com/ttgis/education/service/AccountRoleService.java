package com.ttgis.education.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.AccountRoleKey;
import com.ttgis.education.mapper.AccountRoleMapper;

@Repository
@Service
public class AccountRoleService {
	
	@Resource
	private AccountRoleMapper accountRoleMapper;
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public int deleteByPrimaryKey(AccountRoleKey key){
    	return accountRoleMapper.deleteByPrimaryKey(key);
    }

	/**
	 * 
	 * @param record
	 * @return
	 */
	public int insert(AccountRoleKey record){
		return accountRoleMapper.insert(record);
	}

	/**
	 * 
	 * @param record
	 * @return
	 */
	public int insertSelective(AccountRoleKey record){
		return accountRoleMapper.insertSelective(record);
    }
}