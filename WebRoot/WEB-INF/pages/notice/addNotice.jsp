<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑<c:if test="${noticeSort==2}">党校</c:if>通知</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

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
	<div class="title">当前位置:<c:if test="${noticeSort==2}">党校</c:if>通知管理>编辑<c:if test="${noticeSort==2}">党校</c:if>通知</div>
	<div class="editBlock">
		<form action="createNotice" method="post" class="registerform"  id="fff"   name="fff" >
			<input type="hidden" id="noticeSort" name="noticeSort" value="${noticeSort}" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑<c:if test="${noticeSort==2}">党校</c:if>通知信息</td>
				</tr>
				<tbody>
				<!-- 	<tr>
						<th width="150"><span class="warning">*</span>简介类别</th>
						<td><select id="xcxxXclb" name="xcxxXclb" data-rule="required;"
							style="width: 142px;" onchange="bindXcnr(this.value)">
						</select></td>
					</tr> -->
					
					<tr >
						<th width="150">置顶选择:</th>
						<td>
						<input type="radio" name="noticeTop"  id="noticeTop"  value="0" checked="checked" onclick="radioType()"/> 否 
						<input type="radio"  id="noticeTop"  name="noticeTop" value="1" onclick="radioType()" /> 是 
						</td>
					</tr>
					<tr style="display: none" id = "toptype">
						<th width="150">置顶截止日期:</th>
						<td><input type="text" name="noticeToptime" class="inputText" id="noticeToptime"  value="${notice.noticeTime}" onclick='WdatePicker()'/></td>
					</tr>
					
					<tr >
						<th width="150">名称:</th>
						<td><input id="noticeName" name="noticeName" type="text" class="inputText" style="width:300px;"  value=""   datatype="*" errormsg="不能为空！"/></td>
					</tr>
					<tr>
						<th width="150">制作人:</th>
						<td><input id="noticeProducer" name="noticeProducer" type="text" class="inputText" style="width:300px;" value=""   datatype="*6-16" errormsg="请填写6到16位任意字符！"/></td>
					</tr>
					
					
					<tr>
						<th width="150">通知内容:</th>
						<td>
						
				<!-- 		<textarea id="editor_id" name="noticeContent" style="width:600px;height:320px;" ></textarea> -->
                         <textarea id="TxtFile" name="noticeContent"   style="width:700px;height:320px;"></textarea>
                          </td>
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
	 function radioType(){
		   var chk = 0;

                var chkObjs = document.getElementsByName('noticeTop');
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
</body>

</html>
