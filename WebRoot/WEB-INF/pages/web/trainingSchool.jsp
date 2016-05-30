<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<title>党校动态</title>
<link rel="stylesheet" type="text/css" href="css/index_other.css">
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<link rel="stylesheet" type="text/css" href="css/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
<div class="header_red">
	<%@ include file="../Schooltop.jsp"%>
</div>
<div class="main_red">
	<div class="concainer">
    	<div class="training_dynamic">
        	<div class="training_title"><a href="partySchoolTraining">首页</a> > 党校动态</div>  
        </div>
        <div class="trainiing_news_red">
        	<div class="trainiing_title">
        		<h2>党校新闻</h2>
                <a href="SchoolnoticeInfoAll?mark=2&page=1&sort=3&oid=test001" class="more">更多</a>
            </div>
            <ul>
            	<c:forEach items="${nlist}" var="nl">
                <li>
                <b></b>
                <a href="SchoolnoticeInfo?mark=2&id=${nl.newsId}">${nl.newsName}</a>
                <span class="time">${nl.newsTime}</span></li>
                </c:forEach>
            </ul>
            
        </div>
        <div class="trainiing_news_red">
        	<div class="trainiing_title">
        		<h2>通知公告</h2>
                <a href="SchoolnoticeInfoAll?mark=1&page=1&sort=2&oid=test001" class="more">更多</a>
            </div>
            <ul>
            <c:forEach items="${nolist}" var="nl">
                <li>
                <b></b>
                <a href="SchoolnoticeInfo?mark=1&id=${nl.noticeId}">${nl.noticeName}</a>
                <span class="time">${nl.noticeTime}</span>
                </li>
                </c:forEach>
            </ul>
        </div>
        <c:if test="${sessionScope.Student!=null}">
        <div class="trainiing_news_red">
        	<div class="trainiing_title">
        		<h2>学习计划</h2>
                <a href="SchoolnoticeInfoAll?mark=3&page=1&sort=2&oid=test001" class="more">更多</a>
            </div>
            <ul>
            <c:forEach items="${plist}" var="pl">
                <li>
                <b></b>
                <a href="SchoolnoticeInfo?mark=3&id=${pl.programmeId}">${pl.programmeName}</a>
                <span class="time">${pl.programmeTime}</span>
                </li>
                </c:forEach>
            </ul>
        </div>
        </c:if>
    </div>
</div>





<!-- 底部 版权信息 -->
<%@ include file="../footerS.jsp"%>
</body>
</html>
