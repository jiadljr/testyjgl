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
					<div class="mRwell-tit">修改知识信息</div>
					<form action="" role="form" class="form-horizontal" id="form1" method="post">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<input type="hidden" id="kbId" name="kbId" value="${selectKbEventById.id }">
						<div class="mRwell-container">
							<div class="mRwell-con-center" style="width:85%;">
								<div class="form-group">
									<label for="code" class="col-sm-2">事件编号：</label>
									<div class="col-sm-10"><input type="text" value="${selectKbEventById.eventCode }" id="eventCode" name="eventCode" class="form-control error_null" data-message="事件编号不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="meauName" class="col-sm-2">事件标题：</label>
									<div class="col-sm-10"><input type="text" id="eventName" value="${selectKbEventById.eventName }" name="eventName" class="form-control error_null" data-message="事件标题不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="meauGrade" class="col-sm-2">资产类型：</label>
									<div class="col-sm-10">
										<input type="hidden" id="assetType" name="assetType" value="${typeId }"/>
										<input type="text" id="sourceName" name="sourceName" onkeyup="source(this)" class="form-control error_null_NaN" placeholder="请选择资产类型" value="${typeName }">
										<ul id="sourceTree" class="ztree" style="width:93%;z-index:10000; overflow:auto;position:absolute;top:36px;background-color:#eee;display:none"></ul>
									</div>
								</div>
								<div class="form-group">
									<label for="meauLink" class="col-sm-2">事件等级：</label>
									<div class="col-sm-10">
									<select id="eventLevel" name="eventLevel" class="form-control" >
									  <option value="1" <c:if test="${selectKbEventById.eventLevel eq '1'}"> selected="selected"</c:if>>一级</option>
									  <option value="2" <c:if test="${selectKbEventById.eventLevel eq '2'}"> selected="selected"</c:if>>二级</option>
									  <option value="3" <c:if test="${selectKbEventById.eventLevel eq '3'}"> selected="selected"</c:if>>三级</option>
									  <option value="4" <c:if test="${selectKbEventById.eventLevel eq '4'}"> selected="selected"</c:if>>四级</option>
									</select>
									</div>
								</div>
								
								<div class="form-group">
									<label for="meauDesc" class="col-sm-2">事件产生原因：</label>
									<div class="col-sm-10"><input type="text" value="${selectKbEventById.eventCauses }" id="eventCauses" name="eventCauses" class="form-control error_null" data-message="事件产生原因不得为空！" ></div>
								</div>
								<div class="form-group">
									<label for="meauImage" class="col-sm-2">事件描述：</label>
									<div class="col-sm-10"><input type="text" id="eventDesc" value="${selectKbEventById.eventDesc }" name="eventDesc" data-message="事件标题不得为空！" class="form-control error_null"></div>
								</div>
								<div class="form-group">
									<label for="meauImage" class="col-sm-2">事件解决方案：</label>
									<div class="col-sm-10"><textarea id="eventResolvent"  name="eventResolvent" class="form-control error_null" data-message="事件解决方案不得为空！" style="resize:none">${selectKbEventById.eventResolvent }</textarea></div>
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="updateKbEvent">确定</a>
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
			updateKbEvent();
		})
	</script>
</body>
</html>