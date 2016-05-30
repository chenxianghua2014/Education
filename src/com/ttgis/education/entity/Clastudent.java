package com.ttgis.education.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Clastudent {
    private String clastudentId;//培训班学员信息ID

    private String studentId;//学员表ID

    private String tranclassId;  // 培训班编号

    private String clastudentName;//培训班学员信息培训班名称

    private Integer clastudentType;//培训班学员状态

    private String clastudentPassword;//培训班学员信息临时报名密码	

    private Date clastudentAt;//培训班学员信息记录添加时间
   
    private Integer clastudentDel; //培训班学员信息删除标记

    private String clastudentStartime;//开始时间

    private String clastudentEndtime;// 结束时间

    private BigDecimal clastudentAlltime;  //总学时

    private BigDecimal clastudentNowtime;   // 现学时

    private String clastudentPlan;  //学习进度

    private String clastudentCatalog;// 所属目录	

    private String clastudentResuly;//考核结果
    
    
    private int page;
  	private int size;
  	
  	private int cj ; //层级
	private int type ; //状态 0 未通过              1 通过
  	
	  private Tranclass tranclass; //培训班
	  
	  private String classlb;//类别    返回用
	  
	  private String classresourceUpname;//培训班   
	  
	  
    

    public String getClassresourceUpname() {
		return classresourceUpname;
	}

	public void setClassresourceUpname(String classresourceUpname) {
		this.classresourceUpname = classresourceUpname;
	}

	public String getClasslb() {
		return classlb;
	}

	public void setClasslb(String classlb) {
		this.classlb = classlb;
	}

	public Tranclass getTranclass() {
		return tranclass;
	}

	public void setTranclass(Tranclass tranclass) {
		this.tranclass = tranclass;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	public String getClastudentId() {
        return clastudentId;
    }

    public void setClastudentId(String clastudentId) {
        this.clastudentId = clastudentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getTranclassId() {
        return tranclassId;
    }

    public void setTranclassId(String tranclassId) {
        this.tranclassId = tranclassId;
    }

    public String getClastudentName() {
        return clastudentName;
    }

    public void setClastudentName(String clastudentName) {
        this.clastudentName = clastudentName;
    }

    public Integer getClastudentType() {
        return clastudentType;
    }

    public void setClastudentType(Integer clastudentType) {
        this.clastudentType = clastudentType;
    }

    public String getClastudentPassword() {
        return clastudentPassword;
    }

    public void setClastudentPassword(String clastudentPassword) {
        this.clastudentPassword = clastudentPassword;
    }

    public Date getClastudentAt() {
        return clastudentAt;
    }

    public void setClastudentAt(Date clastudentAt) {
        this.clastudentAt = clastudentAt;
    }

    public Integer getClastudentDel() {
        return clastudentDel;
    }

    public void setClastudentDel(Integer clastudentDel) {
        this.clastudentDel = clastudentDel;
    }

    public String getClastudentStartime() {
        return clastudentStartime;
    }

    public void setClastudentStartime(String clastudentStartime) {
        this.clastudentStartime = clastudentStartime;
    }

    public String getClastudentEndtime() {
        return clastudentEndtime;
    }

    public void setClastudentEndtime(String clastudentEndtime) {
        this.clastudentEndtime = clastudentEndtime;
    }

    public BigDecimal getClastudentAlltime() {
        return clastudentAlltime;
    }

    public void setClastudentAlltime(BigDecimal clastudentAlltime) {
        this.clastudentAlltime = clastudentAlltime;
    }

    public BigDecimal getClastudentNowtime() {
        return clastudentNowtime;
    }

    public void setClastudentNowtime(BigDecimal clastudentNowtime) {
        this.clastudentNowtime = clastudentNowtime;
    }

    public String getClastudentPlan() {
        return clastudentPlan;
    }

    public void setClastudentPlan(String clastudentPlan) {
        this.clastudentPlan = clastudentPlan;
    }

    public String getClastudentCatalog() {
        return clastudentCatalog;
    }

    public void setClastudentCatalog(String clastudentCatalog) {
        this.clastudentCatalog = clastudentCatalog;
    }

    public String getClastudentResuly() {
        return clastudentResuly;
    }

    public void setClastudentResuly(String clastudentResuly) {
        this.clastudentResuly = clastudentResuly;
    }
}