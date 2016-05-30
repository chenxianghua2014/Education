package com.ttgis.education.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttgis.education.entity.Student;
import com.ttgis.education.entity.Statistical;
import com.ttgis.education.service.StatisticalService;
import com.ttgis.education.utils.RandomGUIDUtil;

@Controller
/*@RequestMapping(value = "statistical")*/
public class StatisticalController
{
	@Resource
	StatisticalService statisticalService;

	/**
	 * 回复BBS
	 * 
	 * @param reply
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/praise", method = RequestMethod.POST)
	public Statistical statistical(Statistical statistical, HttpSession session)
	{
		Student account = (Student) session.getAttribute("Student");
		statistical.setStudentId(account.getStudentId());
		if (statistical.getStatisticalType().toString().equals("-1"))
		{
			statisticalService.cancelPraise(statistical);
		} else
		{
			statistical.setStatisticalAt(new Date());
			statistical.setStatisticalDel(new Long(1));
			statistical.setStatisticalId(RandomGUIDUtil.generateKey());
			statisticalService.insertSelective(statistical);
		}
		return statistical;
	}
}
