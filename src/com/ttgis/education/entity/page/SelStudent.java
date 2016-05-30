package com.ttgis.education.entity.page;

public class SelStudent extends Page
{
	private String studentId;

	private String studentName;
	
	private String courseId;
	
	private int begin;
	private int end;

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

}