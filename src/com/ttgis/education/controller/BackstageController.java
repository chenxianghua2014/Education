package com.ttgis.education.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Msg;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Shift;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.CoursePage;
import com.ttgis.education.service.AccountService;
import com.ttgis.education.service.ClassresourceService;
import com.ttgis.education.service.LogService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.ShiftService;
import com.ttgis.education.service.TranclassService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.MD5;
import com.ttgis.education.utils.Tools;


@Controller
public class BackstageController {
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private ShiftService shiftService;
	@Resource
	private TranclassService tranclassService;
	@Resource
	private ClassresourceService classresourceService;
	@Resource
	LogService logService;
	Log log = new Log();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(BackstageController.class);

	/**
	 * 后台用户登陆
	 * 根据账号会出现四中权限
	 * 系统管理员、集团级培训专员、院级培训专员、培训机构
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	public Msg loginForm(Account account,HttpSession session,HttpServletRequest request){
		Msg msg = new Msg();
		account.setAccountPassword(MD5.getMD5ofStr(account.getAccountPassword()));
		Account acc = accountService.checkLogin(account);
		String ss= (String) session.getAttribute("rand");
		ss = ss.toLowerCase();
		String ss1 = request.getParameter("code");
		ss1 = ss1.toLowerCase();
		if(ss.equals(ss1)){
			if(acc == null){
				msg.setHead("登录失败");
				msg.setMsgBody("用户名或密码错误");
				msg.setStatus(Msg.ERROR);
				return msg;
			}
			if(acc.getAccountType() != 0){
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+acc.getAccountLoginid()+"，登陆。");
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(acc.getAccountLoginid());
				log.setLogUserType("后台用户");
				logService.insertSelective(log);
				
				msg.setHead("登录成功");
				msg.setMsgBody(acc.getAccountType());
				session.setAttribute(Const.SESSION_USER, acc);
				msg.setStatus(Msg.SUCCESS);
				return msg;
			}
		}else{
			msg.setHead("登录失败");
			msg.setMsgBody("验证码错误");
			msg.setStatus(Msg.ERROR);
			return msg;
		}
		
		return null;
	}
	/**
	 * 跳转到后台首页
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(Model model,HttpSession session) {
		Account account = (Account)session.getAttribute(Const.SESSION_USER);
		if(account.getAccountType()==4){
		Shift shift=new Shift();
		 Classresource cla=new Classresource();
		shift.setAccountId(account.getAccountId());
		List<Shift> ft = shiftService.ShiftByOId(shift);
		   for(int i = 0; i < 1; i++)  
	        {  
			    ft.get(i);  
	            Tranclass clas = tranclassService.selectByPrimaryKey(ft.get(i).getTranclassId());
	            cla = classresourceService.selectByPrimaryKey(clas.getClassresourceId());
	        } 
		   model.addAttribute("cla", cla);
		}
		model.addAttribute("account", account);
		
		return new ModelAndView("backstage/index");
	}
	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session,HttpServletRequest request) {
		Account acc = (Account)session.getAttribute("sessionUser");
		log.setLogAt(sdf.format(new Date()));
		log.setLogContent("用户："+acc.getAccountLoginid()+"，登出。");
		log.setLogId(Tools.getGUID());
		log.setLogIp(HYLUtil.getIP4(request));
		log.setLogUser(acc.getAccountLoginid());
		log.setLogUserType("后台用户");
		logService.insertSelective(log);
		session.removeValue("sessionUser");
		return "redirect:login";
	}
	
	
	
	/**
	 * 跳转系统管理树
	 * @return
	 */
	@RequestMapping(value = "/adminTree", method = RequestMethod.GET)
	public ModelAndView adminTree(){
		return new ModelAndView("tree/adminTree");
	}
	/**
	 * 查询组织架构
	 * @return
	 */
	@RequestMapping(value = "/OrganizationalStructure", method = RequestMethod.GET)
	public ModelAndView OrganizationalStructure(CoursePage page,Model model){
		
		page.setPageSize(15);
	 List<Organization> organizationList = organizationService.selectCourseBySyllabusId(page);
		int rows = organizationService.selectCourseBySyllabusIdRows(page);
		page.setRows(rows);
		model.addAttribute("coursePage", page);
		model.addAttribute("organizationList", organizationList);
		return new ModelAndView("adminTree/organization");
	}
	/**
	 * 查询后台账号
	 * @return
	 */
//	@RequestMapping(value = "/backgroundAccount", method = RequestMethod.GET)
//	public ModelAndView backgroundAccount(CoursePage page,Model model){               
//		
//		page.setPageSize(15);
//	 List<Organization> organizationList = organizationService.selectCourseBySyllabusId(page);
//		int rows = organizationService.selectCourseBySyllabusIdRows(page);
//		page.setRows(rows);
//		model.addAttribute("coursePage", page);
//		model.addAttribute("organizationList", organizationList);
//		return new ModelAndView("adminTree/organization");
//	}
	
	
	/**
	 * 后台登陆接口
	 * 根据账号会出现四中权限
	 * 系统管理员、集团级培训专员、院级培训专员、培训机构
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/loginInterface", method = RequestMethod.GET)
	public ModelAndView loginInterface(Account account,HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Account acc = accountService.AccountBySFZH(account.getAccountSfzh());
			if(acc != null){
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+acc.getAccountLoginid()+"，接口登陆。");
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(acc.getAccountLoginid());
				log.setLogUserType("后台用户");
				logService.insertSelective(log);
				
				session.setAttribute(Const.SESSION_USER, acc);
				mv.setViewName("redirect:index");
			}else{
				mv.setViewName("redirect:login");
			}
		return mv;
	}
	
	
}
