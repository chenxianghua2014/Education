<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->

<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
function fanhui(classresourceId,cj,classlb) {
	/* alert(classresourceId)
	alert(cj) */
	window.location = "queryClass?classresourceId=" + classresourceId +"&mark=1&page=1&cj="+cj+"&classlb="+classlb;
}
function homeworkOne(courseId) {
	/* alert(classresourceId)
	alert(cj) */
	window.location = "queryHomeworkBycourseId?courseId=" + courseId +"&mark=1&page=1";
}

</script>
</head>
<body>
	<div class="title">当前位置:培训班管理>课程</div>
	<div class="editBlock">
		<form action="journalism" method="get" name="userForm" id="userForm">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">添加课程</td>
				</tr>
				 <tr>
				 	<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="返回"   onclick="fanhui('${tranc.classresourceId}','${classinfos.cj}','${classinfos.classlb}')"/></td>
					<td style="text-align: right;">
					</td> 
				</tr>
			</tbody>
		</table>
	    </form>
	</div>
	<div class="dataGrid">
		<table>
			<caption>课程查询结果</caption>
			<thead>
				<tr>
				     <th>序     号</th> 
				    <th>课程名称</th>
					<th>作业</th>
					
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
					<td>${status.count}</td> 
					<td>${maps.classinfoName }</td>
					<td><a href="#" onclick="homeworkOne('${maps.courseId }')">查看</a></td>
				</tr>
				</c:forEach> 
			</tbody>
		</table>
		<div id="kkpager"></div><br/>
	</div>
</body>
<script type="text/javascript">

var cfg = /\?currentPage=\d{0,5}/;
var cfg2 = /\&currentPage=\d{0,5}/;
var href = window.location.href;

 if(cfg.test(href)){
	 href = href.replace(cfg,'?')+'&';
	}else if(cfg2.test(href)){
	href = href.replace(cfg2,'')+'&';
	
}else{
	if(href.indexOf('?') > -1){
		href = href + '&';
	}else{
		href = href + '?';
	}
} 

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
