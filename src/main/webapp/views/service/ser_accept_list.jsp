<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script
	src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
<script
	src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
<script
	src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
<script src="<%=request.getContextPath()%>/js/ser_accept_list.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style type="text/css">

		.mQuery-2 .form-group  .form-control{ padding:0 12px;}

</style>
</head>
<body>
	<div class="mRight">
		<div class="rightBox">
			<img alt="pos" src="<%=request.getContextPath()%>/images/phone.png"
				onclick="applyAndAccept();">
		</div>
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="javascript:;"><img alt="pos"
						src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="javascript:;">服务管理</a></li>
				<li class="active">受理</li>
			</ol>
		</div>

		<div class="mQuery-2">
			<form action="" role="form" class="form-horizontal" method="post">
				   <input type="hidden" id="pages" name="pages" value="${pages }">
                   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
				<div class="form-group col-sm-4" style="margin-left: -60px">
					<label for="dataStart" class="col-sm-3">日期：</label>
					<div class="col-sm-4">
						<input type="text" id="startTime" name="startTime"

							class="form-control" placeholder="请输入开始时间"  />

					</div>
					<div class="col-sm-1">——</div>
					<div class="col-sm-4">
						<input type="text" id="endTime" name="endTime"

							class="form-control" placeholder="请输入结束时间"  />

							

					</div>
				</div>
				<div class="form-group col-sm-2" style="margin-left: -20px">
					<label for="file_code" class="col-sm-4">部门：</label>
					<div class="col-sm-6" style="position: relative">
						<input type="hidden" name="idDept" id="idDept" value=""/> 

						<input type="text" id="eventDept" name="eventDept" onChange="deptChange()" class="form-control" placeholder="请输入部门" />
		<ul id="tree" class="ztree"
							style="width: 100%; z-index: 10000; overflow: auto; position: absolute; top: 36px; background-color: #eee; display: none"></ul>
					</div>
				</div>
				<div class="form-group col-sm-2" style="margin-left: -60px">
					<label for="eventPer" class="col-sm-5">申告人：</label>
					<div class="col-sm-8 col_sm_9">
						<select id="eventUser" name="eventUser" class="form-control">
						</select>
					</div>
				</div>
				<div class="form-group col-sm-2 sourse">
					<label for="eventSource" class="col-sm-4">来源：</label>
					<div class="col-sm-8">
						<select id="eventSource" name="eventSource" class="form-control">
							<option value="">请选择事件状态</option>
							<option value="20">申告</option>
							<option value="31">审核驳回</option>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-2 status" style="display: none">
					<label for="eventStatus" class="col-sm-4">状态：</label>
					<div class="col-sm-8">
						<select id="eventStatus" name="eventStatus" class="form-control">
							<option value="">请选择事件状态</option>
							<option value="2">正常</option>
							<option value="3">增援</option>
							<option value="4">转派</option>
						</select>
					</div>
				</div>
				<a class="btn btn-primary" id="button_search"><img
					src="<%=request.getContextPath()%>/images/search.png">搜索</a>
			</form>
		</div>
		<div class="mRbot" style="top: 85px; height: 68%; margin-top:15px;">
			<div class="tag" id="tag">
				<div class="acceptWait" id="acceptWait">
					<img alt=""
						src="<%=request.getContextPath()%>/images/wait-accept.png">待受理
				</div>
				<div class="dealWait" id="dealWait">
					<img alt=""
						src="<%=request.getContextPath()%>/images/wait-deal.png">待确定
				</div>
			</div>
			<table class="table table-bordered table-hover table1">
				<thead>
					<tr>
						<th>编号</th>
						<th>标题</th>
						<th>申请人</th>
						<th>申请部门</th>
						<th>申告时间</th>
						<th>联系方式</th>
						<th>来源</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="notAccepttbody">

				</tbody>

			</table>
			<table class="table table-bordered table-hover table2"
				style="display: none">
				<thead>
					<tr>
						<th>编号</th>
						<th>标题</th>
						<th>申请人</th>
						<th>申请部门</th>
						<th>申告时间</th>
						<th>联系方式</th>
						<th>处理人</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="notSuretbody">

				</tbody>

			</table>
		</div>
		<div class="mRpage" id="notAcceptTable"></div>
		<div class="mRpage" id="notSureTable"></div>

	</div>

	<script type="text/javascript">
		$(function(){
			var dataList="[";
			$.ajax({
				type:"post",
				url:getPath()+"/selectDeptAll.do",
				async:false,
				success:function(data){
					$.each(data,function(k,v){
						dataList += "{id:"+v.id+",";
					    dataList += "pId:"+v.parentId+",";
					    dataList += "name:\""+v.name+"\"}";
					    if(k<data.length-1){
							dataList+=",";
						}
					})
					dataList+="]";
				}
			})
			var zTree;
			var zNodes = eval(dataList);
			var setting = {
				view: {
					selectedMulti: false,
					showIcon: false
				},
				data: {
					simpleData: {
						enable:true,
						idKey: "id",
						pIdKey: "pId",
						rootPId: ""
					}
				},
				callback: {
					beforeClick: function(treeId, treeNode) {
						var zTree = $.fn.zTree.getZTreeObj("tree");
						if (treeNode.isParent) {
							//样式
							var treeNode=zTree.getNodeByParam("OrgID", 0);  
						    zTree.selectNode(treeNode);  
						    zTree.expandNode(treeNode, true, false, false);  
							return false;
						}else {
							$("#eventDept").val(treeNode.name);
							$("#idDept").val(treeNode.id);
							if ($('#eventDept').fireEvent)
							{
							$('#eventDept').fireEvent('onchange');
							}
							else
							{
							$('#eventDept').change();
							}
							$("#tree").hide();
							return true;
						}
					}
				}
			};
			$(document).ready(function(){
				var t = $("#tree");
				t = $.fn.zTree.init(t, setting, zNodes);
			});
			//隐藏显示
			$("#eventDept").focus(function(){
				$("#tree").show();
				$("body").bind("mousedown", onBodyDown);
			})
			 function onBodyDown(event) {  
	            if (!(event.target.id == "menuBtn" || event.target.id == "tree" || $(event.target).parents("#tree").length > 0)) {  
	            	$("#tree").hide();
	            }  
	       	} 
			//申告添加的实现
			$(".rightBox").mouseover(function(){
				$(this).css("width","40px").css("cursor","pointer");
			}).mouseout(function(){
				$(this).css("width","");
			}).click(function(){
				window.location.href="<%=request.getContextPath()%>/toApplyAndAccept.do";
							})
			$("#startTime").datetimepicker();
			$("#endTime").datetimepicker();
			$(".authorMenuOpen").hide();
			//展示
			queryNotAcceptList();
			//搜索
			$("#button_search").click(function() {
				if ($("#eventDept").val()=="") {
					$("#idDept").val("");			
				}
				if ($("#notAcceptTable").is(":hidden")) {
					queryNotSureList();
				} else {
					queryNotAcceptList();
				}
			})
			//撤销
			$("#delFile").click(function() {
				deleteEventInfo();
			})
			//添加申告
			$("#addApply").click(function() {
				location.href = getPath() + "/serviceAdd.do";
			})
			//查看申告
			$("table").on("click", "#seeApply", function() {
				$(".check-win").show();
				$(".mask").show();
			})

		})
	</script>
</body>
</html>