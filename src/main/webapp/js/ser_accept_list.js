$(function() {
	// 待受理与待处理的界面切换
	$(".acceptWait").click(function() {
		$("#notSureTable").hide();
		$("#notAcceptTable").show();
		$(".status").hide();
		$(".status select").val("0");
		$(".sourse").show();
		$(".table1").show();
		$(".table2").hide();
		$(".acceptWait").css("border-bottom", "none");
		$(".dealWait").css("border-bottom", "1px solid #70a4dd");
		queryNotAcceptList();
	})
	$(".dealWait").click(function() {
		$("#notAcceptTable").hide();
		$("#notSureTable").show();
		$(".status").show();
		$(".sourse").hide();
		$(".sourse select").val("0");
		$(".table1").hide();
		$(".table2").show();
		$(".acceptWait").css("border-bottom", "1px solid #70a4dd");
		$(".dealWait").css("border-bottom", "none");
		queryNotSureList();
	})
	// 提交受理信息
	$("#addAccept").click(
			function() {
				var dealId = $("#idUserDeal").val();
				if (dealId == "") {
					return;
				}
				
				if ($("input").val() == "" || $("input").hasClass("error")) {
					
					$.each($("input"), function() {
						if ($(this).val() == "") {
							var error = $(this).attr("data-message");
							$(this).val(error);
							$(this).addClass("error");
						}
					})
					return false;
				} else {
					$.post(getPath() + "/accept.do", $("#form1").serialize(),
							function(result) {
								if (result == "success") {
									window.location.href = getPath()
											+ "/toEventInfoAccept.do";
								}
							})
				}
			})
	// 提交转派增援确认信息
	$("#addReinSure").click(
			function() {
				if ($("input").val() == "" || $("input").hasClass("error")) {
					$.each($("input"), function() {
						if ($(this).val() == "") {
							var error = $(this).attr("data-message");
							$(this).val(error);
							$(this).addClass("error");
						}
					})
					return false;
				} else {
					$.post(getPath() + "/sureRein.do", $("#form1").serialize(),
							function(result) {
								if (result == "success") {
									alert("转派增援确认成功!");
									window.location.href = getPath()
											+ "/toEventInfoAccept.do";
								}
							})
				}
			})
	// 提交正常确认信息
	$("#addSure").click(
			function() {
				/*
				 * if ($("input").val() == "" || $("input").hasClass("error")) {
				 * $.each($("input"), function() { if ($(this).val() == "") {
				 * var error = $(this).attr("data-message"); $(this).val(error);
				 * $(this).addClass("error"); } }) return false; } else {
				 */
				$.post(getPath() + "/sureSubmit.do", $("#form1").serialize(),
						function(result) {
							if (result == "success") {
								alert("确认成功!");
								window.location.href = getPath()
										+ "/toEventInfoAccept.do";
							}else if (result == "solve") {
								alert("解决确认成功!");
								window.location.href = getPath()
										+ "/toEventInfoAccept.do";
							}
						})
				// }
			})

	$("#addApplyAndAccept").click(
			function() {
				if ($("input").val() == "" || $("input").hasClass("error")) {
					$.each($("input"), function() {
						if ($(this).val() == "") {
							var error = $(this).attr("data-message");
							$(this).val(error);
							$(this).addClass("error");
						}
					})
					return false;
				} else {
					$.post(getPath() + "/addApplyAndAccept.do", $("#form1").serialize(), function(result) {
						if (result == "success") {
							alert("添加事件成功!");
							window.location.href = getPath()
									+ "/toEventInfoAccept.do";
						}
					})
				}
			})
})

// 替换tbody内容
function queryNotAcceptList() {
	$('#notAcceptTable').empty();
	$('#notAcceptTable')
			.bPage(
					{
						url : getPath() + "/selectAcceptAll.do",
						// 开启异步处理模式
						asyncLoad : true,
						// 关闭服务端页面模式
						serverSidePage : false,
						// 数据自定义填充
						render : function(result) {
							// 受理信息展示
							var pageNumber = result.pageNumber;
							var pageSize = result.pageSize;
							var totalPage = result.totalPage;
							var totalRow = result.totalRow;
							$("#pagenumber").val(pageNumber);
							$("#pagesize").val(pageSize);
							$("#totalpage").val(totalPage);
							$("#totalrow").val(totalRow);
							var accepts = result.notAcceptList;
							var tbody = $('#notAccepttbody').empty();
							for (var i = 0; i < accepts.length; i++) {
								var accept = accepts[i];
								var id = accept.id;
								var eventCode = accept.eventCode;
								eventCode = isnull(eventCode);

								var eventName = accept.eventName;
								eventName = isnull(eventName);
								
								var eventTs = accept.eventTs
								eventTs = isnull(eventTs);
								alert(eventTs);
								var extend2 = accept.extend2;
								extend2 = isnull(extend2);

								var extend1 = accept.extend1;
								extend1 = isnull(extend1);

								var dateCreate = accept.dateCreate;
								dateCreate = isnull(dateCreate);

								var eventContact = accept.eventContact;
								eventContact = isnull(eventContact);

								var eventStatus = accept.eventStatus;
								eventStatus = isnull(eventStatus);
								if (eventStatus == 20) {
									var fromAccept = "申告";
								}
								if (eventStatus == 31) {
									var fromAccept = "审核驳回";
								}

								var tr = "<tr>" + "<td>"
										+ eventCode
										+ "</td>"
										+ "<td>"
										+ eventName
										+ "</td>"
										+ "<td>"
										+ extend2
										+ "</td>"
										+ "<td>"
										+ extend1
										+ "</td>"
										+ "<td>"
										+ getFormatDate(new Date(dateCreate),
												"yyyy-MM-dd hh:mm:ss")
										+ "</td>"
										+ "<td>"
										+ eventContact
										+ "</td>"
										+ "<td>"
										+ fromAccept
										+ "</td>"
										+ "<td><a href='javascript:checkAccept(\""+id+"\",\""+eventTs+"\")' class='btn btn-primary btn-sm' >受理</a></td>"
										+ "</tr>";
								tbody.append(tr);
							}
						},
						params : function() {
							var STATUS = "notAccept";
							var pages = $("#pages").val();
							var pagenumber = $("#pagenumber").val();
							var pagesize = $("#pagesize").val();
							var totalpage = $("#totalpage").val();
							var totalrow = $("#totalrow").val();
							var startTime = $("#startTime").val();
							var eventUser = $("#eventUser").val();
							var endTime = $("#endTime").val();
							var idDept = $("#idDept").val();
							var eventSource = $("#eventSource").val();
							return {
								pages:pages,
					        	pagenumber:pagenumber,
					        	pagesize:pagesize,
					        	totalpage:totalpage,
					        	totalrow:totalrow,
								STATUS : STATUS,
								startTime : startTime,
								endTime : endTime,
								idDept : idDept,
								eventSource : eventSource,
								eventUser : eventUser,
							};
						}
					});
}
function queryNotSureList() {
	$('#notSureTable').empty();
	$('#notSureTable')
			.bPage(
					{
						url : getPath() + "/selectAcceptAll.do",
						// 开启异步处理模式
						asyncLoad : true,
						// 关闭服务端页面模式
						serverSidePage : false,
						// 数据自定义填充
						render : function(result) {
							// 受理信息展示
							var pageNumber = result.pageNumber;
							var pageSize = result.pageSize;
							var totalPage = result.totalPage;
							var totalRow = result.totalRow;
							$("#pagenumber").val(pageNumber);
							$("#pagesize").val(pageSize);
							$("#totalpage").val(totalPage);
							$("#totalrow").val(totalRow);
							var notSures = result.notSureList;
							var notSuretbody = $('#notSuretbody').empty();
							for (var i = 0; i < notSures.length; i++) {
								var notSure = notSures[i];
								var id = notSure.id;
								var eventCode = notSure.event_code;
								eventCode = isnull(eventCode);
								
								var eventName = notSure.event_name;
								eventName = isnull(eventName);

								var extend1 = notSure.extend_1;
								extend1 = isnull(extend1);

								var extend2 = notSure.extend_2;
								extend2 = isnull(extend2);

								var dateCreate = notSure.date_create;
								dateCreate = isnull(dateCreate);

								var eventContact = notSure.event_contact;
								eventContact = isnull(eventContact);

								var dealName = notSure.extend_3;
								dealName = isnull(dealName);
								var eventStatus = notSure.deal_status;
								var status = "";
								if (eventStatus == 2) {
									status = "正常";
								}
								if (eventStatus == 3) {
									status = "增援";
								}
								if (eventStatus == 4) {
									status = "转派";
								}
								var str = "<tr>" + "<td>"
										+ eventCode
										+ "</td>"
										+ "<td>"
										+ eventName
										+ "</td>"
										+ "<td>"
										+ extend1
										+ "</td>"
										+ "<td>"
										+ extend2
										+ "</td>"
										+ "<td>"
										+ getFormatDate(new Date(dateCreate),
												"yyyy-MM-dd hh:mm:ss")
										+ "</td>"
										+ "<td>"
										+ eventContact
										+ "</td>"
										+ "<td>"
										+ dealName
										+ "</td>"
										+ "<td>"
										+ status
										+ "</td>"
										+ "<td><a href='javascript:checkNormal("
										+ id
										+ ")' class='btn btn-primary btn-sm'>确认</a></td>"
										+ "</tr>";
								notSuretbody.append(str);
							}
						},
						params : function() {
							var STATUS = "notSure"
							var startTime = $("#startTime").val();
							var eventContact = $("#eventContact").val();
							var endTime = $("#endTime").val();
							var idDept = $("#idDept").val();
							var eventPer = $("#eventPer").val();
							var statusNotSure = $("#eventStatus").val();
							return {
								startTime : startTime,
								eventContact : eventContact,
								endTime : endTime,
								idDept : idDept,
								statusNotSure : statusNotSure,
								eventPer : eventPer,
								STATUS : STATUS,
							};
						}
					});
}
// 改变岗位时 人员变动
/*
 * function changeUser() { $("#idUserDeal").empty(); var postId =
 * $("#idPost").val(); if (postId != "" && postId != null) { $.ajax({ dataType :
 * "json", type : "post", url : getPath() + "/changeUser.do", data :
 * {"postId":postId}, success : function(result) { var userNameList = result;
 * var str = ""; for (var i = 0; i < userNameList.length; i++) { var userName =
 * userNameList[i]; str+="<option
 * value="+userName.idUser+">"+userName.extend1+"</option>"; } if (str=="") {
 * $("#idUserDeal").append("<option value=''>该职能岗位下没有人员</option>"); }else{
 * $("#idUserDeal").append(str); } } }) } }
 */
// /eventAccept.do
// 点击受理进入受理界面
function checkAccept(eventId,eventTs) {
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href = getPath()+"/eventAccept.do?eventId="+eventId+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
	
}
function cancel(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	layer.closeAll('iframe');
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/toEventInfoAccept.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
// 确认正常
function checkNormal(eventId) {
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href = getPath() + "/checkEvent.do?eventId=" + eventId+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;;
}
function cancels(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/toEventInfoAccept.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
// 文本域最大输入字符
function checkLength(obj, maxlength) {
	if (obj.value.length > maxlength) {
		obj.value = obj.value.substring(0, maxlength);
		alert("最多只允许输入1000个字符");
	}
}
// 科室内容改变触发
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
				thirdSelect.add(new Option("请选择申告人",""))
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
		}
	})
}
function userChange() {
	$("#eventContact").val("");
	var userId = $("#eventUser").val();
	$.ajax({
		type : 'post',
		url : getPath() + "/userById.do",
		data : {
			"userId" : userId
		},
		dataType : "json",
		success : function(result) {
			var userCal = result.userCal;
			var result = result.deptEvent;
			$("#eventContact").val(userCal.cal);
			
		}
	})
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
