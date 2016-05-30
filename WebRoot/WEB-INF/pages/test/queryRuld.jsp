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
</head>
<body>
	<div class="title">当前位置:考试管理 > 试题规则</div>
	<div class="editBlock" id="showWork">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">试题规则</td>
				</tr>
				<tr>
					<td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加试题规则"
						onclick="window.location.href='ruldId?courseId=${ruld.courseId}&syllabusId=${syllabusId}';" />
						<c:if test="${rulds.ruldId!=''&&rulds.ruldId!=null}">
						<input name="button"
						type="button" class="inputButton" value="修改试题规则"
						onclick="window.location.href='ruldIdx?courseId=${ruld.courseId}&ruldId=${rulds.ruldId}&syllabusId=${syllabusId}';" />
						</c:if>
						<input
						type="button" class="inputButton" value="返回"
						onclick="window.location.href='courseTable?syllabusId=${syllabusId}';" />
						</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="editBlock">
	<table>
				<tr>
					<td colspan="4" class="subtitle">编辑试卷规则</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">试卷名称:</th>
						<td style="width:300px">${rulds.ruldName }</td>
						<th width="150">考试时间:</th>
						<td style="width:300px">${rulds.ruldTime }</td>
					</tr>
					<tr >
						<th width="150">有效日期:</th>
						<td style="width:300px">${rulds.ruldYxtime }</td>
						<th width="150">总分数:</th>
						<td style="width:300px">${rulds.ruldTotalscore }</td>
					</tr>
					<tr >
						<th width="150">考试次数:</th>
						<c:if test="${rulds.ruldBits==1}">
						<td style="width:300px">一次</td>
						</c:if>
						<c:if test="${rulds.ruldBits==2}">
						<td style="width:300px">多次</td>
						</c:if>
						<c:if test="${rulds.ruldBits==null}">
						<td style="width:300px"></td>
						</c:if>
						
						<th width="150">是否可以查看答案 :</th>
						<c:if test="${rulds.ruldAnswers==1}">
						<td style="width:300px">是</td>
						</c:if>
						<c:if test="${rulds.ruldAnswers==2}">
						<td style="width:300px">否</td>
						</c:if>
						<c:if test="${rulds.ruldAnswers==null}">
						<td style="width:300px"></td>
						</c:if>
					</tr>
					<tr >
						<th width="150">单选3选1题型名称:</th>
						<td style="width:300px">${rulds.ruldName13 }</td>
						<th width="150">单选4选1题型名称:</th>
						<td style="width:300px">${rulds.ruldName14 }</td>
					</tr>
					<tr >
						<th width="150">单选3选1  题数:</th>
						<td style="width:300px">${rulds.ruldQuiznum13 }</td>
						<th width="150">单选4选1  题数 :</th>
						<td style="width:300px">${rulds.ruldQuiznum14 }</td>
						
					</tr>
					<tr >
						<th width="150">单选3选1试题分数:</th>
						<td style="width:300px">${rulds.ruldScore13 }</td>
						<th width="150">单选4选1试题分数:</th>
						<td style="width:300px">${rulds.ruldScore14 }</td>
					</tr>
					<tr >
						<th width="150">多选6选多题型名称:</th>
						<td style="width:300px">${rulds.ruldName1M }</td>
						<th width="150">判断题型名称:</th>
						<td style="width:300px">${rulds.ruldName2 }</td>
					</tr>
					
					<tr >
						<th width="150">多选6选多个题数:</th>
						<td style="width:300px">${rulds.ruldQuiznum1M }</td>
						<th width="150">判断  题数:</th>
						<td style="width:300px">${rulds.ruldQuiznum2 }</td>	
					</tr>	
					<tr >
						<th width="150">多选6选多个试题分数:</th>
						<td style="width:300px">${rulds.ruldScore1M }</td>
						<th width="150">判断试题分数:</th>
						<td style="width:300px">${rulds.ruldScore2 }</td>
					</tr>
					<tr >
						<th width="150">填空 题型名称:</th>
						<td style="width:300px">${rulds.ruldName3 }</td>
						<th width="150">主观题 题型名称:</th>
						<td style="width:300px">${rulds.ruldName4 }</td>
					</tr>
					<tr >
						<th width="150">填空  题数:</th>
						<td style="width:300px">${rulds.ruldQuiznum3 }</td>
						<th width="150">主观题  题数:</th>
						<td style="width:300px">${rulds.ruldQuiznum4 }</td>
					</tr>
					<tr >
						<th width="150">填空试题分数:</th>
						<td style="width:300px">${rulds.ruldScore3 }</td>
						<th width="150">主观题试题分数:</th>
						<td style="width:300px">${rulds.ruldScore4 }</td>
					</tr>
					<tr >
						<th width="150">多选5选多个名称:</th>
						<td style="width:300px">${rulds.ruldName15M }</td>
						<th width="150">说明:</th>
						<td colspan="3" style="width:300px">${rulds.ruldNote}</td>
					</tr>	
					<tr >
					    <th width="150">多选5选多个试题分数:</th>
						<td style="width:300px">${rulds.ruldScore15M  }</td>	 
						
					</tr>
					<tr >
					   	 
						<th width="150">多选5选多个题数:</th>
						<td style="width:300px">${rulds.ruldQuiznum15M }</td>	
					</tr>
				</tbody>
			</table>
		
		<br />
	</div>
</body>

</html>