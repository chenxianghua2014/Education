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
            <div class="training_title"><a href="mystudent">我的考试</a> > 课程成绩</div>  
           
            <div class="course_box">
            <table  width="100%" border="1" cellspacing="0" cellpadding="0" bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
        <tr>
          <td width="5%" height="35" align="center" bgcolor="#73c2f7" class="font_white03" text-align="center">序号</td>
          <td width="15%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">班名称</td>
          <td width="12%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">人数</td>
          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">班主任</td>

          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">管理级别</td>
          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">培训班详情</td>
          <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">培训班课程</td>
          </tr>
          <c:forEach items="${pageBean.list}" var="t" varStatus="tc">	
	        <tr>
          <td height="28" bgcolor="#FFFFFF" class="font_black02">${tc.index + 1}</td>
          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclass.tranclassName}</td>
          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclass.tranclassNumber}</td>
          <td height="28" bgcolor="#FFFFFF" class="font_black02">${t.tranclass.tranclassTeacher}</td>
          <td bgcolor="#FFFFFF" class="font_black02">${t.tranclass.tranclassRank}</td>
          <td bgcolor="#FFFFFF" class="font_black02"><a href="oneTranningCourse?tranclassId=${t.tranclass.tranclassId}">详情</a></td>
          <td bgcolor="#FFFFFF" class="font_black02"><a href="traincourseCoursTest?coursestudyType=2&page=1&tranclassId=${t.tranclass.tranclassId}">课程</a></td>

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
