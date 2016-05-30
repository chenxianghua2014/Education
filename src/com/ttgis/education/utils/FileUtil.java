package com.ttgis.education.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.FileChannel;

public class FileUtil {
	/**
	 * 文件拷贝
	 * 
	 * @param src
	 * @param des
	 */
	public static void copy(InputStream is, File des) {
		try {
			int bytesum = 0;
			int byteread = 0;
			FileOutputStream fs = new FileOutputStream(des);
			byte[] buffer = new byte[1444];
			while ((byteread = is.read(buffer)) != -1) {
				bytesum += byteread;
				//System.out.println(bytesum);
				fs.write(buffer, 0, byteread);
			}
			is.close();
			fs.close();
		} catch (Exception e) {
			System.out.println("error  ");
			e.printStackTrace();
		}
	}
}
