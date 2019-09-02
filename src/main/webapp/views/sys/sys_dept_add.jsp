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
	<script src="<%=request.getContextPath()%>/js/sys_dept_list.js"></script>
	
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">部门信息</a></li>
				    <li class="active">增加部门</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot	">
				<div class="mRwell">
					<div class="mRwell-tit">添加部门信息</div>
					<form action="<%=request.getContextPath()%>/AddDept.do" role="form" class="form-horizontal" id="form1" method="post">						
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<input type="hidden" name="token" value="${token}" />
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="code" class="col-sm-3">部门编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="code" name="code" class="form-control error_null" data-message="部门编号不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="deptName" class="col-sm-3">部门名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="deptName" name="deptName" class="error_null form-control" data-message="部门级别不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="deptLevel" class="col-sm-3">部门级别<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="hidden" id="level" name="level" value="1"/>
										<select id="deptLevel" name="deptLevel" class="form-control" onchange="closeSelect2()">
											<option value="1">一级部门</option>
											<option value="2">二级部门</option>
											<option value="3">三级部门</option>
											<option value="4">四级部门</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="deptParent" class="col-sm-3">上级部门<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9" style="position:relative;">
										<input type="text" class="serInp form-control" readonly style="background:#fff;" placeholder="请选择上级部门"/>

										<select id="deptParent" name="deptParent" class="form-control" style="display:none; position:absolute;left:15px;z-index:10; top:34px;" size="5">
											<option value="">请选择部门名称</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="deptTel" class="col-sm-3">部门电话<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="deptTel" name="deptTel" class="error_null form-control telClass"  data-message="部门电话不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="remark" class="col-sm-3">部门描述<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<textarea id="remark" name="remark" class="form-control" data-message="部门描述不得为空！"></textarea>
									</div>									
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="addConfirm" onclick="save()">确定</a>
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
		$("#deptParent").width($(".serInp").width())
		$(".serInp").focus(function(){
			$("#deptParent").show();
			$("#deptParent").click();
		})
		
	</script>
</body>
</html>