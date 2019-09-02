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
	<script src="<%=request.getContextPath()%>/js/con_menu_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">配置管理</a></li>
				    <li class="active">修改菜单</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">修改菜单信息</div>
					<form action="<%=request.getContextPath()%>/modMenu.do" role="form" class="form-horizontal" id="form1" method="post">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<input type="hidden" id="menuId" name="menuId" value="${sysMenuOne.id }"/>
						<input type="hidden" id="flag" name="flag" value="${flag}"/>
						<input type="hidden" name="token" value="${token}"/>
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="code" class="col-sm-3">菜单编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="code" name="code" class="form-control" data-message="菜单编号不得为空！" value="${sysMenuOne.code }"></div>
								</div>
								<div class="form-group">
									<label for="meauName" class="col-sm-3">菜单名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="meauName" name="meauName" class="form-control" data-message="菜单名称不得为空！" value="${sysMenuOne.name }"></div>
								</div>
								<div class="form-group">
									<label for="meauGrade" class="col-sm-3">菜单等级<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="hidden" id="level" name="level" value="${sysMenuOne.menuLevel }"/>
										<select id="meauGrade" name="meauGrade" class="form-control" data-message="菜单等级不得为空！" onchange="closeSelect2()">
											<option value="1" <c:if test="${sysMenuOne.menuLevel eq '1'}"> selected="selected"</c:if>>一级菜单</option>
											<option value="2" <c:if test="${sysMenuOne.menuLevel eq '2'}"> selected="selected"</c:if>>二级菜单</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="meauUp" class="col-sm-3">上级菜单<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<select id="meauUp" name="meauUp" class="form-control">
											<option value="">请选择上级菜单</option>
											<c:forEach items="${parName }" var="pName">
												<option value="${pName.id }" <c:if test="${sysMenuOne.parentId eq pName.id}"> selected="selected"</c:if>>${pName.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="meauLink" class="col-sm-3">菜单链接<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="meauLink" name="meauLink" class="form-control" data-message="菜单链接不得为空！" value="${sysMenuOne.menuUrl }"></div>
								</div>
								<div class="form-group">
									<label for="meauDesc" class="col-sm-3">菜单描述<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="meauDesc" name="meauDesc" class="form-control" data-message="菜单描述不得为空！" value="${sysMenuOne.remark }"></div>
								</div>
								<div class="form-group">
									<label for="meauImage" class="col-sm-3">菜单图片<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="meauImage" name="meauImage" class="form-control" data-message="*菜单图片不得为空！" value="${sysMenuOne.menuImg }"></div>
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
			var flag=$("#flag").val();
			if (flag == "yes") {
				$("#meauGrade").attr("disabled","disabled").css("background-color","#EEEEEE;");
				$("#meauUp").attr("disabled","disabled").css("background-color","#EEEEEE;");
			}
		})
	</script>
</body>
</html>