<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>项目看板</title>
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/base.css">
<style>
html,body{ width:100%; height:100%; overflow:hidden;}
body{background:#020140;}
.proMesBox{width:100%; height:100%;position:absolute;top:0; left:0;}
.proMesBox li{float:left; height:100%; position:relative;}
.header{ height:30px; font-family: "KaiTi" ;font-size:20px; color:#fff; line-height:30px;  position:relative;}
.header .ad_pic{ display:inline-block;float:left; width:36px; height:28px;margin-top:2px;}
.titItem{ width:700px; height:30px; position:absolute; left:50%; margin-left:-350px; top:0;z-index:1; text-align:center; overflow:hidden;}
.titItem .border{width: 640px;height: 200px;position:absolute; left:50%; margin-left:-350px;z-index:-1; top:0;border-top: 30px solid #454545;border-right: 30px solid transparent;border-bottom: 30px solid transparent;border-left: 30px solid transparent;}
.header span img{ display:inline-block;float:left; margin-top:4px; width:12px; height:12px;}
.header span{float:right; margin-right:1%;margin-top:10px; height:20px;font-size:12px; line-height:20px;}
.placeHold{position:absolute; left:50% ;bottom:10px;}	
.banner{position:relative; width:200px; height:150px; border:1px solid #ccc;overflow:hidden;}
	.banner ul{ position:absolute; left:0; top:0; height:150px;}
	.banner ul li{width:200px; height:150px; float:left;}
	.moveTran{transition: all 1s; }
	.top{position:relative; width:200px; height:150px; border:1px solid #ccc;overflow:hidden;}
	.top ul{ position:absolute; left:0; top:0; height:150px;width:200px}
	.top ul li{width:200px; height:150px; float:left;}
	.proMes{width:39%; height:49%; margin:0.25%; float:left;background:#10255f; position:relative;overflow:hidden;}
	.proDone{width:25.5%; height:49%; margin:0.25% ; float:left;background:#10255f; position:relative;overflow:hidden;}
	.proList{width:65%; height:49%;margin:0.25% ; float:left;background:#10255f; position:relative;overflow:hidden;}
	.proFlag{width:34%; height:49%;margin:0.25% ; float:left;background:#10255f; position:relative; overflow:hidden;}
	.chartBox{ width:100%; height:100%; float:left;position:absolute;top:0; left:0;}
	.chartBox li{ width:100%;float:left;}

	.timeAxisMes{width:100%; height:100%; position:absolute; left:0; top:0;}
	.timeAxisMes .item{ width:100%;height:10%; float:left;position:relative;}
	.timeAxisMes .item span,.timeAxisMes .item i,.timeAxisMes .item b{position:absolute;}
	.timeAxisMes .item .lineBar{ width:2px ; height:100%; background:#ccc;left:50%; margin-left:-1px;}
	.bigCle{width:10px; height:10px; border-radius:5px; left:50%;margin-left:-5px;top:50%;margin-top:-5px;background:#fff;}
	.smCle{ width:6px; height:6px; border-radius:3px; left:50%;margin-left:-3px; top:50%; margin-top:-3px; background:#656e8b;}
	.leftTime{ font-size:12px;color:#fff; right:50%;margin-right:10px;top:50%; margin-top:-6px;width:70px;}
	.leftTxt{font-size:12px;text-align:right;color:#fff;line-height:20px; height:20px; border-radius:10px; right:50%;margin-right:20px; padding:0 5px;top:50%;line-height:20px; margin-top:-10px;width:40%;background-color:rgba(255,255,255,0.2);}
	.rightTime{ font-size:12px;color:#fff; left:50%;margin-left:10px;top:50%; margin-top:-6px;width:70px;}
	.rightTxt{font-size:12px;text-align:left;color:#fff;line-height:20px; height:20px; border-radius:10px; left:50%;margin-left:20px; padding:0 5px;top:50%;line-height:20px; margin-top:-10px;width:40%;background-color:rgba(255,255,255,0.2);}
	.proMesTit{text-indent:2em; width:100%; height:30px; line-height:30px; color:#fff;float:left;}
	.proMesTxt{ text-indent:2em;width:50%; height:30px; line-height:30px; color:#fff;float:left;display:inline-block;}
 .newTable th{background:#0099cc;color:#fff;border-color:#eee;}
 .newTable {float:left;}
	 .newTable td{color:#fff;border-color:#eee;}
	.mT10{margin-top:10px;}
	  .newTable tbody tr .titTxt{ text-align: left; position:relative;}
         .level2 .titTxt{text-indent: 2em;}
        .level3 .titTxt{text-indent: 4em;}
        .level4 .titTxt{text-indent: 6em;}
        .level5 .titTxt{text-indent: 8em;}
        .level6 .titTxt{text-indent: 10em;}
        .level7 .titTxt{text-indent: 12em;}
        .level8 .titTxt{text-indent: 14em;}
        .level9 .titTxt{text-indent: 16em;}
        .level10 .titTxt{text-indent: 28em;}	
        .schedule{position:relative;width:100px; }
        .schedule span{width:60px; display:inline-block; float:left;text-align:center; position:absolute; left:50%; top:2px;margin-left:-30px;}
   	.schedule p{width:90px; height:5px;margin-top:-1px; background:#aaa;float:left;position:absolute; left:5px; top:20px;}
   	.schedule p b{ display:inline-block;height:5px; background:red;float:left;}
       .roolTable{position:absolute; top:-0px; left:0; } 
       .fixTable{flaot:left;width:100%; position:relative;}
</style>
</head>
<body>
<div class="header">
	<img class="ad_pic" src="<%=request.getContextPath()%>/images/ad_logo.png" />
	北京安定医院
	<div class="titItem">
		项目看板
		<div class="border"></div>
	</div>
	<span>
		<img src="<%=request.getContextPath()%>/images/logos.png" >
		北京乾坤博远科技有限责任公司
	</span>
</div>
<div class="main">
	<div class="proMes" id="proMes">
		<ul class="proMesBox" id="proMesBox">
	
		</ul>
		
	</div>
	<div class="proDone" id="proDone">
	<!-- 圆形图：selectProjectTypeStatus.do -->
		<div id="proDones" style="width:100%; height:100%;">
			
		</div>
	</div>
	<div class="proFlag"  id="amtFrom">
	  <!-- 两个的条形图：selectProjectCapitalTypeCount.do -->
	  <!-- 一个的条形图：selectProjectTypeCount.do -->
		<ul id="amtFromInner" class="chartBox moveTran">
			<li>
				<div id="amtSec" style="width:100%; height:100%;">
				
				</div>
			</li>
			<li>
				<div id="amtSecs" style="width:100%; height:100%;">
				
				</div>
			</li>
			<li>
				<div id="amtSecsss" style="width:100%; height:100%;">
				
				</div>
			</li>
		</ul>
	
	</div>
	<div class="proList" id="proList">
	<!-- 任务：selectProjectBoardQueryTask.do -->
		<table class="newTable roolTable" style="z-index:90">
			<thead>
				<tr>
					<th style="text-align:left;">任务名称</th>				
					<th width="120px;">负责人</th>
					<th width="120px;">状态</th>					
					<th width="190px;">起止时间</th>
					<th width="100px">进度</th>
				</tr>
			</thead>			
		</table>
		<div class="fixTable" id="fixTable"></div>			
	</div>
	<div class="proFlag" id="proFlag">
			<!-- 里程碑任务：selectProjectBoardMilestoneTask.do -->
			
	</div>
</div>


</body>
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>

<script>
$(".main").height($('body').height()-$(".header").height());
$(".chartBox li").height($("#amtFrom").height());
$(".timeAxis .li").width($(".proFlag").width());
$(".placeHold").width($("#proMes").height()*0.45).height($("#proMes").height()*0.45);
$(".runScroll").height($("#proList").height()-27);
const $publicHeight=$("#proList").height()-27;
var timRun=[];
var idClass=[];
$.ajax({
    url :getPath()+"/selectProjectBoardProjectQuery.do",
    dataType : 'json',
    type : "post",
    success : function(data) {  

    	$("#proMesBox").empty()
    	var str="";
    	var fromStr="";
    	var proType="";
    	var proSpeed="";
   		var timeOut="";
   		var IdArr=[]; 
    	for(var i=0;i<data.length;i++){
    		timeOut=(data[i].projTaskCount*29-$("#proList").height())/27-0;
    		idClass.push(data[i].projCode);
    		if(timeOut>10){
    			timRun.push(parseInt(timeOut)+3)
    		}else{
    			timRun.push(10);
    		}
			IdArr.push("placeHold"+i);	    		
    		proSpeed=(data[i].projSpeed-0)*100;
    		if(data[i].amtFrom==1){
    			fromStr="自有资金"
    		}else{
    			fromStr="财政拨款"
    		};
    		if(data[i].projStatus==90){
    			proType="草稿"
    		}else if(data[i].projStatus==91){
    			proType="正常"
    		}else if(data[i].projStatus==96){
    			proType="冻结"
    		}else{
    			proType="完成"
    		}
    		str+="<li><p class='proMesTit mT10'>项目名称："+data[i].projName+"</p>"+
    			"<p class='proMesTit'>起止时间："+data[i].dateStart+"至"+data[i].dateEnd+"</p>"+
    			"<span class='proMesTxt' >　负责人："+data[i].projManager+"</span>"+
    			"<span class='proMesTxt' >项目状态："+proType+"</span>"+
    			"<span class='proMesTxt' >项目类型：</span>"+
    			"<span class='proMesTxt' >资金来源："+fromStr+"</span>"+
				"<div class='placeHold' id='placeHold"+i+"' rel='"+proSpeed+"'></div></li>"				
    	}

      	$("#proMesBox").append(str);
      	$(".proMesBox li").width($("#proMes").width())
      	$("#proMesBox").width($("#proMes").width()*data.length)
      	$(".placeHold").width($("#proMes").height()*0.45).height($("#proMes").height()*0.45);
      	$(".placeHold").css('margin-left',-($(".placeHold").width()*0.5)+'px');
      	const placeWidth=$(".placeHold").width()*0.5;
      	for(var k=0;k<IdArr.length;k++){
      		placeHold($('#'+IdArr[k]).attr("rel")-0,placeWidth,IdArr[k])
      	}
   
       	run(timRun);
    },error:function(err){
    	alert('失败！');
    }
});
function showPrpList(key){
	var newLength=0;
	var setRun=null;
	clearInterval(setRun);
	$.ajax({
	     url:getPath() + "/getProjBoardInsideTaskList.do",
        data:{'projCode':idClass[key]},
        type:'post',
        dataType:'json',
        async:false,
        success:function(data) {
     		clearInterval(setRun);
			var arrData =data;
			newLength=data.length;
        	$("#fixTable").empty();
        	var tableStr="<table class='newTable roolTable' id='runScroll"+key+"'><thead><tr>"+
        	"<th style='text-align:left;'>任务名称</th><th width='120px;''>负责人</th>"+
			"<th width='120px;'>状态</th><th width='190px;'>起止时间</th><th width='100px'>进度</th>"+
			"</tr></thead><tbody id='planTbody' ></tbody></table>"
			$("#fixTable").append(tableStr)				
   			$("#planTbody").empty();	
			$("#planTbody").append("<tr rel='0'></tr>");
			if(data.length==0){
				$("tr[rel='0']").after("<tr rel='0'><td colspan='5'>暂无任务</td></tr>");
			}else{
				GetList(0,data); 
		    	if($publicHeight/27<newLength){
		    		var bulrLength=$publicHeight-(newLength+2)*27;
		    		let topSet=-0;		   
		    		var setRun=setInterval(function(){	    			
		    			topSet-=2.7;
		    			if(topSet<=bulrLength){
		    				topSet=-0;
		    				$("#runScroll"+key).css({"top":"-0px"});
		    				clearInterval(setRun);
		    			}else{
		    				$("#runScroll"+key).css({"top":topSet+"px"});		    		
		    			}
		    		}, 100)
		    	}
			}
        },error:function(){
        	alert("任务列表获取失败")
        }
	})
   function GetList(id,arr) {
        var arrA=[];              
        for (var i in arr) {
        	if(arr[i].parentId==null){
        		var a=0;
        	}
            if (arr[i].parentId != id){
            	 arrA.push(arr[i]);
            }    
        }
        for(var k=arr.length-1;k>=0;k--){
        	if(arr[k].level==null){
        		arr[k].level=1;
        	}
        	var schedule=parseInt(arr[k].taskSpeed*100)+'%';
        	var status = "";
			if(arr[k].taskStatus == "80"){
				status = "进行中";
			}else if(arr[k].taskStatus == "81"){
				status = "已超期";
			}else if(arr[k].taskStatus == "85"){
				status = "完成";
			}
        	if(arr[k].parentId==null||arr[k].parentId=="null"||arr[k].parentId==undefined||arr[k].parentId=="undefined"){
        		arr[k].parentId=0;
        	}
            if(arr[k].parentId==id){
            	var taskStr = "<tr class='level"+arr[k].level+"' rel='"+arr[k].id+"'>"+
				"<td class='titTxt'>"+	arr[k].nameTask+"</td>"+
				"<td width='120px;'>"+arr[k].extend2+"</td>"+
				"<td width='120px;'>"+status+"</td>"+
				"<td width='190px;'>"+getFormatDate(new Date(arr[k].dateStart), "yyyy-MM-dd")+"至"+getFormatDate(new Date(arr[k].dateEnd),"yyyy-MM-dd")+"</td>"+
				"<td class='schedule' width='100px;'>" +"<span>"+schedule+"</span><p><b style='width:"+schedule+"';></td></tr>";      
				$("tr[rel='"+id+"']").after(taskStr);
                GetList(arr[k].id,arrA)
            }
        }
    }
}
function rundist(key){	
	var setBlur=null;
	clearInterval(setBlur);
	$.ajax({
		data:{'projCode':idClass[key]},
		dataType:'json',
		async:false,
		type:'post',
		url: getPath()+"/selectProjectBoardMilestoneTask.do",
		success: function(data){
			var str="<ul class='timeAxisMes' id='timeAxisMes"+key+"'>";
			
			var len=data.length;
			$("#proFlag").empty();
		
			for(var i=0;i<len;i++){
				if(data[i].dateReal==undefined||data[i].dateReal=="undefined"){
					data[i].dateReal="暂未开始"
				}
				if(i%2==0){
					str+="<li class='item'>"+
					"<span class='leftTime'>"+data[i].dateReal+"</span>"+
					"<b class='lineBar'></b>"+
					"<i class='bigCle'></i>"+
					"<i class='smCle'></i>"+
					"<span class='rightTxt lineHide'>"+data[i].nameTask+"</span></li>"
				}else{
					str+="<li class='item'>"+
					"<span class='rightTime'>"+data[i].dateReal+"</span>"+
					"<b class='lineBar'></b>"+
					"<i class='bigCle'></i>"+
					"<i class='smCle'></i>"+
					"<span class='leftTxt lineHide'>"+data[i].nameTask+"</span></li>"
				}
			}
			str+="</ul>";
			
			$("#proFlag").append(str)
			if(len>=10){
				$(".item").height($("#proFlag").height()/10);
				$("#timeAxisMes").height(($("#proFlag").height()/10)*len);		
	    		var bulrLength=(($('body').height()/2)-30)-(len+1)*($("#proFlag").height()/10);
	    		let topSet=-27;

	    		var setBlur=setInterval(function(){	    			
	    			topSet-=2.7;
	    			if(topSet<=bulrLength){
	    				topSet=-27;
	    				$("#timeAxisMes"+key).css({"top":topSet+"px"});
	    				clearInterval(setBlur);
	    			}else{
	    				$("#timeAxisMes"+key).css({"top":topSet+"px"});

	    			}
	    		}, 100)
			}else{
				$(".item").height($("#proFlag").height()/i+1);
				$("#timeAxisMes").height($("#proFlag").height());
			}
			if(data.length==0){
				$("#proFlag").append("<li style='text-align:center;height:40px;line-height:40px;color:#fff'>暂无里程碑任务</li>")
			}
		},
		error:function(){
			alert('里程碑任务获取失败')
		}
	})
}


    function run(arr){
        var pos=0;
        var recordNum=0;
        let rundEr=null;
       	rundist(pos);
      	showPrpList(pos);
      	console.log(rundEr);
    	const totalSlides=$('#proMesBox li').length;
    	const sliderWidth =$('#proMes').width()
    	$('#proMesBox').width(sliderWidth*totalSlides*2);
        for(var i=0;i<arr.length;i++){
            recordNum+=arr[i];      
            rundEr=setTimeout(function (){
                	if(pos == 0){
            			$("#proMesBox").addClass('moveTran')
            		}
            	    pos++;
            	  	rundist(pos);
        	      	showPrpList(pos);
            	    if(pos==totalSlides){ 
            	    	pos = 0;
            	    	$("#proMesBox").removeClass('moveTran');
            	    	run(timRun);
            	    	
            	    }
            	    $("#proMesBox").css('left', -(sliderWidth*pos));
                 
                },recordNum*1000);
          
        }
    }


function banner(pos,idName,picName){
	const totalSlides=$(picName+' li').length;
	const sliderWidth =$(idName).width()
	$(picName).width(sliderWidth*totalSlides*2);
	var autoSlider = setInterval(slideRight, 3000);
	function slideRight(){
		if(pos == 0){
			$(picName).addClass('moveTran')
		}
	    pos++;
	    if(pos==totalSlides){ 
	    	pos = 0;
	    	$(picName).removeClass('moveTran')
	    }
	    $(picName).css('left', -(sliderWidth*pos));
	}
}
banner(0,'#banner','#banInner');
function topMove(pos,idName,picName){
	const totalSlides=$(picName+' li').length;
	const picHtml=$(picName).html();
	const sliderHeight =$(idName).height()
	$(picName).height(sliderHeight*totalSlides*2)
	var autoSlider = setInterval(slideTop,10000);
	function slideTop(){
		if(pos==0){
			$(picName).addClass('moveTran');
		}
		pos++;
		 $(picName).css('top', -(sliderHeight*pos));
	
		 if(pos==totalSlides-1){
			 setTimeout(runStart,1000)
			 function runStart(){
				 $(picName).removeClass('moveTran');
					$(picName).css('top', 0);
					pos=0;
			 }
				
		 }
	}
}
proDone()
function proDone(){
	var depChart = echarts.init(document.getElementById('proDones'));
	var dataMatch=[];
	$.ajax({
		data:{},
		dataType:'json',
		async:false,
		type:'post',
		url: getPath()+"/selectProjectTypeStatus.do",
		success: function(data){
			var obj={};
			for(var key in data){
				obj={
						value:data[key],
						name:key+":"+data[key]
				}
				dataMatch.push(obj);
			}
		},error:function(err){
	    	alert('失败！');
	    }
	});
	var data =dataMatch;
	option = {
	        title: {
	       show:false,
	    },
	    backgroundColor: 'rgba(255,255,255,0)',
	    tooltip: {
	        show: false,
	        trigger: 'item',
	        formatter: "{b}: {c} ({d}%)"
	    },
	   	toolbox: {
	        show: false,
	        feature: {
	            dataView: {readOnly: false},
	            restore: {},
	            saveAsImage: {}
	        }
	    },
	    legend: {
	    	left: '0',
	        bottom: '10',
	        orient: 'vertical',
	        itemGap: 12, //图例每项之间的间隔
	        itemWidth: 10,
	        itemHeight: 10, 
	        bottom: '0%',
	        color:'#fff',
	        data:data,
	        textStyle: {
	            color: [],
	            fontStyle: 'normal',
	            fontFamily: '微软雅黑',
	            fontSize: 12,
	        }
	    },
	    series: [{
	        type: 'pie',
	        selectedMode: 'single',
	        radius: ['40%', '58%'],
	        color: [ '#59ADF3', '#FF999A', '#FFCC67','#CC5962'],

	        label: {
	            normal: {
	                position: 'inner',
	                formatter: '{d}%',

	                textStyle: {
	                    color: '#fff',
	                    fontWeight: 'bold',
	                    fontSize: 12
	                }
	            }
	        },
	        labelLine: {
	            normal: {
	                show: false
	            }
	        },
	        data: data
	    }]
	};
	depChart.setOption(option);
}
function RunDis(id){
	var myData = [];
	var databeast = []
	var databeauty = []

	$.ajax({
		data:{},
		dataType:'json',
		async:false,
		type:'post',
		url: getPath()+"/selectProjectCapitalTypeCount.do",
		success: function(data){
			var dataBt=data[1];
			var dataBy=data[2];
			for(var key in dataBt){
				myData.push(key);
				databeast.push(dataBt[key]);
			}
			for(var key in dataBt){
				databeauty.push(dataBy[key]);
			}			
		},error:function(err){
	    	alert('失败！');
	    }
	});
	var depChart = echarts.init(document.getElementById(id));

	option = {
	    baseOption: {
	        title: {
	            text: '',
	            textStyle: {
	                color: '#fff',
	                fontSize: 16,
	            },
	        },
	        legend: {
	            data: ['自有资金', '财政拨款'],
	            top: 4,
	            bottom:'0',
	            left:'center',
	     
	            textStyle: {
	                color: '#fff',
	            },
	        },
	        tooltip: {
	            show: false,
	        },

	        toolbox: {
	            show: false,
	        },

	        grid: [{
	            show: false,
	            left: '4%',
	            top: 60,
	            bottom: 60,
	            containLabel: true,
	            width: '46%',
	        }, {
	            show: false,
	            left: '50.5%',
	            top: 80,
	            bottom: 60,
	            width: '0%',
	        }, {
	            show: false,
	            right: '4%',
	            top: 60,
	            bottom: 60,
	            containLabel: true,
	            width: '46%',
	        }, ],

	        xAxis: [{
	            type: 'value',
	            inverse: true,
	            axisLine: {
	                show: false,
	            },
	            axisTick: {
	                show: false,
	            },
	            position: 'top',
	            axisLabel: {
	                show: true,
	                textStyle: {
	                    color: '#B2B2B2',
	                    fontSize: 12,
	                },
	            },
	            splitLine: {
	                show: true,
	                lineStyle: {
	                    color: '#1F2022',
	                    width: 1,
	                    type: 'solid',
	                },
	            },
	        }, {
	            gridIndex: 1,
	            show: false,
	        }, {
	            gridIndex: 2,
	            type: 'value',
	            axisLine: {
	                show: false,
	            },
	            axisTick: {
	                show: false,
	            },
	            position: 'top',
	            axisLabel: {
	                show: true,
	                textStyle: {
	                    color: '#B2B2B2',
	                    fontSize: 12,
	                },
	            },
	            splitLine: {
	                show: true,
	                lineStyle: {
	                    color: '#1F2022',
	                    width: 1,
	                    type: 'solid',
	                },
	            },
	        }, ],
	        yAxis: [{
	            type: 'category',
	            inverse: true,
	            position: 'right',
	            axisLine: {
	                show: false
	            },
	            axisTick: {
	                show: false
	            },
	            axisLabel: {
	                show: false,
	                margin: 8,
	                textStyle: {
	                    color: '#9D9EA0',
	                    fontSize: 12,
	                },

	            },
	            data: myData,
	        }, {
	            gridIndex: 1,
	            type: 'category',
	            inverse: true,
	            position: 'left',
	            axisLine: {
	                show: false
	            },
	            axisTick: {
	                show: false
	            },
	            axisLabel: {
	                show: true,
	                textStyle: {
	                    color: '#9D9EA0',
	                    fontSize: 12,
	                },

	            },
	            data: myData.map(function(value) {
	                return {
	                    value: value,
	                    textStyle: {
	                        align: 'center',
	                    }
	                }
	            }),
	        }, {
	            gridIndex: 2,
	            type: 'category',
	            inverse: true,
	            position: 'left',
	            axisLine: {
	                show: false
	            },
	            axisTick: {
	                show: false
	            },
	            axisLabel: {
	                show: false,
	                textStyle: {
	                    color: '#9D9EA0',
	                    fontSize: 12,
	                },

	            },
	            data: myData,
	        }, ],
	        series: [{
                name: '自有资金',
                type: 'bar',
                barGap: 20,
                barWidth: 20,
                label: {
                    normal: {
                        show: false,
                    },
                    emphasis: {
                        show: true,
                        position: 'left',
                        offset: [0, 0],
                        textStyle: {
                            color: '#fff',
                            fontSize: 14,
                        },
                    },
                },
                itemStyle: {
                    normal: {
                        color: '#659F83',
                    },
                    emphasis: {
                        color: '#08C7AE',
                    },
                },
                data: databeast,
            },


            {
                name: '财政拨款',
                type: 'bar',
                barGap: 20,
                barWidth: 20,
                xAxisIndex: 2,
                yAxisIndex: 2,
                label: {
                    normal: {
                        show: false,
                    },
                    emphasis: {
                        show: true,
                        position: 'right',
                        offset: [0, 0],
                        textStyle: {
                            color: '#fff',
                            fontSize: 14,
                        },
                    },
                },
                itemStyle: {
                    normal: {
                        color: '#F68989',
                    },
                    emphasis: {
                        color: '#F94646',
                    },
                },
                data: databeauty,
            }
        ]          

	    },
	};
	depChart.setOption(option);
}
RunDis('amtSec')
RunDis('amtSecsss')

topMove(0,'#amtFrom','#amtFromInner');
function amtSecs(){
	var myData = [];
	var databeast = []
	var databeauty = []
	$.ajax({
		data:{},
		dataType:'json',
		async:false,
		type:'post',
		url: getPath()+"/selectProjectTypeCount.do",
		success: function(data){
			for (var i=0;i<data.length;i++){
				myData.push(data[i].projTypeName);
				databeast.push(data[i].amtFrom1);
				databeauty.push(data[i].amtFrom2);
			}
			
			
		},error:function(err){
	    	alert('失败！');
	    }
	});
	var depChart = echarts.init(document.getElementById('amtSecs'));
	option = {
			   "title": {
			      "text": "",
			      "left": "center",
			      "y": "10",
			      "textStyle": {
			        "color": "#fff"
			      }
			    },
			    "backgroundColor": "#1c2e40",
			    "color": "#384757",
			    "tooltip": {
			      "trigger": "axis",
			      "axisPointer": {
			        "type": "cross",
			        "crossStyle": {
			          "color": "#384757"
			        }
			      }
			    },
			    "legend": {
			      "data": [
			        {
			          "name": "自有资金",
			          "icon": "circle",
			          "textStyle": {
			            "color": "#7d838b"
			          }
			        },
			        {
			          "name": "财政拨款",
			          "icon": "circle",
			          "textStyle": {
			            "color": "#7d838b"
			          }
			        }
			      ],
			      "top": "10%",
			      "textStyle": {
			        "color": "#fff"
			      }
			    },
			    "xAxis": [
			      {
			        "type": "category",
			        "data":myData,
			        "axisPointer": {
			          "type": "shadow"
			        },
			        "axisLabel": {
			          "show": true,
			          "textStyle": {
			            "color": "#7d838b"
			          }
			        }
			      }
			    ],
			    "yAxis": [
			      {
			        "type": "value",
			        "name": "",
			        "nameTextStyle": {
			          "color": "#7d838b"
			        },
			        "min": 0,
			        "max": 100,
			        "interval": 10,
			        "axisLabel": {
			          "show": true,
			          "textStyle": {
			            "color": "#7d838b"
			          }
			        },
			        "axisLine": {
			          "show": true
			        },
			        "splitLine": {
			          "lineStyle": {
			            "color": "#7d838b"
			          }
			        }
			      },
			      {
			        "type": "value",
			        "name": "完成率",
			        "show": true,
			        "axisLabel": {
			          "show": true,
			          "textStyle": {
			            "color": "#7d838b"
			          }
			        }
			      }
			    ],
			    "grid": {
			      "top": "20%"
			    },
			    "series": [
			      {
			        "name": "自有资金",
			        "type": "bar",
			        "data":databeast,
			        "barWidth": "auto",
			        "itemStyle": {
			          "normal": {
			            "color": {
			              "type": "linear",
			              "x": 0,
			              "y": 0,
			              "x2": 0,
			              "y2": 1,
			              "colorStops": [
			                {
			                  "offset": 0,
			                  "color": "rgba(255,37,117,0.7)"
			                },
			                {
			                  "offset": 0.5,
			                  "color": "rgba(0,133,245,0.7)"
			                },
			                {
			                  "offset": 1,
			                  "color": "rgba(0,133,245,0.3)"
			                }
			              ],
			              "globalCoord": false
			            }
			          }
			        }
			      },
			      {
			        "name": "财政拨款",
			        "type": "bar",
			        "data": databeauty,
			        "barWidth": "auto",
			        "itemStyle": {
			          "normal": {
			            "color": {
			              "type": "linear",
			              "x": 0,
			              "y": 0,
			              "x2": 0,
			              "y2": 1,
			              "colorStops": [
			                {
			                  "offset": 0,
			                  "color": "rgba(255,37,117,0.7)"
			                },
			                {
			                  "offset": 0.5,
			                  "color": "rgba(0,255,252,0.7)"
			                },
			                {
			                  "offset": 1,
			                  "color": "rgba(0,255,252,0.3)"
			                }
			              ],
			              "globalCoord": false
			            }
			          }
			        },
			        "barGap": "0"
			      }
			    ]
			};
	depChart.setOption(option);
}
amtSecs()
function placeHold(id,width,idDom){
	var depChart = echarts.init(document.getElementById(idDom));

	var dataStyle = {
		    normal: {
		        label: {
		            show: false
		        },
		        labelLine: {
		            show: false
		        },
		        shadowBlur: 40,
		        shadowColor: 'rgba(40, 40, 40, 0.5)',
		    }
		};
		var placeHolderStyle = {
		    normal: {
		        color: 'rgba(44,59,70,1)',//未完成的圆环的颜色
		        label: {
		            show: false
		        },
		        labelLine: {
		            show: false
		        }
		    },
		    emphasis: {
		        color: 'rgba(44,59,70,1)'//未完成的圆环的颜色
		    }
		};
		option = {
		    title: {
		        text: id+'%',
		        x: 'center',
		        y: 'center',
		        textStyle: {
		            fontWeight: 'normal',
		            color: "#0bb6f0",
		            fontSize: 16
		        },
		        subtext: '项目进度',
		    },
		    color: ['#eb644b', '#313443', '#fff'],
		    tooltip: {
		        show: false,
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        show: false,
		        itemGap: 12,
		        data: ['01', '02']
		    },
		    toolbox: {
		        show: false,
		        feature: {
		            mark: {
		                show: true
		            },
		            dataView: {
		                show: true,
		                readOnly: false
		            },
		            restore: {
		                show: true
		            },
		            saveAsImage: {
		                show: true
		            }
		        }
		    },
		    series: [{
		            name: 'Line 1',
		            type: 'pie',
		            clockWise: false,
		            radius: [width-20,width-15],
		            itemStyle: dataStyle,
		            hoverAnimation: false,

		            data: [{
		                    value: id,
		                    name: '01'
		                }, {
		                    value: 100-id,
		                    name: 'invisible',
		                    itemStyle: placeHolderStyle
		                }

		            ]
		        }, {
		            name: 'Line 2',
		            type: 'pie',
		            animation: false,
		            clockWise: false,
		            radius: [width,width-2],
		            itemStyle: dataStyle,
		            hoverAnimation: false,
		            tooltip: {
		                show: false
		            },
		            data: [{
		                    value: 100,
		                    name: '02',
		                    itemStyle: {
		                        emphasis: {
		                            color: '#313443'
		                        }
		                    }
		                }, {
		                    value: 0,
		                    name: 'invisible',
		                    itemStyle: placeHolderStyle
		                }
		            ]
		        },


		    ]
		};
		depChart.setOption(option);
}

</script>
</html>