package com.ttgis.education.entity.page;


public class trainCroursePage extends Page{
    
    private String pageTranclassId;//培训班id

    private Integer courseDel;//
    
    private String courseType;//
    
    private Integer tranclassRanks;//管理级别

    
    
    public Integer getTranclassRanks() {
		return tranclassRanks;
	}

	public void setTranclassRanks(Integer tranclassRanks) {
		this.tranclassRanks = tranclassRanks;
	}

	public Integer getCourseDel() {
        return courseDel;
    }

    public void setCourseDel(Integer courseDel) {
        this.courseDel = courseDel;
    }

	public String getPageTranclassId() {
		return pageTranclassId;
	}

	public void setPageTranclassId(String pageTranclassId) {
		this.pageTranclassId = pageTranclassId;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
    
}