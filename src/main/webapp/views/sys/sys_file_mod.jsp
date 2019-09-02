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
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
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
				    <li class="active">修改文件</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">修改文件信息</div>
					<form role="form" class="form-horizontal" id="form1" method="post">						
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<input type="hidden" id="fileId" value="${sysFile.id }">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="file_code" class="col-sm-3">文件编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="text" id="file_code" name="file_code" value="${sysFile.code }" class="form-control" data-message="文件编号不得为空！">
									</div>
								</div>
								<div class="form-group">
									<label for="file_name" class="col-sm-3">文件名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<div style="display: none;" id="thelist" class="uploader-list"></div>
										<input type="text" id="file_name" name="file_name" value="${sysFile.name }" class="form-control" data-message="部门级别不得为空！">										
									</div>
								</div>
								<div class="form-group">
									<label for="file_type" class="col-sm-3">文件类型<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9" style="position:relative">
										<input type="text"class="form-control serInp"  readonly placeholder="请选择类型" style="background:#fff;" />
										<select id="file_type" name="file_type" class="form-control" style="display:none; position:absolute;left:15px;z-index:10; top:34px;" size="5">
											<c:forEach var = "fileTy" items = "${selectAll}">
									         <option value="${fileTy.id }" <c:if test="${sysFile.idFileType eq fileTy.id}"> selected="selected"</c:if> >${fileTy.name }</option>
									        </c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="file_remark" class="col-sm-3">文件描述<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<textarea class="col-sm-12" id="file_remark">${sysFile.remark}</textarea>
									</div>								
																		
								</div>
								
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="modConfirm">确定</a>
									<a class="btn btn-sm" onclick="cancel()">取消</a>
								</div>
							</div>
						</div>
					</form>	
				</div>				 
			</div>
		</div>
		<script>
	
		$(".serInp").val($("#file_type option:selected").text());
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
			})
		</script>
</body>
</html>