package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Programme;
import com.ttgis.education.mapper.ProgrammeMapper;

@Repository
@Service
public class ProgrammeService {
	
	@Resource
	private ProgrammeMapper programmeMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return programmeMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Programme record){
		return programmeMapper.insert(record);
	}

	public int insertSelective(Programme record){
		return programmeMapper.insertSelective(record);
	}

	public Programme selectByPrimaryKey(String courseId){
		return programmeMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Programme record){
		return programmeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Programme record){
		return programmeMapper.updateByPrimaryKey(record);
	}
	
	public int updateByPrimaryKeyWithBLOBs(Programme record) {
		return programmeMapper.updateByPrimaryKeyWithBLOBs(record);
	}
	/**
	 * 查询指定条数的标记为 “已发布”的数据
	 * @param num
	 * @return
	 */
	public List<Programme> selectProgrammeByNumber(int num){
		return programmeMapper.selectProgrammeByNumber(num);
	}
	/**
	 * 查询全部标记为“已发布” 的数据
	 * @return
	 */
	public List<Programme> selectAllProgramme() {
		return programmeMapper.selectAllProgramme();
	}
	
	/**
     * 分页查询
     * @return
     */
	public List<Programme> selectPageAll(Programme p) {
		return programmeMapper.selectPageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int ProgrammeCount(Programme p) {
		return programmeMapper.ProgrammeCount(p);
	}
	
	/**
	 * 通过组织结构ID处理计划ID
	 * @param p
	 * @return
	 */
	public Programme ProgrammeByOId(Programme p) {
		return programmeMapper.ProgrammeByOId(p);
	}
	/**
	 * ID查询
	 * @param p
	 * @return
	 */
	public Programme ProgrammeById(Programme p) {
		return programmeMapper.ProgrammeById(p);
	}
	
	/**
	 * 收藏信息分页
	 * @param p
	 * @return
	 */
	public List<Programme> collectionPageAll(Programme p) {
		return programmeMapper.collectionPageAll(p);
	}
	
	/**
	 * 收藏信息总数
	 * @param p
	 * @return
	 */
	public int collectionProgrammeCount(Programme p) {
		return programmeMapper.collectionProgrammeCount(p);
	}
	
	/**
	 * 前台显示
	 * @param p
	 * @return
	 */
	public List<Programme> ProgrammeByOIdAll(Programme p) {
		return programmeMapper.ProgrammeByOIdAll(p);
	}
	
	/**
	 * 前台信息分页
	 * @param p
	 * @return
	 */
	public List<Programme> webPageAll(Programme p) {
		return programmeMapper.webPageAll(p);
	}
	
	/**
	 * 前台信息总数
	 * @param p
	 * @return
	 */
	public int webProgrammeCount(Programme p) {
		return programmeMapper.webProgrammeCount(p);
	}
	
	
	/**
	 * 查询置顶
	 * @param p
	 * @return
	 */
	public List<Programme> webProgrammeTOP(Programme p) {
		return programmeMapper.webProgrammeTOP(p);
	}
	
	
	/**
	 * 站内模糊查询计划
	 * @param p
	 * @return
	 */
	public List<Programme> programmeMyName(Programme p) {
		return programmeMapper.programmeMyName(p);
	}

	
}