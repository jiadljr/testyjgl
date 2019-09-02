$(function(){
	//待受理与待处理的界面切换
	$(".acceptWait").click(function(){
		$("#page3").hide();
		$("#page2").show();
		$(".status").hide();
		$(".status select").val("0");
		$(".sourse").show();
		$(".table1").show();
		$(".table2").hide();
		$(".acceptWait").css("border-bottom","none").css("color","#7fade0");
		$(".dealWait").css("border-bottom","1px solid #70a4dd").css("color","#999");
	})
	$(".dealWait").click(function(){
		queryEndList();
		$("#page2").hide();
		$("#page3").show();
		$(".status").show();
		$(".sourse").hide();
		$(".sourse select").val("0");
		$(".table1").hide();
		$(".table2").show();
		$(".acceptWait").css("border-bottom","1px solid #70a4dd").css("color","#999");
		$(".dealWait").css("border-bottom","none").css("color","#7fade0");
	})
})
function dealChange(){
	var eventDept = $("#eventDept").val();
	if(eventDept == ""){
		$("#idDept").val("");
	}
}
function deptChange(){
	var id = $("#postId").val();
	document.getElementById("userDeal").options.length=0;
	$.ajax({
		type:'post',
		url:getPath()+"/selectPostUser.do",
		data:{"id":id},
		dataType:"json",
		success:function(result){
			if(result != null){
				var thirdSelect = document.getElementById("userDeal");
				for(var i=0;i<result.length;i++){
					thirdSelect.add(new Option(result[i].name,result[i].id));;
				}
			}
		}
	})
}

function queryEndList(){
	$('#page3').empty();
	$('#page3').bPage({
		url :getPath()+"/selectDealEnd.do",
		//开启异步处理模式
		asyncLoad : true,
		//关闭服务端页面模式
		serverSidePage : false,
		//数据自定义填充
		render : function(result){
				var table = $('#tbody');
				table.empty();
				var dealEnd = result.selectDealEnd;
				var pageNumber = result.pageNumber;
				var pageSize = result.pageSize;
				var totalPage = result.totalPage;
				var totalRow = result.totalRow;
				$("#pagenumber").val(pageNumber);
				$("#pagesize").val(pageSize);
				$("#totalpage").val(totalPage);
				$("#totalrow").val(totalRow);
				for (var i = 0; i < dealEnd.length; i++) {
					var id=dealEnd[i].id;
					var code=dealEnd[i].eventCode;
					code=isnull(code);
					var name=dealEnd[i].eventName;
					name=isnull(name);
					var depName=dealEnd[i].deptName;
					depName=isnull(depName);
					var userName=dealEnd[i].userName;
					userName=isnull(userName);
					var eventContact=dealEnd[i].eventContact;
					eventContact=isnull(eventContact);
					var eventPriority=dealEnd[i].eventPriority;
					eventPriority=isnull(eventPriority);
					if(eventPriority == 1){
						eventPriority = "高级";
					}else if(eventPriority == 2){
						eventPriority = "中级";
					}else if(eventPriority == 3){
						eventPriority = "低级";
					}
					var eventLevel=dealEnd[i].eventLevel;
					eventLevel=isnull(eventLevel);
					if(eventLevel == 1){
						eventLevel = "一级事件";
					}else if(eventLevel == 2){
						eventLevel = "二级事件";
					}else if(eventLevel == 3){
						eventLevel = "三级事件";
					}else if(eventLevel == 4){
						eventLevel = "四级事件";
					}
					var eventDesc = dealEnd[i].eventDesc;
					eventDesc = isnull(eventDesc);
					table.append     
					 ('<tr>'+
							 '<td style="display:none;">'+ id +'</td>'+
						      '<td>'+ code +'</td>'+
						      '<td>'+ depName +'</td>'+
						      '<td>'+ userName +'</td>'+
						      '<td>'+ eventContact +'</td>'+
						      '<td>'+ eventPriority +'</td>' +
						      '<td>'+ eventLevel +'</td>' +
						      '<td>'+ name +'</td>'+
						      '<td>'+ eventDesc +'</td>'+
						      '<td class="color" style="display:none">'+ eventDesc +'</td>'+
						      '<td>'+
						       '<a class="btn btn-primary btn-sm" onClick="dealEnd('+id+')">处理</a>'+
						      '</td>'+
						      '</tr>');
		     }
				$("#pages").val("");
			},
		params : function(){
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var eventLevel = $("#eventLevel").val();
			var eventPriority = $("#eventPriority").val();
			var idDept = $("#idDept").val();
			return {
				"pages":pages,
	        	"pagenumber":pagenumber,
	        	"pagesize":pagesize,
	        	"totalpage":totalpage,
	        	"totalrow":totalrow,
				"startTime" : startTime,
				"endTime" : endTime,
				"eventLevel" : eventLevel,
				"eventPriority" : eventPriority,
				"idDept" : idDept,
			};
		}
	});
}
function receiving(id){
	var data = {"id":id};
	$.post(getPath()+"/updateDealNot.do",data,function(){
		location.href = getPath()+"/queryDelNot.do";
	})
}
function dealEnd(id){
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath()+ "/dealEnd.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
}
function cancel(){
	alert(123);
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	alert(index);
	parent.layer.close(index); //再执行关闭 
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath()+ "/queryDelNot.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
function addConfirm(){
	$("#addConfirm").click(function(){
		$.post(getPath()+"/updateDealEnd.do",$("#form1").serialize(),function(){
			location.href = getPath()+"/queryDelNot.do";
		})
	})
}
function showAssets(){
	var dealAssetsUrl = getPath()+"/showAssets.do";
	layer.open({
	    type: 2,
	    title: '资产编号',
	    scrollbar: true,
	    shadeClose: true,
	    area: ['670px', '400px'],
	    content: [dealAssetsUrl,'yes']
	  })
}

