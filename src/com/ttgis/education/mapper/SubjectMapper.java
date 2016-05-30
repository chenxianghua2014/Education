package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Subject;
import com.ttgis.education.entity.page.SelSubject;

public interface SubjectMapper
{
	int deleteByPrimaryKey(String subjectId);

	int insert(Subject record);

	int insertSelective(Subject record);

	Subject selectByPrimaryKey(String subjectId);

	int updateByPrimaryKeySelective(Subject record);

	int updateByPrimaryKey(Subject record);

	int selectCountByWhere(SelSubject record);

	List<Subject> selectByWhere(SelSubject record);

	List<Subject> selectByCourseId(SelSubject record);

}