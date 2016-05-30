<%@page contentType="text/html" pageEncoding="UTF-8"%>

<script language="JavaScript">

	if(top!=self)
	if(self!=top) top.location=self.location;

</script>
<!--[if IE 6]>
<script src="js/DD_belatedPNG.js"></script>
<script>
  DD_belatedPNG.fix('img');
</script>
<![endif]-->
<script type="text/javascript">
function instation(){
	document.ff.submit();
}
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
            <div class="search">
                <div class="search_left">
                	<p>站内搜索</p>
                </div>
                <div class="search_right">
                	<form action="mainInstation?mark=1" method="post" name="ff" id="ff">
                    	<input type="text" class="search_text" name="name" id="name">
                        <input type="button" onclick="instation()" class="search_btn">
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

			
			