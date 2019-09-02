<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>第二版项目主体界面</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/search.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
</head>
<body>
	<!-- top开始 -->
	<div id="top">
		<nav class="navbar navbar-default" role="navigation">
		   <div class="container">
		    <div class="navbar-header">
		        <button type="button" class="navbar-toggle" data-toggle="collapse"
		                data-target="#example-navbar-collapse">
		            <span class="sr-only">切换导航</span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		            <span class="icon-bar"></span>
		        </button>
		        <div><a class="navbar-brand" href="#"><img src="images/tit.jpg" alt="" style="margin-top:-15px;"></a></div>
		    </div>
		    <div class="memb">
		    	<span class="glyphicon glyphicon-user" style="color: rgb(255, 140, 60); ">&nbsp;张三</span>
		    	<!-- 个人中心的下拉框 -->
			    <div class="perdrow">
					<div class="drow1"><a href="">个人中心</a></div>
					<div class="drow2"><a href="">退出登录</a></div>
			    </div>
		    </div>
		   
		    <div class="collapse navbar-collapse" id="example-navbar-collapse">
		        <ul class="nav navbar-nav">
                    <li class="active"><a href="index.html"><span class="glyphicon glyphicon-home" style="color: rgb(255, 140, 60);"></span>系统主页</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-filter" style="color: rgb(255, 140, 60); "></span>服务管理</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-search" style="color: rgb(255, 140, 60); "></span>查询分析</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-tasks" style="color: rgb(255, 140, 60); "></span>管理体系</a></li>
                    <li><a href="#"><span class="glyphicon glyphicon-book" style="color: rgb(255, 140, 60); "></span>配置管理</a></li>
                </ul> 
		    </div>

		   </div>
		</nav>
	</div>
	<!-- top结束 -->
	

	<!-- 页面主体main开始 -->
	<div id="main">
			<div class="mLeft">
				<ul class="list-group">
                    <li class="list-group-item text-center active"><span class="glyphicon glyphicon-inbox" style="color: rgb(255, 140, 60); "></span>系统主页</li>
                    <li class="list-group-item text-center"><span class="glyphicon glyphicon-phone-alt" style="color: rgb(255, 140, 60); "></span>服务台首页</li>
                    <li class="list-group-item text-center"><span class="glyphicon glyphicon-hand-right" style="color: rgb(255, 140, 60); "></span>运维人员主页</li>
                    <li class="list-group-item text-center"><span class="glyphicon glyphicon-film" style="color: rgb(255, 140, 60); "></span>大屏展示</li>
                    <li class="list-group-item text-center"><span class="glyphicon glyphicon-info-sign" style="color: rgb(255, 140, 60); "></span>人员视图</li>
                </ul>
                <hr>
                <div class="hidbtn text-right"><span class="glyphicon glyphicon-transfer" style="color: rgb(255, 140, 60); "></span></div>
			</div>

			<div class="mRight">
				<div class="mRpos">
					<a href="">首页</a>
					<span class="glyphicon glyphicon-chevron-right" style="color: rgb(255, 140, 60);"></span>
					<a href="">系统主页</a>
					<span class="glyphicon glyphicon-chevron-right" style="color: rgb(255, 140, 60);"></span>
					<a href="">统计</a>
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
							<label for="stitle">标题：</label>
							<input type="text" id="stitle" class="form-control" placeholder="请输入标题">
						</div>
						<div class="inp_4 form-group">
							<label for="sperson">申告人：</label>
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
                <th>审告人</th>
                <th>部门</th>
                <th>时间</th>
                <th>申告描述</th>
              </tr>
            </thead>
            <tbody class="ttbody">
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
              <tr>
                <td><input type="checkbox" name="item1"></td>
                <td>1</td>
                <td>xxx故障描述</td>
                <td>张三</td>
                <td>理疗科</td>
                <td>2017-10-10</td>
                <td>xxx请假，没有来</td>
              </tr>
            </tbody>
          </table> 
				</div>
				<div class="mRpage">
					<ul class="pagination">
              <li><a href="#">&laquo;</a></li>
              <li><a href="#">1</a></li>
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
	<!-- foot开始 -->
	<div id="foot">
            版权所有：智能信息化运维管理系统
    </div>
	<!-- foot结束 -->
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
</body>
</html>