<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=request.getContextPath()%>/js/event_agency_list.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style type="text/css">
body{font-size:14px;}
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.author_jump{width:460px;height: 150px;z-index: 2000;border-radius:2px;position: absolute;top: 95px;left: 25%;background-color: #f8f8f8;box-shadow:3px 3px 20px 1px rgba(0,0,0,.3);display: none;}
	.author_jump .author_tit{ padding:0 20px; height:42px; border-radius:2px 2px 0 0 ;line-height:42px;color:#fff;background:#3b85c7;}
	.author_jump .author_tit span{ display:inline-block; float:right;  height:42px; line-height:42px;cursor: pointer;font-size:32px; }
	.author_jump .form-group{ margin-top:10px;height:40px;}
	.author_jump .form-group label{ height:34px; line-height:34px;}
	.btn_list{ float: right;}
    .btn_list .btn_bass{ height: 26px; line-height: 26px; font-size: 12px; margin:0 8px; padding: 0 17px; display: inline-block;color: #fff;border-radius: 5px; }
    .btn_list .btn_bass:hover{ cursor: pointer; text-decoration: none;}
     .btn_list .btn_add{background: #3b85c7; width:58px;padding:0;outline:0;border:0px;}
    .btn_close{ background: #e2614e; }
		#role_layer{width:440px; height:160px; margin:0 auto;}
		#role_layer .role_txt{ height:40px; width:100%; margin-top:10px; }
		#role_layer .role_txt label{ width:30%; height:40px;float:left; line-height:40px;}
		#role_layer .role_txt  .sys_role_select{width:60%; height:40px; float:left; line-height:40px;}
		#role_layer .btnList{margin-top:100px; width:100%; height:40px; text-align:right;}
		#role_layer .btnList .btn{ margin-right:10%; padding:3px 8px; display:inline-block; border:none; }
		#role_layer .btnList .btn_red{ color:#fff;}
		.btn-group .open .selectpicker{max-height:100px !important;overflow-x:hidden !important; overflow-y:auto !important;}
		.mQuery-2 .form-group  .form-control{ padding:0 12px;}
		.QK_arrow{ border-top:6px #333 solid; border-bottom:2px solid #fff;border-left:3px solid #fff;border-right:3px solid #fff; display:block;width:4px;height:12px; position:absolute; right:8px; top:12px; z-index:9999; }
		.QK_search{ width:100%; height:30px; position:relative;}
#searchTree{  border:1px solid #ccc; line-height:20px; margin:0;}

#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;}
.tree_list{height:154px !important; overflow:auto;background:#fff;}
#searchMenu{margin-bottom:5px; position:relative;}
 .accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
.error{border-color:red !important;}
.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
#one{width:200px; height:180px; float:left}
#two{width:50px; height:180px; float:left}
#three{width:200px; height:180px; float:left}
</style>
</head>
<body>
	<div class="mRight">
		<%-- <div class="rightBox">
			<img alt="pos" src="<%=request.getContextPath()%>/images/phone.png"
				>
		</div> --%>
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="javascript:;"><img alt="pos"
						src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="javascript:;">服务管理</a></li>
				<li class="active">待办事件</li>
			</ol>
		</div>

		<div class="mQuery-2">
			<form action="" role="form" class="form-horizontal" method="post">
				<input type="hidden" id="pages" name="pages" value="${pages }">
                <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
                <input type="hidden" id="token" name="token" value="${token}" />
				<div class="form-group col-sm-4">
					<label for="dataStart" class="col-sm-3">日期：</label>
					<div class="col-sm-4">
						<input type="text" id="startTime" name="startTime" value="${startTime }"
							class="form-control" placeholder="请选择开始时间" />
					</div>
					<div class="col-sm-1 text-center">至</div>
					<div class="col-sm-4">
						<input type="text" id="endTime" name="endTime" value="${endTime }"
							class="form-control" placeholder="请选择结束时间" />
					</div>
				</div>
				<div class="form-group col-sm-2">
					<label for="file_code" class="col-sm-5">联系方式：</label>
					<div class="col-sm-7">
						<input type="text" id="contact" name="contact" value="${contact }" class="form-control" placeholder="请输入联系方式" />
					</div>
				</div>
				<div class="form-group col-sm-2" style="margin-left: -20px">
					<label for="file_code" class="col-sm-4">部门：</label>
					<div class="col-sm-7" style="position: relative">
						<span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="idDept" id="idDept" value="${idDept }" /> 
							    <input id="searchTree" type="text" class="span2 form-control" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
							</div> 
							<div id="sidebar">
								<div class="toggleCollapse">
									
									           
							       	<div id="treeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
							          		<div class="accordionContent">
							             		<ul id="a" class="tree treeFolder ">
							               		<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openTree">部门信息</a>
							                			<ul id="bzmenus" class="bzmenus_sub"></ul>
							               		</li>
							             		</ul>
							           	</div>
							        	</div>
							     	</div>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group col-sm-2">
					<label for="file_code" class="col-sm-4">状态：</label>
					<div class="col-sm-8">
						<input type="hidden" value="${eventStatus }" id="eventStatus" name="eventStatus"/>
						<select id="status" name="status" class="form-control" onchange="changeStatus()">
							<option value="" <c:if test="${eventStatus=='' }">selected="selected"</c:if>>请选择事件状态</option>
							<option value="20" <c:if test="${eventStatus=='20' }">selected="selected"</c:if>>待受理</option>
							<option value="50" <c:if test="${eventStatus=='50' }">selected="selected"</c:if>>待处理</option>
						</select>
					</div>
				</div>

				<a class="btn btn-primary" id="button_search"><img
					src="<%=request.getContextPath()%>/images/search.png">搜索</a>


			</form>
		</div>
		 <div class="mRbot" style="top: 85px; height: 70%;">
			<div class="fixTable">
    <table class="table table-bordered table-hover table1">
        <thead>
        <tr>
            <th>事件编号</th>
            <th>标题</th>
            <th style="width:80px;">申报人</th>
            <th style="width:100px;">申报部门</th>
            <th>联系电话</th>
            <th style="width:170px;">申告时间</th>
            <th>申告描述</th>
            <th style="width:80px;">事件办理人</th>
            <th style="width:80px;">状态</th>
            <th>操作</th>
        </tr>
        </thead>
    </table>
</div>
<div class="relTable" style="height:90%; width:100%;">
    <table class="table table-bordered table-hover text-center table1">

        <tbody id="agencyTbody">

        </tbody>

    </table>
</div>
		</div>
		<div class="mRpage" id="agencyTable"></div>
	</div>
		<div class="author_jump">
		
		<p class="author_tit">增援 <span id="btn_outline">×</span></p>
		<input type="hidden" id="event_id" name="event_id"/>
		<div class="form-group">
			<label class="col-sm-3 text-right">增援人</label>
			<div class="col-sm-8">
				<select id="sys_role" name="sys_role" class="form-control selectpicker bla bla bli" multiple data-live-search="true"></select>
			</div>
		</div>
		<div class="btn_list">
			<input class="btn_bass btn_add" type="button" id="button_search_author" value="提交">
		
			<a class="btn_bass btn_close" id="button_cancel_author" style="margin-right:20px;">取消</a>		
		</div>
	</div>

	<div class="mask"></div>
	<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	
<script>

$(function(){
	$("#searchTree").keyup(function(){
		if ($("#searchTree").val() != "") {
			$("#searchTreeClose").show();
			removeLi();
		} else {
			$("#searchTreeClose").hide();
			removeLi();
		}
		$('ul.tree').colExpAll({
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

function queryType(id,name){
	$("#idDept").val(id);
	$("#searchTree").val(name);
	$("#treeDiv").hide();
}
function showTree(){
	$("#treeDiv").show();
	$('body').bind("mousedown", onBodyDown);
}
function onBodyDown(event) { 
	   if (!(event.target.id == "menuBtn" || event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length > 0)) {  
		$("#treeDiv").hide();
	   }  
	}//部门树
	
	
	
	$(function(){		
		/**
		 *添加申告图标
		 *控制页面跳转
		 *不控制显示与隐藏
		*/
		<%-- $(".rightBox").mouseover(function(){
			$(this).css("width","40px").css("cursor","pointer");
		}).mouseout(function(){
			$(this).css("width","");
		}).click(function(){
			window.location.href="<%=request.getContextPath()%>/toApplyAndAccept.do";
		}) --%>
		function GetUrlStr(name){
			  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			   var r = window.location.search.substr(1).match(reg);
			   if(r!=null)
			   return unescape(r[2]);
			   return null;
		}
		function layerTips(tipsTxt){
			layer.open({
	            type:4,
	        	time:2000,
	            tipsMore: true,
	            title: '提示',
	            tips:[2,"#eee"],
	            closeBtn:0,
	            area: ['200px', '70px'],
	            shade: 0,
	            maxmin: false,
	            offset: 'rb',
	            content: ['<p style="color:#333"> 提示</p><p style="color:#666">'+tipsTxt+'</p>', '#eveBox']
	        });
		}
		
		if(GetUrlStr('cid')=='1'){
			layerTips('受理成功')
		}
		if(GetUrlStr('cid')=='2'){
			layerTips('处理成功')
		}
		if(GetUrlStr('cid')=='3'){
			layerTips('转派成功')
		}
		if(GetUrlStr('cid')=='4'){
			layerTips('确认成功')
		}
		if(GetUrlStr('cid')=='5'){
			layerTips('确认成功')
		}
		if(GetUrlStr('cid')=='6'){
			layerTips('添加成功')
		}
	})
	</script>
	<script type="text/javascript">
		$(function() {
			$("#btn_outline").click(function(){
				$(".author_jump").fadeOut();
				$(".mask").fadeOut();
			})
			$("#startTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#endTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			findAgrncyList();
			$("#button_search").click(function(){
				findAgrncyList();
			})
		})
	</script>
</body>
</html>