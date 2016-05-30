package com.ttgis.education.entity;

import java.util.Date;

public class Organization {
    private String organizationId;//组织结构ID
    
    private String organizationDwmc;//组织结构（单位名称）

    private String organizationDwdm;//单位代码

    private String organizationDwzh;//单位账号

    private String organizationZhmm;//账号密码

    private String organizationSjdw;//上级单位ID

    private String organizationDwjc;//上级单位名称

    private String organizationFwqx;//访问权限

    private String organizationDwfzr;//单位负责人

    private String organizationZplxr;//招聘联系人

    private String organizationLxrdh;//联系人电话

    private String organizationLxremail;//联系人邮箱

    private String organizationDwjj;//单位简介

    private Date organizationAt;//组织结构记录添加时间

    private Integer organizationDel;//组织结构删除标记

    private String sfjy;

    private String yjnfy;

    private String yffy;

    private int cpcs;

    private String organizationEjmm;

    private Integer organizationOrder;

    private String organizationGzdh;
    
    private int page;
	private int size;
	
	private String oidall[];//排序组织ID数组
	
	private int oidsort[];//排序数组
	
	private int fwcj;
	
	
	
	



	public int getFwcj() {
		return fwcj;
	}

	public void setFwcj(int fwcj) {
		this.fwcj = fwcj;
	}

	public String[] getOidall() {
		return oidall;
	}

	public void setOidall(String[] oidall) {
		this.oidall = oidall;
	}

	public int[] getOidsort() {
		return oidsort;
	}

	public void setOidsort(int[] oidsort) {
		this.oidsort = oidsort;
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

	public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationDwmc() {
        return organizationDwmc;
    }

    public void setOrganizationDwmc(String organizationDwmc) {
        this.organizationDwmc = organizationDwmc;
    }

    public String getOrganizationDwdm() {
        return organizationDwdm;
    }

    public void setOrganizationDwdm(String organizationDwdm) {
        this.organizationDwdm = organizationDwdm;
    }

    public String getOrganizationDwzh() {
        return organizationDwzh;
    }

    public void setOrganizationDwzh(String organizationDwzh) {
        this.organizationDwzh = organizationDwzh;
    }

    public String getOrganizationZhmm() {
        return organizationZhmm;
    }

    public void setOrganizationZhmm(String organizationZhmm) {
        this.organizationZhmm = organizationZhmm;
    }

    public String getOrganizationSjdw() {
        return organizationSjdw;
    }

    public void setOrganizationSjdw(String organizationSjdw) {
        this.organizationSjdw = organizationSjdw;
    }

    public String getOrganizationDwjc() {
        return organizationDwjc;
    }

    public void setOrganizationDwjc(String organizationDwjc) {
        this.organizationDwjc = organizationDwjc;
    }

    public String getOrganizationFwqx() {
        return organizationFwqx;
    }

    public void setOrganizationFwqx(String organizationFwqx) {
        this.organizationFwqx = organizationFwqx;
    }

    public String getOrganizationDwfzr() {
        return organizationDwfzr;
    }

    public void setOrganizationDwfzr(String organizationDwfzr) {
        this.organizationDwfzr = organizationDwfzr;
    }

    public String getOrganizationZplxr() {
        return organizationZplxr;
    }

    public void setOrganizationZplxr(String organizationZplxr) {
        this.organizationZplxr = organizationZplxr;
    }

    public String getOrganizationLxrdh() {
        return organizationLxrdh;
    }

    public void setOrganizationLxrdh(String organizationLxrdh) {
        this.organizationLxrdh = organizationLxrdh;
    }

    public String getOrganizationLxremail() {
        return organizationLxremail;
    }

    public void setOrganizationLxremail(String organizationLxremail) {
        this.organizationLxremail = organizationLxremail;
    }

    public String getOrganizationDwjj() {
        return organizationDwjj;
    }

    public void setOrganizationDwjj(String organizationDwjj) {
        this.organizationDwjj = organizationDwjj;
    }

    public Date getOrganizationAt() {
        return organizationAt;
    }

    public void setOrganizationAt(Date organizationAt) {
        this.organizationAt = organizationAt;
    }

    public Integer getOrganizationDel() {
        return organizationDel;
    }

    public void setOrganizationDel(Integer organizationDel) {
        this.organizationDel = organizationDel;
    }

    public String getSfjy() {
        return sfjy;
    }

    public void setSfjy(String sfjy) {
        this.sfjy = sfjy;
    }

    public String getYjnfy() {
        return yjnfy;
    }

    public void setYjnfy(String yjnfy) {
        this.yjnfy = yjnfy;
    }

    public String getYffy() {
        return yffy;
    }

    public void setYffy(String yffy) {
        this.yffy = yffy;
    }

    public int getCpcs() {
        return cpcs;
    }

    public void setCpcs(int cpcs) {
        this.cpcs = cpcs;
    }

    public String getOrganizationEjmm() {
        return organizationEjmm;
    }

    public void setOrganizationEjmm(String organizationEjmm) {
        this.organizationEjmm = organizationEjmm;
    }

    public Integer getOrganizationOrder() {
        return organizationOrder;
    }

    public void setOrganizationOrder(Integer organizationOrder) {
        this.organizationOrder = organizationOrder;
    }

    public String getOrganizationGzdh() {
        return organizationGzdh;
    }

    public void setOrganizationGzdh(String organizationGzdh) {
        this.organizationGzdh = organizationGzdh;
    }
}