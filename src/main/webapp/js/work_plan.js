$(function(){
	queryWorkPlan();
	queryWorkPlanAll();
})
function queryWorkPlan(){
	$("#page2").empty();
	$('#page2').bPage({
			url:getPath() + "/selectWorkPlanAll.do",
			asyncLoad : true,
		    serverSidePage : false,
			render:function(result){
				var workPlan = result.selectWorkPlanAll;
				var tbody = $('#myTable').empty();
				for(var i = 0;i<workPlan.length;i++){
					var id = workPlan[i].id;
					id = isnull(id);
					//计划标题
					var planHeadline = workPlan[i].planHeadline;
					planHeadline=isnull(planHeadline);
					//计划类型
					var planType = workPlan[i].planType;
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
					//计划状态
					var planState = workPlan[i].planState;
					planState=isnull(planState);
					if(planState == 1){
						planState = "进行中";
					}else if(planState == 2){
						planState = "未开始";
					}else if(planState == 3){
						planState = "完成";
					}else if(planState == 0){
						planState = "";
					}
					//最近更新
					var recentUpdate = workPlan[i].recentUpdate;
					recentUpdate=isnull(recentUpdate);
					//推送人
					var pushStaff = workPlan[i].pushStaff;
					pushStaff = isnull(pushStaff);
					//计划开始时间
					var planBeginTime = workPlan[i].planBeginTime;
					planBeginTime=isnull(planBeginTime);
					//计划结束时间
					var planFinishTime = workPlan[i].planFinishTime;
					planFinishTime=isnull(planFinishTime);
					
					var tr = "<tr>" +
							 "<td style='display:none;'>"+id+"</td>"+
							 "<td><a href='#' onclick='queryChecks("+id+")'>"+planHeadline+"</a></td>"+
							 "<td>"+planType+"</td>"+
							 "<td>"+planState+"</td>"+
							 "<td>"+FormatDate(recentUpdate)+"</td>"+
							 "<td>"+FormatDate(planBeginTime) +'至'+FormatDate(planFinishTime)+"</td>";
							if(planState == "未开始"){
								tr+="<td><a onclick='accomplish("+id+")' class='btn btn-primary  btn_new_small btn_purple' >完成</a>";
								if(pushStaff == ""){
									tr+="<a onclick='editPlan("+id+")'  class='btn btn-primary  btn_new_small btn_yellow'  >编辑</a>";
								}
								tr+="</td></tr>";
							}else if(planState == "进行中"){
								tr+="<td><a onclick='accomplish("+id+")'  class='btn btn-primary  btn_new_small btn_purple' >完成</a>" +
										"</td></tr>";
							}else if(planState == "完成"){
								tr+="<td>" +
								"</td></tr>";
							}
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
function queryWorkPlanAll(){
	$("#page3").empty();
	$('#page3').bPage({
			url:getPath() + "/sectionWorkPlanAll.do",
			asyncLoad : true,
		    serverSidePage : false,
			render:function(result){
				var workPlan = result.sectionWorkPlanAll;
				var planTable = $('#planTable').empty();
				var userName = $("#userName").val();
				for(var i = 0;i<workPlan.length;i++){
					var id = workPlan[i].id;
					id = isnull(id);
					//计划标题
					var planHeadline = workPlan[i].planHeadline;
					planHeadline=isnull(planHeadline);
					//计划人
					var planner = workPlan[i].planner;
					planner=isnull(planner);
					//负责人
					var principal = workPlan[i].principal;
					principal = isnull(principal);
					//计划类型
					var planType = workPlan[i].planType;
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
					//计划状态
					var planState = workPlan[i].planState;
					planState=isnull(planState);
					if(planState == 1){
						planState = "进行中";
					}else if(planState == 2){
						planState = "未开始";
					}else if(planState == 3){
						planState = "完成";
					}else if(planState == 0){
						planState = "";
					}
					//最近更新
					var recentUpdate = workPlan[i].recentUpdate;
					recentUpdate=isnull(recentUpdate);
					//计划开始时间
					var planBeginTime = workPlan[i].planBeginTime;
					planBeginTime=isnull(planBeginTime);
					//计划结束时间
					var planFinishTime = workPlan[i].planFinishTime;
					planFinishTime=isnull(planFinishTime);
					
					var tr = "<tr>" +
							 "<td style='display:none;'>"+id+"</td>"+
							 "<td><a href='#' onclick='queryCheck("+id+")'>"+planHeadline+"</a></td>"+
							 "<td>"+planner+"</td>"+
							 "<td>"+principal+"</td>"+
							 "<td>"+planType+"</td>"+
							 "<td>"+planState+"</td>"+
							 "<td>"+FormatDate(recentUpdate)+"</td>"+
							 "<td>"+FormatDate(planBeginTime) +'至'+FormatDate(planFinishTime)+"</td>";
					         
					         if(userName == principal){
								 tr+="<td><a onclick='accomplish("+id+")' class='btn btn-primary  btn_new_small btn_purple' >完成</a>" +
									"</td></tr>";
							 }else {
								 tr+="<td>" +
									"</td></tr>";
							 }
							planTable.append(tr);
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
function accomplish(id){
	$.ajax({
		type:"post",
		url:getPath()+"/updatePlanState.do",
		data:{"id":id},
		success:function(){
			queryWorkPlan();
			queryWorkPlanAll();
		}
	})
}
function deletePlan(id){
	if (!confirm("确定删除该条记录吗？")) {
		return;
	}
	$.ajax({
		type:"post",
		url:getPath()+"/deleteWorkPlan.do",
		data:{"id":id},
		success:function(){
			queryWorkPlan();
			queryWorkPlanAll();
		}
	})
}
function queryCheck(id){
	var jump_href =getPath()+"/queryCheck.do?id="+id;
	layer.open({
	    type: 2,
	    title: '查看',
	    shadeClose: false,
	    area: ['720px', '400px'],
	    content:jump_href
	});
}
function queryChecks(id){
	var jump_href =getPath()+"/queryCheckPlan.do?id="+id;
	layer.open({
	    type: 2,
	    title: '查看',
	    shadeClose: false,
	    area: ['720px', '400px'],
	    content:jump_href
	});
}
function editPlan(id){
	  location.href=getPath()+"/queryWorkPlanMod.do?id="+id;
}
//文件下载
function downFile(fileId){
  	$.ajax({
  		data:{fileId: fileId},
  		dataType : 'json',
  		async: false,
  		type : 'post',
  		url : getPath() + "/exportDocLoad.do",
  		success : function(result){
  			if (result.data == "error") {
  				alert("文件不存在!");
  			}else {
  				location.href = getPath() + "/downLoad.do?fileId=" + fileId;
  			}
  		}
  	})
  }
