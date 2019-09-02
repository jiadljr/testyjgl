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
		.passwordMes .p_mes_inner{ width:620px; height:210px;border:1px solid #a8a7a7;margin:0 auto;position:relative;}
		.passwordMes  .Mesp_tit{font-size:12px ;font-weight:normal;　height:20px; line-height:12px;position:absolute;left:10px; top:-6px;background:#fff;}
		.passwordMes .selDiv{ heigth:30px; line-height:30px; margin-top:20px; width:80%;margin-left:10%; } 
		.selDiv select option{height:18px; padding:0 ;line-height:18px; }
		.passwordMes .p_mes_inner .Mesp_txt label{ width:20%;text-align:right; float:left; color:#3d3d3d; margin:0; }
		.passwordMes .p_mes_inner .Mesp_txt input{ border:1px solid #8f8d8d; width:40%; heigth:28px; line-height:30px; border-radius:5px; flaot:left;}
		.passwordMes .p_mes_inner .Mesp_txt span{　display:inline-block; width:30%; height:30xp; line-height:30px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis}
		.passwordMes .btnlist{ width:100%; height:40px;text-align:right; position:absolute;bottom:0px; }
		.passwordMes .btnlist .btn{ color:#f7f7f7; dispaly:inline-block; margin:5px 10px; line-height:1; padding:5px 8px}
		.passwordMes .btnlist .btn_exit{background:#e2614e; border:0px; }	
	

	</style>
</head>
<body>
		
<div class="passwordMes">	
	<form action="<%=request.getContextPath()%>/updDept.do" role="form" class="form-horizontal" id="form1" method="post">
		<input type="hidden" name="user_id" value="${user_id }">
	
				
				<div class="selDiv">
					<input id="selCopy" type="text" class="form-control" value="" placeholder="请选择您的业务代理人">
					<select  id="oper_id" name="oper_id"  class="form-control error_null" style="display:none;" size="5">
					
						<option value="">请选择您的业务代理人</option>
						
						<c:forEach items="${userByRoleType }" var="oper" >
							<c:if test="${user_id != oper.id && oper.id != duty}">
								<option value="${oper.id }">${oper.name }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			
			
	
		<div class="btnlist">
			<a class="btn btn-primary btn-sm" id="accreditSubmit" >确定</a>
			<a class="btn btn-primary btn-sm btn_exit" id="exit"  href="javascript:closeLon();">取消</a>
		</div>
	</form>				
</div>
	<script>
		$(function(){
			$("#selCopy").focus(function(){
				$("#oper_id").show();
				$("#oper_id").click();
				if($("#oper_id option").length>4){
					$("#oper_id").attr("size","5");
				}else{
					$("#oper_id").attr("size",$("#oper_id option").length);
				}
			})
			
			$("#oper_id option").click(function(){
				$("#oper_id ").hide();
				$("#selCopy").val($("#oper_id option:selected").text());
			})
			$("#oper_id").blur(function(){
				$(this).hide();		
			})
			
			/*提交*/
			$("#accreditSubmit").click(function(){
				var oper = $("#oper_id").val();
				if (oper == "") {
					alert("请选择人员！");
					return;
				}
				var dutyNo = window.parent.document.getElementById("dutyNo").value;
				$.ajax({
					url:getPath()+"/accreditProxy.do",
					data:$.param({"dutyNo":dutyNo})+'&'+jQuery('#form1').serialize(),
					dataType:"json",
					type:"post",
					success:function(succ){
						if(succ.result == "success"){
							alert("授权成功")
							closeLon();
							if(succ.dutyTrue != "yes"){
								window.parent.location.href = getPath() + "/index.do";
							}
						}
						if(succ.result == "error"){
							var proxyList = succ.proxyList;
							if(!confirm("当前存在代理人："+proxyList.name+",是否继续更改！")){ 
								closeLon();
								return;
							}else{
								submitSure(proxyList.id,oper);
							}
						}
					},
				})
			})
		})
	</script>
		<script>
		function submitSure(user_id,oper){
			$.ajax({
				url:getPath()+"/continueAccreditProxy.do",
				data:{"user_id":user_id,"oper":oper},
				dataType:"text",
				type:"post",
				success:function(succ){
					if(succ == "success"){
						alert("更改成功");
						closeLon();
					}
				},
			})
		}
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