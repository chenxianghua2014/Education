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
	function Addnew(sjid) {
		window.location = "schoolnewsSkipID?id=0&newsType="+sjid;
	}
	
 	 function deleteNew(newsId) {
	if (confirm("你确定要删除该条记录吗?")) {	
	$.ajax({
			url : "deleteNew",
			type : 'GET',
			data : {
				newsId : newsId
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
	window.location = "schoolnewsSkipID?id=" + newsId+"&mark=1&newsType=4";
}
function newsOnes(newsId) {
	window.location = "newsOnes?newsId=" + newsId;
}
function release(newsId){
      $.ajax({          
            url:"publishNews",  
            type : "post",
            data:{newsId : newsId},  
            error:function(){  
               alert("error occured!!!");  
            },  
            success:function(data){
            if(data!="")
            {
              alert('发布成功');
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
            url:"collectionNews",  
            type : "post",
            data:{newsId : id},  
            error:function(){  
               alert("error occured!!!");  
            },  
            success:function(data){
            if(data!="")
            {
            
            	alert('收藏成功');
            
              
              window.location.reload();
            }
            else
            {
               alert('收藏失败，已收藏');
                window.location.reload();
            }
      
          }  
      }); 
 } 

function cancel(newsId){
    $.ajax({          
          url:"cancelPublishNews",  
          type : "post",
          data:{newsId : newsId},  
          error:function(){  
             alert("error occured!!!");  
          },  
          success:function(data){
          if(data!="")
          {
            alert('取消发布成功');
            window.location.reload();
          }
          else
          {
             alert('取消发布失败');
             history.back(-1);
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
	<div class="title">当前位置:党校管理>党校导航新闻管理</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">党校导航新闻管理</td>
				</tr>
				<tr>
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加"  onclick="Addnew('${news.newsSort}')"/>
						</td>
					<td style="text-align: right;">
					<form action="schooljournalism?newsSort=${news.newsSort}&mark=1&page=1&cj=${cj}" method="post"  >
					<input type="hidden" id="organizationId" name="organizationId" value="${news.organizationId}" />
					&nbsp;
					          
						发布时间:<input type="text" name="newsTime" class="inputText" id="newsTime"  value="${news.newsTime}" onclick='WdatePicker()'/> &nbsp;
						<input  type="submit"  class="inputButton" value="查询" />
						</form>
						</td>
				</tr>
			</tbody>
		</table>

	</div>
	<div class="dataGrid">
		<table>
			<caption>党校导航新闻查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th>新闻序号</th>
					<th>新闻名称</th>
					<th>新闻目录</th>
					<th>制作人</th>
					<th>新闻状态</th>
					<th>发布时间</th>
					<th width="60" class="alignCenter">发布</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<%-- 	<td>${status.count}</td> --%>
					<td>${maps.newsId }</td>
					<td><a href="#" onclick="newsOnes('${maps.newsId}')">${maps.newsName }</a></td>
					<td>${maps.organization.organizationDwmc }</td>
					<td>${maps.newsProducer }</td>
					<td>${maps.newsType }</td>
					<td>${maps.newsTime }</td>


				
					
					<c:if test="${maps.organizationId==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'>
					<c:if test="${maps.newsType=='不可用' }">
					<input name='button'type='button' onclick="release('${maps.newsId}','可用')" class='inputButton' value='发布' />
					</c:if>
					<c:if test="${maps.newsType=='可用' }">
					<input name='button'type='button' onclick="cancel('${maps.newsId}','不可用')" class='inputButton' value='取消' />
					</c:if>
					</td>
					
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.newsId}')" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteNew('${maps.newsId}')" class='inputButton' value='删除' /></td>
					 </c:if> 
					 <c:if test="${maps.organizationId!=sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'>
					<c:if test="${maps.newsType=='不可用' }">
					<input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='发布' />
					</c:if>
					<c:if test="${maps.newsType=='可用' }">
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
