package com.ttgis.education.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Usercourseinfo {
	  private String userId;//用户id

	    private String courseId;//课程id

	    private String scoId;//课件id

	    private String courseTime;//学习时长

	    private Integer scoscore;//成绩

	    private Date viewtime;//学习世间

	    private Integer isfinish;//是否完成

	    private String studytime;//学习时长

	    private Integer isdelete;//删除标记

	    private Date userAt;//添加时间
	    
	    private String sconote;//笔记

	    private String launch;//课件路径

	    private String scoload;

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getCourseId() {
			return courseId;
		}

		public void setCourseId(String courseId) {
			this.courseId = courseId;
		}

		public String getScoId() {
			return scoId;
		}

		public void setScoId(String scoId) {
			this.scoId = scoId;
		}

		public String getCourseTime() {
			return courseTime;
		}

		public void setCourseTime(String courseTime) {
			this.courseTime = courseTime;
		}

		public Integer getScoscore() {
			return scoscore;
		}

		public void setScoscore(Integer scoscore) {
			this.scoscore = scoscore;
		}

		public Date getViewtime() {
			return viewtime;
		}

		public void setViewtime(Date viewtime) {
			this.viewtime = viewtime;
		}

		public Integer getIsfinish() {
			return isfinish;
		}

		public void setIsfinish(Integer isfinish) {
			this.isfinish = isfinish;
		}

		public String getStudytime() {
			return studytime;
		}

		public void setStudytime(String studytime) {
			this.studytime = studytime;
		}

		public Integer getIsdelete() {
			return isdelete;
		}

		public void setIsdelete(Integer isdelete) {
			this.isdelete = isdelete;
		}

		public Date getUserAt() {
			return userAt;
		}

		public void setUserAt(Date userAt) {
			this.userAt = userAt;
		}

		public String getSconote() {
			return sconote;
		}

		public void setSconote(String sconote) {
			this.sconote = sconote;
		}

		public String getLaunch() {
			return launch;
		}

		public void setLaunch(String launch) {
			this.launch = launch;
		}

		public String getScoload() {
			return scoload;
		}

		public void setScoload(String scoload) {
			this.scoload = scoload;
		}
  

    
    //--------------------------------
//    private String organizationId;
//    private String tranclassId;
//    private int page;
//   	private int size;
//   	private int cj ; 
//	private int type ;
//	private String zs;
//	private String gs;
//	private String pjs;
	 //--------------------------------
	
	
	
   
}