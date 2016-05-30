package com.ttgis.education.entity;

import java.util.Date;

public class Reply
{
	private String replyId;

	private String studentId;

	private String bbsId;

	private String replyPid;

	private String replyPstudentid;

	private Date replyAt;

	private Long replyDel;

	private String replyContent;

	private String studentName;

	private String studentHead;

	private Long praise;

	private String studentPname;

	private Long praiseFlag;

	private String bbsTitle;

	private String friendlyAddtime;
	
	private int page;
	
   	private int size;

	public String getReplyId()
	{
		return replyId;
	}

	public void setReplyId(String replyId)
	{
		this.replyId = replyId;
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

	public String getReplyPid()
	{
		return replyPid;
	}

	public void setReplyPid(String replyPid)
	{
		this.replyPid = replyPid;
	}

	public String getReplyPstudentid()
	{
		return replyPstudentid;
	}

	public void setReplyPstudentid(String replyPstudentid)
	{
		this.replyPstudentid = replyPstudentid;
	}

	public Date getReplyAt()
	{
		return replyAt;
	}

	public void setReplyAt(Date replyAt)
	{
		this.replyAt = replyAt;
	}

	public Long getReplyDel()
	{
		return replyDel;
	}

	public void setReplyDel(Long replyDel)
	{
		this.replyDel = replyDel;
	}

	public String getReplyContent()
	{
		return replyContent;
	}

	public void setReplyContent(String replyContent)
	{
		this.replyContent = replyContent;
	}

	public String getStudentName()
	{
		return studentName;
	}

	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}

	public String getStudentHead()
	{
		return studentHead;
	}

	public void setStudentHead(String studentHead)
	{
		this.studentHead = studentHead;
	}

	public Long getPraise()
	{
		return praise;
	}

	public void setPraise(Long praise)
	{
		this.praise = praise;
	}

	public String getStudentPname()
	{
		return studentPname;
	}

	public void setStudentPname(String studentPname)
	{
		this.studentPname = studentPname;
	}

	public Long getPraiseFlag()
	{
		return praiseFlag;
	}

	public void setPraiseFlag(Long praiseFlag)
	{
		this.praiseFlag = praiseFlag;
	}

	public String getBbsTitle()
	{
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle)
	{
		this.bbsTitle = bbsTitle;
	}

	public String getFriendlyAddtime()
	{
		return friendlyAddtime;
	}

	public void setFriendlyAddtime(String friendlyAddtime)
	{
		this.friendlyAddtime = friendlyAddtime;
	}

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
}