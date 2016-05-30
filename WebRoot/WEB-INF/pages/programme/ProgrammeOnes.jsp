<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<%String path = request.getContextPath();%>
<html>
<head>
<meta charset="utf-8">
<title>计划详情</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/style.css" media="all">
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>

</head>

<body>

<div class="course">
	<div class="news_info"><br/>
<%-- 	<input name="newsId" id="newsId"  value="${news.newsId}"  type="hidden" />
	<input name="newsCatalog" id="newsCatalog"  value="${news.newsCatalog}"  type="hidden" /> --%>
		<h1>${programme.programmeName}</h1><br/>
		<div style="font-size: 9pt; writing-mode: lr-tb; text-align: left"><p>${programme.programmeContent}</P></div>
		<br/>
		<br/>
	</div>
</div>
<div style="text-align:center;">
<input type="button" class="inputButton" value="返回" onclick="history.back()" />
</div>

</body>
</html>
