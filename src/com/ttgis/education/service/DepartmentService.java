package com.ttgis.education.service;


import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Department;
import com.ttgis.education.entity.Student;
import com.ttgis.education.mapper.DepartmentMapper;
/**
 * 部门service层
 * @author 陈健龙
 *
 */
@Repository
@Service
public class DepartmentService{

	@Resource
	private DepartmentMapper departmentMapper;
	
	public int deleteByPrimaryKey(String departmentId) {
		return departmentMapper.deleteByPrimaryKey(departmentId);
	}

	public int insert(Department record) {
		return departmentMapper.insert(record);
	}

	public int insertSelective(Department record) {
		return departmentMapper.insertSelective(record);
	}

	public Department selectByPrimaryKey(String departmentId) {
		return departmentMapper.selectByPrimaryKey(departmentId);
	}

	public int updateByPrimaryKeySelective(Department record) {
		return departmentMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Department record) {
		return departmentMapper.updateByPrimaryKey(record);
	}
	
	//查看单位部门
	public List<Department> selectbmlist(Department d){
		return departmentMapper.selectbmlist(d);
	}
	
	/**
	 * 总数统计
	 * @param d
	 * @return
	 */
	public int DepartmentCount(Department d){
		return departmentMapper.DepartmentCount(d);
		
	}
	
	 /**
	  * 部门分页
	  * @param d
	  * @return
	  */
	public List<Department> selectPageAll(Department d) {
		return departmentMapper.selectPageAll(d);
	}
	
	
	/**
	  * 部门ID组合查询
	  * @param d
	  * @return
	  */
	public Department DepartmentByOId(Department d) {
		return departmentMapper.DepartmentByOId(d);
	}
	/**
	  * 部门子集查询
	  * @param d
	  * @return
	  */
	public List<Department> DepartmentUpId(Department d) {
		return departmentMapper.DepartmentUpId(d);
	}
	
	
	/**
	  * 部门收藏总数
	  * @param d
	  * @return
	  */
	public int collectionDepartmentCount(Department d) {
		return departmentMapper.collectionDepartmentCount(d);
	}
	 
	 /**
	  * 部门收藏分页
	  * @param d
	  * @return
	  */
	public List<Department> collectionPageAll(Department d) {
		return departmentMapper.collectionPageAll(d);
	}
	
	/**
	  * 部门下科室查询
	  * @param d
	  * @return
	  */
	public List<Department> DepartmentById(Department d) {
		return departmentMapper.DepartmentById(d);
	}
	
	/**
	  * 合并联动Oid查询
	  * @param d
	  * @return
	  */
	public List<Department> DepartmentHBOId(Department d){
		return departmentMapper.DepartmentHBOId(d);
		
	}
	
	/**
	  * 修改科室上级部门ID
	  * @param d
	  * @return
	  */
	public int updateByDid(Department d) {
		return departmentMapper.updateByDid(d);
	}
	
	 /**
	  * 通过部门ID   修改部门所有科室组织ID
	  * @param d
	  * @return
	  */
	public int updateOidAll(Department d) {
		return departmentMapper.updateOidAll(d);
	}
	
	/**
	  * 合并组织结构，部门修改所属组织ID
	  * @param d
	  * @return
	  */
	public int updateOid(Department d) {
		return departmentMapper.updateOid(d);
	}
	
	
}