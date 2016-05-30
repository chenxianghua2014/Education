package com.ttgis.education.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Courseresource;
import com.ttgis.education.entity.Coursestudy;
import com.ttgis.education.entity.Department;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Record;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Test;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.Videotime;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.AccountService;
import com.ttgis.education.service.ClassinfoService;
import com.ttgis.education.service.ClassresourceService;
import com.ttgis.education.service.ClastudentService;
import com.ttgis.education.service.CourseService;
import com.ttgis.education.service.CourseresourceService;
import com.ttgis.education.service.CoursestudyService;
import com.ttgis.education.service.DepartmentService;
import com.ttgis.education.service.NewsService;
import com.ttgis.education.service.NoticeService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.ProgrammeService;
import com.ttgis.education.service.RecordService;
import com.ttgis.education.service.ShiftService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.service.TestService;
import com.ttgis.education.service.TranclassService;
import com.ttgis.education.service.VideotimeService;
import com.ttgis.education.utils.Const;
/**
 * 培训报告    上传记录
 * @author 王艳鹏
 *
 */
@Controller
public class trainingReportController {
	
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
	private DepartmentService departmentService;
	@Resource
	private RecordService recordService;
	@Resource
	private CourseresourceService courseresourceService;
	@Resource
	private VideotimeService videotimeService;
	
	@Resource
	private TestService testService;
	
/*	private Classresource classresource;
	private Tranclass tranclass;
	private Clastudent clastudent;
	private Course course;
	private Classinfo classinfo;*/
	private Coursestudy coursestudy;
	
	private Student student;
	private Department department;
	private Record record;
	
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(trainingReportController.class);
	
	
	
	/**
	 * 组织架构树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/trainingReportTree", method = RequestMethod.GET)
	public ModelAndView trainingReportTree(Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		Organization oneid = organizationService.selectByPrimaryKey(account.getAccountCatalog());
		List<Organization> organization = organizationService.selectAllOrganization();
  		model.addAttribute("organization", organization);
  		model.addAttribute("account", account);
  		model.addAttribute("oneid", oneid);
		model.addAttribute("type", "培训报告");
		return new ModelAndView("tree/trainingReportTree");
	}
	
	/**
	 * 查询学员（后台）
	 * @return
	 */
	@RequestMapping(value = "/studentHistory")
	public ModelAndView studentHistory(Student s,int mark ,int cj,int page,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			 student = new Student();
			 department=new Department();
		}
		//课程推送    
//		student.setCourseId(s.getCourseId());
		//查询部门------------------------
		department.setOrganizationId(s.getOrganizationId());
		department.setEpartmentRank("4");
//		List<Department> bm = departmentService.selectbmlist(department);
		//--------------判断1级单位和二三级单位-----------------
		Account acc = (Account) session.getAttribute("sessionUser");
//		if (s.getOrganizationId()!=null&&s.getOrganizationId()!="") {
//			if (s.getOrganizationId().equals("test001")) {
//				student.setYs("1");
//			}else{
//				student.setYs("0");
//			}
//		}else{
//			student.setYs("1");
//		}
		List<Department> bm = departmentService.selectbmlist(department);
	
		
		//---------------------条件查询--------------（学员）--------------------
		 //student=s;
		//Student  表
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
		 //人员类别
		 if(s.getStudentCategory()!=null && s.getStudentCategory()!="" ){
			 student.setStudentCategory(s.getStudentCategory());
			}
		 
		 //返回
		 if(s.getStudentCompanyid()!=null && s.getStudentCompanyid()!="" ){
			 student.setStudentCompanyid(s.getStudentCompanyid());
			}
		 //返回
		 if(s.getOrganizationId()!=null && s.getOrganizationId()!="" ){
			 student.setOrganizationId(s.getOrganizationId());
			}
		
		
		//-------------------------------
		if (s.getDepartmentId()==null) {
			
		} else {
			student.setDepartmentId(s.getDepartmentId());
		}
		//------------------------------------------------
		student.setStudentCompanyid(s.getOrganizationId());
		//判断查询部门和子部门的区别
		if (s.getStudentDepartments()!=null&&!s.getStudentDepartments().equals("")) {
			student.setStudentDepartment(s.getStudentDepartments());
		} else {
			student.setStudentDepartment(s.getStudentDepartment());
		}
		PageBean pageBean = new PageBean();
		int allRow = studentService.StudentHistoryCount(student);
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

		List<Student> list = studentService.selectStudentHistory(student);

		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("studentHistory?mark=0&cj="+cj+"&departmentId="+s.getStudentCompanyid()+"&organizationId="+s.getOrganizationId());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("student",student);
		mv.addObject("pageId",s.getStudentCompanyid());
		mv.addObject("pageBean",pageBean);
		//部门
		mv.addObject("bm",bm);
		mv.setViewName("trainingReport/queryStudent");
		return  mv;
	}
	
	
	/**
	 * 培训报告      学员课程（后台）
	 * @return
	 */
	@RequestMapping(value = "/studentHistoryClass")
	public ModelAndView studentHistoryClass(Coursestudy cs,int page ,int mark,HttpSession session) {
		ModelAndView mv = new ModelAndView();
	/*	Student students = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}*/
		if(mark==1){
			coursestudy = new Coursestudy();
		}
//		coursestudy.setCoursestudyType(cs.getCoursestudyType());
		coursestudy.setStudentId(cs.getStudentId());
		System.out.println(cs.getQb());
			 coursestudy.setQb(cs.getQb());
		 //课程名称
		 if(cs.getCourseName()!=null && cs.getCourseName()!="" ){
			 coursestudy.setCourseName(cs.getCourseName());
			}
		 //是否是推送课程
		 if(cs.getCoursestudyType()!=null && cs.getCoursestudyType()!="" ){
			 coursestudy.setCoursestudyType(cs.getCoursestudyType());
			}
		 
		//返回 
		 if(cs.getOrganizationId()!=null && cs.getOrganizationId()!="" ){
			 coursestudy.setOrganizationId(cs.getOrganizationId());
			}
		 if(cs.getCj()!=null && cs.getCj()!=0 ){
			 coursestudy.setCj(cs.getCj());
			}
		 
		 
		 
		 
		PageBean pageBean = new PageBean();
		int allRow = coursestudyService.TrainingReportTop(coursestudy);
		final int length = 15;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		coursestudy.setPage(length*(currentPage1-1));
		coursestudy.setSize(length);
		List<Coursestudy> list = coursestudyService.TrainingReportTopAll(coursestudy);
		
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
						videotime.setStudentId(cs.getStudentId());
//						videotime.setVideotimeMark("1");
//						int shu = videotimeService.videtimeByMark(videotime);
//						if (shu>=1) {
//						time=time+1;
//						}
						List<Videotime> shuList = videotimeService.videtimeByMarkList(videotime);
						if (shuList.size()!=0) {
							if (shuList.size()>Math.floor(Integer.parseInt(shuList.get(0).getVideotimeMark())/60)) {
								time=time+1;	
							}
						}
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
					t.setStudentId(cs.getStudentId());
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
		pageBean.setUrl("studentHistoryClass?mark=0&studentId="+cs.getStudentId());//连接地址
		pageBean.init();

		
		mv.addObject("coursestudy",coursestudy);
		mv.addObject("name",cs.getCoursestudyType());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("trainingReport/queryTrainingRepot");
		return mv;
	}
	

	/**
	 * 上传记录      上传管理（后台）
	 * @return
	 */
	@RequestMapping(value = "/uploadReports")
	public ModelAndView uploadReports(Record cs,int page ,int mark,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		if(mark==1){
			record = new Record();
		}
//		coursestudy.setCoursestudyType(cs.getCoursestudyType());
		if (!account.getAccountCatalog().equals("test001")) {
			record.setAccountCatalog(account.getAccountCatalog());
		} 
		
		 //内容
		 if(cs.getRecordNote()!=null && cs.getRecordNote()!="" ){
			 record.setRecordNote(cs.getRecordNote());
			}
		 //状态
		 if(cs.getRecordType()!=null && cs.getRecordType()!="" ){
			 record.setRecordType(cs.getRecordType());
			}
		 

		PageBean pageBean = new PageBean();
		int allRow = recordService.uploadReportsTop(record);
		final int length =15;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		record.setPage(length*(currentPage1-1));
		record.setSize(length);
		List<Record> list = recordService.uploadReportsTopAll(record);
		
		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("uploadReports?mark=0");//连接地址
		pageBean.init();

		
		mv.addObject("record",record);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("trainingReport/queryUploadReport");
		return mv;
	}
	
	
	
}
