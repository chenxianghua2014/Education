package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Papers;

public interface PapersMapper {
    int deleteByPrimaryKey(String papersId);

    int insert(Papers record);

    int insertSelective(Papers record);

    Papers selectByPrimaryKey(String papersId);

    int updateByPrimaryKeySelective(Papers record);

    int updateByPrimaryKey(Papers record);
    
    /**
     * 试卷套数 
     * @param record
     * @return
     */
    List<Integer> selectCountByWhere(Papers record);
    
    /**
     * 随机抽取试题
     * @param record
     * @return
     */
    Papers thePapers(Papers record);
    
    /**
     * 获取试题
     * @param record
     * @return
     */
    List<Papers> MyPapersQuery(Papers record);
    
    /**
     * 删除试卷
     * @param record
     * @return
     */
    int updatePid(Papers record);
    
    /**
     * 获取最后一个试卷组号
     * @param record
     * @return
     */
    Papers PapersMySum(Papers record);
    
    /**
     * 根据课程ID查全部
     * @param record
     * @return
     */
    List<Papers> thePapersCourseId(Papers record);
}