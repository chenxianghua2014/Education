package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Department;

public interface DepartmentMapper {
    int deleteByPrimaryKey(String departmentId);

	int insert(Department record);

	int insertSelective(Department record);

	Department selectByPrimaryKey(String departmentId);

	int updateByPrimaryKeySelective(Department record);

	int updateByPrimaryKey(Department record);

	
	
	List<Department> selectbmlist(Department d);
	
	/**
	 * 部门总数
	 * @param d
	 * @return
	 */
	 int DepartmentCount(Department d);
	 
	 /**
	  * 部门分页
	  * @param d
	  * @return
	  */
	 List<Department> selectPageAll(Department d);
	 
	 
	 /**
	  * 部门ID组合查询
	  * @param d
	  * @return
	  */
	 Department DepartmentByOId(Department d);
	 /**
	  * 查部门子集
	  * @param d
	  * @return
	  */
	 List<Department> DepartmentUpId(Department d);
	 
	 /**
	  * 部门收藏总数
	  * @param d
	  * @return
	  */
	 int collectionDepartmentCount(Department d);
	 
	 /**
	  * 部门收藏分页
	  * @param d
	  * @return
	  */
	 List<Department> collectionPageAll(Department d);
	 
	 /**
	  * 部门下科室查询
	  * @param d
	  * @return
	  */
	 List<Department> DepartmentById(Department d);
	 
	 /**
	  * 合并联动Oid查询
	  * @param d
	  * @return
	  */
	 List<Department> DepartmentHBOId(Department d);
	 
	 /**
	  * 修改科室上级部门ID
	  * @param d
	  * @return
	  */
	 int updateByDid(Department d);
	 
	 /**
	  * 通过部门ID   修改部门所有科室组织ID
	  * @param d
	  * @return
	  */
	 int updateOidAll(Department d);
	 
	 /**
	  * 合并组织结构，部门修改所属组织ID
	  * @param d
	  * @return
	  */
	 int updateOid(Department d);
}