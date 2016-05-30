<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑课程</title>
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
	<div class="title">当前位置:课程管理>编辑课程</div>
	<div class="editBlock">
		<form action="createCourse" method="post" class="registerform" id="fff"   name="fff" >
			<input type="hidden" id="courseCompany" name="courseCompany" value="无" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑课程信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">课程名称:</th>
						<td><input id="courseName" name="courseName" type="text" class="inputText" style="width:300px;"  value=""  datatype="*" errormsg="不能为空！"/></td>
					</tr>
					<tr>
						<th width="150">发布人:</th>
						<td><input id="coursePubman" name="coursePubman" type="text" class="inputText" style="width:300px;" value=""   datatype="*6-16" errormsg="请填写6到16位任意字符！"/></td>
					</tr>
					
					<tr>
						<th width="150">教师:</th>
						<td>
						<select id="teacherId" name="teacherId" datatype="*" errormsg="不能为空！">
							<option value=" " >请选择</option>
							 <c:forEach items="${tlist}" var="maps" >
							 <option value="${maps.teacherId}" >${maps.teacherName}</option>
							 </c:forEach>
						</select>
						</td>
					</tr>
					
					
						<tr>
					<th width="150"></th>
	                    <td> 
	                    <div>
	                   <!--<img src="./UploadFile/YYZZ/a34d7e214d7b4304b900d1b2514db52e.jpg" style="border:1px solid #000;height: 200px;width:200p\"/>  -->
							<input  id="newsImage"  name="newsImage" type="file" />  <font color="Red">注意：请上传小于800K的图片</font>
	   			            <input  id="courseImage" name="courseImage" type="hidden"  value="" />
				             <div id="myTargetDivPic"></div>
				          </div>    
				        </td>
					</tr>
					
					<tr>
						<th width="150">课程详情</th>
						<td><textarea id="courseDesc" name="courseDesc" style="width:600px;height:320px;"  datatype="*" errormsg="不能为空！"></textarea>
                          </td>
					</tr>
					
					<tr>
						<th width="150">课程目录</th>
						<td>
						<select id="stype" name="stype"  onchange="CUrl()" datatype="*" errormsg="不能为空！">
							<option value=" " >请选择</option>
							 <c:if test="${sessionScope.sessionUser.accountType==1}">
							 <c:forEach items="${slist}" var="maps" varStatus="status">
								 <c:if test="${maps.syllabusId != '2AD34C2B-0A29-5325-1613-3082518F4568'}">
							 		<option value="${maps.syllabusId}" >${maps.syllabusName}</option>
								 </c:if>
							 </c:forEach>
							</c:if>
							<c:if test="${sessionScope.sessionUser.accountType!=1}">
							<option value="2AD34C2B-0A29-5325-1613-3082518F4568" >本单位级课程</option>
							</c:if>
						</select>
                          </td>
					</tr>
					
					<tr>

							<th width="150" id="tname">

							</th>
							<td id="tt">

							</td>

						</tr>
						<tr>

							<th width="150" id="tname1">

							</th>
							<td id="tt1">

							</td>

						</tr>
						<tr>

							<th width="150" id="tname2">

							</th>
							<td id="tt2">

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
		 $(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
})

function saveNew(){
		var url = document.getElementsByName("syllabusId")[0];
		if(url!=undefined){
		var urlpoing1 = url.options[url.options.selectedIndex].value;
			if(urlpoing1!="" ||urlpoing1!=null){
				$("#fff").submit();
			}else{
				alert("请创建底层菜单树");
				
			}
			}else{
				alert("请创建底层菜单树");
			}
	   
	} 

function CUrl() {
	document.getElementById("tt").innerHTML = "";
	document.getElementById("tname").innerHTML = "";
	document.getElementById("tt1").innerHTML = "";
	document.getElementById("tname1").innerHTML = "";
	document.getElementById("tt2").innerHTML = "";
	document.getElementById("tname2").innerHTML = "";
	var url = document.getElementsByName("stype")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	var html = "";
		$.post("syllabusLD", {
			Action : "post",
			id : urlpoing
		}, function(data, textStatus) {
			if (data.length > 0) {

					html += '<select id="stype2" name="stype2" onchange="DCUrl()"  datatype="*" errormsg="不能为空！">';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.syllabusId
								+ '" >' + newdata.syllabusName
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("tt").innerHTML = html;
					document.getElementById("tname").innerHTML = "选择下级目录";
				
			}

		}, "json");
}

function DCUrl() {
	document.getElementById("tt1").innerHTML = "";
	document.getElementById("tname1").innerHTML = "";
	document.getElementById("tt2").innerHTML = "";
	document.getElementById("tname2").innerHTML = "";
	var url = document.getElementsByName("stype2")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	var html = "";
		$.post("syllabusLD", {
			Action : "post",
			id : urlpoing
		}, function(data, textStatus) {
			if (data.length > 0) {

					html += '<select id="syllabusId" name="syllabusId" onchange="Ctype()"  datatype="*" errormsg="不能为空！">';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.syllabusId
								+ '" >' + newdata.syllabusName
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("tt1").innerHTML = html;
					document.getElementById("tname1").innerHTML = "选择底层目录";
				
			}

		}, "json");
}


function Ctype() {
	document.getElementById("tt2").innerHTML = "";
	document.getElementById("tname2").innerHTML = "";
	var url = document.getElementsByName("stype")[0];
	var urlpoing = url.options[url.options.selectedIndex].value;
	var html = "";
	html += '<select id="courseType" name="courseType"  datatype="*" errormsg="不能为空！">';
	html += '<option value="" >请选择</option>';
	if(urlpoing=='1AD34C2B-0A29-5325-1613-3082518F4567'){
		html += '<option value="党校课" >党校课</option>';
	}else{
		html += '<option value="公共课" >公共课</option>' +
				  '<option value="必修课" >必修课</option>' +
				  '<option value="选修课" >选修课</option>' +
				  '<option value="专题课" >专题课</option>';
	}
	html += '</select>'
	document.getElementById("tt2").innerHTML = html;
	document.getElementById("tname2").innerHTML = "课程类型";
}
</script>
</body>

</html>
