package com.ttgis.education.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Coursestudy {

	private String coursestudyId;//课程学习情况ID

    private String courseId; //课程ID

    private String studentId; //学员表ID

    private String coursestudyStartime; //课程学习情况�?��时间

    private String coursestudyEndtime;//课程学习情况结束时间

    private BigDecimal coursestudyAlltime;//课程学习情况总学�?

    private BigDecimal coursestudyNowtime;//课程学习情况现学�?

    private String coursestudyPlan;//课程学习情况学习进度

    private String coursestudyHomework;//课程学习情况作业

    private String coursestudyResult;// 课程学习情况成绩

    private String coursestudyNote;//课程学习情况笔记

    private Date coursestudyAt;//课程学习情况记录添加时间

    private Integer coursestudyDel;//课程学习情况删除标记	
    
    private String coursestudyType;//课程状�?�?代表推�?的课�?      2代表培训班的课程�?
    
    private String coursestudyTranclassid;
    private Course course;
    private int page;
   	private int size;
   	
   	private int sum;
   	
   	private String tranclassId;
   	//------------转存字段---------------
 	private String courseName;
 	private String tranclassName;
 	
 	
	private Integer cj;
 	private String organizationId;
 	private String studentCompanyid;// 学员所属单位编号（有用 组织架构id）
 	
 	
 	private int qb;
 	
   
   	
    public int getQb() {
		return qb;
	}

	public void setQb(int qb) {
		this.qb = qb;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Integer getCj() {
		return cj;
	}

	public void setCj(Integer cj) {
		this.cj = cj;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getStudentCompanyid() {
		return studentCompanyid;
	}

	public void setStudentCompanyid(String studentCompanyid) {
		this.studentCompanyid = studentCompanyid;
	}

	public String getCoursestudyTranclassid() {
		return coursestudyTranclassid;
	}

	public void setCoursestudyTranclassid(String coursestudyTranclassid) {
		this.coursestudyTranclassid = coursestudyTranclassid;
	}

	public String getTranclassName() {
		return tranclassName;
	}

	public void setTranclassName(String tranclassName) {
		this.tranclassName = tranclassName;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTranclassId() {
		return tranclassId;
	}

	public void setTranclassId(String tranclassId) {
		this.tranclassId = tranclassId;
	}

	public String getCoursestudyType() {
		return coursestudyType;
	}

	public void setCoursestudyType(String coursestudyType) {
		this.coursestudyType = coursestudyType;
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getCoursestudyId() {
        return coursestudyId;
    }

    public void setCoursestudyId(String coursestudyId) {
        this.coursestudyId = coursestudyId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCoursestudyStartime() {
        return coursestudyStartime;
    }

    public void setCoursestudyStartime(String coursestudyStartime) {
        this.coursestudyStartime = coursestudyStartime;
    }

    public String getCoursestudyEndtime() {
        return coursestudyEndtime;
    }

    public void setCoursestudyEndtime(String coursestudyEndtime) {
        this.coursestudyEndtime = coursestudyEndtime;
    }

    public BigDecimal getCoursestudyAlltime() {
        return coursestudyAlltime;
    }

    public void setCoursestudyAlltime(BigDecimal coursestudyAlltime) {
        this.coursestudyAlltime = coursestudyAlltime;
    }

    public BigDecimal getCoursestudyNowtime() {
        return coursestudyNowtime;
    }

    public void setCoursestudyNowtime(BigDecimal coursestudyNowtime) {
        this.coursestudyNowtime = coursestudyNowtime;
    }

    public String getCoursestudyPlan() {
        return coursestudyPlan;
    }

    public void setCoursestudyPlan(String coursestudyPlan) {
        this.coursestudyPlan = coursestudyPlan;
    }

    public String getCoursestudyHomework() {
        return coursestudyHomework;
    }

    public void setCoursestudyHomework(String coursestudyHomework) {
        this.coursestudyHomework = coursestudyHomework;
    }

    public String getCoursestudyResult() {
        return coursestudyResult;
    }

    public void setCoursestudyResult(String coursestudyResult) {
        this.coursestudyResult = coursestudyResult;
    }

    public String getCoursestudyNote() {
        return coursestudyNote;
    }

    public void setCoursestudyNote(String coursestudyNote) {
        this.coursestudyNote = coursestudyNote;
    }

    public Date getCoursestudyAt() {
        return coursestudyAt;
    }

    public void setCoursestudyAt(Date coursestudyAt) {
        this.coursestudyAt = coursestudyAt;
    }

    public Integer getCoursestudyDel() {
        return coursestudyDel;
    }

    public void setCoursestudyDel(Integer coursestudyDel) {
        this.coursestudyDel = coursestudyDel;
    }
}