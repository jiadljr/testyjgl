<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sysDuty_list.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys_class.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.print.min.css" rel='stylesheet' media='print' >
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/lib/moment.min.js'></script>
	<script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.js'></script>
    <script src='<%=request.getContextPath()%>/js/utilx.js'></script>
    <style type="text/css">
    	.fc-day-top,.fc-day{ cursor:pointer;}
		.fc-day-grid-event:hover{cursor:default;}
    </style>
</head>
<body>
	<div class="mRight">
		<input type="hidden" value="0" id="hiddenUpdate">
		<input type="hidden" value="${selectDutyRemark.dutyRemark }" id="hiddenRemark">
		<input type="hidden" value="${selectDutyRemark.id }" id="hiddenRemarkId">
 
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="javascript:;">管理体系</a></li>
			    <li class="active">排版管理</li>
			</ol>
		</div>
		<div class="btnRankClass">
		  <a href="#" class="btn_yellow" id="updateDuty">编辑排班</a>
		  <a href="#" class="btn_purple" id="updateRemark">编辑备注</a>
   		  <a href="#" class="btn_red" onclick="deleteDuty()">清空当前月</a>
          <a href="#" class="btn_blue" id="insertDuty">新值班</a>
          <div id="updateButton">
          <a href="#" class="btn_red"  style="float:right" id="buttonCancel">取消</a>
          <a href="#" class="btn_green" id="saveDuty"  style="float:right">保存</a>
          </div>   
		</div>
		<div class="tabRankClass">
			<div class="calendar" style="width: 70%;"></div>
			<div class="divRemark">
				<p>值班说明</p>
				<input type="hidden" id="remarkId" value="${selectDutyRemark.id }">
				<textarea id="remark" readonly="readonly">${selectDutyRemark.dutyRemark }</textarea>
			</div>
		</div>	
	</div>
		
<script type="text/javascript">

	function loadNewPage(){
		location.reload();
	}
	$(function(){
		$("#updateButton").hide();
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
			dayClick: function(start, end) {
				var hiddenUpdate = $("#hiddenUpdate").val();
				if(hiddenUpdate == 0){
					return;
				}else if(hiddenUpdate == 1){
				var params = dateFmt(start);
				 layer.open({
			         type: 2,
			         title: '修改',
			         shadeClose: false,
			         area: ['460px', '180px'],
			         content: getPath()+"/dutyTimeInsert.do?time="+params
			     });
		
				}
			}, 
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
			    		$("#remark").val("");
			    		$("#remarkId").val("");
			    		if(data.dutyRemark != 1){
			    		$("#remark").val(data.dutyRemark);
			    		$("#remarkId").val(data.id);
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
			    		$("#remark").val("");
			    		$("#remarkId").val("");
			    		if(data.dutyRemark != 1){
			    		$("#remark").val(data.dutyRemark);
			    		$("#remarkId").val(data.id);
			    		}
			    	}
			    })
			})
			$(".fc-today-button").click(function(){
				$("#remark").val("");
	    		$("#remark").val($("#hiddenRemark").val());
	    		$("#remarkId").val("");
	    		$("#remarkId").val($("#hiddenRemarkId").val());
			})
	    	$("#insertDuty").click(function(){
	    		location.href = getPath()+"/queryInsertDuty.do";
	    	})
	    	$("#updateDuty").click(function(){
	    		var str=$(".calendar .fc-center h2").text();
				var monthArr=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
	    		strYear=str.substr(str.length-4);
	    		strMonth=str.substring(0,str.length-4).replace(/(^\s*)|(\s*$)/g, "");
			    var strMonthNum= monthArr.lastIndexOf(strMonth);
			    ++strMonthNum;
			    var dutyTime;
			    if(strMonthNum.toString().length == 1){
			    	dutyTime = strYear +"-"+"0"+ strMonthNum;
			    }else{
			    	dutyTime = strYear+"-"+ strMonthNum;
			    }
	    		$.ajax({
	    			type:"post",
	    			url:getPath()+"/selectDutyTimeIsNull.do",
	    			data:{"dutyTime":dutyTime},
	    			dataType:"text",
	    			success:function(data){
	    				alert("可以选择日期来修改排班了");
	    			}
	    		})
	    		$("#hiddenUpdate").val(1);
	    		
	    	})
	    	//修改后的保存
	    	$("#saveDuty").click(function(){
	    		var remark = $("#remark").val();
	    		var remarkId = $("#remarkId").val();
	    		$.ajax({
	    			type:"post",
	    			data:{"remark":remark,"remarkId":remarkId},
	    			url:getPath()+"/updateDutyRemark.do",
	    			success:function(){
	    				$("#remark").attr("readOnly",true);
	    				$("#updateButton").hide();
	    			}
	    		})
	    	})
	    	$("#updateRemark").click(function(){
	    		$("#remark").attr("readOnly",false);
	    		$("#updateButton").show();
	    	})
	    	$("#buttonCancel").click(function(){
	    		$("#remark").attr("readOnly",true);
	    		$("#updateButton").hide();
	    	})
	    })
	        function deleteDuty(){
	    		if(!confirm("确定清空当前月吗？")){
	    			return ;
	    		}
	    		var str=$(".calendar .fc-center h2").text();
				var monthArr=['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'];
	    		strYear=str.substr(str.length-4);
	    		strMonth=str.substring(0,str.length-4).replace(/(^\s*)|(\s*$)/g, "");
			    var strMonthNum= monthArr.lastIndexOf(strMonth);
			    ++strMonthNum;
			    var dutyDate;
			    if(strMonthNum.toString().length == 1){
	    		 dutyDate = strYear +"-"+"0"+ strMonthNum;
			    }else{
			     dutyDate = strYear+"-"+ strMonthNum;
			    }
	    		$.ajax({
	    			type:"post",
	    			data:{"dutyDate":dutyDate},
	    			url:getPath()+"/deleteTime.do",
	    			success:function(){
	    				location.reload();
	    			}
	    		})
	    	}
	    //日期格式转换
	    function dateFmt(creatTime){

	    	var time = new Date(creatTime);

	    	var year = time.getFullYear(); //年

	    	var month = time.getMonth() + 1; //月

	    	var day = time.getDate(); //日
	    	
	    	var dateTimeStr= year + "-"+month+"-"+day;

	    	return dateTimeStr;
	    	}
	</script>
</body>
</html>