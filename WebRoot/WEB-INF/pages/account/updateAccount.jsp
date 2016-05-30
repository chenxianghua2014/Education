<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>修改后台账号</title> 
		<!-- 表格风格样式  -->
		<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript"
			src="<%=path%>/js/jquery/jquery-1.9.1.min.js">
</script>
			<!-- 校验 -->
<script type="text/javascript" src="<%=path%>/js/Validform_v5.3.2_min.js"></script>

<!-- 联动 -->
<script type="text/javascript" charset="utf-8"
			src="<%=path%>/js/utils/accountType.js">
</script>
<script type="text/javascript" charset="utf-8"
			src="<%=path%>/js/utils/accountShowType.js">
</script>



	</head>
	<body>
		<div class="title">
			当前位置:系统管理>修改后台账号
		</div>
		<div class="editBlock">
			<form action="updateAccount" method="post" class="registerform"  id="fff"   name="fff">
					<input type="hidden"  name="accountId" id="accountId" value="${account.accountId}">
					<input type="hidden"  name="oid" id="oid" value="${account.accountCatalog}">
					<input type="hidden"  name="cid" id="cid" value="${account.accountAdmin}">
					<input type="hidden" name="atype" id="atype" value="${sessionScope.sessionUser.accountType}">
					<input type="hidden"  name="accountCatalog" id="accountCatalog" value="${account.accountCatalog}">
					<input type="hidden"  name="id" id="id" value="${id}">
				<table>
					<tr>
						<td colspan="2" class="subtitle">
							修改后台账号信息
						</td>
					</tr>
					<tbody>
						<!-- 	<tr>
						<th width="150"><span class="warning">*</span>简介类别</th>
						<td><select id="xcxxXclb" name="xcxxXclb" data-rule="required;"
							style="width: 142px;" onchange="bindXcnr(this.value)">
						</select></td>
					</tr> --><%--

						<tr>
							<th width="150">
								名称:
							</th>
							<td>
								<input id="accountLoginid" name="accountLoginid" type="text"
									class="inputText" style="width: 300px;" value="${account.accountLoginid}" />
							</td>
						</tr>
						--%><tr>
							<th width="150">
								联系人:
							</th>
							<td>
								<input id="accountName" name="accountName" type="text"
									class="inputText" style="width: 300px;" value="${account.accountName}" datatype="*" errormsg="不能为空！"/>
							</td>
						</tr>
						<tr>
							<th width="150">
								联系电话:
							</th>
							<td>
								<input id="accountPhone" name="accountPhone" type="text"
									class="inputText" style="width: 300px;" value="${account.accountPhone}" datatype="*" errormsg="不能为空！"/>
							</td>
						</tr><%--
						<c:if test="${account.accountId != sessionScope.sessionUser.accountId}">
						<tr>
							<th width="150">
								管理级别:
							</th>
							<td>
								<select name="accountType" id="accountType" onchange="CUrl()">
									<option value="">
										请选择
									</option>
									<c:if test="${rk==5}">
									<option value="1" ${account.accountType==1?"selected":""}>
										集团级
									</option>
									</c:if>
									<c:if test="${rk==1||rk==5}">
									<option value="2" ${account.accountType==2?"selected":""}>
										院一级
									</option>
									</c:if>
									<c:if test="${rk==1||rk==5}">
									<option value="3" ${account.accountType==3?"selected":""}>
										院二级
									</option>
									</c:if>
									<c:if test="${rk!=5}">
									<option value="4" ${account.accountType==4?"selected":""}>
										培训机构
									</option>
									</c:if>
									<c:if test="${rk==5}">
									<option value="5" ${account.accountType==5?"selected":""}>
										系统管理
									</option>
									</c:if>
								</select>
							</td>
						</tr>
						</c:if>
						<tr>

							<th width="150" id="tname">

							</th>
							<td id="tt">

							</td>

						</tr>
						<tr>

							<th width="150" id="tname1">

							</th>
							<td id="tt1">

							</td>

						</tr>
						<tr>

							<th width="150" id="tname2">

							</th>
							<td id="tt2">

							</td>

						</tr>
						<tr>

							<th width="150" id="tname3">

							</th>
							<td id="tt3">

							</td>

						</tr>
						



					--%></tbody>
					<tr>
						<td colspan="2" class="toolbar">
							<input type="submit" class="inputButton" value="确定" />
							&nbsp;&nbsp;
							<input type="button" class="inputButton" value="取消"
								onclick="history.back()" />
						</td>
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


	window.onload=function(){ 
		
	var url = document.getElementsByName("accountType")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	if(urlpoing==2){
		atype();
	}
	if(urlpoing==3){
		atype1();
	}
	if(urlpoing==4){
		atype1();
		FXPXBtype();
	}
	
	} 

		


</script>
	</body>

</html>
