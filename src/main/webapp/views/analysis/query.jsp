<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/query.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
</head>
<body>
	<%@ include file="../include/top.jsp"%>
	<!-- 页面主体main开始 -->
	<div id="main">
		<%@ include file="../include/query_mLeft.jsp"%>
		<div class="mRight">
			<div class="mRpos">
				<a href="">首页</a>
				<span class="glyphicon glyphicon-chevron-right" style="color: rgb(255, 140, 60);"></span>
				<a href="">服务管理</a>
				<span class="glyphicon glyphicon-chevron-right" style="color: rgb(255, 140, 60);"></span>
				<a href="">申告</a>
			</div>
			<div class="mQuery">
				<form action="" role="form">
					<div class="inp_1 form-group">
						<label for="startTime">开始时间：</label>
						<input type="text" id="startTime" class="form-control" placeholder="请输入开始时间">
					</div>
					<div class="inp_2 form-group">
						<label for="endTime">结束时间：</label>
						<input type="text" id="endTime" class="form-control" placeholder="请输入结束时间">
					</div>
					<div class="inp_3 form-group">
						<label for="stitle">服务类型：</label>
						<input type="text" id="stitle" class="form-control" placeholder="请输入标题">
					</div>
					<div class="inp_4 form-group">
						<label for="sperson">事件状态：</label>
						<input type="text" id="sperson" class="form-control" placeholder="请输入申告人">
					</div>
					<button class="btn"><span class="glyphicon glyphicon-search" style="color: rgb(255, 140, 60); ">Search</span></button>
				</form>					
			</div>

			<div class="mRbot">
				<table class="table table-striped">
			        <thead>
			          <tr>
			            <th><input type="checkbox" name="allSelect"></th>
			            <th>编号</th>
			            <th>标题</th>
			            <th>服务类型</th>
			            <th>申告部门</th>
			            <th>申告时间</th>
			            <th>事件描述</th>
			            <th>当前处理人</th>
			            <th>事件状态</th>
			            <th>操作</th>
			          </tr>
			        </thead>
			        <tbody class="ttbody">
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>	<tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn"></a></td>
			          </tr>	
			          <tr>
			            <td><input type="checkbox" name="item1"></td>
			            <td>1</td>
			            <td>897故障描述</td>
			            <td>李四</td>
			            <td>理疗科</td>
			            <td>2017-10-10</td>
			            <td>事件加急处理</td>
			            <td>张三</td>
			            <td>处理中</td>
			            <td><a href="" class="btn">修改</a></td>
			          </tr>			          
			        </tbody>
		        </table> 
			</div>

			<div class="mRpage">
				<ul class="pagination">
	              <li><a href="#">&laquo;</a></li>
	              <li><a href="#" class="active">1</a></li>
	              <li><a href="#">2</a></li>
	              <li><a href="#">3</a></li>
	              <li><a href="#">4</a></li>
	              <li><a href="#">5</a></li>
	              <li><a href="#">&raquo;</a></li>
		        </ul>
			</div>
		</div>
	</div>
	<!-- 页面主体main结束 -->
	<%@ include file="../include/foot.jsp"%>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/query.js"></script>
</body>
</html>