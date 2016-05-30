<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>上传作业</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

<!-- 校验 -->
<link rel="stylesheet" href="<%=path%>/js/validator-0.7.3/jquery.validator.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/local/zh_CN.js"></script>
	


</head>
<body>
	
	<div class="editBlock">
		<form action="uploadHomework" method="post" autocomplete="off"  id="fff"   name="fff" 
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true} " enctype="multipart/form-data">
			<input type="hidden" name="courseId"  id="courseId"  value="${courseId}"/>
			<table>
				
				<tbody>
					<tr>
						<th width="150">上传作业</th>
						<td>
							<input id="file" name="file" type="file" class="inputText" style="width:300px;"  value=""  data-rule="required;"/>
                          </td>
					</tr>
					
				
				</tbody>
				<tr>
					<td colspan="2" class="toolbar">
						<input type="submit" class="inputButton" value="确定"  />&nbsp;&nbsp; 
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>
