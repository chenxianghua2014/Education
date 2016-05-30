package com.ttgis.education.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Course;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Papers;
import com.ttgis.education.entity.Ruld;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Subject;
import com.ttgis.education.entity.Test;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.entity.page.SelStudent;
import com.ttgis.education.entity.page.SelSubject;
import com.ttgis.education.service.CourseService;
import com.ttgis.education.service.PapersService;
import com.ttgis.education.service.RuldService;
import com.ttgis.education.service.StudentService;
import com.ttgis.education.service.SubjectService;
import com.ttgis.education.service.TestService;
import com.ttgis.education.utils.Const;
import com.ttgis.education.utils.RandomGUIDUtil;
import com.ttgis.education.utils.Tools;

/**
 * 
 * @author SJG
 * 
 */
@Controller
public class TestController
{
	@Resource
	SubjectService subjectService;
	@Resource
	TestService testService;
	@Resource
	StudentService studentService;
	@Resource
	private RuldService ruldService;//规则
	@Resource
	private CourseService courseService;
	@Resource
	private PapersService papersService;

	private SelSubject  selSubject;

	/**
	 * 导入试题
	 * 
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/testImport", method = RequestMethod.GET)
	public ModelAndView testImport(Subject subject)
	{
		return new ModelAndView("test/testImport");
	}

	/**
	 * 添加试题
	 * 
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/testAdd", method = RequestMethod.GET)
	public ModelAndView testAdd(Subject subject)
	{
		String url = "";
		switch (subject.getSubjectType())
		{
		case "1_4":
			url = "test/testAdd1_4";
			subject.setSubjectType("1_4");
			break;
		case "1_3":
			url = "test/testAdd1_3";
			subject.setSubjectType("1_3");
			break;
		case "1_5m":
			url = "test/testAdd1_5m";
			subject.setSubjectType("1_5m");
			break;
		case "1_m":
			url = "test/testAdd1_m";
			subject.setSubjectType("1_m");
			break;
		case "2":
			url = "test/testAdd2";
			subject.setSubjectType("2");
			break;
		case "3":
			url = "test/testAdd3";
			subject.setSubjectType("3");
			break;
		case "4":
			url = "test/testAdd4";
			subject.setSubjectType("4");
			break;
		default:
			url = "test/testAdd1_4";
			subject.setSubjectType("1_4");
			break;
		}

		if (subject.getSubjectId() == null)
		{
			subject.setSubjectScore(1.0);
			return new ModelAndView(url);
		} else
			subject = subjectService.selectByPrimaryKey(subject.getSubjectId());
			return new ModelAndView(url, "subject", subject);
	}

	/**
	 * 查看试题
	 * 
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/test")
	public ModelAndView test(SelSubject subject ,int mark, String syllabusId)
	{
		ModelAndView mv = new ModelAndView();
		PageBean pageBean = new PageBean();
		if(mark==1){
			selSubject =new SelSubject();
		}

		//---------------------------------------判断条件------------------------------------------
		if(subject.getSubjectType()!=null && subject.getSubjectType()!="" ){
			selSubject.setSubjectType(subject.getSubjectType());
		}
		if(subject.getSubjectName()!=null && subject.getSubjectName()!="" ){
			selSubject.setSubjectName(subject.getSubjectName());
		}
		selSubject.setCourseId(subject.getCourseId());
		int allRow = subjectService.selectCountByWhere(selSubject);
		final int length = 20;// 每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);// 总页数
		final int currentPage = PageBean.countCurrentPage(subject.getPage());// 当前页为0处理

		selSubject.setBegin(length*(currentPage-1));
		selSubject.setEnd(length*currentPage);
		List<Subject> subjects = subjectService.selectByWhere(selSubject);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(subjects);
		pageBean.setUrl("test?mark=0&courseId=" + selSubject.getCourseId());// 连接地址
		mv.addObject("syllabusId",syllabusId);
		mv.addObject("subject",selSubject);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("test/test");
		return mv;
	}
	/**
	 * 查看试题规则
	 * wyp
	 * @param subject
	 * @return
	 */
	@RequestMapping(value = "/seeRuld", method = RequestMethod.GET)
	public ModelAndView seeRuld(Ruld ruld, String syllabusId)
	{
		ModelAndView mv = new ModelAndView();
		List<Ruld> ruldList = ruldService.selectCountByWhere(ruld);
		if (ruldList.size()!=0) {
			Ruld rulds = ruldList.get(0);
			mv.addObject("rulds",rulds);
		}
		mv.addObject("syllabusId",syllabusId);
		mv.addObject("ruld",ruld);
		mv.setViewName("test/queryRuld");
		return mv;
	}

	/**
	 * 添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/ruldId")
	public ModelAndView ruldId(Ruld ruld,HttpServletRequest request, String syllabusId) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("courseId",ruld.getCourseId());
		mv.addObject("syllabusId",syllabusId);
		mv.setViewName("test/addRuld");
		return mv;
	}
	/**
	 * 修改跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/ruldIdx")
	public ModelAndView ruldIdx(Ruld ruld,HttpServletRequest request, String syllabusId) {
		ModelAndView mv = new ModelAndView();
		Ruld ruldList = ruldService.selectByPrimaryKey(ruld.getRuldId());
		mv.addObject("ruldList",ruldList);
		mv.addObject("courseId",ruld.getCourseId());
		mv.addObject("syllabusId",syllabusId);
		mv.setViewName("test/updateRuld");
		return mv;
	}
	/**
	 * 添加试题规则
	 * 
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ruldSava" , method = RequestMethod.POST)
	public String ruldSava(Ruld rulds) {
		// 屏蔽次课程ID下原来的规则
		if (rulds.getCourseId() != null && rulds.getCourseId() != "") {
			int j = 0;
			Ruld rulda=new Ruld();
			rulda.setCourseId(rulds.getCourseId());
			List<Ruld> ruldList = ruldService.selectCountByWhere(rulda);
			//第一次添加
			if (ruldList.size() == 0) {
				//计算总分数
				if (rulds.getRuldScore13()==null) {
					rulds.setRuldScore13(0.0);
				}
				if (rulds.getRuldQuiznum13()==null) {
					rulds.setRuldQuiznum13(0);
				}
				if (rulds.getRuldScore14()==null) {
					rulds.setRuldScore14(0.0);
				}
				if (rulds.getRuldQuiznum14()==null) {
					rulds.setRuldQuiznum14(0);
				}
				if (rulds.getRuldScore1M()==null) {
					rulds.setRuldScore1M(0.0);
				}
				if (rulds.getRuldQuiznum1M()==null) {
					rulds.setRuldQuiznum1M(0);
				}
				if (rulds.getRuldScore15M()==null) {
					rulds.setRuldScore15M(0.0);
				}
				if (rulds.getRuldQuiznum15M()==null) {
					rulds.setRuldQuiznum15M(0);
				}
				if (rulds.getRuldScore2()==null) {
					rulds.setRuldScore2(0.0);
				}
				if (rulds.getRuldQuiznum2()==null) {
					rulds.setRuldQuiznum2(0);
				}
				if (rulds.getRuldScore3()==null) {
					rulds.setRuldScore3(0.0);
				}
				if (rulds.getRuldQuiznum3()==null) {
					rulds.setRuldQuiznum3(0);
				}
				if (rulds.getRuldScore4()==null) {
					rulds.setRuldScore4(0.0);
				}
				if (rulds.getRuldQuiznum4()==null) {
					rulds.setRuldQuiznum4(0);
				}
				double shu = rulds.getRuldScore13()*rulds.getRuldQuiznum13()+rulds.getRuldScore14()*rulds.getRuldQuiznum14()+rulds.getRuldScore15M()*rulds.getRuldQuiznum15M()+rulds.getRuldScore1M()*rulds.getRuldQuiznum1M()+rulds.getRuldScore2()*rulds.getRuldQuiznum2()+rulds.getRuldScore3()*rulds.getRuldQuiznum3()+rulds.getRuldScore4()*rulds.getRuldQuiznum4();
				rulds.setRuldTotalscore(shu);
				rulds.setRuldId(RandomGUIDUtil.generateKey());// 试题规则ID
				rulds.setRuldAt(new Date());// 添加时间
				rulds.setRuldDel(1);// 删除标记
				int i = ruldService.insertSelective(rulds);// 执行添加

				if (i > 0) {
					return "1";
				}
			} else {
				//第二次添加  屏蔽第前一次次
				for (int i = 0; i < ruldList.size(); i++) {
					ruldList.get(i).setRuldDel(0);
					j = ruldService.updateByPrimaryKeySelective(ruldList.get(i));

				}
				if (j > 0) {
					//计算总分数
					double shu = rulds.getRuldScore13()*rulds.getRuldQuiznum13()+rulds.getRuldScore14()*rulds.getRuldQuiznum14()+rulds.getRuldScore15M()*rulds.getRuldQuiznum15M()+rulds.getRuldScore1M()*rulds.getRuldQuiznum1M()+rulds.getRuldScore2()*rulds.getRuldQuiznum2()+rulds.getRuldScore3()*rulds.getRuldQuiznum3()+rulds.getRuldScore4()*rulds.getRuldQuiznum4();
					rulds.setRuldTotalscore(shu);
					rulds.setRuldId(RandomGUIDUtil.generateKey());// 试题规则ID
					rulds.setRuldAt(new Date());// 添加时间
					rulds.setRuldDel(1);// 删除标记
					int i = ruldService.insertSelective(rulds);// 执行添加

					if (i > 0) {
						//删除对应的题组
						Papers pa=new Papers();
						pa.setCourseId(rulds.getCourseId());
						List<Papers> papersList = papersService.thePapersCourseId(pa);
						for (int g = 0; g < papersList.size(); g++) {
							pa.setPapersId(papersList.get(g).getPapersId());
							pa.setPapersDel(0);
							int h=papersService.updateByPrimaryKeySelective(pa);
						}
						return "1";
					}
				}
			}
		}
		return "2";
	}
	/**
	 * 修改试题规则
	 * 
	 * @param 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ruldUpdateSava" , method = RequestMethod.POST)
	public String ruldUpdateSava(Ruld rulds) {
		if (rulds.getCourseId() != null && rulds.getCourseId() != "") {
			//修改
					//计算总分数
					double shu = rulds.getRuldScore13()*rulds.getRuldQuiznum13()+rulds.getRuldScore14()*rulds.getRuldQuiznum14()+rulds.getRuldScore15M()*rulds.getRuldQuiznum15M()+rulds.getRuldScore1M()*rulds.getRuldQuiznum1M()+rulds.getRuldScore2()*rulds.getRuldQuiznum2()+rulds.getRuldScore3()*rulds.getRuldQuiznum3()+rulds.getRuldScore4()*rulds.getRuldQuiznum4();
					rulds.setRuldTotalscore(shu);
					Ruld r1 = new Ruld();
					r1.setRuldId(rulds.getRuldId());
					r1.setRuldDel(0);
					int j = ruldService.updateByPrimaryKeySelective(r1);// 执行修改  保留原来历史
					rulds.setRuldId(RandomGUIDUtil.generateKey());// 试题规则ID
					rulds.setRuldAt(new Date());// 添加时间
					rulds.setRuldDel(1);// 删除标记
					int i = ruldService.insertSelective(rulds);// 执行添加
					if (i>0) {
						return "1";
					}else{
						return "2";
					}
		}
		return "2";
	}


	/**
	 * 查看试卷
	 * 陈健龙
	 * @param subject
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/queryPapers", method = RequestMethod.GET)
	public ModelAndView queryPapers(Ruld ruld,HttpServletResponse response, String syllabusId) throws IOException{	
		ModelAndView mv = new ModelAndView();
		List<Ruld> list = ruldService.selectCountByWhere(ruld);
		if(list.size() > 0 ){//判断是否有规则
			Papers p = new Papers();
			p.setCourseId(ruld.getCourseId());
			p.setRuldId(list.get(0).getRuldId());
			List<Integer> listp = papersService.selectCountByWhere(p);
			mv.addObject("syllabusId",syllabusId);
			mv.addObject("CourseId",p.getCourseId());
			mv.addObject("RuldId",p.getRuldId());
			mv.addObject("listp",listp);
			mv.setViewName("test/queryPapers");
			return mv;
		}else{
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = null;
			out = response.getWriter();
			out.print("<script>alert('没有可用规则，请添加考试规则')</script>");
			out.print("<script>window.location.href='seeRuld?courseId="+ruld.getCourseId()+"'</script>");
			out.flush();
			return null;
		}

	}

	/**
	 * 保存试题
	 * 
	 * @param subject
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/subjectSava", method = RequestMethod.POST)
	public int subjectSava(Subject subject, HttpSession session)
	{
		if (subject.getSubjectId().equals(""))
		{
			Account account = (Account) session.getAttribute(Const.SESSION_USER);
			subject.setSubjectDel(1);
			subject.setSubjectAt(new Date());
			subject.setSubjectState("可用");
			subject.setSubjectId(RandomGUIDUtil.generateKey().toString());
			subject.setSubjectProducer(account.getAccountId());
			subject.setSubjectCatalog(account.getAccountCatalog());
			return subjectService.insertSelective(subject);
		} else
		{
			return subjectService.updateByPrimaryKeySelective(subject);
		}
	}

	/**
	 * 删除试题
	 * 
	 * @param subjectId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testDel", method = RequestMethod.POST)
	public int testDel(String subjectId)
	{	
		Subject subject= new Subject();
		subject.setSubjectId(subjectId);
		subject.setSubjectDel(0);
		int ch = subjectService.updateByPrimaryKeySelective(subject);
		if(ch==1){
			return 1;
		}else{
			return 0;
		}

	}

	/**
	 * 试题导入
	 * 
	 * @param excel
	 * @param subject
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/testDoImport", method = RequestMethod.POST)
	public ModelAndView testDoImport(@RequestParam("excel") MultipartFile excel, Subject subject, HttpSession session)
	{
		Message message = new Message();
		message.setMessage("导入失败,请参照模版检查excel格式是否正确!");
		message.setUri("testImport?subjectType=" + subject.getSubjectType() + "&courseId=" + subject.getCourseId());
		HSSFWorkbook workbook = null;
		try
		{
			workbook = new HSSFWorkbook(excel.getInputStream()); // 将获取的流转成Excel
			HSSFSheet sheet = workbook.getSheet("Sheet1");// 获取Sheet
			Iterator<Row> rows = sheet.rowIterator();// 将Excel行数据装入迭代器
			boolean result;
			Account account = (Account) session.getAttribute(Const.SESSION_USER);
			switch (subject.getSubjectType())
			{
			case "1_4":
				result = addSubjectFourChoose(subject, rows, account);
				break;
			case "1_3":
				result = addThreeChooseTest(subject, rows, account);
				break;
			case "1_5m":
				result = addSubjectFourChooseD5(subject, rows, account);
				break;
			case "1_m":
				result = addSubjectFourChooseD(subject, rows, account);
				break;
			case "2":
				result = addJudgeTest(subject, rows, account);
				break;
			case "3":
				result = addSubjectiveTest(subject, rows, account);
				break;
			case "4":
				result = addSubjectiveTest(subject, rows, account);
				break;
			default:
				result = addSubjectFourChoose(subject, rows, account);
				break;
			}
			if (result)
			{
				message.setMessage("导入成功!");
				message.setUri("test?mark=1&courseId=" + subject.getCourseId());
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return new ModelAndView("success", "message", message);
	}

	/**
	 * 验证文件大小
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex, HttpServletRequest request)
	{
		Message message = new Message();
		if (ex instanceof org.springframework.web.multipart.MaxUploadSizeExceededException)
		{
			message.setMessage("文件过大!");
			message.setUri("testImport");
		}
		return new ModelAndView("success", "message", message);
	}


	/**
	 * 四项选择题
	 * 
	 * @param subject
	 * @param rows
	 * @param account
	 * @return
	 */
	private boolean addSubjectFourChoose(Subject subject, Iterator<Row> rows, Account account)
	{
		try
		{
			int i = 0;
			while (rows.hasNext())
			{
				i++;
				Row row = (Row) rows.next(); // 获取一行数据
				if (i <= 1)
					continue;
				if (row.getCell(0).toString().trim().length() == 0)
					break;
				// 迭代Excel 行
				Subject inSubject = new Subject();// 创建实体接收数据
				inSubject.setSubjectId(RandomGUIDUtil.generateKey());
				inSubject.setSubjectAt(new Date());
				inSubject.setSubjectDel(1);
				inSubject.setSubjectState("可用");
				inSubject.setSubjectName(row.getCell(0) + ""); // 试题资源名称
				inSubject.setCourseId(subject.getCourseId());
				inSubject.setSubjectType(subject.getSubjectType()); // 试题类型
				String strSttg = ToDBC(row.getCell(1).toString());
				String strStxxa = ToDBC(row.getCell(2).toString());
				String strStxxb = ToDBC(row.getCell(3).toString());
				String strStxxc = ToDBC(row.getCell(4).toString());
				String strStxxd = ToDBC(row.getCell(5).toString());
				inSubject.setSubjectTopic(strSttg); // 试题题干
				inSubject.setSubjectA(strStxxa); // 答案 A
				inSubject.setSubjectB(strStxxb); // 答案 B
				inSubject.setSubjectC(strStxxc); // 答案 C
				inSubject.setSubjectD(strStxxd); // 答案 D
				inSubject.setSubjectAnswer(row.getCell(6).toString().toUpperCase() + ""); // 正确答案
				inSubject.setSubjectProducer(account.getAccountId());
				inSubject.setSubjectCatalog(account.getAccountCatalog());
				subjectService.insertSelective(inSubject);
			}
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 多选题5导入
	 * 
	 * @param subject
	 * @param rows
	 * @param account
	 * @return
	 */
	private boolean addSubjectFourChooseD5(Subject subject, Iterator<Row> rows, Account account)
	{
		try
		{
			int i = 0;
			while (rows.hasNext())
			{
				i++;
				Row row = (Row) rows.next(); // 获取一行数据
				if (i <= 1)
					continue;
				if (row.getCell(0).toString().trim().length() == 0)
					break;
				// 迭代Excel 行
				Subject inSubject = new Subject();// 创建实体接收数据
				inSubject.setSubjectId(RandomGUIDUtil.generateKey());
				inSubject.setSubjectAt(new Date());
				inSubject.setSubjectDel(1);
				inSubject.setSubjectState("可用");
				inSubject.setSubjectName(row.getCell(0) + ""); // 试题资源名称
				inSubject.setCourseId(subject.getCourseId());
				inSubject.setSubjectType(subject.getSubjectType()); // 试题类型
				String strSttg = ToDBC(row.getCell(1).toString());
				String strStxxa = ToDBC(row.getCell(2).toString());
				String strStxxb = ToDBC(row.getCell(3).toString());
				String strStxxc = ToDBC(row.getCell(4).toString());
				String strStxxd = ToDBC(row.getCell(5).toString());
				String strStxxe = ToDBC(row.getCell(6).toString());
				inSubject.setSubjectTopic(strSttg); // 试题题干
				inSubject.setSubjectA(strStxxa); // 答案 A
				inSubject.setSubjectB(strStxxb); // 答案 B
				inSubject.setSubjectC(strStxxc); // 答案 C
				inSubject.setSubjectD(strStxxd); // 答案 D
				inSubject.setSubjectE(strStxxe);// 答案 E
				inSubject.setSubjectAnswer(row.getCell(7).toString().toUpperCase() + ""); // 正确答案
				inSubject.setSubjectProducer(account.getAccountId());
				inSubject.setSubjectCatalog(account.getAccountCatalog());
				subjectService.insertSelective(inSubject);
			}
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 多选题6导入
	 * 
	 * @param subject
	 * @param rows
	 * @param account
	 * @return
	 */
	private boolean addSubjectFourChooseD(Subject subject, Iterator<Row> rows, Account account)
	{
		try
		{
			int i = 0;
			while (rows.hasNext())
			{
				i++;
				Row row = (Row) rows.next(); // 获取一行数据
				if (i <= 1)
					continue;
				if (row.getCell(0).toString().trim().length() == 0)
					break;
				// 迭代Excel 行
				Subject inSubject = new Subject();// 创建实体接收数据
				inSubject.setSubjectId(RandomGUIDUtil.generateKey());
				inSubject.setSubjectAt(new Date());
				inSubject.setSubjectDel(1);
				inSubject.setSubjectState("可用");
				inSubject.setSubjectName(row.getCell(0) + ""); // 试题资源名称
				inSubject.setCourseId(subject.getCourseId());
				inSubject.setSubjectType(subject.getSubjectType()); // 试题类型
				String strSttg = ToDBC(row.getCell(1).toString());
				String strStxxa = ToDBC(row.getCell(2).toString());
				String strStxxb = ToDBC(row.getCell(3).toString());
				String strStxxc = ToDBC(row.getCell(4).toString());
				String strStxxd = ToDBC(row.getCell(5).toString());
				String strStxxe = ToDBC(row.getCell(6).toString());
				String strStxxf = ToDBC(row.getCell(7).toString());
				inSubject.setSubjectTopic(strSttg); // 试题题干
				inSubject.setSubjectA(strStxxa); // 答案 A
				inSubject.setSubjectB(strStxxb); // 答案 B
				inSubject.setSubjectC(strStxxc); // 答案 C
				inSubject.setSubjectD(strStxxd); // 答案 D
				inSubject.setSubjectE(strStxxe);// 答案 E
				inSubject.setSubjectF(strStxxf);// 答案 F
				inSubject.setSubjectAnswer(row.getCell(8).toString().toUpperCase() + ""); // 正确答案
				inSubject.setSubjectProducer(account.getAccountId());
				inSubject.setSubjectCatalog(account.getAccountCatalog());
				subjectService.insertSelective(inSubject);
			}
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 三项选择题导入
	 * 
	 * @param subject
	 * @param rows
	 * @param account
	 * @return
	 */
	private boolean addThreeChooseTest(Subject subject, Iterator<Row> rows, Account account)
	{
		try
		{
			int i = 0;
			while (rows.hasNext())
			{
				i++;
				Row row = (Row) rows.next(); // 获取一行数据
				if (i <= 1)
					continue;
				if (row.getCell(0).toString().trim().length() == 0)
					break;
				// 迭代Excel 行
				Subject inSubject = new Subject();// 创建实体接收数据
				inSubject.setSubjectId(RandomGUIDUtil.generateKey());
				inSubject.setSubjectAt(new Date());
				inSubject.setSubjectDel(1);
				inSubject.setSubjectState("可用");
				inSubject.setSubjectName(row.getCell(0) + ""); // 试题资源名称
				inSubject.setCourseId(subject.getCourseId());
				inSubject.setSubjectType(subject.getSubjectType()); // 试题类型
				String strSttg = ToDBC(row.getCell(1).toString());
				String strStxxa = ToDBC(row.getCell(2).toString());
				String strStxxb = ToDBC(row.getCell(3).toString());
				String strStxxc = ToDBC(row.getCell(4).toString());
				inSubject.setSubjectTopic(strSttg); // 试题题干
				inSubject.setSubjectA(strStxxa); // 答案 A
				inSubject.setSubjectB(strStxxb); // 答案 B
				inSubject.setSubjectC(strStxxc); // 答案 C
				inSubject.setSubjectAnswer(row.getCell(5).toString().toUpperCase() + ""); // 正确答案
				inSubject.setSubjectProducer(account.getAccountId());
				inSubject.setSubjectCatalog(account.getAccountCatalog());
				subjectService.insertSelective(inSubject);
			}
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 判断题导入
	 * 
	 * @param subject
	 * @param rows
	 * @param account
	 * @return
	 */
	private boolean addJudgeTest(Subject subject, Iterator<Row> rows, Account account)
	{
		try
		{
			int i = 0;
			while (rows.hasNext())
			{
				i++;
				Row row = (Row) rows.next(); // 获取一行数据
				if (i <= 1)
					continue;
				if (row.getCell(0).toString().trim().length() == 0)
					break;
				// 迭代Excel 行
				Subject inSubject = new Subject();// 创建实体接收数据
				inSubject.setSubjectId(RandomGUIDUtil.generateKey());
				inSubject.setSubjectAt(new Date());
				inSubject.setSubjectDel(1);
				inSubject.setSubjectState("可用");
				inSubject.setSubjectName(row.getCell(0) + ""); // 试题资源名称
				inSubject.setCourseId(subject.getCourseId());
				inSubject.setSubjectType(subject.getSubjectType()); // 试题类型
				String strSttg = ToDBC(row.getCell(1).toString());
				inSubject.setSubjectTopic(strSttg); // 试题题干
				inSubject.setSubjectAnswer(row.getCell(2).toString().toUpperCase() + ""); // 正确答案
				inSubject.setSubjectProducer(account.getAccountId());
				inSubject.setSubjectCatalog(account.getAccountCatalog());
				subjectService.insertSelective(inSubject);
			}
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	/**
	 * 主观题、填空题导入
	 * 
	 * @param subject
	 * @param rows
	 * @param account
	 * @return
	 */
	private boolean addSubjectiveTest(Subject subject, Iterator<Row> rows, Account account)
	{
		try
		{
			int i = 0;
			while (rows.hasNext())
			{
				i++;
				Row row = (Row) rows.next(); // 获取一行数据
				if (i <= 1)
					continue;
				if (row.getCell(0).toString().trim().length() == 0)
					break;
				// 迭代Excel 行
				Subject inSubject = new Subject();// 创建实体接收数据
				inSubject.setSubjectId(RandomGUIDUtil.generateKey());
				inSubject.setSubjectAt(new Date());
				inSubject.setSubjectDel(1);
				inSubject.setSubjectState("可用");
				inSubject.setSubjectName(row.getCell(0) + ""); // 试题资源名称
				inSubject.setCourseId(subject.getCourseId());
				inSubject.setSubjectType(subject.getSubjectType()); // 试题类型
				String strSttg = ToDBC(row.getCell(1).toString());
				inSubject.setSubjectTopic(strSttg); // 试题题干
				inSubject.setSubjectAnswer("手动评分"); // 正确答案
				inSubject.setSubjectProducer(account.getAccountId());
				inSubject.setSubjectCatalog(account.getAccountCatalog());
				subjectService.insertSelective(inSubject);
			}
			return true;
		} catch (Exception e)
		{
			return false;
		}
	}

	public static String ToDBC(String strSttg)
	{
		return strSttg.replaceAll("\\s", "").replaceAll("\u3000", "");
	}

	/**
	 * 学员考试
	 * 
	 * @param selSubject
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/testView", method = RequestMethod.GET)
	public ModelAndView testView(Ruld ruld, HttpSession session,HttpServletResponse response) throws IOException
	{
		ModelAndView mv = new ModelAndView();
		Student student = (Student)session.getAttribute("Student");
		if(student==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		out = response.getWriter();

		//时间转换
		Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd"); 

		Course c = courseService.selectByPrimaryKey(ruld.getCourseId());
		session.setAttribute("co", c);
		ruld.setRuldYxtime(time.format(nowTime).toString());
		List<Ruld> list = ruldService.selectCountByWhere(ruld);//查询规则



		if(list.size() > 0 ){//判断是否有规则
			Test test =  new Test();
			test.setCourseId(list.get(0).getCourseId());
			test.setRuldId(list.get(0).getRuldId());
			test.setStudentId(student.getStudentId());
			List <Test> tlist =  testService.StudentTextSum(test);

			Papers p = new Papers();
			p.setCourseId(ruld.getCourseId());
			p.setRuldId(list.get(0).getRuldId());
			Papers pp1 = papersService.thePapers(p);//抽取试卷
			if(pp1!=null){//是否有试卷
				if(list.get(0).getRuldBits()==1){
					if(tlist.size()>0){
						out.print("<script>alert('已经考过！！')</script>");
						out.print("<script>window.history.back()</script>");
						out.flush();
						return null;
					}else{
						out.print("<script>alert('本次考试只能进行一次 请认真答题')</script>");
						out.flush();
					}
				}else{
					if(tlist.size()>0){
						test.setCourseId(list.get(0).getCourseId());
						test.setRuldId(list.get(0).getRuldId());
						test.setStudentId(student.getStudentId());
						int sum = testService.testByscore(test);
						out.print("<script>	if (!confirm('您上次考试成绩为"+sum+"，重新考试后将以最新考试为主，是否继续?')) {window.history.back()}</script>");
						out.flush();
					}else{
						out.print("<script>alert('该课程考试可以多次进行，成绩以最新的为主')</script>");
						out.flush();
					}
				}
				p.setPapersThe(pp1.getPapersThe());
				List<Papers> plist= papersService.MyPapersQuery(p);
				String[] strType =
					{ "1_3", "1_4", "1_5m", "1_m", "2", "3", "4" };
				Map<String, List<Papers>> map = new HashMap<String, List<Papers>>();
				for (int i = 0; i < strType.length; i++)
				{
					List<Papers> pplist = new ArrayList<Papers>();
					for(int j = 0 ; j<plist.size();j++){
						Papers pp = plist.get(j);
						if(strType[i].equals(pp.getSubject().getSubjectType())){//晒出试题
							pplist.add(pp);
						}
					}
					map.put(strType[i], pplist);
				}
				session.setAttribute("testMap", map);
				mv.addObject("map",map);
				mv.addObject("ruld",list.get(0));
				mv.setViewName("test/testView");
				return mv;
			}
		}

		out.print("<script>alert('没有试卷或错过考试时间')</script>");
		out.print("<script>window.history.back()</script>");
		out.flush();
		return null;



	}

	/**
	 * 交卷
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/testSubmit"  ,method = RequestMethod.POST)
	public ModelAndView testSubmit(Ruld ruld,HttpServletRequest request, HttpSession session)
	{
		Ruld ruld2 =ruldService.selectByPrimaryKey(ruld.getRuldId());
		Map<String, List<Papers>> mv = (Map<String, List<Papers>>) session.getAttribute("testMap");
		Student student = (Student) session.getAttribute("Student");
		int k = 0;
		Test t = new Test();
		t.setStudentId(student.getStudentId());
		for (Map.Entry<String, List<Papers>> subjectList : mv.entrySet())
		{
			for (Papers subject : subjectList.getValue())
			{
				if(k==0){//结算第最后1次考试标记
					t.setCourseId(subject.getCourseId());
					t = testService.testBymark(t);
					if(t==null){
						k=1;
					}else{
						k=t.getTestMark()+1;
					}

				}
				String[] strings = request.getParameterValues(subject.getSubject().getSubjectId());
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < request.getParameterValues(subject.getSubject().getSubjectId()).length; i++)
				{
					sb.append(strings[i]);
					if (i != strings.length - 1)
						sb.append(",");
				}
				System.out.println(sb.toString());

				Test test = new Test();
				test.setTestId(RandomGUIDUtil.generateKey().toString());
				test.setStudentId(student.getStudentId());
				test.setSubjectId(subject.getSubject().getSubjectId());
				test.setTestAnswer(sb.toString());
				test.setTestAt(new Date());
				test.setTestDel(1);
				test.setCourseId(subject.getSubject().getCourseId());
				test.setTestMark(k);
				test.setRuldId(ruld2.getRuldId());
				if (subject.getSubject().getSubjectType().equals("3") || subject.getSubject().getSubjectType().equals("4"))
					test.setTestScore(-1.0);
				else
				{
					if (sb.toString().equals(subject.getSubject().getSubjectAnswer())){
						if(subject.getSubject().getSubjectType().equals("1_3")){
							test.setTestScore(ruld2.getRuldScore13());
						}
						if(subject.getSubject().getSubjectType().equals("1_4")){
							test.setTestScore(ruld2.getRuldScore14());
						}
						if(subject.getSubject().getSubjectType().equals("1_m")){
							test.setTestScore(ruld2.getRuldScore1M());
						}
						if(subject.getSubject().getSubjectType().equals("1_5m")){
							test.setTestScore(ruld2.getRuldScore15M());
						}
						if(subject.getSubject().getSubjectType().equals("2")){
							test.setTestScore(ruld2.getRuldScore2());
						}
						if(subject.getSubject().getSubjectType().equals("3")){
							test.setTestScore(ruld2.getRuldScore3());
						}
						if(subject.getSubject().getSubjectType().equals("4")){
							test.setTestScore(ruld2.getRuldScore4());
						}

					}else
						test.setTestScore(0.0);
				}
				testService.insertSelective(test);
			}
		}

		Message message = new Message();
		message.setMessage("提交成功!");
		message.setUri("main");
		return new ModelAndView("success", "message", message);
	}

	/**
	 * 成绩管理
	 * 
	 * @param selTest
	 * @return
	 */
	@RequestMapping(value = "/scoreManage", method = RequestMethod.GET)
	public ModelAndView scoreManage(Test selTest)
	{
		PageBean pageBean = new PageBean();
		int allRow = testService.selectTextBysum(selTest);
		final int length = 20;// 每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);// 总页数
		final int currentPage = PageBean.countCurrentPage(selTest.getPage());// 当前页为0处理

		selTest.setPage(length*(currentPage-1));
		selTest.setSize(length);
		List<Test> tests = testService.selectPageAll(selTest);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(tests);
		pageBean.setUrl("scoreManage?studentId="+selTest.getStudentId()+"&courseId="+selTest.getCourseId());// 连接地址

		return new ModelAndView("test/scoreManage", "pageBean", pageBean);
	}

	/**
	 * 手动评分
	 * 
	 * @param test
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updScore", method = RequestMethod.POST)
	public double updScore(Test test)
	{
		double sum = 0;
		Test  test2= testService.selectByPrimaryKey(test.getTestId());
		if(test2.getSubject().getSubjectType().equals("3") ){
			sum=test2.getRuld().getRuldScore3();
			if(test.getTestScore()<=test2.getRuld().getRuldScore3()){
				testService.updateByPrimaryKeySelective(test);
				return 0;
			}

		}
		if(test2.getSubject().getSubjectType().equals("4")){
			sum=test2.getRuld().getRuldScore4();
			if( test.getTestScore()<=test2.getRuld().getRuldScore4()){
				testService.updateByPrimaryKeySelective(test);
				return 0;
			}

		}
		return sum;
	}

	/**
	 * 成绩查询
	 * 
	 * @param selStudent
	 * @return
	 */
	@RequestMapping(value = "/scoreView", method = RequestMethod.GET)
	public ModelAndView scoreView(SelStudent selStudent)
	{
		ModelAndView mv = new ModelAndView();
		PageBean pageBean = new PageBean();

		Ruld  r = new Ruld(); 
		r.setCourseId(selStudent.getCourseId());
		List<Ruld> listr = ruldService.selectCountByWhere(r);
		if(listr.size()>0){
			selStudent.setStudentName(listr.get(0).getRuldId());
		}


		int allRow = studentService.selectCountByWhere(selStudent);
		final int length = 20;// 每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);// 总页数
		final int currentPage = PageBean.countCurrentPage(selStudent.getPage());// 当前页为0处理

		selStudent.setBegin(length*(currentPage-1));
		selStudent.setEnd(length*currentPage);
		List<Student> tests = studentService.selectByWhere(selStudent);
		for (int i = 0; i < tests.size(); i++) {

			Test t = new Test();
			t.setCourseId(selStudent.getCourseId());
			t.setStudentId(tests.get(i).getStudentId());
			int mark = testService.testByscoreManags(t);
			tests.get(i).setStudentNoscore(mark+"");
			if(mark>0){
				tests.get(i).setSize(-1);
			}else{
				int sum = testService.testByscore(t);
				tests.get(i).setSize(sum);
			}

		}

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(tests);
		pageBean.setUrl("scoreView?courseId="+selStudent.getCourseId());// 连接地址

		mv.addObject("ss",selStudent);
		if(listr.size()>0){
			mv.addObject("ruld",listr.get(0));
		}
		mv.addObject("pageBean",pageBean);
		mv.setViewName("test/scoreView");
		return mv;
	}

	@RequestMapping(value = "/MyTest", method = RequestMethod.GET)
	public ModelAndView MyTest(SelStudent selStudent, HttpSession session)
	{
		Student student = (Student) session.getAttribute("Student");

		return null;
	}


	/**
	 * 删除试卷（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deletePapers", method = RequestMethod.GET)
	public String deletePapers(Papers p) {
		int sug = 0;
		sug = papersService.updatePid(p);

		if(sug>0){
			return "ok";
		} else {
			return "no";
		}

	}

	/**
	 * 试卷预览
	 * 
	 * @param selSubject
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/testPreview", method = RequestMethod.GET)
	public ModelAndView testPreview(Papers p, HttpSession session,HttpServletResponse response) throws IOException
	{
		ModelAndView mv = new ModelAndView();
		Map<String, List<Papers>> map = new HashMap<String, List<Papers>>();
		Ruld ruld2 =ruldService.selectByPrimaryKey(p.getRuldId());
		List<Papers> plist= papersService.MyPapersQuery(p);
		String[] strType =
			{ "1_3", "1_4", "1_5m","1_m", "2", "3", "4" };
		for (int i = 0; i < strType.length; i++)
		{
			List<Papers> pplist = new ArrayList<Papers>();
			for(int j = 0 ; j<plist.size();j++){
				Papers pp = plist.get(j);
				if(strType[i].equals(pp.getSubject().getSubjectType())){//晒出试题
					pplist.add(pp);
				}
			}
			map.put(strType[i], pplist);
		}
		mv.addObject("map",map);
		mv.addObject("ruld",ruld2);
		mv.setViewName("test/testPreview");
		return mv;
	}


	/**
	 * 生成试卷
	 * 
	 * @param selSubject
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/savePapers", method = RequestMethod.GET)
	public ModelAndView savePapers(Papers p, HttpSession session,HttpServletResponse response, String syllabusId) throws IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		out = response.getWriter();

		Ruld ruld2 =ruldService.selectByPrimaryKey(p.getRuldId());
		Papers papers1 = papersService.PapersMySum(p);
		int sum =1;
		if(papers1==null){
			sum=1;
		}else {
			sum = papers1.getPapersThe()+1;
		}
		SelSubject selSubject = new SelSubject();
		selSubject.setCourseId(p.getCourseId());
		String[] strType =
			{ "1_3", "1_4","1_5m", "1_m", "2", "3", "4" };
		for (int i = 0; i < strType.length; i++)
		{
			String nameString = "";
			if(strType[i].equals("1_3")){
				selSubject.setPage(ruld2.getRuldQuiznum13());
				nameString= ruld2.getRuldName13();
			}
			if(strType[i].equals("1_4")){
				selSubject.setPage(ruld2.getRuldQuiznum14());
				nameString= ruld2.getRuldName14();
			}
			if(strType[i].equals("1_m")){
				selSubject.setPage(ruld2.getRuldQuiznum1M());
				nameString= ruld2.getRuldName1M();
			}
			if(strType[i].equals("1_5m")){
				selSubject.setPage(ruld2.getRuldQuiznum15M());
				nameString= ruld2.getRuldName15M();
			}
			if(strType[i].equals("2")){
				selSubject.setPage(ruld2.getRuldQuiznum2());
				nameString= ruld2.getRuldName2();
			}
			if(strType[i].equals("3")){
				selSubject.setPage(ruld2.getRuldQuiznum3());
				nameString= ruld2.getRuldName3();
			}
			if(strType[i].equals("4")){
				selSubject.setPage(ruld2.getRuldQuiznum4());
				nameString= ruld2.getRuldName4();
			}
			selSubject.setSubjectType(strType[i]);
			List<Subject> subjects = subjectService.selectByCourseId(selSubject);
			if(subjects.size()!=selSubject.getPage()){
				out.print("<script>alert('"+nameString+"题库数量不足，保存失败！')</script>");
				out.print("<script>window.location.href='queryPapers?courseId="+p.getCourseId()+"&syllabusId="+syllabusId+"'</script>");
				out.flush();
				return null;
			}
		}


		for (int i = 0; i < strType.length; i++)
		{
			if(strType[i].equals("1_3")){
				selSubject.setPage(ruld2.getRuldQuiznum13());

			}
			if(strType[i].equals("1_4")){
				selSubject.setPage(ruld2.getRuldQuiznum14());

			}
			if(strType[i].equals("1_m")){
				selSubject.setPage(ruld2.getRuldQuiznum1M());

			}
			if(strType[i].equals("1_5m")){
				selSubject.setPage(ruld2.getRuldQuiznum15M());

			}
			if(strType[i].equals("2")){
				selSubject.setPage(ruld2.getRuldQuiznum2());

			}
			if(strType[i].equals("3")){
				selSubject.setPage(ruld2.getRuldQuiznum3());

			}
			if(strType[i].equals("4")){
				selSubject.setPage(ruld2.getRuldQuiznum4());

			}
			selSubject.setSubjectType(strType[i]);
			List<Subject> subjects = subjectService.selectByCourseId(selSubject);
			for(int j = 0 ; j<subjects.size();j++){
				Papers pp = new Papers();
				pp.setCourseId(p.getCourseId());
				pp.setPapersAt(new Date());
				pp.setPapersDel(1);
				pp.setPapersId(Tools.getGUID());
				pp.setPapersThe(sum);
				pp.setRuldId(p.getRuldId());
				pp.setSubjectId(subjects.get(j).getSubjectId());
				papersService.insertSelective(pp);

			}
		}

		out.print("<script>alert('保存成功')</script>");
		out.print("<script>window.location.href='queryPapers?courseId="+p.getCourseId()+"&syllabusId="+syllabusId+"'</script>");
		out.flush();
		return null;
	}



	/**
	 * 生成PDF
	 * @param p
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/JkglViewPDF", method = RequestMethod.GET)
	public String JkglViewPDF(Papers p, HttpServletRequest request, HttpServletResponse response)
	{
		Ruld ruld2 =ruldService.selectByPrimaryKey(p.getRuldId());
		List<Papers> plist= papersService.MyPapersQuery(p);
		String[] strType =
			{ "1_3", "1_4", "1_5m","1_m", "2", "3", "4" };


		File file = new File(request.getSession().getServletContext().getRealPath("/pdf"));
		String path = new java.util.Date().getTime() + "Jkgl.pdf";
		File file1 = null;
		file1 = new File(file + "\\" + path);
		if (!file.exists())
		{
			file.mkdirs();
			if (!file1.exists())
			{
				try
				{
					file1.createNewFile();
				} catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		try
		{
			Document doc = new Document(PageSize.A4);
			PdfWriter.getInstance(doc, new FileOutputStream(file1));
			doc.open();
			// 标题字体
			BaseFont bf = BaseFont.createFont(request.getSession().getServletContext().getRealPath("/pdfFonts") + "\\simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font titleFont = new Font(bf, 18, Font.BOLD);
			Paragraph titleP = new Paragraph(ruld2.getRuldName(), titleFont);
			titleP.setAlignment(Paragraph.ALIGN_CENTER);
			doc.add(titleP);
			// 内容字体
			BaseFont bfComic = BaseFont.createFont(request.getSession().getServletContext().getRealPath("/pdfFonts") + "\\simkai.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			Font font = new Font(bfComic, 9, Font.NORMAL);
			doc.add(new Paragraph("答题说明:", font));
			doc.add(new Paragraph("1. 试卷总分"+ruld2.getRuldTotalscore(), font));
			doc.add(new Paragraph("2. "+ruld2.getRuldNote(), font));
			doc.add(new Paragraph("\n" , font));


			for (int i = 0; i < strType.length; i++)
			{
				int a=1;
				if(strType[i].equals("1_3")){
					if(ruld2.getRuldQuiznum13()!=null && ruld2.getRuldQuiznum13()!=0){
						doc.add(new Paragraph(ruld2.getRuldName13()+"每题"+ruld2.getRuldScore13()+"分", font));
					}
				}
				if(strType[i].equals("1_4")){
					if(ruld2.getRuldQuiznum14()!=null && ruld2.getRuldQuiznum14()!=0){
						doc.add(new Paragraph(ruld2.getRuldName14()+"每题"+ruld2.getRuldScore14()+"分", font));
					}
				}
				if(strType[i].equals("1_5m")){
					if(ruld2.getRuldQuiznum15M()!=null && ruld2.getRuldQuiznum15M()!=0){
						doc.add(new Paragraph(ruld2.getRuldName15M()+"每题"+ruld2.getRuldScore15M()+"分", font));
					}
				}
				if(strType[i].equals("1_m")){
					if(ruld2.getRuldQuiznum1M()!=null && ruld2.getRuldQuiznum1M()!=0){
						doc.add(new Paragraph(ruld2.getRuldName1M()+"每题"+ruld2.getRuldScore1M()+"分", font));
					}
				}
				if(strType[i].equals("2")){
					if(ruld2.getRuldQuiznum2()!=null && ruld2.getRuldQuiznum2()!=0){
						doc.add(new Paragraph(ruld2.getRuldName2()+"每题"+ruld2.getRuldScore2()+"分", font));
					}
				}
				if(strType[i].equals("3")){
					if(ruld2.getRuldQuiznum3()!=null && ruld2.getRuldQuiznum3()!=0){
						doc.add(new Paragraph(ruld2.getRuldName3()+"每题"+ruld2.getRuldScore3()+"分", font));
					}
				}
				if(strType[i].equals("4")){
					if(ruld2.getRuldQuiznum4()!=null && ruld2.getRuldQuiznum4()!=0){
						doc.add(new Paragraph(ruld2.getRuldName4()+"每题"+ruld2.getRuldScore4()+"分", font));
					}
				}
				for(int j = 0 ; j<plist.size();j++){
					Papers pp = plist.get(j);
					if(strType[i].equals(pp.getSubject().getSubjectType())){//晒出试题
						if(strType[i].equals("1_3")){
							doc.add(new Paragraph((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "")  , font));
							doc.add(new Paragraph("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("\n" , font));
						}
						if(strType[i].equals("1_4")){
							doc.add(new Paragraph((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "")  , font));
							doc.add(new Paragraph("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("D、"+pp.getSubject().getSubjectD().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("\n" , font));
						}
						if(strType[i].equals("1_5m")){
							doc.add(new Paragraph((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "")  , font));
							doc.add(new Paragraph("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("D、"+pp.getSubject().getSubjectD().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("E、"+pp.getSubject().getSubjectE().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("\n" , font));
						}
						if(strType[i].equals("1_m")){
							doc.add(new Paragraph((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "")  , font));
							doc.add(new Paragraph("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("D、"+pp.getSubject().getSubjectD().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("E、"+pp.getSubject().getSubjectE().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("F、"+pp.getSubject().getSubjectF().replace("<p>", "").replace("</p>", "") , font));
							doc.add(new Paragraph("\n" , font));
						}
						if(strType[i].equals("2")){
							doc.add(new Paragraph((a++) + "、"+pp.getSubject().getSubjectTopic()  , font));
							doc.add(new Paragraph("YES" , font));
							doc.add(new Paragraph("NO" , font));
							doc.add(new Paragraph("\n" , font));
						}
						if(strType[i].equals("3")){
							doc.add(new Paragraph((a++) + "、"+pp.getSubject().getSubjectTopic().replace("$()$", "______").replace("<p>", "").replace("</p>", "")  , font));
							doc.add(new Paragraph("\n" , font));
						}
						if(strType[i].equals("4")){
							doc.add(new Paragraph((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "")  , font));
							doc.add(new Paragraph("\n" , font));

						}

					}
				}
			}
			doc.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return "pdf/" + path;
	}



	/**
	 * 生成Word
	 * @param p
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping(value = "/JkglViewWord", method = RequestMethod.GET)
	public void JkglViewWord(Papers p, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Ruld ruld2 =ruldService.selectByPrimaryKey(p.getRuldId());
		List<Papers> plist= papersService.MyPapersQuery(p);
		String[] strType =
			{ "1_3", "1_4", "1_5m","1_m", "2", "3", "4" };

		String path = request.getSession().getServletContext().getRealPath("/") + "pdf\\";
		String filename  =  new java.util.Date().getTime() + "Word.doc";
		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph p1 = doc.createParagraph();

		// 设置字体对齐方式
		p1.setAlignment(ParagraphAlignment.CENTER);
		p1.setVerticalAlignment(TextAlignment.TOP);
		// 第一页要使用p1所定义的属性
		XWPFRun r1 = p1.createRun();
		// 设置字体是否加粗
		r1.setBold(true);
		r1.setFontSize(20);
		// 设置使用何种字体
		r1.setFontFamily("Courier");
		// 设置上下两行之间的间距
		r1.setTextPosition(20);
		r1.setText(ruld2.getRuldName());//标题

		// 存放试题信息
		XWPFParagraph p3 = doc.createParagraph();
		p3.setWordWrap(true);
		XWPFRun r3 = p3.createRun();
		r3.setTextPosition(10);
		r3.setText("答题说明:");
		r3.addCarriageReturn();
		r3.setText("1. 试卷总分"+ruld2.getRuldTotalscore());
		r3.addCarriageReturn();
		r3.setText("2. "+ruld2.getRuldNote());


		for (int i = 0; i < strType.length; i++)
		{
			int a=1;
			if(strType[i].equals("1_3")){
				if(ruld2.getRuldQuiznum13()!=null && ruld2.getRuldQuiznum13()!=0){
					// 存放试题信息
					XWPFParagraph p8 = doc.createParagraph();
					p8.setWordWrap(true);
					XWPFRun r8 = p8.createRun();
					r8.setTextPosition(10);
					r8.setFontSize(15);
					r8.setText(ruld2.getRuldName13()+"每题"+ruld2.getRuldScore13()+"分");
					r8.addCarriageReturn();
				}
			}
			if(strType[i].equals("1_4")){
				if(ruld2.getRuldQuiznum14()!=null && ruld2.getRuldQuiznum14()!=0){
				// 存放试题信息
				XWPFParagraph p8 = doc.createParagraph();
				p8.setWordWrap(true);
				XWPFRun r8 = p8.createRun();
				r8.setTextPosition(10);
				r8.setFontSize(15);
				r8.setText(ruld2.getRuldName14()+"每题"+ruld2.getRuldScore14()+"分");
				r8.addCarriageReturn();
				}
			}
			if(strType[i].equals("1_5m")){
				if(ruld2.getRuldQuiznum15M()!=null && ruld2.getRuldQuiznum15M()!=0){
				// 存放试题信息
				XWPFParagraph p8 = doc.createParagraph();
				p8.setWordWrap(true);
				XWPFRun r8 = p8.createRun();
				r8.setTextPosition(10);
				r8.setFontSize(15);
				r8.setText(ruld2.getRuldName15M()+"每题"+ruld2.getRuldScore15M()+"分");
				r8.addCarriageReturn();
				}
			}
			if(strType[i].equals("1_m")){
				if(ruld2.getRuldQuiznum1M()!=null && ruld2.getRuldQuiznum1M()!=0){
				// 存放试题信息
				XWPFParagraph p8 = doc.createParagraph();
				p8.setWordWrap(true);
				XWPFRun r8 = p8.createRun();
				r8.setTextPosition(10);
				r8.setFontSize(15);
				r8.setText(ruld2.getRuldName1M()+"每题"+ruld2.getRuldScore1M()+"分");
				r8.addCarriageReturn();
				}
			}
			if(strType[i].equals("2")){
				if(ruld2.getRuldQuiznum2()!=null && ruld2.getRuldQuiznum2()!=0){
				// 存放试题信息
				XWPFParagraph p8 = doc.createParagraph();
				p8.setWordWrap(true);
				XWPFRun r8 = p8.createRun();
				r8.setTextPosition(10);
				r8.setFontSize(15);
				r8.setText(ruld2.getRuldName2()+"每题"+ruld2.getRuldScore2()+"分");
				r8.addCarriageReturn();
				}
			}
			if(strType[i].equals("3")){
				if(ruld2.getRuldQuiznum3()!=null && ruld2.getRuldQuiznum3()!=0){
				// 存放试题信息
				XWPFParagraph p8 = doc.createParagraph();
				p8.setWordWrap(true);
				XWPFRun r8 = p8.createRun();
				r8.setTextPosition(10);
				r8.setFontSize(15);
				r8.setText(ruld2.getRuldName3()+"每题"+ruld2.getRuldScore3()+"分");
				r8.addCarriageReturn();
				}
			}
			if(strType[i].equals("4")){
				if(ruld2.getRuldQuiznum4()!=null && ruld2.getRuldQuiznum4()!=0){
				// 存放试题信息
				XWPFParagraph p8 = doc.createParagraph();
				p8.setWordWrap(true);
				XWPFRun r8 = p8.createRun();
				r8.setTextPosition(10);
				r8.setFontSize(15);
				r8.setText(ruld2.getRuldName4()+"每题"+ruld2.getRuldScore4()+"分");
				r8.addCarriageReturn();
				}
			}
			for(int j = 0 ; j<plist.size();j++){
				Papers pp = plist.get(j);
				if(strType[i].equals(pp.getSubject().getSubjectType())){//晒出试题
					if(strType[i].equals("1_3")){
						// 题目和选项
						XWPFParagraph p4 = doc.createParagraph();
						p4.setWordWrap(true);
						XWPFRun r4 = p4.createRun();
						r4.setTextPosition(13);
						r4.setText((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
					}
					if(strType[i].equals("1_4")){
						// 题目和选项
						XWPFParagraph p4 = doc.createParagraph();
						p4.setWordWrap(true);
						XWPFRun r4 = p4.createRun();
						r4.setTextPosition(13);
						r4.setText((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", ""));
						r4.addCarriageReturn();
						r4.setText("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("D、"+pp.getSubject().getSubjectD().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
					}
					if(strType[i].equals("1_5m")){
						// 题目和选项
						XWPFParagraph p4 = doc.createParagraph();
						p4.setWordWrap(true);
						XWPFRun r4 = p4.createRun();
						r4.setTextPosition(13);
						r4.setText((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("D、"+pp.getSubject().getSubjectD().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("E、"+pp.getSubject().getSubjectE().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
					}
					if(strType[i].equals("1_m")){
						// 题目和选项
						XWPFParagraph p4 = doc.createParagraph();
						p4.setWordWrap(true);
						XWPFRun r4 = p4.createRun();
						r4.setTextPosition(13);
						r4.setText((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("A、"+pp.getSubject().getSubjectA().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("B、"+pp.getSubject().getSubjectB().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("C、"+pp.getSubject().getSubjectC().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("D、"+pp.getSubject().getSubjectD().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("E、"+pp.getSubject().getSubjectE().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("F、"+pp.getSubject().getSubjectF().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();

					}
					if(strType[i].equals("2")){
						// 题目和选项
						XWPFParagraph p4 = doc.createParagraph();
						p4.setWordWrap(true);
						XWPFRun r4 = p4.createRun();
						r4.setTextPosition(13);
						r4.setText((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "") );
						r4.addCarriageReturn();
						r4.setText("YES" );
						r4.addCarriageReturn();
						r4.setText("NO" );
						r4.addCarriageReturn();

					}
					if(strType[i].equals("3")){
						// 题目和选项
						XWPFParagraph p4 = doc.createParagraph();
						p4.setWordWrap(true);
						XWPFRun r4 = p4.createRun();
						r4.setTextPosition(13);
						r4.setText((a++) + "、"+pp.getSubject().getSubjectTopic().replace("$()$", "______").replace("<p>", "").replace("</p>", "")  );
					}
					if(strType[i].equals("4")){
						// 题目和选项
						XWPFParagraph p4 = doc.createParagraph();
						p4.setWordWrap(true);
						XWPFRun r4 = p4.createRun();
						r4.setTextPosition(13);
						r4.setText((a++) + "、"+pp.getSubject().getSubjectTopic().replace("<p>", "").replace("</p>", "") );
						r4.setText("\n" );

					}

				}
			}
		}
		FileOutputStream out = new FileOutputStream(path+filename);
		doc.write(out);
		out.close();
		System.out.println("success");

		downLoad(path+filename, response, false);  
	}

	/**
	 * 在线下载
	 * @param filePath 
	 * @param response 
	 * @param isOnLine
	 * @throws Exception
	 */
	public void downLoad(String filePath, HttpServletResponse response,  
			boolean isOnLine) throws Exception {  
		File f = new File(filePath);  
		/*  
		 * if (!f.exists()) { response.sendError(404, "File not found!");  
		 * return; }  
		 */  
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));  
		byte[] buf = new byte[1024];  
		int len = 0;  
		response.reset(); // 非常重要  
		// 在线打开方式  
		if (isOnLine) {  
			URL u = new URL(filePath);  
			response.setContentType(u.openConnection().getContentType());  
			response.setHeader("Content-Disposition", "inline; filename="  
					+ toUTF8(f.getName()));  
			// 文件名应该编码成UTF-8  
		}  
		// 纯下载方式  
		else {  
			response.setContentType("application/x-msdownload");  
			response.setHeader("Content-Disposition", "attachment; filename="  
					+ toUTF8(f.getName()));  
		}  
		OutputStream out = response.getOutputStream();  
		while ((len = br.read(buf)) > 0)  
			out.write(buf, 0, len);  
		out.flush();  
		br.close();  
		out.close();  
	}  

	// UTF-8编码  
	public String toUTF8(String s) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < s.length(); i++) {  
			char c = s.charAt(i);  
			if (c >= 0 && c <= 255) {  
				sb.append(c);  
			} else {  
				byte[] b;  
				try {  
					b = Character.toString(c).getBytes("utf-8");  
				} catch (Exception ex) {  
					System.out.println(ex);  
					b = new byte[0];  
				}  
				for (int j = 0; j < b.length; j++) {  
					int k = b[j];  
					if (k < 0)  
						k += 256;  
					sb.append("%" + Integer.toHexString(k).toUpperCase());  
				}  
			}  
		}  
		return sb.toString();  
	}  





}
