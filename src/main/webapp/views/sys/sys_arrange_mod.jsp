<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
	<script src="<%=request.getContextPath()%>/js/sys_arrange_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="#">排班管理</a></li>
				    <li class="active">修改值班</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">修改值班信息</div>
					<form action="" role="form" class="form-horizontal" id="form1" method="post">
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<input type="hidden" id="duty_id" name="duty_id" value="${arrange.id }">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="role_name" class="col-sm-3">开始时间<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="text" id="duty_start_time" class="form-control" placeholder="请选择开始时间" value="<fmt:formatDate value="${arrange.dutyStartTime }" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label for="role_name" class="col-sm-3">结束时间<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<input type="text" id="duty_end_time" class="form-control" placeholder="请选择结束时间" value = "<fmt:formatDate value="${arrange.dutyEndTime }" pattern="yyyy-MM-dd HH:mm:ss" />" disabled="disabled">
									</div>
								</div>
								<div class="form-group">
									<label for="role_name" class="col-sm-3">运维人员<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9" style="position:relative;">
										<input type="text" class="serInp form-control error_null">
										<select id="duty_user" name="duty_user" class="form-control" size="5" style="position:absolute; top:34px;left:15px; display:none;">
											<c:forEach items="${operList }" var="oper">
												<option value = "${oper.id }" <c:if test="${arrange.idUser == oper.id }">selected</c:if>>${oper.name }</option>
											</c:forEach>
										</select>	
									</div>
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="updateConfirm">确定</a>
									<a class="btn btn-sm" onclick="cancel()">取消</a>
								</div>
							</div>
						</div>
					</form>					
				</div>				 
			</div>
		</div>

	<script type="text/javascript">
	$(".serInp").val($("#duty_user option:selected").text());
	$("#duty_user").width($(".serInp").width())
	$(".serInp").focus(function(){
		$("#duty_user").show();
		$("#duty_user").click();
	})
	$("#duty_user option").click(function(){
		$("#duty_user ").hide();
		$(".serInp").val($("#duty_user option:selected").text());
	})
	$("#duty_user").blur(function(){
		$(this).hide();		
	})
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