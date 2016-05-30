<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<%String path = request.getContextPath();%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>培训动态</title>
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<!-- 分页 -->
<script type="text/javascript" src="<%=path%>/css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/pager/kkpager_blue.css" />

<script type="text/javascript">

 /* 二级联动查询 */

function getData() {
	var classresourceUpname = $("#classresourceUpname").val();
	var organizationId = $("#orId").val();
	$.ajax({
				url : "twoclass",
				data : {
					classresourceUpname : classresourceUpname,
					organizationId : organizationId
				},
				type : "post",
				error : function() {
					alert("error occured!!!");
				},
				success : function(data) {
					
			/* 	       if(data!="0"){
			                var classresourceId = document.getElementById('classresourceId');  
			                //清空数组  
			                 classresourceId.length = 0; 
			                  $("#classresourceId").append("<option value='' selected=\"selected\">---请选择---</option>");     
			                for(var i=0;i<data.length;i++){  
			                     var xValue=data[i].id;    
			                     var xText=data[i].name; 
			                     var option=new Option(xText,xValue); 
			                     classresourceId.add(option);
			                }  
			              }else{  
			                 var classresourceId = document.getElementById('classresourceId');  
			                 classresourceId.length = 0;  
			              }   */
				       
					if (data.length > 0) {
						$("#departmentIds").empty();
						var html = " <select id='classresourceId'  name='classresourceId'  >"
								+ "<option value='' selected=\"selected\">---全部---</option>";

						for ( var i = 0; i < data.length; i++) {
							var xValue = data[i].id;
							var xText = data[i].name;
							html += "<option value='"+xValue+"'  ${tranclas.classresourceId.equals('"+xValue+"')?'selected':''}>" + xText
									+ "</option>";
						}
						html += " </select>";
						$("#departmentIds").append(html);
					} else {
						var html = "";
						document.getElementById("departmentIds").innerHTML = "";
					} 
				}
			});
} 
/* 二级联动查询 */
function getDatas(oid,up,idsp) {
	
	/* var classresourceUpname = $("#classresourceUpname").val();
	var organizationId = $("#orId").val(); */
	$.ajax({
				url : "twoclass",
				data : {
					classresourceUpname : up,
					organizationId : oid
				},
				type : "post",
				error : function() {
					alert("error occured!!!");
				},
				success : function(data) {
					
			/* 	       if(data!="0"){
			                var classresourceId = document.getElementById('classresourceId');  
			                //清空数组  
			                 classresourceId.length = 0; 
			                  $("#classresourceId").append("<option value='' selected=\"selected\">---请选择---</option>");     
			                for(var i=0;i<data.length;i++){  
			                     var xValue=data[i].id;    
			                     var xText=data[i].name; 
			                     var option=new Option(xText,xValue); 
			                     classresourceId.add(option);
			                }  
			              }else{  
			                 var classresourceId = document.getElementById('classresourceId');  
			                 classresourceId.length = 0;  
			              }   */
				       
					if (data.length > 0) {
						$("#departmentIds").empty();
						var html = " <select id='classresourceId'  name='classresourceId'  >"
								+ "<option value='' selected=\"selected\">---全部---</option>";

						for ( var i = 0; i < data.length; i++) {
							var xValue = data[i].id;
							var xText = data[i].name;
							if(idsp==xValue){
								html += "<option value='"+xValue+"' selected >" + xText
								+ "</option>";
							}else{
								html += "<option value='"+xValue+"'>" + xText
								+ "</option>";
							}
							
						}
						html += " </select>";
						$("#departmentIds").append(html);
					} else {
						var html = "";
						document.getElementById("departmentIds").innerHTML = "";
					} 
				}
			});
}
window.onload=function(){ 
	getDatas("${tranclas.organizationId}","${tranclas.classresourceUpname}","${tranclas.classresourceId}");
	} 
</script>
</head>

<body>
<div class="header">
	<div class="wrap">
			 <%@ include file="../top.jsp"%>
    </div>
</div>




	
	<div class="main">
		<div class="concainer">
			<div class="clearfix">
		
		<div class="my_courses_left">
					<div class="my_synopsis_2">
		<c:if test="${sessionScope.Student!=null}">
		<%@ include file="../LeftTrainingCourse.jsp"%>
		 </c:if>
		</div>
		</div>
			
			
			 
			<div class="my_courses_right">
				<div class="training_title">培训班>培训班目录>${zhu.organizationDwmc}
				 
				
				</div>
				<c:if test="${sessionScope.Student!=null}">
			<form action="allTranningCourse?mark=1&organizationId=${organizationId}&page=1&qtId=1&cj=${cj}" method="post" >
			<div class="peixunban_new">
			<input type="hidden" id="orId" name="orId" value="${tranclas.organizationId}" /> 
			<%--   <input type="text" id="orId" name="orId" value="${organizationId}" />   --%>
			     <%--  <select   id="classresourceUpname"  name="classresourceUpname" onchange="getData('${organizationId}')" > --%>
                  <select   id="classresourceUpname"  name="classresourceUpname" onchange="getData()" >
                 <option value="" selected="selected">全部</option>
				<%--  <option value="1" ${tranclas.classresourceUpname.equals("1")?"selected":""}>常规培训班</option> --%>
				  <option value="1" ${tranclas.classresourceUpname.equals("1")?"selected":""}>常规培训班</option>
				 <option value="2" ${tranclas.classresourceUpname.equals("2")?"selected":""}>专题培训班</option>
				 <c:if test="${organizationId=='test001'}">
				<%--  <option value="0" ${tranclas.classresourceUpname==0?"selected":""} >党校培训班</option> --%>
				  <option value="0" ${tranclas.classresourceUpname.equals("0")?"selected":""}>党校培训班</option>
				 </c:if>
				  <c:if test="${!tranclas.classresourceUpname.equals('')}">
                
                  </c:if>
                 </select> 
                 <div id="departmentIds" name="departmentIds"></div> 
				<input name="button" type="submit"  class="inputButton" value="查询" />
			</div>
			 </form>
			 </c:if>
				<c:if test="${sessionScope.Student!=null}">
				<div class="course_box">
					<table width="100%" border="1" cellspacing="0" cellpadding="0"
						bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
			<!-- 	<li>
					<div class="course_rcontent_nav3 fl_left cnavbg cnavsx">序号</div>
					<div class="course_rcontent_nav5 fl_left cnavbg cnavsx">班名称</div>
					 <div class="course_rcontent_nav5 fl_left cnavbg cnavsx">类别</div>
					<div class="course_rcontent_nav3 fl_left cnavbg cnavsx">人数</div>
					<div class="course_rcontent_nav4 fl_left cnavbg cnavsx">班主任</div>
					<div class="course_rcontent_nav5 fl_left cnavbg cnavsx">所属目录</div>
					<div class="course_rcontent_nav4 fl_left cnavbg cnavsx">管理级别</div>
					<div class="course_rcontent_nav4 fl_left cnavbg cnavsx">培训班详情</div> 
				</li> -->
				<%-- <input type="test" id="orId" name="orId" value="${organizationId}" />  --%>
				 <tr>
		          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03" text-align="center">序号</td>
		          <td width="19%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">班名称</td>
		          <td width="11%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">类别</td>
		          <td width="12%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">人数</td>
		          <td width="11%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">班主任</td>
		          <td width="11%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">所属目录</td>
		          <td width="15%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">管理级别</td>
		          <td width="14%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">培训班详情</td>
		          </tr>
				
				<c:forEach items="${pageBean.list}" var="t" varStatus="tran">
				 <tr>
		          <td height="28" bgcolor="#FFFFFF" class="font_black02">${tran.count}</td>
		          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclassName}</td>
		          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclassCategroy}</td>
		          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclassNumber}</td>
		          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclassTeacher}</td>
		          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclassCatalog}</td>
		          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclassRank}</td>
		          <td bgcolor="#FFFFFF" class="font_black02"><a href="oneTranningCourse?tranclassId=${t.tranclassId}">查看</a></td>
		          </tr>
				<%-- 	<li>
						<div class="course_rcontent_nav3 cnavsx fl_left">${tran.count}</div>
						<div class="course_rcontent_nav5 cnavsx fl_left">${t.tranclassName}</div>
						 <div class="course_rcontent_nav5 cnavsx fl_left">${t.tranclassCategroy}</div>
						<div class="course_rcontent_nav3 cnavsx fl_left">${t.tranclassNumber}</div>
						<div class="course_rcontent_nav4 cnavsx fl_left">${t.tranclassTeacher}</div>
						<div class="course_rcontent_nav5 cnavsx fl_left">${t.tranclassCatalog}</div>
						<div class="course_rcontent_nav4 cnavsx fl_left">${t.tranclassRank}</div>
						<div class="course_rcontent_nav4 cnavsx fl_left"><a href="oneTranningCourse?tranclassId=${t.tranclassId}">查看</a></div> 
					</li> --%>
				</c:forEach>
				
				</table>
					  <div id="kkpager"></div>
				</div>
					</c:if>
			</div>
			</div>
		</div>
		</div>
		<c:if test="${sessionScope.Student==null}">
	
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
