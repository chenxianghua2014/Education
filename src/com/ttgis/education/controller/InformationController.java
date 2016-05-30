package com.ttgis.education.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.News;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Sort;
import com.ttgis.education.entity.page.InformationPage;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.NewsService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.SortService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.Tools;
/**
 * 信息管理模块（树、新闻）
 * <p>Title:InformationController </p>
 *@author 陈健龙
 *
 */

@Controller
public class InformationController {


	@Resource
	private OrganizationService organizationService;
	@Resource
	private NewsService newsService;
	
	@Resource
	private SortService sortService;


	private News news;


	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(InformationController.class);



	//--------------------------------------新闻后台---------------------------------------	
	/**
	 * 组织架构树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zzjgTree", method = RequestMethod.GET)
	public ModelAndView zzjgTree(int n,Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		Organization oneid = organizationService.selectByPrimaryKey(account.getAccountCatalog());
		List<Organization> organization = organizationService.selectAllOrganization();
		model.addAttribute("organization", organization);
		model.addAttribute("account", account);
		model.addAttribute("oneid", oneid);
		if(n == 1){
			model.addAttribute("type", "新闻");
		}
		if(n == 2){
			model.addAttribute("type", "通知");
		}
		if(n == 3){
			model.addAttribute("type", "计划");
		}
		return new ModelAndView("tree/zzjgTree");
	}
	
	
	/**
	 * 党校组织架构树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/schoolzzjgTree", method = RequestMethod.GET)
	public ModelAndView schoolzzjgTree(int n,Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		Organization oneid = organizationService.selectByPrimaryKey(account.getAccountCatalog());
		List<Organization> organization = organizationService.selectAllOrganization();
		model.addAttribute("organization", organization);
		model.addAttribute("account", account);
		model.addAttribute("oneid", oneid);
		if(n == 1){
			model.addAttribute("type", "新闻");
		}
		if(n == 2){
			model.addAttribute("type", "通知");
		}
		if(n == 3){
			model.addAttribute("type", "计划");
		}
		return new ModelAndView("tree/schoolzzjgTree");
	}
	
	
	/**
	 * 查询新闻（后台）
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/journalism")
	public ModelAndView journalism(News n,int mark ,int cj,int page,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			news =new News();
		}
		
		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){//集团账号
			news.setOrganizationId(n.getOrganizationId());
			news.setType(acc.getAccountType());
		}
		if(acc.getAccountType()==2){//院一级账号
			if(cj==1){//判断前台是否点击第1层
				news.setOrganizationId(acc.getAccountCatalog());
			}else{
				news.setOrganizationId(n.getOrganizationId());
			}
			news.setType(acc.getAccountType());

		}

		if(acc.getAccountType()==3){//院二级账号
			news.setOrganizationId(acc.getAccountCatalog());
			news.setType(acc.getAccountType());
		}

		news.setCj(cj);




		//---------------------条件查询----------------------------------
		if(n.getNewsRank()!=null && n.getNewsRank()!=0 ){
			news.setNewsRank(n.getNewsRank());
		}
		if(n.getNewsCatalog()!=null && n.getNewsCatalog()!="" ){
			news.setNewsCatalog(n.getNewsCatalog());
		}
		if(n.getNewsTime()!=null && n.getNewsTime()!="" ){
			news.setNewsTime(n.getNewsTime());
		}

		news.setNewsSort(n.getNewsSort());
		PageBean pageBean = new PageBean();
		int allRow = newsService.NewsCount(news);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		news.setPage(length*(currentPage1-1));
		news.setSize(length);

		List<News> list = newsService.selectPageAll(news);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("journalism?newsSort="+n.getNewsSort()+"&mark=0&cj="+cj+"&organizationId="+n.getOrganizationId());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("news",news);
		mv.addObject("pageNewsId",n.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		if(news.getNewsSort().equals("1")){
			mv.setViewName("backInformation/querynews");
		}else{
			mv.setViewName("news/queryGNews");
		}
		
		return  mv;
	}

	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/newsSkipID")
	public ModelAndView newsSkipID(String id,String newsType,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(newsType.equals("1")){
			if(id.equals("0")){
				mv.setViewName("backInformation/addnew");
			}else{
				news = newsService.selectByPrimaryKey(id);
				mv.addObject("news",news);
				mv.addObject("mark",request.getParameter("mark"));
				mv.setViewName("backInformation/editnew");
			}
		}else if(newsType.equals("2")){
			if(id.equals("0")){
				mv.setViewName("news/addGNew");
			}else{
				news = newsService.selectByPrimaryKey(id);
				mv.addObject("news",news);
				mv.addObject("mark",request.getParameter("mark"));
				mv.setViewName("news/updateGNew");
			}
		}
		
		return mv;
	}



	/**
	 * 提交新闻信息（后台）
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/newSava", method = RequestMethod.POST)
	public ModelAndView newSava(News news,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		news.setOrganizationId(acc.getAccountCatalog());
		news.setNewsRank(acc.getAccountType());
		int sug = 0;

		sug=newsService.insertSelective(news);

		if(sug==1){
			return new ModelAndView("redirect:/journalism?newsSort="+news.getNewsSort()+"&mark=1&organizationId="+news.getOrganizationId()+"&page=1&cj="+news.getNewsRank());
		} else {
			return new ModelAndView("redirect:/addnew");
		}
	}

	/**
	 * 提交修改新闻信息（后台）
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/editNewSava", method = RequestMethod.POST)
	public ModelAndView editNewSava(News news ,int mark) {
		int sug = 0;
		sug=newsService.updateByPrimaryKeySelective(news);

		if(sug==1){
			if(mark==1){
				return new ModelAndView("redirect:/journalism?newsSort="+news.getNewsSort()+"&mark=1&organizationId="+news.getOrganizationId()+"&page=1&cj="+news.getNewsRank());
			}else{
				return new ModelAndView("redirect:/collectionNewsAll?newsSort="+news.getNewsSort()+"&page=1&cj="+news.getNewsRank());
			}
			
		} else {
			return new ModelAndView("redirect:/addnew");
		}
	}

	/**
	 * 删除信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNew", method = RequestMethod.GET)
	public String deleteNew(String newsId) {
		Message msg = new Message();
		int sug = 0;
		if (newsId!=null) {
			sug = newsService.deleteByPrimaryKey(newsId);
		} else {
			msg.setMessage("删除失败");
			msg.setUri("addnew");
			return ("success");
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}

	/**
	 * 跳转新闻内容页面（预览新闻内容）
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value = "/newsOnes", method = RequestMethod.GET)
	public ModelAndView newsOnes(News news, Model model) {
		News newss = newsService.selectByPrimaryKey(news.getNewsId());
		model.addAttribute("news", newss);
		return new ModelAndView("backInformation/newsOnes");
	}
	/**
	 * 发布新闻（后台）
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishNews", method = RequestMethod.POST)
	public String publishNews(News news) {

		news.setNewsType("可用");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		news.setNewsTime(sdf.format(new Date()));
		try {
			int sd = newsService.updateByPrimaryKeySelective(news);
		} catch (Exception e) {
			return "";
		}
		return "ok";
	}
	/**
	 * 取消发布新闻（后台）
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelPublishNews", method = RequestMethod.POST)
	public String cancelPublishNews(News news) {

		news.setNewsType("不可用");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		news.setNewsTime(sdf.format(new Date()));
		try {
			int sd = newsService.updateByPrimaryKeySelective(news);
		} catch (Exception e) {
			return "";
		}
		return "ok";
	}
	
	
	
	/**
	 * 置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/stickNews")
	public ModelAndView stickNews(String id,int mark) {
		ModelAndView mv = new ModelAndView();
		news = newsService.selectByPrimaryKey(id);
		mv.addObject("news",news);
		mv.addObject("mark",mark);
		mv.setViewName("backInformation/stickNews");


		return mv;
	}

	/**
	 * 提交置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addStickNews")
	public ModelAndView addStickNews(News n,int mark) {
		ModelAndView mv = new ModelAndView();
		int i = newsService.updateByPrimaryKeySelective(n);

		if(i>0){ 
			if(mark==1){
				mv.setViewName("redirect:/journalism?newsSort="+news.getNewsSort()+"&mark=1&organizationId="+news.getOrganizationId()+"&page=1&cj="+news.getNewsRank());
			}else{
				mv.setViewName("redirect:/collectionNewsAll?newsSort="+news.getNewsSort()+"&page=1&cj="+news.getNewsRank());
			}
		}else{
			mv.addObject("notice",n);
			mv.setViewName("backInformation/stickNews");
		}



		return mv;
	}


	/**
	 * 添加收藏
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/collectionNews", method = RequestMethod.POST)
	public String collectionNews(News n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortId(Tools.getGUID());
		sort.setSortAt(new Date());
		sort.setSortDel(1);
		sort.setSortInfostate(1);
		sort.setSortInfoid(n.getNewsId());
		sort.setSortUserid(acc.getAccountId());
		List<Sort> slist = sortService.sortMyID(sort);
		if(slist.size()>0){
			return "";
		}else{
			int sd = sortService.insertSelective(sort);
			if(sd>0) {
				return "ok";
			}
			return "";
		}

	}


	/**
	 * 取消收藏
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/collectionDelNews", method = RequestMethod.POST)
	public String collectionDelNews(News n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortInfoid(n.getNewsId());
		sort.setSortUserid(acc.getAccountId());
		sort.setSortInfostate(1);
		List<Sort> slist = sortService.sortMyID(sort);
		sort = slist.get(0);

		int sd = sortService.deleteByPrimaryKey(sort.getSortId());
		if(sd>0) {
			return "ok";
		}
		return "";


	}


	/**
	 * 收藏通知查询
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/collectionNewsAll")
	public ModelAndView collectionNewsAll(News n,int cj,int page,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		n.setNewsSort(n.getNewsSort());
		Account acc = (Account)session.getAttribute("sessionUser");
		n.setOrganizationId(acc.getAccountId());
		PageBean pageBean = new PageBean();
		int allRow = newsService.collectionNewsCount(n);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		n.setPage(length*(currentPage1-1));
		n.setSize(length);

		List<News> list = newsService.collectionPageAll(n);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("collectionNewsAll?newsSort="+n.getNewsSort()+"&page=1&cj="+cj);//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("backInformation/collectionNews");
		return  mv;
	}
	//---------------------------------------------------------------------------党校新闻-------------------------------------------------------------------------
	//---------------------------------------------------------------------------党校新闻-------------------------------------------------------------------------
	//---------------------------------------------------------------------------党校新闻-------------------------------------------------------------------------
	//---------------------------------------------------------------------------党校新闻-------------------------------------------------------------------------
	//---------------------------------------------------------------------------党校新闻-------------------------------------------------------------------------
	
	
	
	
	/**
	 * 查询新闻（后台）
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/schooljournalism")
	public ModelAndView schooljournalism(News n,int mark ,int cj,int page,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			news =new News();
		}
		
		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){//集团账号
			news.setOrganizationId(n.getOrganizationId());
			news.setType(acc.getAccountType());
		}
		if(acc.getAccountType()==2){//院一级账号
			if(cj==1){//判断前台是否点击第1层
				news.setOrganizationId(acc.getAccountCatalog());
			}else{
				news.setOrganizationId(n.getOrganizationId());
			}
			news.setType(acc.getAccountType());

		}

		if(acc.getAccountType()==3){//院二级账号
			news.setOrganizationId(acc.getAccountCatalog());
			news.setType(acc.getAccountType());
		}

		news.setCj(cj);




		//---------------------条件查询----------------------------------
		if(n.getNewsRank()!=null && n.getNewsRank()!=0 ){
			news.setNewsRank(n.getNewsRank());
		}
		if(n.getNewsCatalog()!=null && n.getNewsCatalog()!="" ){
			news.setNewsCatalog(n.getNewsCatalog());
		}
		if(n.getNewsTime()!=null && n.getNewsTime()!="" ){
			news.setNewsTime(n.getNewsTime());
		}

		news.setNewsSort(n.getNewsSort());
		PageBean pageBean = new PageBean();
		int allRow = newsService.NewsCount(news);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		news.setPage(length*(currentPage1-1));
		news.setSize(length);

		List<News> list = newsService.selectPageAll(news);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("schooljournalism?newsSort="+n.getNewsSort()+"&mark=0&cj="+cj+"&organizationId="+n.getOrganizationId());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("news",news);
		mv.addObject("pageNewsId",n.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		if(news.getNewsSort().equals("3")){
			mv.setViewName("schoolNews/querynews");
		}else{
			mv.setViewName("news/queryschoolNews");
		}
		
		return  mv;
	}
	
	
	/**
	 * 党校修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/schoolnewsSkipID")
	public ModelAndView schoolnewsSkipID(String id,String newsType,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(newsType.equals("3")){
			if(id.equals("0")){
				mv.setViewName("schoolNews/addnew");
			}else{
				news = newsService.selectByPrimaryKey(id);
				mv.addObject("news",news);
				mv.addObject("mark",request.getParameter("mark"));
				mv.setViewName("schoolNews/editnew");
			}
		}else if(newsType.equals("4")){
			if(id.equals("0")){
				mv.setViewName("news/addschoolNew");
			}else{
				news = newsService.selectByPrimaryKey(id);
				mv.addObject("news",news);
				mv.addObject("mark",request.getParameter("mark"));
				mv.setViewName("news/updateschoolNew");
			}
		}
		
		return mv;
	}
	
	/**
	 * 提交新闻信息（后台）
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/schoolnewSava", method = RequestMethod.POST)
	public ModelAndView schoolnewSava(News news,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		news.setOrganizationId(acc.getAccountCatalog());
		news.setNewsRank(acc.getAccountType());
		int sug = 0;

		sug=newsService.insertSelective(news);

		if(sug==1){
			return new ModelAndView("redirect:/schooljournalism?newsSort="+news.getNewsSort()+"&mark=1&organizationId="+news.getOrganizationId()+"&page=1&cj="+news.getNewsRank());
		} else {
			return new ModelAndView("redirect:/addnew");
		}
	}

	/**
	 * 提交修改新闻信息（后台）
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/editschoolNewSava", method = RequestMethod.POST)
	public ModelAndView editschoolNewSava(News news ,int mark) {
		int sug = 0;
		sug=newsService.updateByPrimaryKeySelective(news);

		if(sug==1){
			if(mark==1){
				return new ModelAndView("redirect:/schooljournalism?newsSort="+news.getNewsSort()+"&mark=1&organizationId="+news.getOrganizationId()+"&page=1&cj="+news.getNewsRank());
			}else{
				return new ModelAndView("redirect:/collectionschoolNewsAll?newsSort="+news.getNewsSort()+"&page=1&cj="+news.getNewsRank());
			}
			
		} else {
			return new ModelAndView("redirect:/addnew");
		}
	}
	
	/**
	 * 删除信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteschoolNew", method = RequestMethod.GET)
	public String deleteschoolNew(String newsId) {
		Message msg = new Message();
		int sug = 0;
		if (newsId!=null) {
			sug = newsService.deleteByPrimaryKey(newsId);
		} else {
			msg.setMessage("删除失败");
			msg.setUri("addnew");
			return ("success");
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}

	/**
	 * 跳转新闻内容页面（预览新闻内容）
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value = "/newsschoolOnes", method = RequestMethod.GET)
	public ModelAndView newsschoolOnes(News news, Model model) {
		News newss = newsService.selectByPrimaryKey(news.getNewsId());
		model.addAttribute("news", newss);
		return new ModelAndView("schoolNews/newsOnes");
	}
	
	/**
	 * 发布新闻（后台）
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishschoolNews", method = RequestMethod.POST)
	public String publishschoolNews(News news) {

		news.setNewsType("可用");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		news.setNewsTime(sdf.format(new Date()));
		try {
			int sd = newsService.updateByPrimaryKeySelective(news);
		} catch (Exception e) {
			return "";
		}
		return "ok";
	}
	/**
	 * 取消发布新闻（后台）
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/cancelPublishschoolNews", method = RequestMethod.POST)
	public String cancelPublishschoolNews(News news) {

		news.setNewsType("不可用");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		news.setNewsTime(sdf.format(new Date()));
		try {
			int sd = newsService.updateByPrimaryKeySelective(news);
		} catch (Exception e) {
			return "";
		}
		return "ok";
	}
	
	
	
	/**
	 * 置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/stickschoolNews")
	public ModelAndView stickschoolNews(String id,int mark) {
		ModelAndView mv = new ModelAndView();
		news = newsService.selectByPrimaryKey(id);
		mv.addObject("news",news);
		mv.addObject("mark",mark);
		mv.setViewName("schoolNews/stickNews");


		return mv;
	}

	/**
	 * 提交置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addStickschoolNews")
	public ModelAndView addStickschoolNews(News n,int mark) {
		ModelAndView mv = new ModelAndView();
		int i = newsService.updateByPrimaryKeySelective(n);

		if(i>0){ 
			if(mark==1){
				mv.setViewName("redirect:/schooljournalism?newsSort="+news.getNewsSort()+"&mark=1&organizationId="+news.getOrganizationId()+"&page=1&cj="+news.getNewsRank());
			}else{
				mv.setViewName("redirect:/collectionschoolNewsAll?newsSort="+news.getNewsSort()+"&page=1&cj="+news.getNewsRank());
			}
		}else{
			mv.addObject("notice",n);
			mv.setViewName("schoolNews/stickNews");
		}



		return mv;
	}
	
	/**
	 * 添加收藏
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/collectionschoolNews", method = RequestMethod.POST)
	public String collectionschoolNews(News n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortId(Tools.getGUID());
		sort.setSortAt(new Date());
		sort.setSortDel(1);
		sort.setSortInfostate(1);
		sort.setSortInfoid(n.getNewsId());
		sort.setSortUserid(acc.getAccountId());
		List<Sort> slist = sortService.sortMyID(sort);
		if(slist.size()>0){
			return "";
		}else{
			int sd = sortService.insertSelective(sort);
			if(sd>0) {
				return "ok";
			}
			return "";
		}

	}


	/**
	 * 取消收藏
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/collectionDelschoolNews", method = RequestMethod.POST)
	public String collectionDelschoolNews(News n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortInfoid(n.getNewsId());
		sort.setSortUserid(acc.getAccountId());
		sort.setSortInfostate(1);
		List<Sort> slist = sortService.sortMyID(sort);
		sort = slist.get(0);

		int sd = sortService.deleteByPrimaryKey(sort.getSortId());
		if(sd>0) {
			return "ok";
		}
		return "";


	}


	/**
	 * 收藏通知查询
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/collectionschoolNewsAll")
	public ModelAndView collectionschoolNewsAll(News n,int cj,int page,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		n.setOrganizationId(acc.getAccountId());
		PageBean pageBean = new PageBean();
		int allRow = newsService.collectionNewsCount(n);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		n.setPage(length*(currentPage1-1));
		n.setSize(length);

		List<News> list = newsService.collectionPageAll(n);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("collectionschoolNewsAll?newsSort="+n.getNewsSort()+"&page=1&cj="+cj);//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("schoolNews/collectionNews");
		return  mv;
	}


	






	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	//--------------------------方法---------------------------------
	/***
	 * 上传图片
	 * 
	 * @param request
	 * @param mode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upplansort", method = RequestMethod.POST)
	public String uploadFile(HttpServletRequest request) {
//		String realPath = request.getSession().getServletContext()
//		.getRealPath("/UploadFile");
		String realPath = request.getParameter("paurl");
		File files = new File(realPath);
		// 一级一级创建文件夹
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory()) {
			System.out.println("//不存在");
			files.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		realPath = realPath + "\\YYZZ";
		File fileones = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!fileones.exists() && !fileones.isDirectory()) {
			System.out.println("//不存在");
			fileones.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		String fileName = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
			//			String filekey = set.getKey();// Filedata
			multipartFile = set.getValue();// 文件名
		}
		fileName = InformationController.storeIOc(realPath, multipartFile);
		//变化路径   格式化路径
		String realPaths = realPath + "\\" + fileName;
		System.out.println(realPaths);
		realPaths = realPaths.replace("\\", "/");
		System.out.println(realPaths);
//		String change = request.getSession().getServletContext().getRealPath("/");
		String change = request.getParameter("paurl1");
		change = change.replace("\\", "/");
		realPaths = realPaths.replace(change,"" );
		System.out.println(realPaths);
		return realPaths;
	}

	/***
	 * 编辑框上传
	 * 
	 * @param request
	 * @param mode
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/upplansort1", method = RequestMethod.POST)
	public String uploadFile1(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String realPath = request.getSession().getServletContext()
		.getRealPath("/UploadFile");
		File files = new File(realPath);
		// 一级一级创建文件夹
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory()) {
			System.out.println("//不存在");
			files.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		realPath = realPath + "\\YYZZ";
		File fileones = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!fileones.exists() && !fileones.isDirectory()) {
			System.out.println("//不存在");
			fileones.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		String fileName = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
			//			String filekey = set.getKey();// Filedata
			multipartFile = set.getValue();// 文件名
		}
		fileName = InformationController.storeIOc(realPath, multipartFile);
		//变化路径   格式化路径
		String realPaths = realPath + "\\" + fileName;
		System.out.println(realPaths);
		realPaths = realPaths.replace("\\", "/");
		System.out.println(realPaths);
		String change = request.getSession().getServletContext().getRealPath("/");
		change = change.replace("\\", "/");
		realPaths = "../"+realPaths.replace(change,"" );
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();  
		obj.put("error", 0);
		obj.put("url", realPaths);  
		out.print(obj.toString());  
		System.out.println(realPaths);
		return null;
	}

	/***
	 * 编辑框上传
	 * 
	 * @param request
	 * @param mode
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/upplansort3", method = RequestMethod.POST)
	public String uploadFile3(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String realPath = request.getSession().getServletContext()
		.getRealPath("/UploadFile");
		File files = new File(realPath);
		// 一级一级创建文件夹
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory()) {
			System.out.println("//不存在");
			files.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		realPath = realPath + "\\YYZZ";
		File fileones = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!fileones.exists() && !fileones.isDirectory()) {
			System.out.println("//不存在");
			fileones.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		String fileName = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
			//			String filekey = set.getKey();// Filedata
			multipartFile = set.getValue();// 文件名
		}
		fileName = InformationController.storeIOc(realPath, multipartFile);
		//变化路径   格式化路径
		String realPaths = realPath + "\\" + fileName;
		System.out.println(realPaths);
		realPaths = realPaths.replace("\\", "/");
		System.out.println(realPaths);
		String change = request.getSession().getServletContext().getRealPath("/");
		change = change.replace("\\", "/");
		realPaths = realPaths.replace(change,"" );
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();  
		obj.put("error", 0);
		obj.put("url", realPaths);  
		out.print(obj.toString());  
		System.out.println(realPaths);
		return null;
	}



	/***
	 * 编辑框附件上传
	 * 
	 * @param request
	 * @param mode
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/upplansort2", method = RequestMethod.POST)
	public String uploadFile2(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String realPath = request.getSession().getServletContext()
		.getRealPath("/UploadFile");
		File files = new File(realPath);
		// 一级一级创建文件夹
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory()) {
			System.out.println("//不存在");
			files.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		realPath = realPath + "\\YYZZ";
		File fileones = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!fileones.exists() && !fileones.isDirectory()) {
			System.out.println("//不存在");
			fileones.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		//		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		String fileName = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
			//			String filekey = set.getKey();// Filedata
			multipartFile = set.getValue();// 文件名
		}
		fileName = InformationController.storeIOc(realPath, multipartFile);
		//变化路径   格式化路径
		String realPaths = realPath + "\\" + fileName;
		System.out.println(realPaths);
		realPaths = realPaths.replace("\\", "/");
		System.out.println(realPaths);
		String change = request.getSession().getServletContext().getRealPath("/");
		change = change.replace("\\", "/");
		realPaths = "../"+realPaths.replace(change,"" );
		String fileExt = realPaths.substring(realPaths.lastIndexOf(".") + 1).toLowerCase();
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();  
		obj.put("error", 0);
		obj.put("ext", fileExt);
		obj.put("title", request.getParameter("title"));  
		obj.put("url", realPaths);  
		out.print(obj.toString());  
		System.out.println(realPaths);
		return null;
	}

	/***
	 * 编辑框附件上传(带路径)
	 * 
	 * @param request
	 * @param mode
	 * @return
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping(value = "/upplansort4", method = RequestMethod.POST)
	public String uploadFile4(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String realPath = request.getSession().getServletContext()
		.getRealPath("/UploadFile");
		File files = new File(realPath);
		// 一级一级创建文件夹
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory()) {
			System.out.println("//不存在");
			files.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		realPath = realPath + "\\YYZZ";
		File fileones = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!fileones.exists() && !fileones.isDirectory()) {
			System.out.println("//不存在");
			fileones.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		//		String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		String fileName = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
			//			String filekey = set.getKey();// Filedata
			multipartFile = set.getValue();// 文件名
		}
		fileName = InformationController.storeIOc(realPath, multipartFile);
		//变化路径   格式化路径
		String realPaths = realPath + "\\" + fileName;
		System.out.println(realPaths);
		realPaths = realPaths.replace("\\", "/");
		System.out.println(realPaths);
		String change = request.getSession().getServletContext().getRealPath("/");
		change = change.replace("\\", "/");
		realPaths = realPaths.replace(change,"" );
		String fileExt = realPaths.substring(realPaths.lastIndexOf(".") + 1).toLowerCase();
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();  
		obj.put("error", 0);
		obj.put("ext", fileExt);
		obj.put("title", request.getParameter("title"));  
		obj.put("url", realPaths);  
		out.print(obj.toString());  
		System.out.println(realPaths);
		return null;
	}


	/***
	 * 保存图片，返回图片地址
	 * 
	 * @param realPath
	 * @param file
	 * @return
	 */
	public static String storeIOc(String realPath, MultipartFile file)
	{
		File files = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory())
		{
			System.out.println("//不存在");
			files.mkdir();
		} else
		{
			System.out.println("//目录存在");
		}
		// 文件名称
		String fileName = "";
		String logImageName = "";
		// file调用getOriginalFilename获取完成文件名
		String _fileName = file.getOriginalFilename();
		// 用文件名截取最后一个.后面的字符当作扩展名
		String suffix = _fileName.substring(_fileName.lastIndexOf("."));
		/** 使用UUID生成文件名称 **/
		logImageName = UUID.randomUUID().toString().replace("-", "") + suffix;

		fileName = realPath + File.separator + logImageName;
		File restore = new File(fileName);
		try
		{
			file.transferTo(restore);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		// 返回默认的图片地址
		return logImageName;
	}


}
