<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑党校导航新闻</title>
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
<input type="hidden" id="purl" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/UploadFile")%>" /> 
<input type="hidden" id="purl1" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/")%>" /> 
	<div class="title">当前位置:党校导航新闻管理>编辑党校导航新闻</div>
	<div class="editBlock">
		<form action="editschoolNewSava" method="post" id="fff" class="registerform" name="fff">
			<input type="hidden" id="newsId" name="newsId" value="${news.newsId}" /> 
			<input type="hidden" id="mark" name="mark" value="${mark}" /> 
			<input type="hidden" id="organizationId" name="organizationId" value="${news.organizationId}" /> 
			<input type="hidden" id="newsRank" name="newsRank" value="${news.newsRank}" /> 
			<input type="hidden" id="newsSort" name="newsSort" value="${news.newsSort}" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑党校导航新闻信息</td>
				</tr>
				<tbody>

					<tr >
						<th width="150">名称:</th>
						<td><input id="newsName" name="newsName" type="text" class="inputText" style="width:300px;"  value="${news.newsName}" datatype="*" errormsg="不能为空！"/></td>
					</tr>
					<tr>
						<th width="150">制作人:</th>
						<td><input id="newsProducer" name="newsProducer" type="text" class="inputText" style="width:300px;" value="${news.newsProducer}"   datatype="*6-16" errormsg="请填写6到16位任意字符！"/></td>
					</tr>
						<tr>
					<th width="150"></th>
	                    <td> 
	                    <div>
	                   <!--<img src="./UploadFile/YYZZ/a34d7e214d7b4304b900d1b2514db52e.jpg" style="border:1px solid #000;height: 200px;width:200p\"/>  -->
							<input  id="newsImage"  name="newsImage" type="file" />  <font color="Red">注意：请上传小于800K的图片</font>
	   			            <input  id="newsImages" name="newsImage" type="hidden"  value="${news.newsImage}" />
				             <div id="myTargetDivPic">
				              <img src="./${news.newsImage}" style="border:1px solid #000;height: 185px;width:330px"/>
				             </div>
				          </div>    
				        </td>
					</tr>
					
					<tr>
					     <th width="150">新闻内容:</th>
						<td>
						<%-- <textarea id="editor_id" name="newsContent" style="width:600px;height:320px;" >${news.newsContent}</textarea> --%>
                          <textarea id="TxtFile" name="newsContent"   style="width:1000px;height:320px;">${news.newsContent}</textarea> 
                          </td>
					</tr>
				</tbody>
				<tr>
					<!-- <td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定" /> --> 
						<td colspan="2" class="toolbar"><input type="button"
						class="inputButton" value="确定"  onclick="saveNew();"/>
						
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
	function saveNew(){
		 var a=document.getElementById('newsImages').value; 
		 var ids = document.getElementById('TxtFile').value;;
			if(ids==""){
				alert("新闻内容不能为空！");
				return false;}
			 else if(a==""){
				 alert("图片不能为空！");
					return false;
			}else{
				$("#fff").submit();
			}
	   
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
