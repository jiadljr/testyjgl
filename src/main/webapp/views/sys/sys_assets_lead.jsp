<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
		input{border:none;}
		.filebox{ height:40px; width:100%; margin-top:10px;}
		.filebox label{float:left;width:100px; height:34px;line-height:34px;}
		.filebox input{float:left; width:300px;}
		.filebox a{ float:right; margin:4px 8px 0 6px; text-align:center; }	
		.passwordMes .btnlist{ width:100%; height:40px;text-align:right; margin-top:20px; }
		.passwordMes .btnlist .btn{ height: 24px;line-height: 20px;font-size: 12px;margin: 0 8px;padding: 0 17px;display: inline-block;color: #fff;border-radius: 5px;}
	

	</style>
</head>
<body>
		
<div class="passwordMes">	
	<form action="<%=request.getContextPath()%>/updDept.do" role="form" class="form-horizontal" id="form1" method="post">
		<div class="filebox">
			<div style="display: none;" id="thelist" class="uploader-list"></div>
			<label for="file_name" class="col-sm-2">文件名称：</label>
			<input type="hidden" id="record_id">
			<input type="hidden" id="text1">
			<input type="text" id="file_name" name="file_name" class="form-control error_null" data-message="文件名称不得为空！">										
			<input type="hidden">
			<a href="javascript:;" class="browse" id="butFile" class="col-sm-2">浏览</a>
		</div>
		
		<div class="btnlist">
			<a class="btn btn-primary btn-sm" onclick="sureLead()">确定</a>
		</div>
	</form>				
</div>
	
	<script type="text/javascript">
		$(function(){
			uploader = WebUploader.create({
			    // swf文件路径
			    swf: "/plugins/uploadify/Uploader.swf",
			    // 文件接收服务端。
			    server: getPath()+"/leadAssets.do",
				// 选择文件的按钮。可选。
				// 内部根据当前运行是创建，可能是input元素，也可能是flash.
				pick : {id : '#butFile',multiple: false
                    },
				// 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
				resize : false
				});
			  uploader.on('fileQueued',
					function(file) {
				  		if($("#record_id").val() != ""){
				  			uploader.cancelFile($("#record_id").val());
				  		}
						$("#thelist").empty().html(
								'<div id="' + file.id + '" class="item">'
										+ '<h4 class="info">' + file.name
										+ '</h4> </div>');
						document.getElementById("text1").value = document.getElementById("thelist").innerText;
						//不需要记录前一个不同文件的id,因为每一次都走一遍方法
						$("#record_id").val(file.id);
						var a = document.getElementById("thelist").innerText;
						var b = a.split('.')[0];
						$("#file_name").val(b).removeClass('error');
			 });
			uploader.on('uploadBeforeSend', function(block, data) {
				// block为分块数据。
				var file_code=$("#file_code").val();
				var file_name=$("#file_name").val();
				var file_type=$("#file_type").val();
				var file_remark=$("#file_remark").val();
				data.file_code = file_code;
				data.file_name = file_name;
				data.file_type = file_type;
				data.file_remark = file_remark;
				// 修改data可以控制发送哪些携带数据。  
			});
			 uploader.on( 'uploadSuccess', function( file , response) { 
				 uploader.reset();
		 		var index = parent.layer.getFrameIndex(window.name); 
				parent.layer.close(index);
				window.parent.fatherFun();
			   }); 
			 uploader.on("uploadAccept", function( file, data){
				    if(data.msg != null && data.msg != undefined){
				    	alert(data.msg);
				    	var index = parent.layer.getFrameIndex(window.name); 
						parent.layer.close(index);
				    }
				});
			/*页面的切换*/
			$("#top .collapse .nav li").eq(0).removeClass("active");
			$("#top .collapse .nav li").eq(4).addClass("active");
			$("#main .mLeft .list-group li").eq(0).removeClass("active");
			$("#main .mLeft .list-group li").eq(3).addClass("active");
		})
		function sureLead(){
			uploader.upload();
		}
	</script>
</body>
</html>