function shwoContractList(searchStatus){
	$('#paging').empty();
	$('#paging').bPage({
		url : getPath() + "/selectContractAll.do",
		asyncLoad : true,
		serverSidePage : false,
		render : function(data) {
			$("#contractList").empty();
			var pageNumber = data.pageNumber;
			var pageSize = data.pageSize;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			var contractList=data.contractInfoAll;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			var str="";
			for(var i=0;i<contractList.length;i++){
				var code = contractList[i].code;
				code = isnull(code);
				var honourAgreement  = contractList[i].honourAgreement;
				honourAgreement = isnull(honourAgreement);
				if(honourAgreement == 1){
					honourAgreement = "履约中";
				}else if(honourAgreement == 2){
					honourAgreement = "协商中";
				}else if(honourAgreement == 3){
					honourAgreement = "完成";
				}
				var contractTitle = contractList[i].contractTitle;
				contractTitle = isnull(contractTitle);
				var firstPartyUnit = contractList[i].firstPartyUnit;
				firstPartyUnit = isnull(firstPartyUnit);
				var contractTypeCode = contractList[i].contractTypeCode;
				contractTypeCode = isnull(contractTypeCode);
				var extend1 = contractList[i].extend1;
				extend1 = isnull(extend1);
				var contractMoney = contractList[i].contractMoney;
				contractMoney = isnull(contractMoney);
				str += "<tr>" +
						"<td>"+code+"</td>" +
						"<td>"+contractTitle+"</td>" +
						"<td>"+firstPartyUnit+"</td>" +
						"<td>"+contractTypeCode+"</td>" +
						"<td>"+honourAgreement+"</td>" +
						"<td>"+extend1+"</td>" +
						"<td>"+contractMoney+"</td>" +										
						"<td>"+getFormatDate(new Date(isnull(contractList[i].signTime)),"yyyy-MM-dd")+"</td>"+
						"<td style='position:relative;' class='nostr'><a id='proBtn"+contractList[i].id+"' href='javascript:void(0)'  onclick=btnClick("+contractList[i].honourAgreement+",\""+contractList[i].id+"\"); class='btn btnSlide btn_green hoverPayBtn' >···</a><ul class='btnClick'></ul></td>"			;
					
			}
			 $("#contractList").append(str);
				var relTable=$('.relTable');
				var RH=$('body').height()-164;
				
				var relTable=$('.relTable');
				relTable.height(RH);
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
					$(this).click();
					$(this).click(function(){
						$(this).next().show();					
					})
					
				})
		},
		params : function() {
			var conKeyWord=$("#conKeyWord").val();
			
			var firstPartyUnit=$("#firstPartyUnit").val()
			var contractTypeCode=$("#contractTypeCode option:selected").val()
			var signTimeStart=$("#signTimeStart").val();
			var signTimeEnd=$("#signTimeEnd").val();
			var contractMoneyStrat=$("#contractMoneyStrat").val();
			var contractMoneyEnd=$("#contractMoneyEnd").val();
			var fundSource=$("#fundSource").val();
			var paymentMethod=$("#paymentMethod").val();
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			var obj={"conKeyWord":conKeyWord}

			if(searchStatus==1){
				obj={
						"pages":pages,
			        	"pagenumber":pagenumber,
			        	"pagesize":pagesize,
			        	"totalpage":totalpage,
			        	"totalrow":totalrow,
						"conKeyWord":conKeyWord,
					
				}
			}else if(searchStatus==2){
				obj= {
						"pages":pages,
			        	"pagenumber":pagenumber,
			        	"pagesize":pagesize,
			        	"totalpage":totalpage,
			        	"totalrow":totalrow,
						"conKeyWord":conKeyWord,
						"firstPartyUnit":firstPartyUnit,
						"contractTypeCode":contractTypeCode,
						"signTimeStart":signTimeStart,
						"signTimeEnd":signTimeEnd,
						"contractMoneyStrat":contractMoneyStrat,
						"contractMoneyEnd":contractMoneyEnd,
						"fundSource":fundSource,
						"paymentMethod":paymentMethod				
					}
			}
			return obj;
		}
	});
	$("#pages").val("");
}
function btnClick(honourAgreement,id){

	var str="";
	$(".btnClick").hide();
	$proBtn=$("#proBtn"+id)
	$proBtn.next().empty();
	$proBtn.next().show();
	$proBtn.next().css("display","block")
	if(honourAgreement == 1){
		str="<li><a href='javascript:payCheck(\""+id+"\");'  >付款登记</a></li>"+
        "<li><a href='javascript:payFinsh(\""+id+"\");'  >完成</a></li>"+
        "<li><a href='javascript:consult(\""+id+"\");'>协商</a></li>"+
        "<li><a href='javascript:void(0)' onclick=payInfo(\""+id+"\"); >编辑</a></li>"+
        "<li><a href='javascript:delPay(\""+id+"\");' >删除</a></li>";
	}else if(honourAgreement == 2){
		str += "<li><a href='javascript:payMoney(\""+id+"\");'  >支付</a></li>"+
        "<li><a href='javascript:payFinsh(\""+id+"\");'  >完成</a></li>"+
        "<li><a href='javascript:consult(\""+id+"\");'>协商</a></li>"+
        "<li><a href='javascript:void(0)' onclick=payInfo(\""+id+"\"); >编辑</a></li>"+
        "<li><a href='javascript:delPay(\""+id+"\");' >删除</a></li>"
	}else if(honourAgreement == 3){
		str += "<li><a href='javascript:honour(\""+id+"\");'  >履约</a></li>"+
        "<li><a href='javascript:consult(\""+id+"\");'>协商</a></li>"+
        "<li><a href='javascript:delPay(\""+id+"\");' >删除</a></li>"
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
function payInfo(id){
	location.href=getPath()+"/selectContractInfoById.do?id="+id;
}
function honour(id){
	if (!confirm("确定履约合同吗？")) {
		return false;
	}
	$.ajax({
		url : getPath() + '/updateHonourAgreement.do',
		data : {
			"contractId" : id,
			"status":3
		},
		dataType : 'json',
		type : "post",
		traditional : true,// 属性在这里设置
		success : function() {
	
			shwoContractList()
			alert("履约成功");
		},
		error : function(){
			alert("履约失败");
			shwoContractList()
		}
	});	
}
function payFinsh(id){
	if (!confirm("确定完成合同吗？")) {
		return false;
	}
	$.ajax({
		url : getPath() + '/updateHonourAgreement.do',
		data : {
			"contractId" : id,
			"status":2
		},
		dataType : 'json',
		type : "post",
		traditional : true,// 属性在这里设置
		success : function() {
	
			shwoContractList();
			alert("完成成功")
		},
		error : function(){
			alert("完成失败");
			shwoContractList()
		}
	});	
}
function consult(id){
	if (!confirm("确定协商合同吗？")) {
		return false;
	}
	$.ajax({
		url : getPath() + '/updateHonourAgreement.do',
		data : {
			"contractId" : id,
			"status":1
		},
		dataType : 'json',
		type : "post",
		traditional : true,// 属性在这里设置
		success : function() {
			alert("协商成功");
			shwoContractList()
		},
		error : function(){
			alert("协商失败");
			shwoContractList()
		}
	});
}
function delPay(id){
	if (!confirm("确定删除合同吗？")) {
		return false;
	}
	$.ajax({
		url : getPath() + '/deleteProjInfo.do',
		data : {
			"contractId" : id
		},
		dataType : 'json',
		type : "post",
		traditional : true,// 属性在这里设置
		success : function() {
			alert("删除成功")
		},
		error : function(){
			alert("删除失败");
			shwoContractList()
		}
	});
}
function payCheck(id){
	var jup=getPath()+"/queryPayRegisterPage.do?id="+id;
	layer.open({
	    type: 2,
	    title: '付款登记',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '520px'],
	    content: [jup]
	 })
	
}