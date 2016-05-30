package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.page.SelStudent;

public interface StudentMapper
{

	int deleteByPrimaryKey(String studentId);

	int insert(Student record);

	int insertSelective(Student record);

	Student selectByPrimaryKey(String studentId);

	int updateByPrimaryKeySelective(Student record);

	int updateByPrimaryKey(Student record);

	/**
	 * 学院登录
	 * 
	 * @param record
	 * @return
	 */
	Student checkLogin(Student record);

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	List<Student> selectStudentPageAll(Student p);

	/**
	 * 总数
	 * 
	 * @return
	 */
	int StudentCount(Student p);
	/**
	 * 培训报告  学员 分页查询
	 * 
	 * @return
	 */
	List<Student> selectStudentHistory(Student p);

	/**
	 * 培训报告  学员 总数
	 * 
	 * @return
	 */
	int StudentHistoryCount(Student p);
	/**
	 * 部门ID查询学员
	 * 
	 * @param p
	 * @return
	 */
	List<Student> StudentByDId(Student p);

	/**
	 * 修改学员组织ID，部门ID
	 * 
	 * @param p
	 * @return
	 */
	int updateByDid(Student p);

	/**
	 * 通过部门ID 修改学员组织ID
	 * 
	 * @param p
	 * @return
	 */
	int updateOid(Student p);

	/**
	 * 通过部门ID 修改部门所有科室下学员组织ID
	 * 
	 * @param p
	 * @return
	 */
	int updateOidAll(Student p);

	/**
	 * 查询单条详细信息
	 * 
	 * @param studentId
	 * @return
	 */
	Student selectByOneKey(String studentId);

	int selectCountByWhere(SelStudent selStudent);

	List<Student> selectByWhere(SelStudent selStudent);

	/**
	 * 根据studentCompanyId查LIST
	 * 
	 * @param studentCompanyId
	 * @return
	 */
	List<Student> queryBystudentCompanyId(String studentCompanyId);
	
	
	/**
	 * 合并组织结构，学员修改组织ID
	 * @param p
	 * @return
	 */
	int updateHBOid(Student p);
	/**
	 * 查询账号、学员是否存在
	 * @param selStudent
	 * @return
	 */
	int studentByNameSeq(Student selStudent);
	/**
	 * 查询人员byId
	 * @param p
	 * @return
	 */
	Student queryStudentByStudentId(String studentId);
	/**
	 * 查询人员bySEQ
	 * @param p
	 * @return
	 */
	Student queryStudentByStudentSEQ(String studentSeq);
	
	/**
	 * 根据身份证登录后台接口
	 * @param studentoneSfzh
	 * @return
	 */
	Student interfaceLogin(String studentoneSfzh);
	


}