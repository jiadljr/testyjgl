<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html >
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/event_agency_list.js"></script>
	
	<style>	
	.text_hide{overflow: hidden;
text-overflow:ellipsis;
white-space: nowrap;}	
.QK_search{ width:100%; height:30px; position:relative;}
#searchTree{ height:20px; border:1px solid #ccc; line-height:20px; margin:0; width:240px;}
#serviceSidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
#sourceSidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
.tree_list{height:154px !important; overflow:auto;}
#searchMenu{margin-bottom:5px; position:relative;}
 .accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
.error{border-color:red !important;}
.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
#one{width:200px; height:180px; float:left}
#two{width:50px; height:180px; float:left}
#three{width:200px; height:180px; float:left}
	.box_shadow{box-shadow:0 0 5px rgba(0,0,0,.3);}
	.accMes{ width:100%; height:92%;min-height:525px;}
	.accMes .accMes_left{position:relative;width:66%; height:100%; margin:0.5% 0.3%;border:1px solid #e2e0e0;float:left; }
	.accMes .accMes_left .accMesL_tit{text-indent:1.5em; width:100%; height:40px; line-height:40px; font-size:22px;color:#347fce;border-bottom:1px solid #b0ccf3; }
	.accMes .accMes_left form{ padding-top:50px; }
	.accMes .accMes_left .form-group{width:90%;}
	.accMes .accMes_left .form-group label{text-align:right;}
	 .btnlist{ width:100%; text-align:right; position:absolute;bottom:20px;}
	 .btnlist .btn{ color:#fff;padding:6px 14px; margin:0 15px; border-radius:6px;line-height:1;}
	.btnlist .btnClose{ background:#e2614e;}
	.accMes .accMes_right{ width:32%;height:100%; float:right; margin:0.5% 0.3%;border:1px solid #e2e0e0;float:left;}
	.accMes .accMes_right .accMes_right_tit{ width:100%; font-size:14px;color:#f7f7f7;height: 35px;line-height: 35px;background:#41b4f1;position: relative;}
	.accMes .accMes_right .accMes_right_tit::before{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:2;width: 0;height: 0;border-left:35px solid #fff;border-bottom:35px solid transparent;}
	.accMes .accMes_right .accMes_right_tit::after{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:1;width: 0;height: 0;border-bottom:35px solid #3aa2d8;border-left:35px solid transparent;box-shadow:3px 3px 2px rgba(0,0,0,.3)}
	.accMes .accMes_right .accMes_right_tit span{position:absolute;left:-5px;top:-5px ;width:40px; height:40px; display:block;background:#fff;}
	.accMes .accMes_right .accDept{width:100%; height:30px; line-height:30px; }
	.accMes .accMes_right .accDept label{ margin:0; width:30%; hieght:30px; line-height:30px;font-size:12px;text-align:right;float:left;display:inline-block;}
	.accMes .accMes_right .accDept i{font-style:normal; width:60%;hieght:30px; line-height:30px;font-size:12px;text-align:left; float:left;display:inline-block;}
	.accMes .accMes_right .accDept_last textarea{width:60%; height:100px; float:left;}
	.QK_arrow{ border-top:6px #333 solid; border-bottom:2px solid #fff;border-left:3px solid #fff;border-right:3px solid #fff; display:block;width:4px;height:12px; position:absolute; right:23px; top:12px; z-index:9999; }
	.QK_search{ width:100%; height:30px; position:relative;}
	#searchTree{ height:20px; border:1px solid #ccc; line-height:20px; margin:0; width:240px;}
	#serviceSidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
	#sourceSidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
	#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
	.tree_list{height:154px !important; overflow:auto;}
	#searchMenu{margin-bottom:5px; position:relative;}
	 .accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
	.error{border-color:red !important;}
	.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
	#one{width:200px; height:180px; float:left}
	#two{width:50px; height:180px; float:left}
	#three{width:200px; height:180px; float:left}
	
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
			<div class="accMes">
				<div class="accMes_left box_shadow">
					<p class="accMesL_tit">受理信息</p>
					<form role="form" class="form-horizontal" id="form1">
						<input type="hidden" id="startTime" name="startTime" value="${startTime }">
						<input type="hidden" id="endTime" name="endTime" value="${endTime }">
						<input type="hidden" id="contact" name="contact" value="${contact }">
						<input type="hidden" id="idDept" name="idDept" value="${idDept }">
						<input type="hidden" id="eventStatus" name="eventStatus" value="${eventStatus }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
	                    <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
	                    <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
	                    <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
	                    <input type="hidden" id="front" name="front" value="${front }">
						<input type="hidden" name="eventId" id="eventId" value="${eventOne.id }"/>
						<input type="hidden" id="eventStatus" value="${eventOne.eventStatus }"/>
						<input type="hidden" name="token" value="${token}" />
						<input type="hidden" id="eventTs" value="${eventOne.eventTs }">
						<div class="form-group">
							<label for="eventLevel" class="col-sm-3">事件等级<span style="color:#fff;font-size:16px;">*</span>：</label>
							<div class="col-sm-4">
								<select id="eventLevel" name="eventLevel" class="form-control">
									<option value="1">一级事件</option>
									<option value="2">二级事件</option>
									<option value="3">三级事件</option>
									<option value="4" selected>四级事件</option>
								</select>
							</div>
						</div>
							<div class="form-group">
								<label for="sourceName" class="col-sm-3">事件源<span style="color:#fff;font-size:16px;">*</span>：</label>
								<div class="col-sm-4" style="position:relative;">
									<span class="QK_arrow"></span>
									<div class="QK_search">
								<div id="searchMenu">
									<input type="hidden" name="sourceId" id="sourceId" value="" /> 
									<input id="sourceSearchTree" type="text" class="span2 form-control" placeholder="请选择事件源" data-autofocus="autofocus" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showSourceTree()">
								</div> 
								<div id="sourceSidebar">
									<div class="toggleCollapse">
									  <div id="sourceTreeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
											<div class="accordionContent">
												<ul id="b" class="tree treeFolder ">
													<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openSourceTree">事件源</a>
															<ul id="bzmenus" class="source_bzmenus_sub"></ul>
													</li>
												</ul>
											</div>
										</div>
									 </div>
								</div>
							</div>
								</div>
							</div>
							<div class="form-group">
								<label for="eventService" class="col-sm-3">服务类型<span style="color:red;font-size:16px;">*</span>：</label>
								<div class="col-sm-4" style="position:relative;">
								<span class="QK_arrow"></span>
									<div class="QK_search">
								<div id="searchMenu">
									<input type="hidden" id="serviceId" name="serviceId" />
									<input id="serviceSearchTree" type="text" class="span2 form-control" placeholder="请选择服务类型" data-autofocus="autofocus" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showServiceTree()">
								</div> 
								<div id="serviceSidebar" >
									<div class="toggleCollapse">
									  <div id="serviceTreeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
											<div class="accordionContent">
												<ul id="c" class="tree treeFolder ">
													<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openServiceTree">服务类型</a>
															<ul id="bzmenusService" class="service_bzmenus_sub"></ul>
													</li>
												</ul>
											</div>
										</div>
									 </div>
								</div>
							</div>
								</div>
							</div>
							<div class="form-group">
								<label for="eventPriority" class="col-sm-3">优先级别<span style="color:#fff;font-size:16px;">*</span>：</label>
								<div class="col-sm-4">
									<select id="eventPriority" name="eventPriority" class="form-control">
										<option value="1">高</option>
										<option value="2">中</option>
										<option value="3">低</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="idUserDeal" class="col-sm-3">处理人<span style="color:red;font-size:16px;">*</span>：</label>
								<div class="col-sm-4" style="position:relative;">
									<span class="QK_arrow"></span>
									<input type="text" class="form-control error_null selInp" value="" placeholder="请选择人员" readonly style="background:#fff;"/>
									
									<select id="idUserDeal" name="idUserDeal" style="position:absolute; left:15;top:34px; z-index:5; display:none;" size="5" class="form-control">
										<!-- forEach遍历 -->
										<c:forEach items="${userAll}" var="user">
											<option value="${user.id}">${user.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group form_group_txt ">
								<label for="acceptDesc" class="col-sm-3">事件描述<span style="color:red;font-size:16px;">*</span>：</label>
								<div class="col-sm-9">
									<textarea class="form-control error_null" id="acceptDesc" name="acceptDesc" data-message="*事件描述不得为空！"></textarea>
								</div>
							</div>
							<div class="btnlist">
								<a class="btn btn-primary btn-sm" id="addAccept" style="margin-left:10%">确定</a>
								<a class="btn btn-sm btnClose" onclick="cancel()">取消</a>
							</div>
					</form>
				</div>
				<div class="accMes_right box_shadow">
					<p class="accMes_right_tit text-center">申告信息 <span></span></p>
					<p class="accDept">
						<label>申告部门:　</label>
						<i>${eventOne.extend1 }</i>
					</p>
					<p class="accDept">
						<label>申告人:　</label>
						<i class="text_hide">${eventOne.extend2 }</i>
					</p>
					<p class="accDept">
						<label>事件办理人:　</label>
						<i class="text_hide">${eventOne.extend3 }</i>
					</p>
					<p class="accDept">
						<label>联系方式:　</label>
						<i>${eventOne.eventContact }</i>
					</p>
					<p class="accDept">
						<label>申告时间:　</label>
						<i class="text_hide"><fmt:formatDate value="${eventOne.dateCreate }" pattern="yyyy-MM-dd HH:mm:ss" /></i>
					</p>
					<p class="accDept">
						<label>事件标题:　</label>
						<i class="text_hide" title="${eventOne.eventName }">${eventOne.eventName }</i>
					</p>
               		<div class="accDept accDept_last">
               			<label>事件描述:　</label>
               			<textarea disabled="disabled" style=" font-size:14px; color:#555; width:60%;background:#fff; height:54px;border:0 solid #fff;padding:0; outline:none; vertical-align:top; " >${eventOne.eventDesc }</textarea>
          
               		</div>
				</div>
			</div>
                
				<div id="acceptBox" style="position:absolute;width:1px;height:1px; right:10px;bottom:90px; "></div>
			<div class="mRpage" id="page2"></div>
		</div>
	<script type="text/javascript">
		$("#idUserDeal").width($(".selInp").width())
		$(".selInp").focus(function(){
			$("#idUserDeal").show();
			$("#idUserDeal").click();
			
		})
		
		$("#idUserDeal option").click(function(){
			$("#idUserDeal ").hide();
			$(".selInp").val($("#idUserDeal option:selected").text());
		})
		$("#idUserDeal").blur(function(){
			$(this).hide();		
		})
	</script>	
	<script type="text/javascript">
		$(function(){
			jtree_init();
			function jtree_init() {
				var $p = $(document);
				$("ul.tree", $p).jTree();
			}

			$.fn.extend({
				size : function() {
					return this.length;
				}
			})
			//事件源
		$("#sourceSearchTree").keyup(function(){
			if ($("#sourceSearchTree").val() != "") {
				$("#searchTreeClose").show();
				removeSourceLi();
			} else {
				$("#searchTreeClose").hide();
				removeSourceLi();
			}
			$('#b').colExpAll({
				clickType : 'search',
				input : 'sourceSearchTree',
				closeBtn : 'sourceSearchTreeClose',
			});
			removeSourceLi();
		})
		//服务类型
		$("#serviceSearchTree").keyup(function(){
			if ($("#serviceSearchTree").val() != "") {
				$("#searchTreeClose").show();
				removeServiceLi();
			} else {
				$("#searchTreeClose").hide();
				removeServiceLi();
			}
			$('#c').colExpAll({
				clickType : 'search',
				input : 'serviceSearchTree',
				closeBtn : 'serviceSearchTreeClose',
			});
			removeServiceLi();
		})
		
		//事件源
		$("#sourceSearchTree").bind("keydown", function(e) {
			if (e.keyCode == 13) {
				if ($("#sourceSearchTree").val() != "") {
					$("#sourceSearchTreeClose").show();
					removeSourceLi();
				} else {
					$("#sourceSearchTreeClose").hide();
					removeSourceLi();
				}
				$('#b').colExpAll({
					clickType : 'search',
					input : 'sourceSearchTree',
					closeBtn : 'sourceSearchTreeClose',
				});
				removeSourceLi();
			}
		});
		$("#sourceSidebar").hide();
		//事件源
		function removeSourceLi() {
			if($(".typeOne").length>0){
				for (var n = 0; n < $(".typeOne").length; n++) {
					if ($("#sourOne" + n + " li").length == 0) {
						$("#sourOne" + n).remove();
					}
				}
			}
			if($(".typeTwo").length>0){
				for (var m = 0; m < $(".typeTwo").length; m++) {
					if ($("#sourTwo" + m + " li").length == 0) {
						$("#sourTwo" + m).remove();
					}
				}
			}
			
			$("#sourceTreeDiv a").click(function(){
				$("#sourceTreeDiv a").css({"color":"#183152"});
				$(this).css({"color":"red"});
				$("#openSourceTree").css({"color":"#183152"})
				 
			})
		}
		$(function(){
			var url = getPath()+"/selectAssetsTypeForTree.do";
			var typeOne;
			var typeTwo;
			var typeThree;
			$.ajax({
				url:url,
				data:{},
				type:'post',
				async:false,
				success:function(result){
					typeOne = result.assetsTypeOne;
					typeTwo = result.assetsTypeTwo;
					typeThree = result.assetsTypeThree;
					$.each(typeOne,function(i,one){
						var type = "<li  class=\"typeOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"querySourceType("+typeOne[i].id+",\'"+one.name+"\')\">";
							type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"sourOne"+i+"\">";
						$.each(typeTwo,function(j,two){
							if(typeOne[i].id == typeTwo[j].parentId){
								type += "<li class=\"typeTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"querySourceType("+ typeTwo[j].id + ",\'"+two.name+"\')\">";
								type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"sourTwo"+j+"\">";	
								$.each(typeThree,function(k,three){
									if (typeTwo[j].id == typeThree[k].parentId) {
										type += "<li><a autobypy="+three.extend2 +"href=\"#\" onclick=\"querySourceType("+typeThree[k].id+",\'"+three.name+"\')\">";
										type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a>";
									}
								})
								type+="</ul></li>"; 
							}
						})
						type += "</ul></li>"; 
						$(".source_bzmenus_sub").append(type);
						$("#sourceTreeDiv a").click(function(){
							$("#sourceTreeDiv a").css({"color":"#183152"});
							$(this).css({"color":"red"});
							$("#openSourceTree").css({"color":"#183152"})
						})
						 
					})
				}
			})
			removeSourceLi();
			$("#openSourceTree").click();
			$("#openSourceTree").css({"color":"#183152"})
			$("#sourceSearchTree").one("click",function(){
			$.each($(".collapsable"),function(){
				$(this).click();
			})
			$(".last_collapsable").click();
		})
		})
		//服务类型
		$("#serviceSearchTree").bind("keydown", function(e) {
			if (e.keyCode == 13) {
				if ($("#serviceSearchTree").val() != "") {
					$("#serviceSearchTreeClose").show();
					removeServiceLi();
				} else {
					$("#serviceSearchTreeClose").hide();
					removeServiceLi();
				}
				$('#c').colExpAll({
					clickType : 'search',
					input : 'serviceSearchTree',
					closeBtn : 'serviceSearchTreeClose',
				});
				removeServiceLi();
			}
		});
		$("#serviceSidebar").hide();
		//服务类型
		function removeServiceLi() {
			if($(".serviceOne").length>0){
				for (var n = 0; n < $(".serviceOne").length; n++) {
					if ($("#serOne" + n + " li").length == 0) {
						$("#serOne" + n).remove();
					}
				}
			}
			if($(".serviceTwo").length>0){
				for (var m = 0; m < $(".serviceTwo").length; m++) {
					if ($("#serTwo" + m + " li").length == 0) {
						$("#serTwo" + m).remove();
					}
				}
			}
			
			$("#serviceTreeDiv a").click(function(){
				$("#serviceTreeDiv a").css({"color":"#183152"});
				$(this).css({"color":"red"});
				$("#openServiceTree").css({"color":"#183152"})
				 
			})
		}
			$(function(){
				var serviceUrl = getPath()+"/selectServiceTypeAll.do";
				var serviceOne;
				var serviceTwo;
				var serviceThree;
				$.ajax({
					url:serviceUrl,
					data:{},
					type:'post',
					async:false,
					success:function(result){
						serviceOne = result.serviceOne;
						serviceTwo = result.serviceTwo;
						serviceThree = result.serviceThree;
						$.each(serviceOne,function(i,one){
							var serviceType = "<li  class=\"serviceOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryServiceType("+serviceOne[i].id+",\'"+one.name+"\')\">";
							serviceType += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"serOne"+i+"\">";
							$.each(serviceTwo,function(j,two){
								if(serviceOne[i].id == serviceTwo[j].parentId){
									serviceType += "<li class=\"serviceTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryServiceType("+ serviceTwo[j].id + ",\'"+two.name+"\')\">";
									serviceType += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"serTwo"+j+"\">";	
									$.each(serviceThree,function(k,three){
										if (serviceTwo[j].id == serviceThree[k].parentId) {
											serviceType += "<li><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryServiceType("+serviceThree[k].id+",\'"+three.name+"\')\">";
											serviceType +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a>";
										}
									})
									serviceType+="</ul></li>"; 
								}
							})
							serviceType += "</ul></li>"; 
							$(".service_bzmenus_sub").append(serviceType);
							$("#serviceTreeDiv a").click(function(){
								$("#serviceTreeDiv a").css({"color":"#183152"});
								$(this).css({"color":"red"});
								$("#openServiceTree").css({"color":"#183152"})
								 
							})
							 
						})
					}
				})
				removeServiceLi();
				$("#openServiceTree").click();
				$("#openServiceTree").css({"color":"#183152"})
				$("#serviceSearchTree").one("click",function(){
				$.each($(".collapsable"),function(){
					$(this).click();
				})
				$(".last_collapsable").click();
			})
			})
			var status=$("#eventStatus").val();
			 if (status == "20") {
				$("#accept").css("display","none");
				$("#check").css("display","none");
			} 
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
		function showSourceTree(){
			$("#sourceTreeDiv").show();
			$("#sourceSidebar").show();
			$('body').bind("mousedown", onBodyDownSource);
		}
		function onBodyDownSource(event) {
		   if (!(event.target.id == "menuBtn" || event.target.id == "sourceTreeDiv" || $(event.target).parents("#sourceTreeDiv").length > 0)) {  
			$("#sourceTreeDiv").hide();
			$("#sourceSidebar").hide();
		   }  
		}//事件源结束
		function showServiceTree(){
			$("#serviceTreeDiv").show();
			$("#serviceSidebar").show();
			$('body').bind("mousedown", onBodyDownService);
		}
		function onBodyDownService(event) { 
		   if (!(event.target.id == "menuBtn" || event.target.id == "serviceTreeDiv" || $(event.target).parents("#serviceTreeDiv").length > 0)) {  
			$("#serviceTreeDiv").hide();
			$("#serviceSidebar").hide();
		   }  
		}//服务类型结束
		function queryServiceType(id,name){
			$("#serviceId").val(id);///
			$("#serviceSearchTree").val(name);
			$("#serviceTreeDiv").hide();
			$("#serviceSidebar").hide();
		}
		function querySourceType(id,name){
			$("#sourceId").val(id);
			$("#sourceSearchTree").val(name);
			$("#sourceTreeDiv").hide();
			$("#sourceSidebar").hide();
		}
	</script>
</body>
</html>