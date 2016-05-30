<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="all">
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
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
            <div class="login fl_right"><a href="javascript:loginform();">登录</a></div>
            <div class="clear"></div>	
        </div>
        <div class="nav">
        	<ul>
            	<li class="one"><a href="mainout" class="nav1">首页</a></li>
                <li><a href="">课程目录</a></li>
                <li><a href="">我的课程</a></li>
                <li><a href="testView?courseId=F8441C8B-06E6-48C2-8DD6-35AAB00B591E">我的考试</a></li>
                <li><a href="">培训动态</a></li>
                <li><a href="">培训班</a></li>
                <li><a href="">知识资源</a></li>
                <li><a href="">论坛</a></li>
            </ul>
        </div>
        <div class="banner">
        	<div class="banner_bar fl_left">
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
			<div class="login1" id="login" style="width:300px;height:230px;z-index:9999;border:3px solid #005bac;position:absolute;background:#fff;color:#000;left:39%;top:30%;display:none">
				<div style="background:#005BAC; color:#FFF;height:30px;"><div style="position:absolute;top:5px;left:10px;">登录中国航天科在线教育平台</div></div>
				<form action="" method="post">
					<div class="div1" style="position:absolute;top:50px;left:50px;">
						<input type="text" id="studentName" placeholder="用户名" style="height:30px;width:200px">
					</div>
					<div class="div2" style="position:absolute;top:100px;left:50px;">
						<input type="password" id="studentPassword" placeholder="密码" style="height:30px;width:200px">
					</div>
					<div class="nav_list" style="position:absolute;top:150px;left:50px;">
						<ul>
	                       <li class="cur"><a href="javascript:checkLogin();">登录</a></li>
	                    </ul>
					</div>
				</form>
			</div>
            <div class="dynamic fl_left">
            	<h2><span>培训动态</span></h2>
                <a href="training" class="more">更多>></a>
                <ul>
                	<c:forEach items="${planList}" var="pl">
	                	<li><a href="training" title="${pl.programmeName}">${pl.programmeName}</a><span class="time">${pl.programmeAtTemp}</span></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="notice fl_left">
            	<h2><span>通知公告</span></h2>
                <a href="training" class="more">更多>></a>
                <ul>
                	<c:forEach items="${noticeList}" var="nl">
	                	<li><a href="training" title="${nl.noticeName}">${nl.noticeName}</a></li>
                	</c:forEach>
                </ul>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div class="new">
	<div class="wrap">
    	<div class="new_bar">
            <div class="notice fl_left"><br/>
            	<a href="learningGroup"><img src="images/xuexqz.jpg"></a>
            </div>
            <div class="new_right fl_left">
            	<h2><span>最新课程</span></h2>
                <a href="#" class="more">更多>></a>
                <div class="new_right_bar">
                	<div class="right_list fl_left">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/c++_course.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="mianfei">免费</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/course_1.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/course_2.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/course_3.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="mianfei">免费</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/java_course.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/new_list_img.png" alt=""></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div class="hot">
<div class="wrap">
    	<div class="hot_bar">
            <div class="hot_left fl_left">
				<div class="left_content"> 
                    <div class="content_menu clear"> 
                        <ul> 
                            <li class="active"><a href="#">免费排行</a></li> 
                            <li><a href="#">付费排行</a></li> 
                        </ul> 
                    </div>
                    <div class="content_list clear">
                    	<ul class="period">
                        	<li class="first"><span class="number number_1">1</span>
                            	<div class="content_list_menu">
                            		<a href="#" title="横渡太平洋 一共分几步">横渡太平洋 一共分几步</a>
                                	<a href="#" class="list_img fl_left"><img src="images/hot_list_img.png" alt="" width="90px" height="50px"></a>
                                    <div class="content_list_jianjie fl_left">
                                    	<span class="ren">1754人</span>
                                        <span class="jigou" title="谷歌英语">谷歌英语</span>
                                    </div>
                                </div>
                            </li>
                            <li><span class="number number_2">2</span><a href="#" title="古代冶金与中华文明">古代冶金与中华文明</a></li>
                            <li><span class="number number_3">3</span><a href="#" title="奇妙的昆虫世界">奇妙的昆虫世界</a></li>
                            <li><span class="number number_4">4</span><a href="#" title="拓展训练和徒步入门">拓展训练和徒步入门</a></li>
                            <li><span class="number number_5">5</span><a href="#" title="走进中医的世界">走进中医的世界</a></li>
                            <li><span class="number number_6">6</span><a href="#" title="中国评书艺术">中国评书艺术</a></li>
                            <li><span class="number number_7">7</span><a href="#" title="大学美育">大学美育</a></li>
                            <li><span class="number number_8">8</span><a href="#" title="细胞科学与社会">细胞科学与社会</a></li>
                            <li><span class="number number_9">9</span><a href="#" title="工程与法律">工程与法律</a></li>
                            <li><span class="number number_10">10</span><a href="#" title="常见心血管病的治疗与预防">常见心血管病的治疗与预防</a></li>
                        </ul>
                        <ul class="credits">
                        	<li class="first"><span class="number number_1">1</span>
                            	<div class="content_list_menu">
                            		<a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a>
                                	<a href="#" class="list_img fl_left"><img src="images/hot_list_img.png" alt="" width="90px" height="50px"></a>
                                    <div class="content_list_jianjie fl_left">
                                    	<span class="ren">16666人</span>
                                        <span class="jigou" title="谷歌英语">谷歌英语</span>
                                    </div>
                                </div>
                            </li>
                            <li><span class="number number_2">2</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题题题标...</a></li>
                            <li><span class="number number_3">3</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标标标题标...</a></li>
                            <li><span class="number number_4">4</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a></li>
                            <li><span class="number number_5">5</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a></li>
                            <li><span class="number number_6">6</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a></li>
                            <li><span class="number number_7">7</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a></li>
                            <li><span class="number number_8">8</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a></li>
                            <li><span class="number number_9">9</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a></li>
                            <li><span class="number number_10">10</span><a href="#" title="标题标题标题标题标题标题标题标">标题标题标题标题标题标...</a></li>
                        </ul>
                    </div>
                 </div>
            </div>
            <div class="new_right fl_left">
            	<h2><span>热门课程</span></h2>
                <a href="#" class="more">更多>></a>
                <div class="new_right_bar">
                	<div class="right_list fl_left">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/course_1.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="mianfei">免费</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/course_2.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/course_3.jpg" alt=""  width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/java_course.jpg" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="mianfei">免费</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/dxkc_t.png" alt="" width="221px" height="125px"></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                    <div class="right_list fl_left mg_lf">
                    	<a href="#" title="英语课程标题，英语课程标题程标"><img src="images/new_list_img.png" alt=""></a>
                        <a href="#" class="list_title" title="英语课程标题，英语课程标题程标">英语课程标题，英语课程标题程标</a>
                        <p><span class="jiage">1440元</span><i>|</i><a href="#" title="教育机构名称">教育机构名称</a></p>
                    </div>
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
<div class="ad">
	<div class="wrap">
    	<div class="ad_bar">
        	<div>
            	<a href="partySchoolTraining"><img src="images/ad_img.png" alt=""></a>
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
<script type="text/javascript">
	function loginform(){
		if(document.getElementById("login").style.display == 'none'){
			document.getElementById("login").style.display = 'block';
		}else{
			document.getElementById("login").style.display = 'none';
		}
	}
	function checkLogin(){
		var studentName=document.getElementById("studentName").value;
		var studentPassword=document.getElementById("studentPassword").value;
		$.ajax({
	        url: 'checkLogin',
	        type: 'post',
	        data: {studentName:studentName,studentPassword:studentPassword},
	        success: function(d){
	            if(d == 1){
	            	window.location.href = "main";
	            }else{
	            	alert("用户名或密码错误！");
	            }
	        }
	    });
	}
</script>
</html>
