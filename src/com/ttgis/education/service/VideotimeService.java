package com.ttgis.education.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ttgis.education.entity.Videotime;
import com.ttgis.education.mapper.VideotimeMapper;

@Repository
@Service
public class VideotimeService {

	@Resource
	private VideotimeMapper videotimeMapper;


	public int deleteByPrimaryKey(String videotimeId) {
		return videotimeMapper.deleteByPrimaryKey(videotimeId);
	}

	public int insert(Videotime record) {
		return videotimeMapper.insert(record);
	}

	public  int insertSelective(Videotime record) {
		return videotimeMapper.insertSelective(record);
	}

	public Videotime selectByPrimaryKey(String videotimeId) {
		return videotimeMapper.selectByPrimaryKey(videotimeId);
	}

	public int updateByPrimaryKeySelective(Videotime record) {
		return videotimeMapper.updateByPrimaryKeySelective(record);
	}

	public  int updateByPrimaryKey(Videotime record) {
		return videotimeMapper.updateByPrimaryKey(record);
	}

	/**
	 * 陈健龙
	 * 通过学员ID、视频ID查询出观看次数
	 * @param record
	 * @return
	 */
	public List<Videotime> videtimeByCIdSId(Videotime record) {
		return videotimeMapper.videtimeByCIdSId(record);
	}

	/**
	 * 陈健龙
	 * 通过标记查询访问次数
	 * @param record
	 * @return
	 */
	public  int videtimeByMark(Videotime record) {
		return videotimeMapper.videtimeByMark(record);
	}

	/**
	 * 陈健龙
	 * 通过使用标记修改使用完成后的删除标记
	 * @param record
	 * @return
	 */
	public  int updateByMark(Videotime record) {
		return videotimeMapper.updateByMark(record);
	}
	   /**
     * 王艳鹏
     * 记录开始时间和结束时间
     * @param videotimeId
     * @return
     */
	public List<Videotime> timeByMark(Videotime record) {
		return videotimeMapper.timeByMark(record);
	}
	   /**
     * 王艳鹏
     * 通过标记查询访问
     * @param videotimeId
     * @return
     */
	public List<Videotime> videtimeByMarkList(Videotime record) {
		return videotimeMapper.videtimeByMarkList(record);
	}

}
