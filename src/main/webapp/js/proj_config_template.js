$(function(){
	
})

function taskClick(){
	$("#TaskList li:eq(0) a").addClass("hover");
	$("#TaskList li a").click(function(){
		
		$("#TaskList li a").removeClass("hover");
	
		$(this).addClass("hover")	
	})
}
function tempClick(){
	$("#projTemplateList li:eq(0) a").addClass("hover");
	$("#projTemplateList li a").click(function(){
		
		$("#projTemplateList li a").removeClass("hover");
	
		$(this).addClass("hover")	
	})
}
//查询项目模板名称
function getTemplateName(){
	$.ajax({
		url:getPath()+"/findProjTemplateList.do",
		type:"post",
		dataType:"json",
		data:{},
		success:function(data){
			var projTemplateList = data.projTemplateList;
			var templateTaskList = data.templateTaskList;
			if(projTemplateList.length != 0){
				$("#templateId").val(projTemplateList[0].id);
			}else{
				$("#templateId").val("");
			}
			//遍历模板名称
			var projTemplate = $("#projTemplateList").empty();
			var projTemplatestr = "";
			$.each(projTemplateList,function(n,projTemplate){
				projTemplatestr += "<li><a href='#' class='' onclick=showPhaseTask("+projTemplate.id+")>"+projTemplate.templateName+"</a></li>";
			})
			projTemplate.append(projTemplatestr);
			tempClick()
		
			//阶段任务
			var TaskList = $("#TaskList").empty();
			var projTemplateTaskstr = "";
			$.each(templateTaskList,function(i,templateTask){
				projTemplateTaskstr += "<li><a href='#' class='' onclick=showPhaseTaskInform("+templateTask.id+")>"+templateTask.taskName+"</a></li>";
			})
			TaskList.append(projTemplateTaskstr);
			taskClick();
			//第一个阶段任务信息
			var phaseTask = data.phaseTask;
			if(phaseTask != null && phaseTask != undefined){
				$("#phaseTaskId").val(phaseTask.id);
				$("#phaseTitle").val(phaseTask.taskName);
				$("#taskName").val(phaseTask.taskName);
				$("#phaseRemark").val(phaseTask.phaseRemark);
				$("#phaseShow").val(phaseTask.phaseShow);
			}else{
				$("#phaseTaskId").val("");
				document.getElementById("form1").reset();
			}
			
		},
		error:function(){
			alert("数据异常，请联系管理员！");
		}
	})
}

//展示阶段任务列表
function showPhaseTask(templateId){
	$("#templateId").val(templateId);
	$.ajax({
		url:getPath()+"/findPhaseTaskNameList.do",
		type:"post",
		dataType:"json",
		data:{"templateId":templateId},
		success:function(data){
			var phaseTaskNameList = data.templateTaskList;
			//阶段任务
			var TaskList = $("#TaskList").empty();
			var projTemplateTaskstr = "";
			$.each(phaseTaskNameList,function(i,phaseTask){
				projTemplateTaskstr += "<li><a href='#' class='' onclick=showPhaseTaskInform("+phaseTask.id+")>"+phaseTask.taskName+"</a></li>";
			})
			TaskList.append(projTemplateTaskstr);
			taskClick()
			//第
			//第一个阶段任务信息
			var phaseTask = data.phaseTask;
			if(phaseTask != null && phaseTask != undefined){
				$("#phaseTaskId").val(phaseTask.id);
				$("#phaseTitle").val(phaseTask.taskName);
				$("#taskName").val(phaseTask.taskName);
				$("#phaseRemark").val(phaseTask.phaseRemark);
				$("#phaseShow").val(phaseTask.phaseShow);
			}else{
				document.getElementById("form1").reset();
			}
		},
		error:function(){
			alert("数据异常，请联系管理员！");
		}
	})
}
//展示阶段任务信息，并可以编辑保存
function showPhaseTaskInform(phaseTaskId){
	$.ajax({
		url:getPath()+"/findPhaseTaskInform.do",
		type:"post",
		dataType:"json",
		data:{"phaseTaskId":phaseTaskId},
		success:function(data){
			var phaseTask = data;
			//阶段任务
			
			$("#phaseTaskId").val(phaseTask.id);
			$("#phaseTitle").val(phaseTask.taskName);
			$("#taskName").val(phaseTask.taskName);
			$("#phaseRemark").val(phaseTask.phaseRemark);
			$("#phaseShow").val(phaseTask.phaseShow);
		},
		error:function(){
			alert("数据异常，请联系管理员！");
		}
	})
}
//点击编辑按钮显示保存和取消
function showSaveButn(){
	var phaseTaskId = $("#phaseTaskId").val();
	if(phaseTaskId == ""){
		alert("请选择任务进行编辑");
		return;
	}
	$("#phaseRemark").removeAttr("disabled").css("border","1px solid #ccc");
	$("#phaseShow").removeAttr("disabled").css("border","1px solid #ccc");
	$("#updateButn").hide();
	$("#saveButn").show();
	$("#cancelButn").show();
	$(".shadow").show();
	$(".shadowBox1").show();
	$(".shadowBox2").show();
}
//保存
function savePhaseTask(){
	var phaseTaskId = $("#phaseTaskId").val();
	$.ajax({
		url:getPath()+"/savePhaseTaskInform.do",
		type:"post",
		dataType:"text",
		data:$("#form1").serialize(),
		success:function(data){
			if(data == "success"){
				alert("保存成功！");
				$("#phaseRemark").attr("disabled","disabled").css("border","1px solid #ccc");
				$("#phaseShow").attr("disabled","disabled").css("border","1px solid #ccc");
				$("#updateButn").show();
				$("#saveButn").hide();
				$("#cancelButn").hide();
				$(".shadowBox1").hide();
				$(".shadow").hide();
				$(".shadowBox2").hide();
			}
		},
		error:function(result){
			alert($.parseJSON(result.responseText).msg);
		}
	})
	
}
//取消保存
function cancelSave(){
	$("#phaseRemark").attr("disabled","disabled").css("border","1px solid #ccc");
	$("#phaseShow").attr("disabled","disabled").css("border","1px solid #ccc");
	$("#updateButn").show();
	$("#saveButn").hide();
	$("#cancelButn").hide();
	$(".shadowBox1").hide();
	$(".shadowBox2").hide();
	$(".shadow").hide();
}
//返回至项目配置页面
function cancelToProjConfig(){
	location.href = getPath() + "/toProjConfig.do";
}
//新建模板
function addTemplate(){
	var jump_url = getPath()+"/toAddTemplate.do";
	layer.open({
	    type: 2,
	    title: '新建模板',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['462px', '480px'],
	    content: [jump_url,'yes']
	  })
}
//编辑模板
function updateTemplate(){
	var templateId = $("#templateId").val();
	if(templateId == ""){
		alert("请选择模板进行编辑！");
		return;
	}
	var jump_url = getPath()+"/toUpdateTemplate.do?templateId="+templateId;
	layer.open({
		type: 2,
		title: '编辑模板',
		scrollbar: true,
		shadeClose: false,
		area: ['462px', '480px'],
		content: [jump_url,'yes']
	})
}
//删除模板
function deleteTemplate(){
	var templateId = $("#templateId").val();
	if(templateId == ""){
		alert("请选择一个模板后再删除！");
		return;
	}
	if(!confirm("是否删除此项目模板？")){
		return;
	}
	$.ajax({
		url:getPath()+"/deleteProjTemplate.do",
		type:"post",
		dataType:"text",
		data:{"templateId":templateId},
		success:function(data){
			if(data == "success"){
				getTemplateName();
			}
		},
		error:function(){
			alert("删除失败！");
		}
	})
}
//查看模板
function showTemplate(){
	var templateId = $("#templateId").val();
	if(templateId == ""){
		alert("请选择模板进行查看！");
		return;
	}
	var newHref = getPath()+"/showProjProcess.do?templateId="+templateId;
	window.open(newHref)
}
