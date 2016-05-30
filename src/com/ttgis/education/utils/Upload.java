package com.ttgis.education.utils;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class Upload
{
	/***
	 * 保存图片，返回图片地址
	 * 
	 * @param realPath
	 * @param file
	 * @return
	 */
	public static String storeIOc(String realPath, MultipartFile file)
	{
		File files = new File(realPath);
		// 判断文件夹是否存在，不存在就创建一个
		if (!files.exists() && !files.isDirectory())
		{
			System.out.println("//不存在");
			files.mkdir();
		} else
		{
			System.out.println("//目录存在");
		}
		// 文件名称
		String fileName = "";
		String logImageName = "";
		// file调用getOriginalFilename获取完成文件名
		String _fileName = file.getOriginalFilename();
		// 用文件名截取最后一个.后面的字符当作扩展名
		String suffix = _fileName.substring(_fileName.lastIndexOf("."));
		/** 使用UUID生成文件名称 **/
		logImageName = UUID.randomUUID().toString().replace("-", "") + suffix;

		fileName = realPath + File.separator + logImageName;
		File restore = new File(fileName);
		try
		{
			file.transferTo(restore);
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		// 返回默认的图片地址
		return logImageName;
	}
}
