package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.page.CoursePage;

public interface OrganizationMapper {
    int deleteByPrimaryKey(String organizationId);

    int insert(Organization record);

    int insertSelective(Organization record);
	/**
	 * 查询一条组织架构
	 * @param organizationId
	 * @return
	 */
    Organization selectByPrimaryKey(String organizationId);
    /**
	 * 查询是否为二级单位
	 * @param catalogId
	 * @return
	 */
    int judgeTwoUnits(String organizationId);

    int updateByPrimaryKeySelective(Organization record);

    int updateByPrimaryKey(Organization record);
    /**
     * 查询组织架构
     * @return
     */
    List<Organization> selectAllOrganization();
    /**
     * 查询条数(废弃)
     * @param page
     * @return
     */
    int selectCourseBySyllabusIdRows(CoursePage page);
    /**
     * 分页查询(废弃)
     * @param page
     * @return
     */
    List<Organization> selectCourseBySyllabusId(CoursePage page);
    
    /**
     * 查询上级单位
     * @param o
     * @return
     */
    List<Organization> organizationByID(Organization o);
    
    /**
     * ID查询
     * @param o
     * @return
     */
    Organization organizationMyID(Organization o);
    
    
    /**
     * 查询条数
     * @param o
     * @return
     */
    int OrganizationCount(Organization o);
    /**
     * 分页查询
     * @param o
     * @return
     */
    List<Organization> selectPageAll(Organization o);
    
    /**
     * 上级ID查询
     * @param organizationId
     * @return
     */
    List<Organization> organizationSJID(String organizationId);
    
    /**
     * 调转部门查询单位平级
     * @param organizationId
     * @return
     */
    List<Organization> organizationHBSJID(String organizationId);
    
    /**
     * 合并组织修改
     * @param o
     * @return
     */
    int updateHB(Organization o);
    /**
     * 课程推送 查询组织
     * @param 
     * @return
     */
    List<Organization> selectOrganizationKe(Organization o);
}