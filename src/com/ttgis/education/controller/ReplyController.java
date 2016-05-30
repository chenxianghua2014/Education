package com.ttgis.education.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttgis.education.entity.QueryReply;
import com.ttgis.education.entity.Reply;
import com.ttgis.education.entity.Student;
import com.ttgis.education.service.ReplyService;
import com.ttgis.education.utils.FriendlyTime;
import com.ttgis.education.utils.RandomGUIDUtil;
import com.ttgis.education.utils.WriteLog;

@Controller
/*@RequestMapping(value = "reply")*/
public class ReplyController extends WriteLog
{
	@Resource
	ReplyService replyService;

	/**
	 * 回复BBS
	 * 
	 * @param reply
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/doreply", method = RequestMethod.POST)
	public Reply doreply(Reply reply, HttpSession session)
	{
		logInfo("doreply", "");
		Reply insertReply = reply;
		Student student = (Student) session.getAttribute("Student");
		insertReply.setStudentId(student.getStudentId());
		insertReply.setReplyAt(new Date());
		insertReply.setReplyDel(new Long(1));
		insertReply.setReplyId(RandomGUIDUtil.generateKey());
		Reply selrReply = replyService.selectLevel(reply.getReplyPid());
		if (selrReply != null)
		{
			selrReply = replyService.selectByPrimaryKey(reply.getReplyPid());
			insertReply.setReplyPid(selrReply.getReplyPid());
		}

		int r = replyService.insertSelective(insertReply);

		if (r > 0)
			reply = replyService.selectByPrimaryKey(reply.getReplyId());
		else
			reply = null;

		return reply;
	}

	/**
	 * 回复我的帖子
	 * 
	 * @param model
	 * @param queryBbs
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/replytome", method = RequestMethod.GET)
	public String replytome(Model model, QueryReply queryReply, HttpSession session)
	{
		logInfo("replytome", "");
		Student student = (Student) session.getAttribute("Student");
		queryReply.setStudentId(student.getStudentId());
		List<Reply> replies1 = replyService.listPageToMeInfo(queryReply);
		for (Reply reply : replies1)
		{
			reply.setFriendlyAddtime(FriendlyTime.friendly_time(reply.getReplyAt()));
		}
		List<Reply> replies2 = replyService.meToInfo(student.getStudentId());
		for (Reply reply : replies2)
		{
			reply.setFriendlyAddtime(FriendlyTime.friendly_time(reply.getReplyAt()));
		}
		model.addAttribute("replies1", replies1);
		model.addAttribute("replies2", replies2);
		model.addAttribute("replies3", replies2);
		model.addAttribute("page", queryReply);
		return "bbs/replytome";
	}

}
