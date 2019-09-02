$(function() {
	var path = getPath();
	// 搜索
	$("#button_search").click(function() {
		showMenu();
	})
	// 查看
	$("#seeMenu").click(function() {
		if ($(" tbody :checked").length > 1) {
			alert("请选择单行进行查看");
		}
		if ($(" tbody :checked").length < 1) {
			alert("请选择要查看的内容");
		}
		if ($(" tbody :checked").length == 1) {
			var id = $(" tbody :checked").next().val();
			var url = getPath() + "/checkMenu.do";
			$.post(url, {
				"id" : id
			}, function(result) {
				var right = $(".check-content-right");
				var menu = result.data.sysMenuOne;
				var code = menu.code;
				code = isnull(code);
				var name = menu.name;
				var level = menu.menuLevel;
				level = isnull(level);
				if (level == 1) {
					level = '一级菜单';
				} else if (level == 2) {
					level = '二级菜单';
				}
				var parentName = menu.extend1;
				parentName = isnull(parentName);
				var url = menu.menuUrl;
				url = isnull(url);
				var remark = menu.remark;
				remark = isnull(remark);
				var array = [ code, name, level, parentName, url, remark ];
				for (var i = 0; i < right.length; i++) {
					right.eq(i).text(array[i]);
				}
				 layer.open({
			         type: 1,
			         title: '查看信息',
			         shadeClose: false,
			         area: ['463px', '310px'],
			         content:$(".check-win").html()
			     
			       })
			});
	
		}
	})
	$(".openWin button").click(function() {
		$(".openWin").hide();
	})
	// 删除
	$("#deleteMenu").click(function() {
		var ids = $(" tbody :checked");
		if (ids.parents("tr").length < 1) {
			alert("请勾选需要删除的数据！！！");
			return;
		}
		if (!confirm("确定删除该菜单吗？")) {
			return;
		}
		var _list = [];
		for (var i = 0; i < ids.length; i++) {
			_list[i] = ids.eq(i).next().val();
		}
		$.ajax({
			url : path + '/deleteByPrimaryKey.do',
			data : {
				"ids" : _list
			},
			dataType : 'json',
			type : "post",
			traditional : true,// 属性在这里设置
			success : function(result) {
				var mass = result.data
				if (mass == "pError") {
					alert("该菜单还有下级菜单，不能删除!!");
					return;
				} else if (mass == "success") {
					showMenu();
				}
			},
		});

	})
	// 去添加
	var path1 = getPath();
	$("#addMenu").click(function() {
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		window.location.href = path1 + "/toAddMenu.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
	})
	// 添加
	/**
	 *多行非空验证 
	 * 逐个元素添加 class名 err_null
	 * 判断是否存在err_null
	 **/
		
	 $.each($(".error_null"),function(){
         if($(this).val()==""){
             $(this).addClass("err_null");
         }
     })
     $('#meauUp').focus(function(){
    	$(this).removeClass('error');
    	
     })
	$("#addConfirm").click(
			
			function() {
				$.each($(".error_null"),function(){
			         if($(this).val()!==""){
			             $(this).removeClass("err_null");
			         }
			     })
				
				if ($(".error_null").val() == "" || $(".error_null").hasClass("error") || $(".error_null").hasClass("err_null")) {
					$.each($(".error_null"), function() {
						if ($(this).val() == "") {
							var error = $(this).attr("data-message");
							$(this).val(error);
							$(this).addClass("error");
						}
					})
					return false;
				} else {
					var path = getPath();
					$.ajax({
						type:"post",
						url:path + "/insertMenu.do",
						data:$("#form1").serialize(),
						success:function(){
							window.location.href = path
							+ "/toConfigureMenu.do?cid=1";
						},error :function(err){
							alert($.parseJSON(err.responseText).msg);
						}
					})
				}
			})

	// 去修改
	$("#updateMenu").click(function() {
		var ids = $(" tbody :checked").parents("tr");
		if (ids.length < 1) {
			alert("请勾选需要修改的数据！！！");
			return;
		}
		if (ids.length > 1) {
			alert("请选择单条数据进行修改！！！");
			return;
		}
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		var id = $(" tbody :checked").next().val();
		window.location.href = path1 + "/toModMenu.do?id=" + id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
	})
	// 修改
	$("#modConfirm").click(
			function() {
				if ($("input,textarea").val() == ""
						|| $("input,textarea").hasClass("error")) {
					return false;
				} else {
					var path = getPath();
					$.post(path + "/modMenu.do", $("#form1").serialize(),
							function() {
									window.location.href = path + "/toConfigureMenu.do?cid=2";
							})
				}
			})
	/*
	 * 授权功能
	 */
	// 角色:回显
	$("#authorMenu").click(
			function() {
				$("#button_search_author").removeAttr("disabled");
				var ids = $(" tbody :checked").parents("tr");
				if (ids.length < 1) {
					alert("请勾选需要授权的菜单！！！");
					return;
				}
				if (ids.length > 1) {
					alert("请选择单个菜单进行授权！！！");
					return;
				}
				var id = $(" tbody :checked").next().val();
				if (id == "") {
					return;
				}
				$("#sys_role").children("option").prop("selected", false);
				$("#sys_role").selectpicker('refresh');
				var menuName = $(" tbody :checked").parent().next().next()
						.text();
				
				$("#menu_name").val(menuName);
				$("#menu_id").val(id);
				$.post(path + "/selectByMenu.do", {
					"menuId" : id
				}, function(result) {
					var str = result.data;
					str = str.substr(0, str.length - 1);
					if (str != null && str != undefined) {
						var data = str.split(",");
						for (var i = 0; i < data.length; i++) {
							$("#sys_role").children(
									"option[value=" + data[i] + "]").prop(
									"selected", true);
						}
						$('#sys_role').selectpicker('refresh');
						$('#sys_role').selectpicker('render');
					}
				})
				$(".author_jump").fadeIn();
				$(".mask").fadeIn();
				
			
			})
	// 授权:确定
	$("#button_search_author").click(function() {
		$("#button_search_author").attr("disabled","disabled");
		var menuId = $("#menu_id").val();
		var roleStr = $("#sys_role").val();
		if (roleStr == null || roleStr == "") {
			alert("请选择角色！");
			return false;
		}
		var roleStrs = String(roleStr);
		// alert(typeof(roleStr));
		$('#sys_role').selectpicker('refresh');
		$('#sys_role').selectpicker('render');
		$.post(path + "/updateRoleMenu.do", {
			"menuId" : menuId,
			"roleStr" : roleStrs
		}, function(result) {
			showMenu();
			$(".author_jump").fadeOut();
			$(".mask").fadeOut();
			
		})
	})
	// 授权:取消
	$("#button_cancel_author").click(function() {
		$(".author_jump").fadeOut();
		$(".mask").fadeOut();
		
	})
});
// 角色展示
function roleManu(roles) {
	var str = "";
	var sys_role = $("#sys_role");
	for (var i = 0; i < roles.length; i++) {
		var role = roles[i];
		str += "<option value=" + role.id + ">" + role.name + "</option>";

	}
	sys_role.append(str);
	$('#sys_role').selectpicker('refresh');
	$('#sys_role').selectpicker('render');
}
function showMenu() {
	$("#page2").empty();
	$('#page2')
			.bPage(
					{
						url : getPath() + "/selectByExample.do",
						// 开启异步处理模式
						asyncLoad : true,
						// 关闭服务端页面模式
						serverSidePage : false,
						// 数据自定义填充
						render : function(result) {
							var pageNumber = result.pageNumber;
							var pageSize = result.pageSize;
							var totalPage = result.totalPage;
							var totalRow = result.totalRow;
							$("#pagenumber").val(pageNumber);
							$("#pagesize").val(pageSize);
							$("#totalpage").val(totalPage);
							$("#totalrow").val(totalRow);
							var parName = result.parName;
							var str = "";
							for (var j = 0; j < parName.length; j++) {
								str += "<option value=" + parName[j].id + ">"
										+ parName[j].name + "</option>";
							}
							if (document.getElementById("meauUp").options.length == 1) {
								$("#meauUp").append(str);
							}
							var roleAll = result.roleAll;
							var sys_role = document.getElementById("sys_role").options.length;
							if (sys_role == 0) {
								// 将所有角色放入下拉框
								roleManu(roleAll);
							}
							// 分页
							var menus = result.SysMenuInfo;
							var tbody = $('#tbody').empty();
							for (var i = 0; i < menus.length; i++) {
								var menu = menus[i];
								var id = menu.id;
								var code = menu.code;
								code = isnull(code);
								var name = menu.name;
								var level = menu.menuLevel;
								level = isnull(level);
								if (level == 1) {
									level = '一级菜单';
								} else if (level == 2) {
									level = '二级菜单';
								}
								var parentName = menu.extend1;
								parentName = isnull(parentName);
								var url = menu.menuUrl;
								url = isnull(url);
								var remark = menu.remark;
								remark = isnull(remark);
								var tr = "<tr>"
										+ "<td><input type='checkbox' name='item1'><input type='hidden' name='id' value="
										+ id + "></td>" + "<td>" + code
										+ "</td>" + "<td>" + name + "</td>"
										+ "<td>" + level + "</td>" + "<td>"
										+ parentName + "</td>" + "<td>" + url
										+ "</td>" + "<td>" + remark + "</td>"
										+ "</tr>";
								tbody.append(tr);
							}
						},
						params : function() {
							var pages = $("#pages").val();
							var pagenumber = $("#pagenumber").val();
							var pagesize = $("#pagesize").val();
							var totalpage = $("#totalpage").val();
							var totalrow = $("#totalrow").val();
							var menuName = $("#menuName").val();
							var meauUp = $("#meauUp").val();
							var menuLevel = $("#menuLevel").val();
							return {
								"pages":pages,
								"pagenumber":pagenumber,
								"pagesize":pagesize,
								"totalpage":totalpage,
								"totalrow":totalrow,
								menuName : menuName,
								meauUp : meauUp,
								menuLevel : menuLevel,
							};
						}
					});
				$("#pages").val("");
}
// 控制菜单下拉框
function closeSelect2() {
	var meauGrade = $('#meauGrade option:selected').val();
	$("#level").val(meauGrade);
	if (meauGrade == 1) {
		$("#meauUp").attr("disabled", "disabled").css("background-color",
				"#EEEEEE;");
		$('#meauUp').val("");
	}
	if (meauGrade == 2) {
		var menuId = $("#menuId").val();
		var meauUp = $("#meauUp").empty();
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : getPath() + "/selectMenuByLevel.do",
			data : {
				meauGrade : meauGrade - 1,
				menuId : menuId
			},
			success : function(data) {
				var options = "<option value=''>请选择上级菜单</option>";
				for (var i = 0; i < data.length; i++) {
					options += "<option value=" + data[i].id + ">"
							+ data[i].name + "</option>";
				}
				meauUp.append(options);
			}
		})
		$("#meauUp").removeAttr("disabled");
	}
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/toConfigureMenu.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}