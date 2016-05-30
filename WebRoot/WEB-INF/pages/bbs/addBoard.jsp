<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑版块信息</title>
<!-- 表格风格样式  -->
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

</head>
<body>
	<div class="title">当前位置:论坛管理>编辑版块信息</div>
	<div class="editBlock">
		<form action="saveBoard" method="post" id="boardForm" autocomplete="off" name="boardForm"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑版块信息</td>
				</tr>
				<tbody>

					<tr >
						<th width="150">名称:</th>
						<td><input id="boardName" name="boardName" type="text" class="inputText" style="width:300px;"  value="" data-rule="required;"/></td>
					</tr>
					<tr>
						<th width="150">主题:</th>
						<td><input id="boardTitle" name="boardTitle" type="text" class="inputText" style="width:300px;" value=""  data-rule="required;"/></td>
					</tr>
				</tbody>
				<tr>
					<td colspan="2" class="toolbar">
						<input type="button" class="inputButton" value="确定"  onclick="saveBoard();"/>
						&nbsp;&nbsp; 
						<input type="button" class="inputButton" value="取消" onclick="history.back()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function saveBoard(){
			
			if(document.getElementById('boardName').value==""){
				alert("请输入版块名称！");
				return false;
			}
			if(document.getElementById('boardTitle').value==""){
				 alert("请输入主题！");
					return false;
			}
			
			document.boardForm.action="saveBoard";
			document.boardForm.method="post";
			document.boardForm.submit();
	   
	} 
	
	
</script>
</html>
