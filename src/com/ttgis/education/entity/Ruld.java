package com.ttgis.education.entity;

import java.util.Date;

import com.ttgis.education.entity.page.Page;

public class Ruld 
{
    private String ruldId;//试卷规则

    private String courseId;//课程ID

    private String ruldName;//试卷名称

    private Integer ruldTime;//考试时间

    private String ruldYxtime;//有效日期

    private Integer ruldBits;//考试次数

    private Integer ruldAnswers;//是否可以查看答案

    private Double ruldTotalscore;//总分数

    private String ruldName13;//单选3选1题型名称

    private Double ruldScore13;//单选3选1试题规则分数

    private Integer ruldQuiznum13;//单选3选1  题数

    private String ruldName14;//单选4选1

    private Double ruldScore14;

    private Integer ruldQuiznum14;

    private String ruldName1M;//多选6

    private Double ruldScore1M;

    private Integer ruldQuiznum1M;
    
    private String ruldName15M;//多选5

    private Double ruldScore15M;

    private Integer ruldQuiznum15M;

    private String ruldName2;//判断

    private Double ruldScore2;

    private Integer ruldQuiznum2;

    private String ruldName3;//填空

    private Double ruldScore3;

    private Integer ruldQuiznum3;

    private String ruldName4;//主观题

    private Double ruldScore4;

    private Integer ruldQuiznum4;

    private Integer ruldDel;//删除标记

    private Date ruldAt;//添加时间

    private String ruldNote;//说明

    public String getRuldName15M() {
		return ruldName15M;
	}

	public void setRuldName15M(String ruldName15M) {
		this.ruldName15M = ruldName15M;
	}

	public Double getRuldScore15M() {
		return ruldScore15M;
	}

	public void setRuldScore15M(Double ruldScore15M) {
		this.ruldScore15M = ruldScore15M;
	}

	public Integer getRuldQuiznum15M() {
		return ruldQuiznum15M;
	}

	public void setRuldQuiznum15M(Integer ruldQuiznum15M) {
		this.ruldQuiznum15M = ruldQuiznum15M;
	}

	public String getRuldId() {
        return ruldId;
    }

    public void setRuldId(String ruldId) {
        this.ruldId = ruldId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getRuldName() {
        return ruldName;
    }

    public void setRuldName(String ruldName) {
        this.ruldName = ruldName;
    }

    public Integer getRuldTime() {
        return ruldTime;
    }

    public void setRuldTime(Integer ruldTime) {
        this.ruldTime = ruldTime;
    }

    public String getRuldYxtime() {
        return ruldYxtime;
    }

    public void setRuldYxtime(String ruldYxtime) {
        this.ruldYxtime = ruldYxtime;
    }

    public Integer getRuldBits() {
        return ruldBits;
    }

    public void setRuldBits(Integer ruldBits) {
        this.ruldBits = ruldBits;
    }

    public Integer getRuldAnswers() {
        return ruldAnswers;
    }

    public void setRuldAnswers(Integer ruldAnswers) {
        this.ruldAnswers = ruldAnswers;
    }

  

    public Double getRuldTotalscore() {
		return ruldTotalscore;
	}

	public void setRuldTotalscore(Double ruldTotalscore) {
		this.ruldTotalscore = ruldTotalscore;
	}

	public String getRuldName13() {
        return ruldName13;
    }

    public void setRuldName13(String ruldName13) {
        this.ruldName13 = ruldName13;
    }

    public Double getRuldScore13() {
        return ruldScore13;
    }

    public void setRuldScore13(Double ruldScore13) {
        this.ruldScore13 = ruldScore13;
    }

    public Integer getRuldQuiznum13() {
        return ruldQuiznum13;
    }

    public void setRuldQuiznum13(Integer ruldQuiznum13) {
        this.ruldQuiznum13 = ruldQuiznum13;
    }

    public String getRuldName14() {
        return ruldName14;
    }

    public void setRuldName14(String ruldName14) {
        this.ruldName14 = ruldName14;
    }

    public Double getRuldScore14() {
        return ruldScore14;
    }

    public void setRuldScore14(Double ruldScore14) {
        this.ruldScore14 = ruldScore14;
    }

    public Integer getRuldQuiznum14() {
        return ruldQuiznum14;
    }

    public void setRuldQuiznum14(Integer ruldQuiznum14) {
        this.ruldQuiznum14 = ruldQuiznum14;
    }

    public String getRuldName1M() {
        return ruldName1M;
    }

    public void setRuldName1M(String ruldName1M) {
        this.ruldName1M = ruldName1M;
    }

    public Double getRuldScore1M() {
        return ruldScore1M;
    }

    public void setRuldScore1M(Double ruldScore1M) {
        this.ruldScore1M = ruldScore1M;
    }

    public Integer getRuldQuiznum1M() {
        return ruldQuiznum1M;
    }

    public void setRuldQuiznum1M(Integer ruldQuiznum1M) {
        this.ruldQuiznum1M = ruldQuiznum1M;
    }

    public String getRuldName2() {
        return ruldName2;
    }

    public void setRuldName2(String ruldName2) {
        this.ruldName2 = ruldName2;
    }

    public Double getRuldScore2() {
        return ruldScore2;
    }

    public void setRuldScore2(Double ruldScore2) {
        this.ruldScore2 = ruldScore2;
    }

    public Integer getRuldQuiznum2() {
        return ruldQuiznum2;
    }

    public void setRuldQuiznum2(Integer ruldQuiznum2) {
        this.ruldQuiznum2 = ruldQuiznum2;
    }

    public String getRuldName3() {
        return ruldName3;
    }

    public void setRuldName3(String ruldName3) {
        this.ruldName3 = ruldName3;
    }

    public Double getRuldScore3() {
        return ruldScore3;
    }

    public void setRuldScore3(Double ruldScore3) {
        this.ruldScore3 = ruldScore3;
    }

    public Integer getRuldQuiznum3() {
        return ruldQuiznum3;
    }

    public void setRuldQuiznum3(Integer ruldQuiznum3) {
        this.ruldQuiznum3 = ruldQuiznum3;
    }

    public String getRuldName4() {
        return ruldName4;
    }

    public void setRuldName4(String ruldName4) {
        this.ruldName4 = ruldName4;
    }

    public Double getRuldScore4() {
        return ruldScore4;
    }

    public void setRuldScore4(Double ruldScore4) {
        this.ruldScore4 = ruldScore4;
    }

    public Integer getRuldQuiznum4() {
        return ruldQuiznum4;
    }

    public void setRuldQuiznum4(Integer ruldQuiznum4) {
        this.ruldQuiznum4 = ruldQuiznum4;
    }

    public Integer getRuldDel() {
        return ruldDel;
    }

    public void setRuldDel(Integer ruldDel) {
        this.ruldDel = ruldDel;
    }

    public Date getRuldAt() {
        return ruldAt;
    }

    public void setRuldAt(Date ruldAt) {
        this.ruldAt = ruldAt;
    }

    public String getRuldNote() {
        return ruldNote;
    }

    public void setRuldNote(String ruldNote) {
        this.ruldNote = ruldNote;
    }
}