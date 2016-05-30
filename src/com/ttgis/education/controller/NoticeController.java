package com.ttgis.education.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Sort;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.NoticeService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.SortService;
import com.ttgis.education.utils.Tools;

/**
 * 通知模块
 *@author 陈健龙
 */

@Controller
public class NoticeController {

	@Resource
	private NoticeService noticeService;

	@Resource
	private OrganizationService organizationService;

	@Resource
	private SortService sortService;


	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(NoticeController.class);

	private Notice notice;
	/**
	 * 查询通知
	 * @return
	 */
	@RequestMapping(value = "/NoticeAll")
	public ModelAndView NoticeAll(Notice n,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			notice =new Notice();
		}

		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){//集团账号
			notice.setOrganizationId(n.getOrganizationId());
			notice.setType(acc.getAccountType());
		}
		if(acc.getAccountType()==2){//院一级账号
			if(cj==1){//判断前台是否点击第1层
				notice.setOrganizationId(acc.getAccountCatalog());
			}else{
				notice.setOrganizationId(n.getOrganizationId());
			}
			notice.setType(acc.getAccountType());

		}

		if(acc.getAccountType()==3){//院二级账号
			notice.setOrganizationId(acc.getAccountCatalog());
			notice.setType(acc.getAccountType());
		}

		notice.setCj(cj);




		//---------------------条件查询----------------------------------
		if(n.getNoticeRank()!=null && n.getNoticeRank()!=0 ){
			notice.setNoticeRank(n.getNoticeRank());
		}
		if(n.getNoticeCatalog()!=null && n.getNoticeCatalog()!="" ){
			notice.setNoticeCatalog(n.getNoticeCatalog());
		}
		if(n.getNoticeTime()!=null && n.getNoticeTime()!="" ){
			notice.setNoticeTime(n.getNoticeTime());
		}

		notice.setNoticeSort(n.getNoticeSort());
		PageBean pageBean = new PageBean();
		int allRow = noticeService.NoticeCount(notice);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		notice.setPage(length*(currentPage1-1));
		notice.setSize(length);

		List<Notice> list = noticeService.selectPageAll(notice);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("NoticeAll?noticeSort="+n.getNoticeSort()+"&mark=0&cj="+cj+"&organizationId="+n.getOrganizationId());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("notice",notice);
		mv.addObject("pageNewsId",n.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("notice/queryNotice");
		return  mv;
	}

	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/skipID")
	public ModelAndView skipID(String id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Notice n = new Notice();
		mv.addObject("noticeSort",request.getParameter("noticeSort"));
		if(id.equals("0")){
			mv.setViewName("notice/addNotice");
		}else{
			n.setNoticeId(id);
			notice = noticeService.NoticeById(n);
			mv.addObject("notice",notice);
			mv.addObject("mark",request.getParameter("mark"));
			mv.setViewName("notice/updateNotice");
		}

		return mv;
	}

	/**
	 * 添加通知信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/createNotice")
	public ModelAndView createNotice(Notice n ,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		n.setOrganizationId(acc.getAccountCatalog());
		n.setNoticeRank(acc.getAccountType());
		Organization company = organizationService.selectByPrimaryKey(n.getOrganizationId());//单位名称和代码号
		Notice nr = noticeService.NoticeByOId(n);
		//时间转换
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if(nr!=null){
			String[] num = nr.getNoticeId().split("-");
			n.setNoticeId("JH"+company.getOrganizationDwdm()+"-"+(Integer.parseInt(num[1])+1));	
		}else{
			n.setNoticeId("JH"+company.getOrganizationDwdm()+"-1");	
		}
		n.setNoticeCatalog(company.getOrganizationDwmc());
		n.setNoticeType("不可用");
		n.setNoticeDel(1);
		n.setNoticeAt(time.format(nowTime).toString());
		int i = noticeService.insertSelective(n);
		if(i>0){
			mv.setViewName("redirect:NoticeAll?mark=1&noticeSort="+n.getNoticeSort()+"&organizationId="+n.getOrganizationId()+"&page=1&cj="+n.getNoticeRank());
		}else{
			mv.addObject("pageNewsId",n.getOrganizationId());
			mv.setViewName("notice/addNotice");
		}


		return mv;
	}

	/**
	 * 详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryNotice")
	public ModelAndView queryNotice(String id) {
		ModelAndView mv = new ModelAndView();
		Notice n = new Notice();

		n.setNoticeId(id);
		n = noticeService.NoticeById(n);
		mv.addObject("notice",n);
		mv.setViewName("notice/NoticeOnes");


		return mv;
	}

	/**
	 * 发布通知
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishNotice", method = RequestMethod.POST)
	public String publishNotice(Notice n) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		n.setNoticeTime(sdf.format(new Date()));

		int sd = noticeService.updateByPrimaryKeySelective(n);
		if(sd>0) {
			return "ok";
		}
		return "";
	}

	/**
	 * 删除信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteNotice", method = RequestMethod.GET)
	public String deleteNotice(String id) {
		Message msg = new Message();
		int sug = 0;
		if (id!=null) {
			sug = noticeService.deleteByPrimaryKey(id);
		} else {
			msg.setMessage("删除失败");
			return ("success");
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}

	/**
	 * 修改通知信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateNotice")
	public ModelAndView updateNotice(Notice n,int mark) {
		ModelAndView mv = new ModelAndView();
		notice.setNoticeName(n.getNoticeName());
		notice.setNoticeContent(n.getNoticeContent());
		notice.setNoticeProducer(n.getNoticeProducer());
		notice.setNoticeImage(n.getNoticeImage());
		int i = noticeService.updateByPrimaryKeySelective(notice);
		if(i>0){ 
			if(mark==1){
				mv.setViewName("redirect:NoticeAll?mark=1&noticeSort="+notice.getNoticeSort()+"&organizationId="+notice.getOrganizationId()+"&page=1&cj="+notice.getNoticeRank());
			}else{
				mv.setViewName("redirect:collectionNoticeAll?cj="+notice.getNoticeRank()+"&page=1&noticeSort="+notice.getNoticeSort());
			}
			
		}else{
			mv.addObject("notice",n);
			mv.setViewName("notice/updateNotice");
		}


		return mv;
	}


	/**
	 * 置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/stickNotice")
	public ModelAndView stickNotice(String id,int mark) {
		ModelAndView mv = new ModelAndView();
		Notice n = new Notice();

		n.setNoticeId(id);
		notice = noticeService.NoticeById(n);
		mv.addObject("notice",notice);
		mv.addObject("mark",mark);
		mv.setViewName("notice/stickNotice");


		return mv;
	}

	/**
	 * 提交置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addStickNotice")
	public ModelAndView addStickNotice(Notice n,int mark) {
		ModelAndView mv = new ModelAndView();
		int i = noticeService.updateByPrimaryKeySelective(n);

		if(i>0){ 
			if(mark==1){
				mv.setViewName("redirect:NoticeAll?mark=1&noticeSort="+n.getNoticeSort()+"&organizationId="+n.getOrganizationId()+"&page=1&cj="+n.getNoticeRank());
			}else{
				mv.setViewName("redirect:collectionNoticeAll?cj="+n.getNoticeRank()+"&page=1&noticeSort="+n.getNoticeSort());
			}
		}else{
			mv.addObject("notice",n);
			mv.setViewName("notice/stickNotice");
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
	@RequestMapping(value = "/collectionNotice", method = RequestMethod.POST)
	public String collectionNotice(Notice n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortId(Tools.getGUID());
		sort.setSortAt(new Date());
		sort.setSortDel(1);
		sort.setSortInfostate(2);
		sort.setSortInfoid(n.getNoticeId());
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
	@RequestMapping(value = "/collectionDelNotice", method = RequestMethod.POST)
	public String collectionDelNotice(Notice n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortInfoid(n.getNoticeId());
		sort.setSortUserid(acc.getAccountId());
		sort.setSortInfostate(2);
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
	 */
	@RequestMapping(value = "/collectionNoticeAll")
	public ModelAndView collectionNoticeAll(Notice n,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();

		Account acc = (Account)session.getAttribute("sessionUser");
		n.setOrganizationId(acc.getAccountId());
		PageBean pageBean = new PageBean();
		int allRow = noticeService.collectionNoticeCount(n);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		n.setPage(length*(currentPage1-1));
		n.setSize(length);

		List<Notice> list = noticeService.collectionPageAll(n);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("collectionNoticeAll?noticeSort="+n.getNoticeSort()+"&cj="+cj);//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("pageBean",pageBean);
		mv.addObject("notice",n);
		mv.setViewName("notice/collectionNotice");
		return  mv;
	}



	



}
