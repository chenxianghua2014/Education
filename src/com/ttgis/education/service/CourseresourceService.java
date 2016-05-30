package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Courseresource;
import com.ttgis.education.mapper.CourseresourceMapper;

@Repository
@Service
public class CourseresourceService {
	
	@Resource
	private CourseresourceMapper courseresourceMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return courseresourceMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Courseresource record){
		return courseresourceMapper.insert(record);
	}

	public int insertSelective(Courseresource record){
		return courseresourceMapper.insertSelective(record);
	}

	public Courseresource selectByPrimaryKey(String courseId){
		return courseresourceMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Courseresource record){
		return courseresourceMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Courseresource record){
		return courseresourceMapper.updateByPrimaryKey(record);
	}
	
	/**
     * 上传资源总数
     * @param record
     * @return
     */
	public int CourseresourceCount(Courseresource record) {
		return courseresourceMapper.CourseresourceCount(record);
	}
    
    /**
     * 上传资源分页
     * @param record
     * @return
     */
	public List<Courseresource> selectPageAll(Courseresource record) {
		return courseresourceMapper.selectPageAll(record);
	}
	
	/**
     * 前台显示
     * @param record
     * @return
     */
	public List<Courseresource> CourseresourceAll(Courseresource record) {
		return courseresourceMapper.CourseresourceAll(record);
	}

}