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
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/js/proj_config_template.js"></script>
<style>
body{ overflow-y:auto;}
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
.tempBtn{ width:100%; height:40px;  float:left; border-bottom:2px solid #ccc;}
.tempBtn a{  display:inline-block; padding:6px 12px;line-height:1; margin:5px 10px;border-radius:3px;}
.tempList{ width:100%;}
.tempProList ul ,.tempTaskList ul{width:220px; height:360px; float:left;  overflow-y:auto;overflow-x:hidden;}
.tempList .tempProList,.tempList .tempTaskList{ margin-left:10px;width:230px; height:400px; float:left;  border-right :2px #ccc solid;}
.tempList .tempProList li,.tempList .tempTaskList li { height:28px;width:220px;  line-height:28px; text-indent:1em; float:left;}
.tempList .tempProList li .hover,.tempList .tempTaskList li .hover{background:#3b85c7;color:#fff;} 
.tempList .tempProList li:hover a,.tempList .tempTaskList li:hover a{background:#3b85c7;color:#fff; } 
.tempList .tempProList li a,.tempList .tempTaskList li a{ color:#666; display:block ; transition: all 0.2s;overflow: hidden; text-overflow: ellipsis;white-space: nowrap; }
.tempMes {min-height:460px; float:left; margin-left:10px;}

.tempMes .pList{ width:100%;height:40px; line-height:40px; overflow:hidden; float:left; }
.tempMes label{ width:20%; text-align:right; display:inline-block; float:left;}
.tempMes .pList input{ height:30px;float:left; width:60%; margin-top:5px; border:1px solid #ccc; border-radius:3px;}
.tempMes .pList textarea{ height:120px; line-height:18px;float:left; width:60%; margin-top:5px; border:1px solid #ccc; border-radius:3px;}
.tit{ width:60%; height:24px; line-heigth:24px; border-bottom:3px solid #3b85c7; float:left;margin-bottom:10px; }
.rankList{ width: 60px; height:24px; line-height:24px;float:left; display:block; text-align:center }
.rankList .fa {margin:0 3px; color:#999;}
.save{ flaot:right;  display:inline-block; padding:6px 12px;line-height:1; margin:5px 10px;border-radius:3px;}
.shadow{ width:478px; height:404px; position:absolute; background:#000; opacity:0.1;top:74px; left:0;z-index:99; display:none;}
.txtMes{width:100%; float:left; }
.txtMes p{ word-break: break-all;  width:60%;float:left; }
.txtMes label{ width:20%; text-align:right; display:inline-block; float:left;}

</style> 

</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="#">管理体系</a></li>
			    <li><a href="#">项目配置</a></li>
			    <li class="active">项目模板</li>
			</ol>
		</div>
		<div class="tempBtn">
			<a href="#" class="btn_blue" onclick="cancelToProjConfig()">返回</a>
			<a href="#" class="btn_green" onclick="addTemplate()">新建模板</a>
			<a href="#" class="btn_red" onclick="deleteTemplate()">删除模板</a>
			<a href="#" class="btn_yellow" onclick = "updateTemplate()">模板编辑</a>
			<a href="#" class="btn_purple" onclick = "showTemplate()">流程查看</a>	
		</div>
		<div class="tempList">
		<div class="shadow"></div>
		<div class="tempProList">
			<input type="hidden" id="templateId" name="templateId"/>
			<p class="tit">模版名称</p>
			<ul class="" id="projTemplateList">
			</ul>
		</div>
		<div class="tempTaskList">
			<p class="tit">阶段任务</p>
			<ul class="" id="TaskList">
			</ul>
		</div>
			<form  id="form1">
				
				<div class="tempMes">
					<input type="hidden" id="phaseTaskId" name="phaseTaskId"/>
					<p class="tit">任务信息</p><a href="#" id="updateButn" class="save btn_blue" onclick="showSaveButn()">编辑</a>
											<a href="#" id="saveButn" class="save btn_green" onclick="savePhaseTask()">保存</a>
											<a href="#" id="cancelButn" class="save btn_red" onclick="cancelSave()">取消</a>
					<div class="pList" style="margin-top:30px;">
		 				<label>阶段标题：</label>
		 				<input type="hidden" id="phaseTitle" name="phaseTitle" value=""/>
		 				<input type="text" id="taskName" name="taskName" disabled="disabled" style="background:#fff;border: none;">
		 			</div>			
	 				<div class="pList" style="height:130px;">
		 				<label style="line-height:28px;">阶段内容：</label>
		 				<textarea id="phaseRemark" name="phaseRemark" disabled="disabled" style="background:#fff;"></textarea>
		 			</div>
		 			<div class="pList" style="height:130px;">
		 				<label style="line-height:28px;">阶段成果/文档：</label>
		 				<textarea id="phaseShow" name="phaseShow" disabled="disabled" style="background:#fff;"></textarea>
		 			</div>
		 		<!-- <div class="txtMes">
		 					<label>阶段标题：</label>
		 					<p id="txtMes1">fadf dfasfasdnlkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkfnslkanflaknfklsanfklasnfklanfklan</p>
		 			</div>
		 			<div class="txtMes">
		 					<label>阶段内容：</label>
		 					<p id="txtMes2">fadf dfasfasdnlkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkfnslkanflaknfklsanfklasnfklanfklan</p>
		 			</div>
		 			<div class="txtMes">
		 					<label>阶段成果/文档：</label>
		 					<p id="txtMes3">fadf dfasfasdnlkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkfnslkanflaknfklsanfklasnfklanfklan</p>
		 					
		 			</div> -->	
				</div>
			</form>
		</div>
	</div>
<script type="text/javascript">
	$(function(){
		getTemplateName();
		$(".tempMes").width($("body").width()-530)
		$("#saveButn").hide();
		$("#cancelButn").hide();
	})
</script>


  
</body>
</html>