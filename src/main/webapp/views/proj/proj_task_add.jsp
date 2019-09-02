<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/QK_file/QK_file.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/jQuery-searchableSelect/jquery.searchableSelect.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/jQuery-searchableSelect/jquery.searchableSelect.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_task_list.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/QK_file/drFile.js"></script>
	
	<script type="text/javascript">
		function closeLon(){
			var index = parent.layer.getFrameIndex(window.name);  
			setTimeout(function(){parent.layer.close(index)}, 100); 
		}
	</script>	
	<style>
		.taskBox{width:100%; margin:15px auto 0 auto;}
		.taskList{width:100%; height:36px; }
		.taskTxt label,.taskList label{display:inline-block;width:20%; height:30px; line-height:30px; margin:3px 0; float:left; text-align:right; }
		.taskList input{ width:250px; display:inline-block; border-radius:4px; border:1px solid #aaa; height:30px; box-sizing:border-box;margin:3px 0; float:left;}
		.taskList select{ width:250px;display:inline-block; border-radius:4px; border:1px solid #aaa; height:30px; box-sizing:border-box;margin:3px 0; float:left;}
		.taskList .sDate{width:16px;  height:16px; box-sizing:border-box;margin:10px 0; float:left;display:inline-block;}
		.taskTime .timeIn{ width:100px; }
		.taskTime span{ width:50px; display:inline-block;float:left;height:36px; line-height:36px;text-align:center; }
		.taskTime input{width:100px;}
		.taskTxt textarea{ width:70%; display:inline-block;  border-radius:4px; border:1px solid #aaa; height:85px; line-height:18px; box-sizing:border-box;margin:3px 0; float:left;}
		.btnlist{ width:100%; height:40px; text-align:right; position:absolute;left:0; bottom:10px;}
		.btnlist a{ padding:6px 14px; margin:5px 10px; display:inline-block;color:#fff; }
		.btn_add{ background: #1476d9;}
	 	.btn_power{background:#ff6666;}
	 	.taskList .searchable-select-input{width:100%;}
		.searchable-select{width:250px;}
	 	.searchable-has-next{display:none;}
	 	.searchable-has-privious{display:none;}
	 	.searchable-select-items{ float:left;  width:100%;overflow-y:auto; height:180px;}

	 	
	</style>
</head>
<body>
<div class="taskBox">
	<form role="form" class="form-horizontal" id="form1" method="post">
		<input type="hidden" id="pages" name="pages" value="${pages }">
		<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
		<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
		<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
		<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
		<input type="hidden" id="projCode" name="projCode" value="${projCode }">
		<input type="hidden" name="token" value="${token}" />
		<div class="taskList">
			<label for="taskName">任务名称<i style="color:red;font-size:16px;">*</i>：</label>
			<input type="text" id="taskName" name="taskName" class="needIn" data-message="项目名称不得为空！">
		</div>
		<div class="taskList">
			<label for="taskName">上级任务：</label>
			<select id="parentId" name="parentId" class="">
				<option value = "">请选择上级任务</option>
				<c:forEach items="${projTaskList }" var="projTask">
					<option value = "${projTask.id }" <c:if test="${task.id == projTask.id}">selected</c:if>>${projTask.nameTask }</option>
				</c:forEach>
			</select>
		</div>
		<div class="taskList">
			
			<label for="taskWho">任务负责人<i style="color:red;font-size:16px;">*</i>：</label>
			<select id="idTaskHead" name="idTaskHead" class="">
				<c:forEach items="${memberList }" var="member">
					<option value = "${member.idMember }" <c:if test="${task.idTaskHead == member.idMember}">selected</c:if>>${member.extend1 }</option>
				</c:forEach>
			</select>
	
						
		</div>
	
		<div class="taskList taskTime">
			<label>起止时间<i style="color:red;font-size:16px;">*</i>：</label>
			<input type="text" id="startTime" name="startTime" class="needIn">
			<span>——</span>
			<input type="text" id="endTime" name="endTime" class="needIn">
		</div>
		<div class="taskList">
			<label for="taskStartDate" >里程碑任务：</label>
			<input type="checkbox" id="_milestoneTask" name="_milestoneTask" class="sDate">		
		</div>
		<div class="taskTxt">
			<label for="taskDesc">任务说明：</label>
			
			<textarea id="taskDesc" name="taskDesc" class="form-control" data-message="任务说明不得为空！"></textarea>
			
		</div>
		<div class="taskTxt">
			<label for="taskDesc">阶段成果/文档：</label>
			
			<textarea id="phaseShow" name="phaseShow" class="form-control"></textarea>
			
		</div>
		<div class="btnlist">
			<a href="#" class="btn btn_add" id="saveTask" >确定</a>
			<a href="javascript:closeLon();" class="btn btn_power" >取消</a>
		</div>				
	</form>					
</div>

	<script type="text/javascript">
		$(function(){
			$("#parentId").searchableSelect();
			$("#startTime").datetimepicker({
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
			$("#endTime").datetimepicker({
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
			
			//提交
			$("#saveTask").click(function(){
				var  taskName= $("#taskName").val();
				var  parentId= $("#parentId").val();
				var  projCode= $("#projCode").val();
				$.ajax({
					url:getPath()+"/taskNameWhetherRepetition.do",
					data:{"taskName":taskName,"parentId":parentId,"projCode":projCode},
					dataType:'text',
					type:'post',
					success:function(suc){
						if(suc == "nameError"){
							alert("任务名称重复，请重新填写！");
						}else{
							saveTask();
						}
						
					},
					error:function(err){
						
					}
				})
			})
			function saveTask(){
				$.each($(".needIn"),function(){
					
					if($(this).val()==""||$(this).val().length==0){
						$(this).addClass("error");
					}
				})
				$("input").focus(function(){
					$(this).removeClass("error")
				})
				if($(".needIn").hasClass("error")){
					return;
				}
				var reg2 = new RegExp('^[^\\\\/：:*?"<>\?|]+$');
				//var reg = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]")
				
				var reg = new RegExp("[\"'“‘？]");
				var taskName = $("#taskName").val();
				if(reg.test(taskName) || !reg2.test(taskName)){
					alert("任务名称不能包含下列字符\\ /:：*?\"<>|'“‘？");
					return;
				}
				var startTime = $("#startTime").val();
				var endTime = $("#endTime").val();
				if (new Date(startTime).getTime() > new Date(endTime).getTime()) {
					alert("开始时间不能大于结束时间");
					return;
				}
				var milestoneTask = "";
				if($("#_milestoneTask").get(0).checked){
					milestoneTask = 1;
				}else{
					milestoneTask = 0;
				}
				$.ajax({
					url:getPath()+"/addProjTask.do",
					data:$.param({"milestoneTask":milestoneTask})+"&"+$("#form1").serialize(),
					dataType:'text',
					type:'post',
					success:function(suc){
						if(suc == "success"){
							window.parent.showPrpList();
							var index = parent.layer.getFrameIndex(window.name);  
							setTimeout(function(){parent.layer.close(index);}, 50);
						}
						if(suc == "nameError"){
							alert("任务名称重复，请重新填写！");
						}
					},
					error:function(err){
						
					}
				})
			}
		})
	</script>
</body>
</html>