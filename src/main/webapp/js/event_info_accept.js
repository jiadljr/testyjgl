$(function() {
	// 提交受理信息
	$("#addAccept").click(
			function() {
				alert("受理提交");
				$.post(getPath() + "/accept.do", $("#form1").serialize(),
						function(result) {
							if (result == "success") {
								alert("受理成功!");
								window.location.href = getPath()
										+ "/toEventInfoAccept.do";
							}
						})
			})
})

// 替换tbody内容
function queryNotAcceptList() {
	$('#pageTable').empty();
	$('#pageTable')
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
							var accepts = result.notAcceptList;
							var tbody = $('#tbody').empty();
							for (var i = 0; i < accepts.length; i++) {
								var accept = accepts[i];
								var id = accept.id;
								var eventCode = accept.eventCode;
								eventCode = isnull(eventCode);

								var eventName = accept.eventName;
								eventName = isnull(eventName);

								var extend2 = accept.extend2;
								extend2 = isnull(extend2);

								var extend1 = accept.extend1;
								extend1 = isnull(extend1);

								var extend3 = accept.extend3;
								extend3 = isnull(extend3);

								var eventContact = accept.eventContact;
								eventContact = isnull(eventContact);

								var eventStatus = accept.eventStatus;
								if (eventStatus == 20) {
									var fromAccept = "申告";
								}
								var tr = "<tr>"
										+ "<td><input type='checkbox' name='item1'><input type='hidden' name='id' value="
										+ id
										+ "></td>"
										+ "<td>"
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
										+ extend3
										+ "</td>"
										+ "<td>"
										+ eventContact
										+ "</td>"
										+ "<td>"
										+ fromAccept
										+ "</td>"
										+ "<td><a href='javascript:checkAccept("
										+ id
										+ ")' class='btn_a1 btn_gray' >受理</a></td>"
										+ "</tr>";
								tbody.append(tr);
							}
						},
						params : function() {
							alert(111);
							var STATUS = "notAccept";
							var startTime = $("#startTime").val();
							var eventUser = $("#eventUser").val();
							var endTime = $("#endTime").val();
							var idDept = $("#deptId").val();
							var eventStatus = $("#eventStatus").val();
							var acceptFrom = $("#acceptFrom").val();
							return {
								STATUS : STATUS,
								startTime : startTime,
								endTime : endTime,
								idDept : idDept,
								eventStatus : eventStatus,
								acceptFrom : acceptFrom,
								eventUser:eventUser,
							}
						}
					});
}
function queryNotSureList() {
	$('#pageTable').empty();
	$('#pageTable')
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
							var accepts = result.notSureList;
							var tbody = $('#tbody').empty();
							for (var i = 0; i < accepts.length; i++) {
								var accept = accepts[i];
								var id = accept.id;
								var eventCode = accept.eventCode;
								eventCode = isnull(eventCode);

								var eventName = accept.eventName;
								eventName = isnull(eventName);

								var extend2 = accept.extend2;
								extend2 = isnull(extend2);

								var extend1 = accept.extend1;
								extend1 = isnull(extend1);

								var extend3 = accept.extend3;
								extend3 = isnull(extend3);

								var eventContact = accept.eventContact;
								eventContact = isnull(eventContact);

								var eventStatus = accept.eventStatus;
								if (eventStatus == 20) {
									var fromAccept = "申告";
								}
								var tr = "<tr>"
										+ "<td><input type='checkbox' name='item1'><input type='hidden' name='id' value="
										+ id
										+ "></td>"
										+ "<td>"
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
										+ extend3
										+ "</td>"
										+ "<td>"
										+ eventContact
										+ "</td>"
										+ "<td>"
										+ fromAccept
										+ "</td>"
										+ "<td><a href='javascript:checkAccept("
										+ id
										+ ")' class='btn_a1 btn_gray' >受理</a></td>"
										+ "</tr>";
								tbody.append(tr);
							}
						},
						params : function() {
							var STATUS = "notSure";
							return {
								STATUS : STATUS
							}
							/*
							 * var startTime = $("#startTime").val(); var
							 * eventContact = $("#eventContact").val(); var
							 * endTime = $("#endTime").val(); var idDept =
							 * $("#idDept").val(); var eventStatus =
							 * $("#eventStatus").val(); var acceptFrom =
							 * $("#acceptFrom").val(); return { startTime :
							 * startTime, eventContact : eventContact, endTime :
							 * endTime, idDept : idDept, eventStatus :
							 * eventStatus, acceptFrom : acceptFrom, };
							 */
						}
					});
}
// 改变岗位时 人员变动
function changeUser() {
	$("#idUserDeal").empty();
	var postId = $("#idPost").val();
	if (postId != "" && postId != null) {
		$.ajax({
			dataType : "json",
			type : "post",
			url : getPath() + "/changeUser.do",
			data : {
				"postId" : postId
			},
			success : function(result) {
				var userNameList = result;
				var str = "";
				for (var i = 0; i < userNameList.length; i++) {
					var userName = userNameList[i];
					str += "<option value=" + userName.idUser + ">"
							+ userName.extend1 + "</option>";
				}
				if (str == "") {
					$("#idUserDeal").append(
							"<option value=''>该职能岗位下没有人员</option>");
				} else {
					$("#idUserDeal").append(str);
				}
			}
		})
	}
}
// /eventAccept.do
// 点击受理进入受理界面
function checkAccept(eventId) {
	window.location.href = getPath() + "/eventAccept.do?eventId=" + eventId;
}

// 文本域最大输入字符
function checkLength(obj, maxlength) {
	if (obj.value.length > maxlength) {
		obj.value = obj.value.substring(0, maxlength);
		alert("最多只允许输入1000个字符");
	}
}

