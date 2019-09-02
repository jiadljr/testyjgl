<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_info_list.js"></script>
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
	#bPageList{ display:none;}
		#bPageInfo a{ border-left-width:1px; border-radius:4px 0 0 4px;}
		.table th {text-align:center;}
		.mRbot .table tbody tr td{ line-height:0.8;}
		.mRpage { position:absolute; right:10px; bottom:5px;}
		   	.btnHover{ color:blue;}
  			 .btnHover:hover{ text-decoration: underline;}
  			 	.iconClass{position:absolute; width:60px; height:14px; display:block;  right:5px;  top:9px}
        .iconClass i{ cursor:pointer; width:17px; font-size:14px;margin-right: 3px; float:right;  position:static;}
        .iconClass .flagHide{ display:none;}
        .iconClass .flagShow{ display:block;color:red;}
           .iconClass .yShowWarn{color:#d1a314;}
             .iconClass .yHideWarn{display:none;}
         .iconClass .rShowWarn{display:block;color:#da251c;}
           .iconClass .rHideWarn{display:none;}
	</style>
</head>
<body>
	<div class="tabItem">
		<div class="mQuery-2">
				<form action="" role="form" class="form-horizontal" method="post">
					<input type="hidden" id="pages" name="pages" value="${pages }">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<input type="hidden" id = "projCode" name="projCode" value="${projCode }"/>
					<input type="hidden" id="state" name="state" value="${state }"/>
					<input type="hidden" id="pageSize" name="pageSize" value="5"/>
					<div class="form-group col-sm-3" style="margin-left: 0px">
						<label for="eventPer" class="col-sm-6">任务名称:</label>
						<div class="col-sm-6">
							<input type="text" id="taskName" name="taskName"  class="form-control">
						</div>
					</div>
					<div class="form-group col-sm-3" style="margin-left: 0px">
						<label for="eventPer" class="col-sm-4">负责人:</label>
						<div class="col-sm-8 ">
							<select id="idTaskHead" name=idTaskHead class="form-control">
							</select>
						</div>
					</div>
					<div class="form-group col-sm-4" style="margin-left: 0px">
						<label for="eventPer" class="col-sm-6">任务筛选:</label>
						<div class="col-sm-6">
							<select id="milestoneTask" name=milestoneTask class="form-control">
								<option value="">全部</option>
								<option value="1">里程碑任务</option>
							</select>
						</div>
					</div>
					<a class="btn btn-primary" href="#" id="button_search">查询</a>
				</form>
			</div>
		
			<div class="mRbot" style="top: 85px; height: 68%;">
				<table class="table table-bordered text-center table-hover table1">
					<thead>
						<tr>
							<th style="text-align:left;">任务名称</th>
							<th width="90px;">任务负责人</th> 
							<!-- <th>工期</th> -->
							<th>开始时间</th>
							<th>结束时间</th>
							<th>任务状态</th>
							<th width="190px">任务进度</th>
							<th style="display:none;">操作</th>
						</tr>
					</thead>
					<tbody id="projBody">
						
					
					</tbody>
				</table>
			</div>
			<div class="mRpage" id="paging"></div>
		</div>
		<script type="text/javascript">
			$(function(){
				//加载任务列表
			    $("#button_search").click(function(){
					showProjTaskList();
				})
				showProjTaskList();
			})

		</script>
</body>
</html>