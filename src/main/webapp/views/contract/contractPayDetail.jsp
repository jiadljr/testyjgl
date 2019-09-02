<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>合同支付明细</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>

	<style type="text/css">
		.btn { position:absolute; right:10%; top:20px;}
	
		.table{ width:90%; margin:50px auto;}
		h1{text-align:center; font-size:18px; height:30px; line-height:30px;margin-top:20px;}
	</style>
	
</head>
<body>
<a class="btn btn_blue">导出</a>
<h1>合同支付明细</h1>
<table class="table table-bordered">
   <tr>
     <td>合同编号</td>
     <td>合同名称</td>
     <td>对方单位</td>
     <td>合同总价</td>
   </tr>
   <tr>
     <td>${contractInfo.code }</td>
     <td>${contractInfo.contractTitle }</td>
     <td>${contractInfo.firstPartyUnit }</td>
     <td id="contractMoney">${contractInfo.contractMoney }</td>
   </tr>
</table>
 <table  class="table table-bordered">
     <tr>
        <td>编号</td>
        <td>比例%</td>
        <td>支付金额（元）</td>
        <td>支付时间</td>
        <td>支付条件</td>
        <td>发票号</td>
        <td>备注</td>
        <td>登记人</td>
     </tr>
 <c:forEach items="${contractPaymentTerms }" var="payment">
     <tr>
        <td>${payment.code }</td>
        <td class="paymentProportion">${payment.paymentProportion }</td>
        <td class="paymentAmount">${payment.paymentAmount }</td>
        <td>${payment.paymentTime }</td>
        <td>${payment.name }</td>
        <td>${payment.invoice }</td>
        <td>${payment.paymentDesc }</td>
        <td>${payment.extend1 }</td>
     </tr>
 </c:forEach>
      <tr>
        <td>小计</td>
        <td id="paymentProportion"></td>
        <td id="paymentAmount"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
     </tr>
       <tr>
        <td>差额</td>
        <td id="paymentProportions"></td>
        <td id="paymentAmounts"></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
     </tr>
 </table>
</body>
<script>
var paymentProportionNum=0;
$.each($(".paymentProportion"),function(){
	paymentProportionNum+=($(this).text()-0)*100;
})
var paymentAmountNum=0;
$.each($(".paymentAmount"),function(){
	paymentAmountNum+=($(this).text()-0)*100;
})
var num=(($("#contractMoney").text()*100)-paymentAmountNum)/100;

$("#paymentAmounts").html(num)
$("#paymentAmount").html(paymentAmountNum/100)
$("#paymentProportion").html(paymentProportionNum/100)
$("#paymentProportions").html(100-(paymentProportionNum/100))
</script>
</html>