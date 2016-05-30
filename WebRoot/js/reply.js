var currentReplyid; //当前ueditor所在的ID
//赞-BBS
function praiseBbs(_id, type){
	$.ajax({
		type : 'POST',
		url : "praise",
		data : {
			bbsId: _id,
			statisticalType: type
		},
		dataType : "json",
		success : function(praiseReply) {
			if(type == 1){
				$("#praise-"+_id).html(parseInt($("#praise-"+_id).html())+1);
				$("#"+_id).html("取消赞");
				$("#"+_id).attr("onclick", "praiseBbs('"+_id+"',-1)");
			}
			else {
				$("#praise-"+_id).html(parseInt($("#praise-"+_id).html())-1);
				$("#"+_id).html("赞");
				$("#"+_id).attr("onclick", "praiseBbs('"+_id+"',1)");
			}
			window.location.reload();
		}
	});
}
//赞-回复
function praiseReply(_id, type){
	$.ajax({
		type : 'POST',
		url : "praise",
		data : {
			replyId: _id,
			statisticalType: type
		},
		dataType : "json",
		success : function(praiseReply) {
			if(type == 1){
				$("#praise-"+_id).html(parseInt($("#praise-"+_id).html())+1);
				$("#"+_id).html("取消赞");
				$("#"+_id).attr("onclick", "praiseReply('"+_id+"',-1)");
			}
			else {
				$("#praise-"+_id).html(parseInt($("#praise-"+_id).html())-1);
				$("#"+_id).html("赞");
				$("#"+_id).attr("onclick", "praiseReply('"+_id+"',1)");
			}
			window.location.reload();
		}
	});
}
//子回复html
function subreply(_this) {
	$("#replyPid").val(_this.attributes.pid.value);
	$("#bbsId").val(_this.attributes.bbsid.value);
	$("#replyPstudentid").val(_this.attributes.pstudentid.value);
	//删除其他
	$("#replyContent_sub" ).remove();
	$("#reply-" + currentReplyid).html("");
	//创建子回复
	$("#reply-" + _this.id).html("<textarea class='input' id='subsaytext' name='subsaytext'></textarea><div class='btn_replay'><div class='replay_pic'><a href='javascript:void(0);' class='replay_biaoqing'></div><a href='javascript:void(0);' onclick='dosubreply()'> 回复</a></div>");
	$('.replay_biaoqing').qqFace({
		id : 'facebox', 
		assign:'subsaytext', 
		emotions:em 	//表情信息json格式，id表情排序号 phrase表情使用的替代短语url表情文件名
	});
	$("#subsaytext").focus();
	currentReplyid = _this.id;
}
//回复用户
function dosubreply() {
	var content = replace_em($("#subsaytext").val());
	$.ajax({
		type : 'POST',
		url : "doreply",
		data : {
			bbsId : $("#bbsId").val(),
			replyContent : content,
			replyPid: $("#replyPid").val(),
			replyPstudentid: $("#replyPstudentid").val()
		},
		dataType : "json",
		success : function(reply) {
			window.location.reload();
		},
		error : function(e){
			alert(e);
		}
	});
}

//回复BBS
function doreply() {
	var content = replace_em($("#saytext").val());
	$.ajax({
		type : 'POST',
		url : "doreply",
		data : {
			bbsId : $("#bbsId").val(),
			replyContent : content
		},
		dataType : "json",
		success : function(reply) {
			window.location.reload();
		}
	});
}