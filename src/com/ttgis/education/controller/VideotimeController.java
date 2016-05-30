package com.ttgis.education.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Videotime;
import com.ttgis.education.service.VideotimeService;
import com.ttgis.education.utils.Tools;

/**
 * 视频计算
 * @author 陈健龙
 *
 */
@Controller
public class VideotimeController {
	
	@Resource
	private VideotimeService videotimeService;
	
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(VideotimeController.class);
	
	
	/**
	 * 视频播放刷新累计
	 * @param response
	 * @param request
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/videotimeWebAxjx")
	public ModelAndView videotimeWebAxjx(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException{
		ModelAndView mv = new ModelAndView();
		JSONObject obj = new JSONObject();  
		Videotime v = new Videotime();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		//时间转换
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		
		v.setVideotimeId(Tools.getGUID());
		v.setStudentId(student.getStudentId());
		v.setCourseresourceId(request.getParameter("cid"));
		v.setVideotimeAt(time.format(nowTime).toString());
		v.setVideotimeMark(request.getParameter("endtime"));
		v.setVideotimeDel(1);
		int i  = videotimeService.insertSelective(v);
		System.out.println(i+"--------------------------------------------------------------------");
		if(i>0){
			obj.put("key", 1);
		}else{
			obj.put("key", 0);
		}
		System.out.println(obj);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(obj);
		return null;
	}

}
