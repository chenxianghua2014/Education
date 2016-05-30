package com.ttgis.education.entity;

import java.math.BigDecimal;

import com.ttgis.education.plugin.PageEntity;

public class QueryReply extends PageEntity
{
	private String type;

	private String order;

	private String keyword;

	private String bbsId;

	private String replyId;

	private String studentId;

	private String replyPstudentid;

	private BigDecimal replyDelflag;

	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getBbsId()
	{
		return bbsId;
	}

	public void setBbsId(String bbsId)
	{
		this.bbsId = bbsId;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getReplyPstudentid()
	{
		return replyPstudentid;
	}

	public void setReplyPstudentid(String replyPstudentid)
	{
		this.replyPstudentid = replyPstudentid;
	}

	public BigDecimal getReplyDelflag()
	{
		return replyDelflag;
	}

	public void setReplyDelflag(BigDecimal replyDelflag)
	{
		this.replyDelflag = replyDelflag;
	}

	public String getReplyId()
	{
		return replyId;
	}

	public void setReplyId(String replyId)
	{
		this.replyId = replyId;
	}

}