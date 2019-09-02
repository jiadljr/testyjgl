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
	<script src="<%=request.getContextPath()%>/js/contractIndexAdd.js"></script>
	

	<style type="text/css">
	body{overflow-y:auto;}
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.mQuery-2 label{text-align:center;}
	.mQuery-2 select.form-control{padding:0 12px;}
	.mQuery-2 .sildeTit{text-align:left; text-indent: 2em;}
	.slideHide{ height:165px;position:absolute;left:0;top:81px;display:none;}
	.mQuery-2 .highSearchList{ text-align:center; height:40px;}
	.mQuery-2 .highSearchList .btn{ margin:5px 20px; border:none;}
	.varyBox{ width:98%; height:747px; float:left; margin :10px 1%; box-sizing:border-box;box-shadow: 0 0 5px #aaa;position:relative;}
	.varyBox .varyTitBg{  width: 100%;height: 40px;line-height:40px;display:block; text-indent:2em;font-size: 22px;box-sizing:border-box;color: #347fce;border-bottom: 1px solid #b0ccf3;}
	.varyBox .varyTitSm{  width: 100%;height: 20px;line-height:20px;display:block; text-indent:1em;font-size: 14px;box-sizing:border-box;color: #333;border-bottom: 1px solid #333; font-weight:bold;}
	.varyInnerBox{height:687px;}
	.clauseHeight{height:662px;}
	.tabBoxBase{   float:left;margin:10px 20px;box-sizing:border-box;}
	.tabBoxFit{ width:420px;  float:left;margin:10px 20px; box-sizing:border-box;}
	.varyBox .varyInput{width:100%; float:left; margin-top:5px; border:1px solid #aaa;position:relative;overflow:hidden; }
	.varyBox .varyInput .table{position:absolute;top:-36px; left:0; top:-margin-bottom:0;}
	.varyBox  .varyInput .varyTabTit{text-align:center; background:#f1f1f1}
	.varyBox  .varyInput .input{width:100%; box-sizing:border-box;height:26px; line-height:26px;}
	.conTextIn{ width:100%; resize:none; height:80px; line-height:16px;border:none;}
	.btnAdd{width:380px; margin:5px 10px; cursor:pointer; border-radius:3px;float:left; height:26px; line-height:26px; background:#f1f1f1;text-align:center;color:#333; }
	.termList{width:380px; margin:5px 10px; height:90px; float:left; border:1px solid #f1f1f1;box-sizing:border-box;position:relative;}
	.termList .termTit{width:20px; line-height:16px; font-size:14px; padding:20px 3px; height:89px; float:left;box-sizing:border-box; background:#f1f1f1;}
	.termBox{ height:89px; float:left; width:358px;}
	.termBox .termBoxList{ height:29px; line-height:29px; width:358px}
	.termBox .termBoxList .span1{width:70px;}
    .termBox .termBoxList .span2{width:90px;text-align:right;}
	 .termList .closeBtn{position:absolute;right:-5px; top:-5px; height: 20px; width:20px; line-height:20px; box-sizing:border-box;padding-left:1px; border-radius:10px; display:inline-block;color:#fff;border:1px solid #fff;  overflow:hidden;cursor:pointer;font-size:24px; background:#c0c0c0;}
	.termBox .termBoxList span{ text-align:center; display:inline-block;float:left;}
	.termBox .termBoxList input{display:inline-block; float:left; margin:0;}
	.termBox .termBoxList .input{ height:24px; width:120px; margin:2px;border:1px solid #aaa;box-sizing:border-box; line-height:24px;}
	.termBox .termBoxList .radio{margin:8px;}
	#payTerm{overflow-y:auto;}
	.btnlist{ text-align:right; width:100%; height:30px;  position:absolute; top:5px;left:0;} 
   	.btnlist a{  display:inline-block; padding:0 4px; height:24px; line-height:24px; margin:3px 10px;border-radius:3px;} 
	.fileBox{ width:80%; height:80px; float:left; overflow-x:hidden;overflow-y:auto;}
	.fileBtn{ width:20%; height:80px; float:left;}
	.fileBtn a{display:block; width:100%; height:100%; float:left; border-radius:5px; line-height:80px; text-align:center;}
		.drFileItem{ width:70px; margin:5px; height:81px; text-align:center; float:left;}
		.drFileItem i{ font-size:40px; color:#ccc; }
		.drFileItem .drFileName{ font-size:12px; color:#333; width:70px; line-height:14px; overflow:hidden;  height:14px; display:inline-block;}
		.drFileItem .drDel{height:17px; line-height:17px;float:left; width:35px; text-align:center;display:inline-block; }
	
	</style>
	
</head>
<body>

	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="#">合同管理</a></li>
				<li><a href="#">合同台帐</a></li>
				<li class="active">新增台帐</li>
			</ol>
		</div>
		<div class="varyBox">
			<div class="btnlist">
				<a href="#" class="btn_blue" id="sure" onclick="addContractList();" >确认</a>
				<a href="#" id="close" class="btn_red" >关闭</a>
			</div>
			<p class="varyTitBg">新增台帐</p>
			<div class="tabBoxBase varyInnerBox">
				<p class="varyTitSm">合同概述</p>
				<div class="varyInput clauseHeight">
				<form id="form1">
					<table class="table table-bordered ">
						<thead>
							<tr>
								<th width="120px"></th>
								<th></th>
								<th width="120px"> </th>
								<th></th>
							</tr>
						</thead>
						
						<tbody>
							<tr>
								<td class="varyTabTit">合同标题*</td>
								<td colspan="3"><input type="text" class="input inTxt" id="contractName" name="contractName"/></td>
							</tr>
							<tr>
								<td class="varyTabTit">甲方单位*</td>
								<td><input type="text" class="input inTxt" id="firstPartyUnit" name="firstPartyUnit"/></td>
								<td class="varyTabTit">乙方单位*</td>
								<td><input type="text" class="input inTxt " id="secondPartyUnit" name="secondPartyUnit"/></td>
							</tr>
							<tr>
								<td class="varyTabTit">甲方负责人*</td>
								<td><input type="text" class="input inTxt" id="firstPartyPrincipal" name="firstPartyPrincipal"/></td>
								<td class="varyTabTit">乙方负责人*</td>
								<td><input type="text" class="input inTxt" id="secondPartyPrincipal" name="secondPartyPrincipal"/></td>
							</tr>
							<tr>
								<td class="varyTabTit">甲方联系方式*</td>
								<td><input type="text" class="input inTxt" id="firstPartyContactWay" name="firstPartyContactWay"/></td>
								<td class="varyTabTit">乙方联系方式*</td>
								<td><input type="text" class="input inTxt" id="secondPartyContactWay" name="secondPartyContactWay"/></td>
							</tr>
							<tr>
								<td class="varyTabTit">合同类型*</td>
								<td>
									<select class="input" id="contractTypeCode" name="contractTypeCode">
										<c:forEach items="${contractType }" var="conType">
										    <option value="${conType.id }">${conType.typeName }</option>
										</c:forEach>
									</select>	
								</td>
								<td class="varyTabTit">签约时间*</td>
								<td><input type="text" class="input inTxt" id="signTime" name="signTime"/></td>
							</tr>
								<tr>
								<td class="varyTabTit">合同金额*</td>
								<td>
									<input type="text" class="input floatNum inTxt" id="contractMoney" name="contractMoney"/>									
								</td>
								<td class="varyTabTit">币种</td>
								<td>
									<select class="input"  id="currencyId" name="currencyId">
										<c:forEach items="${contractCurrencyType }" var="type">
										   <option value="${type.id }">${type.name }</option>
										</c:forEach>
									</select>	
								</td>
							</tr>
							<tr>
								<td class="varyTabTit">资金来源</td>
								<td>
									<select class="input" id="fundSource" name="fundSource">
										<c:forEach items="${capitalSource }" var="source">
										   <option value="${source.id }">${source.name }</option>
										</c:forEach>
									</select>	
								</td>
								<td class="varyTabTit">付款方式</td>
								<td>
									<select class="input" id="paymentMethod" name="paymentMethod">
										<c:forEach items="${paymentMethod }" var="method">
										    <option value="${method.id }">${method.name }</option>
										</c:forEach>
									</select>	
								</td>
							</tr>
							<tr>
								<td class="varyTabTit">合同开始时间*</td>
								<td><input type="text" id="contractStartTime" name="contractStartTime" class="input inTxt" /></td>
								<td class="varyTabTit">合同结束时间*</td>
								<td><input type="text" class="input inTxt" id="contractEndTime" name="contractEndTime"/></td>
							</tr>
							<tr>
								<td style="height:80px; line-height:80px;" class="varyTabTit">合同标的</td>
								<td colspan="3"><textarea class="conTextIn" id="contractObject"></textarea></td>								
							</tr>
							<tr>
								<td style="height:80px; line-height:80px;" class="varyTabTit">合同文件</td>
								<td colspan="3">
									<div class="fileBox" id="text">
									
									</div>
								
									<div id="fileProjDiv" style="display:none"></div>
									<div id="fileDiv" style="display:none"></div>
									<div class="fileBtn">
										<a href="#" class="btn_blue" id="butFile">文件上传</a>
									</div>
								</td>								
							</tr>
							<tr>
								<td style="height:100px; line-height:100px;" class="varyTabTit">备注</td>
								<td colspan="3"><textarea class="conTextIn" id="contractDesc"></textarea></td>								
							</tr>
						</tbody>
					</table>
					</form>
				</div>	
				
							
			</div>
			<div class="tabBoxFit varyInnerBox">
				<p class="varyTitSm">付款条款</p>
				<div class="varyInput clauseHeight" id="payTerm">
					<div data-mes='0' class="termList" style="display:none;"></div>
				
					<p class="btnAdd" id="addTerm">添加</p>
				</div>	
			</div>
		</div>
	</div>
<script>
$(".tabBoxBase").width($('.varyBox').width()-504)
$("#addTerm").click(function(){
	const dataMes=$(".termList:last").attr('data-mes')-0;
	var num =dataMes>8?dataMes+1:'0'+(dataMes+1);
	var str="<div data-mes='"+(dataMes+1)+"' class='termList'><i class='closeBtn'>×</i>"+
				"<p class='termTit'>条件"+num+"</p>"+
				"<div class='termBox'>"+							
			    "<div class='termBoxList'><i></i>"+
				"<span class='span1'>条件：</span>"+							
				"<input type='text'  value='' class='input payTitle' style='width:240px'>	</div>"+
				"<div class='termBoxList'>"	+
				"<input name='payFor"+(dataMes+1)+"' type='radio' class='radio ' checked value='1'/><span class='span2'>付款金额：</span>"+
				"<input type='text'  value='' class='input floatNum payTitle'><span>(单位：元)</span></div>"+
				"<div class='termBoxList'>"+
				"<input name='payFor"+(dataMes+1)+"' type='radio'  class='radio ' value='2'/>"+
				"<span class='span2'>付款百分比：</span>"+
				"<input type='text'  value='' class='input intNumHundred'>"+
				"<span>(单位：%)</span></div></div></div>";
	$(".termList[data-mes='"+dataMes+"']").after(str);
	$("input:radio").click(function(){
		   checkClick()
	})
	  $(".floatNum").blur(function(){
			 var REX=/^([1-9]\d*|0)(\.\d{1,2})?$/;
			if(!REX.test($(this).val())){
				$(this).addClass("error");
			}
		  })
		$("input").focus(function(){
			$(this).removeClass("error")
		})
			$(".intNumHundred").blur(function(){
		        var reg=/^([1-9]\d{0,1}|100)$/;
		        if(reg.test($(this).val())==false)
		        {
		    		$(this).addClass("error");
		        }
		    })
	$(".termList input").blur(function(){
		$(this).prev().prev().attr("data-main",$(this).val())
	})
	$(".closeBtn").click(function(){
		$(this).parent().remove();
		var nbr=0;
		$.each($(".termList"),function(){
			var nbrs =nbr>8?nbr:'0'+(nbr);		
			$(this).attr("data-mes",nbr)
			$(this).children('.termTit').html("条件"+nbrs);
			$(this).find("input:radio").attr("name","payFor"+nbr)
			nbr++;
		})
	})
})
$(".closeBtn").click(function(){
	$(this).parent().remove();
	var nbr=0;
	$.each($(".termList"),function(){
		var nbrs =nbr>8?nbr:'0'+(nbr);		
		$(this).attr("data-mes",nbr)
		$(this).children('.termTit').html("条件"+nbrs);
		$(this).find("input:radio").attr("name","payFor"+nbr)
		nbr++;
	})
})
$(".termList input").blur(function(){
	$(this).prev().prev().attr("data-main",$(this).val())
})
$("#contractStartTime").datetimepicker({
	lang:'cn',
	timepicker:false,
	format:'Y-m-d',
	formatDate:'Y-m-d',
});
$("#contractEndTime").datetimepicker({
	lang:'cn',
	timepicker:false,
	format:'Y-m-d',
	formatDate:'Y-m-d',
});
$("#signTime").datetimepicker({
	lang:'cn',
	timepicker:false,
	format:'Y-m-d',
	formatDate:'Y-m-d',
});
$("input:radio").click(function(){
	   checkClick()
})
function checkClick(){
	$.each($(".termList"),function(){
		if($(this).find("input:radio:checked").val()=="1"){
			$(this).find("input:radio:checked").next().next().addClass("payTitle")
			$(this).find("input:radio:checked").parent().next().children("input:text").removeClass("payTitle")
		}else if($(this).find("input:radio:checked").val()=="2"){
			$(this).find("input:radio:checked").next().next().addClass("payTitle")
			$(this).find("input:radio:checked").parent().prev().children("input:text").removeClass("payTitle")
		}	
	})   
}
$(function(){
    var i=0;
    var tpl="";
    $("#butFile").click(function() {
        var index=i++;		         
        tpl = "<input type='file' name='file" + index + "' id='file" + index + "'/><br>";
        $("#fileDiv").append(tpl);
        $('#file' + index + '').click();
        $('#file' + index + '').change(function () {
            var file= document.getElementById("file"+index+"").files[0];         
         	var tpFile="<div title='"+file.name+"' class='drFileItem' id='del"+index+"'><i class='fa fa-file'></i><br><a class='drFileName drFileNames' href='javascript:;'>"+file.name+"</a><a class='drDel btn_red' href='javascript:removeFile("+index+");' >删除</a></div>"
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
function removeFile(index){
	if(confirm("是否删除？？？")){
	 	$('#file' + index + '').remove();
	 	$('#del'+index+'').remove();
	}

}
</script>
</body>

</html>