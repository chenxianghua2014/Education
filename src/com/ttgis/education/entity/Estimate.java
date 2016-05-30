package com.ttgis.education.entity;

import java.util.Date;

public class Estimate {
    private String estimateId;

	private String estimateType;

	private String estimateNumber;

	private Integer estimateRank;

	private Integer estimatePoint;

	private String estimateInformation;

	private Date estimateAt;

	private Integer estimateDel;

	private String courseId;
	
	private String estimateAt1;
	
	

	public String getEstimateAt1() {
		return estimateAt1;
	}

	public void setEstimateAt1(String estimateAt1) {
		this.estimateAt1 = estimateAt1;
	}

	public String getEstimateId() {
		return estimateId;
	}

	public void setEstimateId(String estimateId) {
		this.estimateId = estimateId;
	}

	public String getEstimateType() {
		return estimateType;
	}

	public void setEstimateType(String estimateType) {
		this.estimateType = estimateType;
	}

	public String getEstimateNumber() {
		return estimateNumber;
	}

	public void setEstimateNumber(String estimateNumber) {
		this.estimateNumber = estimateNumber;
	}

	public Integer getEstimateRank() {
		return estimateRank;
	}

	public void setEstimateRank(Integer estimateRank) {
		this.estimateRank = estimateRank;
	}

	public Integer getEstimatePoint() {
		return estimatePoint;
	}

	public void setEstimatePoint(Integer estimatePoint) {
		this.estimatePoint = estimatePoint;
	}

	public String getEstimateInformation() {
		return estimateInformation;
	}

	public void setEstimateInformation(String estimateInformation) {
		this.estimateInformation = estimateInformation;
	}

	public Date getEstimateAt() {
		return estimateAt;
	}

	public void setEstimateAt(Date estimateAt) {
		this.estimateAt = estimateAt;
	}

	public Integer getEstimateDel() {
		return estimateDel;
	}

	public void setEstimateDel(Integer estimateDel) {
		this.estimateDel = estimateDel;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	
}