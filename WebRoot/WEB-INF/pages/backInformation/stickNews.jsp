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
		<form action="addStickNews" method="post" autocomplete="off"  id="fff"   name="fff" 
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" name="newsId" id="newsId" value="${news.newsId}">
			<input type="hidden" name="organizationId" id="organizationId" value="${news.organizationId}">
			<input type="hidden" name="newsRank" id="newsRank" value="${news.newsRank}">
			<input type="hidden" id="mark" name="mark" value="${mark}" /> 
			<table>
			
				<tbody>
					<tr >
						<th width="150">置顶选择:</th>
						<td>
						<input type="radio" name="newsTop"  id="newsTop"  value="0" ${news.newsTop==0?"checked":""}  onclick="radioType()"/> 否 
						<input type="radio"  id="newsTop"  name="newsTop" value="1" ${news.newsTop==1?"checked":""} onclick="radioType()" /> 是 
						</td>
					</tr>
					<tr style="display: none" id = "toptype">
						<th width="150">置顶截止日期:</th>
						<td><input type="text" name="newsToptime" class="inputText" id="newsToptime"  value="${news.newsToptime}" onclick='WdatePicker()'/></td>
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
</body>

</html>
