function daty(){
	var startTime = $("#all_startTime").val();
	var endTime = $("#all_endTime").val();
	var moth = "";
	var value = "";
	var moths = "";
	var values = "";
	$.ajax({
		type:"post",
		url:getPath()+"/EventCount.do",
		data:{"startTime":startTime,"endTime":endTime},
		dataType:"json",
		async:false,
		success:function(data){
			var selectDealCount = data.selectDealCount;
			var selectAcceptCount = data.selectAcceptCount;
			$.each(selectDealCount,function(k,v){
				moth += v.userName+",";
				value += v.dealCount+",";
		})
		$.each(selectAcceptCount,function(k,v){
			  moths += v.userName+",";
			  values += v.acceptCount+",";
		})
		}
	})
	var dataMonth = moth.split(",");
	var dataValue = value.split(",");
	var dataMonths = moths.split(",");
	var dataValues = values.split(",");
	var myChart = echarts.init(document.getElementById('deal'));
	var accept = echarts.init(document.getElementById('accept'));
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
	                name: '处理次数',
	                type: 'bar',
	                data: dataValue
	            }]
	        };
	 var options = {
	            tooltip: {},
	            legend: {
	                data:false
	            },
	            xAxis: {
	                data: dataMonths
	            },
	            yAxis: {},
	            series: [{
	                name: '受理次数',
	                type: 'bar',
	                data: dataValues
	            }]
	        };
	myChart.setOption(option);
	accept.setOption(options);
}
function userCount(){
	var startTime = $("#personal_startTime").val();
	var endTime = $("#personal_endTime").val();
	var dealUser = $("#dealId").val();
	var mothSer = "";
	var valueSer = "";
	var mothAll = "";
	var valueAll = "";
	var mothDate = "";
	var valueDate = "";
	$.ajax({
		type:"post",
		url:getPath()+"/selectDealCountBySer.do",
		data:{"startTime":startTime,"endTime":endTime,"dealUser":dealUser},
		dataType:"json",
		async:false,
		success:function(data){
			var selectDealCountBySer = data.selectDealCountBySer;
			var selectSerCounAll = data.selectSerCounAll;
			var selectDealCountByDate = data.selectDealCountByDate;
			$.each(selectDealCountBySer,function(k,v){
				mothSer += v.name+",";
				valueSer += v.extend1+",";
			})
			$.each(selectSerCounAll,function(k,v){
				mothAll += v.name+",";
				valueAll += v.extend1+",";
			})
			$.each(selectDealCountByDate,function(k,v){
				mothDate += v.dates+",";
				valueDate += v.coun+",";
			})
		}
	})
	var dataMonthSer = mothSer.split(",");
	var dataValueSer = valueSer.split(",");
	var dataMonthAll = mothAll.split(",");
	var dataValueAll = valueAll.split(",");
	var dataMonthDate = mothDate.split(",");
	var dataValueDate = valueDate.split(",");
	var serCount = echarts.init(document.getElementById('serCount'));
	var serCountByTime = echarts.init(document.getElementById('serCountByTime'));
	var ech = echarts.init(document.getElementById('ech'));
	var optionSer = {
            tooltip: {},
            legend: {
                data:false
            },
            xAxis: {
                data: dataMonthSer
            },
            yAxis: {},
            series: [{
                name: '申告次数',
                type: 'bar',
                data: dataValueSer
            }]
        };
	var optionAll = {
            tooltip: {},
            legend: {
                data:false
            },
            xAxis: {
                data: dataMonthAll
            },
            yAxis: {},
            series: [{
                name: '平均耗时',
                type: 'bar',
                data: dataValueAll
            }]
        };
	var optionDate = {
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
		        data: dataMonthDate
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
		            data:dataValueDate
		        },
		    ]
		};
	ech.setOption(optionDate);  
	serCount.setOption(optionSer);
	serCountByTime.setOption(optionAll);
}
