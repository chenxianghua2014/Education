package com.ttgis.education.entity;

import java.util.Date;

public class Knowledge {
    private String knowledgeId;

    private String knowledgeResourceId;

    private String knowledgeName;

    private Integer knowledgeType;

    private String knowledgeCatalog;

    private String knowledgeProducer;

    private Integer knowledgeClick;

    private Integer knowledgeCollectnum;

    private String knowledgePath;

    private Date knowledgeAt;

    private Integer knowledgeDel;
    
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

	public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getKnowledgeResourceId() {
        return knowledgeResourceId;
    }

    public void setKnowledgeResourceId(String knowledgeResourceId) {
        this.knowledgeResourceId = knowledgeResourceId;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public Integer getKnowledgeType() {
        return knowledgeType;
    }

    public void setKnowledgeType(Integer knowledgeType) {
        this.knowledgeType = knowledgeType;
    }

    public String getKnowledgeCatalog() {
        return knowledgeCatalog;
    }

    public void setKnowledgeCatalog(String knowledgeCatalog) {
        this.knowledgeCatalog = knowledgeCatalog;
    }

    public String getKnowledgeProducer() {
        return knowledgeProducer;
    }

    public void setKnowledgeProducer(String knowledgeProducer) {
        this.knowledgeProducer = knowledgeProducer;
    }

    public Integer getKnowledgeClick() {
        return knowledgeClick;
    }

    public void setKnowledgeClick(Integer knowledgeClick) {
        this.knowledgeClick = knowledgeClick;
    }

    public Integer getKnowledgeCollectnum() {
        return knowledgeCollectnum;
    }

    public void setKnowledgeCollectnum(Integer knowledgeCollectnum) {
        this.knowledgeCollectnum = knowledgeCollectnum;
    }

    public String getKnowledgePath() {
        return knowledgePath;
    }

    public void setKnowledgePath(String knowledgePath) {
        this.knowledgePath = knowledgePath;
    }

    public Date getKnowledgeAt() {
        return knowledgeAt;
    }

    public void setKnowledgeAt(Date knowledgeAt) {
        this.knowledgeAt = knowledgeAt;
    }

    public Integer getKnowledgeDel() {
        return knowledgeDel;
    }

    public void setKnowledgeDel(Integer knowledgeDel) {
        this.knowledgeDel = knowledgeDel;
    }
}