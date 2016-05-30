package com.ttgis.education.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Classinfo;
import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.Clastudent;
import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Coursestudy;
import com.ttgis.education.entity.Department;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Msg;
import com.ttgis.education.entity.News;
import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Programme;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Syllabus;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.Videotime;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.AccountService;
import com.ttgis.education.service.ClassinfoService;
import com.ttgis.education.service.ClassresourceService;
import com.ttgis.education.service.ClastudentService;
import com.ttgis.education.service.CourseService;
import com.ttgis.education.service.CoursestudyService;
import com.ttgis.education.service.NewsService;
import com.ttgis.education.service.NoticeService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.ProgrammeService;
import com.ttgis.education.service.ShiftService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.service.TranclassService;
import com.ttgis.education.service.VideotimeService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.RandomGUIDUtil;
/**
 * 前台首页
 * @author 王艳鹏
 *
 */
@Controller
public class trainingCourseController {
	
	@Resource
	private AccountService accountService;
	@Resource
	private NewsService newsService;
	@Resource
	private ProgrammeService programmeService;
	@Resource
	private NoticeService noticeService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	private TranclassService tranclassService;
	@Resource
	private ClassresourceService classresourceService;
	@Resource
	private ShiftService shiftService;
	@Resource
	private ClastudentService clastudentService;
	@Resource
	private StudentService studentService;
	@Resource
	private CourseService courseService;
	@Resource
	private ClassinfoService classinfoService;
	@Resource
	private CoursestudyService coursestudyService;
	@Resource
	private VideotimeService videotimeService;
	
	private Classresource classresource;
	private Tranclass tranclass;
	private Clastudent clastudent;
	private Course course;
	private Classinfo classinfo;
	private Coursestudy coursestudy;
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(trainingCourseController.class);
	
	
	/**
	 * 培训班资源管理树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tranningCourseTree", method = RequestMethod.GET)
	public ModelAndView tranningCourseTree(String classresourceId,int n,Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
//		Message msg = new Message();
//		if (account==null) {
//			msg.setMessage("请你登录你的账号或者你的账号已经过期请从新登录");
//			msg.setUri("login");
//			return new ModelAndView("success");
//		} 
		Organization oneid = organizationService.selectByPrimaryKey(account.getAccountCatalog());
		List<Organization> organization = organizationService.selectAllOrganization();
  		model.addAttribute("organization", organization);
  		model.addAttribute("account", account);
  		model.addAttribute("oneid", oneid);
		if(n == 1){
			model.addAttribute("type", "常规培训班");
		}
		if(n == 2){
			model.addAttribute("type", "专题培训班");
		}
		if(n == 3){
			model.addAttribute("type", "党校培训班");
		}
		if(n == 4){
			model.addAttribute("type", "培训班");
			model.addAttribute("ids", classresourceId);
		}
		return new ModelAndView("tree/trainingCourseTree");
	}
	//==========================党校培训班树============================//
	/**
	 * 取得课程管理目录树和账号类型 课程管理目录树
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/partyTrainTree", method = RequestMethod.POST)
	public Msg tree(HttpSession session)
	{
		logger.debug("查询课程目录树");
		List<Classresource> classresource = new ArrayList<Classresource>();
		Account acc = (Account) session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){
			Classresource s = new Classresource();
			s.setOrganizationId(acc.getAccountCatalog());
			classresource = classresourceService.selectPartyTrain(s);
		}else{
//			Syllabus s = new Syllabus();
//			s.setOrganizationId(acc.getAccountCatalog());
//			syllabus = syllabusService.AccAllSyllabus(s);
		}


		return new Msg(acc.getAccountType().toString(), classresource, Msg.SUCCESS);
	}

	/**
	 *   添加树节点 提交上来两个字段 syllabusName， syllabusName
	 * 
	 * @param syllabus
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/partyTrainAddTreeNode", method = RequestMethod.POST)
	public Msg partyTrainAddTreeNode(Classresource classresource, HttpSession session)
	{
		int ch =  0;
		logger.debug("添加树节点:");
		Account acc = (Account) session.getAttribute("sessionUser");
		acc.setAccountCatalog("test001");
		Classresource s  = classresourceService.selectByPrimaryKey(classresource.getClassresourceUpid());
		if(acc.getAccountType()==1){
			if(s.getOrganizationId().equals(acc.getAccountCatalog()) && s.getClassresourceId().equals(" 1AD34C2B-0A29-5325-1613-3082518F4562")){
				ch=1;
			}
		}
//		else{
//			if(s.getOrganizationId().equals(acc.getAccountCatalog()) || s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
//				ch=1;
//			}
//		}
		if(ch!=0){
			if (classresource == null)
				return new Msg("异常消息", "提交数据异常", Msg.ERROR);
			classresource.setOrganizationId(acc.getAccountCatalog());
			classresource.setClassresourceAt(new Date());
			classresource.setClassresourceDel(1);
			classresource.setClassresourceId(RandomGUIDUtil.generateKey());
			classresource.setClassresourceRank(1);
			classresource.setClassresourceUpname("0");
			int r = classresourceService.insertSelective(classresource);
			return new Msg(String.valueOf(r), classresource, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}

	/**
	 * 删除给定节点及其直接子节点
	 * 
	 * @param syllabusId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/partyTrainDelTreeNode", method = RequestMethod.POST)
	public Msg delTreeNode(String classresourceId,HttpSession session)
	{
		int ch =  0;
		Account acc = (Account) session.getAttribute("sessionUser");
		acc.setAccountCatalog("test001");
		Classresource s  = classresourceService.selectByPrimaryKey(classresourceId);
		logger.debug("删除树节点:" + classresourceId);
		if(acc.getAccountType()==1){
			if(s.getOrganizationId().equals(acc.getAccountCatalog()) && !s.getClassresourceId().equals(" 1AD34C2B-0A29-5325-1613-3082518F4562")){
				ch=1;
			}
		}
		/*else{
			if(s.getOrganizationId().equals(acc.getAccountCatalog()) || s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
				ch=1;
			}
		}*/
		if(ch!=0){
			if (classresourceId == null || classresourceId == "")
				return new Msg("异常消息", "提交数据异常", Msg.ERROR);
			int r = classresourceService.deletesByPrimaryKey(classresourceId);
			if (r == 0)
				return new Msg("异常消息", "数据删除异常", Msg.ERROR);
			return new Msg("提示", r, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}

	/**
	 *  根据id更新树节点名称，其他属性不更新
	 * 
	 * @param syllabus
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/partyTrainUpdateTreeNode", method = RequestMethod.POST)
	public Msg updateTreeNode(Classresource classresource,HttpSession session)
	{
		int ch =  0;
		Account acc = (Account) session.getAttribute("sessionUser");
		acc.setAccountCatalog("test001");
		Classresource s2  = classresourceService.selectByPrimaryKey(classresource.getClassresourceId());
		logger.debug("修改树节点名称:" + classresource.getClassresourceId() + "," + classresource.getClassresourceName());
		if(acc.getAccountType()==1){
			if(s2.getOrganizationId().equals(acc.getAccountCatalog()) && !s2.getClassresourceId().equals(" 1AD34C2B-0A29-5325-1613-3082518F4562")){
				ch=1;
			}
		}
//		else{
//			if(s2.getOrganizationId().equals(acc.getAccountCatalog()) || s2.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
//				ch=1;
//			}
//		}
		if(ch!=0){
			if (classresource == null)
				return new Msg("异常消息", "提交数据异常", Msg.ERROR);
			Classresource s = classresourceService.selectByPrimaryKey(classresource.getClassresourceId());
			s.setClassresourceName(classresource.getClassresourceName());
			int r = classresourceService.updateByPrimaryKey(s);
			if (r == 0)
				return new Msg("异常消息", "数据删除异常", Msg.ERROR);
			return new Msg("提示", r, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}

	//==========================党校培训班树============================//
	
	/**
	 * 查询培训班   目录（后台）
	 * @return
	 */
	@RequestMapping(value = "/queryClassCatalog")
	public ModelAndView queryClassCatalog(Classresource classCatalog,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			classresource = new Classresource();
		}
		
		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){//集团账号
			classresource.setOrganizationId(classCatalog.getOrganizationId());
			classresource.setTypes(acc.getAccountType());
		}
		if(acc.getAccountType()==2){//院一级账号
			if(cj==1){//判断前台是否点击第1层
				classresource.setOrganizationId(acc.getAccountCatalog());
			}else{
				classresource.setOrganizationId(classCatalog.getOrganizationId());
			}
			classresource.setTypes(acc.getAccountType());

		}
		if(acc.getAccountType()==3){//院二级账号
			classresource.setOrganizationId(acc.getAccountCatalog());
			classresource.setTypes(acc.getAccountType());
		}
		
		classresource.setCj(cj);
		classresource.setClassresourceName(classCatalog.getClassresourceName());
		
		PageBean pageBean = new PageBean();
		classresource.setClassresourceUpname(classCatalog.getClassresourceUpname());
		int allRow = classresourceService.ClassresourceCount(classresource);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		classresource.setPage(length*(currentPage1-1));
		classresource.setSize(length);

		List<Classresource> list = classresourceService.selectClassresourcePageAll(classresource);

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryClassCatalog?mark=0&cj="+cj+"&organizationId="+classCatalog.getOrganizationId()+"&classresourceUpname="+classCatalog.getClassresourceUpname());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("classCatalog",classCatalog);
		mv.addObject("pageId",classCatalog.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("trainingCourse/queryClassCatalog");
		return  mv;

	}
	
	
	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addEditPage", method = RequestMethod.GET)
	public ModelAndView addEditPage(String id,String pageClassId,Classresource clas) {
		ModelAndView mv = new ModelAndView();
		classresource = new Classresource();
		if(id==null){
			mv.addObject("cj",clas.getClassresourceRank());
			mv.addObject("pageClassId",pageClassId);
			mv.addObject("type",clas.getClassresourceUpname());
			mv.setViewName("trainingCourse/addClassCatalog");
		}else{
			clas.setClassresourceId(id);
			Classresource classList = classresourceService.selectByPrimaryKey(id);
			mv.addObject("classList",classList);
			mv.addObject("cj",clas.getClassresourceRank());
			mv.setViewName("trainingCourse/editClassCatalog");
		}

		return mv;
	}
	  
	
	/**
	 * 添加常规培训班
	 * 
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/classSava" , method = RequestMethod.POST)
	public ModelAndView classSava(Classresource clas) {
		ModelAndView mv = new ModelAndView();
		Organization company = organizationService.selectByPrimaryKey(clas.getOrganizationId());//单位名称和代码号
		//时间转换
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		clas.setClassresourceId(RandomGUIDUtil.generateKey());//培训班ID
//		clas.setClassresourceRank(1);//培训班级别
//	    clas.setClassresourceUpname(company.getOrganizationDwmc());//上级目录名称
		clas.setClassresourceUpid(company.getOrganizationDwdm());//上级目录id
//		clas.setClassresourceAt(time.format(nowTime).toString());
		clas.setClassresourceAt(new Date());//添加时间
		clas.setClassresourceDel(1);//删除标记
		int i = classresourceService.insertSelective(clas);//执行添加
		if(i>0){
			mv.setViewName("redirect:queryClassCatalog?mark=1&organizationId="+clas.getOrganizationId()+"&page=1&cj="+clas.getClassresourceRank()+"&classresourceUpname="+clas.getClassresourceUpname());
		}else{
			mv.addObject("pageClassId",clas.getOrganizationId());
			mv.setViewName("trainingCourse/addClassCatalog");
		}
		return mv;
	}
	
	/**
	 * 修改培训班信息
	 * 
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/classUpdate" , method = RequestMethod.POST)
	public ModelAndView classUpdate(Classresource clas) {
		ModelAndView mv = new ModelAndView();
		clas.setClassresourceName(clas.getClassresourceName());//修改名称
		clas.setClassresourceAt(new Date());//添加修改时间
		
		int i = classresourceService.updateByPrimaryKeySelective(clas);
		if(i>0){ 
				mv.setViewName("redirect:queryClassCatalog?mark=1&organizationId="+clas.getOrganizationId()+"&page=1&cj="+clas.getClassresourceRank()+"&classresourceUpname="+clas.getClassresourceUpname());
		}else{
			mv.addObject("classList",clas);
			mv.addObject("cj",clas.getClassresourceRank());
			mv.setViewName("trainingCourse/editClassCatalog");
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
	@RequestMapping(value = "/deleteClass", method = RequestMethod.GET)
	public String deleteClass(String classresourceId) {
		Message msg = new Message();
		classresource=new Classresource();
		int sug = 0;
		if (classresourceId!=null) {
			classresource.setClassresourceId(classresourceId);
			classresource.setClassresourceDel(0);
			 sug = classresourceService.updateByPrimaryKeySelective(classresource);
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
	/***********************************************培训班（底层）***********************************************/
	/**
	 * 查询培训班   （后台）
	 * @return
	 */
	@RequestMapping(value = "/queryShift")
	public ModelAndView queryShift(Tranclass tranclas,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			tranclass = new Tranclass();
		}
		if(cj==4){
			tranclass.setTypese(4);
		}else{
			tranclass.setTypese(1);
		}
		//添加培训班类别  
		 if(tranclas.getClasslb()!=null && tranclas.getClasslb()!="" ){
			 tranclass.setClasslb(tranclas.getClasslb());
			}else {
				 tranclass.setClasslb("");
			}
		//党校培训班 删掉返回
		 if(tranclas.getClassresourceUpname()!=null && tranclas.getClassresourceUpname()!="" ){
			 tranclass.setClassresourceUpname(tranclas.getClassresourceUpname());
			}
		
		Account account = (Account)session.getAttribute("sessionUser");
		
		tranclass.setAccountids(account.getAccountId());
		tranclass.setTranclassName(tranclas.getTranclassName());
		Classresource zzjd = classresourceService.selectByPrimaryKey(tranclas.getClassresourceId());
		tranclass.setClassresourceId(tranclas.getClassresourceId());
		
		
		PageBean pageBean = new PageBean();
		int allRow = tranclassService.ShiftCount(tranclass);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		tranclass.setPage(length*(currentPage1-1));
		tranclass.setSize(length);

		List<Tranclass> list = tranclassService.selectShiftPageAll(tranclass);
		
	/*	clastudent = new Clastudent();

		  for(int i = 0; i < list.size(); i++)  
	        {  
			  clastudent.setType(1);
			  clastudent.setTranclassId(list.get(i).getTranclassId());
			  int allRows = clastudentService.classStudentCount(clastudent); 
			  Tranclass trans = new Tranclass();
			  trans.setTranclassNumber(allRows);
			  trans.setTranclassId(list.get(i).getTranclassId());
			  int j=tranclassService.updateByPrimaryKeySelective(trans);
			  list.get(i).setTranclassNumber(allRows);
	            //System.out.println(list.get(i));  
	        } */
		

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryShift?mark=0&cj="+cj+"&classresourceId="+tranclas.getClassresourceId()+"&classlb="+tranclass.getClasslb());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("tranclas",tranclass);
		mv.addObject("pageId",tranclas.getClassresourceId());
		mv.addObject("pageBean",pageBean);
		mv.addObject("zzjg",zzjd);
		mv.setViewName("trainingCourse/queryShift");
		return  mv;

	}
	
	/**
	 * 查看报名学员   （后台）
	 * @return
	 */
	@RequestMapping(value = "/queryStudentOne")
	public ModelAndView queryStudentOne(Clastudent Clastudents,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			clastudent = new Clastudent();
		}
		//-----返回-----
		Tranclass tranc = tranclassService.selectByPrimaryKey(Clastudents.getTranclassId());
		//--------------------
		clastudent.setTranclassId(Clastudents.getTranclassId());
		clastudent.setType(Clastudents.getType());
		//--------------------
		PageBean pageBean = new PageBean();
		int allRow = clastudentService.classStudentCount(clastudent);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		clastudent.setPage(length*(currentPage1-1));
		clastudent.setSize(length);

		List<Clastudent> list = clastudentService.classStudentPageAll(clastudent);

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		if (Clastudents.getType()!=1) {
			pageBean.setUrl("queryStudentOne?mark=0&type=0&cj="+cj+"&tranclassId="+Clastudents.getTranclassId()+"&classlb="+Clastudents.getClasslb());//连接地址
		} else {
			pageBean.setUrl("queryStudentOne?mark=0&type=1&cj="+cj+"&tranclassId="+Clastudents.getTranclassId()+"&classlb="+Clastudents.getClasslb());//连接地址
		}
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("Clastudents",Clastudents);
		mv.addObject("tranc",tranc);
		mv.addObject("pageId",Clastudents.getTranclassId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("trainingCourse/queryClassStudents");
		return  mv;

	}
	/**
	 * 审核学员（后台）
	 * 并且添加 到 Coursestudy （课程学习情况）里添加
	 * @param Tranclass
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/StudentExamination", method = RequestMethod.POST)
	public String StudentExamination(Clastudent clastudents) throws ParseException {
		clastudents.setClastudentType(1);//学员状态   1 通过       0 未通过
		try {
		
			Coursestudy dy=new Coursestudy();
			int i = clastudentService.updateByPrimaryKeySelective(clastudents);
			if (i>0) {
				//审核通过同时   把培训班的课程推送给学员
				List<Classinfo> info = classinfoService.selectByTranclassIdKey(clastudents.getTranclassId());	
				for (int j = 0; j < info.size(); j++) {
					
					dy.setCoursestudyId(RandomGUIDUtil.generateKey());
					dy.setCourseId(info.get(j).getCourseId());
					dy.setStudentId(clastudents.getStudentId());
					dy.setCoursestudyType("2");//抱培训班给学员推送的课程
					dy.setCoursestudyAt(new Date());
					dy.setCoursestudyDel(1);
					dy.setCoursestudyTranclassid(info.get(j).getTranclassId());
					//判断是否重复
					List<Coursestudy> panduan = coursestudyService.selectPanDuanKey(dy);
					if (panduan.size()==0) {
						 int z=coursestudyService.insertSelective(dy);
					}
						
			}
				//记录培训班的人数
			      clastudents.setType(1);
				  int allRows = clastudentService.classStudentCount(clastudents); 
				  Tranclass trans = new Tranclass();
				  trans.setTranclassNumber(allRows);
				  trans.setTranclassId(clastudents.getTranclassId());
				  int j=tranclassService.updateByPrimaryKeySelective(trans);
				
			}
			
		} catch (Exception e) {
			return "";
		}
		return "ok";
	}
	
	/**
	 * 添加课程查询   （后台）
	 * @return
	 */
	@RequestMapping(value = "/queryClassCourse")
	public ModelAndView queryClassCourse(Course courses,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			course = new Course();
		}
		//---返回---
		Tranclass tranc = tranclassService.selectByPrimaryKey(courses.getTranclassId());
		//--------------------
		course.setOrganizationId(courses.getOrganizationId());
		course.setTranclassId(courses.getTranclassId());
//		course.setType(courses.getType());
		//--------------------
		PageBean pageBean = new PageBean();
		int allRow = courseService.classCourseCount(course);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		course.setPage(length*(currentPage1-1));
		course.setSize(length);

		List<Course> list = courseService.classCoursePageAll(course);
		
		

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
//		if (Clastudents.getType()!=1) {
//			pageBean.setUrl("queryStudentOne?mark=0&type=0&cj="+cj+"&tranclassId="+Clastudents.getTranclassId());//连接地址
//		} else {
//			pageBean.setUrl("queryStudentOne?mark=0&type=1&cj="+cj+"&tranclassId="+Clastudents.getTranclassId());//连接地址
//		}
		pageBean.setUrl("queryClassCourse?mark=0&type=1&cj="+cj+"&organizationId="+courses.getOrganizationId()+"&tranclassId="+courses.getTranclassId()+"&classlb="+courses.getClasslb());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("courses",courses);
		mv.addObject("tranc",tranc);
		mv.addObject("pageId",courses.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("trainingCourse/queryClassCourse");
		return  mv;

	}
	/**
	 * 给培训班添加课程（后台）
	 * 
	 * @param Tranclass
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/addClassCourses", method = RequestMethod.POST)
	public int addClassCourses(Course courses) throws ParseException {
		int mark;
		Course mc = courseService.selectByPrimaryKey(courses.getCourseId());
		Classinfo classinfo = new Classinfo();
		Coursestudy dy=new Coursestudy();
		classinfo.setClassinfoId(RandomGUIDUtil.generateKey());
		classinfo.setCourseId(courses.getCourseId());
		classinfo.setTranclassId(courses.getTranclassId());
		classinfo.setClassinfoName(mc.getCourseName());
		classinfo.setClassinfoAt(new Date());
		classinfo.setClassinfoDel(1);
		try {
			List<Classinfo> siList = classinfoService.selectByClasId(classinfo);
			if (siList.size() == 0) {
				int i = classinfoService.insertSelective(classinfo);
				if (i>0) {
				List<Clastudent> peoples = clastudentService.selectClasId(courses.getTranclassId());
				for (int j = 0; j < peoples.size(); j++) {
				dy.setCoursestudyId(RandomGUIDUtil.generateKey());
				dy.setCourseId(courses.getCourseId());
				dy.setStudentId(peoples.get(j).getStudentId());
				dy.setCoursestudyType("2");
				dy.setCoursestudyAt(new Date());
				dy.setCoursestudyDel(1);//单独对学员推送的课程
				dy.setCoursestudyTranclassid(peoples.get(j).getTranclassId());
				//判断推送的课程是否报培训班课程是否相同
				List<Coursestudy> panduan = coursestudyService.selectPanDuanKey(dy);
				if (panduan.size()==0) {
			         int z=coursestudyService.insertSelective(dy);
							}
						}	
					}
				
				mark = 0;// 成功
			} else {
				mark = 1;// 请勿重复添加
			}
		} catch (Exception e) {
			return mark = 2;
		}
		return mark;
	}
	
	/**
	 * 添加课程查询   （后台）
	 * @return
	 */
	@RequestMapping(value = "/queryJoinCourse")
	public ModelAndView queryJoinCourse(Classinfo classinfos,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			classinfo = new Classinfo();
		}
		//--返回---
		Tranclass tranc = tranclassService.selectByPrimaryKey(classinfos.getTranclassId());
		//--------------------
		classinfo.setTranclassId(classinfos.getTranclassId());
//		classinfo.setType(classinfo.getType());
		//--------------------
		PageBean pageBean = new PageBean();
		int allRow = classinfoService.classInfoCount(classinfo);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		classinfo.setPage(length*(currentPage1-1));
		classinfo.setSize(length);

		List<Classinfo> list = classinfoService.classInfoPageAll(classinfo);
		
		

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
//		if (Clastudents.getType()!=1) {
//			pageBean.setUrl("queryStudentOne?mark=0&type=0&cj="+cj+"&tranclassId="+Clastudents.getTranclassId());//连接地址
//		} else {
//			pageBean.setUrl("queryStudentOne?mark=0&type=1&cj="+cj+"&tranclassId="+Clastudents.getTranclassId());//连接地址
//		}
		pageBean.setUrl("queryClassCourse?mark=0&type=1&cj="+cj+"&tranclassId="+classinfos.getTranclassId()+"&classlb="+classinfos.getClasslb());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("classinfos",classinfos);
		mv.addObject("tranc",tranc);
		mv.addObject("pageId",classinfos.getTranclassId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("trainingCourse/queryClassInfo");
		return  mv;

	}
	
	/**
	 * 查看学员详细
	 * 
	 * @param Tranclass
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/queryInformation")
	public ModelAndView queryInformation(String studentId) throws ParseException {
		ModelAndView mv = new ModelAndView();
			Student studentone = studentService.selectByOneKey(studentId);
			mv.addObject("maps",studentone);
			mv.setViewName("trainingCourse/queryStudentone");
			return  mv;
	}
	
	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/addEditShiftPage", method = RequestMethod.GET)
	public ModelAndView addEditShiftPage(String id,String pageShiftId,Tranclass tranclass) {
		ModelAndView mv = new ModelAndView();
		classresource = new Classresource();
		if(id==null){
			mv.addObject("cj",tranclass.getTranclassRank());
			mv.addObject("pageShiftId",pageShiftId);
			mv.addObject("pageLb",tranclass.getTranclassCategroy());
			mv.setViewName("trainingCourse/addShift");
		}else{
			tranclass.setTranclassId(id);
			Tranclass shiftList = tranclassService.selectByPrimaryKey(id);
			mv.addObject("shiftList",shiftList);
			mv.addObject("cj",tranclass.getTranclassRank());
			mv.setViewName("trainingCourse/editShift");
		}

		return mv;
	}
	
	/**
	 * 添加常规培训班
	 * 
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shiftSava" , method = RequestMethod.POST)
	public ModelAndView shiftSava(Tranclass tranclas) {
		ModelAndView mv = new ModelAndView();
		Classresource company = classresourceService.selectByPrimaryKey(tranclas.getClassresourceId());//单位名称和代码号
		//时间转换
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		tranclas.setTranclassId(RandomGUIDUtil.generateKey());
		if (tranclas.getTranclassCatalog()==null||tranclas.getTranclassCatalog()=="") {
			tranclas.setTranclassCategroy("3");//1常规班 2专题班 3党校班	
		} 
		tranclas.setTranclassNumber(0);//培训班人数
		tranclas.setTranclassCatalog(company.getClassresourceName());//所属目录
		tranclas.setTranclassAt(new Date());//添加时间
		tranclas.setTranclassDel(1);//删除标记
		tranclas.setTranclassType("0");//0未发布      1发布 
		tranclas.setTranclassApplyendtime(tranclas.getTranclassSetuptime());//报名结束时间（就是培训班开设时间）
//		tranclas.set
//		tranclas.set
//		tranclas.set
//		clas.setClassresourceAt(time.format(nowTime).toString());
		int i = tranclassService.insertSelective(tranclas);//执行添加
		if(i>0){
			mv.setViewName("redirect:queryShift?mark=1&classresourceId="+tranclas.getClassresourceId()+"&page=1&cj="+tranclas.getTranclassRank());
		}else{
			mv.addObject("pageShiftId",tranclas.getClassresourceId());
			mv.setViewName("trainingCourse/addClassCatalog");
		}
		return mv;
	}
	
	/**
	 * 修改培训班信息
	 * 
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shiftUpdate" , method = RequestMethod.POST)
	public ModelAndView shiftUpdate(Tranclass tranclas) {
		ModelAndView mv = new ModelAndView();
//		clas.setClassresourceName(clas.getClassresourceName());//修改名称
		tranclas.setTranclassApplyendtime(tranclas.getTranclassSetuptime());
		tranclas.setTranclassAt(new Date());//添加修改时间
		
		int i = tranclassService.updateByPrimaryKeySelective(tranclas);
		if(i>0){ 
			mv.setViewName("redirect:queryShift?mark=1&classresourceId="+tranclas.getClassresourceId()+"&page=1&cj="+tranclas.getTranclassRank());
		}else{
			mv.addObject("shiftList",tranclas);
			mv.addObject("cj",tranclas.getTranclassRank());
			mv.setViewName("trainingCourse/editShift");
		}
		return mv;
	}
	
	/**
	 * 删除培训班信息（后台）
	 * 
	 * @param tranclassId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteShift", method = RequestMethod.GET)
	public String deleteShift(String tranclassId) {
		Message msg = new Message();
		int sug = 0;
		Tranclass tranclas = new Tranclass();
		if (tranclassId!=null) {
			tranclas.setTranclassId(tranclassId);
			tranclas.setTranclassDel(0);
			 sug = tranclassService.updateByPrimaryKeySelective(tranclas);
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
	 * 发布培训班（后台）
	 * 
	 * @param Tranclass
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/publishShift", method = RequestMethod.POST)
	public String publishShift(Tranclass tranclas) throws ParseException {
		Classinfo classinfos=new Classinfo();
		classinfos.setTranclassId(tranclas.getTranclassId());
//		classinfo.setType(classinfo.getType());
		//--------------------
		int allRow = classinfoService.classInfoCount(classinfos);
		if (allRow==0) {
			return "2";
		}
		//时间转换
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 
		tranclas.setTranclassApplytime(time.format(nowTime).toString());//发布时间（报名开始时间）
		
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal.setTime(time.parse(tranclas.getTranclassApplytime()));
		cal2.setTime(time.parse(tranclas.getTranclassSetuptime()));
		
		//发布时间大于开设时间
		if (cal.after(cal2)||tranclas.getTranclassApplytime().equals(tranclas.getTranclassSetuptime())) {
			System.out.println("报名时间大（饭）" );
			return "1";
			} 
		
		tranclas.setTranclassApplyendtime(tranclas.getTranclassSetuptime());//报名截止时间（报名开设时间）
		tranclas.setTranclassType("1");//发布状态   1 发布       0 未发布
		try {
			int i = tranclassService.updateByPrimaryKeySelective(tranclas);
		} catch (Exception e) {
			return "";
		}
		return "ok";
	}
	
	/**
	 * 跳转培训班详情内容页面（预览培训班详情内容）
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value = "/shiftOnes", method = RequestMethod.GET)
	public ModelAndView shiftOnes(Tranclass tranclas, Model model) {
		Tranclass shift = tranclassService.selectByPrimaryKey(tranclas.getTranclassId());
		model.addAttribute("shift", shift);
		return new ModelAndView("trainingCourse/shiftOnes");
	}
	
	//------------------------------ 前台（培训班报名）---------------------//
	
	/**
	 * 
	 * 前台培训班详细课程
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/coursestudyWebAll")
	public ModelAndView coursestudyWebAll(int type,Coursestudy coursestudys ,int mark ,int page, Model model,HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		//分页显示-----------------------------------------------
		if(mark==1){
			coursestudy =new Coursestudy();
		}
		//---------------------------------------判断条件------------------------------------------
		if(coursestudys.getStudentId()!=null && coursestudys.getStudentId()!="" ){
			coursestudy.setStudentId(coursestudys.getStudentId());
		}
		if(coursestudys.getCoursestudyType()!=null && coursestudys.getCoursestudyType()!=""  ){
			coursestudy.setCoursestudyType(coursestudys.getCoursestudyType());
		}
		if(coursestudys.getTranclassId()!=null && coursestudys.getTranclassId()!=""  ){
			coursestudy.setTranclassId(coursestudys.getTranclassId());
		}
		
		PageBean pageBean = new PageBean();
		int allRow = coursestudyService.CoursestudyWebCount(coursestudy);
		final int length =20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		coursestudy.setPage(length*(currentPage1-1));
		coursestudy.setSize(length);

		List<Coursestudy> list = coursestudyService.selectPageWebAll(coursestudy);
		
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


		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("coursestudyWebAll?mark=0&coursestudyType=2&studentId="+coursestudy.getStudentId()+"&tranclassId="+coursestudy.getTranclassId());//连接地址
		pageBean.init();
		mv.addObject("coursestudys",coursestudys);
		mv.addObject("type",type);
		mv.addObject("pageBean",pageBean);
		mv.addObject("type",type);
		mv.setViewName("web/trainCourse");

		return  mv;
	}

	/**
	 * 根据培训班类别查询培训班
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/twoclass",method=RequestMethod.POST)
	public JSONArray incubatorType(Classresource classresource){
		
		List<Classresource> type = classresourceService.selectByAllKey(classresource);
		 JSONArray array = new JSONArray();  
	       try {  
	        for (Classresource p:type) { 
	  	       JSONObject member = null;   
	            member = new JSONObject();    
	            member.put("name", p.getClassresourceName());  
	            member.put("id", p.getClassresourceId()); 
	            array.add(member);
	        }  
	       } catch (JSONException e) {  
	        e.printStackTrace();  
	    }  
	      
	       return array;
		
	}
	
	
	/**
	 * 培训动态
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/allTranningCourse")
	public ModelAndView allTranningCourse(String organizationId,Tranclass tranclas,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			tranclass = new Tranclass();
		}
		if(cj==4){
			tranclass.setTypese(4);
		}else{
			tranclass.setTypese(1);
		}
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
		
		Classresource classresource =new Classresource();
		classresource.setOrganizationId(organizationId);
		List<Classresource> croues = classresourceService.selectByAllKey(classresource);
		tranclass.setOrganizationId(organizationId);
		tranclass.setQtId(tranclas.getQtId());
//		Classresource zzjd = classresourceService.selectByPrimaryKey(tranclas.getClassresourceId());
		tranclass.setClassresourceId(tranclas.getClassresourceId());
		Organization zhu = organizationService.selectByPrimaryKey(organizationId);
		mv.addObject("zhu",zhu);
		if(tranclas.getClassresourceUpname()!=null && tranclas.getClassresourceUpname()!="" ){
			tranclass.setClassresourceUpname(tranclas.getClassresourceUpname());
			Classresource kc = classresourceService.selectByPrimaryKey(tranclas.getClassresourceId());
			mv.addObject("kc",kc);
		}
		
		PageBean pageBean = new PageBean();
		int allRow = tranclassService.ShiftCount(tranclass);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		tranclass.setPage(length*(currentPage1-1));
		tranclass.setSize(length);

		List<Tranclass> list = tranclassService.selectShiftPageAll(tranclass);

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
//		pageBean.setUrl("allTranningCourse?mark=0&cj="+cj+"&classresourceId="+tranclas.getClassresourceId());//连接地址
		
		if (tranclas.getClassresourceId()!=null&&tranclas.getClassresourceId()!="") {
			pageBean.setUrl("allTranningCourse?mark=0&qtId=1&cj="+cj+"&classresourceId="+tranclas.getClassresourceId()+"&organizationId="+organizationId);//连接地址
		} else {
			pageBean.setUrl("allTranningCourse?mark=0&qtId=1&cj="+cj+"&organizationId="+organizationId);//连接地址
		}
		
		pageBean.init();
		mv.addObject("cj",cj);
		
		mv.addObject("tranclas",tranclas);
		mv.addObject("pageId",tranclas.getClassresourceId());
		mv.addObject("pageBean",pageBean);
		mv.addObject("croues",croues);
		mv.addObject("organizationId",organizationId);
//		mv.addObject("zzjg",zzjd);
		mv.setViewName("webTrainingCourse/trainingCourseList");
		return  mv;

	}
	
	/**
	 * 培训班详情
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/oneTranningCourse", method = RequestMethod.GET)
	public ModelAndView oneTranningCourse(Model model, String tranclassId) {
		Tranclass tranList = tranclassService.selectByPrimaryKey(tranclassId);
		model.addAttribute("tranList", tranList);
		return new ModelAndView("webTrainingCourse/oneTranningCourse");
	}
	/**
	 * 党校页  培训班详情
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/oneTranningCourseDx", method = RequestMethod.GET)
	public ModelAndView oneTranningCourseDx(Model model, String tranclassId) {
		Tranclass tranList = tranclassService.selectByPrimaryKey(tranclassId);
		model.addAttribute("tranList", tranList);
		return new ModelAndView("webTrainingCourse/oneTranningCourseDx");
	}
	
	/**
	 * 培训班报名
	 * 
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/insertStudentInfo", method = RequestMethod.POST)
	public int insertStudentInfo(Model model, Clastudent si,
			HttpSession session) {
		int mark = 0;
		Student student = (Student)session.getAttribute("Student");
		if (student == null) {
			mark = 1;// 未登录
		} else {
			try {
				si.setStudentId(student.getStudentId());
				si.setClastudentName(student.getStudentName());
				si.setClastudentId(RandomGUIDUtil.generateKey());// 主键
				si.setClastudentType(0);// 状态
				si.setClastudentPassword("123456");// 密码
				si.setClastudentAt(new Date());// 时间
				si.setClastudentDel(1);// 删除标记
				List<Clastudent> siList = clastudentService
						.selectByCourseId(si);
				if (siList.size() == 0) {
					clastudentService.insertSelective(si);
					mark = 0;// 成功
				} else {
					mark = 2;// 请勿重复报名
				}
			} catch (Exception e) {
				e.printStackTrace();
				mark = 3;// 报名失败
			}
		}
		return mark;
	}
	//------------------------------------------------------------------//
	 
	/**
	 * 登录前主页
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/mainout1", method = RequestMethod.GET)
	public ModelAndView mainout1(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 培训班
		List<Tranclass> TranclassList = tranclassService.selectByNum(10);
		List<Tranclass> TranclassList1 = new ArrayList<Tranclass>();
		List<Tranclass> TranclassList2 = new ArrayList<Tranclass>();
		if(TranclassList.size() != 0){
			for (int i = 0; i < TranclassList.size(); i++) {
				TranclassList.get(i).setTranclassAtTemp(sdf.format(TranclassList.get(i).getTranclassAt()));
				if (i < 5) {
					TranclassList1.add(TranclassList.get(i));
				} else {
					TranclassList2.add(TranclassList.get(i));
				}
			} 
		}
		List<News> newsList = newsService.selectNewsByNUmber(3);
		List<Programme> planList = programmeService.selectProgrammeByNumber(5);
		for(Programme p : planList){
			p.setProgrammeAtTemp(sdf.format(p.getProgrammeAt()));
		}
		List<Notice> noticeList = noticeService.selectNoticeByNumber(5);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("planList", planList);
		model.addAttribute("newsList", newsList);
		model.addAttribute("tcList1", TranclassList1);
		model.addAttribute("tcList2", TranclassList2);
		return new ModelAndView("main/mainout");
	}
	
	/**
	 * 登录后的主页
	 * @return
	 */
	@RequestMapping(value = "/main1", method = RequestMethod.GET)
	public ModelAndView main1(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 培训班
		List<Tranclass> TranclassList = tranclassService.selectByNum(10);
		List<Tranclass> TranclassList1 = new ArrayList<Tranclass>();
		List<Tranclass> TranclassList2 = new ArrayList<Tranclass>();
		if(TranclassList.size() != 0){
			for (int i = 0; i < TranclassList.size(); i++) {
				TranclassList.get(i).setTranclassAtTemp(sdf.format(TranclassList.get(i).getTranclassAt()));
				if (i < 5) {
					TranclassList1.add(TranclassList.get(i));
				} else {
					TranclassList2.add(TranclassList.get(i));
				}
			}
		}
		List<News> newsList = newsService.selectNewsByNUmber(3);
		List<Programme> planList = programmeService.selectProgrammeByNumber(5);
		List<Notice> noticeList = noticeService.selectNoticeByNumber(5);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("planList", planList);
		model.addAttribute("newsList", newsList);
		model.addAttribute("tcList1", TranclassList1);
		model.addAttribute("tcList2", TranclassList2);
		return new ModelAndView("main/main");
	}
	
	
}
