package com.ttgis.education.entity;

import java.util.Date;

public class Courseresource {
    private String courseresourceId;

    private String courseId;

    private String courseresourceFromname;

    private String courseresourceType;

    private String courseresourceName;

    private String courseresourceFiletype;

    private String courseresourceFileaddr;

    private Date courseresourceAt;

    private Integer courseresourceDel;
    
    private Integer courseresourceMark;
    
    private int page;
   	private int size;

    public Integer getCourseresourceMark() {
		return courseresourceMark;
	}

	public void setCourseresourceMark(Integer courseresourceMark) {
		this.courseresourceMark = courseresourceMark;
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

	public String getCourseresourceId() {
        return courseresourceId;
    }

    public void setCourseresourceId(String courseresourceId) {
        this.courseresourceId = courseresourceId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseresourceFromname() {
        return courseresourceFromname;
    }

    public void setCourseresourceFromname(String courseresourceFromname) {
        this.courseresourceFromname = courseresourceFromname;
    }

    public String getCourseresourceType() {
        return courseresourceType;
    }

    public void setCourseresourceType(String courseresourceType) {
        this.courseresourceType = courseresourceType;
    }

    public String getCourseresourceName() {
        return courseresourceName;
    }

    public void setCourseresourceName(String courseresourceName) {
        this.courseresourceName = courseresourceName;
    }

    public String getCourseresourceFiletype() {
        return courseresourceFiletype;
    }

    public void setCourseresourceFiletype(String courseresourceFiletype) {
        this.courseresourceFiletype = courseresourceFiletype;
    }

    public String getCourseresourceFileaddr() {
        return courseresourceFileaddr;
    }

    public void setCourseresourceFileaddr(String courseresourceFileaddr) {
        this.courseresourceFileaddr = courseresourceFileaddr;
    }

    public Date getCourseresourceAt() {
        return courseresourceAt;
    }

    public void setCourseresourceAt(Date courseresourceAt) {
        this.courseresourceAt = courseresourceAt;
    }

    public Integer getCourseresourceDel() {
        return courseresourceDel;
    }

    public void setCourseresourceDel(Integer courseresourceDel) {
        this.courseresourceDel = courseresourceDel;
    }
}