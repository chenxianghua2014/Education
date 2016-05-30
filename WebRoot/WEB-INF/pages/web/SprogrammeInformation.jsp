<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<%
	String path = request.getContextPath();
%>
<html>
<head>
<meta charset="utf-8">
<title>计划详情</title>
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
</head>

<body>

	<div class="header_red">
		
			<c:if test="${tpk==1 }">
				<%@ include file="../top.jsp"%>
			</c:if>
			<c:if test="${tpk==2 }">
				<%@ include file="../Schooltop.jsp"%>
			</c:if>

		
	</div>

	<div class="main">
		<div class="concainer">
			<div class="training_dynamic">
				<div class="training_title">
					<c:if test="${tpk==1 }">
						<a href="noticeInfoAll?mark=2&page=1&sort=1&oid=test001"
							style="color:#005BAC;">培训新闻</a>&nbsp;>>&nbsp;新闻详情
			</c:if>
					<c:if test="${tpk==2 }">
						<a href="SchoolnoticeInfoAll?mark=3&page=1&sort=2&oid=test001"
							style="color:#005BAC;">学习计划</a>&nbsp;>>&nbsp;计划详情
					
			</c:if>
				</div>
			</div>
			<div class="trainiing_news">
				<div class="news_details_title" style="color: rgb(0, 129, 255);">
					&nbsp;&nbsp;
					<h2>${programme.programmeName}</h2>
				</div>
				<div class="news_details_time">
					时间:${programme.programmeTime} &nbsp;&nbsp;发布单位 :
					${programme.organization.organizationDwmc} &nbsp;&nbsp;字体 ： 【<a
						class="b"
						onclick="Zoom2.style.fontSize='18px';lineHeight='24px';Javascript:return false;"
						href="#">大</a>】 【<a class="b"
						onclick="Zoom2.style.fontSize='16px';Javascript:return false;"
						href="#">中</a>】 【<a class="b"
						onclick="Zoom2.style.fontSize='14px';Javascript:return false;"
						href="#">小</a>】
				</div>

				<div class="news_details_text">${programme.programmeContent}</div>
			</div>
		</div>
	</div>







	<!-- 底部 版权信息 -->
	<%@ include file="../footerS.jsp"%>
</body>
</html>

