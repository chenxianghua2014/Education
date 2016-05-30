package com.ttgis.education.entity;

import java.util.Date;

public class Collect {
    private String collectId;//收藏id

    private String studentId;//学员Id

    private String collectType;//收藏类型

    private String collectContentid;//收藏内容id

    private Date collectAt;//添加时间

    private Integer collectDel;//删除标记

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }

    public String getCollectContentid() {
        return collectContentid;
    }

    public void setCollectContentid(String collectContentid) {
        this.collectContentid = collectContentid;
    }

    public Date getCollectAt() {
        return collectAt;
    }

    public void setCollectAt(Date collectAt) {
        this.collectAt = collectAt;
    }

    public Integer getCollectDel() {
        return collectDel;
    }

    public void setCollectDel(Integer collectDel) {
        this.collectDel = collectDel;
    }
}