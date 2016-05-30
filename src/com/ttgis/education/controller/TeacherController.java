package com.ttgis.education.controller;

import java.io.UnsupportedEncodingException;
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
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Teacher;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.TeacherService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.Tools;
/**
 * 师资管理
 * <p>TeacherController </p>
 *@author 胡炎龙
 *
 */

@Controller
public class TeacherController {


	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private TeacherService teacherService;
	
	private Teacher teacher;


	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(TeacherController.class);



	/**
	 * 组织架构树（后台）
	 * @param n
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/teacherTree", method = RequestMethod.GET)
	public ModelAndView teacherTree(Model model,HttpSession session) {
		Account account = (Account) session.getAttribute(Const.SESSION_USER);
		Organization oneid = organizationService.selectByPrimaryKey(account.getAccountCatalog());
		List<Organization> organization = organizationService.selectAllOrganization();
		model.addAttribute("organization", organization);
		model.addAttribute("account", account);
		model.addAttribute("oneid", oneid);
		return new ModelAndView("tree/teacherTree");
	}
	
	
	/**
	 * 师资列表（后台）
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/queryTeacherList")
	public ModelAndView queryTeacherList(Teacher t,int mark ,Integer cj,int page,HttpSession session) throws UnsupportedEncodingException{
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			teacher =new Teacher();
		}
		

		teacher.setCj(cj);
		teacher.setOrganizationId(t.getOrganizationId());



		//---------------------条件查询----------------------------------
		
		if(t.getTeacherName()!=null && t.getTeacherName()!="" ){
			teacher.setTeacherName(t.getTeacherName());
		}
		PageBean pageBean = new PageBean();
		int allRow = teacherService.teacherCount(teacher);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		teacher.setPage(length*(currentPage1-1));
		teacher.setSize(length);

		List<Teacher> list = teacherService.selectPageAll(teacher);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryTeacherList?mark=0&cj="+cj+"&organizationId="+t.getTeacherCatalog());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("teacher",teacher);
		mv.addObject("organizationId",t.getOrganizationId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("teachers/queryTeacherList");
		return  mv;
	}

	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/teacherJump")
	public ModelAndView teacherJump(String teacherId,Integer cj,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
			if(teacherId.equals("0")){
				mv.addObject("cj",cj);
				mv.setViewName("teachers/addTeacher");
			}else{
				teacher = teacherService.selectByPrimaryKey(teacherId);
				mv.addObject("teacher",teacher);
				mv.addObject("cj",cj);
				mv.addObject("mark",request.getParameter("mark"));
				mv.setViewName("teachers/updateTeacherMessage");
			}
		
		return mv;
	}
	
	/**
	 * 师资保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/teacherSava", method = RequestMethod.POST)
	public ModelAndView teacherSava(Teacher teacher,Integer cj,HttpSession session) throws Exception{
		Account acc = (Account)session.getAttribute("sessionUser");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		int sug = 0;
		teacher.setTeacherCatalog(acc.getAccountCatalog());
		teacher.setTeacherId(Tools.getGUID());  
		teacher.setTeacherAt(sdf.parse(sdf.format(new Date())));
		teacher.setTeacherRank(cj);
		sug=teacherService.insertSelective(teacher);

		if(sug==1){
			Organization o = organizationService.selectByPrimaryKey(acc.getAccountCatalog());
			return new ModelAndView("redirect:/queryTeacherList?mark=1&cj="+o.getOrganizationFwqx()+"&organizationId="+teacher.getTeacherCatalog()+"&page=1");
		} else {
			return new ModelAndView("");
		}
	}

	/**
	 * 修改保存 （后台）
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/teacherUpdateSave", method = RequestMethod.POST)
	public ModelAndView teacherUpdateSave(Teacher teacher,Integer cj) {
		int sug = 0;
		sug=teacherService.updateByPrimaryKeySelective(teacher);

		if(sug==1){
			return new ModelAndView("redirect:/queryTeacherList?mark=1&cj="+cj+"&organizationId="+teacher.getTeacherCatalog()+"&page=1");
		} else {
			return new ModelAndView("");
		}
	}
	/**
	 * 删除信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteTeacher", method = RequestMethod.GET)
	public String deleteTeacher(String teacherId) {
		Message msg = new Message();
		int sug = 0;
		if (teacherId!=null) {
			sug = teacherService.deleteByPrimaryKey(teacherId);
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
	 * 查看教师详情
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value = "/queryTeacherById", method = RequestMethod.GET)
	public ModelAndView queryTeacherById(Teacher teacher, Model model) {
		Teacher teacherById = teacherService.selectByPrimaryKey(teacher.getTeacherId());
		model.addAttribute("teacherById", teacherById);
		return new ModelAndView("teachers/queryTeacherById");
	}
	

	

}
