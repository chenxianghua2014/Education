$(function() {
/*  $("#checkAll").click(function(){
      $('input[name="subBox"]').attr("checked",this.checked);
  });
  var $subBox = $("input[name='subBox']");
  $subBox.click(function(){
      $("#checkAll").attr("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
  });*/
  initData(0);
});

var pageSize = 10;
function initData(pageIndex){	//页索引从0开始
	ajaxPost("queryCourse",{page:pageIndex, pageSize:pageSize, syllabusId:'',courseDel:1 },function(msg){
		//console.log(msg);
		var strHtml = new Array();
		for(var i=0; i<msg.msgBody.length; i++){
			var item = msg.msgBody[i];
			strHtml.push("<tr>");
			strHtml.push("<td>"+i+"</td><td>"+item.courseId+"</td>");
			strHtml.push("<td>"+item.courseName+"</td><td>"+item.courseType+"</td>");
			strHtml.push("<td>"+item.courseStatus+"</td><td>"+item.courseCatalog+"</td>");
			strHtml.push("<td>"+item.coursePubman+"</td><td>"+item.courseCompany+"</td>");
			strHtml.push("</tr>");
		}
		$("#dataBody").html(strHtml.join(""));
		
        //生成分页控件
        kkpager.generPageHtml({
           pno : pageIndex,		//当前页码
           mode : 'click', //可选，默认就是link
           //总页码
           total : (msg.head*1/pageSize>0?(msg.head*1/pageSize+1):(msg.head*1/pageSize)),
           //总数据条数
           totalRecords : msg.head*1,
           //点击页码、页码输入框跳转、以及首页、下一页等按钮都会调用click
           //适用于不刷新页面，比如ajax
           click : function(n){
        	   initData(n-1);
               this.selectPage(n);
           },
           //getHref是在click模式下链接算法，一般不需要配置，默认代码如下
           getHref : function(n){
               return '#';
           }
       });
	});
}
