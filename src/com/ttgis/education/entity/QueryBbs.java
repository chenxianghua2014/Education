package com.ttgis.education.entity;

import com.ttgis.education.plugin.PageEntity;

public class QueryBbs extends PageEntity
{

	private String type;

	private String order;

	private String keyword;

	private String studentId;

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getOrder()
	{
		return order;
	}

	public void setOrder(String order)
	{
		this.order = order;
	}

	public String getKeyword()
	{
		return keyword;
	}

	public void setKeyword(String keyword)
	{
		this.keyword = keyword;
	}

}