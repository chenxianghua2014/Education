<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link> -->
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>
<!-- <script type="text/javascript" src="js/common.js"></script> -->

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>
<!-- 分页 -->
<script type="text/javascript" src="<%=path%>/css/pager/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="<%=path%>/css/pager/kkpager.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/pager/kkpager_blue.css" />
<script type="text/javascript">
	function Addnew(knowledgeResourceId) {
		window.location = "ResourceADDto?knowledgeResourceId="+knowledgeResourceId;
	}

	function collectionNoticeAll(cj) {
		window.location = "collectionNoticeAll?cj="+cj+"&page=1";
	}
	
	function stickNotice(sjid) {
		window.location = "stickNotice?id="+sjid;
	}
	
 	 function deleteProgramme(id) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteNotice",
			type : 'GET',
			data : {
				id: id
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
function editNew(newsId) {
	window.location = "skipID?id=" + newsId+"&mark=1";
}
function newsOnes(newsId) {
	window.location = "queryNotice?id=" + newsId;
}
</script>
</head>
<body>
	<div class="title">当前位置:资源管理>资源浏览</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">资源管理</td>
				</tr>
				<tr>
				<c:if test="${ksum!=0}">
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加"  onclick="Addnew('${knowledgeResourceId}')"/>
					</td>
						</c:if>
					<td style="text-align: right;">
						<form action="Resourcelist?page=1&mark=1" method="post"  >
							<input type="hidden" id="knowledgeResourceId" name="knowledgeResourceId" value="${pageId}" />
						            资源名称:<input type="text" name="knowledgeName" class="inputText" id="knowledgeName" value="${KnowledgeG.knowledgeName }" /> &nbsp;
							<input  type="submit"  class="inputButton" value="查询" />
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>资源查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th width="25%">资源名称</th>
					<th width="10%">资源制作人</th>
					<th width="40%">资源文档位置</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${knowledgelist}" var="maps" varStatus="status">
				<tr>
					<td>${maps.knowledgeName }</td>
					<td>${maps.knowledgeProducer }</td>
					<td>${maps.knowledgePath }</td>
					<td><input name="button" type="button" onclick="window.location.href='UpdataResourcesTo?knowledgeId=${maps.knowledgeId }';" class="inputButton" value="修改"/>
						<input name="button" type="button" onclick="window.location.href='DeleteResources?knowledgeId=${maps.knowledgeId }';" class="inputButton" value="删除"/>
					</td>
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
 
</script>

</html>
