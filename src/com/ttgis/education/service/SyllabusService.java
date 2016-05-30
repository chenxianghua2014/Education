package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttgis.education.entity.Syllabus;
import com.ttgis.education.mapper.SyllabusMapper;

@Repository
@Service
public class SyllabusService {
	
	@Resource
	private SyllabusMapper syllabusMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return syllabusMapper.deleteByPrimaryKey(courseId);
	}
	
	/**
	 * 董再兴
	 * 级联删除节点下的所有直接子节点
	 * @param courseId
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public int deletesByPrimaryKey(String courseId){
		Syllabus syllabs = syllabusMapper.selectByPrimaryKey(courseId);
		if(syllabs==null)return 0;
		syllabs.setSyllabusDel(0);
		syllabusMapper.updateByPrimaryKey(syllabs);
		List<String> list = syllabusMapper.selectByUpperId(courseId);
		for(String id: list){
			syllabs = syllabusMapper.selectByPrimaryKey(id);
			syllabs.setSyllabusDel(0);
			syllabusMapper.updateByPrimaryKey(syllabs);
		}
		return 1;
	}

	public int insert(Syllabus record){
		return syllabusMapper.insert(record);
	}
	
	/**
	 * 董再兴
	 * 课程目录树管理，插入节点
	 * @param record
	 * @return
	 */
	@Transactional(readOnly = false, rollbackFor = DataAccessException.class)
	public int insertCourse(Syllabus record){
		int i = syllabusMapper.insert(record);
		return i;
	}

	public int insertSelective(Syllabus record){
		return syllabusMapper.insertSelective(record);
	}

	public Syllabus selectByPrimaryKey(String courseId){
		return syllabusMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Syllabus record){
		return syllabusMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Syllabus record){
		return syllabusMapper.updateByPrimaryKey(record);
	}

	public List<Syllabus> selectAllSyllabus(){
		return syllabusMapper.selectAllSyllabus();
	}
	
    /**
     * 董再兴
     * 查询给定节点ID下的直接子节点
     * @param syllabsid
     * @return
     */
	public List<String> selectByUpperId(String syllabsid){
    	return syllabusMapper.selectByUpperId(syllabsid);
    }
	
	/**
     * 陈健龙
     * 非集团账号课程树查询
     * @param record
     * @return
     */
	public List<Syllabus> AccAllSyllabus(Syllabus record) {
		return syllabusMapper.AccAllSyllabus(record);
	}
	
	/**
	 * 陈健龙
     * 联动通过上层ID查询
     * @param record
     * @return
     */
	public List<Syllabus> SyllabusByUid(Syllabus record) {
		return syllabusMapper.SyllabusByUid(record);
	}
}