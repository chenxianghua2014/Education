<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的课程</title>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />
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
        	<div class="my_courses_left" >
            	<%@ include file="../LeftMyStudent.jsp"%>
 
        	</div>
            <div class="my_courses_right">
            <div class="training_title"><a href="mystudent">我的课程</a> > 学习轨迹</div>  
            <div class="training_title_right">学习轨迹</div>
            <div class="course_box">
            <table  width="100%" border="1" cellspacing="0" cellpadding="0" bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
        <tr>
          <td width="5%" height="35" align="center" bgcolor="#73c2f7" class="font_white03" text-align="center">序号</td>
          <td width="15%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">课程名称</td>
          <td width="15%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">是否是推送的课程</td>
          <td width="15%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">课程归属的培训班</td>
          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">总学时</td>
          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">现学时</td>
          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">学习进度</td>
          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">成绩</td>
          </tr>
          <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
	        <tr>
          <td height="28" bgcolor="#FFFFFF" class="font_black02">${status.count}</td>
          <td height="28" bgcolor="#FFFFFF" class="font_black02">${maps.courseName }</td>
          <c:if test="${maps.coursestudyType=='1' }">
          <td height="28" bgcolor="#FFFFFF" class="font_black02">是</td>
		  <td height="28" bgcolor="#FFFFFF" class="font_black02">无</td>
			</c:if>
          <c:if test="${maps.coursestudyType!='1' }">
		  <td height="28" bgcolor="#FFFFFF" class="font_black02">否</td>
		  <td height="28" bgcolor="#FFFFFF" class="font_black02">${maps.tranclassName }</td>
			</c:if>
          <td height="28" bgcolor="#FFFFFF" class="font_black02">${maps.coursestudyAlltime }</td>
          <td bgcolor="#FFFFFF" class="font_black02">${maps.coursestudyNowtime }</td>
          <td bgcolor="#FFFFFF" class="font_black02">${maps.coursestudyPlan }</td>
         <%--  <td bgcolor="#FFFFFF" class="font_black02">${maps.coursestudyResult }</td> --%>
          <td bgcolor="#FFFFFF" class="font_black02">${maps.sum}</td>
          </tr>
         </c:forEach>
        </table>
         <div id="kkpager"></div>
        </div>
           
            </div>
        </div>	
    </div>
</div> 
   

<div class="clear"></div>
<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>
</body>
<script type="text/javascript">
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
</html>
