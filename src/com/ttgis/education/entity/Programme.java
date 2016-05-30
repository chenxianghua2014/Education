package com.ttgis.education.entity;

import java.util.Date;

public class Programme {
 

	private String programmeId;

	private String programmeName;

	private String programmeType;

	private String programmeCatalog;

	private String programmeProducer;

	private Integer programmeRank;

	private String programmeImage;

	private String programmeSort;

	private String programmeTime;

	private String programmeAt;

	private Integer programmeDel;

	private String organizationId;

	private String programmeContent;

	 private String programmeAtTemp;
	 
	 private Integer programmeTop;

	private String programmeToptime;
	
	private int page;
	private int size;
	
	private int cj ; 
	
	private int type;
	
	private Organization organization;
	
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCj() {
		return cj;
	}

	public void setCj(int cj) {
		this.cj = cj;
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

	public Integer getProgrammeTop() {
		return programmeTop;
	}

	public void setProgrammeTop(Integer programmeTop) {
		this.programmeTop = programmeTop;
	}

	public String getProgrammeToptime() {
		return programmeToptime;
	}

	public void setProgrammeToptime(String programmeToptime) {
		this.programmeToptime = programmeToptime;
	}

	public String getProgrammeTime() {
		return programmeTime;
	}

	public String getProgrammeAtTemp() {
		return programmeAtTemp;
	}

	public void setProgrammeAtTemp(String programmeAtTemp) {
		this.programmeAtTemp = programmeAtTemp;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public String getProgrammeType() {
		return programmeType;
	}

	public void setProgrammeType(String programmeType) {
		this.programmeType = programmeType;
	}

	public String getProgrammeCatalog() {
		return programmeCatalog;
	}

	public void setProgrammeCatalog(String programmeCatalog) {
		this.programmeCatalog = programmeCatalog;
	}

	public String getProgrammeProducer() {
		return programmeProducer;
	}

	public void setProgrammeProducer(String programmeProducer) {
		this.programmeProducer = programmeProducer;
	}

	public Integer getProgrammeRank() {
		return programmeRank;
	}

	public void setProgrammeRank(Integer programmeRank) {
		this.programmeRank = programmeRank;
	}

	public String getProgrammeImage() {
		return programmeImage;
	}

	public void setProgrammeImage(String programmeImage) {
		this.programmeImage = programmeImage;
	}

	public String getProgrammeSort() {
		return programmeSort;
	}

	public void setProgrammeSort(String programmeSort) {
		this.programmeSort = programmeSort;
	}

	public void setProgrammeTime(String programmeTime) {
		this.programmeTime = programmeTime;
	}


	public String getProgrammeAt() {
		return programmeAt;
	}

	public void setProgrammeAt(String programmeAt) {
		this.programmeAt = programmeAt;
	}

	public Integer getProgrammeDel() {
		return programmeDel;
	}

	public void setProgrammeDel(Integer programmeDel) {
		this.programmeDel = programmeDel;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getProgrammeContent() {
		return programmeContent;
	}

	public void setProgrammeContent(String programmeContent) {
		this.programmeContent = programmeContent;
	}

	
}