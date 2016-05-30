package com.ttgis.education.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.apache.tools.ant.types.Commandline.Marker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Collect;
import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Courseresource;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Msg;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Record;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Syllabus;
import com.ttgis.education.entity.Teacher;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.CoursePage;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.CollectService;
import com.ttgis.education.service.CourseService;
import com.ttgis.education.service.CourseresourceService;
import com.ttgis.education.service.LogService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.RecordService;
import com.ttgis.education.service.SyllabusService;
import com.ttgis.education.service.TeacherService;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.RandomGUIDUtil;
import com.ttgis.education.utils.Tools;

/**
 * 课程
 * <p>
 * Title:CourseController
 * </p>
 * 
 * @author WYP 2015-8-12
 */

@Controller
public class CourseController
{

	@Resource
	private CourseService courseService;

	@Resource
	private CourseresourceService courseresourceService;

	@Resource
	private OrganizationService organizationService;

	@Resource
	private SyllabusService syllabusService;

	@Resource
	private TeacherService teacherService;
	
	@Resource
	private RecordService recordService;
	
	@Resource
	private CollectService collectService;//收藏
	
	@Resource
	LogService logService;
	Log log = new Log();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private Course ncourse;

	private Courseresource courseresource;

	private static Logger logger = Logger.getLogger(CourseController.class);
	
	private static  int mcar;

	// ------------------------------------后台---下---------------------------------------

	// ------------------------------------后台--上---------------------------------
	/**
	 * 陈健龙
	 * 课程目录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/course")
	public ModelAndView course(Course course ,int mark ,int page, Model model,HttpSession session)
	{
		int ch = 0;
		if(mark==1){
			ncourse =new Course();
		}
		Account acc = (Account) session.getAttribute("sessionUser");
		Syllabus s  = syllabusService.selectByPrimaryKey(course.getSyllabusId());
		//-------------------------------------------判断权限前台是否可操作----------------------------------------
		if(!s.getSyllabusUpperId().equals("test001")){

		if(s.getSyllabusUpperId().equals("06897D20-274A-B4F2-8E7A-E0BDF1039C8C")){//主干ID
			if(acc.getAccountType()==1){
				if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
					
					ch=1;
				}
			}else{
				if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
					ch=1;
				}
			}
		}else{
			s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
			if(s.getSyllabusUpperId().equals("06897D20-274A-B4F2-8E7A-E0BDF1039C8C")){
				if(acc.getAccountType()==1){
					if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
						ch=1;
					}
				}
			}else{
				s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
				if(acc.getAccountType()==1){
					if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
						ch=1;
					}
				}
			}

		}
		
		}
		//---------------------------------------判断条件------------------------------------------
		if(course.getCourseCatalog()!=null && course.getCourseCatalog()!="" ){
			ncourse.setCourseCatalog(course.getCourseCatalog());
		}
		if(course.getCoursePubman()!=null && course.getCoursePubman()!="" ){
			ncourse.setCoursePubman(course.getCoursePubman());
		}
		ncourse.setSyllabusId(course.getSyllabusId());
		ModelAndView mv = new ModelAndView();
		PageBean pageBean = new PageBean();
		int allRow = courseService.CoursesCount(ncourse);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		ncourse.setPage(length*(currentPage1-1));
		ncourse.setSize(length);

		List<Course> list = courseService.selectPageAll(ncourse);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("course?mark=0&syllabusId="+ncourse.getSyllabusId());//连接地址
		pageBean.init();
		mv.addObject("course",ncourse);
		mv.addObject("ch",ch);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("course/course");

		return  mv;
	}

	/**
	 * wyp
	 * 收藏课程（前台）
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/collectOnes")
	public String collectOnes(String id,HttpSession session) {
		 
		Collect sc = new Collect();
		Student student = (Student)session.getAttribute("Student");
		
		sc.setCollectId(Tools.getGUID());
		sc.setStudentId(student.getStudentId());
		sc.setCollectType("1");
		sc.setCollectDel(1);
		sc.setCollectAt(new Date());
		sc.setCollectContentid(id);
		List<Collect> pd = collectService.selectScKey(sc);
		if (pd.size()!=0) {
			return ("1");//是否重复
		}
		if (id!=null) {
		collectService.insertSelective(sc);
		} else {
			return ("2");//没有登录
		}
			
			return "ok";
	}
	/**
	 * wyp
	 * 前台收藏课程
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/courseWebSc")
	public ModelAndView courseWebSc(int mark ,int page,HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
	
		if(mark==1){
			ncourse =new Course();
		}
		ncourse.setStudentId(student.getStudentId());
		
		
		
			
		PageBean pageBean = new PageBean();
		int allRow = courseService.CoursesWebSc(ncourse);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		ncourse.setPage(length*(currentPage1-1));
		ncourse.setSize(length);
		
		List<Course> list = courseService.selectPageWebSc(ncourse);
		Courseresource courseresources =new Courseresource();
		for (int j = 0; j < list.size(); j++) {
			//获取总学时    课时
			courseresources.setCourseId(list.get(j).getCourseId());
			courseresources.setCourseresourceType("5");//5视频  7文件
			List<Courseresource> listCount = courseresourceService.CourseresourceAll(courseresources);
			list.get(j).setCourseTimes(new BigDecimal(listCount.size()));
		}
		
		
		
		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("courseWebSc?mark=0");//连接地址
		pageBean.init();
		mv.addObject("course",ncourse);
		mv.addObject("pageBean",pageBean);
	
		mv.setViewName("web/myCollectHistory");

		return  mv;
	}
	/**
	 * 删除培训班信息（后台）
	 * wyp
	 * @param tranclassId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCollect", method = RequestMethod.GET)
	public String deleteCollect(String id,HttpSession session) {
		Collect sc = new Collect();
		Student student = (Student)session.getAttribute("Student");
		if (id!=null) {
			sc.setStudentId(student.getStudentId());
			sc.setCollectContentid(id);
			int i=collectService.deleteScKey(sc);
			if (i!=1) {
				return "no";
			}
		} else {
			return ("2");//没有登录
		} 
			return "ok";
	}

	/**
	 * 陈健龙
	 * 课程修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/courseskipID")
	public ModelAndView courseskipID(String id,HttpServletRequest request,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account) session.getAttribute("sessionUser");
		Teacher teacher = new Teacher();
		teacher.setTeacherCatalog(acc.getAccountCatalog());
		List <Teacher> list = teacherService.selectByOId(teacher); 
		mv.addObject("tlist",list);
		Course c = new Course();
		if(id.equals("0")){
			Syllabus syllabus = new Syllabus();
			syllabus.setSyllabusUpperId("06897D20-274A-B4F2-8E7A-E0BDF1039C8C");
			syllabus.setOrganizationId("test001");
			List <Syllabus> slist = syllabusService.SyllabusByUid(syllabus);
			mv.addObject("slist",slist);
			mv.setViewName("course/addCourse");
		}else{
			c=  courseService.selectByPrimaryKey(id);
			mv.addObject("course",c);
			mv.setViewName("course/updateCourse");
		}

		return mv;
	}


	/**
	 * 陈健龙
	 * 添加课程信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/createCourse")
	public ModelAndView createCourse(Course n ,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		n.setCourseCatalog(acc.getAccountCatalog());
		Organization company = organizationService.selectByPrimaryKey(n.getCourseCatalog());//单位名称和代码号
		Course nr = courseService.CourseByOId(n);
		if(nr!=null){
			String[] num = nr.getCourseId().split("-");
			n.setCourseId("KC"+company.getOrganizationDwdm()+"-"+(Integer.parseInt(num[1])+1));	
		}else{
			n.setCourseId("KC"+company.getOrganizationDwdm()+"-1");	
		}
		n.setCourseDel(1);
		n.setCourseAt(new Date());
		n.setCourseStatus(1);
		n.setCoursePv(1);
		n.setCourseCollectnum(0);
		int i = courseService.insertSelective(n);
		if(i>0){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，添加课程:"+n.getSyllabusId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			mv.setViewName("redirect:course?mark=1&page=1&syllabusId="+n.getSyllabusId());
		}else{
			mv.setViewName("course/addCourse");
		}


		return mv;
	}

	/**
	 * 陈健龙
	 * 修改课程信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateCourse")
	public ModelAndView updateCourse(Course n,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		int i = courseService.updateByPrimaryKeySelective(n);
		if(i>0){ 
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，修改课程:"+n.getSyllabusId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			mv.setViewName("redirect:course?mark=1&page=1&syllabusId="+n.getSyllabusId());
		}else{
			mv.addObject("course",n);
			mv.setViewName("course/updateCourse");
		}


		return mv;
	}

	/**
	 * 陈健龙
	 * 删除信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCourse", method = RequestMethod.GET)
	public String deleteCourse(String id , HttpSession session,HttpServletRequest request) {
		Message msg = new Message();
		Account acc = (Account)session.getAttribute("sessionUser");
		int sug = 0;
		if (id!=null) {
			Course c = new Course();
			c.setCourseId(id);
			c.setCourseDel(0);
			sug = courseService.updateByPrimaryKeySelective(c);
		} else {
			msg.setMessage("删除失败");
			return ("success");
		}
		if(sug==1){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，删除课程:"+id);
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








	/**
	 * 课程列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/courseTable", method = RequestMethod.GET)
	public ModelAndView courseTable(CoursePage coursePage, String syllabusId, HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		int ch=0;
		Account acc = (Account) session.getAttribute("sessionUser");
		Syllabus s  = syllabusService.selectByPrimaryKey(coursePage.getSyllabusId());
		//-------------------------------------------判断权限前台是否可操作----------------------------------------
		if(!s.getSyllabusUpperId().equals("test001")){

		if(s.getSyllabusUpperId().equals("06897D20-274A-B4F2-8E7A-E0BDF1039C8C")){//主干ID
			if(acc.getAccountType()==1){
				if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
					
					ch=1;
				}
			}else{
				if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
					ch=1;
				}
			}
		}else{
			s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
			if(s.getSyllabusUpperId().equals("06897D20-274A-B4F2-8E7A-E0BDF1039C8C")){
				if(acc.getAccountType()==1){
					if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
						ch=1;
					}
				}
			}else{
				s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
				if(acc.getAccountType()==1){
					if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
						ch=1;
					}
				}
			}

		}
		
		}
		
		PageBean pageBean = new PageBean();
		int allRow = courseService.selectCourseBySyllabusIdRows(coursePage);
		final int length = coursePage.getPageSize();// 每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);// 总页数

		List<Course> courseList = courseService.selectCourseBySyllabusId(coursePage);

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(coursePage.getPage());
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(courseList);
		pageBean.setUrl("courseTable?syllabusId=" + coursePage.getSyllabusId());// 连接地址
		
		mv.addObject("zhuan",coursePage.getSyllabusId());
		mv.addObject("pageBean",pageBean);
		mv.addObject("ch",ch);
		mv.setViewName("course/courseTable");
		return mv;
	}
	
	
	
	/**
	 * 成绩课程列表
	 * 
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/courseManage", method = RequestMethod.GET)
	public ModelAndView courseManage(CoursePage coursePage, String syllabusId, HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		int ch=0;
		Account acc = (Account) session.getAttribute("sessionUser");
		Syllabus s  = syllabusService.selectByPrimaryKey(coursePage.getSyllabusId());
		//-------------------------------------------判断权限前台是否可操作----------------------------------------
		if(!s.getSyllabusUpperId().equals("test001")){

		if(s.getSyllabusUpperId().equals("06897D20-274A-B4F2-8E7A-E0BDF1039C8C")){//主干ID
			if(acc.getAccountType()==1){
				if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
					
					ch=1;
				}
			}else{
				if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
					ch=1;
				}
			}
		}else{
			s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
			if(s.getSyllabusUpperId().equals("06897D20-274A-B4F2-8E7A-E0BDF1039C8C")){
				if(acc.getAccountType()==1){
					if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
						ch=1;
					}
				}
			}else{
				s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
				if(acc.getAccountType()==1){
					if(!s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//不等于单位课程ID
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){//等于单位课程ID
						ch=1;
					}
				}
			}

		}
		
		}
		
		PageBean pageBean = new PageBean();
		int allRow = courseService.selectCourseBySyllabusIdRows(coursePage);
		final int length = coursePage.getPageSize();// 每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);// 总页数

		List<Course> courseList = courseService.selectCourseBySyllabusId(coursePage);

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(coursePage.getPage());
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(courseList);
		pageBean.setUrl("courseManage?syllabusId=" + coursePage.getSyllabusId());// 连接地址
		
		mv.addObject("pageBean",pageBean);
		mv.addObject("ch",ch);
		mv.setViewName("course/courseManage");
		return mv;
	}

	/**
	 * syllabusTree
	 * 
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/syllabusTree", method = RequestMethod.GET)
	public ModelAndView syllabusTree(HttpSession session)
	{
		List<Syllabus> syllabuses = syllabusService.selectAllSyllabus();
		return new ModelAndView("tree/syllabusTree", "syllabuses", syllabuses );
	}
	
	/**
	 * 成绩管理课程树
	 * 陈健龙
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/achievementTree", method = RequestMethod.GET)
	public ModelAndView achievementTree(HttpSession session)
	{
		List<Syllabus> syllabuses = syllabusService.selectAllSyllabus();
		return new ModelAndView("tree/achievementTree", "syllabuses", syllabuses );
	}


	/**
	 * 董再兴 2015年08月17日 15:20 取得课程管理目录树和账号类型 课程管理目录树
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	public Msg tree(HttpSession session)
	{
		logger.debug("查询课程目录树");
		List<Syllabus> syllabus = new ArrayList<Syllabus>();
		Account acc = (Account) session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){
			syllabus = syllabusService.selectAllSyllabus();
		}else{
			Syllabus s = new Syllabus();
			s.setOrganizationId(acc.getAccountCatalog());
			syllabus = syllabusService.AccAllSyllabus(s);
		}


		return new Msg(acc.getAccountType().toString(), syllabus, Msg.SUCCESS);
	}

	/**
	 * 董再兴 2015年08月21日 15:58 添加树节点 提交上来两个字段 syllabusName， syllabusName
	 * 
	 * @param syllabus
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addTreeNode", method = RequestMethod.POST)
	public Msg addTreeNode(Syllabus syllabus, HttpSession session)
	{
		int ch =  0;
		logger.debug("添加树节点:");
		Account acc = (Account) session.getAttribute("sessionUser");
		Syllabus s  = syllabusService.selectByPrimaryKey(syllabus.getSyllabusUpperId());
		if(acc.getAccountType()==1){
			if(s.getOrganizationId().equals(acc.getAccountCatalog()) && !s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
				ch=1;
			}
		}else{
			if(s.getOrganizationId().equals(acc.getAccountCatalog()) || s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
				ch=1;
			}
		}
		if(ch!=0){
			if (syllabus == null)
				return new Msg("异常消息", "提交数据异常", Msg.ERROR);
			syllabus.setOrganizationId(acc.getAccountCatalog());
			syllabus.setSyllabusAt(new Date());
			syllabus.setSyllabusDel(1);
			syllabus.setSyllabusId(RandomGUIDUtil.generateKey());
			int r = syllabusService.insertCourse(syllabus);
			return new Msg(String.valueOf(r), syllabus, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}

	/**
	 * 董再兴 删除给定节点及其直接子节点
	 * 
	 * @param syllabusId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delTreeNode", method = RequestMethod.POST)
	public Msg delTreeNode(String syllabusId,HttpSession session)
	{
		int ch =  0;
		Account acc = (Account) session.getAttribute("sessionUser");
		Syllabus s  = syllabusService.selectByPrimaryKey(syllabusId);
		logger.debug("删除树节点:" + syllabusId);
		if(acc.getAccountType()==1){
			if(s.getOrganizationId().equals(acc.getAccountCatalog()) && !s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
				ch=1;
			}
		}else{
			if(s.getOrganizationId().equals(acc.getAccountCatalog()) || s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
				ch=1;
			}
		}
		if(ch!=0){
			if (syllabusId == null || syllabusId == "")
				return new Msg("异常消息", "提交数据异常", Msg.ERROR);
			int r = syllabusService.deletesByPrimaryKey(syllabusId);
			if (r == 0)
				return new Msg("异常消息", "数据删除异常", Msg.ERROR);
			return new Msg("提示", r, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}

	/**
	 * 董再兴 2015-08-27 10:48 根据id更新树节点名称，其他属性不更新
	 * 
	 * @param syllabus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateTreeNode", method = RequestMethod.POST)
	public Msg updateTreeNode(Syllabus syllabus,HttpSession session)
	{
		if(!syllabus.getSyllabusId().equals("1AD34C2B-0A29-5325-1613-3082518F4567") && !syllabus.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
		int ch =  0;
		Account acc = (Account) session.getAttribute("sessionUser");
		Syllabus s2  = syllabusService.selectByPrimaryKey(syllabus.getSyllabusId());
		logger.debug("修改树节点名称:" + syllabus.getSyllabusId() + "," + syllabus.getSyllabusName());
		if(acc.getAccountType()==1){
			if(s2.getOrganizationId().equals(acc.getAccountCatalog()) && !s2.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
				ch=1;
			}
		}else{
			if(s2.getOrganizationId().equals(acc.getAccountCatalog()) || s2.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
				ch=1;
			}
		}
		if(ch!=0){
			if (syllabus == null)
				return new Msg("异常消息", "提交数据异常", Msg.ERROR);
			Syllabus s = syllabusService.selectByPrimaryKey(syllabus.getSyllabusId());
			s.setSyllabusName(syllabus.getSyllabusName());
			int r = syllabusService.updateByPrimaryKey(s);
			if (r == 0)
				return new Msg("异常消息", "数据删除异常", Msg.ERROR);
			return new Msg("提示", r, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
		
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}

	/**
	 * 分页查询课程信息
	 * @param i		pageIndex 从0开始
	 * @param course		分页条件
	 * @param session		获取权限验证
	 * @return	数据总数，当前页msgBody 数据，查询状态
	 */
	@ResponseBody
	@RequestMapping(value="/queryCourse", method=RequestMethod.POST)
	public Msg queryCourse(CoursePage coursePage,Course course, HttpSession session){
		if(coursePage==null||course==null)return new Msg("错误提示","提交数据异常！",Msg.ERROR);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coursePage", coursePage);
		map.put("course", course);
		int count = courseService.selectCoursesBySyllabusIdCount(map);
		List<Course> courses = courseService.selectCoursesBySyllabusId(map);
		return new Msg(String.valueOf(count), courses, Msg.SUCCESS);
	}


	/**
	 * 课程目录联动第2层
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/syllabusLD")
	public ModelAndView syllabusLD(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException{
		Syllabus s = new Syllabus();
		Account acc = (Account) session.getAttribute("sessionUser");
		s.setOrganizationId(acc.getAccountCatalog());
		s.setSyllabusUpperId(request.getParameter("id"));
		List<Syllabus> list = syllabusService.SyllabusByUid(s);
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
	 * 陈健龙
	 * 上传视频资源
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/courseresourcePage")
	public ModelAndView courseresourcePage(Courseresource cr ,int mark ,int page,HttpSession session)
	{
		if(mark==1){
			courseresource =new Courseresource();
		}
		courseresource.setCourseId(cr.getCourseId());
		ModelAndView mv = new ModelAndView();
		PageBean pageBean = new PageBean();
		int allRow = courseresourceService.CourseresourceCount(courseresource);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		courseresource.setPage(length*(currentPage1-1));
		courseresource.setSize(length);

		List<Courseresource> list = courseresourceService.selectPageAll(courseresource);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("courseresourcePage?mark=0&courseId="+courseresource.getCourseId());//连接地址
		pageBean.init();
		mv.addObject("courseresource",courseresource);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("courseresource/courseresource");

		return  mv;
	}
	
	/**
	 * 陈健龙
	 * 删除资源（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCourseresource", method = RequestMethod.GET)
	public String deleteCourseresource(String id ,HttpServletResponse response,HttpSession session,HttpServletRequest request) {
		Message msg = new Message();
		Account acc = (Account)session.getAttribute("sessionUser");
		int sug = 0;
		if (id!=null) {
			Courseresource c = new Courseresource();
			c.setCourseresourceId(id);
			c.setCourseresourceDel(0);
			sug = courseresourceService.updateByPrimaryKeySelective(c);
		} else {
			return ("success");
		}
		if(sug==1){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，删除视频:"+id);
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			msg.setMessage("删除失败");
			return "ok";
		} else {
			return "no";
		}

	}


	/**
	 * 陈健龙
	 * 课程修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/courseresourceskipID")
	public ModelAndView courseresourceskipID(String id,String cid,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Courseresource c = new Courseresource();
		mv.addObject("cid",cid);
		if(id.equals("0")){
			mv.setViewName("courseresource/addCourseresource");
		}else{
			c=  courseresourceService.selectByPrimaryKey(id);
			mv.addObject("courseresource",c);
			mv.setViewName("courseresource/updateCourseresource");
		}

		return mv;
	}

	/**
	 * 陈健龙
	 * 添加课程信息
	 * 
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/createCourseresource")
	public ModelAndView createCourseresource(@RequestParam(value="file") MultipartFile file,HttpServletRequest request,Courseresource n ,HttpServletResponse response,HttpSession session) throws IOException {
		response.setContentType("text/html;charset=UTF-8");
		Account acc = (Account) session.getAttribute("sessionUser");
		PrintWriter out = null;
		out = response.getWriter();
		 if(!file.isEmpty()){  
	            System.out.println("================="+file.getName() + file.getSize());
//	            限定为500M524288000 1073747824
	            if (file.getSize()>1073747824) {
	            	out.print("<script>alert('上传资料不能超过1G')</script>");
					out.print("<script>location.href='courseresourceskipID?id=0&cid="+n.getCourseId()+"'</script>");
					out.flush();
					return null;
	            }
	        }
		String realPath = request.getSession().getServletContext()
		.getRealPath("/UploadFile");
		String chsum =CourseController.storeIOc(realPath, file);
		if(!chsum.equals("1")){
			if(!chsum.equals("0")){
				File dir = new File(realPath+"\\"+chsum);
				if(dir.exists()){
					n.setCourseresourceId(Tools.getGUID());
					n.setCourseresourceDel(1);
					n.setCourseresourceAt(new Date());
					n.setCourseresourceType("5");
					if(mcar==7){
						n.setCourseresourceType(mcar+"");
						mcar=0;
					}
					n.setCourseresourceFileaddr("UploadFile/"+chsum);
					n.setCourseresourceFiletype(chsum.substring(chsum.lastIndexOf(".") + 1).toLowerCase());
					int i = courseresourceService.insertSelective(n);
					if(i>0){
						Record record = new Record();
						record.setRecordAt(new Date());
						record.setRecordClass("课程视频");
						record.setRecordContentid(n.getCourseresourceId());
						record.setRecordDel(1);
						record.setRecordId(Tools.getGUID());
						record.setRecordNote(acc.getAccountName()+"上传课程视频"+n.getCourseresourceName());
						record.setRecordTitle("课程视频上传");
						record.setRecordType("5");
						record.setStudentId(acc.getAccountId());
						recordService.insertSelective(record);
						
						log.setLogAt(sdf.format(new Date()));
						log.setLogContent("用户："+acc.getAccountLoginid()+"，上传视频:"+n.getCourseresourceId());
						log.setLogId(Tools.getGUID());
						log.setLogIp(HYLUtil.getIP4(request));
						log.setLogUser(acc.getAccountLoginid());
						log.setLogUserType("后台用户");
						logService.insertSelective(log);
						
						out.print("<script>alert('保存成功')</script>");
						out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
					}else{
						out.print("<script>alert('资源信息保存失败')</script>");
						out.print("<script>location.href='courseresourceskipID?id=0&cid="+n.getCourseId()+"'</script>");
					}
				}else{
					out.print("<script>alert('文件保存失败')</script>");
					out.print("<script>location.href='courseresourceskipID?id=0&cid="+n.getCourseId()+"'</script>");
				}
			}else{
				out.print("<script>alert('文件上传失败')</script>");
				out.print("<script>location.href='courseresourceskipID?id=0&cid="+n.getCourseId()+"'</script>");
			}
		}else{
			out.print("<script>alert('文件上传格式错误')</script>");
			out.print("<script>location.href='courseresourceskipID?id=0&cid="+n.getCourseId()+"'</script>");
		}
		out.flush();
		return null;
	}
	
	/**
	 * 陈健龙
	 * 修改课程信息
	 * 
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/updateCourseresource")
	public ModelAndView updateCourseresource(@RequestParam(value="file") MultipartFile file,HttpServletRequest request,Courseresource n ,HttpServletResponse response,HttpSession session) throws IOException {
		Account acc = (Account) session.getAttribute("sessionUser");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		if(file.getSize()>0){
			 if(!file.isEmpty()){  
		            System.out.println("================="+file.getName() + file.getSize());
//		            限定为500M524288000 1073747824
		            if (file.getSize()>1073747824) {
		            	out.print("<script>alert('上传资料不能超过1G')</script>");
						out.print("<script>location.href='courseresourceskipID?id=0&cid="+n.getCourseId()+"'</script>");
						out.flush();
						return null;
		            }
		        }

		String realPath = request.getSession().getServletContext()
		.getRealPath("/UploadFile");
		String chsum =CourseController.storeIOc(realPath, file);//视频保存方法
		if(!chsum.equals("1")){
			if(!chsum.equals("0")){
				File dir = new File(realPath+"\\"+chsum);
				if(dir.exists()){
					n.setCourseresourceFileaddr("UploadFile/"+chsum);
					n.setCourseresourceFiletype(chsum.substring(chsum.lastIndexOf(".") + 1).toLowerCase());
					if(mcar==7){
						n.setCourseresourceType(mcar+"");
						mcar=0;
					}else{
						n.setCourseresourceType("5");
					}
					int i = courseresourceService.updateByPrimaryKeySelective(n);
					if(i>0){
						log.setLogAt(sdf.format(new Date()));
						log.setLogContent("用户："+acc.getAccountLoginid()+"，修改视频:"+n.getCourseresourceId());
						log.setLogId(Tools.getGUID());
						log.setLogIp(HYLUtil.getIP4(request));
						log.setLogUser(acc.getAccountLoginid());
						log.setLogUserType("后台用户");
						logService.insertSelective(log);
						
						out.print("<script>alert('保存成功')</script>");
						out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
					}else{
						out.print("<script>alert('资源信息保存失败')</script>");
						out.print("<script>location.href='courseresourceskipID?id="+n.getCourseresourceId()+"&cid="+n.getCourseId()+"'</script>");
					}
				}else{
					out.print("<script>alert('文件保存失败')</script>");
					out.print("<script>location.href='courseresourceskipID?id="+n.getCourseresourceId()+"&cid="+n.getCourseId()+"'</script>");
				}
			}else{
				out.print("<script>alert('文件上传失败')</script>");
				out.print("<script>location.href='courseresourceskipID?id="+n.getCourseresourceId()+"&cid="+n.getCourseId()+"'</script>");
			}
		}else{
			out.print("<script>alert('文件上传格式错误')</script>");
			out.print("<script>location.href='courseresourceskipID?id="+n.getCourseresourceId()+"&cid="+n.getCourseId()+"'</script>");
		}
		out.flush();
		
		}else{
			int i = courseresourceService.updateByPrimaryKeySelective(n);
			if(i>0){
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+acc.getAccountLoginid()+"，修改视频:"+n.getCourseresourceId());
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(acc.getAccountLoginid());
				log.setLogUserType("后台用户");
				logService.insertSelective(log);
				out.print("<script>alert('保存成功')</script>");
				out.print("<script>location.href='courseresourcePage?mark=1&page=1&courseId="+n.getCourseId()+"'</script>");
			}else{
				out.print("<script>alert('资源信息保存失败')</script>");
				out.print("<script>location.href='courseresourceskipID?id="+n.getCourseresourceId()+"&cid="+n.getCourseId()+"'</script>");
			}
		}
		return null;
	}


	/***
	 * 保存文件，返回文件地址
	 * 
	 * @param realPath
	 * @param file
	 * @return
	 */
	public static String storeIOc(String realPath, MultipartFile file)
	{
		int mark = 0;
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
		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("media", "flv,mp4,wmv,avi,mpg,mov");//视频格式
		extMap.put("text", "doc,docx,pdf,ppt,pptx");//文件格式
		// 文件名称
		String fileName = "";
		String logImageName = "";
		// file调用getOriginalFilename获取完成文件名
		String _fileName = file.getOriginalFilename();
		// 用文件名截取最后一个.后面的字符当作扩展名
		String suffix = _fileName.substring(_fileName.lastIndexOf("."));
		String fileExt = _fileName.substring(_fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get("media").split(",")).contains(fileExt)){
			if(!Arrays.<String>asList(extMap.get("text").split(",")).contains(fileExt)){
				return "1";
			}else{
				mark=1;
			}
		}
		/** 使用UUID生成文件名称 **/
		logImageName = UUID.randomUUID().toString().replace("-", "") + suffix;

		fileName = realPath + File.separator + logImageName;
		File restore = new File(fileName);
		try
		{
			file.transferTo(restore);
			if(fileExt.equals("flv")){//如果不是flv的格式执行转换方法
				return logImageName;
			}else{
				if(mark==1){
					mcar = 7;
					return logImageName;
				}else{
					return processFLV(fileName,realPath);//视频转换
				}
			}
		} catch (Exception e){
			//			throw new RuntimeException(e);
		}
		// 返回默认的图片地址
		return "0";
	}
	
	
	/**
	 * 视频转换
	 * 陈健龙 
	 *  ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）  
	 */
    private static String processFLV(String oldfilepath,String url) {  
        // 文件命名  
    	String drurlString = url.substring(0,url.lastIndexOf("UploadFile"));
    	String logImageName = UUID.randomUUID().toString().replace("-", "");
        List<String> commend = new ArrayList<String>();  
        commend.add(drurlString+"ffmpeg\\ffmpeg.exe");  //执行ffmpeg的执行文件路径
        commend.add("-i");  
        commend.add(oldfilepath);//要转换的视频路径  
        commend.add("-ab");  
        commend.add("56");  
        commend.add("-ar");  
        commend.add("22050");  
        commend.add("-qscale");  
        commend.add("8");  
        commend.add("-r");  
        commend.add("15");  //帧数
        commend.add("-s");  
        commend.add("600x500");  //分辨率
        commend.add(url+"\\"+logImageName+".flv");  //转换后的视频保存路径
        try {  
            ProcessBuilder builder = new ProcessBuilder();    
            builder.command(commend);  
            builder.redirectErrorStream(true);  
            Process proc = builder.start();  
            BufferedReader stdout = new BufferedReader(  //字符流保存转换后的视频
                    new InputStreamReader(proc.getInputStream()));  
            while ((stdout.readLine()) != null) {  
             
            }  
            proc.waitFor();     
            stdout.close();  
            deleteFile(oldfilepath);//删除
            return logImageName+".flv";  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "0";  
        }  
    }  
    
    /** 
     * 删除单个文件 
     * 陈健龙 
     * @param fileName 
     *            要删除的文件的文件名 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public static boolean deleteFile(String fileName) {  
     File file = new File(fileName);  
     // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除  
     if (file.exists() && file.isFile()) {  
      if (file.delete()) {  
       System.out.println("删除单个文件" + fileName + "成功！");  
       return true;  
      } else {  
       System.out.println("删除单个文件" + fileName + "失败！");  
       return false;  
      }  
     } else {  
      System.out.println("删除单个文件失败：" + fileName + "不存在！");  
      return false;  
     }  
    }  




}
