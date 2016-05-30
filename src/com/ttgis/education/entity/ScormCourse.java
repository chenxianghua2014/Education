package com.ttgis.education.entity;

import org.springframework.web.multipart.MultipartFile;

public class ScormCourse {
	int id;
	String name;
	MultipartFile scormZipFile;
	
	public ScormCourse(){}
	
	public ScormCourse(int id,String name,MultipartFile scormZipFile){
		this.id=id;
		this.name=name;
		this.scormZipFile=scormZipFile;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public MultipartFile getScormZipFile() {
		return scormZipFile;
	}

	public void setScormZipFile(MultipartFile scormZipFile) {
		this.scormZipFile = scormZipFile;
	}

	
}
