<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑培训班</title>
<!-- 表格风格样式  -->
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
	
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>

<!-- 校验 -->
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>
	

</head>
<body>
	<div class="title">当前位置:培训班管理>常规培训班管理</div>
	<div class="editBlock">
		<form action="classSava" method="post" class="registerform" id="fff"  name="fff" >
			<input type="hidden" id="organizationId" name="organizationId" value="${pageClassId}" /> 
			<input type="hidden" id="classresourceRank" name="classresourceRank" value="${cj}" /> 
				<input type="hidden" id="classresourceUpname" name="classresourceUpname" value="${type}" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑常规培训班信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">培训班名称:</th>
						<td><input id="classresourceName" name="classresourceName" type="text" class="inputText" style="width:300px;"  value="" datatype="*" errormsg="不能为空！"/></td>
					</tr>
				</tbody>
				<tr>
					<!-- <td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定" /> --> 
						<td colspan="2" class="toolbar"><input type="button"
						class="inputButton" value="确定"  onclick="saveClass();"/>
						
						&nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
 $(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
})
function saveClass(){
	$("#fff").submit();
} 

</script>
</html>
