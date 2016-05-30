<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>调转部门</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>


<!-- 校验 -->
<link rel="stylesheet" href="<%=path%>/js/validator-0.7.3/jquery.validator.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/local/zh_CN.js"></script>
	

</head>
<body>
	<div class="title">当前位置:部门管理>调转部门</div>
	<div class="editBlock">
		<form action="updateTransferDepartment" method="post" autocomplete="off"  id="fff"   name="fff" 
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="departmentId" name="departmentId" value="${department.departmentId}" /> 
			<input type="hidden" id="organizationId" name="organizationId" value="${department.organizationId}" /> 
			<input type="hidden" id="epartmentRank" name="epartmentRank" value="${department.epartmentRank}" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">调转部门信息</td>
				</tr>
				<tbody>
				<c:if test="${dlist != null}">
					<tr>
						<th width="150">调转单位:</th>
						<td>
							<select id="organizationId"  name="organizationId" class="inputText" >
								 <c:forEach items="${dlist}" var="maps" varStatus="status">
								<option value="${maps.organizationId}" ${department.organizationId == maps.organizationId ? "selected":""}>${maps.organizationDwmc}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					</c:if>
					
					<c:if test="${bmlist != null}">
					<tr>
						<th width="150">调转部门:</th>
						<td>
							<select id="epartmentUpid"  name="epartmentUpid" class="inputText" >
								 <c:forEach items="${bmlist}" var="maps" varStatus="status">
								<option value="${maps.departmentId}"  ${department.epartmentUpid == maps.departmentId ? "selected":""}>${maps.epartmentName}</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					</c:if>
					
					
					
					
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
	 function saveNew(){
	
		document.fff.submit();
	
	} 
	 
	
	

</script>
</body>

</html>
