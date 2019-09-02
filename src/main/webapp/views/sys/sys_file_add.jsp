<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_file_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">文件信息</a></li>
				    <li class="active">新增文件</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">添加文件信息</div>
					<form action="<%=request.getContextPath()%>/AddDept.do" role="form" class="form-horizontal" id="form1" method="post">						
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="file_code" class="col-sm-3">文件编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="text" id="file_code" name="file_code" class="form-control error_null" data-message="文件编号不得为空！">
									</div>
								</div>
								<div class="form-group">
									<label for="file_name" class="col-sm-3">文件名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-7">
										<div style="display: none;" id="thelist" class="uploader-list"></div>
										<input type="hidden" id="record_id">
										<input type="hidden" id="text1" >
										<input type="text" id="file_name" name="file_name" class="form-control error_null" data-message="文件名称不得为空！"  style="background:#fff;">										
									</div>
									<a class="browse" id="butFile" class="col-sm-2">浏览</a>
								</div>
								<div class="form-group">
									<label for="file_type" class="col-sm-3">文件类型<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9" style="position:relative;">
										<input type="text" class="serInp form-control" style="background:#fff;" readonly placeholder="请选择类型" />
									
										<select id="file_type" name="file_type" class="form-control" style="display:none; position:absolute;left:15px;z-index:10; top:34px;" size="5">
											<c:forEach var = "fileTy" items = "${selectAll}">s
									         <option value="${fileTy.id }">${fileTy.name }</option>
									        </c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="file_remark" class="col-sm-3">文件描述<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<textarea  class="form-control error_null" id="file_remark" data-message="文件描述不得为空！"></textarea>
									</div>								
																		
								</div>
								
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="addConfirm">确定</a>
									<a class="btn btn-sm" onclick="cancel()">取消</a>
								</div>
							</div>
						</div>
					</form>
					
				</div>				 
			</div>

		</div>
		<script>
		$("#file_type").width($(".serInp").width())
		$(".serInp").focus(function(){
			$("#file_type").show();
			$("#file_type").click();
		})
		$("#file_type option").click(function(){
			$("#file_type ").hide();
			$(".serInp").val($("#file_type option:selected").text());
		})
		$("#file_type").blur(function(){
			$(this).hide();		
		})
		</script>
		<script type="text/javascript">
	
			$(function(){
				$("#top .collapse .nav li").eq(0).removeClass("active");
				$("#top .collapse .nav li").eq(3).addClass("active");
				$("#main .mLeft .list-group li").eq(0).removeClass("active");
				$("#main .mLeft .list-group li").eq(4).addClass("active");
				$(".openWin").hide();
				uploader = WebUploader.create({
				    // swf文件路径
				    swf: "/plugins/uploadify/Uploader.swf",
				    // 文件接收服务端。
				    server: getPath()+"/insertFile.do",
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
							if($("#file_name").val()==""){
								$("#file_name").val(b).removeClass('error');
							}else{
								$("#file_name").removeClass('error');
							}
							
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
				 uploader.on( 'uploadSuccess', function( file ) { 
					 uploader.reset();
				   }); 
				uploader.on('uploadComplete', function(file) {
					location.href = getPath()+"/seleDo.do?cid=1";
				});
			})
			//添加
			/**
			 *多行非空验证 
			 * 逐个元素添加 class名 err_null
			 * 判断是否存在err_null
			 **/
			 $.each($(".error_null"),function(){
		         if($(this).val()==""){
		             $(this).addClass("err_null");
		         }
		     })
			$("#addConfirm").click(function(){
				$.each($(".error_null"),function(){
			         if($(this).val()!==""){
			             $(this).removeClass("err_null");
			         }
			     })
					if($(".error_null").val()=="" || $(".error_null").hasClass("error")||$(".error_null").hasClass('err_null')){
						$.each($(".error_null"),function(){
							if($(this).val()==""){
								var error=$(this).attr("data-message");
								$(this).val(error);
								$(this).addClass("error");
							}
						})
						return false;
					}else{
						console.log("上传...");
						uploader.upload();
						console.log("上传成功");
					}
			})
		</script>
</body>
</html>