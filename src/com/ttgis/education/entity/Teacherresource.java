package com.ttgis.education.entity;

import java.util.Date;

public class Teacherresource {
    private String teacherresourceId;

    private String organizationId;

    private String teacherresourceName;

    private String teacherresourceUpname;

    private String teacherresourceUpid;

    private Date teacherresourceAt;

    private Integer teacherresourceDel;

    public String getTeacherresourceId() {
        return teacherresourceId;
    }

    public void setTeacherresourceId(String teacherresourceId) {
        this.teacherresourceId = teacherresourceId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getTeacherresourceName() {
        return teacherresourceName;
    }

    public void setTeacherresourceName(String teacherresourceName) {
        this.teacherresourceName = teacherresourceName;
    }

    public String getTeacherresourceUpname() {
        return teacherresourceUpname;
    }

    public void setTeacherresourceUpname(String teacherresourceUpname) {
        this.teacherresourceUpname = teacherresourceUpname;
    }

    public String getTeacherresourceUpid() {
        return teacherresourceUpid;
    }

    public void setTeacherresourceUpid(String teacherresourceUpid) {
        this.teacherresourceUpid = teacherresourceUpid;
    }

    public Date getTeacherresourceAt() {
        return teacherresourceAt;
    }

    public void setTeacherresourceAt(Date teacherresourceAt) {
        this.teacherresourceAt = teacherresourceAt;
    }

    public Integer getTeacherresourceDel() {
        return teacherresourceDel;
    }

    public void setTeacherresourceDel(Integer teacherresourceDel) {
        this.teacherresourceDel = teacherresourceDel;
    }
}