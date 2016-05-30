<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
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

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="婚筹">
<meta http-equiv="description" content="婚筹">
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<link rel="stylesheet" href="js/validator-0.7.3/jquery.validator.css">
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/ueditor/ueditor.all.min.js">
	
</script>
<script type="text/javascript" charset="utf-8"
	src="js/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript"
	src="js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript"
	src="js/validator-0.7.3/local/zh_CN.js"></script>
<script type="text/javascript">
				var ue = UE.getEditor('bbsContent');
				var editor;
				$(document).ready(function() {
					editor = UE.getEditor('bbsContent');
				});
			</script>

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
        	<form id="frmLogin" method="post" action="savebbs"
					autocomplete="off">
					<div class="answer_title">
					标题：<input type="text" id="bbsTitle" name="bbsTitle" data-rule="标题:required;">
					</div>
					<input type="hidden" name="boardId" id="boardId" value="${boardId}">
					
	            	 
	            	 	<script id="bbsContent" name="bbsContent" type="text/plain"
							style="width:1024px;height:500px;"></script>
	            	 
					<div class="answer_button">
            			<input type="submit" class="save" value="保存" />
            			<input type="button" class="cancel" value="取消"
							onclick="history.back()" />
                		
            		</div>
				</form>	
        </div>      
	</div>
</div>
	<%@ include file="../footer.jsp"%>	
</body>
</html>

