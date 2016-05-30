<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<HTML>
<HEAD>
<TITLE>scorm课件播放</TITLE>
<META http-equiv=Content-Type content="text/html; charset=UTF-8">
<META http-equiv=Pragma content=no-cache>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Expires content=-1000>
<link rel="stylesheet" type="text/css" href="css/scorm/admin.css">
<link rel="stylesheet" type="text/css" href="css/scorm/bootstrap.css">
<script src="js/scorm/scorm.js"></script>
<script src="js/scorm/ajax.js"></script>

<link rel="stylesheet" type="text/css" href="css/index_party.css">
<link rel="stylesheet" type="text/css" href="css/bcss/index_others.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<style type="text/css">
	#col-md-2{
	  width:15%;
	  float:left;
	  clear:left;
	  overflow:hidden;
	}
	#col-md-8{
		width:85%;
		float:right;
		clear:right;
		overflow:hidden;
	}
</style>
</HEAD>
<BODY>
<input type="hidden" id="cid" name="cid" value="${courseresource.courseresourceId}">
<input type="hidden" id="sum" name="sum" value="${sum}">
<div class="header">
   	 <c:if test="${tpk==1 }">
		<%-- <%@ include file="../../top.jsp"%> --%>
		        <div class="concainer">
        <div class="header_top">
            <div class="header_top_content">
            <c:if test="${sessionScope.Student==null}">
                <a href="javascript:loginform();">登录</a>
                </c:if>
            <c:if test="${sessionScope.Student!=null}">
              <a href="mainlogout">退出</a>
              </c:if>
            </div>
        </div>
     <div class="header_menu">
            <div class="logo">
            	<h2><a href="#"><img src="images/logo_img.png" alt="logo"></a></h2>
            </div>
            <div class="search" style="height:44px;">
                <div class="search_left">
                	<p>站内搜索</p>
                </div>
                <div class="search_right">
                	<form>
                    	<input type="text" class="search_text">
                        <input type="button" class="search_btn">
                    </form>
                </div>
            </div>
            <div class="logo_right">
            	<h2><a href="#"><img src="images/logo_img_right.png" alt="logo"></a></h2>
            </div>
        </div>

    </div>
    <div class="nav">
    	<div class="concainer">
           <ul>
        		<c:if test="${sessionScope.Student!=null}">
            	<li class="one"><a href="main" class="nav1">首页</a></li>
            	<li><a href="courseWebAll?mark=1&page=1">课程目录</a></li>
            	 <li><a href="mystudent">我的课程</a></li>
            	  <li><a href="allTest?type=1&page=1">我的考试</a></li>
            	</c:if>
            	<c:if test="${sessionScope.Student==null}">
            	<li class="one"><a href="mainout" class="nav1">首页</a></li>
            	<li><a href="javascript:loginform();">课程目录</a></li>
            	 <li><a href="javascript:loginform();">我的课程</a></li>
            	  <li><a href="javascript:loginform();">我的考试</a></li>
            	</c:if>
                
                <li><a href="training">培训动态</a></li>
                <c:if test="${sessionScope.Student!=null}">
                 <li><a href="allTranningCourse?mark=1&organizationId=${sessionScope.Student.studentCompanyid}&page=1&qtId=1&cj=${sessionScope.Student.organization.organizationFwqx}">培训班</a></li>
                <li><a href="ResourceWebTree?mark=1&page=1">知识资源</a></li>
                <li><a href="board">论坛</a></li> 
                </c:if>
                 <c:if test="${sessionScope.Student==null}">
                 <li><a href="javascript:loginform();">培训班</a></li>
                 <li><a href="javascript:loginform();">知识资源</a></li>
                 <li><a href="javascript:loginform();">论坛</a></li>
                </c:if>

            </ul>
        </div>
    </div>
        
        
        <div class="logink reveal-modal" id="login" style="display:none;">
        		<h2>登录中国航天科工在线培训平台</h2>
        <form action="checkLogin" method="post" name="form1" id="form1" >
		<div class="logink_1">
			<p>培训平台账户</p>
			<input type="text"  id="studentSeq" name = "studentSeq" value="">
		</div>
		<div class="logink_2">
			<p>密码</p>
			<a href="#">忘记密码？</a>
			<div class="clear"></div>
			<input type="password" id="studentPassword" name="studentPassword" value="">
		</div>
		<div class="logink_3">
			<p>验证码</p>
			<input type="text" value="" id="code" name="code">
			<img id='code_img' onclick='this.src=this.src+"?"+Math.random()' src='image.jsp' />
		</div>
		<br>
        <p style="text-align:center;"><input name="sub" type="button" onclick="javascript:checkLogin();"  value="登录" class="reveal-modal_btn"/></p>
        <a class="close-reveal-modal" href="javascript:loginform();">&#215;</a>
        
        </form>
        </div>
	 </c:if>
	 <c:if test="${tpk==2 }">
		<%@ include file="../../Schooltop.jsp"%>
	 </c:if>
        
</div>

<div class="main">
	<div class="concainer">
    	<div class="training_dynamic" style="overflow:hidden; height:60px; position:relative;">
        	<div class="training_title"><a href="courseWebAll?mark=1&page=1">课程目录</a> > ${courseresource.courseresourceName}</div> 
        	<div style="position: absolute;right: 35px;top: 22px"><a href="javascript:history.go(-2)">返回</a></div>
        	<br><br> 
        </div>
    <div class="bbs_home" >
	<div class="wrap">
		<div class="gkkcym">
		<div id="flashContent">
			<br>
			<center><iframe src="courseScorm?id=${id}" name="player" frameborder="1" scrolling="yes" height="700px" width="100%"></iframe></center>
		</div>
		<div id="bj1" style="display:block; overflow:scroll; width:1200px; height:100px;">
			<c:forEach items="${coursenoteList}" var="coursenoteList" varStatus="c">
				<td id="bj11">${coursenoteList.coursenoteContent }</td><br/>
			</c:forEach>
        </div>
        <div id="bj2" style="display:none; overflow:scroll; width:1000px; height:100px;"></div>
		<form id="ttt" name="ttt">
			<input type="hidden" id="coursestudyId" name="coursestudyId" value="${coursestudyId}" /> 
		</form>
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
                          <td colspan="2"><input type="button" id ="saveNote" value="发布"/>
					</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="clear"></div>

		<div class="clear"></div>
		</div>
	</div>
</div>
      
        
    </div>
</div>

<div style="display:none">
<div id="links">
	<b><a href="javascript:void(0);" onclick="Utils.launchSCO(); return false;">Launch SCO</a></b> | <a href="javascript:void(0);" onclick="Utils.clearCookieData(); return false;">Clear Storage</a> | <a href="javascript:void(0);" onclick="$('debug').innerHTML=''; return false;">Clear Event Log</a> | <a href="javascript:void(0);" onclick="document.location=document.location.href;return false;">Refresh Page</a>
</div>
<fieldset>
	<legend>Event Log <b>( <a href="javascript:void(0);" onclick="Utils.toggleDisplay('debug'); return false;">Toggle</a> | <a href="javascript:void(0);" onclick="Utils.dumpAPI(); return false;">Dump Current API Object</a> )</b></legend>
	<div id="debug"></div>
</fieldset>
<fieldset>
	<legend>Options <b>( <a href="javascript:void(0);" onclick="Utils.toggleDisplay('optionSet'); return false;">Toggle</a> )</b></legend>
	<div id="optionSet">
		<div class="tblRowInstructions">Enter the name of the storage to use for this session (or to clear using the option above):</div>
		<div class="tblRow"><div class="tblRowHeader">Storage Name:</div><input type="text" id="cookieNameAlt" size="50"> <a href="javascript:void(0);" onclick="Utils.genNewSessionName(); return false;" class="actionLink">Generate New Name</a></div>
		<div class="tblRowInstructions">Enter the full/relative path/filename for the SCO's launch file. The default is currently &quot;<script>document.write(launchFile);</script>&quot;. Populated automatically if IMS manifest is present:</div>
		<div class="tblRow"><div class="tblRowHeader">Launch File:</div><input type="text" id="launchFileAlt" size="30"></div>
		<div class="tblRowInstructions">Override the default window options (Default values can be changed in the source):</div>
		<div class="tblRow"><div class="tblRowHeader">Width:</div><input type="text" id="winW" size="10"></div>
		<div class="tblRow"><div class="tblRowHeader">Height:</div><input type="text" id="winH" size="10"></div>
		<div class="tblRow">
			<div class="tblRowHeader">Features:</div>
			<div class="inlineOption"><label for="wToolbarOption">Toolbar</label> <input type="checkbox" id="wToolbarOption" onclick="Utils.toggleWindowOption('wToolbar',this);"></div>
			<div class="inlineOption"><label for="wTitlebarOption">Titlebar</label> <input type="checkbox" id="wTitlebarOption" onclick="Utils.toggleWindowOption('wTitlebar',this);"></div>
			<div class="inlineOption"><label for="wLocationOption">Location</label> <input type="checkbox" id="wLocationOption" onclick="Utils.toggleWindowOption('wLocation',this);"></div>
			<div class="inlineOption"><label for="wStatusOption">Statusbar</label> <input type="checkbox" id="wStatusOption" onclick="Utils.toggleWindowOption('wStatus',this);"></div>
			<div class="inlineOption"><label for="wScrollbarsOption">Scrollbars</label> <input type="checkbox" id="wScrollbarsOption" onclick="Utils.toggleWindowOption('wScrollbars',this);"></div>
			<div class="inlineOption"><label for="wMenubarOption">Menubar</label> <input type="checkbox" id="wMenubarOption" onclick="Utils.toggleWindowOption('wMenubar',this);"></div>
			<div class="inlineOption"><label for="wResizableOption">Resizable</label> <input type="checkbox" id="wResizableOption" onclick="Utils.toggleWindowOption('wResizable',this);"></div>

			<div class="optionGroup">
				<a href="#"  onclick="Utils.enableAllWindowOptions();return false;">Select All</a> | <a href="#" onclick="Utils.disableAllWindowOptions();return false;">Deselect All</a>
			</div>
		</div>

		<div class="tblRowInstructions">SimpleAPI behavioral settings:</div>
		<div class="tblRow"><input type="checkbox" id="closeOnFinishOption" onclick="Utils.toggleCloseOnFinishOption(this.checked);"> <label for="closeOnFinishOption">Close SCO on LMSFinish</label></div>
		<div class="tblRow"><input type="checkbox" id="toggleEmbeddedOption" onclick="Utils.toggleEmbeddedParam(this.checked);"> <label for="toggleEmbeddedOption">Launch with custom search string appended to launch file: </label> <input type="text" id="searchString" size="50"></div>
		<div class="tblRow"><input type="checkbox" id="toggleCustomKeyValueOption" onclick="Utils.toggleCustomKeyValueOption(this.checked);"> <label for="toggleCustomKeyValueOption">Launch with custom key/value pair injected into API object: </label><input type="text" id="customApiKey" size="30"> = <input type="text" id="customApiValue" size="30"></div>
	&nbsp;</div>
</fieldset>
</div>
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<script src="js/jquery/jquery.form.js"></script>
<script type="text/javascript">
	var jsq = 0;
	var endtime = 0;
	 
	$("#saveNote").click(function(){
		//$("#noteForm").submit(function(){
			if(document.getElementById('coursestudyId').value==""){
				alert("该课程没有分配给您，无法记录笔记！");
				return false;
			}
			if(document.getElementById('coursenoteContent').value==""){
				alert("请输入笔记内容！");
				return false;
			}
			
			var coursestudyId = $("#coursestudyId").val();
			var coursenoteDel = $("#coursenoteDel").val();
			var id = $("#id").val();
			var tpk = $("#tpk").val();
			var courseId = $("#courseId").val();
			var coursenoteContent = $("#coursenoteContent").val();
			var resultd=false;
			var dt="coursestudyId="+coursestudyId+
					"&coursenoteDel="+coursenoteDel+
					"&id="+id+
					"&tpk="+tpk+
					"&courseId="+courseId+
					"&coursenoteContent="+coursenoteContent;
			$.ajax({
		      	type: "POST",
		      	//async: false,
		     	url: "coursenoteSava",
		      	data: dt,
		      	success: function(data){
		      		resultd=true;
				}
	      	});
			
			document.getElementById('coursenoteContent').value="";
	      	//document.getElementById("bj1").style.display="none";
	      	//document.getElementById("bj2").style.display="block";
	      	//for ( var i = 0; i < resultd.length; i++) {
			//	var list = resultd[i];
			//	html += '<td>' + list.coursenoteContent	+ '<br/></td>';
			//}
			///document.getElementById("bj2").innerHTML = html;
			//
			//$("#bj1").children().before("<td id='bj11'>"+coursenoteContent+"</td><br>");
			 $("#bj1").prepend("<br>"); 
			$("#bj1").prepend("<td>"+coursenoteContent+"</td>");
			
			//$("#bj1").before("<td>"+coursenoteContent+"</td>");
			//$("#bj1").before("<br>");
	      	return false;
		//});	
		//return false;
	});
	
	//每3秒刷新
	function timerJs(str){
		var ch = str.substring(str.indexOf('|')+1);
		var dq = parseInt(ch.substring(0,ch.indexOf('|')));
		var zg = parseInt(str.substring(str.lastIndexOf('|')+1,str.length));
		if(dq>=zg){
			jsq=1;
			endtime=1;
		}
	}
	
		//暂停1 播放0       暂停播放/当前时间/总时间
	function statusAndLength(str){
		var ch = str.substring(0,str.indexOf('|'));
		jsq = ch;
	}

	
	function record(){ 
		//var sum = document.getElementById('sum').value;
		//if(sum==""){
		//	alert("您没有登录，如不登录不记录学习时间");
		//}else{
			setInterval(videotimeJS,60000);
			
			function videotimeJS() {
				if(jsq==0){
					var cid = $("#cid").val();
					$.ajax({
						type: "POST",
				      	//async: false,
				     	url: "videotimeWebAxjx",
				      	data: "cid="+cid+"&endtime="+endtime,
				      	success:function(){}
					});
	   			} 
			}
		//}
	}
		


</script>
<script language="JavaScript">

	if(top!=self)
	if(self!=top) top.location=self.location;

</script>
<script type="text/javascript">
	document.onkeydown=function(event){            
		var e = event || window.event || arguments.callee.caller.arguments[0];   
		if(e && e.keyCode==13){  
			checkLogin();
		}        
	}; 
	function loginform(){
		if(document.getElementById("login").style.display == 'none'){
			document.getElementById("login").style.display = 'block';
		}else{
			document.getElementById("login").style.display = 'none';
		}
	}
	function checkLogin(){
		var event=arguments.callee.caller.arguments[0]||window.event;
		if(event.keyCode==13){return false;}
		var studentSeq=document.getElementById("studentSeq").value;
		var studentPassword=document.getElementById("studentPassword").value;
		var code = document.getElementById("code").value;
		if(studentSeq!=" "&&studentSeq!=""){
			if(studentPassword!=" "&&studentPassword!=""){
			$.ajax({
			url:"StudentcheckLogin",
			type:"post",
			data:{studentSeq:studentSeq,
				studentPassword:studentPassword,
				code:code},
			success:function(msg){
				//alert(msg.status);
				if("error" == msg.status){
					alert(msg.MsgBody);
					location.reload() ;
				}else{
					window.location.href = "main";
				}
			}
		});
			}else{
				alert("密码不能为空！");
			}
		}else{
			alert("用户名不能为空！");
		}
	}
</script>

<!-- 底部 版权信息 -->
<%@ include file="../../footer.jsp"%>

</BODY>
</HTML>
