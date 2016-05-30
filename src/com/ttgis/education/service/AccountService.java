package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Account;
import com.ttgis.education.mapper.AccountMapper;

@Repository
@Service
public class AccountService {
	
	@Resource
	private AccountMapper accountMapper;
	
	public int deleteByPrimaryKey(String accountId){
		return accountMapper.deleteByPrimaryKey(accountId);
	}

	public int insert(Account record){
		return accountMapper.insert(record);
	}

	public int insertSelective(Account record){
		return accountMapper.insertSelective(record);
	}

	public Account selectByPrimaryKey(String accountId){
		return accountMapper.selectByPrimaryKey(accountId);
	}

	public int updateByPrimaryKeySelective(Account record){
		return accountMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Account record){
		return accountMapper.updateByPrimaryKey(record);
	}
	 /**
     * 后台账号登录
     * @param account 后台账号
     * @return
     */
	public Account checkLogin(Account account){
		return accountMapper.checkLogin(account);
	}
	/**
     * 分页查询
     * @return
     */
    public List<Account> selectPageAll(Account p) {
		return accountMapper.selectPageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
    public int AccountCount(Account p) {
		return accountMapper.AccountCount(p);
	}
    /**
	 * ID查询
	 * @return
	 */
    public Account AccountById(Account p) {
		return accountMapper.AccountById(p);
	}
    
    /**
	 * 组织提升按ID查询需要提升的用户
	 * @param p
	 * @return
	 */
    public int updateAccountByOId(Account p) {
		return accountMapper.updateAccountByOId(p);
	}
    
    /**
	 * -合并组织结构，修改权限组织
	 * @param p
	 * @return
	 */
    public int updateOId(Account p) {
		return accountMapper.updateOId(p);
	}
    
    /**
	 * 组织ID查询
	 * @param accountCatalog
	 * @return
	 */
    public List<Account> AccountByOId(String accountCatalog) {
		return accountMapper.AccountByOId(accountCatalog);
	}
	
	/**
	 * 组织上级ID查询
	 * @param accountCatalog
	 * @return
	 */
    public List<Account> AccountByOSJId(String accountCatalog) {
		return accountMapper.AccountByOSJId(accountCatalog);
	}
    
    /**
	 * 账号是否存在查询
	 * @param accountLoginid
	 * @return
	 */
    public Account AccountByName(String accountLoginid) {
		return accountMapper.AccountByName(accountLoginid);
	}
	
	/**
	 * 组织下是否有账号查询
	 * @param accountCatalog
	 * @return
	 */
    public Account AccountByOName(String accountCatalog) {
		return accountMapper.AccountByOName(accountCatalog);
	}
    
    /**
	 * 身份证是否存在查询
	 * @param accountLoginid
	 * @return
	 */
    public Account AccountBySFZH(String accountSfzh) {
		return accountMapper.AccountBySFZH(accountSfzh);
	}

}