package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Videotime;

public interface VideotimeMapper {
    int deleteByPrimaryKey(String videotimeId);

    int insert(Videotime record);

    int insertSelective(Videotime record);

    Videotime selectByPrimaryKey(String videotimeId);

    int updateByPrimaryKeySelective(Videotime record);

    int updateByPrimaryKey(Videotime record);
    
    /**
     * 陈健龙
     * 通过学员ID、视频ID查询出观看次数
     * @param record
     * @return
     */
    List<Videotime> videtimeByCIdSId(Videotime record);
    
    /**
     * 陈健龙
     * 通过标记查询访问次数
     * @param record
     * @return
     */
    int videtimeByMark(Videotime record);
    
    /**
     * 陈健龙
     * 通过使用标记修改使用完成后的删除标记
     * @param record
     * @return
     */
    int updateByMark(Videotime record);
    /**
     * 王艳鹏
     * 记录开始时间和结束时间
     * @param videotimeId
     * @return
     */
    List<Videotime> timeByMark(Videotime record);
    /**
     * 陈健龙
     * 通过标记查询访问
     * @param record
     * @return
     */
    List<Videotime> videtimeByMarkList(Videotime record);
}