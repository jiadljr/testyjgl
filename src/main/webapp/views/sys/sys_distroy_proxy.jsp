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
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
		input{border:none;}
		.redTxt{color:red; font-size:12px;}
		.passwordMes{width:640px; min-heigth:300px; margin-top:20px;}
		.passwordMes  .Mesp_txt{ text-align:center; heigth:30px; line-height:30px; margin-top:20px; font-size:20px;}
		.passwordMes .btnlist{ width:100%; height:40px;text-align:right; }
		.passwordMes .btnlist .btn{ color:#f7f7f7; dispaly:inline-block; margin:5px 10px; line-height:1; padding:5px 8px}
		.passwordMes .btnlist .btn_exit{background:#e2614e; border:0px;}	


	</style>
</head>
<body>
		
<div class="passwordMes">	
	<form action="<%=request.getContextPath()%>/updDept.do" role="form" class="form-horizontal" id="form1" method="post">
		<c:if test="${noProxyPerson != null}">
		
				<p class="Mesp_txt">
					当前业务代理人:<strong>无</strong>
				</p>
		
		</c:if>
		<c:if test="${noProxyPerson == null}">
			<input type="hidden" id="user_id" name="user_id" value="${proxyUser.id }">
		
				<p class="Mesp_txt">
					当前业务代理人:<strong>${proxyUser.name }</strong>
				</p>
		
			<div class="btnlist">
				<a class="btn btn-primary btn-sm" id="distroySubmit" >确定</a>
				<a class="btn btn-primary btn-sm btn_exit" id="exit"  href="javascript:closeLon();">取消</a>
			</div>
		</c:if>
	</form>				
</div>
	<script>
		$(function(){
			$('input').focus(function(){
				if($(this).val().length=="0"){
					$(this).next().html('');
				}
			})
			/*提交*/
			$("#distroySubmit").click(function(){
				var user_id = $("#user_id").val();
				$.ajax({
					url:getPath()+"/distroyProxy.do",
					data:{"user_id":user_id},
					dataType:"text",
					type:"post",
					success:function(succ){
						if(succ == "success"){
							alert("取消成功！");
							closeLon();
						}
					},
					error:function(err){
						if(err == "error"){
							alert("取消失败！");
						}
					},
				})
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
		})
	</script>
</body>
</html>