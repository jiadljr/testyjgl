//点击新建
function add(){
	$("#operType").val("add");
	$("#typeName").val("");
	$("#typeColor").val("#FFFFFF");
	$("#typeRemark").val("");
	$(".shadowBox").show()
	$("#typeColor").removeAttr("disabled").css("border","1px solid #ccc");
	$("#typeRemark").removeAttr("disabled").css("border","1px solid #ccc")
	$("#typeName").removeAttr("disabled").css("border","1px solid #ccc")
	$("#addType").show();
	$("#updateType").hide();
	$("#cancel").show();
	
	$("#newAdd").hide();
	$("#newSave").hide();
	$("#close").hide();
	$("#delete").hide();
	
	
}
//点击编辑
function save(){
	$("#operType").val("update");
	var typeId = $("#typeId").val();
	
	if(typeId == ""){
		alert("请选择类型进行编辑！");
		return;
	}
	$(".shadowBox").show()
	$("#typeColor").removeAttr("disabled").css("border","1px solid #ccc");
	$("#typeRemark").removeAttr("disabled").css("border","1px solid #ccc")
	$("#typeName").removeAttr("disabled").css("border","1px solid #ccc")
	$("#addtype").hide();
	$("#eidttype").show();
	
	$("#addType").hide();
	$("#updateType").show();
	$("#cancel").show();
	
	$("#newAdd").hide();
	$("#newSave").hide();
	$("#close").hide();
	$("#delete").hide();
}
//查询单个项目类型
function showTypeInform(typeId){
	$.ajax({
		url:getPath()+"/findProjTypeInform.do",
		dataType:"json",
		data:{"typeId":typeId},
		success:function(data){
			$("#typeId").val(data.id);
			$("#typeName").val(data.name);
			$("#typeRemark").val(data.remark);
			if(data.color != null){
				$("#typeColor").val(data.color);
				$("#updtypeColor").val(data.color);
			}
			$("#updtypeName").val(data.name);
			$("#updtypeRemark").val(data.remark);
		}
	})
}
//修改类型
function saveType(){
	var typeId = $("#typeId").val();
	var typeName = $("#typeName").val();
	var typeRemark = $("#typeRemark").val();
	var typeColor = $("#typeColor").val();

	if(typeName==""||typeName.length==0){
		$("#typeName").addClass("error")
		return false;
	}
	var typeData = {"typeId":typeId,"typeName":typeName,"typeRemark":typeRemark,"typeColor":typeColor}
	$.ajax({
		url:getPath()+"/updateProjTypeInform.do",
		dataType:"text",
		data:typeData,
		success:function(data){
			if(data == "success"){
				alert("保存成功！");
				location.href = getPath()+"/toProjTypeConfig.do";
			}else{
				alert("失败")
			}
		}
	})
}
//添加类型
function addType(){
	var typeName = $("#typeName").val();
	if(typeName==""||typeName.length==0){
		$("#typeName").addClass("error")
		return false;
	}
	var typeRemark = $("#typeRemark").val();
	var typeColor = $("#typeColor").val();
	
	var typeData = {"typeName":typeName,"typeRemark":typeRemark,"typeColor":typeColor}
	$.ajax({
		url:getPath()+"/insertProjTypeInform.do",
		dataType:"text",
		data:typeData,
		success:function(data){
			if(data == "success"){
				alert("保存成功！");
				location.href = getPath()+"/toProjTypeConfig.do";
			}else{
				alert("失败")
			}
		}
	})
}
//删除
function delType(){
	if(!confirm("是否删除类型？")){
		return;
	}
	var typeId = $("#typeId").val();
	if(typeId == ""){
		alert("请选择类型进行删除！");
		return;
	}
	$.ajax({
		url:getPath()+"/deleteProjType.do",
		dataType:"text",
		data:{"typeId":typeId},
		success:function(data){
			if(data == "success"){
				location.href = getPath()+"/toProjTypeConfig.do";
			}else{
				alert("失败")
			}
		}
	})
}
//取消
function cancel(){
	$(".shadowBox").hide();
	if($("#operType").val() == "add"){
		var updtypeName = $("#updtypeName").val();
		var updtypeRemark = $("#updtypeRemark").val();
		var updtypeColor = $("#updtypeColor").val();
		$("#typeName").val(updtypeName);
		$("#typeRemark").val(updtypeRemark);
		$("#typeColor").val(updtypeColor);
		var hoverTypeId = $("#hoverTypeId").val();
		$("#"+hoverTypeId).addClass("hover");
	}
	$("#typeColor").attr("disabled","disabled").css("border","none");
	$("#typeRemark").attr("disabled","disabled").css("border","none");
	$("#typeName").attr("disabled","disabled").css("border","none");
	$("#newAdd").show();
	$("#newSave").show();
	$("#close").show();
	$("#delete").show();
	$("#addType").hide();
	$("#updateType").hide();
	$("#cancel").hide();
}
//关闭
function closeType(){
	var index = parent.layer.getFrameIndex(window.name); 
	parent.layer.close(index);
}
