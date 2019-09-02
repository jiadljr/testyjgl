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
	<script src="<%=request.getContextPath()%>/js/con_password.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
		input{border:none;}
		.redTxt{color:red; font-size:12px;}
		.passwordMes{width:640px; min-heigth:300px; margin-top:20px;}
		.passwordMes .p_mes_inner{ width:620px; height:210px;border:1px solid #a8a7a7;margin:0 auto;position:relative;}
		.passwordMes .p_mes_inner .Mesp_tit{font-size:12px ;font-weight:normal;　height:20px; line-height:12px;position:absolute;left:10px; top:-6px;background:#fff;}
		.passwordMes .p_mes_inner .Mesp_txt{ heigth:30px; line-height:30px; margin-top:20px;}
		.passwordMes .p_mes_inner .Mesp_txt label{ width:20%;text-align:right; float:left; color:#3d3d3d; margin:0; }
		.passwordMes .p_mes_inner .Mesp_txt input{ border:1px solid #8f8d8d; width:40%; heigth:28px; line-height:30px; border-radius:5px; flaot:left;}
		.passwordMes .p_mes_inner .Mesp_txt span{　display:inline-block; width:30%; height:30xp; line-height:30px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis}
		.passwordMes .btnlist{ width:100%; height:40px;text-align:right; }
		.passwordMes .btnlist .btn{ color:#f7f7f7; dispaly:inline-block; margin:5px 10px; line-height:1; padding:5px 8px}
		.passwordMes .btnlist .btn_exit{background:#e2614e; border:0px;}	


	</style>
</head>
<body>
		
<div class="passwordMes">	
	<form action="<%=request.getContextPath()%>/updDept.do" role="form" class="form-horizontal" id="form1" method="post">
		<div class="p_mes_inner">
			<input type="hidden" id="userId" name="userId" value="${userId }"/>
			<h3 class="Mesp_tit">密码信息</h3>
			<p class="Mesp_txt">
				<label for="old_pword">原密码：</label>
				<input type="text" id="old_pword" name="oldPword" class="error_null" data-message="原密码不能为空" value="">
				<span class="redTxt" id="oldPword">*原密码不得为空</span>
			</p>
			<p class="Mesp_txt">
				<label for="new_pword" >新密码：</label>
				<input type="password" id="new_pword" name="newPword" class=" error_null"  data-message="*新密码不能为空" value=""  maxlength="10" placeholder="6-10位数字&字母组合登录密码">
				<span class="redTxt" id="newPword">*新密码不得为空</span>
			</p>
			<p class="Mesp_txt">
				<label for="new_agin_pword" >再次输入：</label>
				<input type="password" id="new_again_pword" name="newAgainPword" class=" error_null" data-message="*再次输入不能为空" value=""  maxlength="10" placeholder="6-10位数字&字母组合登录密码" onblur="sure()">
				<span class="redTxt"  id="double_in_out">*请再次输入新密码</span>
			</p>
			<p class="redTxt text-center" style="margin-top:20px;">*为了您的账户安全，请在账号失效前修改密码超过时限 请联系管理员</p>
		</div>
		<div class="btnlist">
			<a class="btn btn-primary btn-sm" id="upPassword" >确定</a>
			<a class="btn btn-primary btn-sm btn_exit" id="exit"  href="javascript:closeLon();">取消</a>
		</div>
	</form>				
</div>
	<script>
		$(function(){
			
			$('input').focus(function(){
				if($(this).val().length=="0"){
					$(this).next().html('');
				}
			})
		})
	</script>
		<script>
		function closeLon(){
			var index = parent.layer.getFrameIndex(window.name);  
			parent.layer.close(index) 
		}
	</script>
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