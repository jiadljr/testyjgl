	$(function (){
	$("#sidebar").hide();
				$("#searchTree").keyup(function(){
					if ($("#searchTree").val() != "") {
						$("#searchTreeClose").show();
						removeLi();
					} else {
						$("#searchTreeClose").hide();
						removeLi();
					}
					$('#a').colExpAll({
						clickType : 'search'
					});
					removeLi();
				})
				var url = getPath()+"/findAllDept.do";
				var deptOne;
				var deptTwo;
				var deptThree;
				var deptFour;
				$.ajax({
					url:url,
					data:{},
					type:'post',
					async:false,
					success:function(result){
					deptOne = result.deptOne;
					deptTwo = result.deptTwo;
					deptThree = result.deptThree;
					deptFour = result.deptFour;
					$.each(deptOne,function(i,one){
						var type = "<li  class=\"deptOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryType("+deptOne[i].id+",\'"+one.name+"\')\">";
							type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"one"+i+"\">";
						$.each(deptTwo,function(j,two){
							if(deptOne[i].id == deptTwo[j].parentId){
								type += "<li class=\"deptTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryType("+ deptTwo[j].id + ",\'"+two.name+"\')\">";
								type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"two"+j+"\">";	
								$.each(deptThree,function(k,three){
									if (deptTwo[j].id == deptThree[k].parentId) {
										type += "<li class=\"deptThree\"><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryType("+deptThree[k].id+",\'"+three.name+"\')\">";
										type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a><ul id=\"three"+k+"\">";
										$.each(deptFour,function(m,four){
											if (deptThree[k].id == deptFour[m].parentId) {
												type += "<li><a autobypy="+four.extend2 +"href=\"#\" onclick=\"queryType("+deptFour[m].id+",\'"+four.name+"\')\">";
												type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+four.name+"</a>";
											}
										})
										type+="</ul></li>";
									}
								})
								type+="</ul></li>"; 
							}
						})
						type += "</ul></li>"; 
						$(".bzmenus_sub").append(type);
						$("#treeDiv a").click(function(){
							$("#treeDiv a").css({"color":"#183152"});
							$(this).css({"color":"red"});
							$("#openTree").css({"color":"#183152"})
							 
						})
						 
					})}
				})
				removeLi();
				$("#openTree").click();
				$("#openTree").css({"color":"#183152"})
				
				$("#searchTree").one("click",function(){
				$.each($(".collapsable"),function(){
					$(this).click();
				})
				$(".last_collapsable").click();
			})
		})
	function showTree(){
	$("#treeDiv").show();
	$('body').bind("mousedown", onBodyDown);
	$("#sidebar").show();
	}
	function onBodyDown(event) {
		   if (!(event.target.id == "menuBtn" || event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length > 0)) {  
			$("#treeDiv").hide();
			$("#sidebar").hide();
		   }
		}
	function queryType(id,name){
		$("#idDept").val(id);
		$("#searchTree").val(name);
		$("#treeDiv").hide();
		$("#sidebar").hide();
	}

	//事件源
	
	$("#sourceSearchTree").keyup(function(){
		if ($("#sourceSearchTree").val() != "") {
			$("#searchTreeClose").show();
			removeSourceLi();
		} else {
			$("#searchTreeClose").hide();
			removeSourceLi();
		}
		$('#b').colExpAll({
			clickType : 'search',
			input : 'sourceSearchTree',
			closeBtn : 'sourceSearchTreeClose',
		});
		removeSourceLi();
	})
	$("#sourceSearchTree").bind("keydown", function(e) {
			if (e.keyCode == 13) {
				if ($("#sourceSearchTree").val() != "") {
					$("#sourceSearchTreeClose").show();
					removeSourceLi();
				} else {
					$("#sourceSearchTreeClose").hide();
					removeSourceLi();
				}
				$('#b').colExpAll({
					clickType : 'search',
					input : 'sourceSearchTree',
					closeBtn : 'sourceSearchTreeClose',
				});
				removeSourceLi();
			}
		});
		$("#sourceSidebar").hide();
		//事件源
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
		$(function(){
			var url = getPath()+"/selectAssetsTypeForTree.do";
			var typeOne;
			var typeTwo;
			var typeThree;
			$.ajax({
				url:url,
				data:{},
				type:'post',
				async:false,
				success:function(result){
					typeOne = result.assetsTypeOne;
					typeTwo = result.assetsTypeTwo;
					typeThree = result.assetsTypeThree;
					$.each(typeOne,function(i,one){
						var type = "<li  class=\"typeOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"querySourceType("+typeOne[i].id+",\'"+one.name+"\')\">";
							type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"sourOne"+i+"\">";
						$.each(typeTwo,function(j,two){
							if(typeOne[i].id == typeTwo[j].parentId){
								type += "<li class=\"typeTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"querySourceType("+ typeTwo[j].id + ",\'"+two.name+"\')\">";
								type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"sourTwo"+j+"\">";	
								$.each(typeThree,function(k,three){
									if (typeTwo[j].id == typeThree[k].parentId) {
										type += "<li><a autobypy="+three.extend2 +"href=\"#\" onclick=\"querySourceType("+typeThree[k].id+",\'"+three.name+"\')\">";
										type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a>";
									}
								})
								type+="</ul></li>"; 
							}
						})
						type += "</ul></li>"; 
						$(".source_bzmenus_sub").append(type);
						$("#sourceTreeDiv a").click(function(){
							$("#sourceTreeDiv a").css({"color":"#183152"});
							$(this).css({"color":"red"});
							$("#openSourceTree").css({"color":"#183152"})
						})
						 
					})
				}
			})
			removeSourceLi();
			$("#openSourceTree").click();
			$("#openSourceTree").css({"color":"#183152"})
			$("#sourceSearchTree").one("click",function(){
			$.each($(".collapsable"),function(){
				$(this).click();
			})
			$(".last_collapsable").click();
		})
		})
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
		function querySourceType(id,name){
			$("#sourceId").val(id);
			$("#sourceSearchTree").val(name);
			$("#sourceTreeDiv").hide();
			$("#sourceSidebar").hide();
		}
//列表的展示
function getPathList(){
	$('#page2').empty();
	$('#page2').bPage({
		url :getPath()+"/selectAssets.do",
		//开启异步处理模式
		asyncLoad : true,
		//关闭服务端页面模式
		serverSidePage : false,
		//数据自定义填充
		render : function(result){
				var pageNumber = result.pageNumber;
				var pageSize = result.pageSize;
				var totalPage = result.totalPage;
				var totalRow = result.totalRow;
				$("#pagenumber").val(pageNumber);
				$("#pagesize").val(pageSize);
				$("#totalpage").val(totalPage);
				$("#totalrow").val(totalRow);
				var table = $('#myTable');
				table.empty();
				var assets = result.assetsList;
				for (var i = 0; i < assets.length; i++) {
					var id=assets[i].id;
					var code=assets[i].code;
					code=isnull(code);
					var name=assets[i].name;
					name=isnull(name);
					var asModel=assets[i].asModel;
					asModel=isnull(asModel);
					var extend2=assets[i].extend2;
					extend2=isnull(extend2);
					var asManuf=assets[i].asManuf;
					asManuf=isnull(asManuf);
					var extend3=assets[i].extend3;
					extend3=isnull(extend3);
					var extend1=assets[i].extend1;
					extend1=isnull(extend1);
					var status = assets[i].status;
					if(status == 0){
						status = "正常";
					}else if(status == 1){
						status = "故障";
					}
					table.append     
					 ('<tr ><td ><input id="check" type="checkbox" name="item1"><input type="hidden" value=' +assets[i].id+'></td>'+
							 '<td style="display:none;">'+ id +'</td>'+
						      '<td>'+ code +'</td>'+
						      '<td>'+name +'</td>'+
						      '<td>'+ asModel +'</td>'+
						      '<td>'+extend2 +'</td>'+
						      '<td>'+asManuf +'</td>'+
						      '<td>'+extend3 +'</td>'+
						      '<td>'+extend1 +'</td>' +
						      '<td>'+status +'</td></tr>');
		     }
			},
		params : function(){
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			var asManuDept = $("#idDept").val();
			if ($("#asManuDept").val() == "") {
				asManuDept = "";
			}
			var asModel = $("#asModel").val();
			var name = $("#name").val();
			var code = $("#code").val()
			return {
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
				asManuDept : asManuDept,
				asModel : asModel,
				name : name,
				code : code,
			};
		}
	});
	$("#pages").val("");
}
//删除
function deleteId(){
	if($("tbody input:checked").length==0){
		alert("请选择需要删除的数据！");
		return false;
	}
	if(!confirm("确定删除该资产吗？")){
		return;
	}
	var ids=$(" tbody input:checked");
	var _list = new Array();  
	for (var i = 0; i < ids.length; i++) {  
	    _list[i] = ids.eq(i).parent().next().text();  
	}
	var url=getPath()+"/deleteAssets.do";
	$.ajax({
        url :url,
        data : {"id" : _list},
        dataType : 'json',
        type : "post",
        traditional: true,//属性在这里设置
        success : function(result) {
           $('#myTable').empty();
           getPathList();
        },
    });
}
//增加资产界面
function add(){
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		location.href=getPath() + "/addAssets.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
//修改资产界面
function update(){
	if($("tbody input:checked").length==0){
		alert("请选择需要修改的数据！");
		return false;
	}
	if($("tbody input:checked").length>1){
		alert("请选择单行数据进行修改！");
		return false;
	}
	var id=$("tbody input:checked").next().val();
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/modifyAsset.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
$(function(){
	//增加
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
     $("#sourceName").focus(function(){
    	 $("#sourceName").removeClass("error");
     })
    $("#asManuDept").focus(function(){
    	 $("#asManuDept").removeClass("error");
     })
	$("#addAssetsOk").click(function(){
		if($("#asMType").val()==""){
			$("#sourceName").addClass("error");
		}
		  if($("#idDept").val()==""){
			  $("#asManuDept").addClass("error");
		  }
		$.each($(".error_null"),function(){
	         if($(this).val()!==""){
	             $(this).removeClass("err_null");
	         }
	     })
		if($(".error_null").val()=="" || $(".error_null,#sourceName").hasClass("error")|| $(".error_null").hasClass("err_null")){
			$.each($(".error_null"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;
		}
		$.ajax({
			type:"post",
			url:getPath() + "/addAssetsOk.do",
			data:$("#form1").serialize(),
			traditional: true,
			success:function(result){
					location.href=getPath() + "/seleAssets.do?cid=1";
			},error:function(err){
				alert($.parseJSON(err.responseText).msg);
			}
		})
	})
	//修改
	$("#modAssetsOk").click(function(){
		if($("input").val()=="" || $("input").hasClass("error")){
			$.each($("input"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;
		}else{
			$.ajax({
				type:"post",
				url:getPath()+"/assSub.do",
				data:$("#form1").serialize(),
				traditional: true,
				success:function(result){
					if(result == "success"){
						location.href=getPath() + "/seleAssets.do?cid=2";
					}
				},error:function(err){
					alert(123123);
					alert($.parseJSON(err.responseText).msg);
				}
			})
		}
	})
})
//查看资产界面
function examine(){
	$(".openWinShow").show();
}
function cancle(){
	$(".openWinShow").hide();
}

function getExamine(){
	$("#examine").click(function(){
		if($("input:checked").length>1){
			alert("请选择单行进行查看");
		}
		if($("input:checked").length<1){
			alert("请选择要查看的内容");
		}if($("input:checked").length==1){
			var id=$("input:checked").parent().next().text();			
			var url=getPath() + "/seleAssById.do";
			$.ajax({
				type:"post",
				url:url,
				data:{"id":id},
				success:function(result){
					var myTab = $("#myTab");
					var seleAss=result.seleAss;
					for(var i = 0;i<seleAss.length;i++){
					var status = seleAss[i].status;
					if(status == 0){
						status = "正常";
					}else if(status == 1){
						status = "删除";
					}
					var array=[isnull(seleAss[i].code),isnull(seleAss[i].name),isnull(seleAss[i].asModel),isnull(seleAss[i].extend2)
					           ,isnull(seleAss[i].asManuf),isnull(seleAss[i].extend3),isnull(seleAss[i].extend1),isnull(status)];
					var right=$(".check-content-right");
					for (var i = 0; i < right.length; i++) {
						right.eq(i).text(array[i]);
					}
					 layer.open({
				         type: 1,
				         title: '查看信息',
				         shadeClose: false,
				         area: ['463px', '420px'],
				         content:$(".check-win").html()
				     
				       })
					}
				},error:function(err){
					alert($.parseJSON(err.responseText).msg);
				}
			})
			
		}
	})
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/seleAssets.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}

/**资产导入*/
function toLead(){
	 layer.open({
		    type: 2,
		    title: '资产导入',
		    shadeClose: false,
		    area: ['480px', '140px'],
			content:getPath()+'/toLead.do'
	 })
}

function changeAssetsType(){
	if ($("#sourceName").val()=="") {
		$("#asMType").val("");
	}
}
function changeDept(){
	if ($("#asManuDept").val()=="") {
		$("#idDept").val("");
	}
}
function downLoadLead(){
	$.ajax({
		url:getPath()+"/excelOut.do",
		type:"post",
		async:false,
		success:function(result){
			if (result == 'success') {
				location.href = getPath()+"/downExcel.do";
			}
		},
		error:function(err){
			alert($.parseJSON(err.responseText).msg);
		}
	})
	
}
