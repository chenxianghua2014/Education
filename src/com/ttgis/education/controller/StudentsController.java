package com.ttgis.education.controller;


import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Coursestudy;
import com.ttgis.education.entity.Department;
import com.ttgis.education.entity.Dictionary;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Studenthree;
import com.ttgis.education.entity.Studentone;
import com.ttgis.education.entity.Studenttwo;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.AccountService;
import com.ttgis.education.service.ClassinfoService;
import com.ttgis.education.service.ClastudentService;
import com.ttgis.education.service.CoursestudyService;
import com.ttgis.education.service.DepartmentService;
import com.ttgis.education.service.DictionaryService;
import com.ttgis.education.service.LogService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.service.StudenthreeService;
import com.ttgis.education.service.StudentoneService;
import com.ttgis.education.service.StudenttwoService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.RandomGUIDUtil; 
import com.ttgis.education.utils.Tools;
/**
 * 学院信息管理科
 * <p>Title:StudentsController </p>
 *@author 王艳鹏
 *2015-8-18
 */

@Controller
public class StudentsController{

	@Resource
	private AccountService accountService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private StudentService studentService;
	@Resource
	private StudentoneService studentoneService;
	@Resource
	private StudenttwoService studenttwoService;
	@Resource
	private StudenthreeService studenthreeService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private CoursestudyService coursestudyService;

	@Resource
	private ClastudentService clastudentService;

	@Resource
	private ClassinfoService classinfoService;
	@Resource
	LogService logService;
	Log log = new Log();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Student student;
	private Department department;

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(InformationController.class);



	//--------------------------------------部门后台---------------------------------------




	/**
	 * 组织架构树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/zzjgStudentsTree", method = RequestMethod.GET)
	public ModelAndView zzjgStudentsTree(int n,Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		Organization oneid = organizationService.selectByPrimaryKey(account.getAccountCatalog());
		List<Organization> organization = organizationService.selectAllOrganization();
		Department dd = new Department();
		dd.setEpartmentRank("4");//部门
		List <Department> bmlist = departmentService.DepartmentHBOId(dd);
		dd.setEpartmentRank("5");//科室
		List <Department> kslist = departmentService.DepartmentHBOId(dd);
		model.addAttribute("bmlist", bmlist);
		model.addAttribute("kslist", kslist);
		model.addAttribute("organization", organization);
		model.addAttribute("account", account);
		model.addAttribute("oneid", oneid);
		String dizhi="";
		if(n == 1){
			model.addAttribute("type", "部门");
			dizhi="tree/zzjgStudentsTree";
		}
		if(n == 2){
			model.addAttribute("type", "学员");
			dizhi="tree/zzjgStudentsTrees";
		}
		return new ModelAndView(dizhi);
	}



	/**
	 * 人员调出（后台）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/studentout")
	public ModelAndView studentout(Student student,HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Account acc = (Account) session.getAttribute("sessionUser");
		student.setStudentType(2);//游离
		student.setStudentAt(new Date());//更改时间
		//		student.setStudentDel(0);//删除标记为0 删除
		int i = studentService.updateByPrimaryKeySelective(student);
		if(i>0){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，调出学员："+student.getStudentSeq());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
		}
		mv.setViewName("redirect:studentxyht?mark=1&page=1&organizationId="+student.getOrganizationId()+"&cj="+student.getCj());

		return  mv;
	}

	/**
	 * 人员调入（后台）
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "/studentin")
//	public ModelAndView studentin(Student student,HttpSession session,HttpServletRequest request){
//		ModelAndView mv = new ModelAndView();
//		Account acc = (Account) session.getAttribute("sessionUser");
//		student.setStudentType(1);//游离
//		student.setStudentAt(new Date());//更改时间
//		//		student.setStudentDel(1);//删除标记为1 添加
//		student.setStudentCompanyid(acc.getAccountCatalog());
//		int i = studentService.updateByPrimaryKeySelective(student);
//		if(i>0){
//			log.setLogAt(sdf.format(new Date()));
//			log.setLogContent("用户："+acc.getAccountLoginid()+"，调入学员："+student.getStudentSeq()+"学员部门"+student.getStudentDepartment());
//			log.setLogId(Tools.getGUID());
//			log.setLogIp(HYLUtil.getIP4(request));
//			log.setLogUser(acc.getAccountLoginid());
//			log.setLogUserType("后台用户");
//			logService.insertSelective(log);
//		}
//		
//		mv.setViewName("redirect:studentxyht?mark=1&page=1&cj="+student.getCj()+"&organizationId="+acc.getAccountCatalog());
//
//		return  mv;
//	}
	
	/**
	 * 批量人员调入（后台）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/studentins")
	public ModelAndView studentins(Student student,HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Account acc = (Account) session.getAttribute("sessionUser");
		String[] box = student.getCheckboxids();
		for (int i = 0; i < box.length; i++) {
			student.setStudentId(box[i]);//学员Id
			student.setStudentType(1);//游离
			student.setStudentAt(new Date());//更改时间
			//		student.setStudentDel(1);//删除标记为1 添加
			student.setStudentCompanyid(acc.getAccountCatalog());
		
			//判断查询部门和子部门的区别
			if (student.getStudentDepartments()!=null&&!student.getStudentDepartments().equals("")&&!student.getStudentDepartments().equals("9")) {
				student.setStudentDepartment(student.getStudentDepartments());
			} else {
				student.setStudentDepartment(student.getStudentDepartment());
			}
			System.out.println(student.getStudentDepartment());
			int j = studentService.updateByPrimaryKeySelective(student);
			if(j>0){
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+acc.getAccountLoginid()+"，调入学员："+student.getStudentSeq()+"学员部门"+student.getStudentDepartment());
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(acc.getAccountLoginid());
				log.setLogUserType("后台用户");
				logService.insertSelective(log);
			}	
		}
		
		
		mv.setViewName("redirect:studentxyht?mark=1&page=1&cj="+student.getCj()+"&organizationId="+acc.getAccountCatalog());

		return  mv;
	}

	/**
	 * 批量推送（后台）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/pushStudent")
	public ModelAndView pushStudent(Student student,HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		Coursestudy dy=new Coursestudy();
		//		Classinfo info=new Classinfo();
		Account acc = (Account) session.getAttribute("sessionUser");
		System.out.println(student.getCourseId());
		String[] box = student.getCheckboxids();
		for (int i = 0; i < box.length; i++) {
			//			info.setStudentId(box[i]);
			//			info.setCourseId(student.getCourseId());
			//			List<Classinfo> infos = classinfoService.selectByXinPanIdKey(info);
			//			if (infos.size()==0) {

			dy.setCoursestudyId(RandomGUIDUtil.generateKey());
			dy.setCourseId(student.getCourseId());
			dy.setStudentId(box[i]);
			dy.setCoursestudyType("1");
			dy.setCoursestudyAt(new Date());
			dy.setCoursestudyDel(1);//单独对学员推送的课程
			//判断推送的课程是否报培训班课程是否相同
			List<Coursestudy> panduan = coursestudyService.selectPanDuanKey(dy);
			if (panduan.size()==0) {
				int j=coursestudyService.insertSelective(dy);
				
				
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+acc.getAccountLoginid()+"，向学员："+box[i]+",推送课程:"+student.getCourseId());
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(acc.getAccountLoginid());
				log.setLogUserType("后台用户");
				logService.insertSelective(log);
			}
			//			} else {
			//				
			//			}
		}

		mv.setViewName("redirect:studentxyht?mark=1&page=1&organizationId="+acc.getAccountCatalog()+"&cj="+acc.getAccountType()+"&courseId="+student.getCourseId());

		return  mv;
	}


	/**
	 * 查询部门（后台）
	 * @return
	 */
	@RequestMapping(value = "/studentsbmht", method = RequestMethod.GET)
	public ModelAndView studentsbmht(Department d,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		List<Department> bmlist= departmentService.selectbmlist(d);
		mv.addObject("list", bmlist);
		return new ModelAndView("backStudents/querynews");
	}
	//--------------------------------------学员后台---------------------------------------	
	/**
	 * 查询学员（后台）
	 * @return
	 */
	@RequestMapping(value = "/studentxyht")
	public ModelAndView studentxyht(Student s,int mark ,String organizationId,int cj,int page,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			student = new Student();
			department=new Department();
		}
		//====================||
		Account acc = (Account) session.getAttribute("sessionUser");
				student.setStudentCompanyid(s.getOrganizationId());
		//课程推送    
		if (s.getCourseId()!=null&&s.getCourseId()!="") {
			student.setCourseId(s.getCourseId());
				student.setYs("0");
				if (acc.getAccountCatalog().equals("test001")) {
					
					if (s.getStudentCompanyids()!=null&&!s.getStudentCompanyids().equals("")) {
						student.setStudentCompanyid(s.getStudentCompanyids());
					} else {
						student.setStudentCompanyid(s.getStudentCompanyid());
					}
//					student.setStudentCompanyid(s.getStudentCompanyid());
				}
				
		}else{
			if (s.getOrganizationId()!=null&&s.getOrganizationId()!="") {
				if (s.getOrganizationId().equals("test001")) {
					student.setYs("1");
				}else{
					student.setYs("0");
				}
			}else{
				student.setYs("1");
			}
		}
		
		//查询部门------------------------
		department.setOrganizationId(s.getOrganizationId());
		department.setEpartmentRank("4");
		//		List<Department> bm = departmentService.selectbmlist(department);
		//--------------判断1级单位和二三级单位-----------------
//		Account acc = (Account) session.getAttribute("sessionUser");
//		if (s.getOrganizationId()!=null&&s.getOrganizationId()!="") {
//			if (s.getOrganizationId().equals("test001")) {
//				student.setYs("1");
//			}else{
//				student.setYs("0");
//			}
//		}else{
//			student.setYs("1");
//		}
		if (cj==6) {
			student.setJop(cj+"");
			department.setOrganizationId(acc.getAccountCatalog());
			student.setStudentCompanyid("");
		}
		List<Department> bm = departmentService.selectbmlist(department);
//================推送查询全部组织id==================
		Organization orke = new Organization();
		orke.setFwcj(1);
		orke.setOrganizationFwqx("3");
List<Organization> listOrke = organizationService.selectOrganizationKe(orke);
//==================================

		//---------------------条件查询--------------（学员）--------------------
		//student=s;
		//Student  表
		if ( s.gettFour()!=null&&s.gettFour().equals("1")) {
			//人员姓名
			if(s.getStudentName()!=null && s.getStudentName()!="" ){
				student.setStudentName(s.getStudentName());
			}
			//电话号码
			if(s.getStudentTelephone()!=null && s.getStudentTelephone()!="" ){
				student.setStudentTelephone(s.getStudentTelephone());
			}
			// 邮     箱
			if(s.getStudentEmail()!=null && s.getStudentEmail()!="" ){
				student.setStudentEmail(s.getStudentEmail());
			}
			//学员状态
			if(s.getStudentType()!=null && s.getStudentType()!=0 ){
				student.setStudentType(s.getStudentType());
			}
			//人员类别
			if(s.getStudentCategory()!=null && s.getStudentCategory()!="" ){
				student.setStudentCategory(s.getStudentCategory());
			}
		}
		//Studentone 1表
		Studentone studentone=new Studentone();
		if (s.gettOne()!=null&&s.gettOne().equals("1") ) {
			if (s.getStudentone()!=null ) {
				//人员类别
				if(s.getStudentone().getStudentoneXb()!=null && s.getStudentone().getStudentoneXb()!="" ){
					studentone.setStudentoneXb(s.getStudentone().getStudentoneXb());
					student.setStudentone(studentone);
				}
				//身份证
				if(s.getStudentone().getStudentoneSfzh()!=null && s.getStudentone().getStudentoneSfzh()!="" ){
					studentone.setStudentoneSfzh(s.getStudentone().getStudentoneSfzh());
					student.setStudentone(studentone);
				}
				//年  龄
				if(s.getStudentone().getStudentoneAge()!=null && s.getStudentone().getStudentoneAge()!=0 ){
					studentone.setStudentoneAge(s.getStudentone().getStudentoneAge());
					student.setStudentone(studentone);
				}
				//学 历
				if(s.getStudentone().getStudentoneXl()!=null && s.getStudentone().getStudentoneXl()!="" ){
					studentone.setStudentoneXl(s.getStudentone().getStudentoneXl());
					student.setStudentone(studentone);
				}
				// 学 位
				if(s.getStudentone().getStudentoneXw()!=null && s.getStudentone().getStudentoneXw()!="" ){
					studentone.setStudentoneXw(s.getStudentone().getStudentoneXw());
					student.setStudentone(studentone);
				}
				// 政治面貌
				if(s.getStudentone().getStudentoneZzmm()!=null && s.getStudentone().getStudentoneZzmm()!="" ){
					studentone.setStudentoneZzmm(s.getStudentone().getStudentoneZzmm());
					student.setStudentone(studentone);
				}
				//用工形式
				if(s.getStudentone().getStudentoneYgxs()!=null && s.getStudentone().getStudentoneYgxs()!="" ){
					studentone.setStudentoneYgxs(s.getStudentone().getStudentoneYgxs());
					student.setStudentone(studentone);
				}
				//行政级别
				if(s.getStudentone().getStudentoneXzjb()!=null && s.getStudentone().getStudentoneXzjb()!="" ){
					studentone.setStudentoneXzjb(s.getStudentone().getStudentoneXzjb());
					student.setStudentone(studentone);
				}
				//专业技术职务
				if(s.getStudentone().getStudentoneZyjszw()!=null && s.getStudentone().getStudentoneZyjszw()!="" ){
					studentone.setStudentoneZyjszw(s.getStudentone().getStudentoneZyjszw());
					student.setStudentone(studentone);
				}
				//工人技术职务
				if(s.getStudentone().getStudentoneGrjszw()!=null && s.getStudentone().getStudentoneGrjszw()!="" ){
					studentone.setStudentoneGrjszw(s.getStudentone().getStudentoneGrjszw());
					student.setStudentone(studentone);
				}
				// 所学专业
				if(s.getStudentone().getStudentoneSxzy()!=null && s.getStudentone().getStudentoneSxzy()!="" ){
					studentone.setStudentoneSxzy(s.getStudentone().getStudentoneSxzy());
					student.setStudentone(studentone);
				}
				//毕肄业
				if(s.getStudentone().getStudentoneByy()!=null && s.getStudentone().getStudentoneByy()!="" ){
					studentone.setStudentoneByy(s.getStudentone().getStudentoneByy());
					student.setStudentone(studentone);
				}
				//毕（肄）业学校或单位
				if(s.getStudentone().getStudentoneByyxx()!=null && s.getStudentone().getStudentoneByyxx()!="" ){
					studentone.setStudentoneByyxx(s.getStudentone().getStudentoneByyxx());
					student.setStudentone(studentone);
				}
				//院（系）
				if(s.getStudentone().getStudentoneYx()!=null && s.getStudentone().getStudentoneYx()!="" ){
					studentone.setStudentoneYx(s.getStudentone().getStudentoneYx());
					student.setStudentone(studentone);
				}
				//学位授予单位
				if(s.getStudentone().getStudentoneXwsydw()!=null && s.getStudentone().getStudentoneXwsydw()!="" ){
					studentone.setStudentoneXwsydw(s.getStudentone().getStudentoneXwsydw());
					student.setStudentone(studentone);
				}
				//全日制或在职
				if(s.getStudentone().getStudentoneQrzhzz()!=null && s.getStudentone().getStudentoneQrzhzz()!="" ){
					studentone.setStudentoneQrzhzz(s.getStudentone().getStudentoneQrzhzz());
					student.setStudentone(studentone);
				}
				//是否当前学历
				if(s.getStudentone().getStudentoneSfdqxl()!=null && s.getStudentone().getStudentoneSfdqxl()!="" ){
					studentone.setStudentoneSfdqxl(s.getStudentone().getStudentoneSfdqxl());
					student.setStudentone(studentone);
				}
				//出生日期
				if(s.getStudentone().getStudentoneCsri()!=null && s.getStudentone().getStudentoneCsri()!="" ){
					studentone.setStudentoneCsri(s.getStudentone().getStudentoneCsri());
					student.setStudentone(studentone);
				}
				//参加工作时间
				if(s.getStudentone().getStudentoneCjgzsj()!=null && s.getStudentone().getStudentoneCjgzsj()!="" ){
					studentone.setStudentoneCjgzsj(s.getStudentone().getStudentoneCjgzsj());
					student.setStudentone(studentone);
				}
				// 现单位工作时间
				if(s.getStudentone().getStudentoneXdwgzsj()!=null && s.getStudentone().getStudentoneXdwgzsj()!="" ){
					studentone.setStudentoneXdwgzsj(s.getStudentone().getStudentoneXdwgzsj());
					student.setStudentone(studentone);
				}
				//入学时间
				if(s.getStudentone().getStudentoneRxsj()!=null && s.getStudentone().getStudentoneRxsj()!="" ){
					studentone.setStudentoneRxsj(s.getStudentone().getStudentoneRxsj());
					student.setStudentone(studentone);
				}
				//毕（肄）业时间
				if(s.getStudentone().getStudentoneByysj()!=null && s.getStudentone().getStudentoneByysj()!="" ){
					studentone.setStudentoneByysj(s.getStudentone().getStudentoneByysj());
					student.setStudentone(studentone);
				}
			}
		}
		//Studenttwo 2表
		Studenttwo studenttwo=new Studenttwo();
		if(s.gettTwo()!=null&&s.gettTwo().equals("1")){
			if (s.getStudenttwo()!=null ) {
				//职务类别
				if(s.getStudenttwo().getStudenttwoZwlb()!=null && s.getStudenttwo().getStudenttwoZwlb()!="" ){
					studenttwo.setStudenttwoZwlb(s.getStudenttwo().getStudenttwoZwlb());
					student.setStudenttwo(studenttwo);
				}
				//行政职务名称
				if(s.getStudenttwo().getStudenttwoXzzwmc()!=null && s.getStudenttwo().getStudenttwoXzzwmc()!="" ){
					studenttwo.setStudenttwoXzzwmc(s.getStudenttwo().getStudenttwoXzzwmc());
					student.setStudenttwo(studenttwo);
				}
				//党内职务名称
				if(s.getStudenttwo().getStudenttwoDnzwmc()!=null && s.getStudenttwo().getStudenttwoDnzwmc()!="" ){
					studenttwo.setStudenttwoDnzwmc(s.getStudenttwo().getStudenttwoDnzwmc());
					student.setStudenttwo(studenttwo);
				}
				//待遇级别
				if(s.getStudenttwo().getStudenttwoDyjb()!=null && s.getStudenttwo().getStudenttwoDyjb()!="" ){
					studenttwo.setStudenttwoDyjb(s.getStudenttwo().getStudenttwoDyjb());
					student.setStudenttwo(studenttwo);
				}
				//职务属性
				if(s.getStudenttwo().getStudenttwoZwsx()!=null && s.getStudenttwo().getStudenttwoZwsx()!="" ){
					studenttwo.setStudenttwoZwsx(s.getStudenttwo().getStudenttwoZwsx());
					student.setStudenttwo(studenttwo);
				}
				//是否当前职务
				if(s.getStudenttwo().getStudenttwoSfdqzw()!=null && s.getStudenttwo().getStudenttwoSfdqzw()!="" ){
					studenttwo.setStudenttwoSfdqzw(s.getStudenttwo().getStudenttwoSfdqzw());
					student.setStudenttwo(studenttwo);
				}
				//是否最初任同职级
				if(s.getStudenttwo().getStudenttwoSfzcrtzj()!=null && s.getStudenttwo().getStudenttwoSfzcrtzj()!="" ){
					studenttwo.setStudenttwoSfzcrtzj(s.getStudenttwo().getStudenttwoSfzcrtzj());
					student.setStudenttwo(studenttwo);
				}
				//专业技术资格名称
				if(s.getStudenttwo().getStudenttwoZyjszgmc()!=null && s.getStudenttwo().getStudenttwoZyjszgmc()!="" ){
					studenttwo.setStudenttwoZyjszgmc(s.getStudenttwo().getStudenttwoZyjszgmc());
					student.setStudenttwo(studenttwo);
				}
				//专业技术任职资格
				if(s.getStudenttwo().getStudenttwoZyjsrzzg()!=null && s.getStudenttwo().getStudenttwoZyjsrzzg()!="" ){
					studenttwo.setStudenttwoZyjsrzzg(s.getStudenttwo().getStudenttwoZyjsrzzg());
					student.setStudenttwo(studenttwo);
				}
				//专业类别
				if(s.getStudenttwo().getStudenttwoZylb()!=null && s.getStudenttwo().getStudenttwoZylb()!="" ){
					studenttwo.setStudenttwoZylb(s.getStudenttwo().getStudenttwoZylb());
					student.setStudenttwo(studenttwo);
				}
				//聘任专业技术职务名称
				if(s.getStudenttwo().getStudenttwoPrzyjszwmc()!=null && s.getStudenttwo().getStudenttwoPrzyjszwmc()!="" ){
					studenttwo.setStudenttwoPrzyjszwmc(s.getStudenttwo().getStudenttwoPrzyjszwmc());
					student.setStudenttwo(studenttwo);
				}
				//是否当前专业技术职务
				if(s.getStudenttwo().getStudenttwoSfdqzyjszw()!=null && s.getStudenttwo().getStudenttwoSfdqzyjszw()!="" ){
					studenttwo.setStudenttwoSfdqzyjszw(s.getStudenttwo().getStudenttwoSfdqzyjszw());
					student.setStudenttwo(studenttwo);
				}
				//工人技术资格
				if(s.getStudenttwo().getStudenttwoGrjszg()!=null && s.getStudenttwo().getStudenttwoGrjszg()!="" ){
					studenttwo.setStudenttwoGrjszg(s.getStudenttwo().getStudenttwoGrjszg());
					student.setStudenttwo(studenttwo);
				}
				// 聘任工人技术职务
				if(s.getStudenttwo().getStudenttwoPrgrjszw()!=null && s.getStudenttwo().getStudenttwoZwlb()!="" ){
					studenttwo.setStudenttwoZwlb(s.getStudenttwo().getStudenttwoZwlb());
					student.setStudenttwo(studenttwo);
				}
				//从事工作或担任职务
				if(s.getStudenttwo().getStudenttwoCsgzhdrzw()!=null && s.getStudenttwo().getStudenttwoCsgzhdrzw()!="" ){
					studenttwo.setStudenttwoCsgzhdrzw(s.getStudenttwo().getStudenttwoCsgzhdrzw());
					student.setStudenttwo(studenttwo);
				}
				// 异常类别
				if(s.getStudenttwo().getStudenttwoYclb()!=null && s.getStudenttwo().getStudenttwoYclb()!="" ){
					studenttwo.setStudenttwoYclb(s.getStudenttwo().getStudenttwoYclb());
					student.setStudenttwo(studenttwo);
				}
				//异常原因
				if(s.getStudenttwo().getStudenttwoYcyy()!=null && s.getStudenttwo().getStudenttwoYcyy()!="" ){
					studenttwo.setStudenttwoYcyy(s.getStudenttwo().getStudenttwoYcyy());
					student.setStudenttwo(studenttwo);
				}
				//任职时间
				if(s.getStudenttwo().getStudenttwoRzsj()!=null && s.getStudenttwo().getStudenttwoRzsj()!="" ){
					studenttwo.setStudenttwoRzsj(s.getStudenttwo().getStudenttwoRzsj());
					student.setStudenttwo(studenttwo);
				}
				//免职时间
				if(s.getStudenttwo().getStudenttwoMzsj()!=null && s.getStudenttwo().getStudenttwoMzsj()!="" ){
					studenttwo.setStudenttwoMzsj(s.getStudenttwo().getStudenttwoMzsj());
					student.setStudenttwo(studenttwo);
				}
				//取得时间
				if(s.getStudenttwo().getStudenttwoQdsj()!=null && s.getStudenttwo().getStudenttwoQdsj()!="" ){
					studenttwo.setStudenttwoQdsj(s.getStudenttwo().getStudenttwoQdsj());
					student.setStudenttwo(studenttwo);
				}
				//聘任起始时间
				if(s.getStudenttwo().getStudenttwoPrqssj()!=null && s.getStudenttwo().getStudenttwoPrqssj()!="" ){
					studenttwo.setStudenttwoPrqssj(s.getStudenttwo().getStudenttwoPrqssj());
					student.setStudenttwo(studenttwo);
				}
				//聘任终止时间
				if(s.getStudenttwo().getStudenttwoPrzzsj()!=null && s.getStudenttwo().getStudenttwoPrzzsj()!="" ){
					studenttwo.setStudenttwoPrzzsj(s.getStudenttwo().getStudenttwoPrzzsj());
					student.setStudenttwo(studenttwo);
				}
				//取得资格时间
				if(s.getStudenttwo().getStudenttwoQdzgsj()!=null && s.getStudenttwo().getStudenttwoQdzgsj()!="" ){
					studenttwo.setStudenttwoQdzgsj(s.getStudenttwo().getStudenttwoQdzgsj());
					student.setStudenttwo(studenttwo);
				}
				//聘任时间
				if(s.getStudenttwo().getStudenttwoPrsj()!=null && s.getStudenttwo().getStudenttwoPrsj()!="" ){
					studenttwo.setStudenttwoPrsj(s.getStudenttwo().getStudenttwoPrsj());
					student.setStudenttwo(studenttwo);
				}
				//个人简历起始时间
				if(s.getStudenttwo().getStudenttwoGrjlqssj()!=null && s.getStudenttwo().getStudenttwoGrjlqssj()!="" ){
					studenttwo.setStudenttwoGrjlqssj(s.getStudenttwo().getStudenttwoGrjlqssj());
					student.setStudenttwo(studenttwo);
				}
				//个人简历结束时间
				if(s.getStudenttwo().getStudenttwoGrjljssj()!=null && s.getStudenttwo().getStudenttwoGrjljssj()!="" ){
					studenttwo.setStudenttwoGrjljssj(s.getStudenttwo().getStudenttwoGrjljssj());
					student.setStudenttwo(studenttwo);
				}
				// 参加党派时间
				if(s.getStudenttwo().getStudenttwoCjdpsj()!=null && s.getStudenttwo().getStudenttwoCjdpsj()!="" ){
					studenttwo.setStudenttwoCjdpsj(s.getStudenttwo().getStudenttwoCjdpsj());
					student.setStudenttwo(studenttwo);
				}
				//工种名称（子集）
				if(s.getStudenttwo().getStudenttwoGzmc()!=null && s.getStudenttwo().getStudenttwoGzmc()!="" ){
					studenttwo.setStudenttwoGzmc(s.getStudenttwo().getStudenttwoGzmc());
					student.setStudenttwo(studenttwo);
				}
				//工种名称
				if(s.getStudenttwo().getStudenttwoGzmcSx()!=null && s.getStudenttwo().getStudenttwoGzmcSx()!="" ){
					studenttwo.setStudenttwoGzmc("!@#$%");
					student.setStudenttwo(studenttwo);
				}
			}
		}

		//Studenthree 3表
		Studenthree studenthree=new Studenthree();
		if(s.gettThree()!=null&&s.gettThree().equals("1")){
			if (s.getStudenttwo()!=null ) {
				//留学状态
				if(s.getStudenthree().getStudenthreeLxzt()!=null && s.getStudenthree().getStudenthreeLxzt()!="" ){
					studenthree.setStudenthreeLxzt(s.getStudenthree().getStudenthreeLxzt());
					student.setStudenthree(studenthree);
				}	
				//派出单位
				if(s.getStudenthree().getStudenthreePcdw()!=null && s.getStudenthree().getStudenthreePcdw()!="" ){
					studenthree.setStudenthreePcdw(s.getStudenthree().getStudenthreePcdw());
					student.setStudenthree(studenthree);
				}	
				//专业
				if(s.getStudenthree().getStudenthreeZy()!=null && s.getStudenthree().getStudenthreeZy()!="" ){
					studenthree.setStudenthreeZy(s.getStudenthree().getStudenthreeZy());
					student.setStudenthree(studenthree);
				}	
				//留学身份
				if(s.getStudenthree().getStudenthreeLxsf()!=null && s.getStudenthree().getStudenthreeLxsf()!="" ){
					studenthree.setStudenthreeLxsf(s.getStudenthree().getStudenthreeLxsf());
					student.setStudenthree(studenthree);
				}	
				//留学国别
				if(s.getStudenthree().getStudenthreeLxgb()!=null && s.getStudenthree().getStudenthreeLxgb()!="" ){
					studenthree.setStudenthreeLxgb(s.getStudenthree().getStudenthreeLxgb());
					student.setStudenthree(studenthree);
				}	
				// 培训项目名称
				if(s.getStudenthree().getStudenthreePxxmmc()!=null && s.getStudenthree().getStudenthreePxxmmc()!="" ){
					studenthree.setStudenthreePxxmmc(s.getStudenthree().getStudenthreePxxmmc());
					student.setStudenthree(studenthree);
				}	
				//培训天数
				if(s.getStudenthree().getStudenthreePxts()!=null ){
					studenthree.setStudenthreePxts(s.getStudenthree().getStudenthreePxts());
					student.setStudenthree(studenthree);
				}	
				//培训类型
				if(s.getStudenthree().getStudenthreePxlx()!=null && s.getStudenthree().getStudenthreePxlx()!="" ){
					studenthree.setStudenthreePxlx(s.getStudenthree().getStudenthreePxlx());
					student.setStudenthree(studenthree);
				}	
				//培训渠道
				if(s.getStudenthree().getStudenthreePxqd()!=null && s.getStudenthree().getStudenthreePxqd()!="" ){
					studenthree.setStudenthreePxqd(s.getStudenthree().getStudenthreePxqd());
					student.setStudenthree(studenthree);
				}	
				//离退类别
				if(s.getStudenthree().getStudenthreeLtlb()!=null && s.getStudenthree().getStudenthreeLtlb()!="" ){
					studenthree.setStudenthreeLtlb(s.getStudenthree().getStudenthreeLtlb());
					student.setStudenthree(studenthree);
				}	
				//国别及地区
				if(s.getStudenthree().getStudenthreeGbjdq()!=null && s.getStudenthree().getStudenthreeGbjdq()!="" ){
					studenthree.setStudenthreeGbjdq(s.getStudenthree().getStudenthreeGbjdq());
					student.setStudenthree(studenthree);
				}	
				//出国（境）事由
				if(s.getStudenthree().getStudenthreeCgsy()!=null && s.getStudenthree().getStudenthreeCgsy()!="" ){
					studenthree.setStudenthreeCgsy(s.getStudenthree().getStudenthreeCgsy());
					student.setStudenthree(studenthree);
				}	
				//出国所去单位
				if(s.getStudenthree().getStudenthreeCgsqdw()!=null && s.getStudenthree().getStudenthreeCgsqdw()!="" ){
					studenthree.setStudenthreeCgsqdw(s.getStudenthree().getStudenthreeCgsqdw());
					student.setStudenthree(studenthree);
				}	
				//出国派出单位
				if(s.getStudenthree().getStudenthreeCgpcdw()!=null && s.getStudenthree().getStudenthreeCgpcdw()!="" ){
					studenthree.setStudenthreeCgpcdw(s.getStudenthree().getStudenthreeCgpcdw());
					student.setStudenthree(studenthree);
				}	
				//备注
				if(s.getStudenthree().getStudenthreeBj()!=null && s.getStudenthree().getStudenthreeBj()!="" ){
					studenthree.setStudenthreeBj(s.getStudenthree().getStudenthreeBj());
					student.setStudenthree(studenthree);
				}	
				//领导干部标识
				if(s.getStudenthree().getStudenthreeLdgbbz()!=null && s.getStudenthree().getStudenthreeLdgbbz()!="" ){
					studenthree.setStudenthreeLdgbbz(s.getStudenthree().getStudenthreeLdgbbz());
					student.setStudenthree(studenthree);
				}	
				//领导后备标识
				if(s.getStudenthree().getStudenthreeLdhbbz()!=null && s.getStudenthree().getStudenthreeLdhbbz()!="" ){
					studenthree.setStudenthreeLdhbbz(s.getStudenthree().getStudenthreeLdhbbz());
					student.setStudenthree(studenthree);
				}	
				//型号干部标识
				if(s.getStudenthree().getStudenthreeXhgbbz()!=null && s.getStudenthree().getStudenthreeXhgbbz()!="" ){
					studenthree.setStudenthreeXhgbbz(s.getStudenthree().getStudenthreeXhgbbz());
					student.setStudenthree(studenthree);
				}	
				//董监事标识
				if(s.getStudenthree().getStudenthreeDjsbz()!=null && s.getStudenthree().getStudenthreeDjsbz()!="" ){
					studenthree.setStudenthreeDjsbz(s.getStudenthree().getStudenthreeDjsbz());
					student.setStudenthree(studenthree);
				}	
				//留学时间
				if(s.getStudenthree().getStudenthreeLxsj()!=null && s.getStudenthree().getStudenthreeLxsj()!="" ){
					studenthree.setStudenthreeLxsj(s.getStudenthree().getStudenthreeLxsj());
					student.setStudenthree(studenthree);
				}	
				// 离（退）休时间
				if(s.getStudenthree().getStudenthreeLtxsj()!=null && s.getStudenthree().getStudenthreeLtxsj()!="" ){
					studenthree.setStudenthreeLtxsj(s.getStudenthree().getStudenthreeLtxsj());
					student.setStudenthree(studenthree);
				}	
				//出国时间
				if(s.getStudenthree().getStudenthreeCgsj()!=null && s.getStudenthree().getStudenthreeCgsj()!="" ){
					studenthree.setStudenthreeCgsj(s.getStudenthree().getStudenthreeCgsj());
					student.setStudenthree(studenthree);
				}	
				//计划回国时间
				if(s.getStudenthree().getStudenthreeJhhgsj()!=null && s.getStudenthree().getStudenthreeJhhgsj()!="" ){
					studenthree.setStudenthreeJhhgsj(s.getStudenthree().getStudenthreeJhhgsj());
					student.setStudenthree(studenthree);
				}	
				//实际回国时间
				if(s.getStudenthree().getStudenthreeSjhgsj()!=null && s.getStudenthree().getStudenthreeSjhgsj()!="" ){
					studenthree.setStudenthreeSjhgsj(s.getStudenthree().getStudenthreeSjhgsj());
					student.setStudenthree(studenthree);
				}	

			}
		}
		//-------------------------------
		if (s.getDepartmentId()==null) {

		} else {
			student.setDepartmentId(s.getDepartmentId());
		}
		//------------------------------------------------
//		student.setStudentCompanyid(s.getOrganizationId());
		//判断查询部门和子部门的区别
		
			if (s.getStudentDepartments()!=null&&!s.getStudentDepartments().equals("")&&!s.getStudentDepartments().equals("9")) {
				student.setStudentDepartment(s.getStudentDepartments());
			} else {
				student.setStudentDepartment(s.getStudentDepartment());
			}
		
		
//		student.setStudentDepartment(de.getEpartmentUpid());//传过来的科室ID
		//数据字典--------
		List<Dictionary> ax = dictionaryService.selectDataKey("AX");//性别
		List<Dictionary> am = dictionaryService.selectDataKey("AM");//学历
		List<Dictionary> an = dictionaryService.selectDataKey("AN");//学位
		List<Dictionary> BF = dictionaryService.selectDataKey("BF");//健康状况
		List<Dictionary> BG = dictionaryService.selectDataKey("BG");//婚姻状况
		List<Dictionary> XA = dictionaryService.selectDataKey("XA");//用工形式
		List<Dictionary> OZ = dictionaryService.selectDataKey("OZ");//待遇级别
		List<Dictionary> AY = dictionaryService.selectDataKey("AY");//国（境）外侨属标识
		List<Dictionary> XD = dictionaryService.selectDataKey("XD");//涉密等级
		List<Dictionary> HY = dictionaryService.selectDataKey("HY");//毕肄业
		List<Dictionary> BQ = dictionaryService.selectDataKey("BQ");//职务类别
		List<Dictionary> IR = dictionaryService.selectDataKey("IR");//职务属性
		List<Dictionary> AK = dictionaryService.selectDataKey("AK");//党内职务名称（子集）
		List<Dictionary> AL = dictionaryService.selectDataKey("AL");//享受职级待遇	
		List<Dictionary> YC = dictionaryService.selectDataKey("YC");//专业类别	
		List<Dictionary> YB = dictionaryService.selectDataKey("YB");//专业技术任职资格		
		List<Dictionary> XJ = dictionaryService.selectDataKey("XJ");//工人技术资格		
		List<Dictionary> AT = dictionaryService.selectDataKey("AT");//政治面貌		
		List<Dictionary> CA = dictionaryService.selectDataKey("CA");//异常类别		
		List<Dictionary> CC = dictionaryService.selectDataKey("CC");//考核结果		
		//		List<Dictionary> K8 = dictionaryService.selectDataKey("K8");//表彰级别
		List<Dictionary> AR = dictionaryService.selectDataKey("AR");//处分名称	
		List<Dictionary> HT = dictionaryService.selectDataKey("HT");//监察机关直接给予的	
		List<Dictionary> XR = dictionaryService.selectDataKey("XR");//留学状态	
		List<Dictionary> XS = dictionaryService.selectDataKey("XS");//留学身份
		List<Dictionary> YG = dictionaryService.selectDataKey("YG");//培训类型	
		List<Dictionary> YH = dictionaryService.selectDataKey("YH");//培训渠道	
		List<Dictionary> HD = dictionaryService.selectDataKey("HD");//离退类别	
		List<Dictionary> IL = dictionaryService.selectDataKey("IL");//返聘情况	
		List<Dictionary> IJ = dictionaryService.selectDataKey("IJ");//参加各项活动
		List<Dictionary> XK = dictionaryService.selectDataKey("XK");//技能人员称号	
		List<Dictionary> XZ = dictionaryService.selectDataKey("XZ");//专家称号	
		List<Dictionary> AS = dictionaryService.selectDataKey("AS");//与本人关系	
		List<Dictionary> BR = dictionaryService.selectDataKey("BR");//出国（出境）事由	
		List<Dictionary> OC = dictionaryService.selectDataKey("OC");//领导干部标识	
		List<Dictionary> OD = dictionaryService.selectDataKey("OD");//领导后备标识	
		List<Dictionary> OE = dictionaryService.selectDataKey("OE");//型号干部标识	
		List<Dictionary> OG = dictionaryService.selectDataKey("OG");//董监事标识	
		List<Dictionary> IT = dictionaryService.selectDataKey("IT");//劳动合同类型	
		List<Dictionary> Q2 = dictionaryService.selectDataKey("Q2");//全日制或在职	
		List<Dictionary> AB = dictionaryService.selectDataKey("AB");//籍贯、出生地(子集)	
		List<Dictionary> AD = dictionaryService.selectDataKey("AD");//国家名称	
		List<Dictionary> AE = dictionaryService.selectDataKey("AE");//民族	
		List<Dictionary> AJ = dictionaryService.selectDataKey("AJ");//专业技术名称 聘任专业技术职务名称	
		List<Dictionary> JA = dictionaryService.selectDataKey("JA");//工种名称（子集）
		List<Dictionary> JATop = dictionaryService.selectDataTopKey("JA");//工种名称（头部）
		//---------------

		//---------------
		PageBean pageBean = new PageBean();
		int allRow = studentService.StudentCount(student);
		//判断条数的多少
		final int length;
		if (allRow<=150) {
			length = 150;//每页记录数
		} else {
			length = 20;//每页记录数
		}
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		student.setPage(length*(currentPage1-1));
		student.setSize(length);
		
		

		List<Student> list = studentService.selectStudentPageAll(student);
		//转译
		for (int i = 0; i < list.size(); i++) {
		/*	Studentone stuone=new Studentone();
			Studenttwo stutwo=new Studenttwo();
			Studenthree stuthree=new Studenthree(); */
			
				//存部门
					Department departments=new Department();
			if (list.get(i).getDepartment()!=null) {
				departments = departmentService.selectByPrimaryKey(list.get(i).getDepartment().getEpartmentUpid());
				if (departments!=null) {
					list.get(i).setStudentUpcompanyid(departments.getEpartmentName());
				}else{
					list.get(i).setStudentUpcompanyid(list.get(i).getDepartment().getEpartmentName());
					list.get(i).getDepartment().setEpartmentName("");
				}
			}
			
			

			if (list.get(i).getStudentone()!=null) {
				//性  别
				if(list.get(i).getStudentone().getStudentoneXb()!=null && list.get(i).getStudentone().getStudentoneXb()!="" ){
					list.get(i).getStudentone().setStudentoneXb(this.zhuanYi("AX", list.get(i).getStudentone().getStudentoneXb()));
				}
				//学 历
				if(list.get(i).getStudentone().getStudentoneXl()!=null && list.get(i).getStudentone().getStudentoneXl()!="" ){
					list.get(i).getStudentone().setStudentoneXl(this.zhuanYi("AM", list.get(i).getStudentone().getStudentoneXl()));
				}
				//学 位
				if(list.get(i).getStudentone().getStudentoneXw()!=null && list.get(i).getStudentone().getStudentoneXw()!="" ){
					list.get(i).getStudentone().setStudentoneXw(this.zhuanYi("AN", list.get(i).getStudentone().getStudentoneXw()));
				}
				//政治面貌
				if(list.get(i).getStudentone().getStudentoneZzmm()!=null && list.get(i).getStudentone().getStudentoneZzmm()!="" ){
					list.get(i).getStudentone().setStudentoneZzmm(this.zhuanYi("AT", list.get(i).getStudentone().getStudentoneZzmm()));
				}
				//用工形式
				if(list.get(i).getStudentone().getStudentoneYgxs()!=null && list.get(i).getStudentone().getStudentoneYgxs()!="" ){
					list.get(i).getStudentone().setStudentoneYgxs(this.zhuanYi("XA", list.get(i).getStudentone().getStudentoneYgxs()));
				}
				//毕肄业
				if(list.get(i).getStudentone().getStudentoneByy()!=null && list.get(i).getStudentone().getStudentoneByy()!="" ){
					list.get(i).getStudentone().setStudentoneByy(this.zhuanYi("HY", list.get(i).getStudentone().getStudentoneByy()));
				}

			}
			//---------------------------------
			if (list.get(i).getStudenttwo()!=null) {
				//职务类别
				if(list.get(i).getStudenttwo().getStudenttwoZwlb()!=null && list.get(i).getStudenttwo().getStudenttwoZwlb()!="" ){
					String ss = this.zhuanYi("BQ", list.get(i).getStudenttwo().getStudenttwoZwlb());
					list.get(i).getStudenttwo().setStudenttwoZwlb(ss);
				}
				//待遇级别
				if(list.get(i).getStudenttwo().getStudenttwoDyjb()!=null && list.get(i).getStudenttwo().getStudenttwoDyjb()!="" ){
					list.get(i).getStudenttwo().setStudenttwoDyjb(this.zhuanYi("OZ", list.get(i).getStudenttwo().getStudenttwoDyjb()));
				}
				//职务属性
				if(list.get(i).getStudenttwo().getStudenttwoZwsx()!=null && list.get(i).getStudenttwo().getStudenttwoZwsx()!="" ){
					list.get(i).getStudenttwo().setStudenttwoZwsx(this.zhuanYi("IR", list.get(i).getStudenttwo().getStudenttwoZwsx()));
				}
				//专业类别
				if(list.get(i).getStudenttwo().getStudenttwoZylb()!=null && list.get(i).getStudenttwo().getStudenttwoZylb()!="" ){
					list.get(i).getStudenttwo().setStudenttwoZylb(this.zhuanYi("YC", list.get(i).getStudenttwo().getStudenttwoZylb()));
				}
				//工人技术资格
				if(list.get(i).getStudenttwo().getStudenttwoGrjszg()!=null && list.get(i).getStudenttwo().getStudenttwoGrjszg()!="" ){
					list.get(i).getStudenttwo().setStudenttwoGrjszg(this.zhuanYi("XJ", list.get(i).getStudenttwo().getStudenttwoGrjszg()));
				}
				//异常类别
				if(list.get(i).getStudenttwo().getStudenttwoYclb()!=null && list.get(i).getStudenttwo().getStudenttwoYclb()!="" ){
					list.get(i).getStudenttwo().setStudenttwoYclb(this.zhuanYi("CA", list.get(i).getStudenttwo().getStudenttwoYclb()));
				}
			}
			//-----------------------------
			if (list.get(i).getStudenthree()!=null) {
				//留学状态
				if(list.get(i).getStudenthree().getStudenthreeLxzt()!=null && list.get(i).getStudenthree().getStudenthreeLxzt()!="" ){
					list.get(i).getStudenthree().setStudenthreeLxzt(this.zhuanYi("XR", list.get(i).getStudenthree().getStudenthreeLxzt()));
				}
				//留学身份
				if(list.get(i).getStudenthree().getStudenthreeLxsf()!=null && list.get(i).getStudenthree().getStudenthreeLxsf()!="" ){
					list.get(i).getStudenthree().setStudenthreeLxsf(this.zhuanYi("XS", list.get(i).getStudenthree().getStudenthreeLxsf()));
				}
				//留学国别
				if(list.get(i).getStudenthree().getStudenthreeLxgb()!=null && list.get(i).getStudenthree().getStudenthreeLxgb()!="" ){
					list.get(i).getStudenthree().setStudenthreeLxgb(this.zhuanYi("AD", list.get(i).getStudenthree().getStudenthreeLxgb()));
				}
				//培训类型
				if(list.get(i).getStudenthree().getStudenthreePxlx()!=null && list.get(i).getStudenthree().getStudenthreePxlx()!="" ){
					list.get(i).getStudenthree().setStudenthreePxlx(this.zhuanYi("YG", list.get(i).getStudenthree().getStudenthreePxlx()));
				}
				//培训渠道
				if(list.get(i).getStudenthree().getStudenthreePxqd()!=null && list.get(i).getStudenthree().getStudenthreePxqd()!="" ){
					list.get(i).getStudenthree().setStudenthreePxqd(this.zhuanYi("XR", list.get(i).getStudenthree().getStudenthreePxqd()));
				}
				//离退类别
				if(list.get(i).getStudenthree().getStudenthreeLtlb()!=null && list.get(i).getStudenthree().getStudenthreeLtlb()!="" ){
					list.get(i).getStudenthree().setStudenthreeLtlb(this.zhuanYi("HD", list.get(i).getStudenthree().getStudenthreeLtlb()));
				}
				//国别及地区
				if(list.get(i).getStudenthree().getStudenthreeGbjdq()!=null && list.get(i).getStudenthree().getStudenthreeGbjdq()!="" ){
					list.get(i).getStudenthree().setStudenthreeGbjdq(this.zhuanYi("HD", list.get(i).getStudenthree().getStudenthreeGbjdq()));
				}
				//领导干部标识
				if(list.get(i).getStudenthree().getStudenthreeLdgbbz()!=null && list.get(i).getStudenthree().getStudenthreeLdgbbz()!="" ){
					list.get(i).getStudenthree().setStudenthreeLdgbbz(this.zhuanYi("OC", list.get(i).getStudenthree().getStudenthreeLdgbbz()));
				}
				//领导后备标识
				if(list.get(i).getStudenthree().getStudenthreeLdhbbz()!=null && list.get(i).getStudenthree().getStudenthreeLdhbbz()!="" ){
					list.get(i).getStudenthree().setStudenthreeLdhbbz(this.zhuanYi("OD", list.get(i).getStudenthree().getStudenthreeLdhbbz()));
				}
				//型号干部标识
				if(list.get(i).getStudenthree().getStudenthreeXhgbbz()!=null && list.get(i).getStudenthree().getStudenthreeXhgbbz()!="" ){
					list.get(i).getStudenthree().setStudenthreeXhgbbz(this.zhuanYi("OE", list.get(i).getStudenthree().getStudenthreeXhgbbz()));
				}
				//离退类别
				if(list.get(i).getStudenthree().getStudenthreeDjsbz()!=null && list.get(i).getStudenthree().getStudenthreeDjsbz()!="" ){
					list.get(i).getStudenthree().setStudenthreeDjsbz(this.zhuanYi("OG", list.get(i).getStudenthree().getStudenthreeDjsbz()));
				}
			}


//			list.get(i).setStudentone(stuone);
//			list.get(i).setStudenttwo(stutwo);
//			list.get(i).setStudenthree(stuthree);


		}

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("studentxyht?mark=0&cj="+cj+"&departmentId="+s.getStudentCompanyid()+"&organizationId="+s.getOrganizationId());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("student",student);
		mv.addObject("companyId",organizationId);
		mv.addObject("pageId",s.getStudentCompanyid());
		mv.addObject("organizationId",s.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		//单位  一二级
		mv.addObject("listOrke",listOrke);
		//部门
		mv.addObject("bm",bm);
		//数据字典
		mv.addObject("ax",ax);//性别
		mv.addObject("am",am);//学历
		mv.addObject("an",an);//学位
		mv.addObject("BF",BF);//健康状况
		mv.addObject("BG",BG);//婚姻状况
		mv.addObject("XA",XA);//用工形式
		mv.addObject("OZ",OZ);//待遇级别
		mv.addObject("AY",AY);//国（境）外侨属标识
		mv.addObject("XD",XD);//涉密等级
		mv.addObject("HY",HY);//毕肄业
		mv.addObject("BQ",BQ);//职务类别
		mv.addObject("IR",IR);//职务属性
		mv.addObject("AK",AK);//党内职务名称（子集）
		mv.addObject("AL",AL);//享受职级待遇
		mv.addObject("YC",YC);//专业类别
		mv.addObject("YB",YB);//专业技术任职资格
		mv.addObject("XJ",XJ);//工人技术资格
		mv.addObject("AT",AT);//政治面貌
		mv.addObject("CA",CA);//异常类别
		mv.addObject("CC",CC);//考核结果
		//		mv.addObject("K8",K8);//表彰级别
		mv.addObject("AR",AR);//处分名称
		mv.addObject("HT",HT);//监察机关直接给予的
		mv.addObject("XR",XR);//留学状态
		mv.addObject("XS",XS);//留学身份
		mv.addObject("YG",YG);//培训类型
		mv.addObject("YH",YH);//培训渠道
		mv.addObject("HD",HD);//离退类别
		mv.addObject("IL",IL);//返聘情况
		mv.addObject("IJ",IJ);//参加各项活动
		mv.addObject("XK",XK);//技能人员称号
		mv.addObject("XZ",XZ);//专家称号
		mv.addObject("AS",AS);//与本人关系
		mv.addObject("BR",BR);//出国（出境）事由
		mv.addObject("OC",OC);//领导干部标识
		mv.addObject("OD",OD);//领导后备标识
		mv.addObject("OE",OE);//型号干部标识
		mv.addObject("OG",OG);//董监事标识
		mv.addObject("IT",IT);//劳动合同类型
		mv.addObject("Q2",Q2);//全日制或在职
		mv.addObject("AB",AB);//籍贯、出生地(子集)
		mv.addObject("AD",AD);//国家名称
		mv.addObject("AE",AE);//民族
		mv.addObject("AJ",AJ);//专业技术名称 聘任专业技术职务名称
		mv.addObject("JA",JA);//工种名称（子集）
		mv.addObject("JATop",JATop);//工种名称（头部）

		mv.setViewName("Students/queryStudents");
		return  mv;
	}

	/**
	 * 转译查询
	 * @param model
	 * @return
	 */
	public String zhuanYi(String dictionaryCodeset,String dictionaryCode){

		Map<String, String> map = new HashMap<String, String>();
		map.put("dictionaryCodeset", dictionaryCodeset);
		map.put("dictionaryCode", dictionaryCode);
		Dictionary zy = dictionaryService.selectDataZyKey(map);

		if(zy!=null  ){
			return zy.getDictionaryDescribe();
		} 
		return "";
	}

	/**
	 * 根据部门查询子部门
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/twoLevels",method=RequestMethod.POST)
	public JSONArray incubatorType(Department department){

		List<Department> type = departmentService.DepartmentUpId(department);

		JSONArray array = new JSONArray();  
		try {  
			for (Department p:type) { 
				JSONObject member = null;   
				member = new JSONObject();    
				member.put("name", p.getEpartmentName());  
				member.put("id", p.getDepartmentId()); 
				array.add(member);
			}  
		} catch (JSONException e) {  
			e.printStackTrace();  
		}  

		return array;

	}
	//================================================
	/**
	 * 根据二级单位查询三级单位
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/twoCompany",method=RequestMethod.POST)
	public JSONArray twoCompany(Organization organization){
		organization.setFwcj(3);
		organization.setOrganizationSjdw(organization.getOrganizationSjdw());
List<Organization> type1 = organizationService.selectOrganizationKe(organization);
		JSONArray array = new JSONArray();  
		try {  
			for (Organization p:type1) { 
				JSONObject member = null;   
				member = new JSONObject();    
				member.put("name", p.getOrganizationDwmc());  
				member.put("id", p.getOrganizationId()); 
				array.add(member);
			}  
		} catch (JSONException e) {  
			e.printStackTrace();  
		}  

		return array;

	}
	
	/**
	 * 根据单位查询部门
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/twoBm",method=RequestMethod.POST)
	public JSONArray twoBm(Department department){
		department.setOrganizationId(department.getOrganizationId());
		department.setEpartmentRank("4");
		List<Department> bm = departmentService.selectbmlist(department);
		JSONArray array = new JSONArray();  
		try {  
			for (Department p:bm) { 
				JSONObject member = null;   
				member = new JSONObject();    
				member.put("name", p.getEpartmentName());  
				member.put("id", p.getDepartmentId()); 
				array.add(member);
			}  
		} catch (JSONException e) {  
			e.printStackTrace();  
		}  

		return array;

	}
	//================================================
	/**
	 * 根据部门查询子部门
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/twoDataJA",method=RequestMethod.POST)
	public JSONArray twoDataJA(Studenttwo studenttwo){
		List<Dictionary> listJA=new ArrayList<Dictionary>();
		List<Dictionary> JATail = dictionaryService.selectDataTailKey("JA");//工种名称（头部）
		for (int i = 0; i < JATail.size(); i++) {
			Dictionary dictionary=new Dictionary();
			if (JATail.get(i).getDictionaryCode().substring(0, 2).equals(studenttwo.getStudenttwoGzmcSx())) {
				dictionary.setDictionaryCode(JATail.get(i).getDictionaryCode());
				dictionary.setDictionaryDescribe(JATail.get(i).getDictionaryDescribe());
				listJA.add(dictionary);
			} 
		}
		JSONArray array = new JSONArray();  
		try {  
			for (Dictionary p:listJA) { 
				JSONObject member = null;   
				member = new JSONObject();    
				member.put("name", p.getDictionaryDescribe());  
				member.put("id", p.getDictionaryCode()); 
				array.add(member);
			}  
		} catch (JSONException e) {  
			e.printStackTrace();  
		}  

		return array;

	}

	/**
	 * 前台登录弹出框
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/loginFrm", method = RequestMethod.GET)
	public ModelAndView loginFrm(HttpSession session) {
		return new ModelAndView("main/login");
	}

	/**
	 * 前台登录弹出框 执行登录
	 * @param student
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/StudentCheckLogin", method = RequestMethod.POST)
	public ModelAndView checkloginFrm(Student student,String code, HttpSession session,
			Model model) {
		Student loginStudent = studentService.checkLogin(student);
		String rand= (String) session.getAttribute("rand"); 

		if(rand.toLowerCase().equals(code.toLowerCase())){
			try {
				if (loginStudent == null) {
					model.addAttribute("message", "用户名或者密码错误！");
				} else {
					session.setAttribute("Student", loginStudent);
					model.addAttribute("message", "登录成功！");
				}
			} catch (Exception e) {
				System.err.println("login操作失败!!");
				model.addAttribute("message", "登录失败,请联系管理员！");
			}		
		}
		else{
			model.addAttribute("message", "验证码错误！");
		}
		return new ModelAndView("main/login", "result", model);
	}
	
	/**
	 * 添加跳转页
	 * @return
	 */
	@RequestMapping(value="/addStudents")
	public ModelAndView addStudents(HttpServletRequest request ,String companyId,HttpSession session,String organizationId,String pageId,Integer cj) {
		ModelAndView mv = new ModelAndView();
		
		department=new Department();
		department.setEpartmentRank("4");
		//		List<Department> bm = departmentService.selectbmlist(department);
		//--------------判断1级单位和二三级单位-----------------
		Account acc = (Account) session.getAttribute("sessionUser");
		if (companyId!=null&&companyId!="") {
			if (companyId.equals("test001")) {
				student.setYs("1");
			}else{
				student.setYs("0");
			}
		}else{
			student.setYs("1");
		}
		if (cj==6) {
			student.setJop(cj+"");
			
		}
		department.setOrganizationId(acc.getAccountCatalog());
		List<Department> bm = departmentService.selectbmlist(department);
		
		//数据字典--------
				List<Dictionary> ax = dictionaryService.selectDataKey("AX");//性别
				List<Dictionary> am = dictionaryService.selectDataKey("AM");//学历
				List<Dictionary> an = dictionaryService.selectDataKey("AN");//学位
				List<Dictionary> BF = dictionaryService.selectDataKey("BF");//健康状况
				List<Dictionary> BG = dictionaryService.selectDataKey("BG");//婚姻状况
				List<Dictionary> XA = dictionaryService.selectDataKey("XA");//用工形式
				List<Dictionary> OZ = dictionaryService.selectDataKey("OZ");//待遇级别
				List<Dictionary> AY = dictionaryService.selectDataKey("AY");//国（境）外侨属标识
				List<Dictionary> XD = dictionaryService.selectDataKey("XD");//涉密等级
				List<Dictionary> HY = dictionaryService.selectDataKey("HY");//毕肄业
				List<Dictionary> BQ = dictionaryService.selectDataKey("BQ");//职务类别
				List<Dictionary> IR = dictionaryService.selectDataKey("IR");//职务属性
				List<Dictionary> AK = dictionaryService.selectDataKey("AK");//党内职务名称（子集）
				List<Dictionary> AL = dictionaryService.selectDataKey("AL");//享受职级待遇	
				List<Dictionary> YC = dictionaryService.selectDataKey("YC");//专业类别	
				List<Dictionary> YB = dictionaryService.selectDataKey("YB");//专业技术任职资格		
				List<Dictionary> XJ = dictionaryService.selectDataKey("XJ");//工人技术资格		
				List<Dictionary> AT = dictionaryService.selectDataKey("AT");//政治面貌		
				List<Dictionary> CA = dictionaryService.selectDataKey("CA");//异常类别		
				List<Dictionary> CC = dictionaryService.selectDataKey("CC");//考核结果		
//				List<Dictionary> K8 = dictionaryService.selectDataKey("K8");//表彰级别
				List<Dictionary> AR = dictionaryService.selectDataKey("AR");//处分名称	
				List<Dictionary> HT = dictionaryService.selectDataKey("HT");//监察机关直接给予的	
				List<Dictionary> XR = dictionaryService.selectDataKey("XR");//留学状态	
				List<Dictionary> XS = dictionaryService.selectDataKey("XS");//留学身份
				List<Dictionary> YG = dictionaryService.selectDataKey("YG");//培训类型	
				List<Dictionary> YH = dictionaryService.selectDataKey("YH");//培训渠道	
				List<Dictionary> HD = dictionaryService.selectDataKey("HD");//离退类别	
				List<Dictionary> IL = dictionaryService.selectDataKey("IL");//返聘情况	
				List<Dictionary> IJ = dictionaryService.selectDataKey("IJ");//参加各项活动
				List<Dictionary> XK = dictionaryService.selectDataKey("XK");//技能人员称号	
				List<Dictionary> XZ = dictionaryService.selectDataKey("XZ");//专家称号	
				List<Dictionary> AS = dictionaryService.selectDataKey("AS");//与本人关系	
				List<Dictionary> BR = dictionaryService.selectDataKey("BR");//出国（出境）事由	
				List<Dictionary> OC = dictionaryService.selectDataKey("OC");//领导干部标识	
				List<Dictionary> OD = dictionaryService.selectDataKey("OD");//领导后备标识	
				List<Dictionary> OE = dictionaryService.selectDataKey("OE");//型号干部标识	
				List<Dictionary> OG = dictionaryService.selectDataKey("OG");//董监事标识	
				List<Dictionary> IT = dictionaryService.selectDataKey("IT");//劳动合同类型	
				List<Dictionary> Q2 = dictionaryService.selectDataKey("Q2");//全日制或在职	
				List<Dictionary> AB = dictionaryService.selectDataKey("AB");//籍贯、出生地(子集)	
				List<Dictionary> AD = dictionaryService.selectDataKey("AD");//国家名称	
				List<Dictionary> AE = dictionaryService.selectDataKey("AE");//民族	
				List<Dictionary> AJ = dictionaryService.selectDataKey("AJ");//专业技术名称 聘任专业技术职务名称	
				List<Dictionary> JA = dictionaryService.selectDataKey("JA");//工种名称（子集）
				List<Dictionary> JATop = dictionaryService.selectDataTopKey("JA");//工种名称（头部）
				//---------------
				//数据字典
				mv.addObject("ax",ax);//性别
				mv.addObject("am",am);//学历
				mv.addObject("an",an);//学位
				mv.addObject("BF",BF);//健康状况
				mv.addObject("BG",BG);//婚姻状况
				mv.addObject("XA",XA);//用工形式
				mv.addObject("OZ",OZ);//待遇级别
				mv.addObject("AY",AY);//国（境）外侨属标识
				mv.addObject("XD",XD);//涉密等级
				mv.addObject("HY",HY);//毕肄业
				mv.addObject("BQ",BQ);//职务类别
				mv.addObject("IR",IR);//职务属性
				mv.addObject("AK",AK);//党内职务名称（子集）
				mv.addObject("AL",AL);//享受职级待遇
				mv.addObject("YC",YC);//专业类别
				mv.addObject("YB",YB);//专业技术任职资格
				mv.addObject("XJ",XJ);//工人技术资格
				mv.addObject("AT",AT);//政治面貌
				mv.addObject("CA",CA);//异常类别
				mv.addObject("CC",CC);//考核结果
//				mv.addObject("K8",K8);//表彰级别
				mv.addObject("AR",AR);//处分名称
				mv.addObject("HT",HT);//监察机关直接给予的
				mv.addObject("XR",XR);//留学状态
				mv.addObject("XS",XS);//留学身份
				mv.addObject("YG",YG);//培训类型
				mv.addObject("YH",YH);//培训渠道
				mv.addObject("HD",HD);//离退类别
				mv.addObject("IL",IL);//返聘情况
				mv.addObject("IJ",IJ);//参加各项活动
				mv.addObject("XK",XK);//技能人员称号
				mv.addObject("XZ",XZ);//专家称号
				mv.addObject("AS",AS);//与本人关系
				mv.addObject("BR",BR);//出国（出境）事由
				mv.addObject("OC",OC);//领导干部标识
				mv.addObject("OD",OD);//领导后备标识
				mv.addObject("OE",OE);//型号干部标识
				mv.addObject("OG",OG);//董监事标识
				mv.addObject("IT",IT);//劳动合同类型
				mv.addObject("Q2",Q2);//全日制或在职
				mv.addObject("AB",AB);//籍贯、出生地(子集)
				mv.addObject("AD",AD);//国家名称
				mv.addObject("AE",AE);//民族
				mv.addObject("AJ",AJ);//专业技术名称 聘任专业技术职务名称
				mv.addObject("JA",JA);//工种名称（子集）
				mv.addObject("JATop",JATop);//工种名称（头部）
				mv.addObject("cj",cj);	
				mv.addObject("organizationId",organizationId);	
				mv.addObject("companyId",companyId);
				mv.addObject("pageId",pageId);	
				
				//部门
				mv.addObject("bm",bm);
		mv.setViewName("Students/addStudents");
		
		return mv;
	}
	/**
	 * 人员录入保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/studentSava", method = RequestMethod.POST)
	public ModelAndView studentSava(String organizationId,String companyId,int cj,Student student,Studentone studentone,Studenttwo studenttwo,Studenthree studenthree,HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		Account acc = (Account)session.getAttribute("sessionUser");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Student ifst = studentService.queryStudentByStudentSEQ(student.getStudentSeq());
		List<Studentone> idCarList = studentoneService.queryStudentBySFZH(studentone);
		if(idCarList.size()>0){
			out.print("99999");
			return null;
		}else{
		if(ifst!=null){
			out.print("1");
			return null;
		
		}else{
			studenthree.setStudenthreeId(Tools.getGUID());   
			studenthree.setStudenthreeAt(sdf.parse(sdf.format(new Date())));
			studenthree.setStudenthreeDel(1);
			int sthree = studenthreeService.insert(studenthree);
			
			if(sthree>0){
				studenttwo.setStudenttwoId(Tools.getGUID());
				studenttwo.setStudenttwoAt2(sdf.parse(sdf.format(new Date())));
				studenttwo.setStudenttwoDel2(1);
				int stwo = studenttwoService.insert(studenttwo);
				
				if(stwo>0){
					studentone.setStudentoneId(Tools.getGUID());
					studentone.setStudentoneName(student.getStudentName());
					studentone.setStudentoneAt(sdf.parse(sdf.format(new Date())));
					studentone.setStudentoneDel(1);
					int sone = studentoneService.insert(studentone);
					
					if(sone>0){
						student.setStudentId(Tools.getGUID());
						student.setStudentAt(sdf.parse(sdf.format(new Date())));
						student.setStudentDel(1);
						student.setStudentCompanyid(companyId);
						if(student.getStudentDepartments()==null|| student.getStudentDepartments().equals("0")){
							student.setStudentDepartment(student.getStudentDepartment());
						}else{
							student.setStudentDepartment(student.getStudentDepartments());
							
						}

						student.setStudentoneId(studentone.getStudentoneId());
						student.setStudenttwoId(studenttwo.getStudenttwoId());
						student.setStudenthreeId(studenthree.getStudenthreeId());
						int st = studentService.insert(student);
						if(st>0){
							log.setLogAt(sdf.format(new Date()));
							log.setLogContent("用户："+acc.getAccountLoginid()+"，人员添加。学员id："+student.getStudentId());
							log.setLogId(Tools.getGUID());
							log.setLogIp(HYLUtil.getIP4(request));
							log.setLogUser(acc.getAccountLoginid());
							log.setLogUserType("后台用户");
							logService.insertSelective(log);

							out.print("2");
							return null;
						}else{
							out.print("3");
							return null;
						}
					}else{
						out.print("3");
						return null;
						
					}
				}else{
					out.print("3");
					return null;
				}
				
			}else{
				out.print("3");
				return null;
				
			}
		}
		
		
	}
	}
	/**
	 * 修改跳转页
	 * @return
	 */
	@RequestMapping(value="/updateStudent")
	public ModelAndView updateStudent(String id ,String companyId,HttpServletRequest request ,String pageId,String organizationId,Integer cj,int pan) {
		ModelAndView mv = new ModelAndView();
		Student student = studentService.selectByPrimaryKey(id);
		Studentone studentone = studentoneService.selectByPrimaryKey(student.getStudentoneId());
		Studenttwo studenttwo = studenttwoService.selectByPrimaryKey(student.getStudenttwoId());
		Studenthree studenthree = studenthreeService.selectByPrimaryKey(student.getStudenthreeId());
		
		
		//数据字典--------
				List<Dictionary> ax = dictionaryService.selectDataKey("AX");//性别
				List<Dictionary> am = dictionaryService.selectDataKey("AM");//学历
				List<Dictionary> an = dictionaryService.selectDataKey("AN");//学位
				List<Dictionary> BF = dictionaryService.selectDataKey("BF");//健康状况
				List<Dictionary> BG = dictionaryService.selectDataKey("BG");//婚姻状况
				List<Dictionary> XA = dictionaryService.selectDataKey("XA");//用工形式
				List<Dictionary> OZ = dictionaryService.selectDataKey("OZ");//待遇级别
				List<Dictionary> AY = dictionaryService.selectDataKey("AY");//国（境）外侨属标识
				List<Dictionary> XD = dictionaryService.selectDataKey("XD");//涉密等级
				List<Dictionary> HY = dictionaryService.selectDataKey("HY");//毕肄业
				List<Dictionary> BQ = dictionaryService.selectDataKey("BQ");//职务类别
				List<Dictionary> IR = dictionaryService.selectDataKey("IR");//职务属性
				List<Dictionary> AK = dictionaryService.selectDataKey("AK");//党内职务名称（子集）
				List<Dictionary> AL = dictionaryService.selectDataKey("AL");//享受职级待遇	
				List<Dictionary> YC = dictionaryService.selectDataKey("YC");//专业类别	
				List<Dictionary> YB = dictionaryService.selectDataKey("YB");//专业技术任职资格		
				List<Dictionary> XJ = dictionaryService.selectDataKey("XJ");//工人技术资格		
				List<Dictionary> AT = dictionaryService.selectDataKey("AT");//政治面貌		
				List<Dictionary> CA = dictionaryService.selectDataKey("CA");//异常类别		
				List<Dictionary> CC = dictionaryService.selectDataKey("CC");//考核结果		
//				List<Dictionary> K8 = dictionaryService.selectDataKey("K8");//表彰级别
				List<Dictionary> AR = dictionaryService.selectDataKey("AR");//处分名称	
				List<Dictionary> HT = dictionaryService.selectDataKey("HT");//监察机关直接给予的	
				List<Dictionary> XR = dictionaryService.selectDataKey("XR");//留学状态	
				List<Dictionary> XS = dictionaryService.selectDataKey("XS");//留学身份
				List<Dictionary> YG = dictionaryService.selectDataKey("YG");//培训类型	
				List<Dictionary> YH = dictionaryService.selectDataKey("YH");//培训渠道	
				List<Dictionary> HD = dictionaryService.selectDataKey("HD");//离退类别	
				List<Dictionary> IL = dictionaryService.selectDataKey("IL");//返聘情况	
				List<Dictionary> IJ = dictionaryService.selectDataKey("IJ");//参加各项活动
				List<Dictionary> XK = dictionaryService.selectDataKey("XK");//技能人员称号	
				List<Dictionary> XZ = dictionaryService.selectDataKey("XZ");//专家称号	
				List<Dictionary> AS = dictionaryService.selectDataKey("AS");//与本人关系	
				List<Dictionary> BR = dictionaryService.selectDataKey("BR");//出国（出境）事由	
				List<Dictionary> OC = dictionaryService.selectDataKey("OC");//领导干部标识	
				List<Dictionary> OD = dictionaryService.selectDataKey("OD");//领导后备标识	
				List<Dictionary> OE = dictionaryService.selectDataKey("OE");//型号干部标识	
				List<Dictionary> OG = dictionaryService.selectDataKey("OG");//董监事标识	
				List<Dictionary> IT = dictionaryService.selectDataKey("IT");//劳动合同类型	
				List<Dictionary> Q2 = dictionaryService.selectDataKey("Q2");//全日制或在职	
				List<Dictionary> AB = dictionaryService.selectDataKey("AB");//籍贯、出生地(子集)	
				List<Dictionary> AD = dictionaryService.selectDataKey("AD");//国家名称	
				List<Dictionary> AE = dictionaryService.selectDataKey("AE");//民族	
				List<Dictionary> AJ = dictionaryService.selectDataKey("AJ");//专业技术名称 聘任专业技术职务名称	
				List<Dictionary> JA = dictionaryService.selectDataKey("JA");//工种名称（子集）
				List<Dictionary> JATop = dictionaryService.selectDataTopKey("JA");//工种名称（头部）
				//---------------
				//数据字典
				mv.addObject("ax",ax);//性别
				mv.addObject("am",am);//学历
				mv.addObject("an",an);//学位
				mv.addObject("BF",BF);//健康状况
				mv.addObject("BG",BG);//婚姻状况
				mv.addObject("XA",XA);//用工形式
				mv.addObject("OZ",OZ);//待遇级别
				mv.addObject("AY",AY);//国（境）外侨属标识
				mv.addObject("XD",XD);//涉密等级
				mv.addObject("HY",HY);//毕肄业
				mv.addObject("BQ",BQ);//职务类别
				mv.addObject("IR",IR);//职务属性
				mv.addObject("AK",AK);//党内职务名称（子集）
				mv.addObject("AL",AL);//享受职级待遇
				mv.addObject("YC",YC);//专业类别
				mv.addObject("YB",YB);//专业技术任职资格
				mv.addObject("XJ",XJ);//工人技术资格
				mv.addObject("AT",AT);//政治面貌
				mv.addObject("CA",CA);//异常类别
				mv.addObject("CC",CC);//考核结果
//				mv.addObject("K8",K8);//表彰级别
				mv.addObject("AR",AR);//处分名称
				mv.addObject("HT",HT);//监察机关直接给予的
				mv.addObject("XR",XR);//留学状态
				mv.addObject("XS",XS);//留学身份
				mv.addObject("YG",YG);//培训类型
				mv.addObject("YH",YH);//培训渠道
				mv.addObject("HD",HD);//离退类别
				mv.addObject("IL",IL);//返聘情况
				mv.addObject("IJ",IJ);//参加各项活动
				mv.addObject("XK",XK);//技能人员称号
				mv.addObject("XZ",XZ);//专家称号
				mv.addObject("AS",AS);//与本人关系
				mv.addObject("BR",BR);//出国（出境）事由
				mv.addObject("OC",OC);//领导干部标识
				mv.addObject("OD",OD);//领导后备标识
				mv.addObject("OE",OE);//型号干部标识
				mv.addObject("OG",OG);//董监事标识
				mv.addObject("IT",IT);//劳动合同类型
				mv.addObject("Q2",Q2);//全日制或在职
				mv.addObject("AB",AB);//籍贯、出生地(子集)
				mv.addObject("AD",AD);//国家名称
				mv.addObject("AE",AE);//民族
				mv.addObject("AJ",AJ);//专业技术名称 聘任专业技术职务名称
				mv.addObject("JA",JA);//工种名称（子集）
				mv.addObject("JATop",JATop);//工种名称（头部）
		if(studenttwo!=null){
 		if(studenttwo.getStudenttwoGzmc()!=null && studenttwo.getStudenttwoGzmc().length()>0){
			for(int i = 0 ; i<JATop.size() ;i++){
				if(JATop.get(i).getDictionaryCode().equals(studenttwo.getStudenttwoGzmc().substring(0,2))){
					studenttwo.setStudenttwoGzmcSx(JATop.get(i).getDictionaryCode());
				}
			}
		}
		}	
		mv.addObject("student",student);
		mv.addObject("studentone",studentone);
		mv.addObject("studenttwo",studenttwo);
		mv.addObject("studenthree",studenthree);
		mv.addObject("cj",cj);
		mv.addObject("pageId",pageId);
		mv.addObject("organizationId",organizationId);
		mv.addObject("companyId",companyId);
		if (pan==1) {
			mv.setViewName("Students/detailedStudents");
		} else {
			mv.setViewName("Students/updateStudents");
		}
		
		
		return mv;
	}
	
	/**
	 * 人员录入保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateStudentSava", method = RequestMethod.POST)
	public ModelAndView updateStudentSava(int cj,String companyId,String organizationId,Student student,Studentone studentone,Studenttwo studenttwo,Studenthree studenthree,HttpSession session, HttpServletResponse response,HttpServletRequest request) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		int sthree = studenthreeService.updateByPrimaryKeySelective(studenthree);
		
		if(sthree>0){
			
			int stwo = studenttwoService.updateByPrimaryKeySelective(studenttwo);
			
			if(stwo>0){

				studentone.setStudentoneName(student.getStudentName());
				int sone = studentoneService.updateByPrimaryKeySelective(studentone);
				
				if(sone>0){
					
					int st = studentService.updateByPrimaryKeySelective(student);
					if(st>0){
						log.setLogAt(sdf.format(new Date()));
						log.setLogContent("用户："+acc.getAccountLoginid()+"，人员修改。学员id："+student.getStudentId());
						log.setLogId(Tools.getGUID());
						log.setLogIp(HYLUtil.getIP4(request));
						log.setLogUser(acc.getAccountLoginid());
						log.setLogUserType("后台用户");
						logService.insertSelective(log);
						
						out.print("<script>alert('修改成功！')</script>");
						out.print("<script>window.close()</script>");
						return new ModelAndView("redirect:/studentxyht?mark=1&page=1&cj="+cj+"&organizationId="+companyId);
						
					}else{
						out.print("<script>alert('修改失败！')</script>");
						out.print("<script>window.close()</script>");
					}
					
					
				}else{
					out.print("<script>alert('修改失败！')</script>");
					out.print("<script>window.close()</script>");
				}
				
				
			}else{
				out.print("<script>alert('修改失败！')</script>");
				out.print("<script>window.close()</script>");
			}
			
		}else{
			out.print("<script>alert('修改失败！')</script>");
			out.print("<script>window.close()</script>");
			
		}
		
		out.flush();
		return null;
		
		
	}
	


	/**
	 * 导入学员信息
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/StudentsImport", method = RequestMethod.GET)
	public ModelAndView StudentsImport(HttpSession session) {
		return new ModelAndView("Students/StudentsImport");
	}


	/**
	 * 导入学员信息
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/StudentsImportAdd")
	public ModelAndView StudentsImportAdd(@RequestParam("excel") MultipartFile excel,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		String ch = "";
		
		Account acc = (Account)session.getAttribute("sessionUser");
		HSSFWorkbook workbook = null;
		int mark = 0; //标记
		try {
			workbook = new HSSFWorkbook(excel.getInputStream());// 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			Iterator<Row> rows1 = sheet.rowIterator();// 将Excel行数据装入迭代器
			int j = 0;
			while (rows.hasNext())//不能有空值
			{
				j++;

					Row row = (Row) rows.next(); // 获取一行数据

					if (j <= 1)
						continue;
					if (row.getCell(0).toString().trim().length() == 0)
						break;

					for(int k = 0 ; k<=83;k++){
						if(row.getCell(k)==null){
							mark=1;
							break;
						}
						
						if(k==0){
							Student s = new Student();// 创建实体接收数据
							s.setStudentId(RandomGUIDUtil.generateKey());
							s.setStudentSeq(ToDBC(row.getCell(0).toString()));//判断
							int count = studentService.studentByNameSeq(s);
							if(count>0){
								ch = "导入失败，账号("+s.getStudentSeq()+")已存在或有字段为空";
								mark=1;
								break;
							}
						}
						
						if(k==6){
							//身份证判断
							Studentone so1 = new Studentone();
							Cell cell1=row.getCell(6);  
							cell1.setCellType(Cell.CELL_TYPE_STRING);  
							so1.setStudentoneSfzh(cell1.getStringCellValue());
							List<Studentone> listson = studentoneService.queryStudentBySFZH(so1);
							if(listson.size()>0){
								ch = "导入失败，身份证("+so1.getStudentoneSfzh()+")已存在或有字段为空";
								mark=1;
								break;
							}
						}
						
					}
					if(mark==1){
						break;
					}
				

			}

			//判断是否有值等于NULL，如果等于NULL不执行添加
			if(mark==0){
				int i = 0;
				while (rows1.hasNext())
				{
					i++;
					
					Row row1 = (Row) rows1.next(); // 获取一行数据
					if (i <= 1)
						continue;
					if (row1.getCell(0).toString().trim().length() == 0)
						break;
					Dictionary dd = new Dictionary();
					// 迭代Excel 行
					//-----------学员附属表1---------------------------------------
					Studentone so = new Studentone();
					so.setStudentoneId(RandomGUIDUtil.generateKey());
					Cell cell1=row1.getCell(6);  
					cell1.setCellType(Cell.CELL_TYPE_STRING);  
					so.setStudentoneSfzh(cell1.getStringCellValue());
					
					dd.setDictionaryCodeset("AX");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(7).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						so.setStudentoneXb(dd.getDictionaryCode());//性别 (密)
					}
					cell1=row1.getCell(8);  
					cell1.setCellType(Cell.CELL_TYPE_STRING);  
					so.setStudentoneAge(Integer.parseInt(cell1.getStringCellValue()));
					dd = new Dictionary();
					dd.setDictionaryCodeset("AM");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(9).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						so.setStudentoneXl(dd.getDictionaryCode());//学历 (密)
					}
					dd = new Dictionary();
					dd.setDictionaryCodeset("AN");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(10).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						so.setStudentoneXw(dd.getDictionaryCode());//学位 (密)
					}
					dd = new Dictionary();
					dd.setDictionaryCodeset("AT");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(11).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						so.setStudentoneZzmm(dd.getDictionaryCode());//政治面貌 (密)
					}
					dd = new Dictionary();
					dd.setDictionaryCodeset("XA");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(12).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						so.setStudentoneYgxs(dd.getDictionaryCode());//用工形式 (密)
					}
					
					so.setStudentoneXzjb(ToDBC(row1.getCell(13).toString()));
					so.setStudentoneZyjszw(ToDBC(row1.getCell(14).toString()));
					so.setStudentoneGrjszw(ToDBC(row1.getCell(15).toString()));
					so.setStudentoneSxzy(ToDBC(row1.getCell(16).toString()));
					dd = new Dictionary();
					dd.setDictionaryCodeset("HY");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(17).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						so.setStudentoneByy(dd.getDictionaryCode());//毕肄业 (密)
					}
					
					so.setStudentoneByyxx(ToDBC(row1.getCell(18).toString()));
					so.setStudentoneYx(ToDBC(row1.getCell(19).toString()));
					so.setStudentoneXwsydw(ToDBC(row1.getCell(20).toString()));
					dd = new Dictionary();
					dd.setDictionaryCodeset("Q2");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(21).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						so.setStudentoneQrzhzz(dd.getDictionaryCode());//全日制或在职 (密)
					}
					
					if(ToDBC(row1.getCell(22).toString()).equals("是")){
						so.setStudentoneSfdqxl("1");
					}else{
						so.setStudentoneSfdqxl("2");
					}
					if(ToDBC(row1.getCell(23).toString()).equals("是")){
						so.setStudentoneSfdqxl("1");
					}else{
						so.setStudentoneSfdqxl("2");
					}
					so.setStudentoneAt(new Date());
					so.setStudentoneDel(1);
					so.setStudentoneCsri(getCell(row1.getCell(24)));
					so.setStudentoneCjgzsj(getCell(row1.getCell(25)));
					so.setStudentoneXdwgzsj(getCell(row1.getCell(26)));
					so.setStudentoneRxsj(getCell(row1.getCell(27)));
					so.setStudentoneByysj(getCell(row1.getCell(28)));


					//-----------学员附属表2---------------------------------------
					Studenttwo st = new Studenttwo();
					st.setStudenttwoId(RandomGUIDUtil.generateKey());
					dd = new Dictionary();
					dd.setDictionaryCodeset("BQ");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(29).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoZwlb(dd.getDictionaryCode());//职务类别（密）
					}
					
					st.setStudenttwoXzzwmc(ToDBC(row1.getCell(30).toString()));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("AK");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(31).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoDnzwmc(dd.getDictionaryCode());//党内职务名称（密）
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("OZ");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(32).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoDyjb(dd.getDictionaryCode());//待遇级别(密)
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("IR");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(33).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoZwsx(dd.getDictionaryCode());//职务属性（密）
					}

					if(ToDBC(row1.getCell(34).toString()).equals("是")){
						so.setStudentoneSfdqxl("1");
					}else{
						so.setStudentoneSfdqxl("2");
					}
					
					if(ToDBC(row1.getCell(35).toString()).equals("是")){
						so.setStudentoneSfdqxl("1");
					}else{
						so.setStudentoneSfdqxl("2");
					}

					st.setStudenttwoZyjszgmc(ToDBC(row1.getCell(36).toString()));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("YB");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(37).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoZyjsrzzg(dd.getDictionaryCode());//专业技术任职资格（密
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("YC");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(38).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoZylb(dd.getDictionaryCode());//专业类别（密）
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("AJ");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(39).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoPrzyjszwmc(dd.getDictionaryCode());//聘任专业技术职务名称（密）
					}
					
					if(ToDBC(row1.getCell(40).toString()).equals("是")){
						so.setStudentoneSfdqxl("1");
					}else{
						so.setStudentoneSfdqxl("2");
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("JA");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(41).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoGzmc(dd.getDictionaryCode());//工种名称（密）
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("XJ");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(42).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoGrjszg(dd.getDictionaryCode());//工人技术资格（密
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("XJ");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(43).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoPrgrjszw(dd.getDictionaryCode());//聘任工人技术职务（密）
					}
					
					st.setStudenttwoCsgzhdrzw(ToDBC(row1.getCell(44).toString()));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("CA");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(45).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoYclb(dd.getDictionaryCode());//异常类别（密）
					}
					
					st.setStudenttwoYcyy(ToDBC(row1.getCell(46).toString()));
					st.setStudenttwoAt2(new Date());
					st.setStudenttwoDel2(1);
					st.setStudenttwoRzsj(getCell(row1.getCell(47)));
					st.setStudenttwoMzsj(getCell(row1.getCell(48)));
					st.setStudenttwoQdsj(getCell(row1.getCell(49)));
					st.setStudenttwoPrqssj(getCell(row1.getCell(50)));
					st.setStudenttwoPrzzsj(getCell(row1.getCell(51)));
					st.setStudenttwoQdzgsj(getCell(row1.getCell(52)));
					st.setStudenttwoPrsj(getCell(row1.getCell(53)));
					st.setStudenttwoGrjlqssj(getCell(row1.getCell(54)));
					st.setStudenttwoGrjljssj(getCell(row1.getCell(55)));
					st.setStudenttwoCjdpsj(getCell(row1.getCell(56)));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("JA");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(57).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						st.setStudenttwoGzmcSx(dd.getDictionaryCode());//工种名称筛选（密）
					}
					
					
					
					//-----------学员附属表3---------------------------------------
					Studenthree sth   =  new Studenthree();
					sth.setStudenthreeId(RandomGUIDUtil.generateKey());
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("XR");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(58).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeLxzt(dd.getDictionaryCode());// 留学状态(密)
					}
					
					sth.setStudenthreePcdw(ToDBC(row1.getCell(59).toString()));
					sth.setStudenthreeZy(ToDBC(row1.getCell(60).toString()));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("XS");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(61).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeLxsf(dd.getDictionaryCode());//留学身份(密)
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("AD");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(62).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeLxgb(dd.getDictionaryCode()); //留学国别（国家名称？）(密)
					}
					
					sth.setStudenthreePxxmmc(ToDBC(row1.getCell(63).toString()));
					sth.setStudenthreePxts(getCell(row1.getCell(64)));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("YG");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(65).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreePxlx(dd.getDictionaryCode());//培训类型(密)
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("YH");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(66).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreePxqd(dd.getDictionaryCode());//培训渠道(密)
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("HD");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(67).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeLtlb(dd.getDictionaryCode());//离退类别(密)
					}
					
					sth.setStudenthreeGbjdq(ToDBC(row1.getCell(68).toString()));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("BR");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(69).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeCgsy(dd.getDictionaryCode());//出国（境）事由(密)
					}
					
					sth.setStudenthreeCgsqdw(ToDBC(row1.getCell(70).toString()));
					sth.setStudenthreeCgpcdw(ToDBC(row1.getCell(71).toString()));
					sth.setStudenthreeBj(ToDBC(row1.getCell(72).toString()));
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("OC");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(73).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeLdgbbz(dd.getDictionaryCode());//领导干部标识	(密)
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("OD");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(74).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeLdhbbz(dd.getDictionaryCode());//领导后备标识(密)
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("OE");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(75).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeXhgbbz(dd.getDictionaryCode());//型号干部标识(密)
					}
					
					dd = new Dictionary();
					dd.setDictionaryCodeset("OG");
					dd.setDictionaryDescribe(ToDBC(row1.getCell(76).toString()));
					dd = dictionaryService.selectDescribe(dd);
					if(dd!=null){
						sth.setStudenthreeDjsbz(dd.getDictionaryCode());//董监事标识(密)
					}
					
					sth.setStudenthreeAt(new Date());
					sth.setStudenthreeDel(1);
					sth.setStudenthreeLxsj(getCell(row1.getCell(77)));
					sth.setStudenthreeLtxsj(getCell(row1.getCell(78)));
					sth.setStudenthreeCgsj(getCell(row1.getCell(79)));
					sth.setStudenthreeJhhgsj(getCell(row1.getCell(80)));
					sth.setStudenthreeSjhgsj(getCell(row1.getCell(81)));


					//-----------------学员表---------------------------------------
					Student s = new Student();// 创建实体接收数据
					s.setStudentId(RandomGUIDUtil.generateKey());
					s.setStudentSeq(ToDBC(row1.getCell(0).toString()));//判断
					s.setStudentName(ToDBC(row1.getCell(1).toString()));
					s.setStudentCategory(ToDBC(row1.getCell(2).toString()));
					s.setStudentRank(ToDBC(row1.getCell(3).toString()));
					s.setStudentType(1);//改2游离
					s.setStudentCompanyid(getCell(row1.getCell(82)));//新加单位ID
					s.setStudentDepartment(getCell(row1.getCell(83)));//新加部门ID
					Cell cell=row1.getCell(4);  
					cell.setCellType(Cell.CELL_TYPE_STRING);  
					s.setStudentTelephone(cell.getStringCellValue());
					s.setStudentEmail(ToDBC(row1.getCell(5).toString()));
					s.setStudentPassword("123456");	
					s.setStudentAt(new Date());
					s.setStudentDel(1);
					//-----------------学员表---------------------------------------
//					int count = studentService.studentByNameSeq(s);
//					if(count==0){//判断数据库中账号是否存在
						int soi = studentoneService.insertSelective(so);
						int sti = studenttwoService.insertSelective(st);
						int sthi = studenthreeService.insertSelective(sth);
						if(soi>0 && sti>0 && sthi>0){
							s.setStudenttwoId(st.getStudenttwoId());
							s.setStudentoneId(so.getStudentoneId());
							s.setStudenthreeId(sth.getStudenthreeId());
							studentService.insertSelective(s);
							
							log.setLogAt(sdf.format(new Date()));
							log.setLogContent("用户："+acc.getAccountLoginid()+"，导入学员。");
							log.setLogId(Tools.getGUID());
							log.setLogIp(HYLUtil.getIP4(request));
							log.setLogUser(acc.getAccountLoginid());
							log.setLogUserType("后台用户");
							logService.insertSelective(log);
						}

//					}else{
//						PrintWriter out = null;
//						out = response.getWriter();
//						out.print("<script>alert('导入失败，身份证和账号已存在或有字段为空')</script>");
//						out.flush();
//						return new ModelAndView("Students/StudentsImport");
//					}

				}
			}else{
				PrintWriter out = null;
				out = response.getWriter();
				out.print("<script>alert('"+ch+"')</script>");
				out.flush();
				return new ModelAndView("Students/StudentsImport");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return new ModelAndView("redirect:studentxyht?mark=1&page=1&cj=6");
	}

	/**
	 * 导入字符串处理
	 * @param strSttg
	 * @return
	 */
	public static String ToDBC(String strSttg)
	{
		//strSttg = strSttg.replaceAll(".0", "");
		return strSttg.replaceAll("\\s", "").replaceAll("\u3000", "");
	}

	/**
	 * 导入日期格式
	 * @param cell
	 * @return
	 */
	public String getCell(Cell cell) {
		DecimalFormat df = new DecimalFormat("#");
		if (cell == null)
			return "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC:
			if(HSSFDateUtil.isCellDateFormatted(cell)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				return sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())).toString();
			}
			return df.format(cell.getNumericCellValue());
		case HSSFCell.CELL_TYPE_STRING:
			System.out.println(cell.getStringCellValue());
			return cell.getStringCellValue();
		case HSSFCell.CELL_TYPE_FORMULA:
			return cell.getCellFormula();
		case HSSFCell.CELL_TYPE_BLANK:
			return "";
		case HSSFCell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue() + "";
		case HSSFCell.CELL_TYPE_ERROR:
			return cell.getErrorCellValue() + "";
		}
		return "";
	}

}