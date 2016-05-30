<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<title>婚筹</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="婚筹">
<meta http-equiv="description" content="婚筹">
<link type="text/css" rel="stylesheet" href="../css/page.css" />
<link type="text/css" href="../js/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script type="text/javascript" src="../js/jquery/jquery.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="../js/jquery.qqFace.js"></script>
<script src="../js/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="../js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="../js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" charset="utf-8" src="../js/reply.js"></script>
<style type="text/css">
div {
	width: 100%;
}

span.emotion {
	width: 42px;
	height: 20px;
	background: url('../images/icon.gif') no-repeat 2px 2px;
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
	width: 800px;
	height: 60px;
	border: 1px solid #ccc
}
</style>
</head>
<body>
	<div id="replyContent">
		<c:choose>
			<c:when test="${not empty replies1 }">
				<c:forEach items="${replies1 }" var="reply1" varStatus="status">
					<div id="rc-${reply1.replyId }">
						<div>
							<img src="${reply1.accountHead }" width="50px" height="50px" />${reply1.accountName }：${reply1.replyContent }---${reply1.friendlyAddtime }---
							<c:choose>
								<c:when test="${reply1.praiseFlag eq 0}">
									<a href="javascript:void(0);" onclick="praiseReply(this.id,1)"
										id="${reply1.replyId }">赞</a>
								</c:when>
								<c:otherwise>
									<a href="javascript:void(0);" onclick="praiseReply(this.id,-1)"
										id="${reply1.replyId }">取消赞</a>
								</c:otherwise>
							</c:choose>
							(<span id="praise-${reply1.replyId }">${reply1.praise }</span>) <br>
							回复我的主题：<a href="../bbs/bbsdetails?bbsId=${reply1.bbsId }">"${reply1.bbsTitle }"</a><a
								href="javascript:void(0);" class="subreply"
								onclick="subreply(this)" id="${reply1.replyId }"
								pid="${reply1.replyId }" bbsid="${reply1.bbsId }"
								paccountid="${reply1.accountId }">回复</a>
							<div id="reply-${reply1.replyId }" class="reply1"></div>
							<c:forEach items="${replies2 }" var="reply2" varStatus="status">
								<c:choose>
									<c:when test="${reply2.replyPid  eq reply1.replyId }">
										<div id="rc-${reply2.replyId }">
											<div>
												<img src="${reply2.accountHead }" width="50px" height="50px" />二级回复:(${reply2.accountName }
												回复 ${reply2.accountPname })---回复内容(${reply2.replyContent })---${reply2.friendlyAddtime }---
												<c:choose>
													<c:when test="${reply2.praiseFlag eq 0}">
														<a href="javascript:void(0);"
															onclick="praiseReply(this.id,1)" id="${reply2.replyId }">赞</a>
													</c:when>
													<c:otherwise>
														<a href="javascript:void(0);"
															onclick="praiseReply(this.id,-1)" id="${reply2.replyId }">取消赞</a>
													</c:otherwise>
												</c:choose>
												(<span id="praise-${reply2.replyId }">${reply2.praise }</span>)
												<a href="javascript:void(0);" class="subreply"
													onclick="subreply(this)" id="${reply2.replyId }"
													pid="${reply2.replyId }" bbsid="${reply2.bbsId }"
													paccountid="${reply2.accountId }">回复</a>
												<div id="reply-${reply2.replyId }" class="reply2"></div>
											</div>
											<c:forEach items="${replies3 }" var="reply3"
												varStatus="status">
												<c:choose>
													<c:when test="${reply3.replyPid  eq reply2.replyId }">
														<div id="rc-${reply3.replyId }">
															<div>
																<img src="${reply3.accountHead }" width="50px"
																	height="50px" />二级回复:(${reply3.accountName } 回复
																${reply3.accountPname })---回复内容(${reply3.replyContent })---${reply3.friendlyAddtime }---
																<c:choose>
																	<c:when test="${reply3.praiseFlag eq 0}">
																		<a href="javascript:void(0);"
																			onclick="praiseReply(this.id,1)"
																			id="${reply3.replyId }">赞</a>
																	</c:when>
																	<c:otherwise>
																		<a href="javascript:void(0);"
																			onclick="praiseReply(this.id,-1)"
																			id="${reply3.replyId }">取消赞</a>
																	</c:otherwise>
																</c:choose>
																(<span id="praise-${reply3.replyId }">${reply3.praise }</span>)
																<a href="javascript:void(0);" class="subreply"
																	onclick="subreply(this)" id="${reply3.replyId }"
																	pid="${reply3.replyPid }" bbsid="${reply3.bbsId }"
																	paccountid="${reply3.accountId }">回复</a>
																<div id="reply-${reply3.replyId }" class="reply3"></div>
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
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>说点什么吧...
		</c:otherwise>
		</c:choose>
	</div>
	<br />
	<input type="text" id="bbsId" />
	<input type="text" id="replyPid" />
	<input type="text" id="replyPaccountid" />
	<div class="page_and_btn">${page.pageStr }</div>
</body>
</html>