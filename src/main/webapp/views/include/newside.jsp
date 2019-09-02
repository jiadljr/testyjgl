<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/index_auditor.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/newside.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/index_auditor.js"></script>
	<script src="<%=request.getContextPath()%>/js/newside.js"></script>	
</head>
<body>
	<%@ include file="../include/top.jsp"%>
	<!-- 页面主体main开始 -->
	<div id="main">
		<div class="mLeft">
			<ul class="list-group" id="dh">
		      <!--   <li class="list-group-item text-center active">
		        	<a href=""><span class="glyphicon glyphicon-inbox" style="color: rgb(39, 85, 215); "></span>系统主页</a>
		        	<div class="dropdown">
		        		<ul class="list-group left">
		        			<li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-inbox" style="color: rgb(39, 85, 215); "></span>系统主页</a></li>
		        			<li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-phone-alt" style="color: rgb(39, 85, 215); "></span>服务台首页</a></li>
					        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-hand-right" style="color: rgb(39, 85, 215); "></span>运维人员主页</a></li>
					        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-film" style="color: rgb(39, 85, 215); "></span>大屏展示</a></li>
					        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-info-sign" style="color: rgb(39, 85, 215); "></span>人员视图</a></li>
				        </ul>
				        <ul class="list-group right">
		        			<li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-inbox" style="color: rgb(39, 85, 215); "></span>系统主页</a></li>
		        			<li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-phone-alt" style="color: rgb(39, 85, 215); "></span>服务台首页</a></li>
				        </ul>				        
		        	</div>
	        	</li>
		        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-phone-alt" style="color: rgb(39, 85, 215); "></span>服务台首页</a></li>
		        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-hand-right" style="color: rgb(39, 85, 215); "></span>运维人员主页</a></li>
		        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-film" style="color: rgb(39, 85, 215); "></span>大屏展示</a></li>
		        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-info-sign" style="color: rgb(39, 85, 215); "></span>人员视图</a></li> -->
		        
		    </ul>
		    <div class="hidbtn text-right"><span class="glyphicon glyphicon-transfer" style="color: rgb(255, 140, 60); "></span></div>
		</div>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="#">首页</a></li>
				    <li><a href="#">系统主页</a></li>
				    <li class="active">主页</li>
				</ol>
			</div>
			<div class="mRbot">
				<div class="unexamine">
					<table class="table table-bordered">
						<caption>待审核事件</caption>
						<thead>
							<th>事件标题</th>
							<th>事件内容</th>
						</thead>
						<tbody>							
							<tr>
								<td>事件001</td>
								<td>005申告事件</td>
							</tr>
							<tr>
								<td>事件001</td>
								<td>005申告事件</td>
							</tr>
							<tr>
								<td>事件001</td>
								<td>005申告事件</td>
							</tr>
							
						</tbody>
					</table>
				</div>
				<div class="newthing">
					<table class="table table-bordered">
						<caption>今日新增事件</caption>
						<thead>
							<th>事件标题</th>
							<th>事件内容</th>
						</thead>
						<tbody>							
							<tr>
								<td>事件001</td>
								<td>005申告事件</td>
							</tr>
							<tr>
								<td>事件001</td>
								<td>005申告事件</td>
							</tr>
							<tr>
								<td>事件001</td>
								<td>005申告事件</td>
							</tr>
							
						</tbody>
					</table>
				</div>
				<div class="yearstatis">
					<div class="title">事件年度统计</div>
					<div class="content">
						<div class="sg"><span class="glyphicon glyphicon-folder-open" style="font-size:30px;color: rgb(57, 42, 181);"></span><span>申告<b style="font-size:30px;color:blue">30</b></span></div>
						<div class="sl"><span class="glyphicon glyphicon-share" style="font-size:30px;color: rgb(57, 42, 181);"></span><span>受理<b style="font-size:30px;color:blue">20</b></span></div>
						<div class="cl"><span class="glyphicon glyphicon-ok" style="font-size:30px;color: rgb(57, 42, 181);"></span><span>处理<b style="font-size:30px;color:blue">10</b></span></div>
					</div>
				</div>
				<div class="servicestatis" id="service"></div>
			</div>
		</div>
	</div>
	<!-- 页面主体main结束 -->
	<%@ include file="../include/foot.jsp"%>
	
</body>
</html>