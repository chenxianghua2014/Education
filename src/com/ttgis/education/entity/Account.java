package com.ttgis.education.entity;

import java.util.Date;

public class Account {
    private String accountId;

	private String accountLoginid;

	private String accountPassword;

	private String accountCatalog;

	private Integer accountType;

	private Integer accountRank;

	private String accountAdmin;

	private Date accountAt;

	private Integer accountDel;

	private String accountName;

	private String accountPhone;
	
	private String accountSfzh;
	
	private int page;
	private int size;
	
    private String bc[];

	public String[] getBc() {
		return bc;
	}

	public void setBc(String[] bc) {
		this.bc = bc;
	}

	
	private Organization organization;
	

	
	  public String getAccountSfzh() {
		return accountSfzh;
	}

	public void setAccountSfzh(String accountSfzh) {
		this.accountSfzh = accountSfzh;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	

	public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public Organization getOrganization() {
			return organization;
		}

		public void setOrganization(Organization organization) {
			this.organization = organization;
		}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountLoginid() {
		return accountLoginid;
	}

	public void setAccountLoginid(String accountLoginid) {
		this.accountLoginid = accountLoginid;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getAccountCatalog() {
		return accountCatalog;
	}

	public void setAccountCatalog(String accountCatalog) {
		this.accountCatalog = accountCatalog;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getAccountRank() {
		return accountRank;
	}

	public void setAccountRank(Integer accountRank) {
		this.accountRank = accountRank;
	}

	public String getAccountAdmin() {
		return accountAdmin;
	}

	public void setAccountAdmin(String accountAdmin) {
		this.accountAdmin = accountAdmin;
	}

	public Date getAccountAt() {
		return accountAt;
	}

	public void setAccountAt(Date accountAt) {
		this.accountAt = accountAt;
	}

	public Integer getAccountDel() {
		return accountDel;
	}

	public void setAccountDel(Integer accountDel) {
		this.accountDel = accountDel;
	}



  

    
}