package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.page.CoursePage;
import com.ttgis.education.mapper.OrganizationMapper;

@Repository
@Service
public class OrganizationService {
	
	@Resource
	private OrganizationMapper organizationMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return organizationMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Organization record){
		return organizationMapper.insert(record);
	}

	public int insertSelective(Organization record){
		return organizationMapper.insertSelective(record);
	}
	/**
	 * 查询一条组织架构
	 * @param courseId
	 * @return
	 */
	public Organization selectByPrimaryKey(String courseId){
		return organizationMapper.selectByPrimaryKey(courseId);
	}
	/**
	 *  查询是否为二级单位（无值代表是3级单位）
	 * @param catalogId
	 * @return
	 */
	public int judgeTwoUnits(String catalogId){
		return organizationMapper.judgeTwoUnits(catalogId);
	}
	public int updateByPrimaryKeySelective(Organization record){
		return organizationMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Organization record){
		return organizationMapper.updateByPrimaryKey(record);
	}

	/**
	 * 查询组织架构
	 * 
	 * @return
	 */
	public List<Organization> selectAllOrganization() {
		return organizationMapper.selectAllOrganization();
	}

	/**
	 * 分页查询
	 * 
	 * @param page
	 * @return
	 */
	public List<Organization> selectCourseBySyllabusId(CoursePage record) {
		return organizationMapper.selectCourseBySyllabusId(record);
	}

	/**
	 * 查询条数
	 * 
	 * @param page
	 * @return
	 */
	public int selectCourseBySyllabusIdRows(CoursePage page) {
		return organizationMapper.selectCourseBySyllabusIdRows(page);
	}
	
	 /**
     * 查询上级单位
     * @param o
     * @return
     */
	public List<Organization> organizationByID(Organization o) {
		return organizationMapper.organizationByID(o);
	}
	
	/**
     * ID查询
     * @param o
     * @return
     */
	 public Organization organizationMyID(Organization o){
		 return organizationMapper.organizationMyID(o);
	 }
	 
	 /**
	     * 查询条数
	     * @param o
	     * @return
	     */
	 public  int OrganizationCount(Organization o) {
		return organizationMapper.OrganizationCount(o);
	}
	    /**
	     * 分页查询
	     * @param o
	     * @return
	     */
	 public  List<Organization> selectPageAll(Organization o) {
			return organizationMapper.selectPageAll(o);
		}
	 
	 /**
	     * 上级ID查询
	     * @param organizationId
	     * @return
	     */
	 public List<Organization> organizationSJID(String organizationId) {
		return organizationMapper.organizationSJID(organizationId);
	}
	 
	 /**
	     * 调转部门查询单位平级
	     * @param organizationId
	     * @return
	     */
	 public List<Organization> organizationHBSJID(String organizationId) {
		return organizationMapper.organizationHBSJID(organizationId);
	}
	 
	 /**
	     * 合并组织修改
	     * @param o
	     * @return
	     */
	 public int updateHB(Organization o) {
		return organizationMapper.updateHB(o);
	}
	  /**
	     * 课程推送 查询组织
	     * @param o
	     * @return
	     */
	 public  List<Organization> selectOrganizationKe(Organization o) {
			return organizationMapper.selectOrganizationKe(o);
		}

}