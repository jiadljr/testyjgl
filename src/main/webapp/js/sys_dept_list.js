$(function() {
	var path = getPath();
	// 查看
	$("#checkDept").click(function() {
		var ids = $(" tbody :checked");
		if (ids.parents("tr").length == 1) {
			var id = $(" tbody :checked").next().val();
			$.ajax({
				url : getPath() + "/checkDept.do",
				data : {
					"id" : id
				},
				dataType : "json",
				type : "post",
				success : function(result) {
					var dept = result.deptAll;
					var code = dept.code;
					code = isnull(code);
					var name = dept.name;
					name = isnull(name);
					var level = dept.level;
					level = isnull(level);
					if (level == 1) {
						level = "一级部门";
					}
					if (level == 2) {
						level = "二级部门";
					}
					if (level == 3) {
						level = "三级部门";
					}
					var extend1 = dept.extend1;
					extend1 = isnull(extend1);
					var remark = dept.remark;
					remark = isnull(remark);
					var right = $(".check-content-right");
					var array = [ code, name, level, extend1, remark ];
					for (var i = 0; i < right.length; i++) {
						right.eq(i).text(array[i]);
					}
					 layer.open({
				         type: 1,
				         title: '查看信息',
				         shadeClose: false,
				         area: ['463px', '260px'],
				         content:$(".check-win").html()				     
			       })
				}
			})
		} else {
			alert("请勾选需要查看的单条数据！！！");
			return;
		}
	
	})
	// 搜索
	$("#button_search").click(function() {
		showDept();
	})
	// 删除
	$("#delDept").click(function() {
		var ids = $(" tbody :checked");
		if (ids.parents("tr").length < 1) {
			alert("请勾选需要删除的数据！！！");
			return;
		}
		if (!confirm("确定删除该部门吗？")) {
			return;
		}
		var _list = [];
		for (var i = 0; i < ids.length; i++) {
			_list[i] = ids.eq(i).next().val();
		}
		$.ajax({
			url : path + '/deleteDeptByPrimaryKey.do',
			data : {
				"ids" : _list
			},
			dataType : 'json',
			type : "post",
			traditional : true,// 属性在这里设置
			success : function(result) {
				var mass = result.data;
				if (mass == "pError") {
					alert("该部门还有下级部门，不能删除!!");
					return;
				} else if (mass == "success") {
					showDept();
				}
			},
		});
	})

	// 去添加界面
	$("#addDept").click(function() {
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		window.location.href = path + "/toAddDept.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
	})
	// 添加
	 $.each($(".error_null"),function(){
         if($(this).val()==""){
             $(this).addClass("err_null");
         }
     })
	$("#addConfirm").click(
		function() {	
			$.each($(".error_null"),function(){
		         if($(this).val()!==""){
		             $(this).removeClass("err_null");
		         }
		     })
			if ($(".error_null").val() == "" || $(".error_null").hasClass("error")|| $(".error_null").hasClass("err_null")) {
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
					url:path + "/AddDept.do",
					data:$("#form1").serialize(),
					success:function(){
						window.location.href = path + "/sysDepart.do?cid=1";
					},error:function(err){
						alert($.parseJSON(err.responseText).msg);
					}
				})
				}
			})
	/*
	 * 去修改
	 */
	$("#updDept").click(function() {
		var ids = $(" tbody :checked").parents("tr");
		if (ids.length < 1) {
			alert("请勾选需要修改的数据！！！");
			return;
		}
		if (ids.length > 1) {
			alert("请选择单条数据进行修改！！！");
			return;
		}
		var id = $(" tbody :checked").next().val();
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		window.location.href = path + "/toUpdDept.do?id=" + id + "&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
	})
	// 修改
	$("#modConfirm").click(
			function() {
				$("#deptParent").removeAttr("disabled");
				$("#deptLevel").removeAttr("disabled");
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
					var path = getPath();
					$.post(path + "/updDept.do", $("#form1").serialize(),
							function(result) {
								
								window.location.href = path + "/sysDepart.do?cid=2";
							})
				}
			})
})
//添加
 function save(){
		
	 }
	
// 替换tbody内容
function showDept() {
	$('#page2').empty();
	$('#page2')
			.bPage(
					{
						url : getPath() + "/selectPrimAll.do",
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
							var parentAll = result.parentAll;
							var str = "";
							for (var i = 0; i < parentAll.length; i++) {
								str += "<option value=" + parentAll[i].id + ">"
										+ parentAll[i].name + "</option>";
							}
							if (document.getElementById("deptParent").options.length == 1) {
								$("#deptParent").append(str);
							}
							// 部门列表展示
							var depts = result.deptAll;
							var tbody = $('#tbody').empty();
							for (var i = 0; i < depts.length; i++) {
								var dept = depts[i];
								var ds = dept.ds;
								if (ds != 1) {
									var id = dept.id;
									var code = dept.code;
									code = isnull(code);
									var name = dept.name;
									name = isnull(name);
									var level = dept.level;
									level = isnull(level);
									if (level == 1) {
										level = "一级部门";
									}
									if (level == 2) {
										level = "二级部门";
									}
									if (level == 3) {
										level = "三级部门";
									}
									if (level == 4) {
										level = "四级部门";
									}
									var extend1 = dept.extend1;
									extend1 = isnull(extend1);
									var remark = dept.remark;
									remark = isnull(remark);
									var tr = "<tr>"
											+ "<td><input type='checkbox' name='item1'><input type='hidden' name='id' value="
											+ id + "></td>" + "<td>" + code
											+ "</td>" + "<td>" + name + "</td>"
											+ "<td>" + level + "</td>" + "<td>"
											+ extend1 + "</td>" + "<td>"
											+ remark + "</td>" + "</tr>";
									tbody.append(tr);
								}
							}
						},
						params : function() {
							var pages = $("#pages").val();
							var pagenumber = $("#pagenumber").val();
							var pagesize = $("#pagesize").val();
							var totalpage = $("#totalpage").val();
							var totalrow = $("#totalrow").val();
							var deptName = $("#deptName").val();
							var deptLevel = $("#deptLevel").val();
							var deptParent = $("#deptParent").val();
							return {
								"pages":pages,
								"pagenumber":pagenumber,
								"pagesize":pagesize,
								"totalpage":totalpage,
								"totalrow":totalrow,
								deptName : deptName,
								deptLevel : deptLevel,
								deptParent : deptParent,
							};
						}
					});
	$("#pages").val("");
}
function closeSelect2() {
	var deptLevel = $('#deptLevel option:selected').val();
	$("#level").val(deptLevel);
	if (deptLevel == 1) {
		$(".serInp").attr("disabled", "disabled").css("background-color",
				"#EEEEEE;").val("");
		$('#deptParent').val("");
	}else{
		var deptId = $("#deptId").val();
		var deptParent=$("#deptParent").empty();
		$.ajax({
			type:'post',
			dataType:'json',
			url: getPath()+"/selectDeptByLevel.do",
			data:{deptLevel: deptLevel-1,deptId:deptId},
			success:function(data){
				var options="<option value=''>请选择上级部门</option>";
				for (var i = 0; i < data.length; i++) {
					options+="<option value="+data[i].id+">"+data[i].name+"</option>";
				}
				deptParent.append(options);
				$("#deptParent option").click(function(){
					$(this).parent().hide();
					$(".serInp").val($(this).text());
				})
				$("#deptParent").blur(function(){
					$(this).hide();		
				})
			}
		})
			
		$(".serInp").removeAttr("disabled");
		$("#deptParent").empty();
	}
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/sysDepart.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
