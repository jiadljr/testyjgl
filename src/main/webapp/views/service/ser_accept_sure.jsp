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
	<script src="<%=request.getContextPath()%>/js/event_agency_list.js"></script>
	<style>	
	.box_shadow{box-shadow:0 0 5px rgba(0,0,0,.3);}
	i{font-style:normal;}
	.text_hide{overflow: hidden;
text-overflow:ellipsis;
white-space: nowrap;}
	.sureMes{ width:100%; height:92%;min-height:525px;}
	.sureMes .sureMes_left{position:relative;width:66%; height:100%; margin:0.5% 0.3%;border:1px solid #e2e0e0;float:left; }
	.sureMes .sureMes_left .sureMes_tit{text-indent:1.5em; width:100%; height:40px; line-height:40px; font-size:22px;color:#347fce;border-bottom:1px solid #b0ccf3; }
	.sureMes .sureMes_left #sureTab{width:90%; margin:10px auto;}
	.sureMes .sureMes_left #sureTab ul{ width:20%; float:left;}
	.sureMes .sureMes_left #sureTab ul li{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;background:#f9f9f9; box-sizing:border-box; text-align:center;line-height:40px; width:100%; height:40px; border:1px  solid #ccc; position:relative;}
	.sureMes .sureMes_left #sureTab ul .cur{background:#fff;}
	.sureMes .sureMes_left #sureTab ul .cur span{height:38px; width:5px ;display:block; position:absolute;right:-2px; top:0;background:#fff;}
	.sureMes .sureMes_left #sureTab ul .cur i{width:2px; height:38px; position:absolute;left:-1px;background:#41b4f1;}
	.sureMes .sureMes_left #sureTab .tabItem{display:none;padding-top:10px;box-sizing:border-box; width:79%; float:left; border:1px solid #ccc;margin-bottom:20px;}
	.text_hidden{display:none;  }
	.sureMes .sureMes_left .form-group{width:90%;}
	.sureMes .sureMes_left .form-group label{text-align:right;}
	 .btnlist{ width:100%; text-align:right; position:absolute;bottom:20px;}
	 .btnlist .btn{ color:#fff;padding:6px 14px; margin:0 15px; border-radius:6px;line-height:1;}
	.btnlist .btnClose{ background:#e2614e;}
	.sureMes .sureMes_right{ width:32%;height:100%; float:right; margin:0.5% 0.3%;float:left;}
	.sureMes .sureMes_right .sureMesR_top,.sureMes .sureMes_right .sureMesR_bot{ width:100%; height:49%;}
	.sureMes .sureMes_right .sureMesR_bot{margin-top:2%;}
	.sureMes .sureMes_right .sureMes_right_tit{ width:100%; font-size:14px;color:#f7f7f7;height: 35px;line-height: 35px;background:#41b4f1;position: relative;}
	.sureMes .sureMes_right .sureMes_right_tit::before{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:2;width: 0;height: 0;border-left:35px solid #fff;border-bottom:35px solid transparent;}
	.sureMes .sureMes_right .sureMes_right_tit::after{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:1;width: 0;height: 0;border-bottom:35px solid #3aa2d8;border-left:35px solid transparent;box-shadow:3px 3px 2px rgba(0,0,0,.3)}
	.sureMes .sureMes_right .sureMes_right_tit span{position:absolute;left:-5px;top:-5px ;width:40px; height:40px; display:block;background:#fff;}
	.sureMes .sureMes_right .suerMes_list{ width:100%; height:30px; line-height:30px;}
	.sureMes .sureMes_right .suerMes_list label{ margin:0;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;float:left; width:34%; text-indent: 1em; display:inline-block;text-align:right;}
	.sureMes .sureMes_right .suerMes_list span{ float:left; width:65%; display:inline-block;}
	.sureMes .sureMes_right .suerMes_last{ height:42px;}
	.sureMes .sureMes_right .suerMes_last textarea{ float:left; width:59%;height:40px; line-height:20px; border-radius:3px;}
	.sureMes .sureMes_right .accDept{width:100%; height:30px; line-height:30px; }
	.sureMes .sureMes_right .accDept span{width:25%; hieght:30px; line-height:30px;font-size:12px;text-align:right;float:left;display:inline-block;}
	.sureMes .sureMes_right .accDept i{font-style:normal; width:74%;hieght:30px; line-height:30px;font-size:12px;text-align:left; float:right;display:inline-block;}
	.sureMes .sureMes_right .accDept_last textarea{width:60%; height:100px; float:left;margin-left:1%;}
		
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
	<div class="sureMes">
		<div class="sureMes_left box_shadow">
			<p class="sureMes_tit">确认信息</p>
        	<input type="hidden" id="startTime" name="startTime" value="${startTime }">
			<input type="hidden" id="endTime" name="endTime" value="${endTime }">
			<input type="hidden" id="contact" name="contact" value="${contact }">
			<input type="hidden" id="idDept" name="idDept" value="${idDept }">
			<input type="hidden" id="eventStatus" name="eventStatus" value="${eventStatus }">
			<input type="hidden" id="front" name="front" value="${front }"/>
			<form role="form" class="form-horizontal" id="form1">
				<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
				<input type="hidden" name="eventId" value="${idEvent }"/>
				<input type="hidden" name="checkId" value="${checkId }"/>
				<input type="hidden" name="eventCode" value="${eventOne.event_code }"/>
				<input type="hidden" name="eventName" value="${eventOne.event_name }"/>
				<input type="hidden" id="dealId" name="dealId" value="${dealIdList }">
				<input type="hidden" id="fileIdList" name="fileIdList" value="${fileIdList }">
				<input type="hidden" name="token" value="${token}" />
					<div id="sureTab">
						<ul class="tabList">
					
							<c:forEach items="${SolveList }" var="solve">
								<li>${solve.dealName }<i></i><span></span></li>
							</c:forEach>
						</ul>
						
						<c:forEach items="${SolveList }" var="solve">
						<div class="tabItem">
							<div class="form-group">												
							
									<label for="eventService" class="col-sm-3">岗位:</label>
									<p class="col-sm-4">${solve.dealPost }</p>
						
									<label for="eventService" class="col-sm-2">处理人:</label>
									<p  class="col-sm-3 ">${solve.dealName }</p>
								
							</div>
							<div class="form-group">
								<label for="eventPriority" class="col-sm-3">处理时间:</label>
								<p class="col-sm-9">${solve.dateDeal }</p>
							</div>
							<div class="form-group">									
								<label for="eventLevel" class="col-sm-3 pull-left">涉及资产:</label>
								<div class="col-sm-7 pull-left text_hide">
									<c:if test="${solve.assetsName == ''}">无</c:if>
									<c:if test="${solve.assetsName != ''}">${solve.assetsName }</c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="acceptDesc" class="col-sm-3">处理描述:</label>
								<div class="col-sm-7">
									<textarea class="form-control" id="acceptDesc" name="acceptDesc" data-message="*事件描述不得为空！" disabled="disabled">${solve.dealDesc }</textarea>
									<input type="hidden" id="acceptDesc_${solve.id }" name="acceptDesc_${solve.id }" value="${solve.dealDesc }">
								</div>
							</div>
							 <input type="hidden" id="eventCause_${solve.id }" name="eventCause_${solve.id }" value="${solve.eventCause }">
							<div class="form-group">
								<label for="acceptDesc" class="col-sm-3">附件：</label>
								<div class="col-sm-9" style="height:40px; overflow:auto;">
									<c:if test="${solve.files == null}"><p class="col-sm-9">无<p></c:if>
									<c:if test="${solve.files != null}">
										<c:forEach items="${solve.files }" var="file">
											<input type="hidden" id="fileId_${file.fileId }" name="fileId_${file.fileId }" value="${file.fileId }">
											<p class="col-sm-12" >
												<a href="#" onclick="downFile(${file.fileId })">${file.fileName }</a>
											</p>
										</c:forEach>
									</c:if>
								</div>
								
								
							</div>
							<div class="form-group">
								<label for="acceptDesc" class="col-sm-3">处理结果：</label>
								<p class="col-sm-9">
									<c:if test="${solve.dealStatus == 2 }">解决</c:if>
									<c:if test="${solve.dealStatus == 3 }">转派至${solve.reinPost }:${solve.reinName }</c:if>
								</p>
							</div>
						</div>
					</c:forEach>						
				</div>					
					
				<!-- <div class="form-group">
					<label for="acceptDesc" class="col-sm-3">结果：</label>
					<div class="col-sm-4">
						<input type="radio" name="result" id="result" value="1" checked >通过												
					</div>
					<div class="col-sm-4">												
						<input type="radio" name="result" id="result" value="0">重新处理
					</div>
				</div> 	
				<div class="form-group">
					<label for="acceptDesc" class="col-sm-3">意见：</label>
					<div class="col-sm-9">
						<textarea class="form-control error_null" id="sureDesc" name="sureDesc" data-message="*意见不得为空！"></textarea>
					</div>
				</div>
				<div class="btnlist">
					<a class="btn btn-primary btn-sm" id="addSure" style="margin-left:10%">确定</a>
					<a class="btn btn-sm btnClose" onclick="cancel()">取消</a>
				</div>-->	
				<input type="hidden" id="eventDesc" name="eventDesc" value="${eventOne.event_desc}">
			    <input type="hidden" id="eventLevel" name="eventLevel" value="${eventOne.event_level }">		
			</form>					
		</div>
		<div class="sureMes_right">
			<div class="sureMesR_top box_shadow">
				<p class="sureMes_right_tit text-center">申告信息<span></span></p>
				<p class="suerMes_list">
					<label>申告部门:　</label><span class="text_hide">${eventOne.dept_name}</span>
				</p>
				<p class="suerMes_list">
					<label>申告人:　</label><span class="text_hide">${eventOne.aplly_name }</span>
				</p>
				<p class="suerMes_list">
					<label>联系方式:　</label><span class="text_hide">${eventOne.event_contact}</span>
				</p>
				<p class="suerMes_list">
					<label>申告时间:　</label><span class="text_hide" ><fmt:formatDate value="${eventOne.date_create}" pattern="yyyy/MM/dd HH:mm:ss" /></span>
				</p>
				<p class="suerMes_list ">
					<label>事件标题:　</label><span class="text_hide">${eventOne.event_name}</span>
				</p>
				<p class="suerMes_list">
					<label>事件描述:　</label>
					<textarea disabled="disabled" style=" font-size:14px; color:#555; background:#fff; border:0 solid #fff;padding:0; outline:none; vertical-align:top; " >${eventOne.event_desc}</textarea>
				</p>
			</div>
			<div class="sureMesR_bot box_shadow">
				<p class="sureMes_right_tit text-center" >受理信息<span></span></p>
				<p class="suerMes_list">
					<label>受理人:　</label><span class="text_hide">${eventOne.accept_name }</span>
				
				</p>
				<p class="suerMes_list">
					<label>受理时间:　</label><span class="text_hide"><fmt:formatDate value="${eventOne.date_accept}" pattern="yyyy/MM/dd HH:mm:ss" /></span>
				</p>
				<p class="suerMes_list">
					<label>事件等级:　</label><span class="text_hide">	<c:if test="${eventOne.event_level == 1}">一级事件</c:if>
								<c:if test="${eventOne.event_level == 2}">二级事件</c:if>
								<c:if test="${eventOne.event_level == 3}">三级事件</c:if>
								<c:if test="${eventOne.event_level == 4}">四级事件</c:if></span>
				</p>
				<p class="suerMes_list"><label>事件源:　</label><span class="text_hide">${eventOne.event_source }</span></p>
				<p class="suerMes_list"><label>服务类型:　</label><span class="text_hide">${eventOne.service_name }</span></p>
				<p class="suerMes_list"><label>优先级:　</label><span class="text_hide"ss><c:if test="${eventOne.event_priority == 1}">高</c:if><c:if test="${eventOne.event_priority == 2}">中</c:if><c:if test="${eventOne.event_priority == 3}">低</c:if></span>									</p>
			</div>
		</div>
	</div>
</div>      	
		
		<!--  <div class="form-group">
			<label for="acceptDesc" class="col-sm-3">产生原因：</label>
			<div class="col-sm-9">
				<textarea class="form-control" disabled="disabled">${solve.eventCause }</textarea>
			</div>
		</div>-->
		
											
											
								
										
											
					
				
			
		
	<script type="text/javascript">
		$(function(){
			if($('#sureTab .tabList li').length<='1'){
				$('#sureTab .tabList').hide();
				$('#sureTab').css({'width':'100%'});
				$('#sureTab .tabItem').css({'border':'none','width':'100%','display':'block','margin':'0'})
				$('.text_hidden').css({'display':'block'});
			}
			$("#sureTab .tabItem:eq(0)").show();
			$("#sureTab .tabList li:eq(0)").addClass('cur');
		   	$("#sureTab .tabList li").click(function(){
		        $("#sureTab .tabList li").eq($(this).index()).addClass("cur").siblings().removeClass('cur');
				$("#sureTab .tabItem").hide().eq($(this).index()).show();
	        });
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