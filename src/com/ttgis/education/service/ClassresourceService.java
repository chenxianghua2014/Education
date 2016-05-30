package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Syllabus;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.trainCroursePage;
import com.ttgis.education.mapper.ClassresourceMapper;

@Repository
@Service
public class ClassresourceService {
	
	@Resource
	private ClassresourceMapper classresourceMapper;
	
	public int deleteByPrimaryKey(String classresourceId) {
		return classresourceMapper.deleteByPrimaryKey(classresourceId);
	}

	public int insert(Classresource record) {
		return classresourceMapper.insert(record);
	}

	public int insertSelective(Classresource record) {
		return classresourceMapper.insertSelective(record);
	}

	public Classresource selectByPrimaryKey(String classresourceId) {
		return classresourceMapper.selectByPrimaryKey(classresourceId);
	}

	public int updateByPrimaryKeySelective(Classresource record) {
		return classresourceMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Classresource record) {
		return classresourceMapper.updateByPrimaryKey(record);
	}
	
	/**
     * 通过ID查询下级数据
     * @param record
     * @return
     */
	public List<Classresource> classresourceMyID (Classresource record) {
		return classresourceMapper.classresourceMyID(record);
	}
    
    /**
     * 通过组织机构ID查询
     * @param record
     * @return
     */
	public List<Classresource> classresourceByOID (Classresource record) {
		return classresourceMapper.classresourceByOID(record);
	}
	
	/**
	 * ID查询本身
	 * @param record
	 * @return
	 */
	public Classresource classresourceByID(Classresource record){
		return classresourceMapper.classresourceByID(record);
		
	}
	/**
     * 分页查询
     * @return
     */
	public List<Classresource> selectClassresourcePageAll(Classresource p) {
		return classresourceMapper.selectClassresourcePageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int ClassresourceCount(Classresource p) {
		return classresourceMapper.ClassresourceCount(p);
	}
	/**
     * 查询培训班
     * @return
     */
	public List<Classresource> selectByAllKey(Classresource p) {
		return classresourceMapper.selectByAllKey(p);
	}
	/**
     * 查询党校培训班
     * @return
     */
	public List<Classresource> selectPartyTrain(Classresource p) {
		return classresourceMapper.selectPartyTrain(p);
	}
	
	/**
	 * 
	 * 级联删除节点下的所有直接子节点
	 * @param courseId
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public int deletesByPrimaryKey(String courseId){
		Classresource classresource = classresourceMapper.selectByPrimaryKey(courseId);
		if(classresource==null)return 0;
		classresource.setClassresourceDel(0);
		classresourceMapper.updateByPrimaryKey(classresource);
		List<String> list = classresourceMapper.selectByUpperId(courseId);
		for(String id: list){
			classresource = classresourceMapper.selectByPrimaryKey(id);
		classresource.setClassresourceDel(0);
		classresourceMapper.updateByPrimaryKey(classresource);
		}
		return 1;
	}
	

}
