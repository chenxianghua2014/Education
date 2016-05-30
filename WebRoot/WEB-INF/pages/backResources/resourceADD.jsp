<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%String path = request.getContextPath();%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>编辑资源</title>
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
<!-- 上传文件 -->
<link type="text/css" rel="stylesheet" href="js/uploadify/css/uploadify.css"/>	
<script type="text/javascript" src="js/uploadify/jquery.uploadify-3.1.min.js"></script>

</head>
<body>
	<div class="title">当前位置:资源管理>添加资源</div>
	<div class="editBlock">
		<form action="ResourceADD" method="post" autocomplete="off"  id="fff"   name="fff" 
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true} " enctype="multipart/form-data">
			<input type="hidden" name="knowledgeResourceId"  id="knowledgeResourceId"  value="${knowledgeResourceId}"/>
			<input type="hidden" name="knowledgePath"  id="knowledgePath"  />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑资源信息</td>
				</tr>
				<tbody>
					<tr >
						<th width="150">资源名称:</th>
						<td><input id="knowledgeName" name="knowledgeName" type="text" class="inputText" style="width:300px;"  value=""  data-rule="required;"/></td>
					</tr>
					<tr >
						<th width="150">制作人:</th>
						<td><input id="knowledgeProducer" name="knowledgeProducer" type="text" class="inputText" style="width:300px;"  value=""  data-rule="required;"/></td>
					</tr>
					<tr>
							<th width="150">
								文件支持下载
							</th>
							<td>
								<select name="knowledgeCatalog" id="knowledgeCatalog"  data-rule="required;">
									<option value="1">
										是
									</option>
									<option value="2">
										否
									</option>
									</select>
							</td>
						</tr>
				
					
					<tr>
						<th width="150">上传资源</th>
						<td>
							<input id="file_upload" name="file" type="file" class="inputText" style="width:300px;"  value="" />
                           <div id="uploader_queue"></div> 
                          </td>
                          
					</tr>
					
				
				</tbody>
				<tr>
					<td colspan="2" class="toolbar"><input type="submit" class="inputButton" value="确定"  />  </td>
				</tr>
			</table>
		</form>
	</div>
	
<script>

  $(function() {
      $("#file_upload").uploadify({
       	  'auto' : true,
       	  'formData' : {'folder' : 'file'},
          'height' : 30,
          'swf' : 'js/uploadify/uploadify.swf', // flash
          'uploader' : 'shangchuan', // 数据处理url
          'width' : 120,
          'multi': false,
          'fileTypeDesc' : '格式：docx,pdf,flv,mp4',
          'fileTypeExts' : '*.flv;*.docx;*.doc;*.mp4;*.pdf;*.ppt;*.pptx',
          'fileSizeLimit' : '200MB',
          'buttonText' : '选择文件',
          'uploadLimit' : 10,
          'progressData':'speed', // 显示上传进度百分比
          'successTimeout' : 5,
          'requeueErrors' : false,
          'removeTimeout' : 10,
          'queueSizeLimit' :10,
          'queueID'  : 'uploader_queue',
          'onInit' : function (){},
          'onFallback': function () {//初始化浏览器不兼容触发 也可监视浏览器是否禁用flash播放插件
            alert('浏览器可能禁用flash播放插件或者flash版本过低，请您确定是否禁用插件或者升级高版本');
        },
       // 单个文件上传成功时的处理函数
		  'onUploadSuccess' : function(file, data, response){
		  		$("#knowledgePath").attr("value",data);
   				},
          'onQueueComplete' : function(queueData) {
     			alert("上传成功");
    			}      
      });
  });
 </script>
</body>

</html>
