package com.ttgis.education.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Estimate;
import com.ttgis.education.entity.Syllabus;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.CourseService;
import com.ttgis.education.service.EstimateService;
import com.ttgis.education.service.SyllabusService;

/**
 * 评价管理
 * <p>
 * Title:CourseController
 * </p>
 * 
 * @author 李慕阳 2015-8-12
 */

@Controller
public class EstimateController {
	
	@Resource
	private SyllabusService syllabusService;
	@Resource
	private CourseService courseService;
	@Resource
	private EstimateService estimateService;
	
	private Course ncourse;
	/**
	 * 李慕阳	课程评价
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/Estimate")
	public ModelAndView Estimate(Course course ,int mark ,int page, Model model,HttpSession session)
	{
		int ch = 0;
		if(mark==1){
			ncourse =new Course();
		}
		Account acc = (Account) session.getAttribute("sessionUser");
		Syllabus s  = syllabusService.selectByPrimaryKey(course.getSyllabusId());
		//-------------------------------------------判断权限前台是否可操作----------------------------------------
		if(s.getSyllabusRank()==0){
			if(acc.getAccountType()==1){
				if(s.getSyllabusId().equals("1AD34C2B-0A29-5325-1613-3082518F4568")||s.getSyllabusId().equals("BAD34C2B-0A29-5325-1613-3082518F4568")||s.getSyllabusId().equals("1AD34C2B-0A29-5325-1613-3082518F4567")){
					
					ch=1;
				}
			}else{
				if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
					ch=1;
				}
			}
		}else{
			s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
			if(s.getSyllabusRank()==0){
				if(acc.getAccountType()==1){
					if(s.getSyllabusId().equals("1AD34C2B-0A29-5325-1613-3082518F4568")||s.getSyllabusId().equals("BAD34C2B-0A29-5325-1613-3082518F4568")||s.getSyllabusId().equals("1AD34C2B-0A29-5325-1613-3082518F4567")){
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
						ch=1;
					}
				}
			}else{
				s  = syllabusService.selectByPrimaryKey(s.getSyllabusUpperId());
				if(acc.getAccountType()==1){
					if(s.getSyllabusId().equals("1AD34C2B-0A29-5325-1613-3082518F4568")||s.getSyllabusId().equals("BAD34C2B-0A29-5325-1613-3082518F4568")||s.getSyllabusId().equals("1AD34C2B-0A29-5325-1613-3082518F4567")){
						ch=1;
					}
				}else{
					if(s.getSyllabusId().equals("2AD34C2B-0A29-5325-1613-3082518F4568")){
						ch=1;
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

		List<Course> list = courseService.selectPageAllEstimate(ncourse);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("Estimate?mark=0&syllabusId="+ncourse.getSyllabusId());//连接地址
		pageBean.init();
		mv.addObject("course",ncourse);
		mv.addObject("ch",ch);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("backEstimate/estimate");

		return  mv;
	}
	
	/**
	 * 李慕阳	课程评价学员
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/EstimateStudent")
	public ModelAndView EstimateStudent(Course course ,int mark ,int page, Model model,HttpSession session)
	{
		int ch = 0;
		if(mark==1){
			ncourse =new Course();
		}
		
		//---------------------------------------判断条件------------------------------------------
		if(course.getCourseCatalog()!=null && course.getCourseCatalog()!="" ){
			ncourse.setCourseCatalog(course.getCourseCatalog());
		}
		if(course.getCoursePubman()!=null && course.getCoursePubman()!="" ){
			ncourse.setCoursePubman(course.getCoursePubman());
		}
		ncourse.setSyllabusId(course.getSyllabusId());
		ncourse.setCourseId(course.getCourseId());
		ModelAndView mv = new ModelAndView();
		PageBean pageBean = new PageBean();
		int allRow = estimateService.estimateCount(ncourse);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		ncourse.setPage(length*(currentPage1-1));
		ncourse.setSize(length);

		List<Estimate> list = estimateService.estimatepage(ncourse);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("EstimateStudent?mark=0&syllabusId="+ncourse.getSyllabusId());//连接地址
		pageBean.init();
		mv.addObject("course",ncourse);
		mv.addObject("ch",ch);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("backEstimate/estimateStudent");

		return  mv;
	}
}
