package com.ttgis.education.entity;

import java.util.Date;

public class Sort {
    private String sortId;

    private String sortInfoid;

    private String sortUserid;

    private Integer sortInfostate;

    private Integer sortField;

    private Date sortAt;

    private Integer sortDel;

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getSortInfoid() {
        return sortInfoid;
    }

    public void setSortInfoid(String sortInfoid) {
        this.sortInfoid = sortInfoid;
    }

    public String getSortUserid() {
        return sortUserid;
    }

    public void setSortUserid(String sortUserid) {
        this.sortUserid = sortUserid;
    }

    public Integer getSortInfostate() {
        return sortInfostate;
    }

    public void setSortInfostate(Integer sortInfostate) {
        this.sortInfostate = sortInfostate;
    }

    public Integer getSortField() {
        return sortField;
    }

    public void setSortField(Integer sortField) {
        this.sortField = sortField;
    }

    public Date getSortAt() {
        return sortAt;
    }

    public void setSortAt(Date sortAt) {
        this.sortAt = sortAt;
    }

    public Integer getSortDel() {
        return sortDel;
    }

    public void setSortDel(Integer sortDel) {
        this.sortDel = sortDel;
    }
}