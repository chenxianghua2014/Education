package com.ttgis.education.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.page.CoursePage;
import com.ttgis.education.mapper.CourseMapper;

@Repository
@Service
public class CourseService {
	
	@Resource
	private CourseMapper courseMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return courseMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Course record){
		return courseMapper.insert(record);
	}

	public int insertSelective(Course record){
		return courseMapper.insertSelective(record);
	}

	public Course selectByPrimaryKey(String courseId){
		return courseMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Course record){
		return courseMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Course record){
		return courseMapper.updateByPrimaryKey(record);
	}
	/**
	 * 查询全部课程
	 * @return
	 */
	public List<Course> selectAllCourse(){
		return courseMapper.selectAllCourse();
	}
	/**
	 * 分页分目录查询
	 * @param record
	 * @return
	 */
	public List<Course> selectCourseBySyllabusId(CoursePage record){
		return courseMapper.selectCourseBySyllabusId(record);
	}
	/**
	 * 分页分目录查询总条数
	 * @param page
	 * @return
	 */
	public int selectCourseBySyllabusIdRows(CoursePage page){
		return courseMapper.selectCourseBySyllabusIdRows(page);
	}
	
	public List<Course> selectToStudyOver(String studentId){
		return courseMapper.selectToStudyOver(studentId);
	}
	
	public List<Course> selectByTranningCourseId(String tranningCourseId){
		return courseMapper.selectByTranningCourseId(tranningCourseId);
	}
	
	public List<Course> selectTopNumber(int num){
		return courseMapper.selectTopNumber(num);
	}
	
	/**
	 * 董再兴	2015-08-31 16:49
	 * 课程管理中的课程分页
	 * @param map	包含分页和课程实体两个查询对象
	 * @return
	 */
	public  List<Course> selectCoursesBySyllabusId(Map map){
		return courseMapper.selectCoursesBySyllabusId(map);
	}
	
	/**
	 * 董再兴	2015-08-31 16:57
	 * 课程管理中的课程总数
	 * @param map	包含分页和课程实体两个查询对象
	 * @return
	 */
	public int selectCoursesBySyllabusIdCount(Map map){
		return courseMapper.selectCoursesBySyllabusIdCount(map);
	}

	
	
	/**
     * 课程分页
     * @param record
     * @return
     */
	public List<Course> selectPageAll(Course record) {
		return courseMapper.selectPageAll(record);
	}
    
    /**
     * 课程总数
     * @param record
     * @return
     */
	public int CoursesCount(Course record) {
		return courseMapper.CoursesCount(record);
	}
	
	/**
     * 添加数据查询
     * @param record
     * @return
     */
	public Course CourseByOId(Course record) {
		return courseMapper.CourseByOId(record);
	}
	
	//------------培训班--------------
	/**
     * 分页查询
     * @return
     */
	public List<Course> classCoursePageAll(Course p) {
		return courseMapper.classCoursePageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int classCourseCount(Course p) {
		return courseMapper.classCourseCount(p);
	}
	
	
	/**
	 * 前台页面显示课程信息
	 */
	public List<Course> CourseAll(Course p) {
		return courseMapper.CourseAll(p);
	}
	
	/**
	 * 前台页面显示课程信息
	 */
	public List<Course> CourseAllPV() {
		return courseMapper.CourseAllPV();
	}
	
	/**
     * 前台课程分页
     * @param record
     * @return
     */
	public List<Course> selectPageWebAll(Course record) {
		return courseMapper.selectPageWebAll(record);
	}
    
    /**
     * 前台课程总数
     * @param record
     * @return
     */
	public int CoursesWebCount(Course record) {
		return courseMapper.CoursesWebCount(record);
	}
	/**
     * queryNameList查id,name
     */
	public List<Course> queryNameList(){
		return courseMapper.queryNameList();
	}
	/**
     * 前台课程评分分页
     * @param record
     * @return
     */
	public List<Course> selectPageAllEstimate(Course ncourse) {
		
		return courseMapper.selectPageAllEstimate(ncourse);
	}
	/**
     * 前台收藏课程分页
     * @param record
     * @return
     */
	public List<Course> selectPageWebSc(Course record) {
		return courseMapper.selectPageWebSc(record);
	}
    
    /**
     * 前台收藏课程总数
     * @param record
     * @return
     */
	public int CoursesWebSc(Course record) {
		return courseMapper.CoursesWebSc(record);
	}
	
	/**
     * 站内模糊查询课程
     * @param record
     * @return
     */
	public List<Course> queryMyName(Course record) {
		return courseMapper.queryMyName(record);
	}
}