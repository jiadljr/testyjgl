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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/css/layui.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/plugins/layui/layui.js" > </script>
	
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select/jquery.selectseach.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/event_agency_list.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
	<style>	
		.layui-layer-page .layui-layer-title {background: #3b85c7 !important;color: #fff !important;}
		.text_hide{overflow: hidden;text-overflow:ellipsis;white-space: nowrap;}	
		.drFileName{ width:65% ; float:left;}
		.drDel{display:inline-block; float:right; text-align:right; color:red;}
		.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.box_shadow{box-shadow:0 0 5px rgba(0,0,0,.3);}
		.dealMes{ width:100%; height:92%;min-height:525px;}
		.dealMes .dealMes_left{position:relative;width:66%; height:100%; margin:0.5% 0.3%;border:1px solid #e2e0e0;float:left; }
		.dealMes .dealMes_left .dealMesL_tit{text-indent:1.5em; width:100%; height:40px; line-height:40px; font-size:22px;color:#347fce;border-bottom:1px solid #b0ccf3; }
		.dealMes .dealMes_left form{ padding-top:50px; }
		.dealMes .dealMes_left .form-group{width:90%;}
		.dealMes .dealMes_left .form-group label{text-align:right;}
		.btnlist{ width:100%; text-align:right; position:absolute;bottom:20px;}
	 .btnlist .btn{ color:#fff;padding:6px 14px; margin:0 15px; border-radius:6px;line-height:1;}
	.btnlist .btnClose{ background:#e2614e;}
	.btnList{ text-align:right;margin-bottom:10px;}
	.btnList .btn{ color:#fff;padding:4px 10px; margin-right:15px; border-radius:3px;line-height:1;}
	
	.dealMes .dealMes_right{ /*border-radius:5px; border:1px solid #ccc; */box-sizing: border-box; width:32%;height:100%; float:right; margin:0.5% 0.3%;float:left;border-top:0px;}
		.dealMes .dealMes_right .dealMesR_top{width:90%; min-height:220px ; margin:2% 0 0 5%; padding-bottom:10px; float:left;}
		.dealMes .dealMes_right .dealMesR_bot{ width:90%; min-height:180px;margin:2% 0 2% 5%;padding-bottom:10px; float:left;}
		.dealMes .dealMes_right .dealMesR_mid{width:90%; min-height:140px;margin:2% 0 0 5%; padding-bottom:10px; float:left;}	
		.dealMes .dealMes_right .dealMesR_news{width:90%; min-height:100px;margin:2% 0 2% 5%; padding-bottom:10px; float:left;}	
		
		.dealMes .dealMes_right .dealMes_right_tit{ width:100%; font-size:14px;color:#f7f7f7;height: 35px;line-height: 35px;background:#41b4f1;position: relative;}
		.dealMes .dealMes_right .dealMes_right_tit::before{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:2;width: 0;height: 0;border-left:35px solid #fff;border-bottom:35px solid transparent;}
		.dealMes .dealMes_right .dealMes_right_tit::after{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:1;width: 0;height: 0;border-bottom:35px solid #3aa2d8;border-left:35px solid transparent;box-shadow:3px 3px 2px rgba(0,0,0,.3)}
		.dealMes .dealMes_right .dealMes_right_tit span{position:absolute;left:-5px;top:-5px ;width:40px; height:40px; display:block;background:#fff;}
		.dealMes .dealMes_right .dealMes_con{width:100%; height:24px; line-height:24px; }
		.dealMes .dealMes_right .dealMes_con label{margin:0; width:25%; height:20px; text-align:right; line-height:20px;font-size:12px;float:left;display:inline-block;}
		.dealMes .dealMes_right .dealMes_con span{font-style:normal; width:64%;height:20px; line-height:20px;font-size:12px;text-align:left; float:left;display:inline-block;}
		.mRwell-right-con,.dealMes_con{height:20px; line-height:30px;}
		.kb_search_list{ width:100%; heigth:36px; float:left;}
		.kb_search_list li{  box-sizing: border-box; float:left;cursor:pointer; width:25%;text-align:center; border:1px solid #ccc; height:36px;line-height:40px; border-radius:5px 5px 0 0;border-bottom:0px ;}
		.kb_search_list .nav_on{ background:url("<%=request.getContextPath()%>/images/tab_list.png"); border-bottom:0px;}
		.deal_on{display:none; float:left; overflow-y:auto; width:100%; border:1px solid #ccc; border-radius:0 5px 5px 5px;}
		.table > tbody > tr > td{border-top:0px; }
		#PageList{text-align:center; width:100%; float:left; }
		#kbEventDiv{ width:100%; height:80%; margin: 0 auto; min-height:380px; }
		#kbEventDiv li{ width:100%; float:left; height:10%;min-height:40px; }
		#kbEventDiv li a{ height:40px; color:red; line-height:40px; width:80%; margin-left:10%; float:left; font-size:16px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
		.drFileItem{ margin-top:3px; width:100%;height:20px;}
			.QK_arrow{ border-top:6px #333 solid; border-bottom:2px solid #fff;border-left:3px solid #fff;border-right:3px solid #fff; display:block;width:4px;height:12px; position:absolute; right:23px; top:12px; z-index:9999; }
		
	</style>	
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">处理信息</li>
				</ol>
			</div>
			<div class="dealMes">
				<div class="dealMes_left box_shadow">
					<p class="dealMesL_tit" >处理信息</p>
					<form role="form" class="form-horizontal" id="form1" name="form1" enctype="multipart/form-data" method="post">
						<input type="hidden" id="startTime" name="startTime" value="${startTime }">
						<input type="hidden" id="endTime" name="endTime" value="${endTime }">
						<input type="hidden" id="contact" name="contact" value="${contact }">
						<input type="hidden" id="idDept" name="idDept" value="${idDept }">
						<input type="hidden" id="eventStatus" name="eventStatus" value="${eventStatus }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                 				<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                 				<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                 				<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
                 				<input type="hidden" id="oper" name="oper" value="${oper }">
                 				<input type="hidden" name="token" value="${token}" />
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">事件标题<span style="color:#fff;font-size:16px;">*</span>：</label>
							<input type="hidden" value="${selectEventInfoAll.id }" name="idEvent" id="idEvent">
							<input type="hidden" value="${selectEventInfoAll.deal_id }" name="id" id="id">
							<input type="hidden" value="${selectEventInfoAll.event_name  }" name="eventName" id="eventName">
							<input type="hidden" value="${selectEventInfoAll.event_status }" id="eventStatus" name="eventStatus">
							<input type="hidden" value="${selectEventInfoAll.event_code }" id="eventCode" name="eventCode">
							<p class="col-sm-9">${selectEventInfoAll.event_name}</p>
						</div>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">处理结果<span style="color:#fff;font-size:16px;">*</span>：</label>
							<div class="col-sm-9">		
								<div class="col-sm-3">
									<input type="radio" name="dealResult" value="2" checked>&nbsp;&nbsp;解决
								</div>
								<div class="col-sm-3">
									<input type="radio" name="dealResult" value="3">&nbsp;&nbsp;转派									
								</div>										
							</div>
						</div>
						<div id="propertyHide">
							<div class="form-group">
								<label for="deptLevel" class="col-sm-3">涉及资产<span style="color:#fff;font-size:16px;">*</span>：</label>
								<div class="col-sm-9">
									<input type="hidden" id="assets_id" name="assets_id" value="${assetsId }"/>
									<input type="text" id="property"  name=property onclick="showAssets()" value="${assetsName }" class="form-control "readonly placeholder="请选择涉及资产">
								</div>
							</div>
						</div>
						<div class="an-sol" style="display:none">
							<div class="form-group">
								<label class="col-sm-3">处理人<span style="color:red;font-size:16px;">*</span>：</label>
								<div class="col-sm-9" style="position:relative;">
									<span class="QK_arrow"></span>
									
									<input type="text" class="form-control error_null selInp"  placeholder="请选择人员" readonly style="background:#fff;">
									<select class="form-control error_null" id="userDeal" style="display:none; position:absolute;top:34px;left:15px;z-index:10;background:#fff !important;" name="userDeal" size="5">
									<option value="" selected="selected">请选择人员</option>
										<c:forEach var = "sfm" items = "${userByRoleType}">
											<option  value="${sfm.id }">${sfm.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group" id="dealReason">
							<label for="eventCause" class="col-sm-3" id="txt_l_rem">产生原因<span style="color:#fff;font-size:16px;">*</span>：</label>
							<div class="col-sm-9">
								<textarea rows="3"  maxlength="255" onchange="this.value=this.value.substring(0, 255)" onkeydown="this.value=this.value.substring(0, 255)" onkeyup="this.value=this.value.substring(0, 255)"  class="form-control " id="eventCause" name="eventCause">${selectEventInfoAll.event_cause }</textarea>
							</div>
						</div>
						<div class="form-group" id="dealDiv">
							<label for="remark" class="col-sm-3" id="txt_l_rem">处理过程<span style="color:red;font-size:16px;">*</span>：</label>
							<div class="col-sm-9">
								<textarea class="form-control error_null1" id="dealRemark" name="dealRemark" data-message="*处理过程不得为空！">${selectEventInfoAll.deal_desc }</textarea>
							</div>
						</div>
						<div class="form-group" id="redeployDiv" style="display: none;">
							<label for="remark" class="col-sm-3" id="txt_l_rem">转派原因<span style="color:red;font-size:16px;">*</span>：</label>
							<div class="col-sm-9">
								<textarea class="form-control error_null2" id="redeployRemark" name="redeployRemark" data-message="*转派原因不得为空！"></textarea>
							</div>
						</div>
						<div id="fileNameHide">
							<div class="form-group" id="file-group">
								<label for="deptLevel" class="col-sm-3">附件上传<span style="color:#fff;font-size:16px;">*</span>：</label>
								<div class="col-sm-9">
									<a class="text-center btn btn_blue " style="padding:0 12px;" id="butFile" >浏览</a>
								
									<div id="app" style="display: none;">
									</div>
									
								</div>
								<div id="fixMail" style=" height:60px; margin-left:25%; width:75%; overflow:auto;">
									
									<c:forEach items="${files }" var="file">
										<div class="col-sm-11 drFileItem" id="${file.fileId }">
											<a href="#" style="color:blue" class="drFileNames" onclick="downFile(${file.fileId })">${file.fileName }</a> <a href="#" style="color:red;float:right" onclick="delFile(${file.fileId },${selectEventInfoAll.id })">删除</a>
										</div>
									</c:forEach>
									<div id="text" class="col-sm-11">
									</div>
								</div>
							</div>
						</div>
						<input type="hidden" id="eventLevel" name="eventLevel" value="${selectEventInfoAll.event_level }">
						<input type="hidden" id="eventDesc" name="eventDesc" value="${selectEventInfoAll.event_desc }">
						<input type="hidden" id="eventDesc" name="eventDesc" value="${selectEventInfoAll.event_desc }">
						<input type="hidden" id="eventCode">
						<div class="btnlist">
							<a class="btn btn-primary btn-sm" id="addConfirm" style="margin-left:10%">确定</a>
							<a class="btn btn-sm btnClose" onclick="cancel()">取消</a>
						</div>	
					</form>			
				</div>
				
				<div class="dealMes_right">
					<ul class="kb_search_list">
						<li class="li nav_on">事件信息</li>
						<li class="li">参考知识</li>
					
					</ul>
					<div class="deal_on" style="display:block;">
				   		
						<div class="dealMesR_top box_shadow">
							<p class="dealMes_right_tit text-center">申告信息 <span></span></p>
							<p class="dealMes_con" style="margin-top:10px">
	              				<label >申告部门：</label>
	              				<span class="text_hide">${deptName.name }</span>
	                		</p>
	                		<p class="dealMes_con">
	                			<label >申告人：</label>
	                			<span class="text_hide">${userApply.name }</span>
	                		</p>
	                		<p class="dealMes_con">
	                			<label>联系方式：</label>
	                			<span class="text_hide" >${selectEventInfoAll.event_contact }</span>
	                		</p>
	                		<p class="dealMes_con">
	                			<label>申告时间：</label>
	                			<span class="text_hide"><fmt:formatDate value="${selectEventInfoAll.date_create }" pattern="yyyy/MM/dd HH:mm:ss" /></span>
	                		</p>
	                	
	                		
							
	                		<div class="form-group" style="margin-top:0px;">
	                			<label style="vertical-align:top;float:left; width:25%; font-size:12px; text-align:right;">事件描述：</label>
	                			<div style="width:60%; float:left;" >
	                				${selectEventInfoAll.event_desc }
	                			</div>
	                		</div>
						</div>
						<div class="dealMesR_bot box_shadow">
							<p class="dealMes_right_tit text-center">受理信息 <span></span></p>	                	
	                		<p class="dealMes_con" style="margin-top:10px">
	                			<label >受理人：</label>
	                			<span class="text_hide" >${userAccept.name }</span>
	                		</p>                		
	                		<p class="dealMes_con">
	                			<label>受理时间：</label>
	                			<span class="text_hide" ><fmt:formatDate value="${selectEventInfoAll.date_accept }" pattern="yyyy/MM/dd HH:mm:ss" /></span>
	                		</p>
	                		<p class="dealMes_con">
	                			<label >事件等级：</label>
	                			<span class="text_hide">
	                			<c:if test="${selectEventInfoAll.event_level eq  1}">一级</c:if>
	                            <c:if test="${selectEventInfoAll.event_level eq  2}">二级</c:if>
		                        <c:if test="${selectEventInfoAll.event_level eq  3}">三级</c:if>
	                            <c:if test="${selectEventInfoAll.event_level eq  4}">四级</c:if>
	                            </span>
	                		</p>
	                		<p class="dealMes_con">
	                			<label >事件源：</label>
	                			<input type="hidden" id="sourceId" value="${selectEventInfoAll.idSource }">
	                			<span class="text_hide">${selectEventInfoAll.event_source }</span>
	                		</p>
	                		<p class="dealMes_con">
	                			<label >服务类型：</label>
	                			<span class="text_hide">${selectEventInfoAll.service_name }</span>
	                		</p>
	                		<div class="form-group" style=" float:left; width:100%;margin-top:0px;">
	                			<label style="vertical-align:top;float:left; width:25%; text-align:right;font-size:12px; "  >受理描述：</label>
	                			<div style="float:left;width:60%; ">${selectEventInfoAll.accept_desc }</div>
	                		</div>
	               		</div>
	               		<div class="dealMesR_news box_shadow" id="movePeopleDiv">
							<p class="dealMes_right_tit text-center">转派信息 <span></span></p>	                	
	                	
	                	
	                		<p class="dealMes_con" style="margin-top:10px">
	                			<label>转派人：</label>
	                			<span>${dealDesc.extend1 }</span>
	                		</p>
							
	                		<p class="dealMes_con">
	                			<label>转派时间：</label>
	                			<span ><fmt:formatDate value="${dealDesc.dateDeal }" pattern="yyyy-MM-dd HH:mm:ss" /></span>
	                		</p>
	                		<div class="form-group" style="margin-top:0px;">
	                			<label style="vertical-align:top;float:left; width:25%; font-size:12px; text-align:right;">转派原因 ：</label>
	                			<div style="width:60%; float:left; font-size:12px;" id="movePeople">${dealDesc.dealDesc}</div>
	                		</div>
	                		
						</div>
	               		<div class="dealMesR_mid box_shadow" id="mRwellDiv">
							<p class="dealMes_right_tit text-center">确认信息 <span></span></p>	                	
	                	
	                	
	                		<p class="dealMes_con" style="margin-top:10px">
	                			<label>确认结果：</label>
	                			<span >重新处理</span>
	                		</p>
	
	                		<div class="dealMes_con" style="margin-top:10px;">
	                			<label  style="vertical-align:top;float:left;">意见：</label>
	                			<div class="mRwell-con-right" style="float:left; line-height:0;font-size:0;"><textarea disabled="disabled" class="form-control"style="width:100%;vertical-align:top;background:#fff;">${selectAnewDeal.remark }</textarea></div>
	                		</div>
						</div>
						
					</div>
					<div class="deal_on" >
						<div class="kb_serach "style="margin-top:10px;" >
							<label class="col-sm-3 text-center" style="line-height:34px;">知识搜索</label>
							<div class="col-sm-6">
								<input type="text" id="kbEvent" name="kbEvent" value="${selectEventInfoAll.event_source }" class="form-control">
							</div>
							<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
							
						</div>
						<div id="bankMes" style="width:450px; margin:0 auto;"></div>
						<ul id="kbEventDiv">
						</ul>
						<div id="PageList"></div>
						<div id="kbEventFileDiv" style="display:none;">
						<table class="table table-bordered  text-center" style="width:90%; margin:10px auto;">
							<tbody>
								<tr>
									<td width="160px">事件名称</td>
									<td class="nostr" id="EventName"></td>
								</tr>
								<tr>
									<td width="160px;">事件描述</td>
									<td class="nostr" id="EventDesc"></td>
								</tr>
								<tr>
									<td width="160px">解决方案</td>
									<td class="nostr" id="EventPlan"></td>
								</tr>
								<tr>
									<td width="160px">关联文件</td>
									<td class="nostr" id="EventFile"></td>
								</tr>
							</tbody>
						
						
						</table>
						
						<div class="btnList">
							<a class="btn btn-sm btn_blue" id="btnAddkb">引用</a>
							<a class="btn btn-sm btn_red" href="javascript:closePage();">取消</a>
						
						</div>
							
						</div>
					</div>
				</div>		
			</div>

			
                
			<div id="tipsBoxAlert" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
				
			<div class="mRpage" id="page2"></div>
		</div>
	<script type="text/javascript">
		$("#userDeal").width($("#property").width())
		$(".selInp").focus(function(){
			$("#userDeal").show();
			$("#userDeal").click();
			
		})
		
		$("#userDeal option").click(function(){
			$("#userDeal ").hide();
			$(".selInp").val($("#userDeal option:selected").text()).removeClass("error");
		})
		$("#userDeal").blur(function(){
			$(this).hide();		
		})
	</script>
	<script>
	function closePage(){
		layer.closeAll('page');
	}
	</script>	
	<script type="text/javascript">
		var warpH=$('.dealMes_right').height();
		
		$(".deal_on").css({"height":warpH-36+"px","overflow-y":"auto"});
			
		$(document).ready(function(){
			
			$(".deal_on").eq(0).show();
		    $(".kb_search_list .li").click(function(){
		    $(".kb_search_list .li").eq($(this).index()).addClass("nav_on").siblings().removeClass('nav_on');
			$(".deal_on").hide().eq($(this).index()).show();
			
		    });
		});
</script>
	<script type="text/javascript">
		$(function(){
			 $('.form-control').css('background','#fff')
			//$('#select1').selectseach('/plugins/select/multiselect-hover.gif'); 
			/* $('.search').kuCity(); */
			var a = $("#eventStatus").val();
			if(a == 62){
				$("#mRwellDiv").show();
			
			}else{
				$("#mRwellDiv").hide();
			}
			$("input[name=dealResult]").change(function(){
				if($("input[name=dealResult]:checked").val()=="2"){
					$("#dealDiv").show();
					$("#redeployDiv").hide();
					$(".an-sol").hide();
					$("#dealReason").show();
					$("#propertyHide").show();
					$("#fileNameHide").show();
				}else{
					$("#redeployDiv").show();
					$("#dealReason").hide();
					$("#dealDiv").hide();
					$("#propertyHide").hide();
					$("#fileNameHide").hide();
					$(".an-sol").show();
				}
			})
			$("#addConfirm").click(function(){
				if($("input[name=dealResult]:checked").val()=="2"){
					if ($("#dealRemark").val()=='' || $("#dealRemark").hasClass("error")) {
						$.each($("#dealRemark,#eventCause"),function(){
							if($(this).val()==""){
								var error=$(this).attr("data-message");
								$(this).val(error);
								$(this).addClass("error");
							}
						})
						return false;	
					} 
				}else{
					if($(".selInp").val()==""||$(".selInp").val()=="请选择人员"){
						$(".selInp").addClass("error")
					}else{
						$(".selInp").removeClass("error");
					}
					$(".selInp").focus(function(){
						$(".selInp").removeClass("error")
					})
					
					if ($("#redeployRemark").val()==''||$("#redeployRemark").hasClass("error")||$(".selInp").hasClass("error")) {
					
						$.each($("#redeployRemark"),function(){
							if($(this).val()==""){
								var error=$(this).attr("data-message");
								$(this).val(error);
								$(this).addClass("error");
							}
						})
						return false;	
					} 
				}
				var formData = new FormData($('#form1')[0]); 
				$.ajax({
					type:"post",
					url:getPath()+"/updateDealEnd.do",
					data:formData,
					async: false,
					processData : false,
			        contentType : false,
			        success:function(result){
			        	if(result.up == 1){
				    		var oper = $("#oper").val();
				    		if (oper == "运维人员主页") {
				    			location.href = getPath() + "/goOperations.do?cid=2";
				    			return;
				    		}
				    		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				    		parent.layer.close(index); //再执行关闭 
				    		var did = result.dealResult;
							window.location.href=getPath() + "/toAgencyPage.do?cid="+did;
						}
			        	if(result.result == "error"){
			        		alert(result.alreadyChoose+"已经参与该事件，请重新选择！")
			        	}
			        }
				})
			})
			
			$("#button_search").click(function(){
				selectKbEventName();
			})
			selectKbEventName();
		  })
		  function selectKbEventName(){
			var kbEvent = $("#kbEvent").val();
			   $.ajax({
				   type:"post",
				   url:getPath()+"/selectKbEventCondition.do",
				   data:{"kbEvent":kbEvent},
				   success:function(data){
					   layui.use(['laypage', 'layer'], function(){
						   var laypage = layui.laypage
						   ,layer = layui.layer;
						   laypage.render({
							    elem: 'PageList'
							    ,count: data.length
							    ,limit: 9
							    ,groups:2
							    ,theme: '#1E9FFF'
							    ,jump: function(obj){
							      //模拟渲染
							      document.getElementById('kbEventDiv').innerHTML = function(){
							        var arr = []
							        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
							        layui.each(thisData, function(index, item){
							        	
							        	if(typeof(item.event_name)=="undefined"){
							        		
							        		item.event_name="未命名！！！";
							        	}
							        	
										 arr.push("<li><a href='javascript:;' class='text_hide' id='btnLook' onclick='sure(\""+item.id+"\")' >"+item.event_name+"</a></li>");
							        });
							        return arr.join('');
							      }();
							    }
							  });
					   })
				   }
			   })
		}
		   function sure(id){
				 
			   $.ajax({
				   type:"post",
				   url:getPath()+"/selectKbEventById.do",
				   data:{"id":id},
				   success:function(data){
				
					   var kbEvent = data.selectKbEventById;
					   var kbEventFile = data.selectKbEventFileById;
					   $("#EventName").html(kbEvent.eventName);
					   $("#EventDesc").html(kbEvent.eventDesc);
					   $("#EventPlan").html(kbEvent.eventResolvent);
					   var PD=kbEvent.eventResolvent;
					   $("#btnAddkb").attr("onclick","pullID(\""+PD+"\")");
					   var str="";
					   for(var i = 0;i<kbEventFile.length;i++){
						    
							str += "<a href='#' style='display:inline-block;' onclick='downFile(\""+kbEventFile[i].fileId+"\")'>"+kbEventFile[i].fileName+"</a><br>"
								
					   }
						$("#EventFile").html(str)	
					   layui.use('layer', function(){
					  		var layer = layui.layer;
					  
					  			layer.open({
						    		type:1,
						    		title:'查看详情',
						   	 		shadeClose: false,
						   			area: ['652px', '260px'],
						    		content: $('#kbEventFileDiv').html()
						  		})
						
							});
					 
					 
				   }
			   })
		   }
		
		   function pullID(PD){
			   	if($("#dealRemark").hasClass("error")){
			   		$("#dealRemark").removeClass("error");
			   		$("#dealRemark").val(PD+'\r\n');
			   	}else{
			   	   	document.getElementById("dealRemark").value+=PD+'\r\n';					 
			   	}
			   	layer.closeAll('page');
		   }
		   $(function(){
		        var i=0;
		        var tpl="";
		        $("#butFile").click(function() {
		            var index=i++;
		         
		            tpl = "<input type='file' name='file" + index + "' id='file" + index + "'/><br>";
		            $("#app").append(tpl);
		            $('#file' + index + '').click();
		            $('#file' + index + '').change(function () {
		                var file= document.getElementById("file"+index+"").files[0];
		             
		             	var tpFile="<p class='drFileItem' id='del"+index+"'><a class='drFileName drFileNames' href='javascript:;'>"+file.name+"</a><a class='drDel' href='javascript:remove("+index+");' >删除</a></p>"
		                $("#text").append(tpFile)
		                var len=$("#fixMail .drFileItem").length;
		    			var drFileNameK='';
		    			for(var ks=0;ks<len-1;ks++){		    				
		    				if(len=='1'){
		    					break;
		    				}
		    				drFileNameK=$(".drFileNames").eq(ks).text();
		    				 if(file.name==drFileNameK){
		    				 	$('#file' + index + '').remove();
		    				 	$('#del'+index+'').remove();
		    				 }

		    			}
		            })
		        })

		    })

		    function remove(index){
		        $('#file' + index + '').remove();
		        $('#del'+index+'').remove();
		    }
	</script>

	<script>
		$(function(){
		
       if($("#movePeople").text()==""){		
				$("#movePeopleDiv").hide();
			}
			
		})

	</script>
</body>
</html>