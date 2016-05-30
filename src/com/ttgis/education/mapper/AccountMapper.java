package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Account;

public interface AccountMapper {


	int deleteByPrimaryKey(String accountId);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(String accountId);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);
    /**
     * 后台账号登录
     * @param account 后台账号
     * @return
     */
    Account checkLogin(Account account);
    
    /**
     * 分页查询
     * @return
     */
	List<Account> selectPageAll(Account p);
	
	/**
     * 总数
     * @return
     */
	int AccountCount(Account p);
	
	/**
	 * ID查询
	 * @return
	 */
	Account AccountById(Account p);
	
	/**
	 * 组织提升按ID查询需要提升的用户
	 * @param p
	 * @return
	 */
	int updateAccountByOId(Account p);
	
	/**
	 * -合并组织结构，修改权限组织
	 * @param p
	 * @return
	 */
	int updateOId(Account p);
	
	/**
	 * 组织ID查询
	 * @param accountCatalog
	 * @return
	 */
	List<Account> AccountByOId(String accountCatalog);
	
	/**
	 * 组织上级ID查询
	 * @param accountCatalog
	 * @return
	 */
	List<Account> AccountByOSJId(String accountCatalog);
	
	/**
	 * 账号是否存在查询
	 * @param accountLoginid
	 * @return
	 */
	Account AccountByName(String accountLoginid);
	
	/**
	 * 组织下是否有账号查询
	 * @param accountCatalog
	 * @return
	 */
	Account AccountByOName(String accountCatalog);
	
	/**
	 * 身份证是否存在查询
	 * @param accountLoginid
	 * @return
	 */
	Account AccountBySFZH(String accountSfzh);
}