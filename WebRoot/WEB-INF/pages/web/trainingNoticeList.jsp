<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%String path = request.getContextPath();%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>培训动态</title>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="<%=path%>/css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/pager/kkpager_blue.css" />
</head>

<body>
<div class="header">
	<div class="wrap">
			 <%@ include file="../top.jsp"%>
    </div>
</div>
<c:if test="${sessionScope.Student!=null}">
<div class="main">
	<div class="concainer">
    	<div class="clearfix">
        	<div class="my_courses_left" >
        	 <%@ include file="../Left.jsp"%>
        	</div>
            <div class="my_courses_right">
            <div class="training_title"><a href="training">培训动态</a> > 
            <c:if test="${mark==1}">
				培训通知
				</c:if>
				<c:if test="${mark==2}">
				培训新闻
				</c:if>
				<c:if test="${mark==3}">
				培训计划
				</c:if></div>
            <div class="course_box">
            <div class="trainiing_news">
            <ul>
            <c:if test="${mark==1}">
			<c:forEach items="${pageBean.list}" var="nl">
			 <li><b></b><a href="noticeInfo?mark=1&id=${nl.noticeId}">${nl.noticeName } </a><span class="time">${nl.noticeTime}</span></li>
				</c:forEach>
				</c:if>
				
				<c:if test="${mark==2}">
			<c:forEach items="${pageBean.list}" var="nl">
			<li><b></b><a href="noticeInfo?mark=2&id=${nl.newsId}">${nl.newsName } </a><span class="time">${nl.newsTime}</span></li>			
				</c:forEach>
				</c:if>
				
				
				<c:if test="${mark==3}">
			<c:forEach items="${pageBean.list}" var="nl">
			<li><b></b><a href="noticeInfo?mark=3&id=${nl.programmeId}">${nl.programmeName }</a><span class="time">${nl.programmeName}</span></li>			
				</c:forEach>
				</c:if>
            </ul>
        </div></div>
        <c:if test="${pageBean.totalPage>1}">
				<div id="kkpager"></div>
				</c:if>
            </div>
        </div>	
    </div>
</div>
</c:if>




<c:if test="${sessionScope.Student==null}">
<div class="main">
	<div class="concainer">
    	<div class="training_dynamic">
        	   <div class="training_title"><a href="training">培训动态</a> > 
            <c:if test="${mark==1}">
				培训通知
				</c:if>
				<c:if test="${mark==2}">
				培训新闻
				</c:if>
				<c:if test="${mark==3}">
				培训计划
				</c:if></div>
        </div>
        <div class="trainiing_news">
               <ul>
            <c:if test="${mark==1}">
			<c:forEach items="${pageBean.list}" var="nl">
			 <li><b></b><a href="noticeInfo?mark=1&id=${nl.noticeId}">${nl.noticeName } </a><span class="time">${nl.noticeTime}</span></li>
				</c:forEach>
				</c:if>
				
				<c:if test="${mark==2}">
			<c:forEach items="${pageBean.list}" var="nl">
			<li><b></b><a href="noticeInfo?mark=2&id=${nl.newsId}">${nl.newsName } </a><span class="time">${nl.newsTime}</span></li>			
				</c:forEach>
				</c:if>
				
				
				<c:if test="${mark==3}">
			<c:forEach items="${pageBean.list}" var="nl">
			<li><b></b><a href="noticeInfo?mark=3&id=${nl.programmeId}">${nl.programmeName }</a><span class="time">${nl.programmeName}</span></li>			
				</c:forEach>
				</c:if>
            </ul>
        </div>
				<c:if test="${pageBean.totalPage>1}">
				<div id="kkpager"></div>
				</c:if>
    </div>
</div>
</c:if>




<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>
<script type="text/javascript">

var cfg = /\?currentPage=\d{0,5}/;
var cfg2 = /\&currentPage=\d{0,5}/;
var href = window.location.href;

 if(cfg.test(href)){
	 href = href.replace(cfg,'?')+'&';
	}else if(cfg2.test(href)){
	href = href.replace(cfg2,'')+'&';
	
}else{
	if(href.indexOf('?') > -1){
		href = href + '&';
	}else{
		href = href + '?';
	}
} 

        //生成分页控件  
         kkpager.generPageHtml({
            pno : '${pageBean.currentPage}',
            mode : 'link', //可选，默认就是link
            //总页码  
            total : '${pageBean.totalPage}',  
            //总数据条数  
            totalRecords : '${pageBean.allRow}',  
            //链接前部  
            hrefFormer : '${pageBean.url}&page=',
            //链接尾部  
            hrefLatter : '1',
            //链接算法
            getLink : function(n){
                if(n == 1){
                    return this.hrefFormer + this.hrefLatter;
                }
                return this.hrefFormer + n;
            }

        });
 
</script>
</body>
</html>
