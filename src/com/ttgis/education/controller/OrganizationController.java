package com.ttgis.education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Department;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.AccountService;
import com.ttgis.education.service.DepartmentService;
import com.ttgis.education.service.LogService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.Tools;
/**
 * 组织结构管理控制层
 * @author 陈健龙
 *
 */
@Controller
public class OrganizationController {
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private StudentService studentService;
	
	private Organization organization;
	
	@Resource
	LogService logService;
	Log log = new Log();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(OrganizationController.class);
	
	
	/**
	 * 组织架构树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/accountTree", method = RequestMethod.GET)
	public ModelAndView accountTree(Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		String oid = "";
		if(account.getAccountType()==5){//系统管理员账号
			oid = "test001";
		}else{
			oid = account.getAccountCatalog();
		}
		
		Organization oneid = organizationService.selectByPrimaryKey(oid);
		List<Organization> organization = organizationService.selectAllOrganization();
		model.addAttribute("organization", organization);
		model.addAttribute("account", account);
		model.addAttribute("oneid", oneid);
		return new ModelAndView("organization/userLeft");
	}
	
	/**
	 * 组织结构查询
	 * @return
	 */
	@RequestMapping(value = "/organizationAll")
	public ModelAndView organizationAll(int mark ,Organization o,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		Organization ou =new Organization();
		//--------------------------------------判定处理---------------------------------------
		if(mark==1){
			organization = new Organization();
		}

		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==2){
			ou.setOrganizationId(acc.getAccountCatalog());
			ou.setOrganizationOrder(acc.getAccountType());
			organization.setOrganizationSjdw(acc.getAccountCatalog());
		}else{//集团或超管加入下拉查询
			ou.setOrganizationId("test001");
			ou.setOrganizationOrder(acc.getAccountType());
			//下拉判定------------------------------
			if(o.getOrganizationSjdw()!=null && o.getOrganizationSjdw()!=""){
				organization.setOrganizationSjdw(o.getOrganizationSjdw());
			}
		}
		
		List<Organization> listtype = organizationService.organizationByID(ou);
		mv.addObject("listtype",listtype);
		
		if(o.getOrganizationDwmc()!=null && o.getOrganizationDwmc()!=""){
			organization.setOrganizationDwmc(o.getOrganizationDwmc());
		}
		
		//--------------------------------分页----------------------------------------------------------
		PageBean pageBean = new PageBean();
		int allRow = organizationService.OrganizationCount(organization);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		organization.setPage(length*(currentPage1-1));
		organization.setSize(length);

		List<Organization> list = organizationService.selectPageAll(organization);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("organizationAll?mark=0");//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.addObject("organization",o);
		mv.setViewName("organization/queryOrganization");
		return  mv;
	}
	
	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/OrganizationskipID")
	public ModelAndView AccountskipID(String id,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Organization p = new Organization();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		
		if(id.equals("0")){
			if(acc.getAccountType()!=2){
				p.setOrganizationId("test001");
				p.setOrganizationOrder(acc.getAccountType());
			}
			List<Organization> listtype = organizationService.organizationByID(p);
			mv.addObject("listtype",listtype);
			mv.setViewName("organization/addOrganization");
		}else{
			p.setOrganizationId(id);
			p = organizationService.organizationMyID(p);
			mv.addObject("organization",p);
			mv.setViewName("organization/updateOrganization");
		}

		return mv;
	}
	
	/**
	 * 添加组织结构信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/createOrganization")
	public ModelAndView createAccount(Organization p,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//时间转换
		p.setOrganizationAt(new Date());
		p.setOrganizationId(Tools.getGUID());
		p.setOrganizationDel(1);
		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==2){
			p.setOrganizationSjdw(acc.getAccountCatalog());
		}
		if(p.getOrganizationSjdw().equals("test001")){
			p.setOrganizationFwqx("2");
		}else{
			p.setOrganizationFwqx("3");
		}
		int i = organizationService.insertSelective(p);
		if(i>0){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，创建组织:"+p.getOrganizationId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			mv.setViewName("redirect:accountTree");
		}else{
			mv.setViewName("organization/addOrganization");
		}


		return mv;
	}
	
	/**
	 * 修改组织结构信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateOrganization")
	public ModelAndView updateOrganization(Organization a ,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		int i = organizationService.updateByPrimaryKeySelective(a);
		if(i>0){ 
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，修改组织:"+a.getOrganizationId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			mv.setViewName("redirect:accountTree");
		}else{
			mv.addObject("organization",a);
			mv.setViewName("organization/updateOrganization");
		}


		return mv;
	}
	
	
	/**
	 * 提升
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/promotionOrganization", method = RequestMethod.GET)
	public String promotionOrganization(String id,HttpSession session,HttpServletRequest request) {
		Message msg = new Message();
		Account ac = (Account)session.getAttribute("sessionUser");
		Organization o = new Organization();
		o.setOrganizationSjdw("test001");
		o.setOrganizationFwqx("2");
		o.setOrganizationId(id);
		int sug = 0;
		if (id!=null) {
			sug = organizationService.updateByPrimaryKeySelective(o);
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+ac.getAccountLoginid()+"，提升组织:"+id);
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(ac.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			Account acc = new Account();
			acc.setAccountCatalog(id);
			accountService.updateAccountByOId(acc);
		} else {
			msg.setMessage("提升失败");
			return ("success");
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}
	
	
	/**
	 * 合并跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/organizationMerger")
	public ModelAndView organizationMerger(String id,HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("organization/mergerOrganization");
		return mv;
	}
	
	/**
	 * 合并组织机构信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateOrganizationMerger")
	public ModelAndView updateOrganizationMerger(Organization p,HttpSession session,HttpServletRequest request,String hbbmid) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		String rank = request.getParameter("epartmentRank");//层级
		String mb = request.getParameter("organizationUpid");//目标组织ID
		String br = request.getParameter("broid");//并入组织ID
		
		p.setOrganizationAt(new Date());
		p.setOrganizationId(Tools.getGUID());
		p.setOrganizationDel(1);
		if(rank.equals("4")){//合并二级组织
			p.setOrganizationSjdw("test001");
			p.setOrganizationFwqx("2");
		}else{
			p.setOrganizationFwqx("3");
			p.setOrganizationSjdw(request.getParameter("typeone"));
		}
		
		int i=organizationService.insertSelective(p);
		if(i>0){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，合并组织:"+mb+"与"+br+"合并后id:"+p.getOrganizationId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
		}
		Organization o = new Organization();
		Department d = new Department();
		Student s = new Student();
		Account a = new Account();
		//组织1处理
		a.setAccountCatalog(p.getOrganizationId());
		a.setAccountId(br);
		s.setDepartmentId(br);
		s.setStudentCompanyid(p.getOrganizationId());
		d.setOrganizationId(p.getOrganizationId());
		d.setDepartmentId(br);
		o.setOrganizationId(br);
		o.setOrganizationSjdw(p.getOrganizationId());
		if(rank.equals("4")){//合并二级组织
			//修改二级组织下的三级组织的上级ID
			organizationService.updateHB(o);
		}
		//修改部门和科室的组织ID
		departmentService.updateOid(d);
		//修改学员的组织ID
		studentService.updateHBOid(s);
		//后台账号的组织ID
		accountService.updateOId(a);
		//删除并入的组织
		o.setOrganizationDel(0);
		organizationService.updateByPrimaryKeySelective(o);
		
		
		
		//组织2处理
		a.setAccountCatalog(p.getOrganizationId());
		a.setAccountId(mb);
		s.setDepartmentId(mb);
		s.setStudentCompanyid(p.getOrganizationId());
		d.setOrganizationId(p.getOrganizationId());
		d.setDepartmentId(mb);
		o.setOrganizationId(mb);
		o.setOrganizationSjdw(p.getOrganizationId());
		if(rank.equals("4")){//合并二级组织
			//修改二级组织下的三级组织的上级ID
			organizationService.updateHB(o);
		}
		//修改部门和科室的组织ID
		departmentService.updateOid(d);
		//修改学员的组织ID
		studentService.updateHBOid(s);
		//后台账号的组织ID
		accountService.updateOId(a);
		//删除并入的组织
		o.setOrganizationDel(0);
		organizationService.updateByPrimaryKeySelective(o);
		
		

		mv.setViewName("redirect:accountTree");
		


		return mv;
	}
	
	
	
	/**
	 * 排序跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/organizationSort")
	public ModelAndView organizationSort(String id,HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("organization/sortOrganization");
		return mv;
	}
	
	/**
	 * 排序修改
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateOrganizationSort")
	public ModelAndView updateOrganizationSort(Organization p) {
		ModelAndView mv = new ModelAndView();
		for(int i = 0 ;i<p.getOidall().length ;i++){
			Organization o = new Organization();
			o.setOrganizationId(p.getOidall()[i]);
			o.setCpcs(p.getOidsort()[i]);
			organizationService.updateByPrimaryKeySelective(o);
		}
		
		mv.setViewName("redirect:accountTree");
		return mv;
	}
	
	
	
	
	
	

	/**
	 * 合并二级组织联动
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/HBOrganization",method=RequestMethod.POST)
	public ModelAndView HBOrganization(HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<Organization> list = organizationService.organizationSJID("test001");
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);


		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
	
	

	/**
	 * 合并三级组织联动
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/HBSOrganization",method=RequestMethod.POST)
	public ModelAndView HBSOrganization(String id,HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<Organization> list = organizationService.organizationSJID(id);
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);


		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}


	
	

}
