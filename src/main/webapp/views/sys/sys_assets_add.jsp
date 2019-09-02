<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_assets_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style type="text/css">
		.QK_search{ width:100%; height:30px; position:relative;}
#searchTree{ border:1px solid #ccc; line-height:20px; margin:0; width:100%;}
#serviceSearchTree{border:1px solid #ccc; line-height:20px; margin:0; width:50%;}
#serviceSidebar{ width:100%; float:left;  margin-top:5px;position:absolute; top:30px;left:50%;z-index:10000; background:#fff; display:none;}
#sourceSidebar{ width:240px; float:left; height:184px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
#sidebar{ width:240px; float:left; height:154px;  margin-top:5px;position:absolute; top:30px;left:0;z-index:10000; background:#fff; display:none;}
.tree_list{height:154px !important; overflow:auto;}
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
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">单位信息</a></li>
				    <li class="active">增加资产信息</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">添加资产信息</div>
					<form role="form" class="form-horizontal" id="form1" method="post">
						<input type="hidden" id="pages" name="pages" value="${pages }">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
						<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
						<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
						<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<input type="hidden" name="token" value="${token}" />
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
									<label for="code" class="col-sm-3">资产编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="code" name="code" class="form-control error_null" data-message="资产编号不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-3">资产名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="name" name="name"  class="form-control error_null" data-message="资产名称不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="sub_name" class="col-sm-3">资产型号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="asModel" name="asModel"  class="form-control error_null" data-message="资产型号不得为空！"></div>
								</div>						
								<div class="form-group">
									<label for="comAbbre" class="col-sm-3">资产类型<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<div class="col-sm-4" style="position:relative;">
									<span class="QK_arrow"></span>
									<div class="QK_search">
								<div id="searchMenu">
									<input type="hidden" name="sourceId" id="sourceId" value="" /> 
									<input id="sourceSearchTree" type="text" class="span2 form-control" placeholder="请选择事件源" data-autofocus="autofocus" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showSourceTree()">
								</div> 
								<div id="sourceSidebar">
									<div class="toggleCollapse">
									  <div id="sourceTreeDiv" class="accordion" fillSpace="sidebar" style="display: none;">
											<div class="accordionContent">
												<ul id="b" class="tree treeFolder ">
													<li class="tree_list" style="display: block;"><a autobypy="bz" dataType="topmenu" childMneu="bzmenus" keyboard="A" style="color:#183152" id="openSourceTree">事件源</a>
															<ul id="bzmenus" class="source_bzmenus_sub"></ul>
													</li>
												</ul>
											</div>
										</div>
									 </div>
								</div>
							</div>
								</div>
									</div>
								</div>
								<div class="form-group">
									<label for="mail" class="col-sm-3">生产厂家<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="asManuf" name="asManuf"  class="form-control error_null" data-message="资产厂家不得为空！" ></div>
								</div>
								<div class="form-group">
									<label for="asManuPost" class="col-sm-3">使用部门<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<div class="row_model" style="position: relative">
						    <span class="QK_arrow"></span>
							<div class="QK_search">
							<div id="searchMenu">
								<input type="hidden" name="idDept" id="idDept" value="" /> 
							    <input id="searchTree" type="text" value="" class="span2 form-control" data-autofocus="autofocus" placeholder="请选择部门" x-webkit-speech="" lang="zh-CN" x-webkit-grammar="builtin:translate" onwebkitspeechchange="$('#searchTreeBtn').trigger('click')" onclick="showTree()">
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
								</div>
								<div class="form-group">
									<label for="status" class="col-sm-3">状态<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<select id="status" name="status" class="form-control">
											<option value="0">正常</option>
											<option value="1">故障</option>
										</select>
									</div>
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="addAssetsOk">确定</a>
									<a class="btn btn-sm" onclick="cancel()">取消</a>
								</div>
							</div>
						</div>
					</form>					
				</div>				 
			</div>
		</div>
</body>
</html>