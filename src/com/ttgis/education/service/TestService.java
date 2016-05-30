package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Test;
import com.ttgis.education.entity.page.SelTest;
import com.ttgis.education.mapper.TestMapper;

@Repository
@Service
public class TestService
{

	@Resource
	private TestMapper TestMapper;

	public int deleteByPrimaryKey(String courseId)
	{
		return TestMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Test record)
	{
		return TestMapper.insert(record);
	}

	public int insertSelective(Test record)
	{
		return TestMapper.insertSelective(record);
	}

	public Test selectByPrimaryKey(String courseId)
	{
		return TestMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Test record)
	{
		return TestMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Test record)
	{
		return TestMapper.updateByPrimaryKey(record);
	}

	public int selectCountByWhere(SelTest selTest)
	{
		return TestMapper.selectCountByWhere(selTest);
	}

	public List<Test> selectByWhere(SelTest selTest)
	{
		return TestMapper.selectByWhere(selTest);
	}

	public List<Test> selectByCourseId(Test test)
	{
		return TestMapper.selectByCourseId(test);
	}
	
	/**
	 * 最后一次考试的答案 
	 * @param test
	 * @return
	 */
	public Test testBymark(Test test) {
		return TestMapper.testBymark(test);
	}
	
	/**
	 * 学生本门课程的成绩 
	 * @param test
	 * @return
	 */
	public int testByscore(Test test) {
		return TestMapper.testByscore(test);
	}
	
	/**
	 * 学员本门课程试题查询
	 * @param test
	 * @return
	 */
	public List<Test> testByS(Test test) {
		return TestMapper.testByS(test);
	}
	
	/**
	 * 学员本规则本科考试次数
	 * @param test
	 * @return
	 */
	public List<Test> StudentTextSum(Test test) {
		return TestMapper.StudentTextSum(test);
	}
	
	/**
	 * 是否评分结束
	 * @param test
	 * @return
	 */
	public int testByscoreManags(Test test) {
		return TestMapper.testByscoreManags(test);
	}
	
	/**
	 * 考试评分分页
	 * @param test
	 * @return
	 */
	public List<Test> selectPageAll(Test test) {
		return TestMapper.selectPageAll(test);
	}
	
	/**
	 * 考试评分总数
	 * @param test
	 * @return
	 */
	public int selectTextBysum(Test test) {
		return TestMapper.selectTextBysum(test);
	}

}