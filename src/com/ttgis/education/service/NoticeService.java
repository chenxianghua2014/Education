package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Notice;
import com.ttgis.education.mapper.NoticeMapper;

@Repository
@Service
public class NoticeService {
	
	@Resource
	private NoticeMapper noticeMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return noticeMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(Notice record){
		return noticeMapper.insert(record);
	}

	public int insertSelective(Notice record){
		return noticeMapper.insertSelective(record);
	}

	public Notice selectByPrimaryKey(String courseId){
		return noticeMapper.selectByPrimaryKey(courseId);
	}

	public int updateByPrimaryKeySelective(Notice record){
		return noticeMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Notice record){
		return noticeMapper.updateByPrimaryKey(record);
	}
	public List<Notice> selectNoticeByNumber(int num){
		return noticeMapper.selectNoticeByNumber(num);
	}

	public List<Notice> selectAllNotice() {
		return noticeMapper.selectAllNotice();
	}
	
	/**
     * 分页查询
     * @return
     */
	public List<Notice> selectPageAll(Notice p) {
		return noticeMapper.selectPageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int NoticeCount(Notice p) {
		return noticeMapper.NoticeCount(p);
	}
	
	/**
	 * 通过组织结构ID处理计划ID
	 * @param p
	 * @return
	 */
	public Notice NoticeByOId(Notice p) {
		return noticeMapper.NoticeByOId(p);
	}
	
	/**
	 * ID查询
	 * @param p
	 * @return
	 */
	public Notice NoticeById(Notice p) {
		return noticeMapper.NoticeById(p);
	}
	
	/**
	 * 收藏信息分页
	 * @param p
	 * @return
	 */
	public List<Notice> collectionPageAll(Notice p) {
		return noticeMapper.collectionPageAll(p);
	}
	
	/**
	 * 收藏信息总数
	 * @param p
	 * @return
	 */
	public int collectionNoticeCount(Notice p) {
		return noticeMapper.collectionNoticeCount(p);
	}
	
	/**
	 * 按组织机构查询所有
	 * @param p
	 * @return
	 */
	public List<Notice>  NoticeByIdAll(Notice p){
		return noticeMapper.NoticeByIdAll(p);
		
	}
	
	/**
	 * 前台信息分页
	 * @param p
	 * @return
	 */
	public List<Notice> webPageAll(Notice p) {
		return noticeMapper.webPageAll(p);
	}
	
	/**
	 * 前台信息总数
	 * @param p
	 * @return
	 */
	public int webNoticeCount(Notice p) {
		return noticeMapper.webNoticeCount(p);
	}
	
	/**
	 * 查询置顶
	 * @param p
	 * @return
	 */
	public List<Notice> webNoticeTOP(Notice p) {
		return noticeMapper.webNoticeTOP(p);
	}
	
	
	/**
	 * 通知模糊查询
	 * @param p
	 * @return
	 */
	public List<Notice> noticeMyName(Notice p) {
		return noticeMapper.noticeMyName(p);
	}
}