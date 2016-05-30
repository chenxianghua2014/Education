package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.QueryReply;
import com.ttgis.education.entity.Reply;
import com.ttgis.education.mapper.ReplyMapper;

@Repository
@Service
public class ReplyService
{

	@Resource
	private ReplyMapper replyMapper;

	public int insertSelective(Reply reply)
	{
		return replyMapper.insertSelective(reply);
	}

	public int updateByPrimaryKeySelective(Reply reply)
	{
		return replyMapper.updateByPrimaryKeySelective(reply);
	}

	public int deleteByPrimaryKey(String replyId)
	{
		return replyMapper.deleteByPrimaryKey(replyId);
	}

	public Reply selectLevel(String replyId)
	{
		return replyMapper.selectLevel(replyId);
	}

	public Reply selectByPrimaryKey(String replyId)
	{
		return replyMapper.selectByPrimaryKey(replyId);
	}

	public List<Reply> listPageInfo(QueryReply queryReply)
	{
		return replyMapper.listPageInfo(queryReply);
	}

	public List<Reply> listPageToMeInfo(QueryReply queryReply)
	{
		return replyMapper.listPageToMeInfo(queryReply);
	}

	public List<Reply> meToInfo(String accountId)
	{
		return replyMapper.meToInfo(accountId);
	}

	public List<Reply> selectSubReply(QueryReply queryReply)
	{
		return replyMapper.selectSubReply(queryReply);
	}

	public List<Reply> selectByAccountId(String accountId)
	{
		return replyMapper.selectByAccountId(accountId);
	}
	/*主贴回复分页显示 */
	/**
     * 总数
     * @return
     */
	public int replyCount(Reply reply)
	{
		return replyMapper.replyCount(reply);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Reply> queryReplyByPage(Reply reply)
	{
		return replyMapper.queryReplyByPage(reply);
	}
	/*回复贴分页显示 */
	/**
     * 总数
     * @return
     */
	public int replyCountByReplyPid(Reply reply)
	{
		return replyMapper.replyCountByReplyPid(reply);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Reply> queryReplyByReplyPid(Reply reply)
	{
		return replyMapper.queryReplyByReplyPid(reply);
	}
	
}
