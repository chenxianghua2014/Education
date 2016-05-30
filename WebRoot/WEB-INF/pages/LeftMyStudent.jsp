<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
            	<div class="my_synopsis">
                	<div class="my_synopsis_img">
                    	<img width="80" height="80"  src="images/toux.jpg" alt="my_synopsis">
                        <p>${sessionScope.Student.studentName}</p>
                    </div>
                    <div>
                    	<table>
                            <tr>
                            	<td height="25">级别：</td>
                                <td height="25">${sessionScope.Student.studentRank}</td>
                            </tr>
                            <tr>
                            	<td height="25">所在单位：</td>
                                <td height="25">${sessionScope.Student.organization.organizationDwmc}</td>
                            </tr>
                            <tr>
                            	<td height="25">所在部门：</td>
                                <td height="25">${sessionScope.Student.department.epartmentName}</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="my_synopsis_2">
              <div class="course_lmenu">
					<div class="leftsidebar_box">
					<dl class="system_log">
						<dt><a href="mystudent" style="color: #666666;">个人资料</a></dt>
					</dl>
					<dl class="custom">
						<dt>全部课程<img src="images/select_xl.png"></dt>
						<dd><a href="mycourse?type=1&page=1">必修课程</a></dd>
						<dd><a href="mycourse?type=2&page=1">选修课程</a></dd>
						<dd><a href="mycourse?type=3&page=1">党校课程</a></dd>
					</dl>
					<dl class="channel">
						<!-- <dt >我的专题班</dt> -->
						<dt><a href="myTraincourse?type=1&page=1" style="color: #666666;">我的常规培训班</a></dt>
						<dt><a href="myTraincourse?type=2&page=1" style="color: #666666;">我的专题培训班</a></dt>
						<dt><a href="myTraincourse?type=0&page=1" style="color: #666666;">我的党校培训班</a></dt>
					</dl>
					
					<dl class="app">
						 <dt><a href="courseWebSc?mark=1&page=1" style="color: #666666;">我的收藏</a></dt> 
					</dl>
					
						
					<dl class="cloud">
						<dt><a href="myTrainHistory?type=1&page=1" style="color: #666666;">我的学习轨迹</a></dt>
					</dl>
					
					</div>
					<script type="text/javascript">
					$(".leftsidebar_box dt").css({"background-color":"#ffffff"});
					$(".leftsidebar_box dt img").attr("src","images/select_xl.png");
					$(function(){
						$(".leftsidebar_box dd").hide();
						$(".leftsidebar_box dt").click(function(){
							$(".leftsidebar_box dt").css({"background-color":"#ffffff"})
							$(this).css({"background-color": "#fafafa"});
							$(this).parent().find('dd').removeClass("menu_chioce");
							$(".leftsidebar_box dt img").attr("src","images/select_xl.png");
							$(this).parent().find('img').attr("src","images/select_xl01.png");
							$(".menu_chioce").slideUp(); 
							$(this).parent().find('dd').slideToggle();
							$(this).parent().find('dd').addClass("menu_chioce");
						});
					})
					$(function(){
						$(".leftsidebar_box dd").click(function(){
							$(".leftsidebar_box dd").css({"background-color":"#ffffff"})
							$(this).css({"background-color": "#1c7ace"});
						});
					})
					</script>
				</div>
                
                </div>



	
		
		