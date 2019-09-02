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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<link  rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/table/bootstrap-table.min.css" />
	<script src="<%=request.getContextPath()%>/bootstrap/table/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page2.js"></script>

	
	<script src="<%=request.getContextPath()%>/js/contractPayList.js"></script>
	<style type="text/css">
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.mQuery-2 label{text-align:center;}
	.mQuery-2 select.form-control{padding:0 12px;}
	.mQuery-2 .sildeTit{text-align:left; text-indent: 2em;}
	.slideHide{ height:165px;position:absolute;left:0;top:81px;display:none;}
	.mQuery-2 .highSearchList{ text-align:center; height:40px;}
	.mQuery-2 .highSearchList .btn{ margin:5px 20px; border:none;}
	.outPayMes{ width:460px; height:230px;z-index:1001; display:none;background:#fff;position:absolute;left:0 ;top:0 ; bottom:0;  right:0; box-shadow:0 0 3px #aaa;margin:auto;}
	.outPayMes h3{background:#3b85c7;height:30px; line-height:30px;width:100%; color:#fff;text-align:center;}
	    .mRight .fixTable{ float:left; height:38px; width:100%;}
    .mRight .fixTable table { margin-bottom:0;}
    .mRight .relTable{ float:left; width:100%; overflow-y:auto;overflow-x:hidden;}
    .mRight .relTable table{ margin-bottom:0;}
    .mRight .relTable table td{ line-height:1;}
	.outPayMes p { height:30px; line-height:30px; width:100%;}
	.outPayMes .outPayMesTit{text-indent: 2em;}
	.outPayMes p span{ width:31%; display:inline-block; text-align:center;box-sizing:border-box;}
	.outPayBtn{height:40px;line-height:40px; text-align:right; margin-top:5px;}
	.outPayBtn .btn{padding:3px 12px;border:none;}
	</style>
	
</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="#">合同管理</a></li>
				<li class="active">付款台帐</li>				
			</ol>
		</div>
		<div class="mQuery-2">
			<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
			<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
			<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
			<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
			<input type="hidden" id="pages" name="pages" value="${pages }">
			<div class="col-sm-6" style="margin-left: 0px">
				<label class="col-sm-2">输入关键词：</label>				
				<div class="col-sm-5">
					<input type="text" id="projName" name="projName"  class="form-control" value="" placeholder="合同关键字">
				</div>
				<a class="btn btn-primary" href="#" id="button_search" style="margin-top:0; margin-left:5px;" onclick="shwoPayList(1);">搜索</a>
				<a class="btn btn-primary btn_green" href="javascript:slideShow();" style="margin-top:0; margin-left:5px;border:none;">高级搜索</a>			
				<a class="btn btn-primary btn_blue" href="javascript:outBoxShow();" id="outBtn" style="margin-top:0; margin-left:5px; border:none; ">导出</a>														
			
			</div>
		</div>
		<div class="mQuery-2 slideHide">
			<form >			
				<div class="col-sm-12" style="margin-left: 0px">
					<label  class="col-sm-4  sildeTit">搜索条件</label>				
				</div>
		
				<div class="col-sm-5" style="margin-left: 0px">
					<label for="sideComoany" class="col-sm-4 ">对方单位：</label>				
					<div class="col-sm-6">
						<input type="text" id="sideComoany" name="sideComoany"  class="form-control" value="" placeholder="">
					</div>				
				</div>
				<div class="col-sm-5" style="margin-left: 0px">
					<label for="goodLevel" class="col-sm-4 " >合同编号：</label>	
					<div class="col-sm-6">
						<input type="text" id="contractCode" name="contractCode"  class="form-control" value="" placeholder="">
					</div>			
				
				</div>
				<div class="col-sm-5" style="margin-left: 0px">
					<label for="contractTypeCode" class="col-sm-4 ">合同类型：</label>				
					<div class="col-sm-3">
						<select id="conType"  class="form-control" >
							<option value="">请选择类型</option>
						</select>
					</div>	
				</div>
				<div class="col-sm-5" style="margin-left: 0px">
					<label  class="col-sm-4 ">合同金额：</label>				
					<div class="col-sm-6">
						<div class="col-sm-5">
							<input type="text" id="contractMoneyStrat" name="contractMoneyStrat"  class="form-control" value="" placeholder="">
						</div>
						<label for="goodLevel" class="col-sm-2 ">至</label>				
						<div class="col-sm-5">
							<input type="text" id="contractMoneyEnd" name="contractMoneyEnd"  class="form-control" value="" placeholder="">
						</div>					
					</div>
				</div>
				<div class="col-sm-5" style="margin-left: 0px">
					<label for="fundSource" class="col-sm-4 ">资金来源：</label>				
					<div class="col-sm-3">
						<select id="fundSource"  class="form-control" >
							<option value="">请选择来源</option>
						</select>
					</div>	
				</div>
				<div class="col-sm-5" style="margin-left: 0px">
					<label class="col-sm-4 ">已结比例：</label>				
					<div class="col-sm-6">
						<div class="col-sm-5">
							<input type="text" id="alreadyStart" name="alreadyStart"  class="form-control" value="" placeholder="">
						</div>
						<label for="goodLevel" class="col-sm-2 ">至</label>				
						<div class="col-sm-5">
							<input type="text" id="alreadyEnd" name="alreadyEnd"  class="form-control" value="" placeholder="">
						</div>
					</div>	
				</div>
				<div class="col-sm-12 highSearchList" >
					<a class="btn btn-primary" href="#" onclick="searchHigh()" >搜索</a>
					<input class="btn btn-primary btn_purple"  value="重置" type="reset">					
					<a class="btn btn-primary btn_red" href="javascript:slideHide();" >关闭</a>				
				</div>
			</form>							
		</div>
        <div class="fixTable mRbot" style="height:38px;">
   			<table class="table table-bordered table1" >
				<thead>
					<tr>
						<th style="text-align:left;" width="180px" >合同编号</th>
						<th style="text-align:left;">合同标题</th>
						<th style="text-align:left;">对方单位</th>
						<th width="90px">合同类型</th>
						<th width="100px">合同金额</th>
						<th width="100px;">已结金额</th>
						<th width="100px;">未结金额</th>
						<th width="80px;">已结比例</th>
						<th width="70px;">操作</th>
					</tr>
				</thead>
			
			</table>
		
        </div>
		<div class="relTable" id="showtable">
			<table class="table table-bordered table-hover table1">
				<tbody  id="payListBody">
					
				</tbody>			
			</table>
		</div>

		<div class="mRpage" id="paging"></div>
	</div>
	<div class="outPayMes">
		<h3>导出设置</h3>
		<p class="outPayMesTit">请选择属性</p>
		<p>
			<span><input type="checkbox" name="outMes" value="contractTypeCode" class="contractTypeCode">合同类型</span>
			<span><input type="checkbox" name="outMes" value="honourAgreement" class="honourAgreement">履约情况</span>
			<span><input type="checkbox" name="outMes" value="signTime" >签署日期</span>			
		</p>
		<p>
			<span><input type="checkbox" name="outMes" value="paymentMethod">付款方式</span>
			<span><input type="checkbox" name="outMes" value="paymentName">支付条件</span>
			<span><input type="checkbox" name="outMes" value="fundSource">资金来源</span>			
		</p>
		<p>
			<span><input type="checkbox" name="outMes" value="closedAmount">已结金额</span>
			<span><input type="checkbox" name="outMes" value="closedProportion">已结比例</span>
			<span><input type="checkbox" name="outMes" value="openAmount">未结金额</span>
		</p>
		<p>
			<span><input type="checkbox" name="outMes" value="invoice">发票号　</span>
			<span><input type="checkbox" name="outMes" value="paymentDesc">备　　注</span>
			<span><input type="checkbox" name="outMes" value="registrant">登记人　</span>			
		</p>
		<div class="col-sm-12 outPayBtn" >
			<a class="btn btn-primary" href="#" >确定</a>
			<a class="btn btn-primary btn_blue" href="javascript:showOutMes();" >预览</a>						
			<a class="btn btn-primary btn_red"  href="javascript:outBoxHide();">取消</a>				
		</div>
	</div>
	<div class="mask"></div>
</body>
<script>
function searchHigh(){
	slideHide();
	shwoPayList(2)
}

shwoPayList();
function slideShow(){
	$(".slideHide").slideDown()
}
function slideHide(){
	$(".slideHide").slideUp()
}

function outBoxShow(){
	$(".mask").show();
	$(".outPayMes").show();
};
function outBoxHide(){
	$(".mask").hide();
	$(".outPayMes").hide();	
}
function showOutMes(){
	var obj={};
	$.each($(".outPayMes input:checkbox:checked"),function(){
		obj[$(this).val()]=$(this).val();
	})

	$.ajax({
		type:"post",
		url:getPath()+"/selectExportPreview.do",
		data:{"outMes":1},
		success:function(){
			
		}
	}) 
}
</script>
</html>