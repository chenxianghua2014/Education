<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑教师信息</title>
<!-- 表格风格样式  -->
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- 上传图片插件     -->
<link rel="stylesheet" href="js/uploadify/css/uploadify.css" type="text/css"></link>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="js/uploadPlan_pic.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

<!-- 校验 -->
<link rel="stylesheet" href="js/validator-0.7.3/jquery.validator.css" type="text/css">
<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" charset="utf-8" src="js/validator-0.7.3/local/zh_CN.js"></script>
	
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
<input type="hidden" id="purl" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/UploadFile")%>" /> 
<input type="hidden" id="purl1" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/")%>" /> 
	<div class="title">当前位置:师资管理>编辑教师信息</div>
	<div class="editBlock">
		<form action="teacherSave" method="post" id="teacherForm" autocomplete="off" name="teacherForm"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="organizationId" name="organizationId" value="${organizationId}" /> 
			<input type="hidden" id="teacherDel" name="teacherDel" value="1" /> 
			<input type="hidden" id="cj" name="cj" value="${cj}" /> 
			
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑教师信息</td>
				</tr>
				<tbody>

					<tr >
						<th width="150">教师姓名:</th>
						<td><input id="teacherName" name="teacherName" type="text" class="inputText" style="width:300px;"  value="" data-rule="required;"/></td>
					</tr>
					<tr>
						<th width="150">教师年龄:</th>
						<td><input id="teacherAge" name="teacherAge" type="text" class="inputText" style="width:300px;" value=""  data-rule="required;"/></td>
					</tr>
					<tr>
					<th width="150">教师图片</th>
	                    <td> 
	                    <div>
	                   <!--<img src="./UploadFile/YYZZ/a34d7e214d7b4304b900d1b2514db52e.jpg" style="border:1px solid #000;height: 200px;width:200p\"/>  -->
							<input  id="newsImage"  name="teacherPhoto" type="file" /> <font color="Red">注意：请上传小于800K的图片</font>
	   			            <input  id="newsImages" name="teacherPhoto" type="hidden"  value="" />
				             <div id="myTargetDivPic"></div>
				              <script type="text/javascript">
	      /*        var a=document.getElementById('newsImages').value; 
	             if (a!="") {
					document.getElementById('newsImages').value="";
				 }  */
	             </script>
				          </div>    
				        </td>
					</tr>
					<tr>
					     <th width="150">简历:</th>
						<td>
						<!-- <textarea id="editor_id" name="teacherInformation" style="width:600px;height:320px;" ></textarea> -->
                        <textarea id="TxtFile" name="teacherInformation"   style="width:700px;height:320px;"></textarea>
                          </td>
					</tr>
					
				</tbody>
				<tr>
						<th width="150">教师管理人:</th>
						<td><input id="teacherAdmin" name="teacherAdmin" type="text" class="inputText" style="width:300px;" value=""  data-rule="required;"/></td>
					</tr>
				
				
				<tr>
					<!-- <td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定" /> --> 
						<td colspan="2" class="toolbar"><input type="button"
						class="inputButton" value="确定"  onclick="saveTeacher();"/>
						
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
			if(document.getElementById('newsImages').value==""){
				 alert("请上传教师图片！");
					return false;
			}
			if(document.getElementById('TxtFile').value==""){
				 alert("请填写教师简历！");
					return false;
			}
			document.teacherForm.action="teacherSava";
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
