<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改试题规则</title>
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

	</script>
</head>
<body>
<input type="hidden" id="purl" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/UploadFile")%>" /> 
<input type="hidden" id="purl1" name="newsSort" value="<%=request.getSession().getServletContext().getRealPath("/")%>" /> 
	<div class="title">当前位置:试卷管理>修改试卷规则</div>
	<div class="editBlock">
		<form action="" method="post" id="fff" class="registerform"  name="fff">
			<input type="hidden" id="courseId" name="courseId" value="${courseId}" /> 
			<input type="hidden" id="ruldId" name="ruldId" value="${ruldList.ruldId}" /> 
			<input type="hidden" id="ruldDel" name="ruldDel" value="${ruldList.ruldDel}" /> 
			<input type="hidden" id="syllabusId" name="syllabusId" value="${syllabusId}" /> 
			<table>
				<tr>
					<td colspan="4" class="subtitle">修改试卷规则</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">试卷名称:</th>
						<td><input id="ruldName" name="ruldName" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldName}" /></td>
						<th width="150">考试时间:</th>
						<td><input id="ruldTime" name="ruldTime" type="text" class="inputText"  style="width:300px;"  value="${ruldList.ruldTime}" /></td>
					</tr>
				
					<tr >
						<th width="150">考试次数:</th>
						<td>
						<select style="width:200px;" id="ruldBits" name="ruldBits">
							<option value="${ruldList.ruldBits}" selected="selected">请选择</option>
						<option value=1 ${ruldList.ruldBits==1 ?"selected":""}>一次</option>
						<option value=2 ${ruldList.ruldBits==2 ?"selected":""}>多次</option>
						</select>
						</td>
						<th width="150">是否可以查看答案 :</th>
						<td>
							<select style="width:200px;" id="ruldAnswers" name="ruldAnswers">
							<option value="${ruldList.ruldAnswers}" selected="selected">请选择</option>
						<option value=1 ${ruldList.ruldAnswers==1 ?"selected":""}>是</option>
						<option value=2 ${ruldList.ruldAnswers==2 ?"selected":""}>否</option>
						</select>
						</td>
					</tr>
					<tr >
						<th width="150">单选3选1题型名称:</th>
						<td>
						<input name="ruldName13" id="ruldName13"  type="text" style="width:300px;"  value="${ruldList.ruldName13}" class="inputText" />
						</td>
						<th width="150">单选4选1题型名称:</th>
						<td><input id="ruldName14" name="ruldName14" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldName14}" /></td>
					</tr>
					<tr >
						<th width="150">单选3选1  题数:</th>
						<td>
						<input id="ruldQuiznum13" name="ruldQuiznum13" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldQuiznum13}" />
						</td>
						<th width="150">单选4选1  题数 :</th>
							<td><input id="ruldQuiznum14" name="ruldQuiznum14" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldQuiznum14}" /></td>
					</tr>
					<tr >
							<th width="150">单选3选1试题分数:</th>
						<td><input id="ruldScore13" name="ruldScore13" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldScore13}" /></td>
						<th width="150">单选4选1试题分数:</th>
							<td><input id="ruldScore14" name="ruldScore14" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldScore14}" /></td>
					</tr>
					<tr >
						<th width="150">多选6选多个题型名称:</th>
						<td><input id="ruldName1M" name="ruldName1M" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldName1M}" /></td>
						<th width="150">判断题型名称:</th>
						<td><input id="ruldName2" name="ruldName2" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldName2}" /></td>
					</tr>
					<tr >
						<th width="150">多选6选多个题数:</th>
						<td><input id="ruldQuiznum1M" name="ruldQuiznum1M" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldQuiznum1M}" /></td>
						<th width="150">判断  题数:</th>
						<td><input id="ruldQuiznum2" name="ruldQuiznum2" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldQuiznum2}" /></td>
					</tr>	
					<tr >
						
						<th width="150">多选6选多个试题分数:</th>
						<td><input id="ruldScore1M" name="ruldScore1M" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldScore1M}" /></td>
					    <th width="150">判断试题分数:</th>
						<td><input id="ruldScore2" name="ruldScore2" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldScore2}" /></td>
					</tr>
					<tr >
						<th width="150">填空 题型名称:</th>
						<td><input id="ruldName3" name="ruldName3" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldName3}" /></td>
						<th width="150">主观题 题型名称:</th>
						<td><input id="ruldName4" name="ruldName4" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldName4}" /></td>
						
					</tr>
					<tr >
						<th width="150">填空  题数:</th>
						<td><input id="ruldQuiznum3" name="ruldQuiznum3" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldQuiznum3}" /></td>
						<th width="150">主观题  题数:</th>
						<td><input id="ruldQuiznum4" name="ruldQuiznum4" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldQuiznum4}" /></td>
					</tr>
					<tr >
						
						<th width="150">填空试题分数:</th>
						<td><input id="ruldScore3" name="ruldScore3" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldScore3}" /></td>
						<th width="150">主观题试题分数:</th>
						<td><input id="ruldScore4" name="ruldScore4" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldScore4}" /></td>
					</tr>
					<!-- 	<tr >
						<th width="150">有效日期:</th>
						<td><input id="ruldYxtime" name="ruldYxtime" type="text" class="inputText" onclick='WdatePicker()' style="width:300px;"  value="" /></td>
						<th width="150">总分数:</th>
						<td><input id="ruldTotalscore" name="ruldTotalscore" type="text" class="inputText" style="width:300px;"  value="" /></td>
					</tr> -->
					<tr >
					    <th width="150">多选5选多个题型名称:</th>
						<td><input id="ruldName15M" name="ruldName15M" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldName15M}" /></td>
						<th width="150">有效日期:</th>
						<td><input id="ruldYxtime" name="ruldYxtime" type="text" class="inputText" onclick='WdatePicker()' style="width:300px;"  value="${ruldList.ruldYxtime}" /></td>
					</tr>
					<tr >
					<th width="150">多选5选多个题数:</th>
					<td><input id="ruldQuiznum15M" name="ruldQuiznum15M" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldQuiznum15M}" /></td>
					<th width="150">说明:</th>
							<td >
							<textarea  id="ruldNote" name="ruldNote"  style="width:300px;height: 20px;" >${ruldList.ruldNote}</textarea>
							</td>
					</tr>
					<tr >
						<th width="150">多选5选多个试题分数:</th>
					<td><input id="ruldScore15M" name="ruldScore15M" type="text" class="inputText" style="width:300px;"  value="${ruldList.ruldScore15M}" /></td>
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
		var ruldName = document.getElementById("ruldName").value;
		var ruldTime = document.getElementById("ruldTime").value;
		var ruldYxtime = document.getElementById("ruldYxtime").value;
		var ruldBits = document.getElementById("ruldBits").value;
		var ruldAnswers = document.getElementById("ruldAnswers").value;

		var ruldNote = document.getElementById("ruldNote").value;
		
		var courseId = document.getElementById("courseId").value;
		var syllabusId = document.getElementById("syllabusId").value;
		var patrn=/^[0-9]{1,20}$/; 
		if(ruldName==""){
			alert("请输入试卷名称！");
			return;
		}
 		if(ruldTime==""){
			alert("请输入考试时间！");
			return;
		} 
		if(ruldYxtime==""){
			alert("请输入有效日期！");
			return;
		}
		if(!patrn.exec(ruldTime)){
			alert("考试时间内请输入数字！");
			return;
		}
		if(ruldBits==""){
			alert("请选择考试次数！");
			return;
		}
		if(ruldAnswers==""){
			alert("请选择是否可以查看答案！");
			return;
		}
	/* 	if(ruldTotalscore==""){
			alert("请输入总分数！");
			return;
		
		} */
		if(ruldNote==""){
			alert("请填写说明！");
			return;
		}
		 $.ajax({
      	type: "POST",
     	url: "ruldUpdateSava",
      	data: $('#fff').serialize(),
      	success: function(msg){
      		 if(msg==1){
       		 	alert( "修改成功" );
       			/* window.location = "addEditPage?id=" + classresourceId
				+ "&classresourceRank=" + rank; */
       		 var url = "<%=path%>/seeRuld?courseId="+courseId+"&syllabusId="+syllabusId;
     		window.location.href = url;
       		 }else {
       		 	alert( "修改失败" );
       		 }
     		}
  		});
			
	} 
	
	

</script>
</html>
