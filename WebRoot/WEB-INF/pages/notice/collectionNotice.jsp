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
	function Addnew(sjid,cj) {
		window.location = "skipID?id=0&pageNewsId="+sjid+"&noticeRank="+cj;
	}

	function collectionNoticeAll(cj) {
		window.location = "collectionNoticeAll?cj="+cj+"&page=1";
	}
	
	function stickNotice(sjid) {
		window.location = "stickNotice?id="+sjid+"&mark=0";
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
function editNew(newsId,noticeSort) {
	window.location = "skipID?id=" + newsId+"&mark=0&noticeSort="+noticeSort;
}
function newsOnes(newsId) {
	window.location = "queryNotice?id=" + newsId;
}
function release(id,type1){
      $.ajax({          
            url:"publishNotice",  
            type : "post",
            data:{noticeId : id,noticeType:type1},  
            error:function(){  
               alert("error occured!!!");  
            },  
            success:function(data){
            if(data!="")
            {
            if(type1=="可用"){
            	alert('发布成功');
            }else{
            	alert('取消发布成功');
            }
              
              window.location.reload();
            }
            else
            {
               alert('发布失败');
               history.back(-1);
            }
      
          }  
      }); 
 } 
function collection(id){
      $.ajax({          
            url:"collectionDelNotice",  
            type : "post",
            data:{noticeId : id},  
            error:function(){  
               alert("error occured!!!");  
            },  
            success:function(data){
            if(data!="")
            {
            
            	alert('取消收藏成功');
            
              
              window.location.reload();
            }
            else
            {
               alert('取消失败');
                window.location.reload();
            }
      
          }  
      }); 
 } 
function loadData() {
	$.ajax({
		type : 'POST',
		url : "newsDataCount",
		data : {
			/* userName : $("#userName").val(), */
			newsCatalog : $("#newsCatalog").val(),
			newsRank : $("#newsRank").val(),
			zhuanNewsTime : $("#zhuanNewsTime").val()
		},
		dataType : "json",
		success : function(count) {
			dataCount = count;
			var optInit = getOptionsFromForm();
			$("#Pagination").pagination(dataCount, optInit);
			$("#setoptions").click(function() {
				var opt = getOptionsFromForm();
				$("#Pagination").pagination(dataCount, opt);
			});
		}
	});
}



/*  	function saveWeddingphoto() {
		document.ff.action = "saveWeddingphoto";
		document.ff.method = "post";
		document.ff.submit();
	}  */
</script>
</head>
<body>
	<div class="title">当前位置:<c:if test="${notice.noticeSort==2}">党校</c:if>信息管理><c:if test="${notice.noticeSort==2}">党校</c:if>通知收藏</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle"><c:if test="${notice.noticeSort==2}">党校</c:if>通知收藏</td>
				</tr>
			</tbody>
		</table>
	    
	</div>
	<div class="dataGrid">
		<table>
			<caption><c:if test="${notice.noticeSort==2}">党校</c:if>通知收藏查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th>通知编号</th>
					<th>通知名称</th>
					<th>通知所属目录</th>
					<th>制作人</th>
					<th>通知状态</th>
					<th>置顶状态</th>
					<th>发布时间</th>
					<th width="60" class="alignCenter">置顶</th>
					<th width="80" class="alignCenter">收藏</th>
					<th width="80" class="alignCenter">发布</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<%-- 	<td>${status.count}</td> --%>
					<td>${maps.noticeId }</td>
					<td><a href="#" onclick="newsOnes('${maps.noticeId}')">${maps.noticeName }</a></td>
					<td>${maps.organization.organizationDwmc }</td>
					<td>${maps.noticeProducer }</td>
					<td>${maps.noticeType }</td>
					<td>
					<c:if test="${maps.noticeTop==1 }">是</c:if>
					<c:if test="${maps.noticeTop==0 }">否</c:if>
					</td>
					<td>${maps.noticeTime }</td>
					<c:choose>
					<c:when test="${sessionScope.sessionUser.accountType==1 ||  maps.organizationId==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input name='button'type='button' onclick="stickNotice('${maps.noticeId}')" class='inputButton' value='置顶' /></td>
					</c:when>
					<c:otherwise>
					<td class='alignCenter'><input name='button' type='button'  style="color:#AFB8C0"  class='inputButton' value='置顶' /></td>
					</c:otherwise>
					</c:choose>

				
					
					<c:if test="${maps.organizationId==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input name='button'type='button' onclick="collection('${maps.noticeId}')" class='inputButton' value='取消' /></td>
					<td class='alignCenter'>
					<c:if test="${maps.noticeType=='不可用' }">
					<input name='button'type='button' onclick="release('${maps.noticeId}','可用')" class='inputButton' value='发布' />
					</c:if>
					<c:if test="${maps.noticeType=='可用' }">
					<input name='button'type='button' onclick="release('${maps.noticeId}','不可用')" class='inputButton' value='取消' />
					</c:if>
					</td>
					
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.noticeId}','${maps.noticeSort}')" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteProgramme('${maps.noticeId}')" class='inputButton' value='删除' /></td>
					 </c:if> 
					 <c:if test="${maps.organizationId!=sessionScope.sessionUser.accountCatalog}">
					 	<td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='取消' /></td>
					<td class='alignCenter'>
					<c:if test="${maps.noticeType=='不可用' }">
					<input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='发布' />
					</c:if>
					<c:if test="${maps.noticeType=='可用' }">
					<input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='取消' />
					</c:if>
					</td>
					
					<td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0"class='inputButton' value='删除' /></td>
					 </c:if> 
					 
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
