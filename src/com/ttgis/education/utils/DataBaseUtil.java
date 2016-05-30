package com.ttgis.education.utils;
 
import java.io.File;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.SQLException;  
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
public class DataBaseUtil {
	  /** 
     * 获取数据库连接 
     * @return Connection 对象 
     */  
    public static Connection getConnection() {  
        Connection conn = null;  
        try {  
        	 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
//             String url = "jdbc:jtds:sqlserver://172.23.3.124:1433;DatabaseName=EDUVIDEO";  
//             String username = "sa";  
//             String password = "sa"; 
             String url = "jdbc:jtds:sqlserver://www.ttgis.com:1433;DatabaseName=EDUVIDEOS";  
             String username = "EDU";  
             String password = "Dzx123!@#"; 
             conn = DriverManager.getConnection(url, username, password);  
              
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
      
    public static void closeConn(Connection conn) {  
        if (conn != null) {  
            try {  
                conn.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** 
	     * 备份数据库 
	     * @return backup 
	     * @throws Exception 
	     */  
//	public String backup(HttpServletRequest request) { 
//		String realPath = request.getSession().getServletContext()
//				.getRealPath("/UploadBak");
////	        ActionContext context = ActionContext.getContext();  
////	        HttpServletRequest request = (HttpServletRequest) context  
////	                .get(ServletActionContext.HTTP_REQUEST);  
////	        String webtruepath = request.getParameter("path");  
//	        String name = "EDUVIDEO"; //数据库名  
//	        Date nowTime=new Date(); 
//			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			String times = time.format(nowTime).toString();
//			String names = name+times;
//	        try {  
//	            File file = new File(realPath);  
//	            String path = file.getPath() + "\\" + names + ".bak";// name文件名  
//	            String bakSQL = "backup database " + name + " to disk=? with init";// SQL语句  
//	            PreparedStatement bak = DataBaseUtil.getConnection()  
//	                    .prepareStatement(bakSQL);  
//	            bak.setString(1, path);// path必须是绝对路径  
//	            bak.execute(); // 备份数据库  
//	            bak.close();  
//	        } catch (Exception e) {  
//	            e.printStackTrace();  
//	        }  
//	        return "backup";  
//	    } 
//
//	}

}
}