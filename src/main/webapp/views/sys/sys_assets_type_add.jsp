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
	<script src="<%=request.getContextPath()%>/js/sys_assets_type_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">资产类型</a></li>
				    <li class="active">增加资产类型</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot	">
				<div class="mRwell">
					<div class="mRwell-tit">添加资产类型信息</div>
					<form role="form" class="form-horizontal" id="form1" method="post">
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<input type="hidden" name="token" value="${token}" />
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="code" class="col-sm-3">类型编码<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="code" name="code" class="form-control error_null" data-message="资产编号不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-3">类型名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="name" name="name"  class="form-control error_null" data-message="资产名称不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="Level" class="col-sm-3">层次<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="hidden" id="layer" name="layer" value="1"/>
										<select id="level" name="level" class="form-control" onchange="closeSelect2()">
											<option value="1">一级资产</option>
											<option value="2">二级资产</option>
											<option value="3">三级资产</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="parentName" class="col-sm-3">上级类型<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9" style="position:relative;">
										<input type="text" class="serInp form-control" readonly style="background:#fff;" placeholder="请选择上级类型" />
										
										<select id="parentName" name="parentName" class="form-control" style="display:none; position:absolute;left:15px;z-index:10; top:34px;" size="5">
											<option value="" selected="selected">请选择上级类型</option>
											<c:forEach items="${pName }" var="pName">
												<option value="${pName.id }">${pName.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>											
								<div class="form-group">
									<label for="remark" class="col-sm-3">类型说明<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="remark" name="remark"  class="form-control error_null" data-message="资产厂家不得为空！" ></div>
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="addAssetsTypeOk">确定</a>
									<a class="btn btn-sm" onclick="cancel()">取消</a>
								</div>
							</div>
						</div>
					</form>
					
				</div>				 
			</div>

		</div>
			<script type="text/javascript">
		$(function(){
			$(".serInp").attr("disabled","disabled").css("background-color","#EEEEEE;");
		
		})
	</script>
	<script>
		$("#parentName").width($(".serInp").width())
		$(".serInp").focus(function(){
			$("#parentName").show();
			$("#parentName").click();
		})
		
	</script>
	<script type="text/javascript">
		$(function(){
			closeSelect2();
		})
	</script>
</body>
</html>