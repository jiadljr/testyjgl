<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_apply_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>	
	<style>
	
		/* .mRwell_inner{
			overflow: auto;
		    overflow-x: auto;
		    overflow-y: auto;
		} */
		.fixTable .table thead tr{ background:url("<%=request.getContextPath()%>/images/thead-bg.png");}
		.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
		  .evaluate {width: 100%; }
        .evaluate .eva_inner{ width:99%; height:200px; margin :10px auto 0 auto;border-radius: 6px;background-color: #f8f8f8;  box-shadow: 3px 3px 20px 1px rgba(0,0,0,.3);}
        .evaluate .eva_tit{ width: 100%; height: 40px;text-align: center; line-height: 40px;font-size: 14px; color:#4f4e4e;text-align: center;}
        .evaluate .btn_list{ width: 100%; height:40px; line-height: 40px; text-align: right; }
        .evaluate .btn_list .btn{ padding:3px 4px; line-height:1; margin-right:15px;}
        .evaluate .eve_star {width: 80%;  margin: 0 auto; text-align: center;}
        .evaluate .eve_txt{width: 80%;  margin: 0 auto; height:70px; margin-top:10px;  }
        .evaluate .eve_txt .eve_lab{ width: 20%; float: left ;text-align: right;}
        .evaluate .eve_txt .eve_area{width: 75%; float: right ; resize: none; height: 60px; border-radius: 5px;}
		.fixTable .table th{text-align:center;}
		.relTable .table{box-sizing:border-box;margin-bottom:0;float:left; }
		.relTable .table tbody tr td{box-sizing:border-box; height:18px !important;padding:7px 8px; }
				.mQuery-2 .form-group  .form-control{ padding:0 12px;}
		
	</style>
</head>
<body >
		<div id="storage" style="display:none;"></div>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">申告</li>
				</ol>
			</div>

			<div class="mQuery-2">
				<form action="" role="form" class="form-horizontal" method="post">
				   <input type="hidden" id="pages" name="pages" value="${pages }">
                   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="form-group col-sm-4">
						<label for="dataStart" class="col-sm-3">日期：</label>
						<div class="col-sm-4">

							<input type="text" id="startTime" name="startTime" class="form-control" placeholder="请选择开始时间" >

						</div>
						<div class="col-sm-1 text-center">至</div>
						<div class="col-sm-4">

							<input type="text" id="endTime" name="endTime" class="form-control" placeholder="请选择结束时间" >

						</div>
					</div>
	
					<div class="form-group col-sm-3" >
						<label for="file_code" class="col-sm-3">状态：</label>
						<div class="col-sm-7">
							<select id="eventStatus" name="eventStatus" class="form-control">
								<option value="">请选择事件状态</option>
							    <option value="20">待受理</option>
							    <option value="50">待处理</option>
							    <option value="29">已撤销</option>
							    <option value="70">待评价</option>
							</select>
						</div>
					</div>
					
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
						
					
				</form>								
			</div>
			
			<div class="btnout">
				<input type="hidden" id="dutyNo" value = "${dutyNo }"/>
                <a class="btn btn-primary btn-sm" id="addApply"><img alt="" src="<%=request.getContextPath()%>/images/add-apply.png">添加申告</a>
            </div>
			
			
			<div class="fixTable">
				<table class="table table-bordered text-center table-hover table-striped" style="margin-bottom:0px;">
					<thead>
                		<tr>
	                		<th>标题</th>
	                		<th>申请人</th>
	                		<th>申告部门</th>
	                		<th>申告时间</th>
	                		<th>联系方式</th>
	                		<th>状态</th>
	                		<th>描述</th>
	                		<th style="width:200px;">操作</th>
                		</tr>                		
                	</thead>
				</table>
			</div>	
			<div class="relTable" style="height:65%;">
				<table class="table text-center table-bordered table-hover table-striped">
             
                	<tbody id="tbody"></tbody>
                		
                </table>
			</div>			
                	
		
			<div class="mRpage" id="page2"></div>
		</div>
	

			<div class="look_box"> <div class="mRwell_inner" style="box-shadow:0 5px 20px #d2d2d2;height:340px; width:450px;margin:5px; "> <table class=" table table-bordered table-hover text-center"> <tr> <td>申告人<input type="hidden" id="seeApplyId"></td><td id="name"></td><td>联系电话</td><td id="eventContact"></td> </tr> <tr> <td >申告部门</td><td id="deptName" colspan="3"></td> </tr> <tr> <td>事件标题</td><td id="eventName" colspan="3"></td> </tr> <tr> <td style="height:60px; line-height:60px; padding:0;">事件描述</td><td colspan="3" class="nostr" style=" height:60px;line-height:20px;padding:0;" ><div  id="eventDesc" style="height:100%;width:100%;overflow-x:hidden;overflow-y:auto;"></div></td> </tr> </table> <div class="bot_box" id="eventProcedure" style="height:115px; overflow-y:auto; overflow-x:hidden;"> </div> </div> </div>

	
<div class="evaluate" style="display:none;">
    <div class="eva_inner">
        <form  role="form" class="form-horizontal" id="form2" method="post">
            <p class="eva_tit">请您对本次服务进行评价</p>
            <input type="hidden" id="idEvent" name="idEvent" value="">
            <input type="hidden" id="codeEvent" name="codeEvent" value="">
            <input type="hidden" id="nameEvent" name="nameEvent" value="">
            <div class="eve_star star star1">
                <label for="file_code" ></label>
                <input type="hidden" id="event_assess" name="event_assess">
                <img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
                <img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
                <img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
                <img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
                <img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
            	<label class="col-sm-2" id="starTxt" style="color:#999; float:right;">优质</label>
            </div>
            <div class="eve_txt">
                <label for="remark" class="eve_lab">意见：</label>

                <textarea class="eve_area error_null" id="remark" name="remark"></textarea>

            </div>

        </form>
    </div>
    <div class="btn_list">
        <a class="btn btn-primary " id="addConfirm">提交评价</a>
	   <a class="btn btn-primary btn_red " style="border:0;" href="javascript:closePaged();">取消评价</a>
    </div>
</div>
	<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	<script>
	function closePaged(){
		layer.closeAll('page');
	}
	</script>
	<script>
	$(function(){
		
		function GetUrlStr(name){
			  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			   var r = window.location.search.substr(1).match(reg);
			   if(r!=null)
			   return unescape(r[2]);
			   return null;
		}
		function storage(npm){
			layer.open({
	            type:4,
	        	time:2000,
	            tipsMore: true,
	            title: '提示',
	            tips:[2,"#eee"],
	            closeBtn:0,
	            area: ['200px', '70px'],
	            shade: 0,
	            maxmin: false,
	            offset: 'rb',
	            content: ['<p style="color:#333"> 提示</p><p style="color:#666">　'+npm+'</p>', '#eveBox']
	        });
		}
		if(GetUrlStr('cid')=='2'){
			storage('修改成功');
			
		}
		
		
		
			
	})
	function sunFun(){
			queryApplyList();
			layer.open({
	            type:4,
	        	time:3000,
	            tipsMore: true,
	            title: '提示',
	            tips:[2,"#eee"],
	            closeBtn:0,
	            area: ['200px', '70px'],
	            shade: 0,
	            maxmin: false,
	            offset: 'rb',
	            content: ['<p style="color:#333"> 提示</p><p style="color:#666">添加成功</p>', '#eveBox']
	        });
		}
	function sunTion(){
		queryApplyList();
		layer.open({
            type:4,
        	time:3000,
            tipsMore: true,
            title: '提示',
            tips:[2,"#eee"],
            closeBtn:0,
            area: ['200px', '70px'],
            shade: 0,
            maxmin: false,
            offset: 'rb',
            content: ['<p style="color:#333"> 提示</p><p style="color:#666">修改成功</p>', '#eveBox']
        });
	}
	</script>
	<script type="text/javascript">
		$(function(){
			//评价星级的实现
			$(".star img").click(function(){
				
				if($(this).attr("src")=="<%=request.getContextPath()%>/images/star-yellow.png"){
					$(this).nextAll("img").attr("src","<%=request.getContextPath()%>/images/star-white.png").stop();
					$(this).nextAll("img").addClass('starLen');
					
				}else{
					$(this).attr("src","<%=request.getContextPath()%>/images/star-yellow.png").stop();
					$(this).prevAll("img").attr("src","<%=request.getContextPath()%>/images/star-yellow.png").stop();
					$(this).removeClass('starLen').stop;
					$(this).prevAll("img").removeClass('starLen').stop();
					
				}	
var starLen=$('.starLen');
				
				if(starLen.length=='0'){
					$('#starTxt').html('优质');
				}
				if(starLen.length=='1'){
					$('#starTxt').html('满意');
				}
				if(starLen.length=='2'){
					$('#starTxt').html('一般');
				}
				if(starLen.length=='3'){
					$('#starTxt').html('失望');
				}
				if(starLen.length=='4'){
					$('#starTxt').html('不满');
				}
			})
			$("#addConfirm").click(function(){
				var a=0,b=0,c=0,arr=[],p1=[];
				if($(".error_null").val().length=='0' && !($(".error_null").hasClass("error"))){
					var remarkClass = $("#remark").attr("class")+ " error";
					$("#remark").attr("class",remarkClass);
					return false;
				}
				for(i=0;i<$(".star1 img").length;i++){
					if($(".star1 img").eq(i).attr("src")==$(".star1 img").eq(0).attr("src")){
						a++;
					}
					if((i+1)%5==0){
						arr.push(a);
					}
				}
				$("#event_assess").val(a);
			 	var url = "./updateDealAsses.do";
				var data = $("#form2").serialize();
				$.post(url,data,function(result){
					if(result == "success"){
						queryApplyList();
						layer.closeAll('page');
						$('.eve_area').val('');
						$('.eve_area').text('');
						layer.open({
				            type:4,
				        	time:2000,
				            tipsMore: true,
				            title: '提示',
				            tips:[2,"#eee"],
				            closeBtn:0,
				            area: ['200px', '70px'],
				            shade: 0,
				            maxmin: false,
				            offset: 'rb',
				            content: ['<p style="color:#333"> 提示</p><p style="color:#666">　　　评价成功</p>', '#eveBox']
				        });
					}else{
						alert("评价失败！");
					}
				});
			})
			$("#startTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#endTime").datetimepicker({
				lang:'zh-CN',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$(".authorMenuOpen").hide();	
			
			//展示
			queryApplyList();
			//搜索
			$("#button_search").click(function(){
				queryApplyList();
			})
			//撤销
			$("#delFile").click(function(){
				deleteEventInfo();
			})
			//添加申告
		    $("#addApply").click(function(){
		    	$("#storage").html('');
		    	var pageNumber = $("#pagenumber").val();
		    	var pageSize = $("#pagesize").val();
		    	var totalPage = $("#totalpage").val();
		    	var totalRow = $("#totalrow").val();
		    	var jump_height="400px";
		    	var jump_list = getPath()+"/serviceAdd.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
		    	if ($("#dutyNo").val() == "yes") {
		    		jump_height="500px";
		    		jump_list = getPath()+"/toApplyAndAccept.do";
				}

		    	 layer.open({
		    		    type: 2,
		    		    title: '添加申告',
		    		    shadeClose: false,
		    		    area: ['660px', jump_height],
		    		    content: jump_list

		    		  })
		    })
		})
/* 		var display = false;
    function fn(){
	    if(display){
	       document.getElementById('tree').style.display = "none";
	       display = false;
	     }
	    else {
	        document.getElementById('tree').style.display = "";
	        display = true;
	    }
} */
	</script>
	<script>
	
	$(function(){
		var sonTer=$("#layui-layer-iframe1").contents().find("#sonParameter").val()
		if(sonTer=="sonPar"){
				layer.open({
		            type:4,
		            time:2000,
		            tipsMore: true,
		            title: '提示',
		            tips:[1,"#fff0a5"],
		            closeBtn:0,
		            area: ['200px', '70px'],
		            shade: 0,
		            maxmin: false,
		            offset: 'rb',
		            content: ['<p style="color:#333"> 提示</p><p style="color:#666">　　　操作成功</p>', '#tipsBox']
		        });
			}
			    
	      
	})
	
	</script>
</body>
</html>
