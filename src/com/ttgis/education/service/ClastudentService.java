package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Clastudent;
import com.ttgis.education.mapper.ClastudentMapper;

@Repository
@Service
public class ClastudentService {
	
	@Resource
	private ClastudentMapper clastudentMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return clastudentMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Clastudent record){
		return clastudentMapper.insert(record);
	}

	public int insertSelective(Clastudent record){
		return clastudentMapper.insertSelective(record);
	}

	public Clastudent selectByPrimaryKey(String courseId){
		return clastudentMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Clastudent record){
		return clastudentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Clastudent record){
		return clastudentMapper.updateByPrimaryKey(record);
	}
	/**
	 * 我的课程 前台  培训班
	 * @param si
	 * @return
	 */
		public List<Clastudent> selectAllKey(Clastudent si) {
			return clastudentMapper.selectAllKey(si);
		}
	/**
	 * 是否重复
	 * @param si
	 * @return
	 */
	public List<Clastudent> selectByCourseId(Clastudent si) {
		return clastudentMapper.selectByCourseId(si);
	}
	
	/**
     * 分页查询
     * @return
     */
	public List<Clastudent> classStudentPageAll(Clastudent p) {
		return clastudentMapper.classStudentPageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int classStudentCount(Clastudent p) {
		return clastudentMapper.classStudentCount(p);
	}
	/**
     * 前台我的课程  培训班 分页查询
     * @return
     */
	public List<Clastudent> classStudentPageAllIn(Clastudent p) {
		return clastudentMapper.classStudentPageAllIn(p);
	}
	
	/**
     * 前台我的课程  培训班   总数
     * @return
     */
	public int selectAllInKey(Clastudent p) {
		return clastudentMapper.selectAllInKey(p);
	}

	/**
	 * 根据学生Id查培训班次
	 * @param si
	 * @return
	 */
	public List<Clastudent> selectStudentClaId(String id) {
		return clastudentMapper.selectStudentClaId(id);
	}
	/**
	 * 根据培训班Id查审核通过的培训班学生
	 * @param si
	 * @return
	 */
	public List<Clastudent> selectClasId(String id) {
		return clastudentMapper.selectClasId(id);
	}

}