package com.ttgis.education.entity;

import java.util.Date;

public class Syllabus {
    private String syllabusId;

    private String organizationId;

    private Integer syllabusRank;

    private String syllabusName;

    private String syllabusUpperId;

    private Date syllabusAt;

    private Integer syllabusDel;

    public String getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(String syllabusId) {
        this.syllabusId = syllabusId;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getSyllabusRank() {
        return syllabusRank;
    }

    public void setSyllabusRank(Integer syllabusRank) {
        this.syllabusRank = syllabusRank;
    }

    public String getSyllabusName() {
        return syllabusName;
    }

    public void setSyllabusName(String syllabusName) {
        this.syllabusName = syllabusName;
    }

    public String getSyllabusUpperId() {
        return syllabusUpperId;
    }

    public void setSyllabusUpperId(String syllabusUpperId) {
        this.syllabusUpperId = syllabusUpperId;
    }

    public Date getSyllabusAt() {
        return syllabusAt;
    }

    public void setSyllabusAt(Date syllabusAt) {
        this.syllabusAt = syllabusAt;
    }

    public Integer getSyllabusDel() {
        return syllabusDel;
    }

    public void setSyllabusDel(Integer syllabusDel) {
        this.syllabusDel = syllabusDel;
    }
}