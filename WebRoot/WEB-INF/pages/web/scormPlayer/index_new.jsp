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
<!--[if IE 6]>
<script src="js/DD_belatedPNG.js"></script>
<script>
  DD_belatedPNG.fix('img');
</script>
<![endif]-->
<script src="js/jquery/jquery-1.9.1.min.js"></script>
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
<div class="header_red">
  
	<div class="concainer">
        <div class="header_top">
            <div class="header_top_content">
            	<c:if test="${sessionScope.Student==null}">
            	  <div class="login fl_right">
            	 <a href="javascript:loginform();">登录</a>
            	  </div>
            	</c:if>
            	<c:if test="${sessionScope.Student!=null}">
            	<div class="logind fl_right">
            	 <div class="loginp">
						<p>欢迎，${sessionScope.Student.studentName}</p>
						<a href="mainlogout">退出<span></span></a>
					</div>
					<img src="images/toux.jpg" alt="我的头像" width="40px" height="40px">
					</div>
            	</c:if>
               
            </div>
        </div>
        <div class="header_menu">
            <div class="logo">
            	<h2><a href="#"><img src="images/logo_img_red.png" alt="logo"></a></h2>
            </div>
            <div class="search"  style="height:44px;">
                <div class="search_left" >
                	<p>站内搜索</p>
                </div>
                <div class="search_right">
                	<form>
                    	<input type="text" class="search_text">
                        <input type="button" class="search_btn">
                    </form>
                </div>
            </div>
        </div>
    </div>
 <div class="navred">
    	<div class="concainer">
            <ul>
                <li class="li_bg"><a href="partySchoolTraining">首页</a></li>
                <li class="li_bg"><a href="Schooltraining" >党校动态</a></li>
                 <c:if test="${sessionScope.Student!=null}">
                <li class="li_bg"><a href="partySchool?page=1&sid=0" >党校课程</a></li>
                	<li><a href="mystudent">我的学习</a></li>
				</c:if>
				<c:if test="${sessionScope.Student==null}">
				<li class="li_bg"><a href="javascript:loginform();" >党校课程</a></li>
					<li><a href="javascript:loginform();">我的学习</a></li>
				</c:if>
            </ul>
        </div>
    </div>   
     <div class="logink reveal-modal" id="login" style="display:none;visibility: visible;">
        		<h2 style="background: #EA1010;">登录中国航天科工在线培训平台</h2>
        <form action="checkLogin" method="post" name="form1" id="form1">
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
        <p style="text-align:center;"><input style="background: #EA1010;" name="sub" type="button" onclick="javascript:checkLogin();"  value="登录" class="reveal-modal_btn"/></p>
        <a class="close-reveal-modal" href="javascript:loginform();">&#215;</a>
        
        </form>
        </div>
	 
        
</div>

<div class="main">
	<div class="concainer">
    	<div class="training_dynamic"  style="overflow:hidden; height:60px; position:relative;">
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

	
	$(document).ready(function(){ 
		var sum = document.getElementById('sum').value;
		if(sum==""){
			alert("您没有登录，如不登录不记录学习时间");
		}else{
			setInterval(videotimeJS,60000);
			function videotimeJS() {
				if(jsq==0){
					var cid = document.getElementById('cid').value;
					$.post(
						"videotimeWebAxjx", 
						{
							Action : "post",
							cid : cid,
							endtime : endtime
						}, 
						function(data, textStatus) {}, 
						"json"
					);
	   			} 
			}
		}
	});
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
					window.location.href = "partySchoolTraining";
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
<%@ include file="../../footerS.jsp"%>

</BODY>
</HTML>
