/**
 * Use this ScriptDoc file to manage the documentation for the corresponding namespace in your JavaScript library.
 *
 * @author SJG
 */ 

function getRootPath(){
    // 获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    projectName = "/Education";
    return (localhostPaht + projectName);
};
$(window).load(function() {
	$('#newsImage').uploadify({
        'swf': getRootPath() + '/js/uploadify/uploadify.swf?ver='+ Math.random(),
        'uploader':'/Education/upplansort',// uploadify.swf 文件的相对路径   //文件保存路径
//		'uploader' :getRootPath()+ '/js/uploadify/uploadify.swf',
//		'script' : 'upload',//  后台处理程序的相对路径 
//		'script' : getRootPath()+ '/weddingphoto/upplansort',//  后台处理程序的相对路径 
        'auto': true, // 是否自动开始
        'multi': false, // 是否支持多文件上传
        'preventCaching' : true,	//若设置为true，一个随机数将被加载swf文件URL的后面，防止浏览器缓存。
        'buttonText': '上传图片', // 按钮上的文字
        'height':'32',                //浏览按钮的高度     
        'width':'140',  
        'simUploadLimit': 1, // 一次同步上传的文件数目
        'fileSizeLimit': '2MB', // 设置单个文件大小限制
        'queueSizeLimit': 1, // 队列中同时存在的文件个数限制
        'fileTypeDesc': '仅支持格式:jpg,gif,jpeg,png,bmp,最大2M', // 如果配置了以下的'fileExt'属性，那么这个属性是必须的
        'fileTypeExts': '*.jpg;*.gif;*.jpeg;*.png;*.bmp',// 允许的格式
        'cancelImg': 'uploadify/images/uploadify-cancel.png',
        'queueID': 'myTargetDivPic',//文件队列的ID，该ID与存放文件队列的div的ID一致。
        'onQueueFull': function(event, queueSizeLimit){
            alert("一次只能上传1张图片！");
            return false;
        }, 
       'onError': function(event, queueID, fileObj){
	            alert("文件:" + fileObj.name + "上传失败");
        },
        'onFallback': function () {//初始化浏览器不兼容触发 也可监视浏览器是否禁用flash播放插件
            alert('浏览器可能禁用flash播放插件或者flash版本过低，请您确定是否禁用插件或者升级高版本');
        },
        'onUploadSuccess' : function(file, data, response) {
        	 document.getElementById('noticeImage').value=data;
             $('#myTargetDivPic').html("<img src=\"../" + data + "\" style=\"border:1px solid #000;height: 200px;width:200px\"/>"); 
        }
      
        ,
        'onQueueComplete': function(queueData){
            alert('上传图片成功！');
        	 return;
        }
    });
});

function Add(){
    if ($("#myTargetDivPic").html() == "") {
        alert("请选择要上传的文件");
    }
    else {
        alert(document.getElementById("myTargetDivPic").innerText);
        $('#wphotoLogo').uploadify('upload', '*');
    }
}
function Upp(){
        alert(document.getElementById("myTargetDivPic").innerText);
        $('#wphotoLogo').uploadify('cancel', '*');
    
}
