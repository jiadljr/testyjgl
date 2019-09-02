<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prompt.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page2.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_info_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style type="text/css">
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.mQuery-2 .form-group{ margin-bottom:0;}
	.ztree {padding: 0;border: 2px solid #CDD6D5;width:160px; height:150px; overflow:auto;}
   	.btn_base{  font-size:14px; padding:3px 5px;margin:10px 80px 10px 10px; float:right;   border-radius:3px; color:#fff; display:inline-block;}
   	.btn_refresh{ background:#72b960; }
   	.btn_add{ background: #1476d9;}
   	.btn_delete{background:#ff0000;}
   	.btn_edit{background:#feb94a;}
   	.btn_lookfoward{background:#00c1de;}
   	.btn_power{background:#ff6666;}
   	.btn_signout{background:#d5d53f;}
   	.btn_signin{background:#e28786;}
   	.btn_download{background:#9065b4;}
   	.navFin{ float:left; height:40px; line-height:40px;margin-left:30px;}
   	.navFin li{float:left; padding:0 5px; cursor:pointer;}
   	.navFinOn{ color:#1476d9;}
   	.schedule span{width:60px; display:inline-block; float:left;text-align:center; }
   	.schedule p{width:170px; height:5px;margin-top:-1px; background:#aaa;float:left;}
   	.schedule p b{ transition: all 2s; display:inline-block;height:5px; background:red;float:left;}
   	.btnClick{position:absolute;z-index:999;  right:16px;top:0; background:#f4f4f4; text-align:center;  height:36px; width:200px; display:none;} 
   	.btnClick li{ float:left; height:36px; width:60px;}
	
   	.btnClick li a{ display:inline-block; width:60px; font-size:12px;  height:36px; line-height:36px; color:#999;}                  
   	.btnClick li a:hover{background:#00c1de; color:#fff;}
   	.btnHover{ color:blue;}
   	.btnHover:hover{ text-decoration: underline;}
   	.btn_base:hover{color:#fff; text-decoration: none;}
   	#projBody tr td{ position:relative;}
   	.iconClass{position:absolute; width:60px; height:14px; display:block;  right:5px;  top:9px}
        .iconClass i{ cursor:pointer; width:17px; font-size:14px;margin-right: 3px; float:right;  position:static;}
        .iconClass .flagHide{ display:none;}
        .iconClass .flagShow{ display:block;color:red;}
           .iconClass .yShowWarn{color:#d1a314;}
             .iconClass .yHideWarn{display:none;}
         .iconClass .rShowWarn{display:block;color:#da251c;}
           .iconClass .rHideWarn{display:none;}
           .mRight .fixTable{ float:left; height:38px; width:100%;}
           .mRight .fixTable table { margin-bottom:0;}
           .mRight .relTable{ float:left; width:100%; overflow-y:auto;overflow-x:hidden;}
           .mRight .relTable table{ margin-bottom:0;}
           .mRight .relTable table td{ line-height:1;}
           	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page	.layui-layer-title{color:#fff;background:#3b85c7; }
	</style>
</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="#"><img alt="pos"
						src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="#">项目管理</a></li>
				<li class="active">我的项目</li>
			</ol>
		</div>
		<div class="mQuery-2">
			<form action="" role="form" class="form-horizontal" method="post">
			<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
			<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
			<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
			<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
			<input type="hidden" id="pages" name="pages" value="${pages }">
				<input type="hidden" id="pStatus" name="pStatus" value="${pStatus }"/>
				<div class="form-group col-sm-4" style="margin-left: 0px">
					<label for="eventPer" class="col-sm-2">项目名称:</label>
					<div class="col-sm-10">
						<input type="text" id="projName" name="projName"  class="form-control" value="${paramProjName }">
					</div>
				</div>
				<%-- <div class="form-group col-sm-2 goodLevel" >
					<label for="projCode" class="col-sm-5">项目编号:</label>
					<div class="col-sm-8 col_sm_9">
						<input type="text" id="projCode" name="projCode"  class="form-control" value="${paramProjCode }">
					</div>
				</div> --%>
				<div class="form-group col-sm-2 goodLevel" >
					<label for="goodLevel" class="col-sm-5">项目分类:</label>
					<div class="col-sm-7">
						<select id="projType" name="projType" class="form-control">
							<option value="">请选择</option>
							<c:forEach items="${projTypeList }" var="pType">
								<option value="${pType.id }" <c:if test="${pType.id == paramProjType }">selected</c:if>>${pType.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group col-sm-2 goodLevel" >
					<label for="goodLevel" class="col-sm-5">项目监控:</label>
					<div class="col-sm-7">
						<select id="projControl" name="projControl" class="form-control">
							<option value="0" <c:if test="${projControl == '0'}">selected</c:if>>否</option>
							<option value="1" <c:if test="${projControl == '1'}">selected</c:if>>是</option>
						</select>
					</div>
				</div>
				<a class="btn btn-primary" href="javascript:selectId();" id="button_search">查询</a>
			</form>
		</div>
		<ul class="navFin">
				<li class="navFinOn" id="pStatus1"><input type="hidden" value="全部"/>全部（<span id="allPro"></span>）</li>
				<li>|</li>
				<li id="pStatus2"><input type="hidden" value="未完成"/>未完成（<span id="unfinPro"></span>）</li>
				<li>|</li>
				<li id="pStatus3"><input type="hidden" value="冻结"/>冻结（<span id="stopPro"></span>）</li>
				<li>|</li>
				<li id="pStatus4"><input type="hidden" value="完成"/>完成（<span id="finishPro"></span>）</li>
				<li>|</li>
				<li id="pStatus5"><input type="hidden" id="pStatus5" name="pStatus5" value="延期"/>延期（<span id="overTimePro"></span>）</li>
				<li>|</li>
				<li id="pStatus6"><input type="hidden" value="草稿"/>草稿（<span id="draftPro"></span>）</li>
		</ul>
		<a href="javascript:moreAdd();" class="btn_base btn_add btn_blue"><i class="fa fa-plus"></i>&nbsp;批量导入</a>
		<a href="javascript:add();" style="margin-right:15px;" class="btn_base btn_add"><i class="fa fa-plus"></i>&nbsp;新建项目</a>
	
		<!--<div class="btnout">
            <a onclick="add()" class="btn btn-primary btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">新增</a>
            <a onclick="update()" class="btn btn-primary btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
        </div>-->
        <div class="fixTable mRbot">
   			<table class="table table-bordered table1">
				<thead>
					<tr>
						<th style="text-align:left;">项目名称</th>
						<th width="60px;">负责人</th>
						<th width="90px">项目类型</th>
						<th width="80px">项目状态</th>
						<th width="110px">开始时间</th>
						<th width="110px">结束时间</th>
						<th width="190px">项目进度</th>
						<th width="90px" >操作</th>
					</tr>
				</thead>
			</table>
        </div>
		<div class="relTable" >
			<table class="table table-bordered table-hover table1">
			
				<tbody id="projBody">
					
				</tbody>
			</table>
		</div>
		<div class="mRpage" id="paging"></div>
	</div>
	<script type="text/javascript">
		$(function(){
			
			$(".relTable").height($("body").height()-210);
			$("#button_search").click(function(){
				showProjInfoList();
			})
			showProjInfoList();
			$(".navFin li").click(function(){
				$(".navFin li").removeClass("navFinOn");
				$(this).addClass("navFinOn");	
				var pStatus = $(this).find("input").val();
				changeInputState(pStatus);
				showProjInfoList();
			})
			
			var pStatus = $("#pStatus").val();
			if(pStatus == "全部"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus1").addClass("navFinOn");
			}else if(pStatus == "未完成"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus2").addClass("navFinOn");
			}else if(pStatus == "冻结"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus3").addClass("navFinOn");
			}else if(pStatus == "完成"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus4").addClass("navFinOn");
			}else if(pStatus == "延期"){
				$(".navFin li").removeClass("navFinOn");
				$("#pStatus5").addClass("navFinOn");
			}
		
		})
		function fatherFun(){
			showProjInfoList();
			storage('导入成功');
			
		}
		function changeInputState(status){
			$("#pStatus").val(status);
		}
		
	</script>
	<script>
  
	</script>
</body>
</html>