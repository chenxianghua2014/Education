package com.ttgis.education.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.ttgis.education.entity.Log;

import com.ttgis.education.entity.page.PageBean;

import com.ttgis.education.service.LogService;

import com.ttgis.education.utils.WriteLog;
@Controller

public class LogController extends WriteLog
{
	@Resource
	LogService logService;
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(LogController.class);
	
	/**
	 * 论坛板块列表（后台）
	 * @return
	 * @throws 
	 */
	@RequestMapping(value = "/queryLogByPage")
	public ModelAndView queryLogByPage(Log l,int page,HttpSession session) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		Log log = new Log();
		
		//---------------------条件查询----------------------------------
		if(l.getLogAt()!=null && l.getLogAt()!="" ){
			log.setLogAt(l.getLogAt());
		}
		if(l.getLogContent()!=null && l.getLogContent()!="" ){
			log.setLogContent(l.getLogContent());
		}
		if(l.getLogUser()!=null && l.getLogUser()!="" ){
			log.setLogUser(l.getLogUser());
		}
		PageBean pageBean = new PageBean();
		int allRow = logService.logCount(log);
		final int length = 20;//每页记录数
		int totalPage = PageBean.countTotalPage(length, allRow);//总页数
		final int currentPage1 = PageBean.countCurrentPage(page);//当前页为0处理

		log.setPage(length*(currentPage1-1));
		log.setSize(length);

		List<Log> list = logService.queryLogByPage(log);

		pageBean.setPageSize(20);
		pageBean.setCurrentPage(currentPage1);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.setUrl("queryLogByPage?x=1");//连接地址
		pageBean.init();
		mv.addObject("pageBean",pageBean);
		mv.setViewName("log/queryLogByPage");
		return  mv;
	}
	
	/**
	 * 导出  日志
	 * 
	 * @param 
	 * @return
	 * @throws Exception 
	 */
	
	@RequestMapping(value = "/logExcel")
	public void logExcel(HttpServletRequest request,HttpServletResponse response,Log log) throws Exception {
		
		List<Log> list = logService.queryLogList(log);
		

		HttpSession hs = request.getSession();
		
		ServletContext sc = hs.getServletContext();
		
		String pathContext = sc.getRealPath("");
		
		
		File file = new File(pathContext+"\\excel");
		
		if(!file.exists()||!file.isDirectory()) file.mkdir();
		String path="";
			  path = new java.util.Date().getTime() + "日志统计表.xlsx";
		File file1 = null;
		file1 = new File(file + "\\" + path);
		if (!file.exists())
		{
			System.out.println("//不存在");
			file.mkdirs();
			if (!file1.exists())
			{
				try
				{
					file1.createNewFile();
				} catch (IOException e)
				{
//					logInfo("exportKscj", "******Error" + "e:" + e.getMessage().toString());
					e.printStackTrace();
				}
			}
		}
		try
		{
			// 第一步，创建一个webbook，对应一个Excel文件  
			SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
			
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet 
			Sheet sheet;
				 sheet = workbook.createSheet("日志统计表");
			
//			sheet.setColumnWidth(0, 2500); // 调整第一列宽度
//			sheet.setColumnWidth(1, 5500); // 调整第一列宽度
//			sheet.setColumnWidth(2, 2500); // 调整第一列宽度
//			sheet.setColumnWidth(3, 2500); // 调整第一列宽度
			  // 设置表格默认列宽度为18个字节
	        sheet.setDefaultColumnWidth((short) 18);
	        sheet.setColumnWidth((short) 0, (short) 1400);// 设置列宽  
	        sheet.setDefaultRowHeight((short)500);
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
//	        HSSFRow row = (HSSFRow) sheet.createRow((int) 0);  
			// 设置字体
			org.apache.poi.ss.usermodel.Font font = workbook.createFont();
			font.setFontHeightInPoints((short) 10); // 字体高度
			font.setColor(HSSFFont.BOLDWEIGHT_NORMAL); // 字体颜色
			font.setFontName("黑体"); // 字体
			font.setBoldweight(HSSFFont.ANSI_CHARSET); // 宽度

			// 设置单元格类型
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
			cellStyle.setWrapText(true);
			
			// 表头为第一行
			Row row = sheet.createRow(0);
			Cell cell;
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("序号");
			
			cell = row.createCell(1);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("操作人");
			
			cell = row.createCell(2);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("日志内容");
			
			cell = row.createCell(3);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("操作时间");
			
			
			cell = row.createCell(4);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("IP");
			
			cell = row.createCell(5);
			cell.setCellStyle(cellStyle);
			cell.setCellValue("用户类型");
			
			
			
		 // 第五步，写入实体数据 实际应用中这些数据从数据库得到
			for (int i = 0; i < list.size(); i++) {
				Log logList = list.get(i);
				row = sheet.createRow((int) i + 1);  
				row.createCell((short) 0).setCellValue((double) i+1); 
	            row.createCell((short) 1).setCellValue(logList.getLogUser());  
	            row.createCell((short) 2).setCellValue(logList.getLogContent()); 
	            row.createCell((short) 3).setCellValue(logList.getLogAt());
	            row.createCell((short) 4).setCellValue(logList.getLogIp()); 
	            row.createCell((short) 5).setCellValue(logList.getLogUserType()); 
	                 
			}
			//变化路径   格式化路径
//			String realPaths = file + "\\" + path;
//			System.out.println(realPaths);
//			realPaths = realPaths.replace("\\", "/");
//			System.out.println(realPaths);
			FileOutputStream fOut = new FileOutputStream(file1);
			workbook.write(fOut);
			fOut.flush();
			fOut.close();
	        
	        
	        
	        
	        
			
		} catch (Exception e)
		{
//			logInfo("exportKscj", "******Error e:" + e.getMessage().toString());
			e.printStackTrace();
		}
		String realPaths = file + "\\" + path;
		System.out.println(realPaths);
		realPaths = realPaths.replace("\\", "/");
		System.out.println(realPaths);
		  downLoad(realPaths, response, false);  
		
		
		
		
	}
	/**
	 * 在线下载
	 * @param filePath 
	 * @param response 
	 * @param isOnLine
	 * @throws Exception
	 */
	public void downLoad(String filePath, HttpServletResponse response,  
            boolean isOnLine) throws Exception {  
        File f = new File(filePath);  
        /*  
         * if (!f.exists()) { response.sendError(404, "File not found!");  
         * return; }  
         */  
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));  
        byte[] buf = new byte[1024];  
        int len = 0;  
        response.reset(); // 非常重要  
        // 在线打开方式  
        if (isOnLine) {  
            URL u = new URL(filePath);  
            response.setContentType(u.openConnection().getContentType());  
            response.setHeader("Content-Disposition", "inline; filename="  
                    + new String( f.getName().getBytes("gb2312"), "ISO8859-1" ));  
            // 文件名应该编码成UTF-8  
        }  
        // 纯下载方式  
        else {  
            response.setContentType("application/x-msdownload");  
            response.setHeader("Content-Disposition", "attachment; filename="  
                    +new String( f.getName().getBytes("gb2312"), "ISO8859-1" ));  
        }  
        OutputStream out = response.getOutputStream();  
        while ((len = br.read(buf)) > 0)  
            out.write(buf, 0, len);  
        out.flush();  
        br.close();  
        out.close();  
    }  
	// UTF-8编码  
    public String toUTF8(String s) throws UnsupportedEncodingException {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < s.length(); i++) {  
            char c = s.charAt(i);  
            if (c >= 0 && c <= 255) {  
                sb.append(c);  
            } else {  
                byte[] b;  
                try {  
                    b = Character.toString(c).getBytes("UTF-8"); 
                } catch (Exception ex) {  
                    System.out.println(ex);  
                    b = new byte[0];  
                }  
                for (int j = 0; j < b.length; j++) {  
                    int k = b[j];  
                    if (k < 0)  
                        k += 256;  
                    sb.append("%" + Integer.toHexString(k).toUpperCase());  
                }  
            }  
        }  
        return sb.toString();  
    }  
		
	
}
