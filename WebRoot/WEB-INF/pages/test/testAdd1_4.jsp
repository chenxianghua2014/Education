<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="css/skins/default/main.css" />
<link rel="stylesheet" href="js/validator-0.7.3/jquery.validator.css"
	type="text/css"></link>
<script type="text/javascript" src="js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="js/ueditor/lang/zh-cn/zh-cn.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src=""></script>
<script type="text/javascript"
	src="js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" src="js/validator-0.7.3/local/zh_CN.js"></script>
</head>
<body>
	<div class="title">当前位置:考试管理 > 试题制作</div>
	<div class="editBlock">
		<form method="post" autocomplete="off"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="subjectId" name="subjectId"
				value="${ subject.subjectId }" /> <input type="hidden"
				id="courseId" name="courseId" value="${ subject.courseId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">试题制作</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>试题资源名称</th>
						<td><input type="text" id="subjectName" name="subjectName"
							value="${subject.subjectName}" /></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>试题类型</th>
						<td><input type="hidden" id="subjectType" name="subjectType"
							value="${subject.subjectType}" />选择题(四个选项)</td>
					</tr>
					
					<tr>
						<th><span class="warning">*</span>试题题干</th>
						<td><script id="subjectTopic" name="subjectTopic"
								type="text/plain" style="width:100%;height:100px;"
								data-rule="required;"></script></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>A</th>
						<td><script id="subjectA" name="subjectA" type="text/plain"
								style="width:100%;height:60px;"></script></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>B</th>
						<td><script id="subjectB" name="subjectB" type="text/plain"
								style="width:100%;height:60px;"></script></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>C</th>
						<td><script id="subjectC" name="subjectC" type="text/plain"
								style="width:100%;height:60px;"></script></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>D</th>
						<td><script id="subjectD" name="subjectD" type="text/plain"
								style="width:100%;height:60px;"></script></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>选择题答案</th>
						<td>A <input type="radio" id="subjectAnswer_A"
							name="subjectAnswer" value="A"
							<c:out value="${subject.subjectAnswer== 'A'?'checked':''}"/> />
							B <input type="radio" id="subjectAnswer_B" name="subjectAnswer"
							value="B"
							<c:out value="${subject.subjectAnswer== 'B'?'checked':''}"/> />
							C <input type="radio" id="subjectAnswer_C" name="subjectAnswer"
							value="C"
							<c:out value="${subject.subjectAnswer== 'C'?'checked':''}"/> />
							D <input type="radio" id="subjectAnswer_D" name="subjectAnswer"
							value="D"
							<c:out value="${subject.subjectAnswer== 'D'?'checked':''}"/> />
						</td>
					</tr>
					<tr>
						<th></th>
						<td><input type="submit" class="inputButton" value="确认添加" />&nbsp;&nbsp;
							<input type="button" class="inputButton" value="取消"
							onclick="history.back()" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	var editor;
	var editorA;
	var editorB;
	var editorC;
	var editorD;
	var toobars = [ 'fullscreen', 'source', '|', 'undo', 'redo', '|', 'bold',
			'italic', 'underline', 'fontborder', 'strikethrough',
			'superscript', 'subscript', 'removeformat', 'formatmatch',
			'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor',
			'backcolor', 'insertorderedlist', 'insertunorderedlist',
			'selectall', 'cleardoc', '|', 'rowspacingtop', 'rowspacingbottom',
			'lineheight', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyjustify', 'simpleupload' ];
	$(document).ready(function() {
		editor = UE.getEditor('subjectTopic', {
			toolbars : [ toobars ],
			maximumWords : 1000
		});
		editor.addListener("ready", function() {
			editor.setContent('${subject.subjectTopic }');
		});
		editorA = UE.getEditor('subjectA', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorA.addListener("ready", function() {
			editorA.setContent('${subject.subjectA }');
		});
		editorB = UE.getEditor('subjectB', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorB.addListener("ready", function() {
			editorB.setContent('${subject.subjectB }');
		});
		editorC = UE.getEditor('subjectC', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorC.addListener("ready", function() {
			editorC.setContent('${subject.subjectC }');
		});
		editorD = UE.getEditor('subjectD', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorD.addListener("ready", function() {
			editorD.setContent('${subject.subjectD }');
		});

		$('form').validator({
			//自定义用于当前实例的消息
			messages : {
				required : "请填写{0}"
			},
			//待验证字段集合
			fields : {
				subjectName : 'required',
				subjectType : 'required',
				subjectScore : 'required;range[0~20]',
				subjectAnswer : 'checked'
			},
			valid : function(form) {
				if (editor.getContent().length == 0) {
					alert("请填写试题题干!");
					editor.focus();
					return;
				}
				if (editorA.getContent().length == 0) {
					alert("请填写A选项内容!");
					editorA.focus();
					return;
				}
				if (editorB.getContent().length == 0) {
					alert("请填写B选项内容!");
					editorB.focus();
					return;
				}
				if (editorC.getContent().length == 0) {
					alert("请填写C选项内容!");
					editorC.focus();
					return;
				}
				if (editorD.getContent().length == 0) {
					alert("请填写D选项内容!");
					editorD.focus();
					return;
				}
				//表单验证通过，提交表单到服务器
				$.ajax({
					url : "subjectSava",
					method : "post",
					data : $('form').serialize(),
					success : function(d) {
						if (d > 0) {
							alert("保存成功!");
							window.location.href = document.referrer;
						}
					}
				});
			}
		});
	});
</script>
</html>