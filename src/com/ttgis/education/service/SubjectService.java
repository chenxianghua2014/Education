package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Subject;
import com.ttgis.education.entity.page.SelSubject;
import com.ttgis.education.mapper.SubjectMapper;

@Repository
@Service
public class SubjectService
{

	@Resource
	private SubjectMapper subjectMapper;

	public int deleteByPrimaryKey(String courseId)
	{
		return subjectMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Subject record)
	{
		return subjectMapper.insert(record);
	}

	public int insertSelective(Subject record)
	{
		return subjectMapper.insertSelective(record);
	}

	public Subject selectByPrimaryKey(String courseId)
	{
		return subjectMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Subject record)
	{
		return subjectMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Subject record)
	{
		return subjectMapper.updateByPrimaryKey(record);
	}

	public int selectCountByWhere(SelSubject subject)
	{
		return subjectMapper.selectCountByWhere(subject);
	}

	public List<Subject> selectByWhere(SelSubject subject)
	{
		return subjectMapper.selectByWhere(subject);
	}

	public List<Subject> selectByCourseId(SelSubject subject)
	{
		return subjectMapper.selectByCourseId(subject);
	}

}