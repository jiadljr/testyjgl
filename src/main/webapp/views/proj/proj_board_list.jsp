<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prompt.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<link  rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/table/bootstrap-table.min.css" />
	<script src="<%=request.getContextPath()%>/bootstrap/table/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page2.js"></script>

	
	<script src="<%=request.getContextPath()%>/js/proj_board_list.js"></script>
	<style type="text/css">
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.mQuery-2 .form-group{ margin-bottom:0;}
   	#projBody tr td{ position:relative;}
    .mRight .fixTable{ float:left; height:38px; width:100%;}
    .mRight .fixTable table { margin-bottom:0;}
    .mRight .relTable{ float:left; width:100%; overflow-y:auto;overflow-x:hidden;}
    .mRight .relTable table{ margin-bottom:0;}
    .mRight .relTable table td{ line-height:1;}
   	.iconClass{position:absolute; width:60px; height:14px; display:block;  right:5px;  top:9px}
        .iconClass i{ cursor:pointer; width:17px; font-size:14px;margin-right: 3px; float:right;  position:static;}
        .iconClass .flagHide{ display:none;}
        .iconClass .flagShow{ display:block;color:red;}
           .iconClass .yShowWarn{color:#d1a314;}
             .iconClass .yHideWarn{display:none;}
         .iconClass .rShowWarn{display:block;color:#da251c;}
           .iconClass .rHideWarn{display:none;}
   	
   	.QK_search{ width:100%; height:30px; position:relative;}
	#searchTree{ height:20%; border:1px solid #ffffff; line-height:20px; margin:0; width:25%;}
	#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:99;}
	.tree_list{height:154px !important; overflow:auto;}
	#searchMenu{margin-bottom:5px; position:relative;}
	.accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
	.error{border-color:red !important;}
	.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
 	.QK_arrow{ border-top:6px #333 solid; border-bottom:2px solid #fff;border-left:3px solid #fff;border-right:3px solid #fff; display:block;position:absolute;right:9px; top:12px; z-index:98; }
	#one{width:200px; height:180px; float:left}
	#two{width:50px; height:180px; float:left}
	#three{width:200px; height:180px; float:left}
	.btnTryList{ width:280px; position:absolute; right:0; top:2px;}
	.btnTryList .btn{padding:2px 12px;}
	.titleSearch{width:100%; height:40px; background:#f5f5f5;}
	</style>
	
</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="#"><img alt="pos"
						src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
								<li><a href="#">项目管理</a></li>
				<li><a href="#">项目配置</a></li>
				<li class="active">看板配置</li>
			</ol>
		</div>
		<div class="btnTryList">
			<a href="#" class="btn btn-primary" onclick="cancelToProjConfig()">返回</a>
			<a href="#" class="btn btn_red" onclick="resetForm()">重置</a>
			<a href="#" class="btn btn_green" onclick="saveChecked()">保存</a>
		</div>
		<form action="" id="form1" role="form" class="form-horizontal" method="post">		
			<div class="mQuery-2"> 
				<div id="allArray" style="display:none;" >
				</div>
			
				<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
				<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
				<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
				<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
				<input type="hidden" id="pages" name="pages" value="${pages }">
				<div class="col-sm-4" style="margin-left: 0px">
					<label for="goodLevel" class="col-sm-3">输入关键词：</label>				
					<div class="col-sm-6">
						<input type="text" id="projName" name="projName"  class="form-control" value="" placeholder="项目名称关键字">
					</div>
					<a class="btn btn-primary" href="#" id="button_search" style="margin-top:0; margin-left:5px;">查询</a>
				</div>
			</div>
			<div class="mQuery-2" >
				<input type="hidden" id="serach_proj_name" value=""/>
				<div class="form-group col-sm-1" style="margin-left: 0px">
					<label for="goodLevel" class="col-sm-10">已选项目：</label>
					<input type="checkbox"  id="already" />
				</div>
				<div class="form-group col-sm-2 goodLevel" >
					<label for="goodLevel" class="col-sm-5">项目类型:</label>
					<div class="col-sm-7">
						<select id="projType" name="projType" class="form-control" onchange="showProjInfoList()">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-2 goodLevel" >
					<label for="goodLevel" class="col-sm-5">资金来源:</label>
					<div class="col-sm-7">
						<!-- <select id="amtFrom" name="amtFrom" class="form-control" onchange="showProjInfoList()">
							<option value="">全部</option>
							<option value="1">自有资金</option>
							<option value="2">财政拨款</option>
						</select> -->
						<select id="amtFrom" name="amtFrom" class="form-control" onchange="showProjInfoList()">
							<option value="">请选择</option>
							<c:forEach items="${capitalSourceAll }" var="capitalSource" >
								<option value="${capitalSource.id }">${capitalSource.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-2 goodLevel" >
					<label class="col-sm-5" style="float:left">所属部门：</label>
					<div class="col-sm-7">
						<div class="QK_search">
						<div id="searchMenu" class="" >
						 <span class="QK_arrow"></span>
							<input type="hidden" name="idDept" id="idDept" value=""/> 

						    <input id="searchTree" type="text" style="height:30px;width:100%; padding:0 12%;border:1px solid #ccc; border-radius:6px;" onChange="deptChange()" class="proEditIn" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()" value="">

						</div> 
						
						<select id="eventDept" class="qkby_sel" style=" position:absolute;top:34px; left:0; width:100%; border-radius: 5px; display: none; height:100px;" name="eventUser" onChange="userChange()" size="5" >
						</select>
							<div id="sidebar">
								<div class="toggleCollapse">
						       		<div id="treeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
						          		<div class="accordionContent" style="background-color: #ffffff">
					             			<ul id="a" class="tree treeFolder ">
						               			<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#fff" id="openTree">部门信息</a>
						                			<ul id="bzmenus" class="bzmenus_sub"></ul>
						               			</li>
					             			</ul>
						           		</div>
						        	</div>
						    	</div>
							</div>
						</div>
					</div>
					<span id="clearInput" style=" height:18px;display:none; width:18px; background:#fff;line-height:20px; text-align:center; line-height:18px;z-index:99999;font-size:18px;position:absolute;right:5px; top:6px; cursor:pointer;">×</span>
				</div>
				<div class="form-group col-sm-2 goodLevel" >
					<label for="goodLevel" class="col-sm-5">项目状态:</label>
					<div class="col-sm-7">
						<select id="projStatus" name="projStatus" class="form-control" onchange="showProjInfoList()">
							<option value="">全部</option>
							<option value="91">正常</option>
							<option value="96">冻结</option>
							<option value="1">延期</option>
						</select>
					</div>
				</div>	
			</div>				
		</form>
        <div class="fixTable mRbot">
   			<table class="table table-bordered table1" >
				<thead>
					<tr>
						<th width="3%"><input type="checkbox" name="allSelect"></th>
						<th style="text-align:left;">项目名称</th>
						<th width="100px">项目进度</th>
						<th width="190px;">负责人</th>
					</tr>
				</thead>
			
			</table>
		
        </div>
		<div class="relTable" id="showtable">
			<table class="table table-bordered table-hover table1">
				<tbody  id="projBody">
					
				</tbody>			
			</table>
		</div>

		<div class="mRpage" id="paging"></div>
	</div>
	<script type="text/javascript">
		$(function(){
			$(".relTable").height($("body").height()-210);			

			changeAll();
			getProjType();
			$("#already").click(function(){
		
				changeAll();
			})
			function changeAll(){
				if($("#already").is(":checked")==true){
					runProjInfo();
				
					$("select,#searchTree").change(function(){
						runProjInfo();
					})
				}else{
					showProjInfoList();
				}
			}
		
		})
	</script>

</body>
</html>