<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>笔记</title>
<!-- 表格风格样式  -->
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

	

</head>
<body>
	<div class="title">当前位置:笔记管理>笔记</div>  
	<div class="editBlock">
		
			
			<table>
				<tr>
					<td colspan="2" class="subtitle">笔记</td>
				</tr>
				<tbody>

					<tr >
						<th width="150">笔记内容:</th>
						<td>
						${coursenoteByid.coursenoteContent}
						</td>
					</tr>
					
				<tr>
					<!-- <td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定" /> --> 
						<td colspan="2" class="toolbar">
						<!-- <input type="button"
						class="inputButton" value="确定"  onclick="saveTeacher();"/>
						
						&nbsp;&nbsp; --> <input
						type="button" class="inputButton" value="返回"
						onclick="history.back()" /></td>
				</tr>
			</table>
		
	</div>
</body>
<script type="text/javascript">
/* 	var ue = UE.getEditor('newsContent');
	var editor;
	$(document).ready(function() {
		editor = UE.getEditor('newsContent');
	}); */
	function saveTeacher(){
			
			if(document.getElementById('teacherName').value==""){
				alert("请输入教师姓名！");
				return false;
			}
			if(document.getElementById('teacherAge').value==""){
				 alert("请输入教师年龄！");
					return false;
			}
			if(document.getElementById('editor_id').value==""){
				 alert("请填写教师简历！");
					return false;
			}
			document.teacherForm.action="teacherUpdateSave";
			document.teacherForm.method="post";
			document.teacherForm.submit();
			
	   
	} 
	
	function radioType(){
		   var chk = 0;

                var chkObjs = document.getElementsByName('newsTop');
                for(var i=0;i<chkObjs.length;i++){
                    if(chkObjs[i].checked){
                        chk = i;
                        break;
                    }
                }
		  if(chk==1){
			  document.getElementById("toptype").style.display="";
			  
		  }else{
			  document.getElementById("toptype").style.display="none";
		  }
	 }
	

</script>
</html>
