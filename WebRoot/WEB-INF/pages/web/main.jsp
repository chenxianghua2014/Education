<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<script type="text/javascript" src="js/jquery.superslide.2.1.1.js"></script>
<script type="text/javascript" src="js/zzsc.js"></script>
</head>

<body>
<div class="header">
	
    	 <%@ include file="../top.jsp"%>
    	 
    	  <div class="b_banner" >
        <div class="concainer">
        	<div class="banner_content">
            	<div class="b_my_synopsis" style="float:left;">
                	<div class="b_my_synopsis_img">
                    	<img width="80" height="80" alt="" src="images/toux.jpg">
                        <p>${sessionScope.Student.studentName}</p>
                    </div>
                    <div class="b_my_synopsis_table">
                    	<table>
                            <tr>
                            	<td>级别：</td>
                                <td>${sessionScope.Student.studentRank}</td>
                            </tr>
                            <tr>
                            	<td>所在单位：</td>
                                <td>${sessionScope.Student.organization.organizationDwmc}</td>
                            </tr>
                            <tr>
                            	<td>所在部门：</td>
                                <td>${sessionScope.Student.department.epartmentName}</td>
                            </tr>
                        </table>
                    </div>
                </div>
            	<div class="b_banner_menu">
                    <div class="b_www51buycom">
                        <ul class="51buypic">
                        <c:forEach items="${gdlist}" var="maps" varStatus="status">
                		<c:if test="${status.count<7}">
	                	<li><a href="noticeInfo?mark=2&id=${maps.newsId}" ><img src="${maps.newsImage}"/></a></li>
	                	</c:if>
                	</c:forEach>     
                        </ul>
                        <div class="num">
                            <ul></ul>
                        </div>
                    </div>
                    <script>
                    /*鼠标移过某个按钮 高亮显示*/
                    $(".prev,.next").hover(function(){
                        $(this).fadeTo("show",0.7);
                    },function(){
                        $(this).fadeTo("show",0.1);
                    })
                    $(".b_www51buycom").slide({ titCell:".num ul" , mainCell:".51buypic" , effect:"fold", autoPlay:true, delayTime:700 , autoPage:true });
                    </script>

                </div>
                <div class="b_new">
                    <div class="course_title">
                        <h2>培训新闻</h2>
                        <a href="noticeInfoAll?mark=2&page=1&sort=1&oid=test001" class="more">更多</a>
                    </div>
                    <ul>
                    <c:forEach items="${nlist}" var="maps" varStatus="status">
                		<c:if test="${status.count<6}">
                		<li><a href="noticeInfo?mark=2&id=${maps.newsId}" >
                		<c:choose> 
   						 <c:when test="${fn:length(maps.newsName) > 10}"> 
       					 <c:out value="${fn:substring(maps.newsName, 0, 10)}......" /> 
   						 </c:when> 
   						<c:otherwise> 
      					<c:out value="${maps.newsName}" /> 
    					</c:otherwise> 
						</c:choose> 
                		</a></li>
	                	</c:if>
                	</c:forEach>          
                    </ul>
                </div>
            </div>	
        </div>
    </div>
    	 
    	 </div>
  <div class="Notice">
	<div class="concainer">
    	<div class="Notice_content">
        	<div class="course_title">
                <h2>通知公告</h2>
                <a href="noticeInfoAll?mark=1&page=1&sort=1&oid=test001" class="more">更多</a>
            </div>
            
            <ul class="ul_bg">
            <c:forEach items="${nolist}" var="maps" varStatus="status">
                		<c:if test="${status.count<3}">
	                	<li><a href="noticeInfo?mark=1&id=${maps.noticeId}" title="${maps.noticeName}">${maps.noticeName}</a></li>
	                	</c:if>
             </c:forEach>
            </ul>
            <ul>
            <c:forEach items="${nolist}" var="maps" varStatus="status">
                		<c:if test="${status.count<5 && status.count>2}">
	                	<li><a href="noticeInfo?mark=1&id=${maps.noticeId}" title="${maps.noticeName}">${maps.noticeName}</a></li>
	                	</c:if>
             </c:forEach>
            </ul>
        </div>
    </div>
</div>
 <div class="course" style="overflow:hidden; clear:both;">
	<div class="concainer">
    	<div class="course_content">
        	<div class="course_title">
                <h2>最新课程</h2>
                <c:if test="${sessionScope.Student!=null}">
                <a href="courseWebAll?mark=1&page=1" class="more">更多</a>
                </c:if>
            </div>
			<div class="course_list">
			
			   <c:forEach items="${clist}" var="maps" varStatus="status">
			   <c:if test="${status.count==1 || status.count==6}">
			    <div class="course_list_content">
			   <div class="course_list_bl">
                    	<a href="courseInfo?coursestudyId=${maps.coursestudy.coursestudyId}&tpk=1&id=${maps.courseId}" >
                        	<div class="course_img">
                            	<div class="img_pic"><img src="${maps.courseImage}" class="imgPic"></div>
                                <div class="img_tit"><h3>${maps.courseName}</h3></div>
                                <div class="img_name">${maps.organization.organizationDwmc} </div>
                                <div class="img_price"><img src="images/img_mianfei.jpg"></div>
                            </div>
                        </a>
                    </div>
                </div>
			   </c:if>
			   <c:if test="${status.count!=1 && status.count!=6}">
                 <div class="course_list_content course_margin">
                	<div class="course_list_bl">
                    	<a href="courseInfo?coursestudyId=${maps.coursestudy.coursestudyId}&tpk=1&id=${maps.courseId}" >
                        	<div class="course_img">
                            	<div class="img_pic"><img src="${maps.courseImage}" class="imgPic"></div>
                                <div class="img_tit"><h3>${maps.courseName}</h3></div>
                                <div class="img_name">${maps.organization.organizationDwmc} </div>
                                <div class="img_price"><img src="images/img_mianfei.jpg"></div>
                            </div>
                        </a>
                    </div>
                </div>
             	</c:if>
               </c:forEach>
            

           </div>
        </div>
    </div>
</div> 
 <div class="hot_course">
	<div class="concainer">
        <div class="course_content">
        	<div class="course_title">
            	<h2>最新课程</h2>
                <c:if test="${sessionScope.Student!=null}">
                <a href="courseWebAll?mark=1&page=1" class="more">更多</a>
                </c:if>
            </div>
            <div>
                <div class="btn_list">
                    <ul>
                        <li><a href="noticeInfoAll?mark=3&page=1&sort=1&oid=test001"><img src="images/hot_img_03.png" alt=""></a></li>
                        <li><a href="#"><img src="images/hot_img_06.gif" alt=""></a></li>
                        <li><a href="#"><img src="images/hot_img_08.gif" alt=""></a></li>
                        <li><a href="#"><img src="images/hot_img_10.gif" alt=""></a></li>
                        <li><a href="#"><img src="images/hot_img_12.gif" alt=""></a></li>
                    </ul>
                </div>
                <div class="course_show">
                	
                     <c:forEach items="${clist1}" var="maps" varStatus="status">
               			<c:if test="${status.count<7}">
                		<div class="course_show_content">
                        <div class="course_show_bl">
                            <a href="courseInfo?coursestudyId=${maps.coursestudy.coursestudyId}&tpk=1&id=${maps.courseId}" >
                                <div class="show_course_img">
                                    <div class="show_img_pic"><img src="${maps.courseImage}"  class="show_imgPic"></div>
                                    <div class="show_img_name">${maps.courseName}</div>
                                </div>
                            </a>
                        </div>
                	</div>
						</c:if>
                	</c:forEach>
                </div>
        	</div>
        </div>
    </div>
</div>
  <div class="ad">
	<div class="concainer">
    	<div class="ad_content ad_content_a01">
        	<a href=" javascript:alert('暂无连接');" ><img src="images/banner_new.jpg" alt=""></a>
            <a href="http://www.cnki.net/" style="margin-left:10px;"><img src="images/banner_cnki.jpg" alt=""></a>
        </div>
    	<div class="ad_content">
        	<a href="partySchoolTraining"><img src="images/ad_img.jpg" alt=""></a>
        </div>
    </div>
</div>
    	 
    	 
   

<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>

</body>
</html>
