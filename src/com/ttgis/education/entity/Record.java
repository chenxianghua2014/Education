package com.ttgis.education.entity;

import java.util.Date;

public class Record {
    private String recordId;// 日志记录ID

    private String recordClass; // 日志记录类别

    private String recordType; // 日志记录状态

    private String recordContentid;// 日志记录内容ID
   
    private String studentId; // 日志（后台账号ID）

    private Date recordAt; //  日志记录添加时间

    private Integer recordDel; //  日志记录删除标记

    private String recordTitle;// 标题

    private String recordNote;// 内容
    
	private int page;
	private int size;
	
	
	private String accountCatalog;//组织架构ID
	
	
	
    public String getAccountCatalog() {
		return accountCatalog;
	}

	public void setAccountCatalog(String accountCatalog) {
		this.accountCatalog = accountCatalog;
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

	public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordClass() {
        return recordClass;
    }

    public void setRecordClass(String recordClass) {
        this.recordClass = recordClass;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordContentid() {
        return recordContentid;
    }

    public void setRecordContentid(String recordContentid) {
        this.recordContentid = recordContentid;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getRecordAt() {
        return recordAt;
    }

    public void setRecordAt(Date recordAt) {
        this.recordAt = recordAt;
    }

    public Integer getRecordDel() {
        return recordDel;
    }

    public void setRecordDel(Integer recordDel) {
        this.recordDel = recordDel;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordNote() {
        return recordNote;
    }

    public void setRecordNote(String recordNote) {
        this.recordNote = recordNote;
    }
}