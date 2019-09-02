$(function(){
	/**搜索*/
	$("#button_search").click(function(){
		showArrangeList();
	})
	/**跳转添加页面*/
	$("#addDuty").click(function(){
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		location.href = getPath()+"/toAddArrange.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
	})
	
	/**添加*/
	$("#addConfirm").click(function(){
		var duty_user = "";
		$.each($("#dutyUserText a i"),function(){
			duty_user += $(this).text()+",";
		})
		duty_user=duty_user.substring(0,duty_user.length-1)
		if (duty_user == null || duty_user == "") {
			alert("请选择值班人员！");
			return false;
		}
		var dutyUsers = String(duty_user);
		var duty_start_time = $("#duty_start_time").val();
		var duty_end_time = $("#duty_end_time").val();
		var timeScope = $("#timeScope").val();
		if (new Date(duty_start_time).getTime() >= new Date(duty_end_time).getTime()) {
			alert("开始时间要小于结束时间");
			return false;
		}
		if(duty_start_time == "" || duty_end_time == ""){
			alert("请选择值班时间");
			return false;
		}
		if (timeScope == "") {
			alert("请选择时间段");
			return;
		}
		$.ajax({
			url:getPath()+"/addArrange.do",
			data:$.param({"duty_user":dutyUsers})+'&'+jQuery('#form1').serialize(),
			dataType:'json',
			type:'post',
			success:function(result){
				if (result.resultMessage == "success") {
					window.location.href=getPath()+"/toArrangeList.do?cid=1";
				}
				if(result.resultMessage == "error"){
					alert(result.errorMessage);
				}
			},
			error:function(result){
				alert($.parseJSON(result.responseText).msg);
			}
		})
	})
	/**修改*/
	$("#updateConfirm").click(function(){
		$.ajax({
			url:getPath()+"/updateArrange.do",
			data:$("#form1").serialize(),
			dataType:"json",
			type:"post",
			success:function(result){
				window.location.href=getPath()+"/toArrangeList.do?cid=2"
			}
		})
	})
})

function showArrangeList() {
	$("#page2").empty();
	$('#page2').bPage(
			{
				url : getPath() + "/selectArrangeList.do",
				// 开启异步处理模式
				asyncLoad : true,
				// 关闭服务端页面模式
				serverSidePage : false,
				// 数据自定义填充
				render : function(result) {
					/**查询运维人员*/
					if (document.getElementById("oper_id").options.length==1) {
						var operList = result.operList;
						var operStr = "";
						for (var i = 0; i < operList.length; i++) {
							operStr += "<option value=" + operList[i].id + ">"
									+ operList[i].name + "</option>";
						}
						$("#oper_id").append(operStr);
					}
					
					/**展示值班信息*/
					var pageNumber = result.pageNumber;
					var pageSize = result.pageSize;
					var totalPage = result.totalPage;
					var totalRow = result.totalRow;
					$("#pagenumber").val(pageNumber);
					$("#pagesize").val(pageSize);
					$("#totalpage").val(totalPage);
					$("#totalrow").val(totalRow);
					var arrangeList = result.arrangeList;
					var tbody = $("#tbody").empty();
					for (var j = 0; j < arrangeList.length; j++) {
						var arrange = arrangeList[j];
						var id = arrange.id;
						var name = arrange.extend1;
						var dutyStartTime = arrange.dutyStartTime;
						var dutyEndTime = arrange.dutyEndTime;
						name = isnull(name);
						dutyStartTime = isnull(dutyStartTime);
						dutyEndTime = isnull(dutyEndTime);
						var arrangeTr = "";
						arrangeTr += "<tr><td>" + name + "</td>" + "<td>"
								+ getFormatDate(new Date(dutyStartTime), "yyyy年MM月dd日  hh:mm:ss") + "至"+ getFormatDate(new Date(dutyEndTime), "yyyy年MM月dd日 hh:mm:ss") + "</td>"
								+ "<td><a class='btn btn_yellow btn-sm ' onclick='updDuty(\""+id+"\")' >修改</a>  <a class='btn btn_green btn-sm ' onclick='delDuty(\""+id+"\")' >删除</a></td>"
								+ "<tr>";
						tbody.append(arrangeTr);
					}
				},
				params : function() {
					var pages = $("#pages").val();
					var oper_id = $("#oper_id").val();
					var duty_start_time = $("#duty_start_time").val();
					var duty_end_time = $("#duty_end_time").val();
					var pages = $("#pages").val();
					var pagenumber = $("#pagenumber").val();
					var pagesize = $("#pagesize").val();
					var totalpage = $("#totalpage").val();
					var totalrow = $("#totalrow").val();
					return {
						"pages" : pages,
						"oper_id" : oper_id,
						"duty_start_time" : duty_start_time,
						"duty_end_time" : duty_end_time,
						"pagenumber":pagenumber,
						"pagesize":pagesize,
						"totalpage":totalpage,
						"totalrow":totalrow,
					};
				}
			});
	$("#pages").val("");
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href = getPath() + "/toArrangeList.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
/**修改*/
function updDuty(id){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/toUpdateArrange.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages+"&user_id="+id;
}
/**值班导出*/
function dutyExport(){
	$.ajax({
		url:getPath()+"/ifHaveData.do",
		data:{},
		type:"post",
		success:function(result){
			location.href=getPath()+"/dutyExport.do"
		},
		error:function(err){
			alert($.parseJSON(err.responseText).msg);
		}
	})
	
}
/**删除*/
function delDuty(dutyId){
	if(!confirm("确定删除？")){
		return;
	}
	$.ajax({
		url:getPath()+"/deleteArrange.do",
		data:{"dutyId":dutyId},
		dataType:"text",
		type:"post",
		success:function(result){
			if (result == "success") {
				window.location.href=getPath()+"/toArrangeList.do?cid=3";
			}
		}
	})
}