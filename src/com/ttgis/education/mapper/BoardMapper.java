package com.ttgis.education.mapper;

import java.util.List;
import java.util.Map;

import com.ttgis.education.entity.Board;

public interface BoardMapper
{
	int deleteByPrimaryKey(String boardId);

	int insert(Board record);

	int insertSelective(Board record);

	Board selectByPrimaryKey(String boardId);

	int updateByPrimaryKeySelective(Board record);

	int updateByPrimaryKey(Board record);

	List<Board> selectByWhere(Map<String, String> map);
	
	/**
     * 总数
     * @return
     */
	int boardCount(Board board);
	/**
     * 分页查询
     * @return
     */
	List<Board> queryBoardByPage(Board board);
}