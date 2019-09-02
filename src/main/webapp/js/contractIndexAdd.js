function addContractList(){
	var contractName=$("#contractName").val();
	var firstPartyUnit=$("#firstPartyUnit").val();
	var secondPartyUnit=$("#secondPartyUnit").val();
	var firstPartyPrincipal=$("#firstPartyPrincipal").val();
	var secondPartyPrincipal=$("#secondPartyPrincipal").val();
	var firstPartyContactWay=$("#firstPartyContactWay").val();
	var secondPartyContactWay=$("#secondPartyContactWay").val();
	var signTime=$("#signTime").val();
	var currencyId=$("#currencyId option:selected").val();
	var fundSource=$("#fundSource option:selected").val();
	var paymentMethod=$("#paymentMethod option:selected").val();
	var contractStartTime=$("#contractStartTime").val();
	var contractEndTime=$("#contractEndTime").val();
	var contractObject=$("#contractObject").val();
	var contractDesc=$("#contractDesc").val();
	var contractMoney=$("#contractMoney").val()-0;
	var formDate = new FormData($("#form1")[0]);

	if (new Date(contractStartTime).getTime() > new Date(contractEndTime).getTime()) {
		alert("开始时间不能大于结束时间");
		return;
	}
	formDate.append("contractObject",contractObject);
	formDate.append("contractDesc",contractDesc);
	formDate.append("contractMoney",contractMoney);
	$.each($(".inTxt"),function(){
		if($(this).val()==""||$(this).val().length==0){
			$(this).addClass("error")
		}
	})
	$.each($(".inTxt"),function(){
		if($(this).hasClass()=="error"){
			return false;
		}
	})
	$.ajax({
    	url:getPath()+"/insertContract.do",
		data:formDate,
		type:"post",
		dataType:"json",
		traditional: true,
		async: false,
		processData : false,
	    contentType : false,
		success:function(data){
			var insertContractInfo = data.insertContractInfo;
			var contractId = data.contractId;
			if(insertContractInfo == 1){
				insertContractPaymentTerms(contractId);
			}
		},
    })
}
function insertContractPaymentTerms(contractId){
	var lastLen=$(".termList:last").attr('data-mes')-0;
	var num=0;


	for(var j=1;j<=lastLen;j++){
		if($("input:radio[name='payFor"+j+"']:checked").val()=="1"){
			num+=$("input[name='payFor"+j+"']:checked").attr("data-main")-0;
		}else{
			num+=($("input[name='payFor"+j+"']:checked").attr("data-main")/100)*contractMoney;
		}
	}
	var paySoArray=[];
	var pay1="";
	var pay2="";
	$.each($(".termList"),function(){
		if($(this).find("input:radio:checked").val()=="1"){
			pay1=$(this).find("input:radio:checked").next().next().val();
			pay2="";
		}else{
			pay1="";
			pay2=$(this).find("input:radio:checked").next().next().val();
		}
		var obj={
				"name":$(this).find("input:text:eq(0)").val(),
				"pay1":pay1,
				"pay2":pay2,
				"contractId":contractId,
				
		}
		paySoArray.push(obj);
		
	})
	$.each($(".payTitle"),function(){
		if($(this).val()==""||$(this).val().length==0){
			$(this).addClass("error")
		}
	})
	$.each($(".payTitle"),function(){
		if($(this).hasClass()=="error"){
			return false;
		}
	})
	if(num>contractMoney){
		alert("付款条款总和不可大于实际金额!");
		return false;
	}
	$.ajax({
		type:"post",
		url:getPath()+"/insertContractPayMentTermsBatch.do",
	    data:{"paySoArray":JSON.stringify(paySoArray)},
	    success:function(){
	    	
	    },error:function(err){
	    	alert("新增付款条款失败，请重新");
	    }
	})
}