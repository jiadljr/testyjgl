<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	
	<script src="<%=request.getContextPath()%>/js/index.js"></script>

	<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_task_list.js"></script>
	<style type="text/css">
		body{overflow-y:auto;overflow-x:hidden; background:#f8f8f8;}
	
		
   		.taskMes{ width:650px; height:350px; margin:0 auto;}
   		.taskMesTab{ width:650px; height:320px; margin:0 auto;}
		.taskFileTab{ width:650px; height:320px; overflow-y:auto; overflow-x:hidden;float:left; }
		.taskDesc{ float:left; width:500px; margin-top:12px; line-height:16px; height:138px; oveflow-x:hidden;overflow-y:auto;}
		.text{height:280px; float:left; width:650px; oveflow-y:auto;}
		.descBox{width:650;margin:5px auto; height:105px; border:1px solid #ccc; float:left; background:#f8f8f8;}
		.descBox textarea{ display:block; box-sizing:border-box;width:640px; height:60px; float:left;margin:5px 0 5px 5px; line-height:16px; background:#fff;border-radius:5px; outline:none;}
		.descBox .descBtn{cursor:pointer; width:60px; height:30px; float:right;margin:0 5px 5px 0;display:block; background:#1476d9;  color:#fff; text-center;line-height:30px; font-size:16px;text-align:center}
		#taskDescList{width:650px; height:180px; margin:10px auto;overflow-y:auto;overflow-x:hidden;}
		#taskDescList li{border-bottom:1px solid #ccc; width:650px; float:left; min-height:20px;}
		#taskDescList li p{word-break:break-all; word-wrap:break-word; padding:0 10px;margin-top:7px; width:480px; float:left;display:inline-block; line-height:16px;}
		#taskDescList li span{ width:140px; color:blue; line-height:30px;height:30px; float:left;display:inline-block; text-align:center;}
		.descTit{ width:640px; height:30px; line-height:30px; font-size:16px;color:#555;margin:0 auto;}
	</style>
</head>
<body>
<div class="taskMes">
	<form role="form" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
		<input type="hidden" id="taskId" name="taskId" value="${taskId }"/>
		<input type="hidden" id="user_name" name="user_name" value="${user_name }"/>
		<p class="descTit">${taskName }</p>
		<div class="taskTalkTab">
			<div class="descBox" onkeydown="descEnter(event)">
				<textarea placeholder="请输入任务备注" id="taskRecord" name="taskRecord"  class=" proEditIn error_null"></textarea>
				<a class="descBtn" onclick="saveTaskRecord()">保存</a>				
			</div>
			<ul id="taskDescList">
				
			</ul>
		
		</div>
		
	</form>
</div>
	
		
	<script type="text/javascript">
	
	$(function(){
		
		loadTaskRecord();
	})
	
	</script>
	<script type="text/javascript">
	

	</script>
</body>
</html>