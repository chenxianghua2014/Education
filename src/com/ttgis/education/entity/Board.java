package com.ttgis.education.entity;

public class Board
{
	private String boardId;

	private String boardName;

	private String boardTitle;

	private String boardMaster;

	private Long boardCheckresult;

	private String boardCheckuser;

	private String boardCheckcause;

	private String boardAt;

	private String boardDel;

	private Long bbsCount;
	
	private String boardCatalog;
	
	private String boardExamine;
	
	private int page;
	
   	private int size;
	
	public String getBoardId()
	{
		return boardId;
	}

	public void setBoardId(String boardId)
	{
		this.boardId = boardId;
	}

	public String getBoardName()
	{
		return boardName;
	}

	public void setBoardName(String boardName)
	{
		this.boardName = boardName;
	}

	public String getBoardTitle()
	{
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle)
	{
		this.boardTitle = boardTitle;
	}

	public String getBoardMaster()
	{
		return boardMaster;
	}

	public void setBoardMaster(String boardMaster)
	{
		this.boardMaster = boardMaster;
	}

	public Long getBoardCheckresult()
	{
		return boardCheckresult;
	}

	public void setBoardCheckresult(Long boardCheckresult)
	{
		this.boardCheckresult = boardCheckresult;
	}

	public String getBoardCheckuser()
	{
		return boardCheckuser;
	}

	public void setBoardCheckuser(String boardCheckuser)
	{
		this.boardCheckuser = boardCheckuser;
	}

	public String getBoardCheckcause()
	{
		return boardCheckcause;
	}

	public void setBoardCheckcause(String boardCheckcause)
	{
		this.boardCheckcause = boardCheckcause;
	}

	public String getBoardAt() {
		return boardAt;
	}

	public void setBoardAt(String boardAt) {
		this.boardAt = boardAt;
	}

	public Long getBbsCount()
	{
		return bbsCount;
	}

	public void setBbsCount(Long bbsCount)
	{
		this.bbsCount = bbsCount;
	}

	public String getBoardCatalog() {
		return boardCatalog;
	}

	public void setBoardCatalog(String boardCatalog) {
		this.boardCatalog = boardCatalog;
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

	public String getBoardExamine() {
		return boardExamine;
	}

	public void setBoardExamine(String boardExamine) {
		this.boardExamine = boardExamine;
	}

	public String getBoardDel() {
		return boardDel;
	}

	public void setBoardDel(String boardDel) {
		this.boardDel = boardDel;
	}

	
}