package com.ttgis.education.entity;

import java.util.Date;
/**
 * 试卷表
 * <p>Title:Papers </p>
 *@author WYP
 *2016-1-20
 */
public class Papers {
    private String papersId; 

    private String ruldId;

    private String subjectId;

    private String courseId;

    private Integer papersThe;//所属卷组

    private Integer papersDel;

    private Date papersAt;
    
    private Subject subject;

    public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getPapersId() {
        return papersId;
    }

    public void setPapersId(String papersId) {
        this.papersId = papersId;
    }

    public String getRuldId() {
        return ruldId;
    }

    public void setRuldId(String ruldId) {
        this.ruldId = ruldId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getPapersThe() {
        return papersThe;
    }

    public void setPapersThe(Integer papersThe) {
        this.papersThe = papersThe;
    }

    public Integer getPapersDel() {
        return papersDel;
    }

    public void setPapersDel(Integer papersDel) {
        this.papersDel = papersDel;
    }

    public Date getPapersAt() {
        return papersAt;
    }

    public void setPapersAt(Date papersAt) {
        this.papersAt = papersAt;
    }
}