<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
		<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_assets_list.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>	
	.QK_search{ width:100%; height:30px; position:relative;}
	#searchTree{ height:20%; border:1px solid #ccc; line-height:20px; margin:0; width:100%;}
	.selDiv{ width:200px; height:30px; position:relative;}
	.selDiv #selInp{ width:100%; height:30px;  border-radius:4px; border:1px solid #aaa; }
	.selDiv #sellist{display:none; position:absolute; left:0; top:32px; z-index:10000; border-radius:0 0 5px 5px;}
	#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:99;}
	.tree_list{height:154px !important; overflow:auto;}
	#searchMenu{margin-bottom:5px; position:relative;}
	.accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
	.error{border-color:red !important;}
	.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
	#one{width:200px; height:180px; float:left}
	#two{width:50px; height:180px; float:left}
	#three{width:200px; height:180px; float:left}
	#bPageList{ display:none;}	
	#bPageInfo a{ border-left-width:1px; border-radius:4px 0 0 4px;}
		.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page	.layui-layer-title{color:#fff;background:#3b85c7; }
		
	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">管理体系</a></li>
				    <li class="active">资产管理</li>
				</ol>
			</div>

			<div class="mQuery">
				<form role="form" id="forms">
					<input type="hidden" id="pages" name="pages" value="${pages }">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="inp_1 form-group">
						<label for="comNum">资产编号：</label>
						<input type="text" id="code" name="code" value="" class="form-control" placeholder="请输入资产编号">
					</div>
					<div class="inp_2 form-group">
						<label for="comName">资产名称：</label>
						<input type="text" id="name" name="name" value="" class="form-control" placeholder="请输入资产名称">
					</div>
					<div class="inp_3 form-group">
						<label for="comAbbre">资产型号：</label>
						<input type="text" id="asModel" name="asModel" value="" class="form-control" placeholder="请输入资产型号">
					</div>
					<div class="inp_3 col-sm-3 form-group">
						<label for="comAbbre" style="width:70px;">部门名称：</label>
						<div class="row_model " style="position: relative;width:120px;float:left; ">
							<span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="idDept" id="idDept" value="${userOne.idDept }" /> 
							    <input id="searchTree" type="text" style="width: 100%;margin-top:-3px; height: 26px;" value="${userOne.extend2 }" class="span2 form-control" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
							</div> 
							<select id="eventDept" class="qkby_sel" style=" position:absolute;top:34px; left:0; width:100%; border-radius: 5px; display: none; height:100px;" name="eventUser" onChange="userChange()" size="5" >
							</select>
							<div id="sidebar">
								<div class="toggleCollapse">
							       	<div id="treeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
							          		<div class="accordionContent" style="background-color: #ffffff">
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
					<a class="btn btn-primary" id="seach"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</form>								
			</div>
			
			<div class="btnout" style="width:600px;">
                <a onclick="add()" class="btn btn-primary btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">新增</a>
                <a onclick="update()" class="btn btn-primary btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
                <a onclick="deleteId()" class="btn btn-primary btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-del.png">删除</a>
                <a onclick="examine()" class="btn btn-primary btn-sm " id="examine"><img alt="" src="<%=request.getContextPath()%>/images/btn-sear.png">查看</a>
                <a onclick="toLead()" class="btn btn-primary btn-sm " id=""><img alt="" src="<%=request.getContextPath()%>/images/btn-input.png">导入</a>
                <a onclick="downLoadLead()" class="btn btn-primary btn-sm " id=""><img alt="" src="<%=request.getContextPath()%>/images/btn-download.png">模板下载</a>
            </div>

			<div class="mRbot" style="height: 68%;">				
                <div id="data_panel"></div>
                <table class="table table-bordered text-center" id="text">
					<thead>
						<tr>
							<th width="3%"><input type="checkbox" name="allSelect"></th>
							<th>资产编号</th>
							<th>资产名称</th>
							<th>资产型号</th>
							<th>资产类别</th>
							<th>生产厂家</th>
							<th>启用日期</th>
							<th>使用部门</th>
							<th>资产状态</th>
						</tr>						
					</thead>
					<tbody id="myTable">					
					</tbody>					
				</table>
			</div>
			
			<div class="mRpage" id="page2"></div>
		</div>

	<!-- 查看弹出框 -->
		<div class="check-win">
			<table class="table table-bordered text-center" style="margin-top:10px; width:90%; margin-left:auto; margin-right:auto;">
				<tbody>
					<tr>
						<td style="width:100px;">资产编号:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >资产名称:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >资产型号:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >资产类别:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >生产厂家:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >启用日期:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >使用部门:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >资产状态:</td>
						<td class="check-content-right"></td>
					</tr>
					
				</tbody>
				
			</table>
			
		</div>
		<!-- 背景变暗 -->
		<div class="mask"></div>
				<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	    <script type="text/javascript">
	    $(function(){
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
								type += "<li><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryType("+deptThree[k].id+",\'"+three.name+"\')\">";
								type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a>";
								$.each(deptFour,function(m,four){
									if (deptThree[k].id == deptFour[m].parentId) {
										type += "<li class=\"deptThree\"><a autobypy="+four.extend2 +"href=\"#\" onclick=\"queryType("+deptFour[m].id+",\'"+four.name+"\')\">";
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
		$("#sidebar").hide();
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
	    </script>
		<script>
		function GetUrlStr(name){
			  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			   var r = window.location.search.substr(1).match(reg);
			   if(r!=null)
			   return unescape(r[2]);
			   return null;
		}
		function storage(npm){
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
	            content: ['<p style="color:#333"> 提示</p><p style="color:#666">　'+npm+'</p>', '#eveBox']
	        });
		}
		if(GetUrlStr('cid')=='1'){
			storage('添加成功');
			
		}
		if(GetUrlStr('cid')=='2'){
			storage('修改成功');
			
		}
		function fatherFun(){
			getPathList();
			storage('导入成功');
			
		}
	

	</script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
    <script>
    $(function(){
    	$("#top .collapse .nav li").eq(0).removeClass("active");
    	$("#top .collapse .nav li").eq(3).addClass("active");
    	$("#main .mLeft .list-group li").eq(0).removeClass("active");
    	$("#main .mLeft .list-group li").eq(5).addClass("active");
    	$(".openWin").hide();
    	$(".openWinShow").hide();
    	getPathList();
    	$("#seach").click(function(){
    		$('#myTable').empty();
    		getPathList();
    	})
    	getExamine();
    	$("#cancle").click({
    		
    	})
    })
    </script>
    <script>

    </script>
</body>
</html>