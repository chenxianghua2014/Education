package com.ttgis.education.entity;

import java.util.Date;
/**
 * 培训班
 * <p>Title:Tranclass </p>
 *@author WYP
 *2015-8-13
 */
public class Tranclass {
    private String tranclassId;//培训班编号

	private String classresourceId;//培训班目录ID

	private String tranclassCategroy;//培训班类别

	private String tranclassName;//培训班名称

	private Integer tranclassNumber;//培训班人数

	private String tranclassTeacher;//培训班班主任

	private String tranclassCatalog;//培训班所属目录

	private String tranclassRank;//培训班管理级别

	private String tranclassInclude;//培训班包含课程

	private String tranclassDetails;//培训班培训班详情

	private String tranclassFile;//培训班详情文件路径

	private String tranclassStudent;//培训班学员信息

	private String tranclassFace;//培训班面授信息

	private String tranclassCondition;//培训班培训学习情况
	
	private String tranclassType;//培训班状态	
	
	private String tranclassSetuptime;//培训班开设时间

	private String tranclassEndtime;//培训班培训结束时间

	private String tranclassApplytime;//培训班报名开始时间

	private String tranclassApplyendtime;//培训班报名截止时间	

	private Date tranclassAt;//培训班记录添加时间

	private Integer tranclassDel;//培训班删除标记
	//-----------------------------
    private String tranclassAtTemp;
    
    private int page;
   	private int size;
   	private int cj ; 
	private int typese ; 
	private String accountids ; 
	private String classlb ;
	private String classresourceUpname;
	
	
	public String getClassresourceUpname() {
		return classresourceUpname;
	}

	public void setClassresourceUpname(String classresourceUpname) {
		this.classresourceUpname = classresourceUpname;
	}

	public String getClasslb() {
		return classlb;
	}

	public void setClasslb(String classlb) {
		this.classlb = classlb;
	}

	private int qtId ; 
	private String organizationId;
   	
   	

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public int getQtId() {
		return qtId;
	}

	public void setQtId(int qtId) {
		this.qtId = qtId;
	}

	public int getTypese() {
		return typese;
	}

	public void setTypese(int typese) {
		this.typese = typese;
	}

	public String getAccountids() {
		return accountids;
	}

	public void setAccountids(String accountids) {
		this.accountids = accountids;
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

	public String getTranclassAtTemp() {
		return tranclassAtTemp;
	}

	public void setTranclassAtTemp(String tranclassAtTemp) {
		this.tranclassAtTemp = tranclassAtTemp;
	}

	public String getTranclassId() {
		return tranclassId;
	}

	public void setTranclassId(String tranclassId) {
		this.tranclassId = tranclassId;
	}

	public String getClassresourceId() {
		return classresourceId;
	}

	public void setClassresourceId(String classresourceId) {
		this.classresourceId = classresourceId;
	}

	public String getTranclassCategroy() {
		return tranclassCategroy;
	}

	public void setTranclassCategroy(String tranclassCategroy) {
		this.tranclassCategroy = tranclassCategroy;
	}

	public String getTranclassName() {
		return tranclassName;
	}

	public void setTranclassName(String tranclassName) {
		this.tranclassName = tranclassName;
	}

	public Integer getTranclassNumber() {
		return tranclassNumber;
	}

	public void setTranclassNumber(Integer tranclassNumber) {
		this.tranclassNumber = tranclassNumber;
	}

	public String getTranclassTeacher() {
		return tranclassTeacher;
	}

	public void setTranclassTeacher(String tranclassTeacher) {
		this.tranclassTeacher = tranclassTeacher;
	}

	public String getTranclassCatalog() {
		return tranclassCatalog;
	}

	public void setTranclassCatalog(String tranclassCatalog) {
		this.tranclassCatalog = tranclassCatalog;
	}

	public String getTranclassRank() {
		return tranclassRank;
	}

	public void setTranclassRank(String tranclassRank) {
		this.tranclassRank = tranclassRank;
	}

	public String getTranclassInclude() {
		return tranclassInclude;
	}

	public void setTranclassInclude(String tranclassInclude) {
		this.tranclassInclude = tranclassInclude;
	}

	public String getTranclassDetails() {
		return tranclassDetails;
	}

	public void setTranclassDetails(String tranclassDetails) {
		this.tranclassDetails = tranclassDetails;
	}

	public String getTranclassFile() {
		return tranclassFile;
	}

	public void setTranclassFile(String tranclassFile) {
		this.tranclassFile = tranclassFile;
	}

	public String getTranclassStudent() {
		return tranclassStudent;
	}

	public void setTranclassStudent(String tranclassStudent) {
		this.tranclassStudent = tranclassStudent;
	}

	public String getTranclassFace() {
		return tranclassFace;
	}

	public void setTranclassFace(String tranclassFace) {
		this.tranclassFace = tranclassFace;
	}

	public String getTranclassCondition() {
		return tranclassCondition;
	}

	public void setTranclassCondition(String tranclassCondition) {
		this.tranclassCondition = tranclassCondition;
	}

	public Date getTranclassAt() {
		return tranclassAt;
	}

	public void setTranclassAt(Date tranclassAt) {
		this.tranclassAt = tranclassAt;
	}

	public Integer getTranclassDel() {
		return tranclassDel;
	}

	public void setTranclassDel(Integer tranclassDel) {
		this.tranclassDel = tranclassDel;
	}

	public String getTranclassType() {
		return tranclassType;
	}

	public void setTranclassType(String tranclassType) {
		this.tranclassType = tranclassType;
	}

	public String getTranclassSetuptime() {
		return tranclassSetuptime;
	}

	public void setTranclassSetuptime(String tranclassSetuptime) {
		this.tranclassSetuptime = tranclassSetuptime;
	}

	public String getTranclassEndtime() {
		return tranclassEndtime;
	}

	public void setTranclassEndtime(String tranclassEndtime) {
		this.tranclassEndtime = tranclassEndtime;
	}

	public String getTranclassApplytime() {
		return tranclassApplytime;
	}

	public void setTranclassApplytime(String tranclassApplytime) {
		this.tranclassApplytime = tranclassApplytime;
	}

	public String getTranclassApplyendtime() {
		return tranclassApplyendtime;
	}

	public void setTranclassApplyendtime(String tranclassApplyendtime) {
		this.tranclassApplyendtime = tranclassApplyendtime;
	}

	
    


    
   
}