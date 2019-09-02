<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/configure_menu.css"> --%>
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_user_list.js"></script>	
	<style type="text/css">
	.QK_search{ width:100%; height:30px; position:relative;}
	#searchTree{ height:25px; border:1px solid #ccc; line-height:20px; margin:0; width:200px;}
	
	#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;}
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
	.layui-layer-page .layui-layer-title { color:#fff;background:#3b85c7; }
	.author_jump{width:600px;height: 200px;z-index: 2000;border-radius:2px;position: absolute;top: 95px;left: 25%;background-color: #f8f8f8;box-shadow:3px 3px 20px 1px rgba(0,0,0,.3);display: none;}
	.author_jump .author_tit{ padding:0 20px; height:42px; border-radius:2px 2px 0 0 ;line-height:42px;color:#fff;background:#3b85c7;}
	.author_jump .author_tit span{ display:inline-block; float:right;  height:42px; line-height:42px;cursor: pointer;font-size:32px; }
	.author_jump .form-group{ margin-top:10px;height:40px;}
	.author_jump .form-group label{ height:34px; line-height:34px;}
	.btn_list{ float: right;}
    .btn_list .btn_bass{ height: 26px; line-height: 26px; font-size: 12px; margin:0 8px; padding: 0 17px; display: inline-block;color: #fff;border-radius: 5px; }
    .btn_list .btn_bass:hover{ cursor: pointer; text-decoration: none;}
    .btn_list .btn_add{background: #3b85c7; width:58px;padding:0;outline:0;border:0px;}
		
    .btn_close{ background: #e2614e; }
		.ztree {
		padding: 0;
		border: 2px solid #CDD6D5;
		width:160px; height:150px; overflow:auto;
		}
	</style>
</head>
	
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">管理体系</a></li>
				    <li class="active">人员信息</li>
				</ol>
			</div>

			<div class="mQuery">
				<form action="" role="form">
					<input type="hidden" id="pages" name="pages" value="${pages }">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="form-group">
						<label for="sperson">员工编号：</label>
						<input type="text" id="perNum" class="form-control" placeholder="请输入员工编号">
					</div>
					<div class="form-group">
						<label for="userName">姓名：</label>
						<input type="text" id="userName" class="form-control" placeholder="请输入人员姓名">
					</div>
					<div class="form-group">
						<label for="userDept" class="ml10">部门名称：</label>
						<div class="col-sm-2">
							<span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="userDept" id="userDept" value="${idDept }" /> 
							    <input id="searchTree" type="text" class="form-control" onblur="deptBlur()" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')">
							</div> 
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
					<div class="form-group">
						<label for="stitle">手机：</label>
						<input type="text" id="cal" class="form-control" placeholder="请输入手机号">
					</div>
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</form>								
			</div>
			
			<div class="btnout">
                <a class="btn btn-sm" id="addUser"><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">新增</a>
                <a class="btn btn-sm" id="updUser"><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
                <a class="btn btn-sm" id="delUser"><img alt="" src="<%=request.getContextPath()%>/images/btn-del.png">删除</a>
                <a class="btn btn-sm" id="checkUser"><img alt="" src="<%=request.getContextPath()%>/images/btn-sear.png">查看</a>
                <a class="btn btn-sm" id="authorUser"><img alt="" src="<%=request.getContextPath()%>/images/btn-grant.png">授权</a>
            </div>

			<div class="mRbot" style="height: 68%;">				
                <table class="table table-bordered table-hover text-center">
                	<thead>
                		<tr>
                			<th width="3%"><input type="checkbox" name="allSelect"></th>
	                		<th>员工编号</th>
	                		<th>姓名</th>
	                		<th>性别</th>
	                		<th>身份证号</th>
	                		<th>部门名称</th>
	                		<th>岗位</th>
	                		<th>电话</th>
	                		<th>手机</th>
	                		<th>邮箱</th>
                		</tr>                		
                	</thead>
                	<tbody id="tbody">
                	</tbody>
                </table>	
			</div>
			<div class="mRpage" id="page2"></div>
		</div>
	<div class="author_jump">
		<p class="author_tit">人员授权 <span id="btn_outline">×</span></p>
		<div class="form-group">
			<label class="col-sm-3 text-right">人员名称</label>
			<div class="col-sm-8">
				<input type="hidden" id="user_id" class="form-control" value="">
				<input type="text" id="user_name" class="form-control" disabled >					
			</div>				
		</div>
		<div class="form-group">
			<label class="col-sm-3 text-right">角色</label>
			<div class="col-sm-8">
				<select id="sys_role" name="sys_role" class="form-control selectpicker bla bla bli" multiple data-live-search="true"></select>
			</div>
		</div>
		<div class="btn_list">
			
		<input class="btn_bass btn_add" type="button" id="button_search_author" value="提交">
			
			<a class="btn_bass btn_close" id="button_cancel_author" style="margin-right:20px;">取消</a>		
		</div>
	</div>
	
	<!-- 查看弹出框 -->
	<div class="check-win">
		<table class="table table-bordered text-center" style="margin-top:10px; width:90%; margin-left:auto; margin-right:auto;">
			<tbody>
				<tr>
					<td style="width:100px;">人员编号:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr>
					<td >姓名:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr>
					<td >性别:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr>
					<td >身份证号:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr>
					<td >部门名称:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr style="display:none;">
					<td >公司名称:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr>
					<td >岗位:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr>
					<td >电话:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr>
					<td >手机:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr style="display:none;">
					<td >岗位:</td>
					<td class="check-content-right"></td>
				</tr>
				
				<tr>
					<td >邮箱:</td>
					<td class="check-content-right"></td>
				</tr>
				<tr style="display:none;">
				<td >岗位:</td>
					<td class="check-content-right"></td>
				</tr>
			</tbody>
			
		</table>
		
	</div>
	
		<div class="mask"></div>
		<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	
		<script>
	$(function(){
		$("#sidebar").hide();
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

	$("#searchTree").click(function (){
		$("#treeDiv").show();
		$('body').bind("mousedown", onBodyDown);
		$("#sidebar").show();
	})
	function onBodyDown(event) { 
		   if (!(event.target.id == "menuBtn" || event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length > 0)) {  
			$("#treeDiv").hide();
			$("#sidebar").hide();
		   }  
		}
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
	})
	
	function queryType(id,name){
		$("#userDept").val(id);
		$("#searchTree").val(name);
		$("#treeDiv").hide();
		$("#sidebar").hide();
	}
	function deptBlur(){
		var userDept = $("#searchTree").val();
		if(userDept == ""){
			$("#userDept").val("");
		}
	}
	</script>
	<script type="text/javascript">
		$(function(){
			$("#btn_outline").click(function(){
				$(".author_jump").fadeOut();
				$(".mask").fadeOut();
			})
			$("#sys_role").selectpicker({
			    'selectedText': '123'
			});
			$(".authorMenuOpen").hide();			
			//展示
			showUser();	
			$("#button_search").click(function(){
				showUser();	
			})
		})
	</script>
</body>
</html>