package com.ttgis.education.entity;

import java.util.Date;

public class Classresource {
    private String classresourceId;//培训班目录ID

    private String organizationId;//组织结构ID

    private String classresourceName;//培训班目录名称

    private Integer classresourceRank;//培训班目录级别	
    
    private String classresourceUpname;//上级目录名称   1 常规培训班     2  专题培训班     0 党校培训班

    private String classresourceUpid;//上级目录编号

    private Date classresourceAt;//添加时间

    private Integer classresourceDel;  //删除标记
    
    private int page;
	private int size;
	private int cj ;
	private int types ; //登录权限类型
	
	

    public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
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

	public String getClassresourceId() {
        return classresourceId;
    }

    public void setClassresourceId(String classresourceId) {
        this.classresourceId = classresourceId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getClassresourceName() {
        return classresourceName;
    }

    public void setClassresourceName(String classresourceName) {
        this.classresourceName = classresourceName;
    }

    public Integer getClassresourceRank() {
        return classresourceRank;
    }

    public void setClassresourceRank(Integer classresourceRank) {
        this.classresourceRank = classresourceRank;
    }

    public String getClassresourceUpname() {
        return classresourceUpname;
    }

    public void setClassresourceUpname(String classresourceUpname) {
        this.classresourceUpname = classresourceUpname;
    }

    public String getClassresourceUpid() {
        return classresourceUpid;
    }

    public void setClassresourceUpid(String classresourceUpid) {
        this.classresourceUpid = classresourceUpid;
    }

    public Date getClassresourceAt() {
        return classresourceAt;
    }

    public void setClassresourceAt(Date classresourceAt) {
        this.classresourceAt = classresourceAt;
    }

    public Integer getClassresourceDel() {
        return classresourceDel;
    }

    public void setClassresourceDel(Integer classresourceDel) {
        this.classresourceDel = classresourceDel;
    }
}