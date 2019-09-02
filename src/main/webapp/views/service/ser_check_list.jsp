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
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_check_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>	
	<style type="text/css">
		.QK_search{ width:100%; height:30px; position:relative;}
		#searchTree{ border:1px solid #ccc; line-height:20px; margin:0; width:100%;}
		#serviceSearchTree{border:1px solid #ccc; line-height:20px; margin:0; width:50%;}
		#serviceSidebar{ width:100%; float:left;  margin-top:5px;position:absolute; top:30px;left:34%;z-index:10000; background:#fff; display:none;}
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
		.relTable{ width:100%; } 
		.mRight .mRbot .relTable .table{ float:none; clear:both;}
		.fixTable{height:37px;}
		.fixTable .table *{border-bottom:none;}
		.mQuery-2 .form-group  .form-control{ padding:0 6px;}
		
	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">审核</li>
				</ol>
			</div>

			<div class="mQuery-2">
				<form action="" role="form" class="form-horizontal" method="post">
				   <input type="hidden" id="pages" name="pages" value="${pages }">
                   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="form-group col-sm-4" >
						<label for="dataStart" class="col-sm-3">日期：</label>
						<div class="col-sm-4">
							<input type="text" id="startTime" name="startTime" class="form-control" placeholder="请选择起始时间" >
						</div>
						<div class="col-sm-1 text-center" >至</div>
						<div class="col-sm-4">
							<input type="text" id="endTime" name="endTime" class="form-control"  placeholder="请选择结束时间">
						</div>
					</div>
					<div class="form-group col-sm-2">
						<label for="eventLevel" class="col-sm-5">事件等级：</label>
						<div class="col-sm-7">
							<select id="eventLevel" name="eventLevel" class="form-control">
								<option value="">请选择事件等级</option>
							    <option value="1">一级</option>
							    <option value="2">二级</option>
							    <option value="3">三级</option>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-2">
						<label for="eventSource" class="col-sm-5">优先级：</label>
						<div class="col-sm-7">
							<select id="eventPriority" name="eventPriority" class="form-control">
								<option value="">请选择优先级</option>
							    <option value="1">高</option>
							    <option value="2">中</option>
							    <option value="3">低</option>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-3" style="margin-left:-20px">
						<label for="eventService" class="col-sm-4" >服务类型：</label>
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
										
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>	
				</form>								
			</div>
			
			<%-- <div class="btnout">
                <a class="btn btn-primary btn-sm" id="addApply"><img alt="" src="<%=request.getContextPath()%>/images/add-apply.png">添加申告</a>
            </div> --%>

			<div class="mRbot" style="top:85px;height: 68%; margin-top:15px;">
				<div class="fixTable">
					 <table class="table table-bordered table-hover text-center table1">
	                	<thead>
	                		<tr>
		                		<th>事件编号</th>
		                		<th>标题</th>
		                		<th style="width:100px;">申请部门</th>
		                		<th>申告时间</th>
		                		<th>受理时间</th>	                		
		                		<th style="width:70px;" >优先级</th>
		                		<th style="width:90px;">事件等级</th>
		                		<th style="width:100px;">操作</th>
	                		</tr>                		
	                	</thead>                		
	                </table>
				</div>			
               	<div class="relTable">
     				<table class="table table-bordered table-hover text-center table1">
						<tbody id="tbody"></tbody>               		
           			</table>
               	</div>
			</div>
			<div class="mRpage" id="pageTable"></div>
		</div>
	
	<script type="text/javascript">
	
	$(function(){
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
		//展示
		showCheckList();
		//搜索
		$("#button_search").click(function(){
			var eventService = $("#eventService").val();
			if(eventService == ""){
				$("#serviceId").val("");
			}
			showCheckList();
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
	})
	</script>
</body>
</html>