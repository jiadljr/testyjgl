<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>部门统计</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>	
		
	</style>	
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">查询分析</a></li>
				    <li class="active">时间查询</li>
				</ol>
			</div>
			
			<div class="mRbot">
				<div class="tag" style="height:70%;">
					<div id="deal" style="width: 46%;height:96%; margin:2%; border: 1px solid #ccc; float:left;"></div>
					<div id="accept" style="width: 46%;height:96%; margin:2%;border: 1px solid #ccc; float:left;"></div>
				
				
				
					<!--<div class="tag-time"><img alt="pos" src="<%=request.getContextPath()%>/images/point-black.png">季</div>
					<div class="tag-time"><img alt="pos" src="<%=request.getContextPath()%>/images/point-black.png">月</div>
					<div class="tag-time active"><img alt="pos" src="<%=request.getContextPath()%>/images/point-blue.png">日</div>
					-->
				</div>
				<div class="mRcont">
					<div class="mRcont-title">日度事件统计结果</div>
				</div>				 
			</div>
		</div>
	<script type="text/javascript">

			$(function(){
				var dealDataName="";
				var dealDataCount="";
				var acceptDataName="";
				var acceptDataCount="";
				$.ajax({
					type:"post",
					url:getPath()+"/EventCount.do",
					async:false,
					success:function(result){
						var dealCount=result.dealCount;
						for (var i = 0; i < dealCount.length; i++) {
							dealDataName+=","+dealCount[i].name;
							dealDataCount+=","+dealCount[i].extend1;
						}
						var acceptCount=result.acceptCount;
						for (var j = 0; j < acceptCount.length; j++) {
							acceptDataName+=","+acceptCount[j].name;
							acceptDataCount+=","+acceptCount[j].extend1;
						}
					}
				})
				dealDataName=dealDataName.substr(1).split(",");
				dealDataCount=dealDataCount.substr(1).split(",")
				acceptDataName=acceptDataName.substr(1).split(",");
				acceptDataCount=acceptDataCount.substr(1).split(",")
				//处理统计
				var myDealChart = echarts.init(document.getElementById('deal'));
				var dealOption = {
				    color: ['#3398DB'],
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : dealDataName,
				            axisTick: {
				                alignWithLabel: true
				            }
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'直接访问',
				            type:'bar',
				            barWidth: '60%',
				            data:dealDataCount
				        }
				    ]
				};
				myDealChart.setOption(dealOption);
				//受理统计
				var myAcceptChart = echarts.init(document.getElementById('accept'));
				var acceptOption = {
				    color: ['#3398DB'],
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : acceptDataName,
				            axisTick: {
				                alignWithLabel: true
				            }
				        }
				    ],
				    yAxis : [
				        {
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'直接访问',
				            type:'bar',
				            barWidth: '60%',
				            data:acceptDataCount
				        }
				    ]
				};
				myAcceptChart.setOption(acceptOption);
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