<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>培训动态</title>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10" />
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
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
        	<div class="training_title"><a href="main">首页</a> > 搜索" ${name} "</div>  
        </div>
        <div class="trainiing_news">
        <c:if test="${!empty nlist }">
        	<div class="trainiing_title">
        		<h2>培训新闻</h2>
            </div>
            <ul>
            	<c:forEach items="${nlist}" var="nl">
            		<li><b></b><a href="noticeInfo?mark=2&id=${nl.newsId}">${nl.newsName} </a><span class="time">${nl.newsTime}</span></li>
            	</c:forEach>
            </ul>
            </c:if>
             <c:if test="${!empty nolist }">
            <div class="trainiing_title">
        		<h2>通知公告</h2>
            </div>
            <ul>
            	<c:forEach items="${nolist}" var="nl">
           			<li><b></b><a href="noticeInfo?mark=1&id=${nl.noticeId}">${nl.noticeName}</a><span class="time">${nl.noticeTime}</span></li>
               	</c:forEach>
            </ul>
            </c:if>
            
            <c:if test="${sessionScope.Student!=null}">
            <c:if test="${!empty plist }">
            <div class="trainiing_title">
        		<h2>培训计划</h2>
            </div>
            <ul>
            	<c:forEach items="${plist}" var="pl">
            		<li><b></b><a href="noticeInfo?mark=3&id=${pl.programmeId}">${pl.programmeName}</a><span class="time">${pl.programmeTime}</span></li>
            	</c:forEach>
            </ul>
            </c:if>
            <c:if test="${!empty clist }">
            <div class="trainiing_title">
        		<h2>课程</h2>
            </div>
            <ul>
            	 <c:forEach items="${clist}" var="maps" varStatus="status">
            		<li><b></b><a href="courseInfo?coursestudyId=${maps.coursestudy.coursestudyId}&tpk=1&id=${maps.courseId}">${maps.courseName}</a><span class="time"><fmt:formatDate value="${maps.courseAt}" pattern="yyyy-MM-dd"/></span></li>
            	</c:forEach>
            </ul>
            </c:if>
            </c:if>
        </div>
        
    </div>
</div>

<!-- 底部 版权信息 -->

<%@ include file="../footer.jsp"%>

</body>
</html>
