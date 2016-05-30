package com.ttgis.education.entity.page;

public class CoursePage extends Page
{

	private String syllabusId;

	private Integer courseDel;

	private String courseType;

	public Integer getCourseDel()
	{
		return courseDel;
	}

	public void setCourseDel(Integer courseDel)
	{
		this.courseDel = courseDel;
	}

	public String getSyllabusId()
	{
		return syllabusId;
	}

	public void setSyllabusId(String syllabusId)
	{
		this.syllabusId = syllabusId;
	}

	public String getCourseType()
	{
		return courseType;
	}

	public void setCourseType(String courseType)
	{
		this.courseType = courseType;
	}

}