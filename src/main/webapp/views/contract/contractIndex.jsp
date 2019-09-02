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
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
		<script src="<%=request.getContextPath()%>/js/index.js"></script>
	
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page2.js"></script>
	<script src="<%=request.getContextPath()%>/js/contractIndex.js"></script>
	<style type="text/css">
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.mQuery-2 label{text-align:center;}
	.mQuery-2 select.form-control{padding:0 12px;}
	.mQuery-2 .sildeTit{text-align:left; text-indent: 2em;}
	.slideHide{ height:185px;position:absolute;left:0;top:81px;display:none;z-index:9999;}
	.mQuery-2 .highSearchList{ text-align:center; height:40px;}
	.mQuery-2 .highSearchList .btn{ margin:5px 20px; border:none;}
	.hoverPayBtn{width:20px; display:inline-block; line-height:20px; height:20px; padding:0; border-radius:10px;}
		.btnClick{position:absolute;z-index:999;  right:16px;top:0; background:#f4f4f4; text-align:center;  height:36px; width:200px; display:none;} 
   	.btnClick li{ float:left; height:36px; width:60px;}
	    .mRight .fixTable{ float:left; height:38px; width:100%;}
    .mRight .fixTable table { margin-bottom:0;}
    .mRight .relTable{ float:left; width:100%; overflow-y:auto;overflow-x:hidden;}
    .mRight .relTable table{ margin-bottom:0;}
    .mRight .relTable table td{ line-height:1;}
   	.btnClick li a{ display:inline-block; width:60px; font-size:12px;  height:36px; line-height:36px; color:#999;}                  
   	.btnClick li a:hover{background:#00c1de; color:#fff;}
   	.btnHover{ color:blue;}
   	.btnHover:hover{ text-decoration: underline;}
   	
	</style>
	
</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="#">合同管理</a></li>
				<li class="active">合同台帐</li>
				
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
					<input type="text" id="conKeyWord" name="conKeyWord"  class="form-control" value="" placeholder="合同名称关键字">
				</div>
				<a class="btn btn-primary" href="#" id="button_search" style="margin-top:0; margin-left:5px;" onclick="shwoContractList(1)">搜索</a>
				<a class="btn btn-primary btn_green" href="javascript:slideShow();" style="margin-top:0; margin-left:5px;border:none;">高级搜索</a>			
			</div>
			<div class="col-sm-6" style="margin-left: 0px">
				<a class="btn btn-primary btn_blue" href="javascript:queryInsertPage();" id="button_search" style="margin-right:55px; border:none; float:right;">新增</a>			
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
						<input type="text" id="firstPartyUnit" name="firstPartyUnit"  class="form-control" value="" placeholder="">
					</div>				
				</div>
				<div class="col-sm-5" style="margin-left: 0px">
					<label for="goodLevel" class="col-sm-4 " >签署日期：</label>				
					<div class="col-sm-6">
						<div class="col-sm-5">
							<input type="text" id="signTimeStart" name="signTimeStart"  class="form-control" value="" placeholder="">
						</div>
						<label for="goodLevel" class="col-sm-2 ">至</label>				
						<div class="col-sm-5">
							<input type="text" id="signTimeEnd" name="signTimeEnd"  class="form-control" value="" placeholder="">
						</div>
					</div>	
				</div>
				<div class="col-sm-5" style="margin-left: 0px">
					<label for="conType" class="col-sm-4 ">合同类型：</label>				
					<div class="col-sm-3">
						<select id="contractTypeCode"  class="form-control" >
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
					<label class="col-sm-4 ">付款方式：</label>				
					<div class="col-sm-6">
						<div class="col-sm-5">
							<select id="payFunc"  class="form-control" >
								<option value="">请选择方式</option>
							</select>
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
   			<table class="table table-bordered table1 text-center" >
				<thead>
					<tr>
					    <th>合同编号</th>
	                    <th>合同标题</th>
	                    <th>对方单位</th>
	                    <th width="90px;">合同类型</th>
	                    <th width="90px">履约情况</th>
	                    <th width="90px">资金来源</th>
	                    <th width="120px">合同金额</th>
	                    <th width="90px">签署日期</th>
	                    <th width="70px">操作</th>
					</tr>
				</thead>
			
			</table>
		
        </div>
		<div class="relTable" id="showtable">
			<table class="table table-bordered table-hover table1 text-center">
				<tbody  id="contractList">
					
				</tbody>			
			</table>
		</div>

		<div class="mRpage" id="paging"></div>
	</div>

</body>
<script>
$(function(){
	shwoContractList()
	
})

function searchHigh(){
	slideHide();
	shwoContractList(2)
}
function slideShow(){
	$(".slideHide").slideDown()
}
function slideHide(){
	$(".slideHide").slideUp()
}
function payReg(){
	var jump_url = "payRegister.jsp";
	layer.open({
	    type: 2,
	    title: '付款登记',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['660px', '520px'],
	    content: [jump_url,'yes']
   })
}
$("#signTimeEnd").datetimepicker({
	lang:'cn',
	timepicker:false,
	format:'Y-m-d',
	formatDate:'Y-m-d',
});
$("#signTimeStart").datetimepicker({
	lang:'cn',
	timepicker:false,
	format:'Y-m-d',
	formatDate:'Y-m-d',
});
function queryInsertPage(){
	location.href = getPath()+"/queryInsertPage.do";
}
</script>
</html>