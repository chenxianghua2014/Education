<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>排序组织结构</title>
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
	<div class="title">当前位置:系统管理>排序组织结构</div>
	<div class="editBlock">
		<form action="updateOrganizationSort" method="post" autocomplete="off"  id="fff"   name="fff" 
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" name="sum"  id="sum" value="0"/>
			<table>
				<tr>
					<td colspan="2" class="subtitle">排序组织结构信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150">排序层级:</th>
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
		var sum = document.getElementById('sum').value;
		var ch =1;
		for(var i = 0 ;i<sum;i++){
			var sort = document.getElementById('oidsort['+i+']').value;
			if(sort=="" || sort==0 || sort==null){
				ch = 0;
				break;
			}
		}
		if(ch==1){
			document.fff.submit();
		}else{
			alert("有单位排序没有填写");
		}
	} 
	 
	 
	function bumen() {
	document.getElementById("hb").innerHTML = "";
	document.getElementById("hbt").innerHTML = "";
	document.getElementById("mb").innerHTML = "";
	document.getElementById("mbt").innerHTML = "";
	var mark = document.getElementById('epartmentRank').value;
	var html= "";
	$.post("HBOrganization", {
			Action : "post"
		}, function(data, textStatus) {
			if (data.length > 1 || mark==" ") {
				if (mark == 4) {
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html +='<input type="text" name="oidsort"  id="oidsort['+i+']"  value="'+newdata.cpcs+'" style="width: 30px;"/>' +
						newdata.organizationDwmc+
						'<input type="hidden" name="oidall"  id="oidall"  value="'+newdata.organizationId+'"/><br><br>';
					}
					document.getElementById('sum').value=data.length;
					document.getElementById("mb").innerHTML = html;
					document.getElementById("mbt").innerHTML = "排序";
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
				alert("没有二级组织，或少于两个");
			}

		}, "json");
	
} 	
	
	function CUrltype(){
		document.getElementById("mb").innerHTML = "";
		document.getElementById("mbt").innerHTML = "";
		var url = document.getElementsByName("typeone")[0];
		var urlpoing = url.options[url.options.selectedIndex].value;
		var html= "";
		$.post("HBSOrganization", {
			Action : "post",
			id : urlpoing
		}, function(data, textStatus) {
			if (data.length > 1 ) {
					for ( var i = 0; i < data.length; i++) {
						var newdata = data[i];

						html +='<input type="text" name="oidsort"  id="oidsort['+i+']"  value="'+newdata.cpcs+'" style="width: 30px;"/>' +
						newdata.organizationDwmc+
						'<input type="hidden" name="oidall"  id="oidall"  value="'+newdata.organizationId+'"/><br><br>';

					}
					
					document.getElementById('sum').value=data.length;
					document.getElementById("mb").innerHTML = html;
					document.getElementById("mbt").innerHTML = "排序";
			}else{
				alert("没有三级组织，或三级组织不到两个");
			}

		}, "json");
	}
	 
	

</script>
</body>

</html>
