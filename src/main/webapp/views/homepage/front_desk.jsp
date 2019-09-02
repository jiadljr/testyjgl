<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>第二版项目主体界面</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/front_desk.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	
</head>
<body>
<div class="mRight">
	<div class="mRpos">
		<ol class="breadcrumb">
		    <li><a href="javascript:;">首页</a></li>
		    <li><a href="javascript:;">系统主页</a></li>
		    <li class="active">服务台首页</li>
		</ol>
	</div>
	<div class="mRbot" style="overflow: auto;">
		<div class="mRpreson">
			<div class="wait_accept waitTable border_blue" onLoad="">
				<p class="title_bgk">　待受理　(<span id="acceptCount"></span>)
				<div class="tbodyBox">
					<table class="table table-hover text-center">
						<tbody id="acceptTbody"></tbody>				
					</table>
				</div>
				<ul class="acceptPage">
					<li class="prev prevA"></li>
					<li class="next nextA"></li>
				</ul>	
			</div>	
			
		<!--  	<div class="wait_sure waitTable border_blue">
				<p class="title_bgk">　待确认　(<span id="notSureCount"></span>)
				<div class="tbodyBox">
					<table class="table table-hover  text-center">
						<tbody id="notSureTbody"></tbody>				
					</table>
				</div>
				<ul class="acceptPage">
					<li class="prev prevB"></li>
					<li class="next nextB"></li>
				</ul>	
			</div>-->
			<div class="overTime waitTable border_blue">
				<p class="title_bgk">　超时事件　(<span id="overTimeCount"></span>)
				<div class="tbodyBox">
					<table class="table table-hover text-center">
						<tbody id="overTimeTbody"></tbody>				
					</table>
				</div>
				<ul class="acceptPage">
					<li class="prev prevC"></li>
					<li class="next nextC"></li>
				</ul>
			</div>
			<div class="todayNewEvent border_blue">
				<p class="title_bgk">　今日新增事件(<span id="eve_new"></span>)　已受理(<span id="eve_deal"></span>)　已完成(<span id="eve_finsh"></span>)</p>
				<div class=" fixTable">
					<table class="table table-bordered table-center">
						<thead>
							<tr>
								<th>事件编号</th>
								<th>标题</th>
								<th style="width:100px">申告部门</th>
								<th>申告时间</th>
								<th style="width:100px">服务类型</th>
								<th style="width:80px;">状态</th>
							</tr>
						</thead>
					</table>			
				</div>
				<div class=" relTable">
					<table class="table table-hover text-center table-bordered">
						<tbody id="todayTbody"></tbody>				
					</table>
				</div>
				<ul class="acceptPage">
					<li class="prev prevD"></li>
					<li class="next nextD"></li>
				</ul>	
			</div>
		<div class="eventRank border_blue" >
			<p class="title_bgk">　事件等级</p>
			<ul id="eventRank">
				<li class="red">
					<p style="line-height:1; overflow:hidden">0<br>一级事件</p>
					
				</li>
				<li class="orange">
					<p>0</p>
					<p>一级事件</p>
				</li>
				<li class="yellow">
					<p>0</p>
					<p>一级事件</p>
				</li>
				<li class="blue">
					<p>0</p>
					<p>一级事件</p>
				</li>
				
			</ul>
 		</div>
		<div class="per_list border_blue" >
			<p class="title_bgk">　运维人员</p>
			<div class="tulBox">
				<ul id="tul">
				
				</ul>
			</div>
			
 		</div>
					
				
		</div>
		
	</div>
</div>
<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/front_desk.js"></script>
	<script>
		$(function(){
			var pHeight=$('.per_list').height();
			var eHeight=$('.eventRank').height();
			var wHeight=$('.waitTable').height();
			var tHeight=$('.title_bgk').height();
			var newHeightA=eHeight-tHeight;
			var newHeightB=pHeight-tHeight;
			var newHeight=wHeight-tHeight-25;
			$('.tulBox').height(newHeightB);
			$('.tbodyBox').height(newHeight);
			$('#eventRank').height(newHeightA);
		})
	</script>
	<script type="text/javascript">
		$(function(){			
			showFrontDesk();
			function GetUrlStr(name){
				  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
				   var r = window.location.search.substr(1).match(reg);
				   if(r!=null)
				   return unescape(r[2]);
				   return null;
			}
			function layerTips(tipsTxt){
				layer.open({
		            type:4,
		        	time:2000,
		            tipsMore: true,
		            title: '提示',
		            tips:[2,"#eee"],
		            closeBtn:0,
		            area: ['200px', '70px'],
		            shade: 0,
		            maxmin: false,
		            offset: 'rb',
		            content: ['<p style="color:#333"> 提示</p><p style="color:#666">'+tipsTxt+'</p>', '#eveBox']
		        });
			}
			
			if(GetUrlStr('cid')=='1'){
				layerTips('受理成功')
			}
			if(GetUrlStr('cid')=='2'){
				layerTips('处理成功')
			}
			if(GetUrlStr('cid')=='3'){
				layerTips('转派成功')
			}
			if(GetUrlStr('cid')=='4'){
				layerTips('确认成功')
			}
			if(GetUrlStr('cid')=='5'){
				layerTips('确认成功')
			}
		})
	</script>
</body>
</html>

