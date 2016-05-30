<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/skins/default/main.css" />
<link rel="stylesheet" type="text/css"
	href="js/jBox/Skins/Blue/jbox.css"></link>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css">
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<script type="text/javascript" src="js/ajaxCommon.js"></script>
<script type="text/javascript" src="js/test.js"></script>

<script type="text/javascript">
 function delTest(papersThe,CourseId,RuldId) {
		if (confirm("你确定要删除该条记录吗?")) {	
		$.ajax({
				url : "deletePapers",
				type : 'GET',
				data : {
					papersThe : papersThe,
					courseId:CourseId,
					ruldId:RuldId
					
				},
				success : function(data){
					if(data=="ok"){
						alert("删除成功");
						window.location.reload();
					}else{
						alert("删除失败");
						window.location.reload();
					}
				}
			  });
	}  
	 	 }
 
 
 function JkglViewPDF(papersThe,CourseId,RuldId) {
		$.ajax({
				url : "JkglViewPDF",
				type : 'GET',
				data : {
					papersThe : papersThe,
					courseId:CourseId,
					ruldId:RuldId
					
				},
				success : function(data){
					window.location.href=data;
				}
			  });
	 	 }
 
 function JkglViewWord(papersThe,CourseId,RuldId) {
		$.ajax({
				url : "JkglViewWord",
				type : 'GET',
				data : {
					papersThe : papersThe,
					courseId:CourseId,
					ruldId:RuldId
					
				},
				success : function(data){
					window.open(data);
				}
			  });
	 	 }
</script>
</head>
<body>
	<div class="title">当前位置:考试管理 > 查看试卷</div>
	<div class="editBlock" id="showWork">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查看试卷
					</td>
					
				</tr>
				<tr>
					<td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="生成试卷"
						onclick="window.location.href='savePapers?courseId=${CourseId}&ruldId=${RuldId}&syllabusId=${syllabusId}';" />
						<input name="button" type="button"
						onclick="window.location.href='courseTable?syllabusId=${syllabusId}';"
						class='inputButton' value='返回' />
						</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>查询结果</caption>
			<thead>
				<tr>
				<th width="60px;">序号</th>
				<th width="60px;">试卷</th>
				<th width="60px;">预览</th>
				<th width="60px;">PDF</th>
				<th width="60px;">Word</th>
				<th width="60px;">删除</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listp}" var="maps" varStatus="status">
					<tr>
					<td>${status.count}</td>
						<td>试卷${maps}</td>
						<td class="alignCenter"><input name="button" type="button"
							onclick="window.location.href='testPreview?papersThe=${maps}&courseId=${CourseId}&ruldId=${RuldId}';"
							class='inputButton' value='预览' /></td>
								<td class="alignCenter"><input name="button" type="button"
							onclick="JkglViewPDF('${maps}','${CourseId}','${RuldId}')"
							class='inputButton' value='PDF' /></td>
							<td class="alignCenter"><input name="button" type="button"
							onclick="window.location.href='JkglViewWord?papersThe=${maps}&courseId=${CourseId}&ruldId=${RuldId}';"
							class='inputButton' value='Word' /></td>
						<td class="alignCenter"><input name="button" type="button"
							onclick="delTest('${maps }','${CourseId}','${RuldId}')" class='inputButton'
							value='删除' /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>

</html>