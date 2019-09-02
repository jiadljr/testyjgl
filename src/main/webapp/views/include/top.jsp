<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		        <div><a class="navbar-brand" href="#"><img src="images/logo.png" alt="" style="margin-top:-15px;width: 400px;height: 50px"></a></div>
		    </div>

		    <div class="news" style="float:right">
		    	<span class="glyphicon glyphicon-bullhorn" style="color: rgb(39, 85, 215);font-size:20px"></span><span class="badge" style="margin-left:5px">50</span>
		    	<div class="news_drow" style="display:none">
		    		<p>10:37 您有一条新的申告需要处理</p>
		    		<p>10:43 您有一条新的申告需要处理</p>
		    		<p>10:50 您有一条新的申告需要处理</p>
		    		<p>10:58 您有一条新的申告需要处理</p>
		    		<p>10:50 您有一条新的申告需要处理</p>
		    		<p>10:58 您有一条新的申告需要处理</p>
		    	</div>
		    </div>

		    <div class="memb">
		    	<span class="glyphicon glyphicon-user" style="color: rgb(39, 85, 215); ">&nbsp;</span>${user_name }
		    	<!-- 个人中心的下拉框 -->
			    <div class="perdrow">
					<div class="drow1"><a href="<%=request.getContextPath()%>/personalCenter.do">个人中心</a></div>
					<div class="drow2"><a href="javascript:logout()">退出登录</a></div>
			    </div>
		    </div>		    
		    
		    <div class="collapse navbar-collapse" id="example-navbar-collapse">
		        <ul class="nav navbar-nav">
                    <li class="active"><a href="<%=request.getContextPath()%>/views/index/index_auditor.jsp"><span class="glyphicon glyphicon-home" style="color: rgb(39, 85, 215);"></span>系统主页</a></li>
                    <li><a href="<%=request.getContextPath()%>/views/service/service.jsp"><span class="glyphicon glyphicon-filter" style="color: rgb(39, 85, 215); "></span>服务管理</a></li>
                    <li><a href="<%=request.getContextPath()%>/views/query/query.jsp"><span class="glyphicon glyphicon-search" style="color: rgb(39, 85, 215); "></span>查询分析</a></li>
                    <li><a href="<%=request.getContextPath()%>/seleDuty.do"><span class="glyphicon glyphicon-tasks" style="color: rgb(39, 85, 215); "></span>管理体系</a></li>
                    <li><a href="<%=request.getContextPath() %>/toConfigureMenu.do"><span class="glyphicon glyphicon-book" style="color: rgb(39, 85, 215); "></span>配置管理</a></li>
                </ul> 
		    </div>

		   </div>
		</nav>
	</div>
	<!-- top结束 -->
	
	<script>
	function logout(){
		if(confirm("确定退出登录？")){
			$.post("<%=request.getContextPath()%>/logout.do",function(){
				top.location.href ="<%=request.getContextPath()%>/toLogin.do";	
			})
		}
	}
	</script>