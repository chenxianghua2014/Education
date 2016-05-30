package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Programme;

public interface NoticeMapper {



	int deleteByPrimaryKey(String noticeId);

	int insert(Notice record);

	int insertSelective(Notice record);

	Notice selectByPrimaryKey(String noticeId);

	int updateByPrimaryKeySelective(Notice record);

	int updateByPrimaryKeyWithBLOBs(Notice record);

	int updateByPrimaryKey(Notice record);

	
    /**
     * 查询指定数目的标记为“已发布”的数据
     * @param num
     * @return
     */
    List<Notice> selectNoticeByNumber(int num);
    /**
     * 查询指全部的标记为�?已发布�?的数�?
     * @param num
     * @return
     */
	List<Notice> selectAllNotice();
	
	/**
     * 分页查询
     * @return
     */
	List<Notice> selectPageAll(Notice p);
	
	/**
     * 总数
     * @return
     */
	int NoticeCount(Notice p);
	
	/**
	 * 通过组织结构ID处理计划ID
	 * @param p
	 * @return
	 */
	Notice NoticeByOId(Notice p);
	
	/**
	 * ID查询
	 * @param p
	 * @return
	 */
	Notice NoticeById(Notice p);
	
	/**
	 * 收藏信息分页
	 * @param p
	 * @return
	 */
	List<Notice> collectionPageAll(Notice p);
	
	/**
	 * 收藏信息总数
	 * @param p
	 * @return
	 */
	int collectionNoticeCount(Notice p);
	
	/**
	 * 按组织机构查询所有
	 * @param p
	 * @return
	 */
	List<Notice>  NoticeByIdAll(Notice p);
	
	/**
	 * 前台信息分页
	 * @param p
	 * @return
	 */
	List<Notice> webPageAll(Notice p);
	
	/**
	 * 前台信息总数
	 * @param p
	 * @return
	 */
	int webNoticeCount(Notice p);
	
	
	/**
	 * 查询置顶
	 * @param p
	 * @return
	 */
	List<Notice> webNoticeTOP(Notice p);
	
	
	/**
	 * 通知模糊查询
	 * @param p
	 * @return
	 */
	List<Notice> noticeMyName(Notice p);
}