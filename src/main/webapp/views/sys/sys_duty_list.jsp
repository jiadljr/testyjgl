<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys_class.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.print.min.css" rel='stylesheet' media='print' >
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/lib/moment.min.js'></script>
	<script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.js'></script>
    <script src='<%=request.getContextPath()%>/js/utilx.js'></script>
    <style type="text/css">
       body{overflow-y:auto;}
    </style>
</head>
<body>
        <input type="hidden" value="${selectDutyRemark.dutyRemark }" id="hiddenRemark">
		<div class="tabRankClass">
			<div class="calendar" style="width: 70%;"></div>
			<div class="divRemark">
				<p>值班说明</p>
				<input type="hidden" id="remarkId" value="${selectDutyRemark.id }">
				<textarea id="remark" readonly="readonly">${selectDutyRemark.dutyRemark }</textarea>
			</div>
		</div>	
		<script type="text/javascript">

	$(function(){
		
		/*页面的切换*/
		$("#top .collapse .nav li").eq(0).removeClass("active");
		$("#top .collapse .nav li").eq(3).addClass("active");
		$("#main .mLeft .list-group li").eq(0).removeClass("active");
		$("#main .mLeft .list-group li").eq(0).addClass("active");
		$(".openWin");
		$("#dayDuty").selectpicker({
	        'selectedText': '123'
	    });
		$("#nightDuty").selectpicker({
	        'selectedText': '123'
	    });
		var extend2 = "";
		var dataList="[";
		$.ajax({
			type:"post",
			url:getPath()+"/selectDutyTime.do",
			async:false,
			success:function(data){
				$.each(data,function(k,v){
					    if(v.extend1.indexOf("T12")){
					    dataList += "{\"title\":\""+v.extend2+"\",";
					    dataList += "\"start\":\""+v.extend1+"\",";
					    dataList += "\"color\":\"#0080ff\"}";
					    }
					    if(k<data.length){
							dataList+=",";
						}
				})
				dataList+="]";
			},
			error:function(){
				alert("加载失败");
			}
		})
		console.log(dataList);
		/*日历插件的参数复制*/
		$('.calendar').fullCalendar({
			/*插件汉化*/
			isRTl:false,
			firstDay:0,
			monthNames:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			monthNamesShort:['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
			dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
			dayNamesShort:['周日','周一','周二','周三','周四','周五','周六'],
			buttonText:{
				todya:'返回今天',
				month:'月',
				week:'周',
				day:'日'
			},	 
			
			header: {
				left: 'prev,next today',
				center: 'title',
				right:''
			},
			height: 500,
			navLinks: false, // can click day/week names to navigate views可以单击每天/每周名字导航视图
			selectable: true,
			selectHelper: true,
			editable: true,
			eventLimit: true,
			events:eval(dataList)
		});
		$('.fc-icon-left-single-arrow').eq(0).addClass('prevMonth').removeClass('fc-icon fc-icon-left-single-arrow').text('上一月').css('color','#aaa')
		$('.fc-icon-right-single-arrow').eq(0).addClass('nextMonth').removeClass('fc-icon fc-icon-right-single-arrow').text('下一月').css('color','#aaa')
		$('.fc-today-button').text('今天')
	})
	</script>
	<script type="text/javascript">
	$(function(){
    	$('.fc-next-button').click(function(){
    		var str=$(".calendar .fc-center h2").text();
			var monthArr=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
    		strYear=str.substr(str.length-4);
    		strMonth=str.substring(0,str.length-4).replace(/(^\s*)|(\s*$)/g, "");
		    var strMonthNum= monthArr.lastIndexOf(strMonth);
		    if(strMonthNum == 11){
	    		++strYear;
	    		strMonthNum = 01;
	    	}else{
		  
		    ++strMonthNum;
	    	}
		    var dutyDate;
		    if(strMonthNum.toString().length == 1){
    		 dutyDate = strYear +"-"+"0"+ strMonthNum;
		    }else{
		     dutyDate = strYear+"-"+ strMonthNum;
		    }
		    $.ajax({
		    	type:"post",
		    	data:{"dutyDate":dutyDate},
		    	url:getPath()+"/selectDutyRemark.do",
		    	dataType:"json",
		    	async:false,
		    	success:function(data){
		    		$("#remark").text("");
		    		if(data.dutyRemark != 1){
		    		$("#remark").text(data.dutyRemark);
		    		}
		    		
		    	}
		    })
		})
		$(".fc-prev-button").click(function(){
			var str=$(".calendar .fc-center h2").text();
			var monthArr=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
    		strYear=str.substr(str.length-4);
    		strMonth=str.substring(0,str.length-4).replace(/(^\s*)|(\s*$)/g, "");
		    var strMonthNum= monthArr.lastIndexOf(strMonth);
		    if(strMonthNum == 0){
		    	--strYear;
		    	strMonthNum = 12;
		    }else{
		    	++strMonthNum;
		    }
		    var dutyDate;
		    if(strMonthNum.toString().length == 1){
    		 dutyDate = strYear +"-"+"0"+ strMonthNum;
		    }else{
		     dutyDate = strYear+"-"+ strMonthNum;
		    }
		    $.ajax({
		    	type:"post",
		    	data:{"dutyDate":dutyDate},
		    	url:getPath()+"/selectDutyRemark.do",
		    	dataType:"json",
		    	success:function(data){
		    		$("#remark").text("");
		    		if(data.dutyRemark != 1){
		    		$("#remark").text(data.dutyRemark);
		    		}
		    	}
		    })
		})
		$(".fc-today-button").click(function(){
			$("#remark").text("");
    		$("#remark").text($("#hiddenRemark").val());
		})
	})
	</script>
</body>
</html>