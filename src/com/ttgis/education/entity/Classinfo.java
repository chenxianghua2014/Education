package com.ttgis.education.entity;

import java.util.Date;

public class Classinfo {
    private String classinfoId;

	private String tranclassId;

	private String courseId;

	private String classinfoName;

	private Date classinfoAt;

	private Integer classinfoDel;

	private String classinfoStartime;

	private String classinfoEndtime;
	
	 private int page;
	   	private int size;
	   	private int cj ; 
		private int type ;
		
		private String classlb;//返回
		
		private String studentId;//判断是否重复
		private Tranclass tranclass;//培训班次
		private String tranclassName;//培训班
		
		

	public String getTranclassName() {
			return tranclassName;
		}

		public void setTranclassName(String tranclassName) {
			this.tranclassName = tranclassName;
		}

	public Tranclass getTranclass() {
			return tranclass;
		}

		public void setTranclass(Tranclass tranclass) {
			this.tranclass = tranclass;
		}

	public String getStudentId() {
			return studentId;
		}

		public void setStudentId(String studentId) {
			this.studentId = studentId;
		}

	public String getClasslb() {
			return classlb;
		}

		public void setClasslb(String classlb) {
			this.classlb = classlb;
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

		public int getCj() {
			return cj;
		}

		public void setCj(int cj) {
			this.cj = cj;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

	public String getClassinfoId() {
		return classinfoId;
	}

	public void setClassinfoId(String classinfoId) {
		this.classinfoId = classinfoId;
	}

	public String getTranclassId() {
		return tranclassId;
	}

	public void setTranclassId(String tranclassId) {
		this.tranclassId = tranclassId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getClassinfoName() {
		return classinfoName;
	}

	public void setClassinfoName(String classinfoName) {
		this.classinfoName = classinfoName;
	}

	public Date getClassinfoAt() {
		return classinfoAt;
	}

	public void setClassinfoAt(Date classinfoAt) {
		this.classinfoAt = classinfoAt;
	}

	public Integer getClassinfoDel() {
		return classinfoDel;
	}

	public void setClassinfoDel(Integer classinfoDel) {
		this.classinfoDel = classinfoDel;
	}

	public String getClassinfoStartime() {
		return classinfoStartime;
	}

	public void setClassinfoStartime(String classinfoStartime) {
		this.classinfoStartime = classinfoStartime;
	}

	public String getClassinfoEndtime() {
		return classinfoEndtime;
	}

	public void setClassinfoEndtime(String classinfoEndtime) {
		this.classinfoEndtime = classinfoEndtime;
	}

	
}