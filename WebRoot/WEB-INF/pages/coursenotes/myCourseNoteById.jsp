<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的笔记</title>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>

</head>

<body>
<div class="header">
	<div class="wrap">
		<%@ include file="../top.jsp"%>  
       </div>
   </div>
   
         <div class="main">
	<div class="concainer">
    	<div class="clearfix">
        	<div class="my_courses_left" >
            	<%@ include file="../LeftMyStudent.jsp"%>
 
        	</div>
            <div class="my_courses_right">
            <div class="training_title"><a href="mystudent">我的课程</a> > 笔记内容</div>  
            <div class="training_title_right">笔记内容</div>
       		 <p>${coursenoteByid.coursenoteContent}</p></div>
        </div>
           
            </div>
        </div>	

   

<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>
</body>
</html>
