<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/con_number.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<style>
	.mRight .mRbot{ width:600px; margin:0 auto;}
	.mRight .mRbot .page-header { color:#3f90e6; border-color:#3f90e6;}
	.mRight .mRbot .con_title{ color:#3f90e6; }
	</style>	
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">配置管理</a></li>
				    <li class="active">参数配置</li>
				</ol>
			</div>					
			<div class="mRbot">
				<h4 class="page-header">参数设置</h4>
				<form id="configForm">
				<div class="con_title">密码时效</div>
				<div class="con_content">
					<div class="con_left">
						密码有效期：
						<select name="pwdAging" id="pwdAging">
							<option value="30" <c:if test="${pwdAging == 30}">selected</c:if>>30</option>
							<option value="60" <c:if test="${pwdAging == 60}">selected</c:if>>60</option>
							<option value="90" <c:if test="${pwdAging == 90}">selected</c:if>>90</option>
						</select>
						天
					</div>
					<div class="con_right">*更换登录密码的周期</div>
				</div>

				<div class="con_title" style="display:none;">值班时间</div>
				<div class="con_content" style="display:none;">
					<div class="con_left">
						白班：
						<select name="fDayShift" id="fDayShift">
							<c:forEach var = "item" begin="00" end="23">
						    <option value="${item}" <c:if test="${item == fDayShift }">selected</c:if> >${item}</option>
					    	</c:forEach>
						</select>时-
						<select name="tDayShift" id="tDayShift">
							<c:forEach var = "item" begin="00" end="23">
						    <option value="${item}" <c:if test="${item == tDayShift }">selected</c:if> >${item}</option>
					    	</c:forEach>
						</select>时
					</div>
					<div class="con_right">*白天值班的时间</div>
					<div class="con_left">
						夜班：
						<select name="fNightShift" id="fNightShift">
							<c:forEach var = "item" begin="00" end="23">
						    <option value="${item}" <c:if test="${item == fNightShift }">selected</c:if> >${item}</option>
					    	</c:forEach>
						</select>时-
						<select name="tNightShift" id="tNightShift">
							<c:forEach var = "item" begin="00" end="23">
						    <option value="${item}" <c:if test="${item == tNightShift }">selected</c:if> >${item}</option>
					    	</c:forEach>
						</select>时
					</div>
					<div class="con_right">*晚上值班的时间</div>
					
					<div class="btnlist">
						<button class="btn btn-primary btn-md" onclick="saveConfig()">确定</button>
						<button class="btn btn-primary btn-md" onclick="cancelModify()">取消</button>
					</div>
				</div>
				</form>
			</div>
		</div>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/con_para_info.js"></script>
	<script type="text/javascript">
	function saveConfig(){
		var getPath = "<%=request.getContextPath()%>/saveParamConfig.do";
		$.ajax({
			cache: false,
	        type: "POST",
	        url:getPath,
	        data:$('#configForm').serialize(),// 你的formId
	        async: false,
	        error: function() {
	                    alert("设置失败，请稍后重试");
	                },
	        success: function() {
	                  window.location.href = "<%=request.getContextPath()%>/toParamConfig.do";
	                }
	    });
	}
	</script>
</body>
</html>