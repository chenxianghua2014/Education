<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%String path = request.getContextPath();%>
<!doctype html>
<html>

<head>
<meta charset="utf-8">
<title>党校动态</title>
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="<%=path%>/css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/pager/kkpager_blue.css" />

<link rel="stylesheet" type="text/css" href="css/index_other.css">
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/other.css">
</head>
<body>
<div class="header_red">
	<%@ include file="../Schooltop.jsp"%>
</div>
<div class="main_red">
	<div class="concainer">
    	<div class="training_dynamic">
        	<div class="training_title">
        		<a href="Schooltraining">党校动态</a>&nbsp;>>
        		<c:if test="${mark==1}">
				通知公告
				</c:if>
				<c:if test="${mark==2}">
				培训新闻
				</c:if>
				<c:if test="${mark==3}">
				学习计划
				</c:if>
				<c:if test="${mark==4}">
				党校培训
				</c:if>
        	</div> 
        </div>
        
        
        <div class="trainiing_news_red">
        
        	<c:if test="${mark==1}">
            <ul>
            	<c:forEach items="${pageBean.list}" var="nl">
                <li>
                <b></b>
                <a href="SchoolnoticeInfo?mark=1&id=${nl.noticeId}">${nl.noticeName }</a>
                
                <span class="time">${nl.noticeTime}</span></li>
                </c:forEach>
            </ul>
            </c:if>
            
            <c:if test="${mark==2}">
            <ul>
            	<c:forEach items="${pageBean.list}" var="nl">
                <li>
                <b></b>
                <a href="SchoolnoticeInfo?mark=2&id=${nl.newsId}">${nl.newsName }</a>
                
                <span class="time">${nl.newsTime}</span></li>
                </c:forEach>
            </ul>
            </c:if>
            
            <c:if test="${mark==3}">
            <ul>
            	<c:forEach items="${pageBean.list}" var="nl">
                <li>
                <b></b>
                <a href="SchoolnoticeInfo?mark=3&id=${nl.programmeId}">${nl.programmeName }</a>
                
                <span class="time">${nl.programmeName}</span></li>
                </c:forEach>
            </ul>
            </c:if>
            
            <c:if test="${mark==4}">
            <ul>
            	<c:forEach items="${pageBean.list}" var="maps">
                <li>
                <b></b>
                <a href="oneTranningCourseDx?tranclassId=${maps.tranclassId}">${maps.tranclassName }<span class="time">${maps.tranclassSetuptime}</span></a></li>
                </c:forEach>
            </ul>
            </c:if>
            <c:if test="${pageBean.totalPage>1}">
				<div id="kkpager"></div>
				</c:if>
        </div>
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
        
    </div>
</div>





<!-- 底部 版权信息 -->
<%@ include file="../footerS.jsp"%>
</body>
</html>
