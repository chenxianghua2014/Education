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
							var html = " <select  style='width:200px;' id='studenttwoGzmc'  name='studenttwoGzmc'  >"
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
		<form action="updateStudentSava" method="post" id="fff" class="registerform"  name="fff">
			<input type="hidden" id="cj" name="cj" value="${cj}" /> 
			<input type="hidden" id="companyId" name="companyId" value="${companyId}" /> 
			<input type="hidden" id="pageId" name="pageId" value="${pageId}" /> 
			<input id="studentId" name="studentId" type="hidden" class="inputText" style="width:300px;"  value="${student.studentId}" />
			<input id="studentCompanyid" name="studentCompanyid" type="hidden" class="inputText" style="width:300px;"  value="${student.studentCompanyid}" />
			<input id="studentoneId" name="studentoneId" type="hidden" class="inputText" style="width:300px;"  value="${studentone.studentoneId}" />
			<input id="studenttwoId" name="studenttwoId" type="hidden" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoId}" />
			<input id="studenthreeId" name="studenthreeId" type="hidden" class="inputText" style="width:300px;"  value="${studenthree.studenthreeId}" />
			<input id="studentAt" name="studentAt" type="hidden" class="inputText" style="width:300px;"  value="${student.studentAt}" />
			<input id="studentDel" name="studentDel" type="hidden" class="inputText" style="width:300px;"  value="${student.studentDel}" />
			<input id="studentoneAt" name="studentoneAt" type="hidden" class="inputText" style="width:300px;"  value="${studentone.studentoneAt}" />
			<input id="studentoneDel" name="studentoneDel" type="hidden" class="inputText" style="width:300px;"  value="${studentone.studentoneDel}" />
			<input id="studenttwoAt2" name="studenttwoAt2" type="hidden" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoAt2}" />
			<input id="studenttwoDel2" name="studenttwoDel2" type="hidden" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoDel2}" />
			<input id="studenthreeAt" name="studenthreeAt" type="hidden" class="inputText" style="width:300px;"  value="${studenthree.studenthreeAt}" />
			<input id="studenthreeDel" name="studenthreeDel" type="hidden" class="inputText" style="width:300px;"  value="${studenthree.studenthreeDel}" />
			<table>
				<tr>
					<td colspan="4" class="subtitle">编辑学员信息</td>
				</tr>
				<tbody>

					<tr >
						<th width="150">学员编号:</th>
						<td><input id="studentSeq" readonly="readonly" name="studentSeq" type="text" class="inputText" style="width:300px;"  value="${student.studentSeq }" /></td>
						<th width="150">学员密码:</th>
						<td><input id="studentPassword" name="studentPassword" type="password" class="inputText" style="width:300px;"  value="${student.studentPassword }" /></td>
					</tr>
					<tr >
						<th width="150">学员姓名:</th>
						<td><input id="studentName" name="studentName" type="text" class="inputText" style="width:300px;"  value="${student.studentName }" /></td>
						<th width="150">学员人员类别:</th>
						<td><input id="studentCategory" name="studentCategory" type="text" class="inputText" style="width:300px;"  value="${student.studentCategory }" /></td>
					</tr>
					<tr >
						<th width="150">学员级别:</th>
						<td><input id="studentRank" name="studentRank" type="text" class="inputText" style="width:300px;"  value="${student.studentRank }" /></td>
						<th width="150">手机号码:</th>
						<td><input id="studentTelephone" name="studentTelephone" type="text" class="inputText" style="width:300px;"  value="${student.studentTelephone }" /></td>
					</tr>
					<tr >
						<th width="150">电子邮箱:</th>
						<td><input id="studentEmail" name="studentEmail" type="text" class="inputText" style="width:300px;"  value="${student.studentTelephone }" /></td>
						<th width="150">人员类别:</th>
						<td><input id="studentoneRylb" name="studentoneRylb" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneRylb }" /></td>
					</tr>
					<tr >
						<th width="150">身份证号:</th>
						<td><input readonly="readonly" id="studentoneSfzh" name="studentoneSfzh" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneSfzh }" /></td>
						<th width="150">性别:</th>
						<td>
						<select style="width:200px;" id="studentoneXb" name="studentoneXb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${ax}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studentone.studentoneXb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">年龄 :</th>
						<td><input id="studentoneAge" name="studentoneAge" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneAge }" /></td>
						<th width="150">学历:</th>
						<td>
						<select style="width:200px;" id="studentoneXl" name="studentoneXl">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${am}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studentone.studentoneXl==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">学位 :</th>
						<td>
						<select style="width:200px;" id="studentoneXw" name="studentoneXw">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${an}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studentone.studentoneXw==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">政治面貌:</th>
						<td>
						<select style="width:200px;" id="studentoneZzmm" name="studentoneZzmm">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${AT}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studentone.studentoneZzmm==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">用工形式 :</th>
						<td>
						<select style="width:200px;" id="studentoneYgxs" name="studentoneYgxs">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${XA}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studentone.studentoneYgxs==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">行政级别:</th>
						<td><input id="studentoneXzjb" name="studentoneXzjb" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneXzjb}" /></td>
					</tr>
					<tr >
						<th width="150">专业技术职务:</th>
						<td><input id="studentoneZyjszw" name="studentoneZyjszw" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneZyjszw}" /></td>
						<th width="150">工人技术职务:</th>
						<td><input id="studentoneGrjszw" name="studentoneGrjszw" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneGrjszw}" /></td>
					</tr>
					<tr >
						<th width="150">所学专业:</th>
						<td><input id="studentoneSxzy" name="studentoneSxzy" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneSxzy}" /></td>
						<th width="150">毕（肄）业:</th>
						<td>
						<select style="width:200px;" id="studentoneByy" name="studentoneByy">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${HY}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studentone.studentoneByy==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">毕（肄）业学校或单位:</th>
						<td><input id="studentoneByyxx" name="studentoneByyxx" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneByyxx}" /></td>
						<th width="150">院（系）:</th>
						<td><input id="studentoneYx" name="studentoneYx" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneYx}" /></td>
					</tr>
					<tr >
						<th width="150">学位授予单位:</th>
						<td><input id="studentoneXwsydw" name="studentoneXwsydw" type="text" class="inputText" style="width:300px;"  value="${studentone.studentoneXwsydw}" /></td>
						<th width="150">全日制或在职:</th>
						<td>
						<select style="width:200px" id="studentoneQrzhzz" name="studentoneQrzhzz">
							<option value="">请选择</option>
							<option value=1 ${studentone.studentoneQrzhzz==1?"selected":""}>全日制</option>
							<option value=2 ${studentone.studentoneQrzhzz==2?"selected":""}>在职</option>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">是否当前学历:</th>
						<td>
						<select style="width:200px" id="studentoneSfdqxl" name="studentoneSfdqxl">
							<option value="">请选择</option>
							<option value=1 ${studentone.studentoneSfdqxl==1?"selected":""}>是</option>
							<option value=2 ${studentone.studentoneSfdqxl==2?"selected":""}>否</option>
						</select>
						</td>
						<th width="150">是否当前学位:</th>
						<td>
						<select style="width:200px" id="studentoneSfdqxw" name="studentoneSfdqxw">
							<option value="">请选择</option>
							<option value=1 ${studentone.studentoneSfdqxw==1?"selected":""}>是</option>
							<option value=2 ${studentone.studentoneSfdqxw==2?"selected":""}>否</option>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">出生日期:</th>
						<td>
						<input name="studentoneCsri" id="studentoneCsri" value="${studentone.studentoneCsri}" type="text" 
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">参加工作时间:</th>
						<td>
						<input name="studentoneCjgzsj" id="studentoneCjgzsj" value="${studentone.studentoneCjgzsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">现单位工作时间:</th>
						<td>
						<input name="studentoneXdwgzsj" id="studentoneXdwgzsj" value="${studentone.studentoneXdwgzsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">入学时间:</th>
						<td>
						<input name="studentoneRxsj" id="studentoneRxsj" value="${studentone.studentoneRxsj}"
							type="text" class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">毕（肄）业时间:</th>
						<td>
						<input name="studentoneByysj" id="studentoneByysj" value="${studentone.studentoneByysj}" type="text"
							class="inputText" onclick='WdatePicker()' /></td>
						<th width="150">职务类别:</th>
						<td>
						<select style="width:200px;" id="studenttwoZwlb" name="studenttwoZwlb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${BQ}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenttwo.studenttwoZwlb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						
					</tr>
					<tr >
						<th width="150">行政职务名称:</th>
						<td><input id="studenttwoXzzwmc" name="studenttwoXzzwmc" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoXzzwmc}" /></td>
						<th width="150">党内职务名称:</th>
						<td><input id="studenttwoDnzwmc" name="studenttwoDnzwmc" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoDnzwmc}" /></td>
					</tr>
					<tr >
						<th width="150">待遇级别:</th>
						<td>
						<select style="width:200px;" id="studenttwoDyjb" name="studenttwoDyjb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${OZ}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenttwo.studenttwoDyjb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">职务属性:</th>
						<td>
						<select style="width:200px;" id="studenttwoZwsx" name="studenttwoZwsx">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${IR}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenttwo.studenttwoZwsx==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">是否当前职务:</th>
						<td>
						<select style="width:200px" id="studenttwoSfdqzw" name="studenttwoSfdqzw">
							<option value="">请选择</option>
							<option value=1 ${studenttwo.studenttwoSfdqzw==1?"selected":""}>是</option>
							<option value=2 ${studenttwo.studenttwoSfdqzw==2?"selected":""}>否</option>
						</select>
						</td>
						<th width="150">是否最初任同职级:</th>
						<td>
						<select style="width:200px" id="studenttwoSfzcrtzj" name="studenttwoSfzcrtzj">
							<option value="">请选择</option>
							<option value=1 ${studenttwo.studenttwoSfzcrtzj==1?"selected":""}>是</option>
							<option value=2 ${studenttwo.studenttwoSfzcrtzj==2?"selected":""}>否</option>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">专业技术资格名称:</th>
						<td><input id="studenttwoZyjszgmc" name="studenttwoZyjszgmc" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoZyjszgmc}" /></td>
						<th width="150">专业技术任职资格:</th>
						<td><input id="studenttwoZyjsrzzg" name="studenttwoZyjsrzzg" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoZyjsrzzg}" /></td>
					</tr>
					<tr >
						<th width="150">专业类别:</th>
						<td>
						<select style="width:200px;" id="studenttwoZylb" name="studenttwoZylb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${YC}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenttwo.studenttwoZylb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">聘任专业技术职务名称:</th>
						<td><input id="studenttwoPrzyjszwmc" name="studenttwoPrzyjszwmc" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoPrzyjszwmc}" /></td>
					</tr>
					<tr >
						<th width="150">是否当前专业技术职务:</th>
						<td>
						<select style="width:200px" id="studenttwoSfdqzyjszw" name="studenttwoSfdqzyjszw">
							<option value="">请选择</option>
							<option value=1 ${studenttwo.studenttwoSfdqzyjszw==1?"selected":""}>是</option>
							<option value=2 ${studenttwo.studenttwoSfdqzyjszw==2?"selected":""}>否</option>
						</select>
						</td>
						<th width="150">参加党派时间:</th>
						<td>
						<input name="studenttwoCjdpsj" id="studenttwoCjdpsj" value="${studenttwo.studenttwoCjdpsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						
					</tr>
					<tr >
						<th width="150">工人技术资格:</th>
						<td>
						<select style="width:200px;" id="studenttwoGrjszg" name="studenttwoGrjszg">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${XJ}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenttwo.studenttwoGrjszg==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">聘任工人技术职务:</th>
						<td><input id="studenttwoPrgrjszw" name="studenttwoPrgrjszw" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoPrgrjszw}" /></td>
					</tr>
					<tr >
						<th width="150">所在单位:</th>
						<td><input id="studenttwoSzdw" name="studenttwoSzdw" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoSzdw}" /></td>
						<th width="150">从事工作或担任职务:</th>
						<td><input id="studenttwoCsgzhdrzw" name="studenttwoCsgzhdrzw" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoCsgzhdrzw}" /></td>
					</tr>
					<tr >
						<th width="150">异常类别:</th>
						<td>
						<select style="width:200px;" id="studenttwoYclb" name="studenttwoYclb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${CA}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenttwo.studenttwoYclb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">异常原因:</th>
						<td><input id="studenttwoYcyy" name="studenttwoYcyy" type="text" class="inputText" style="width:300px;"  value="${studenttwo.studenttwoYcyy}" /></td>
					</tr>
					<tr >
						<th width="150">任职时间:</th>
						<td>
						<input name="studenttwoRzsj" id="studenttwoRzsj" value="${studenttwo.studenttwoRzsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">免职时间:</th>
						<td>
						<input name="studenttwoMzsj" id="studenttwoMzsj" value="${studenttwo.studenttwoMzsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">聘任起始时间:</th>
						<td>
						<input name="studenttwoPrqssj" id="studenttwoPrqssj" value="${studenttwo.studenttwoPrqssj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">聘任终止时间:</th>
						<td>
						<input name="studenttwoPrzzsj" id="studenttwoPrzzsj" value="${studenttwo.studenttwoPrzzsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">取得资格时间:</th>
						<td>
						<input name="studenttwoQdzgsj" id="studenttwoQdzgsj" value="${studenttwo.studenttwoQdzgsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">聘任时间:</th>
						<td>
						<input name="studenttwoPrsj" id="studenttwoPrsj" value="${studenttwo.studenttwoPrsj}" type="text" 
							class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">个人简历起始时间:</th>
						<td>
						<input name="studenttwoGrjlqssj" id="studenttwoGrjlqssj" value="${studenttwo.studenttwoGrjlqssj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">个人简历结束时间:</th>
						<td>
						<input name="studenttwoGrjljssj" id="studenttwoGrjljssj" value="${studenttwo.studenttwoGrjljssj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">工种名称:</th>
						<td>
						<select style="width:200px;" id="studenttwoGzmcSx" name="studenttwoGzmcSx" onchange="getDataJA()">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${JATop}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}"
											${studenttwo.studenttwoGzmcSx==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						
						<th width="150">工种名称筛选（子集）:</th>
						<td>
						<div id="studenttwoGzmcId">
						<select style="width:200px;" id="studenttwoGzmc" name="studenttwoGzmc" onchange="getDataJA()">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${JA}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}"
											${studenttwo.studenttwoGzmc==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</div>
						</td>
					</tr>
					<tr >
						<th width="150">留学状态:</th>
						<td>
						<select style="width:200px;" id="studenthreeLxzt" name="studenthreeLxzt">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${XR}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeLxzt==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">派出单位:</th>
						<td><input id="studenthreePcdw" name="studenthreePcdw" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreePcdw}" /></td>
					</tr>
					<tr >
						<th width="150">专业:</th>
						<td><input id="studenthreeZy" name="studenthreeZy" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreeZy}" /></td>
						<th width="150">留学身份:</th>
						<td>
						<select style="width:200px;" id="studenthreeLxsf" name="studenthreeLxsf">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${XS}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeLxsf==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">留学国别:</th>
						<td>
						<select style="width:200px;" id="studenthreeLxgb" name="studenthreeLxgb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${AD}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeLxgb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">培训项目名称:</th>
						<td><input id="studenthreePxxmmc" name="studenthreePxxmmc" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreePxxmmc}" /></td>
					</tr>
					<tr >
						<th width="150">培训天数:</th>
						<td><input id="studenthreePxts" name="studenthreePxts" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreePxts}" /></td>
						<th width="150">培训类型:</th>
						<td>
						<select style="width:200px;" id="studenthreePxlx" name="studenthreePxlx">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${YG}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreePxlx==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">培训渠道:</th>
						<td>
						<select style="width:200px;" id="studenthreePxqd" name="studenthreePxqd">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${YH}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreePxqd==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">离退类别:</th>
						<td>
						<select style="width:200px;" id="studenthreeLtlb" name="studenthreeLtlb">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${HD}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeLtlb==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">国别及地区:</th>
						<td>
						<select style="width:200px;" id="studenthreeGbjdq" name="studenthreeGbjdq">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${AD}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeGbjdq==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">出国（境）事由:</th>
						<td><input id="studenthreeCgsy" name="studenthreeCgsy" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreeCgsy}" /></td>
					</tr>
					<tr >
						<th width="150">出国所去单位:</th>
						<td><input id="studenthreeCgsqdw" name="studenthreeCgsqdw" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreeCgsqdw}" /></td>
						<th width="150">出国派出单位:</th>
						<td><input id="studenthreeCgpcdw" name="studenthreeCgpcdw" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreeCgpcdw}" /></td>
					</tr>
					<tr >
						<th width="150">备注:</th>
						<td><input id="studenthreeBj" name="studenthreeBj" type="text" class="inputText" style="width:300px;"  value="${studenthree.studenthreeBj}" /></td>
						<th width="150">领导干部标识:</th>
						<td>
						<select style="width:200px;" id="studenthreeLdgbbz" name="studenthreeLdgbbz">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${OC}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeLdgbbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">领导后备标识:</th>
						<td>
						<select style="width:200px;" id="studenthreeLdhbbz" name="studenthreeLdhbbz">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${OD}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeLdhbbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">型号干部标识:</th>
						<td>
						<select style="width:200px;" id="studenthreeXhgbbz" name="studenthreeXhgbbz">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${OE}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeXhgbbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">董监事标识:</th>
						<td>
						<select style="width:200px;" id="studenthreeDjsbz" name="studenthreeDjsbz">
							<option value="" selected="selected">请选择</option>
								<c:forEach items="${OG}" var="maps" varStatus="status">
									<option value="${maps.dictionaryCode}" ${studenthree.studenthreeDjsbz==maps.dictionaryCode?"selected":""}>${maps.dictionaryDescribe}</option>
								</c:forEach>
						</select>
						</td>
						<th width="150">学员状态:</th>
						<td>
						<select style="width:200px" id="studentType" name="studentType">
							<option value="" >清选择</option>
							<option value=1 ${student.studentType==1?"selected":""}>在职</option>
							<option value=2 ${student.studentType==2?"selected":""}>游离</option>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">留学时间:</th>
						<td>
						<input name="studenthreeLxsj" id="studenthreeLxsj" value="${studenthree.studenthreeLxsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">离（退）休时间:</th>
						<td>
						<input name="studenthreeLtxsj" id="studenthreeLtxsj" value="${studenthree.studenthreeLtxsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">出国时间:</th>
						<td>
						<input name="studenthreeCgsj" id="studenthreeCgsj" value="${studenthree.studenthreeCgsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">计划回国时间:</th>
						<td>
						<input name="studenthreeJhhgsj" id="studenthreeJhhgsj" value="${studenthree.studenthreeJhhgsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
					</tr>
					<tr >
						<th width="150">实际回国时间:</th>
						<td>
						<input name="studenthreeSjhgsj" id="studenthreeSjhgsj" value="${studenthree.studenthreeSjhgsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						<th width="150">学习币:</th>
						<td><input id="studentCoin" name="studentCoin" type="text" class="inputText" style="width:300px;"  value="${student.studentCoin}" /></td>
					</tr>
					<tr >
						<th width="150">取得时间:</th>
						<td>
						<input name="studenttwoQdsj" id="studenttwoQdsj" value="${studenttwo.studenttwoQdsj}" type="text"
							class="inputText" onclick='WdatePicker()' />
						</td>
						
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
		
		var studentPassword = document.getElementById("studentPassword");
		var studentName = document.getElementById("studentName");
		var studentCategory = document.getElementById("studentCategory");
		
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
		$("#fff").submit();
		
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
