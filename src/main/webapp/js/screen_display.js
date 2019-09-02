function showScreenDisplay(){
	$.ajax({
		data:{},
		dataType:'json',
		async:false,
		type:'post',
		url: getPath()+"/selectScreen.do",
		success: function(data){
			//科室申告统计
			var depApplyCopy = data.countDeptApply;
			
			var depApply=[];
			for(var dep_i=0;dep_i<depApplyCopy.length;dep_i++){
				if(depApplyCopy[dep_i].extend1!=="0"){
					depApply.push(depApplyCopy[dep_i]);
				}
			}
			var depName="";
			var depCount="";
			for (var k = depApply.length-1; k >= 0; k--) {
				depName+=","+depApply[k].name;
				depCount+=","+depApply[k].extend1;
			}
			depName = depName.substr(1).split(",");
		
			
			for(var depi=0;depi<depName.length;depi++){
				if(depName[depi].length>5){
					depName[depi]=depName[depi].substr(0,5)+"..."
				}
			}
			
			depCount = depCount.substr(1).split(",");
			var depChart = echarts.init(document.getElementById('dept'));
			depOption = {
			    title: {
			        text: '',
			        subtext: ''
			    },
			    tooltip: {
			        trigger: 'axis',
			        axisPointer: {
			            type: 'shadow'
			        }
			    },
			    legend: {
			        data: false
			       
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'value',
			        boundaryGap: [0, 0.01],
			        axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#fff'
                        }
                    }
			    },
			    yAxis: {
			        type: 'category',
			        data: depName,
			        axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#fff'
                        }
                    }
			    },
			    series: [
			        {
			            name: '申告数',
			            type: 'bar',
			            data: depCount
			        }
			    ]
			};
			depChart.setOption(depOption);
			//事件等级统计
			var levelList = data.levelList;
			$('#eventRank li b').eq(0).text(levelList.first_level);
			$('#eventRank li b').eq(1).text(levelList.second_level);
			$('#eventRank li b').eq(2).text(levelList.third_level);
			$('#eventRank li b').eq(3).text(levelList.fourth_level);
			
			//状态统计
			var statusList = data.statusList;
			for (var m = 0; m < statusList.length; m++) {
				$('#eventTypeS .round').eq(m).text(statusList[m])
			}
			//优先级
			var prioritys = data.priorityList;
			for (var o = 0; o < prioritys.length; o++) {
				$('#evrntGrade .color').eq(o).text(prioritys[o])
			}
			//一级服务类型统计
			var sysList = data.sysList;
			var sysName = "";
			var sysCount = "";
			for (var m = 0; m < sysList.length; m++) {
				sysCount+=","+sysList[m].extend1;
				sysName+=","+sysList[m].name;
			}
			sysName = sysName.substr(1).split(",");
			sysCount = sysCount.substr(1).split(",");

			var sysChart = echarts.init(document.getElementById('service'));
			sysOption = {
				color: ['#3398DB'],
				tooltip : {
					trigger: 'axis',
					axisPointer : {            
						type : 'shadow'       
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
						data : sysName,
						axisTick: {
							alignWithLabel: true
						}, axisLabel: {
							show: true,
							textStyle: {
								color: '#fff'
							}
						}
					}
				],
				yAxis : [
					{
						type : 'value',
						axisLabel: {
							show: true,
							textStyle: {
								color: '#fff'
							}
						}
					}
				],
				series : [
					{
						name:'事件数量',
						type:'bar',
						barWidth: '60%',
						data:sysCount
					}
				]
			};
			sysChart.setOption(sysOption);
			//一个月内的事件统计
			var timeDay = data.timeDay;
			var dayName = "";
			var dayCount = "";
			for (var n = 0; n < timeDay.length; n++) {
				dayName+=","+timeDay[n].extend1;
				dayCount+=","+timeDay[n].extend2;
			}
			dayName = dayName.substr(1).split(",");
			dayCount = dayCount.substr(1).split(",");
			var dayChart = echarts.init(document.getElementById('time'));   
			var dayOption = {
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
						data: dayName,
						 axisLabel: {
	                            show: true,
	                            textStyle: {
	                                color: '#fff'
	                            }
	                        }
					},
					yAxis: {
						type: 'value',
						axisLabel: {
							formatter: '{value}',
							 show: true,
	                            textStyle: {
	                                color: '#fff'
	                            }
						}
					},
					series: [
						{
							name:'申告次数',
							type:'line',
							data:dayCount
						},
					]
				};
			dayChart.setOption(dayOption);
		}
	})
}
function autoEvent(){
	
	function eventAlert(){
		var pageNow=$("#alertPageNow").val();
		$.ajax({
			url:getPath()+"/eventAlert.do",
			data:{pageNow:pageNow},
			dataType:'json',
			type:'post',
			async:false,
			success:function(data){
				//事件提醒
				var eventAlerts = data.dealList;
				var alertTbody = $("#alertTbody").empty();
				var eventAlertStr = "";
				var spn=1;
				var operStr = "";
				
				var dataCount=eventAlerts.length;
			
				
				$('.nextA').click(function(){
					if(spn>Math.ceil(dataCount/5)-1){
						spn=1;
						acceptPage(spn);
					}else{
						spn++;
						acceptPage(spn);					
					}
				})		
				acceptPage(1);
				function acceptPage(pn){
					$("#alertTbody").empty();
					var pageSize=5;
					var pageCount=Math.ceil(dataCount/pageSize);
					
					if(pn==0||pn>pageCount){
			            return;
			        }
			        var startPage = pageSize * (pn - 1);		        
			        var endPage = pn<pageCount?pageSize * pn:dataCount;
			            for (var r =startPage; r < endPage; r++) {
			            	var eventAlert = eventAlerts[r];
			            	var eventStatus = eventAlert.eventStatus;
			            	var dateCreate = eventAlert.dateCreate;
							var eventAlertName = eventAlert.eventName;
							var eventAlertDept = eventAlert.deptName;
							var eventAlertUser = eventAlert.dealUserName;
							var eventAlertDealTime = eventAlert.dealTime;
							var minutes = eventAlert.minutes;
							dateCreate = isnull(dateCreate);
							eventAlertName = isnull(eventAlertName);
							eventAlertDept = isnull(eventAlertDept);
							eventAlertUser = isnull(eventAlertUser);
							eventAlertDealTime = isnull(eventAlertDealTime);
							if(eventAlertDealTime != ""){
								eventAlertDealTime = getFormatDate(new Date(eventAlertDealTime), "MM-dd hh:mm:ss")
							}
							minutes = isnull(minutes);
							var blueStr='';
							var coLors
							if(eventStatus != "20"){
								if(minutes<=10 && minutes>=0){
									coLors="trcoLors4"
									blueStr="<td>" + minutes+"分</td>"
								}else if(minutes<0){
									coLors="trcoLors3"
									blueStr="<td>已超时" +Math.abs(minutes)+"分</td>"
								}else{
									coLors="trcoLors2"
									blueStr="<td>" + minutes+"分</td>"
								}
							}else{
								coLors="trcoLors1"
							}
							eventAlertStr = "<tr class='"+coLors+"'><td>"+getFormatDate(new Date(dateCreate), "MM-dd hh:mm:ss")+"</td>"+
							"<td>" + eventAlertName +"</td>"+
							"<td>" + eventAlertDept +"</td>"+
							"<td>" + eventAlertUser +"</td>"+
							"<td>" + eventAlertDealTime +"</td>"+blueStr+
							"</tr>";
							alertTbody.append(eventAlertStr);
			            }			           
			            var AlertStrHei=($('.eventRemind').height()-40)/6;
			         
						$('.eventRemind .table thead tr th,.eventRemind .table tbody tr td').css({'padding-top':0,'padding-bottom':0,'height':AlertStrHei+'px','line-height':AlertStrHei+'px'});
				
				}
			}
		})
	}
		
	function dealStatus(){
		var pageNow = $("#dealPageNow").val();
		$.ajax({
			data : {pageNumber:pageNow},
			url : getPath()+"/allDealStatus.do",
			dataType : "json",
			async:false,
			type : "post",
			success : function(data){
				/*
				 * 自动播放效果实现
				 * 生成分页
				 * 利用计时器循环分页
				 * 当播放到最后一页时
				 * 计数器调0
				 * 
				 * */
				var operStatus = data.operStatus;
				var spn=1;
				var operStr = "";
				var dataCount=operStatus.length;			
				
				
				$('.nextB').click(function(){
					if(spn>Math.ceil(dataCount/5)-1){
						spn=1;
						acceptPage(spn);
					}else{
						spn++;
						acceptPage(spn);					
					}
				})		
				acceptPage(1);
				function acceptPage(pn){
					var pageSize=6;
					var pageCount=Math.ceil(dataCount/pageSize);				
					if(pn==0||pn>pageCount){
			            return;
			        }
			        var startPage = pageSize * (pn - 1); 
			        var operTbody = $("#operTbody").empty();
			        var endPage = pn<pageCount?pageSize * pn:dataCount;
			            for (var r =startPage; r < endPage; r++) {
			            	var oper = operStatus[r];
							var operName = oper.name;
							var operNotDeal = oper.notDeal;
							var operEventFinish = oper.eventFinish;
							var operDealAll = oper.dealAll;
							operName = isnull(operName);
							operNotDeal = isnull(operNotDeal);
							operEventFinish = isnull(operEventFinish);
							operDealAll = isnull(operDealAll);
							operStr = "<tr><td>"+operName+"</td>"+
									"<td>" + operNotDeal +"</td>"+
									"<td>" + operEventFinish +"</td>"+
									"<td>" + operDealAll +"</td>"+
									"</tr>";
							  operTbody.append(operStr);
			            }			           
			            var dealHeight=$('.dealerCondition').height();
						$('.dealerCondition .table thead tr th,.dealerCondition .table tbody tr td').css({'height':(dealHeight-50)/7,'padding':0,'line-height':(dealHeight-50)/7+'px'})		
				}		
			}
		})
	}
	function all(){
		
		eventAlert();
		dealStatus();
	}
	all();
	
}
