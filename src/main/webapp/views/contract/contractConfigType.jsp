<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<script src="<%=request.getContextPath()%>/js/contractConfigType.js"></script>
<style>
	.setType{ width:100%; height:100%;}
	.shadowBox{ width:230px;  height:310px; position:absolute; left:5px; top:5px; display:none;z-index:9999;}
	.setType .setTypeList{ width:220px; overflow-y:auto;overflow-x:hidden; height:300px; float:left; margin:5px; box-shadow:0 0 3px #aaa; }
	.setType .setTypeList li { height:28px;width:100%; line-height:28px; flaot:left; text-indent: 2em;}
	.setType .setTypeList li .hover{background:#3b85c7;color:#fff;} 
	.setType .setTypeList li:hover a{background:#3b85c7;color:#fff; } 
	.setType .setTypeList li a{ color:#666; display:block ; transition: all 0.2s; }
	.addtype{width:400px; height:300px; float:left;}
	.addtype .pList{ width:100%;height:40px; line-height:40px; overflow:hidden; float:left; }
	.addtype label{ width:130px; text-align:right; display:inline-block; float:left;}
	.addtype .pList input{ height:30px;float:left; width:240px; margin-top:5px; border:1px solid #ccc; border-radius:3px;}
	.addtype .pList textarea{ height:70px; line-height:18px;float:left; width:240px; margin-top:5px; border:1px solid #ccc; border-radius:3px;}

	.roleBox {width:100%; float:left;}
	.roleList{ width:360px; float:left;}
	.btnlist{ text-align:right; width:100%; height:30px; float:left; position:absolute; bottom:10px;} 
   	.btnlist a{  display:inline-block; padding:0 4px; height:24px; line-height:24px; margin:3px 10px;border-radius:3px;}
   	.btnRankClass{ text-align:right; position:absolute; top:0; right:0;}
	
</style>
</head>
<body>
			<input type="hidden" id="contractTypeId" value=""/>
 			<input type="hidden" id="updtypeName" value=""/>
 			<input type="hidden" id="updtypeRemark" value=""/>
 			<input type="hidden" id="updtypeColor" value=""/>
 			<input type="hidden" id="operType" value=""/><!-- 需要添加hover的类型 -->
 			<input type="hidden" id="hoverTypeId" value=""/><!-- 需要添加hover的类型 -->
 			
 			
	<form id="form1">
	
 	<div class="setType">
 		<div class="shadowBox"></div>
 		<ul class="setTypeList" id="typeUl">
 			
 		</ul>
 		<div class="addtype">
 		
 			<div class="pList" style="margin-top:30px;">
 				<label>类型名称：</label>
 				<input type="text" id="typeName" name="typeName" disabled="disabled" class="template needIn"  style="background:#fff;border:none;" value="">
 			</div>
			<div class="pList">
 				<label>颜色背景：</label>
 				<input type="color" style="width:70px; border:none;" value=""  disabled="disabled"  id="color" name="color" >
 			</div>
 			<div class="pList" style="height:80px;">
 				<label style="line-height:28px;">类型说明：</label>
 				<textarea id="typeDesc" name="typeDesc" class="template needIn" disabled="disabled" style="background:#fff;border:none;"></textarea>
 			</div>
 		
 			
 		</div>
	<div class="btnlist">
		<a href="#" id="newAdd" class="btn_blue" onclick="add()">新建</a>
		<a href="#" id="newSave" class="btn_green" onclick="save()">编辑</a>
		<!-- 添加的保存 -->
		<a href="#" id="addType" class="btn_blue" onclick="addType()">保存</a>
		<!-- 修改的保存 -->
		<a href="#" id="updateType" class="btn_green" onclick="saveType()">保存</a>
		<!-- 公共的取消 -->
		<a href="#" class="btn_red" id="cancel" onclick="cancel()">取消</a>
		<a href="#" id="delete" class="btn_purple" onclick="delType()">删除</a>
		<a href="#" id="close" class="btn_red" onclick="closeType()">关闭</a>
	</div>
 	</div>

</form>
<script type="text/javascript">
	$(function(){
		showTypeAll();
	
		$("#typeName").focus(function(){
			$(this).removeClass("error")
		})
		$("#addType").hide();
		$("#updateType").hide();
		$("#cancel").hide();
		$(".setTypeList li a").click(function(){

			$(".setTypeList li a").removeClass("hover")
			$("#typeName").removeClass("error")
			$(this).addClass("hover")
			
		})
	})
	
	$("#newAdd").click(function(){
		$("#hoverTypeId").val($(".setTypeList li .hover").attr("id"));
		$(".setTypeList li a").removeClass("hover")
	})
	function close(){
		location.href = getPath+"/toProjConfig.do";
	}
</script>


  
</body>
</html>