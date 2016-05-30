<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<%String path = request.getContextPath();%>
<html>
<head>
<meta charset="utf-8">
<title>党校培训班</title>
<link rel="stylesheet" type="text/css" href="css/index_other.css">
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/other.css">
<%-- 
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/lang/zh-cn/zh-cn.js"></script> --%>
<script type="text/javascript">
	function submitBM(tranclassId){
		$.ajax({
			url:'insertStudentInfo',
			type:'post',
			data:{tranclassId:tranclassId},
			success:function(data){
				if(data == 0){
					alert("报名已提交，正在审核中...");
				}
				if(data == 1){
					alert("请登陆后进行报名");
					window.location.href = "mainout";
				}
				if(data == 2){
					alert("报名申请已提交，请勿重复报名");
				}
				if(data == 3){
					alert("报名失败");
				}
			}
		});
	}
</script>
</head>

<body>

<div class="header_red">
	<%@ include file="../Schooltop.jsp"%>
</div>


<div class="main_red">
	<div class="concainer">
    	<div class="training_dynamic">
        	<div class="training_title">
			<a href="partySchoolTraining" style="color:#005BAC;">首页</a>&nbsp;>>&nbsp;培训班信息
			</div>  
        </div>
        <div class="trainiing_news">
        <form action="" method="post">
        	<div class="news_details_title" >&nbsp;&nbsp;<h2>培训班信息</h2></div>
         
            <div class="news_details_text" >
              
             ${tranList.tranclassDetails}
              
              </div> 
              	<div style="text-align: center;">
					<input type="button" value="报名" style="font-size:23px ;width: 150px; height: 38px" onclick="submitBM('${tranList.tranclassId}')">
					<input type="button" value="关闭" style="font-size:23px ;width: 150px; height: 38px" onclick="history.back();">
				</div>
				</form>
</div>
	</div>
</div>


<!-- 底部 版权信息 -->
<%@ include file="../footerS.jsp"%>
</body>

</html>
