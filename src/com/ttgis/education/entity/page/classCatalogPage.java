package com.ttgis.education.entity.page;


public class classCatalogPage extends Page{
    
    private String pageclassresourceId;//培训班目录id

    private Integer courseDel;//
    
    private String courseType;//
    
    private Integer classresourceRanks;//管理级别

    
    

	public String getPageclassresourceId() {
		return pageclassresourceId;
	}

	public void setPageclassresourceId(String pageclassresourceId) {
		this.pageclassresourceId = pageclassresourceId;
	}

	public Integer getClassresourceRanks() {
		return classresourceRanks;
	}

	public void setClassresourceRanks(Integer classresourceRanks) {
		this.classresourceRanks = classresourceRanks;
	}

	public Integer getCourseDel() {
        return courseDel;
    }

    public void setCourseDel(Integer courseDel) {
        this.courseDel = courseDel;
    }



	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
    
}