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
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Programme;
import com.ttgis.education.entity.Sort;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.ProgrammeService;
import com.ttgis.education.service.SortService;
import com.ttgis.education.utils.Tools;

/**
 * 计划模块
 *@author 陈健龙
 */

@Controller
public class ProgrammeController {

	@Resource
	private ProgrammeService programmeService;

	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private SortService sortService;


	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(ProgrammeController.class);

	private Programme programme;
	/**
	 * 查询计划
	 * @return
	 */
	@RequestMapping(value = "/programmeAll")
	public ModelAndView programmeAll(Programme p,int mark ,int cj,int page ,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			programme =new Programme();
		}

		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){//集团账号
			programme.setOrganizationId(p.getOrganizationId());
			programme.setType(acc.getAccountType());
		}
		if(acc.getAccountType()==2){//院一级账号
			if(cj==1){//判断前台是否点击第1层
				programme.setOrganizationId(acc.getAccountCatalog());
			}else{
				programme.setOrganizationId(p.getOrganizationId());
			}
			programme.setType(acc.getAccountType());

		}

		if(acc.getAccountType()==3){//院二级账号
			programme.setOrganizationId(acc.getAccountCatalog());
			programme.setType(acc.getAccountType());
		}

		programme.setCj(cj);




		//---------------------条件查询----------------------------------
		if(p.getProgrammeRank()!=null && p.getProgrammeRank()!=0 ){
			programme.setProgrammeRank(p.getProgrammeRank());
		}
		if(p.getProgrammeCatalog()!=null && p.getProgrammeCatalog()!="" ){
			programme.setProgrammeCatalog(p.getProgrammeCatalog());
		}
		if(p.getProgrammeTime()!=null && p.getProgrammeTime()!="" ){
			programme.setProgrammeTime(p.getProgrammeTime());
		}
		
		programme.setProgrammeSort(p.getProgrammeSort());
		PageBean pageBean = new PageBean();
		int allRow = programmeService.ProgrammeCount(programme);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		programme.setPage(length*(currentPage1-1));
		programme.setSize(length);

		List<Programme> list = programmeService.selectPageAll(programme);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("programmeAll?programmeSort="+programme.getProgrammeSort()+"&mark=0&cj="+cj+"&organizationId="+p.getOrganizationId());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("programme",programme);
		mv.addObject("pageNewsId",p.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("programme/queryProgramme");
		return  mv;
	}

	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/programmeskipID")
	public ModelAndView programmeskipID(String id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("programmeSort",request.getParameter("programmeSort"));
		Programme p = new Programme();
		if(id.equals("0")){
			mv.setViewName("programme/addProgramme");
		}else{
			p.setProgrammeId(id);
			programme = programmeService.ProgrammeById(p);
			mv.addObject("programme",programme);
			mv.addObject("mark",request.getParameter("mark"));
			mv.setViewName("programme/updateProgramme");
		}

		return mv;
	}

	/**
	 * 添加计划信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/createProgramme")
	public ModelAndView createProgramme(Programme p,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		p.setOrganizationId(acc.getAccountCatalog());
		p.setProgrammeRank(acc.getAccountType());
		Organization company = organizationService.selectByPrimaryKey(p.getOrganizationId());//单位名称和代码号
		Programme pr = programmeService.ProgrammeByOId(p);
		//时间转换
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		if(pr!=null){
			String[] num = pr.getProgrammeId().split("-");
			p.setProgrammeId("JH"+company.getOrganizationDwdm()+"-"+(Integer.parseInt(num[1])+1));	
		}else{
			p.setProgrammeId("JH"+company.getOrganizationDwdm()+"-1");	
		}
		p.setProgrammeCatalog(company.getOrganizationDwmc());
		p.setProgrammeType("不可用");
		p.setProgrammeDel(1);
		p.setProgrammeAt(time.format(nowTime).toString());
		int i = programmeService.insertSelective(p);
		if(i>0){
			mv.setViewName("redirect:programmeAll?programmeSort="+p.getProgrammeSort()+"&mark=1&organizationId="+p.getOrganizationId()+"&page=1&cj="+p.getProgrammeRank());
		}else{
			mv.addObject("pageNewsId",p.getOrganizationId());
			mv.setViewName("programme/addProgramme");
		}


		return mv;
	}


	/**
	 * 删除信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteProgramme", method = RequestMethod.GET)
	public String deleteProgramme(String id) {
		Message msg = new Message();
		int sug = 0;
		if (id!=null) {
			sug = programmeService.deleteByPrimaryKey(id);
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
	 * 发布计划
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishProgramme", method = RequestMethod.POST)
	public String publishProgramme(Programme p) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		p.setProgrammeTime(sdf.format(new Date()));

		int sd = programmeService.updateByPrimaryKeySelective(p);
		if(sd>0) {
			return "ok";
		}
		return "";
	}


	/**
	 * 详细页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryProgramme")
	public ModelAndView queryProgramme(String id) {
		ModelAndView mv = new ModelAndView();
		Programme p = new Programme();

		p.setProgrammeId(id);
		p = programmeService.ProgrammeById(p);
		mv.addObject("programme",p);
		mv.setViewName("programme/ProgrammeOnes");


		return mv;
	}


	/**
	 * 修改计划信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateProgramme")
	public ModelAndView updateProgramme(Programme p,int mark) {
		ModelAndView mv = new ModelAndView();
		programme.setProgrammeName(p.getProgrammeName());
		programme.setProgrammeContent(p.getProgrammeContent());
		programme.setProgrammeProducer(p.getProgrammeProducer());
		programme.setProgrammeImage(p.getProgrammeImage());
		int i = programmeService.updateByPrimaryKeySelective(programme);
		if(i>0){ 
			if(mark==1){
				mv.setViewName("redirect:programmeAll?programmeSort="+programme.getProgrammeSort()+"&mark=1&organizationId="+programme.getOrganizationId()+"&page=1&cj="+programme.getProgrammeRank());
			}else{
				mv.setViewName("redirect:collectionProgrammeAll?programmeSort="+programme.getProgrammeSort()+"&cj="+programme.getProgrammeRank()+"&page=1");
			}
		}else{
			mv.addObject("programme",p);
			mv.setViewName("programme/updateProgramme");
		}


		return mv;
	}
	
	
	
	/**
	 * 置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/stickProgramme")
	public ModelAndView stickProgramme(String id,int mark) {
		ModelAndView mv = new ModelAndView();
		Programme n = new Programme();

		n.setProgrammeId(id);
		programme = programmeService.ProgrammeById(n);
		mv.addObject("programme",programme);
		mv.addObject("mark",mark);
		mv.setViewName("programme/stickProgramme");


		return mv;
	}

	/**
	 * 提交置顶跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addStickProgramme")
	public ModelAndView addStickProgramme(Programme n,int mark) {
		ModelAndView mv = new ModelAndView();
		int i = programmeService.updateByPrimaryKeySelective(n);

		if(i>0){ 
			if(mark==1){
				mv.setViewName("redirect:programmeAll?programmeSort="+n.getProgrammeSort()+"&mark=1&organizationId="+n.getOrganizationId()+"&page=1&cj="+n.getProgrammeRank());
			}else{
				mv.setViewName("redirect:collectionProgrammeAll?programmeSort="+n.getProgrammeSort()+"&cj="+n.getProgrammeRank()+"&page=1");
			}
			
		}else{
			mv.addObject("programme",n);
			mv.setViewName("programme/stickProgramme");
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
	@RequestMapping(value = "/collectionProgramme", method = RequestMethod.POST)
	public String collectionProgramme(Programme n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortId(Tools.getGUID());
		sort.setSortAt(new Date());
		sort.setSortDel(1);
		sort.setSortInfostate(3);
		sort.setSortInfoid(n.getProgrammeId());
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
	@RequestMapping(value = "/collectionDelProgramme", method = RequestMethod.POST)
	public String collectionDelProgramme(Programme n,HttpSession session) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortInfoid(n.getProgrammeId());
		sort.setSortUserid(acc.getAccountId());
		sort.setSortInfostate(3);
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
	@RequestMapping(value = "/collectionProgrammeAll")
	public ModelAndView collectionProgrammeAll(Programme n,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();

		Account acc = (Account)session.getAttribute("sessionUser");
		n.setOrganizationId(acc.getAccountId());
		PageBean pageBean = new PageBean();
		int allRow = programmeService.collectionProgrammeCount(n);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		n.setPage(length*(currentPage1-1));
		n.setSize(length);

		List<Programme> list = programmeService.collectionPageAll(n);

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("collectionProgrammeAll?programmeSort="+n.getProgrammeSort()+"&cj="+cj);//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("programme",n);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("programme/collectionProgramme");
		return  mv;
	}





}
