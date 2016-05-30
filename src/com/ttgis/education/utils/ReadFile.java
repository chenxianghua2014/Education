package com.ttgis.education.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

/**
 * 读取磁盘文件 输出给页面
 * @author 佳东
 *
 */
public class ReadFile {
	public void readImages(String imageRoute,HttpServletResponse response){
		if(imageRoute != null && !"".equals(imageRoute)){  
            FileInputStream is;  
            try {  
                is = new FileInputStream(imageRoute);  
                int i = is.available(); // 得到文件大小  
                byte data[] = new byte[i];  
                is.read(data); // 读数据  
                is.close();  
                response.setContentType("image/*"); // 设置返回的文件类型  
                OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象  
                toClient.write(data); // 输出数据  
                toClient.close();  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
	}
}
