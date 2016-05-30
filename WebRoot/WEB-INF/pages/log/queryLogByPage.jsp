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
<!--JBOX  -->
<script type="text/javascript" src="js/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<link rel="stylesheet" type="text/css" href="js/jBox/Skins/Blue/jbox.css"></link>
<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	//导出 
function excel() {
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var logContent = document.getElementById("logContent1").value;
	if (confirm("您确定要导出么?")) {
		$.jBox.tip("正在生成，请耐心等待！", 'loading');
		window.setTimeout(function() {
			window.location = "logExcel?startDate="+startDate+"&endDate="+endDate+"&logContent="+logContent;
			$.jBox.tip('已完成。', 'success');
		}, 2000);

	}
}
</script>
<script type="text/javascript">
function saveBeifen() {
	if (confirm("你确定要备份么?")) {
	$.ajax({
			url : "backup",
			type : 'GET',
			success : function(data){
				if(data=="ok"){
					alert("备份成功");
					window.location.reload();
				}else{
					alert("备份失败");
					window.location.reload();
				}
			}
		  });
}  
 	 }
</script>
</head>
<body>
	<div class="title">当前位置:系统管理>日志管理</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">日志列表筛选</td>
				</tr>
				<tr>
			<!-- 	<td style="text-align: left">
				<input type="button" class="inputButton" value="数据库备份"  onclick="saveBeifen();"/>
				</td> -->
					<td style="text-align: right;">
						<form action="queryLogByPage?page=1" method="post"  >
							 日志内容:<input type="text" name="logContent" class="inputText" id="logContent" value="${log.logContent}" /> &nbsp;
							 记录时间:<input name="logAt" id="logAt" value="${log.logAt}" type="text" class="inputText" onclick='WdatePicker()' />&nbsp;
							 操作人:<input type="text" name="logUser" class="inputText" id="logUser" value="${log.logUser}" /> &nbsp;
					    	<input  type="submit"  class="inputButton" value="查询" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">导出日志列表</td>
				</tr>
				<tr>
					<td style="text-align: right;">
						<form action="" id="logForm" method="post"  >
							日志内容:<input type="text" name="logContent" class="inputText" id="logContent1" value="${log.logContent}" /> &nbsp;
							开始时间:<input name="startDate" id="startDate" value="${log.startDate}" type="text" class="inputText" onclick='WdatePicker()' />&nbsp;
							结束时间:<input name="endDate" id="endDate" value="${log.endDate}" type="text" class="inputText" onclick='WdatePicker()' />&nbsp;
							
					    	<input  type="button" onclick="excel()" class="inputButton" value="导出" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>

	</div>
	<div class="dataGrid">
		<table>
			<caption>日志列表</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th width="100">操作人</th>
					<th>日志内容</th>
					<th width="200">操作时间</th>
					<th width="100">用户IP</th>
					<th width="100">用户类型</th>
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<%-- 	<td>${status.count}</td> --%>
					<td>${maps.logUser}</td>
					<td>${maps.logContent }</td>
					<td>${maps.logAt }</td>
					<td>${maps.logIp }</td>
					<td>${maps.logUserType }</td>
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
