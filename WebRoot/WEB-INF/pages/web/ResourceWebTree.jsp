<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>我的课程</title>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />
<script type="text/javascript">

var cfg = false;
function select_all(){ //全选     
	if(cfg == false){
	    var inputs = document.getElementsByTagName("input");     
	    for(var i=0;i<inputs.length;i++)     
	    {     
	      if(inputs[i].getAttribute("type") == "checkbox")     
	      {     
	        inputs[i].checked = true;     
	      }     
	    }   
	    cfg = true;
	}else{
		 var inputs = document.getElementsByTagName("input");     
	    for(var i=0;i<inputs.length;i++)     
	    {     
	      if(inputs[i].getAttribute("type") == "checkbox")     
	      {     
	        inputs[i].checked = false;     
	      }     
	    }   
	    cfg= false;
	}
 }
function selectType(){
	var courseType = document.getElementById("test").value;
	var hrefStr = window.location.href
	if(hrefStr.indexOf("courseType=") > 0){
		window.location.href = hrefStr.replace(hrefStr.substring(hrefStr.indexOf("courseType="),hrefStr.indexOf("courseType=")+12),"courseType="+courseType);
	}else{
		if(hrefStr.indexOf("syllabusId=") > 0){
			var a = window.location.href = window.location.href + "&courseType=" + courseType;
		}else{
			var a = window.location.href = window.location.href + "?courseType=" + courseType;
		}
	}
}
//收藏
function collectionCourse(){
	var cfg2 = 0 ;
	var cfg1 = 0 ;
	var cfg0 = 0 ;
	var num = 0;
	var inputs = document.getElementsByName('checkbox');//获取所有的input标签对象。
	for(var i=0;i<inputs.length;i++){
		var obj = inputs[i];
		if(obj.checked==true){
			//alert(obj.value);
			num ++ ;
			 $.ajax({
				url:'collectionCourse',
				type:'post',
				data:{courseId:obj.value},
				async: false,
				success:function(d){
					if(d == 2){ //成功 
						cfg2++;
					}
					if(d == 1){ //重复
						cfg1++;
					}
					if(d == 0){
						cfg0++; //未登录
					}
					obj.checked = false;
				}
			}); 
		
     	}
	}
	if(num == 0){
		alert("请选择课程后点击收藏！");
	}else{
		if(cfg0 == 0){
			/* if(cfg1 != 0){
				alert("有"+cfg1+"门课程重复收藏！");
			} 
			if(cfg2 != 0){
				alert("成功收藏"+cfg2+"门课程！");
			}*/
			alert("成功收藏"+cfg2+"门课程！");
		}else{
			alert("请登录后再收藏！");
		}
	}
}
//报名
function addCourse(){
	var congif2 = 0 ;
	var congif1 = 0 ;
	var congif0 = 0 ;
	var number = 0;
	var inputs = document.getElementsByName('checkbox');//获取所有的input标签对象。
	for(var i=0;i<inputs.length;i++){
		var obj = inputs[i];
		if(obj.checked==true){
			//alert(obj.value);
			number ++ ;
			 $.ajax({
				url:'addCourse',
				type:'post',
				data:{courseId:obj.value},
				async: false,
				success:function(d){
					if(d == 2){ //成功 
						congif2 ++;
					}
					if(d == 1){ //重复
						congif1++;
					}
					if(d == 0){
						congif0++; //未登录
					}
					obj.checked = false;
				}
			}); 
		
     	}
	}
	if(number == 0){
		alert("请选择课程后点击报名！");
	}else{
		if(congif0 == 0){
			/* if(cfg1 != 0){
				alert("有"+cfg1+"门课程重复收藏！");
			} 
			if(cfg2 != 0){
				alert("成功收藏"+cfg2+"门课程！");
			}*/
			alert("成功报名"+congif2+"门课程！");
		}else{
			alert("请登录后再报名！");
		}
	}
}
/* function showSpanId(syllabusName){
	document.getElementById("spanId").innerHTML = syllabusName;
} */
</script>
</head>

<body>
<div class="header">
	<div class="wrap">
			<%@ include file="../top.jsp"%>
        </div>
    </div>
    
    
    
<div class="main">
		<div class="concainer">
			<div class="clearfix">
				<div class="my_courses_left">
					<div class="my_synopsis_2">
				<div class="tree">
				<c:forEach items="${knowledgeResource}" var="s">
				 <div class="tree_box">
				 <c:if test="${s.knowledgeResourceRank==0}">
        			<h3>
            			${s.knowledgeResourceName}
            		</h3>
            		
            		<ul class="tree_one" >
            		<c:forEach items="${knowledgeResource}" var="s1">
            		<c:if test="${s1.knowledgeResourceUpid==s.knowledgeResourceId}">
            		<h4>
                    <a href="ResourceWeblist?page=1&knowledgeResourceId=${s1.knowledgeResourceId}" > ${s1.knowledgeResourceName}</a>
               		 </h4>
            		</c:if>
            		</c:forEach>
            		 </ul>

            		 </c:if>
        		</div>
				</c:forEach>
            </div>
			</div>
			<script type="text/javascript">
				$(function(){
					var h3 = $(".tree_box").find("h3");
					var tree_one = $(".tree_box").find(".tree_one");
					var h4 = $(".tree_one").find("h4");
					var tree_two = $(".tree_one").find(".tree_two");
					h3.each(function(i){
						$(this).click(function(){
							tree_one.eq(i).slideToggle();
							tree_one.eq(i).parent().siblings().find(".tree_one").slideUp();
						})
					})
					h4.each(function(i){
						$(this).click(function(){
							tree_two.eq(i).slideToggle();
							tree_two.eq(i).parent().siblings().find(".tree_two").slideUp();
						})
					})
				})
			</script>
		</div>
		
		
		<div class="my_courses_right">
				<div class="training_title">知识资源</div>
				<div class="course_box">
					<table width="100%" border="1" cellspacing="0" cellpadding="0"
						bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
					<tr>	
					 <td width="10%" height="35" align="center" bgcolor="#73c2f7" class="font_white03" text-align="center">序号</td>
			          <td width="19%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">资源名称</td>
			          <td width="11%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">制作人</td>
			          <td width="12%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">浏览次数</td>
			          <td width="22%" height="35" align="center" bgcolor="#73c2f7" class="font_white03">操作</td>	
			        </tr>  
				<!-- 	<div class="course_rcontent_nav2 fl_left cnavbg cnavsx" style="width:130px">序号</div>
					<div class="course_rcontent_nav6 fl_left cnavbg cnavsx" style="width:260px">资源名称</div>
					<div class="course_rcontent_nav3 fl_left cnavbg cnavsx" style="width:150px">制作人</div>
					<div class="course_rcontent_nav3 fl_left cnavbg cnavsx" style="width:140px">浏览次数</div>
					<div class="course_rcontent_nav5 fl_left cnavbg cnavsx" style="width:150px">操作</div> -->
				
				<c:forEach items="${knowledge}" var="c" varStatus="cour" >	
					<tr>
					  <td height="28" bgcolor="#FFFFFF" class="font_black02">${cour.index + 1}</td>
			          <td height="28" bgcolor="#FFFFFF" class="font_black02">${c.knowledgeName}</td>
			          <td height="28" bgcolor="#FFFFFF" class="font_black02">${c.knowledgeProducer}</td>
			          <td height="28" bgcolor="#FFFFFF" class="font_black02">${c.knowledgeClick}</td>
			          <c:if test="${c.knowledgeCatalog=='1'}">
			          <td bgcolor="#FFFFFF" class="font_black02"><a href="selectResourcesOne?knowledgeId=${c.knowledgeId}" style="color:red;">下载/播放</a></td>
					  </c:if>
					  <c:if test="${c.knowledgeCatalog!='1'}">
			             <td bgcolor="#FFFFFF" class="font_black02">拒绝下载</td>
					  </c:if>
					</tr>
						<%-- <div class="course_rcontent_nav2 cnavsx fl_left" style="width:130px">${cour.index + 1}</div>
						<div class="course_rcontent_nav6 cnavsx fl_left" style="width:260px">${c.knowledgeName}</a></div>
						<div class="course_rcontent_nav3 cnavsx fl_left" style="width:150px">${c.knowledgeProducer}</div>
						<div class="course_rcontent_nav3 cnavsx fl_left" style="width:140px">${c.knowledgeClick}</div>
						<div class="course_rcontent_nav5 cnavsx fl_left" style="width:150px">
						<a href="selectResourcesOne?knowledgeId=${c.knowledgeId}" style="color:red;">下载/播放</a></div> --%>
					
				</c:forEach>
		
			
			
			</table>
					  <div id="kkpager"></div>
				</div>
			</div>
			</div>
		</div>
	</div>
	<!-- 底部 版权信息 -->
	<%@ include file="../footer.jsp"%>
</body>
<script type="text/javascript">
         //生成分页控件  
         kkpager.generPageHtml({
            pno : '${pageBean.currentPage}',
            mode : 'link', //可选，默认就是link
            //总页码  
            total : '${pageBean.totalPage}',  
            //总数据条数  
            totalRecords : '${pageBean.allRow}',  
            //链接前部  
            hrefFormer : '${pageBean.url}&page=',
            //链接尾部  
            hrefLatter : '1',
            //链接算法
            getLink : function(n){
                if(n == 1){
                    return this.hrefFormer + this.hrefLatter;
                }
                return this.hrefFormer + n;
            }

        });
 
</script>
</html>
