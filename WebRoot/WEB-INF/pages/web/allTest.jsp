<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html >
<head>
<meta charset="utf-8">
<title>我的考试</title>

<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">

<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
 -->
 <script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />
<script type="text/javascript">
	var cfg = false;
	function select_all() { //全选     
		if (cfg == false) {
			var inputs = document.getElementsByTagName("input");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].getAttribute("type") == "checkbox") {
					inputs[i].checked = true;
				}
			}
			cfg = true;
		} else {
			var inputs = document.getElementsByTagName("input");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].getAttribute("type") == "checkbox") {
					inputs[i].checked = false;
				}
			}
			cfg = false;
		}
	}
	function selectType() {
		var courseType = document.getElementById("test").value;
		var hrefStr = window.location.href
		if (hrefStr.indexOf("courseType=") > 0) {
			window.location.href = hrefStr.replace(hrefStr.substring(hrefStr
					.indexOf("courseType="),
					hrefStr.indexOf("courseType=") + 12), "courseType="
					+ courseType);
		} else {
			if (hrefStr.indexOf("syllabusId=") > 0) {
				var a = window.location.href = window.location.href
						+ "&courseType=" + courseType;
			} else {
				var a = window.location.href = window.location.href
						+ "?courseType=" + courseType;
			}
		}
	}
	//收藏
	function collectionCourse() {
		var cfg2 = 0;
		var cfg1 = 0;
		var cfg0 = 0;
		var num = 0;
		var inputs = document.getElementsByName('checkbox');//获取所有的input标签对象。
		for ( var i = 0; i < inputs.length; i++) {
			var obj = inputs[i];
			if (obj.checked == true) {
				//alert(obj.value);
				num++;
				$.ajax({
					url : 'collectionCourse',
					type : 'post',
					data : {
						courseId : obj.value
					},
					async : false,
					success : function(d) {
						if (d == 2) { //成功 
							cfg2++;
						}
						if (d == 1) { //重复
							cfg1++;
						}
						if (d == 0) {
							cfg0++; //未登录
						}
						obj.checked = false;
					}
				});

			}
		}
		if (num == 0) {
			alert("请选择课程后点击收藏！");
		} else {
			if (cfg0 == 0) {
				/* if(cfg1 != 0){
					alert("有"+cfg1+"门课程重复收藏！");
				} 
				if(cfg2 != 0){
					alert("成功收藏"+cfg2+"门课程！");
				}*/
				alert("成功收藏" + cfg2 + "门课程！");
			} else {
				alert("请登录后再收藏！");
			}
		}
	}
	//报名
	function addCourse() {
		var congif2 = 0;
		var congif1 = 0;
		var congif0 = 0;
		var number = 0;
		var inputs = document.getElementsByName('checkbox');//获取所有的input标签对象。
		for ( var i = 0; i < inputs.length; i++) {
			var obj = inputs[i];
			if (obj.checked == true) {
				//alert(obj.value);
				number++;
				$.ajax({
					url : 'addCourse',
					type : 'post',
					data : {
						courseId : obj.value
					},
					async : false,
					success : function(d) {
						if (d == 2) { //成功 
							congif2++;
						}
						if (d == 1) { //重复
							congif1++;
						}
						if (d == 0) {
							congif0++; //未登录
						}
						obj.checked = false;
					}
				});

			}
		}
		if (number == 0) {
			alert("请选择课程后点击报名！");
		} else {
			if (congif0 == 0) {
				/* if(cfg1 != 0){
					alert("有"+cfg1+"门课程重复收藏！");
				} 
				if(cfg2 != 0){
					alert("成功收藏"+cfg2+"门课程！");
				}*/
				alert("成功报名" + congif2 + "门课程！");
			} else {
				alert("请登录后再报名！");
			}
		}
	}
	/* function showSpanId(syllabusName){
	 document.getElementById("spanId").innerHTML = syllabusName;
	 } */
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

						<div class="tree">
							
								<div class="tree_box">
										<h3>
											<a href="allTest?type=1&page=1">必修课</a>
										</h3>
								</div>
								<div class="tree_box">
										<h3>
										<a href="allTest?type=2&page=1">选修课</a>
											
										</h3>
								</div>
								<div class="tree_box">
										<h3>
										<a href="allTest?type=3&page=1">党校课</a>
											
										</h3>
								</div>
								<div class="tree_box">
										<h3>
										<a href="traincourseTest?type=1&page=1">常规培训班</a>
										</h3>
								</div>
								<div class="tree_box">
										<h3>
										<a href="traincourseTest?type=2&page=1">专题培训班</a>
											
										</h3>
								</div>
								<div class="tree_box">
										<h3>
										<a href="traincourseTest?type=0&page=1">党校培训班</a>
											
										</h3>
								</div>
							
						</div>

					</div>
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
											tree_one.eq(i).parent().siblings()
													.find(".tree_one")
													.slideUp();
										})
							})
							h4.each(function(i) {
								$(this).click(
										function() {
											tree_two.eq(i).slideToggle();
											tree_two.eq(i).parent().siblings()
													.find(".tree_two")
													.slideUp();
										})
							})
						})
					</script>
				</div>
				<div class="my_courses_right">
				<div class="training_title">我的考试>课程成绩</div>
				<div class="course_box">
					<table width="100%" border="1" cellspacing="0" cellpadding="0"
						bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
						<tr>
							<td width="10%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03" text-align="center">序号</td>
							<td width="19%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">课程名称</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">课程类型</td>
							<td width="12%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">成绩</td>
							<td width="22%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">查看试卷</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">报告分析</td>
							<td width="15%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">考试</td>
						</tr>
						<c:forEach items="${pageBean.list}" var="c" varStatus="cour">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${cour.index
									+ 1}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${c.course.courseName}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${c.course.courseType}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">
								<c:if test="${c.sum == -1}">评分中</c:if>
								<c:if test="${c.sum != -1}">${c.sum}</c:if>
								</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02"><a href="allTestQuery?mark=1&courseId=${c.courseId}">查看</a></td>
								<td bgcolor="#FFFFFF" class="font_black02"><a href="allTestQuery?mark=2&courseId=${c.courseId}">报告</a></td>
								<td bgcolor="#FFFFFF" class="font_black02"><a
									href="testView?courseId=${c.courseId}"
									style="color:red;">考试</a></td>
							</tr>
						</c:forEach>
					</table>
					  <div id="kkpager"></div>
				</div>
			</div>
			</div>

			

		</div>
	</div>

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
