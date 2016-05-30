package com.ttgis.education.entity;

import java.util.Date;

public class KnowledgeResource {
    private String knowledgeResourceId;

    private String organizationId;

    private Integer knowledgeResourceRank;

    private String knowledgeResourceName;

    private String knowledgeResourceUpname;

    private String knowledgeResourceUpid;

    private Date knowledgeResourceAt;

    private Integer knowledgeResourceDel;
    
    private int page;
   	private int size;
   	
   	

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

	public String getKnowledgeResourceId() {
        return knowledgeResourceId;
    }

    public void setKnowledgeResourceId(String knowledgeResourceId) {
        this.knowledgeResourceId = knowledgeResourceId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getKnowledgeResourceRank() {
        return knowledgeResourceRank;
    }

    public void setKnowledgeResourceRank(Integer knowledgeResourceRank) {
        this.knowledgeResourceRank = knowledgeResourceRank;
    }

    public String getKnowledgeResourceName() {
        return knowledgeResourceName;
    }

    public void setKnowledgeResourceName(String knowledgeResourceName) {
        this.knowledgeResourceName = knowledgeResourceName;
    }

    public String getKnowledgeResourceUpname() {
        return knowledgeResourceUpname;
    }

    public void setKnowledgeResourceUpname(String knowledgeResourceUpname) {
        this.knowledgeResourceUpname = knowledgeResourceUpname;
    }

    public String getKnowledgeResourceUpid() {
        return knowledgeResourceUpid;
    }

    public void setKnowledgeResourceUpid(String knowledgeResourceUpid) {
        this.knowledgeResourceUpid = knowledgeResourceUpid;
    }

    public Date getKnowledgeResourceAt() {
        return knowledgeResourceAt;
    }

    public void setKnowledgeResourceAt(Date knowledgeResourceAt) {
        this.knowledgeResourceAt = knowledgeResourceAt;
    }

    public Integer getKnowledgeResourceDel() {
        return knowledgeResourceDel;
    }

    public void setKnowledgeResourceDel(Integer knowledgeResourceDel) {
        this.knowledgeResourceDel = knowledgeResourceDel;
    }
}