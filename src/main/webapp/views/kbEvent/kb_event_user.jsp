<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/layui/css/layui.css">

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>

	<script src="<%=request.getContextPath()%>/plugins/layui/layui.js" > </script>

<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style>
.layui-layer-page .layui-layer-title {background: #3b85c7 !important;color: #fff !important;}

	.search{ width:600px; height:60px; margin:30px auto }
	.search #kbEvent{outline:none;border:1px solid #ccc; padding: 0 10px; font-size:18px; width:400px; height:40px; float:left; margin-top:10px;margin-left:10px; border-radius:5px 0 0 5px; }
	.search #kbButton{outline:none; width:100px; height:40px; background:#3385ff; color:#fff;line-height:40px; text-align:center;float:left;border:none;border-radius:0 5px 5px 0;margin-top:10px;}
	#kbEventDiv{ width:600px; height:350px; margin: 0 auto; }
	#kbEventDiv li{ width:600px; float:left; height:60px;margin-bottom:5px;}
	#kbEventDiv li a{ height:20px; line-heght:20px; width:500px;margin-left:40px; float:left; font-size:16px;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
#kbEventDiv li div{color:#999; width:400px;display:block ;margin-left:50px;height:16px;line-height:16px;font-size:12px; overflow: hidden;white-space: nowrap;text-overflow: ellipsis;}
#kbEventDiv li span{ color:#67da67; height:16px;line-height:16px;width:100%;padding-left:50px;font-size:12px;}
#PageList{text-align:center; width:100%; height:40px; float:left; }
#kbEventFileDiv{width:100%; display:none;}
#kbEventFileDiv p{ width:90%; line-height:20px;}

</style>
<body>
<div class="mRight">
	<div class="mRpos">
		<ol class="breadcrumb">
		    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
		    <li><a href="javascript:;">知识库</a></li>
		    <li class="active">知识库</li>
		</ol>
	</div>
	<div class="search" onkeydown="SearchBtn(event);">
	
		<input type="text" id="kbEvent" name="kbEvent" >
		<input type="button" id="kbButton"  value="检索" title="检索一下" placeholder="请输入关键字" />
	

	</div>
	<div id="bankMes" style="width:450px; margin:0 auto;"></div>
	<ul id="kbEventDiv">
	</ul>
	<div id="PageList"></div>
	<div id="kbEventFileDiv">
	<table class="table table-bordered  text-center" style="width:90%; margin:10px auto;">
		<tbody>
			<tr>
				<td width="160px">事件名称</td>
				<td class="nostr" id="EventName"></td>
			</tr>
			<tr>
				<td width="160px;">事件描述</td>
				<td class="nostr" id="EventDesc"></td>
			</tr>
			<tr>
				<td width="160px">解决方案</td>
				<td class="nostr" id="EventPlan"></td>
			</tr>
			<tr>
				<td width="160px">关联文件</td>
				<td class="nostr" id="EventFile"></td>
			</tr>
		</tbody>
	
	
	</table>

	</div>
	
</div>


<script type="text/javascript">
$(function(){

	$('#btnLook').click(function(){
		
	})


})
function SearchBtn(event){
	
	  if (event.keyCode==13)   //回车键的键值为13
	     document.getElementById("kbButton").click();  //调用登录按钮的登录事件
}

   $(function(){
	   $("#kbButton").click(function(){
		   var kbEvent = $("#kbEvent").val();
		   $.ajax({
			   type:"post",
			   url:getPath()+"/selectKbEventCondition.do",
			   data:{"kbEvent":kbEvent},
			   success:function(data){
				   document.getElementById('kbEventDiv').innerHTML = "";
				  
 				   var bttt="<li style='height:60px;line-height:60px font-size:24px; color:red; '>没有找到任何相关信息！！！！</li>"
				    
					
				   layui.use(['laypage', 'layer'], function(){
					   var laypage = layui.laypage
					   ,layer = layui.layer;
					   laypage.render({
						    elem: 'PageList'
						    ,count: data.length
						    ,limit: 5
						    ,theme: '#1E9FFF'
						    ,jump: function(obj){
						      //模拟渲染
						      document.getElementById('kbEventDiv').innerHTML = function(){
						        var arr = []
						        ,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
						        layui.each(thisData, function(index, item){
						        	
						        	if(typeof(item.event_name)=="undefined"){
						        		
						        		item.event_name="未命名！！！";
						        	}
						        	if(typeof(item.event_resolvent)=="undefined"){
						        		item.event_resolvent="没有改事件的解释！！！";
						        	}
						        	if(typeof(item.assetsName)=="undefined"){
						        		item.assetsName="没有涉及资产！！！";
						        	}
									 arr.push("<li><a href='javascript:;' id='btnLook' onclick='sure(\""+item.id+"\")' >"+item.event_name+"</a><div>解决方案:"+item.event_resolvent+"</div><span>涉及资产:"+item.assetsName+"</span></li>");
						        });
						        return arr.join('');
						      }();
						    }
						  });
				   })
				   if(data.length=='0'){
						
						$("#bankMes").html(bttt);
					}else{
						$("#bankMes").html("");
					}
			   }
		   })
	   })
   })
   function sure(id){
	 
	   $.ajax({
		   type:"post",
		   url:getPath()+"/selectKbEventById.do",
		   data:{"id":id},
		   success:function(data){
			  
			   var kbEvent = data.selectKbEventById;
			   var kbEventFile = data.selectKbEventFileById;
			   $("#EventName").html(kbEvent.eventName);
			   $("#EventDesc").html(kbEvent.eventDesc);
			   $("#EventPlan").html(kbEvent.eventResolvent);
			   var str="";
			   for(var i = 0;i<kbEventFile.length;i++){
				    
					str += "<a href='#' style='display:inline-block;' onclick='downFile(\""+kbEventFile[i].fileId+"\")'>"+kbEventFile[i].fileName+"</a><br>"
							
			   }
			   $("#EventFile").html(str)	
			   layui.use('layer', function(){
			  		var layer = layui.layer;
			  
			  			layer.open({
				    		type:1,
				    		title:'查看详情',
				   	 		shadeClose: false,
				   			area: ['652px', '260px'],
				    		content: $('#kbEventFileDiv').html()
				  		})
				
					});
			 
		   }
	   })
	
		
	  
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