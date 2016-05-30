package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.page.SelStudent;
import com.ttgis.education.mapper.StudentMapper;

@Repository
@Service
public class StudentService
{

	@Resource
	private StudentMapper studentMapper;

	public int deleteByPrimaryKey(String courseId)
	{
		return studentMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Student record)
	{
		return studentMapper.insert(record);
	}

	public int insertSelective(Student record)
	{
		return studentMapper.insertSelective(record);
	}

	public Student selectByPrimaryKey(String courseId)
	{
		return studentMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Student record)
	{
		return studentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Student record)
	{
		return studentMapper.updateByPrimaryKey(record);
	}

	/**
	 * 修改学员组织ID，部门ID
	 * 
	 * @param p
	 * @return
	 */
	public int updateByDid(Student p)
	{
		return studentMapper.updateByDid(p);
	}

	/**
	 * 学院登录
	 * 
	 * @param record
	 * @return
	 */
	public Student checkLogin(Student record)
	{
		return studentMapper.checkLogin(record);
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	public List<Student> selectStudentPageAll(Student p)
	{
		return studentMapper.selectStudentPageAll(p);
	}

	/**
	 * 总数
	 * 
	 * @return
	 */
	public int StudentCount(Student p)
	{
		return studentMapper.StudentCount(p);
	}
	
	/**
	 * 培训报告  学员 分页查询
	 * 
	 * @return
	 */
	public List<Student> selectStudentHistory(Student p)
	{
		return studentMapper.selectStudentHistory(p);
	}

	/**
	 * 培训报告  学员 总数
	 * 
	 * @return
	 */
	public int StudentHistoryCount(Student p)
	{
		return studentMapper.StudentHistoryCount(p);
	}

	/**
	 * 部门ID查询学员
	 * 
	 * @param p
	 * @return
	 */
	public List<Student> StudentByDId(Student p)
	{
		return studentMapper.StudentByDId(p);
	}

	/**
	 * 通过部门ID 修改学员组织ID
	 * 
	 * @param p
	 * @return
	 */
	public int updateOid(Student p)
	{
		return studentMapper.updateOid(p);
	}

	/**
	 * 通过部门ID 修改部门所有科室下学员组织ID
	 * 
	 * @param p
	 * @return
	 */
	public int updateOidAll(Student p)
	{
		return studentMapper.updateOidAll(p);
	}

	/**
	 * 查询单条详细信息
	 * 
	 * @param courseId
	 * @return
	 */
	public Student selectByOneKey(String studentId)
	{
		return studentMapper.selectByOneKey(studentId);
	}

	public int selectCountByWhere(SelStudent selStudent)
	{
		return studentMapper.selectCountByWhere(selStudent);
	}

	public List<Student> selectByWhere(SelStudent selStudent)
	{
		return studentMapper.selectByWhere(selStudent);
	}

	/**
	 * 根据studentCompanyId查LIST
	 * 
	 * @param studentCompanyId
	 * @return
	 */
	public List<Student> queryBystudentCompanyId(String studentCompanyId){
		return studentMapper.queryBystudentCompanyId(studentCompanyId);
	}
	
	
	
	/**
	 * 合并组织结构，学员修改组织ID
	 * @param p
	 * @return
	 */
	public int updateHBOid(Student p) {
		return studentMapper.updateHBOid(p);
	}

	/**
	 * 查询人员byId
	 * @param p
	 * @return
	 */
	public Student queryStudentByStudentId(String studentId){
		return studentMapper.queryStudentByStudentId(studentId);
	}
	/**
	 * 查询人员bySEQ
	 * @param p
	 * @return
	 */
	public Student queryStudentByStudentSEQ(String studentSeq){
		return studentMapper.queryStudentByStudentSEQ(studentSeq);
	}

	
	
	/**
	 * 查询账号、学员是否存在
	 * @param s
	 * @return
	 */
	public int studentByNameSeq(Student s) {
		return studentMapper.studentByNameSeq(s);
	}
	
	
	/**
	 * 根据身份证登录后台接口
	 * @param studentoneSfzh
	 * @return
	 */
	public Student interfaceLogin(String studentoneSfzh) {
		return studentMapper.interfaceLogin(studentoneSfzh);
	}


}