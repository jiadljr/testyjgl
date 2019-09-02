<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_arrange_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
	#dutyUserText{width:100%; height:100px ;background:#fff; cursor:pointer;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;}
	#dutyUserText{width:100%; height:100px ;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;background:#fff;}
	#dutyUserText a{overflow: hidden; text-overflow: ellipsis;white-space: nowrap;position:relative; display:inline-block; text-align:center; float:left; line-height:24px;height:24px; width:70px; margin:5px; background:#ff6900;color:#fff; }
	#dutyUserText a i{ display:none;}
	#dutyUserText a:hover{text-decoration: none; cursor: pointer;}
	#dutyUserText a span{ font-size:20px; position:absolute;right:0; top:-3px; display:block; height:20px; width:20px;color:fff; }
	
	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="#">排班管理</a></li>
				    <li class="active">添加值班</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">添加值班信息</div>
					<form action="" role="form" class="form-horizontal" id="form1" method="post">
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="role_name" class="col-sm-3">开始时间<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="text" id="duty_start_time" name="duty_start_time" class="form-control" placeholder="请选择开始时间" >
									</div>
								</div>
								<div class="form-group">
									<label for="role_name" class="col-sm-3">结束时间<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="text" id="duty_end_time" name="duty_end_time" class="form-control" placeholder="请选择结束时间" >
									</div>
								</div>
								<div class="form-group">
									<label for="status" class="col-sm-3">时间段<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<select id="timeScope" name="timeScope" class="form-control">
											<option value="1">12:00:00 - 12:00:00</option>
											<option value="2">12:00:00 - 08:00:00</option>
											<option value="3">08:00:00 - 08:00:00</option>
											<option value="4">08:00:00 - 12:00:00</option>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="role_name" class="col-sm-3">运维人员<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9" style="position:relative;">
										<div style="" id="dutyUserText" ></div>
										<select id="duty_user" name="duty_user"  class="form-control" multiple style="position:absolute;left:15px;top:100px; display:none;" size="5" >
											<c:forEach items="${userByRoleType }" var="oper">
												<option value = "${oper.id }">${oper.name }</option>
											</c:forEach>
										</select>				
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
		<script type="text/javascript">
			$("#duty_user").width($("#dutyUserText").width()-20)
			$("#dutyUserText").click(function(e){
				
				$("#duty_user").show();
				$("#duty_user").click();
				if($("#duty_user option").length==$(".bulingbuling").length){
					$("#duty_user").hide()
				}else{
					$("#duty_user").show()
				}
				e.stopPropagation();
			})
			$("#duty_user option").click(function(e){
				var text=$(this).text();
				var textId=$(this).val();
				$(this).hide().addClass("bulingbuling");
				if($("#duty_user option").length==$(".bulingbuling").length){
					$("#duty_user").hide()
				}else{
					$("#duty_user").show()
				}
				e.stopPropagation();
				document.getElementById("dutyUserText").innerHTML+="<a href='#'>"+text+"<i>"+textId+"</i><span>×</span></a>";
				$("#dutyUserText a span").click(function(e){
					$(this).parent().remove();
					e.stopPropagation()
					var newidcode=$(this).prev().text();
					$.each($("#duty_user option"),function(){
						if($(this).val()==newidcode){
							$(this).removeClass("bulingbuling");
							$(this).show();
						}
					})
					
				})
				
			})
			$("#duty_user").blur(function(){
				$(this).hide();		
			})
			$("#duty_user").click(function(e){
				e.stopPropagation();
			})
			$(document).click(function(){
				$("#duty_user").hide()
			})
			
		</script>
	<script type="text/javascript">
		$(function(){
		
			$("#duty_start_time").datetimepicker({
				lang:'ka',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
				minDate:'-1970/01/03'
			});
			$("#duty_end_time").datetimepicker({
				lang:'zh-CN',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
				minDate:'-1970/01/01'
			});
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