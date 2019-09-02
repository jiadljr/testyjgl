function sky(){
	var moth = "";
	var value = "";
	$.ajax({
		type:"post",
		url:getPath()+"/selectSky.do",
		async:false,
		success:function(data){
			$.each(data,function(k,v){
				moth += v.zeroPoint+",";moth += v.onePoint+",";
				moth += v.twoPoint+",";moth += v.threePoint+",";
				moth += v.fourPoint+",";moth += v.fivePoint+",";
				moth += v.sixPoint+",";moth += v.savePoint+",";
				moth += v.eightPoint+",";moth += v.ninePoint+",";
				moth += v.tenPoint+",";moth += v.elevenPoint+",";
				moth += v.twelvePoint+",";moth += v.thirteenPoint+",";
				moth += v.fourteenPoint+",";moth += v.fifteenPoint+",";
				moth += v.sixteenPoint+",";moth += v.seventeenPoint+",";
				moth += v.eighteenPoint+",";moth += v.nineteenPoint+",";
				moth += v.twentyPoint+",";moth += v.twentyOnePoint+",";
				moth += v.twentyTwoPoint+",";moth += v.twentyThreePoint+",";
		})
		}
	})
	var dataMonth = moth.split(",");
	var myChart = echarts.init(document.getElementById('sky')); 
	var option = {
		    title: {
		        text: '',
		    },
		    tooltip: {
		        trigger: 'axis'
		    },
		    legend: {
		        data:false
		    },
		    toolbox: {
		        show: false,
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
		        data: ["0时","1时","2时","3时","4时","5时","6时","7时","8时","9时","10时","11时","12时","13时","14时","15时","16时","17时","18时","19时","20时","21时","22时","23时"]
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
		            data:dataMonth
		        },
		    ]
		};
	myChart.setOption(option);  
}
function dept(){
	//getPath()+"/countDeptApply.do",
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var deptId = $("#deptId").val();
	var deptName = $("#deptName").val();
	var sourceId = $("#sourceId").val();
	var serviceId = $("#serviceId").val();
	var type = $("#DeptTbody").empty();
	var moth = "";
	var value = "";
	$.ajax({
		type:"post",
		url:getPath()+"/selectServiceDeptCount.do",
		data:{"startTime":startTime,"endTime":endTime,"deptName":deptName,"deptId":deptId,"sourceId":sourceId,"serviceId":serviceId},
		async:false,
		success:function(data){
			var tr ="";
			$.each(data,function(k,v){
				var idDe = v.idDe;
				var mos= "";
				var valu;
				if(v.deptName != undefined){
					moth += v.deptName+",";
					value += v.coun+",";
				}
				if(idDe != undefined){
					for(var i = 0;i<idDe.length;i++){
						if(idDe[i] != ""){
							mos += idDe[i]+",";
							valu += 0+",";
						}
					}
					moth = moth+mos;
					value = value +valu;
				}
		})
		 for(var i = 0;i<data.length;i++){
			 var nameDept = data[i].deptName;
			 nameDept = isnull(nameDept);
			 var serviceName = data[i].serviceName;
			 serviceName = isnull(serviceName);
			 var serviceCount = data[i].coun;
			 serviceCount = isnull(serviceCount);
			 tr +="<tr>" +
			 		"<td align='center'>"+nameDept+"</td>" +
			 		"<td align='center'>"+serviceName+"</td>" +
			 		"<td align='center'>"+serviceCount+"</td>" +
			 		"</tr>";
		 }
			type.append(tr);
		}
	})
	var dataMonth = moth.split(",");
	var dataValue = value.split(",");
	var myChart = echarts.init(document.getElementById('dept')); 
	 var option = {
	            title: {
	                text: ''
	            },
	            tooltip: {},
	            legend: {
	                data:false
	            },
	            xAxis: {
	                data: dataMonth
	            },
	            yAxis: {},
	            series: [{
	                name: '申告次数',
	                type: 'bar',
	                data: dataValue
	            }]
	        };
	myChart.setOption(option);
}
function serviceType(){
	var startTime = $("#startTim").val();
	var endTime = $("#endTim").val();
	var moth = "";
	var value = "";
	$.ajax({
		type:"post",
		url:getPath()+"/selectDepId.do",
		data:{"startTime":startTime,"endTime":endTime},
		async:false,
		success:function(data){
			$.each(data,function(k,v){
				var a = v.name;
				if(a == null){
					a = "";
				}
				moth += a+",";
				value += v.extend1+",";
		})
		}
	})
	var dataMonth = moth.split(",");
	var dataValue = value.split(",");
	var myChart = echarts.init(document.getElementById('serviceType')); 
	 var option = {
	            tooltip: {},
	            legend: {
	                data:false
	            },
	            xAxis: {
	                data: dataMonth
	            },
	            yAxis: {},
	            series: [{
	                name: '申告次数',
	                type: 'bar',
	                data: dataValue
	            }]
	        };
	myChart.setOption(option);
}
function selectDay(){
	$.ajax({
		type:"post",
		url:getPath()+"/selectDay.do",
		async:false,
		success:function(data){
			$("#tbody").empty();
			for(var i=0; i<data.length;i++){
				var tr="";
				
				dates = data[i].dates;
				dates=isnull(dates);
				amount = data[i].amount;
				amount=isnull(amount);
				oneLevel = data[i].oneLevel;
				oneLevel=isnull(oneLevel);
				twoLevel = data[i].twoLevel;
				twoLevel=isnull(twoLevel);
				threeLevel = data[i].threeLevel;
				threeLevel=isnull(threeLevel);
				fourLevel = data[i].fourLevel;
				fourLevel=isnull(fourLevel);
				tr="<tr>"
					+ "<td>"+dates+"</td>"
					+ "<td style='width:50px;'>" + amount +"</td>"
					+ "<td>" + oneLevel +"</td>"
					+ "<td>" + twoLevel +"</td>"
					+ "<td>" + threeLevel +"</td>"
					+ "<td class='rchdkn'>" + fourLevel +"</td></tr>";
				$("#tbody").append(tr);
			}
			var RCH=$('.roomCount').height();
			$('.relTable').height(RCH-90);
	        if(RCH-90<$('.relTable .table').height()){
	        	$('.rchdkn').css({'padding-left':0,'padding-right':0,'width':'64px'});  
	           
	        }
			
		}
	})
}
function dealCount(){
	var moth = "";
	var value = "";
	$.ajax({
		type:"post",
		url:getPath()+"/dealCount.do",
		async:false,
		success:function(data){
			$.each(data,function(k,v){
				moth += v.extend2+",";
				value += v.extend1+",";
		})
		}
	})
	var dataMonth = moth.split(",");
	var dataValue = value.split(",");
	var myChart = echarts.init(document.getElementById('dealCount'));   
var option = {
	    title: {
	        text: '当月处理数量统计',
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['处理数量']
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
	            name:'处理数数',
	            type:'line',
	            data:dataValue
	        },
	    ]
	};
myChart.setOption(option);
}
function mycalld(){
	var dataList = "[";
    $.ajax({
		type:"post",
		url:getPath()+"/findDutyArrangeByName.do",
		async:false,
		success:function(data){
			$.each(data,function(k,v){
				var dataDuty = dateFmt(v.dutyDate)
			    dataList += "{\"start\":\""+dataDuty+"\",";
				dataList += "\"color\":\"#0080ff\"}";
			if(k<data.length-1){
				dataList+=",";
			}
		})
		dataList+="]";
			
		}
    })
    $('.mycalld').fullCalendar({
		header: {
			left: 'prev',
			center: 'title',
			right: 'next'
		},
		navLinks: true, // can click day/week names to navigate views
		selectable: true,
		selectHelper: true,
		events:eval(dataList)
	});
}
function selectServiceTime(){
		var startTime = $("#startTim").val();
		var endTime = $("#endTim").val();
	$.ajax({
		type:"post",
		url:getPath()+"/selectServiceTime.do",
		data:{"startTime":startTime,"endTime":endTime},
		success:function(data){
			$("#serviceMyType").empty();
			for(var i=0; i<5;i++){
				var tr="";
				var a = 1;
				var b = a+i;
				name = data[i].name;
				name=isnull(name);
				serviceTime = data[i].serviceTime;
				serviceTime=isnull(serviceTime);
				tr="<tr>"
					+ "<td>"+b+"</td>"
					+ "<td>" + name +"</td>"
					+ "<td>" + serviceTime +"</td></tr>";
				$("#serviceMyType").append(tr);
			}
		}
	})
}
function myType(){
		var startTime = $("#startTim").val();
		var endTime = $("#endTim").val();
	$.ajax({
		type:"post",
		url:getPath()+"/selectServiceTypeApplyAll.do",
		data:{"startTime":startTime,"endTime":endTime},
		success:function(data){
			$("#myTabe").empty();
			for(var i=0; i<5;i++){
				var a = 1;
				var b = a+i;
				var tr="";
				name = data[i].name;
				name=isnull(name);
				count = data[i].count;
				count=isnull(count);
				tr="<tr>"
					+ "<td>"+ b +"</td>"
					+ "<td>" + name +"</td>"
					+ "<td>" + count +"</td></tr>";
				$("#myTabe").append(tr);
			}
		}
	})
}
function dateFmt(creatTime){

	var time = new Date(creatTime);

	var year = time.getFullYear(); //年

	var month = time.getMonth() + 1; //月

	var day = time.getDate(); //日
	
	var dateTimeStr= year + "-"+month+"-"+day;

	return dateTimeStr;

	}