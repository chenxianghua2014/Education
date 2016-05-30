<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->

<!-- <script type="text/javascript" src="js/common.js"></script> -->

<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

<!-- 查询下拉 -->
<link href="css/index.css" rel="stylesheet" type="text/css" />

<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8"
	src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">

	  // 返回
		function fanhui(organizationId, cj) {
			window.location = "studentHistory?mark=1&organizationId="+organizationId+"&page=1&cj="+cj;
		}
	
	function querySee(studentId) {
		window.location = "studentHistoryClass?studentId=" + studentId+"&mark=1&page=1" ;
	}
</script>
</head>
<body>
	<div class="title">当前位置:上传管理>上传记录</div>
	<div class="editBlock">
		<form action="uploadReports?mark=1&page=1" method="post">
			<td style="text-align: right;">
				&nbsp;
				         资源内容名称:<input type="text" name="recordNote" class="inputText" id="recordNote" value="${record.recordNote}"/> &nbsp;
				&nbsp;
		 	 	上传类别:<select
				id="recordType" name="recordType">
					<option value="" >全部</option>
					<option value="5" ${record.recordType==5?"selected":""}>课程视频</option>
					<option value="4" ${record.recordType==4?"selected":""}>资源</option>
					<option value="3" ${record.recordType==3?"selected":""}>共享资源</option>
			</select> 
					 
					<input name="button" type="submit"  class="inputButton" value="查询" /> 
			</td>
		</form>
	</div>
	<div class="dataGrid">
		<div>
				<table>
					<caption>上传记录结果</caption>
					<thead>
						<tr>
							<th style="width:70px">序 号</th>
							<th style="width:70px">日志记录类别</th>
							<th style="width:70px">标题</th>
							<th style="width:70px">内容</th>
						
						</tr>
					</thead>
					<tbody id="">
						<c:forEach items="${pageBean.list}" var="maps" varStatus="status">
							<tr>
							
								<td style="width:70px">${status.count}</td>
								<td style="width:70px">${maps.recordClass }</td>
								<td style="width:70px">${maps.recordTitle }</td>
								<td style="width:70px">${maps.recordNote }</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
		<div id="kkpager"></div>
		<br />
	</div>
</body>
<script type="text/javascript">
	var cfg = /\?currentPage=\d{0,5}/;
	var cfg2 = /\&currentPage=\d{0,5}/;
	var href = window.location.href;

	if (cfg.test(href)) {
		href = href.replace(cfg, '?') + '&';
	} else if (cfg2.test(href)) {
		href = href.replace(cfg2, '') + '&';

	} else {
		if (href.indexOf('?') > -1) {
			href = href + '&';
		} else {
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
		getLink : function(n) {
			if (n == 1) {
				return this.hrefFormer + this.hrefLatter;
			}
			return this.hrefFormer + n;
		}

	});
</script>
</html>
