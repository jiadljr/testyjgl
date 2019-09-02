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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_deal_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">处理</li>
				</ol>
			</div>

			<div class="mQuery-2">
				<form action="" role="form" class="form-horizontal" method="post">
				   <input type="hidden" id="pages" name="pages" value="${pages }">
                   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="form-group col-sm-4" style="margin-left:-60px">
						<label for="dataStart" class="col-sm-3">日期：</label>
						<div class="col-sm-4">
							<input type="text" id="startTime" name="startTime" class="form-control">
						</div>
						<div class="col-sm-1">——</div>
						<div class="col-sm-4">
							<input type="text" id="endTime" name="endTime" class="form-control">
						</div>
					</div>
					<div class="form-group col-sm-2">
						<label for="eventSource" class="col-sm-5">事件等级：</label>
						<div class="col-sm-7">
							<select id="eventLevel" name="eventLevel" class="form-control">
								<option value="">请选择事件等级</option>
							    <option value="1">一级</option>
							    <option value="2">二级</option>
							    <option value="3">三级</option>
							    <option value="4">四级</option>
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
					<div class="form-group col-sm-2" style="margin-left:-20px">
						<label for="file_code" class="col-sm-4" >部门：</label>
						<div class="col-sm-6" style="position:relative">
							<input type="hidden" id="idDept" name="idDept">
							<input type="text" id="eventDept" name="eventDept" onChange="dealChange()" class="form-control">
							<ul id="tree" class="ztree" style="width:100%;z-index:10000; overflow:auto;position:absolute;top:36px;background-color:#eee;display:none"></ul>
						</div>
					</div>				
										
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>	
				</form>								
			</div>
			
			<%-- <div class="btnout">
                <a class="btn btn-primary btn-sm" id="addApply"><img alt="" src="<%=request.getContextPath()%>/images/add-apply.png">添加申告</a>
            </div> --%>

			<div class="mRbot" style="top:85px;height: 68%; margin-top:15px;">
				<div class="tag">
					<%-- <div class="acceptWait"><img alt="" src="<%=request.getContextPath()%>/images/orders-off.png">未接单</div> --%>
					<div class="dealWait"><img alt="" src="<%=request.getContextPath()%>/images/orders-on.png">已接单</div>
				</div>				
               <!--  <table class="table table-bordered table-hover table1" style="display:none">
                	<thead>
                		<tr>
	                		<th>事件编号</th>
	                		<th>标题</th>
	                		<th>申请部门</th>
	                		<th>申告人</th>
	                		<th>联系方式</th>
	                		<th>受理时间</th>
	                		<th>优先级</th>
	                		<th>事件等级</th>
	                		<th>操作</th>
                		</tr>                		
                	</thead>
                	<tbody id="myTable">
                		
                	</tbody>
                		
                </table> -->
                <table class="table table-bordered table-hover table2" >
                	<thead>
                		<tr>
	                		<th>事件编号</th>
	                		<th>申告部门</th>
	                		<th>申告人</th>
	                		<th>联系方式</th>
	                		<th>优先级</th>
	                		<th>事件等级</th>
	                		<th>标题</th>
	                		<th>事件描述</th>
	                		<th>操作</th>
                		</tr>                		
                	</thead>
                	<tbody id="tbody">
                	
                	</tbody>
                		
                </table>
			</div>
<!-- 
			<div class="mRpage" id="page2"></div> -->
			<div class="mRpage" id="page3"></div>
		</div>
	
	<script type="text/javascript">
		$(function(){
			/* $("#page3").hide(); */
			/* queryNotList(); */
			queryEndList();
			$("#button_search").click(function(){
				queryEndList();
			})
		})
	</script>
</body>
</html>