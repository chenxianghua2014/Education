package com.ttgis.education.entity;


public class Notice {

	

	private String noticeId;

	private String noticeName;

	private String noticeType;

	private String noticeCatalog;

	private String noticeProducer;

	private Integer noticeRank;

	private String noticeImage;

	private String noticeSort;

	private String noticeTime;

	private String noticeAt;

	private Integer noticeDel;

	private String organizationId;

	private String noticeContent;
	
	private Integer noticeTop;

	private String noticeToptime;
	
	private int page;
	private int size;
	
	private int cj ; //层级
	
	private int type ; //登录权限类型
	
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

	public Integer getNoticeTop() {
		return noticeTop;
	}

	public void setNoticeTop(Integer noticeTop) {
		this.noticeTop = noticeTop;
	}

	public String getNoticeToptime() {
		return noticeToptime;
	}

	public void setNoticeToptime(String noticeToptime) {
		this.noticeToptime = noticeToptime;
	}

	public String getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(String noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}

	public String getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}

	public String getNoticeCatalog() {
		return noticeCatalog;
	}

	public void setNoticeCatalog(String noticeCatalog) {
		this.noticeCatalog = noticeCatalog;
	}

	public String getNoticeProducer() {
		return noticeProducer;
	}

	public void setNoticeProducer(String noticeProducer) {
		this.noticeProducer = noticeProducer;
	}

	public Integer getNoticeRank() {
		return noticeRank;
	}

	public void setNoticeRank(Integer noticeRank) {
		this.noticeRank = noticeRank;
	}

	public String getNoticeImage() {
		return noticeImage;
	}

	public void setNoticeImage(String noticeImage) {
		this.noticeImage = noticeImage;
	}

	public String getNoticeSort() {
		return noticeSort;
	}

	public void setNoticeSort(String noticeSort) {
		this.noticeSort = noticeSort;
	}



	public String getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(String noticeTime) {
		this.noticeTime = noticeTime;
	}

	public String getNoticeAt() {
		return noticeAt;
	}

	public void setNoticeAt(String noticeAt) {
		this.noticeAt = noticeAt;
	}

	public Integer getNoticeDel() {
		return noticeDel;
	}

	public void setNoticeDel(Integer noticeDel) {
		this.noticeDel = noticeDel;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	
}