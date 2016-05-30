<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<head>
<title>中国航天科工在线培训平台</title>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bcss/index_other.css">
<link rel="stylesheet" type="text/css" href="css/bbs.css">
<link rel="stylesheet" type="text/css" href="css/other.css">
<script type="text/javascript" src="js/jquery.superslide.2.1.1.js"></script>
<script type="text/javascript" src="js/zzsc.js"></script>

<%--<link type="text/css" rel="stylesheet" href="css/pageCss/style.css" />
<link type="text/css" href="js/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
--%>
<script type="text/javascript" charset="utf-8"
	src="js/jquery.qqFace.js"></script>
<script src="js/jquery/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" charset="utf-8" src="js/reply.js"></script>
<style type="text/css">
span.emotion {
	width: 42px;
	height: 20px;
	background: url('images/pageImages/icon.gif') no-repeat 2px 2px;
	padding-left: 20px;
	cursor: pointer
}

span.emotion:hover {
	background-position: 2px -28px
}

.qqFace {
	margin-top: 4px;
	background: #fff;
	padding: 2px;
	border: 1px #dfe6f6 solid;
}

.qqFace table td {
	padding: 0px;
}

.qqFace table td img {
	cursor: pointer;
	border: 1px #fff solid;
}

.qqFace table td img:hover {
	border: 1px #0066cc solid;
}

.input {
	width: 446px;
	height: 60px;
	border: 1px solid #ccc
}
</style>
</head>
<body>
<div class="header">
	<%@ include file="../top.jsp"%>
</div>
	<!--bbs content start-->
<div class="main">
	<div class="concainer">
		<div class="training_dynamic">
        	<div class="luntan_title">论坛><span>${bbs.bbsTitle } </span></div>  
        </div>
		<!--bbs left start-->
		<div class="answer">
		<%-- 主贴s --%>
		<div class="answer_list">
			<ul class="ul_list">
            	<li class="ul_list_title">${bbs.bbsTitle }</li>
            	<li class="ul_list_main">
            		${bbs.bbsContent } <br> <br>
           		<time><fmt:formatDate value="${bbs.bbsAt }" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></time>
            	</li>
        	</ul>
            <ul class="luntan_look">
            	<li class="look_01"  onclick="praiseBbs(this.id,1)" >
	            	
				</li>
            	<%-- <li>浏览：<span>${bbs.viewCount }</span></li> --%>
            	<li>回复：<span>${bbs.replyCount }</span></li>
            	
            </ul>
            
		</div>
			<%-- 主贴e --%>
			
			
				<!--回复列表-->
				<ul id="replyContent">
					<c:choose>
						<c:when test="${not empty replies1 }">
							<c:forEach items="${replies1 }" var="reply1" varStatus="status">
								<li id="rc-${reply1.replyId }">
									<div class="answer_bottom">
									
									<ul class="ul_list">
					                	<li class="ul_list_title"><a href="#">${reply1.studentName }</a></li>
					                    <li class="ul_list_main">回复：<a href="#">${reply1.replyContent }</a><time>${reply1.friendlyAddtime }</time></li>
					                </ul>
					                <ul class="luntan_look">
					                    <li class="look_01">
					                    			
					                    </li>
					                    <li class="look_02">
					                    	<%-- <span>
						                    	<a href="javascript:;" class="reply" onclick="subreply(this)" id="${reply1.replyId }"
													pid="${reply1.replyId }" bbsid="${bbs.bbsId }" pstudentid="${reply1.studentId }">
													<em></em>回复</a>
													
											</span> --%>
										</li>
										
										
					                </ul>
					                <div id="reply-${reply1.replyId }" class="reply1"></div>

										<c:forEach items="${replies2 }" var="reply2"
											varStatus="status">
											<c:choose>
												<c:when test="${reply2.replyPid eq reply1.replyId }">
													<div id="rc-${reply2.replyId }">
														<div class="answer_answer">
															<ul class="ul_list">
										                        <li class="ul_list_title"><a href="#">${reply2.studentName } 回复 ${reply2.studentPname }</a></li>
										                        <li class="ul_list_main">回复：<a href="#">${reply2.replyContent }</a><time>${reply2.friendlyAddtime }</time></li>
										                    </ul>
															<ul class="luntan_look">
									                        <li class="look_01">
																
															</li>
									                        <li class="look_02">
									                        	<%-- <span>
										                        	<a href="javascript:;" class="reply" onclick="subreply(this)" id="${reply2.replyId }"
																		pid="${reply2.replyId }" bbsid="${bbs.bbsId }" pstudentid="${reply2.studentId }"><em></em>回复</a>
										                        </span> --%>
										                        
									                        </li>
									                    </ul>
														<div id="reply-${reply2.replyId }" class="reply1"></div>	
														</div>
														
														
														<c:forEach items="${replies3 }" var="reply3"
															varStatus="status">
															<c:choose>
																<c:when test="${reply3.replyPid  eq reply2.replyId }">
																	<div id="rc-${reply3.replyId }">
																		<div class="answer_answer">
																			<ul class="ul_list">
														                        <li class="ul_list_title"><a href="#">${reply3.studentName } 回复 ${reply3.studentPname }</a></li>
														                        <li class="ul_list_main">回复：<a href="#">${reply3.replyContent }</a><time>${reply3.friendlyAddtime }</time></li>
														                    </ul>
														                    <ul class="luntan_look">
														                        <li class="look_01">
														                        	
														                        </li>
														                        <li class="look_02">
															                       <%--  <span>
															                        	<a href="javascript:;" class="reply"
																						onclick="subreply(this)" id="${reply3.replyId }"
																						pid="${reply3.replyPid }" bbsid="${bbs.bbsId }"
																						pstudentid="${reply3.studentId }"><em></em>回复</a>
															                        </span> --%>
															                        
														                        </li>
														                    </ul>	
														                    <div id="reply-${reply3.replyId }" class="reply1"></div>
																		</div>
																		
																	</div>
																</c:when>
															</c:choose>
														</c:forEach>
														
														
													</div>
												</c:when>
											</c:choose>
										</c:forEach>
									</div>
								</li>
							</c:forEach>
						</c:when>
					</c:choose>
					</ul>
					<!--回复结束-->
				
				<div class="answer_border">
					<textarea  class="answer_bar"  id="saytext" name="saytext"></textarea>
					<div class="btn_replay">
						<!-- <div class="replay_pic">
							<a href="javascript:;" class="replay_biaoqing">表情</a>
						</div> -->
						<c:choose>
							<c:when test="${Student eq null}">
								 <input type="button" onclick="showLogin()" value="回复">
							</c:when>
							<c:otherwise>
								<input type="button" onclick="doreply()" value="回复">
							</c:otherwise>
						</c:choose>
						<input type="hidden" id="bbsId" value="${bbs.bbsId }" /> <input
							type="hidden" id="replyPid" /> <input type="hidden"
							id="replyPstudentid" />
					</div>
				</div>
			
		</div>
	</div>
	</div>
	<!--bbs left end-->
</body>
<script type="text/javascript">
	$(function() {
		$('.replay_biaoqing').qqFace({
			id : 'facebox',
			assign : 'saytext',
			emotions : em
		// 			表情信息json格式，id表情排序号 phrase表情使用的替代短语url表情文件名
		});
		$(".praiseReply").click(function() {
			praiseReply(this.id, -1);
		});
	});
	function showLogin() {
		$.jBox.open("iframe:../loginFrm", "用户登录", 260, 200, {
			buttons : {}
		});
	}
</script>
</html>

