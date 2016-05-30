package com.ttgis.education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Clastudent;
import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Coursenote;
import com.ttgis.education.entity.Courseresource;
import com.ttgis.education.entity.Coursestudy;
import com.ttgis.education.entity.Estimate;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Msg;
import com.ttgis.education.entity.News;
import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Programme;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Studentone;
import com.ttgis.education.entity.Syllabus;
import com.ttgis.education.entity.Test;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.Videotime;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.ClastudentService;
import com.ttgis.education.service.CourseService;
import com.ttgis.education.service.CoursenoteService;
import com.ttgis.education.service.CourseresourceService;
import com.ttgis.education.service.CoursestudyService;
import com.ttgis.education.service.EstimateService;
import com.ttgis.education.service.LogService;
import com.ttgis.education.service.NewsService;
import com.ttgis.education.service.NoticeService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.ProgrammeService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.service.StudenthreeService;
import com.ttgis.education.service.StudentoneService;
import com.ttgis.education.service.StudenttwoService;
import com.ttgis.education.service.SyllabusService;
import com.ttgis.education.service.TestService;
import com.ttgis.education.service.TranclassService;
import com.ttgis.education.service.VideotimeService;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.RandomGUID;
import com.ttgis.education.utils.Tools;

/**
 * 前台首页
 * @author 陈健龙
 *
 */
@Controller
public class mainController {

	@Resource
	private CoursenoteService coursenoteService;
	@Resource
	private TranclassService tranclassService;
	@Resource
	private NewsService newsService;
	@Resource
	private ProgrammeService programmeService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private StudentService studentService;
	@Resource
	private OrganizationService organizationService;

	@Resource
	private CourseService courseService;

	@Resource
	private CourseresourceService courseresourceService;

	@Resource
	private SyllabusService syllabusService;

	@Resource
	private CoursestudyService coursestudyService;
	@Resource
	private EstimateService estimateService;

	@Resource
	private VideotimeService videotimeService;


	@Resource
	private ClastudentService clastudentService;

	@Resource
	private TestService testService;

	@Resource
	private StudentoneService studentoneService;
	@Resource
	private StudenttwoService studenttwoService;
	@Resource
	private StudenthreeService studenthreeService;

	@Resource
	LogService logService;
	Log log = new Log();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	private Course ncourse;

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(mainController.class);

	/**
	 * 学员登录
	 * @param s
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/StudentcheckLogin")
	public Msg StudentcheckLogin(Student student,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		JSONObject obj = new JSONObject();  
		Student s = studentService.checkLogin(student);
		String ss= (String) session.getAttribute("rand");
		ss = ss.toLowerCase();
		String ss1 = request.getParameter("code");
		ss1 = ss1.toLowerCase();
		if(ss.equals(ss1)){
			if(s == null){
				obj.put("MsgBody", "用户名或密码错误");
				obj.put("status", "error");
			}else{
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+s.getStudentSeq()+"，登陆。");
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(s.getStudentSeq());
				log.setLogUserType("前台用户");
				logService.insertSelective(log);
				obj.put("status", "success");
				session.setAttribute("Student", s);
			}
		}else{
			obj.put("MsgBody", "验证码错误");
			obj.put("status", "error");
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		System.out.println(obj);
		out.println(obj);
		return null;
	}

	/**
	 * 站内搜索
	 * @param model 
	 * @param name 模糊查询
	 * @return
	 */
	@RequestMapping(value = "/mainInstation")
	public ModelAndView mainInstation(Model model,String name,String mark) {
		//----课程标题模糊查询--------
		Course course = new Course();
		course.setCourseName(name);
		List<Course> clist = courseService.queryMyName(course);
		//----课程标题模糊查询--------
		//----计划标题模糊查询--------
		Programme p = new Programme();
		p.setProgrammeName(name);
		List<Programme> plist = programmeService.programmeMyName(p);
		//----计划标题模糊查询--------
		//----通知标题模糊查询--------
		Notice notice = new Notice();
		notice.setNoticeName(name);
		List<Notice> nolist = noticeService.noticeMyName(notice);
		//----通知标题模糊查询--------
		//----新闻标题模糊查询-------
		News news =new News();
		news.setNewsName(name);
		List<News> nlist = newsService.newsMyName(news);
		//----新闻标题模糊查询-------
		model.addAttribute("nlist", nlist);
		model.addAttribute("nolist", nolist);
		model.addAttribute("plist", plist);
		model.addAttribute("clist", clist);
		model.addAttribute("mark", mark);
		model.addAttribute("name", name);
		if(name!=null&&name!=""){

			if(mark.equals("1")){
				return new ModelAndView("web/mainInstation");
			}else{
				return new ModelAndView("web/mainInstationS");
			}
		}else{
			if(mark.equals("1")){
				return new ModelAndView("redirect:main");
			}else{
				return new ModelAndView("redirect:partySchoolTraining");
			}
			
		}

	}

	/**
	 * 董再兴	2015年08月17日  16:00
	 * 统一处理URL跳转	redirect?r=xx|yy 形式
	 * @param r
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/redirect2", method = RequestMethod.GET)
	public ModelAndView redirect2(@RequestParam("r") String r){
		logger.info("公用跳转redirect2Controller:" + r);
		r = (r.indexOf("|")>-1)?r.replace("|", "/"):"error";
		return new ModelAndView(r);
	}

	/**
	 * 登录前主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mainout", method = RequestMethod.GET)
	public ModelAndView mainout(Model model) {
		Notice notice = new Notice();
		News news = new News();
		notice.setOrganizationId("test001");
		notice.setNoticeSort("1");
		news.setOrganizationId("test001");
		news.setNewsSort("1");
		List<Notice>nolist = noticeService.NoticeByIdAll(notice);
		List<News>nlist =newsService.NewsByOIdAll(news);
		news.setNewsSort("2");
		List<News>gdlist =newsService.NewsByOIdAll(news);
		//课程查询
		Course course = new Course();
		course.setCourseType("1");
		List<Course>clist = courseService.CourseAll(course);
		List<Course>clist1 =courseService.CourseAllPV();


		model.addAttribute("clist", clist);
		model.addAttribute("clist1", clist1);
		model.addAttribute("nlist", nlist);
		model.addAttribute("gdlist", gdlist);
		model.addAttribute("nolist", nolist);
		return new ModelAndView("web/mainout");
	}

	/**
	 * 培训动态
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/training", method = RequestMethod.GET)
	public ModelAndView training(Model model) {
		Notice notice = new Notice();
		News news = new News();
		Programme p = new Programme();
		notice.setOrganizationId("test001");
		notice.setNoticeSort("1");
		news.setOrganizationId("test001");
		news.setNewsSort("1");
		p.setOrganizationId("test001");
		p.setProgrammeSort("1");
		List<Notice>nolist = noticeService.NoticeByIdAll(notice);
		List<News>nlist =newsService.NewsByOIdAll(news);
		List<Programme>plist = programmeService.ProgrammeByOIdAll(p);


		model.addAttribute("plist", plist);
		model.addAttribute("nlist", nlist);
		model.addAttribute("nolist", nolist);
		return new ModelAndView("web/training");
	}



	/**
	 * 详细显示页
	 * @param id
	 * @param mark
	 * @return
	 */
	@RequestMapping(value = "/noticeInfo", method = RequestMethod.GET)
	public ModelAndView noticeInfo(String id,int mark) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("tpk",1);
		if(mark==1){//通知
			Notice notice = new Notice();
			notice.setNoticeId(id);
			notice = noticeService.NoticeById(notice);
			mv.addObject("notice",notice);
			mv.setViewName("web/noticeInformation");
		}else if(mark==2){//新闻
			News news = new News();
			news = newsService.selectByPrimaryKey(id);
			mv.addObject("news",news);
			mv.setViewName("web/newsInformation");
		}else if(mark==3){//计划
			Programme p = new Programme();
			p =programmeService.selectByPrimaryKey(id);
			mv.addObject("programme",p);
			mv.setViewName("web/programmeInformation");
		}

		return mv;
	}

	/**
	 * 更多显示页
	 * @param id
	 * @param mark
	 * @return
	 */
	@RequestMapping(value = "/noticeInfoAll", method = RequestMethod.GET)
	public ModelAndView noticeInfoAll(String oid,String sort,int mark,int page,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student!=null){
			if(student.getOrganization().getOrganizationFwqx().equals("2")){
				Organization oneid = organizationService.selectByPrimaryKey(student.getStudentCompanyid());
				mv.addObject("oneid",oneid);
			}
			if(student.getOrganization().getOrganizationFwqx().equals("3")){
				Organization oneid = organizationService.selectByPrimaryKey(student.getStudentCompanyid());
				mv.addObject("oneid",oneid);
				Organization oneid1 = organizationService.selectByPrimaryKey(oneid.getOrganizationSjdw());
				mv.addObject("oneid1",oneid1);
			}

		}
		if(mark==1){//------------------------------------------------通知
			Notice notice = new Notice();
			notice.setNoticeSort(sort);
			notice.setOrganizationId(oid);
			notice.setType(1);
			//------------------------------------判断置顶时间，到期修改置顶状态-------------------------------------
			List<Notice> toplist = noticeService.webNoticeTOP(notice);
			if(toplist.size()>0){
				for(int i=0 ; i<toplist.size();i++){
					Notice topn = new Notice();
					topn = toplist.get(i);
					topn.setNoticeTop(0);
					noticeService.updateByPrimaryKeySelective(topn);
				}
			}

			PageBean pageBean = new PageBean();
			int allRow = noticeService.webNoticeCount(notice);
			final int length = 50;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			notice.setPage(length*(currentPage1-1));
			notice.setSize(length);

			List<Notice> list = noticeService.webPageAll(notice);

			pageBean.setPageSize(length);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("noticeInfoAll?mark="+mark+"&oid="+oid+"&sort="+sort);//连接地址
			pageBean.init();
			mv.addObject("mark",mark);
			mv.addObject("pageBean",pageBean);
			mv.setViewName("web/trainingNoticeList");
		}else if(mark==2){//-----------------------------------------------------新闻
			News news = new News();
			news.setNewsSort(sort);
			news.setOrganizationId(oid);
			news.setType(1);
			//------------------------------------判断置顶时间，到期修改置顶状态-------------------------------------
			List<News> toplist = newsService.webNewsTOP(news);
			if(toplist.size()>0){
				for(int i=0 ; i<toplist.size();i++){
					News topn = new News();
					topn = toplist.get(i);
					topn.setNewsTop(0);
					newsService.updateByPrimaryKeySelective(topn);
				}
			}

			PageBean pageBean = new PageBean();
			int allRow = newsService.webNewsCount(news);
			final int length = 50;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			news.setPage(length*(currentPage1-1));
			news.setSize(length);

			List<News> list = newsService.webPageAll(news);

			pageBean.setPageSize(length);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("noticeInfoAll?mark="+mark+"&oid="+oid+"&sort="+sort);//连接地址
			pageBean.init();
			mv.addObject("mark",mark);
			mv.addObject("pageBean",pageBean);
			mv.setViewName("web/trainingNoticeList");
		}else if(mark==3){//-----------------------------------------------------计划
			Programme p = new Programme();
			p.setProgrammeSort(sort);
			p.setOrganizationId(oid);
			p.setType(1);
			//------------------------------------判断置顶时间，到期修改置顶状态-------------------------------------
			List<Programme> toplist = programmeService.webProgrammeTOP(p);
			if(toplist.size()>0){
				for(int i=0 ; i<toplist.size();i++){
					Programme topn = new Programme();
					topn = toplist.get(i);
					topn.setProgrammeTop(0);
					programmeService.updateByPrimaryKeySelective(topn);
				}
			}

			PageBean pageBean = new PageBean();
			int allRow = programmeService.webProgrammeCount(p);
			final int length = 50;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			p.setPage(length*(currentPage1-1));
			p.setSize(length);

			List<Programme> list = programmeService.webPageAll(p);

			pageBean.setPageSize(length);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("noticeInfoAll?mark="+mark+"&oid="+oid+"&sort="+sort);//连接地址
			pageBean.init();
			mv.addObject("mark",mark);
			mv.addObject("pageBean",pageBean);
			mv.setViewName("web/trainingNoticeList");
		}

		return mv;
	}

	//-------------------------------------------------------------------------课程--------------------------------------------------------------------
	//-------------------------------------------------------------------------课程--------------------------------------------------------------------
	//-------------------------------------------------------------------------课程--------------------------------------------------------------------
	//-------------------------------------------------------------------------课程--------------------------------------------------------------------
	//-------------------------------------------------------------------------课程--------------------------------------------------------------------


	/**
	 * 陈健龙
	 * 前台课程目录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/courseWebAll")
	public ModelAndView courseWebAll(Course course ,int mark ,int page, Model model,HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		//目录树-------------------------------------------------------
		List<Syllabus> syllabus = new ArrayList<Syllabus>();
		if(student.getStudentCompanyid().equals("test001")){
			syllabus = syllabusService.selectAllSyllabus();
		}else{
			Syllabus s = new Syllabus();
			s.setOrganizationId(student.getStudentCompanyid());
			syllabus = syllabusService.AccAllSyllabus(s);
		}

		//分页显示-----------------------------------------------
		if(mark==1){
			ncourse =new Course();
		}
		//---------------------------------------判断条件------------------------------------------
		if(course.getSyllabusId()!=null && course.getSyllabusId()!="" ){
			ncourse.setSyllabusId(course.getSyllabusId());
			Syllabus syllthree=syllabusService.selectByPrimaryKey(course.getSyllabusId());
			syllthree.getSyllabusName();
			Syllabus sylltwo=syllabusService.selectByPrimaryKey(syllthree.getSyllabusUpperId());
			sylltwo.getSyllabusName();
			Syllabus syllone=syllabusService.selectByPrimaryKey(sylltwo.getSyllabusUpperId());
			syllone.getSyllabusName();
			String ml=syllone.getSyllabusName()+">"+sylltwo.getSyllabusName()+">"+syllthree.getSyllabusName();
			ncourse.setMl(ml);
		}
		if(ncourse.getSyllabusId()==null || ncourse.getSyllabusId()==""  ){
			ncourse.setCourseCatalog(student.getStudentCompanyid());
		}

		PageBean pageBean = new PageBean();
		int allRow = courseService.CoursesWebCount(ncourse);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		ncourse.setPage(length*(currentPage1-1));
		ncourse.setSize(length);

		List<Course> list = courseService.selectPageWebAll(ncourse);
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
		pageBean.setUrl("courseWebAll?mark=0");//连接地址
		pageBean.init();
		mv.addObject("course",ncourse);
		mv.addObject("pageBean",pageBean);
		mv.addObject("syllabus",syllabus);
		mv.setViewName("web/allCourse");

		return  mv;
	}




	/**
	 * 详细显示页
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/courseInfo", method = RequestMethod.GET)
	public ModelAndView courseInfo(String coursestudyId,String id,String tpk,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		Estimate e=new Estimate();
		Course c = courseService.selectByPrimaryKey(id);
		//-------------------浏览次数累加-----------------------------
		if(c.getCoursePv()!=null && c.getCoursePv()>0){
			c.setCoursePv(c.getCoursePv()+1);
		}else{
			c.setCoursePv(1);
		}
		int i = courseService.updateByPrimaryKeySelective(c);
		//---------------------查看是否评价次课程-------------------------------------------
		if(student!=null){
			e.setEstimateNumber(student.getStudentId());
			e.setCourseId(id);
			Estimate estimate=estimateService.selectEstimatesfcz(e);
			mv.addObject("estimate",estimate);
		}

		Courseresource cr = new Courseresource();
		cr.setCourseId(c.getCourseId());
		//cr.setCourseresourceType("6");
		List<Courseresource> list = courseresourceService.CourseresourceAll(cr);
		mv.addObject("crlist",list);
		mv.addObject("course",c);
		mv.addObject("courseId",id);
		mv.addObject("coursestudyId",coursestudyId);
		mv.addObject("tpk",tpk);
		mv.setViewName("web/courseDesc");
		return mv;
	}

	/**
	 * 学院星级评分
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/webEstimate", method = RequestMethod.GET)
	public ModelAndView webEstimate(String coursestudyId,String score,String id,String tpk,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		RandomGUID guid=new RandomGUID();
		Estimate e=new Estimate();
		Student student = (Student)session.getAttribute("Student");
		e.setEstimateId(guid.valueAfterMD5);
		e.setCourseId(id);
		e.setEstimatePoint(Integer.parseInt(score));
		e.setEstimateAt(new Date());
		e.setEstimateType(student.getStudentName());
		e.setEstimateNumber(student.getStudentId());
		e.setEstimateDel(1);
		int i=estimateService.insertSelective(e);
		if(tpk.equals("2")){
			mv.setViewName("redirect:courseInfoS?tpk="+tpk+"&coursestudyId="+coursestudyId+"&id="+id);
		}else{
			mv.setViewName("redirect:courseInfo?tpk="+tpk+"&coursestudyId="+coursestudyId+"&id="+id);
		}

		return mv;
	}

	/**
	 * 我的课程(个人介绍)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/mystudent", method = RequestMethod.GET)
	public ModelAndView mystudent(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}else{
			if(student.getStudentoneId()!=null){
				Studentone so = studentoneService.selectByPrimaryKey(student.getStudentoneId());
				mv.addObject("so",so);
			}
		}
		mv.setViewName("web/mystudent");
		return mv;
	}

	/**
	 * 我的课程(推送课程)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/mycourse", method = RequestMethod.GET)
	public ModelAndView mycourse(int page ,int type,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}

		Coursestudy cs =new Coursestudy();
		cs.setStudentId(student.getStudentId());
		if(type==1){
			cs.setCoursestudyType("必修课");
		}else if(type==2){
			cs.setCoursestudyType("选修课");
		}else{
			cs.setCoursestudyType("党校课");
		}
		PageBean pageBean = new PageBean();
		int allRow = coursestudyService.CoursestudyWebType(cs);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		cs.setPage(length*(currentPage1-1));
		cs.setSize(length);
		List<Coursestudy> list = coursestudyService.selectPageWebTypeAll(cs);
		Videotime videotime = new Videotime();
		for (int i = 0; i < list.size(); i++) {
			videotime.setCourseId(list.get(i).getCourseId());
			videotime.setStudentId(list.get(i).getStudentId());
			List<Videotime> time = videotimeService.timeByMark(videotime);
			if (time.size()!=0) {
				list.get(i).setCoursestudyStartime(time.get(0).getVideotimeAt());
				list.get(i).setCoursestudyEndtime(time.get(time.size()-1).getVideotimeAt());
			}
		}

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("mycourse?type="+type);//连接地址
		pageBean.init();
		mv.addObject("name",cs.getCoursestudyType());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("web/mycourse");
		return mv;
	}

	/**
	 * 我的培训班
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/myTraincourse", method = RequestMethod.GET)
	public ModelAndView myTraincourse(int page ,int type,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}

		Clastudent cla =new Clastudent();
		cla.setStudentId(student.getStudentId());
		cla.setClassresourceUpname(type+"");//培训班的分类
		PageBean pageBean = new PageBean();
		int allRow = clastudentService.selectAllInKey(cla);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		cla.setPage(length*(currentPage1-1));
		cla.setSize(length);
		List<Clastudent> list = clastudentService.classStudentPageAllIn(cla);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("myTraincourse?type="+type);//连接地址
		pageBean.init();
		//		mv.addObject("name",cla.getCoursestudyType());
		mv.addObject("pageBean",pageBean);
		mv.addObject("type",type);
		mv.setViewName("web/myTraincourse");
		return mv;
	}

	/**
	 * 我的学习轨迹
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/myTrainHistory", method = RequestMethod.GET)
	public ModelAndView myTrainHistory(int page,int type ,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		Coursestudy dy=new Coursestudy();
		dy.setStudentId(student.getStudentId());
		PageBean pageBean = new PageBean();
		int allRow = coursestudyService.TrainingReportTop(dy);
		final int length = 15;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		dy.setPage(length*(currentPage1-1));
		dy.setSize(length);
		List<Coursestudy> list = coursestudyService.TrainingReportTopAll(dy);

		Courseresource courseresource = new Courseresource();//课程资源
		Videotime videotime = new Videotime();//
		for (int i = 0; i < list.size(); i++) {
			Course courseName = courseService.selectByPrimaryKey(list.get(i).getCourseId());
			if (courseName!=null) {
				list.get(i).setCourseName(courseName.getCourseName());//获得课程名称
				//获取总学时
				courseresource.setCourseId(list.get(i).getCourseId());
				courseresource.setCourseresourceType("5");//5视频  7文件
				List<Courseresource> listCount = courseresourceService.CourseresourceAll(courseresource);
				list.get(i).setCoursestudyAlltime(new BigDecimal(listCount.size()));
				//获取现学时
				int time=0;
				if (listCount.size()!=0) {
					for (int j = 0; j <listCount.size(); j++) {
						videotime.setCourseresourceId(listCount.get(j).getCourseresourceId());
						videotime.setStudentId(student.getStudentId());
						//					videotime.setVideotimeMark("1");
						//					int shu = videotimeService.videtimeByMark(videotime);
						List<Videotime> shuList = videotimeService.videtimeByMarkList(videotime);
						if (shuList.size()!=0) {
							if (shuList.size()>Math.floor(Integer.parseInt(shuList.get(0).getVideotimeMark())/60)) {
								time=time+1;	
							}
						}
						//					if (shu>=1) {
						//					time=time+1;
						//					}
					}
					list.get(i).setCoursestudyNowtime(new BigDecimal(time));
					//学习进度coursestudyPlan
					if (listCount.size()>0&&time>0) {
						Double intimeRate2=((time*1.0)/listCount.size()*1.0);
						list.get(i).setCoursestudyPlan(Math.round(intimeRate2*1000)/10.0+"");
					}
					//成绩
					Test t = new Test();
					t.setCourseId(list.get(i).getCourseId());
					t.setStudentId(student.getStudentId());
					int sum = testService.testByscore(t);
					list.get(i).setSum(sum);


				}
			}

			Tranclass tranclassName = tranclassService.selectByPrimaryKey(list.get(i).getCoursestudyTranclassid());
			if (tranclassName!=null) {
				list.get(i).setTranclassName(tranclassName.getTranclassName());
			}

		}

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("myTrainHistory?type="+type);//连接地址
		pageBean.init();
		//		mv.addObject("name",cla.getCoursestudyType());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("web/myTrainHistory");
		return mv;
	}
	/**
	 * 视频页面
	 * @param id
	 * @return
	 * @modified Jiahua Jin
	 */

	@RequestMapping(value = "/courseVideo", method = RequestMethod.GET)
	public ModelAndView courseVideo(String courseId,String id,HttpSession session,String coursestudyId,int tpk) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		Courseresource c= courseresourceService.selectByPrimaryKey(id);
		if(!c.getCourseresourceType().equals("6")){//如归课程类型不为6，执行原来的逻辑
			if(student!=null){
				Videotime v = new Videotime();
				v.setCourseresourceId(c.getCourseresourceId());
				v.setStudentId(student.getStudentId());
				int sum = videotimeService.videtimeByMark(v);
				mv.addObject("sum",sum);
			}
			if(student!=null){
				Coursenote cn  =new Coursenote();
				cn.setCourseId(courseId);
				cn.setStudentId(student.getStudentId());
				List<Coursenote> coursenoteList=coursenoteService.selectAllList(cn);
				mv.addObject("coursenoteList",coursenoteList);
			}

			mv.addObject("tpk",tpk);
			mv.addObject("id",id);
			mv.addObject("courseId",courseId);
			mv.addObject("coursestudyId",coursestudyId);
			mv.addObject("courseresource",c);
			mv.setViewName("web/courseVideo");

		}else{//如归课程类型为6，则执行scorm课件
			if(student!=null){//课程笔记
				Coursenote cn  =new Coursenote();
				cn.setCourseId(courseId);
				cn.setStudentId(student.getStudentId());
				List<Coursenote> coursenoteList=coursenoteService.selectAllList(cn);
				mv.addObject("coursenoteList",coursenoteList);
			}

			mv.addObject("tpk",tpk);
			mv.addObject("id",id);
			mv.addObject("courseId",courseId);
			mv.addObject("coursestudyId",coursestudyId);
			mv.addObject("courseresource",c);
			mv.setViewName("web/scormPlayer/index");
		}
		return mv;
	}

	/**
	 * 课程播放器
	 * @author Jiahua Jin
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/courseScorm", method = RequestMethod.GET)
	public ModelAndView scormVideo(String id,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Courseresource c= courseresourceService.selectByPrimaryKey(id);
		System.out.println("addr     :"+c.getCourseresourceFileaddr());
		//String src = "http://localhost:8080/"+c.getCourseresourceFileaddr();//.replace("/Education/", "");
		String src = c.getCourseresourceFileaddr().replace("/Education/htdoc", "UploadFile");
		StringBuffer  url = request.getRequestURL();
		String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();  
		src=tempContextUrl+src;
		System.out.println("SRC:        -------    "+src);
		mv.addObject("src", src);
		mv.setViewName("web/scormPlayer/player");;
		return mv;
	}

	/**
	 * 党校课程
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/partySchool", method = RequestMethod.GET)
	public ModelAndView partySchool(int page,String sid ,HttpSession session) {
		ModelAndView mv = new ModelAndView();

		//目录树-------------------------------------------------------
		List<Syllabus> syllabus = new ArrayList<Syllabus>();
		syllabus = syllabusService.selectAllSyllabus();


		//----------------------查询条件-------------------------------
		Course cs =new Course();
		cs.setCourseType("党校课");
		if(!sid.equals("0")){
			cs.setSyllabusId(sid);
		}

		PageBean pageBean = new PageBean();
		int allRow = courseService.CoursesWebCount(cs);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		cs.setPage(length*(currentPage1-1));
		cs.setSize(length);

		List<Course> list = courseService.selectPageWebAll(cs);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("partySchool?sid="+sid);//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.addObject("syllabus",syllabus);
		mv.setViewName("web/partySchool");
		return mv;
	}

	/**
	 * 我的考试课程
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/allTest", method = RequestMethod.GET)
	public ModelAndView allTest(HttpSession session,int page,int type) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}

		Coursestudy cs =new Coursestudy();
		cs.setStudentId(student.getStudentId());
		if(type==1){
			cs.setCoursestudyType("必修课");
		}else if(type==2){
			cs.setCoursestudyType("选修课");
		}else{
			cs.setCoursestudyType("党校课");
		}
		PageBean pageBean = new PageBean();
		int allRow = coursestudyService.CoursestudyWebType(cs);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		cs.setPage(length*(currentPage1-1));
		cs.setSize(length);
		List<Coursestudy> list = coursestudyService.selectPageWebTypeAll(cs);
		for (int i = 0; i < list.size(); i++) {

			Test t = new Test();
			t.setCourseId(list.get(i).getCourseId());
			t.setStudentId(student.getStudentId());
			int mark = testService.testByscoreManags(t);
			if(mark>0){
				list.get(i).setSum(-1);
			}else{
				int sum = testService.testByscore(t);
				list.get(i).setSum(sum);
			}

		}

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("allTest?type="+type);//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.setViewName("web/allTest");
		return mv;
	}


	/**
	 * 我的考试培训班
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/traincourseTest", method = RequestMethod.GET)
	public ModelAndView traincourseTest(int page ,int type,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}

		Clastudent cla =new Clastudent();
		cla.setClastudentType(1);
		cla.setStudentId(student.getStudentId());
		cla.setClassresourceUpname(type+"");//培训班的分类
		PageBean pageBean = new PageBean();
		int allRow = clastudentService.selectAllInKey(cla);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		cla.setPage(length*(currentPage1-1));
		cla.setSize(length);
		List<Clastudent> list = clastudentService.classStudentPageAllIn(cla);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("myTraincourse?type="+type);//连接地址
		pageBean.init();
		//		mv.addObject("name",cla.getCoursestudyType());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("web/allTraincourse");
		return mv;
	}

	/**
	 * 
	 * 我的考试培训班(课程)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/traincourseCoursTest")
	public ModelAndView traincourseCoursTest(Coursestudy coursestudys ,int page, Model model,HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		coursestudys.setStudentId(student.getStudentId());
		PageBean pageBean = new PageBean();
		int allRow = coursestudyService.CoursestudyWebCount(coursestudys);
		final int length =20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		coursestudys.setPage(length*(currentPage1-1));
		coursestudys.setSize(length);

		List<Coursestudy> list = coursestudyService.selectPageWebAll(coursestudys);

		for (int i = 0; i < list.size(); i++) {
			Test t = new Test();
			t.setCourseId(list.get(i).getCourseId());
			t.setStudentId(student.getStudentId());
			int sum = testService.testByscore(t);
			list.get(i).setSum(sum);
		}


		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("traincourseCoursTest?mark=0&coursestudyType="+coursestudys.getCoursestudyType()+"&tranclassId="+coursestudys.getTranclassId());//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.setViewName("web/allTest");

		return  mv;
	}

	/**
	 * 试卷查看
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/allTestQuery", method = RequestMethod.GET)
	public ModelAndView allTestQuery(int mark,Test test,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		test.setStudentId(student.getStudentId());
		List<Test> list = testService.testByS(test);
		mv.addObject("mark",mark);
		mv.addObject("testlist",list);
		mv.setViewName("web/allTestQuery");
		return  mv;
	}








	//-------------------------------------------------------------------------党校--------------------------------------------------------------------
	//-------------------------------------------------------------------------党校--------------------------------------------------------------------
	//-------------------------------------------------------------------------党校--------------------------------------------------------------------
	//-------------------------------------------------------------------------党校--------------------------------------------------------------------
	//-------------------------------------------------------------------------党校--------------------------------------------------------------------
	/**
	 * 党校课程页面
	 */
	@RequestMapping(value = "/courseInfoS", method = RequestMethod.GET)
	public ModelAndView courseInfoS(String coursestudyId,String id,int tpk,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		Estimate e=new Estimate();
		Course c = courseService.selectByPrimaryKey(id);
		//-------------------浏览次数累加-----------------------------
		if(c.getCoursePv()!=null && c.getCoursePv()>0){
			c.setCoursePv(c.getCoursePv()+1);
		}else{
			c.setCoursePv(1);
		}
		int i = courseService.updateByPrimaryKeySelective(c);
		//----------------------------------------------------------------
		//---------------------查看是否评价次课程-------------------------------------------
		if(student!=null){
			e.setEstimateNumber(student.getStudentId());
			e.setCourseId(id);
			Estimate estimate=estimateService.selectEstimatesfcz(e);
			mv.addObject("estimate",estimate);
		}

		Courseresource cr = new Courseresource();
		cr.setCourseId(c.getCourseId());
		List<Courseresource> list = courseresourceService.CourseresourceAll(cr);
		mv.addObject("crlist",list);
		mv.addObject("course",c);
		mv.addObject("courseId",id);
		mv.addObject("coursestudyId",coursestudyId);
		mv.addObject("tpk",tpk);
		mv.setViewName("web/courseDescS");
		return mv;
	}

	/**
	 * 党校视频页面
	 * @param id
	 * @return
	 */

	@RequestMapping(value = "/courseVideoS", method = RequestMethod.GET)
	public ModelAndView courseVideoS(String courseId,String id,HttpSession session,String coursestudyId,int tpk) {
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		Courseresource c= courseresourceService.selectByPrimaryKey(id);
		if(!c.getCourseresourceType().equals("6")){//如归课程类型不为6，执行原来的逻辑
			if(student!=null){
				Videotime v = new Videotime();
				v.setCourseresourceId(c.getCourseresourceId());
				v.setStudentId(student.getStudentId());
				int sum = videotimeService.videtimeByMark(v);
				mv.addObject("sum",sum);
			}
			if(student!=null){
				Coursenote cn  =new Coursenote();
				cn.setCourseId(courseId);
				cn.setStudentId(student.getStudentId());
				List<Coursenote> coursenoteList=coursenoteService.selectAllList(cn);
				mv.addObject("coursenoteList",coursenoteList);
			}

			mv.addObject("tpk",tpk);
			mv.addObject("id",id);
			mv.addObject("courseId",courseId);
			mv.addObject("coursestudyId",coursestudyId);
			mv.addObject("courseresource",c);
			mv.setViewName("web/courseVideoS");

		}else{//如归课程类型为6，则执行scorm课件
			if(student!=null){//课程笔记
				Coursenote cn  =new Coursenote();
				cn.setCourseId(courseId);
				cn.setStudentId(student.getStudentId());
				List<Coursenote> coursenoteList=coursenoteService.selectAllList(cn);
				mv.addObject("coursenoteList",coursenoteList);
			}

			mv.addObject("tpk",tpk);
			mv.addObject("id",id);
			mv.addObject("courseId",courseId);
			mv.addObject("coursestudyId",coursestudyId);
			mv.addObject("courseresource",c);
			mv.setViewName("web/scormPlayer/index_new");
		}


		return mv;
	}


	/**
	 * 党校登录前主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/partySchoolTraining", method = RequestMethod.GET)
	public ModelAndView partySchoolTraining(Model model) {
		Notice notice = new Notice();
		News news = new News();
		notice.setOrganizationId("test001");
		notice.setNoticeSort("2");
		news.setOrganizationId("test001");
		news.setNewsSort("3");
		List<Notice>nolist = noticeService.NoticeByIdAll(notice);
		List<News>nlist =newsService.NewsByOIdAll(news);
		news.setNewsSort("4");
		List<News>gdlist =newsService.NewsByOIdAll(news);
		Course course = new Course();
		course.setCourseType("2");
		List<Course>clist = courseService.CourseAll(course);

		Tranclass tranclass = new Tranclass();
		List<Tranclass>tranclassList = tranclassService.selectShiftDxSix(tranclass);

		model.addAttribute("tranclassList", tranclassList);
		model.addAttribute("clist", clist);
		model.addAttribute("nlist", nlist);
		model.addAttribute("gdlist", gdlist);
		model.addAttribute("nolist", nolist);
		return new ModelAndView("web/partySchoolTraining");
	}


	/**
	 * 党校培训动态
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Schooltraining", method = RequestMethod.GET)
	public ModelAndView Schooltraining(Model model) {
		Notice notice = new Notice();
		News news = new News();
		Programme p = new Programme();
		notice.setOrganizationId("test001");
		notice.setNoticeSort("2");
		news.setOrganizationId("test001");
		news.setNewsSort("3");
		p.setOrganizationId("test001");
		p.setProgrammeSort("2");
		List<Notice>nolist = noticeService.NoticeByIdAll(notice);
		List<News>nlist =newsService.NewsByOIdAll(news);
		List<Programme>plist = programmeService.ProgrammeByOIdAll(p);


		model.addAttribute("plist", plist);
		model.addAttribute("nlist", nlist);
		model.addAttribute("nolist", nolist);
		return new ModelAndView("web/trainingSchool");
	}


	/**
	 * 党校详细显示页
	 * @param id
	 * @param mark
	 * @return
	 */
	@RequestMapping(value = "/SchoolnoticeInfo", method = RequestMethod.GET)
	public ModelAndView SchoolnoticeInfo(String id,int mark) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("tpk",2);
		if(mark==1){//通知
			Notice notice = new Notice();
			notice.setNoticeId(id);
			notice = noticeService.NoticeById(notice);
			mv.addObject("notice",notice);
			mv.setViewName("web/SnoticeInformation");
		}else if(mark==2){//新闻
			News news = new News();
			news = newsService.selectByPrimaryKey(id);
			mv.addObject("news",news);
			mv.setViewName("web/SnewsInformation");
		}else if(mark==3){//计划
			Programme p = new Programme();
			p =programmeService.selectByPrimaryKey(id);
			mv.addObject("programme",p);
			mv.setViewName("web/SprogrammeInformation");
		}

		return mv;
	}


	/**
	 * 党校更多显示页
	 * @param id
	 * @param mark
	 * @return
	 */
	@RequestMapping(value = "/SchoolnoticeInfoAll", method = RequestMethod.GET)
	public ModelAndView SchoolnoticeInfoAll(String oid,String sort,int mark,int page,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		int type = 1;
		Student student = (Student)session.getAttribute("Student");
		if(student!=null){
			oid = student.getStudentCompanyid();
			type = Integer.parseInt(student.getOrganization().getOrganizationFwqx());
		}
		if(mark==1){//------------------------------------------------通知
			Notice notice = new Notice();
			notice.setNoticeSort(sort);
			notice.setOrganizationId(oid);
			notice.setType(type);
			//------------------------------------判断置顶时间，到期修改置顶状态-------------------------------------
			List<Notice> toplist = noticeService.webNoticeTOP(notice);
			if(toplist.size()>0){
				for(int i=0 ; i<toplist.size();i++){
					Notice topn = new Notice();
					topn = toplist.get(i);
					topn.setNoticeTop(0);
					noticeService.updateByPrimaryKeySelective(topn);
				}
			}

			PageBean pageBean = new PageBean();
			int allRow = noticeService.webNoticeCount(notice);
			final int length = 50;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			notice.setPage(length*(currentPage1-1));
			notice.setSize(length);

			List<Notice> list = noticeService.webPageAll(notice);

			pageBean.setPageSize(length);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("SchoolnoticeInfoAll?mark="+mark+"&oid="+oid+"&sort="+sort);//连接地址
			pageBean.init();
			mv.addObject("mark",mark);
			mv.addObject("pageBean",pageBean);
		}else if(mark==2){//-----------------------------------------------------新闻
			News news = new News();
			news.setNewsSort(sort);
			news.setOrganizationId(oid);
			news.setType(type);
			//------------------------------------判断置顶时间，到期修改置顶状态-------------------------------------
			List<News> toplist = newsService.webNewsTOP(news);
			if(toplist.size()>0){
				for(int i=0 ; i<toplist.size();i++){
					News topn = new News();
					topn = toplist.get(i);
					topn.setNewsTop(0);
					newsService.updateByPrimaryKeySelective(topn);
				}
			}

			PageBean pageBean = new PageBean();
			int allRow = newsService.webNewsCount(news);
			final int length = 50;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			news.setPage(length*(currentPage1-1));
			news.setSize(length);

			List<News> list = newsService.webPageAll(news);

			pageBean.setPageSize(length);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("SchoolnoticeInfoAll?mark="+mark+"&oid="+oid+"&sort="+sort);//连接地址
			pageBean.init();
			mv.addObject("mark",mark);
			mv.addObject("pageBean",pageBean);
		}else if(mark==3){//-----------------------------------------------------计划
			Programme p = new Programme();
			p.setProgrammeSort(sort);
			p.setOrganizationId(oid);
			p.setType(type);
			//------------------------------------判断置顶时间，到期修改置顶状态-------------------------------------
			List<Programme> toplist = programmeService.webProgrammeTOP(p);
			if(toplist.size()>0){
				for(int i=0 ; i<toplist.size();i++){
					Programme topn = new Programme();
					topn = toplist.get(i);
					topn.setProgrammeTop(0);
					programmeService.updateByPrimaryKeySelective(topn);
				}
			}

			PageBean pageBean = new PageBean();
			int allRow = programmeService.webProgrammeCount(p);
			final int length = 50;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			p.setPage(length*(currentPage1-1));
			p.setSize(length);

			List<Programme> list = programmeService.webPageAll(p);

			pageBean.setPageSize(length);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("SchoolnoticeInfoAll?mark="+mark+"&oid="+oid+"&sort="+sort);//连接地址
			pageBean.init();
			mv.addObject("mark",mark);
			mv.addObject("pageBean",pageBean);
		}else if(mark==4){//--------------------------------------------培训班
			Tranclass tranclass = new Tranclass();
			PageBean pageBean = new PageBean();
			int allRow = tranclassService.ShiftCountPage(tranclass);
			final int length = 50;//每页记录数
			int totalPage = PageBean.countTotalPage(length, allRow);//总页数
			final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

			tranclass.setPage(length*(currentPage1-1));
			tranclass.setSize(length);

			List<Tranclass> list = tranclassService.selectShiftDxPage(tranclass);

			pageBean.setPageSize(length);
			pageBean.setCurrentPage(currentPage1);
			pageBean.setAllRow(allRow);
			pageBean.setTotalPage(totalPage);
			pageBean.setList(list);
			pageBean.setUrl("SchoolnoticeInfoAll?mark="+mark+"&oid="+oid+"&sort="+sort);//连接地址
			pageBean.init();
			mv.addObject("mark",mark);
			mv.addObject("pageBean",pageBean);
		}
		mv.setViewName("web/trainingSchoolList");
		return mv;
	}



	/**
	 * 登录后的主页
	 * @return
	 */
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(Model model,HttpSession session) {
		Notice notice = new Notice();
		News news = new News();
		notice.setOrganizationId("test001");
		notice.setNoticeSort("1");
		news.setOrganizationId("test001");
		news.setNewsSort("1");
		List<Notice>nolist = noticeService.NoticeByIdAll(notice);
		List<News>nlist =newsService.NewsByOIdAll(news);
		news.setNewsSort("2");
		List<News>gdlist =newsService.NewsByOIdAll(news);
		//课程查询
		Course course = new Course();
		course.setCourseType("1");
		List<Course>clist = courseService.CourseAll(course);
		List<Course>clist1 =courseService.CourseAllPV();


		model.addAttribute("clist1", clist1);
		model.addAttribute("clist", clist);
		model.addAttribute("nlist", nlist);
		model.addAttribute("gdlist", gdlist);
		model.addAttribute("nolist", nolist);
		Student student = (Student)session.getAttribute("Student");
		if(student!=null){
			return new ModelAndView("web/main");
		}else{
			return new ModelAndView("redirect:mainout");
		}

	}

	/**
	 * 退出登录
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/mainlogout", method = RequestMethod.GET)
	public String mainlogout(HttpSession session,HttpServletRequest request) {
		Student student = (Student)session.getAttribute("Student");
		log.setLogAt(sdf.format(new Date()));
		log.setLogContent("用户："+student.getStudentSeq()+"，登出。");
		log.setLogId(Tools.getGUID());
		log.setLogIp(HYLUtil.getIP4(request));
		log.setLogUser(student.getStudentSeq());
		log.setLogUserType("前台用户");
		logService.insertSelective(log);
		session.removeValue("Student");
		return "redirect:mainout";
	}



	/**
	 * 学员登录接口
	 * @param s
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/StudentLoginInterface")
	public ModelAndView StudentLoginInterface(Studentone student,HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		Student s = studentService.interfaceLogin(student.getStudentoneSfzh());
		if(s !=null){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+s.getStudentSeq()+"，登陆。");
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(s.getStudentSeq());
			log.setLogUserType("前台用户");
			logService.insertSelective(log);

			session.setAttribute("Student", s);
		}
		mv.setViewName("redirect:main");
		return mv;
	}

	/**
	 * 学员登录接口
	 * @param s
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/newGitLogin")
	public ModelAndView newGitLogin(HttpSession session,HttpServletRequest request,HttpServletResponse response) throws IOException, ParseException {
		ModelAndView mv = new ModelAndView();
		response.setContentType("text/html;charset=UTF-8");
		Studentone student = new Studentone();
		String dnname = request.getHeader("dnname");
		System.out.println("头信息获取="+dnname);
		if (dnname != null) {
			dnname = new String(dnname.getBytes("ISO8859-1"), "GB2312");
			System.out.println("主题号" + dnname);
			String identity = null;
			int ii = dnname.indexOf("CN=");
			if (ii != -1 ) {
				dnname = dnname.substring(ii + 3);
				int jj = dnname.indexOf(",");
				if (jj != -1) {
					identity = dnname.substring(0, jj);
					student.setStudentoneName(identity);
				}
				System.out.println("姓名=" + identity);
			}

			int i1 = dnname.indexOf("T=");
			if (i1 != -1 && dnname.length() > 15) {
				dnname = dnname.substring(i1 + 2);
				int j = dnname.indexOf(",");
				if (j != -1) {
					identity = dnname.substring(0, j);
					student.setStudentoneSfzh(identity);
				}
				System.out.println("身份证=" + identity);
			}
			Student s = studentService.interfaceLogin(student.getStudentoneSfzh());
			if(s !=null){
				log.setLogAt(sdf.format(new Date()));
				log.setLogContent("用户："+s.getStudentSeq()+"，登陆。");
				log.setLogId(Tools.getGUID());
				log.setLogIp(HYLUtil.getIP4(request));
				log.setLogUser(s.getStudentSeq());
				log.setLogUserType("前台用户");
				logService.insertSelective(log);

				session.setAttribute("Student", s);
			}else{
				if(student.getStudentoneSfzh()!=null && !student.getStudentoneSfzh().equals("") ){
					student.setStudentoneName("云平台用户");
					student.setStudentoneId(Tools.getGUID());
					student.setStudentoneAt(sdf.parse(sdf.format(new Date())));
					student.setStudentoneDel(1);
					int i = studentoneService.insert(student);
					if(i>0){
						Student sss = new Student();
						sss.setStudentoneId(student.getStudentoneId());
						sss.setStudentId(Tools.getGUID());
						sss.setStudentAt(sdf.parse(sdf.format(new Date())));
						sss.setStudentDel(1);
						sss.setStudentPassword("123456");
						sss.setStudentSeq(Tools.getGUID());
						sss.setStudentName(student.getStudentoneName());
						sss.setStudentCompanyid("test001");
						sss.setStudentDepartment("JHzyyyh");
						sss.setStudentCategory("云用户");
						sss.setStudentType(1);
						int j = studentService.insertSelective(sss);
						if(j>0){
							log.setLogAt(sdf.format(new Date()));
							log.setLogContent("用户："+sss.getStudentSeq()+"，登陆。");
							log.setLogId(Tools.getGUID());
							log.setLogIp(HYLUtil.getIP4(request));
							log.setLogUser(sss.getStudentSeq());
							log.setLogUserType("前台用户");
							logService.insertSelective(log);
							s = studentService.interfaceLogin(student.getStudentoneSfzh());
							session.setAttribute("Student", s);
						}

					}
				}
			}
		}else{
			PrintWriter out = null;
			out = response.getWriter();
			out.print("<script>alert('没有获取到头信息')</script>");
			out.print("<script>window.location.href='main'</script>");
			out.flush();
		}

		mv.setViewName("redirect:main");
		return mv;
	}



}
