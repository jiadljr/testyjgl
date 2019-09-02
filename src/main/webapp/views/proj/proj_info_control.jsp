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
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page2.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_info_control.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style type="text/css">
	.mQuery-2 .form-group{ margin-bottom:0;}
   	.schedule span{width:60px; display:inline-block; float:left;text-align:center; }
   	.schedule p{width:170px; height:5px;margin-top:2px; background:#aaa;float:left;}
   	.schedule p b{ display:inline-block;height:5px; background:red;float:left;}                   
	.form-group{ margin-bottom:0;}
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
		 #rMenu{ width: 80px; position: absolute; display: none;box-shadow: 0 0 12px #aaa;}
        #rMenu li{ background: #fff; border-bottom: 1px solid #ccc; text-align:center; width: 80px; height: 20px;line-height: 20px; margin: 0; float: left;}
        #rMenu li a{ color: #777; display: block;}
			.iconClass{position:absolute; width:60px; height:14px; display:block;  right:5px;  top:9px}
        .iconClass i{ cursor:pointer; width:17px; font-size:14px;margin-right: 3px; float:right;  position:static;}
        .iconClass .flagHide{ display:none;}
        .iconClass .flagShow{ display:block;color:red;}
           .iconClass .yShowWarn{color:#d1a314;}
             .iconClass .yHideWarn{display:none;}
         .iconClass .rShowWarn{display:block;color:#da251c;}
           .iconClass .rHideWarn{display:none;}
              	.btnHover{ color:blue;}
   	.btnHover:hover{ text-decoration: underline;}
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
				<li class="active">项目监控</li>
			</ol>
		</div>

		<div class="mQuery-2" >
			<form action="" role="form" class="form-horizontal" method="post">
				<input type="hidden" id="pages" name="pages" value="${pages }">
				<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
				<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
				<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
				<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
				<div class="form-group col-sm-3" style="margin-left: 0px">
					<label for="eventPer" class="col-sm-3">项目名称:</label>
					<div class="col-sm-9 ">
						<input type="text" id="projName" name="projName"  class="form-control">
					</div>
				</div>
			
			
				<div class="form-group col-sm-2" style="margin-left: 0px">
					<label for="eventPer" class="col-sm-6">项目经理:</label>
					<div class="col-sm-6">
						<select id="idManager" name="idManager" class="form-control">
							<option>请选择</option>
							<c:forEach items="${userByRoleType }" var="role">
								<option value="${role.id }">${role.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
			
				<div class="form-group col-sm-6" style="margin-left: 0px">
					<label for="eventPer" class="col-sm-2">时间点:</label>
					<div class="col-sm-2">
						<select id="milestoneTask" name=milestoneTask class="form-control" onchange="changeDate(this);">
							<option value="0">今天</option>
							<option value="1">自定义</option>
						</select>
					</div>
					<label for="eventPer" class="col-sm-1"style="width:2%"></label>
					<div class="col-sm-5 timeSelBox" style="display:none;">
						<div class="col-sm-5">
							<input type="text" id="startTime" name="startTime"  class="form-control">
						</div>
					
					</div>
				</div>
				
				
				<div class="form-group col-sm-2" style="margin-left: 0px; text-align:right; position:absolute;right:10px; top:35px;">
					<a class="btn btn-primary" href="#" id="button_search">查询</a>
					<input type="reset" value="重置" class="btn btn-primary btn_red" style="border:none;" >
					<a class="btn btn_green" href="#" id="export"  onclick="exportCotrol()" style="border:none; height:27px;" >导出</a>
				</div>
			</form>
		</div>
		<div class="fixTable mRbot" >
			<table class="table table-bordered text-center table-hover table1">
				<thead>
					<tr>
						<th style="text-align:left;" >项目名称</th>
						<th width="90px;">负责人</th>
						<th width="110px">开始时间</th>
						<th width="110px">结束时间</th>
						<th width="210px">项目进度</th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="relTable" >
			<table class="table table-bordered table-hover table1">
			
				<tbody id="projControlBody">
					
				</tbody>
			</table>
		</div>
		<div class="mRpage" id="paging"></div>
	</div>

	<script type="text/javascript">
		$(function(){
			$(".relTable").height($("body").height()-170);

			$("#startTime").datetimepicker({
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
			$("#endTime").datetimepicker({
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
			$("#milestoneTask").change(function(){
				if($("#milestoneTask option:selected").val()==0){
					$(".timeSelBox").hide()
					$("#startTime").val("");
				}else{
					$(".timeSelBox").show()
				}
			})
			$("#button_search").click(function(){
				showProjControlList();
			})
			showProjControlList();
		
		})
		
	</script>
</body>
</html>