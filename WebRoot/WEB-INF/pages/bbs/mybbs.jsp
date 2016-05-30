<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>中国航天科工在线培训平台</title>

<%--<link type="text/css" rel="stylesheet" href="../css/pageCss/style.css" />
<link type="text/css" href="../js/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>--%>

<script src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" charset="utf-8" src="js/Effects.js"></script>

<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bbs.css">
<link rel="stylesheet" type="text/css" href="css/other.css">
<script type="text/javascript" src="js/jquery.superslide.2.1.1.js"></script>
<script type="text/javascript" src="js/zzsc.js"></script>

</head>
<body>
<div class="header">
	<%@ include file="../top.jsp"%>
</div>
<div class="main">
	<div class="concainer">
        <div class="training_dynamic">
        	<div class="luntan_title"><a href="main">首页</a> <span> > 论坛</span></div>  
        </div>
	<div class="answer">
        	<table style=" width:100%;">
            	<tr>
                    <td style="background:#f1f1f1">标题 </td>
                    <td style="background:#f1f1f1">作者</td>
                    <td style="background:#f1f1f1">回复</td>
                    <td style="background:#f1f1f1">发表时间</td>
                </tr>
		
		<c:choose>
			<c:when test="${not empty bbss }">
				<c:forEach items="${bbss }" var="bbs">
					<tr class="main_info"
						onclick="window.location.href= 'bbsdetails?bbsId=${bbs.bbsId }'">
						<td>${bbs.bbsTitle }</td>
						<td>${bbs.studentName }</td>
						<td>${bbs.replyCount }</td>
						<td>${bbs.friendlyAddtime }</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="4">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="paging">${page.pageStr }</div>
	</div>
	</div>
</div>
	
	<%@ include file="../footer.jsp"%>
</body>
</html>

