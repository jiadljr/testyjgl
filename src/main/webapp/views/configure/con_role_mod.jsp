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
	<script src="<%=request.getContextPath()%>/js/con_role_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">角色信息</a></li>
				    <li class="active">修改角色</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<input type="hidden" id="pages" name="pages" value="${pages }">
				<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
				<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
				<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
				<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
				<div class="mRwell">
					<div class="mRwell-tit">修改角色信息</div>
					<form action="" role="form" class="form-horizontal" id="form1" method="post">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<input type="hidden" name="roleId" value="${roleOne.id }"/>
								<div class="form-group">
									<label for="role_name" class="col-sm-3">角色编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="roleCode" name="roleCode" class="form-control" data-message="角色编号不得为空！" value="${roleOne.code }"></div>
								</div>
								<div class="form-group">
									<label for="role_name" class="col-sm-3">角色名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="roleName" name="roleName" class="form-control" data-message="角色名称不得为空！" value="${roleOne.name }"></div>
								</div>
								<div class="form-group">
									<label for="role_remark" class="col-sm-3">角色描述<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="roleRemark" name="roleRemark" class="form-control" data-message="角色描述不得为空！" value="${roleOne.remark }"></div>
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
	<script type="text/javascript">
		$(function(){
			/*页面的切换*/
			$("#top .collapse .nav li").eq(0).removeClass("active");
			$("#top .collapse .nav li").eq(4).addClass("active");
			$("#main .mLeft .list-group li").eq(0).removeClass("active");
			$("#main .mLeft .list-group li").eq(3).addClass("active");
			$(".openWin").hide();
			$("#meauUp").attr("disabled","disabled").css("background-color","#EEEEEE;");
		})
	</script>
</body>
</html>