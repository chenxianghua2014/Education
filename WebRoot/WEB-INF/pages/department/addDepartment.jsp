<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑部门</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>


<!-- 校验 -->
<script type="text/javascript" src="<%=path%>/js/Validform_v5.3.2_min.js"></script>
	

</head>
<body>
	<div class="title">当前位置:部门管理>编辑部门</div>
	<div class="editBlock">
		<form action="createDepartment" class="registerform" method="post" id="fff"   name="fff" >
			<input type="hidden" id="epartmentRank" name="epartmentRank" value="${department.epartmentRank}" /> 
			<input type="hidden" id="organizationId" name="organizationId" value="${department.organizationId}" /> 
			<c:if test="${department.epartmentRank==5}">
				<input type="hidden" id="epartmentUpid" name="epartmentUpid" value="${department.epartmentUpid}" /> 
			</c:if>
			
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑部门信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">组织:</th>
						<td>${organization.organizationDwmc}</td>
					</tr>
					<c:if test="${department.epartmentRank==5}">
					<tr >
						<th width="150">上层部门:</th>
						<td>${dd.epartmentName}</td>
					</tr>
					</c:if>
					<tr >
						<th width="150">部门名称:</th>
						<td><input id="epartmentName" name="epartmentName" type="text" class="inputText" style="width:300px;"  value=""  datatype="*" errormsg="不能为空！"/></td>
					</tr>
				
					
					
					
				</tbody>
				<tr>
					<td colspan="2" class="toolbar"><input type="button"
						class="inputButton" value="确定"  onclick="saveNew();"/>&nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
	$(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
})
	
	 function saveNew(){
			$("#fff").submit();
	} 
	 
	 
	function bumen(n) {
	 var ids = document.getElementById('epartmentRank').value;;
	if(ids==5){

			document.getElementById("bm").style.display="";
			
	}else{
		document.getElementById("bm").style.display="none";
	}
} 	
	 
	

</script>
</body>

</html>
