<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="css/skins/default/main.css" rel="stylesheet" type="text/css" />


<script type="text/javascript" src="css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/pager/kkpager_blue.css" />

<!-- 下拉日期 -->
<script type="text/javascript" charset="utf-8" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	
function deleteReply(replyId) {
	if (confirm("你确定要删除该条记录吗?")) {	
	$.ajax({
			url : "deleteReply",
			type : 'GET',
			data : {
				replyId : replyId
			},
			success : function(data){
				if(data=="ok"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert("删除失败");
					window.location.reload();
				}
			}
		  });
}  
 	 }
	 
function queryReplyByReplyId(replyId) {
	window.location = "queryReplyByReplyId?replyId=" + replyId;
}
function queryReplyByReplyPid(replyPid) {
	window.location = "queryReplyByReplyPid?page=1&replyPid=" + replyPid;
}

</script>
</head>
<body>
	<div class="title">当前位置:学习圈子>回复管理</div>
	<div class="dataGrid">
		<table>
			<caption>回复列表</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <%--<th width="300">ID</th>
					--%><th>回帖内容</th>
					<th>回帖时间</th>
					<th width="60" class="alignCenter">删除</th>  
					 
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<%-- 	<td>${status.count}</td> --%>
					<%--<td>
					<a href="#" onclick="queryReplyByReplyId('${maps.replyId}')">${maps.replyId}</a></td>--%>
					<td>
					<a href="#" onclick="queryReplyByReplyId('${maps.replyId}')">${maps.replyContent}</a>
					
					</td>
					<td>
					<fmt:formatDate value="${maps.replyAt }" type="both"
							pattern="yyyy-MM-dd HH:mm:ss" />
					</td>
					 	
					<c:choose>
       					<c:when test="${accountType==1}">
             				<td class='alignCenter'><input name='button'type='button' onclick="deleteReply('${maps.replyId}')" class='inputButton' value='删除' /></td>
						</c:when>
						<c:when test="${accountType==2}">
             				<td class='alignCenter'><input name='button'type='button' onclick="deleteReply('${maps.replyId}')" class='inputButton' value='删除' /></td>
						</c:when>
						<c:when test="${accountType==3}">
             				<td class='alignCenter'><input name='button'type='button' onclick="deleteReply('${maps.replyId}')" class='inputButton' value='删除' /></td>
						</c:when> 
						<c:otherwise>
							<td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0"class='inputButton' value='删除' /></td>
						</c:otherwise>
					</c:choose>
					
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
