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
	function Addnew(sjid,rank) {
		window.location = "addnew?sjid="+sjid+"&rank="+rank;
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
	window.location = "editNew?newsId=" + newsId;
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
	<div class="title">当前位置:培训班管理>培训班</div>
	<div class="editBlock">
		<form action="journalism" method="get" name="userForm" id="userForm">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">新闻管理</td>
				</tr>
				<%-- <tr>
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加"  onclick="Addnew('${sjid}','${informationPage.newsRanks}')"/></td>
					<td style="text-align: right;">
					<input type="hidden" id="pageNewsId" name="pageNewsId" value="${sjid}" />
					<input type="hidden" id="newsRanks" name="newsRanks" value="${informationPage.newsRanks}" />
					&nbsp;
					            发布单位:<input type="text" name="newsCatalog" class="inputText" id="newsCatalog" value="${informationPage.newsCatalog}"/> &nbsp;
						发布级别:
						<select id="newsRank" name="newsRank" >
							<option value="" >请选择</option>
							<option value="1" ${informationPage.newsRank=="1"?"selected":""}>一级</option>
							<option value="2" ${informationPage.newsRank=="2"?"selected":""}>二级</option>
							<option value="3" ${informationPage.newsRank=="3"?"selected":""}>三级</option>
					</select>
						 &nbsp;
						发布时间:<input type="text" class="inputText" onclick="WdatePicker({ maxDate:'%y-%M-%d' })"  readonly="readonly"  id="newsTime"  name="newsTime" value="${informationPage.newsTime}"/>&nbsp;
						<input name="button" type="submit"  class="inputButton" value="查询" />
						
					</td>
				</tr> --%>
			</tbody>
		</table>
	    </form>
	</div>
	<div class="dataGrid">
		<table>
			<caption>培训班</caption>
			<thead>
				<tr>
				     <th>序      号</th> 
				    <th>班名称</th>
					<th>人数</th>
					<th>班主任</th>
					<th>开设时间</th>
					<th>结束时间</th>
					<th>面授信息</th>
					<!-- <th width="60" class="alignCenter">预览</th>
					<th width="60" class="alignCenter">发布</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  --> 
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${trainList}" var="maps" varStatus="status">
				<tr>
					<td>${status.count}</td> 
					<td>${maps.tranclassName }</td>
					<td>${maps.tranclassNumber }</td>
					<td>${maps.tranclassTeacher }</td>
					<td>${maps.tranclassSetuptime }</td>
					<td>${maps.tranclassEndtime }</td>
					<td>${maps.tranclassFace }</td>
				<%-- 	<td>${maps.newsRank}</td>
					<td>${informationPage.newsRanks }</td> --%>
					<%-- <td class='alignCenter'><input name='button'type='button' onclick="newsOnes('${maps.newsId}')" class='inputButton' value='预览' /></td>
					
				<c:choose>
					    <c:when test="${maps.newsRank == informationPage.newsRanks }">
									<td class='alignCenter'>
										<c:choose>
											<c:when test="${maps.newsType =='不可用'}">
										       <input name='button'type='button' onclick="release('${maps.newsId}')" class='inputButton' value='发布' />
										    </c:when>
										       <c:otherwise>
										          <input name='button'type='button' onclick="cancel('${maps.newsId}')" class='inputButton' value='取消' />     
					                            </c:otherwise>
										</c:choose>
									</td>
									<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.newsId}')" class='inputButton' value='编辑' /></td>
									 <td class='alignCenter'><input name='button'type='button' onclick="deleteNew('${maps.newsId}')" class='inputButton' value='删除' /></td> 
					   </c:when>
						   <c:otherwise>
						   <td class='alignCenter'>
										<c:choose>
											<c:when test="${maps.newsType =='不可用'}">
										       <input style="color:#AFB8C0" name='button'type='button' onclick="" class='inputButtonDisable' value='发布' />
										    </c:when>
										       <c:otherwise>
										          <input style="color:#AFB8C0" name='button'type='button' onclick="" class='inputButtonDisable' value='取消' />     
					                            </c:otherwise>
										</c:choose>
									</td>
									<td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button' onclick="" class='inputButtonDisable' value='编辑' /></td>
									 <td class='alignCenter'><input style="color:#AFB8C0" name='button'type='button' onclick="" class='inputButtonDisable' value='删除' /></td> 
						   </c:otherwise>
				</c:choose>--%>
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
            pno : '${informationPage.currentPage}',
            mode : 'link', //可选，默认就是link
            //总页码  
            total : '${informationPage.totalPage}',  
            //总数据条数  
            totalRecords : '${informationPage.rows}',  
            //链接前部  
            hrefFormer : 'journalism?currentPage=',
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
