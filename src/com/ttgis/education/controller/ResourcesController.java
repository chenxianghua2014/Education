package com.ttgis.education.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.Knowledge;
import com.ttgis.education.entity.KnowledgeResource;
import com.ttgis.education.entity.Msg;
import com.ttgis.education.entity.Notice;
import com.ttgis.education.entity.Record;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.KnowledgeResourceService;
import com.ttgis.education.service.KnowledgeService;
import com.ttgis.education.service.RecordService;
import com.ttgis.education.utils.RandomGUIDUtil;
import com.ttgis.education.utils.Tools;


/**
 * 知识资源浏览
 * <p>
 * Title:CourseController
 * </p>
 * 
 * @author 李慕阳 2015-8-12
 */

@Controller
public class ResourcesController implements ServletContextAware{
	
	@Resource
	private KnowledgeService knowledgeService;//知识资源
	@Resource
	private KnowledgeResourceService knowledgeResourceService;//知识资源目录
	@Resource
	private RecordService recordService;
	private ServletContext servletContext;
	private Knowledge KnowledgeG;
	/**
	 * 
	 * 李慕阳  获得知识资源目录树
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ResourceTree", method = RequestMethod.POST)
	public Msg ResourceTree(HttpSession session)
	{	
		KnowledgeResource s = new KnowledgeResource();
		List<KnowledgeResource> knowledgeResource = new ArrayList<KnowledgeResource>();
		Account acc = (Account) session.getAttribute("sessionUser");
		if(acc.getAccountType()==1){
			knowledgeResource = knowledgeResourceService.selectknowledgeResource(s);
		}else{
			
			s.setOrganizationId(acc.getAccountCatalog());
			knowledgeResource = knowledgeResourceService.selectknowledgeResource(s);
		}


		return new Msg(acc.getAccountType().toString(), knowledgeResource, Msg.SUCCESS);
	}
	
	
	/**
	 * 
	 * 李慕阳  根据知识资源目录id 查询目录下的资源
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/Resourcelist")
	public ModelAndView Resourcelist(Knowledge knowledge,int page,int mark)
	{	
		//去资源目录权限
		int ksum = knowledgeResourceService.selectByPrimaryKey(knowledge.getKnowledgeResourceId()).getKnowledgeResourceRank();
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			KnowledgeG =new Knowledge();
		}
		if(knowledge.getKnowledgeName()!=null){
			KnowledgeG.setKnowledgeName(knowledge.getKnowledgeName());
		}
		KnowledgeG.setKnowledgeResourceId(knowledge.getKnowledgeResourceId());
		PageBean pageBean = new PageBean();
		int allRow = knowledgeService.selectBywaiKeyCount(KnowledgeG);
		final int length = 10;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理
		KnowledgeG.setPage(length*(currentPage1-1));
		KnowledgeG.setSize(length);
		List<Knowledge> knowledgelist=knowledgeService.selectBywaiKey(KnowledgeG);
		
		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(knowledgelist);
		pageBean.setUrl("Resourcelist?mark=0&knowledgeResourceId="+knowledge.getKnowledgeResourceId());//连接地址
		pageBean.init();
		mv.addObject("KnowledgeG",KnowledgeG);
		mv.addObject("pageId",knowledge.getKnowledgeResourceId());
		mv.addObject("pageBean",pageBean);
		
		mv.addObject("ksum",ksum);
		mv.addObject("knowledgelist",knowledgelist);
		mv.addObject("knowledgeResourceId",knowledge.getKnowledgeResourceId());
		mv.setViewName("backResources/resourcesList");
		return mv;
	}
	/**
	 * 
	 * 李慕阳  模糊查询 查询目录下的资源
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping(value = "/Resourcelist1", method = RequestMethod.POST)
	public ModelAndView Resourcelist1(Knowledge knowledge)
	{	
		ModelAndView mv = new ModelAndView();
		List<Knowledge> knowledgelist=knowledgeService.selectBywaiKey(knowledge);
		mv.addObject("knowledgelist",knowledgelist);
		mv.addObject("knowledgeResourceId",knowledge.getKnowledgeResourceId());
		mv.setViewName("backResources/resourcesList");
		return mv;
	}*/
	/**
	 * 李慕阳 添加 资源目录树节点。
	 * 
	 * @param syllabus
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addResourceTreeNode", method = RequestMethod.POST)
	public Msg addResourceTreeNode(KnowledgeResource knowledgeResource, HttpSession session)
	{
		int ch =  0;
		Account acc = (Account) session.getAttribute("sessionUser");
		KnowledgeResource kr  = knowledgeResourceService.selectByPrimaryKey(knowledgeResource.getKnowledgeResourceUpid());
		if(acc.getAccountType()==1){
			if(kr.getOrganizationId().equals(acc.getAccountCatalog()) && !kr.getKnowledgeResourceId().equals("2")){
				ch=1;
			}
		}else{
			if(kr.getOrganizationId().equals(acc.getAccountCatalog()) || kr.getKnowledgeResourceId().equals("2")){
				ch=1;
			}
		}
		if(ch!=0){
			knowledgeResource.setOrganizationId(acc.getAccountCatalog());
			knowledgeResource.setKnowledgeResourceAt(new Date());
			knowledgeResource.setKnowledgeResourceDel(1);
			knowledgeResource.setKnowledgeResourceId(RandomGUIDUtil.generateKey());
			int r = knowledgeResourceService.insert(knowledgeResource);
			return new Msg(String.valueOf(r), knowledgeResource, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}
	
	/**
	 * 李慕阳 修改 资源目录树节点名字。
	 * 
	 * @param syllabus
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/updateResourceTreeNode", method = RequestMethod.POST)
	public Msg updateResourceTreeNode(KnowledgeResource knowledgeResource, HttpSession session)
	{
		int ch =  0;
		Account acc = (Account) session.getAttribute("sessionUser");
		KnowledgeResource kr  = knowledgeResourceService.selectByPrimaryKey(knowledgeResource.getKnowledgeResourceId());
		if(acc.getAccountType()==1){
			if(kr.getOrganizationId().equals(acc.getAccountCatalog()) && !kr.getKnowledgeResourceId().equals("2")){
				ch=1;
			}
		}else{
			if(kr.getOrganizationId().equals(acc.getAccountCatalog()) || kr.getKnowledgeResourceId().equals("2")){
				ch=1;
			}
		}
		if(ch!=0){
			
			int r = knowledgeResourceService.updateByPrimaryKeySelective(knowledgeResource);
			return new Msg(String.valueOf(r), knowledgeResource, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}
	
	/**
	 * 李慕阳 删除资源目录树节点。
	 * 
	 * @param syllabus
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/delResourceTreeNode", method = RequestMethod.POST)
	public Msg delResourceTreeNode(KnowledgeResource knowledgeResource, HttpSession session)
	{
		int ch =  0;
		Account acc = (Account) session.getAttribute("sessionUser");
		KnowledgeResource kr  = knowledgeResourceService.selectByPrimaryKey(knowledgeResource.getKnowledgeResourceId());
		if(acc.getAccountType()==1){
			if(kr.getOrganizationId().equals(acc.getAccountCatalog()) && !kr.getKnowledgeResourceId().equals("2")){
				ch=1;
			}
		}else{
			if(kr.getOrganizationId().equals(acc.getAccountCatalog()) || kr.getKnowledgeResourceId().equals("2")){
				ch=1;
			}
		}
		if(ch!=0){
			int r = knowledgeResourceService.deleteByPrimaryKey(knowledgeResource.getKnowledgeResourceId());
			return new Msg(String.valueOf(r), knowledgeResource, Msg.SUCCESS);
		}else{
			return new Msg("权限不够", "权限不够", Msg.ERROR);
		}
	}
	/**
	 * 资源添加跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/ResourceADDto")
	public ModelAndView ResourceADDto(String knowledgeResourceId,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("knowledgeResourceId",knowledgeResourceId);
		mv.setViewName("backResources/resourceADD");
		return mv;
	}
	/**
	 * 资源添加
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/ResourceADD",method = RequestMethod.POST)
	public ModelAndView ResourceADD(Knowledge k,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account) session.getAttribute("sessionUser");
		k.setKnowledgeId(RandomGUIDUtil.generateKey());
		int i=knowledgeService.insert(k);
		if(i==1){
			Record record = new Record();
			record.setRecordAt(new Date());
			if(acc.getAccountCatalog().equals("test001")){
				record.setRecordClass("共享资源");
			}else {
				record.setRecordClass("单位资源");
			}
			
			record.setRecordContentid(k.getKnowledgeId());
			record.setRecordDel(1);
			record.setRecordId(Tools.getGUID());
			record.setRecordNote(acc.getAccountName()+"上传资源"+k.getKnowledgeName());
			record.setRecordTitle("资源上传");
			if(acc.getAccountCatalog().equals("test001")){
				record.setRecordType("3");
			}else {
				record.setRecordType("4");
			}
			
			record.setStudentId(acc.getAccountId());
			recordService.insertSelective(record);
		}
		mv.setViewName("redirect:Resourcelist?page=1&mark=1&knowledgeResourceId="+k.getKnowledgeResourceId());
		return mv;
	}
	/**
	 * 资源删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/DeleteResources",method = RequestMethod.GET)
	public ModelAndView DeleteResources(Knowledge k) {
		ModelAndView mv = new ModelAndView();
		Knowledge knowledge=knowledgeService.selectByPrimaryKey(k.getKnowledgeId());
		knowledgeService.deleteByPrimaryKey(k.getKnowledgeId());
		mv.setViewName("redirect:Resourcelist?mark=0&page=1&knowledgeResourceId="+knowledge.getKnowledgeResourceId());
		return mv;
	}
	/**
	 * 资源修改页跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/UpdataResourcesTo",method = RequestMethod.GET)
	public ModelAndView UpdataResourcesTo(Knowledge k) {
		ModelAndView mv = new ModelAndView();
		Knowledge knowledge=knowledgeService.selectByPrimaryKey(k.getKnowledgeId());
		mv.addObject("knowledge",knowledge);
		mv.setViewName("backResources/resourcesUpdata");
		return mv;
	}
	/**
	 * 资源修改
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/UpdataResources",method = RequestMethod.POST)
	public ModelAndView UpdataResources(Knowledge k) {
		ModelAndView mv = new ModelAndView();
		Knowledge knowledge=knowledgeService.selectByPrimaryKey(k.getKnowledgeId());
		knowledgeService.updateByPrimaryKeySelective(k);
		mv.setViewName("redirect:Resourcelist?mark=0&page=1&knowledgeResourceId="+knowledge.getKnowledgeResourceId());
		return mv;
	}
	
/*----------------------------------------------------前台方法------------------------------------------------------=*/
	/**
	 * 
	 * 李慕阳  前台获得知识资源目录树
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ResourceWebTree")
	public ModelAndView ResourceWebTree(HttpSession session,int page)
	{	
		
		ModelAndView mv = new ModelAndView();
		Student s = (Student) session.getAttribute("Student");
		if(s==null){
			mv.setViewName("redirect:mainout");
			return  mv;
		}
		KnowledgeResource k = new KnowledgeResource();
		List<KnowledgeResource> knowledgeResource = new ArrayList<KnowledgeResource>();
		List<Knowledge> knowledge = new ArrayList<Knowledge>();
		
		PageBean pageBean = new PageBean();
		int allRow = 0;
		int totalPage=0;
		final int length = 15;//每页记录数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理
		k.setPage(length*(currentPage1-1));
		k.setSize(length);
		
		
		if(s.getOrganization().getOrganizationFwqx().equals("1")){
			knowledgeResource = knowledgeResourceService.selectknowledgeResource(k);
			knowledge=knowledgeService.selectQTknowledge(k);
			allRow = knowledgeService.selectQTknowledgeCount(k);
			totalPage = PageBean.countTotalPage(length, allRow);//总页数
		}else{
			
			k.setOrganizationId(s.getStudentCompanyid());
			knowledgeResource = knowledgeResourceService.selectknowledgeResource(k);
			knowledge=knowledgeService.selectQTknowledge(k);
			allRow = knowledgeService.selectQTknowledgeCount(k);
			totalPage = PageBean.countTotalPage(length, allRow);//总页数
		}
		
		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(knowledge);
		pageBean.setUrl("ResourceWebTree?");//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		
		mv.addObject("knowledgeResource",knowledgeResource);
		mv.addObject("knowledge",knowledge);
		mv.setViewName("web/ResourceWebTree");
		return mv;
	}	
	/**
	 * 
	 * 李慕阳  前台根据知识资源目录id 查询目录下的资源
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/ResourceWeblist")
	public ModelAndView ResourceWeblist(HttpSession session,Knowledge k,int page)
	{	
		ModelAndView mv = new ModelAndView();
		KnowledgeResource kr = new KnowledgeResource();
		List<KnowledgeResource> knowledgeResource = new ArrayList<KnowledgeResource>();
		List<Knowledge> knowledge = new ArrayList<Knowledge>();
		
		PageBean pageBean = new PageBean();
		int allRow = 0;
		int totalPage=0;
		final int length = 15;//每页记录数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理
		k.setPage(length*(currentPage1-1));
		k.setSize(length);
		
		Student s = (Student) session.getAttribute("Student");
		if(s.getOrganization().getOrganizationFwqx().equals("1")){
			knowledgeResource = knowledgeResourceService.selectknowledgeResource(kr);
			knowledge=knowledgeService.selectBywaiKey(k);
			allRow = knowledgeService.selectBywaiKeyCount(k);
			totalPage = PageBean.countTotalPage(length, allRow);//总页数
		}else{
			
			kr.setOrganizationId(s.getStudentCompanyid());
			knowledgeResource = knowledgeResourceService.selectknowledgeResource(kr);
			knowledge=knowledgeService.selectBywaiKey(k);
			allRow = knowledgeService.selectBywaiKeyCount(k);
			totalPage = PageBean.countTotalPage(length, allRow);//总页数
		}
		
		pageBean.setPageSize(length);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(knowledge);
		pageBean.setUrl("ResourceWeblist?knowledgeResourceId="+k.getKnowledgeResourceId());//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		
		mv.addObject("knowledgeResource",knowledgeResource);
		mv.addObject("knowledge",knowledge);
		mv.setViewName("web/ResourceWebTree");
		return mv;
	}
	/**
	 * 
	 * 李慕阳  前台下载/播放视频
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/selectResourcesOne", method = RequestMethod.GET)
	public ModelAndView selectResourcesOne(HttpSession session,Knowledge k,HttpServletResponse response)
	{	
		ModelAndView mv = new ModelAndView();
		Knowledge knowledge = knowledgeService.selectResourcesOne(k);
		String[] s=knowledge.getKnowledgePath().split("\\.");
		if(s[1].equals("doc")||s[1].equals("docx")||s[1].equals("pdf")||s[1].equals("ppt")||s[1].equals("pptx")){
			//文档文件下载
			fileDownload(response, knowledge);
			System.out.println("文档文件下载");
			mv.setViewName("redirect:ResourceWebTree?page=1");
			return mv;
		}else {
			//视频文件播放
			
			System.out.println("视频文件播放");
			mv.addObject("knowledge",knowledge);
			mv.setViewName("web/ResourceWebVideo");
			return mv;
		}
		
	}
/*——————————————————————————————————————————————————————————————————————————————————————————————————————————————*/
	/***
	 * 上传文件返回文件名到上传的页面，
	 * @param request
	 * @param mode
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/shangchuan", method = RequestMethod.POST)
	public String uploadphoto(HttpServletRequest request) {
		String realPath = request.getSession().getServletContext().getRealPath("/UploadFile");
		File files = new File(realPath);
		// 一级一级创建文件夹
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory()) {
			System.out.println("//不存在");
			files.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		realPath = realPath + "\\QYZM";
		File fileones = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!fileones.exists() && !fileones.isDirectory()) {
			System.out.println("//不存在");
			fileones.mkdir();
		} else {
			System.out.println("//目录存在");
		}
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		MultipartFile multipartFile = null;
		String fileName = null;
		for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
//			String filekey = set.getKey();// Filedata
			multipartFile = set.getValue();// 文件名
		}
		fileName = ResourcesController.storeIOc(realPath, multipartFile);
		//变化路径   格式化路径
		String realPaths = realPath + "\\" + fileName;
		System.out.println(realPaths);
		realPaths = realPaths.replace("\\", "/");
		System.out.println(realPaths);
		String change = request.getSession().getServletContext().getRealPath("/");
		change = change.replace("\\", "/");
		realPaths = realPaths.replace(change,"" );
		System.out.println(realPaths);
		return realPaths;
	}
	
	/***
	 * 保存图片，返回图片地址
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
		// 文件名称
		String fileName = "";
		String logImageName = "";
		// file调用getOriginalFilename获取完成文件名
		String _fileName = file.getOriginalFilename();
		// 用文件名截取最后一个.后面的字符当作扩展名
		String suffix = _fileName.substring(_fileName.lastIndexOf("."));
		/** 使用UUID生成文件名称 **/
		logImageName = UUID.randomUUID().toString().replace("-", "") + suffix;

		fileName = realPath + File.separator + logImageName;
		File restore = new File(fileName);
		try
		{
			file.transferTo(restore);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		// 返回默认的图片地址
		return logImageName;
	}
	//文件下载
	public void fileDownload(HttpServletResponse response,Knowledge k){  
        //获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
        String path = servletContext.getRealPath("/");  
  
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        response.setContentType("multipart/form-data");  
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        response.setHeader("Content-Disposition", "attachment;fileName="+k.getKnowledgeName()+"."+k.getKnowledgePath().split("\\.")[1]);  
        ServletOutputStream out;  
        //通过文件路径获得File对象(假如此路径中有一个download.pdf文件)  
        File file = new File(path + k.getKnowledgePath());  
  
        try {  
            FileInputStream inputStream = new FileInputStream(file);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = response.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[1024];  
            while (b != -1){  
                b = inputStream.read(buffer);  
                //4.写到输出流(out)中  
                out.write(buffer,0,b);  
            }  
            inputStream.close();  
            out.close();  
            out.flush();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void setServletContext(ServletContext servletContext) {  
        this.servletContext = servletContext;  
    }  
	
}
