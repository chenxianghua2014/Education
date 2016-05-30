package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.News;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.InformationPage;
import com.ttgis.education.entity.page.trainCroursePage;
import com.ttgis.education.mapper.TranclassMapper;

@Repository
@Service
public class TranclassService {
	
	@Resource
	private TranclassMapper TranclassMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return TranclassMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Tranclass record){
		return TranclassMapper.insert(record);
	}

	public int insertSelective(Tranclass record){
		return TranclassMapper.insertSelective(record);
	}

	public Tranclass selectByPrimaryKey(String courseId){
		return TranclassMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Tranclass record){
		return TranclassMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Tranclass record){
		return TranclassMapper.updateByPrimaryKey(record);
	}
	/**
	 * 查询指定条数的数据
	 * @param num
	 * @return
	 */
	public List<Tranclass> selectByNum(int num){
		return TranclassMapper.selectByNum(num);
	}
	
	/**
     * 分页查询
     * @return
     */
	public List<Tranclass> selectShiftPageAll(Tranclass p) {
		return TranclassMapper.selectShiftPageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int ShiftCount(Tranclass p) {
		return TranclassMapper.ShiftCount(p);
	}
	
	 /**
     * 培训班ID查询
     * @param t
     * @return
     */
	public List<Tranclass> TranclassByOId(Tranclass t) {
		return TranclassMapper.TranclassByOId(t);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Tranclass> selectShiftDxPage(Tranclass p) {
		return TranclassMapper.selectShiftDxPage(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int ShiftCountPage(Tranclass p) {
		return TranclassMapper.ShiftCountPage(p);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Tranclass> selectShiftDxSix(Tranclass p) {
		return TranclassMapper.selectShiftDxSix(p);
	}
}