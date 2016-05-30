package com.ttgis.education.entity;

import java.util.Date;

public class TestAndSubject
{
	private String testId;

	private String subjectId;

	private String studentId;

	private String testAnswer;

	private Double testScore;

	private Date testAt;

	private Integer testDel;

	private String subjectType;

	private String subjectTopic;

	private String subjectAnswer;

	private double subjectScore;
	
    private String courseId;
    
    private Integer testMark;
    
    private Subject subject;
    
    private String ruldId;
    
    private Ruld ruld;


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

	public Integer getTestMark() {
		return testMark;
	}

	public void setTestMark(Integer testMark) {
		this.testMark = testMark;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getTestId()
	{
		return testId;
	}

	public void setTestId(String testId)
	{
		this.testId = testId;
	}

	public String getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(String subjectId)
	{
		this.subjectId = subjectId;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getTestAnswer()
	{
		return testAnswer;
	}

	public void setTestAnswer(String testAnswer)
	{
		this.testAnswer = testAnswer;
	}

	public Double getTestScore()
	{
		return testScore;
	}

	public void setTestScore(Double testScore)
	{
		this.testScore = testScore;
	}

	public Date getTestAt()
	{
		return testAt;
	}

	public void setTestAt(Date testAt)
	{
		this.testAt = testAt;
	}

	public Integer getTestDel()
	{
		return testDel;
	}

	public void setTestDel(Integer testDel)
	{
		this.testDel = testDel;
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

	public String getSubjectAnswer()
	{
		return subjectAnswer;
	}

	public void setSubjectAnswer(String subjectAnswer)
	{
		this.subjectAnswer = subjectAnswer;
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