<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/css/layui.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
	<script src="<%=request.getContextPath()%>/js/ana_time_info.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script src="<%=request.getContextPath()%>/plugins/layui/layui.js" > </script>
	<script src='<%=request.getContextPath()%>/js/utilx.js'></script>
</head>
<body>

<style>
.QK_search{ width:100%; height:30px; position:relative;}
.QK_search input{ border:1px solid #aaa;}
#searchTree{ margin:0; width:240px;}
#serviceSidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
#sourceSidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
.tree_list{height:154px !important; overflow:auto;}
#searchMenu{margin-bottom:5px; position:relative;}
 .accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
.error{border-color:red !important;}
.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
#one{width:200px; height:180px; float:left}
#two{width:50px; height:180px; float:left}
#three{width:200px; height:180px; float:left}
a{text-decoration: none; }
.mRnewbot{ height:100%; float:left; width:100%;}
.scr_bot table {width:100%;}
.scr_bot table tr td:nth-child(1){width:90px; background:#ecf3fd;  border-left: 1px solid #696969; border-right: 1px solid #606069; }
.scr_top .table #tbody tr td{ height:24px; line-height:24px; padding:0;}
.scr_top .table{  height:100%; margin:0; float:left;}
.scr_top{ width:96%;margin:0 auto; height:54px; }
.scr_top .copy_box{width:15px; height:100%; float:left; box-sizing: border-box; border:1px solid #ccc; }
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	
.scr_bot{ height:200px; }
.relDeptbody{}
.relTable{ width:96%; margin:0 2%;height:70%;overflow-x:hidden;overflow-y:auto; }
.relTable.table tbody tr td:nth-child(2){ width:50px;}
.mRtablist { height:87%; position:relative;}
.table_t .color_none td:nth-child(1){ background:#fff; border-right: 1px solid #ccc;}
.table_t tr td:nth-child(1){width:90px; background:#ecf3fd;  border-left: 1px solid #696969; border-right: 1px solid #606069; }
.mRtablist table thead td{ line-height:0.7;}
.mRtablist table thead .spaical,.mRtablist table thead .spa_num{ line-height:2.4;}
.mRtablist table thead .spa_num{ width:50px;}
.mRtablist .ech,.mRtablist .sky{  height:100%;  width:100%;}
.tabItem{ opacity:0; height:100%; position:absolute;width:100%;z-index:-1;}
.showBlock{ opacity:1;z-index:2;}
.mRnewbot .mRtabs{  border-bottom: 1px solid #ccc;  width: 100%; height: 32px;}
.mRnewbot .mRtabs li{ position: relative; float:left; margin: 0 10px;  }
.mRnewbot .mRtabs li span{ height: 32px; display: block; line-height:32px; color:#ccc; cursor: pointer;}
.mRnewbot .mRtabs .nav_on span{  border-bottom: 1px solid #3eabe7; color:#3eabe7; }
.mRnewbot .nav_on i{border:4px solid #3eabe7; margin-left:-4px; border-left-color:#fff;border-right-color:#fff;border-bottom-color:#fff;  width:4px;position:absolute ; left: 50%; bottom: -8px; height:4px; display: block;  box-sizing: border-box; }
.border-blue{ box-shadow:1px 1px 8px #aaa; width:40%; float:left; margin:4px; height:100%;}
.title_bgk{background:#fbfbfb;color:#86b3e4; height:30px; border-bottom: 1px solid #e8e8e8; line-height:30px;}
.box_show{ box-shadow:1px 1px 8px #aaa; margin:4px; height:49%; float:left; width:55%; padding-bottom:5px;}
.num_statis{ width:98%; margin-left:6px; box-shadow:1px 1px 8px #aaa; height:40%;padding-bottom:10px; }
.keshi{width:98%; margin-left:6px;  box-shadow:1px 1px 8px #aaa; height:50%;margin-top:10px;}
.keshi .ke_inner{  width:60%; margin: 0 auto; height:38px;}
.relDeptbody { overflow-y:scroll; width:60%; margin: 0 auto;height:60%;}
.keshi .ke_inner table thead th{ text-align:center; }
.num_statis .dept{width :100%; height:100%; }
.ser_new{ width:98%; margin:0 1%;  box-shadow:1px 1px 8px #aaa;height:44%;padding：5px;}
.ser_new .serviceType{width:98% !important; height:98% !important;}
.ser_left,.ser_right{ width:48%; float:left; box-shadow:1px 1px 8px #aaa; height:44%; margin: 1%; paddding:0 50px;}
.tab_form .btn_sm{ width:44px; height:30px;border:none; outline:none; text-align:center;display: inline-block;margin:0;  border-radius: 4px;background:#3f90e6; color:#fff; }
    .scr_in{ height:200px;}
    .ser_left table,.ser_right table{ border:none;width:80%; margin: 0 auto;}
    .ser_left .table thead  th,.ser_right .table thead th{ padding:6px; width:80px; line-height:0.8; text-align:center;}
    .ser_left .table tbody  td,.ser_right .table tbody td{ padding:6px; line-height:0.8;}
    .tab_form span{ padding: 0 8px;  margin:5px 0; line-height:30px; font-size:12px;color:#5f5f5f;}
    .tab_form input{border:1px solid #aaa;  height:30px; border-radius:3px; margin:5px 0; color:#999;width:90px;padding:0 5px;}
</style>
<div class="mRight">
	<div class="mRpos">
		<ol class="breadcrumb">
		    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
		    <li><a href="javascript:;">查询分析</a></li>
		    <li class="active">事件统计</li>
		</ol>
	</div>
	<div class="mRnewbot">
		<ul class="mRtabs">
			<li class="nav_on"><span>时间统计</span><i></i></li>
			<li><span>科室统计</span><i></i></li>
			<li><span>服务类型</span><i></i></li>
		</ul>
		<div class="mRtablist">
			<div class="tabItem showBlock">
				<div class="border-blue roomCount">
					<p class="title_bgk">　科室申告数量统计</p>
					<div class="scr_top fixTable" >
						<table   id="scroll_top" class="table table-bordered table-hover text-center table_t">
	                		<thead>
	                			<tr>
		                			<td id="scr_time_spaical" rowspan="2" class="spaical">日期</td>
		                			<td id="scr_time_spa_num" rowspan="2" class="spa_num">数量</td>
		                			<td colspan="4">事件等级</td>
	                			</tr>
								<tr class="color_none">
									<td id="scr_time_spa_leavl1" >一级</td>
		                			<td id="scr_time_spa_leavl2">二级</td>
		                			<td id="scr_time_spa_leavl3">三级</td>
		                			<td style="width:80px;">四级</td>
								</tr>               		
	                		</thead>
                		</table>
               		</div>
	   
					<div class="relTable" >
						<table   id="scroll_top" class="table table-bordered table-hover text-center table_t">
	                		<tbody id="tbody">
	                		   
	                		</tbody>
	        
              			</table>		
					</div>
         	 </div>	
         
			 <div class="box_show">
				<p class="title_bgk">　申告分布时间统计</p>
					<div id="sky"  class="sky"></div>
			 </div>
			<div class="box_show">
				<p class="title_bgk">　月度事件统计</p>
				<div id="ech"  class="ech"></div>
			</div>	
		</div>
		<div class="tabItem">
			<div class="tab_form">
				<form id="form1">
					<span>起止时间:</span>
					<input value="" type="text" id= "startTime" placeholder="选择开始时间" >
					<span>至</span>
					<input value="" type="text" id= "endTime" placeholder="选择结束时间">
					<span>科室名称：</span>
					<input type="hidden" id="deptId" name="deptId">
					<input type="text" id="deptName" style="width:180px;"  name=deptName onclick="showDept()" readonly placeholder="选择需要查询的部门">
					<span>事件源：</span>
					<input type="hidden" id="sourceId" name="sourceId">
					<input type="text" id="sourceName" style="width:180px;"  name=sourceName onclick="showSource()" readonly placeholder="选择需要查询的事件源">
					<span>服务类型：</span>
					<input type="hidden" id="serviceId" name="serviceId">
					<input type="text" id="serviceName" style="width:180px;"  name=serviceName onclick="showService()" readonly placeholder="选择需要查询的服务类型">
					<input value="查询" type="button" id="deptButton" class="btn_sm">
					<input value="导出" type="button" id="deptEventReportButton" class="btn_sm"> 
				</form> 
			</div>

			<div class="num_statis">
				<p class="title_bgk">　科室申告数量统计</p>
				<div id="dept" class="dept" ></div>
			</div>
			<div class="keshi">
				<p class="title_bgk">　科室申告数量统计</p>
				<div class="ke_inner">
					<table class="table table-bordered table-hover text-center">
						<thead>
							<tr>
								<th>科室名称</th>
								<th>服务类型</th>
								<th>申告次数</th>
							</tr>
						</thead>
					</table>
				</div>
				<div class="relDeptbody">
					<table class="table">
						<tbody id="DeptTbody">						
						</tbody>
					</table>				
				</div> 
			</div>
		</div>
		<div class="tabItem">
				<div class="tab_form">
					<form>
						<span  >起止时间:</span>
						
						<input value="" type="text" id="startTim" placeholder="请选择开始时间">
						<span>至</span>
						<input value="" type="text" id="endTim" placeholder="请选择结束时间">
						<input value="查询" type="button" id="serviceButton" class="btn_sm">
						<input value="导出" type="button" id="eventOutButton" class="btn_sm">
					</form> 
				</div>
				<div class="ser_new" id="ser_new">
					<p class="title_bgk">　按服务类型统计</p>
					<div id="serviceType" class="serviceType" ></div>
				</div>
				<div class="ser_left">
					<p class="title_bgk">　按数量统计</p>
				
					<table id="idData" class="table table-bordered table-hover text-center" >
               			<thead>
               			<tr>
                			<td>序号</td>
                			<td>服务类型</td>
                			<td>申告次数</td>
               			</tr> 
               			<thead>   
               			<tbody id="myTabe">
               			
               		    </tbody>  
					</table>

               		<div class="Npage" id="Npagel"></div>
               	
               	</div>
               	<div id='websiteMain1'></div>
			

				<div class="ser_right">
			
				<p class="title_bgk">　按耗时统计</p>
				
					 	<table class="table table-bordered table-hover text-center">
               		<thead>
               			<tr>
                			<th>序号</th>
                			<th>服务类型</th>
                			<th>用时</th>
               			</tr>                		
               		</thead>
               		<tbody id="serviceMyType">
               	
               		</tbody>
               		
               	</table> 

				</div>
			</div>
		</div>	
	</div>
</div>
<script>
$(document).ready(function(){
    $(".mRtabs li").click(function(){
    $(".mRtabs li").eq($(this).index()).addClass("nav_on").siblings().removeClass('nav_on');
	$(".mRtablist .tabItem").css({"opacity":0,"z-index":-1}).eq($(this).index()).css({"opacity":1,"z-index":2});
    });
});
function showDept(){
	var deptId = $("#deptId").val();
	var deptName = $("#deptName").val();
	var dealAssetsUrl = getPath()+"/showAssetsDeal.do?assets_id="+deptId+"&property="+deptName;
	layer.open({
	    type: 2,
	    title: '部门信息',
	    scrollbar: true,
	    shadeClose: true,
	    area: ['670px', '460px'],
	    content: [dealAssetsUrl,'yes']
	  })
}
function showSource(){
	var sourceId = $("#sourceId").val();
	var sourceName = $("#sourceName").val();
	var dealAssetsUrl = getPath()+"/showSourcDeal.do?assets_id="+sourceId+"&property="+sourceName;
	layer.open({
	    type: 2,
	    title: '事件源信息',
	    scrollbar: true,
	    shadeClose: true,
	    area: ['670px', '460px'],
	    content: [dealAssetsUrl,'yes']
	  })
}
function showService(){
	var serviceId = $("#serviceId").val();
	var serviceName = $("#serviceName").val();
	var dealAssetsUrl = getPath()+"/showServiceDeal.do?assets_id="+serviceId+"&property="+serviceName;
	layer.open({
	    type: 2,
	    title: '服务类型信息',
	    scrollbar: true,
	    shadeClose: true,
	    area: ['670px', '460px'],
	    content: [dealAssetsUrl,'yes']
	  })
}
</script>
<script type="text/javascript">
$(function(){ 
	$("#startTime").datetimepicker({
		lang:'zh',
		timepicker : false,
		format : 'Y-m-d',
		formatDate : 'Y-m-d',
	});
	$("#endTime").datetimepicker({
		lang:'zh',
		timepicker : false,
		format : 'Y-m-d',
		formatDate : 'Y-m-d',
	});
	$("#startTim").datetimepicker({
		lang:'zh',
		timepicker : false,
		format : 'Y-m-d',
		formatDate : 'Y-m-d',
	});
	$("#endTim").datetimepicker({
		lang:'zh',
		timepicker : false,
		format : 'Y-m-d',
		formatDate : 'Y-m-d',
	});
	$("#serviceButton").click(function(){
		selectServiceTime();
		myType();
		serviceType();
	})
	$("#deptButton").click(function(){
		dept();
	})
	$("#eventOutButton").click(function(){
		var startTime = $("#startTim").val();
		var endTime = $("#endTim").val();
		location.href = getPath()+"/generatReport.do?start_time="+startTime+"&end_time="+endTime;
	})
	$("#deptEventReportButton").click(function(){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var deptId = $("#deptId").val();
		var deptName = $("#deptName").val();
		var sourceId = $("#sourceId").val();
		var serviceId = $("#serviceId").val();
		location.href = getPath()+"/exportDeptCount.do?start_time="+startTime+"&end_time="+endTime+"&deptId="+deptId+
				"&deptName="+deptName+"&sourceId="+sourceId+"&serviceId="+serviceId;
	})
	var moth = "";
	var value = "";
	$.ajax({
		type:"post",
		url:getPath()+"/selectMonth.do",
		async:false,
		success:function(data){
			$.each(data,function(k,v){
				moth += v.dates+",";
				value += v.amount+",";
		})
		}
	})
	var dataMonth = moth.split(",");
	var dataValue = value.split(",");
	var myChart = echarts.init(document.getElementById('ech'));   
var option = {
	    title: {
	        text: '',
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['申告次数']
	    },
	    toolbox: {
	        show: true,
	        feature: {
	            dataZoom: {
	                yAxisIndex: 'none'
	            },
	            dataView: {readOnly: false},
	            magicType: {type: ['line', 'bar']},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    xAxis:  {
	        type: 'category',
	        boundaryGap: false,
	        data: dataMonth
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel: {
	            formatter: '{value}'
	        }
	    },
	    series: [
	        {
	            name:'申告次数',
	            type:'line',
	            data:dataValue
	        },
	    ]
	};
myChart.setOption(option);  


sky();
dept();
myType();
serviceType();
selectDay();
selectServiceTime();
});  
</script>
	
	
</body>
</html>