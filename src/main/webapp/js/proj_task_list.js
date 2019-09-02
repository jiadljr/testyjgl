$(function(){
	$("#taskName").blur(function(){
		if(Trim($("#taskName").val())==""){
			$(this).addClass("error")
		}
	});
})

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
			var taskProjName = $("#taskProjName");
			var pageNumber = data.pageNumber;
			var pageSize = data.pageSize;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			$("#bPageDropList").val(pageSize);
			$.each(projTaskList,function(k, projTask) {
				var id=projTask.id;
				var schedule=projTask.taskSpeed;
	        	schedule=Number(schedule*100).toFixed(0);
	        	schedule+="%";
	        	//
	        	var warning=""
					if(projTask.pf == 1){
						warning="rShowWarn"//alert("超期啦！！");//
		            }else{
		            	warning="rHideWarn"
		            }
	        	var flag="";
	        	if(projTask.milestoneTask==0){
	        		flag="flagHide"
	        	}else{
	        		flag="flagShow"
	        	}
	        
				var projTd = "<tr rel='"+id+"'><td style='text-align:left; position:relative;' >"
					+ "<a title='"+projTask.nameTask+"' href='javascript:void(0);' onclick = showTask("+id+") class='btnHover' >"+projTask.nameTask
					+ "</a><b class='iconClass'><i  title='任务超期' class='"+warning+" fa fa-warning'></i><i  title='里程碑任务' class='"+flag+" fa fa-flag'></i></b></td><td style='text-align:left;'>" 
					+ projTask.extend3
					+ "</td><td>" 
					+ projTask.extend1
					+ "</td><td>" 
					+ getFormatDate(new Date(projTask.dateStart),"yyyy-MM-dd")
					+ "</td><td>" 
					+ getFormatDate(new Date(projTask.dateEnd),"yyyy-MM-dd")
					+ "</td><td class='schedule'>" +"<span>"+schedule+"</span><p><b style='width:"+schedule+"';></td>";
					projTd += "<td>";
					projTd += "<a href='javascript:promptBox(2,"+id+");' class='btn btn_new_small btn_green' >进度</a>"+
					         "<a href='#' onclick=\"taskRecord("+id+",'"+projTask.nameTask+"')\" class='btn btn_new_small btn_red' >任务记录</a>";
				projTd += "</td></tr>";
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
			//不同状态的数量
			var taskStatusCount = data.taskStatusCount;
			var normal = taskStatusCount.normal;
			var overTime = taskStatusCount.overTime;
			var finish = taskStatusCount.finish;
			var all = normal+finish;
			
			$("#allTask").text(all);
			$("#normalTask").text(normal);
			$("#overTimeTask").text(overTime);
			$("#finishTask").text(finish);
			
		},
		params : function() {
			var projCode = $("#projCode").val();
			var taskProjName = $("#taskProjName").val();
			var taskName = $("#taskName").val();
			var pStatus = $("#pStatus").val();
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			return {
				"taskProjName" : taskProjName,
				"taskName": taskName,
				"projCode":projCode,
				"state" : "taskState",
				"pStatus":pStatus,
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
				"freezeTask":"freezeTaskYes"
			};
		}
	});
	$("#pages").val("");
}

//任务交流
function taskRecord(taskId,taskName){
	var jump_url = getPath()+"/toProjTaskRecord.do?taskId="+taskId+"&taskName="+taskName;
	layer.open({
	    type: 2,
	    title: '任务记录',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '400px'],
	    content: [jump_url,'yes']
	  })
}
//查看任务
function showTask(taskId){
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var pStatus = $("#pStatus").val();
	location.href = getPath()+"/toProjTaskDeal.do?taskId="+taskId+"&state="+"mineTask"+"&pageNumber="+pageNumber+
		"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pStatus="+pStatus;
}
//加载相关文件
function loadTaskFile(){
	var taskId = $("#taskId").val();
	var state = $("#state").val();
	$.ajax({
		url:getPath()+"/taskFileList.do",
		data:{"taskId":taskId},
		dataType:"json",
		type:"post",
		success:function(data){
			var file = data.files;
			var str = "";
			$("#fileListDiv").empty();
			document.getElementById('text').innerHTML = "";
			$("#app").empty();
			$.each(file,function(i,val){
				str += "<div title='"+file[i].fileName+"' class='drFileItem' id="+file[i].fileId+"><i class='fa fa-file'></i><br><a class='drFileName drFileNames' href='javascript:;'>"+file[i].fileName+"</a><a class='drDel btn_green'  href='#' onclick=downFile("+file[i].fileId+")>下载</a>";
				if(state != "projControlTask"){
					str+="<a class='drDel btn_red' href='javascript:void(0);' onclick=delFile("+file[i].fileId+","+file[i].projFileId+")>删除</a>";
				}
				str+="</div>";
			})
			$("#fileListDiv").append(str);
		},
		error:function(error){
			alert("失败");
		}
	})
	
}

//任务记录
//保存
function saveTaskRecord(){
	var taskRecord = $("#taskRecord").val();
	$("#taskRecord").next("p").remove();

	var taskId = $("#taskId").val();
	if(taskRecord==""||$("#taskRecord").hasClass("error")){
		$("#taskRecord").addClass("error");
		return false;
	}
	if(taskRecord.length>200){
		$("#taskRecord").after("<p style='width:200px; height:20px; float:left; color:red;'>请输入200字以下！</p>")
		return false;
	}else{
		$("#taskRecord").next("p").remove();
	}
	$.ajax({
		url:getPath()+"/insertTaskRecord.do",
		data:{"taskRecord":taskRecord,"taskId":taskId},
		dateType:"text",
		type:"post",
		success:function(success){
			if(success == "succ"){
				$("#taskRecord").val("");
				$("#taskDescList").prepend("<li><p><strong>"+$("#user_name").val()+":</strong>"+taskRecord+"</p><span>"+getFormatDate(new Date(),"yyyy-MM-dd hh:mm:ss")+"</span></li>")
			}
		},
		error:function(){
			alert("新增数据失败！");
		}
	})
}
//加载任务记录
function loadTaskRecord(){
	var taskId = $("#taskId").val();
	var taskName = $("#taskName").val();
	$("#taskDescList").empty();
	$.ajax({
		url:getPath()+"/getTaskRecordList.do",
		data:{"taskId":taskId},
		dataType:"json",
		type:"post",
		success:function(data){
			var arrData=data.taskRecordList;
			var str=""
				
			for(var i=arrData.length-1;i>=0;i--){
				str+="<li><p><strong>"+arrData[i].extend1+":</strong>"+arrData[i].taskRecord+"</p><span>"+getFormatDate(new Date(arrData[i].createTime),"yyyy-MM-dd hh:mm:ss")+"</span></li>";
			}
			$("#taskDescList").append(str);
		},
		error:function(error){
			alert("失败");
		}
	})
	
}
//进度条
function promptBox(Mes,id){
    var str="";
    str="<div class='promptWarp'></div>"+
        "<div class='promptBox' id='promptBox' onkeydown='promptEnter(event,"+id+","+Mes+");'>"+
           " <p>请输入进度：</p>"+
            " <input type='text' id='promptInput' />" +
            "<span>%</span>"+
            "<p id='promptTxt'></p>"+
            "<div class='btn-list'>"+
            "<a href='javascript:promptSure("+Mes+","+id+")' class='promptSure'>确定</a>"+
            "<a href='javascript:promptHide();' class='promptExit'>取消</a></div></div>";
    $("body").append(str);

    $("#promptInput").val("").focus();
    $("#promptTxt").html("");
}

function promptSure(Mes,id){
    var $num=$("#promptInput").val();
    $num=parseInt($num,10);
    if($num>=0&&$num<=100){    	
        promptHide();
    }else if($num>100) {
        $("#promptInput").val("100");
    }else {
        $("#promptTxt").html("请输入0--100的数字！！！")
    }
    //写入后台
	$.ajax({
    	url:getPath()+"/updateTaskSpeed.do",
		data:{"taskId":id,"taskNum":$num},
		type:"post",
		dataType:"text",
		success:function(suc){
			if (suc == "succ") {
				$("#pages").val(1);
				showProjTaskList();
			}
		},
    })
    
}
function promptHide(){
    $(".promptWarp").remove();
    $(".promptBox").remove();
}
function promptEnter(event,id,Mes){
    if (event.keyCode==13)   //回车键的键值为13
        promptSure(Mes,id);  //调用登录按钮的登录事件
}
function descEnter(event){
    if (event.keyCode==13)   //回车键的键值为13
    	saveTaskRecord() ; //调用登录按钮的登录事件
}
//返回按钮
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var pStatus = $("#pStatus").val();
	var url = "/toProjTaskPage.do";
	var str = "";
	if($("#cancel").val() == "popCancel"){
		var projCode = $("#projCode").val();
		url = "/toProjTaskPop.do";
		str = "&state=projTaskState&projCode="+projCode;
	}
	location.href = getPath() + url + "?pageNumber="+pageNumber+
	"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages+"&pStatus="+pStatus+str;
}
//记录导出
function report(){
	if($("#taskDescList li").length == 0){
		alert("没有可以导出的数据！");
		return;
	}
	
	var path = getPath();
	var taskId = $("#taskId").val();
	location.href = path+"/taskRecordExport.do?taskId="+taskId;
	
}

