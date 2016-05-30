package com.ttgis.education.entity;

import java.util.Date;
import java.util.List;

public class Bbs
{
	private String bbsId;

	private String boardId;

	private String studentId;

	private String bbsTitle;

	private String bbsCover;

	private String bbsImgsrc;

	private Long bbsStatus;

	private String bbsSet;

	private Long bbsIstop;

	private Date bbsSettoptime;

	private Long bbsToporder;

	private Long bbsIsessence;

	private Date bbsSetessencetime;

	private Long bbsEssenceorder;

	private Long bbsIshot;

	private Date bbsSethottime;

	private Long bbsHotorder;

	private Long bbsCheckresult;

	private String bbsCheckuser;

	private String bbsCheckcause;

	private Date bbsChecktime;

	private Date bbsAt;

	private Long bbsDel;

	private String bbsContent;

	private Long bbsDelflag;

	private Long praise;

	private Long praiseFlag;

	private String friendlyAddtime;

	private Long replyCount;

	private Long viewCount;

	private String studentName;

	private String studentHead;

	private List<Reply> reply;
	
	private int page;
	
   	private int size;

	public String getBbsId()
	{
		return bbsId;
	}

	public void setBbsId(String bbsId)
	{
		this.bbsId = bbsId;
	}

	public String getBoardId()
	{
		return boardId;
	}

	public void setBoardId(String boardId)
	{
		this.boardId = boardId;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

	public String getBbsTitle()
	{
		return bbsTitle;
	}

	public void setBbsTitle(String bbsTitle)
	{
		this.bbsTitle = bbsTitle;
	}

	public String getBbsCover()
	{
		return bbsCover;
	}

	public void setBbsCover(String bbsCover)
	{
		this.bbsCover = bbsCover;
	}

	public String getBbsImgsrc()
	{
		return bbsImgsrc;
	}

	public void setBbsImgsrc(String bbsImgsrc)
	{
		this.bbsImgsrc = bbsImgsrc;
	}

	public Long getBbsStatus()
	{
		return bbsStatus;
	}

	public void setBbsStatus(Long bbsStatus)
	{
		this.bbsStatus = bbsStatus;
	}

	public String getBbsSet()
	{
		return bbsSet;
	}

	public void setBbsSet(String bbsSet)
	{
		this.bbsSet = bbsSet;
	}

	public Long getBbsIstop()
	{
		return bbsIstop;
	}

	public void setBbsIstop(Long bbsIstop)
	{
		this.bbsIstop = bbsIstop;
	}

	public Date getBbsSettoptime()
	{
		return bbsSettoptime;
	}

	public void setBbsSettoptime(Date bbsSettoptime)
	{
		this.bbsSettoptime = bbsSettoptime;
	}

	public Long getBbsToporder()
	{
		return bbsToporder;
	}

	public void setBbsToporder(Long bbsToporder)
	{
		this.bbsToporder = bbsToporder;
	}

	public Long getBbsIsessence()
	{
		return bbsIsessence;
	}

	public void setBbsIsessence(Long bbsIsessence)
	{
		this.bbsIsessence = bbsIsessence;
	}

	public Date getBbsSetessencetime()
	{
		return bbsSetessencetime;
	}

	public void setBbsSetessencetime(Date bbsSetessencetime)
	{
		this.bbsSetessencetime = bbsSetessencetime;
	}

	public Long getBbsEssenceorder()
	{
		return bbsEssenceorder;
	}

	public void setBbsEssenceorder(Long bbsEssenceorder)
	{
		this.bbsEssenceorder = bbsEssenceorder;
	}

	public Long getBbsIshot()
	{
		return bbsIshot;
	}

	public void setBbsIshot(Long bbsIshot)
	{
		this.bbsIshot = bbsIshot;
	}

	public Date getBbsSethottime()
	{
		return bbsSethottime;
	}

	public void setBbsSethottime(Date bbsSethottime)
	{
		this.bbsSethottime = bbsSethottime;
	}

	public Long getBbsHotorder()
	{
		return bbsHotorder;
	}

	public void setBbsHotorder(Long bbsHotorder)
	{
		this.bbsHotorder = bbsHotorder;
	}

	public Long getBbsCheckresult()
	{
		return bbsCheckresult;
	}

	public void setBbsCheckresult(Long bbsCheckresult)
	{
		this.bbsCheckresult = bbsCheckresult;
	}

	public String getBbsCheckuser()
	{
		return bbsCheckuser;
	}

	public void setBbsCheckuser(String bbsCheckuser)
	{
		this.bbsCheckuser = bbsCheckuser;
	}

	public String getBbsCheckcause()
	{
		return bbsCheckcause;
	}

	public void setBbsCheckcause(String bbsCheckcause)
	{
		this.bbsCheckcause = bbsCheckcause;
	}

	public Date getBbsChecktime()
	{
		return bbsChecktime;
	}

	public void setBbsChecktime(Date bbsChecktime)
	{
		this.bbsChecktime = bbsChecktime;
	}

	public Date getBbsAt()
	{
		return bbsAt;
	}

	public void setBbsAt(Date bbsAt)
	{
		this.bbsAt = bbsAt;
	}

	public Long getBbsDel()
	{
		return bbsDel;
	}

	public void setBbsDel(Long bbsDel)
	{
		this.bbsDel = bbsDel;
	}

	public String getBbsContent()
	{
		return bbsContent;
	}

	public void setBbsContent(String bbsContent)
	{
		this.bbsContent = bbsContent;
	}

	public Long getBbsDelflag()
	{
		return bbsDelflag;
	}

	public void setBbsDelflag(Long bbsDelflag)
	{
		this.bbsDelflag = bbsDelflag;
	}

	public Long getPraise()
	{
		return praise;
	}

	public void setPraise(Long praise)
	{
		this.praise = praise;
	}

	public Long getPraiseFlag()
	{
		return praiseFlag;
	}

	public void setPraiseFlag(Long praiseFlag)
	{
		this.praiseFlag = praiseFlag;
	}

	public String getFriendlyAddtime()
	{
		return friendlyAddtime;
	}

	public void setFriendlyAddtime(String friendlyAddtime)
	{
		this.friendlyAddtime = friendlyAddtime;
	}

	public Long getReplyCount()
	{
		return replyCount;
	}

	public void setReplyCount(Long replyCount)
	{
		this.replyCount = replyCount;
	}

	public Long getViewCount()
	{
		return viewCount;
	}

	public void setViewCount(Long viewCount)
	{
		this.viewCount = viewCount;
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

	public List<Reply> getReply()
	{
		return reply;
	}

	public void setReply(List<Reply> reply)
	{
		this.reply = reply;
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