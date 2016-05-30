<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>修改资源</title>
<!-- 表格风格样式  -->
<link href="<%=path%>/css/skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery/jquery-1.9.1.min.js"></script>

<!-- 日期 -->
<script type="text/javascript" src="<%=path%>/js/My97DatePicker/WdatePicker.js"></script>

<!-- 校验 -->
<link rel="stylesheet" href="<%=path%>/js/validator-0.7.3/jquery.validator.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/jquery/jquery.form.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/jquery.validator.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/js/validator-0.7.3/local/zh_CN.js"></script>
	
<link rel="stylesheet" type="text/css" href="js/jBox/Skins/Blue/jbox.css"></link>
<script type="text/javascript" src="js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="js/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/jquery/jquery-migrate-1.2.1.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('form').validator({
			valid : function(form) {
				//表单验证通过，提交表单到服务器
				$.jBox.tip("正在导入，请耐心等待一下！", 'loading');
				document.ff.action = "updateCourseresource";
				document.ff.method = "post";
				document.ff.submit();
			}
		});
	});
</script>



</head>
<body>
	<div class="title">当前位置:课程管理>修改资源</div>
	<div class="editBlock">
			<form enctype="multipart/form-data" name="ff" id="ff">
			<input type="hidden" name="courseId"  id="courseId"  value="${cid}"/>
			<input type="hidden" name="courseresourceId"  id="courseresourceId"  value="${courseresource.courseresourceId}"/>
			<table>
				<tr>
					<td colspan="2" class="subtitle">修改资源信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">资源名称:</th>
						<td><input id="courseresourceName" name="courseresourceName" type="text" class="inputText" style="width:300px;"  value="${courseresource.courseresourceName}"  data-rule="required;"/></td>
					</tr>
					<tr>
							<th width="150">
								文件支持下载(限制文本)
							</th>
							<td>
								<select name="courseresourceMark" id="courseresourceMark"  data-rule="required;">
									<option value="1" ${courseresource.courseresourceMark=="1"?"selected":"" }>
										是
									</option>
									<option value="2" ${courseresource.courseresourceMark=="2"?"selected":"" }>
										否
									</option>
									</select>
							</td>
						</tr>
				
					
					<tr>
						<th width="150">上传资源</th>
						<td>
							<input id="file" name="file" type="file" class="inputText" style="width:300px;"  />
                          </td>
					</tr>
					
				
				</tbody>
				<tr>
					<td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定"  />&nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>

</html>
