$(function() {
	//关闭弹框
	$("#button_cancel_author").click(function() {
		$(".author_jump").fadeOut();
		$(".mask").fadeOut();
	})
	//添加处理人
	$("#button_search_author").click(function() {
		
		var roleStr = $("#sys_role").val();
		
		if (roleStr == null || roleStr == "") {
			alert("请选择处理人进行增援!!");
			return;
		}
		var event_id=$("#event_id").val();
		var token=$("#token").val();
		var roleStrs = String(roleStr);
		$('#sys_role').selectpicker('refresh');
		$('#sys_role').selectpicker('render');
		$("#button_search_author").attr("disabled","disabled");
		$.post(getPath() + "/addDealUser.do", {
			"event_id":event_id,
			"dealUsers" : roleStrs,
		}, function(result) {
			if (result.result == "success") {
				$(".author_jump").fadeOut();
				$(".mask").fadeOut();
				findAgrncyList();
				layer.open({
		            type:4,
		        	time:2000,
		            tipsMore: true,
		            title: '提示',
		            tips:[2,"#fff0a5"],
		            closeBtn:0,
		            area: ['200px', '70px'],
		            shade: 0,
		            maxmin: false,
		            offset: 'rb',
		            content: ['<p style="color:#333"> 提示</p><p style="color:#666">　　　添加成功！！！</p>', '#eveBox']
		        });
				
			}

			if (result.result == "error") {
				alert(alreadyChoose+"已经参与该事件，请重新选择！");
				$(".author_jump").fadeOut();
				$(".mask").fadeOut();

			}
		})
	})
	$('#idUserDeal,#eventService').focus(function(){
		$(this).removeClass('error');
	})
	
	// 提交受理信息
	$("#addAccept").click(function(){
			var eventTs = $("#eventTs").val();
			var eventId = $("#eventId").val();
			$.ajax({
				type:"post",
				url:getPath()+"/selectEventTs.do",
				data:{"id":eventId},
				success:function(data){
					var time = data.eventTs;
					if(time != eventTs){
						alert("数据过时！请刷新列表");
						return;
					}
					submitAccept();
				}
			  })
			})
			function submitAccept(){
			if($("#serviceId").val() =='' || $("#serviceId").val() == null){
				$("#serviceSearchTree").addClass('error');
				return false;
			}
			if($(".selInp").val()== "请选择处理人" || $(".selInp").val() == ""){
				$('.selInp').addClass('error');
			}
			
			
			if ($(".error_null").hasClass("error")) {
			$.each($(".error_null"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;	
			} else {
				$.post(getPath() + "/accept.do", $("#form1").serialize(),
						function(result) {
							if (result == "success") {
								var front = $("#front").val();
								if (front == "服务台首页") {
									location.href = getPath() + "/toFrontDesk.do?cid=1";
									return;
								}
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					    		parent.layer.close(index); //再执行关闭
								window.location.href = getPath()
										+ "/toAgencyPage.do?cid=1";
							}
						})
			}
	}
	// 提交正常确认信息
	$("#addSure").click(
			function() {
				if ($(".error_null").val() == "" || $(".error_null").hasClass("error")) {					
					$.each($(".error_null"), function() {
						if ($(this).val() == "") {
							var error = $(this).attr("data-message");
							$(this).val(error);
							$(this).addClass("error");
						}
					})
					return false;
				}
				
				$.post(getPath() + "/sureSubmit.do", $("#form1").serialize(),
						function(result) {
							if (result == "success") {
								var front = $("#front").val();
								if (front == "服务台首页") {
									location.href = getPath() + "/toFrontDesk.do?cid=4";
									return;
								}
								var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					    		parent.layer.close(index); //再执行关闭
								window.location.href = getPath()
										+ "/toAgencyPage.do?cid=4";
							}
						})
				// }
			})
			 $('#eventUser ').focus(function(){
			 $(this).removeClass('error');
		 })
	 $('#eventService').focus(function(){
			    	 $('#eventService').removeClass('error');
			     })
			
	$("#addApplyAndAccept").click(
			function() {
			var idUserDeal=$("#idUserDeal option:selected").val()
		
			if(idUserDeal==""||idUserDeal==undefined){
				$("#selInps").addClass("error")
			}
				if($('#serviceId').val()=="" || $("#serviceId").val() == null){
					$('#serviceSearchTree').addClass("error");
				}
				$.each($(".error_null"),function(){
			         if($(this).val()!==""){
			             $(this).removeClass("err_null");
			         }
			     })
			     var selInp = $("#eventUser").val();
			    if(selInp == "" || selInp == null){
			    	$("#selInp").addClass("error");
			    	return false;
			    }
			
				if ($(".error_null").val() == ""|| $(".error_null").hasClass("err_null")|| $(".error_null").hasClass("error")|| $("#serviceSearchTree").hasClass("error")) {
				
					$.each($(".error_null"), function() {
						if ($(this).val() == "") {
							var error = $(this).attr("data-message");
							$(this).val(error);
							$(this).addClass("error");
						}
					})
					
					return false;
				} else {
					$.ajax({
						type:"post",
						url:getPath() + "/addApplyAndAccept.do",
						data:$("#form1").serialize(),
						success:function(){
								window.parent.sunFun();
								var index = parent.layer.getFrameIndex(window.name);  
								setTimeout(function(){parent.layer.close(index);}, 50);
						},error:function(err){
							alert($.parseJSON(err.responseText).msg);
						}
					})
				}
			})
})

function findAgrncyList() {

	$('#agencyTable').empty();
	$('#agencyTable')
			.bPage(
					{
						url : getPath() + "/findAgrncyList.do",
						// 开启异步处理模式
						asyncLoad : true,
						// 关闭服务端页面模式
						serverSidePage : false,
						// 数据自定义填充
						render : function(result) {
							var events = result.eventList;						
							var agencyTbody = $("#agencyTbody").empty();
							var pageNumber = result.pageNumber;
							var pageSize = result.pageSize;
							var totalPage = result.totalPage;
							var totalRow = result.totalRow;
							$("#pagenumber").val(pageNumber);
							$("#pagesize").val(pageSize);
							$("#totalpage").val(totalPage);
							$("#totalrow").val(totalRow);
							$("#bPageDropList").val(pageSize);
							/**
							 * 添加申告功能实现
							 * 根据人员信息(result.duty)
							 * 判断是否展示按钮
							 * 返回值(yes/no)
							 * */
							
							if(result.duty=="yes"){
								$('.rightBox').show();
							}else if(result.duty=="no"){
								$('.rightBox').hide();
							}
						
							for (var i = 0; i < events.length; i++) {
								var event = events[i];
								var code = event.eventCode;
								var name = event.eventName;
								var applyUser = event.extend1;
								var deptName = event.extend2;
								var createName = event.extend3;
								var contact = event.eventContact;
								var cDate = event.dateCreate;
								var remark = event.eventDesc;
								var eStatus = event.eventStatus;

								code = isnull(code);
								name = isnull(name);
								applyUser = isnull(applyUser);
								deptName = isnull(deptName);
								createName = isnull(createName);
								contact = isnull(contact);
								cDate = isnull(cDate);
								remark = isnull(remark);
								eStatus = isnull(eStatus);
								var status = "";
								var operate = "";
								if (eStatus == '20') {
									status = "待受理";
									operate += "<a class=\"btn btn-primary  btn_new_small btn_green\" onclick=\"checkAccept("
										+ event.id + ","+event.eventTs+")\">受理</ a>";
								} else if (eStatus == "50") {
									status = "待处理";
									operate += "<a class=\"btn btn-primary  btn_new_small btn_red\" onclick='selectDealUser(\""+event.id+"\")'>增援</a> ";
									operate += "<a class=\"btn btn-primary  btn_new_small btn_yellow\" onclick='dealEnd(\""+event.id+"\")'>处理</a> ";
								} else if (eStatus == "61") {
									status = "处理驳回";
									operate += "<a class=\"btn btn-primary  btn_new_small btn_red\" onclick=\"selectDealUser(\""+event.id+"\")\">增援</a> ";
									operate += "<a class=\"btn btn-primary  btn_new_small btn_yellow\" onclick=\"dealEnd("
											+ event.id + ")\">处理</a> ";
								} else if (eStatus == "60") {
									status = "待确定";
									operate += "<a class=\"btn btn-primary  btn_new_small btn_purple\" onclick=\"checkNormal("
											+ event.id + ")\">确认</a> ";
								}
								var str = "<tr><td>"
										+ code
										+ "</td>"
										+ "<td>"
										+ name
										+ "</td>"
										+ "<td>"
										+ applyUser
										+ "</td>"
										+ "<td>"
										+ deptName
										+ "</td>"
										+ "<td>"
										+ contact
										+ "</td>"
										+ "<td>"
										+ getFormatDate(new Date(cDate),
												"yyyy/MM/dd hh:mm:ss")
										+ "</td>" + "<td>" + remark + "</td>"
										+ "<td>" + createName + "</td>"
										+ "<td>" + status + "</td>" + "<td>"
										+ operate + "</td>" + "</tr>";
								agencyTbody.append(str);
							}
							var relTable=$('.relTable');
							
							var RH=$('.mRight').height()-182;
							$('.mRbot').height($('.mRight').height()-145);					     
							var relTable=$('.relTable');
							var relTab=$('.relTable .table');
							var thTab=$('.fixTable .table tr th');
							var tdTab=$('.relTable .table tr:eq(0) td');
							var len=thTab.length;
							
							var relTabHeight=relTab.height();
							for(var thI=0;thI<(len-1);thI++){
							tdTab.eq(thI).width(thTab.eq(thI).width()) ;

							}

							if(RH<relTabHeight){
							for(var thI=0;thI<(len-1);thI++){
							tdTab.eq(thI).width(thTab.eq(thI).width()) ;

							}


							relTable.addClass('relAuto')
							}else{
							relTable.removeClass('relAuto')
							}
						},
						params : function() {
							var startTime = $("#startTime").val();
							var endTime = $("#endTime").val();
							var contact = $("#contact").val();
							var idDept = $("#idDept").val();
							var eventStatus = $("#eventStatus").val();
							var pages = $("#pages").val();
							var pagenumber = $("#pagenumber").val();
							var pagesize = $("#pagesize").val();
							var totalpage = $("#totalpage").val();
							var totalrow = $("#totalrow").val();
							return {
								startTime : startTime,
								endTime : endTime,
								contact : contact,
								idDept : idDept,
								eventStatus : eventStatus,
								"pages":pages,
								"pagenumber":pagenumber,
								"pagesize":pagesize,
								"totalpage":totalpage,
								"totalrow":totalrow,
							};
						}
					});
	$("#pages").val("");
}
//取消按钮
function cancel(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index); //再执行关闭 
	var front = $("#front").val();
	if (front == "服务台首页") {
		location.href = getPath() + "/toFrontDesk.do";
		return;
	}
	var oper = $("#oper").val();
	if (oper == "运维人员主页") {
		location.href = getPath() + "/goOperations.do";
		return;
	}
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var contact = $("#contact").val();
	var idDept = $("#idDept").val();
	var eventStatus = $("#eventStatus").val();
	location.href = getPath() + "/toAgencyPage.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages+"&startTime="+startTime+"&endTime="+endTime+"&contact="+contact+"&idDept="+idDept+"&eventStatus="+eventStatus;
}

// 受理
function checkAccept(eventId,eventTs) {
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var contact = $("#contact").val();
	var idDept = $("#idDept").val();
	var eventStatus = $("#eventStatus").val();
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	$.ajax({
		type:"post",
		url:getPath()+"/selectEventTs.do",
		data:{"id":eventId},
		success:function(data){
			var time = data.eventTs;
			if(time != eventTs){
				alert("数据过时！请刷新列表");
				return;
			}
			window.location.href = getPath() + "/eventAccept.do?eventId=" + eventId
			+ "&pageNumber=" + pageNumber + "&pageSize=" + pageSize
			+ "&totalPage=" + totalPage + "&totalRow=" + totalRow+"&startTime="+startTime+"&endTime="+endTime+"&contact="+contact+"&idDept="+idDept+"&eventStatus="+eventStatus;
		}
	})
}
//查看受理信息
function seeAccept(id) {
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href = getPath() + "/seeAccept.do?id=" + id
			+ "&pageNumber=" + pageNumber + "&pageSize=" + pageSize
			+ "&totalPage=" + totalPage + "&totalRow=" + totalRow+"&startTime="+startTime+"&endTime="+endTime+"&contact="+contact+"&idDept="+idDept+"&eventStatus="+eventStatus;
}
// 处理
function dealEnd(id) {
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var contact = $("#contact").val();
	var idDept = $("#idDept").val();
	var eventStatus = $("#eventStatus").val();
	var data = {"id":id,"pageNumber":pageNumber,"pageSize":pageSize,
			    "totalPage":totalPage,"totalRow":totalRow,
			    "startTime":startTime,"endTime":endTime,
			    "contact":contact,"idDept":idDept,"eventStatus":eventStatus};
  $.ajax({
	  type:"post",
	  url:getPath() + "/dealEnd.do",
	  data:data,
	  async:false,
	  success:function(data){
		  var pageSize = data.pageSize;
		  var startTime = data.startTime;
		  var endTime = data.endTime;
		  var contact = data.contact;
		  var idDept = data.idDept;
	      var eventStatus = data.eventStatus;
		  var pageNumber = data.pageNumber;
		  var totalPage = data.totalPage;
		  var totalRow = data.totalRow;
		  var id = data.id;
		  location.href = getPath()+"/queryDeal.do?pageSize="+pageSize+"&startTime="+startTime
		                            +"&endTime="+endTime+"&contact="+contact+"&idDept="+idDept
		                            +"&eventStatus="+eventStatus+"&pageNumber="+pageNumber
		                            +"&totalPage="+totalPage+"&totalRow="+totalRow+"&id="+id;
	  },
	  error:function(err){
		  alert($.parseJSON(err.responseText).msg)
	  }
  })
}
// 增援
function selectDealUser(id){
	$("#button_search_author").removeAttr("disabled");
	var deal_user = $("#sys_role").empty();
	$.post(getPath()+"/selectDealUser.do",{"event_id": id},function(result){
		var dealUsers= result.selectDealUser;
		var event_id = result.event_id;
		$("#event_id").val(event_id);
		var str = "";
		for (var i = 0; i < dealUsers.length; i++) {
			var dealUser = dealUsers[i];
			str += "<option value="+dealUser.id +">"+dealUser.name +"</option>";
		}
		deal_user.append(str);
		$('#sys_role').selectpicker('refresh');
		$('#sys_role').selectpicker('render');
	})
	$(".author_jump").fadeIn();
	$("#button_search_author").removeAttr("disabled");
	$(".mask").fadeIn();
	
}
// 审核(待确定)
function checkNormal(eventId) {
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	var contact = $("#contact").val();
	var idDept = $("#idDept").val();
	var eventStatus = $("#eventStatus").val();
	window.location.href = getPath() + "/checkEvent.do?eventId=" + eventId
			+ "&pageNumber=" + pageNumber + "&pageSize=" + pageSize
			+ "&totalPage=" + totalPage + "&totalRow=" + totalRow+"&startTime="+startTime+"&endTime="+endTime+"&contact="+contact+"&idDept="+idDept+"&eventStatus="+eventStatus;
}
//检测文件是否存在
function downFile(fileId){
	$.ajax({
		data:{fileId: fileId},
		dataType : 'json',
		async: false,
		type : 'post',
		url : getPath() + "/exportDocLoad.do",
		success : function(result){
			if (result.data == "error") {
				alert("文件不存在!");
			}else {
				location.href = getPath() + "/downLoad.do?fileId=" + fileId;
			}
		}
	})
}
//
function changeStatus(){
	$("#eventStatus").val($("#status").val());
}
function clearDept(){
	if ($("#eventDept").val() == '') {
		$("#idDept").val("");
	}
}
//弹出资产编号
function showAssets(){
//	$("#cd", document.iframes('iframe').document)
	var assets_id = $("#assets_id").val();
	var property = $("#property").val();
	var dealAssetsUrl = getPath()+"/showAssets.do?assets_id="+assets_id+"&property="+property;
	layer.open({
	    type: 2,
	    title: '资产信息',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['570px', '452px'],
	    content: [dealAssetsUrl,'yes']
	  })
}
//删除文件
function delFile(fileId){
	$("#fileDiv").append(fileId+",");
	$("#"+fileId).hide();
}
function delSeeFile(fileId){
	uploader.cancelFile(fileId);
	$("#thelist").empty();
	$("#col"+fileId).remove();
	$("#sm"+fileId).remove();
}
//科室内容改变触发
var deptCall="";
function deptChange() {
	var id = $("#idDept").val();
	document.getElementById("eventUser").options.length = 0;
	$.ajax({
		type : 'post',
		url : getPath() + "/selectDeptUser.do",
		data : {
			"id" : id
		},
		dataType : "json",
		success : function(result) {
			if (result.selectUser != null) {
				var thirdSelect = document.getElementById("eventUser");
				for (var i = 0; i < result.selectUser.length; i++) {
					thirdSelect.add(new Option(result.selectUser[i].name,
							result.selectUser[i].id));
				}
				if ($('#eventUser').fireEvent)
				{
				$('#eventUser').fireEvent('onchange');
				}
				else
				{
				$('#eventUser').change();
				}
			}
			deptCall = result.tel;
			$("#callApply").val(result.tel);
		}
	})
}
function userChange() {
	$("#selInp").val("请选择申告人");
	$("#eventUser option").click(function(){
		$("#eventUser ").hide();
		$("#selInp").val($(this).text()).removeClass("error");
	})
	var userId = $("#eventUser").val();
	$.ajax({
		type : 'post',
		url : getPath() + "/userById.do",
		data : {
			"userId" : userId
		},
		dataType : "json",
		success : function(result) {
			if (result.cal != "" && result.cal != null) {
				$("#callApply").val(result.cal); 
			}else{
				$("#callApply").val(deptCall); 
			}
		}
	})
}
/**首字母搜索树*/
function removeSourceLi() {
	if($(".typeOne").length>0){
		for (var n = 0; n < $(".typeOne").length; n++) {
			if ($("#sourOne" + n + " li").length == 0) {
				$("#sourOne" + n).remove();
			}
		}
	}
	if($(".typeTwo").length>0){
		for (var m = 0; m < $(".typeTwo").length; m++) {
			if ($("#sourTwo" + m + " li").length == 0) {
				$("#sourTwo" + m).remove();
			}
		}
	}
	
	$("#sourceTreeDiv a").click(function(){
		$("#sourceTreeDiv a").css({"color":"#183152"});
		$(this).css({"color":"red"});
		$("#openSourceTree").css({"color":"#183152"})
		 
	})
}
function querySourceType(id,name){
	$("#sourceId").val(id);
	$("#sourceSearchTree").val(name);
	$("#sourceTreeDiv").hide();
	$("#sourceSidebar").hide();
}
function showSourceTree(){
	$("#sourceTreeDiv").show();
	$("#sourceSidebar").show();
	$('body').bind("mousedown", onBodyDownSource);
}
function onBodyDownSource(event) { 
   if (!(event.target.id == "menuBtn" || event.target.id == "sourceTreeDiv" || $(event.target).parents("#sourceTreeDiv").length > 0)) {  
	$("#sourceTreeDiv").hide();
	$("#sourceSidebar").hide();
   }  
}
/**服务类型*/
function removeServiceLi() {
	if($(".serviceOne").length>0){
		for (var n = 0; n < $(".serviceOne").length; n++) {
			if ($("#serOne" + n + " li").length == 0) {
				$("#serOne" + n).remove();
			}
		}
	}
	if($(".serviceTwo").length>0){
		for (var m = 0; m < $(".serviceTwo").length; m++) {
			if ($("#serTwo" + m + " li").length == 0) {
				$("#serTwo" + m).remove();
			}
		}
	}
	
	$("#serviceTreeDiv a").click(function(){
		$("#serviceTreeDiv a").css({"color":"#183152"});
		$(this).css({"color":"red"});
		$("#openServiceTree").css({"color":"#183152"})
		 
	})
}
function queryServiceType(id,name){
	$("#serviceId").val(id);///
	$("#serviceSearchTree").val(name);
	$("#serviceTreeDiv").hide();
	$("#serviceSidebar").hide();
}
function showServiceTree(){
	$("#serviceTreeDiv").show();
	$("#serviceSidebar").show();
	$('body').bind("mousedown", onBodyDownService);
}
function onBodyDownService(event) { 
   if (!(event.target.id == "menuBtn" || event.target.id == "serviceTreeDiv" || $(event.target).parents("#serviceTreeDiv").length > 0)) {  
	$("#serviceTreeDiv").hide();
	$("#serviceSidebar").hide();
   }  
}