<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cmn-Hans">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运维人员主页</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ana_oper.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.print.min.css" rel='stylesheet' media='print' >
    <script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
   	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
    <script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/lib/moment.min.js'></script>
	<script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.js'></script>
	<script src="<%=request.getContextPath()%>/js/ana_operation_info.js"></script>
	<script src='<%=request.getContextPath()%>/js/utilx.js'></script>
	<style type="text/css">

	</style>
</head>
<body>
<div class="mRight">
	<div class="mRpos">
		<ol class="breadcrumb">
		    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
		    <li><a href="javascript:;">系统主页</a></li>
		    <li class="active">运维人员主页</li>
		</ol>
	</div>
	<div class="dealTab">
		<p class="dealTab_tit" >　我的事件</p>
		<div class="dealTabInnerTop">
		
			<ul id="dealTabList">
				<li class="accept_Wait cur" id="acceptWait">待办(<span></span>)</li>
				<li class="deal_Wait" id="dealWait">已办(<span></span>)</li>
			</ul>
			<ul id="MonthDeal">
				<li class="yes">
					<p>${finishDealCount.dealEnd }</p>
					<span>本月已处理</span>
				</li>
				<li class="none">
					<p>${finishDealCount.dealNot }</p>
					<span>本月未处理</span>
				</li>
			</ul>
			<form action="" role="form" class="form-horizontal " method="post" style="width: 720px;">
				<label for="eventPer" >申告人:　</label>
				<input id="eventUser" name="eventUser" class="form-control" />
				<a class="btn btn-primary" id="button_search"><img
					src="<%=request.getContextPath()%>/images/search.png">搜索</a>				
			</form>
		</div>
		<div class="tableItems" id="commission">
			<div class="tableItemsInner">
					<table class="table table-bordered table-hover table-center">
				<thead>
					<tr>
						
						<th>事件编号</th>
						<th>事件标题</th>
						<th>申告科室</th>
						<th width="160px">响应时间</th>
						<th width="100px">事件等级</th>
						<th width="80px">优先级</th>
						<th width="160px">最迟解决时间</th>
						<th width="80px">处理人</th>
						<th width="60px">操作</th>
					</tr>
				</thead>
				<tbody id="commTbody"></tbody>	
			</table>
			</div>
		
			<ul class="acceptPage">
				<li class="prev prevA"></li>
				<li class="next nextA"></li>
			</ul>
		</div>
		<div class="tableItems" id="haveDone">
			<div class="tableItemsInner">
				<table class="table table-bordered table-center">
					<thead>
						<tr>
							<th>事件编号</th>
							<th>事件标题</th>
							<th>申告科室</th>
							<th>申告时间</th>
							<th>响应时间</th>
							<th width="100px">解决时长</th>
							<th>解决时间</th>
							<th width="120px">实际用时</th>
							<!-- <th>事件涉及处理人</th>-->
							<th width="60px">操作</th>
						</tr>
					</thead>
					<tbody id="haveTbody"></tbody>
				</table>
			</div>
				<ul class="acceptPage">
				<li class="prev prevB"></li>
				<li class="next nextB"></li>
			</ul>
		</div>
	</div>
	<div class="taskList" >
		<p class="title_bgk">　我的任务<a class="btn btn-sm btn_green" style="float:right;padding:4px 10px; margin-top:1px; margin-right:5px;" href="javascript:void(0)" onclick="haveMoreTask()">更多</a></p>
			<table class="table table-bordered table-center">
				<thead>
					<tr>
						<th>任务标题</th>				
						<th>项目名称</th>
						<th>任务起止时间</th>					
						<th>任务进度</th>
					</tr>
				</thead>
				<tbody id="taskTbody"></tbody>
			</table>
		
	</div>	
	<div class="mycal">
		<p class="mycal_tit">我的排班表 <right><a href="#" class="btn btn-sm btn_blue" style="float:right;padding:4px 10px; margin-right:5px;" onclick="dutyQuery()">查看排班</a></right></p>
		<div class="mycalld"></div>
	</div>
		<div class="taskList"   style="margin-top:10px;">
		<p class="title_bgk">　工作计划<a class="btn btn-sm btn_yellow" style="float:right;padding:4px 10px; margin-top:1px; margin-right:5px;" href="javascript:void(0)" onclick="moreClick()">更多</a></p>
			<table class="table table-bordered table-center" style="margin-bottom:0;">
				<thead>
					<tr>
						<th style="text-align:left;">标题</th>				
						<th width="120px;">类型</th>
						<th width="120px;">计划类型</th>					
						<th width="190px;">提交时间</th>
					</tr>
				</thead>
			
			</table>
			<div style="width:100%; height:150px; position:relative;overflow:hidden; " id="runScroll">
				<table class="table table-bordered table-center" style="position:absolute; top:0; left:0; z-index:-1;  ">
		
					<tbody id="planTbody" style="height:142px;">
					
					</tbody>
				</table>
			
			</div>
		
	</div>	
	<div class="mycal" style="margin-top:10px;">
		<p class="mycal_tit">推送查看 <a href="#" class="btn btn-sm btn_red" style="float:right;padding:4px 10px; margin-right:5px;" onclick="viewMore()">查看更多</a></p>
		<ul class="pushlook">
		</ul>
	</div>
	<div class="deal_count">
		<p class="title_bgk">　最近30天处理事件统计</p>
		<div id="dealCount" class="dealCount" ></div>
	</div>

</div>
<script type="text/javascript">
var tabHeight=$('.dealTab').height();
var dealHeight=$('.dealTabInnerTop').height();
var titHeight=$('.dealTab_tit').height()+5;
$('.tableItems').height(tabHeight-dealHeight-titHeight);
$('.tableItemsInner').height(tabHeight-dealHeight-titHeight-30)
$(".tableItems:eq(0)").show();
$("#dealTabList li:eq(0)").addClass('cur');
	$("#dealTabList li").click(function(){
    $("#dealTabList li").eq($(this).index()).addClass("cur").siblings().removeClass('cur');
	$(".tableItems").hide().eq($(this).index()).show();
});
$(function(){
	$("#button_search").click(function() {
		if ($("#haveDone").is(":hidden")) {
			deptTbody();
		} else {
			solveTbody();
		}
	})
	mycalld();
	dealCount();
	deptTbody();
	solveTbody();
	showMineTask();
})
function moreClick(){
	location.href = getPath()+"/workPlanList.do?planId=1";
}
function viewMore(){
	location.href = getPath()+"/queryWorkSelect.do?pushId=1";
}
</script>
<script>
$(function () {
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
	
	
});
function dutyQuery(){
	var jump_height="550px";
	var jump_list = getPath()+"/seleDuty.do";
	layer.open({
	    type: 2,
	    title: '排班表',
	    shadeClose: false,
	    area: ['900px', jump_height],
	    content: jump_list
	  })
}
function haveMoreTask(){
	location.href = getPath()+"/toProjTaskPage.do?taskId=1";
}
</script>

</body>
</html>