package com.ttgis.education.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Classresource;
import com.ttgis.education.entity.Log;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.Organization;
import com.ttgis.education.entity.Shift;
import com.ttgis.education.entity.Tranclass;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.AccountService;
import com.ttgis.education.service.ClassresourceService;
import com.ttgis.education.service.LogService;
import com.ttgis.education.service.OrganizationService;
import com.ttgis.education.service.TranclassService;
import com.ttgis.education.service.ShiftService;
import com.ttgis.education.utils.DataBaseUtil;
import com.ttgis.education.utils.HYLUtil;
import com.ttgis.education.utils.MD5;
import com.ttgis.education.utils.Tools;
/**
 * 权限关联控制层
 * @author 陈健龙
 *
 */
@Controller
public class AccountController {
	
	@Resource
	private AccountService accountService;
	
	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private ClassresourceService classresourceService;
	
	@Resource
	private TranclassService  tranclassService;
	
	@Resource
	private ShiftService ShiftService;
	
	@Resource
	LogService logService;
	Log log = new Log();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private Account account ;
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(AccountController.class);
	
	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		return new ModelAndView("admin/login");
	}
	
	/** 
     * 备份数据库 
     * @return backup 
     * @throws Exception 
     */ 
	@ResponseBody
	@RequestMapping(value = "/backup", method = RequestMethod.GET)
public String backup(HttpServletRequest request) { 
	String realPath = request.getSession().getServletContext()
			.getRealPath("/UploadBak");
        String name = "EDUVIDEOS"; //数据库名  
        Date nowTime=new Date(); 
		SimpleDateFormat time=new SimpleDateFormat("yyyyMMddHHmmss");
		String times = time.format(nowTime).toString();
		String names = name+times;
        try {  
        	File file = new File(realPath);
        	if (!file.exists() && !file.isDirectory()) {
        		System.out.println("//不存在");
        		file.mkdir();
        	} else {
        		System.out.println("//目录存在");
        	} 
            String path = file.getPath() + "\\" + names + ".bak";// name文件名  
            String bakSQL = "backup database " + name + " to disk=? with init";// SQL语句  
            PreparedStatement bak = DataBaseUtil.getConnection()  
                    .prepareStatement(bakSQL);  
            System.out.println(bak);
            bak.setString(1, path);// path必须是绝对路径  
            System.out.println(bak);
            bak.execute(); // 备份数据库  
            bak.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "ok";  
    } 
	
	
	 /** 
     * 数据库还原 
     * @return recovery 
     */  
	@ResponseBody
	@RequestMapping(value = "/recovery", method = RequestMethod.GET)
    public String recovery(HttpServletRequest request) {  
    	String realPath = request.getSession().getServletContext()
    			.getRealPath("/UploadBak");
        String name = "******";  
        String dbname = "******"; 
        String nameis = "EDUVIDEO"; //数据库名 
        try {  
            File file = new File(realPath);
        	if (!file.exists() && !file.isDirectory()) {
        		System.out.println("//不存在");
        		file.mkdir();
        	} else {
        		System.out.println("//目录存在");
        	} 
            String path = file.getPath() + "\\" + name + ".bak";// name文件名  
            String recoverySql = "ALTER   DATABASE  " + nameis + "  SET   ONLINE   WITH   ROLLBACK   IMMEDIATE";// 恢复所有连接  
              
            PreparedStatement ps = DataBaseUtil.getConnection()  
                    .prepareStatement(recoverySql);  
            CallableStatement cs = DataBaseUtil.getConnection().prepareCall("{call killrestore(?,?)}");  
                cs.setString(1, dbname); // 数据库名  
                cs.setString(2, path); // 已备份数据库所在路径  
                cs.execute(); // 还原数据库  
                ps.execute(); // 恢复数据库连接          
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "recovery";  
    }  
	
	
	
	/**
	 * 后台账号分页查询
	 * @return
	 */
	@RequestMapping(value = "/accountAll")
	public ModelAndView accountAll(int mark ,Account a,int page,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(mark==1){
			account = new Account();
		}
		if(a.getAccountLoginid()!=null&&a.getAccountLoginid()!=""){
			account.setAccountLoginid(a.getAccountLoginid());
		}
		
		
		Account acc = (Account)session.getAttribute("sessionUser");
		account.setAccountType(acc.getAccountType());
		account.setAccountCatalog(acc.getAccountCatalog());
		PageBean pageBean = new PageBean();
		int allRow = accountService.AccountCount(account);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		account.setPage(length*(currentPage1-1));
		account.setSize(length);

		List<Account> list = accountService.selectPageAll(account);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("accountAll?mark=0");//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.addObject("account",a);
		mv.setViewName("account/queryAccount");
		return  mv;
	}
	
	
	/**
	 * 后台账号查询
	 * @return
	 */
	@RequestMapping(value = "/accountOnes")
	public ModelAndView accountOnes(Account a){
		ModelAndView mv = new ModelAndView();
		List<Account> list = accountService.AccountByOId(a.getAccountCatalog());
		List<Account> sjlist = accountService.AccountByOSJId(a.getAccountCatalog());
		mv.addObject("sjlist",sjlist);
		mv.addObject("list",list);
		mv.addObject("id",a.getAccountCatalog());
		mv.setViewName("account/AccountOnes");
		return  mv;
	}
	
	
	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/AccountskipID")
	public ModelAndView AccountskipID(String id,HttpSession session,HttpServletRequest request) {
		Account acc = (Account)session.getAttribute("sessionUser");
		ModelAndView mv = new ModelAndView();
		mv.addObject("rk",acc.getAccountType());
		Account p = new Account();
		if(id.equals("0")){
			mv.setViewName("account/addAccount");
		}else{
			p.setAccountId(id);
			p = accountService.AccountById(p);
			mv.addObject("account",p);
			mv.addObject("id",request.getParameter("oid"));
			mv.setViewName("account/updateAccount");
		}

		return mv;
	}
	
	/**
	 * 添加帐户信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/createAccount")
	public ModelAndView createAccount(Account p,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		//时间转换
		p.setAccountPassword(MD5.getMD5ofStr(p.getAccountPassword()));
		p.setAccountAt(new Date());
		if(p.getAccountType()==1){
			p.setAccountCatalog("test001");
		}
		p.setAccountId(Tools.getGUID());
		p.setAccountDel(1);
		int i = accountService.insertSelective(p);
		if(i>0){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，创建账号:"+p.getAccountId());
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			if(p.getAccountType()==4){
				for(int j =0 ; j<p.getBc().length;j++){
					Shift ss = new Shift();
					ss.setShiftId(Tools.getGUID());
					ss.setAccountId(p.getAccountId());
					ss.setTranclassId(p.getBc()[j]);
					ShiftService.insertSelective(ss);
				}
				
			}
			//mv.setViewName("redirect:accountAll?page=1&mark=1");
			mv.setViewName("redirect:accountTree");
		}else{
			mv.setViewName("account/addAccount");
		}


		return mv;
	}
	
	/**
	 * 修改账户信息
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateAccount")
	public ModelAndView updateAccount(Account a,String id,HttpSession session,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Account acc = (Account)session.getAttribute("sessionUser");
		
//		if(a.getAccountType()==5){
//			a.setAccountCatalog(" ");
//		}
		int i = accountService.updateByPrimaryKeySelective(a);
		if(i>0){ 
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，修改账号:"+id);
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			
//			ShiftService.deleteByPrimaryKey(a.getAccountId());
//			if(a.getAccountType()==4){
//				for(int j =0 ; j<a.getBc().length;j++){
//					Shift ss = new Shift();
//					ss.setShiftId(Tools.getGUID());
//					ss.setAccountId(a.getAccountId());
//					ss.setTranclassId(a.getBc()[j]);
//					ShiftService.insertSelective(ss);
//				}
//				
//			}
			//mv.setViewName("redirect:accountAll?mark=1&page=1");
			mv.setViewName("redirect:accountOnes?accountCatalog="+id);
		}else{
			mv.addObject("account",a);
			mv.setViewName("account/updateAccount");
		}


		return mv;
	}
	
	/**
	 * 删除（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.GET)
	public String deleteAccount(String id,HttpSession session,HttpServletRequest request) {
		Message msg = new Message();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		int sug = 0;
		if (id!=null) {
			ShiftService.deleteByPrimaryKey(id);
			sug = accountService.deleteByPrimaryKey(id);
		} else {
			msg.setMessage("删除失败");
			return ("success");
		}
		if(sug==1){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，删除账号:"+id);
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			return "ok";
		} else {
			return "no";
		}

	}
	
	/**
	 * 密码重置
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/passwordAccount", method = RequestMethod.GET)
	public String passwordAccount(String id,HttpSession session,HttpServletRequest request) {
		Message msg = new Message();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		Account a = new Account();
		int sug = 0;
		a.setAccountId(id);
		a.setAccountPassword(MD5.getMD5ofStr("123456"));
		if (id!=null) {
			sug = accountService.updateByPrimaryKeySelective(a);
		} else {
			msg.setMessage("重置失败");
			return ("success");
		}
		if(sug==1){
			log.setLogAt(sdf.format(new Date()));
			log.setLogContent("用户："+acc.getAccountLoginid()+"，重置密码,账号:"+id);
			log.setLogId(Tools.getGUID());
			log.setLogIp(HYLUtil.getIP4(request));
			log.setLogUser(acc.getAccountLoginid());
			log.setLogUserType("后台用户");
			logService.insertSelective(log);
			return "ok";
		} else {
			return "no";
		}

	}
	
	
	


	
	
	
	
	
	//-----------------------------------验证-----------------------------------------------------
	
	/**
	 * 添加账号验证
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/accountNameVerify", method = RequestMethod.GET)
	public String accountNameVerify(Account a) {
		Account a1 = new Account();
		Account a2 = new Account();
		Account a3 = new Account();
		a1 = accountService.AccountByName(a.getAccountLoginid());
		a3 = accountService.AccountBySFZH(a.getAccountSfzh());
		if(a1==null){
			if(a3==null){
				a2 = accountService.AccountByOName(a.getAccountCatalog());
				if(a2==null){
					return "ok";
				}else{
					return "no";
				}
			}else{
				return "sfzhno";
			}
		}else{
			return "nameno";
		}
	}
	
	
	
	
	
	//-----------------------------------------------------联动-------------------------------------------------------------------
	
	
	/**
	 * 非培训专员组织机构联动
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/typel",method=RequestMethod.POST)
	public ModelAndView typel(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException{
		Account acc = (Account)session.getAttribute("sessionUser");
		Organization o = new Organization();
		o.setOrganizationOrder(acc.getAccountType());
		if(acc.getAccountType()==1||acc.getAccountType()==5){
			o.setOrganizationId(request.getParameter("id"));
		}else if(acc.getAccountType()==2){
			o.setOrganizationId(acc.getAccountCatalog());
		}else if(acc.getAccountType()==3){
			o.setOrganizationId(acc.getAccountCatalog());
			o.setOrganizationOrder(2);
		}
		
		List<Organization> list = organizationService.organizationByID(o);
		
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
	/**
	 * 培训专员组织机构联动
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/typel1",method=RequestMethod.POST)
	public ModelAndView typel1(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Organization o = new Organization();
		o.setOrganizationOrder(1);
		o.setOrganizationId(request.getParameter("id"));
		List<Organization> list = organizationService.organizationByID(o);
		Organization ou = organizationService.organizationMyID(o);
		list.add(ou);
		Collections.reverse(list);
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
	/**
	 * 第2级组织机构联动 
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/DCtypel",method=RequestMethod.POST)
	public ModelAndView DCtypel(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Organization o = new Organization();
		o.setOrganizationOrder(1);
		o.setOrganizationId(request.getParameter("id"));
		List<Organization> list = organizationService.organizationByID(o);
		
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
	/**
	 * 培训班
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/BCtypel",method=RequestMethod.POST)
	public ModelAndView BCtypel(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Classresource o = new Classresource();
		o.setOrganizationId(request.getParameter("id"));
		List<Classresource> list = classresourceService.classresourceByOID(o);
		
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
	/**
	 * 班次
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/BCtypel1",method=RequestMethod.POST)
	public ModelAndView BCtypel1(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Tranclass o = new Tranclass();
		Shift ss = new Shift();
		String uid = request.getParameter("uid");
		o.setClassresourceId(request.getParameter("id"));
		List<Tranclass> list = tranclassService.TranclassByOId(o);
		if(!uid.equals("0")){
			ss.setAccountId(uid);
			List<Shift> sslist = ShiftService.ShiftByOId(ss);
			for(int i = 0 ;i<list.size();i++){
				for(int j = 0 ; j<sslist.size();j++){
					if(list.get(i).getTranclassId().equals(sslist.get(j).getTranclassId())){
						list.get(i).setTranclassType("selected");
					}
				}
			}
		}
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
	
	/**
	 * 非培训专员反向查询组织机构联动
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/fxtypel",method=RequestMethod.POST)
	public ModelAndView fxtypel(HttpServletResponse response,HttpServletRequest request,HttpSession session) throws IOException{
		Organization o = new Organization();
		Account acc = (Account)session.getAttribute("sessionUser");
		o.setOrganizationOrder(acc.getAccountType());
		if(acc.getAccountType()==1||acc.getAccountType()==5){
			o.setOrganizationId("test001");
		}else if(acc.getAccountType()==2){
			o.setOrganizationId(acc.getAccountCatalog());
		}else if(acc.getAccountType()==3){
			o.setOrganizationId(acc.getAccountCatalog());
		}
		
		List<Organization> list = new ArrayList<Organization>();
		List<Organization> list1 = organizationService.organizationByID(o);
		
		o.setOrganizationId(request.getParameter("id"));
		Organization ou = organizationService.organizationMyID(o);
		String oid = ou.getOrganizationSjdw();
		for(int i=0;i<list1.size();i++){
			if(oid.equals(list1.get(i).getOrganizationId())||ou.getOrganizationId().equals(list1.get(i).getOrganizationId())){
				o = list1.get(i);
			}else{
				list.add(list1.get(i));
			}
		}
		if(o.getOrganizationDwmc()!=null){
			list.add(o);
		}
		if(o.getOrganizationId().equals("test001")){
			list.add(ou);
		}
		Collections.reverse(list);
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
	
	/**
	 * 反向培训班
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/FXBCtypel")
	public ModelAndView FXBCtypel(HttpServletResponse response,HttpServletRequest request) throws IOException{
		Classresource o = new Classresource();
		
		o.setOrganizationId(request.getParameter("id"));
		List<Classresource> list = new ArrayList<Classresource>();
		List<Classresource> list1 = classresourceService.classresourceByOID(o);
		
		String bid = request.getParameter("bid");
		
		for(int i = 0 ;i<list1.size();i++){
			if(list1.get(i).getClassresourceId().equals(bid)){
				o=list1.get(i);
			}else{
				list.add(list1.get(i));
			}
		}
		list.add(o);
		Collections.reverse(list);
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		System.out.println(js);
		

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		out.println(json);
		return null;
	}
	
}
