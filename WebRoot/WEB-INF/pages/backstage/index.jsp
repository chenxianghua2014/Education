<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" class="off">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<title>在线教育</title>
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/zh-cn-system.css" rel="stylesheet" type="text/css" />
<link href="css/dialog.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="css/style/zh-cn-styles1.css" title="styles1" media="screen" />
<link rel="stylesheet" type="text/css" href="css/style/zh-cn-styles2.css" title="styles2" media="screen" />
<link rel="stylesheet"fr type="text/css" href="css/style/zh-cn-styles3.css" title="styles3" media="screen" />
<link rel="stylesheet" type="text/css" href="css/style/zh-cn-styles4.css" title="styles4" media="screen" />
<script language="javascript" type="text/javascript" src="js/jquery.min.js"></script> 
<script language="javascript" type="text/javascript" src="js/styleswitch.js"></script>
<script language="javascript" type="text/javascript" src="js/dialog.js"></script>
<script language="javascript" type="text/javascript" src="js/hotkeys.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.sgallery.js"></script>
<style type="text/css">
.objbody{overflow:hidden}
.btns{background-color:#666;}
.btns{position: absolute; top:116px; right:30px; z-index:1000; opacity:0.6;}
.btns2{background-color:rgba(0,0,0,0.5); color:#fff; padding:2px; border-radius:3px; box-shadow:0px 0px 2px #333; padding:0px 6px; border:1px solid #ddd;}
.btns:hover{opacity:1;}
.btns h6{padding:4px; border-bottom:1px solid #666; text-shadow: 0px 0px 2px #000;}
.btns .pd4{ padding-top:4px; border-top:1px solid #999;}
.pd4 li{border-radius:0px 6spx 0px 6px; margin-top:2px; margin-bottom:3px; padding:2px 0px;}
.btns .pd4 li span{padding:0px 6px;}
.pd{padding:4px;}
.ac{background-color:#333; color:#fff;}
.hvs{background-color:#555; cursor: pointer;}
.bg_btn{background: url(images/admin_img/icon2.jpg) no-repeat; width:32px; height:32px;}
</style>
</head>
<body scroll="no" class="objbody">
<div class="header">
	<div class="logo lf">
		<a href="index" >
			<span class="invisible">在线教育系统</span>
		</a>
	</div>
    <div class="col-auto">
    	<div class="log white cut_line">[您好！${sessionScope.sessionUser.accountLoginid}  ]
			<span>|</span>
			<a href="logout">[退出]</a>
			<span>|</span>
    		<a href="index" id="site_homepage">站点首页</a>&nbsp;&nbsp;
    	</div>
    </div>
</div>

<div id="content" style="width: auto;">
	<div class="col-left left_menu">
    	<div id="Scroll" style="height: 510px;">
    	<!-- ****************************************集团****************************************************** -->
			<div id="leftMain"  style=" overflow-x:no;overflow-y:no;height:640px;width:135px">
			<c:if test="${sessionScope.sessionUser.accountType!=5 && sessionScope.sessionUser.accountType!=4}">
				<h3 class="f14" onclick="showUl('switchsul1','span1');"><span id="span1" class="switchs cu" title="展开与收缩"></span>信息管理</h3>
				<ul id="switchsul1" style="display:none">
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showInformationTree(1);">新闻</a></li>
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showInformationTree(2);">通知</a></li>
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showInformationTree(3);">计划</a></li>
					<c:if test="${sessionScope.sessionUser.accountType==1 }">
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showInformationTree(4);">滚动新闻</a></li>
					</c:if>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul2','span2');"><span id="span2" class="switchs cu" title="展开与收缩"></span>学员管理</h3>
				<ul id="switchsul2" style="display:none">
					<li id="course" class="sub_menu"><a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showStudentszzjgTree(1);">部门</a></li>
					<li id="course" class="sub_menu"><a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showStudentszzjgTree(2);">学员</a></li>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul3','span3');"><span id="span3" class="switchs cu" title="展开与收缩"></span>课程管理</h3>
				<ul id="switchsul3" style="display:none">
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showtree();">课程</a></li><%--
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showTreeOne();">课程导入</a></li>
				 	<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showTreeThree();">课程目录管理</a></li> 
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showTreeTwo();">课程分配原则</a></li>
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" >课程分配权限</a></li>--%>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul4','span4');"><span id="span4" class="switchs cu" title="展开与收缩"></span>考试管理</h3>
				<ul id="switchsul4" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showSubjectTree();">试题制作</a>
					</li>
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showScoreView();">成绩管理</a>
					</li>
				</ul>
				</c:if>
					<c:if test="${sessionScope.sessionUser.accountType!=5&& sessionScope.sessionUser.accountType!=4}">
				<h3 class="f14" onclick="showUl('switchsul5','span5');"><span id="span5" class="switchs cu" title="展开与收缩"></span>培训班管理</h3>
				<ul id="switchsul5" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showtranningCourse(1);">常规培训班</a>
					</li>
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showtranningCourse(2);">专题培训班</a>
					</li>
					<!-- <li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showtranningCourse(3);">党校培训班</a>
					</li> -->
					<c:if test="${sessionScope.sessionUser.accountType==1}">
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showtranningShowtree();">党校培训班</a></li>
				   </c:if>
				</ul>
				</c:if>
				<c:if test="${sessionScope.sessionUser.accountType!=5&& sessionScope.sessionUser.accountType==4}">
				<h3 class="f14" onclick="showUl('switchsul5','span5');"><span id="span5" class="switchs cu" title="展开与收缩"></span>培训班管理</h3>
				<ul id="switchsul5" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showtranningCourse(4,'${cla.classresourceId}');">${cla.classresourceName}</a>
					</li>
				</ul>
				</c:if>
				<c:if test="${sessionScope.sessionUser.accountType!=5 && sessionScope.sessionUser.accountType!=4}">
				<h3 class="f14" onclick="showUl('switchsul6','span6');"><span id="span6" class="switchs cu" title="展开与收缩"></span>党校管理</h3>
				<ul id="switchsul6" style="display:none">
					<li id="course" class="sub_menu" style="color:#AFB8C0">中央党校国资委分校</li>
					<li id="course" class="sub_menu" style="color:#AFB8C0">集团党校</li>
					<li id="course" class="sub_menu"><a href="javascript:;" hidefocus="true" style="outline:none;" onclick="showInformationTree2(1);">党校新闻</a></li>
					<li id="course" class="sub_menu"><a href="javascript:;" hidefocus="true" style="outline:none;" onclick="showInformationTree2(2);">党校通知</a></li>
					<li id="course" class="sub_menu"><a href="javascript:;" hidefocus="true" style="outline:none;" onclick="showInformationTree2(3);">党校计划</a></li>
					<c:if test="${sessionScope.sessionUser.accountType==1 }">
					<li id="course" class="sub_menu"><a href="javascript:;" hidefocus="true" style="outline:none;" onclick="showInformationTree2(4);">党校导航新闻</a></li>
					</c:if>
					<li id="course" class="sub_menu"><a href="javascript:;" hidefocus="true" style="outline:none;" onclick="showtree();">课程管理</a></li>
					<li id="course" class="sub_menu"><a href="javascript:void(0)" hidefocus="true" style="outline:none;" onclick="showOperationManagementTree();">作业管理</a></li>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul7','span7');"><span id="span7" class="switchs cu" title="展开与收缩"></span>问卷调查</h3>
				<ul id="switchsul7" style="display:none">
					<li id="course" class="sub_menu">
						问卷调查
					</li>
				</ul>
				</c:if>
				<c:if test="${sessionScope.sessionUser.accountType!=5}">
				<h3 class="f14" onclick="showUl('switchsul8','span8');"><span id="span8" class="switchs cu" title="展开与收缩"></span>学习交流</h3>
				<ul id="switchsul8" style="display:none">
				<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="bbs();">学习圈子</a>
					</li>
					<c:if test="${sessionScope.sessionUser.accountType!=4}">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="examine();">学习圈子审核</a>
					</li>
					</c:if>
				</ul>
				</c:if>
				<c:if test="${sessionScope.sessionUser.accountType!=5 && sessionScope.sessionUser.accountType!=4}">
				<h3 class="f14" onclick="showUl('switchsul9','span9');"><span id="span9" class="switchs cu" title="展开与收缩"></span>笔记管理</h3>
				<ul id="switchsul9" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showHomeworkTree();">笔记管理</a>
					</li>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul10','span10');"><span id="span10" class="switchs cu" title="展开与收缩"></span>师资管理</h3>
				<ul id="switchsul10" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;"  onclick="showTeacherTree();">师资管理</a>
					</li>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul11','span11');"><span id="span11" class="switchs cu" title="展开与收缩"></span>资源管理</h3>
				<ul id="switchsul11" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showKnowledgeTree();">资源浏览</a>
					</li>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul12','span12');"><span id="span12" class="switchs cu" title="展开与收缩"></span>评价管理</h3>
				<ul id="switchsul12" style="display:none">
				<!-- 	<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showEstimateTree(1);">课程评价</a>
					</li> -->
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showEstimateTree();">课程评价</a>
					</li>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul13','span13');"><span id="span13" class="switchs cu" title="展开与收缩"></span>报告管理</h3>
				<ul id="switchsul13" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showCourseStudyTree();">培训报告</a>
					</li>
				</ul>
				<h3 class="f14" onclick="showUl('switchsul14','span14');"><span id="span14" class="switchs cu" title="展开与收缩"></span>上传管理</h3>
				<ul id="switchsul14" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showUploadTree();">上传记录</a>
					</li>
				</ul>
				</c:if>
				
				<c:if test="${sessionScope.sessionUser.accountType!=4}">
				<h3 class="f14" onclick="showUl('switchsul15','span15');"><span id="span15" class="switchs cu" title="展开与收缩"></span>系统管理</h3>
				<ul id="switchsul15" style="display:none">
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="showAdminTree();">系统管理</a>
					</li>
					<li id="course" class="sub_menu">
						<a href="javascript:void(0);" hidefocus="true" style="outline:none;" onclick="log();">日志管理</a>
					</li>
				</ul>
				</c:if>
				
				<h3 class="f14" style="color:#AFB8C0" onclick=""><span id="span16" class="switchs cu" title="展开与收缩"></span>虚拟教室管理</h3>
				
			</div>
			
			<!-- ****************************************集团****************************************************** -->
		</div>
        <a href="javascript:;" id="openClose" style="outline: invert none medium; height: 560px;" hidefocus="hidefocus" class="open" title="展开与关闭"><span class="hidden">展开</span></a>
    </div>

	<div class="col-1 lf cat-menu" style="display:none;" id="display_center_id" height="100%">
		<div class="content">
			<iframe name="center_frame" id="center_frame" src="" frameborder="false" scrolling="auto" style="border: none; height: 465px;" width="100%" height="auto" allowtransparency="true">
				
			</iframe>
		</div>
	</div>

	<div class="col-auto mr8">
		<div class="crumbs">
			<!-- <div class="shortcut cu-span">
				<a href="#" target="right"><span>生成首页</span></a>
				<a href="#" target="right"><span>更新缓存</span></a>
				<a href="#"><span>后台地图</span></a>
			</div>-->
			当前位置：<span id="current_pos"></span>
		</div> 
    	<div class="col-1">
        	<div class="content" style="position:relative; overflow:hidden">
                 <iframe name="right" id="rightMain" src="" frameborder="false" scrolling="auto" style="border: none; margin-bottom: 30px; height: 491px;" width="100%" height="auto" allowtransparency="true">
                
                </iframe> 
             <!--      <iframe name="right" id="rightMain" src="" frameborder="false" scrolling="auto" style="border: none; margin-bottom: 30px; height: 491px;width: 1000px;"  width="auto" height="auto" allowtransparency="true">
                
                </iframe>  -->
                <div class="fav-nav">
					<div id="panellist"></div>
					<div id="paneladd"></div>
					<input type="hidden" id="menuid" value=""/>
					<input type="hidden" id="bigid" value=""/>
                    <div id="help" class="fav-help"></div>
				</div>
        	</div>
        </div>
    </div>
</div>

<script type="text/javascript"> 
function setRightMain(url){
	$("#rightMain").attr("src",url);
}

if(!Array.prototype.map)
Array.prototype.map = function(fn,scope) {
  var result = [],ri = 0;
  for (var i = 0,n = this.length; i < n; i++){
	if(i in this){
	  result[ri++]  = fn.call(scope ,this[i],i,this);
	}
  }
return result;
};

var getWindowSize = function(){
return ["Height","Width"].map(function(name){
  return window["inner"+name] ||
	document.compatMode === "CSS1Compat" && document.documentElement[ "client" + name ] || document.body[ "client" + name ]
});
}
window.onload = function (){
	if(!+"\v1" && !document.querySelector) { // for IE6 IE7
	  document.body.onresize = resize;
	} else { 
	  window.onresize = resize;
	}
	function resize() {
		wSize();
		return false;
	}
}
function wSize(){
	//这是一字符串
	var str=getWindowSize();
	var strs= new Array(); //定义一数组
	strs=str.toString().split(","); //字符分割
	var heights = strs[0]-150,Body = $('body');$('#rightMain').height(heights);   
	//iframe.height = strs[0]-46;
	if(strs[1]<980){
		$('.header').css('width',980+'px');
		$('#content').css('width',980+'px');
		Body.attr('scroll','');
		Body.removeClass('objbody');
	}else{
		$('.header').css('width','auto');
		$('#content').css('width','auto');
		Body.attr('scroll','no');
		Body.addClass('objbody');
	}
	
	var openClose = $("#rightMain").height()+39;
	$('#center_frame').height(openClose+9);
	$("#openClose").height(openClose+30);	
	$("#Scroll").height(openClose-20);
	windowW();
}
wSize();
function windowW(){
	if($('#Scroll').height()<$("#leftMain").height()){
		$(".scroll").show();
	}else{
		$(".scroll").hide();
	}
}
windowW();


//左侧开关
$("#openClose").click(function(){
	if($(this).data('clicknum')==1) {
		$("html").removeClass("on");
		$(".left_menu").removeClass("left_menu_on");
		$(this).removeClass("close");
		$(this).data('clicknum', 0);
		$(".scroll").show();
	} else {
		$(".left_menu").addClass("left_menu_on");
		$(this).addClass("close");
		$("html").addClass("on");
		$(this).data('clicknum', 1);
		$(".scroll").hide();
	}
	return false;
});

function _M(menuid,targetUrl) {
	$("#menuid").val(menuid);
	$("#bigid").val(menuid);
	$("#paneladd").html('<a class="panel-add" href="javascript:add_panel();"><em>添加</em></a>');
	if(menuid!=8) {
		$("#leftMain").load("?m=admin&c=index&a=public_menu_left&menuid="+menuid, {limit: 25}, function(){
		   windowW();
		 });
	} else {
		$("#leftMain").load("?m=admin&c=phpsso&a=public_menu_left&menuid="+menuid, {limit: 25}, function(){
		   windowW();
		 });
	}
	//$("#rightMain").attr('src', targetUrl);
	$('.top_menu').removeClass("on");
	$('#_M'+menuid).addClass("on");
	$.get("?m=admin&c=index&a=public_current_pos&menuid="+menuid, function(data){
		$("#current_pos").html(data);
	});
	//当点击顶部菜单后，隐藏中间的框架
	$('#display_center_id').css('display','none');
	//显示左侧菜单，当点击顶部时，展开左侧
	$(".left_menu").removeClass("left_menu_on");
	$("#openClose").removeClass("close");
	$("html").removeClass("on");
	$("#openClose").data('clicknum', 0);
	$("#current_pos").data('clicknum', 1);
}
function _MP(menuid,targetUrl) {
	$("#menuid").val(menuid);
	$("#paneladd").html('<a class="panel-add" href="javascript:add_panel();"><em>添加</em></a>');

	$("#rightMain").attr('src', targetUrl+'&menuid='+menuid+'&pc_hash='+pc_hash);
	$('.sub_menu').removeClass("on fb blue");
	$('#_MP'+menuid).addClass("on fb blue");
	$.get("?m=admin&c=index&a=public_current_pos&menuid="+menuid, function(data){
		$("#current_pos").html(data+'<span id="current_pos_attr"></span>');
	});
	$("#current_pos").data('clicknum', 1);
	show_help(targetUrl);
}

function add_panel() {
	var menuid = $("#menuid").val();
	$.ajax({
		type: "POST",
		url: "?m=admin&c=index&a=public_ajax_add_panel",
		data: "menuid=" + menuid,
		success: function(data){
			if(data) {
				$("#panellist").html(data);
			}
		}
	});
}
function delete_panel(menuid, id) {
	$.ajax({
		type: "POST",
		url: "?m=admin&c=index&a=public_ajax_delete_panel",
		data: "menuid=" + menuid,
		success: function(data){
			$("#panellist").html(data);
		}
	});
}

function paneladdclass(id) {
	$("#panellist span a[class='on']").removeClass();
	$(id).addClass('on')
}
setInterval("session_life()", 160000);
function session_life() {
	$.get("?m=admin&c=index&a=public_session_life");
}
function lock_screen() {
	$.get("?m=admin&c=index&a=public_lock_screen");
	$('#dvLockScreen').css('display','');
}
function check_screenlock() {
	var lock_password = $('#lock_password').val();
	if(lock_password=='') {
		$('#lock_tips').html('<font color="red">密码不能为空。</font>');
		return false;
	}
	$.get("?m=admin&c=index&a=public_login_screenlock", { lock_password: lock_password},function(data){
		if(data==1) {
			$('#dvLockScreen').css('display','none');
			$('#lock_password').val('');
			$('#lock_tips').html('锁屏状态，请输入密码解锁');
		} else if(data==3) {
			$('#lock_tips').html('<font color="red">密码重试次数太多</font>');
		} else {
			strings = data.split('|');
			$('#lock_tips').html('<font color="red">密码错误，您还有'+strings[1]+'次尝试机会！</font>');
		}
	});
}

function menuScroll(num){
	var Scroll = document.getElementById('Scroll');
	if(num==1){
		Scroll.scrollTop = Scroll.scrollTop - 60;
	}else{
		Scroll.scrollTop = Scroll.scrollTop + 60;
	}
}
//显示党校培训班的树状列表
function showtranningShowtree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=tree|partyClassTree&i=0&c=${sessionScope.sessionUser.accountCatalog}';
}
//显示课程的树状列表
function showtree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=course|tree&i=0&c=${sessionScope.sessionUser.accountCatalog}';
}
//显示课程导入的树状列表
function showTreeOne(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=course|tree&i=1&c=${sessionScope.sessionUser.accountCatalog}';
}
//显示课程分配原则的树状列表
function showTreeTwo(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=course|tree&i=2&c=${sessionScope.sessionUser.accountCatalog}';
}
//课程分配原则
function showTreeThree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=course|tree&i=3&c=${sessionScope.sessionUser.accountCatalog}';
}
//左侧菜单伸缩
function showUl(u,s){
	if(document.getElementById(u).style.display == "none"){
		document.getElementById(u).style.display = "block";
		$("#"+s).addClass("on");
	}else{
		document.getElementById(u).style.display = "none";
		$("#"+s).removeClass("on");
	}
}
//显示学员注册的树状列表
function showStudentsTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='studentsTree';
}
//显示笔记管理的树状列表
function showHomeworkTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='homeworkTree';
}
//显示课程管理的树状列表
function showCourseTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='courseTree';
}
//显示作业管理的树状列表
function showOperationManagementTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=tree|operationManagementTree&i=0&c=${sessionScope.sessionUser.accountCatalog}';

}
//显示信息的树状列表
function showInformationTree(num){
	document.getElementById('display_center_id').style.display = 'block';
	if(num == 1){
		document.getElementById('center_frame').src='zzjgTree?n=1';
	}
	if(num == 2){
		document.getElementById('center_frame').src='zzjgTree?n=2';
	}
	if(num == 3){
		document.getElementById('center_frame').src='zzjgTree?n=3';
	}
	if(num == 4){
		document.getElementById('display_center_id').style.display = 'none';
		document.getElementById('rightMain').src="journalism?newsSort=2&mark=1&organizationId=test001&page=1&cj=1"
	}
}
//显示学员管理的树状列表
function showStudentszzjgTree(num){
	document.getElementById('display_center_id').style.display = 'block';
	if(num == 1){
		document.getElementById('center_frame').src='zzjgStudentsTree?n=1';
	}
	if(num == 2){
		document.getElementById('center_frame').src='zzjgStudentsTree?n=2';
	}
	
}
//组织结构表
function toOrganization(){
	document.getElementById('display_center_id').style.display = 'none';
	document.getElementById('rightMain').src='/../eduOnline/ShowReport.wx?PAGEID=organization';
}
//显示部门的树状列表showSurveyTree
function showDepartmentTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='departmentTree';
}
//显示师资管理的树状列表showSurveyTree
function showTeacherTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='teacherTree';
}
//显示调查问卷的树状列表
function showSurveyTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='surveyTree';
}
//显示资源浏览的树状列表
function showKnowledgeTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=backResources|tree&c=${sessionScope.sessionUser.accountCatalog}';
}
//显示学习交流的树状列表
function showFroumTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='froumTree';
}
//评价管理目录
function showEstimateTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='redirect2?r=backEstimate|tree&c=${sessionScope.sessionUser.accountCatalog}';
}
//显示培训报告列表
function showCourseStudyTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='trainingReportTree';
}
//显示上传报告列表
function showUploadTree(){
	document.getElementById('display_center_id').style.display = 'none';
	document.getElementById('rightMain').src="uploadReports?&mark=1&page=1"
}
//培训班目录
function showtranningCourse(num,ids){
	document.getElementById('display_center_id').style.display = 'block';
	if(num == 1){
		document.getElementById('center_frame').src='tranningCourseTree?n=1';
	}
	if(num == 2){
		document.getElementById('center_frame').src='tranningCourseTree?n=2';
	}
	if(num == 3){
		document.getElementById('center_frame').src='tranningCourseTree?n=3';
	}
	if(num == 4){
		document.getElementById('center_frame').src='tranningCourseTree?n=4&classresourceId='+ids;
	}
}

//党校目录
function showPartySchoolTree(num){
	document.getElementById('display_center_id').style.display = 'block';
	if(num == 1){
		document.getElementById('center_frame').src='partySchoolTree?n=1';
	}
	if(num == 2){
		document.getElementById('center_frame').src='partySchoolTree?n=2';
	}
}
function showInformationTree2(num){
	document.getElementById('display_center_id').style.display = 'block';
	if(num == 1){
		document.getElementById('center_frame').src='schoolzzjgTree?n=1';
	}
	if(num == 2){
		document.getElementById('center_frame').src='schoolzzjgTree?n=2';
	}
	if(num == 3){
		document.getElementById('center_frame').src='schoolzzjgTree?n=3';
	}
	if(num == 4){
		document.getElementById('display_center_id').style.display = 'none';
		document.getElementById('rightMain').src="schooljournalism?newsSort=4&mark=1&organizationId=test001&page=1&cj=1"
	}
	
}
function showSubjectTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='syllabusTree';
}
function showScoreView(){
	/*	document.getElementById('display_center_id').style.display = 'none';
	document.getElementById('rightMain').src='scoreView'; */
 	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='achievementTree'; 
}
function showSubjectTrees(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='subjectTrees';
}
function showUploadStudyTree(){
	document.getElementById('display_center_id').style.display = 'block';
	document.getElementById('center_frame').src='uploadTree';
}
function showAdminTree(){
	//document.getElementById('display_center_id').style.display = 'block';
	//document.getElementById('center_frame').src='adminTree';
	document.getElementById('display_center_id').style.display = 'none';
	document.getElementById('rightMain').src="accountTree"
}
function bbs() {
	document.getElementById('display_center_id').style.display = 'none';
	document.getElementById('rightMain').src="queryBoardByPage?page=1&examine=1";
}
function examine() {
	document.getElementById('display_center_id').style.display = 'none';
	document.getElementById('rightMain').src="queryBoardByPage?page=1&examine=0";
}
function log() {
	document.getElementById('display_center_id').style.display = 'none';
	document.getElementById('rightMain').src="queryLogByPage?page=1";
}

</script>
</body>
</html>