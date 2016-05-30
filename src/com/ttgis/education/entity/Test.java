package com.ttgis.education.entity;

import java.util.Date;

public class Test {

	

	private String testId;
	
	private String subjectId;//试题资源ID

    private String ruldId;//试题规则ID

    private String studentId;//学员ID

    private String testAnswer;//答案

    private Double testScore;//-1手工批卷 0 错 1 对

    private Date testAt;//添加时间

    private Integer testDel;//删除标记
    
    private String courseId;//课程ID

	private Integer testMark;//
	
	private Subject subject;
	
	private Ruld ruld;
	
	 private int page;
	 private int size;


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

	public Ruld getRuld() {
		return ruld;
	}

	public void setRuld(Ruld ruld) {
		this.ruld = ruld;
	}

	public String getRuldId() {
		return ruldId;
	}

	public void setRuldId(String ruldId) {
		this.ruldId = ruldId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public Integer getTestMark() {
		return testMark;
	}

	public void setTestMark(Integer testMark) {
		this.testMark = testMark;
	}

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTestAnswer() {
        return testAnswer;
    }

    public void setTestAnswer(String testAnswer) {
        this.testAnswer = testAnswer;
    }

    public Double getTestScore() {
        return testScore;
    }

    public void setTestScore(Double testScore) {
        this.testScore = testScore;
    }

    public Date getTestAt() {
        return testAt;
    }

    public void setTestAt(Date testAt) {
        this.testAt = testAt;
    }

    public Integer getTestDel() {
        return testDel;
    }

    public void setTestDel(Integer testDel) {
        this.testDel = testDel;
    }
}