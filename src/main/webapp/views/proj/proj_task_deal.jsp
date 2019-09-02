<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	
	<script src="<%=request.getContextPath()%>/js/index.js"></script>

	<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_task_list.js"></script>
	<style type="text/css">
		body{overflow-y:auto;overflow-x:hidden; background:#f8f8f8;}
	
		
		.schedule{ position:relative;  }
	 	.schedule span{width:60px; display:inline-block; float:left;text-align:center; position:absolute;top:4px; left:20px;}
   		.schedule p{width:500px; height:5px;margin-top:-1px; background:#aaa;float:left; position:absolute;bottom:12px;}
   		.schedule p b{ display:inline-block;height:5px; background:red;float:left;}
   		.taskMes{ width:650px; height:350px; margin:0 auto;}
   		.taskMesTab{ width:650px; height:320px; margin:0 auto;}
   		.taskList{ width:650px; height:40px; margin:0 auto;}
   		.mRtabs{  border-bottom: 1px solid #ccc;  width: 100%; height: 30px;}
		.mRtabs li{ position: relative; float:left;  width:70px; text-align:center; }
		.mRtabs li span{ height: 26px; display: block; width:100%; line-height:26px; color:#666; cursor: pointer; margin-top:4px;}
		.mRtabs .nav_on span{    border-bottom: 1px solid #3eabe7; background:#3eabe7; color:#fff;}
		.nav_on i{border:4px solid #3eabe7; margin-left:-4px; border-left-color:#fff;border-right-color:#fff;border-bottom-color:#fff;  width:4px;position:absolute ; left: 50%; bottom: -8px; height:4px; display: block;  box-sizing: border-box; }
		.taskTxt{line-height:40px;  width:180px;padding-left:5px; display:inline-block; text-align:left; height:40px; float:left;}
   		.taskList label{ line-height:40px; width:100px; display:inline-block; float:left; text-align:right;}
		.taskList .fa{ color:ccc;}
		.taskTit,.timeTask{ line-height:40px;}
		.taskDescWarp{ height:150px}
		.taskFileTab{ display:none; width:650px; height:320px; overflow-y:auto; overflow-x:hidden;float:left; }
		.taskDesc{ float:left; width:500px; margin-top:12px; line-height:16px; height:138px; oveflow-x:hidden;overflow-y:auto;}
		.text{height:280px; float:left; width:650px; oveflow-y:auto;}
		.drFileItem{ width:70px; margin:5px; float:left; height:95px; text-align:center; float:left;}
		.drFileItem i{ font-size:40px; color:#ccc; }
		.drFileItem .drFileName{ font-size:12px; color:#333; width:70px; line-height:14px; overflow:hidden;  height:28px; display:inline-block;}
		.drFileItem .drDel{height:17px; line-height:17px; width:35px; text-align:center;display:inline-block; }
		.descBox{width:650;margin:5px auto; height:105px; border:1px solid #ccc; float:left; background:#f8f8f8;}
		.descBox textarea{ display:block; box-sizing:border-box;width:640px; height:60px; float:left;margin:5px 0 5px 5px; line-height:16px; background:#fff;border-radius:5px; outline:none;}
		.descBox .descBtn{cursor:pointer; width:60px; height:30px; float:right;margin:0 5px 5px 0;display:block; background:#1476d9;  color:#fff; text-center;line-height:30px; font-size:16px;text-align:center}
		#taskDescList{width:650px; height:180px; margin:10px auto;overflow-y:auto;overflow-x:hidden;}
		#taskDescList li{border-bottom:1px solid #ccc; width:650px; float:left; min-height:20px;}
		#taskDescList li p{word-break:break-all; word-wrap:break-word; padding:0 10px;margin-top:7px; width:480px; float:left;display:inline-block; line-height:16px;}
		#taskDescList li span{ width:140px; color:blue; line-height:30px;height:30px; float:left;display:inline-block; text-align:center;}
		
	</style>
</head>
<body>
<div class="taskMes">
	<form role="form" class="form-horizontal" id="form1" method="post" enctype="multipart/form-data">
		<ul class="mRtabs">
			<li class="nav_on" id="overHide"><span>任务信息</span></li>
			<li id="overShow"><span>相关文件</span></li>
			<li id="taskShow"><span>任务记录</span></li>
		</ul>
		<div class="fileTab taskList" style="display: none; " id="reportDiv">
			<a href="javascript:report();" id="report" class="text-center btn btn_blue"  style="padding:0 12px; margin-top:9px;margin-left:20px; ">导出记录</a>
		</div>
		<div class="taskMesTab">
			<input type="hidden" id="pages" name="pages" value="${pages }">
			<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
			<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
			<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
			<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
			<input type="hidden" id="taskId" name="taskId" value="${task.id }">
			<input type="hidden" id="projCode" name="projCode" value="${projCode }">
			<input type="hidden" id="taskName" name="taskName" value="${task.nameTask }">
			<input type="hidden" id="user_name" name="user_name" value="${user_name }"/>
			<input type="hidden" id="state" name="state" value="${state }"/>
			<input type="hidden" id="projStatus" name="projStatus" value="${projStatus }"/>
			<table class="table table-bordered">
				<tbody>
					<tr>
					<td width="100px">任务名称：</td>
					<td class="nostr">${task.nameTask }</td>
					</tr>
					<tr>
					<td>负责人：</td>
					<td>${task.extend1 }</td>
					</tr>
					<tr>
						<td >任务进度：</td>
						<td class="schedule"> <span>${task.extend3 }</span><p><b style="width:${task.extend3 };"></b></p></td>
					</tr>
					<tr>
						<td>起止时间：</td>
						<td>
						<fmt:formatDate value="${task.dateStart}" pattern="yyyy-MM-dd" />
					&nbsp;至&nbsp;
					<fmt:formatDate value="${task.dateEnd}" pattern="yyyy-MM-dd" />
						</td>
					</tr>
					<tr>
						<td>任务说明:</td>
						<td class="nostr" >${task.taskDesc}</td>
					</tr>
					<tr>
						<td>阶段成果/文档:</td>
						<td class="nostr" >${task.phaseShow}</td>
					</tr>
				</tbody>
			</table>

		</div>
		<div class="taskFileTab">
			<div class="fileTab taskList" id="taskFileDiv">
		
				<a class="text-center btn btn_blue " style="padding:0 12px; margin-top:9px;margin-left:20px; " id="butFile" >选择文件</a>									
				<a class="text-center btn btn_yellow" style="padding:0 12px; margin-top:9px;"  onclick="uploading()">保存</a>									
			
			</div>
			<div id="fileListDiv" style="width:650px; float:left;" ><!-- 存放返回的文件集合 --></div>
			
			<div id="app" style="display: none;"></div>			
			<div id="text" >
			
			</div>	
						
	
		</div>
		
		<div class="taskTalkTab" style="display:none;">
			<div class="descBox" onkeydown="descEnter(event)">
				<textarea placeholder="请输入0-200个字" id="taskRecord" name="taskRecord"  class=" proEditIn error_null"></textarea>
				
				<a class="descBtn" onclick="saveTaskRecord()">保存</a>				
			</div>
			<ul id="taskDescList">
				
			</ul>
		
		</div>
		
	</form>
</div>
	
		
	<script type="text/javascript">
		$(function(){
			$("#taskRecord").click(function(){
				$(this).removeClass("error");
			})
			var state = $("#state").val();
			if(state == "projControlTask"){
				$("#taskFileDiv").hide();
				$(".descBox").hide();
			}
		})
		var projStatus=$("#projStatus").val();
		
		$("#overShow").click(function(){
			$(".mRtabs li").removeClass("nav_on");
			$(this).addClass("nav_on")
			$(".taskFileTab").show();
			if(projStatus == 96 || projStatus == 97){
				$("#taskFileDiv").hide();
			}
			$(".taskMesTab").hide();
			$(".taskTalkTab").hide();
			$("#reportDiv").hide();
			loadTaskFile();
		})
		//任务记录
		$("#taskShow").click(function(){
			$(".mRtabs li").removeClass("nav_on");
			$(this).addClass("nav_on")
			$(".taskFileTab").hide();
			$(".taskMesTab").hide();
			$(".taskTalkTab").show();
			if($("#state").val() != "projControlTask"){
				$("#reportDiv").show();
				if(projStatus == 96 || projStatus == 97){
					$("#reportDiv").hide();
					$(".descBox").hide();
				}
			}
			
			loadTaskRecord();
		})
		$("#overHide").click(function(){
			$(".mRtabs li").removeClass("nav_on");
			$(this).addClass("nav_on")
			$("#reportDiv").hide();
			$(".taskTalkTab").hide();
			$(".taskFileTab").hide();
			$(".taskMesTab").show();
		})

	</script>
	<script type="text/javascript">
	//上传
	function uploading(){
		var formData = new FormData($('#form1')[0]);
		/* var fileIds = $("#fileDiv").text();
		formData.append("fileIds",fileIds); */
		$.ajax({
			type:"post",
			url:getPath() + "/uploaddingTaskFile.do",
			data:formData,
			dataType:"text",
			traditional: true,
			async: false,
			processData : false,
	        contentType : false,
			success:function(result){
				if(result == "succ"){
					alert("成功");
					loadTaskFile();
				}
			},
			error:function(result){
				alert("错误");
			}
		})
	}
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
	    function remove(index){
	        $('#file' + index + '').remove();
	        $('#del'+index+'').remove();
	    }

		//清除所有文件
		function deleteAllFile(){
			var indexMax = $("#text .drFileItem").length;
			for (var index = 0; index < indexMax; index++) {
				$('#file' + index + '').remove();
		        $('#del'+index+'').remove();
			}
		}
		//删除文件，将项目文件id存放在div中
		/* function delFile(fileId,projFileId){
			$("#fileDiv").append(projFileId+",");
			$("#"+fileId).remove();
		} */
		//删除任务文件
		function delFile(fileId,projFileId){
			var flag = confirm("确定删除吗?");
	        if(flag){
	        	$.ajax({
	    			url:getPath()+"/deleteFile.do",
	    			data:{"fileId":fileId,"projFileId":projFileId},
	    			dataType:"text",
	    			type:"post",
	    			success:function(success){
	    				if (success == "succ") {
	    					loadTaskFile();
	    				}
	    			},
	    			error:function(){
	    				
	    			}
	    		})
	        }else{
	            return;
	        }
			
		}

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