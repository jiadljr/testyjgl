<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys_class.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>

	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>

<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style type="text/css">
#pushStaff,#dutyUserText{width:480px; height:140px ;background:#fff; cursor:pointer;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;}
#pushStaff a,#dutyUserText a{overflow: hidden; text-overflow: ellipsis;white-space: nowrap;position:relative; display:inline-block; text-align:center; float:left; line-height:24px;height:24px; width:70px; margin:5px; background:#ff6900;color:#fff; }
	#pushStaff a i,#dutyUserText a i{ display:none;}
	#pushStaff a:hover,#dutyUserText a:hover{text-decoration: none; cursor: pointer;}
	#pushStaff a span,#dutyUserText a span{ font-size:20px;z-index:99999; position:absolute;right:0; top:-3px; display:block; height:20px; width:20px;color:#fff; }
    #planDiv {width:100%; float:left;}
    
		.drFileItem{ width:70px; margin:5px; float:left; height:95px; text-align:center; float:left;}
		.drFileItem i{ font-size:40px; color:#ccc; }
		.drFileItem .drFileName{ font-size:12px; color:#333; width:70px; line-height:14px; overflow:hidden;  height:28px; display:inline-block;}
		.drFileItem .drDel{height:17px; line-height:17px; width:35px; text-align:center;display:inline-block; }
			.titline{ width:90%; height:30px; line-height:30px; border-bottom:1px solid #aaa; float:left; }
	
    .addBox{ box-shadow:0 0 4px #ccc; margin:5px auto; height:900px; position:relative;}
    .workPlanTit{ width:100%; height:40px;font-size:22px;color:#347fce;border-bottom:1px solid #b0ccf3; }
	.work_group{ height:40px; width:100%;float:left; line-height:40px;}
		.work_group label{ width:120px; height:40px;  display:inline-block;text-align:right; float:left;}
	.work_group .inputTxt{padding:0 10px; width:280px; height:26px; line-height:26px; display:inline-block; margin-top:7px; border:1px solid #ccc; border-radius:4px;}
		#form1 .work_group .inputAuto{width:100px;}
	.work_group select{width:100px;height:26px; line-height:26px; display:inline-block; float:left;margin-top:7px; border:1px solid #ccc; border-radius:4px; }
	.work_group textarea{padding:0 10px; width:480px; height:80px; line-height:16px; display:inline-block; float:left;margin-top:7px; border:1px solid #ccc; border-radius:4px;}
		.btnList{ width:200px;height:40px; float:left; text-align:right; float:right;}
	.btnList a{padding:3px 12px; color:#fff; margin:10px 5px; background-color: #337ab7; display: inline-block;font-size: 14px;text-align: center;white-space: nowrap;vertical-align: middle; cursor: pointer;border: none;border-radius: 4px;}
	.proRight{ width:400px; position:absolute; right:5px;  hieght:500px;}
</style>
</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="javascript:;">个人办公</a></li>
			     <li><a href="javascript:;">工作计划</a></li>
			    <li class="active">新增工作计划</li>
			</ol>
		</div>
		<div class="addBox">
			<form id="form1">
			  <input type="hidden" id="workId" name="workId" value="${check.id }">
			  <input type="hidden" id="hidBeginTime" value="${check.planBeginTime }">
			  <input type="hidden" id="hidPlanState" name="hidPlanState" value="${check.planState }">
			  <input type="hidden" id="hidFinishTime" value="${check.planFinishTime }">
			  <input type="hidden" id="hidParticipant" value="${check.participant }">
			  <input type="hidden" id="hidParticipantName" value="${check.participantName }">
			  <input type="hidden" id="hidplanTime" value="${check.planTime }">
			  <input type="hidden" id="hidDutyUserTextId" value="${check.pushStaff }">
			  <input type="hidden" id="hidDutyUserTextName" value="${check.pushStaffName }">
			<div class="workPlanTit">　新增工作计划 	 <div class="btnList">
			  <a id="but" class="btn_blue">提交</a>
			  <a id="cancel" class="btn_red">取消</a>
		</div></div>
			<div class="work_group">
				<label >标题*：</label>
				<input type="text" id="planHeadline" name="planHeadline" value="${check.planHeadline }" class="inputTxt error_null" />
			</div>
			<div class="work_group">
				<label >类型*：</label>
				<input type="radio" id="workType" name="workType" value="1" <c:if test="${check.workType eq '1'}">checked</c:if>>个人计划
	       	    <input type="radio" id="workType" name="workType" value="2" <c:if test="${check.workType eq '2'}">checked</c:if>>部门计划<br>
			</div>
	
			<div class="work_group">
				<label >计划类型*：</label>
				<select id="planType" name="planType">
	       		   <option value="1" <c:if test="${check.planType eq '1'}">selected</c:if>>周计划</option>
	       		   <option value="2" <c:if test="${check.planType eq '2'}">selected</c:if>>月计划</option>
	       		   <option value="3" <c:if test="${check.planType eq '3'}">selected</c:if>>年计划</option>
	       		   <option value="4" <c:if test="${check.planType eq '4'}">selected</c:if>>自定义</option>
	       	  	</select>
			</div>
			<div class="work_group">
				<label >日期*：</label>
				<input type="text" style="width:100px;" id="planTime" name="planTime" class="inputTxt error_null" />
			</div>
			<div class="work_group">
				<label>起止日期*：</label>
				<input type="text" id="planBeginTime" class="inputTxt error_null" name="planBeginTime" style="width:100px;">至<input style="width:100px;" class="inputTxt error_null" type="text" id="planFinishTime" name="planFinishTime">				
			</div>
			<div class="work_group">
				<label>提醒：</label>
				  <input type="radio" id="remind" name="remind" value="1" <c:if test="${check.remind eq '1'}">checked</c:if>>不提醒
				  <input type="radio" id="remind" name="remind" value="2" <c:if test="${check.remind eq '2'}">checked</c:if>>每天
				  <input type="radio" id="remind" name="remind" value="3" <c:if test="${check.remind eq '3'}">checked</c:if>>每周
				  <input type="radio" id="remind" name="remind" value="4" <c:if test="${check.remind eq '4'}">checked</c:if>>每月
				  <input type="radio" id="remind" name="remind" value="5" <c:if test="${check.remind eq '5'}">checked</c:if>>自定义
			</div>
			<div class="work_group">
				<label></label>
				<input type="text" id="remindTime" class="inputTxt" name="remindTime" disabled="disabled" style="width: 100px">
				<select style="display:none;" id="remindWeek" name="remindWeek" class="inputTxt inputAuto" style="width:100px;" >
					<option value="1" <c:if test="${check.remindTime eq '1'}">selected</c:if>>星期日</option>
					<option value="2" <c:if test="${check.remindTime eq '2'}">selected</c:if>>星期一</option>
					<option value="3" <c:if test="${check.remindTime eq '3'}">selected</c:if>>星期二</option>
					<option value="4" <c:if test="${check.remindTime eq '4'}">selected</c:if>>星期三</option>
					<option value="5" <c:if test="${check.remindTime eq '5'}">selected</c:if>>星期四</option>
					<option value="6" <c:if test="${check.remindTime eq '6'}">selected</c:if>>星期五</option>
					<option value="7" <c:if test="${check.remindTime eq '7'}">selected</c:if>>星期六</option>
				</select>
				<select style="display:none;"  id="remindMonth" name="remindMonth"  class="inputTxt inputAuto" style="width:100px;" >
					
				</select>
				<input type="text" id="remindAuto" value="${check.remindTime }" class="inputTxt" name="remindTime"   style="width: 100px;display:none;">
				
			</div>
			<div class="work_group" style="height:100px">
				<label>内容：</label>
				<textarea id="content" name="content">${check.content }</textarea>
			</div>
			<div class="work_group radioBox" style="display:none">
				<label> 负责人：</label>
				<select id="principal" name="principal">
		     		<c:forEach items="${userByRoleType }" var="oper">
						<option value = "${oper.id }">${oper.name }</option>
					</c:forEach>
		     	</select>
			</div>
	

		<div id="planDiv" class="work_group radioBox" style="height:150px; display:none;" >
		
		        <label for="role_name" >参与人<span style="color:red;font-size:16px;">*</span>：</label>
				<div class="roleList" style="position:relative;">
					<div style="" id="dutyUserText" ></div>
						<select id="duty_user" name="duty_user"  class="form-control" multiple style="position:absolute;left:120px;top:135px;z-index:9; width:480px; height:80px; display:none;" size="5" >
							<c:forEach items="${userByRoleType }" var="oper">
								<option value = "${oper.id }">${oper.name }</option>
							</c:forEach>
					</select>				
			   </div>
		 </div>
		 	<div class="work_group">
				<label>推送：</label>
				<input type="checkbox" id="check" checked style="float:left; margin-top:14px;" />
				<div class="roleList pushStaff" style="position:relative;height:150px; margin-top:40px; width:480px; margin-left:120px; display:none">
					<div style="" id="pushStaff"></div>
					<select id="selPlan_user" name="selPlan_user"  class="form-control" multiple style="position:absolute;left:0;top:135px; width:480px; height:80px; display:none;" size="5" >
						<c:forEach items="${userByRoleType }" var="oper">
							<option value = "${oper.id }">${oper.name }</option>
						</c:forEach>
					</select>				
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
		
	</div>
	<input type="hidden" value="${check.remindTime }" id="remidHide">
		<script>
	var nstr="";
	var remidHide = $("#remidHide").val();
	for(var i=1;i<=31;i++){
		if(remidHide==i){
			nstr+="<option selected='selected'>"+i+"</option>"
		}else{
			nstr+="<option>"+i+"</option>"
		}
		
	}
	$("#remindMonth").append(nstr);
	function remindRun(){
		if($("input:radio[name='remind']:checked").val()==1||$("input:radio[name='remind']:checked").val()==2){
			$("#remindTime").show();
			$("#remindWeek").hide();
			$("#remindMonth").hide();
			$("#remindAuto").hide();
		}else if($("input:radio[name='remind']:checked").val()==3){
			$("#remindTime").hide();
			$("#remindWeek").show();
			$("#remindMonth").hide();
			$("#remindAuto").hide();
			
		}else if($("input:radio[name='remind']:checked").val()==4){
			$("#remindTime").hide();
			$("#remindWeek").hide();
			$("#remindMonth").show();
			$("#remindAuto").hide();
			
		}else if($("input:radio[name='remind']:checked").val()==5){
			$("#remindTime").hide();
			$("#remindWeek").hide();
			$("#remindMonth").hide();
			$("#remindAuto").show();
			
		}
	}
	remindRun()
	 $("input[name='remind']").change(function(){
		 remindRun()
	 })
	</script>
	<script type="text/javascript">
	$(function(){
	  $("#planBeginTime").val(FormatDate($("#hidBeginTime").val()));
	  $("#planFinishTime").val(FormatDate($("#hidFinishTime").val()));
	  $("#planTime").val(FormatDate($("#hidplanTime").val()));
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
		var participantId = $("#hidParticipant").val();
		var participantName = $("#hidParticipantName").val();
		if(participantId != ""){
		var idUsers = participantId.split(",");
		var nameUsers = participantName.split(",");
		var str="";
		$("#dutyUserText").empty();
		for(var i=0;i<idUsers.length;i++){
			str+="<a href='#' title='"+nameUsers[i]+"'>"+nameUsers[i]+"<i>"+idUsers[i]+"</i><span>×</span></a>"
			$.each($("#duty_user option"),function(){
				if($(this).val()==idUsers[i]){
					$(this).addClass("bulingbuling");
					$(this).hide();
				}
			})
		}
		$("#dutyUserText").append(str);
		
		$("#dutyUserText a span").click(function(e){
			$(this).parent().remove();
			e.stopPropagation()
			var newidcode=$(this).prev().text();
			$.each($("#duty_user option"),function(){
				if($(this).val()==newidcode){
					$(this).removeClass("bulingbuling");
					$(this).show();
				}
			})
			
		})	
	  }
	})
	</script>
		<script>
	  	function NumZero(id){
 			return id=id>=10?id:"0"+id;
 		}
	    var tDate=new Date();         
        var tYear=tDate.getFullYear()
    	var tMonth=tDate.getMonth()+1;      
   
        var tDay=tDate.getDate();
 		tMonth=NumZero(tMonth);
 		tDay = NumZero(tDay);
 		var today=tYear+"-"+tMonth+"-"+tDay;
 		$("#planTime").val(today)
 		selectPlan();
		function getInput(){
			var str=$("#planTime").val();
			var oYear=str.substr(0,4)-0;
			var oMonth=str.substr(5,2)-0;
			var oDay = str.substr(8,2)-0;
			return [oYear,oMonth,oDay];
		}
 		function outYear(){
 			var Years=getInput();
 			var starYears=Years[0]+"-"+"01"+"-"+"01";
 			var endYears=Years[0]+"-"+"12"+"-"+"31";
 			return [starYears,endYears]
 		}
 		function outMonth(){
 			var Months=getInput();
 	        var nDate=new Date(Months[0],(Months[1]-0),0);
 	        var dayNum=nDate.getDate(); 
 	        dayNum=NumZero(dayNum);
 	        var newM=NumZero(Months[1])
 	        var stratMonth = Months[0]+"-"+newM+"-"+"01";
 	        var endMonth =Months[0]+"-"+newM+"-"+dayNum;
 	        return [stratMonth,endMonth]			
 		}
 		function outWeek(){
 			var week=getInput();
 			var wM=week[1]-0-1;
 			var wW=week[2]-0;
 			var wDate=new Date(week[0],wM,wW);
 			var tWeek=wDate.getDay();
 			var startDate=new Date(week[0],wM,(wW-tWeek))
 			var sY=startDate.getFullYear();
 			var sM=startDate.getMonth()+1;   
 			var sD=startDate.getDate();
 			sM=NumZero(sM);
 			sD=NumZero(sD);
 			var startWeek=sY+"-"+sM+"-"+sD;
 			var endDate=new Date(week[0],wM,wW+(6-tWeek));
 			var eY=endDate.getFullYear();
 			var eM=endDate.getMonth()+1;
 			var eD=endDate.getDate();
			eM=NumZero(eM);
			eD=NumZero(eD);
			var endWeek=eY+"-"+eM+"-"+eD;
			return [startWeek,endWeek]
 		}
 		function selectPlan(){
     		if($("#planType option:selected").val()==1){
     			var weekPlan=outWeek();
     			$("#planBeginTime").val(weekPlan[0])
     			$("#planFinishTime").val(weekPlan[1])
     		}else if($("#planType option:selected").val()==2){
     			var MonthPlan=outMonth();
     			$("#planBeginTime").val(MonthPlan[0])
     			$("#planFinishTime").val(MonthPlan[1])
     		}else if($("#planType option:selected").val()==3){
     			var yearPaln=outYear();
     			$("#planBeginTime").val(yearPaln[0])
     			$("#planFinishTime").val(yearPaln[1])
     		}else if($("#planType option:selected").val()==4){
     			$("#planBeginTime").val("")
     			$("#planFinishTime").val("")
     		}
 		}
     	$("#planType").change(function(){
     		selectPlan();
     	})
     	$("#planTime").change(function(){
     		selectPlan();
     	})
	</script>

	  <script type="text/javascript">
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
	    
	  function checkRun(){
			if($("#check").is(":checked")==true){
				$(".pushStaff").show()
			}else{
				$(".pushStaff").hide()
			}
		}
		checkRun()
		$("#check").change(function(){
			checkRun()
		})
		function radioRun(){
			if($("input:radio[name='workType']:checked").val()==1){
				$(".radioBox").hide();
				
			}else{
				$(".radioBox").show();
			}
		}
		radioRun()
		 $("input[name='workType']").change(function(){
			 radioRun()
		 })
	  $(function(){
		
			$("#planTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#planBeginTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#planFinishTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#remindAuto").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			
		  $(".addBox").width($("body").width()-20)

		  document.getElementById("pushStaff").readOnly=true;
		  $('input[type=checkbox][name=check]').change(function() {
			  var aaa = $("#check").prop("checked");
			  if(aaa){
				  document.getElementById("pushStaff").readOnly=false;
			  }else{
				  document.getElementById("pushStaff").readOnly=true;
			  }
		  })
          document.getElementById("remindTime").readOnly=true;
		  $('input[type=radio][name=remind]').change(function() {
	            var val=$('input:radio[name="remind"]:checked').val();
	            if(val == 1 || val == 2){
	            	document.getElementById("remindTime").readOnly=true;
	            }else{
	            	document.getElementById("remindTime").readOnly=false;
	            }
		  })
		  $("#but").click(function(){
			  if($("input:radio[name='remind']:checked").val()==5&&$("#remindAuto").val()==""){
				  $("#remindAuto").addClass("error");
			  }
			  var planHeadline = $("#planHeadline").val();
			  if(planHeadline == ""){
				  $("#planHeadline").addClass("error");
			  }
			  var planTime = $("#planTime").val();
			  if(planTime == ""){
				  $("#planTime").addClass("error");
			  }
			  var planBeginTime = $("#planBeginTime").val();
			  if(planBeginTime == ""){
				  $("#planBeginTime").addClass("error");
			  }
			  var planFinishTime = $("#planFinishTime").val();
			  if(planFinishTime == ""){
				  $("#planFinishTime").addClass("error");
			  }
			  if($("#planHeadline").hasClass("error")||$("#remindAuto").hasClass("error")||$("#planTime").hasClass("error")|| $("#planBeginTime").hasClass()|| $("#planFinishTime").hasClass("error")){
				  return;
			  }
			  var idUserGroup ="";
			  var nameUserGroup = "";
			  $.each($("#dutyUserText a i"),function(){
					idUserGroup += $(this).text()+",";
					nameUserGroup += $(this).parent().attr("title")+",";
				})
				idUserGroup=idUserGroup.substring(0,idUserGroup.length-1);
				nameUserGroup=nameUserGroup.substring(0,nameUserGroup.length-1);
				var userGroup ="";
				var userGroupName = "";
				$.each($("#pushStaff a i"),function(){
				   userGroup += $(this).text()+",";
				   userGroupName += $(this).parent().attr("title")+",";
				})
				userGroup=userGroup.substring(0,userGroup.length-1);
				userGroupName=userGroupName.substring(0,userGroupName.length-1);
			  var idUserGroup ="";
			  var nameUserGroup = "";
			  $.each($("#dutyUserText a i"),function(){
					idUserGroup += $(this).text()+",";
					nameUserGroup += $(this).parent().attr("title")+",";
				})
				idUserGroup=idUserGroup.substring(0,idUserGroup.length-1);
				nameUserGroup=nameUserGroup.substring(0,nameUserGroup.length-1);
				var fileProjIds = $("#fileProjDiv").text();
				var fileIds = $("#fileDiv").text();
				var formDate = new FormData($("#form1")[0]);
				formDate.append("idUserGroup",idUserGroup);
				formDate.append("nameUserGroup",nameUserGroup);
				formDate.append("fileProjIds",fileProjIds);
				formDate.append("fileIds",fileIds);
				formDate.append("userGroup",userGroup);
				formDate.append("userGroupName",userGroupName);
			  $.ajax({
				  type:"post",
				  url:getPath()+"/updateWorkPlan.do",
				  data:formDate,
				  traditional: true,
				  async: false,
				  processData : false,
			      contentType : false,
				  success:function(data){
					  if(data == 0){
					  alert("修改失败");
					  }else{
					  location.href =getPath()+"/workPlanList.do";
					  }
				  }
			  })
		  })
		  $("#cancel").click(function(){
			  history.back(-1);
		  })
		  
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
			selPag("#dutyUserText","#duty_user")
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