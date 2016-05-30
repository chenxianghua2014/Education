package com.ttgis.education.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Course {
    private String courseId;//课程ID

    private String syllabusId;//课程目录ID

    private String teacherId;//教师ID

    private String courseName;//课程名称

    private String courseType;//课程类型

    private Integer courseStatus;//课程状态

    private String courseCatalog;//课程所属目录    组织架构id

    private String coursePubman;//课程发布人

    private String courseTeacher;//课程授课教师

    private String courseCompany;//课程发布单位
    
    private BigDecimal courseTimes;//课程课时

    private String courseImage;//课程图片

    private String courseDesc; //课程详情

    private Integer coursePv; //课程浏览量

    private Integer courseCollectnum;//课程收藏次数

    private String courseRes;//课程资源

    private Integer courseCoin;//课程学习币

    private String courseQuestuion;//课程试题资源

    private String courseJobid;//课程作业编号

    private String courseJobdesc;//课程作业详情

    private Date courseAt;//课程记录添加时间

    private Integer courseDel;//课程删除标记
    
    
    //--------------------------------
    private String organizationId;
    private String tranclassId;
    private int page;
   	private int size;
   	private int cj ; 
	private int type ;
	private Organization organization;
	private Teacher teacher;
	private Coursestudy coursestudy;
	private String classlb;//返回的类别
	private String studentId;//学员ID收藏
	 //--------------------------------
	private String zs;
	private String gs;
	private String pjs;
	private String ml;
	
	
    public String getMl() {
		return ml;
	}

	public void setMl(String ml) {
		this.ml = ml;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getZs() {
		return zs;
	}

	public void setZs(String zs) {
		this.zs = zs;
	}

	public String getGs() {
		return gs;
	}

	public void setGs(String gs) {
		this.gs = gs;
	}

	public String getPjs() {
		return pjs;
	}

	public void setPjs(String pjs) {
		this.pjs = pjs;
	}

	public String getClasslb() {
		return classlb;
	}

	public void setClasslb(String classlb) {
		this.classlb = classlb;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getTranclassId() {
		return tranclassId;
	}

    public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	public void setTranclassId(String tranclassId) {
		this.tranclassId = tranclassId;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
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

	public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(String syllabusId) {
        this.syllabusId = syllabusId;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Integer getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(Integer courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseCatalog() {
        return courseCatalog;
    }

    public void setCourseCatalog(String courseCatalog) {
        this.courseCatalog = courseCatalog;
    }

    public String getCoursePubman() {
        return coursePubman;
    }

    public void setCoursePubman(String coursePubman) {
        this.coursePubman = coursePubman;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getCourseCompany() {
        return courseCompany;
    }

    public void setCourseCompany(String courseCompany) {
        this.courseCompany = courseCompany;
    }

    public BigDecimal getCourseTimes() {
        return courseTimes;
    }

    public void setCourseTimes(BigDecimal courseTimes) {
        this.courseTimes = courseTimes;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public Integer getCoursePv() {
        return coursePv;
    }

    public void setCoursePv(Integer coursePv) {
        this.coursePv = coursePv;
    }

    public Integer getCourseCollectnum() {
        return courseCollectnum;
    }

    public void setCourseCollectnum(Integer courseCollectnum) {
        this.courseCollectnum = courseCollectnum;
    }

    public String getCourseRes() {
        return courseRes;
    }

    public void setCourseRes(String courseRes) {
        this.courseRes = courseRes;
    }

    public Integer getCourseCoin() {
        return courseCoin;
    }

    public void setCourseCoin(Integer courseCoin) {
        this.courseCoin = courseCoin;
    }

    public String getCourseQuestuion() {
        return courseQuestuion;
    }

    public void setCourseQuestuion(String courseQuestuion) {
        this.courseQuestuion = courseQuestuion;
    }

    public String getCourseJobid() {
        return courseJobid;
    }

    public void setCourseJobid(String courseJobid) {
        this.courseJobid = courseJobid;
    }

    public String getCourseJobdesc() {
        return courseJobdesc;
    }

    public void setCourseJobdesc(String courseJobdesc) {
        this.courseJobdesc = courseJobdesc;
    }

    public Date getCourseAt() {
        return courseAt;
    }

    public void setCourseAt(Date courseAt) {
        this.courseAt = courseAt;
    }

    public Integer getCourseDel() {
        return courseDel;
    }

    public void setCourseDel(Integer courseDel) {
        this.courseDel = courseDel;
    }

	public Coursestudy getCoursestudy() {
		return coursestudy;
	}

	public void setCoursestudy(Coursestudy coursestudy) {
		this.coursestudy = coursestudy;
	}
}