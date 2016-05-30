package com.ttgis.education.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Bbs;
import com.ttgis.education.entity.Board;
import com.ttgis.education.entity.Message;
import com.ttgis.education.entity.QueryBbs;
import com.ttgis.education.entity.QueryReply;
import com.ttgis.education.entity.Reply;
import com.ttgis.education.entity.Statistical;
import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.BbsService;
import com.ttgis.education.service.BoardService;
import com.ttgis.education.service.ReplyService;
import com.ttgis.education.service.StatisticalService;
import com.ttgis.education.utils.CompresPic;
import com.ttgis.education.utils.FriendlyTime;
import com.ttgis.education.utils.WriteLog;
import com.ttgis.education.utils.RandomGUIDUtil;

@Controller
/*@RequestMapping(value = "bbs")*/
public class BbsController extends WriteLog
{
	@Resource
	BbsService bbsService;
	@Resource
	BoardService boardService;
	@Resource
	ReplyService replyService;
	@Resource
	StatisticalService statisticsService;

	@RequestMapping(value = "board", method = RequestMethod.GET)
	public String board(Model model)
	{
		logInfo("board", "");
		List<Board> boards = boardService.selectByWhere(null);
		model.addAttribute("boards", boards);
		return "bbs/board";
	}

	/**
	 * BBS主页
	 * 
	 * @param model
	 * @param page
	 * @return
	 */
	
	@RequestMapping(value = "/bbs")
	public ModelAndView bbs(String boardId,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		Bbs bbs = new Bbs();
		//---------------------条件查询----------------------------------
		bbs.setBoardId(boardId);
		PageBean pageBean = new PageBean();
		int allRow = bbsService.bbsCount(bbs);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		bbs.setPage(length*(currentPage1-1));
		bbs.setSize(length);

		List<Bbs> list = bbsService.queryBbsByPage(bbs);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("bbs?boardId="+boardId);//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.addObject("boardId",boardId);
		mv.setViewName("bbs/bbs");
		return  mv;
	}
	
	
	/**
	 * 我的BBS
	 * 
	 * @param model
	 * @param queryBbs
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/mybbs", method = RequestMethod.GET)
	public String mybbs(Model model, QueryBbs queryBbs, HttpSession session)
	{
		logInfo("mybbs", "");
		Student student = (Student) session.getAttribute("Student");
		queryBbs.setStudentId(student.getStudentId());
		List<Bbs> bbss = bbsService.listPageInfo(queryBbs);
		for (Bbs bbs : bbss)
		{
			bbs.setFriendlyAddtime(FriendlyTime.friendly_time(bbs.getBbsAt()));
		}
		model.addAttribute("bbss", bbss);
		model.addAttribute("page", queryBbs);
		return "bbs/mybbs";
	}

	/**
	 * 详情
	 * 
	 * @param model
	 * @param bbsId
	 * @return
	 */
	@RequestMapping(value = "/bbsdetails", method = RequestMethod.GET)
	public String bbsdetails(Model model, String bbsId, QueryReply queryReply, HttpSession session)
	{
		
		Student student = (Student) session.getAttribute("Student");
		String studentId = "";
		Map<String, String> map = new HashMap<String, String>();
		map.put("bbsId", bbsId);
		if (student != null)
			studentId = student.getStudentId();

		Statistical statistics = new Statistical();
		statistics.setStatisticalAt(new Date());
		statistics.setStatisticalDel(new Long(1));
		statistics.setStatisticalId(RandomGUIDUtil.generateKey());
		statistics.setBbsId(bbsId);
		statistics.setStatisticalType(new Long(0));
		statisticsService.insertSelective(statistics);

		map.put("studentId", studentId);
		Bbs bbs = bbsService.selectDetailsByPrimaryKey(map);
		// 查询留言
		queryReply.setBbsId(bbsId);
		queryReply.setStudentId(studentId);
		// 一级留言
		List<Reply> replies1 = replyService.listPageInfo(queryReply);
		for (Reply reply : replies1)
		{
			reply.setFriendlyAddtime(FriendlyTime.friendly_time(reply.getReplyAt()));
		}
		// 二级留言
		List<Reply> replies2 = new ArrayList<Reply>();
		replies2 = replyService.selectSubReply(queryReply);
		for (Reply reply : replies2)
		{
			reply.setFriendlyAddtime(FriendlyTime.friendly_time(reply.getReplyAt()));
		}

		model.addAttribute("bbs", bbs);
		model.addAttribute("replies1", replies1);
		model.addAttribute("replies2", replies2);
		model.addAttribute("replies3", replies2);
		model.addAttribute("account", student);
		model.addAttribute("page", queryReply);
		return "bbs/bbsdetails";
	}

	/**
	 * 发帖页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/writebbs", method = RequestMethod.GET)
	public ModelAndView writebbs(String boardId)
	{
		ModelAndView mv = new ModelAndView();
		logInfo("bbs/writebbs", "");
		mv.addObject("boardId",boardId);
		mv.setViewName("bbs/writebbs");
		return mv;
	}

	/**
	 * 保存帖子
	 * 
	 * @param bbs
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/savebbs", method = RequestMethod.POST)
	public ModelAndView savebbs(Bbs bbs, HttpSession session)
	{
		logInfo("savebbs", JSONArray.fromObject(bbs).toString());
		Student student = (Student) session.getAttribute("Student");
		bbs.setStudentId(student.getStudentId());
		bbs.setBbsAt(new Date());
		bbs.setBbsDel(new Long(1));
		bbs.setBbsId(RandomGUIDUtil.generateKey());

		CompresPic mypic = new CompresPic();
		System.out.println(session.getServletContext().getRealPath("/"));

		// 为移动端类似朋友圈功能做缩略图
		String regEx = "src=\"([^\"]+)\"";
		Pattern pat = Pattern.compile(regEx);
		Matcher m1 = pat.matcher(bbs.getBbsContent());
		Matcher m2 = pat.matcher(bbs.getBbsContent());
		int i = 0;
		while (m1.find())
		{
			String src = m1.group().substring(5, m1.group().length() - 1);
			String[] srcSplit = src.split("\\.");
			if (srcSplit[srcSplit.length - 1].toString().toLowerCase().indexOf("gif") == -1)
			{
				i++;
			}
		}
		int j = 0;
		String strCover = "";
		while (m2.find())
		{
			String src = m2.group().substring(5, m2.group().length() - 1);
			String[] srcSplit = src.split("\\.");
			String filename = src.split("/")[src.split("/").length - 1];
			String path = session.getServletContext().getRealPath("/").replace("education\\", "") + src.replace(filename, "").replace("../", "");

			if (srcSplit[srcSplit.length - 1].toString().toLowerCase().indexOf("gif") == -1)
			{
				if (i == 1)
				{
					mypic.compressPic(path, path + "one", filename, filename, 143, 143, false);
					strCover += src.replace(filename, "") + "one/" + filename + ",";
				} else if (i == 2 || i == 3)
				{
					if (j == 2)
						break;
					mypic.compressPic(path, path + "two", filename, filename, 69, 143, false);
					strCover += src.replace(filename, "") + "two/" + filename + ",";
				} else if (i > 3)
				{
					if (j == 4)
						break;
					mypic.compressPic(path, path + "four", filename, filename, 69, 69, false);
					strCover += src.replace(filename, "") + "four/" + filename + ",";
				}
			}
			j++;
		}
		/*if (strCover.length() > 1)
			bbs.setBbsCover(strCover.substring(0, strCover.length() - 1));*/

		bbsService.insertSelective(bbs);
		Message message = new Message();
		message.setMessage("操作成功!");
		message.setUri("bbs?page=1&boardId="+bbs.getBoardId());
		return new ModelAndView("success", "message", message);
	}
}
