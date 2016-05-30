package com.ttgis.education.mapper;

import java.util.List;
import java.util.Map;

import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.CoursePage;

public interface CourseMapper {
    int deleteByPrimaryKey(String courseId);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String courseId);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);
    
   List<Course> selectAllCourse();
    
    int selectCourseBySyllabusIdRows(CoursePage page);
    
    List<Course> selectCourseBySyllabusId(CoursePage page);
    /**
     * 查询可以进行考试的课程
     * @return
     */
    List<Course> selectToStudyOver(String studentId);
    
    List<Course> selectByTranningCourseId(String tranningCourseId);
    
    List<Course> selectTopNumber(int num);
    
    /**
     * 不用
     * 课程中的分页
     * @param map
     * @return
     */
    List<Course> selectCoursesBySyllabusId(Map map);
    
    /**
     * 不用
     * 课程中的总数
     * @param map
     * @return
     */
    int selectCoursesBySyllabusIdCount(Map map);
    
    
    
    /**
     * 课程分页
     * @param record
     * @return
     */
    List<Course> selectPageAll(Course record);
    
    /**
     * 课程总数
     * @param record
     * @return
     */
    int CoursesCount(Course record);
    
    /**
     * 添加数据查询（组合ID）
     * @param record
     * @return
     */
    Course CourseByOId(Course record);
  
   //-----------------------培训班课程----------------------- 
    /**
     * 分页查询
     * @return
     */
	List<Course> classCoursePageAll(Course p);
	
	/**
     * 总数
     * @return
     */
	int classCourseCount(Course p);
	 //---------------------培训班课程----------------------- 
	
	/**
	 * 前台页面显示课程信息
	 */
	List<Course> CourseAll(Course p);
	
	/**
	 * 前台页面显示课程信息
	 */
	List<Course> CourseAllPV();
	
	
	/**
     * 前台课程分页
     * @param record
     * @return
     */
    List<Course> selectPageWebAll(Course record);
    
    /**
     * 前台课程总数
     * @param record
     * @return
     */
    int CoursesWebCount(Course record);
    /**
     * queryNameList查id,name
     */
    List<Course> queryNameList();

	List<Course> selectPageAllEstimate(Course ncourse);
	/**
     * 前台收藏课程分页
     * @param record
     * @return
     */
    List<Course> selectPageWebSc(Course record);
    
    /**
     * 前台收藏课程总数
     * @param record
     * @return
     */
    int CoursesWebSc(Course record);
    
    
    /**
     * 站内模糊查询课程
     * @param record
     * @return
     */
    List<Course> queryMyName(Course record);
}