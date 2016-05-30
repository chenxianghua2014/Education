package com.ttgis.education.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Board;
import com.ttgis.education.mapper.BoardMapper;

@Repository
@Service
public class BoardService
{

	@Resource
	private BoardMapper boardMapper;

	public int insertSelective(Board Board)
	{
		return boardMapper.insertSelective(Board);
	}

	public int updateByPrimaryKeySelective(Board Board)
	{
		return boardMapper.updateByPrimaryKeySelective(Board);
	}

	public int deleteByPrimaryKey(String boardId)
	{
		return boardMapper.deleteByPrimaryKey(boardId);
	}

	public Board selectByPrimaryKey(String boardId)
	{
		return boardMapper.selectByPrimaryKey(boardId);
	}

	public List<Board> selectByWhere(Map<String, String> map)
	{
		return boardMapper.selectByWhere(map);
	}
	
	/**
     * 总数
     * @return
     */
	public int boardCount(Board board)
	{
		return boardMapper.boardCount(board);
	}
	/**
     * 分页查询
     * @return
     */
	public List<Board> queryBoardByPage(Board board)
	{
		return boardMapper.queryBoardByPage(board);
	}
	
}
