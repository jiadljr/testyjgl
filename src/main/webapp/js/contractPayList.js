function shwoPayList(searchStatus){
	$('#paging').empty();
	$('#paging').bPage({
		url : getPath() + "/selectPaymentStandingBook.do",
		asyncLoad : true,
		serverSidePage : false,
		render : function(data) {

			var projBody = $("#payListBody").empty();

			var pageNumber = data.pageNumber;
			var pageSize = data.pageSize;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			var paymentStandingBook  = data.paymentStandingBook;
			var str = "";
			for(var i = 0;i<paymentStandingBook.length;i++){
				var code = paymentStandingBook[i].code;//合同编号
				code = isnull(code);
				var contractTitle = paymentStandingBook[i].contractTitle;//合同标题
				contractTitle = isnull(contractTitle);
				var firstPartyUnit = paymentStandingBook[i].firstPartyUnit;//对方单位
				firstPartyUnit = isnull(firstPartyUnit);
				var extend1 = paymentStandingBook[i].extend1//合同类型
				extend1 = isnull(extend1);
				var contractMoney = paymentStandingBook[i].contractMoney//合同金额
				contractMoney = isnull(contractMoney);
				var setExtend2 = paymentStandingBook[i].setExtend2//已结金额
				setExtend2 = isnull(setExtend2);
				var setExtend3 = paymentStandingBook[i].setExtend3//未结金额
				setExtend3 = isnull(setExtend3);
				var setExtend4 = paymentStandingBook[i].setExtend4//已结比例
				setExtend4 = isnull(setExtend4);
				str += "<tr><td>"+code+"</td>"+
						"<td>"+contractTitle+"</td>"+
						"<td>"+firstPartyUnit+"</td>"+
						"<td>"+extend1+"</td>"+
						"<td>"+contractMoney+"</td>"+
						"<td>"+setExtend2+"</td>"+
						"<td>"+setExtend3+"</td>"+
						"<td>"+setExtend4+"</td>"+
						"<td><a href='"+getPath()+"/selectContractDetail.do?id="+paymentStandingBook[i].id+"' target='_blank'>明细</a></td></tr>"
			}
			$("#payListBody").append(str);
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
		},
		params : function() {
		var firstPartyUnit=$("#firstPartyUnit").val()
		var conKeyWord=$("#projName").val();
		var contractTypeCode=$("#contractTypeCode option:selected").val()
		var contractCode=$("#contractCode").val();
		var alreadyStart =$("#alreadyStart").val();	
		var alreadyEnd=$("#alreadyEnd").val();	
		var contractMoneyStrat=$("#contractMoneyStrat").val();
		var contractMoneyEnd=$("#contractMoneyEnd").val();
		var fundSource=$("#fundSource").val();
		var pages = $("#pages").val();
		var pagenumber = $("#pagenumber").val();
		var pagesize = $("#pagesize").val();
		var totalpage = $("#totalpage").val();
		var totalrow = $("#totalrow").val();
		var obj={}
	
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
					"alreadyStart":alreadyStart,
					"alreadyEnd":alreadyEnd,
					"firstPartyUnit":firstPartyUnit,
					"contractTypeCode":contractTypeCode,
					"contractCode":contractCode,
					"contractMoneyStrat":contractMoneyStrat,
					"contractMoneyEnd":contractMoneyEnd,
					"fundSource":fundSource,		
				}
		}
			return obj;
		}
	});
	$("#pages").val("");
}
