package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Test;
import com.ttgis.education.entity.page.SelTest;

public interface TestMapper
{


	int deleteByPrimaryKey(String testId);

	int insert(Test record);

	int insertSelective(Test record);

	Test selectByPrimaryKey(String testId);

	int updateByPrimaryKeySelective(Test record);

	int updateByPrimaryKey(Test record);

	int selectCountByWhere(SelTest selTest);

	List<Test> selectByWhere(SelTest selTest);

	List<Test> selectByCourseId(Test test);
	/**
	 * 陈健龙
	 * 最后一次考试的答案 
	 * @param test
	 * @return
	 */
	Test testBymark(Test test);
	
	/**
	 * 陈健龙
	 * 学生本门课程的成绩 
	 * @param test
	 * @return
	 */
	int testByscore(Test test);
	
	/**
	 * 学员本门课程试题查询
	 * @param test
	 * @return
	 */
	List<Test> testByS(Test test);
	
	/**
	 * 学员本规则本科考试次数
	 * @param test
	 * @return
	 */
	List<Test> StudentTextSum(Test test);
	
	/**
	 * 是否评分结束
	 * @param test
	 * @return
	 */
	int testByscoreManags(Test test);
	
	/**
	 * 考试评分分页
	 * @param test
	 * @return
	 */
	List<Test> selectPageAll(Test test);
	
	/**
	 * 考试评分总数
	 * @param test
	 * @return
	 */
	int selectTextBysum(Test test);
}