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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prompt.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page2.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_task_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style type="text/css">
	.mQuery-2 .form-group{ margin-bottom:0;}
	.ztree {padding: 0;border: 2px solid #CDD6D5;width:160px; height:150px; overflow:auto;}
	.btn_class{ height:40px; }
	.btn_class a:hover{text-decoration: none;color:#fff; opacity:1;}
   	.btn_base{ opacity:0.7; font-size:14px; padding:3px 5px;margin:10px 0 10px 10px; float:left;   border-radius:3px; color:#fff; display:inline-block;}
   	.btn_refresh{ background:#72b960; }
   	.btn_add{ background: #1476d9;}
   	.btn_delete{background:#ff0000;}
   	.btn_edit{background:#feb94a;}
   	.btn_lookfoward{background:#00c1de;}
   	.btn_power{background:#ff6666;}
   	.btn_signout{background:#d5d53f;}
   	.btn_signin{background:#e28786;}
   	.btn_download{background:#9065b4;}
   	.navFin{ float:left; height:40px; line-height:40px;margin-left:30px;}
   	.navFin li{float:left; padding:0 5px; cursor:pointer;}
   	.navFinOn{ color:#1476d9;}
   	.schedule span{width:60px; display:inline-block; float:left;text-align:center; }
   	.schedule p{width:170px; height:5px;margin-top:2px; background:#aaa;float:left;}
   	.schedule p b{ display:inline-block;height:5px; background:red;float:left;}                   
	.form-group{ margin-bottom:0;}
	
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	   	.btnHover{ color:blue;}
   	.btnHover:hover{ text-decoration: underline;}
   		.iconClass{position:absolute; width:60px; height:14px; display:block;  right:5px;  top:9px}
        .iconClass i{ cursor:pointer; width:17sssssspx; font-size:14px;margin-right: 3px; float:right;  position:static;}
        .iconClass .flagHide{ display:none;}
        .iconClass .flagShow{ display:block;color:red;}
           .iconClass .yShowWarn{color:#d1a314;}
             .iconClass .yHideWarn{display:none;}
         .iconClass .rShowWarn{display:block;color:#da251c;}
           .iconClass .rHideWarn{display:none;}
                   .mRight .fixTable{ float:left; height:38px; width:100%;}
           .mRight .fixTable table { margin-bottom:0;}
           .mRight .relTable{ float:left; width:100%; overflow-y:auto;overflow-x:hidden;}
              .mRight .relTable table{ margin-bottom:0;}
              .mRight .relTable table td{ line-height:0.9;}
	</style>
</head>
<body>
	<div class="mRight">
		
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="#"><img alt="pos"
						src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="#">项目管理</a></li>
				<li class="active">我的任务</li>
			</ol>
		</div>

		<div class="mQuery-2">
			<form action="" role="form" class="form-horizontal" method="post">
				<input type="hidden" id="pages" name="pages" value="${pages }">
				<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
				<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
				<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
				<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
				<input type="hidden" id="pStatus" name="pStatus" value="${pStatus }"/>
				<div class="form-group col-sm-3" style="margin-left: 0px">
					<label for="eventPer" class="col-sm-3">任务名称:</label>
					<div class="col-sm-9">
						<input type="text" id="taskName" name="taskName"  class="form-control">
					</div>
				</div>
			
				<a class="btn btn-primary" href="#" id="button_search">查询</a>
			</form>
		</div>
		<ul class="navFin">
				<li class="navFinOn" id="pStatus1"><input type="hidden" value="全部"/>全部（<span id="allTask"></span>）</li>
				<li>|</li>
				<li id="pStatus2"><input type="hidden" value="正常"/>正常（<span id="normalTask"></span>）</li>
				<li>|</li>
				<li id="pStatus3"><input type="hidden" value="完成"/>完成（<span id="finishTask"></span>）</li>
				<li>|</li>
				<li id="pStatus4"><input type="hidden" value="已超期"/>超期（<span id="overTimeTask"></span>）</li>
		</ul>
		  <div class="fixTable mRbot">
				<table class="table table-bordered text-center table-hover table1">
					<thead>
						<tr>
							<th style="text-align:left;">任务名称</th>						
							<th style="text-align:left;">所属项目</th>						
							<th width="90px;">项目负责人</th>
							<th width="110px">开始时间</th>
							<th width="110px">结束时间</th>
							<th width="190px">任务进度</th>
							<th id="oper" width="130px;">操作</th>
						</tr>
					</thead>
				</table>
		  </div>

		<div class="relTable" >
			<table class="table table-bordered table-hover table1">			
				<tbody id="projBody">					
				</tbody>
			</table>
		</div>
		<div class="mRpage" id="paging"></div>
	</div>

	<script type="text/javascript">
		$(function(){
			$(".relTable").height($("body").height()-210);
			$("#button_search").click(function(){
				showProjTaskList();
			})
			showProjTaskList();
			$(".navFin li").click(function(){
				$(".navFin li").removeClass("navFinOn");
				$(this).addClass("navFinOn");	
				var pStatus = $(this).find("input").val();
				changeInputState(pStatus);
				showProjTaskList();
			})
			var pStatus = $("#pStatus").val();
			if(pStatus == "全部"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus1").addClass("navFinOn");
			}else if(pStatus == "正常"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus2").addClass("navFinOn");
			}else if(pStatus == "完成"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus3").addClass("navFinOn");
			}else if(pStatus == "已超期"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus4").addClass("navFinOn");
			}
		})
		function changeInputState(status){
			$("#pStatus").val(status);
		}
		function GetUrlStr(name){
		  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		   var r = window.location.search.substr(1).match(reg);
		   if(r!=null)
		   return unescape(r[2]);
		   return null;
		}
		if(GetUrlStr('taskId')==1){
			window.parent.balabala()
		}
	</script>
</body>
</html>