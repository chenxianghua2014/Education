package com.ttgis.education.entity;

import java.util.Date;

public class Statistical
{
	private String statisticalId;

	private String studentId;

	private String bbsId;

	private String replyId;

	private Long statisticalType;

	private Date statisticalAt;

	private Long statisticalDel;

	public String getStatisticalId()
	{
		return statisticalId;
	}

	public void setStatisticalId(String statisticalId)
	{
		this.statisticalId = statisticalId;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getBbsId()
	{
		return bbsId;
	}

	public void setBbsId(String bbsId)
	{
		this.bbsId = bbsId;
	}

	public String getReplyId()
	{
		return replyId;
	}

	public void setReplyId(String replyId)
	{
		this.replyId = replyId;
	}

	public Long getStatisticalType()
	{
		return statisticalType;
	}

	public void setStatisticalType(Long statisticalType)
	{
		this.statisticalType = statisticalType;
	}

	public Date getStatisticalAt()
	{
		return statisticalAt;
	}

	public void setStatisticalAt(Date statisticalAt)
	{
		this.statisticalAt = statisticalAt;
	}

	public Long getStatisticalDel()
	{
		return statisticalDel;
	}

	public void setStatisticalDel(Long statisticalDel)
	{
		this.statisticalDel = statisticalDel;
	}
}