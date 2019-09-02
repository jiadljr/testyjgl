$(function(){
	queryWorkReport();
})
function queryWorkReport(){
	$("#page2").empty();
	$('#page2').bPage({
			url:getPath() + "/selectWorkReport.do",
			asyncLoad : true,
		    serverSidePage : false,
			render:function(result){
				var workReport = result.selectWorkReport;
				var tbody = $('#myTable').empty();
				for(var i = 0;i<workReport.length;i++){
					var id = workReport[i].id;
					id = isnull(id);
					//汇报标题
					var reportName = workReport[i].reportName;
					reportName=isnull(reportName);
					//计划类型
					var reportType = workReport[i].reportType;
					reportType=isnull(reportType);
					if(reportType == 1){
						reportType = "自定义";
					}else if(reportType == 2){
						reportType = "日报";
					}else if(reportType == 3){
						reportType = "周报";
					}else if(reportType == 4){
						reportType = "月报";
					}else if(reportType == 0){
						reportType = "";
					}else if(reportType == 5){
						reportType = "年报";
					}
					//状态
					var reportState = workReport[i].reportState;
					reportState=isnull(reportState);
					if(reportState == 1){
						reportState = "草稿";
					}else if(reportState == 2){
						reportState = "完成";
					}else if(reportState == 0){
						reportState = "";
					}
					//提交时间
					var subbTime = workReport[i].subTime;
					subbTime=isnull(subbTime);
					var tr = "<tr>" +
							 "<td style='display:none;'>"+id+"</td>"+
							 "<td><a href='#' onclick='queryCheck("+id+")'>"+reportName+"</a></td>"+
							 "<td>"+reportType+"</td>"+
							 "<td>"+reportState+"</td>"+
							 "<td>"+getFormatDate(new Date(subbTime),"yyyy-MM-dd hh:mm:ss")+"</td>"+
					         "<td>"+
					         "<a onclick='deletePlan(\""+id+"\",\""+reportState+"\")' class='btn btn-primary  btn_new_small btn_red'>删除</a>";
							if(reportState == "草稿"){//如果状态为草稿则随时可以编辑
								tr+="<a onclick='edit(\""+id+"\",\""+reportState+"\")' class='btn btn-primary  btn_new_small btn_yellow'>编辑</a>";
							}
							tr+="</td></tr>";
					         tbody.append(tr);
				}
				var relTable=$('.relTable');
				var RH=$('body').height()-160;		     
				var relTable=$('.relTable');
				var relTab=$('.relTable table');
				var thTab=$('.fixTable table tr th');
				var tdTab=$('.relTable table tr:eq(0) td');
				var len=thTab.length;			
				var relTabHeight=relTab.height();
				for(var thI=0;thI<(len);thI++){
					tdTab.eq(thI+1).width(thTab.eq(thI).width()) ;
				}
				if(RH<relTabHeight){
					for(var thI=0;thI<(len-1);thI++){
						tdTab.eq(thI+1).width(thTab.eq(thI).width()) ;	
					}		
				}
			},
			params : function() {
				
			}
	  })
}
function edit(id){
	 location.href = getPath()+"/queryWorkReportMod.do?reportId="+id;
}
function queryCheck(id){
	var jump_href = getPath()+"/workReportCheck.do?id="+id;
	layer.open({
	    type: 2,
	    title: '查看',
	    shadeClose: false,
	    area: ['720px', '400px'],
	    content:jump_href
	});
}
function deletePlan(id,reportState){
	if (!confirm("确定删除该条记录吗？")) {
		return;
	}
	$.ajax({
		type:"post",
		url:getPath()+"/deleteWorkReport.do",
		data:{"id":id,"reportState":reportState},
		success:function(){
			queryWorkReport();
		}
	})
}