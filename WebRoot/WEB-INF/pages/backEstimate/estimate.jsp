<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />
</head>
<body>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="3" class="subtitle">课程</td>
				</tr>
				<tr>
				
					<td style="text-align: left"></td>
					<td style="text-align: right;">
					<form action="Estimate?syllabusId=${course.syllabusId}&mark=1&page=1" method="post"  >
					<input type="hidden" name="courseCatalog" value="1" />
					课程名称:
					<input type="text" id=coursePubman name="coursePubman" value="${course.coursePubman}" />
					&nbsp;
					<input  type="submit"  class="inputButton" value="查询" />
					</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>课程评分</caption>
			<thead>
				<tr>
				    <th>SN</th>
					<th>课程名称</th>
					<th>总评分</th>
					<th>评分人数</th>
					<th>平均分</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="dataBody">
			 <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${maps.courseName }</td>
					<td>${maps.zs }</td>
					<td>${maps.gs }</td>
					<td>${maps.pjs }</td>
					<td class='alignCenter'><input name='button'type='button' onclick="Courseresource('${maps.courseId}')" class='inputButton' value='评价信息' /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="kkpager"></div>
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
 	function Courseresource(id) {
		window.location = "EstimateStudent?mark=1&page=1&courseId="+id;
	}
</script>
</html>