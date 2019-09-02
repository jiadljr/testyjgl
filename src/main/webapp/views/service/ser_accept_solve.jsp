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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_accept_list.js"></script>
	<style>		
	</style>	
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">受理信息</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">				
                <div class="mRwell mRwell-service">
                	<div class="mRwell-left">
                		<div class="mRwell-tit">确认信息</div>
							<form role="form" class="form-horizontal" id="form1">
								<div class="mRwell-container">
									<div class="mRwell-con-center" style="width:90%">
									<input type="hidden" name="eventId" value="${solve.id_event }"/>
									<input type="hidden" name="dealId" value="${solve.id }"/>
									<input type="hidden" name="dealUserId" value="${idUserDeal }"/>
										<c:forEach items="${SolveList }" var="solve">
											<div class="details" >
											<div class="form-group">
												<label for="eventLevel" class="col-sm-3 pull-left">资产编号：</label>
												<div class="col-sm-7 pull-left">
													${solve.property }
												</div>
												<!--  <div class="close-win pull-right" style="margin-right:20px"></div>-->
											</div>
										
											<div class="form-group">
												<div class="col-sm-5" style="padding:0">
													<label for="eventService" class="col-sm-6 col_n_6">处理人：</label>
													<div class="col-sm-6 col_n_4">
														${solve.dealName }
													</div>
												</div>
												<div class="col-sm-7">
													<label for="eventService" class="col-sm-4">岗位：</label>
													<div class="col-sm-8">
														${solve.dealPost }
													</div>
												</div>
											</div>
											<div class="form-group">
												<label for="eventPriority" class="col-sm-3">处理时间：</label>
												<div class="col-sm-9">
													${solve.dateDeal }
												</div>
											</div>
											<div class="form-group">
												<label for="acceptDesc" class="col-sm-3">处理描述：</label>
												<div class="col-sm-9">
													<textarea class="form-control" id="acceptDesc" name="acceptDesc" data-message="*事件描述不得为空！">${solve.dealDesc }</textarea>
												</div>
											</div>
											<div class="form-group">
												<label for="acceptDesc" class="col-sm-3">附件：</label>
												<div class="col-sm-9">
													<a href="/images/myw3schoolimage.jpg" download="w3logo">demo.doc</a>
												</div>
												<div class="col-sm-9">
													<a href="/images/myw3schoolimage.jpg" download="w3logo">demo.jpg</a>
												</div>
											</div>
											<div class="form-group">
												<label for="acceptDesc" class="col-sm-3">处理结果：</label>
												<div class="col-sm-9">
													<c:if test="${solve.dealStatus == 3 }">增援</c:if>
													<c:if test="${solve.dealStatus == 4 }">转派</c:if>
													至${solve.reinPost }:${solve.reinName }
												</div>
											</div>
											<!-- <div class="form-group">
												<label for="acceptDesc" class="col-sm-3">转派原因：</label>
												<div class="col-sm-9">
													增援
												</div>
											</div> -->
										</div>
										</c:forEach>
										<div class="form-group">
											<label for="acceptDesc" class="col-sm-3">结果：</label>
											<div class="col-sm-4">
												<input type="radio" name="result" id="result">通过												
											</div>
											<div class="col-sm-4">												
												<input type="radio" name="result" id="result">重新处理
											</div>
										</div>
										<div class="form-group">
											<label for="acceptDesc" class="col-sm-3">意见：</label>
											<div class="col-sm-9">
												<textarea class="form-control" id="acceptDesc" name="acceptDesc" data-message="*事件描述不得为空！"></textarea>
											</div>
										</div>								
										
										<div class="btnlist">
											<a class="btn btn-primary btn-sm" id="addNomalSure" style="margin-left:10%">确定</a>
											<a class="btn btn-sm" onclick="history.go(-1)">取消</a>
										</div>
									</div>
								</div>
							</form>
                	</div>
                	<div class="mRwell-right">
                		<div class="mRwell-right-apply">
	                		<div class="mRwell-right-tit">申告信息</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">申告部门：</label><div class="mRwell-con-right">${eventOne.extend1 }</div>
	                		</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">申告人：</label><div class="mRwell-con-right">${eventOne.extend2 }</div>
	                		</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">联系方式：</label><div class="mRwell-con-right">${eventOne.eventContact }</div>
	                		</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">申告时间：</label><div class="mRwell-con-right"><fmt:formatDate value="${eventOne.dateCreate}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
	                		</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">事件标题：</label><div class="mRwell-con-right">${eventOne.eventName }</div>
	                		</div>
	                	<div class="mRwell-right-last">
	                			<label class="mRwell-con-left" style="float:left;">事件描述：</label><div class="mRwell-con-right"style="float:left; line-height:0; font-size:0;"><textarea disabled="disabled" class="form-control" style="width:100%; vertical-align:top;">${eventOne.eventDesc }</textarea></div>
	                		</div>
                		</div>
                		<div class="mRwell-right-accept">
	                		<div class="mRwell-right-tit">受理信息</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">受理人：</label><div class="mRwell-con-right">${userOne.name }</div>
	                		</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">受理时间：</label><div class="mRwell-con-right"><fmt:formatDate value="${eventOne.dateAccept}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
	                		</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">事件等级：</label>
                			<div class="mRwell-con-right">
								<c:if test="${eventOne.eventLevel == 1}">一级事件</c:if>
								<c:if test="${eventOne.eventLevel == 2}">二级事件</c:if>
								<c:if test="${eventOne.eventLevel == 3}">三级事件</c:if>
								<c:if test="${eventOne.eventLevel == 4}">四级事件</c:if>
							</div>
	                		</div>
	                		<div class="mRwell-right-con">
	                			<label class="mRwell-con-left">服务类型：</label><div class="mRwell-con-right">应用服务</div>
	                		</div>
	                		<div class="mRwell-right-con">
								<label for="deptLevel" class="mRwell-con-left">优先级：</label>
								<div class="mRwell-con-right">
									<c:if test="${eventOne.eventPriority == 1}">高</c:if>
									<c:if test="${eventOne.eventPriority == 2}">中</c:if>
									<c:if test="${eventOne.eventPriority == 3}">低</c:if>
								</div>
							</div>
                		</div>              			
                	</div>
				</div>
				</div>
			<div class="mRpage" id="page2"></div>
		</div>
	<script type="text/javascript">
		$(function(){
			 if($(".close-win").text()=="打开"){
				 $(".close-win").parent().nextAll().hide();
			 }
			 $(".close-win").click(function(){
				 /* if($(this).text()=="关闭"){
					 $(this).text("打开");
					 $(this).parent().nextAll().hide();
				 }
				 if($(this).text()=="打开"){
					 $(this).text("关闭");
					 $(this).parent().nextAll().show();
				 } */
				 if($(this).text()=="关闭"){
					 $(this).text("打开");
					 $(this).parent().nextAll().hide();
					 return;
				 }
				 if($(this).text()=="打开"){
					 $(this).text("关闭");
					 $(this).parent().nextAll().show();
					 return;
				 }
			 })
			 $('#eventName').bind('input propertychange',function(){ //添加监听input值的改变事件
				   var tvalmum;
				     //统计input输入字段的长度
				   tvalnum = $(this).val().length;
				   //如果大于8个字直接进行字符串截取
				   if(tvalnum>50){
				     var tval = $(this).val();        
				     tval = tval.substring(0,50);        
				     $(this).val(tval);
				     alert('最多只能输入50个字符！'); 
				   }
				   
				}); 
		})
	</script>
</body>
</html>