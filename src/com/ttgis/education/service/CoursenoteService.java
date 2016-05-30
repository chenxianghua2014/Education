package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Coursenote;
import com.ttgis.education.mapper.CoursenoteMapper;

@Repository
@Service
public class CoursenoteService {
	
	@Resource
	private CoursenoteMapper coursenoteMapper;
	
	public int deleteByPrimaryKey(String coursenoteId){
		return coursenoteMapper.deleteByPrimaryKey(coursenoteId);
	}

	public int insert(Coursenote record){
		return coursenoteMapper.insert(record);
	}

	public int insertSelective(Coursenote record){
		return coursenoteMapper.insertSelective(record);
	}

	public Coursenote selectByPrimaryKey(String courseId){
		return coursenoteMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Coursenote record){
		return coursenoteMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Coursenote record){
		return coursenoteMapper.updateByPrimaryKey(record);
	}
	 /**
     * 总数
     * @return
     */
	public int coursenoteCount(Coursenote coursenote){
		return coursenoteMapper.coursenoteCount(coursenote);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Coursenote> selectPageAll(Coursenote coursenote){
		return coursenoteMapper.selectPageAll(coursenote);
	}
	/**
     * 前台 根据视频id查询
     * @return
     */
	public List<Coursenote> selectAllList(Coursenote coursenote){
		return coursenoteMapper.selectAllList(coursenote);
	}
}