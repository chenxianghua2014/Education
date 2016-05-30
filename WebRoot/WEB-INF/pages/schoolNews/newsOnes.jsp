<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<%String path = request.getContextPath();%>
<html>
<head>
<meta charset="utf-8">
<title>新闻详情</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>
<%-- 
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/ueditor/lang/zh-cn/zh-cn.js"></script> --%>



</head>

<body>

<div class="course">
	<div class="news_info"><br/>
<%-- 	<input name="newsId" id="newsId"  value="${news.newsId}"  type="hidden" />
	<input name="newsCatalog" id="newsCatalog"  value="${news.newsCatalog}"  type="hidden" /> --%>
		<h1>${news.newsName}</h1><br/>
		<div style="font-size: 9pt; writing-mode: lr-tb; text-align: left"><p>${news.newsContent}</P></div>
		<br/>
		<br/>
	</div>
</div>
<div style="text-align:center;">
<input type="button" class="inputButton" value="返回" onclick="history.back()" />
</div>
<script type="text/javascript">
    function alls(){
     var newsId = $("#newsId").val();  
     var newsCatalog = $("#newsCatalog").val();  
       $.ajax({          
             url:"publishNews",  
             type : "post",
             data:{newsId : newsId,newsCatalog:newsCatalog},  
             error:function(){  
                alert("error occured!!!");  
             },  
             success:function(data){
             if(data!="")
             {
               alert('发布成功');
   window.location.href = '/../eduOnline/ShowReport.wx?PAGEID=newsone&NEWS_ID='+data;
             }
             else
             {
                alert('发布失败');
                history.back(-1);
  /*  window.location.href = '/../eduOnline/ShowReport.wx?PAGEID=newsone&NEWS_ID='+newsCatalog'; */
             }
       
           }  
       }); 
   
  } 
</script>
</body>
</html>
