$(function(){
	queryLookUpAll();
})
function queryWorkPlanReport(){
	$("#page2").empty();
	$('#page2').bPage({
			url:getPath() + "/workPlanReport.do",
			asyncLoad : true,
		    serverSidePage : false,
			render:function(data){
				var result = data.planReport;
				var tbody = $('#myTable').empty();
				for(var i = 0;i<result.length;i++){
					var id = result[i].id;
					id = isnull(id);
					//汇报标题
					var reportName = result[i].reportName;
					reportName=isnull(reportName);
					//计划类型
					var reportType = result[i].reportType;
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
					//提交时间
					var subbTime = result[i].subTime;
					subbTime=isnull(subbTime);
					//汇报添加人
					var reportUser = result[i].reportUser;
					reportUser = isnull(reportUser);
					var tr = "<tr>" +
							 "<td style='display:none;'>"+id+"</td>"+
							 "<td><a href='#' onclick='queryCheck("+id+")'>"+reportName+"</a></td>"+
							 "<td>工作汇报</td>"+
							 "<td>"+reportType+"</td>"+
							 "<td>"+reportUser+"</td>"+
							 "<td>"+getFormatDate(new Date(subbTime),"yyyy-MM-dd hh:mm:ss")+"</td>"+
					         "<td>" +
							 "<a onclick='delReport("+id+")'  class='btn btn-primary  btn_new_small btn_red' >删除</a>" +
							 "</td></tr>";
					tbody.append(tr);
				}
				var relTable=$('#planTabs .relTable');
				var RH=$('body').height()-160;
				relTable.height(RH)
				var relTable=$('#planTabs .relTable');
				var relTab=$('#planTabs .relTable table');
				var thTab=$('#planTabs .fixTable table tr th');
				var tdTab=$('#planTabs .relTable table tr:eq(0) td');
				var len=thTab.length;			
				var relTabHeight=relTab.height();
				for(var thI=0;thI<(len-1);thI++){
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
function queryLookUpAll(){
	$("#page3").empty();
	$('#page3').bPage({
			url:getPath() + "/selectLookUpAll.do",
			asyncLoad : true,
		    serverSidePage : false,
			render:function(data){
				var result = data.workPlan;
				var tbody = $('#planTable').empty();
				for(var i = 0;i<result.length;i++){
					var id = result[i].id;
					id = isnull(id);
					//汇报标题
					var planHeadline = result[i].planHeadline;
					planHeadline=isnull(planHeadline);
					var workType = result[i].workType;
					workType = isnull(workType);
					if(workType == 1){
						workType = "个人计划";
					}else if(workType == 2){
						workType = "部门计划";
					}
					//计划类型
					var planType = result[i].planType;
					planType=isnull(planType);
					if(planType == 1){
						planType = "周计划";
					}else if(planType == 2){
						planType = "月计划";
					}else if(planType == 3){
						planType = "年计划";
					}else if(planType == 4){
						planType = "自定义";
					}else if(planType == 0){
						planType = "";
					}
					//提交时间
					var foundTime = result[i].foundTime;
					foundTime=isnull(foundTime);
					//汇报添加人
					var planner = result[i].planner;
					planner = isnull(planner);
					var tr = "<tr>" +
							 "<td style='display:none;'>"+id+"</td>"+
							 "<td><a href='#' onclick='queryCheckPlan(\""+id+"\",\""+workType+"\")'>"+planHeadline+"</a></td>"+
							 "<td>"+workType+"</td>"+
							 "<td>"+planType+"</td>"+
							 "<td>"+planner+"</td>"+
							 "<td>"+getFormatDate(new Date(foundTime),"yyyy-MM-dd hh:mm:ss")+"</td>"+
							 "<td>"+
							 "<a  onclick='delPlan("+id+")' class='btn btn-primary  btn_new_small btn_red'>删除</a>" +
							 "</td></tr>";
					tbody.append(tr);
				}
				var relTable=$('#workTabs .relTable');
				var RH=$('body').height()-165;
				relTable.height(RH)
				var relTable=$('#workTabs .relTable');
				var relTab=$('#workTabs .relTable table');
				var thTab=$('#workTabs .fixTable table tr th');
				var tdTab=$('#workTabs .relTable table tr:eq(0) td');
				var len=thTab.length;			
				var relTabHeight=relTab.height();
				for(var thI=0;thI<(len-1);thI++){
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
function queryCheck(id){
	var jump_href = getPath()+"/queryPlanReportCheck.do?id="+id;
	layer.open({
	    type: 2,
	    title: '查看',
	    shadeClose: false,
	    area: ['720px', '400px'],
	    content:jump_href
	});
}
function queryCheckPlan(id,workType){
	if(workType == "个人计划"){
		var jump_href =getPath()+"/queryCheckPlan.do?id="+id;
		layer.open({
		    type: 2,
		    title: '查看',
		    shadeClose: false,
		    area: ['720px', '400px'],
		    content:jump_href
		});
	}else if(workType == "部门计划"){
		var jump_href =getPath()+"/queryCheck.do?id="+id;
		layer.open({
		    type: 2,
		    title: '查看',
		    shadeClose: false,
		    area: ['720px', '400px'],
		    content:jump_href
		});
	}
}
function delPlan(id){
	if (!confirm("确定删除该条记录吗？")) {
		return;
	}
	$.ajax({
		type:"post",
		url:getPath()+"/deleteUserPlanDs.do",
		data:{"id":id},
		success:function(){
			queryLookUpAll();
		}
	})
}
function delReport(id){
	if (!confirm("确定删除该条记录吗？")) {
		return;
	}
	$.ajax({
		type:"post",
		url:getPath()+"/deleteUserReportDs.do",
		data:{"id":id},
		success:function(){
			queryWorkPlanReport();
		}
	})
}