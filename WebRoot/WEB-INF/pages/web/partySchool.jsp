<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>党校课程</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

<link rel="stylesheet" type="text/css" href="css/index_other.css">
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="css/other.css">
<script type="text/javascript">
				$(function() {
					var h3 = $(".tree_box").find("h3");
					var tree_one = $(".tree_box").find(".tree_one");
					var h4 = $(".tree_one").find("h4");
					var tree_two = $(".tree_one").find(".tree_two");
					h3.each(function(i) {
						$(this).click(
								function() {
									tree_one.eq(i).slideToggle();
									tree_one.eq(i).parent().siblings().find(
											".tree_one").slideUp();
								})
					})
					h4.each(function(i) {
						$(this).click(
								function() {
									tree_two.eq(i).slideToggle();
									tree_two.eq(i).parent().siblings().find(
											".tree_two").slideUp();
								})
					})
				})
			</script>
</head>

<body>
	<div class="header_red">
		<%@ include file="../Schooltop.jsp"%>
	</div>

	<div class="main_red">
		<div class="concainer">
			<div class="clearfix">
				<div class="my_courses_left">
				
				
				<div class="my_synopsis_2">
						<div class="tree">
						
							<div class="tree_box">
								
									<h3>
										党校课程
									</h3>
									<ul class="tree_one">
										<c:forEach items="${syllabus}" var="s1">
											<c:if test="${s1.syllabusUpperId=='1AD34C2B-0A29-5325-1613-3082518F4567'}">
												<h4>${s1.syllabusName}</h4>
												<ul class="tree_two">
													<c:forEach items="${syllabus}" var="s2">
														<c:if test="${s2.syllabusUpperId==s1.syllabusId}">
															<li><a
																href="partySchool?page=1&sid=${s2.syllabusId}"
																style="color: #fff;"> ${s2.syllabusName}</a></li>
														</c:if>
													</c:forEach>
												</ul>
											</c:if>
										</c:forEach>
									</ul>
							
							</div>
				
					</div>
			</div>
		</div>
				
				<div class="my_courses_right">
					<div class="training_title"><a href="partySchoolTraining">首页</a>  > 党校的课堂</div>
					
					 <div class="course_box">
					 <c:forEach items="${pageBean.list}" var="c" varStatus="cour">
						<div class="curriculum_n">
	                        <a href="courseInfoS?tpk=2&id=${c.courseId}"><img src="${c.courseImage}" alt="${c.courseName}"></a>
	                        <div>
	                            <a class="curriculum_na" href="courseInfoS?tpk=2&id=${c.courseId}">${c.courseName}</a><span></span></br>
	                            <p>发布单位：${c.organization.organizationDwmc }</p><p>发布人：${c.coursePubman}</p></br>
	                            <p>
	                            <h3>
	                            ${c.courseDesc}
	                            </h3>
	                            </p></br>
	                        </div>
	                    </div>
                    </c:forEach>
                    
					</div>
					
				
				</div>
				<div class="course_rcontent_fany course_rcontent_fany1">
					<div id="kkpager"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- 底部 版权信息 -->
	<%@ include file="../footerS.jsp"%>

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
		getLink : function(n) {
			if (n == 1) {
				return this.hrefFormer + this.hrefLatter;
			}
			return this.hrefFormer + n;
		}

	});
</script>
</html>
