package com.ttgis.education.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HYLUtil {
	/**
     * 返回当前日期的毫秒字符串
     * @return 
     */
    public static String getMiles()
    {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date curDate = new Date(System.currentTimeMillis());
		String now = sf.format(curDate);
		return now;
    }
	public static byte[] readInputStream(InputStream inputStream) throws Exception {
		byte[] buffer = new byte[1024];
		int len = 0;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while ((len = inputStream.read(buffer)) != -1) {
			bos.write(buffer, 0, len);
		}
		bos.close();
		return bos.toByteArray();
	}
	/**
	 * 将远程服务器文件保存在本地
	 * @param imgurl 远程文件地址
	 * @param name   下载后的文件名称
	 * @param response
	 * @throws Exception
	 */
	public static void createImage(String imgurl, String filePath) throws Exception {
		URL url = new URL(imgurl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStream inputStream = conn.getInputStream(); // 通过输入流获得文件数据
		byte[] getData = readInputStream(inputStream); // 获得文件的二进制数据
		File imageFile = new File(filePath);
		FileOutputStream fos = new FileOutputStream(imageFile);
		fos.write(getData);
		fos.close();
	}
	/**
	 * 
	 * @param response
	 * @param str 要下载的文件的名字
	 * @param filePath  文件所在的路径
	 */
	public static void downFile(HttpServletResponse response,String str,String filePath)
	{
		String path = filePath+str;
		File file = new File(path);
		if(file.exists())
		{
			try {
				InputStream fis = new BufferedInputStream(new FileInputStream(path));
	            byte[] buffer = new byte[fis.available()];
	            fis.read(buffer);
	            fis.close();
	            // 清空response
	            response.reset();
	            // 设置response的Header
	            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(str,"utf-8"));
	            response.addHeader("Content-Length", "" + file.length());
	            response.setContentType("application/octet-stream");
	            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	            toClient.write(buffer);
	            toClient.flush();
	            toClient.close();
	            file.delete();
			} catch (Exception e) {
				//e.printStackTrace();
				file.delete();
			}
		}
	}
	/*获取ip*/
	public static String getIP4(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for"); 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("Proxy-Client-IP");   
		}     
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getHeader("WL-Proxy-Client-IP");       
		}      
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
			ip = request.getRemoteAddr();        
		}    
		return ip; 
	} 
}
