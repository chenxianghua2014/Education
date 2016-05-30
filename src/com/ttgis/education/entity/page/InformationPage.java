package com.ttgis.education.entity.page;



public class InformationPage extends Page{
    
    private String pageNewsId;//新闻所在目录id
    
    private String newsCatalog;//新闻上级目录
    
    private Integer newsRank;//新闻管理级别
    
    private Integer newsRanks;//新闻管理级别
    
    private String zhuanNewsTime;//新闻发布时间(String)
    
    private String newsTime;//新闻发布时间

    private Integer courseDel;//
    
    private String courseType;//
    
    private String urlsy;//
    
    

   


	public String getUrlsy() {
		return urlsy;
	}

	public void setUrlsy(String urlsy) {
		this.urlsy = urlsy;
	}

	public Integer getNewsRanks() {
		return newsRanks;
	}

	public void setNewsRanks(Integer newsRanks) {
		this.newsRanks = newsRanks;
	}

	public String getZhuanNewsTime() {
		return zhuanNewsTime;
	}

	public void setZhuanNewsTime(String zhuanNewsTime) {
		this.zhuanNewsTime = zhuanNewsTime;
	}

	public String getNewsCatalog() {
		return newsCatalog;
	}

	public void setNewsCatalog(String newsCatalog) {
		this.newsCatalog = newsCatalog;
	}

	public Integer getNewsRank() {
		return newsRank;
	}

	public void setNewsRank(Integer newsRank) {
		this.newsRank = newsRank;
	}

	public String getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(String newsTime) {
		this.newsTime = newsTime;
	}

	public Integer getCourseDel() {
        return courseDel;
    }

    public void setCourseDel(Integer courseDel) {
        this.courseDel = courseDel;
    }

	

	public String getPageNewsId() {
		return pageNewsId;
	}

	public void setPageNewsId(String pageNewsId) {
		this.pageNewsId = pageNewsId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
    
}