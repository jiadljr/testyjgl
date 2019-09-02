<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<style type="text/css">
		.error_box{ width:40%; height:60%;margin:50px auto;}
		.error_box h1{ height:130px; line-height:130px; font-size:90px; color:#3782d3; text-align:left;text-indent:40px;position:relative;}
		.error_box h1 img{ position:absolute;left:-30px;top:40px;z-index:2; width:100px; height:100px;}
		.error_box .error_sor{ height:30px; line-height:30px; font-size:18px; color:#3782d3;}
		.error_box .error_sor strong{font-size:22px; }
		.error_box p{  line-height:24px; font-size:14px;  color:#3782d3;}
		.error_box p a{ color:#3782d3;}
		.error_box p a:hover{ text-decoration:underline; }
		.error_box .error_on a{ text-decoration:underline;}
	</style>
</head>
<body>
	<div class="error_box">
		<h1>404<img src="<%=request.getContextPath()%>/images/error_bgk.png" alt="图片不存在" tittle="404"></h1>
		<p class="error_sor"><strong>抱歉！　</strong>您访问的页面不存在</p>
		<p>您要访问的页面不存在或者已删除，请联系管理员</p>
		<p>您还可以进行以下操作</p>
		<p><a href="javascript:;" onclick="history.go(-1)" >>>返回上一步</a></p>
		<p class="error_on"><a href="/Security_ad/index.do" target="_blank">>>返回首页</a></p>	
	</div>
</body>
</html>