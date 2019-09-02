<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style>
body,html{width:100%; height:100%; overflow:hidden;}
.filebox{ height:40px; width:100%; margin-top:10px;}
.filebox label{float:left;width:100px; height:34px;line-height:34px;display:inline-block; text-align:center}
.filebox input{float:left; width:300px; height:34px;line-height:34px;outline:none;}
 .way1 {  cursor: pointer; display:block;  margin: 8px 10px;  float: left;  width: 170px; height:24px;  position: relative;  background: #1476d9;  border-radius: 4px;  line-height: 24px;  }
 .way1 input { cursor: pointer;  display: block;  width: 100%;  height: 45px;  opacity: 0;  position: absolute;  z-index: 3;  top: 0;  left: 0;  }
 .way1 a { cursor: pointer;  color:#fff;  display: block;  width: 100%; position: absolute;  z-index: 2;  top: 0;  left: 0;  text-align: center  }
 .btnList{ height:40px; line-height:40px; text-align:right;position:absolute; bottom:0; left:0;width:100%;}
 .btnList a{margin-right:20px;} 
 #butFile .webuploader-pick{margin-top:9px;}
 
</style>
<title>Insert title here</title>

</head>
<body>
<form action="" method="post" id="iconForm" enctype="multipart/form-data">
	<div class="fileTab taskList">
		<a href="#" class="btn_base btn_strong btn_blue " style="font-size:14px; float:left;padding:5px 12px; margin-top:9px;margin-left:20px; "  onclick="downLoadLead()">模版下载</a>
		<a href="javascript:;" class="browse" id="butFile" style="margin-top:9px;text-align:center;" class="col-sm-2 text-center btn_blue">浏览</a>
		 
		    <div class="filebox">
			<div style="display: none;" id="thelist" class="uploader-list"></div>
			<label for="file_name" class="col-sm-2">文件名称：</label>
		
				<input type="text" id="file_name" name="file_name" class="form-control error_null" data-message="文件名称不得为空！">										
		
			<input type="hidden" id="record_id">
			<input type="hidden" id="text1">
			<input type="hidden">
	   </div>  									
	</div>
	<div class="btnList">
		<a class="btn_base btn_strong btn_blue " href="#"  onclick="sureLead()">确认</a>
		<a class="btn_base btn_strong btn_red "   href="#">取消</a>
		
	</div>
</form>
	<script type="text/javascript">
	function downLoadLead(){
		location.href = getPath()+"/projectExcelExport.do";
		/* $.ajax({
			url:getPath()+"/projectExcelExport.do",
			type:"post",
			async:false,
			success:function(result){
				if (result == 'success') {
					location.href = getPath()+"/projectDownExcel.do";
				}
			},
			error:function(err){
				alert($.parseJSON(err.responseText).msg);
			}
		}) */
		
	}
	$(function(){
		uploader = WebUploader.create({
		    // swf文件路径
		    swf: "/plugins/uploadify/Uploader.swf",
		    // 文件接收服务端。
		    server: getPath()+"/batchImportTask.do",
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