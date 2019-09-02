$(function(){
	$("#acceptWait").click(function() {
		$("#commission").show();
		$("#haveDone").hide();
		$(".acceptWait").css("border-bottom", "none");
		$(".dealWait").css("border-bottom", "1px solid #70a4dd");
		deptTbody();
	})
	$("#dealWait").click(function() {
		$("#commission").hide();
		$("#haveDone").show();
		$(".acceptWait").css("border-bottom", "1px solid #70a4dd");
		$(".dealWait").css("border-bottom", "none");
		solveTbody();
	})
	selectRemindTimeAll();
	selectPushMessage();
})


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
	        text: '',
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['处理数量']
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
	            name:'处理数量',
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
		url:getPath()+"/selectDutyTimeByIdUser.do",
		async:false,
		success:function(data){
			$.each(data,function(k,v){
				var dataDuty = dateFmt(v.dutyTime)
			    dataList += "{\"start\":\""+dataDuty+"\",";
			    dataList += "\"end\":\""+dataDuty+"\",";
				dataList += "\"color\":\"#0080ff\"}";
			if(k<data.length-1){
				dataList+=",";
			}
		})
		dataList+="]";
//			
		}
    })
    $('.mycalld').fullCalendar({

    	/*汉化插件*/
    	isRTl:false,
		firstDay:0,
		monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesShort:['周日','周一','周二','周三','周四','周五','周六'],
		buttonText:{
			today:'返回今天',
			month:'月',
			week:'周',
			day:'日'
		},	 
		header: {
			left: 'prev',
			center: 'title',
			right: 'next'
		},
		
		navLinks: false, // can click day/week names to navigate views可以单击每天/每周名字导航视图
		selectable: true,
		selectHelper: true,
		events:eval(dataList)
	});
    $('.mycalld').fullCalendar( 'unselect' )
    $('.fc-icon-left-single-arrow').eq(0).addClass('prevMonth').removeClass('fc-icon fc-icon-left-single-arrow').text('上一月').css('color','#aaa')
	$('.fc-icon-right-single-arrow').eq(0).addClass('nextMonth').removeClass('fc-icon fc-icon-right-single-arrow').text('下一月').css('color','#aaa')
}
function solveTbody(){
	var eventUser=$("#eventUser").val();
	$.ajax({
		type:"post",
		data:{"eventUser":eventUser},
		url:getPath()+"/selectProcessed.do",
		success:function(data){
			
			var spn=1;
			var dateCount=data.length;
			
			$('#dealWait span').html(dateCount);
				$('.nextB').click(function(){
					if(spn>Math.ceil(dateCount/5)-1){
						return false;
					}else{
						spn++;
						waitPage(spn);
						
					}
				})
				
				$('.prevB').click(function(){
					if(spn=='1'){
						return false;
					}else{
						spn--;	
						waitPage(spn);
					}
				})
			waitPage(1);
			function waitPage(pn){
				$("#haveTbody").empty();
				var pageSize=5;
				var pageCount=Math.ceil(dateCount/pageSize);
				if(pn==0||pn>pageCount){
		            return;
		        }
			    var startPage = pageSize * (pn - 1);
		        var endPage = pn<pageCount?pageSize * pn:dateCount;
				for(var i=startPage; i<endPage;i++){
					var tr="";
					eventId = data[i].eventId;
					dealId = data[i].dealId;
					eventCode = data[i].eventCode;
					eventCode=isnull(eventCode);
					eventName = data[i].eventName;
					eventName=isnull(eventName);
					deptName = data[i].deptName;
					deptName = isnull(deptName);
					dateCreate = data[i].dateCreate;
					dateCreate = isnull(dateCreate);
					responTime = data[i].responTime;
					responTime=isnull(responTime);
					solveTime = data[i].solveTime;
					solveTime = isnull(solveTime);
					dateDeal = data[i].dateDeal;
					dateDeal=isnull(dateDeal);
					eventStatus = data[i].eventStatus;
					eventStatus=isnull(eventStatus);
					minsum = data[i].minsum;
					minsum = isnull(minsum);
					if (minsum == "") {
						minsum = 0;
					}
					eventStatus = data[i].eventStatus;
					eventStatus = isnull(eventStatus);
					tr="<tr>"
						+ "<td>"+eventCode+"</td>"
						+ "<td>" + eventName +"</td>"
						+ "<td>" + deptName +"</td>"
						+ "<td>" + getFormatDate(new Date(dateCreate), "yyyy-MM-dd hh:mm:ss")+"</td>"
						+ "<td>" + getFormatDate(new Date(responTime), "yyyy-MM-dd hh:mm:ss")+"</td>"
						+ "<td>" + solveTime +"分钟</td>"
						+ "<td>" + getFormatDate(new Date(dateDeal), "yyyy-MM-dd hh:mm:ss") +"</td>"
						+ "<td>" + minsum +"分钟</td>"
						+ "<td><a href='javascript:checkEvent(\""+eventId+"\",\""+dealId+"\")' >查看</a></td>"
						+ "</tr>";
					$("#haveTbody").append(tr);
					
				}
				var lenHei=(($('.tableItemsInner').height()-$('.tableItemsInner .table thead th').height()-2)/5)-4;
			}
		}
	})
}
function deptTbody(){
	var eventUser=$("#eventUser").val();
	$.ajax({
		type:"post",
		data:{"eventUser":eventUser},
		url:getPath()+"/untreated.do",
		success:function(data){
			
			
			var spn=1;
			var dateCount=data.length;
			$('#acceptWait span').html(dateCount);
				$('.nextA').click(function(){
					if(spn>Math.ceil(dateCount/5)-1){
						return false;
					}else{
						spn++;
						waitPage(spn);
						
					
					}
				})
				
				$('.prevA').click(function(){
					if(spn=='1'){
						return false;
					}else{
						spn--;	
						waitPage(spn);
					}
				})
			waitPage(1);
			function waitPage(pn){
				$("#commTbody").empty();
				var pageSize=5;
				var pageCount=Math.ceil(dateCount/pageSize);
				if(pn==0||pn>pageCount){
		            return;
		        }
			    var startPage = pageSize * (pn - 1);
		        var endPage = pn<pageCount?pageSize * pn:dateCount;
				for(var i=startPage; i<endPage;i++){
					var tr="";
					eventId = data[i].eventId;
					eventCode = data[i].eventCode;
					eventCode=isnull(eventCode);
					eventName = data[i].eventName;
					eventName=isnull(eventName);
					deptName = data[i].deptName;
					deptName = isnull(deptName);
					eventLevel = data[i].eventLevel;
					eventLevel = isnull(eventLevel);
					if (eventLevel == "1") {
						var level = "一级事件";
					}
					if (eventLevel == "2") {
						var level = "二级事件";
					}
					if (eventLevel == "3") {
						var level = "三级事件";
					}
					if (eventLevel == "4") {
						var level = "四级事件";
					}
					eventPriority = data[i].eventPriority;
					eventPriority = isnull(eventPriority);
					if (eventPriority == "1") {
						var priority = "高";
					}
					if (eventPriority == "2") {
						var priority = "中";
					}
					if (eventPriority == "3") {
						 var priority = "低";
					}
					dateAccept = data[i].dateAccept;
					dateAccept=isnull(dateAccept);
					dealTime = data[i].dealTime;
					dealTime=isnull(dealTime);
					eventStatus = data[i].eventStatus;
					eventStatus = isnull(eventStatus);
					users = data[i].users;
					users = isnull(users);
					var dates = dateFmts(dateAccept);
					tr="<tr>"
						+ "<td>"+eventCode+"</td>"
						+ "<td>" + eventName +"</td>"
						+ "<td>" + deptName +"</td>"
						+ "<td>" + getFormatDate(new Date(dateAccept), "yyyy-MM-dd hh:mm:ss")+"</td>"
						+ "<td>" + level +"</td>"
						+ "<td>" + priority +"</td>"
						+ "<td>" + dealTime +"</td>"
						+ "<td>" + users +"</td>"
						+ "<td><a href='javascript:dealEnd(\""+eventId+"\")' >处理</a></td>"
						+ "</tr>";
					$("#commTbody").append(tr);
				}
				var lenHei=(($('.tableItemsInner').height()-$('.tableItemsInner .table thead th').height()-2)/5)-4;
				$('.tableItemsInner .table tbody tr td').css({'height':lenHei+'px','line-height':lenHei+'px'});

			}
			
			var relTable=$('.relTable');
	        var relTab=$('.relTable .table');
	        var thTab=$('.fixTable .table tr th');
	        var tdTab=$('.relTable .table tr:eq(0) td');
	        var len=thTab.length;
	        var relHeight=relTable.height();
	        var relTabHeight=relTab.height();
	       
	       
	        if(relHeight<relTabHeight){
	        	  for(var thI=0;thI<(len-1);thI++){
	                  tdTab.eq(thI).width(thTab.eq(thI).width()-16) ;
	  
	              }
               

	            relTable.addClass('relAuto')
	        }else{
	        	
	        relTable.removeClass('relAuto')
	        }
		}
	})
}
function add0(time){return time<10?'0'+time:time; }
function dateFmt(creatTime){

	var time = new Date(creatTime);

	var year = time.getFullYear(); //年

	var month = time.getMonth() + 1; //月

	var day = time.getDate(); //日
	
	var dateTimeStr= year + "-"+add0(month)+"-"+add0(day);
	
	return dateTimeStr;

	}

function dateFmts(creatTime){
	var time = new Date(creatTime);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();

	return y+'-'+add0(m)+'-'+add0(d);

	

	}
//处理
function dealEnd(id) {
	var oper = "运维人员主页";
	$.ajax({
		type:"post",
		url:getPath() + "/dealEnd.do",
		data:{"id":id,"oper":oper},
		success:function(data){
		
			  var pageSize = data.pageSize;
			  var startTime = data.startTime;
			  var endTime = data.endTime;
			  var contact = data.contact;
			  var idDept = data.idDept;
		      var eventStatus = data.eventStatus;
			  var pageNumber = data.pageNumber;
			  var totalPage = data.totalPage;
			  var totalRow = data.totalRow;
			  var oper = data.oper;
			  var id = data.id;
			  location.href = getPath()+"/queryDeal.do?pageSize="+pageSize+"&startTime="+startTime
			                            +"&endTime="+endTime+"&contact="+contact+"&idDept="+idDept
			                            +"&eventStatus="+eventStatus+"&pageNumber="+pageNumber
			                            +"&totalPage="+totalPage+"&totalRow="+totalRow+"&id="+id+"&oper="+oper;
		  },
		  error:function(err){
			  alert($.parseJSON(err.responseText).msg)
		  }
	})
}
//查看
function checkEvent(id,dealId){
	var jump_url=getPath()+"/showOneself.do?eventId="+id+"&dealId="+dealId;
	 layer.open({
		    type: 2,
		    title: '查看',
		    scrollbar: true,
		    shadeClose: false,
		    area: ['670px', '400px'],
		    content: [jump_url,'yes']

		  })
}
function clearDept(){
	if ($("#eventDept").val() == '') {
		$("#idDept").val("");
	}
}
//展示我的任务
function showMineTask(){
	$.ajax({
		type:"post",
		data:{},
		url:getPath()+"/getTaskWarnList.do",
		dataType:"json",
		success:function(data){
			
			var spn=1;
			var dateCount=data.length;
			
			$('#dealWait span').html(dateCount);
				$('.nextC').click(function(){
					if(spn>Math.ceil(dateCount/3)-1){
						return false;
					}else{
						spn++;
						runPage(spn);
						
					}
				})
				
				$('.prevC').click(function(){
					if(spn=='1'){
						return false;
					}else{
						spn--;	
						runPage(spn);
					}
				})
			runPage(1);
				var taskTr=""
			function runPage(pn){
				$("#taskTbody").empty();
				var paSize=5;
				var pageCount=Math.ceil(dateCount/paSize);
				if(pn==0||pn>pageCount){
		            return;
		        }
			    var startPage = paSize * (pn - 1);
		        var endPage = pn<pageCount?paSize * pn:dateCount;
				for(var i=startPage; i<endPage;i++){
				
					var taskSpeed = data[i].taskSpeed;
					taskSpeed=Number(taskSpeed*100);
					
					 taskTr ="<tr><td>"+ data[i].nameTask+"</td><td>"+data[i].extend1+"</td>"+
									"<td>"+getFormatDate(new Date(data[i].dateStart),"yyyy/MM/dd")+"-"+getFormatDate(new Date( data[i].dateEnd),"yyyy/MM/dd")+"</td>"+
									"<td class='schedule'><span>"+taskSpeed+"%</span><p><b style=\"width:"+taskSpeed+"%\";></td></tr>";
					
					
					 $("#taskTbody").append(taskTr);
				}
				
				var lenHei=(($('.tableItemsInner').height()-$('.tableItemsInner .table thead th').height()-2)/5)-4;
			}
		
		}
	})
}
//工作计划
function selectRemindTimeAll(){
	$.ajax({
		type:"post",
		url:getPath()+"/selectRemindTimeAll.do",
		dataType:"json",
		success:function(data){
			var selectRemindDay = data.selectRemindDay;
			var tbody = $('#planTbody').empty();
			var str = "";
			//查询每天的提醒
			for(var i=0;i<selectRemindDay.length;i++){
				if(selectRemindDay[i] == undefined){
					break;
				}
				var planHeadline = selectRemindDay[i].planHeadline;
				planHeadline = isnull(planHeadline);
				var workType = selectRemindDay[i].workType;
				workType = isnull(workType);
				if(workType == 1){
					workType = "个人任务";
				}else if(workType == 2){
					workType = "部门任务";
				}
				var planType = selectRemindDay[i].planType;
				planType = isnull(planType);
				if(planType == 1){
					planType = "周计划";
				}else if(planType == 2){
					planType = "月计划";
				}else if(planType == 3){
					planType = "年计划";
				}else if(planType == 4){
					planType = "自定义";
				}
				var planBeginTime = selectRemindDay[i].planBeginTime;
				var planFinishTime = selectRemindDay[i].planFinishTime;
				str += "<tr>" +
					   "<td>"+planHeadline+"</td>"+
					   "<td width='120px'>"+workType+"</td>"+
					   "<td width='120px'>"+planType+"</td>"+
					   "<td width='190px'>"+FormatTime(planBeginTime) +'-'+FormatTime(planFinishTime)+"</td>"+
						"</tr>";
			}
			if(selectRemindDay.length>=5){
				var newStr=str+str;
				tbody.append(newStr);
				var highO=-(28*selectRemindDay.length);
				var topSet=0;
				setInterval(function(){
					
					topSet-=2.8;
					console.log(topSet)
					if(topSet<=highO){
						topSet=0;
						$("#runScroll table").css({"top":topSet+"px"});
					}else{
						$("#runScroll table").css({"top":topSet+"px"});
					}
				

				}, 100)
			
			}else{
				tbody.append(str);
			}
		
		}
	})
}
function selectPushMessage(){
	$.ajax({
		type:"post",
		url:getPath()+"/selectPushMessage.do",
		dataType:"json",
		success:function(data){
			var pushlook = $('.pushlook').empty();
			var str = "";
			for(var i = 0;i<data.length;i++){
				var id = data[i].id;
				id = isnull(id);
				var workType = data[i].workType;
				workType = isnull(workType);
				if(workType == 1){
					workType = "个人任务";
				}else if(workType == 2){
					workType = "部门任务";
				}else if(workType == ""){
					workType = "工作汇报";
				}
				var subTime = data[i].subTime;
				subTime = isnull(subTime);
				var content = data[i].content;
				content = isnull(content);
				str += "<li><a onclick='queryPlanReport(\""+id+"\",\""+workType+"\")'>" +
					   "<p><b>"+workType+"</b><b style='float:right;'>"+FormatDate(subTime)+"</b></p>"+
					   "<p class='txt'>"+
					      content
				       +"</p></a>"+
					   "</li>";
			}
			pushlook.append(str);
			  $(".pushlook li a").click(function(){
					$(this).parent().remove();
				})
		}
	
	})
}
function queryPlanReport(id,workType){
	if(workType == "个人任务"){
		var jump_href =getPath()+"/queryCheckPlan.do?id="+id;
		layer.open({
		    type: 2,
		    title: '查看',
		    shadeClose: false,
		    area: ['720px', '400px'],
		    content:jump_href
		});
	}else if(workType == "部门任务"){
		var jump_href =getPath()+"/queryCheck.do?id="+id;
		layer.open({
		    type: 2,
		    title: '查看',
		    shadeClose: false,
		    area: ['720px', '400px'],
		    content:jump_href
		});
	}else if(workType == "工作汇报"){
		var jump_href = getPath()+"/workReportCheck.do?id="+id;
		layer.open({
		    type: 2,
		    title: '查看',
		    shadeClose: false,
		    area: ['720px', '400px'],
		    content:jump_href
		});
	}
}