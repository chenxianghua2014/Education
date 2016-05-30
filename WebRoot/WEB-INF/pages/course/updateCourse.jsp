<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改课程</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

<!-- 校验 -->
<script type="text/javascript" src="<%=path%>/js/Validform_v5.3.2_min.js"></script>
	
	<!-- 上传图片插件     -->
<link rel="stylesheet" href="js/uploadify/css/uploadify.css" type="text/css"></link>
<script type="text/javascript" src="js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="js/uploadPlan_pic1.js"></script>

</head>
<body>
<input type="hidden" id="purl" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/UploadFile")%>" /> 
<input type="hidden" id="purl1" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/")%>" /> 
	<div class="title">当前位置:课程管理>修改课程</div>
	<div class="editBlock">
		<form action="updateCourse" method="post" class="registerform"  id="fff"   name="fff" >
			<input type="hidden" id="courseId" name="courseId" value="${course.courseId}" /> 
			<input type="hidden" id="syllabusId" name="syllabusId" value="${course.syllabusId}" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">修改课程信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">课程名称:</th>
						<td><input id="courseName" name="courseName" type="text" class="inputText" style="width:300px;"  value="${course.courseName}"  datatype="*" errormsg="不能为空！"/></td>
					</tr>
					<tr>
						<th width="150">发布人:</th>
						<td><input id="coursePubman" name="coursePubman" type="text" class="inputText" style="width:300px;" value="${course.coursePubman}"   datatype="*6-16" errormsg="请填写6到16位任意字符！"/></td>
					</tr>
					
					
					
					<tr>
						<th width="150">教师:</th>
						<td>
						<select id="teacherId" name="teacherId" datatype="*" errormsg="不能为空！"> 
							<option value=" " >请选择</option>
							 <c:forEach items="${tlist}" var="maps" >
							 <option value="${maps.teacherId}" ${course.teacherId==maps.teacherId?"selected":""}>${maps.teacherName}</option>
							 </c:forEach>
						</select>
						</td>
					</tr>
					
					<tr>
					<th width="150"></th>
	                    <td> 
	                    <div>
	                   <!--<img src="./UploadFile/YYZZ/a34d7e214d7b4304b900d1b2514db52e.jpg" style="border:1px solid #000;height: 200px;width:200p\"/>  -->
							<input  id="newsImage"  name="newsImage" type="file" datatype="*" errormsg="不能为空！"/>  <font color="Red">注意：请上传小于800K的图片</font>
	   			            <input  id="courseImage" name="courseImage" type="hidden"  value="${course.courseImage}" />
				             <div id="myTargetDivPic">
				              <img src="./${course.courseImage}" style="border:1px solid #000;height: 185px;width:330px"/>
				             </div>
				          </div>    
				        </td>
					</tr>
					
					<tr>
						<th width="150">课程详情</th>
						<td><textarea id="courseDesc" name="courseDesc" style="width:600px;height:320px;"  datatype="*" errormsg="不能为空！">${course.courseDesc}</textarea>
                          </td>
					</tr>
					
				</tbody>
				<tr>
					<td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定"  />&nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>

</body>
<script type="text/javascript">
		 $(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
})
</script>
</html>
