package com.ttgis.education.entity;

public class Coursenote {
    private String coursenoteId;

    private String coursestudyId;

   

    private Integer coursenoteDel;

    private String coursenoteContent;
    
    //学员表组织id
    private String studentCompanyId;
    
    private int type ; //登录权限类型
	
    private int cj ; //层级
    
    private String organizationId;//上级单位id
    
    private Organization organization;
    
    private String courseId;
    private String courseName;
    private String studentId;
    private String studentName;
    private int page;
   	private int size;
   	private String coursenoteAt;
    public String getCoursenoteId() {
        return coursenoteId;
    }

    public void setCoursenoteId(String coursenoteId) {
        this.coursenoteId = coursenoteId;
    }

    public String getCoursestudyId() {
        return coursestudyId;
    }

    public void setCoursestudyId(String coursestudyId) {
        this.coursestudyId = coursestudyId;
    }

    public Integer getCoursenoteDel() {
        return coursenoteDel;
    }

    public void setCoursenoteDel(Integer coursenoteDel) {
        this.coursenoteDel = coursenoteDel;
    }

    public String getCoursenoteContent() {
        return coursenoteContent;
    }

    public void setCoursenoteContent(String coursenoteContent) {
        this.coursenoteContent = coursenoteContent;
    }

	public String getStudentCompanyId() {
		return studentCompanyId;
	}

	public void setStudentCompanyId(String studentCompanyId) {
		this.studentCompanyId = studentCompanyId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCj() {
		return cj;
	}

	public void setCj(int cj) {
		this.cj = cj;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
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

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public String getCoursenoteAt() {
		return coursenoteAt;
	}

	public void setCoursenoteAt(String coursenoteAt) {
		this.coursenoteAt = coursenoteAt;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	
}