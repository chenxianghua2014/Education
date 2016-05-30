package com.ttgis.education.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Classinfo;
import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.Clastudent;
import com.ttgis.education.entity.Homework;
import com.ttgis.education.entity.Msg;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.ClassinfoService;
import com.ttgis.education.service.ClassresourceService;
import com.ttgis.education.service.ClastudentService;
import com.ttgis.education.service.HomeworkService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.service.TranclassService;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.Tools;


import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.net.URLEncoder;  

import org.apache.tools.zip.ZipEntry;  
import org.apache.tools.zip.ZipOutputStream;  

/**
 * 党校作业（树、新闻）
 * <p>HomeWorkController </p>
 *@author 胡炎龙
 *
 */

@Controller
public class HomeWorkController {

	@Resource
	private HomeworkService homeworkService;
	@Resource
	private ClassresourceService classresourceService;
	@Resource
	private TranclassService tranclassService;
	@Resource
	private ClastudentService clastudentService;
	@Resource
	private ClassinfoService classinfoService;
	@Resource
	private StudentService studentService;

	private Homework homeWork;
	private Tranclass tranclass;
	private Clastudent clastudent;
	private Classinfo classinfo;
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(HomeWorkController.class);
	//---------------------
	//==========================党校培训班树============================//
	/**
	 * 取得课程管理目录树和账号类型 课程管理目录树
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/operationManagementTree")
	public Msg operationManagementTree(HttpSession session)
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



	//==========================党校培训班树============================//
	/**
	 * 查询培训班   （后台）
	 * @return
	 */
	@RequestMapping(value = "/queryClass")
	public ModelAndView queryClass(Tranclass tranclas,int mark ,int cj,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			tranclass = new Tranclass();
		}
		if(cj==4){
			tranclass.setTypese(4);
		}else{
			tranclass.setTypese(1);
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

		clastudent = new Clastudent();

		for(int i = 0; i < list.size(); i++)  
		{  
			clastudent.setType(1);
			clastudent.setTranclassId(list.get(i).getTranclassId());
			int allRows = clastudentService.classStudentCount(clastudent); 
			Tranclass trans = new Tranclass();
			trans.setTranclassNumber(allRows);
			trans.setTranclassId(list.get(i).getTranclassId());
			int j=tranclassService.updateByPrimaryKeySelective(trans);
			System.out.println(j);

			//System.out.println(list.get(i));  
		} 


		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryClass?mark=0&cj="+cj+"&classresourceId="+tranclas.getClassresourceId());//连接地址
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("tranclas",tranclas);
		mv.addObject("pageId",tranclas.getClassresourceId());
		mv.addObject("pageBean",pageBean);
		mv.addObject("zzjg",zzjd);
		mv.setViewName("homeWork/queryClass");
		return  mv;

	}
	/**
	 * 添加课程查询   （后台）
	 * @return
	 */
	@RequestMapping(value = "/queryClassInformation")
	public ModelAndView queryClassInformation(Classinfo classinfos,int mark ,int cj,int page,HttpSession session){
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
		mv.setViewName("homeWork/queryClassInformation");
		return  mv;

	}
	/**
	 * 查看报名学员   （后台）
	 * @return
	 */
	@RequestMapping(value = "/queryStudentByCourse")
	public ModelAndView queryStudentByCourse(Clastudent Clastudents,int mark ,int cj,int page,HttpSession session){
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
			pageBean.setUrl("queryStudentByCourse?mark=0&type=0&cj="+cj+"&tranclassId="+Clastudents.getTranclassId()+"&classlb="+Clastudents.getClasslb());//连接地址
		} else {
			pageBean.setUrl("queryStudentByCourse?mark=0&type=1&cj="+cj+"&tranclassId="+Clastudents.getTranclassId()+"&classlb="+Clastudents.getClasslb());//连接地址
		}
		pageBean.init();
		mv.addObject("cj",cj);
		mv.addObject("Clastudents",Clastudents);
		mv.addObject("tranc",tranc);
		mv.addObject("pageId",Clastudents.getTranclassId());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("homeWork/queryStudentByCourse");
		return  mv;

	}
	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/homeWorkJump")
	public ModelAndView teacherJump(String courseId,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("courseId",courseId);
		mv.setViewName("homeWork/homeWorkUpload");
		return mv;
	}
	/**
	 * 跳转培训班详情内容页面（预览培训班详情内容）
	 * 
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value = "/classOnes", method = RequestMethod.GET)
	public ModelAndView shiftOnes(Tranclass tranclas, Model model) {
		Tranclass shift = tranclassService.selectByPrimaryKey(tranclas.getTranclassId());
		model.addAttribute("shift", shift);
		return new ModelAndView("homeWork/classOnes");
	}
	/**
	 * 查看学员详细
	 * 
	 * @param Tranclass
	 * @return
	 * @throws ParseException 
	 */
	@ResponseBody
	@RequestMapping(value = "/queryHomework")
	public ModelAndView queryHomework(String studentId) throws ParseException {
		ModelAndView mv = new ModelAndView();
		Student studentone = studentService.selectByOneKey(studentId);
		mv.addObject("maps",studentone);
		mv.setViewName("homeWork/queryStudentInformation");
		return  mv;
	}
	/**
	 * 作业列表（后台）
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/queryHomeworkBycourseId")
	public ModelAndView queryHomeworkBycourseId(Homework h,int mark,String courseId ,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			homeWork =new Homework();
		}

		Account acc = (Account)session.getAttribute("sessionUser");

		homeWork.setCourseId(courseId);
		//---------------------条件查询----------------------------------

		/*if(c.getStudentId()!=null && c.getStudentId()!="" ){
			coursenote.setStudentId(c.getStudentId());
		}
		if(c.getCourseId()!=null && c.getCourseId()!="" ){
			coursenote.setCourseId(c.getCourseId());
		}*/
		PageBean pageBean = new PageBean();
		int allRow = homeworkService.homeworkCount(homeWork);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		homeWork.setPage(length*(currentPage1-1));
		homeWork.setSize(length);

		List<Homework> list = homeworkService.selectPageAll(homeWork);
		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryHomeworkBycourseId?mark=0");//连接地址
		pageBean.init();


		mv.addObject("homeWork",homeWork);
		mv.addObject("organizationId",acc.getAccountCatalog());
		mv.addObject("pageBean",pageBean);

		mv.setViewName("homeWork/queryHomework");
		return  mv;
	}

	//-------------------------------------
	
	//-------------------
	/**
	 * 胡炎龙
	 * 批量下载作业
	 * @param 
	 * @return
	 */
	@RequestMapping("exportqrcode")
	public void downFiles(String values, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes){
			
			response.reset();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ request.getContextPath() + "/";
			String str=values;
			String[] strArray=str.substring(1).split(",");
			String filePath = request.getSession().getServletContext().getRealPath("/temp")+"\\";
			
			String zipName = HYLUtil.getMiles()+".zip";
			File[] file = new File[strArray.length];
			byte buffer[] = new byte[1024];
			
			File pathExisit = new File(filePath);
			try {
				// 判断文件夹是否存在，不存在就创建一个
				if (!pathExisit.exists() && !pathExisit.isDirectory())
				{
					System.out.println("//不存在");
					pathExisit.mkdir();
				} else
				{
					System.out.println("//目录存在");
				}
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(filePath+zipName));
				for(int i=0;i<strArray.length;i++)
				{
					String fileName = FilenameUtils.getName(basePath+strArray[i]);
					String realName = this.homeworkService.selectIdByHomeworkPath(strArray[i]);
					String url = basePath+strArray[i];
					//将url地址中的中文进行转码
					url = url.substring(0,url.lastIndexOf("/"))+"/"+URLEncoder.encode(fileName,"utf-8");
					HYLUtil.createImage(url,filePath+fileName);
					file[i] = new File(filePath+fileName);
					//设置压缩包中的文件名
					FileInputStream fis = new FileInputStream(file[i]);
					out.putNextEntry(new ZipEntry(realName));
					out.setComment("utf-8");
					int len;
					while ((len = fis.read(buffer)) > 0)
					{                        
						out.write(buffer, 0, len);                    
					}
					out.closeEntry();
					fis.close();
					file[i].delete();
				}
				out.close();
				HYLUtil.downFile(response,zipName,filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	//-------------------
	/**
	 * 胡炎龙
	 * 添加课程信息
	 * 
	 * @param 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/uploadHomework")
	public ModelAndView uploadHomework(@RequestParam(value="file") MultipartFile file,HttpServletRequest request,Homework homework ,HttpServletResponse response,HttpSession session,String courseId) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		out = response.getWriter();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Student student = (Student)session.getAttribute("Student");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/UploadFile");
		String chsum =HomeWorkController.storeIOc(realPath, file);
		if(!chsum.equals("1")){
			if(!chsum.equals("0")){
				File dir = new File(realPath+"\\"+chsum);
				if(dir.exists()){
					homework.setHomeworkId(Tools.getGUID());
					homework.setHomeworkDel(1);
					homework.setHomeworkAt(sdf.parse(sdf.format(new Date())));
					homework.setHomeworkPath("UploadFile/"+chsum);
					homework.setStudentId(student.getStudentId());
					homework.setCourseId(courseId);
					homework.setHomeworkCatalog(student.getStudentCompanyid());
					homework.setHomeworkName(chsum);
					
					homework.setHomeworkState(1);
					homework.setHomeworkAdmin("1");
					homework.setHomeworkResult(1);
					homework.setHomeworkRank(1);
					//n.setCourseresourceFiletype(chsum.substring(chsum.lastIndexOf(".") + 1).toLowerCase());
					int i = homeworkService.insertSelective(homework);
					if(i>0){
						out.print("<script>alert('上传成功!')</script>");
						out.print("<script>window.close()</script>");
					}else{
						out.print("<script>alert('作业保存失败,请重新上传作业！')</script>");
						out.print("<script>window.close()</script>");
					}


				}else{
					out.print("<script>alert('文件保存失败,请重新上传作业！')</script>");
					out.print("<script>window.close()</script>");
				}
			}else{
				out.print("<script>alert('文件上传失败,请重新上传作业！')</script>");
				out.print("<script>window.close()</script>");
			}
		}else{
			out.print("<script>alert('文件上传格式错误,请重新上传作业！（作业格式只能为.PDF格式）')</script>");
			out.print("<script>window.close()</script>");
		}

		out.flush();
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
		extMap.put("media", "pdf");
		// 文件名称
		String fileName = "";
		String logImageName = "";
		// file调用getOriginalFilename获取完成文件名
		String _fileName = file.getOriginalFilename();
		// 用文件名截取最后一个.后面的字符当作扩展名
		String suffix = _fileName.substring(_fileName.lastIndexOf("."));
		String fileExt = _fileName.substring(_fileName.lastIndexOf(".") + 1).toLowerCase();
		if(!Arrays.<String>asList(extMap.get("media").split(",")).contains(fileExt)){
			return "1";
		}
		/** 使用UUID生成文件名称 **/
		logImageName = UUID.randomUUID().toString().replace("-", "") + suffix;

		fileName = realPath + File.separator + logImageName;
		File restore = new File(fileName);
		try
		{
			file.transferTo(restore);
			return logImageName;
		} catch (Exception e){
			//			throw new RuntimeException(e);
		}
		// 返回默认的图片地址
		return "0";
	}


}
