<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>培训班</title>
<!-- 表格风格样式  -->
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
	
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>

<!-- 校验 -->
<script type="text/javascript" src="js/Validform_v5.3.2_min.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

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
   <script type="text/javascript">

</script> 
</head>
<body>
	<div class="title">当前位置:培训班管理>培训班管理</div>
	<div class="editBlock">
		<form action="shiftUpdate" method="post" id="fff" class="registerform" name="fff" >
			<input type="hidden" id="tranclassId" name="tranclassId" value="${shiftList.tranclassId}" />
			<input type="hidden" id="classresourceId" name="classresourceId" value="${shiftList.classresourceId}" />
			<input type="hidden" id="tranclassRank" name="tranclassRank" value="${cj}" />
			
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑培训班信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">培训班名称:</th>
						<td><input id="tranclassName" name="tranclassName" type="text" class="inputText" style="width:300px;"  value="${shiftList.tranclassName}" datatype="*" errormsg="不能为空！"/></td>
					</tr>
					 <tr >
						<th width="150">班主任:</th>
						<td><input id="tranclassTeacher" name="tranclassTeacher" type="text" class="inputText" style="width:300px;"  value="${shiftList.tranclassTeacher}"  datatype="*" errormsg="不能为空！"/></td>
					</tr>
					<tr >
						<th width="150">开设时间:</th>
						<%-- <td><input type="text" name="tranclassSetuptime" class="inputText" id="tranclassSetuptime"  value="${shiftList.tranclassSetuptime}" onclick="WdatePicker({ minDate:'#tranclassEndtime' })"/></td> --%>
						<td><input type="text" name="tranclassSetuptime" class="inputText" id="tranclassSetuptime"  value="${shiftList.tranclassSetuptime}" onclick="WdatePicker()" datatype="*" errormsg="不能为空！"/></td>
					</tr>
					<tr >
						<th width="150">结束时间:</th>
						<td><input type="text" name="tranclassEndtime" class="inputText" id="tranclassEndtime"  value="${shiftList.tranclassEndtime}" onclick="WdatePicker({minDate:'#F{$dp.$D(\'tranclassSetuptime\')}'})" datatype="*" errormsg="不能为空！"/></td>
					</tr>
				<!-- 	<tr >
						<th width="150">报名开始时间:</th>
						<td><input type="text" name="tranclassApplytime" class="inputText" id="tranclassApplytime"  value="" onclick='WdatePicker()'/></td>
					</tr>
					<tr >
						<th width="150">报名截止时间:</th>
						<td><input type="text" name="tranclassApplyendtime" class="inputText" id="tranclassApplyendtime"  value="" onclick='WdatePicker()'/></td>
					</tr> -->
					<tr >
						<th width="150">面授信息:</th>
						<td>
						<select id="tranclassFace" name="tranclassFace">
							<option value="0" ${shiftList.tranclassFace==0 ?"selected":""}>否</option>
							<option value="1" ${shiftList.tranclassFace==1 ?"selected":""}>是</option>
					</select>
					</td>  
					</tr> 
					<tr>
						<th width="150">班级详情:</th>
						<td>
						<%-- <textarea id="editor_id" name="tranclassDetails" style="width:600px;height:320px;" datatype="*" errormsg="不能为空！">${shiftList.tranclassDetails}</textarea> --%>
                          <textarea id="TxtFile" name="tranclassDetails"   style="width:700px;height:320px;"  datatype="*" errormsg="不能为空！">${shiftList.tranclassDetails}</textarea>
                          </td>
					</tr>
				</tbody>
				<tr>
					<!-- <td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定" /> --> 
						<td colspan="2" class="toolbar"><input type="button"
						class="inputButton" value="确定"  onclick="saveShift();"/>
						
						&nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
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
function saveShift(){
	$("#fff").submit();
} 

</script>
</html>
