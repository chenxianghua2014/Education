<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>置顶通知</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>


<script type="text/javascript">
window.onload=function(){ 
 radioType();
 }
</script>
</head>
<body>

	<div class="editBlock">
		<form action="addStickProgramme" method="post" autocomplete="off"  id="fff"   name="fff" 
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" name="programmeId" id="programmeId" value="${programme.programmeId}">
			<input type="hidden" name="organizationId" id="organizationId" value="${programme.organizationId}">
			<input type="hidden" name="programmeRank" id="programmeRank" value="${programme.programmeRank}">
			<input type="hidden" name="programmeSort" id="programmeSort" value="${programme.programmeSort}">
			<input type="hidden" id="mark" name="mark" value="${mark}" /> 
			<table>
			
				<tbody>
					<tr >
						<th width="150">置顶选择:</th>
						<td>
						<input type="radio" name="programmeTop"  id="programmeTop"  value="0" ${programme.programmeTop==0?"checked":""}  onclick="radioType()"/> 否 
						<input type="radio"  id="programmeTop"  name="programmeTop" value="1" ${programme.programmeTop==1?"checked":""} onclick="radioType()" /> 是 
						</td>
					</tr>
					<tr style="display: none" id = "toptype">
						<th width="150">置顶截止日期:</th>
						<td><input type="text" name="programmeToptime" class="inputText" id="programmeToptime"  value="${programme.programmeToptime}" onclick='WdatePicker()'/></td>
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
	<script type="text/javascript">

	 function radioType(){
		   var chk = 0;

                var chkObjs = document.getElementsByName('programmeTop');
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
</body>

</html>
