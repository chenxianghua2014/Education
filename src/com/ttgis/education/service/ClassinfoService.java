package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Classinfo;
import com.ttgis.education.mapper.ClassinfoMapper;

@Repository
@Service
public class ClassinfoService {
	
	@Resource
	private ClassinfoMapper classinfoMapper;
	
	public int deleteByPrimaryKey(String courseStudyId){
		return classinfoMapper.deleteByPrimaryKey(courseStudyId);
    }

	public int insert(Classinfo record){
		return classinfoMapper.insert(record);
	}

	public int insertSelective(Classinfo record){
		return classinfoMapper.insertSelective(record);
	}

	public Classinfo selectByPrimaryKey(String courseStudyId){
		return classinfoMapper.selectByPrimaryKey(courseStudyId);
	}

	public int updateByPrimaryKeySelective(Classinfo record){
		return classinfoMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Classinfo record){
		return classinfoMapper.updateByPrimaryKey(record);
	}
	/**
	 * 推送课程与培训班课程对比
	 * @param si
	 * @return
	 */
		public List<Classinfo> selectByXinPanIdKey(Classinfo in) {
			return classinfoMapper.selectByXinPanIdKey(in);
		}
	/**
	 * 是否重复
	 * @param si
	 * @return
	 */
		public List<Classinfo> selectByTranclassIdKey(String id) {
			return classinfoMapper.selectByTranclassIdKey(id);
		}
	/**
	 * 是否重复
	 * @param si
	 * @return
	 */
		public List<Classinfo> selectByClasId(Classinfo si) {
			return classinfoMapper.selectByClasId(si);
		}
		/**
	     * 分页查询
	     * @return
	     */
		public List<Classinfo> classInfoPageAll(Classinfo p) {
			return classinfoMapper.classInfoPageAll(p);
		}
		
		/**
	     * 总数
	     * @return
	     */
		public int classInfoCount(Classinfo p) {
			return classinfoMapper.classInfoCount(p);
		}
		
		/**
	     * 培训报告   培训班名称    可到培训班/班次
	     * @return
	     */
		public Classinfo selectTrainingName(String p) {
			return classinfoMapper.selectTrainingName(p);
		}
		
}