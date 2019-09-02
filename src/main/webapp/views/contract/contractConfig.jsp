<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prompt.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_info_config.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style type="text/css">
	.layui-layer-iframe	.layui-layer-title{color:#fff;background:#3b85c7; }
	.layui-layer-page .layui-layer-title{color:#fff;background:#3b85c7; }
	.typeLists{width:100%; min-height:260px; float:left;.}
	.typeLists	li{ cursor:pointer; position:relative; height:120px; width:120px; line-height:120px; color:#fff; font-size:16px; font-weight:bolder;text-align:center; float:left; margin:10px; border-radius:6px; }
	.typeLists	li img{ position:absolute; bottom:0; right:0; width:80px; height:80px; }
	.typeLists .typeblue{background: linear-gradient(to bottom right, #f399c3 , #c382f4);}
	.typeLists .typegray{background: linear-gradient(to bottom right, #8042f7 , #8042f7);}
	.typeLists .typeLook{background: linear-gradient(to bottom right, #9ff4db , #eff1d0);}
	</style>
</head>
<body>

	<div class="mRight">
		
		<div class="mRpos">
			<ol class="breadcrumb">
				<li><a href="#"><img alt="pos"
						src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				<li><a href="#">合同管理</a></li>
				<li class="active">合同配置</li>
			</ol>
		</div>
		<ul class="typeLists">
			<li class="typeblue"  onclick = "">
				合同模版
				<img src="<%=request.getContextPath()%>/images/proConf2.png">
				
			</li>
			<li class="typegray"  onclick = "configConType()">
				合同类型
				<img src="<%=request.getContextPath()%>/images/proConf1.png">				
			</li>
			<li class="typeLook"  onclick = "">
				支付配置
				<img src="<%=request.getContextPath()%>/images/proConfigLook.png">				
			</li>
		</ul>		
	</div>	
<script>
function configConType(){
	var jump_url = "contractConfigType.jsp";
	layer.open({
	    type: 2,
	    title: '合同类型设置',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['660px', '400px'],
	    content: [jump_url,'yes']
	  })
}
</script>
</body>

</html>