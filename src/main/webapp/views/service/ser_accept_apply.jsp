<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/conAdd_menu.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
<script
	src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"> </script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
<script src="<%=request.getContextPath()%>/plugins/select2/category_filter.js"></script>
<script src="<%=request.getContextPath()%>/plugins/select2/jQuery.Hz2Py-min.js"></script>
<script
	src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<script src="<%=request.getContextPath()%>/js/event_agency_list.js"></script>
<style type="text/css">
.QK_search{ width:100%; height:30px; position:relative;}
.QK_search input{ border:1px solid #aaa;}
#searchTree{ margin:0; width:240px;}
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
.selDiv{ width:200px; height:30px; position:relative;}
.selDiv #selInp{ width:100%; height:30px;  border-radius:4px; border:1px solid #aaa; }
.selDiv #sellist{display:none; position:absolute; left:0; top:32px; z-index:10000; border-radius:0 0 5px 5px;}
.mRight .mNewRbot .mRwell-service .mRwell-left {height: 60%;min-height: 300px;width: 60%;overflow-x: hidden;overflow-y: auto;border: 1px solid #999;}
.mRwell-left .mRwell-con-tit {width: 100%;height: 50px;line-height: 50px;text-align: left;font-size: 14px;letter-spacing: 2px;font-weight: bold;color: #3f90e6;border-bottom: 1px solid #3f90e6;}
.mRwell-left .mRwell-container {width: 70%;margin: 0 auto;}
.mRwell-right {width: 35%;}
.mRwell-left .form-group {margin-top: 10px;}
.ztree {padding: 0;border: 2px solid #CDD6D5;width: 160px;height: 150px;overflow: auto;}
input, select, textarea {border: 1px solid #aaa;}
.mRwell_box {width: 100%;min-height: 460px;background: #f0f0f0;float:left;}
label {margin: 0;font-weight: normal;text-align: right;}
.mRwell_box .mRwell-con-tit-big {width: 100%;height: 24px;margin: 0;text-align: left;line-height: 24px;font-size: 12px;color: #fff;background: #3b85c7;}
.mRwell-con-tit-big span {float: right;display: inline-block;font-size: 14px;margin-right: 10px;height: 24px;line-height: 24px;cursor: pointer;}
.mRwell_box .mRwell_inner {
	width: 646px;
	
	box-shadow: 0 5px 20px #d2d2d2;
	min-height: 350px;
	margin: 5px auto;
	background: #fff;
	padding-top: 8px;
}

.mRwell_box  .mRwell_inner .mRwell_top {
	position: relative;
	width: 640px;
	min-height: 205px;
	margin: 0 auto;
	border: 1px solid #a2a0a0;
	box-sizing: border-box;
}

.mRwell_box  .mRwell_inner .mRwell_bot {
	position: relative;
	width: 640px;
	min-height: 195px;
	margin: 8px auto;
	border: 1px solid #a2a0a0;
	box-sizing: border-box;
}

.mRwell_box  .mRwell_inner .rel_tit {
	display: inline-block;
	padding: 0 4px;
	background: #fff;
	color: #3d3d3d;
	position: absolute;
	left: 4px;
	top: -6px;
	font-size: 14px;
	height: 14px;
	line-height: 14px;
}

.row_item {
	height: 30px;
	line-height: 30px;
	margin-top: 5px;
}

.row_item label {
	width: 25%;
	height: 30px;
	line-height: 30px;
	font-size: 14px;
	float: left;
	color: #3d3d3d;
}

.row_item input {
	border-radius: 3px;
	outline: none;
	padding:0 3px;
	width: 25%;
	height: 30px;
	line-height: 30px;
	font-size: 14px;
	float: left;
}
.row_item select{border-radius: 3px;outline: none;width: 25%;height: 30px;line-height: 30px;font-size: 14px;float: left;}
.row_item .row_model {
	width: 25%;
	height: 30px;
	line-height: 30px;
	font-size: 14px;
	border: none;
	float: left;
}

.row_item .row_model input {
	border-radius: 3px;
	outline: none;
	width: 100%;
	padding:0 3px;
	height: 30px;
	line-height: 30px;
	font-size: 14px;
	float: left;
}

.row_item .row_txt {
	width: 70%;
	height: 60px;
	line-height: 40px;
	float: left;
}

.row_item .row_txt textarea {
	width: 100%;
	resize: none;
	height: 100%;
	border-radius: 4px;
	line-height: 20px;
}

.row_item .row_tel {
	width: 20%;
}





.row_item .deal_model {
	width: 20%;
	float: left;
}

.row_item .deal_model select {
	width: 100%;
	float: left;
	height:30px;
}


.btnLast {
	float: right;
}

.btn_bass {
	height: 20px;
	line-height: 20px;
	font-size: 12px;
	margin: 0 8px;
	padding: 0 17px;
	display: inline-block;
	color: #fff;
	border-radius: 5px;
}

.btn_add {
	background: #3b85c7;
}

.btn_close {
	background: #e2614e;
}

.btn_con {
	background: #6697cb;
}

.row_option {
	width: 20%;
	float: left;
	text-align: center;
}

.row_option input {
	width: 100%;
	border-radius: 3px;
}

#userApply {
	width: 25%;
}
.mRwell_bot .row_item select{ width:20%;}
.mRwell_bot .row_item .deal_model select {width: 100%;}
.row_item .selDiv{position:relative;}
.row_item .selDivTop{width: 25%;height: 24px;line-height: 24px;font-size: 12px;float: left;}
.row_item .selDiv input{ width:100%; border-radius:3px;}
.row_item .selDiv select{ position:absolute; top:24px;left:0; height:auto; width:100%; border-radius:3px;}
.QK_arrow{ border-top:6px #333 solid; border-bottom:2px solid #fff;border-left:3px solid #fff;border-right:3px solid #fff; display:block;width:4px;height:12px; position:absolute; right:8px; top:12px; z-index:98; }
</style>
</head>
<body>




	<form role="form" class="form-horizontal" id="form1">
		<div class="mRwell_box">
			<input type="hidden" id="pagenumber" name="pagenumber"
				value="${pageNumber }"> <input type="hidden" id="pagesize"
				name="pagesize" value="${pageSize }"> <input type="hidden"
				id="totalpage" name="totalpage" value="${totalPage }"> <input
				type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
			<input type="hidden" id="tab" name="tab" value="${str }" />
			<input type="hidden" name="token" value="${token}" />
			<div class="mRwell_inner">
				<div class="mRwell_top">
					<span class="rel_tit">申告信息<span style="color:#fff;font-size:16px;">*</span>: </span>

					<div class="row_item" style="margin-bottom:3px;">
						<label for="depart" class="">申告部门<span style="color:#fff;font-size:16px;">*</span>: </label>
						<div class="row_model" style="position: relative">
							<span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="idDept" id="idDept" value="${userOne.idDept }" /> 
							    <input id="searchTree" type="text" style="width: 100%;margin-top:-3px; height: 30px;" value="${userOne.extend2 }" class="span2 form-control" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
							</div> 
							<select id="eventDept" class="qkby_sel" style=" position:absolute;top:34px; left:0; width:100%; border-radius: 5px; display: none; height:100px;" name="eventUser" onChange="userChange()" size="5" >
							</select>
							<div id="sidebar">
								<div class="toggleCollapse">
							       	<div id="treeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
							          		<div class="accordionContent" style="background-color: #ffffff">
							             		<ul id="a" class="tree treeFolder ">
							               		<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openTree">部门信息</a>
							                			<ul id="bzmenus" class="bzmenus_sub"></ul>
							               		</li>
							             		</ul>
							           	</div>
							        	</div>
							     	</div>
							</div>
						</div>
						</div>
					</div>
					<div class="row_item">
						<label for="userApply" class="">申告人<span style="color:#fff;font-size:16px;">*</span>: </label>
						<div id="selDiv" class="selDiv selDivTop" style="position:relative;">
							<span class="QK_arrow"></span>
							<input id="selInp" type="text" value="${user_name }" class="qkby_input" readonly style="background:#fff; width:100%;" >
							<select id="eventUser" class="qkby_sel" style=" position:absolute;top:34px; left:0; width:100%; border-radius: 5px; display: none; height:100px;" name="eventUser" onchange="userChange()" size="5" >
								<c:forEach var="sfm" items="${selectUser}">
									<option value="${sfm.id }"
										<c:if test="${sfm.id eq user_id}"> selected="selected"</c:if>>${sfm.name }</option>
								</c:forEach>
							</select>
						</div>
						 <label for="" class="row_tel" >联系电话<span style="color:#fff;font-size:16px;">*</span>: </label>

						<c:if test="${userOne.cal != null}">
							<input type="text" id="callApply" name="callApply"
								value="${userOne.cal }" class="telClass" data-message="*电话不得为空">
						</c:if>
						<c:if test="${userOne.cal == null}">
							<input type="text" id="callApply" name="callApply"
								value="${dep_tel }" class="telClass" data-message="*电话不得为空">
						</c:if>

					</div>

					<div class="row_item">
						<label for="tit" class=" ">事件标题<span style="color:red;font-size:16px;">*</span>: </label> <input type="text"
							id="eventName" name="eventName" class="error_null"
							data-message="*事件标题不得为空！">
					</div>
					<div class="row_item">
						<label for="remark" class=" ">事件描述<span style="color:red;font-size:16px;">*</span>: </label>
						<div class="row_txt">
							<textarea id="eventDesc" name="eventDesc" class="error_null"
								data-message="*事件描述不得为空！"></textarea>
						</div>
					</div>

	        </div>
     
				<div class="mRwell_bot">
					<span class="rel_tit">受理信息</span> <input type="hidden"
						name="eventId" value="${eventOne.id }" /> <input type="hidden"
						id="eventStatus" value="${eventOne.eventStatus }" />
					<div class="row_item">
					 <label for="remark" class=" ">事件源<span style="color:#fff;font-size:16px;">*</span>:</label>
						<div class="row_option" style="position:relative">
						    <span class="QK_arrow"></span>
							<div class="QK_search">
								<div id="searchMenu">
									<input type="hidden" name="sourceId" id="sourceId" value="" /> 
									<input id="sourceSearchTree" style="margin-top:-3px;" type="text" class="span2 form-control" placeholder="请选择事件源" data-autofocus="autofocus" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showSourceTree()">
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
						<label for="remark" class=" ">服务类型<span style="color:red;font-size:16px;">*</span>: </label>
						<div class="row_option">
							<span class="QK_arrow" style="right:72px;top:21px;"></span>
							<div class="QK_search">
								<div id="searchMenu">
									<input type="hidden" id="serviceId" name="serviceId" />
									<input id="serviceSearchTree" style="margin-top:-3px;"  type="text" class="span2 form-control" placeholder="请选择服务类型" data-autofocus="autofocus" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showServiceTree()">
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
					<div class="row_item">
						<label for="eventPriority">优先级<span style="color:#fff;font-size:16px;">*</span>: </label> <select
							id="eventPriority" name="eventPriority">
							<option value="1">高</option>
							<option value="2">中</option>
							<option value="3" selected="selected">低</option>
						</select>
						<label for="eventLevel" class=" ">事件等级<span style="color:#fff;font-size:16px;">*</span>: </label> <select
							id="eventLevel" name="eventLevel" class="">
							<option value="">一级事件</option>
							<option value="2">二级事件</option>
							<option value="3">三级事件</option>
							<option value="4" selected>四级事件</option>
						</select>
						
					</div>
					<div class="row_item">
						<label for="idUserDeal">处理人<span style="color:red;font-size:16px;">*</span>: </label>
						<div id="selDiv" class="selDiv deal_model selDivBot">
						<input type="hidden" id="dealName"  name="dealName" value=""  />
								<span class="QK_arrow"></span>
							<input id="selInps" type="text"  value="请选择处理人" class="qkby_input" readonly style="background:#fff;"   >
						
							<select id="idUserDeal" name="idUserDeal" class="qkby_sel" style="display:none; psoition:absolute;left:0;top:31px;" size="5">
								<!-- forEach遍历 -->
								<c:forEach var="sfm" items="${userAll}">
									<option value="${sfm.id }">${sfm.name }</option>
								</c:forEach>
							</select>
						</div> 
						
					</div>
					<div class="row_item">
						<label for="remark" class="">受理描述<span style="color:red;font-size:16px;">*</span>:</label>
						<div class="row_txt">
							<textarea class="error_null" id="acceptDesc" name="acceptDesc"
								data-message="*受理描述不得为空！"></textarea>
						</div>
					</div>
				</div>

			</div>
			<div class="btnLast">
				<a class="btn_bass btn_add" id="addApplyAndAccept">保存</a> 
				<a class="btn_bass btn_add btn_red" id="addApplyContinue">取消</a>
			</div>

		</div>

	</form>
	<script>
	$(function(){
		$("#addApplyContinue").click(function(){
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		})
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
		//部门
		$("#searchTree").keyup(function(){
			if ($("#searchTree").val() != "") {
				$("#searchTreeClose").show();
				removeLi();
			} else {
				$("#searchTreeClose").hide();
				removeLi();
			}
			$('#a').colExpAll({
				clickType : 'search'
			});
			removeLi();
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
						$("a").click(function(){
							$("a").css({"color":"#183152"});
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
							$("a").click(function(){
								$("a").css({"color":"#183152"});
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
			//部门
			$("#searchTree").bind("keydown", function(e) {
				if (e.keyCode == 13) {
					if ($("#searchTree").val() != "") {
						$("#searchTreeClose").show();
						removeLi();
					} else {
						$("#searchTreeClose").hide();
						removeLi();
					}
					$('ul.tree').colExpAll({
						clickType : 'search'
					});
					removeLi();
				}
			});
		function removeLi() {
			if($(".deptOne").length>0){
				for (var n = 0; n < $(".deptOne").length; n++) {
					if ($("#one" + n + " li").length == 0) {
						$("#one" + n).remove();
					}
				}
			}
			if($(".deptTwo").length>0){
				for (var m = 0; m < $(".deptTwo").length; m++) {
					if ($("#two" + m + " li").length == 0) {
						$("#two" + m).remove();
					}
				}
			}
			if($(".deptThree").length>0){
				for (var m = 0; m < $(".deptThree").length; m++) {
					if ($("#three" + m + " li").length == 0) {
						$("#three" + m).remove();
					}
				}
	    }
	
	$("#treeDiv a").click(function(){
		$("#treeDiv a").css({"color":"#183152"});
		$(this).css({"color":"red"});
		$("#openTree").css({"color":"#183152"})
		 
	})
}
		$("#sidebar").hide();
		var url = getPath()+"/findAllDept.do";
		var deptOne;
		var deptTwo;
		var deptThree;
		var deptFour;
		$.ajax({
			url:url,
			data:{},
			type:'post',
			async:false,
			success:function(result){
			deptOne = result.deptOne;
			deptTwo = result.deptTwo;
			deptThree = result.deptThree;
			deptFour = result.deptFour;
			$.each(deptOne,function(i,one){
				var type = "<li  class=\"deptOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryType("+deptOne[i].id+",\'"+one.name+"\')\">";
					type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"one"+i+"\">";
				$.each(deptTwo,function(j,two){
					if(deptOne[i].id == deptTwo[j].parentId){
						type += "<li class=\"deptTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryType("+ deptTwo[j].id + ",\'"+two.name+"\')\">";
						type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"two"+j+"\">";	
						$.each(deptThree,function(k,three){
							if (deptTwo[j].id == deptThree[k].parentId) {
								type += "<li class=\"deptThree\"><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryType("+deptThree[k].id+",\'"+three.name+"\')\">";
								type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a><ul id=\"three"+k+"\">";
								$.each(deptFour,function(m,four){
									if (deptThree[k].id == deptFour[m].parentId) {
										type += "<li><a autobypy="+four.extend2 +"href=\"#\" onclick=\"queryType("+deptFour[m].id+",\'"+four.name+"\')\">";
										type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+four.name+"</a>";
									}
								})
								type+="</ul></li>";
							}
						})
						type+="</ul></li>"; 
					}
				})
				type += "</ul></li>"; 
				$(".bzmenus_sub").append(type);
				$("#treeDiv a").click(function(){
					$("#treeDiv a").css({"color":"#183152"});
					$(this).css({"color":"red"});
					$("#openTree").css({"color":"#183152"})
					 
				})
				 
			})}
		})
		removeLi();
		$("#openTree").click();
		$("#openTree").css({"color":"#183152"})
		$("#searchTree").one("click",function(){
		$.each($(".collapsable"),function(){
			$(this).click();
		})
		$(".last_collapsable").click();
	})
	})

	function queryType(id,name){
		$("#idDept").val(id);
		$("#searchTree").val(name);
		$("#treeDiv").hide();
		$("#sidebar").hide();
		document.getElementById("eventUser").options.length = 0;
		$.ajax({
			type : 'post',
			url : getPath() + "/selectDeptUser.do",
			data : {
				"id" : id
			},
			dataType : "json",
			success : function(result) {
				if (result.selectUser != null) {
					var thirdSelect = document.getElementById("eventUser");
					for (var i = 0; i < result.selectUser.length; i++) {
						thirdSelect.add(new Option(result.selectUser[i].name,
								result.selectUser[i].id));
					}
					if ($('#eventUser').fireEvent)
					{
					$('#eventUser').fireEvent('onchange');
					}
					else
					{
					$('#eventUser').change();
					}
				}
				deptCall = result.tel;
				$("#callApply").val(result.tel);
			}
		})
	}
	function showTree(){
		$("#treeDiv").show();
		$('body').bind("mousedown", onBodyDown);
		$("#sidebar").show();
	}
	function onBodyDown(event) { 
		   if (!(event.target.id == "menuBtn" || event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length > 0)) {  
			$("#treeDiv").hide();
			$("#sidebar").hide();
		   }  
		}
		
	
		$(".qkby_input").click(function(e){
			$(this).removeClass("error")
			var $qkbySel=$(this).next();
			$(this).next().show();
			$(this).next().click();
			e.stopPropagation();
		
		})
		
		$(".qkby_sel option").click(function(e){
			$(this).parent().hide();
			e.stopPropagation();
			$(this).parent().prev().val($(this).text());
		})
		$(".qkby_sel").blur(function(){
			$(this).hide();		
		})
		$(".qkby_sel").click(function(e){
			e.stopPropagation();		
		})
		$(document).click(function(){
			$(".qkby_sel").hide()
		})
		function deptTreeBlur(){
			$("#sidebar").hide();
		}
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