function selectTime(id){
	$.ajax({
    	url:getPath()+"/selectContractPayMentTermsById.do",
		data:{
			"paymentId":id
		},
		type:"post",
		dataType:"json",
		async: false,
		success:function(data){
			console.log(data)
		    $("#paymentAmount").val(data.paymentAmount)
		},
    })
}
function register(){

	var definition=$("input[name='definition']:checked").val()
	$.each($(".finalInput"),function(){
		if($(this).val()=="")
			$(this).addClass("error")
	})
		

	if(definition==1){
	
		if($("#payNames option:selected").text()==""){
			$("#payNames").addClass("error")
		}
		var payId= $("#payNames option:selected").val();
		var payName=$("#payNames option:selected").text();	
	}else if(definition==2){
		var payId= "";
		var payName=$("#payNameStr").val();
		if($(".defInput").val()==""){
			$(".defInput").addClass("error");
		}

	}

		if($(".nullInput").hasClass("error")){
			return false;
		}
	$.ajax({
    	url:getPath()+"/updateContractPaymentTerms.do",
		data:{
			"invoice":$("#invoice").val(),
			"paymentDesc":$("#paymentDesc").val(),	
			"contractId":GetUrlStr("id"),
			"payId":payId,
			"payName":payName,
			"definition":definition,
			"paymentAmount":$("#paymentAmount").val(),
			"paymentTime":$("#paymentTime").val()
		},
		type:"post",
		dataType:"json",
		async: false,
		success:function(){
		  	window.parent.shwoContractList(1);
			var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			parent.layer.close(index);
		},
		error:function(){
			alert("失败")
		}
    })
}