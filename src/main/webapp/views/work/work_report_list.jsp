<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
<script src="<%=request.getContextPath()%>/js/work_report.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>

</head>
<style>
	 .layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	 .layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.tables{ width:100%; border-spacing: 0;border-collapse: collapse; float:left;  margin:0;}
	.tables thead tr{background:url("<%=request.getContextPath()%>/images/thead-bg.png")}
	.tables th,.tables td{ border:1px solid #ccc; height:30px; line-height:30px;text-align:center; }
	.tables th{ height:36px;line-height:36px;}
	.tableList{width:200px; height:40px; line-height:40px;  text-align:center;  margin-left:30px; float:left;}
	.tableList li{ cursor:pointer; width:100px; float:left;}
	.tableList .cur{    border-bottom: 2px solid #32a8ee;color: #32a8ee;} 
	.planTabs{ margin-top:10px; float:left;}
	.addBtn{ float:right;margin:10px 150px 0 40px; color:#fff; background-color: #337ab7; display: inline-block; width:66px; height:26px; font-size: 14px; line-height: 26px;text-align: center;white-space: nowrap;vertical-align: middle; cursor: pointer;border: none;border-radius: 4px;}
     .fixTable{  height:38px; width:100%;}
         .fixTable table { margin-bottom:0;}
           .relTable{  width:100%; overflow-y:auto;overflow-x:hidden;}
          .relTable table{ margin-bottom:0;}
           .relTable table td{ line-height:1;}
</style>
<body>
	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="javascript:;">个人办公</a></li>
			    <li class="active">工作汇报</li>
			</ol>
		</div>
		<input type="button" id="but" value="新增" class="addBtn" />
		
		<div class="fixTable">
			<table class="tables" style="margin-top:10px;">
			  <thead>
			      <tr>
					<th>汇报标题</th>   
					<th >汇报类型</th>
					<th >状态</th>
					<th >提交时间</th>
					<th >操作</th>      
			      </tr>
		      </thead>
    	  </table>
		</div>
		<div  class="relTable">
		<table class="tables">
  			<tbody id="myTable">	
  			</tbody>
 		 </table>
		</div>

  <div class="mRpage" id="page2"></div>
</div>

  <script type="text/javascript">
  $(function(){
	  $(".relTable").height($("body").height()-160);
	  $("#but").click(function(){
			location.href= getPath()+"/addWorkReport.do";	    	 
	  })
  })
  </script>
</body>
</html>