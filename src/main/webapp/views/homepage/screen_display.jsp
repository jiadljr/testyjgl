<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>大屏展示</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/screen_display.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<style>

body{background:#020140; border-radius:0px;}
.header{ height:30px; font-family: "KaiTi" ;font-size:20px; color:#fff; line-height:30px;}
.header .ad_pic{ display:inline-block;float:left; width:36px; height:28px;margin-top:2px;}
.header span img{ display:inline-block;float:left; margin-top:4px; width:12px; height:12px;}
.header span{float:right; margin-right:1%;margin-top:10px; height:20px;font-size:12px; line-height:20px;}
.screenBox{ width:100%;}
.eventRemind{position:relative;width:67.35%; height:40%; margin:0.3% 0.2%; margin-bottom:0;float:left; border-radius:5px; overflow:hidden;background:#1c1b53;}
.eventRemind .table{ border:none;margin-top:24px;}
.eventRemind .table thead tr th{text-align:center; border:none; border-bottom:1px solid #a4a4a4; color:#f7f7f7;} 
.eventRemind .table tr td{ background:#1c1b53; color:#f7f7f7; border:none;}
.todayEventSource{ margin:0.3% 0.2%;position:relative; width:25%; height:40%;border-radius:5px; overflow:hidden; float:left;background:#1c1b53; }
.todayEventCount{margin:0.3% 0.2%;position:relative; width:31.2%; height:40%;border-radius:5px; overflow:hidden; float:left;background:#1c1b53;}  
.todayEventCount .tit{position:static;}
.todayEventCount #eventTypeS{ height:19%; width:100%; padding-top:10px;float:left; }
.todayEventCount #eventTypeS li{color:#fff; width:10%; margin:0 1.24%; float:left;text-align:center;}
.todayEventCount #eventTypeS .round{border-radius:50%;}
.todayEventCount #eventTypeS .blue{ background:#3f90e6;}
.todayEventCount #eventTypeS .purple{ background:#7265ab;}
.todayEventCount #eventTypeS .green{ background:#ff9900;}
.todayEventCount #eventTypeS .yellow{ background:#79c877;}
.todayEventCount #eventRank{width:100%;height:49%; text-align:center; color:#fff;float:left;padding-top:10px;}
.todayEventCount #eventRank li{ width:25%; float:left;}
.todayEventCount #eventRank li b{ display:inline-block; color:#fff; font-size:20px; border-radius:40% 0 40% 0; width:80%;}
.todayEventCount #eventRank li span{ width:100%; display:inline-block;height:20px;line-height:20px;}
.todayEventCount #eventRank .red b{background:#ff0000; }
.todayEventCount #eventRank .orange b{background:#ff6900;}
.todayEventCount #eventRank .yellow b{background:#ffd200;}
.todayEventCount #eventRank .blue b{background:#007eff;}
.todayEventCount #evrntGrade{ width:97%;height:19%; margin-left:2%;  float:left;} 
.todayEventCount #evrntGrade li {color:#fff; width:15%; margin:0 0.7%; float:left;text-align:center;}
.todayEventCount #evrntGrade .red{background:#fc2020;line-height:1;}
.todayEventCount #evrntGrade .blue{background:#1476d9;line-height:1;}
.todayEventCount #evrntGrade .green{background:#02d502;line-height:1;}
.QKmonthApplyCount{ width:100%;height:100%;position:absolute;top:0; left:0;opacity:1;z-index:2;}
.QKserviceTypeCount{width:100%;height:100%;position:absolute;top:0; left:0;opacity:0;z-index:-2;}
.eventMax{margin:0.3% 0.2%;position:relative; width:23%; height:57%;border-radius:5px; overflow:hidden; float:left;background:#1c1b53;}
.countSet{margin:0.3% 0.2%;width:44%; height:57%;float:left;position:relative;　}
.monthApplyCount{position:relative; width:100%; height:100%;border-radius:5px; overflow:hidden; float:left;background:#1c1b53;}
.serviceTypeCount{position:relative; width:100%; height:100%;border-radius:5px; overflow:hidden; float:left;background:#1c1b53;}
.dealerCondition{ margin:0.3% 0.2%;position:relative; width:31.2%;  height:57%;border-radius:5px; overflow:hidden; float:left;background:#1c1b53;}
.dealerCondition .table{ border:none;margin-top:40px;}
.dealerCondition .table thead tr th{border:none; text-align:center; border-bottom:1px solid #a4a4a4; color:#f7f7f7;} 
.dealerCondition .table tr td{ background:#1c1b53; color:#f7f7f7; border:none;}

.tit{ position:absolute; left:0; top:0; z-index:999; height:24px;width:100%; line-height:24px;background:#333265; color:#f7f7f7;}
.tit i{display:inline-block; float:left; height:16px; width:3px; border-radius:1.5px; background:#f7f7f7; margin:4px;}
.tit span{display:inline-block; float:left; height:24px; line-height:24px; font-size:14px;}
.trcoLors1 td{color:#ffd200 !important;}
.trcoLors2 td{color:#7265ab !important;}
.trcoLors3 td{color:red !important;} 
.trcoLors4 td{color:#05fde8 !important;}	

</style>

<body>
<div class="header"><img class="ad_pic" src="<%=request.getContextPath()%>/images/ad_logo.png" >北京安定医院<span><img src="<%=request.getContextPath()%>/images/logos.png" >北京乾坤博远科技有限责任公司</span></div>
<div class="screenBox">
	<input type="hidden" name="alertPageNow" id="alertPageNow" value = "0"/>
	<input type="hidden" name="dealPageNow" id="dealPageNow" value = "1"/>
	<div class="eventRemind ">
		<p class="tit"><i></i><span>事件提醒</span></p>
		<table class="table table-bordered table-hover text-center">
			<thead>
				<tr>
					<th width="140px">申告时间</th>
					<th>事件标题</th>
					<th width="100px">申告科室</th>
					<th width="80px;" >处理人</th>
					<th width="140px">结束时间</th>
					<th width="90px">剩余时间</th>

				</tr>
			</thead>
			<tbody id="alertTbody"></tbody>
		</table>									
	</div>
	<div class="todayEventCount ">
		<p class="tit"><i></i><span>今日事件统计</span></p>
		<ul id="eventTypeS">
			<li class="blue round"></li>
			<li>申告</li>
			<li class="purple round"></li>
			<li>受理</li>
			<li class="green round"></li>
			<li>处理</li>
			<li class="yellow round"></li>
			<li>完成</li>
		</ul>
		<ul id="eventRank">
			<li class="red">
				<b></b>
				<span>一级事件</span>
			</li>
			<li class="orange"> 
				<b></b>
				<span>二级事件</span>
			</li>
			<li class="yellow">
				<b></b>
				<span>三级事件</span>
			</li>
			<li class="blue">
				<b></b>
				<span>四级事件</span>
			</li>				
		</ul>
		<ul id="evrntGrade">
			<li class="red color"></li>
			<li>高</li>
			<li class="blue color"></li>
			<li>中</li>
			<li class="green color"></li>
			<li>低</li>
		</ul>
	</div>
	<div class="eventMax ">
		<div class="tit"><i></i><span>事件多发科室统计</span></div>
		<div id="dept" style="width: 100%;height:100%;"></div>
	</div>
	<div class="countSet">
		<div class="QKmonthApplyCount " >
			<div class="monthApplyCount " >
				<div class="tit"><i></i><span>近30天申告统计</span></div>
				<div id="time" style="width: 100%;height:107%;"></div>
			</div>
		</div>
		<div class="QKserviceTypeCount">	
			<div class="serviceTypeCount">
				<div class="tit"><i></i><span>服务类型统计</span></div>
				<div id="service" style="width: 100%;height:100%;"></div>
			</div>
		</div>
	</div>
	<div class="dealerCondition ">
		<p class="tit"><i></i><span>本月事件处理情况</span></p>				
		<table id="boomTn" class="table text-center">
			<thead>
				<tr>
					<th>处理人</th>
					<th>待处理</th>
					<th>已完成</th>
					<th>合计</th>
				</tr>
			</thead>
			<tbody id="operTbody"></tbody>
		</table>				
	</div>
	<a href="#" class="nextA"></a>
	<a href="#" class="nextB"></a>
	<a href="#" class="nextC"></a>
</div>
	<script>
		$(".screenBox").height($(document).height()-30);
		$(window).resize(function() {  
			$(".screenBox").height($(document).height()-30);
		}); 
	</script>
	<script>
		
	</script>
	<script type="text/javascript">
		$(function(){
			var QK_i=1;
			$(".nextC").click(function(){
				QK_i++;
				if(QK_i>2){
					QK_i=1;
				}
				if(QK_i=="1"){
					$(".QKmonthApplyCount").css({"opacity":1,"z-index":"2"});
					$(".QKserviceTypeCount").css({"opacity":0,"z-index":"-2"});
				}else if(QK_i=="2"){
					$(".QKmonthApplyCount").css({"opacity":0,"z-index":"-2"});
					$(".QKserviceTypeCount").css({"opacity":1,"z-index":"2"});
				}
			})
			var typeW=$('#eventTypeS li').width();
			var gradeW=$('#evrntGrade li').width();
			var rankW=$('#eventRank li b').width();
			var timerAuto=null;			
			clearInterval(timerAuto);
			timerAuto=setInterval(showScreenDisplay, 30000);
			setInterval(autoEvent, 30000)
			setInterval(function(){
				$(".nextA").click();
				$(".nextB").click();
				$(".nextC").click();
			}, 5000)
			$('#eventRank li b').css({'height':rankW+'px','line-height':rankW+'px' })
			$('#eventTypeS li').css({'height':typeW+'px','line-height':typeW+'px'})
			$('#evrntGrade li').css({'height':gradeW/2+'px','line-height':gradeW/2+'px' })
			showScreenDisplay();
			autoEvent();
			
			$(".tag-time").click(function(){
				$(".tag-time").removeClass("active");
				$(".tag-time img").attr("src","<%=request.getContextPath()%>/images/point-black.png");
				$(this).addClass("active");
				$(this).find("img").attr("src","<%=request.getContextPath()%>/images/point-blue.png");
			})
		})
	
	</script>
	
</body>
</html>