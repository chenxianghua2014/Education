<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>视频播放</title>
<link rel="stylesheet" type="text/css" href="css/index_party.css">
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">
	function coursenoteSava(){
		/* var id =document.getElementById("id").value;
		var tpk = document.getElementById("tpk").value;
		var coursestudyId=document.getElementById("coursestudyId").value; */
		var html = "";
		var courseId = document.getElementById("courseId").value;
		alert(courseId);
		if(document.getElementById('coursenoteContent').value==""){
				alert("请输入笔记内容！");
				return false;
			}
		$.ajax({
      	type: "POST",
     	url: "coursenoteSava",
      	data: $('#noteForm').serialize(),
      	success: function(msg){
      		 document.getElementById('coursenoteContent').value="";
       		 	
       		 	$.ajax({
		      	type: "POST",
		     	url: "courseNoteByCourseId",
		      	data:'courseId='+courseId ,
		      	success: function(data){
		      	document.getElementById("bj1").style.display="none";
		      	document.getElementById("bj2").style.display="block";
		      		for ( var i = 0; i < data.length; i++) {
						var list = data[i];

						html += '<td>bj2----' + list.coursenoteContent	+ '</td>';

					}
					document.getElementById("bj2").innerHTML = html;
					}
		      	});
			}
      	});

	}	
	</script>
		<style type="text/css" media="screen">
		#flashContent { width:100%; height:100%; }
		</style>
</head>

<body>
<input type="hidden" id="cid" name="cid" value="${courseresource.courseresourceId}">
<input type="hidden" id="sum" name="sum" value="${sum}">
<div class="header">
	
			<c:if test="${tpk==1 }">
		<%@ include file="../top.jsp"%>
	 </c:if>
	 <c:if test="${tpk==2 }">
		<%@ include file="../Schooltop.jsp"%>
	 </c:if>
        
    </div>

<div class="main">
	<div class="concainer">
    	<div class="training_dynamic">
        	<div class="training_title"><a href="courseWebAll?mark=1&page=1">课程目录</a> > ${courseresource.courseresourceName}</div>  
        </div>
       <div class="bbs_home" >
	<div class="wrap">
		<div class="gkkcym">
			<div id="flashContent">
			<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" width="1000px" height="480px" id="videoPlayer" align="middle">
				<param name="movie" value="images/videoPlayer.swf" />
				<param name="quality" value="high" />
				<param name="bgcolor" value="#cccccc" />
				<param name="play" value="true" />
				<param name="loop" value="true" />
				<param name="wmode" value="window" />
				<param name="scale" value="showall" />
				<param name="menu" value="true" />
				<param name="devicefont" value="false" />
				<param name="FlashVars" value="videoURL=../${courseresource.courseresourceFileaddr}" />
				<param name="salign" value="" />
				<param name="allowScriptAccess" value="sameDomain" />
				<param name="allowFullScreen" value="true" />
				<!--[if !IE]>-->
				<object type="application/x-shockwave-flash" data="images/videoPlayer.swf" width="1200px" height="480px">
					<param name="movie" value="images/videoPlayer.swf" />
					<param name="quality" value="high" />
					<param name="bgcolor" value="#cccccc" />
					<param name="play" value="true" />
					<param name="loop" value="true" />
					<param name="wmode" value="window" />
					<param name="scale" value="showall" />
					<param name="FlashVars" value="videoURL=../${courseresource.courseresourceFileaddr}" />
					<param name="menu" value="true" />
					<param name="devicefont" value="false" />
					<param name="salign" value="" />
					<param name="allowScriptAccess" value="sameDomain" />
					<param name="allowFullScreen" value="true" />
				<!--<![endif]-->
					<a href="http://www.adobe.com/go/getflash">
						<img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="获得 Adobe Flash Player" />
					</a>
				<!--[if !IE]>-->
				</object>
				<!--<![endif]-->
			</object>
		</div>
		<div id="bj1" style="display:block; overflow:scroll; width:1200px; height:100px;">
			<c:forEach items="${coursenoteList}" var="coursenoteList" varStatus="c">
			<td>${coursenoteList.coursenoteContent }<br/></td>
			</c:forEach>
        </div>
        <div id="bj2" style="display:none; overflow:scroll; width:1000px; height:100px;"></div>
		<form action="coursenoteSava" method="post" id="noteForm" name="noteForm"	>
			<input type="hidden" id="coursestudyId" name="coursestudyId" value="${coursestudyId}" /> 
			<input type="hidden" id="coursenoteDel" name="coursenoteDel" value="1" /> 
			<input type="hidden" id="id" name="id" value="${id}" /> 
			<input type="hidden" id="tpk" name="tpk" value="${tpk }" /> 
			<input type="hidden" id="courseId" name="courseId" value="${courseId}" /> 
			<table>
				<tbody>
					<tr>
					     <th width="150">笔记:</th>
						<td><textarea id="coursenoteContent" name="coursenoteContent" style="width:900px;height:40px;" ></textarea>
                          <td colspan="2"><input type="button"
						class="inputButton" value="发布"  onclick="coursenoteSava();"/>
					</td>
                          
					</tr>
				</tbody>
				
			</table>
		</form>
		<%--
			<ul class="gkkcym_x5g">
				<li>
					<a href="#">
						<img src="images/banner_01.png" alt="">
						<div></div>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/banner_01.png" alt="">
						<div></div>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/banner_01.png" alt="">
						<div></div>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/banner_01.png" alt="">
						<div></div>
					</a>
				</li>
				<li>
					<a href="#">
						<img src="images/banner_01.png" alt="">
						<div></div>
					</a>
				</li>
			</ul>
			--%><div class="clear"></div>

			<div class="clear"></div>
		</div>
	</div>
</div>
      
        
    </div>
</div>



<script type="text/javascript">

var jsq = 0;
var endtime = 0;
	function coursenoteSava(){
		/* var id =document.getElementById("id").value;
		var tpk = document.getElementById("tpk").value;
		var coursestudyId=document.getElementById("coursestudyId").value; */
		var html = "";
		var courseId = document.getElementById("courseId").value;
			if(document.getElementById('coursestudyId').value==""){
			alert("该课程没有分配给您，无法记录笔记！");
				return false;
		}
		if(document.getElementById('coursenoteContent').value==""){
				alert("请输入笔记内容！");
				return false;
			}
		$.ajax({
      	type: "POST",
     	url: "coursenoteSava",
      	data: $('#noteForm').serialize(),
      	success: function(data){
      		 document.getElementById('coursenoteContent').value="";
       		 	
		      	document.getElementById("bj1").style.display="none";
		      	document.getElementById("bj2").style.display="block";
		      		for ( var i = 0; i < data.length; i++) {
						var list = data[i];

						html += '<td>' + list.coursenoteContent	+ '<br/></td>';

					}
					document.getElementById("bj2").innerHTML = html;
					
		      	
			}
      	});

	}	
		//每3秒刷新
			function timerJs(str){
				var ch = str.substring(str.indexOf('|')+1);
				var dq = parseInt(ch.substring(0,ch.indexOf('|')))+3;
				var zg = parseInt(str.substring(str.lastIndexOf('|')+1,str.length));
				if(dq>=zg){
					endtime=zg;
					videotimeJS1();
					jsq=1;
				}
			}
	
		//暂停1 播放0       暂停播放/当前时间/总时间
			function statusAndLength(str){
				var ch = str.substring(0,str.indexOf('|'));
				jsq = ch;
			}
		
		
		function videotimeJS1() {
				if(jsq==0){
			var cid = document.getElementById('cid').value;
			$.post("videotimeWebAxjx", {
			Action : "post",
			cid : cid,
			endtime : endtime
		}, function(data, textStatus) {
			

		}, "json");
		
	   } 
		}

	
window.onload=function(){ 
		var sum = document.getElementById('sum').value;
		if(sum==""){
			alert("您没有登录，如不登录不记录学习时间");
		}else{
	
		
		setInterval(videotimeJS,60000);
		function videotimeJS() {
				if(jsq==0){
			var cid = document.getElementById('cid').value;
			$.post("videotimeWebAxjx", {
			Action : "post",
			cid : cid,
			endtime : endtime
		}, function(data, textStatus) {
			

		}, "json");
		
	   } 
		}
		
	}
}
		


</script>
<!-- 底部 版权信息 -->
<%@ include file="../footer.jsp"%>
</body>
</html>
