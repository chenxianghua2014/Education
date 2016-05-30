<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>合并部门</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>


<!-- 校验 -->
<link rel="stylesheet" href="<%=path%>/js/validator-0.7.3/jquery.validator.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/local/zh_CN.js"></script>
	

</head>
<body>
	<div class="title">当前位置:部门管理>合并部门</div>
	<div class="editBlock">
		<form action="updateDepartmentMerger" method="post" autocomplete="off"  id="fff"   name="fff" 
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="organizationId" name="organizationId" value="${department.organizationId}" /> 
			<table>
				<tr>
					<td colspan="2" class="subtitle">合并部门信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150">合并层级:</th>
						<td>
							<select id="epartmentRank"  name="epartmentRank" class="inputText" onchange="bumen()">
								<option value=" ">请选择</option>
								<option value="4">部门</option>
								<option value="5">科室</option>
							</select>
						</td>
					</tr>
					
					<tr>
						<th width="150" id = "hbt"></th>
						<td id="hb">
							
						</td>
					</tr>
						<tr>
						<th width="150" id = "mbt"></th>
						<td id="mb">
							
						</td>
					</tr>
						<tr>
						<th width="150" id = "mbt1"></th>
						<td id="mb1">
							
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
	 function saveNew(){
		 var mark = document.getElementById('epartmentRank').value;
		 if(mark==4){
			 var hbbmid = document.getElementsByName("hbbmid")[0];
			 var hbid = hbbmid.options[hbbmid.options.selectedIndex].value;
			 var hbbmid1 = document.getElementsByName("epartmentUpid")[0];
			 var hbid1 = hbbmid1.options[hbbmid1.options.selectedIndex].value;
			 if(hbid1==hbid){
				 alert("合并部门不能相同");
			 }else{
				 document.fff.submit();
			 }
		 }else{
			 var hbbmid = document.getElementsByName("hbbmid")[0];
			 var hbid = hbbmid.options[hbbmid.options.selectedIndex].value;
			 var hbbmid1 = document.getElementsByName("epartmentUpid")[0];
			 var hbid1 = hbbmid1.options[hbbmid1.options.selectedIndex].value;
			 if(hbid1==hbid){
				 alert("合并科室不能相同");
			 }else{
				 document.fff.submit();
			 }
		 }
			
	} 
	 
	 
	function bumen() {
	document.getElementById("hb").innerHTML = "";
	document.getElementById("hbt").innerHTML = "";
	document.getElementById("mb").innerHTML = "";
	document.getElementById("mbt").innerHTML = "";
	document.getElementById("mb1").innerHTML = "";
	document.getElementById("mbt1").innerHTML = "";
	var mark = document.getElementById('epartmentRank').value;
	var oid = document.getElementById('organizationId').value;
	var html= "";
	var html1= "";
	$.post("HBtype", {
			Action : "post",
			organizationId : oid,
			epartmentRank:4
		}, function(data, textStatus) {
			if (data.length > 1 || mark==" ") {
				if (mark == 4) {
					html += '<select id="epartmentUpid" name="epartmentUpid" >';
					
					html1 += '<select id="hbbmid" name="hbbmid" >';
				
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.departmentId
								+ '" >' + newdata.epartmentName
								+ '</option>';
						html1 += '<option value="' + newdata.departmentId
								+ '" >' + newdata.epartmentName
								+ '</option>';

					}

					html += '</select>'
					html1 += '</select>'

					document.getElementById("hb").innerHTML = html1;
					document.getElementById("hbt").innerHTML = "选择合并部门";
					document.getElementById("mb").innerHTML = html;
					document.getElementById("mbt").innerHTML = "选择目标部门";
					
				} else if (mark == 5){
					
					html += '<select id="typeone" name="typeone" onchange="CUrltype()">';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.departmentId
								+ '" >' + newdata.epartmentName
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("hb").innerHTML = html;
					document.getElementById("hbt").innerHTML = "选择部门";
				}
			}else{
				alert("没有部门，或部门不到两个");
			}

		}, "json");
	
} 	
	
	function CUrltype(){
		document.getElementById("mb1").innerHTML = "";
		document.getElementById("mbt1").innerHTML = "";
		document.getElementById("mb").innerHTML = "";
		document.getElementById("mbt").innerHTML = "";
		var url = document.getElementsByName("typeone")[0];
		var urlpoing = url.options[url.options.selectedIndex].value;
		var html= "";
		var html1= "";
		$.post("HBKStype", {
			Action : "post",
			epartmentUpid : urlpoing
		}, function(data, textStatus) {
			if (data.length > 1 ) {
					html += '<select id="epartmentUpid" name="epartmentUpid" >';
					
					html1 += '<select id="hbbmid" name="hbbmid" >';
					
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.departmentId
								+ '" >' + newdata.epartmentName
								+ '</option>';
						html1 += '<option value="' + newdata.departmentId
								+ '" >' + newdata.epartmentName
								+ '</option>';

					}

					html += '</select>'
					html1 += '</select>'

					document.getElementById("mb").innerHTML = html1;
					document.getElementById("mbt").innerHTML = "选择合并科室";
					document.getElementById("mb1").innerHTML = html;
					document.getElementById("mbt1").innerHTML = "选择目标科室";
					
				
			}else{
				alert("没有科室，或科室不到两个");
			}

		}, "json");
	}
	 
	

</script>
</body>

</html>
