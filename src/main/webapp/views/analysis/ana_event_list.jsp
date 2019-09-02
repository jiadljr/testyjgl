<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>	
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/ana_event_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
	.QK_search{ width:100%; height:30px; position:relative;}
#searchTree{ border:1px solid #ccc; line-height:20px; margin:0; width:100%;}
#serviceSearchTree{border:1px solid #ccc; line-height:20px; margin:0; width:50%;}
#serviceSidebar{ width:100%; float:left;  margin-top:5px;position:absolute; top:30px;left:50%;z-index:10000; background:#fff; display:none;}
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
			.layui-layer-iframe .layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-iframe{ overflow-x:hidden;overflow-y:auto !important;}
	.fixTable{height:38px;}
	.relTable{width:100%;}
			.mQuery-2 .form-group  .form-control{ padding:0 12px;}
	
	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">查询分析</a></li>
				    <li class="active">事件查询</li>
				</ol>
			</div>

			<div class="mQuery-2" style="height:90px">
				<form action="" role="form" class="form-horizontal" method="post">
					<div class="form-group col-sm-4">
						<label for="dataStart" class="col-sm-3">日期：</label>
						<div class="col-sm-4">
							<input type="text" id="startTime" name="startTime" class="form-control" placeholder="请选择开始时间">
						</div>
						<div class="col-sm-1 text-center">至</div>
						<div class="col-sm-4">
							<input type="text" id="endTime" name="endTime" class="form-control" placeholder="请选择结束时间">
						</div>
					</div>
					<div class="form-group col-sm-3">
						<label for="eventContact" class="col-sm-5">联系方式：</label>
						<div class="col-sm-7">
							<input type="text" id="eventContact" name="eventContact" class="form-control" placeholder="请输入联系方式">
						</div>
					</div>
					<div class="form-group col-sm-5">
						<label for="eventCode" class="col-sm-3">事件编号：</label>
						<div class="col-sm-4">
							<input type="text" id="eventCode" name="eventCode" class="form-control" placeholder="请输入时间编号">
						</div>
					</div>
					<div class="form-group col-sm-2" style="margin-top:-10px">
						<label for="file_code" class="col-sm-6">服务类型：</label>
						<div class="row_option">
							<span class="QK_arrow" style="right:72px;top:21px;"></span>
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
					<div class="form-group col-sm-2" style="margin-top:-10px">
						<label for="file_code" class="col-sm-6">申告部门：</label>
						<div class="col-sm-6" style="position:relative">
						<div class="row_model" style="position: relative">
						    <span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="idDept" id="idDept" value="${userOne.idDept }" /> 
							    <input id="searchTree" type="text" value="${userOne.extend2 }" class="span2 form-control" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
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
					</div>
					<div class="form-group col-sm-3" style="margin-top:-10px">
						<label for="eventSource" class="col-sm-5">事件状态：</label>
						<div class="col-sm-7">
							<select id="eventSource" name="eventSource" class="form-control">
								<option value="">请选择事件状态</option>
							    <option value="20">申告</option>
							    <option value="50">已受理</option>
							    <option value="70">已处理</option>
							    <option value="77">已完成</option>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-5" style="margin-top:-10px">
						<label for="eventLevel" class="col-sm-3">事件等级：</label>
						<div class="col-sm-4">
							<select id="eventLevel" name="eventLevel" class="form-control">
								<option value="">请选择事件等级</option>
							    <option value="1">一级</option>
							    <option value="2">二级</option>
							    <option value="3">三级</option>
							    <option value="4">四级</option>
							</select>
						</div>
						<a class="btn btn-primary" id="button_search" style="margin-left:5px;margin-top:0;"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
					</div>					
						
				</form>								
			</div>
			<div class="mRbot" style="top:130px">
				<div class="fixTable">
					 <table class="table table-bordered text-center table-hover table1">
	                	<thead>
	                		<tr>
		                		<th>事件编号</th>
		                		<th>标题</th>
		                		<th  style="width:120px;">申请部门</th>
		                		<th>申告时间</th>
		                		<th style="width:120px;">服务类型</th>
		                		<th style="width:120px;">事件状态</th>
		                		<th style="width:90px;">操作</th>
	                		</tr>                		
	                	</thead>
	                	
	                		
	                </table>
				</div>
				<div class="relTable">
					<table class="table table-bordered text-center table-hover">
						<tbody id="tbody">
	                	</tbody>
					</table>
				</div>			
               
			</div>
			<div class="mRpage" id="pageTable"></div>

		</div>
		<script type="text/javascript">
		$(function(){
			$("#sidebar").hide();
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
		function queryType(id,name){
			$("#idDept").val(id);
			$("#searchTree").val(name);
			$("#treeDiv").hide();
			$("#sidebar").hide();
		}
		</script>
		<script type="text/javascript">
		   $(function(){
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
		   })
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
		</script>
	
	<script type="text/javascript">
		$(function(){
			analysisList();
			$("#startTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#endTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#button_search").click(function(){
				analysisList();
			})
		})
	</script>

</body>
</html>