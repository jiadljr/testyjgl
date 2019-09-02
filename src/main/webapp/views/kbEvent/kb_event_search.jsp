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
        .evaluate .eva_inner{ width:99%; height:200px; margin :10px auto 0 auto;border-radius: 6px;background-color: #f8f8f8;  box-shadow: 3px 3px 20px 1px rgba(0,0,0,.3);}
        .evaluate .eva_tit{ width: 100%; height: 40px;text-align: center; line-height: 40px;font-size: 14px; color:#4f4e4e;text-align: center;}
        .evaluate .btn_list{ width: 100%; height:40px; line-height: 40px; text-align: right; }
        .evaluate .btn_list .btn{ padding:3px 4px; line-height:1; margin-right:15px;}
        .evaluate .eve_star {width: 80%;  margin: 0 auto; text-align: center;}
        .evaluate .eve_txt{width: 80%;  margin: 0 auto; height:70px; margin-top:10px;  }
        .evaluate .eve_txt .eve_lab{ width: 20%; float: left ;text-align: right;}
        .evaluate .eve_txt .eve_area{width: 75%; float: right ; resize: none; height: 60px; border-radius: 5px;}
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

			<div class="mQuery-2">
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
								<th>资产类型</th>
								<th>事件类型</th>
								<th>事件等级</th>
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
	<script>
	$(function(){
		kbEvent();
		$("#button_search").click(function(){
			
		})
	})
	</script>
</body>
</html>
