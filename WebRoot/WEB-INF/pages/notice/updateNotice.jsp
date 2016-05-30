<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改<c:if test="${notice.noticeSort==2}">党校</c:if>通知</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>

<!-- 校验 -->
<script type="text/javascript" src="<%=path%>/js/Validform_v5.3.2_min.js"></script>
	
<!-- 文本编辑器   （改）-->
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
	
	
<!-- 文本编辑器 -->
<%-- <script type="text/javascript" src="<%=path%>/js/kindeditor/kindeditor-min.js" charset="UTF-8"></script>
<script type="text/javascript">
		KE.show({
			 id : "editor_id",
		     width : "800px",
		     height : "200px",		    
		     resizeMode : 1,
		     allowFileManager : true,
		     /*图片上传的SERVLET路径*/
		     imageUploadJson : "/Education/upplansort3", 
		     /*图片管理的SERVLET路径*/     
		     fileManagerJson : "/Education/upplansort3",
		     /*允许上传的附件类型*/
		     accessoryTypes : "docx|doc|xls|pdf|txt|ppt|rar|zip",
		     /*附件上传的SERVLET路径*/
		     accessoryUploadJson : "/Education/upplansort4"
		});
	</script> --%>
</head>
<body>
	<div class="title">当前位置:<c:if test="${notice.noticeSort==2}">党校</c:if>通知管理>修改<c:if test="${notice.noticeSort==2}">党校</c:if>通知</div>
	<div class="editBlock">
		<form action="updateNotice" method="post"  class="registerform"  id="fff"   name="fff" >
			<input type="hidden" id="noticeId" name="noticeId" value="${notice.noticeId}" /> 
			<input type="hidden" id="mark" name="mark" value="${mark}" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">修改<c:if test="${notice.noticeSort==2}">党校</c:if>通知信息</td>
				</tr>
				<tbody>
				<!-- 	<tr>
						<th width="150"><span class="warning">*</span>简介类别</th>
						<td><select id="xcxxXclb" name="xcxxXclb" data-rule="required;"
							style="width: 142px;" onchange="bindXcnr(this.value)">
						</select></td>
					</tr> -->
					
					<tr >
						<th width="150">名称:</th>
						<td><input id="noticeName" name="noticeName" type="text" class="inputText" style="width:300px;"  value="${notice.noticeName}"  datatype="*" errormsg="不能为空！"/></td>
					</tr>
					<tr>
						<th width="150">制作人:</th>
						<td><input id="noticeProducer" name="noticeProducer" type="text" class="inputText" style="width:300px;" value="${notice.noticeProducer}"  datatype="*6-16" errormsg="请填写6到16位任意字符！"/></td>
					</tr>
				
					
					<tr>
						<th width="150">通知内容:</th>
						<td>
						<%-- <textarea id="editor_id" name="noticeContent" style="width:600px;height:320px;" >${notice.noticeContent}</textarea> --%>
                        <textarea id="TxtFile" name="noticeContent"   style="width:700px;height:320px;">${notice.noticeContent}</textarea>
                          </td>
					</tr>
				</tbody>
				<tr>
					<td colspan="2" class="toolbar"><input type="button"
						class="inputButton" value="确定"  onclick="saveNew();"/>&nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript">
var editor = new UE.ui.Editor({ setheight:230,autoHeightEnabled:false });
editor.render("TxtFile");
//1.2.4以后可以使用一下代码实例化编辑器
//UE.getEditor('myEditor')
</script> 
		<script type="text/javascript">
		 $(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
})
	 function saveNew(){
		 var ids = document.getElementById('TxtFile').value;;
			if(ids==""){
				alert("通知内容不能为空！");
				return false;
			}else{
				$("#fff").submit();
			}
	   
	} 

</script>
</body>

</html>
