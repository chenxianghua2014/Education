<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>课程详情</title>
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<link type="text/css" rel="stylesheet" href="js/raty/application.css">
<script type="text/javascript" src="js/raty/jquery.raty.min.js"></script>

<!-- <script type="text/javascript" src="xuxiangka.js"></script> -->
<script type="text/javascript">
	function collectOne(id) {
		if (confirm("你确定要收藏本课程么?")) {
			$.ajax({
				url : "collectOnes",
				type : 'GET',
				data : {
					id : id
				},
				success : function(data1) {
					if (data1 == "ok") {
						alert("收藏成功");
						window.location.reload();
					} else if (data1 == "1") {
						alert("请勿重复收藏");
						window.location.reload();
					} else if (data1 == "2") {
						alert("请登录账号");
						window.location.reload();
					} else {
						alert("收藏失败");
						window.location.reload();
					}
				}
			});
		}
	}
</script>
</head>

<body>
<div class="header_red">
	
			

		<%@ include file="../Schooltop.jsp"%>

       
    </div>
    
    
    <div class="main">
	<div class="concainer">
        <div class="video_page">
			<div class="video_img">
			<c:choose>
                    			<c:when test="${course.courseImage == null}">
	                        		<img src="images/banner_img_02.jpg">
                    			</c:when>
                    			<c:otherwise>
                    				<img src="${course.courseImage}" style="width:605px;height:273px;">
                    			</c:otherwise>
                    		</c:choose>
            </div>
            <div class="video_xiangQ"  style="position: relative">
            	<h2>${course.courseName}</h2>
             <div style="text-align: right;width: 60px;position: absolute;top: 30px;right: -7px;" >
            	<!-- <a href="#" style="font-size:15px;color:#F11111;font-weight:bold;">收藏</a> -->
            	<input name="button" type="button" class="inputButton" value="收藏"  onclick="collectOne('${courseId}')" style="width:50px; height:30px; background: #FF0000; color:#fff;border-radius:5px"/>
            </div>
                <p>${course.courseDesc}</p>
            </div>
        </div>
        <div class="video_page">
			<div class="video_page_dagang">
            	<h2>课程大纲</h2>
                <div>
					<ul>
                    	<div class="dagang_title"><span class="dagang_title01">课程名称 </span><span class="dagang_title02">课程时间</span><span class="dagang_title03"></span></div>
                    	<c:forEach items="${crlist}" var="c">
                    	<c:if test="${c.courseresourceType == 5}">
                    	 <li><a href="courseVideoS?courseId=${courseId}&coursestudyId=${coursestudyId}&id=${c.courseresourceId}&tpk=${tpk}"><span class="dagang_title01">${c.courseresourceName} </span><span class="dagang_title02"><fmt:formatDate value="${c.courseresourceAt}" pattern="yyyy年MM月dd日HH点mm分ss秒"/></span><span class="dagang_title03"><img src="images/dagang_img_06.png"></span></a></li>
						</c:if>
						<c:if test="${c.courseresourceType == 6}">
                    	 <li><a href="courseVideoS?courseId=${courseId}&coursestudyId=${coursestudyId}&id=${c.courseresourceId}&tpk=${tpk}"><span class="dagang_title01">${c.courseresourceName} </span><span class="dagang_title02"><fmt:formatDate value="${c.courseresourceAt}" pattern="yyyy年MM月dd日HH点mm分ss秒"/></span><span class="dagang_title03"><img src="images/dagang_img_06.png"></span></a></li>
						</c:if>
                        </c:forEach>
                        <br><br><br>
                        <div class="dagang_title"><span class="dagang_title01">文本名称 </span><span class="dagang_title02">文本类型</span><span class="dagang_title03"></span></div>
                    	<c:forEach items="${crlist}" var="c">
                    	<c:if test="${c.courseresourceType == 7}">
                    	 <li><a href="${c.courseresourceFileaddr }"><span class="dagang_title01">${c.courseresourceName} </span><span class="dagang_title02">${c.courseresourceFiletype }</span><span class="dagang_title03">下载</span></a></li>
						</c:if>
                        </c:forEach>
                   </ul>	
                </div>
            </div>
            <div class="video_page_daoshi" style="width: 300px;">
            	<h2>导师简介</h2>
            	<c:if test="${sessionScope.Student!=null}">
                <div><div class="demo">
								    <div id="star" class="target-demo" style=" text-align: center"></div>
								  </div>
								</div>
								</c:if>
                <div class="video_page_daoshi_neirong">
                	<img src="${course.teacher.teacherPhoto}" alt="">
                	<h3>${course.teacher.teacherName}</h3>	
                	<%--
                	<h3>轰6K战轰机如何打击美关岛</h3>
                    <p>哈工程学院 教师</p>
                    --%>
                    <p style="width: 250px;line-height: 20px;">${course.teacher.teacherInformation}</p>	
                </div>
                
            </div>
        </div>
    </div>
</div>
    


<!-- 底部 版权信息 -->
<%@ include file="../footerS.jsp"%>
<input type="hidden" id="courseId" name="courseId" value="${courseId}">
<input type="hidden" id="coursestudyId" name="coursestudyId" value="${coursestudyId}">
<input type="hidden" id="tpk" name="tpk" value="${tpk}">
<input type="hidden" id="estimatePoint" name="estimatePoint" value="${estimate.estimatePoint}">
<script type="text/javascript">
	var courseId=$("#courseId").val();
	var coursestudyId=$("#coursestudyId").val();
	var tpk=$("#tpk").val();
	var estimatePoint=$("#estimatePoint").val();
    $(function() {
	      if(estimatePoint==""){
		      	$('#star').raty({
			  	number: 10,//多少个星星设置
				score: 0,//初始值是设置
				targetType: 'number',//类型选择，number是数字值，hint，是设置的数组值
		        path      : 'images',
		        cancelOff : 'cancel-off.png',
		        cancelOn  : 'cancel-on.png',
		        size      : 24,
		        starHalf  : 'star-half.png',
		        starOff   : 'star-off.png',
		        starOn    : 'star-on.png',
		        target    : '',
		        cancel    : false,
		        targetKeep: true,
		        precision : false,//是否包含小数
		        click: function(score, evt) {
		        	var url="webEstimate?id=" + courseId+"&coursestudyId="+coursestudyId+"&tpk=2&score="+score;
		          window.location = url;
			        }
			      });    
		    }else{
		    	$('#star').raty({
			  	number: 10,//多少个星星设置
				score: estimatePoint,//初始值是设置
				targetType: 'number',//类型选择，number是数字值，hint，是设置的数组值
		        path      : 'images',
		        cancelOff : 'cancel-off.png',
		        cancelOn  : 'cancel-on.png',
		        size      : 24,
		        starHalf  : 'star-half.png',
		        starOff   : 'star-off.png',
		        starOn    : 'star-on.png',
		        target    : '',
		        readOnly : true,  
		        cancel    : false,
		        targetKeep: true,
		        precision : false//是否包含小数
			      }); 
		    	}
      	}
    );

  
  </script>
</body>
</html>
