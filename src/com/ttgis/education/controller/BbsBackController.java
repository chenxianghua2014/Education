package com.ttgis.education.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ttgis.education.entity.Account;
import com.ttgis.education.entity.Bbs;
import com.ttgis.education.entity.Board;
import com.ttgis.education.entity.Reply;
import com.ttgis.education.entity.page.PageBean;
import com.ttgis.education.service.BbsService;
import com.ttgis.education.service.BoardService;
import com.ttgis.education.service.ReplyService;
import com.ttgis.education.service.StatisticalService;
import com.ttgis.education.utils.Tools;
import com.ttgis.education.utils.WriteLog;
@Controller

public class BbsBackController extends WriteLog
{
	@Resource
	BbsService bbsService;
	@Resource
	BoardService boardService;
	@Resource
	ReplyService replyService;
	@Resource
	StatisticalService statisticsService;
	
	private Board board;
	
	private Bbs bbs;
	
	private Reply reply;

	/**
	 * 论坛板块列表（后台）
	 * @return
	 * @throws 
	 */
	@RequestMapping(value = "/queryBoardByPage")
	public ModelAndView queryBoardByPage(String examine,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		Board board = new Board();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		//---------------------条件查询----------------------------------
		if(!"test001".equals(acc.getAccountCatalog())){
			board.setBoardCatalog(acc.getAccountCatalog());
		}
		board.setBoardExamine(examine);
		PageBean pageBean = new PageBean();
		int allRow = boardService.boardCount(board);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		board.setPage(length*(currentPage1-1));
		board.setSize(length);

		List<Board> list = boardService.queryBoardByPage(board);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryBoardByPage?examine="+examine);//连接地址
		pageBean.init();
		mv.addObject("boardCatalog",acc.getAccountCatalog());
		mv.addObject("accountType",acc.getAccountType());
		mv.addObject("examine",examine);
		mv.addObject("pageBean",pageBean);
		mv.setViewName("bbs/queryBoardByPage");
		return  mv;
	}
	
	/**
	 * 修改添加跳转页
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/boardJump")
	public ModelAndView boardJump(String boardId,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
			if(boardId.equals("0")){
				mv.setViewName("bbs/addBoard");
			}else{
				board = boardService.selectByPrimaryKey(boardId);
				mv.addObject("board",board);
				mv.setViewName("bbs/updateBoard");
			}
		
		return mv;
	}
	
	/**
	 * 版块保存
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/saveBoard", method = RequestMethod.POST)
	public ModelAndView saveBoard(Board board,HttpSession session) throws Exception{
		Account acc = (Account)session.getAttribute("sessionUser");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int sug = 0;
		board.setBoardCatalog(acc.getAccountCatalog());
		board.setBoardId(Tools.getGUID());  
		board.setBoardMaster(acc.getAccountLoginid());
		board.setBoardAt(sdf.format(new Date()));
		board.setBoardExamine("0");
		board.setBoardDel("1");
		sug=boardService.insertSelective(board);

		if(sug==1){
			return new ModelAndView("redirect:/queryBoardByPage?page=1");
		} else {
			return new ModelAndView("");
		}
	}
	/**
	 * 版块修改保存 （后台）
	 * 
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/updateBoard", method = RequestMethod.POST)
	public ModelAndView updateBoard(Board board) {
		int sug = 0;
		board.setBoardExamine("0");
		sug=boardService.updateByPrimaryKeySelective(board);

		if(sug==1){
			return new ModelAndView("redirect:/queryBoardByPage?examine=0&page=1");
		} else {
			return new ModelAndView("");
		}
	}
	
	/**
	 * 删除版块信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBoard", method = RequestMethod.GET)
	public String deleteBoard(String boardId ) {
		int sug = 0;
		if (boardId!=null) {
			board = boardService.selectByPrimaryKey(boardId);
			board.setBoardDel("0");
			sug=boardService.updateByPrimaryKeySelective(board);
		} else {
			return "success";
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}
	
	/**
	 * 审核版块信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/examineBoard", method = RequestMethod.GET)
	public String examineBoard(String boardId ) {
		int sug = 0;
		if (boardId!=null) {
			board = boardService.selectByPrimaryKey(boardId);
			board.setBoardExamine("1");
			sug=boardService.updateByPrimaryKeySelective(board);
		} else {
			return "success";
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}
	
	/**
	 * 论坛bbs列表（后台）
	 * @return
	 * @throws 
	 */
	@RequestMapping(value = "/queryBbsByPage")
	public ModelAndView queryBbsByPage(String boardId,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		Bbs bbs = new Bbs();
		//---------------------条件查询----------------------------------
		Account acc = (Account)session.getAttribute("sessionUser");
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
		pageBean.setUrl("queryBbsByPage?boardId="+boardId);//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.addObject("accountType",acc.getAccountType());
		mv.setViewName("bbs/queryBbsByPage");
		return  mv;
	}
	
	/**
	 * 删除bbs信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteBbs", method = RequestMethod.GET)
	public String deleteBbs(String bbsId ) {
		int sug = 0;
		if (bbsId!=null) {
			bbs = bbsService.selectByPrimaryKey(bbsId);
			bbs.setBbsDel(new Long(0));
			sug=bbsService.updateByPrimaryKeySelective(bbs);
		} else {
			return "success";
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}
	/**
	 * 查bbs信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	
	@RequestMapping(value = "/queryBbsById", method = RequestMethod.GET)
	public ModelAndView queryBbsById(String bbsId, Model model) {
		bbs = bbsService.selectByPrimaryKey(bbsId);
		model.addAttribute("bbs", bbs);
		return new ModelAndView("bbs/queryBbsById");
	}
	
	/**
	 * 主贴回复贴列表（后台）
	 * @return
	 * @throws 
	 */
	@RequestMapping(value = "/queryReplyByBbsId")
	public ModelAndView queryReplyByBbsId(String bbsId,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		Reply reply = new Reply();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		//---------------------条件查询----------------------------------
		
		reply.setBbsId(bbsId);
		PageBean pageBean = new PageBean();
		int allRow = replyService.replyCount(reply);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		reply.setPage(length*(currentPage1-1));
		reply.setSize(length);

		List<Reply> list = replyService.queryReplyByPage(reply);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryReplyByBbsId?bbsId="+bbsId);//连接地址
		pageBean.init();
		mv.addObject("accountType",acc.getAccountType());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("bbs/queryReplyByBbsId");
		return  mv;
	}
	
	/**
	 * 查回复信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	
	@RequestMapping(value = "/queryReplyByReplyId", method = RequestMethod.GET)
	public ModelAndView queryReplyByReplyId(String replyId, Model model) {
		reply = replyService.selectByPrimaryKey(replyId);
		model.addAttribute("reply", reply);
		return new ModelAndView("bbs/queryReplyByReplyId");
	}
	/**
	 * 删除回复信息（后台）
	 * 
	 * @param newsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteReply", method = RequestMethod.GET)
	public String deleteReply(String replyId ) {
		int sug = 0;
		if (replyId!=null) {
			reply =replyService.selectByPrimaryKey(replyId);
			reply.setReplyDel(new Long(0));
			sug=replyService.updateByPrimaryKeySelective(reply);
		} else {
			return "success";
		}
		if(sug==1){
			return "ok";
		} else {
			return "no";
		}

	}
	/**
	 * 回复列表（后台）
	 * @return
	 * @throws 
	 */
	@RequestMapping(value = "/queryReplyByReplyPid")
	public ModelAndView queryReplyByReplyPid(String replyPid,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		Reply reply = new Reply();
		Account acc = (Account)session.getAttribute("sessionUser");
		
		//---------------------条件查询----------------------------------
		
		reply.setReplyPid(replyPid);
		PageBean pageBean = new PageBean();
		int allRow = replyService.replyCountByReplyPid(reply);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		reply.setPage(length*(currentPage1-1));
		reply.setSize(length);

		List<Reply> list = replyService.queryReplyByReplyPid(reply);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryReplyByReplyPid?replyPid="+replyPid);//连接地址
		pageBean.init();
		mv.addObject("accountType",acc.getAccountType());
		mv.addObject("pageBean",pageBean);
		mv.setViewName("bbs/queryReplyByReplyPid");
		return  mv;
	}
	
}
