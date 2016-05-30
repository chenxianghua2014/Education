function showAddTest() {
	var strHtml = ""
			+ "<div class='editBlock'><table>"
			+ "<tr><th>试题类型:</th><td><input type='radio' name='subjectType' value='1_4' checked='checked'/>选择题(四个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='1_3'/>选择题(三个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='1_5m'/>多选题(五个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='1_m'/>多选题(六个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='2'/>判断题</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='3'/>填空题</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='4'/>主观题</td></tr>"
			+ "<tr><th></th><td><input name='button' onclick='addTest()' type='button' class='inputButton' value='添加' />&nbsp;&nbsp;<input name='button' onclick='$.jBox.close();' type='button' class='inputButton' value='返回' /></td></tr></table></div>";
	$.jBox.open(strHtml, '选择添加试题类型', 350, 262, {
		buttons : {
			'关闭' : true
		}
	});
}

function showImportTest() {
	var strHtml = ""
			+ "<div class='editBlock'><table>"
			+ "<tr><th>试题类型:</th><td><input type='radio' name='subjectType' value='1_4' checked='checked'/>选择题(四个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='1_3'/>选择题(三个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='1_5m'/>多选题(五个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='1_m'/>多选题(六个选项)</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='2'/>判断题</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='3'/>填空题</td></tr>"
			+ "<tr><th></th><td><input type='radio' name='subjectType' value='4'/>主观题</td></tr>"
			+ "<tr><th></th><td><input name='button' onclick='importTest()' type='button' class='inputButton' value='导入' />&nbsp;&nbsp;<input name='button' onclick='$.jBox.close();' type='button' class='inputButton' value='返回' /></td></tr></table></div>";
	$.jBox.open(strHtml, '选择导入试题类型', 350, 262, {
		buttons : {
			'关闭' : true
		}
	});
}

function importTest() {
	window.location.href = "testImport?subjectType="
			+ $("input[name='subjectType']:checked").val() + "&courseId="
			+ getUrlParam("courseId");
}

function addTest() {
	window.location.href = "testAdd?subjectType="
			+ $("input[name='subjectType']:checked").val() + "&courseId="
			+ getUrlParam("courseId");
}

function delTest(subjectId) {
	if (confirm("您确定要删除该条记录吗?")) {
		$.ajax({
			type : 'POST',
			url : "testDel",
			data : {
				subjectId : subjectId
			},
			dataType : "json",
			success : function(r) {
				if (r > 0) {
					alert("删除成功!");
					window.location.reload();
				}
			}
		});
	}
}
