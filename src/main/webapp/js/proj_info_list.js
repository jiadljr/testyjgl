
$(function(){
	$("input").focus(function(){
		$(this).removeClass("error")
	})
})
//项目列表
function showProjInfoList() {
	$('#paging').empty();
	$('#paging').bPage({
		url : getPath() + "/findProjList.do",
		// 开启异步处理模式
		asyncLoad : true,
		// 关闭服务端页面模式
		serverSidePage : false,
		// 数据自定义填充
		render : function(data) {
			var projBody = $("#projBody").empty();
			var projInfoList = data.projInfoList;
			var pageNumber = data.pageNumber;
			var pageSize = data.pageSize;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			$("#bPageDropList").val(pageSize);
			$.each(projInfoList,function(k, projInfo) {
				var id=projInfo.id;
				var projCode=projInfo.projCode;
				var projSpeed = projInfo.projSpeed;
				projSpeed=Number(projSpeed*100).toFixed(0);
				projSpeed+="%";
				var projStatus = projInfo.projStatus;
				
				var status = "";
				if (projStatus == "90") {
					status = "草稿";
				}else if(projStatus == "91"){
					status = "正常";
				}else if(projStatus == "96"){
					status = "冻结";
				}else if(projStatus == "97"){
					status = "完成";
				}
				
				var warning=""
				if(projInfo.pf == 1){
					if(projStatus != "96"){
						warning="yShowWarn"//alert("超期啦！！");//
					}else{
						warning="yHideWarn"
					}
	            }else{
	            	warning="yHideWarn"
	            }
				var color=projInfo.extend3;
					
				
				var projTd = "<tr style='color:"+color+";' rel='"+id+"' name='"+projInfo.projStatus+"'><td>"
					+ "<a href='javascript:void(0);' onclick = edit('"+projCode+"') class='btnHover'  title='"+projInfo.projName+"'>"+projInfo.projName+"</a><b class='iconClass'><i title='项目超期' class='"+warning+" fa fa-warning'></i></b></td>"
					+ "</td>"+"<td>" +isnull(projInfo.extend1)+
							"</td>" +
							"<td>" +isnull(projInfo.extend2)+ "</td>" +
							"<td>" +status+ "</td>" +
							"<td>" +getFormatDate(new Date(isnull(projInfo.dateStart)),"yyyy-MM-dd")+ "</td>" +
							"<td>" +getFormatDate(new Date(isnull(projInfo.dateEnd)),"yyyy-MM-dd")+ "</td>" + 
							"<td class='schedule'>" +"<span>"+projSpeed+"</span><p><b style='width:"+projSpeed+"';></td>"+
							"<td style='position:relative;' class='nostr'><a id='proBtn"+id+"' href='javascript:void(0)'  onclick=btnClick("+projStatus+",'"+projCode+"',"+id+"); class='btn btnSlide btn_green' style='width:50px; display:inline-block; line-height:16px; height:16px; padding:0; border-radius:25px;'>···</a><ul class='btnClick'></ul></td>"			
				projBody.append(projTd);
			})
			var relTable=$('.relTable');
			var RH=$('body').height()-210;		     
			var relTable=$('.relTable');
			var relTab=$('.relTable .table');
			var thTab=$('.fixTable .table tr th');
			var tdTab=$('.relTable .table tr:eq(0) td');
			var len=thTab.length;			
			var relTabHeight=relTab.height();
			for(var thI=0;thI<(len);thI++){
				tdTab.eq(thI).width(thTab.eq(thI).width()) ;
			}
			if(RH<relTabHeight){
				for(var thI=0;thI<(len-1);thI++){
					tdTab.eq(thI).width(thTab.eq(thI).width()) ;	
				}		
			}
			$(".btnSlide").mouseover(function(){
				$(this).click(function(e){
					$(this).next().show();					
				})
				$(this).click();
			})
        //将数量放入（）中
        var statusCountMap = data.statusCountMap;
		var notFinishCount = statusCountMap.notFinish;
		var freezeCount = statusCountMap.freeze;
		var finishCount = statusCountMap.finish;
		var overTime = statusCountMap.overTime;
		var draftCount = statusCountMap.draft;
		var allCount = notFinishCount+finishCount+freezeCount;
        $("#allPro").text(allCount);
        $("#unfinPro").text(notFinishCount);
        $("#stopPro").text(freezeCount);
        $("#finishPro").text(finishCount);
        $("#overTimePro").text(overTime);
        $("#draftPro").text(draftCount);
		},
		params : function() {
			var projName = $("#projName").val();
			var projCode = $("#projCode").val();
			var projType = $("#projType").val();
			var pStatus = $("#pStatus").val();
			var projControl = $("#projControl").val();
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			if(totalrow == "" || totalrow==null || totalrow==undefined){
				pages = "";
			}
			return {
				"projName" : projName,
				"projCode": projCode,
				"projType": projType,
				"pStatus":pStatus,
				"projControl":projControl,
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
			};
		}
	});
	$("#pages").val("");
}
//项目列表中的操作
function btnClick(projStatus,projCode,id){

	var str="";
	$(".btnClick").hide();
	$proBtn=$("#proBtn"+id)
	$proBtn.next().empty();
	$proBtn.next().show();
	$proBtn.next().css("display","block")
	if (projStatus== "90") {
		str = "<li><a href=\"javascript:updateProjStatus("+id+",91,'"+projCode+"');\" >正常</a></li>"+
			"<li><a href=\"javascript:del('"+projCode+"');\" class='' >删除</a></li>"+
		         "<li><a href='javascript:void(0)' onclick=taskList('"+projCode+"'); >任务列表</a></li>";
	} else if(projStatus == "91"){
		str = "<li><a href='javascript:updateProjStatus("+id+","+"96);'  >冻结</a></li>"+
              "<li><a href='javascript:updateProjStatus("+id+","+"97);'  >完成</a></li>"+
              "<li><a href='javascript:promptBox(1,"+id+");' >进度</a></li>"+
              "<li><a href='javascript:void(0)' onclick=taskList('"+projCode+"'); >任务列表</a></li>";
	}else if(projStatus == "93"){
		str= "<li><a href='javascript:updateProjStatus("+id+","+"91);' >正常</a></li>"+
          "<li><a href='javascript:updateProjStatus("+id+","+"96);'  >冻结</a></li>"+
          "<li><a href='javascript:updateProjStatus("+id+","+"97);'  >完成</a></li>"+
          "<li><a href='javascript:promptBox(1,"+id+");' >进度</a></li>"+
          "<li><a href='javascript:void(0)' onclick=taskList('"+projCode+"'); >任务列表</a></li>";
	}else if(projStatus == "96"){
		str = "<li><a href='javascript:updateProjStatus("+id+","+"91);'  >正常</a></li>"+
          "<li><a href='javascript:updateProjStatus("+id+","+"97);'  >完成</a></li>";
	}else if(projStatus == "97"){
		str= "<li><a href='javascript:updateProjStatus("+id+","+"91);'  >正常</a></li>"+
          "<li><a href='javascript:updateProjStatus("+id+","+"96);'  >冻结</a></li>"+
          "<li><a href='javascript:promptBox(1,"+id+");'>进度</a></li>"+
          "<li><a href='javascript:void(0)' onclick=taskList('"+projCode+"'); >任务列表</a></li>"+
          "<li><a href='javascript:exportFile(\""+projCode+"\");' >导出文档</a></li>";
	}
	$proBtn.next().append(str);
	
	var $li=$proBtn.next().find('li');
	
	$proBtn.next().width($li.length*60);

	$proBtn.next().mouseover(function(e){
		$(this).show();
		
		 e.stopPropagation();
	})
	$proBtn.next().mouseout(function(e){
		$(this).hide();
		
		 e.stopPropagation();
	})
	$proBtn.click(function(e){
		$(this).next().show();
		 e.stopPropagation();
	})
}
//项目进度
function promptBox(Mes,id,projStatus){
    var str="";
    str="<div class='promptWarp'></div>"+
        "<div class='promptBox' id='promptBox' onkeydown='promptEnter(event,"+id+","+Mes+");'>"+
           " <p>请输入进度：</p>"+
            " <input type='text' id='promptInput' />" +
            "<span>%</span>"+
            "<p id='promptTxt'></p>"+
            "<div class='btn-list'>"+
            "<a href='javascript:promptSure("+Mes+","+id+","+projStatus+")' class='promptSure'>确定</a>"+
            "<a href='javascript:promptHide();' class='promptExit'>取消</a></div></div>";
    $("body").append(str);

    $("#promptInput").val("").focus();
    $("#promptTxt").html("");
}
//进度提交
function promptSure(Mes,id,projStatus){
    var $num=$("#promptInput").val();
    $num=parseInt($num,10);
    if($num>=0&&$num<=100){    	
        promptHide();
        $("tr[rel='"+id+"'] .schedule").find("span").html($num+"%");
        $("tr[rel='"+id+"'] .schedule").find("b").css("width",$num+"%");
    }else if($num>100) {
        $("#promptInput").val("100");
    }else {
        $("#promptTxt").html("请输入0--100的数字！！！")
    }
    if($num == 100){
    	$("#taskStatusSpan"+id).html("完成");
    }else{
    	$("#taskStatusSpan"+id).html("进行中");
    }
    
    if($num>=0 && $num<=100){
    	//写入后台
        if (Mes == 1) {
        	$.ajax({
            	url:getPath()+"/updateProjSpeed.do",
        		data:{"projId":id,"projNum":$num,"projStatus":projStatus},
        		type:"post",
        		dataType:"text",
        		async: false,
        		success:function(suc){
        			if (suc == "succ") {
        				$("#pages").val(1);
        				showProjInfoList();
        			}
        		},
            })
    	}else if(Mes == 2){
    		$.ajax({
    	    	url:getPath()+"/updateTaskSpeed.do",
    			data:{"taskId":id,"taskNum":$num},
    			type:"post",
    			dataType:"text",
    			async: false,
    			success:function(suc){
    				if (suc == "succ") {
    					
    				}
    			},
    	    })
    	}
    }
}
function promptHide(){
    $(".promptWarp").remove();
    $(".promptBox").remove();
}
function promptEnter(event,id,Mes){
    if (event.keyCode==13)   //回车键的键值为13
        promptSure(Mes,id);  //调用登录按钮的登录事件
}
//我的项目:查询
function selectId(){
	showProjInfoList()
}
//我的项目：跳转添加界面
function add() {
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var paramProjName = $("#projName").val();
	var paramProjType = $("#projType").val();
	var pStatus = $("#pStatus").val();
	location.href = getPath() + "/toAddProjPage.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&paramProjName="+paramProjName+
	"&paramProjType="+paramProjType+"&pStatus="+pStatus;
}

//我的项目：跳转修改界面
function edit(projCode){
	var pStatus = $("#pStatus").val();
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var paramProjName = $("#projName").val();
	var paramProjType = $("#projType").val();
	var projControl = $("#projControl").val();
	location.href = getPath() + "/toUpdateProj.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&projCode="+projCode+"&state=projState"+"&paramProjName="+paramProjName+
	"&paramProjType="+paramProjType+"&pStatus="+pStatus+"&projControl="+projControl;
}
//项目状态变更
function updateProjStatus(projId,projStatus,projCode){
	$.ajax({
		url:getPath()+"/updateProjStatus.do",
		data:{"projId":projId,"projStatus":projStatus,"projCode":projCode},
		type:"post",
		dataType:"text",
		success:function(suc){
			if (suc == "success") {
				$("#pages").val(1);
				showProjInfoList();
			}
			if(suc == "noTask"){
				alert("您没有创建任何任务");
			}
		},
		error:function(){
			alert("错误");
		}
		
	})
	
}
//我的项目删除
function del(projCode){
	if (!confirm("确定删除该项目吗？")) {
		return;
	}
	$.ajax({
		url : getPath() + '/deleteProjInfo.do',
		data : {
			"projCode" : projCode
		},
		dataType : 'text',
		type : "post",
		traditional : true,// 属性在这里设置
		success : function(succ) {
			if (succ == "succ") {
				showProjInfoList()
			}
		},
		error : function(){
			alert("删除项目失败");
			showProjInfoList()
		}
	});
	

}

//项目列表操作中的任务列表
function showProjTaskList() {
	
	$('#paging').empty();
	$('#paging').bPage({
		url : getPath() + "/projTaskList.do",
		// 开启异步处理模式
		asyncLoad : true,
		// 关闭服务端页面模式
		serverSidePage : false,
		// 数据自定义填充
		render : function(data) {
			var projBody = $("#projBody").empty();
			var projTaskList = data.projTaskList;
			var pageNumber = data.pageNumber;
			var pageSize = data.pageSize;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			$("#bPageDropList").val(pageSize);
			var state = data.state;
			var idTaskHead = $("#idTaskHead");
			if($("#idTaskHead option").size() == 0){
				var memberList = data.memberList;
				var strOption = "<option value=''>请选择任务负责人</option>";
				$.each(memberList,function(i,member){
					strOption += "<option value = " + member.idMember + ">" + member.extend1 +"</option>";
				})
				idTaskHead.append(strOption);
			}
			$.each(projTaskList,function(k, projTask) {
				var id=projTask.id;
				var taskStatus=projTask.taskStatus;
				var schedule=projTask.taskSpeed;
	        	schedule=Number(schedule*100).toFixed(0);
	        	schedule+="%";
				var status = "";
				if(taskStatus == "80"){
					status = "进行中";
				}else if(taskStatus == "85"){
					status = "完成";
				}
				var projTd = "<tr><td style='text-align:left;'>"
					+ "<a href='javascript:void(0);' onclick = popTask("+id+") class='btnHover' title='"+projTask.nameTask+"'>"+projTask.nameTask
					+ "</td><td>" 
					+ projTask.extend2
					+ "</td><td>" 
					+ getFormatDate(new Date(projTask.dateStart),"yyyy-MM-dd")
					+ "</td><td>" 
					+ getFormatDate(new Date(projTask.dateEnd),"yyyy-MM-dd")
					+ "</td><td>" 
					+ status
					+ "</td><td class='schedule'>" +"<span>"+schedule+"</span><p><b style='width:"+schedule+"';></td>";
					if (state != "projTaskState") {
						projTd += "<td class='nostr'>";
						if (taskStatus != "85") {
							projTd += "<a href='javascript:addChildrenTask("+id+");' class='btn btn_new_small btn_green' >添加子任务</a>";
						}
						projTd += "<a href='javascript:taskSpeed("+id+");' class='btn btn_new_small btn_yellow' >任务进度</a>"+
								"<a href='javascript:taskTalk("+id+");' class='btn btn_new_small btn_yellow' >任务交流</a>"+
								"<a href='javascript:delTask("+id+");' class='btn btn_new_small btn_red' >删除</a>"+
								"<a href='javascript:editTask("+id+");' class='btn btn_new_small btn_yellow' >编辑</a>";
						projTd += "</td></tr>";
					}
					projBody.append(projTd);
			})
		},
		params : function() {
			var idTaskHead = $("#idTaskHead").val();
			var milestoneTask = $("#milestoneTask").val();
			var taskName = $("#taskName").val();
			var projCode = $("#projCode").val();
			var pageSize = $("#pageSize").val();
			var state = $("#state").val();
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			return {
				"taskName":taskName,
				"idTaskHead": idTaskHead,
				"milestoneTask": milestoneTask,
				"projCode":projCode,
				"pageSize":pageSize,
				"state":state,
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
			};
		}
	});
	$("#pages").val("");
}
function moreAdd(){
	layer.open({
	    type: 2,
	    title: '批量导入',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '400px'],
	    content: ['views/proj/3.jsp','yes']
	 })
}
//项目列表-任务列表-任务处理
function popTask(taskId){
	var projCode = $("#projCode").val();
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath()+"/toProjTaskDeal.do?pageNumber="+pageNumber+
	"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&taskId="+taskId+"&state=projMineTask"+"&cancel=popCancel"+"&projCode="+projCode+"&fromTask=1";
}
function taskList(projCode){
	var jump_url = getPath() + "/toProjTaskPop.do?projCode="+projCode+"&state=projTaskState";
	layer.open({
	    type: 2,
	    title: '任务列表',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['800px', '400px'],
	    content: [jump_url,'yes']

	  })
}

//改变项目类型的说明
function changeTypeRemark(){
	var typeId = $("#projType option:selected").val();
	$.ajax({
		data:{"typeId": typeId},
		dataType : 'json',
		async: false,
		type : 'post',
		url : getPath() + "/changeProjTypeRemark.do",
		success : function(data){
			$(".anwser .txt").text(isnull(data.remark));
		}
	})
	
}
//导出文档
function exportFile(projCode){
	var path = getPath();
	$.ajax({
		url:path+"/whetherOrNotFile.do",
		data:{"projCode":projCode},
		dataType:'json',
		type:"post",
		success:function(data){
			if(data.result == "success"){
				location.href = path + "/exportAllFile.do?zipFilePath="+data.zipFilePath+"&fileName="+data.fileName;
			}else{
				alert(data.result);
			}
		},
		error:function(error){
			alert("打包失败..不存在该项目的文件夹！");
		}
	})
}