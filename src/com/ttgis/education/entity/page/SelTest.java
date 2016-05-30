package com.ttgis.education.entity.page;

import com.ttgis.education.entity.Ruld;

public class SelTest  extends Page
{
	private String subjectId;

	private String studentId;
	
	private String ruldId;
	
	private Ruld ruld;
	
	private int begin;
	private int end;


	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Ruld getRuld() {
		return ruld;
	}

	public void setRuld(Ruld ruld) {
		this.ruld = ruld;
	}
	

	public String getRuldId() {
		return ruldId;
	}

	public void setRuldId(String ruldId) {
		this.ruldId = ruldId;
	}

	public String getSubjectId()
	{
		return subjectId;
	}

	public void setSubjectId(String subjectId)
	{
		this.subjectId = subjectId;
	}

	public String getStudentId()
	{
		return studentId;
	}

	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}

}