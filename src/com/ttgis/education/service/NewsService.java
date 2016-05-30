package com.ttgis.education.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttgis.education.entity.News;
import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.mapper.NewsMapper;
import com.ttgis.education.mapper.OrganizationMapper;

@Repository
@Service

public class NewsService {
	
	@Resource
	private NewsMapper newsMapper;
	@Resource
	private OrganizationMapper organizationMapper;
	
	public int deleteByPrimaryKey(String courseId){
		return newsMapper.deleteByPrimaryKey(courseId);
	}

	public int insert(News record){
		return newsMapper.insert(record);
	}
/**
 * 增加一条
 * @param record
 * @return
 */
	@ResponseBody
	public int insertSelective(News news){
		Organization company = organizationMapper.selectByPrimaryKey(news.getOrganizationId());//单位名称和代码号
		int judge=organizationMapper.judgeTwoUnits(news.getOrganizationId());//判断是否是二级单位
		News newNumber = newsMapper.newsNumber(company.getOrganizationId());
		//主键规则   ：  XW+单位代码-本单位新闻稿序号
				if(newNumber!=null){
					String[] num = newNumber.getNewsId().split("-");
					news.setNewsId("XW"+company.getOrganizationDwdm()+"-"+(Integer.parseInt(num[1])+1));	
				}else{
					news.setNewsId("XW"+company.getOrganizationDwdm()+"-1");	
				}
				news.setNewsDel(1);
				news.setNewsType("不可用");//新闻状态
				news.setNewsCatalog(company.getOrganizationDwmc());//新闻所属目录
				news.setNewsAt(new Date());
				
				
		
		return newsMapper.insertSelective(news);
	}

	public News selectByPrimaryKey(String courseId){
		return newsMapper.selectByPrimaryKey(courseId);
	}
	@ResponseBody
	public int updateByPrimaryKeySelective(News news){
		return newsMapper.updateByPrimaryKeySelective(news);
	}

	public int updateByPrimaryKey(News record){
		return newsMapper.updateByPrimaryKey(record);
	}
	/**
	 * 查询指定条数的数据
	 * @param num
	 * @return
	 */
	public List<News> selectNewsByNUmber(int num){
		return newsMapper.selectNewsByNUmber(num);
	}
	/**
	 * 查询全部数据
	 * @return
	 */
	public List<News> selectAllNews(){
		return newsMapper.selectAllNews();
	}
	/**
     * 分页查询
     * @return
     */
	public List<News> selectPageAll(News p) {
		return newsMapper.selectPageAll(p);
	}
	
	/**
     * 总数
     * @return
     */
	public int NewsCount(News p) {
		return newsMapper.NewsCount(p);
	}
	/**
	 * 设 本单位新闻稿序号 
	 * @return
	 */
	public News newsNumber(String newsCatalog){
		return newsMapper.newsNumber(newsCatalog);
	}
	
	/**
	 * 收藏信息分页
	 * @param p
	 * @return
	 */
	public List<News> collectionPageAll(News p) {
		return newsMapper.collectionPageAll(p);
	}
	
	/**
	 * 收藏信息总数
	 * @param p
	 * @return
	 */
	public int collectionNewsCount(News p) {
		return newsMapper.collectionNewsCount(p);
	}
	
	
	/**
	 * 前台查询
	 * @param n
	 * @return
	 */
	public List<News> NewsByOIdAll(News n) {
		return newsMapper.NewsByOIdAll(n);
	}
	
	
	/**
	 * 前台分页
	 * @param n
	 * @return
	 */
	public List<News> webPageAll(News n) {
		return newsMapper.webPageAll(n);
	}
	
	/**
	 * 前台总条数
	 * @param n
	 * @return
	 */
	public int webNewsCount(News n) {
		return newsMapper.webNewsCount(n);
	}
	
	/**
	 * 查询过期置顶
	 * @param n
	 * @return
	 */
	public List<News> webNewsTOP(News n) {
		return newsMapper.webNewsTOP(n);
	}
	
	
	/**
	 * 站内模糊查询搜索
	 * @param n
	 * @return
	 */
	public List<News> newsMyName(News n) {
		return newsMapper.newsMyName(n);
	}
	
	

}