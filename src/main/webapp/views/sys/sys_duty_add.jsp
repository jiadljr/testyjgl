<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sysDuty_list.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_duty_list.js"></script>
    <script src='<%=request.getContextPath()%>/js/utilx.js'></script>
   	<style type="text/css">
   		.mRbot{
   			top:0;bottom:0;
   			padding:0;
   			margin:0;
   		}
   	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">添加值班信息</div>
					<form action="" role="form" class="form-horizontal" method="post">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<input type="hidden" id="time" value="${parameter }">
								<div class="form-group">
									<label for="dutyTime" class="col-sm-2 text-right">值班时间：</label>
									<div class="col-sm-8"><input type="text" id="dutyTime" class="form-control"></div>
								</div>
								<div class="form-group">
									<label for="dayDuty" class="col-sm-2 text-right">白班人员：</label>
									<div class="col-sm-8">
									<!-- 存放白班代表的数字 -->
									<input type="hidden" id="inpDuty">
									<!-- 存放选中的多选的ID -->
								    <input type="hidden" id="dayDutyId">
										<select id="dayDuty" name="dutyMan" class="form-control selectpicker bla bla bli" data-message="白班人员不得为空！" multiple data-live-search="true">
											<c:forEach var = "sfm" items = "${userByRoleType}">
											<option value="${sfm.id }">${sfm.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="nightDuty" class="col-sm-2 text-right">夜班人员：</label>
									<div class="col-sm-8">
									<!-- 存放夜班代表的数字 -->
									<input type="hidden" id="inpNight">
									<!-- 存放选中的多选的ID -->
								    <input type="hidden" id="nightDutyId">
										<select id="nightDuty" name="dutyMa" class="form-control selectpicker bla bla bli" data-message="夜班人员不得为空！" multiple data-live-search="true">
											<c:forEach var = "sfm" items = "${userByRoleType}">
											<option value="${sfm.id }">${sfm.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="confirm">确定</a>
									<a class="btn btn-sm" onclick="history.go(-1)">取消</a>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	 <script type="text/javascript">
	 $(function(){
			var params = $("#time").val();
			$.post(getPath()+"/selectByCond.do?time="+params,function(result){
				var dayStaff = result.dayStaff;
				day = dayStaff.substr(0,dayStaff.length-1);
				var a = day.split(",");
				var nightStaff = result.nightStaff;
				nig = nightStaff.substr(0,nightStaff.length-1);
				var b = nig.split(",");
				$("#dutyTime").val(params);
				for(var i =0; i<a.length;i++){
					$("#dayDuty").children("option[value=" + a[i] + "]").prop("selected",true);
				}
				$('#dayDuty').selectpicker('refresh');
				$('#dayDuty').selectpicker('render');
			    for(var i =0; i<b.length;i++){
			    	$("#nightDuty").children("option[value=" + b[i] + "]").prop("selected",true);
			    }
			    $('#nightDuty').selectpicker('refresh');
			    $('#nightDuty').selectpicker('render');
			})
			confirm();
			change();
		})
	    $("#dayDuty").selectpicker({
	        'selectedText': '123'
	    });
		$("#nightDuty").selectpicker({
	        'selectedText': '123'
	    });
    </script>
</body>
</html>