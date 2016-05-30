<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的考试</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
</head>

<body>
<div class="header">
	<div class="wrap">
		
	 <%@ include file="../top.jsp"%>
   </div>
</div>

<div class="main">
	<div class="concainer">
    	<div class="training_dynamic">
        	<div class="training_title"><a href="main">首页</a> > 我的考试</div>  
        </div>
        <div class="trainiing_news">
        	<div class="trainiing_title">
        		<h2>考题</h2>
            </div>
           <div class="course_box">
           <!--  查看考题     开始 -->
           <c:if test="${mark==1 }">
					<table width="100%" border="1" cellspacing="0" cellpadding="0"
						bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
						<tr>
							<td width="19%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">类型</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">考题</td>
							<td width="12%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">A选项</td>
							<td width="22%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">B选项</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">C选项</td>
							<td width="15%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">D选项</td>
						</tr>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '1_4' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">选择题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '1_3' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">选择题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '1_m' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">多选题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '2' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">判断题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '3' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">填空题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">
								<c:set
											value="${ fn:split(nl.subject.subjectTopic, '$()$') }" var="topics" />
										<c:forEach items="${ topics }" var="topic">${ topic } ( )
										</c:forEach>
								</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '4' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">主观题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
							</tr>
							</c:if>
						</c:forEach>
					</table>
					  </c:if>
					   <!--  查看考题     结束-->
					   
					   
					   
					    <!--  分析报告     开始 -->
					    <c:if test="${mark==2 }">
					<table width="100%" border="1" cellspacing="0" cellpadding="0"
						bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
						<tr>
							<td width="19%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">类型</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">考题</td>
							<td width="12%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">A选项</td>
							<td width="22%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">B选项</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">C选项</td>
							<td width="15%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">D选项</td>
								<td width="15%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">正确答案</td>
								<td width="15%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">本人回答</td>
						</tr>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '1_4' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">选择题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectAnswer }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.testAnswer }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '1_3' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">选择题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectAnswer }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.testAnswer }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '1_m' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">多选题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectAnswer }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.testAnswer }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '2' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">判断题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectAnswer }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.testAnswer }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '3' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">填空题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">
								<c:set
											value="${ fn:split(nl.subject.subjectTopic, '$()$') }" var="topics" />
										<c:forEach items="${ topics }" var="topic">${ topic } ( )
										</c:forEach>
								</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectAnswer }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.testAnswer }</td>
							</tr>
							</c:if>
						</c:forEach>
						<c:forEach items="${testlist}" var="nl" varStatus="cour">
						<c:if test="${nl.subject.subjectType == '4' }">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">主观题</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectTopic}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectA }</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectB }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectC }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectD }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.subject.subjectAnswer }</td>
								<td bgcolor="#FFFFFF" class="font_black02">${nl.testAnswer }</td>
							</tr>
							</c:if>
						</c:forEach>
					</table>
					  </c:if>
					    <!--  分析报告     结束 -->
				</div>
            	
            
        </div>
    </div>
</div>

<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>
</body>
</html>
