<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑学员信息</title>
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
	

	<script type="text/javascript">
	/* 二级联动查询 */
	var flag = 0;
	function getData() {
		var studentDepartment = $("#studentDepartment").val();
		
		$.ajax({
					url : "twoLevels",
					data : {
						departmentId : studentDepartment
					},
					type : "post",
					error : function() {
						alert("error occured!!!");
					},
					success : function(data) {
						if (data.length > 0) {
						 	document.getElementById("departmentIds").style.display="";
							$("#departmentIds").empty();
							var html = " <select id='studentDepartments'  name='studentDepartments'  >"
									+ "<option value='0' selected=\"selected\">---请选择---</option>";
							
							for ( var i = 0; i < data.length; i++) {
								var xValue = data[i].id;
								var xText = data[i].name;
								html += "<option value='"+xValue+"' >" + xText
										+ "</option>";
							}
						
							html += " </select>";
							$("#departmentIds").append(html);
							flag =1;
						} else{
							document.getElementById("departmentIds").style.display="none";
							flag =0;
						}
					}
				});
	}

	/* 二级联动查询      工种名称（子集） */
	function getDataJA() {
		var studenttwoGzmcSx = $("#studenttwoGzmcSx").val();
		$
				.ajax({
					url : "twoDataJA",
					data : {
						studenttwoGzmcSx : studenttwoGzmcSx
					},
					type : "post",
					error : function() {
						alert("error occured!!!");
					},
					success : function(data) {
						if (data.length > 0) {
							$("#studenttwoGzmcId").empty();
							var html = " <select  style='width:200px;' id='studenttwoGzmc'  name='studenttwo.studenttwoGzmc'  >"
									+ "<option value='' selected=\"selected\">请选择</option>";

							for ( var i = 0; i < data.length; i++) {
								var xValue = data[i].id;
								var xText = data[i].name;
								html += "<option value='"+xValue+"' >" + xText
										+ "</option>";
							}
							html += " </select>";
							$("#studenttwoGzmcId").append(html);
						} else {
							var html = "";
							document.getElementById("studenttwoGzmcId").innerHTML = "";
						}
					}
				});
	}
	</script>
</head>
<body>
<input type="hidden" id="purl" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/UploadFile")%>" /> 
<input type="hidden" id="purl1" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/")%>" /> 
	<div class="title">当前位置:学员管理>编辑学员信息</div>
	<div class="editBlock">
		<form action="" method="post" id="fff" class="registerform"  name="fff">
			<input type="hidden" id="cj" name="cj" value="${cj}" /> 
			<input type="hidden" id="companyId" name="companyId" value="${companyId}" /> 
			<input type="hidden" id="studentType" name="studentType" value="1" /> 
			<table>
				<tr>
					<td colspan="4" class="subtitle">编辑学员信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">学员编号:</th>
						<td><input id="studentSeq" name="studentSeq" type="text" class="inputText" style="width:300px;"  value="" /></td>
						<th width="150">学员密码:</th>
						<td><input id="studentPassword" name="studentPassword" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>
					<tr >
						<th width="150">学员姓名:</th>
						<td><input id="studentName" name="studentName" type="text" class="inputText" style="width:300px;"  value="" /></td>
						<th width="150">身份证号:</th>
						<td><input id="studentoneSfzh" name="studentoneSfzh" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>
					<tr >
						<th width="150">性别:</th>
						<td>
						<select style="width:200px;" id="studentoneXb" name="studentoneXb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${ax}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${student.studentone.studentoneXb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">年龄 :</th>
						<td><input id="studentoneAge" name="studentoneAge" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>
					<tr >
						<th width="150">出生日期:</th>
						<td>
						<input name="studentoneCsri" id="studentoneCsri" value="${student.studentone.studentoneCsri}" type="text" 
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">政治面貌:</th>
						<td>
						<select style="width:200px;" id="studentoneZzmm" name="studentoneZzmm">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${AT}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${student.studentone.studentoneZzmm==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">参加工作时间:</th>
						<td>
						<input name="studentoneCjgzsj" id="studentoneCjgzsj" value="${student.studentone.studentoneCjgzsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">学员级别:</th>
						<td><input id="studentRank" name="studentRank" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>
					<tr >
						<th width="150">学历:</th>
						<td>
						<select style="width:200px;" id="studentoneXl" name="studentoneXl">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${am}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${student.studentone.studentoneXl==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">学位 :</th>
						<td>
						<select style="width:200px;" id="studentoneXw" name="studentoneXw">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${an}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${student.studentone.studentoneXw==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">毕（肄）业学校或单位:</th>
						<td><input id="studentoneByyxx" name="studentoneByyxx" type="text" class="inputText" style="width:300px;"  value="" /></td>
						<th width="150">从事工作或担任职务:</th>
						<td><input id="studenttwoCsgzhdrzw" name="studenttwoCsgzhdrzw" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>
					
					<tr >
						<th width="150">行政职务名称:</th>
						<td><input id="studenttwoXzzwmc" name="studenttwoXzzwmc" type="text" class="inputText" style="width:300px;"  value="" /></td>
						<th width="150">行政级别:</th>
						<td><input id="studentoneXzjb" name="studentoneXzjb" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>	
					<tr >
						<th width="150">党内职务名称:</th>
						<td><input id="studenttwoDnzwmc" name="studenttwoDnzwmc" type="text" class="inputText" style="width:300px;"  value="" /></td>
						<th width="150">专业技术职务:</th>
						<td><input id="studentoneZyjszw" name="studentoneZyjszw" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>
					<tr >
						<th width="150">工人技术职务:</th>
						<td><input id="studentoneGrjszw" name="studentoneGrjszw" type="text" class="inputText" style="width:300px;"  value="" /></td>
						<th width="150">学习币:</th>
						<td><input id="studentCoin" name="studentCoin" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr>
					<tr >
						<th width="150">所属部门:</th>
						<td>
							<select style="width:200px;" id="studentDepartment"
									name="studentDepartment" onchange="getData()">

								<option value="0" selected="selected">请选择</option>

								<c:forEach items="${bm}" var="maps" varStatus="status">
								<option value="${maps.departmentId}">${maps.epartmentName}</option>
								</c:forEach>
							</select>
						</td>
						
						<th width="150">所属科室:</th>
						<td>
							<div id="departmentIds" name="departmentIds" style="display:none"></div>
						</td>
						
					</tr>
					<tr >
						<th width="150">学员人员类别:</th>
						<td><input id="studentCategory" name="studentCategory" type="text" class="inputText" style="width:300px;"  value="" /></td>
						
					</tr>
				</tbody>
				<tr>
				
						<td colspan="4" class="toolbar"><input type="button"
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
		 $(function(){
	//$(".registerform").Validform();  //就这一行代码！;
	$.Tipmsg.tit="提示信息";
	var demo=$(".registerform").Validform();
	demo.tipmsg.s="请填写内容";
});

	function saveNew(){
		var cj = document.getElementById("cj").value;
		var companyId = document.getElementById("companyId").value;
		var studentSeq = document.getElementById("studentSeq").value;
		var studentPassword = document.getElementById("studentPassword").value;
		var studentName = document.getElementById("studentName").value;
		var studentCategory = document.getElementById("studentCategory").value;
		var url = document.getElementsByName("studentDepartment")[0];
		var studentDepartments = document.getElementsByName("studentDepartments")[0];
		 var studentoneSfzh = document.getElementById("studentoneSfzh").value;
		if(studentSeq==""){
			alert("请输入学员编号！");
			return;
		}
		if(studentPassword==""){
			alert("请输入密码！");
			return;
		}
		if(studentName==""){
			alert("请输入学员姓名！");
			return;
		}
		if(studentCategory==""){
			alert("请输入学员人员类别！");
			return;
		}
		if(flag == 1){
			var ifStudentDepartments=studentDepartments.options[studentDepartments.options.selectedIndex].value;
			if(ifStudentDepartments=="0"||ifStudentDepartments==""){
				alert("请选择科室！");
				return; 
			}
		}
/* 			if(departmentIds=="0"||departmentIds==""){
			alert("请选择科室！");
			return;
		} */
 			if(url!=undefined){
			var departmentIds = url.options[url.options.selectedIndex].value;
			if(departmentIds=="0"){
			alert("请选择部门！");
			return;
		}
		}else{
			alert("请选择部门！");
			return;
		} 
		 $.ajax({
      	type: "POST",
     	url: "studentSava",
      	data: $('#fff').serialize(),
      	success: function(msg){
      		 if(msg==99999){
      			alert( "身份证号已存在" );
      		 } else if(msg==1){
       		 	alert( "学员编号已存在" );
       		 }else if(msg==3){
       			alert( "添加失败" ); 
       		 } else if (msg==2){
       		 	alert( "添加成功" );
       			var url = "<%=path%>/studentxyht?mark=1&page=1&cj="+cj+"&organizationId="+companyId;
        		window.location.href = url;
       		 }
     		}
  		});
			
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
