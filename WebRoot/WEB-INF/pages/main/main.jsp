<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>首页</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
	<!-------------------banner滚动---------------------->
			<script>
			$(function(){
				/*自动轮播*/
				var n=0;
				function number(){
				if(n<2){
				n=n+1;
				}else{
				n=0;}	
				$(".bnPicture").hide();
				$(".bnPicture").eq(n).show();
				$(".bnButton a").removeClass("bnThis")
				$(".bnButton a").eq(n).addClass("bnThis")
				}
				var timer=setInterval(number,2000);
				/*鼠标移到停止播放*/
				$(".bannerbig").hover(function(){
					clearInterval(timer);	
				},function(){
					timer=setInterval(number,2000);	
				})
				/*鼠标移过换图*/
				$(".bnButton a").hover(function(){
					
					var x=$(".bnButton a").index(this)
					n=x;
					$(".bnPicture").hide();
					$(".bnPicture").eq(x).show();
					$(".bnButton a").removeClass("bnThis")
					$(this).addClass("bnThis")
				})	
			})
			</script>
</head>

<body>
<div class="header">
	<div class="wrap">
    	<div class="header_top">
        	<div class="logo fl_left">
            	<a href="login" target="_blank"><img src="images/logo_img.png" alt="logo"></a>
            </div>
            <div class="search fl_left">
            	<input type="text" value="搜索课程" class="search_bar fl_left">
                <button class="search_btn"></button>
                <div class="clear"></div>
            </div>
            <c:if test="${sessionScope.student==null}">
				<div class="login fl_right">
					<a href="mainout">登录</a>
				</div>
			</c:if>
			<c:if test="${sessionScope.student!=null}">
				<div class="logind fl_right">
					<div class="loginp">
						<p>欢迎，${sessionScope.student.studentName}</p>
						<a href="mainout">退出<span></span></a>
					</div>
					<img src="images/toux.jpg" alt="我的头像" width="40px" height="40px">
				</div>
			</c:if>
            <div class="clear"></div>	
        </div>
        <div class="nav">
        	<ul>
            	<li class="one"><a href="main" class="nav1">首页</a></li>
                <li><a href="course">课程目录</a></li>
                <li><a href="mycourse">我的课程</a></li>
                <li><a href="trainingCourse">我的考试</a></li>
                <li><a href="training">培训动态</a></li>
                <li><a href="allTranningCourse">培训班</a></li>
                <li><a href="allKnowledge">知识资源</a></li>
                <li><a href="bbs">论坛</a></li>
            </ul>
        </div>
        <div class="banner">
			<div class="banner_grxx fl_left">
				<div class="banner_grxx_tx">
					<a href="#"><img src="images/toux.jpg" width="38px" height="38px"></a>
					<p>ID：A0105021</p>
					<p>总学时：30h</p>
				</div>
				<div class="clear"></div>
				<p>姓名：王学霸</p>
				<p>级别：高级</p>
				<p>所在单位：**有限公司</p>
				<p>所在部门：**部</p>
				<p>手机号码：18866668888</p>
				<p>电子邮箱：xueba@163.com</p>
			</div>

        	<div class="banner_bard fl_left">
            	<div class="bnBig1">
					<div class="bnButton">
						<a href="newsInformation?id=${newsList[0].newsId}"></a>
						<a href="newsInformation?id=${newsList[1].newsId}"></a>
						<a href="newsInformation?id=${newsList[2].newsId}"></a>
					</div>
					<a href="newsInformation?id=${newsList[0].newsId}"><img class="bnPicture" src="images/news_a.jpg"></a>
					<a href="newsInformation?id=${newsList[1].newsId}"><img style="display:none" class="bnPicture" src="images/news_b.jpg"></a>
					<a href="newsInformation?id=${newsList[2].newsId}"><img style="display:none" class="bnPicture" src="images/news_c.jpg"></a>
				</div>
            </div>
			<div class="notice fl_left">
            	<h2><span>培训动态</span></h2>
                <a href="trainingNewList?mark=2" class="more">更多>></a>
                <ul>
                	<c:forEach items="${planList}" var="pl">
	                	<li><a href="planInformation?id=${pl.programmeId}" title="${pl.programmeName}">${pl.programmeName}</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="notice fl_left">
            	<h2><span>通知公告</span></h2>
                <a href="trainingNewList?mark=1" class="more">更多>></a>
                <ul>
                	<c:forEach items="${noticeList}" var="nl">
	                	<li><a href="noticeInformation?id=${nl.noticeId}" title="${nl.noticeName}">${nl.noticeName}</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div class="new">
	<div class="wrap">
		<div class="new_left_six fl_left">
			<div class="new_left_sixbg"><img src="images/dlzylddh1.png" alt=""><a href="trainingCourse">培训计划</a></div>
			<div class="new_left_sixbg"><img src="images/dlzylddh2.png" alt=""><a href="rankingList">课程排行榜</a></div>
			<div class="new_left_sixbg"><img src="images/dlzylddh3.png" alt=""><a href="trainingCourse">资源排行榜</a></div>
			<div class="new_left_sixbg"><img src="images/dlzylddh4.png" alt=""><a href="#">搜索</a></div>
			<div class="new_left_sixbg"><img src="images/dlzylddh5.png" alt=""><a href="#">问卷调查</a></div>
			<div class="new_left_sixbg"><img src="images/dlzylddh6.png" alt=""><a href="learningGroup">学习圈子</a></div>
		</div>
        <!-- <div class="new_right fl_left">
            	<h2><span>最新培训班</span></h2>
                <a href="allTranningCourse" class="more">更多>></a>
                <div class="new_right_bar">
                	<div class="right_list fl_left">
                    	<a href="courseVideo2" title="英语课程标题，英语课程标题程标"><img src="images/new_list_img.png" alt=""></a>
                        <a href="courseVideo2" class="list_title" title="英语课程标题，英语课程标题程标">UI设计零基础入门班</a>
                        <p><span class="mianfei">免费</span><i>|</i><a href="#" title="教育机构名称">磐信科技</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="courseVideo1" title="英语课程标题，英语课程标题程标"><img src="images/java_course.jpg" style="width:221px;height:125px" alt=""></a>
                        <a href="courseVideo1" class="list_title" title="英语课程标题，英语课程标题程标">JAVA培训，从入门到精通</a>
                        <p><span class="jiage">1680元</span><i>|</i><a href="#" title="教育机构名称">教育机构</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="courseVideo" title="英语课程标题，英语课程标题程标"><img src="images/c++_course.jpg" style="width:221px;height:125px" alt=""></a>
                        <a href="courseVideo" class="list_title" title="英语课程标题，英语课程标题程标">最适合自学的c语言视频教程</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构</a></p>
                    </div>
                </div>
        </div> -->
        <div class="dynamic partytrain_kd1 fl_left">
			<h2><span>最新培训班</span></h2>
            <a href="allTranningCourse" class="more">更多>></a>
            <div>
				<ul class="partytrain_pxtz1">
	            	<c:forEach items="${tcList1}" var="t1">
			          	<li><a href="oneTranningCourse?tranningCourseId=${t1.tranningCourseId}" title="">${t1.tranningCourseName}</a><span class="time">${t1.tranningCourseAtTemp}</span></li>
					</c:forEach>
			    </ul>
			</div>
			<div style="float:left;">
			    <ul class="partytrain_pxtz1" style="float:left;">
	            	<c:forEach items="${tcList2}" var="t2" varStatus="tc">
			          	<li><a href="oneTranningCourse?tranningCourseId=${t2.tranningCourseId}" title="">${t2.tranningCourseName}</a><span class="time">${t2.tranningCourseAtTemp}</span></li>
					</c:forEach>
			    </ul>
		    </div>
        </div>
		<div class="new_right1 fl_left new_rightx" >
            	<h2><span>最新课程</span></h2>
                <a href="allCourse" class="more">更多>></a>
                <div class="new_right_bar">
                	<div class="right_list fl_left">
                    	<a href="courseVideo" title="英语课程标题，英语课程标题程标"><img src="images/course_1.jpg" style="width:221px;height:125px" alt=""></a>
                        <a href="courseVideo" class="list_title" title="英语课程标题，英语课程标题程标">内训师培训</a>
                        <p><span class="mianfei">免费</span><i>|</i><a href="#" title="教育机构名称">教育机构</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="courseVideo1" title="英语课程标题，英语课程标题程标"><img src="images/course_2.jpg" style="width:221px;height:125px" alt=""></a>
                        <a href="courseVideo1" class="list_title" title="英语课程标题，英语课程标题程标">职场蜕变，HR培训</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="courseVideo2" title="英语课程标题，英语课程标题程标"><img src="images/course_3.jpg" style="width:221px;height:125px" alt=""></a>
                        <a href="courseVideo2" class="list_title" title="英语课程标题，英语课程标题程标">带你走向人生的巅峰</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构</a></p>
                    </div>
                </div>
        </div>
        <div class="clear"></div>
		<div class="new_rightb">
			<a href="allKnowledge"><img src="images/logozxzy.png" alt="最新资源"></a>
			<a href="http://www.cnki.net/" target="_blank"><img src="images/logocnki.png" alt="中国知网"></a>
		</div>
    </div>
</div>
<div class="ad">
	<div class="wrap">
    	<div class="ad_bar">
        	<div>
            	<a href="partySchoolTraining" target="_blank"><img src="images/ad_img.png" alt=""></a>
            </div>
        </div>
    </div>
</div>
<div class="foot">
	<div class="wrap">
		<h3>LOGO</h3>
		<p>Copyright © 2015 Tencent. All Rights Reserved.</p>
		<p>在线教育机构有限公司 版权所有 | 在线教育课堂服务协议</p>
	</div>
</div>
</body>
</html>
