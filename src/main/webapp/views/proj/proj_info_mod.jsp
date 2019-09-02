<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prompt.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_info_mod.js"></script>
	<style type="text/css">
	body{overflow-y:auto;overflow-x:hidden; background:#f8f8f8;}
	.form-group{margin-bottom:0;}
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
		.tabItem{ opacity:0; height:100%; position:absolute;width:100%;z-index:-1;}
		.showBlock{ opacity:1;z-index:2; height:660px;}
		.mRbot .mRtabs{  border-bottom: 1px solid #ccc;  width: 100%; height: 32px;}
		.mRtabs li{ position: relative; float:left;  width:70px; text-align:center; }
		.mRtabs li span{ height: 26px;width:100%; display: block; line-height:26px; color:#666; cursor: pointer; margin-top:6px;}
		.mRtabs .nav_on span{    border-bottom: 1px solid #3eabe7; background:#3eabe7; color:#fff;}
		.mRbot .nav_on i{border:4px solid #3eabe7; margin-left:-4px; border-left-color:#fff;border-right-color:#fff;border-bottom-color:#fff;  width:4px;position:absolute ; left: 50%; bottom: -8px; height:4px; display: block;  box-sizing: border-box; }
		.btn_class{ height:40px; position:absolute; right:10px;top:-28px;}
		.btn_class a:hover{text-decoration: none;color:#fff; opacity:1;}
	   	.btn_base{ margin:0 3px;  font-size:14px; padding:2px 10px; float:left;   border-radius:3px; color:#fff; display:inline-block;}
	   	.btn_refresh{ background:#72b960; }
	   	.btn_add{ background: #1476d9;}
	   	.btn_delete{background:#ff0000;}
	   	.btn_edit{background:#feb94a;}
	   	.btn_lookfoward{background:#00c1de;}
	   	.btn_power{background:#ff6666;}
	   	.btn_signout{background:#d5d53f;}
	   	.btn_signin{background:#e28786;}
	   	.btn_download{background:#9065b4;}
	
		.drFileItem{ width:70px; margin:5px; float:left; height:95px; text-align:center; float:left;}
		.drFileItem i{ font-size:40px; color:#ccc; }
		.drFileItem .drFileName{ font-size:12px; color:#333; width:70px; line-height:14px; overflow:hidden;  height:28px; display:inline-block;}
		.drFileItem .drDel{height:17px; line-height:17px; width:35px; text-align:center;display:inline-block; }
	   	#dutyUserText{width:100%; height:100px ;background:#fff; cursor:pointer;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;}
		#dutyUserText{width:100%; height:100px ;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;background:#fff;}
		#dutyUserText a{overflow: hidden; text-overflow: ellipsis;white-space: nowrap;position:relative; display:inline-block; text-align:center; float:left; line-height:24px;height:24px; width:70px; margin:5px; background:#ff6900;color:#fff; }
		#dutyUserText a i{ display:none;}
		#dutyUserText a:hover{text-decoration: none; cursor: pointer;}
		#dutyUserText a span{ font-size:20px; position:absolute;right:0; top:-3px; display:block; height:20px; width:20px;color:fff; }
		.mRight .mRpage{
			bottom:54px;
		}
		#plan{float:left; width:60%; margin-top:15px;}
		#scrollBar {width: 80%;height: 10px;background-color: #ccc;-webkit-border-radius: 2em;-moz-border-radius: 2em;		border-radius: 2em;		cursor: pointer;		}		
		#scroll_Track {width: 0px;height: 10px;background-color: #ff4400;-webkit-border-radius: 2em;-moz-border-radius: 2em;	border-radius: 2em;}		
		#scroll_Thumb {height: 25px;width: 25px;background-color: #efefef;-webkit-border-radius: 2em;-moz-border-radius: 2em;border-radius: 2em;border: 1px solid #ccc;-webkit-box-shadow: 0px 0px 5px #ccc;-moz-box-shadow: 0px 0px 5px #ccc;box-shadow: 0px 0px 5px #ccc;position: absolute;margin-top: -18px;cursor: pointer;}
		
		#scroll_Thumb:hover {background-color: #ff4400;border: 1px solid #fff;}
		 	.schedule span{width:60px; display:inline-block; float:left;text-align:center; }
   	.schedule p{width:170px; height:5px;margin-top:-1px; background:#aaa;float:left;}
   	.schedule p b{ display:inline-block;height:5px; background:red;float:left;}
   	.btnClick{box-shadow:0 0 4px #aaa;  position:absolute;z-index:999;  right:0;top:0; background:#eee; text-align:center;  height:36px; width:200px; display:none;} 
   	.btnClick li{ float:left; width:60px;}
   	.btnClick li a{ display:inline-block; width:60px; font-size:12px;  height:36px; line-height:36px; color:#999;}                  
	   	.btnClick li a:hover{background:#00c1de; color:#fff;}
	
	 .titTxt span{ width: 14px; color: #777; text-indent: 0; height: 14px;margin:0 4px; opacity: 0;display: inline-block; font-size: 14px;  text-align: center;}
        .titTxt .showBtn{ border: 1px solid #ccc; border-radius: 2px; cursor: pointer;opacity: 1; }
        .hideBtn{ border: none}
        .titTxt{ text-align: left; position:relative;}
        .titTxt .iconClass{position:absolute; width:60px; height:14px; display:block;  right:5px;  top:9px}
        .titTxt i{ cursor:pointer; width:17px; font-size:14px;margin-right: 3px; float:right;  position:static;}
        .titTxt .flagHide{ display:none;}
        .titTxt .flagShow{ display:block;color:red;}
           .titTxt .yShowWarn{color:#d1a314;}
             .titTxt .yHideWarn{display:none;}
         .titTxt .rShowWarn{display:block;color:#da251c;}
           .titTxt .rHideWarn{display:none;}
        .level2,.level3,.level4,.level5,.level6,.level7,.level8,.level9,.level10{display: none;}
        .level2 .titTxt{text-indent: 2em;}
        .level3 .titTxt{text-indent: 4em;}
        .level4 .titTxt{text-indent: 6em;}
        .level5 .titTxt{text-indent: 8em;}
        .level6 .titTxt{text-indent: 10em;}
        .level7 .titTxt{text-indent: 12em;}
        .level8 .titTxt{text-indent: 14em;}
        .level9 .titTxt{text-indent: 16em;}
        .level10 .titTxt{text-indent: 28em;}	
		.projLeft{width:58%;  float:left; margin-left:1%; }
		.projMes{ width:100%;}
		.projMes input,.projMes select{ box-sizing:border-box; border-radius:3px; border:none; float:left;background:#f8f8f8; display:inline-block;}
		.titline{ width:90%; height:30px; line-height:30px; border-bottom:1px solid #aaa; float:left; }
		.pro_l_top{ width:100%; float:left; height:450px;}
		.proPlan,.pro_input_list{position:relative; width:100%; height:40px; line-height:40px; float:left;  }
		.proPlan label,.pro_input_list label{ margin:0; height:40px; line-height:40px; width:20%; float:left; display:inline-block; text-align:right;}
		.pro_txt_list{ height:100px;}
		.pro_txt_list textarea{ margin-top:10px; border:none; height:90px; width: 60%; padding: 0 3%;background:#f8f8f8; border-radius:5px; line-height:16px;}
		.proPlan{ height:70px;}
		.pro_input_list .proTxt{ width:60%; padding:0 3%; height:30px; margin:5px 0;}
		.pro_input_list select{ width:60%; padding :0 3%; height:30px; margin:5px 0;}
		.pro_input_list .timeInput{width:28%; padding:0 3%; height:30px; margin:5px 0;}
		.pro_big_list{ height:100px;}
		.pro_big_list .manBox{width:61% ; height:100px; float:left;}
		.pro_l_bot{ height:300px; width:100%; float:left;}
		.proRight{width:38%; float:left; margin-left:2%;}
		.downLine{color:blue;}
		.downLine:hover{text-decoration: underline;}
		#proExit,#proSave{display:none;}
		.blockEnter{width:80px; height:31px; background:#f8f8f8; position:absolute; right:13%; top:5px;}
		.anwser{ width:160px; line-height:18px;min-height:120px; box-shadow:0 0 4px #ccc; color:#666; position:absolute; left:50%; top:5px; z-index:999; background:#fff; display:none; }
		.anwser .titItem{ height:20px; line-height:20px;color:#fff;background:#3b85c7;  width:100%; text-indent: 1em; }
		.anwser .titItem i{ font-style:normal; float:right; margin-right:10px; display:block; height:20px; width:20px; cursor:pointer; }
		.QK_search{ width:100%; height:30px; position:relative;}
		#searchTree{ height:20%; border:1px solid #ffffff; line-height:20px; margin:0; width:25%;}
		#sidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:99;}
		.tree_list{height:154px !important; overflow:auto;}
		#searchMenu{margin-bottom:5px; position:relative;}
		.accordion .accordionContent .tree .tree_list .folder_expandable{ display:none;}
		.error{border-color:red !important;}
		.accordion .accordionContent  .tree_list .folder_collapsable,#bzmenus  .typeOne .folder_expandable,.accordion .accordionContent .tree .file{display:none;}
	 	.QK_arrow{ border-top:6px #333 solid; border-bottom:2px solid #fff;border-left:3px solid #fff;border-right:3px solid #fff; display:block;position:absolute;right:9px; top:12px; z-index:98; }
		#one{width:200px; height:180px; float:left}
		#two{width:50px; height:180px; float:left}
		#three{width:200px; height:180px; float:left}
	</style>
</head>
<body>

	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="#">管理体系</a></li>
			    <li class="active">我的项目</li>
			</ol>
		</div>
		<div class="mRbot mNewRbot">
			<ul class="mRtabs"  >
				<li class="nav_on" id="overHide"><span>项目信息</span></li>
				<li id="overShow"> <span>任务列表</span></li>
			</ul>
			<div id="onOverShow" style="position:absolute; display:none; left:85px;top:34px; z-index:10;width:60px; height:34px;"></div>
		
			<div class="tabItem showBlock">
				<div class="btn_class ">
					<a href="javascript:cancel();" id="back" class="btn_base btn_red"  >返回</a>
					<a href="javascript:proEdit();" id="proEdit" class="btn_base btn_add"  >编辑</a>
					<a href="javascript:updateSave();" id="proSave" class="btn_base btn_refresh" >保存</a><!-- 默认的是编辑后的保存 -->
					<a href="javascript:proExit();" id="proExit" class="btn_base btn_power" >取消</a>
				</div>
				<div class="projMes">
					<form role="form" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">						
						<div class="projLeft">
							<div class="pro_l_top">
								<input type="hidden" id="pages" name="pages" value="${pages }">
								<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
								<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
								<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
								<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
								<input type="hidden" id="id" name="id" value="${proj.id }">
								<input type="hidden" id="projSpeed" name="projSpeed" value="${proj.projSpeed }">
								<input type="hidden" id="status" name="status" value="${status }"/>
								<input type="hidden" id="projCode" name="projCode" value="${proj.projCode }"/>
								<input type="hidden" id="paramProjName" name="paramProjName" value="${paramProjName }">
								<input type="hidden" id="paramProjType" name="paramProjType" value="${paramProjType }">
								<input type="hidden" id="projControl" name="projControl" value="${projControl }">
								<input type="hidden" id="pStatus" name="pStatus" value="${pStatus }">
								<input type="hidden" id="projStatus" name="projStatus" value="${projStatus }">
								<input type="hidden" id="vs" name="vs" value="${proj.vs }">
								<input type="hidden" name="token" value="${token}" />
								<p class="titline">基本信息</p>
								<input type="hidden" id="state" name="state" value="${state }"/>						
								<input type="hidden" name="roleId" value="${roleOne.id }"/>						
								
								<div class="pro_input_list">
									<label>项目名称<i style="color:red;font-size:16px;">*</i>：</label>
									<input type="text" id="projName" name="projName" value="${proj.projName }" class="proTxt proEditIn needIn" data-message="项目名称不得为空！">
								</div>
								<div class="pro_input_list" id="projTemplateDiv" style="display: none;">
									<label>项目模板<i style="color:red;font-size:16px;">*</i>：</label>
									<select id="projTemplate" name="projTemplate" class="proEditIn" style="width:25%;">
										<option value="">不选择模板</option>
										<c:forEach items="${projTemplateList }" var="projTemplate">
											<option value="${projTemplate.id }">${projTemplate.templateName }</option>
										</c:forEach>
									</select>
									<i class="question fa fa-question-circle-o" style="font-size:16px; color:#feb94a; cursor:pointer; margin-left:20px;"  class="fa fa-question-circle-o" id="downProjTemplate"></i>
								</div>
								<div class="pro_input_list">
									<label>项目类型<i style="color:red;font-size:16px;">*</i>：</label>
									<select id="projType" name="projType" class="proEditIn"  onchange="changeTypeRemark()" style="width:25%;">
										<c:forEach items="${projTypeList }" var="projType">
											<option value="${projType.id }" <c:if test="${proj.projType == projType.id}">selected</c:if>>${projType.name }</option>
										</c:forEach>
									</select>
									<i class="question fa fa-question-circle-o" style="font-size:16px; color:#feb94a; cursor:pointer; margin-left:20px;"  class="fa fa-question-circle-o"></i>
									<div class="anwser">
										<p class="titItem">项目类型解释 <i class="anwClose">×</i><p>
										<p class="txt" id="typeAlert" style="word-wrap:break-word; word-break:break-all;  width:100%">${proTypeRemark }</p>
									</div>
									<div class="blockEnter" style="width:50%;"></div>							
								</div>
								<div class="pro_input_list">
								<div class="blockEnter" style="width:50%;"></div>	
									<label>所属部门：</label>
									<div class="row_model" style="position: relative; width:25%;float:left;">
									   
										<div class="QK_search">
										<div id="searchMenu" class="" >
										 <span class="QK_arrow"></span>
											<input type="hidden" name="idDept" id="idDept" value="${proj.idDept }"/> 
										    <input id="searchTree" type="text" style="height:30px;width:100%; padding:0 12%;" onChange="deptChange()" class="proEditIn" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()" value="${proj.extend3 }">
										</div> 
										<select id="eventDept" class="qkby_sel" style=" position:absolute;top:34px; left:0; width:100%; border-radius: 5px; display: none; height:100px;" name="eventUser" onChange="userChange()" size="5" >
										</select>
											<div id="sidebar">
												<div class="toggleCollapse">
										       		<div id="treeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
										          		<div class="accordionContent" style="background-color: #ffffff">
									             			<ul id="a" class="tree treeFolder ">
										               			<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#fff" id="openTree">部门信息</a>
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
								
								<div class="pro_input_list">
									<label >项目金额<i style="color:red;font-size:16px;">*</i>：</label>
									<input type="text" id="projAmt" name="projAmt" value="<fmt:formatNumber value="${proj.projAmt }" pattern="#.00"/>" class="amtTxt proEditIn  proTxt needIn" style="width:25%;float:left;" data-message="项目金额不得为空！"><span style="width:40px; text-align:center;display:inline-block;" >元</span>
								</div>
								<div class="pro_input_list">
									<label >资金来源<i style="color:red;font-size:16px;">*</i>：</label>
									<%-- <select id="amtFrom" name="amtFrom" class="proEditIn" data-message="">
										<option value="1" <c:if test="${proj.amtFrom == '1' }">selected</c:if>>自有资金</option>
										<option value="2" <c:if test="${proj.amtFrom == '2' }">selected</c:if>>财政拨款</option>
									</select> --%>
									<select id="amtFrom" name="amtFrom" class="proEditIn" data-message="">
										<c:forEach items="${capitalSourceAll }" var="capitalSource" >
											<option value="${capitalSource.id }" <c:if test="${proj.amtFrom == capitalSource.id }">selected</c:if>>${capitalSource.name }</option>
										</c:forEach>
									</select>
										
								<div class="blockEnter"></div>						
								</div>
								<div class="pro_input_list">
									<label >合同编号<i style="color:red;font-size:16px;">*</i>：</label>
									<input type="text" id="contCode" name="contCode" value="${proj.contCode }" class="proTxt proEditIn needIn" data-message="合同编号不得为空！">
								</div>
								<div class="pro_input_list " style="position:relative;">
									<label>起止时间<i style="color:red;font-size:16px;">*</i>：</label>
									<div class="proEditBox" style="position:absolute;z-index:9999; width:100%; height:100%;"></div>
									<input type="text" id="startTime" name="startTime" value="<fmt:formatDate value="${proj.dateStart}" pattern="yyyy-MM-dd" />" class="proEditIn timeInput needIn">
									<span style="width:4%; text-align:center;display:inline-block; float:left;">至</span>
									<input type="text" id="endTime" name="endTime" value="<fmt:formatDate value="${proj.dateEnd}" pattern="yyyy-MM-dd" />" class="proEditIn timeInput needIn">
								</div>
								<div class="pro_input_list pro_txt_list">
									<label >项目备注：</label>
									<textarea id="projExplain" name="projExplain"  class=" proEditIn">${proj.projExplain }</textarea>
								</div>
					
							</div>
							<div class="pro_l_bot">
								<p class="titline">成员信息</p>
								<div class="pro_input_list">
									<label for="projManager" >项目经理<i style="color:red;font-size:16px;">*</i>：</label>									
									<select id="projManager" name="projManager" class="proEditIn needSel">
										<option value="">请选择项目经理</option>
										<c:forEach items="${userByRoleType }" var="operRole">
											<option value="${operRole.id }" <c:if test="${proj.idProjManager == operRole.id}">selected</c:if>>${operRole.name }</option>
										</c:forEach>
									</select>	
									<div class="blockEnter"></div>			
								</div>
								<div class="pro_input_list pro_big_list">
									<label>项目成员<i style="color:red;font-size:16px;">*</i>：</label>
									<div class="manBox" style="position:relative;">
										<div class="proEditBox" style="position:absolute;z-index:9999; width:100%; height:100%;"></div>
										<div style="" id="dutyUserText" class="" ></div>
										<input type="hidden" id="projMembers" name="projMembers" value="" class=""/>
										<select id="projMember" name="projMember"  class="form-control proEditIn" multiple style="position:absolute;margin:0;top:100px; display:none;height:100px; z-index:999;" size="5" >
											<c:forEach items="${userByRoleType }" var="operRole">
												<option value = "${operRole.id }">${operRole.name }</option>
											</c:forEach>
										</select>
									</div>
									<div style="display: none;" id="newDiv">
										<select id="newMember" name="newMember">
											<c:forEach items="${memberList }" var="newMember">
												<option value = "${newMember.idMember }">${newMember.extend1 }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						</div>
					
						<div class="proRight">
							<p class="titline">相关文件</p>
							<div id="fileProjDiv" style="display:none"></div>
							<div id="fileDiv" style="display:none"></div>
							<div class="fileTab taskList">
								<a class="text-center btn btn_blue proEditBtn" style="padding:0 12px; margin-top:9px;margin-left:20px; " id="butFile" >选择文件</a>									
							</div>
							<div id="fileListDiv" style="width:406.63px; float:left;" ><!-- 存放返回的文件集合 -->
								<c:forEach items="${files }" var="file">	
									<div title="${file.fileName }" class='drFileItem' id="${file.fileId }"><i class='fa fa-file'></i><br><a class='drFileName drFileNames' href='javascript:;'>${file.fileName }</a><a class='drDel btn_green'  href='#' onclick="downFile(${file.fileId })">下载</a><a class='drDel btn_red proEditBtn' href='javascript:void(0);' onclick="delFile(${file.fileId },${file.projFileId })">删除</a>
										
									</div>
								</c:forEach>
							</div>
							
							<div id="app" style="display: none;"></div>			
							<div id="text" >
							
							</div>	
							
							

						</div>
					
					</form>
				</div>
			</div>
			<div class="tabItem">
				<div class="mQuery-2">
						<form action="" role="form" class="form-horizontal" method="post">
							<div class="form-group col-sm-4" style="margin-left: 0px">
								<label for="eventPer" class="col-sm-3">任务名称:</label>
								<div class="col-sm-9">
									<input type="text" id="taskName" name="taskName"  class="form-control">
								</div>
							</div>
							<div class="form-group col-sm-2" style="margin-left: 0px">
								<label for="eventPer" class="col-sm-3">负责人:</label>
								<div class="col-sm-9">
									<select id="idTaskHead" name=idTaskHead class="form-control">
										<option value="">请选择任务负责人</option>
										<c:forEach items="${memberList }" var="member">
											<option value = "${member.idMember }" <c:if test="${task.idTaskHead == member.idMember}">selected</c:if>>${member.extend1 }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group col-sm-2" style="margin-left: 0px">
								<label for="eventPer" class="col-sm-5">任务筛选:</label>
								<div class="col-sm-8 col_sm_9">
									<select id="milestoneTask" name=milestoneTask class="form-control">
										<option value="">全部</option>
										<option value="1">里程碑任务</option>
									</select>
								</div>
							</div>
							<a class="btn btn-primary" href="#" id="button_search">查询</a>
						</form>
					</div>
					<div class="btn_class">
						<a href="javascript:cancel();" id="taskBack" class="btn_base btn_red"  >返回</a>
					
						<a href="javascript:addTask();" id="addTask" class="btn_base btn_add"><i class="fa fa-plus"></i>&nbsp;新增任务</a>
			
			
					</div>
				
					<div class="mRbot" style="top: 85px; height: 68%;">
						<table class="table table-bordered text-center table-hover table1">
							<thead>
								<tr>
									<th style="text-align:left;">任务名称</th>
									<th width="60px;">负责人</th> 
									<!-- <th>工期</th> -->
									<th width="140px">开始时间</th>
									<th width="140px">结束时间</th>
									<th width="80px">任务状态</th>
									<th width="190px">任务进度</th>
									<th width="60px" id="taskOper">操作</th>
								</tr>
							</thead>
							<tbody id="projBody">
								
							
							</tbody>
						</table>
					</div>
					<div class="mRpage" id="paging"></div>
				</div>	
		</div>
	</div>
	<script>
	$(function(){
		$(".question").click(function(){
			if($("#typeAlert").text() == "null"){
				$("#typeAlert").text("");
			}
			$(this).next().show();
		})
		$(".anwClose").click(function(){
			$(this).parent().parent().hide();
		})
		//加载模板图片
		$("#downProjTemplate").click(function(){
			var templateId = $("#projTemplate option:selected").val();
			if(templateId != ""){
				var newHref = getPath()+"/showProjProcess.do?templateId="+templateId;
				window.open(newHref)
			}
		})
		//判断是否是项目监控界面中的项目查看
		var state = $("#state").val();
		if(state == "projControl"){
			$(".mRpos").hide();
			$("#taskOper").hide();
			$(".btn_class").hide();
			$(".mQuery-2").hide();
		}
		//如果项目为冻结，完成的状态则不可以操作新增任务功能
		var projStatus = $("#projStatus").val();
		if(projStatus == 96 || projStatus == 97){
			$("#addTask").hide();
			$("#proEdit").hide();
		}
		  $("#onOverShow").click(function(){
			  alert("请完成项目信息的填写！！！")
		  })
		$(".proEditIn").attr("disabled","disabled");	
	
		$(".proEditBtn").hide();
		$(".timeInput").width(80);
		
		var status = $("#status").val();
		if(status == "add"){
			//默认将部门下所有的人填入项目成员
			var projMember = $("#projMember option");
			for (var j = 0; j < projMember.length; j++) {
				document.getElementById("dutyUserText").innerHTML+="<a href='#'>"+projMember.eq(j).text()+"<i>"+projMember.eq(j).val()+"</i><span>×</span></a>";
				projMember.eq(j).hide().addClass("bulingbuling");
			}
			$("#dutyUserText a span").click(function(e){
				
				$(this).parent().remove();
				e.stopPropagation()
				var newidcode=$(this).prev().text();
				$.each($("#projMember option"),function(){
					if($(this).val()==newidcode){
						$(this).removeClass("bulingbuling");
						$(this).show();
					}
				})
				
			})
			$("#back").hide();
			$("#projTemplateDiv").show();
	        proEdit();
	        $("#proExit").attr("href","#")//新建取消
	        $("#proExit").click(function(){
	        	cancel();
	        })
	        $("#onOverShow").show();
	        $("#proSave").attr("href","#")//新建保存
	   		$("#overShow div").show();
	        $("#proSave").click(function(){
	        	addSave();
	        })
		}else{
			//回显：拿到回显的值和所有的值进行比较，相等的执行隐藏和添加
			var newMember = $("#newMember option");
			var projMember = $("#projMember option");
			for (var j = 0; j < projMember.length; j++) {
				for (var i = 0; i < newMember.length; i++) {
					if(newMember.eq(i).val() == projMember.eq(j).val()){
						document.getElementById("dutyUserText").innerHTML+="<a href='#'>"+projMember.eq(j).text()+"<i>"+projMember.eq(j).val()+"</i><span>×</span></a>";
						projMember.eq(j).hide().addClass("bulingbuling");
					}
				}
			}
			$("#dutyUserText a span").click(function(e){
			
				$(this).parent().remove();
				e.stopPropagation()
				var newidcode=$(this).prev().text();
				$.each($("#projMember option"),function(){
					if($(this).val()==newidcode){
						$(this).removeClass("bulingbuling");
						$(this).show();
					}
				})
				
			})
		}
		$("#overShow").click(function(){	
			if(state == "projControl"){
				$("#taskBack").hide();
			}
			showPrpList();
		
		})
		$("#overHide").click(function(){
			if($("#status").val() == "add"){
				$("#proEdit").hide();			
			}
		})
	})
	
	</script>
	<script type="text/javascript">
		//项目成员
		
		//
		$("#projMember").width($("#dutyUserText").width()-10)
		$("#dutyUserText").click(function(e){
			
			$("#projMember").show();
			$("#projMember").click();
			if($("#projMember option").length==$(".bulingbuling").length){
				$("#projMember").hide()
			}else{
				$("#projMember").show()
			}
			e.stopPropagation();
		})
		$("#projMember option").click(function(e){
			var text=$(this).text();
			var textId=$(this).val();
			$(this).hide().addClass("bulingbuling");//下拉框中的名称隐藏
			if($("#projMember option").length==$(".bulingbuling").length){
				$("#projMember").hide()
			}else{
				$("#projMember").show()
			}
			e.stopPropagation();
			document.getElementById("dutyUserText").innerHTML+="<a href='#'>"+text+"<i>"+textId+"</i><span>×</span></a>";
			$("#dutyUserText a span").click(function(e){
				$(this).parent().remove();
				e.stopPropagation()
				var newidcode=$(this).prev().text();
				$.each($("#projMember option"),function(){
					if($(this).val()==newidcode){
						$(this).removeClass("bulingbuling");
						$(this).show();
					}
				})
				
			})
			
		})
		$("#projMember").blur(function(){
			$(this).hide();		
		})
		$("#projMember").click(function(e){
			e.stopPropagation();
		})
		$(document).click(function(){
			$("#projMember").hide()
		})
		
	</script>
	<script type="text/javascript">
		
		$(document).ready(function(){
			
			
		    $(".mRtabs li").click(function(){
		    	$(".mRtabs li").eq($(this).index()).addClass("nav_on").siblings().removeClass('nav_on');
				$(".mRbot .tabItem").css({"opacity":0,"z-index":-1}).eq($(this).index()).css({"opacity":1,"z-index":2});
		    });
		    //加载任务列表
		    $("#button_search").click(function(){
		    	if($("#milestoneTask option:selected").val()==""&&$("#idTaskHead option:selected").val()==""&&$("#taskName").val()==""){
		    	
		    		showPrpList();
		    	}else{	    		
		    		showProList();
		    	}
		    	
			})
		});
		
		$(function(){			
			$("#startTime").datetimepicker({
				lang:'zh',
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
			$("#endTime").datetimepicker({
				lang:'cn',
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
		})
	
		//上传
		$(function(){
		        var i=0;
		        var tpl="";
		        $("#butFile").click(function() {
		            var index=i++;		         
		            tpl = "<input type='file' name='file" + index + "' id='file" + index + "'/><br>";
		            $("#app").append(tpl);
		            $('#file' + index + '').click();
		            $('#file' + index + '').change(function () {
		                var file= document.getElementById("file"+index+"").files[0];
		             
		             	var tpFile="<div title='"+file.name+"' class='drFileItem' id='del"+index+"'><i class='fa fa-file'></i><br><a class='drFileName drFileNames' href='javascript:;'>"+file.name+"</a><a class='drDel btn_red' href='javascript:remove("+index+");' >删除</a></div>"
		                $("#text").append(tpFile)
		                var len=$("#text .drFileItem").length;
		           
		    			var drFileNameK='';
		    			for(var ks=0;ks<len-1;ks++){		    				
		    				
		    				drFileNameK=$(".drFileNames").eq(ks).text();
		    			
		    				 if(file.name==drFileNameK){
		    				 	$('#file' + index + '').remove();
		    				 	$('#del'+index+'').remove();
		    				 }

		    			}
		    			
		            })
		        })
		    })
	    
	</script>
</body>
</html>