<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>合并组织结构</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>


<!-- 校验 -->
<script type="text/javascript" src="<%=path%>/js/Validform_v5.3.2_min.js"></script>
	

</head>
<body>
	<div class="title">当前位置:系统管理>合并组织结构</div>
	<div class="editBlock">
		<form action="updateOrganizationMerger" method="post" class="registerform"  id="fff"   name="fff" >
			<table>
				<tr>
					<td colspan="2" class="subtitle">合并组织结构信息</td>
				</tr>
				<tbody>
					<tr>
							<th width="150">
								组织名称:
							</th>
							<td>
								<input id="organizationDwmc" name="organizationDwmc" type="text"
									class="inputText" style="width: 300px;" value=""  datatype="*" errormsg="不能为空！"/>
							</td>
						</tr>
						<tr>
							<th width="150">
								单位代码:
							</th>
							<td>
								<input id="organizationDwdm" name="organizationDwdm"
									type="text" class="inputText" style="width: 300px;"
									value="" />
							</td>
						</tr>
						<tr>
							<th width="150">
								单位负责人:
							</th>
							<td>
								<input id="organizationDwfzr" name="organizationDwfzr" type="text"
									class="inputText" style="width: 300px;" value="" />
							</td>
						</tr>
						<tr>
							<th width="150">
								联系电话:
							</th>
							<td>
								<input id="organizationLxrdh" name="organizationLxrdh" type="text"
									class="inputText" style="width: 300px;" value="" />
							</td>
						</tr>
						<tr>
							<th width="150">
								邮箱:
							</th>
							<td>
								<input id="organizationLxremail" name="organizationLxremail" type="text"
									class="inputText" style="width: 300px;" value="" />
							</td>
						</tr>
					<tr>
						<th width="150">合并层级:</th>
						<td>
							<select id="epartmentRank"  name="epartmentRank" class="inputText" onchange="bumen()">
								<option value=" ">请选择</option>
								<option value="4">二级</option>
								<option value="5">三级</option>
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
		 var organizationDwmc = document.getElementById('organizationDwmc').value;
		 if(organizationDwmc==""){
			 alert("请输入组织名称");
			 return false;
		 }
		 if(mark==4){
			 var hbbmid = document.getElementsByName("broid")[0];
			 var hbid = hbbmid.options[hbbmid.options.selectedIndex].value;
			 var hbbmid1 = document.getElementsByName("organizationUpid")[0];
			 var hbid1 = hbbmid1.options[hbbmid1.options.selectedIndex].value;
			 if(hbid1==hbid){
				 alert("合并部门不能相同");
			 }else{
				 document.fff.submit();
			 }
		 }else{
			 var hbbmid = document.getElementsByName("broid")[0];
			 var hbid = hbbmid.options[hbbmid.options.selectedIndex].value;
			 var hbbmid1 = document.getElementsByName("organizationUpid")[0];
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
	var html= "";
	var html1= "";
	$.post("HBOrganization", {
			Action : "post"
		}, function(data, textStatus) {
			if (data.length > 1 || mark==" ") {
				if (mark == 4) {
					html += '<select id="organizationUpid" name="organizationUpid" >';
					
					html1 += '<select id="broid" name="broid" >';
				
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';
						html1 += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';

					}

					html += '</select>'
					html1 += '</select>'
					document.getElementById("mb").innerHTML = html;
					document.getElementById("mbt").innerHTML = "选择合并组织结构";
					document.getElementById("hb").innerHTML = html1;
					document.getElementById("hbt").innerHTML = "选择合并组织结构";
					
					
				} else if (mark == 5){
					
					html += '<select id="typeone" name="typeone" onchange="CUrltype()">';
					html += '<option value="" >请选择</option>';
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';

					}

					html += '</select>'

					document.getElementById("hb").innerHTML = html;
					document.getElementById("hbt").innerHTML = "选择二级组织";
				}
			}else{
				alert("没有二级组织，或二级组织不到两个");
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
		$.post("HBSOrganization", {
			Action : "post",
			id : urlpoing
		}, function(data, textStatus) {
			if (data.length > 1 ) {
					html += '<select id="organizationUpid" name="organizationUpid" >';
					
					html1 += '<select id="broid" name="broid" >';
					
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';
						html1 += '<option value="' + newdata.organizationId
								+ '" >' + newdata.organizationDwmc
								+ '</option>';

					}

					html += '</select>'
					html1 += '</select>'

					document.getElementById("mb").innerHTML = html1;
					document.getElementById("mbt").innerHTML = "选择合并组织结构";
					document.getElementById("mb1").innerHTML = html;
					document.getElementById("mbt1").innerHTML = "选择合并组织结构";
					
				
			}else{
				alert("没有三级组织，或三级组织不到两个");
			}

		}, "json");
	}
	 
	

</script>
</body>

</html>
