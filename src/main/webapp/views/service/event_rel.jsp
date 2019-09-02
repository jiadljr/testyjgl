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
	<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
	
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
		.author_jump{width:460px;height: 150px;z-index: 2000;border-radius:2px;position: absolute;top: 95px;left: 25%;background-color: #f8f8f8;box-shadow:3px 3px 20px 1px rgba(0,0,0,.3);display: none;}
	.author_jump .author_tit{ padding:0 20px; height:42px; border-radius:2px 2px 0 0 ;line-height:42px;color:#fff;background:#3b85c7;}
	.author_jump .author_tit span{ display:inline-block; float:right;  height:42px; line-height:42px;cursor: pointer;font-size:32px; }
	.author_jump .form-group{ margin-top:10px;height:40px;}
	.author_jump .form-group label{ height:34px; line-height:34px;}
	.btn_list{ float: right;}
    .btn_list .btn_bass{ height: 26px; line-height: 26px; font-size: 12px; margin:0 8px; padding: 0 17px; display: inline-block;color: #fff;border-radius: 5px; }
    .btn_list .btn_bass:hover{ cursor: pointer; text-decoration: none;}
    .btn_add{background: #3b85c7;}
    .btn_close{ background: #e2614e; }

	</style>
</head>
<body>
	<div class="author_jump">
		
		<div class="form-group">
			<label class="col-sm-3 text-right">增援人</label>
			<div class="col-sm-8">
				<select id="sys_role" name="sys_role" class="form-control selectpicker bla bla bli" multiple data-live-search="true"></select>
			</div>
		</div>
		<div class="btn_list">
			<a class="btn_bass btn_add" id="button_search_author">提交</a>
			<a class="btn_bass btn_close" id="button_cancel_author" style="margin-right:20px;">取消</a>		
		</div>
	</div>
</body>
</html>
<script>
//关闭弹框
$("#button_cancel_author").click(function() {
	$(".author_jump").fadeOut();
	$(".mask").fadeOut();
})
//添加处理人
$("#button_search_author").click(function() {
	var roleStr = $("#sys_role").val();
	if (roleStr == null || roleStr == "") {
		alert("请选择处理人进行增援!!");
		return;
	}
	var event_id=$("#event_id").val();
	var roleStrs = String(roleStr);
	$('#sys_role').selectpicker('refresh');
	$('#sys_role').selectpicker('render');
	$.post(getPath() + "/addDealUser.do", {
		"event_id":event_id,
		"dealUsers" : roleStrs
	}, function(result) {
		if (result == "success") {
			$(".author_jump").fadeOut();
			$(".mask").fadeOut();
			
			
			findAgrncyList();
			
			
			layer.open({
	            type:4,
	        	time:2000,
	            tipsMore: true,
	            title: '提示',
	            tips:[2,"#fff0a5"],
	            closeBtn:0,
	            area: ['200px', '70px'],
	            shade: 0,
	            maxmin: false,
	            offset: 'rb',
	            content: ['<p style="color:#333"> 提示</p><p style="color:#666">　　　添加成功！！！</p>', '#eveBox']
	        });
		}
		if (result == "error") {
			alert(alreadyChoose+"已经参与该事件，请重新选择！");
		}
	})
})
$('#idUserDeal,#eventService').focus(function(){
	$(this).removeClass('error');
})

</script>