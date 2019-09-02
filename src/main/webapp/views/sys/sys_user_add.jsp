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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_user_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
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
			.mOldRbot .mRwell .form-horizontal .mRwell-container .need_group{ width:50%; float:left;height:34px; line-height:34px;}
	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">单位信息</a></li>
				    <li class="active">增加人员</li>
				</ol>
			</div>
		
			<div class="mRbot mNewRbot mOldRbot">
				<div class="mRwell">
					<div class="mRwell-tit">添加人员信息</div>
					<form role="form" class="form-horizontal" id="form1">
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<input type="hidden" name="token" value="${token}" />
						<div class="mRwell-con-tit">个人信息</div>
						<div class="mRwell-container">
															
								<div class="form-group need_group">
									<label for="code" class="col-sm-3">编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="code" name="code" class="form-control" data-message="编号不得为空！"></div>
								</div>
							
									<div class="form-group need_group">
									<label for="userName" class="col-sm-3">姓名<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="userName" name="userName" class="form-control" data-message="姓名不得为空！"></div>
								</div>
								<div class="form-group need_group">
									<label for="code" class="col-sm-3">性别<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-4">
									     <input type="radio" name="userSex" id="userSex" value="1" checked>男						
									</div>
									<div class="col-sm-4">									   
									     <input type="radio" name="userSex" id="userSex" value="2">女									    
									</div>
								</div>
															
								<div class="form-group need_group">
									<label for="tel" class="col-sm-3">手机<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="userCall" name="userCall" class="form-control telClass" data-message="手机不得为空！"  onkeyup="value=value.replace(/[^\d]/g,'')" onblur="checkPhone()"></div>
								</div>
							
							
								<div class="form-group need_group">
									<label for="code" class="col-sm-3">身份证号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="userUuid" name="userUuid" class="form-control" data-message="身份证号不得为空！" onblur="checkUuid()"  ></div>
								</div>
								<div class="form-group need_group">
									<label for="mail" class="col-sm-3">邮箱<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-8"><input type="text" id="userMail" name="userMail" class="form-control" data-message="邮箱不得为空！"></div>
								</div>	
							
						</div>
						<div class="mRwell-con-tit">公司信息</div>
						<div class="mRwell-container">							
							<div class="form-group need_group">
								<label for="name" class="col-sm-3">部门名称：</label>
								<div class="row_model col-sm-8" style="position: relative">
						    <span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="idDept" id="idDept" value="${userOne.idDept }" /> 
							    <input id="searchTree" type="text" onChange="deptChange()" value="${userOne.extend2 }" class="span2 form-control" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
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
							<div class="form-group need_group">
								<label for="sub_name" class="col-sm-3">岗位：</label>
								<div class="col-sm-8" style="position:relative;">
									<input type="text" class="serInp form-control" readonly style="background:#fff;" placeholder="请选择岗位" />
									<select id="userPost" name="userPost" class="form-control" style="display:none;" data-message="部门不得为空！" size="5">
										<option value="">请选择岗位</option>
										<c:forEach var = "sfm" items = "${postAll}">
										 <option value="${sfm.id }">${sfm.name }</option>
										</c:forEach>
									</select>
								</div>
							</div>							
							<div class="form-group need_group">
								<label for="tel" class="col-sm-3">电话：</label>
								<div class="col-sm-8">
									<input type="hidden" id="userTel" name="userTel" class="form-control">
									<input type="text" id="telUser" name="telUser" class="form-control telClass"  disabled="disabled">
								</div>
							</div>		
						
						</div>			
						<div class="btnlist">
							<a class="btn btn-primary btn-sm" id="addConfirm">确定</a>
							<a class="btn btn-sm" onclick="cancel()">取消</a>
						</div>
					</form>
					
				</div>				 
			</div>

		</div>
</body>
<script type="text/javascript">
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
<script type="text/javascript">
	$("#userPost").width($(".serInp").width())
	$(".serInp").focus(function(){
		$("#userPost").show();
		$("#userPost").click();
	})
	$("#userPost option").click(function(){
		$("#userPost ").hide();
		$(".serInp").val($("#userPost option:selected").text());
	})
	$("#userPost").blur(function(){
		$(this).hide();		
	})
</script>
</html>