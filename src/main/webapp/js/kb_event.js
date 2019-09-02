var zNodesSource;
var setting2;
$(function(){
	//资产类型
	var dataSourceList = "[";
	$.ajax({
		type : "post",
		url : getPath() + "/selectAssetsTypeForTree.do",
		async : false,
		success : function(data) {
			var assetsType = data.selectPrimAll;
			$.each(assetsType, function(k, v) {
				dataSourceList += "{id:" + v.id + ",";
				dataSourceList += "pId:" + v.parentId + ",";
				dataSourceList += "name:\"" + v.name + "\"}";
				if (k < assetsType.length - 1) {
					dataSourceList += ",";
				}
			})
			dataSourceList += "]";
		}
	})
	var zTreeSource;
	zNodesSource = eval(dataSourceList);
	setting2 = {
		view : {
			selectedMulti : false,
			showIcon: false
		},
		data : {
			simpleData : {
				enable : true,
				idKey : "id",
				pIdKey : "pId",
				rootPId : ""
			}
		},
		callback : {
			beforeClick : function(treeId, treeNode) {
				var zTreeSource = $.fn.zTree.getZTreeObj("sourceTree");
				$("#sourceName").val(treeNode.name);
				$("#assetType").val(treeNode.id);
				$("#sourceTree").hide();
				return true;
			}
		}
	};
	$(document).ready(function() {
		var t = $("#sourceTree");
		t = $.fn.zTree.init(t, setting2, zNodesSource);
	});

	$("#sourceName").focus(function() {
		$("#sourceTree").show();
		$('body').bind("mousedown", onBodyDownSource);
	})
	function onBodyDownSource(event) {  
	   if (!(event.target.id == "menuBtn" || event.target.id == "sourceTree" || $(event.target).parents("#sourceTree").length > 0)) {  
		$("#sourceTree").hide();
	   }  
	}
})
//关键字搜索部门树
function deptZtrees() {
    $.fn.zTree.init($("#tree"), setting, zNodes);
}
function deptUp(txtObj){
	 if (txtObj.value.length > 0) {
		 deptZtrees();
            var zTree = $.fn.zTree.getZTreeObj("tree");
            var nodeList = zTree.getNodesByParamFuzzy("name", txtObj.value);
            //将找到的nodelist节点更新至Ztree内
            $.fn.zTree.init($("#tree"), setting, nodeList);
        } else {
            //隐藏树
        	deptZtrees();                
        }         
}
//查询资产类别
function sourceZtrees() {
    $.fn.zTree.init($("#sourceTree"), setting2, zNodesSource);
}
function source(txtObj){
	if (txtObj.value.length > 0) {
		sourceZtrees();
           var zTree = $.fn.zTree.getZTreeObj("sourceTree");
           var nodeList = zTree.getNodesByParamFuzzy("name", txtObj.value);
           //将找到的nodelist节点更新至Ztree内
           $.fn.zTree.init($("#sourceTree"), setting2, nodeList);
       } else {
           //隐藏树
    	  sourceZtrees();                
       }    
}
//事件查询列表
function kbEvent() {
	$('#page2').empty();
	$('#page2')
			.bPage(
					{
						url : getPath() + "/selectKbEventAll.do",
						// 开启异步处理模式
						asyncLoad : true,
						// 关闭服务端页面模式
						serverSidePage : false,
						// 数据自定义填充
						render : function(data) {
							$("#DeptTbody").empty();
							var dealEnd = data.selectDealEnd;
							var pageNumber = data.pageNumber;
							var pageSize = data.pageSize;
							var totalPage = data.totalPage;
							var totalRow = data.totalRow;
							$("#pagenumber").val(pageNumber);
							$("#pagesize").val(pageSize);
							$("#totalpage").val(totalPage);
							$("#totalrow").val(totalRow);
							var datas = data.selectKbEventAll;
							for (var i = 0; i < datas.length; i++) {
								var id = datas[i].id;
								var eventCode = datas[i].event_code;
								eventCode = isnull(eventCode);
								var eventName = datas[i].event_name;
								eventName = isnull(eventName);
								var eventLevel = datas[i].event_level;
								eventLevel = isnull(eventLevel);
								if (eventLevel == 1) {
									eventLevel = "一级";
								} else if (eventLevel == 2) {
									eventLevel = "二级";
								} else if (eventLevel == 3) {
									eventLevel = "三级";
								} else if (eventLevel == 4) {
									eventLevel = "四级";
								}
								var eventCauses = datas[i].event_causes;
								eventCauses = isnull(eventCauses);
								var eventDesc = datas[i].event_desc;
								eventDesc = isnull(eventDesc);
								var eventResolvent = datas[i].event_resolvent;
								eventResolvent = isnull(eventResolvent);
								tr = "<tr>" + "<td style='display:none;'>"
										+ id
										+ "</td>"
										+ "<td>"
										+ eventCode
										+ "</td>"
										+ "<td>"
										+ eventName
										+ "</td>"
										+ "<td>"
										+ eventLevel
										+ "</td>"
										+ "<td>"
										+ eventCauses
										+ "</td>"
										+ "<td>"
										+ eventDesc
										+ "</td>"
										+ "<td>"
										+ eventResolvent
										+ "</td>"
										+ "<td>"
										+ "<a href='javascript:kbDelete("
										+ id
										+ ")' class=\"btn btn-primary  btn_new_small btn_red\">删除</a>"
										+ "<a href='javascript:kbExamine("
										+ id
										+ ")' class=\"btn btn-primary  btn_new_small btn_yellow\">修改</a>"
										+ "<a href='javascript:kbSeach("
										+ id
										+ ")' class=\"btn btn-primary  btn_new_small btn_blue\">查看</a></td></tr>";
								$("#DeptTbody").append(tr);
							}
						
							var relTable=$('.relTable');
							
							var RH=$('.mRight').height()-165;
				
					        var relTab=$('.relTable .table');
					        var thTab=$('.fixTable .table tr th');
					        var tdTab=$('.relTable .table tr:eq(0) td');
					        var len=thTab.length;
					     
					        relTable.height(RH);
					        var relTabHeight=relTab.height();
					       
					        for(var thI=0;thI<(len-1);thI++){
				                  tdTab.eq(thI+1).width(thTab.eq(thI).width()) ;
				  
				              }
					        if(RH<relTabHeight){
					        	  for(var thI=0;thI<(len-1);thI++){
					                  tdTab.eq(thI+1).width(thTab.eq(thI).width()) ;
					  
					              }
					        	  

					            relTable.addClass('relAuto')
					        }else{
					            relTable.removeClass('relAuto')
					        }
							$("#pages").val("");
						},
						params : function() {
							var kbEvent = $("#kbEvent").val();
							var pages = $("#pages").val();
							var pagenumber = $("#pagenumber").val();
							var pagesize = $("#pagesize").val();
							var totalpage = $("#totalpage").val();
							var totalrow = $("#totalrow").val();
							return {
								"kbEvent" : kbEvent,
								"pages":pages,
					        	"pagenumber":pagenumber,
					        	"pagesize":pagesize,
					        	"totalpage":totalpage,
					        	"totalrow":totalrow
							};
						}
					});
}
function kbAdd() {
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/insertKbEvent.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}

function kbDelete(id) {
	if (!confirm("是否删除该条知识")) {
		return;
	}
	$.ajax({
		type : "post",
		url : getPath() + "/deleteKbEvent.do",
		data : {
			"id" : id
		},
		success : function(data) {
			if (data == 1) {
				kbEvent();
			}
		}
	})
}
function kbExamine(id) {
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/updateKbEventById.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
function updateKbEvent() {
	$.each($(".error_null"),function(){
		if($(this).val()==""){
			var error=$(this).attr("data-message");
			$(this).val(error);
			$(this).addClass("error");
		}
	})
	$("#updateKbEvent").click(function() {
		if($('.error_null').hasClass('error')||$('.error_null').val()=="" ){
			$.each($(".error_null"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;
		}
		var kbId = $("#kbId").val();
		var eventCode = $("#eventCode").val();
		var eventName = $("#eventName").val();
		var assetType = $("#assetType").val();
		var eventLevel = $("#eventLevel").val();
		var eventCauses = $("#eventCauses").val();
		var eventDesc = $("#eventDesc").val();
		var eventResolvent = $("#eventResolvent").val();
		$.ajax({
			type : "post",
			url : getPath() + "/updateKbEventInfo.do",
			data : {
				"kbId" : kbId,
				"eventCode" : eventCode,
				"eventName" : eventName,
				"assetType" : assetType,
				"eventLevel" : eventLevel,
				"eventCauses" : eventCauses,
				"eventDesc" : eventDesc,
				"eventResolvent" : eventResolvent
			},
			success : function(data) {
				if (data == 1) {
					location.href = getPath() + "/kbEvent.do?cid=2";
				}
			}
		})
	})
}
function kbSeach(id) {
	$.ajax({
		type : "post",
		url : getPath() + "/selectKbEventInfoById.do",
		data : {
			"id" : id,
		},
		success : function(data) {
			var right = $(".check-content-right").length;
			var eventCode = data.eventCode;
			eventCode = isnull(eventCode);
			var eventName = data.eventName;
			eventName = isnull(eventName);
			var eventLevel = data.eventLevel;
			eventLevel = isnull(eventLevel);
			var eventCauses = data.eventCauses;
			eventCauses = isnull(eventCauses);
			var eventDesc = data.eventDesc;
			eventDesc = isnull(eventDesc);
			var eventResolvent = data.eventResolvent;
			eventResolvent = isnull(eventResolvent);
			if (eventLevel == 1) {
				eventLevel = "一级";
			}
			if (eventLevel == 2) {
				eventLevel = "二级";
			}
			if (eventLevel == 3) {
				eventLevel = "三级";
			}
			if (eventLevel == 4) {
				eventLevel = "四级";
			}
			var arr = [ eventCode, eventName,eventLevel,
					eventCauses, eventDesc, eventResolvent ];
			for (i = 0; i < right; i++) {
				$(".check-content-right").eq(i).text(arr[i]);
			}
		}
	})
	$(".check-win").show();
}
function closeWin(){
	$(".check-win").hide();
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/kbEvent.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}