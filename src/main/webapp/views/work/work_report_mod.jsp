<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style>
#form1{  margin:10px auto; box-shadow:0 0 3px #aaa; display:block; }
	.itemList{ width:100%; height:40px; line-height:40px; float:left;}
	.itemList label{ width:120px; height:40px;  display:inline-block;text-align:right; float:left;}
	.itemList .inputTxt{padding:0 10px; width:480px; height:26px; line-height:26px; display:inline-block; float:left;margin-top:7px; border:1px solid #ccc; border-radius:4px;}
	.itemList select{width:100px;height:26px; line-height:26px; display:inline-block; float:left;margin-top:7px; border:1px solid #ccc; border-radius:4px; }
	.itemList textarea{padding:10px; width:480px; height:140px; line-height:16px; display:inline-block; float:left;margin-top:7px; border:1px solid #ccc; border-radius:4px;}
	.btnList{ width:100%;height:40px; float:left; text-align:right;}
	.btnList a{padding:3px 12px; color:#fff; margin:10px 5px; background-color: #337ab7; display: inline-block;font-size: 14px;text-align: center;white-space: nowrap;vertical-align: middle; cursor: pointer;border: none;border-radius: 4px;}
		.proRight{ width:400px; position:absolute; right:5px;  hieght:500px;}
	.btnList{ width:400px;height:40px; text-align:right; float:right;}
	.btnList a{padding:3px 12px; color:#fff; margin:10px 5px; background-color: #337ab7; display: inline-block;font-size: 14px;text-align: center;white-space: nowrap;vertical-align: middle; cursor: pointer;border: none;border-radius: 4px;}
	    .workPlanTit{ width:100%; height:40px;font-size:22px;color:#347fce;border-bottom:1px solid #b0ccf3; }
	.drFileItem{ width:70px; margin:5px; float:left; height:95px; text-align:center; float:left;}
		.drFileItem i{ font-size:40px; color:#ccc; }
		.drFileItem .drFileName{ font-size:12px; color:#333; width:70px; line-height:14px; overflow:hidden;  height:28px; display:inline-block;}
		.drFileItem .drDel{height:17px; line-height:17px; width:35px; text-align:center;display:inline-block; }
		#pushStaff,#dutyUserText{width:480px; height:140px ;background:#fff; cursor:pointer;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;}
	#pushStaff a,#dutyUserText a{overflow: hidden; text-overflow: ellipsis;white-space: nowrap;position:relative; display:inline-block; text-align:center; float:left; line-height:24px;height:24px; width:70px; margin:5px; background:#ff6900;color:#fff; }
	#pushStaff a i,#dutyUserText a i{ display:none;}
	#pushStaff a:hover,#dutyUserText a:hover{text-decoration: none; cursor: pointer;}
	#pushStaff a span,#dutyUserText a span{ font-size:20px;z-index:99999; position:absolute;right:0; top:-3px; display:block; height:20px; width:20px;color:#fff; }
    #planDiv {width:100%; float:left;}
</style>
</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="javascript:;">个人办公</a></li>
			     <li><a href="javascript:;">工作汇报</a></li>
			    <li class="active">新增工作汇报</li>
			</ol>
		</div>
		<form id="form1">
		<input type="hidden" id="workId" name="workId" value="${report.id }">
		<input type="hidden" id="hidDutyUserTextId" value="${report.reportStaff }">
		<input type="hidden" id="hidDutyUserTextName" value="${report.reportStaffName }">
<div class="workPlanTit">　新增工作计划 			
<div class="btnList">
	  <a id="sub" class="btn_blue">提交</a>
	  <a id="subDraft" class="btn_green">保存草稿</a>
	  <a id="close" class="btn_red">关闭</a>
</div></div>
<div class="itemList">
	<label>标题*：</label>
	<input type="text" id="reportName" name="reportName" value="${report.reportName }" class="inputTxt error_null" />
</div>
<div class="itemList">
	<label>汇报类型*：</label>
	<select id="reportType" name="reportType" class="error_null">
 		   <option value="1" <c:if test="${report.reportType eq '1'}">selected</c:if>>自定义</option>
 		   <option value="2" <c:if test="${report.reportType eq '2'}">selected</c:if>>日报</option>
 		   <option value="3" <c:if test="${report.reportType eq '3'}">selected</c:if>>周报</option>
 		   <option value="4" <c:if test="${report.reportType eq '4'}">selected</c:if>>月报</option>
 		   <option value="5" <c:if test="${report.reportType eq '5'}">selected</c:if>>年报</option>
	   </select>
</div>
<div class="itemList selPlanList" >
	<label>推送：</label>
	<input type="checkbox" id="check" checked style="float:left; margin-top:14px;" />
		<div class="roleList reportStaff" style="position:relative;height:150px; margin-top:40px; width:480px; margin-left:120px; display:none">
			<div style="" id="pushStaff"></div>
			<select id="selPlan_user" name="selPlan_user"  class="form-control" multiple style="position:absolute;left:0;top:135px; width:480px; height:80px; display:none;" size="5" >
				<c:forEach items="${userByRoleType }" var="oper">
					<option value = "${oper.id }">${oper.name }</option>
				</c:forEach>
			</select>			
	</div>
</div>
<div class="itemList" style="height:150px;">
	<label>内容*：</label>
	<textarea id="reportContent" class="error_null" name="reportContent">${report.reportContent }</textarea> 
</div>
	<div class="proRight">
				<p class="titline">相关文件</p>
				<div id="fileProjDiv" style="display:none"></div>
				<div id="fileDiv" style="display:none"></div>
				<div class="fileTab taskList">
					<a class="text-center btn btn_blue proEditBtn" style="padding:0 12px; margin-top:9px;margin-left:20px; " id="butFile" >选择文件</a>									
				</div>
				<div id="fileListDiv" style="width:406.63px; float:left;" ><!-- 存放返回的文件集合 -->
					<c:forEach items="${selectPlanFile }" var="file">	
						<div title="${file.sfName }" class='drFileItem' id="${file.sfId }"><i class='fa fa-file'></i><br><a class='drFileName drFileNames' href='javascript:;'>${file.sfName }</a><a class='drDel btn_green'  href='#' onclick="downFile(${file.sfId })">下载</a><a class='drDel btn_red proEditBtn' href='javascript:void(0);' onclick="delFile(${file.sfId },${file.plaFileId })">删除</a>
						</div>
					</c:forEach>
				</div>
				
				<div id="app" style="display: none;"></div>			
				<div id="text" >
				
				</div>	
			</div>
       </form>
  </div>
<script type="text/javascript">
var hidDutyUserTextId = $("#hidDutyUserTextId").val();
var hidDutyUserTextName = $("#hidDutyUserTextName").val();
if(hidDutyUserTextId != ""){
var idUser = hidDutyUserTextId.split(",");
var nameUser = hidDutyUserTextName.split(",");
var str="";
$("#pushStaff").empty();
for(var i=0;i<idUser.length;i++){
	str+="<a href='#' title='"+nameUser[i]+"'>"+nameUser[i]+"<i>"+idUser[i]+"</i><span>×</span></a>"
	$.each($("#selPlan_user option"),function(){
		if($(this).val()==idUser[i]){
			$(this).addClass("bulingbuling");
			$(this).hide();
		}
	})
}
$("#pushStaff").append(str);

$("#pushStaff a span").click(function(e){
	$(this).parent().remove();
	e.stopPropagation()
	var newidcode=$(this).prev().text();
	$.each($("#selPlan_user option"),function(){
		if($(this).val()==newidcode){
			$(this).removeClass("bulingbuling");
			$(this).show();
		}
	})
})	
}
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
    function delFile(fileId,projFileId){
	$("#fileProjDiv").append(projFileId+",");
	$("#fileDiv").append(fileId+",");
	$("#"+fileId).remove();
  }
  function remove(index){
	$('#file' + index + '').remove();
	$('#del'+index+'').remove();
  }
</script>

<script type="text/javascript">

function delPag(boxId,selId){
	  $(boxId+" a span").click(function(e){
			$(this).parent().remove();
			e.stopPropagation()
			var newidcode=$(this).prev().text();
			$.each($(selId+" option"),function(){
				if($(this).val()==newidcode){
					$(this).removeClass("bulingbuling");
					$(this).show();
				}
			})
			
		})
}

	function selPag(boxId,selId){
	  $(boxId).click(function(e){
			$(this).removeClass("error");
			$(selId).show();
			$(selId).click();
			if($(selId+" option").length == $(".bulingbuling").length){
				$(selId).hide()
			}else{
				$(selId).show()
			}
			e.stopPropagation();
		})
		$(selId+" option").click(function(e){
			var text=$(this).text();
			var textId=$(this).val();
			
			$(this).hide().addClass("bulingbuling");
			e.stopPropagation();
			if($(selId+" option").length==$(".bulingbuling").length){
				$(selId).hide()
			}else{
				$(selId).show()
			}
			var pagStr="";
			pagStr+="<a href='#' title='"+text+"'>"+text+"<i>"+textId+"</i><span onClick='delPag('"+boxId+"','"+selId+"')'>×</span></a>";
			$(boxId).append(pagStr);
			
			$(boxId+" a span").click(function(e){
				$(this).parent().remove();
				e.stopPropagation()
				var newidcode=$(this).prev().text();
				$.each($(selId+" option"),function(){
					if($(this).val()==newidcode){
						$(this).removeClass("bulingbuling");
						$(this).show();
					}
				})
			
			})
		
		})
	$(selId).blur(function(){
		$(this).hide();		
	})
	$(selId).click(function(e){
		e.stopPropagation();
	})
	$(document).click(function(){
		$(selId).hide()
	})
}
	selPag("#pushStaff","#selPlan_user")
	delPag("#pushStaff","#selPlan_user")
checkRun();

function checkRun(){
	if($("#check").is(":checked")==true){
		$(".reportStaff").show();
		$(".selPlanList").height(200)
	}else{
		$(".reportStaff").hide()
		$(".selPlanList").height(40)
	}
}
$("#check").change(function(){
	checkRun()
})
$(function(){
	$("#form1").width($("body").width()-30);
	$("#form1").height($("body").height()-65);
	$("#sub").click(function(){
		var reportName = $("#reportName").val();
		if(reportName == ""){
			$("#reportName").addClass("error");
		}
		var reportType = $("#reportType").val();
		if(reportType == ""){
			$("#reportType").addClass("error");
		}
		var  reportContent = $("#reportContent").text();
		if(reportContent == ""){
			$("#reportContent").addClass("error");
		}
		if($("#reportName").hasClass("error")||$("#reportType").hasClass("error")|| $("#reportContent").hasClass("error")){
			  return;
		  }
		var idUserGroup ="";
		var nameUserGroup = "";
		$.each($("#pushStaff a i"),function(){
		   idUserGroup += $(this).text()+",";
		   nameUserGroup += $(this).parent().attr("title")+",";
	    })
		idUserGroup=idUserGroup.substring(0,idUserGroup.length-1);
		nameUserGroup=nameUserGroup.substring(0,nameUserGroup.length-1);
		var formData = new FormData($("#form1")[0]);
		formData.append("idUserGroup",idUserGroup);
		formData.append("nameUserGroup",nameUserGroup);
		formData.append("draft",2);
		var fileProjIds = $("#fileProjDiv").text();
		var fileIds = $("#fileDiv").text();
		formData.append("fileProjIds",fileProjIds);
		formData.append("fileIds",fileIds);
		$.ajax({
			type:"post",
			url:getPath()+"/updateWorkReport.do",
			data:formData,
			traditional: true,
			async: false,
			processData : false,
		    contentType : false,
			success:function(data){
				if(data == 0){
					alert("修改失败");
				}else{
					location.href=getPath()+"/workReportList.do";
				}
				
			}
		})
	})
	$("#subDraft").click(function(){
		var reportName = $("#reportName").val();
		if(reportName == ""){
			$("#reportName").addClass("error");
		}
		var reportType = $("#reportType").val();
		if(reportType == ""){
			$("#reportType").addClass("error");
		}
		var  reportContent = $("#reportContent").text();
		if(reportContent == ""){
			$("#reportContent").addClass("error");
		}
		if($("#reportName").hasClass("error")||$("#reportType").hasClass("error")|| $("#reportContent").hasClass("error")){
			  return;
		  }
		var idUserGroup ="";
		var nameUserGroup = "";
		$.each($("#pushStaff a i"),function(){
		   idUserGroup += $(this).text()+",";
		   nameUserGroup += $(this).parent().attr("title")+",";
	    })
		idUserGroup=idUserGroup.substring(0,idUserGroup.length-1);
		nameUserGroup=nameUserGroup.substring(0,nameUserGroup.length-1);
		var formData = new FormData($("#form1")[0]);
		formData.append("idUserGroup",idUserGroup);
		formData.append("nameUserGroup",nameUserGroup);
		formData.append("draft",1);
		var fileProjIds = $("#fileProjDiv").text();
		var fileIds = $("#fileDiv").text();
		formData.append("fileProjIds",fileProjIds);
		formData.append("fileIds",fileIds);
		$.ajax({
			type:"post",
			url:getPath()+"/updateWorkReport.do",
			data:formData,
			traditional: true,
			async: false,
			processData : false,
		    contentType : false,
			success:function(data){
				if(data == 0){
					alert("修改草稿失败");
				}else{
					location.href=getPath()+"/workReportList.do";
				}
				
			}
		})
	})
	$("#close").click(function(){
		window.history.go(-1);
	})
})
	//文件下载
	  //
	  function downFile(fileId){
	  	$.ajax({
	  		data:{fileId: fileId},
	  		dataType : 'json',
	  		async: false,
	  		type : 'post',
	  		url : getPath() + "/exportDocLoad.do",
	  		success : function(result){
	  			if (result.data == "error") {
	  				alert("文件不存在!");
	  			}else {
	  				location.href = getPath() + "/downLoad.do?fileId=" + fileId;
	  			}
	  		}
	  	})
	  }
</script>
</body>
</html>