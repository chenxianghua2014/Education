<%@page contentType="text/html" pageEncoding="UTF-8"%>
	<div class="footer">
	<div class="concainer">
    	<div class="footer_content">
        	<div class="footer_nav">
            	<div class="footer_nav_img">
                	<img src="images/footer_img.jpg">
                </div>
                <ul>
                <c:if test="${sessionScope.Student!=null}">
            	<li class="li_bg"><a href="main" class="nav1">首页</a></li>
            	<li class="li_bg"><a href="courseWebAll?mark=1&page=1">课程目录</a></li>
            	 <li class="li_bg"><a href="mystudent">我的课程</a></li>
            	  <li class="li_bg"><a href="allTest?type=1&page=1">我的考试</a></li>
            	</c:if>
            	<c:if test="${sessionScope.Student==null}">
            	<li class="li_bg"><a href="mainout" class="nav1">首页</a></li>
            	<li class="li_bg"><a href="javascript:loginform();">课程目录</a></li>
            	 <li class="li_bg"><a href="javascript:loginform();">我的课程</a></li>
            	  <li class="li_bg"><a href="javascript:loginform();">我的考试</a></li>
            	</c:if>
                
                <li class="li_bg"><a href="training">培训动态</a></li>
                <c:if test="${sessionScope.Student!=null}">
                 <li class="li_bg"><a href="allTranningCourse?mark=1&organizationId=${sessionScope.Student.studentCompanyid}&page=1&qtId=1&cj=${sessionScope.Student.organization.organizationFwqx}">培训班</a></li>
                <li class="li_bg"><a href="ResourceWebTree?mark=1&page=1">知识资源</a></li>
                <li><a href="board">论坛</a></li>
                </c:if>
                 <c:if test="${sessionScope.Student==null}">
                 <li class="li_bg"><a href="javascript:loginform();">培训班</a></li>
                  <li class="li_bg"><a href="javascript:loginform();">知识资源</a></li>
                  <li><a href="javascript:loginform();">论坛</a></li>
                </c:if>
               
                </ul>
            </div>
            <div class="copyright">
            	<h3>Copyright © 2015 Tencent. All Rights Reserved.</h3>
                <p>在线教育机构有限公司 版权所有 | 在线教育课堂服务协议</p>
            </div>
        </div>
    </div>
</div>