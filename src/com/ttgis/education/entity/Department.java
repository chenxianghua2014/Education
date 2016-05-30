package com.ttgis.education.entity;

import java.util.Date;

public class Department {
    private String departmentId;//部门ID

	private String organizationId;//学员表ID	

	private String epartmentName;//部门名称

	private String epartmentRank;//层级	
	
	private Integer epartmentUpname;//上级部门名称

	private String epartmentUpid;//上级部门编号

	private Date epartmentAt;//部门添加时间

	private Integer epartmentDel;//部门删除标记
	
	private int page;
	
	private int size;
	
	private int cj ; //层级
	
	private int type ; //登录权限类型
	
	private Organization organization;
	
	private String oidall[];//排序组织ID数组
	
	private int oidsort[];//排序数组
	
	

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

	public int getCj() {
		return cj;
	}

	public void setCj(int cj) {
		this.cj = cj;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getEpartmentName() {
		return epartmentName;
	}

	public void setEpartmentName(String epartmentName) {
		this.epartmentName = epartmentName;
	}

	public String getEpartmentRank() {
		return epartmentRank;
	}

	public void setEpartmentRank(String epartmentRank) {
		this.epartmentRank = epartmentRank;
	}

	public Integer getEpartmentUpname() {
		return epartmentUpname;
	}

	public void setEpartmentUpname(Integer epartmentUpname) {
		this.epartmentUpname = epartmentUpname;
	}

	public String getEpartmentUpid() {
		return epartmentUpid;
	}

	public void setEpartmentUpid(String epartmentUpid) {
		this.epartmentUpid = epartmentUpid;
	}

	public Date getEpartmentAt() {
		return epartmentAt;
	}

	public void setEpartmentAt(Date epartmentAt) {
		this.epartmentAt = epartmentAt;
	}

	public Integer getEpartmentDel() {
		return epartmentDel;
	}

	public void setEpartmentDel(Integer epartmentDel) {
		this.epartmentDel = epartmentDel;
	}

	
}