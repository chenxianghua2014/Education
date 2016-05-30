package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Syllabus;

public interface SyllabusMapper {
    int deleteByPrimaryKey(String syllabusId);

    int insert(Syllabus record);

    int insertSelective(Syllabus record);

    Syllabus selectByPrimaryKey(String syllabusId);

    int updateByPrimaryKeySelective(Syllabus record);

    int updateByPrimaryKey(Syllabus record);
    
    /**
     * 董再兴
     * 查询全部数据
     * @return
     */
    List<Syllabus> selectAllSyllabus();
    
    /**
     * 董再兴
     * 查询给定节点ID下的直接子节点
     * @param syllabsid
     * @return
     */
    List<String> selectByUpperId(String syllabusUpperId);
    
    /**
     * 陈健龙
     * 非集团账号课程树查询
     * @param record
     * @return
     */
    List<Syllabus> AccAllSyllabus(Syllabus record);
    
    /**
     * 联动通过上层ID查询
     * @param record
     * @return
     */
    List<Syllabus> SyllabusByUid(Syllabus record);
    
}