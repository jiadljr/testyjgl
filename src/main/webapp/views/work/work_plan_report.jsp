<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>

	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>

<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<script src="<%=request.getContextPath()%>/js/work_plan_report.js"></script>
<style type="text/css">
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.tables{ width:100%; border-spacing: 0;border-collapse: collapse; float:left;}
	.tables thead tr{background:url("<%=request.getContextPath()%>/images/thead-bg.png")}
	.tables th,.tables td{ border:1px solid #ccc; height:30px; line-height:30px;text-align:center; }
	.tables th{ height:36px;line-height:36px;}
	.tableList{width:200px; height:40px; line-height:40px;  text-align:center;  margin-left:30px; float:left;}
	.tableList li{cursor:pointer; width:100px; float:left;}
	.tableList .cur{    border-bottom: 2px solid #32a8ee;color: #32a8ee;} 
	.tableItems{ margin-top:10px; float:left;}
	.addBtn{ float:right;margin:10px 150px 0 0; color:#fff; background-color: #337ab7; display: inline-block; width:66px; height:26px; font-size: 14px; line-height: 26px;text-align: center;white-space: nowrap;vertical-align: middle; cursor: pointer;border: none;border-radius: 4px;}
     .fixTable{  height:38px; width:100%;}
         .fixTable table { margin-bottom:0;}
           .relTable{  width:100%; overflow-y:auto;overflow-x:hidden;}
          .relTable table{ margin-bottom:0;}
           .relTable table td{ line-height:1;}
</style>
</head>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="javascript:;">个人办公</a></li>
			    <li class="active">我的查阅</li>
			</ol>
		</div>
	
		<ul id="tableList" class="tableList">
			<li class="cur">工作计划</li>
			<li onClick="queryWorkPlanReport()" >工作汇报</li>
		</ul>
		<div class="tableItems" id="workTabs" >
			<div class="fixTable">
				<table  class="tables">
					 <thead>
					      <tr>
							<th>标题</th>
							<th>类型</th>
							<th>子类型</th>
							<th>推送人</th>
							<th>推送时间</th>
							<th>操作</th>    
					      </tr>
					  </thead>
			  	</table>
			</div>
			<div class="relTable">
				<table  class="tables">
					  <tbody id="planTable">	
					  </tbody>
			  	</table>
			</div>

  <div class="mRpage" id="page3"></div>
	</div>
		<div class="tableItems" style="display:none;" id="planTabs">
			<div class="fixTable">
			  <table class="tables">
				  <thead>
				      <tr>
						<th>标题</th>
						<th>类型</th>
						<th>子类型</th>
						<th>推送人</th>
						<th>推送时间</th>
						<th>操作</th>    
				      </tr>
				  </thead>
				 
			  </table>
			</div>
			<div class="relTable">
				<table class="tables">
			
				  <tbody id="myTable">	
				  </tbody>
			  </table>
			</div>
	
			  <div class="mRpage" id="page2"></div>
		</div>				
</div>
</body>
<script>
$(function(){
	$("#tableList li").click(function(){
	    $("#tableList li").eq($(this).index()).addClass("cur").siblings().removeClass('cur');
		$(".tableItems").hide().eq($(this).index()).show();
	});
})
if(GetUrlStr('pushId')==1){
	window.parent.pushMenu()
}
</script>
</html>