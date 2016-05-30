package com.ttgis.education.mapper;

import java.util.List;

import com.ttgis.education.entity.QueryReply;
import com.ttgis.education.entity.Reply;

public interface ReplyMapper
{
	int deleteByPrimaryKey(String replyId);

	int insert(Reply record);

	int insertSelective(Reply record);

	Reply selectByPrimaryKey(String replyId);

	Reply selectLevel(String replyId);

	int updateByPrimaryKeySelective(Reply record);

	int updateByPrimaryKey(Reply record);

	List<Reply> listPageInfo(QueryReply queryReply);

	List<Reply> listPageToMeInfo(QueryReply queryReply);

	List<Reply> meToInfo(String accountId);

	List<Reply> selectSubReply(QueryReply queryReply);

	List<Reply> selectByAccountId(String accountId);
	
	/*主贴回复分页显示*/
	/**
     * 总数
     * @return
     */
	int replyCount(Reply reply);
	/**
     * 分页查询
     * @return
     */
	List<Reply> queryReplyByPage(Reply reply);
	
	/*回复贴分页显示*/
	/**
     * 总数
     * @return
     */
	int replyCountByReplyPid(Reply reply);
	/**
     * 分页查询
     * @return
     */
	List<Reply> queryReplyByReplyPid(Reply reply);
}