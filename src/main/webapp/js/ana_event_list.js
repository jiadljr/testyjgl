//事件查询列表
function analysisList(){
	$('#pageTable').empty();
	$('#pageTable')
			.bPage(
					{
						url : getPath() + "/selectAnalysisList.do",
						// 开启异步处理模式
						asyncLoad : true,
						// 关闭服务端页面模式
						serverSidePage : false,
						// 数据自定义填充
						render : function(result) {
							// 受理信息展示
							var analysisList = result.eventList;
							var tbody = $('#tbody').empty();
							for (var i = 0; i < analysisList.length; i++) {
								var analysis = analysisList[i];
								var id=analysis.id;
								var code=analysis.eventCode;
								code=isnull(code);
								var name=analysis.eventName;
								name=isnull(name);
								var dept=analysis.extend1;
								dept=isnull(dept);
								var dateCreate=analysis.dateCreate;
								dateCreate=isnull(dateCreate);
								var eventService = analysis.extend2;
								eventService=isnull(eventService);
								var level=analysis.eventLevel;
								level=isnull(level);
								var eventStatus=analysis.eventStatus;
								eventStatus=isnull(eventStatus);
								if (eventStatus == 20) {
									var status = "新申告";
								}
								
								if (eventStatus == 50) {
									var status="已受理";
								}
								
								if (eventStatus == 70) {
									var status="已处理";
								}
								if (eventStatus == 77) {
									var status="已完成";
								}
								
								var tr = "<tr>" + "<td>"
										+ code
										+ "</td>"
										+ "<td>"
										+ name
										+ "</td>"
										+ "<td>"
										+ dept
										+ "</td>"
										+ "<td>"
										+ getFormatDate(new Date(dateCreate),
												"yyyy-MM-dd hh:mm:ss")
										+ "</td>"
										+ "<td>"
										+ eventService
										+ "</td>"
										+ "<td>"
										+ status
										+ "</td>"
										+ "<td><a href='javascript:checkEvent(\""+id+"\",\""+eventStatus+"\")' class='btn btn-primary btn-sm' >查看</a></td>"
										+ "</tr>";
								tbody.append(tr);
							}
							var relTable=$('.relTable');
							
							var RH=$('.mRight').height()-220;
				
					        var relTab=$('.relTable .table');
					        var thTab=$('.fixTable .table tr th');
					        var tdTab=$('.relTable .table tr:eq(0) td');
					        var len=thTab.length;
					       
					        relTable.height(RH);
					        var relTabHeight=relTab.height();
					       
					        for(var thI=0;thI<(len-1);thI++){
				                  tdTab.eq(thI).width(thTab.eq(thI).width()) ;
				  
				              }
					        if(RH<relTabHeight){
					        	  for(var thI=0;thI<(len-1);thI++){
					                  tdTab.eq(thI).width(thTab.eq(thI).width()) ;
					  
					              }
					        	  

					            relTable.addClass('relAuto')
					        }else{
					            relTable.removeClass('relAuto')
					        }
						},
						params : function() {
							var startTime = $("#startTime").val();
							var eventContact = $("#eventContact").val();
							var endTime = $("#endTime").val();
							var eventCode = $("#eventCode").val();
							var idService = $("#serviceId").val();
							var idDept = $("#idDept").val();
							var eventSource = $("#eventSource").val();
							var eventLevel = $("#eventLevel").val();
							if($("#serviceSearchTree").val() == ""){
								idService = "";
							}
							if($("#searchTree").val() == ""){
								idDept = "";
							}
							return {
								startTime : startTime,
								endTime : endTime,
								eventContact : eventContact,
								eventCode : eventCode,
								idService : idService,
								idDept : idDept,
								eventSource : eventSource,
								eventLevel : eventLevel,
							};
						}
					});
}

function checkEvent(id,status){
	var jump_url=getPath()+"/checkEventOne.do?eventId="+id+"&status="+status;
	 layer.open({
		    type: 2,
		    title: '查看',
		    scrollbar: true,
		    shadeClose: false,
		    area: ['670px', '400px'],
		    content: [jump_url,'yes']

		  })
}
function clearService(){
	if ($("#eventService").val() == "") {
		$("#idService").val("");
	}
}
function clearDept(){
	if ($("#eventDept").val() == "") {
		$("#idDept").val("");
	}
}
//检测文件是否存在
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