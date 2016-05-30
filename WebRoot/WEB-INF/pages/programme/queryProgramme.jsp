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
		window.location = "programmeskipID?id=0&programmeSort="+sjid+"&programmeRank="+cj;
	}
	
		function collectionNoticeAll(cj,programmeSort) {
		window.location = "collectionProgrammeAll?cj="+cj+"&page=1&programmeSort="+programmeSort;
	}
	
	function stickNotice(sjid) {
		window.location = "stickProgramme?id="+sjid+"&mark=1";
	}
	
	
 	 function deleteProgramme(id) {
	if (confirm("你确定要删除该条记录吗?")) {
	$.ajax({
			url : "deleteProgramme",
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
function editNew(newsId,programmeSort) {
	window.location = "programmeskipID?id=" + newsId+"&mark=1&programmeSort="+programmeSort;
}
function newsOnes(newsId) {
	window.location = "queryProgramme?id=" + newsId;
}
function release(id,type1){
      $.ajax({          
            url:"publishProgramme",  
            type : "post",
            data:{programmeId : id,programmeType:type1},  
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
            url:"collectionProgramme",  
            type : "post",
            data:{programmeId : id},  
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
	<div class="title">当前位置:<c:if test="${programme.programmeSort==2}">党校</c:if>信息管理><c:if test="${programme.programmeSort==2}">党校</c:if>计划管理</div>
	<div class="editBlock">
		
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle"><c:if test="${programme.programmeSort==2}">党校</c:if>计划管理</td>
				</tr>
				<tr>
					<td style="text-align: left">
					<input name="button" type="button"
						class="inputButton" value="添加"  onclick="Addnew('${programme.programmeSort}','${cj}')"/>
						<input name="button" type="button"
						class="inputButton" value="切换收藏"  onclick="collectionNoticeAll('${cj}','${programme.programmeSort}')"/>
						</td>
						
					<td style="text-align: right;">
					<form action="programmeAll?programmeSort=${programme.programmeSort}&mark=1&page=1&cj=${cj}" method="post"  >
					发布级别:<select
						id="programmeRank" name="programmeRank">
							<option value=0 >全部</option>
							<option value=1 ${programme.programmeRank==1?"selected":""}>一级</option>
							<option value=2 ${programme.programmeRank==2?"selected":""}>二级</option>
							<option value=3 ${programme.programmeRank==3?"selected":""}>三级</option>
					</select>
					<input type="hidden" id="organizationId" name="organizationId" value="${programme.organizationId}" />
					&nbsp;
					            发布单位:<input type="text" name="programmeCatalog" class="inputText" id="programmeCatalog" value="${programme.programmeCatalog}" /> &nbsp;
						发布时间:<input type="text" name="programmeTime" class="inputText" id="programmeTime"  value="${programme.programmeTime}" onclick='WdatePicker()'/> &nbsp;
						<input  type="submit"  class="inputButton" value="查询" />
						</form>
						</td>
						
				</tr>
			</tbody>
		</table>
	    
	</div>
	<div class="dataGrid">
		<table>
			<caption><c:if test="${programme.programmeSort==2}">党校</c:if>计划查询结果</caption>
			<thead>
				<tr>
				    <!-- <th>序      号</th> -->
				    <th>计划编号</th>
					<th>计划名称</th>
					<th>计划所属目录</th>
					<th>制作人</th>
					<th>计划状态</th>
					<th>置顶状态</th>
					<th>发布时间</th>
					<th width="60" class="alignCenter">置顶</th>
					<th width="60" class="alignCenter">收藏</th>
					<th width="60" class="alignCenter">发布</th>
					<th width="60" class="alignCenter">修改</th>
					 <th width="60" class="alignCenter">删除</th>  
				</tr>
			</thead>
			<tbody id="">
			    <c:forEach items="${pageBean.list}" var="maps" varStatus="status">
				<tr>
				<%-- 	<td>${status.count}</td> --%>
					<td>${maps.programmeId }</td>
					<td><a href="#" onclick="newsOnes('${maps.programmeId}')">${maps.programmeName }</a></td>
					<td>${maps.organization.organizationDwmc }</td>
					<td>${maps.programmeProducer }</td>
					<td>${maps.programmeType }</td>
					<td>
					<c:if test="${maps.programmeTop==1 }">是</c:if>
					<c:if test="${maps.programmeTop==0 }">否</c:if>
					</td>
					<td>${maps.programmeTime }</td>
					
					<c:choose>
					<c:when test="${sessionScope.sessionUser.accountType==1 ||  maps.organizationId==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input name='button'type='button' onclick="stickNotice('${maps.programmeId}')" class='inputButton' value='置顶' /></td>
					</c:when>
					<c:otherwise>
					<td class='alignCenter'><input name='button' type='button'  style="color:#AFB8C0"  class='inputButton' value='置顶' /></td>
					</c:otherwise>
					</c:choose>

					
					<c:if test="${maps.organizationId==sessionScope.sessionUser.accountCatalog}">
					<td class='alignCenter'><input name='button'type='button' onclick="collection('${maps.programmeId}')" class='inputButton' value='收藏' /></td>
					<td class='alignCenter'>
					<c:if test="${maps.programmeType=='不可用' }">
					<input name='button'type='button' onclick="release('${maps.programmeId}','可用')" class='inputButton' value='发布' />
					</c:if>
					<c:if test="${maps.programmeType=='可用' }">
					<input name='button'type='button' onclick="release('${maps.programmeId}','不可用')" class='inputButton' value='取消' />
					</c:if>
					</td>
					
					<td class='alignCenter'><input name='button'type='button' onclick="editNew('${maps.programmeId}','${maps.programmeSort}')" class='inputButton' value='编辑' /></td>
					 <td class='alignCenter'><input name='button'type='button' onclick="deleteProgramme('${maps.programmeId}')" class='inputButton' value='删除' /></td>
					 </c:if> 
					 <c:if test="${maps.organizationId!=sessionScope.sessionUser.accountCatalog}">
					 	<td class='alignCenter'><input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='收藏' /></td>
					<td class='alignCenter'>
					<c:if test="${maps.programmeType=='不可用' }">
					<input name='button'type='button' style="color:#AFB8C0" class='inputButton' value='发布' />
					</c:if>
					<c:if test="${maps.programmeType=='可用' }">
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
