package com.ttgis.education.controller;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Coursenote;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.CourseService;
import com.ttgis.education.service.CoursenoteService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.Tools;
/**
 * 笔记管理（树、新闻）
 * <p>TeacherController </p>
 *@author 胡炎龙
 *
 */

@Controller
public class CourseNoteController {


	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private CoursenoteService coursenoteService;
	@Resource
	private StudentService studentService;
	@Resource
	private CourseService courseService;
	
	
	private Coursenote coursenote;
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(CourseNoteController.class);



	/**
	 * 组织架构树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/homeworkTree", method = RequestMethod.GET)
	public ModelAndView homeworkTree(Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		Organization oneid = organizationService.selectByPrimaryKey(account.getAccountCatalog());
		List<Organization> organization = organizationService.selectAllOrganization();
		model.addAttribute("organization", organization);
		model.addAttribute("account", account);
		model.addAttribute("oneid", oneid);
		
		return new ModelAndView("tree/homeworkTree");
	}
	
	
	/**
	 * 笔记列表（后台）
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/queryCoursenoteList")
	public ModelAndView queryCoursenoteList(Coursenote c,int mark ,Integer cj,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			coursenote =new Coursenote();
		}
		
		Account acc = (Account)session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){//集团账号
			coursenote.setStudentCompanyId(c.getOrganizationId());
			coursenote.setOrganizationId(c.getOrganizationId());
			coursenote.setType(acc.getAccountType());
		}
		if(acc.getAccountType()==2){//院一级账号
			if(cj==1){//判断前台是否点击第1层
				coursenote.setStudentCompanyId(c.getOrganizationId());
			}else{
				coursenote.setStudentCompanyId(c.getOrganizationId());
			}
			coursenote.setType(acc.getAccountType());

		}

		if(acc.getAccountType()==3){//院二级账号
			coursenote.setStudentCompanyId(c.getOrganizationId());
			coursenote.setType(acc.getAccountType());
		}

		coursenote.setCj(cj);


		//---------------------判断删除标记------------------------------
		int chmark =0;
		if(c.getOrganizationId().equals(acc.getAccountCatalog())){
			chmark = 1;
		}

		//---------------------条件查询----------------------------------
		
		if(c.getStudentId()!=null && c.getStudentId()!="" ){
			coursenote.setStudentId(c.getStudentId());
		}
		if(c.getCourseId()!=null && c.getCourseId()!="" ){
			coursenote.setCourseId(c.getCourseId());
		}
		PageBean pageBean = new PageBean();
		int allRow = coursenoteService.coursenoteCount(coursenote);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		coursenote.setPage(length*(currentPage1-1));
		coursenote.setSize(length);

		List<Coursenote> list = coursenoteService.selectPageAll(coursenote);
		List<Student> studentList = studentService.queryBystudentCompanyId(c.getOrganizationId());
		List<Course> courseList = courseService.queryNameList();
		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryCoursenoteList?mark=0&cj="+cj+"&organizationId="+c.getOrganizationId());//连接地址
		pageBean.init();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list!=null&&list.size()>0){
			for(Coursenote cn:list){
				if(cn.getCoursenoteContent()!=null&&!"".equals(cn.getCoursenoteContent()!=null)){
					String str=cn.getCoursenoteContent();
						if(str.length()>20){
							
							cn.setCoursenoteContent(str.substring(0,20)+"...");
							
					}
				}
				cn.setCoursenoteAt(sdf.format(sdf.parse(cn.getCoursenoteAt())));
			}
		}
		mv.addObject("chmark",chmark);
		mv.addObject("cj",cj);
		mv.addObject("coursenote",coursenote);
		
		mv.addObject("pageBean",pageBean);
		mv.addObject("studentList",studentList);
		mv.addObject("courseList",courseList);
		mv.setViewName("coursenotes/queryCoursenoteList");
		return  mv;
	}
	/**
	 * 删除信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteCoursenote", method = RequestMethod.GET)
	public String deleteCoursenote(String coursenoteId) {
		Message msg = new Message();
		int sug = 0;
		if (coursenoteId!=null) {
			sug = coursenoteService.deleteByPrimaryKey(coursenoteId);
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
	 * 查看笔记详情（后台）
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value = "/queryCoursenoteById", method = RequestMethod.GET)
	public ModelAndView queryCoursenoteById(Coursenote coursenote, Model model) {
		Coursenote coursenoteByid = coursenoteService.selectByPrimaryKey(coursenote.getCoursenoteId());
		model.addAttribute("coursenoteByid", coursenoteByid);
		return new ModelAndView("coursenotes/queryCoursenoteByid");
	}
	/**
	 * 我的笔记(前台)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/myCourseNoteList", method = RequestMethod.GET)
	public ModelAndView myCourseNoteList(String organizationId,int page ,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		
		Coursenote cn  =new Coursenote();
		
		cn.setStudentId(student.getStudentId());
		cn.setCourseId(organizationId);
		PageBean pageBean = new PageBean();
		int allRow = coursenoteService.coursenoteCount(cn);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理
		cn.setPage(length*(currentPage1-1));
		cn.setSize(length);
		List<Coursenote> list = coursenoteService.selectPageAll(cn);
		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("myCourseNoteList?organizationId="+organizationId);//连接地址
		pageBean.init();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(list!=null&&list.size()>0){
			for(Coursenote c:list){
				if(c.getCoursenoteContent()!=null&&!"".equals(c.getCoursenoteContent()!=null)){
					String str=c.getCoursenoteContent();
						if(str.length()>20){
							
							c.setCoursenoteContent(str.substring(0,20)+"...");
							
					}
				}
				c.setCoursenoteAt(sdf.format(sdf.parse(c.getCoursenoteAt())));
			}
		}
		
		mv.addObject("cn",cn);
		mv.addObject("organizationId",cn.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("coursenotes/myCourseNoteList");
		return  mv;
	
	}
	/**
	 * 查看笔记详情（前台）
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value = "/myCourseNoteById", method = RequestMethod.GET)
	public ModelAndView myCourseNoteById(Coursenote coursenote, Model model) {
		Coursenote coursenoteByid = coursenoteService.selectByPrimaryKey(coursenote.getCoursenoteId());
		model.addAttribute("coursenoteByid", coursenoteByid);
		return new ModelAndView("coursenotes/myCourseNoteById");
	}


	/**
	 * 笔记保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/coursenoteSava", method = RequestMethod.POST)
	public ModelAndView coursenoteSava(Coursenote coursenote,String coursestudyId,HttpSession session,String courseId, HttpServletResponse response,HttpServletRequest request) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		coursenote.setCoursenoteId(Tools.getGUID());
		coursenote.setCoursenoteAt(sdf.format(new Date()));
		coursenoteService.insertSelective(coursenote);

		Student student = (Student)session.getAttribute("Student");
		Coursenote cn  =new Coursenote();
		cn.setCourseId(courseId);
		cn.setStudentId(student.getStudentId());
		List<Coursenote> coursenoteList=coursenoteService.selectAllList(cn);
		
		JSONArray json = JSONArray.fromObject(coursenoteList);
		String js = json.toString();
		System.out.println(js);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
		
	}
	
}
