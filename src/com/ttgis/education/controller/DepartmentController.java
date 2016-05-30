package com.ttgis.education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Department;
import com.ttgis.education.entity.Dictionary;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Sort;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.DepartmentService;
import com.ttgis.education.service.DictionaryService;
import com.ttgis.education.service.LogService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.SortService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.Tools;

/**
 * 部门控制层
 * @author 陈健龙	
 *
 */

@Controller
public class DepartmentController {
	@Resource
	private DictionaryService dictionaryService;

	@Resource
	private DepartmentService departmentService;

	@Resource
	private OrganizationService organizationService;

	@Resource
	private SortService sortService;

	@Resource
	private StudentService studentService;
	
	@Resource
	LogService logService;
	Log log = new Log();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private Department department;

	/**
	 * 查询部门
	 * @return
	 */
	@RequestMapping(value = "/DepartmentAll")
	public ModelAndView DepartmentAll(Department n,int mark ,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark>0){
			department =new Department();
		}

		department.setOrganizationId(n.getOrganizationId());

		//---------------------条件查询----------------------------------
		if(n.getEpartmentName()!=null && n.getEpartmentName()!="" ){
			department.setEpartmentName(n.getEpartmentName());
		}
		if(n.getEpartmentRank().equals("4") ){
			department.setEpartmentRank(n.getEpartmentRank());
		}else if(n.getEpartmentRank().equals("5") ){
			department.setEpartmentRank(n.getEpartmentRank());
			department.setEpartmentUpid(n.getEpartmentUpid());
		}
		

		PageBean pageBean = new PageBean();
		int allRow = departmentService.DepartmentCount(department);
		if(allRow>0||department.getEpartmentRank().equals("4")){
			final int length = 20;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			department.setPage(length*(currentPage1-1));
			department.setSize(length);

			List<Department> list = departmentService.selectPageAll(department);

			pageBean.setPageSize(20);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("DepartmentAll?mark=0&epartmentRank="+n.getEpartmentRank()+"&organizationId="+n.getOrganizationId());//连接地址
			pageBean.init();
			mv.addObject("department",department);
			mv.addObject("pageNewsId",n.getOrganizationId());
			mv.addObject("pageBean",pageBean);
			mv.addObject("mark",mark);
			mv.setViewName("department/queryDepartment");
		}else{
			mv.addObject("department",department);
			mv.setViewName("redirect:studentsDepartmentAll?page=1&studentCompanyid="+n.getOrganizationId()+"&studentDepartment="+n.getEpartmentUpid());
			
		}
		
		return  mv;
	}


	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/departmentSkipID")
	public ModelAndView departmentSkipID(Department n,String id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Organization organization =organizationService.selectByPrimaryKey(n.getOrganizationId());
		mv.addObject("organization",organization);
		if(n.getEpartmentRank().equals("5")){
			Department dd = departmentService.selectByPrimaryKey(n.getEpartmentUpid());
			mv.addObject("dd",dd);
		}
		
		if(id.equals("0")){
			mv.addObject("department",n);
			mv.setViewName("department/addDepartment");
		}else{
			department = departmentService.selectByPrimaryKey(id);
			mv.addObject("department",department);
			mv.setViewName("department/updateDepartment");
		}

		return mv;
	}

	/**
	 * 添加部门信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/createDepartment")
	public ModelAndView createDepartment(Department n ,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		Organization company = organizationService.selectByPrimaryKey(n.getOrganizationId());//单位名称和代码号
		Department nr = departmentService.DepartmentByOId(n);
		if(nr!=null){
			String[] num = nr.getDepartmentId().split("-");
			if(num.length==1){
				n.setDepartmentId("JH"+company.getOrganizationDwdm()+"-1");	
			}else{
				n.setDepartmentId("JH"+company.getOrganizationDwdm()+"-"+(Integer.parseInt(num[1])+1));	
			}
			
		}else{
			n.setDepartmentId("JH"+company.getOrganizationDwdm()+"-1");	
		}
		n.setEpartmentDel(1);
		n.setEpartmentAt(new Date());
		int i = departmentService.insertSelective(n);
		if(i>0){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，添加部门。部门id："+n.getDepartmentId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			mv.setViewName("redirect:DepartmentAll?page=1&mark=3&epartmentRank="+n.getEpartmentRank()+"&organizationId="+n.getOrganizationId()+"&epartmentUpid="+n.getEpartmentUpid());
		}else{
			mv.setViewName("department/addDepartment");
		}


		return mv;
	}

	/**
	 * 修改部门信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateDepartment")
	public ModelAndView updateDepartment(Department n,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		int i = departmentService.updateByPrimaryKeySelective(n);
		if(i>0){ 
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，修改部门。部门id："+n.getDepartmentId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);

			mv.setViewName("redirect:DepartmentAll?page=1&mark=3&epartmentRank="+n.getEpartmentRank()+"&organizationId="+n.getOrganizationId()+"&epartmentUpid="+n.getEpartmentUpid());


		}else{
			mv.addObject("department",n);
			mv.setViewName("department/updateDepartment");
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
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.GET)
	public String deleteDepartment(String id,HttpSession session,HttpServletRequest request) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Message msg = new Message();
		Student ss = new Student();
		Department dd = new Department();
		int sug = 0;
		if (id!=null) {
			ss.setDepartmentId(id);
			List<Student> list = studentService.StudentByDId(ss);
			if(list.size()>0){
				return "sno";
			}else{
				dd.setEpartmentUpid(id);
				List<Department> dList = departmentService.DepartmentById(dd);
				if(dList.size()>0){
					return "dno";
				}else{
					sug = departmentService.deleteByPrimaryKey(id);
					if(sug==1){
						log.setLogAt(sdf.format(new Date()));
						log.setLogContent("用户："+acc.getAccountLoginid()+"，删除部门。部门id："+id);
						log.setLogId(Tools.getGUID());
						log.setLogIp(HYLUtil.getIP4(request));
						log.setLogUser(acc.getAccountLoginid());
						log.setLogUserType("后台用户");
						logService.insertSelective(log);
						return "ok";
					} else {
						return "no";
					}
				}
			}
		} else {
			msg.setMessage("删除失败");
			return ("success");
		}


	}



	/**
	 * 添加收藏
	 * 
	 * @param news
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/collectionDepartment", method = RequestMethod.POST)
	public String collectionDepartment(Department n,HttpSession session,HttpServletRequest request) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortId(Tools.getGUID());
		sort.setSortAt(new Date());
		sort.setSortDel(1);
		sort.setSortInfostate(4);
		sort.setSortInfoid(n.getDepartmentId());
		sort.setSortUserid(acc.getAccountId());
		List<Sort> slist = sortService.sortMyID(sort);
		if(slist.size()>0){
			return "";
		}else{
			int sd = sortService.insertSelective(sort);
			if(sd>0) {
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+acc.getAccountLoginid()+"，收藏部门。部门id："+n.getDepartmentId());
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(acc.getAccountLoginid());
				log.setLogUserType("后台用户");
				logService.insertSelective(log);
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
	@RequestMapping(value = "/collectionDelDepartment", method = RequestMethod.POST)
	public String collectionDelDepartment(Department n,HttpSession session,HttpServletRequest request) {
		Account acc = (Account)session.getAttribute("sessionUser");
		Sort sort = new Sort();
		sort.setSortInfoid(n.getDepartmentId());
		sort.setSortUserid(acc.getAccountId());
		sort.setSortInfostate(4);
		List<Sort> slist = sortService.sortMyID(sort);
		sort = slist.get(0);

		int sd = sortService.deleteByPrimaryKey(sort.getSortId());
		if(sd>0) {
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，取消收藏部门。部门id："+n.getDepartmentId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			return "ok";
		}
		return "";


	}



	/**
	 * 收藏通知查询
	 * @return
	 */
	@RequestMapping(value = "/collectionDepartmentAll")
	public ModelAndView collectionDepartmentAll(Department n,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();

		Account acc = (Account)session.getAttribute("sessionUser");
		n.setOrganizationId(acc.getAccountId());
		PageBean pageBean = new PageBean();
		int allRow = departmentService.collectionDepartmentCount(n);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		n.setPage(length*(currentPage1-1));
		n.setSize(length);

		List<Department> list = departmentService.collectionPageAll(n);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("collectionNoticeAll?cj="+cj);//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("pageBean",pageBean);
		mv.addObject("department",n);
		mv.setViewName("department/collectionDepartment");
		return  mv;
	}


	/**
	 * 合并跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/departmentMerger")
	public ModelAndView departmentMerger(String id,HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Department d =new Department();
		d.setOrganizationId(id);
		mv.addObject("department",d);
		mv.setViewName("department/mergerDepartment");
		return mv;
	}
	
	/**
	 * 合并部门信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateDepartmentMerger")
	public ModelAndView updateDepartmentMerger(Department n,String hbbmid,HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		if(n.getEpartmentRank().equals("4")){
		
			//修改学员部门ID
			Student s  = new Student();
			s.setDepartmentId(hbbmid);
			s.setStudentCompany(n.getEpartmentUpid());
			int i = studentService.updateByDid(s);
			//修改科室上级部门ID
			Department dd = new Department();
			dd.setDepartmentId(hbbmid);
			dd.setEpartmentUpid(n.getEpartmentUpid());
			departmentService.updateByDid(dd);
		}else{
			//修改学员部门ID
			Student s  = new Student();
			s.setDepartmentId(hbbmid);
			s.setStudentCompany(n.getEpartmentUpid());
			int i = studentService.updateByDid(s);
		}
		log.setLogAt(sdf.format(new Date()));
		log.setLogContent("用户："+acc.getAccountLoginid()+"，合并部门"+hbbmid+"与"+n.getEpartmentUpid());
		log.setLogId(Tools.getGUID());
		log.setLogIp(HYLUtil.getIP4(request));
		log.setLogUser(acc.getAccountLoginid());
		log.setLogUserType("后台用户");
		logService.insertSelective(log);
		departmentService.deleteByPrimaryKey(hbbmid);
		Department dd = departmentService.selectByPrimaryKey(n.getEpartmentUpid());
		mv.setViewName("redirect:DepartmentAll?page=1&mark=3&epartmentRank="+n.getEpartmentRank()+"&organizationId="+n.getOrganizationId()+"&epartmentUpid="+dd.getEpartmentUpid());



		return mv;
	}
	
	/**
	 * 调转跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/departmentTransfer")
	public ModelAndView departmentTransfer(String id,HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Department d = departmentService.selectByPrimaryKey(id);
		if(d.getEpartmentRank().equals("4")){
			//查询同等级单位
			List<Organization> list = organizationService.organizationHBSJID(d.getOrganizationId());
			mv.addObject("dlist",list);
		}else{
			//查询同等级部门
			Department dd = new Department(); 
			dd.setOrganizationId(d.getOrganizationId());
			dd.setEpartmentRank("4");
			List<Department> list = departmentService.DepartmentHBOId(dd);
			mv.addObject("bmlist",list);
		}
		mv.addObject("department",d);
		mv.setViewName("department/transferDepartment");
		return mv;
	}
	
	/**
	 * 修改调转部门信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateTransferDepartment")
	public ModelAndView updateTransferDepartment(Department n,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");

		if(n.getEpartmentRank().equals("4")){
			Student s  = new Student();
			s.setDepartmentId(n.getDepartmentId());
			s.setStudentCompanyid(n.getOrganizationId());
			//修改部门下学员组织ID
			studentService.updateOid(s);
			//修改科室组织ID
			departmentService.updateOidAll(n);
			//修改科室下学员组织ID
			studentService.updateOidAll(s);
		}
		int i = departmentService.updateByPrimaryKeySelective(n);
		if(i>0){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，调转部门,部门id"+n.getDepartmentId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			mv.setViewName("redirect:DepartmentAll?page=1&mark=3&epartmentRank="+n.getEpartmentRank()+"&organizationId="+n.getOrganizationId()+"&epartmentUpid="+n.getEpartmentUpid());

		}else{
			mv.addObject("department",n);
			mv.setViewName("department/transferDepartment");
		}


		return mv;
	}
	
	
	/**
	 * 排序跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/departmentSort")
	public ModelAndView departmentSort(String id,HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("department/sortDepartment");
		return mv;
	}
	
	/**
	 * 排序修改
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/updateDepartmentSort")
	public ModelAndView updateDepartmentSort(Department p) {
		ModelAndView mv = new ModelAndView();
		for(int i = 0 ;i<p.getOidall().length ;i++){
			Department d = new Department();
			d.setDepartmentId(p.getOidall()[i]);
			d.setEpartmentUpname(p.getOidsort()[i]);
			departmentService.updateByPrimaryKeySelective(d);
		}
		mv.setViewName("redirect:DepartmentAll?page=1&mark=3&epartmentRank="+p.getEpartmentRank()+"&organizationId="+p.getOrganizationId()+"&epartmentUpid="+p.getEpartmentUpid());
		return mv;
	}
	
	/**
	 * 查看科室显示学员分页
	 * @return
	 */
	@RequestMapping(value = "/studentsDepartmentAll")
	public ModelAndView studentsDepartmentAll(Student s,int page){
		ModelAndView mv = new ModelAndView();


		PageBean pageBean = new PageBean();
		int allRow = studentService.StudentCount(s);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		s.setPage(length*(currentPage1-1));
		s.setSize(length);

		List<Student> list = studentService.selectStudentPageAll(s);
		
		for (int i = 0; i < list.size(); i++) {
			/*	Studentone stuone=new Studentone();
				Studenttwo stutwo=new Studenttwo();
				Studenthree stuthree=new Studenthree(); */

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


//				list.get(i).setStudentone(stuone);
//				list.get(i).setStudenttwo(stutwo);
//				list.get(i).setStudenthree(stuthree);


			}

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("studentsDepartmentAll?studentCompanyid="+s.getStudentCompanyid()+"&studentDepartment="+s.getStudentDepartment());//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.addObject("department",s);
		mv.setViewName("department/queryStudents");
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
	 * 合并部门联动
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/HBtype",method=RequestMethod.POST)
	public ModelAndView HBtype(Department d,HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<Department> list = departmentService.DepartmentHBOId(d);
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
	 * 合并科室联动
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/HBKStype",method=RequestMethod.POST)
	public ModelAndView HBKStype(Department d,HttpServletResponse response,HttpServletRequest request) throws IOException{
		List<Department> list = departmentService.DepartmentById(d);
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
