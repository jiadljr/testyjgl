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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
		<script src="<%=request.getContextPath()%>/js/contractPayRegister.js"></script>
	
	<style>
	.tableTitle{background:#f1f1f1; text-align:center;}  
	.table{ width:96%; margin:5px auto;}
	.table tbody tr td{padding:2px 8px; }
		.importBox{ height:24px; border:1px solid #ccc; box-sizing:border-box;}
		.importLong{ width:100%;}
		.selectBg4{ width:100%; border:1px solid #ccc;}
		.selectBg4 option{ height:22px; line-height:22px;}
		.tabReg{width:100%; height:90px; margin-top:5px;}
		.tabReg textarea{height:100%; resize:none;border:1px solid #ccc;}
		.btnlist{ text-align:right; width:100%; height:30px; float:left; position:absolute; bottom:10px;} 
   		.btnlist a{  display:inline-block; padding:0 4px; height:24px; line-height:24px; margin:3px 10px;border-radius:3px;}
  
	</style>
	


		
</head>
<body>
<form id="form1">
	<table class="table table-bordered " >
	<tbody>
		<tr>
			<td width="120px;" class="tableTitle">合同名称</td>
			<td>${contractInfo.contractTitle }</td>
		</tr>
		<tr>
			<td class="tableTitle">合同金额</td>
			<td>${contractInfo.contractMoney }</td>
		</tr>
		<tr>
			<td class="tableTitle">已付金额</td>
			<td>${alreadyPaymentAmountSum.extend1 }</td>
		</tr>
		<tr>
			<td class="tableTitle">已付比例</td>
			<td>${str }</td>
		</tr>
		<tr>
			<td align="center" height="120px;">支付条件*</td>
			<td>
				<div style="height:20px;" ><input name="definition" value="1" type="radio" checked />已定义  &nbsp; &nbsp;<input value="2" name="definition"  type="radio"/> 未定义</div>
				<div class="tabReg1 tabReg" >
					<select size="4" class="selectBg4" id="payNames" >
					
						<c:forEach items="${contractTerms }" var="contract">
						   <option value="${contract.id }">${contract.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="tabReg2 tabReg" style="display:none;">
					<textarea class="importLong nullInput defInput" id="payNameStr"></textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td align="center">支付时间*</td>
			<td><input type="text" id="paymentTime" class="importBox nullInput finalInput" name="paymentTime" /></td>
		</tr>
		<tr>
			<td align="center">支付金额*</td>
			<td><input type="text" class="importBox floatNum nullInput finalInput " id="paymentAmount" name="paymentAmount" /></td>
		</tr>
	
		<tr>
			<td align="center">发票号</td>
			<td><input type="text"  class="importBox importLong inputFa nullInput finalInput"  id="invoice" name="invoice" /></td>
		</tr>
		<tr>
			<td align="center" height="100px;">备注</td>
			<td><textarea class="importLong" style="height:100px;border:none;" id="paymentDesc" name="paymentDesc" ></textarea></td>
		</tr>
	</tbody>
	</table>
</form>

<div class="btnlist">
	<a href="#" class="btn_blue" id="sure"  onclick="register()">确认</a>
	<a href="#" id="close" class="btn_red" >关闭</a>
</div>
</body>
<script>
$("#close").click(function(){
	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
	parent.layer.close(index);
})
$(function(){


	$("#payNames").change(function(){
		selectTime($("#payNames option:selected").val())
	})

	$("#paymentTime").datetimepicker({
		lang:'cn',
		timepicker:false,
		format:'Y-m-d',
		formatDate:'Y-m-d',
	});
	$("input[name='definition']").change(function(){
		$("#payNames").removeClass("error");
		$(".defInput").removeClass("error");
		if($(this).val()=="1"){
			$(".tabReg1").show();
			$(".tabReg2").hide();
		}else if($(this).val()=="2"){
			$(".tabReg2").show();
			$(".tabReg1").hide();
		}
	})
	
})
</script>
</html>