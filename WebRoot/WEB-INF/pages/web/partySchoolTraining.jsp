<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>党校首页</title>
<style type="text/css">
.course_margin1{ 
margin-left:18px;
}

.course_red_list_content1 {
float: left;
width: 225px;
height: 225px;
float: left;
background-color: #fff;
}
</style>

<link rel="stylesheet" type="text/css" href="css/index_other.css">
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<script language="javascript" type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery.jslides.js"></script>
<script type="text/javascript" src="js/zzsc.js"></script>
</head>

<body>
	<div class="header_red">
		<%@ include file="../Schooltop.jsp"%>
		<%--培训新闻--%>
		<div class="banner_red">
			<div class="hot_course_red">
				<div class="concainer">
					<div class="course_content_red">
						<div class="course_title">
							<h2 class="new_title_redd">培训新闻</h2>
							<a href="SchoolnoticeInfoAll?mark=2&page=1&sort=3&oid=test001"
								class="more">更多</a>
						</div>

						<div>
							<div class="course_show_party">
								<c:forEach items="${gdlist}" var="maps" varStatus="status">
									<c:if test="${status.count<5}">
										<div class="course_show_partybox">
											<div class="course_party_bl">
												<a href="SchoolnoticeInfo?mark=2&id=${maps.newsId}">
													<div class="show_party_party_img">
														<div class="show_party_img_pic">
															<img src="${maps.newsImage}" alt=""  class="show_party_imgPic">
														</div>
														<div class="show_party_img_name"></div>
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
		</div>
	</div>
	<%--培训新闻--%>
	<%--通知公告--%>
	
	<div class="Notice">
		<div class="concainer">
	    	<div class="Notice_content">
	        	<div class="course_title">
	                <h2>通知公告</h2>
	                <a href="SchoolnoticeInfoAll?mark=1&page=1&sort=2&oid=test001" class="more">更多</a>
	            </div>
	            <ul class="ul_bg_red">
	            	<c:forEach items="${nolist}" var="maps" varStatus="status">
		                <c:if test="${status.count<3}">
			                <li>
			                	<a class="dd_red" href="SchoolnoticeInfo?mark=1&id=${maps.noticeId}" title="${maps.noticeName}">${maps.noticeName}</a>&nbsp;&nbsp;&nbsp;
			                	[${maps.noticeTime}]
			                </li>
		                </c:if>
	           		</c:forEach>
	           	</ul>	
	           	<ul class="ul_bg_red">	
	            	<c:forEach items="${nolist}" var="maps" varStatus="status">
		                <c:if test="${status.count>2 && status.count<5}">
			                <li>
				                <a class="dd_red" href="SchoolnoticeInfo?mark=1&id=${maps.noticeId}" title="${maps.noticeName}">${maps.noticeName}</a>&nbsp;&nbsp;&nbsp;
				                [${maps.noticeTime}]
			                </li>
		                </c:if>
	          		</c:forEach>
	            </ul>
	        </div>
	    </div>
	</div>
	<%--通知公告--%>
	<%--最新课程--%>
	<div class="course" style="overflow:hidden; clear:both;">
	<div class="concainer">
    	<div class="course_content">
        	<div class="course_title">
                <h2>最新课程</h2>
                <c:if test="${sessionScope.Student!=null}">
                <a href="partySchool?page=1&sid=0" class="more">更多</a>
                </c:if>
            </div>
			<div class="course_list">
	            <c:forEach items="${clist}" var="maps" varStatus="status">
	            <c:choose>
	            <c:when test="${status.count==1 }">
            	<div class="course_red_list_content1">
            		<div class="course_red_list_bl">
            			<c:if test="${sessionScope.Student!=null}">
                    	<a href="courseInfoS?tpk=2&id=${maps.courseId}" >
                    	</c:if>
                    	<c:if test="${sessionScope.Student==null}">
                    	<a href="javascript:loginform();" >
                    	</c:if>
                        	<div class="course_red_img">
                            	<div class="img_pic"><img src="${maps.courseImage}" class="imgPic"></div>
                                <div class="img_tit"><h3>${maps.courseName}</h3></div>
                                <div class="img_name">${maps.organization.organizationDwmc}</div>
                                <div class="img_red_price">免费</div>
                            </div>
                        </a>
                    </div>
                </div>
               </c:when>
               <c:when test="${status.count==6 }">
            	<div class="course_red_list_content1">
            		<div class="course_red_list_bl">
                    	<c:if test="${sessionScope.Student!=null}">
                    	<a href="courseInfoS?tpk=2&id=${maps.courseId}" >
                    	</c:if>
                    	<c:if test="${sessionScope.Student==null}">
                    	<a href="javascript:loginform();" >
                    	</c:if>
                        	<div class="course_red_img">
                            	<div class="img_pic"><img src="${maps.courseImage}" class="imgPic"></div>
                                <div class="img_tit"><h3>${maps.courseName}</h3></div>
                                <div class="img_name">${maps.organization.organizationDwmc}</div>
                                <div class="img_red_price">免费</div>
                            </div>
                        </a>
                    </div>
                </div>
               </c:when>
               <c:otherwise>
              	<div class="course_red_list_content1 course_margin1">
            		<div class="course_red_list_bl">
                    	<c:if test="${sessionScope.Student!=null}">
                    	<a href="courseInfoS?tpk=2&id=${maps.courseId}" >
                    	</c:if>
                    	<c:if test="${sessionScope.Student==null}">
                    	<a href="javascript:loginform();" >
                    	</c:if>
                        	<div class="course_red_img">
                            	<div class="img_pic"><img src="${maps.courseImage}" class="imgPic"></div>
                                <div class="img_tit"><h3>${maps.courseName}</h3></div>
                                <div class="img_name">${maps.organization.organizationDwmc}</div>
                                <div class="img_red_price">免费</div>
                            </div>
                        </a>
                    </div>
                </div>
       		   </c:otherwise>
               </c:choose>
	           </c:forEach>
           </div>
        </div>
    </div>
</div>
	
	<%--最新课程--%>
	
	<%--本周培训--%>
	<div class="hot_course">
	<div class="concainer">
        <div class="course_content">
        	<div class="course_title">
            	<h2>本周培训</h2>
                <a href="SchoolnoticeInfoAll?mark=4&page=1" class="more">更多</a>
            </div>
            <div>
              
              <div class="video_page_dagang_red">
            	
                <div>
					<ul>
                    	 <c:forEach items="${tranclassList}" var="maps" varStatus="status">
                        <li><a href="oneTranningCourseDx?tranclassId=${maps.tranclassId}"><span class="dagang_red_title01">${maps.tranclassSetuptime}</span><span class="dagang_red_title02">${maps.tranclassName} </span><span class="dagang_red_title03">${maps.tranclassTeacher}</span></a></li>
                       <!--  <li><a href="#"><span class="dagang_red_title01">2015/06/16</span><span class="dagang_red_title02">中国航天科工党组中心组开展“严以律己”专题研讨 </span><span class="dagang_red_title03">优秀讲师</span></a></li>
                        <li><a href="#"><span class="dagang_red_title01">2015/06/16</span><span class="dagang_red_title02">中国航天科工党组中心组开展“严以律己”专题研讨 </span><span class="dagang_red_title03">优秀讲师</span></a></li>
                        <li><a href="#"><span class="dagang_red_title01">2015/06/16</span><span class="dagang_red_title02">中国航天科工党组中心组开展“严以律己”专题研讨 </span><span class="dagang_red_title03">优秀讲师</span></a></li>
                        <li><a href="#"><span class="dagang_red_title01">2015/06/16</span><span class="dagang_red_title02">中国航天科工党组中心组开展“严以律己”专题研讨 </span><span class="dagang_red_title03">优秀讲师</span></a></li>
                        <li><a href="#"><span class="dagang_red_title01">2015/06/16</span><span class="dagang_red_title02">中国航天科工党组中心组开展“严以律己”专题研讨 </span><span class="dagang_red_title03">优秀讲师</span></a></li> -->
                         </c:forEach>
                    </ul>	
                </div>
            </div>
            	
              
        	</div>
        </div>
    </div>
</div>
	<%--本周培训--%>

<!-- 底部 版权信息 -->
<%@ include file="../footerS.jsp"%>
</body>
</html>
