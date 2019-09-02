$(function() {
	var path = getPath();
	$("#seach").click(function() {
		showAssetsType();
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
   
	$("#addAssetsTypeOk").click(
			
			function() {
				$.each($(".error_null"),function(){
			         if($(this).val()!==""){
			             $(this).removeClass("err_null");
			         }
			     })
			
				if ($(".error_null").val() == "" || $(".error_null").hasClass("error")|| $(".err_null").hasClass("error")) {
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
						url:getPath() + "/insertAssetsType.do",
						data:$("#form1").serialize(),
						success:function(result){
								window.location.href = getPath()
										+ "/toSysAssetsType.do?cid=1";
						},error: function (err){
							alert($.parseJSON(err.responseText).msg);
						}
					})
				}
			})
	// 修改提交
	$("#modAssetsTypeOk").click(
			function() {
				if ($("input[type=text]").val() == ""
						|| $("input").hasClass("error")) {
					$.each($("input"), function() {
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
						url:getPath() + "/modifyAssetsTypeOk.do",
						data:$("#form1").serialize(),
						success:function(result){
							window.location.href = getPath()
							+ "/toSysAssetsType.do?cid=2";
						},error:function(err){
							alert($.parseJSON(err.responseText).msg);
						}
					})
				}
			})
	// 查看
	$("#checkAssetsType").click(function() {
		var ids = $(" tbody :checked");
		if (ids.parents("tr").length == 1) {
			var id = $(" tbody :checked").next().val();
			$.ajax({
				url : getPath() + "/checkAssetsType.do",
				data : {
					"id" : id
				},
				dataType : "json",
				type : "post",
				success : function(result) {
					var right = $(".check-content-right");
					var assetsType = result;
					var code = assetsType.code;
					code = isnull(code);
					var name = assetsType.name;
					var layered = assetsType.layer;
					layered = isnull(layered);
					if (layered == 1) {
						layered = '一级资产';
					} else if (layered == 2) {
						layered = '二级资产';
					} else if (layered == 3) {
						layered = '三级资产';
					}
					var parentName = assetsType.extend1;
					parentName = isnull(parentName);
					var remark = assetsType.remark;
					remark = isnull(remark);
					var array = [ code, name, layered, parentName, remark ];
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
				},error:function(err){
					alert($.parseJSON(err.responseText).msg);
				}
			})
		} else {
			alert("请勾选需要查看的单条数据！！！");
			return;
		}
		
	})
})
function showAssetsType() {
	$('#page2').empty();
	$('#page2')
			.bPage(
					{
						url : getPath() + "/selectSysAssetsType.do",
						// 开启异步处理模式
						asyncLoad : true,
						// 关闭服务端页面模式
						serverSidePage : false,
						// 数据自定义填充
						render : function(result) {
							// 将上级资产填入下拉框
							var pageNumber = result.pageNumber;
							var pageSize = result.pageSize;
							var totalPage = result.totalPage;
							var totalRow = result.totalRow;
							$("#pagenumber").val(pageNumber);
							$("#pagesize").val(pageSize);
							$("#totalpage").val(totalPage);
							$("#totalrow").val(totalRow);
							var parentName = result.parentName;
							var str = "";
							for (var i = 0; i < parentName.length; i++) {
								str += "<option value=" + parentName[i].id
										+ ">" + parentName[i].name
										+ "</option>";
							}
							if (document.getElementById("assetsTypeParent").options.length == 1) {
								$("#assetsTypeParent").append(str);
							}
							// 展示资产类型表格
							var tbody = $('#tbody').empty();
							var assetsTypeAll = result.assetsTypeAll;
							for (var j = 0; j < assetsTypeAll.length; j++) {
								var assetsType = assetsTypeAll[j];
								var id = assetsType.id;
								var code = assetsType.code;
								code = isnull(code);
								var name = assetsType.name;
								var layer = assetsType.layer;
								layer = isnull(layer);
								if (layer == 1) {
									layer = '一级资产';
								} else if (layer == 2) {
									layer = '二级资产';
								} else if (layer == 3) {
									layer = '三级资产';
								}
								var parentName = assetsType.extend1;
								parentName = isnull(parentName);
								var remark = assetsType.remark;
								remark = isnull(remark);
								var tr = "<tr>"
										+ "<td><input type='checkbox' name='item1'><input type='hidden' name='id' value="
										+ id + "></td>" + "<td>" + code
										+ "</td>" + "<td>" + name + "</td>"
										+ "<td>" + parentName + "</td>"
										+ "<td>" + layer + "</td>"
										+ "<td>" + remark + "</td>" 
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
							var assetsType = $("#assetsType").val();
							var assetsTypeParent = $("#assetsTypeParent").val();
							var assetsTypeLevel = $("#assetsTypeLevel").val();
							return {
								"pages":pages,
								"pagenumber":pagenumber,
								"pagesize":pagesize,
								"totalpage":totalpage,
								"totalrow":totalrow,
								assetsType : assetsType,
								assetsTypeParent : assetsTypeParent,
								assetsTypeLevel : assetsTypeLevel,
							};
						}
					});
	$("#pages").val("");
}
// 修改
function update() {
	if ($("tbody input:checked").length == 0) {
		alert("请选择需要修改的数据!");
		return false;
	}
	if ($("tbody input:checked").length > 1) {
		alert("请选择单行数据进行修改!");
		return false;
	}
	var id = $("tbody input:checked").next().val();
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/modifyAssets.do?id=" + id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
// 增加
function add() {
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/addAssetsType.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}

// 删除
function deleteId() {
	if ($("tbody input:checked").length == 0) {
		alert("请选择需要删除的数据！");
		return;
	}
	if (!confirm("确定删除该资产类型吗？")) {
		return;
	}
	var ids = $(" tbody :checked");
	var _list = [];
	for (var i = 0; i < ids.length; i++) {
		_list[i] = ids.eq(i).next().val();
	}
	$.ajax({
		url : getPath() + '/deleteAssetsType.do',
		data : {
			"ids" : _list
		},
		dataType : 'json',
		type : "post",
		traditional : true,// 属性在这里设置
		success : function(result) {
			if (result.data == "success") {
				showAssetsType();
			} else if (result.data == "pError") {
				alert("该资产下面还有子资产,请先对其进行删除!!");
			}
		},
	});
}
// 资产类型下拉
function closeSelect2() {
	var level = $('#level option:selected').val();
	$("#layer").val(level);
	if (level == 1) {
		$(".serInp").attr("disabled", "disabled").css("background-color",
				"#EEEEEE;").val("");
		$('#parentName').val("");
	}
	if (level == 2) {
		var assetsTypeId = $("#assetsTypeId").val();
		var parentName = $("#parentName").empty();
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : getPath() + "/selectAssetsTypeByLevel.do",
			data : {
				level : level - 1,
				assetsTypeId : assetsTypeId
			},
			success : function(data) {
				var options = "<option value=''>请选择资产类型</option>";
				for (var i = 0; i < data.length; i++) {
					options += "<option value=" + data[i].id + ">"
							+ data[i].name + "</option>";
				}
				parentName.append(options);
				$("#parentName option").click(function(){
					$(this).parent().hide();
					$(".serInp").val($(this).text());
				})
				$("#parentName").blur(function(){
					$(this).hide();		
				})
			}
		})
		$(".serInp").removeAttr("disabled");
	}
	if (level == 3) {
		var assetsTypeId = $("#assetsTypeId").val();
		var parentName = $("#parentName").empty();
		$.ajax({
			type : 'post',
			dataType : 'json',
			url : getPath() + "/selectAssetsTypeByLevel.do",
			data : {
				level : level - 1,
				assetsTypeId : assetsTypeId
			},
			success : function(data) {
				var options = "<option value=''>请选择资产类型</option>";
				for (var i = 0; i < data.length; i++) {
					options += "<option value=" + data[i].id + ">"
							+ data[i].name + "</option>";
				}
				parentName.append(options);
				$("#parentName option").click(function(){
					$(this).parent().hide();
					$(".serInp").val($(this).text());
				})
				$("#parentName").blur(function(){
					$(this).hide();		
				})
			}
		})
		$(".serInp").removeAttr("disabled");
	}
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/toSysAssetsType.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}