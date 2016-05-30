<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的课程</title>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

var cfg = false;
function select_all(){ //全选     
	if(cfg == false){
	    var inputs = document.getElementsByTagName("input");     
	    for(var i=0;i<inputs.length;i++)     
	    {     
	      if(inputs[i].getAttribute("type") == "checkbox")     
	      {     
	        inputs[i].checked = true;     
	      }     
	    }   
	    cfg = true;
	}else{
		 var inputs = document.getElementsByTagName("input");     
	    for(var i=0;i<inputs.length;i++)     
	    {     
	      if(inputs[i].getAttribute("type") == "checkbox")     
	      {     
	        inputs[i].checked = false;     
	      }     
	    }   
	    cfg= false;
	}
 }
</script>
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
            <div class="training_title"><a href="mystudent">我的课程</a> > 个人资料</div>  
            <div class="training_title_right">个人介绍</div>
            <div class="training_title_text"> 
            <p>
            姓名：${sessionScope.Student.studentName}
    		</p>
    		 <p>
            年龄：${so.studentoneAge}
    		</p>
    		 <p>
            身份证：${so.studentoneSfzh}
    		</p>
    		 <p>
            邮箱：${sessionScope.Student.studentEmail}
    		</p>
    		 <p>
            学习币：${sessionScope.Student.studentCoin}
    		</p>
    		 <p>
            行政级别：${so.studentoneXzjb}
    		</p>
    		<p>
            专业技术职务：${so.studentoneZyjszw}
    		</p>
    		<p>
            工人技术职务：${so.studentoneGrjszw}
    		</p>
    		<p>
             所学专业：${so.studentoneSxzy}
    		</p>
    		<p>
             毕（肄）业学校或单位：${so.studentoneByyxx}
    		</p>
    		<p>
             学位授予单位：${so.studentoneXwsydw}
    		</p>
    		</div> 
           
        </div>	
    </div>
</div> 
   
   
   

<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>
</body>
</html>
