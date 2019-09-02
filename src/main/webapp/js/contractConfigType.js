function showTypeAll(){
	//加载所有合同类型
	$.ajax({
		url:getPath()+"/selectContractTypeAll.do",
		type:"post",
		dateType:"json",
		success:function(data){
			var setTypeList = $("#typeUl").empty();
			var liStr = "";
			$.each(data,function(k,v){
				liStr += "<li><a href='#' onclick='showTypeInform(\""+v.id+"\")' id='typeId"+v.id+"' >"+v.typeName+"</a></li>";
			})
			setTypeList.append(liStr);
	
			$(".setTypeList li a").click(function(){
				$(".setTypeList li a").removeClass("hover")
				$("#typeName").removeClass("error")
				$(this).addClass("hover")
				
			})
			$("#typeUl li").eq(0).children().click();

		}
		
	})

}

//点击新建
function add(){
	$("#operType").val("add");
	$("#typeName").val("");
	$("#color").val("#FFFFFF");
	$("#typeDesc").val("");
	$(".shadowBox").show()
	$("#color").removeAttr("disabled").css("border","1px solid #ccc");
	$("#typeDesc").removeAttr("disabled").css("border","1px solid #ccc")
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
	var contractTypeId = $("#contractTypeId").val();
	
	if(contractTypeId == ""){
		alert("请选择类型进行编辑！");
		return;
	}
	$(".shadowBox").show()
	$("#color").removeAttr("disabled").css("border","1px solid #ccc");
	$("#typeDesc").removeAttr("disabled").css("border","1px solid #ccc")
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
function showTypeInform(contractTypeId){
	$.ajax({
		url:getPath()+"/selectContractTypeBuId.do",
		dataType:"json",
		data:{"contractTypeId":contractTypeId},
		success:function(data){
			$("#contractTypeId").val(data.id);
			$("#typeName").val(data.typeName);
			$("#typeDesc").val(data.typeExplain);
			if(data.color != null){
				$("#color").val(data.color);
				$("#updtypeColor").val(data.color);
			}
			$("#updtypeName").val(data.typeName);
			$("#updtypeRemark").val(data.typeExplain);
		}
	})
}
//修改类型
function saveType(){
	var typeId = $("#contractTypeId").val();
	var typeName = $("#typeName").val();
	var typeDesc = $("#typeDesc").val();
	var color = $("#color").val();

	if(typeName==""||typeName.length==0){
		$("#typeName").addClass("error")
		return false;
	}
	var typeData = {"typeId":typeId,"typeName":typeName,"typeDesc":typeDesc,"color":color}
	$.ajax({
		url:getPath()+"/updateContractType.do",
		dataType:"text",
		data:typeData,
		success:function(data){
			if(data == "success"){
				alert("保存成功！");
				location.reload()
				/**
				 * 刷新
				 */
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
	var typeDesc = $("#typeDesc").val();
	var color = $("#color").val();
	
	var typeData = {"typeName":typeName,"typeDesc":typeDesc,"color":color}
	$.ajax({
		url:getPath()+"/insertContractType.do",
		dataType:"text",
		data:typeData,
		success:function(data){
			if(data == "success"){
				alert("保存成功！");
				location.reload()
				/**
				 * 刷新
				 */
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
	var typeId = $("#contractTypeId").val();
	if(typeId == ""){
		alert("请选择类型进行删除！");
		return;
	}
	$.ajax({
		url:getPath()+"/deleteContractType.do",
		dataType:"text",
		data:{"typeId":typeId},
		success:function(data){
			if(data == "success"){
				location.reload()
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
		$("#typeDesc").val(updtypeRemark);
		$("#color").val(updtypeColor);
		var hoverTypeId = $("#hoverTypeId").val();
		$("#"+hoverTypeId).addClass("hover");
	}
	$("#color").attr("disabled","disabled").css("border","none");
	$("#typeDesc").attr("disabled","disabled").css("border","none");
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
