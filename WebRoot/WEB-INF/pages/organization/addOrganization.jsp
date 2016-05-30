<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>编辑组织结构</title>
		<!-- 表格风格样式  -->
		<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet"
			type="text/css" />
		<script type="text/javascript"
			src="<%=path%>/js/jquery/jquery-1.9.1.min.js">
</script>
<!-- 校验 -->
<script type="text/javascript" src="<%=path%>/js/Validform_v5.3.2_min.js"></script>




	</head>
	<body>
		<div class="title">
			当前位置:系统管理>编辑组织结构
		</div>
		<div class="editBlock">
			<form action="createOrganization" method="post" class="registerform"  id="fff"   name="fff">

				<table>
					<tr>
						<td colspan="2" class="subtitle">
							编辑组织结构信息
						</td>
					</tr>
					<tbody>
						<!-- 	<tr>
						<th width="150"><span class="warning">*</span>简介类别</th>
						<td><select id="xcxxXclb" name="xcxxXclb" data-rule="required;"
							style="width: 142px;" onchange="bindXcnr(this.value)">
						</select></td>
					</tr> -->

						<tr>
							<th width="150">
								组织名称:
							</th>
							<td>
								<input id="organizationDwmc" name="organizationDwmc" type="text"
									class="inputText" style="width: 300px;" value=""  datatype="*" errormsg="不能为空！"/>
							</td>
						</tr>
						<tr>
							<th width="150">
								单位代码:
							</th>
							<td>
								<input id="organizationDwdm" name="organizationDwdm"
									type="text" class="inputText" style="width: 300px;"
									value=""  datatype="*" errormsg="不能为空！"/>
							</td>
						</tr>
						<tr>
							<th width="150">
								单位负责人:
							</th>
							<td>
								<input id="organizationDwfzr" name="organizationDwfzr" type="text"
									class="inputText" style="width: 300px;" value=""  datatype="*" errormsg="不能为空！"/>
							</td>
						</tr>
						<tr>
							<th width="150">
								联系电话:
							</th>
							<td>
								<input id="organizationLxrdh" name="organizationLxrdh" type="text"
									class="inputText" style="width: 300px;" value=""  datatype="*" errormsg="不能为空！"/>
							</td>
						</tr>
						<tr>
							<th width="150">
								邮箱:
							</th>
							<td>
								<input id="organizationLxremail" name="organizationLxremail" type="text"
									class="inputText" style="width: 300px;" value=""  datatype="*" errormsg="不能为空！"/>
							</td>
						</tr>
						<c:if test="${sessionScope.sessionUser.accountType==1||sessionScope.sessionUser.accountType==5}">
						<tr>
							<th width="150">
								上级单位:
							</th>
							<td>
							<select id="organizationSjdw" name="organizationSjdw" onchange="">
							<option value="test001" >集团公司总部 </option>					
							<c:forEach items="${listtype}" var="listtype" varStatus="status">
								<option value="${listtype.organizationId}" >${listtype.organizationDwmc}</option>
							</c:forEach>
							</select>
							</td>
						</tr>
						</c:if>
						
						
					</tbody>
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

	</body>

</html>

<script type="text/javascript">
			 $(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
})
</script>
