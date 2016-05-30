package com.ttgis.education.entity;

import java.util.Date;

public class Homework {
    private String homeworkId;

    private String studentId;

    private String courseId;

    private Integer homeworkState;

    private String homeworkCatalog;

    private String homeworkAdmin;

    private Integer homeworkRank;

    private String homeworkPath;

    private Integer homeworkResult;

    private Date homeworkAt;

    private Integer homeworkDel;
    
    private String homeworkName;
    
    private String organizationId;//上级单位id
    private int page;
   	private int size;
   	private String studentName;
    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getHomeworkState() {
        return homeworkState;
    }

    public void setHomeworkState(Integer homeworkState) {
        this.homeworkState = homeworkState;
    }

    public String getHomeworkCatalog() {
        return homeworkCatalog;
    }

    public void setHomeworkCatalog(String homeworkCatalog) {
        this.homeworkCatalog = homeworkCatalog;
    }

    public String getHomeworkAdmin() {
        return homeworkAdmin;
    }

    public void setHomeworkAdmin(String homeworkAdmin) {
        this.homeworkAdmin = homeworkAdmin;
    }

    public Integer getHomeworkRank() {
        return homeworkRank;
    }

    public void setHomeworkRank(Integer homeworkRank) {
        this.homeworkRank = homeworkRank;
    }

    public String getHomeworkPath() {
        return homeworkPath;
    }

    public void setHomeworkPath(String homeworkPath) {
        this.homeworkPath = homeworkPath;
    }

    public Integer getHomeworkResult() {
        return homeworkResult;
    }

    public void setHomeworkResult(Integer homeworkResult) {
        this.homeworkResult = homeworkResult;
    }

    public Date getHomeworkAt() {
        return homeworkAt;
    }

    public void setHomeworkAt(Date homeworkAt) {
        this.homeworkAt = homeworkAt;
    }

    public Integer getHomeworkDel() {
        return homeworkDel;
    }

    public void setHomeworkDel(Integer homeworkDel) {
        this.homeworkDel = homeworkDel;
    }

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getHomeworkName() {
		return homeworkName;
	}

	public void setHomeworkName(String homeworkName) {
		this.homeworkName = homeworkName;
	}
}