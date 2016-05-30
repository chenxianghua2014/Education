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
				value="${ subject.subjectId }" /><input type="hidden" id="courseId"
				name="courseId" value="${ subject.courseId }" />
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
							value="${subject.subjectType}" />判断题</td>
					</tr>
				
					<tr>
						<th><span class="warning">*</span>试题题干</th>
						<td><script id="subjectTopic" name="subjectTopic"
								type="text/plain" style="width:100%;height:100px;"
								data-rule="required;"></script></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>选择题答案</th>
						<td>&#10004 <input type="radio" id="subjectAnswer_yes"
							name="subjectAnswer" value="YES"
							<c:out value="${subject.subjectAnswer== 'YES'?'checked':''}"/> />
							&#10006 <input type="radio" id="subjectAnswer_no"
							name="subjectAnswer" value="NO"
							<c:out value="${subject.subjectAnswer== 'NO'?'checked':''}"/> />
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