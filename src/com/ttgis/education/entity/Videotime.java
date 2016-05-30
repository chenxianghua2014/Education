package com.ttgis.education.entity;

public class Videotime {
    private String videotimeId;

    private String courseresourceId;

    private String studentId;

    private String videotimeAt;

    private Integer videotimeDel;

    private String videotimeMark;
    
    private String courseId;
    

    public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getVideotimeId() {
        return videotimeId;
    }

    public void setVideotimeId(String videotimeId) {
        this.videotimeId = videotimeId;
    }

    public String getCourseresourceId() {
        return courseresourceId;
    }

    public void setCourseresourceId(String courseresourceId) {
        this.courseresourceId = courseresourceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getVideotimeAt() {
        return videotimeAt;
    }

    public void setVideotimeAt(String videotimeAt) {
        this.videotimeAt = videotimeAt;
    }

    public Integer getVideotimeDel() {
        return videotimeDel;
    }

    public void setVideotimeDel(Integer videotimeDel) {
        this.videotimeDel = videotimeDel;
    }

    public String getVideotimeMark() {
        return videotimeMark;
    }

    public void setVideotimeMark(String videotimeMark) {
        this.videotimeMark = videotimeMark;
    }
}