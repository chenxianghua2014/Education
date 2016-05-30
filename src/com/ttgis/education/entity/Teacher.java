package com.ttgis.education.entity;

import java.util.Date;

public class Teacher {
    private String teacherId;

    private String teacherresourceId;

    private String teacherName;

    private String teacherInformation;

    private String teacherCatalog;

    private Integer teacherAge;

    private String teacherAdmin;

    private Integer teacherRank;

    private String teacherPhoto;

    private Date teacherAt;

    private Integer teacherDel;

    private int type ; //登录权限类型
	
    private int cj ; //层级
    
    private String organizationId;//上级单位id
    
    private Organization organization;
    
    private int page;
   	private int size;
    
    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherresourceId() {
        return teacherresourceId;
    }

    public void setTeacherresourceId(String teacherresourceId) {
        this.teacherresourceId = teacherresourceId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherInformation() {
        return teacherInformation;
    }

    public void setTeacherInformation(String teacherInformation) {
        this.teacherInformation = teacherInformation;
    }

    public String getTeacherCatalog() {
        return teacherCatalog;
    }

    public void setTeacherCatalog(String teacherCatalog) {
        this.teacherCatalog = teacherCatalog;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String getTeacherAdmin() {
        return teacherAdmin;
    }

    public void setTeacherAdmin(String teacherAdmin) {
        this.teacherAdmin = teacherAdmin;
    }

    public Integer getTeacherRank() {
        return teacherRank;
    }

    public void setTeacherRank(Integer teacherRank) {
        this.teacherRank = teacherRank;
    }

    public String getTeacherPhoto() {
        return teacherPhoto;
    }

    public void setTeacherPhoto(String teacherPhoto) {
        this.teacherPhoto = teacherPhoto;
    }

    public Date getTeacherAt() {
        return teacherAt;
    }

    public void setTeacherAt(Date teacherAt) {
        this.teacherAt = teacherAt;
    }

    public Integer getTeacherDel() {
        return teacherDel;
    }

    public void setTeacherDel(Integer teacherDel) {
        this.teacherDel = teacherDel;
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
}