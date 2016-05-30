var setting = {
	view: {
		addHoverDom: addHoverDom,	//添加基础课程
		removeHoverDom: removeHoverDom,	//删除节点
		selectedMulti: true
	},
	edit: {
		enable: true,
		editNameSelectAll: true,
		showRemoveBtn: showRemoveBtn,
		showRenameBtn: showRenameBtn,
		renameTitle: "重命名"
	},
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		beforeDrag: beforeDrag,
		beforeEditName: beforeEditName,
		beforeRemove: beforeRemove,
		beforeRename: beforeRename,
		onRemove: onRemove,
		onRename: onRename
	}
};

var zNodes =[];

var log, className = "dark";
function beforeDrag(treeId, treeNodes) {
	return false;
}
function beforeEditName(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	return confirm("重命名【" + treeNode.name + "】 ？");
}
function beforeRemove(treeId, treeNode) {
	className = (className === "dark" ? "":"dark");
	showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.selectNode(treeNode);
	if(confirm("确认删除【" + treeNode.name + "】 吗？")){
		ajaxPost("delResourceTreeNode", {knowledgeResourceId:treeNode.id}, function(msg){
			if(msg.status=='error'){
				alert(msg.head);
			}
			zNodes = [];
			$.fn.zTree.destroy("treeDemo");
			ajaxPost("ResourceTree", "", backData);
			
		});
	}
}
function onRemove(e, treeId, treeNode) {
	showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
}
function beforeRename(treeId, treeNode, newName, isCancel) {
	className = (className === "dark" ? "":"dark");
	showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	if (newName.length == 0) {
		alert("名称不能为空.");
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		setTimeout(function(){zTree.editName(treeNode)}, 10);
		return false;
	}else{
		ajaxPost("updateResourceTreeNode", {knowledgeResourceId:treeNode.id, knowledgeResourceName:newName}, function(msg){
			if(msg.status=='error'){
				alert(msg.head);
			}
			zNodes = [];
			$.fn.zTree.destroy("treeDemo");
			ajaxPost("ResourceTree", "", backData);
			return true;
		});
	}
}
function onRename(e, treeId, treeNode, isCancel) {
	showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
}
function showRemoveBtn(treeId, treeNode) {
	if(treeNode.level>=1)return true;
}
function showRenameBtn(treeId, treeNode) {
	return true;
}
function showLog(str) {
	if (!log) log = $("#log");
	log.append("<li class='"+className+"'>"+str+"</li>");
	if(log.children("li").length > 8) {
		log.get(0).removeChild(log.children("li")[0]);
	}
}
function getTime() {
	var now= new Date(),
	h=now.getHours(),
	m=now.getMinutes(),
	s=now.getSeconds(),
	ms=now.getMilliseconds();
	return (h+":"+m+":"+s+ " " +ms);
}

var newCount = 1;
function addHoverDom(treeId, treeNode) {
	//if(treeNode.name!="基础课程")return false;
	if(treeNode.level==1)return false;
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='添加目录' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var dirname=prompt("请输入要建立的目录名称","");
		dirname = $.trim(ToCDB(dirname));
		var pattern = /[`~!@#$%^&*()_+<>?:"{},.\/;'[\]]/im;
		if(pattern.test(dirname)){
			alert("提示信息:您输入的数据含有非法字符!"); return false;
		}
		if(dirname==null||dirname=="")return;
		
		var syllabus = {knowledgeResourceName: dirname, knowledgeResourceUpid: treeNode.id};
		
		ajaxPost("addResourceTreeNode", syllabus, function(msg){
			if(msg.status=='error'){
				alert(msg.head);
			}else{
			zNodes = [];
			$.fn.zTree.destroy("treeDemo");
			ajaxPost("ResourceTree", "", backData);
			}
		});
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};

function selectAll() {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
}

//半角转全角
function ToDBC(value) { 
	var tmp = "";
	for(var i=0;i<value.length;i++){ 
		if(value.charCodeAt(i)==32){ 
			tmp= tmp+ String.fromCharCode(12288); 
		} 
		if(value.charCodeAt(i)<127){ 
			tmp=tmp+String.fromCharCode(value.charCodeAt(i)+65248); 
		}
	} 
	return tmp; 
}

//全角转换为半角
function ToCDB(value){
	var tmp = "";
	for(var i=0;i<value.length;i++){
		if(value.charCodeAt(i)>65248&&value.charCodeAt(i)<65375){ 
			tmp += String.fromCharCode(value.charCodeAt(i)-65248); 
		}else{
			tmp += String.fromCharCode(value.charCodeAt(i)); 
		}
	}
	return tmp;
}

//加载课程目录树
function backData(msg){
	if(msg.status!="success")return;
	var jo = new Array();
	for(var i=0; i<msg.msgBody.length; i++){
		zNodes.push({ id:msg.msgBody[i].knowledgeResourceId,pId:msg.msgBody[i].knowledgeResourceUpid, name:msg.msgBody[i].knowledgeResourceName, open:true, 
			icon:"images/file.gif", iconOpen:"images/folder-closed.gif", iconClose:"images/folder-closed.gif",click:"direct21('" + msg.msgBody[i].knowledgeResourceId + "');"});
	}
	//console.log(msg);
	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	$("#selectAll").bind("click", selectAll);
}

function direct21(knowledgeResourceId){
	window.parent.document.getElementById('rightMain').src="Resourcelist?page=1&mark=1&knowledgeResourceId="+knowledgeResourceId;
}

