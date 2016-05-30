package com.ttgis.education.entity;

import java.util.Date;

public class Subject
{
	private String subjectId;

	private String courseId;//课程ID

	private String subjectName;//试题资源名称

	private String subjectState;//试题资源状态

	private String subjectCatalog;//试题资源所属目录

	private String subjectProducer;//试题资源制作人	
	
	private Integer subjectRank;//试题资源管理级别

	private String subjectType;//试题资源题目类型

	private String subjectTopic;//试题资源题干信息

	private String subjectA;//试题资源A

	private String subjectB;//试题资源B

	private String subjectC;//试题资源C

	private String subjectD;//试题资源D
	
	private String subjectE;//试题资源E

	private String subjectF;//试题资源F


	private String subjectAnswer;//试题资源答案

	private String subjectExplain;//实体资源答案详解

	private Date subjectAt;//试题资源记录添加时间

	private Integer subjectDel;//试题资源删除标记

	private double subjectScore;//分数

	public String getSubjectE() {
		return subjectE;
	}

	public void setSubjectE(String subjectE) {
		this.subjectE = subjectE;
	}

	public String getSubjectF() {
		return subjectF;
	}

	public void setSubjectF(String subjectF) {
		this.subjectF = subjectF;
	}

	public String getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(String subjectId)
	{
		this.subjectId = subjectId;
	}

	public String getCourseId()
	{
		return courseId;
	}

	public void setCourseId(String courseId)
	{
		this.courseId = courseId;
	}

	public String getSubjectName()
	{
		return subjectName;
	}

	public void setSubjectName(String subjectName)
	{
		this.subjectName = subjectName;
	}

	public String getSubjectState()
	{
		return subjectState;
	}

	public void setSubjectState(String subjectState)
	{
		this.subjectState = subjectState;
	}

	public String getSubjectCatalog()
	{
		return subjectCatalog;
	}

	public void setSubjectCatalog(String subjectCatalog)
	{
		this.subjectCatalog = subjectCatalog;
	}

	public String getSubjectProducer()
	{
		return subjectProducer;
	}

	public void setSubjectProducer(String subjectProducer)
	{
		this.subjectProducer = subjectProducer;
	}

	public Integer getSubjectRank()
	{
		return subjectRank;
	}

	public void setSubjectRank(Integer subjectRank)
	{
		this.subjectRank = subjectRank;
	}

	public String getSubjectType()
	{
		return subjectType;
	}

	public void setSubjectType(String subjectType)
	{
		this.subjectType = subjectType;
	}

	public String getSubjectTopic()
	{
		return subjectTopic;
	}

	public void setSubjectTopic(String subjectTopic)
	{
		this.subjectTopic = subjectTopic;
	}

	public String getSubjectA()
	{
		return subjectA;
	}

	public void setSubjectA(String subjectA)
	{
		this.subjectA = subjectA;
	}

	public String getSubjectB()
	{
		return subjectB;
	}

	public void setSubjectB(String subjectB)
	{
		this.subjectB = subjectB;
	}

	public String getSubjectC()
	{
		return subjectC;
	}

	public void setSubjectC(String subjectC)
	{
		this.subjectC = subjectC;
	}

	public String getSubjectD()
	{
		return subjectD;
	}

	public void setSubjectD(String subjectD)
	{
		this.subjectD = subjectD;
	}

	public String getSubjectAnswer()
	{
		return subjectAnswer;
	}

	public void setSubjectAnswer(String subjectAnswer)
	{
		this.subjectAnswer = subjectAnswer;
	}

	public String getSubjectExplain()
	{
		return subjectExplain;
	}

	public void setSubjectExplain(String subjectExplain)
	{
		this.subjectExplain = subjectExplain;
	}

	public Date getSubjectAt()
	{
		return subjectAt;
	}

	public void setSubjectAt(Date subjectAt)
	{
		this.subjectAt = subjectAt;
	}

	public Integer getSubjectDel()
	{
		return subjectDel;
	}

	public void setSubjectDel(Integer subjectDel)
	{
		this.subjectDel = subjectDel;
	}

	public double getSubjectScore()
	{
		return subjectScore;
	}

	public void setSubjectScore(double subjectScore)
	{
		this.subjectScore = subjectScore;
	}
}