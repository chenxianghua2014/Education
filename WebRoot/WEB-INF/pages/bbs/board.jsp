<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>中国航天科工在线培训平台</title>
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
        	<div class="training_title"><a href="main">首页</a> > 论坛</div>  
        </div>
        <div class="forum_news">
        
        <c:forEach items="${boards }" var="board">
            <div class="forum">
                <a href="#"></a>
                <div class="forum_right">
                    <h1>
						<a id="${board.boardId }" href="bbs?page=1&boardId=${board.boardId }">${board.boardName }</a>
					</h1>
                    <ul>
                       <li>主题：<span>${board.boardTitle }</span></li>
                       <li>帖数：<span>${board.bbsCount }</span></li>
                    </ul>
                    <p></p>
                </div>
            </div>
        </c:forEach>  
            
        </div>
	</div>
</div>
<%@ include file="../footer.jsp"%>
</body>
</html>

