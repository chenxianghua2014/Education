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
function select_all() { //全选     
		if (cfg == false) {
			var inputs = document.getElementsByTagName("input");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].getAttribute("type") == "checkbox") {
					inputs[i].checked = true;
				}
			}
			cfg = true;
		} else {
			var inputs = document.getElementsByTagName("input");
			for ( var i = 0; i < inputs.length; i++) {
				if (inputs[i].getAttribute("type") == "checkbox") {
					inputs[i].checked = false;
				}
			}
			cfg = false;
		}
	}
function fanhui() {
	/* alert(classresourceId)
	alert(cj) */
	window.history.go( -1 );
}

function download(){
	if ($(":checkbox[name=checkboxids]:checked").size() == 0) {
			alert("请至少选择一份作业进行下载！");
			return;
	}
	var value="";
    for (var i=0;i<$(":checkbox[name=checkboxids]").length;i++ ){
    if($(":checkbox[name=checkboxids]")[i].checked){ //判断复选框是否选中
     		value=value+","+$(":checkbox[name=checkboxids]")[i].value; //值的拼凑 .. 具体处理看你的需要,
  		}
    }
	var url = "exportqrcode?values="+value;
    window.location.href=url;
}
</script>
</head>
<body>
	<div class="title">当前位置:作业管理>作业</div>
	<div class="editBlock">
		<form action="journalism" method="get" name="userForm" id="userForm">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">作业目录</td>
				</tr>
				 <tr>
				 	<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="返回"   onclick="fanhui()"/></td>
					<td style="text-align: right;">
					<input name="button" type="button"
						class="inputButton" value="下载"   onclick="download()"/></td>
					
					</td> 
				</tr>
			</tbody>
		</table>
	    </form>
	</div>
	<div class="dataGrid">
		<table>
			<caption>作业查询结果</caption>
			<thead>
				<tr>
				<th style="width:30px"><input type="checkbox"
								onclick="select_all();" value="0" /></th>
				     <th>序     号</th> 
				    <th>学员</th>
					<th>作业下载</th>
					
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<td><input type="checkbox" id="checkboxids"
									name="checkboxids" value="${maps.homeworkPath}" /></td>
					<td>${status.count}</td> 
					<td>${maps.studentName }</td>
					<td>
					<a href="${maps.homeworkPath }" >下载</a>
					</td>
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
