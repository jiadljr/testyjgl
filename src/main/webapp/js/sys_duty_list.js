function change(){
	//设置改动的白班夜班的人员数字
	$("#dayDuty").change(function(){
		$("#inpDuty").val(1);
	})
	$("#nightDuty").change(function(){
		$("#inpNight").val(2);
	})
}
function confirm(){
	//点击确定按钮
	$("#confirm").click(function(){
		var dutyTime = $("#dutyTime").val();
		var dayDuty = $("#dayDuty").val();
		if(dayDuty == null){
			dayDuty = "";
		}
		var dayDutyId=$("#dayDutyId").val(","+dayDuty+",");
		var dayId = $("#dayDutyId").val();
		var nightDuty = $("#nightDuty").val();
		if(nightDuty == null){
			nightDuty ="";
		}
		var nightDutyId=$("#nightDutyId").val(","+nightDuty+",");
		var nighId = $("#nightDutyId").val();
		var inpDuty = $("#inpDuty").val();
		var inpNight = $("#inpNight").val();
		$.ajax({
			type:"post",
			data:{"dutyTime":dutyTime,"dayDuty":dayId,"nightDuty":nighId,"inpDuty":inpDuty,"inpNight":inpNight},
		    url:getPath()+"/submitDuty.do",
		    success:function(){
		    	location.href = getPath()+"/seleDuty.do";
		    },error:function(){
		    	alert("加载失败")
		    }
		})
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