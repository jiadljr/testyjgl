<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/kb_event.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">知识库</a></li>
				    <li class="active">知识库</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">添加知识信息</div>
					<form action="" role="form" class="form-horizontal" id="form1" method="post">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<input type="hidden" id="token" name="token" value="${token}" />
						<div class="mRwell-container" >
							<div class="mRwell-con-center" style="width:85%;">
								<div class="form-group">
									<label for="code" class="col-sm-2">事件编号：</label>
									<div class="col-sm-10"><input type="text" id="eventCode" name="eventCode" class="form-control error_null" data-message="事件编号不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="meauName" class="col-sm-2">事件标题：</label>
									<div class="col-sm-10"><input type="text" id="eventName" name="eventName" class="form-control error_null" data-message="事件标题不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="meauGrade" class="col-sm-2">资产类型：</label>
									<div class="col-sm-10">
										<input type="hidden" id="assetType" name="assetType" />
										<input type="text" id="sourceName" name="sourceName" onkeyup="source(this)" class="form-control error_null_NaN" placeholder="请选择资产类型">
										<ul id="sourceTree" class="ztree" style="width:93%;z-index:10000; overflow:auto;position:absolute;top:36px;background-color:#eee;display:none"></ul>
									</div>
								</div>
								<div class="form-group">
									<label for="meauLink" class="col-sm-2">事件等级：</label>
									<div class="col-sm-10">
									<select id="eventLevel" name="eventLevel" class="form-control" >
									  <option value="1">一级</option>
									  <option value="2">二级</option>
									  <option value="3">三级</option>
									  <option value="4">四级</option>
									</select>
									</div>
								</div>
								<div class="form-group">
									<label for="meauDesc" class="col-sm-2">事件产生原因：</label>
									<div class="col-sm-10"><input type="text" id="eventCauses" name="eventCauses" class="form-control error_null" data-message="事件产生原因不得为空！" ></div>
								</div>
								<div class="form-group">
									<label for="meauImage" class="col-sm-2">事件描述：</label>
									<div class="col-sm-10"><input type="text" id="eventDesc" name="eventDesc" class="form-control error_null" data-message="事件标题不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="meauImage" class="col-sm-2">事件解决方案：</label>
									<div class="col-sm-10"><textarea  id="eventResolvent" name="eventResolvent" class="form-control error_null" data-message="事件解决方案不得为空！" style="resize:none;"></textarea></div>
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="addKbEvent">确定</a>
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
			$.each($(".error_null"),function(){
		         if($(this).val()==""){
		             $(this).addClass("err_null");
		         }
		     })
			$("#addKbEvent").click(function() {
				$.each($(".error_null"),function(){
			         if($(this).val()!==""){
			             $(this).removeClass("err_null");
			         }
			     })
				if($(".error_null").val()==""|| $(".error_null").hasClass("error")||$('.error_null').hasClass('err_null')){
				
					$.each($(".error_null"),function(){
							if($(this).val()==""){
								var error=$(this).attr("data-message");
								$(this).val(error);
								$(this).addClass("error");
							}
						})
					return false;
				}
				var eventCode = $("#eventCode").val();
				var eventName = $("#eventName").val();
				var assetType = $("#assetType").val();
				var eventLevel = $("#eventLevel").val();
				var eventCauses = $("#eventCauses").val();
				var eventDesc = $("#eventDesc").val();
				var token = $("#token").val();
				var eventResolvent = $("#eventResolvent").val();
				$.ajax({
					type : "post",
					url : getPath() + "/insertKbEventInfo.do",
					data : {
						"eventCode" : eventCode,
						"eventNme" : eventName,
						"assetType" : assetType,
						"eventLevel" : eventLevel,
						"eventCauses" : eventCauses,
						"eventDesc" : eventDesc,
						"eventResolvent" : eventResolvent,
						"token":token
					},
					success : function(data) {
						if (data == 1) {
							location.href = getPath() + "/kbEvent.do?cid=1";
						}
					}
				})
			})
		})
	</script>
</body>
</html>