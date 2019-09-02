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
	<script src="<%=request.getContextPath()%>/js/kb_event.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>	
	<style>
		.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
		.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
		  .evaluate {width: 100%; }
		  .fixTable .table thead tr{ background:url("<%=request.getContextPath()%>/images/thead-bg.png");}
		  
        .evaluate .eva_inner{ width:99%; height:200px; margin :10px auto 0 auto;border-radius: 6px;background-color: #f8f8f8;  box-shadow: 3px 3px 20px 1px rgba(0,0,0,.3);}
        .evaluate .eva_tit{ width: 100%; height: 40px;text-align: center; line-height: 40px;font-size: 14px; color:#4f4e4e;text-align: center;}
        .evaluate .btn_list{ width: 100%; height:40px; line-height: 40px; text-align: right; }
        .evaluate .btn_list .btn{ padding:3px 4px; line-height:1; margin-right:15px;}
        .evaluate .eve_star {width: 80%;  margin: 0 auto; text-align: center;}
        .evaluate .eve_txt{width: 80%;  margin: 0 auto; height:70px; margin-top:10px;  }
        .evaluate .eve_txt .eve_lab{ width: 20%; float: left ;text-align: right;}
        .evaluate .eve_txt .eve_area{width: 75%; float: right ; resize: none; height: 60px; border-radius: 5px;}
		.fixTable { height:39px;}
		.relTable{overflow-y:auto; overflow-x:hidden;}
		.fixTable .table th{text-align:center;}
	</style>
</head>
<body >
		<div id="storage" style="display:none;"></div>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">知识库</a></li>
				    <li class="active">知识库</li>
				</ol>
			</div>

			<div class="mQuery-2" style="display:none;">
				<form action="" role="form" class="form-horizontal" method="post">
				   <input type="hidden" id="pages" name="pages" value="${pages }">
                   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
				</form>								
			</div>
			<div class="btnout">
                <a class="btn btn-primary btn-sm" onclick="kbAdd()"><img alt="" src="<%=request.getContextPath()%>/images/add-apply.png">添加知识</a>
            </div>
			<div class="fixTable">
				<table class="table table-bordered text-center table-hover table-striped" style="margin-bottom:0px;">
					<thead>
                		<tr>
								<th>事件编号</th>
								<th>事件标题</th>
								<th style="width:100px;">事件等级</th>
								<th>事件产生原因</th>
								<th>事件描述</th>
								<th>事件解决方案</th>
								<th>操作</th>
							</tr>       		
                	</thead>
				</table>
			</div>	
			<div class="relTable" style="height:65%;">
				<table class="table text-center table-bordered table-hover table-striped">
                	<tbody id="DeptTbody">	
                </table>
			</div>			
			<div class="mRpage" id="page2"></div>
		</div>
		<div class="check-win">
		<div class="check-tit">
			<div class="check-tit-left">查看知识信息</div>
			<div class="check-tit-right" onclick="closeWin()">X</div>
		</div>
		<div class="check-content">
			<div class="check-content-left">事件编码:</div>
			<div class="check-content-right"></div>
			<div class="check-content-left">事件标题:</div>
			<div class="check-content-right"></div>
			<div class="check-content-left">事件等级:</div>
			<div class="check-content-right"></div>
			<div class="check-content-left">事件产生原因:</div>
			<div class="check-content-right"></div>
			<div class="check-content-left">事件描述:</div>
			<div class="check-content-right"></div>
			<div class="check-content-left">事件解决方案:</div>
			<div class="check-content-right"></div>
		</div>
	</div>
	<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	<script>
	function GetUrlStr(name){
		  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		   var r = window.location.search.substr(1).match(reg);
		   if(r!=null)
		   return unescape(r[2]);
		   return null;
	}
	function layerTips(tipsTxt){
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
          content: ['<p style="color:#333"> 提示</p><p style="color:#666">'+tipsTxt+'</p>', '#eveBox']
      });
	}
	
	if(GetUrlStr('cid')=='1'){
		layerTips('添加成功')
	}
	if(GetUrlStr('cid')=='2'){
		layerTips('修改成功')
	}
	</script>
	<script>
	$(function(){
		
		kbEvent();
	})
	</script>
</body>
</html>
