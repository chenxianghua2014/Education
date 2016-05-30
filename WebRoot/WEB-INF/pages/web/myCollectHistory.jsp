<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>我的课程</title>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bcss/other.css">
<script src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />
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
        	<div class="my_courses_left" >
            	<%@ include file="../LeftMyStudent.jsp"%>
 
        	</div>
            <div class="my_courses_right">
            <div class="training_title"><a href="mystudent">我的收藏</a> >课程</div>  
            <div class="training_title_right">我的收藏</div>
            <div class="course_box">
            <table  width="100%" border="1" cellspacing="0" cellpadding="0" bgcolor="#d5dade" text-align="center" bordercolor="#4ea2d9">
       <tr>
							<td width="10%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03" text-align="center">序号</td>
							<td width="15%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">课程名称</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">课时</td>
							<td width="12%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">班主任</td>
							<td width="22%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">发布单位</td>
							<td width="11%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">课程类型</td>
							<td width="12%" height="35" align="center" bgcolor="#73c2f7"
								class="font_white03">课程详细</td>
							<td width="15%" height="35" align="center" bgcolor="#73c2f7"
							    class="font_white03">删除</td>
						</tr>
                            <c:forEach items="${pageBean.list}" var="c" varStatus="cour">
							<tr>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${cour.index
									+ 1}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02"><a
									href="courseInfo?tpk=1&id=${c.courseId}&coursestudyId=${c.coursestudy.coursestudyId}">${c.courseName}</a></td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${c.courseTimes}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${c.teacher.teacherName}</td>
								<td height="28" bgcolor="#FFFFFF" class="font_black02">${c.organization.organizationDwmc
									}</td>
								<td bgcolor="#FFFFFF" class="font_black02">${c.courseType}</td>
								<td bgcolor="#FFFFFF" class="font_black02"><a
									href="courseInfo?tpk=1&id=${c.courseId}&coursestudyId=${c.coursestudy.coursestudyId}"
									style="color:red;">详情</a></td>
									<td bgcolor="#FFFFFF" class="font_black02"><a
									href="#" onclick="deleteCollect('${c.courseId}')" style="color:red;">删除</a></td>
							</tr>
						</c:forEach>
        </table>
         <div id="kkpager"></div>
        </div>
           
            </div>
        </div>	
    </div>
</div> 
   

<div class="clear"></div>
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
 
         
         function deleteCollect(id) {
        		if (confirm("你确定要取消该收藏吗?")) {
        		$.ajax({
        				url : "deleteCollect",
        				type : 'GET',
        				data : {
        					id : id
        				},
        				success : function(data){
        					if(data=="ok"){
        						alert("取消成功");
        						window.location.reload();
        					}else if(data=="1"){
        						alert("请登录账号");
        						window.location.reload();
        					}else{
        						alert("操作失败");
        						window.location.reload();
        					}
        				}
        			  });
        	}  
        	 	 }
</script>
</html>
