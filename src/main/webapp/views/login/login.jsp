<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>智能信息化运维管理系统</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
 	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/login.js"></script>
    <script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	
</head>
<body>
<input type="hidden" id="ContextPath" value="<%=request.getContextPath()%>">

<h1 class="login_tit"><i class="logo"></i></h1>
<div class="login_box">
	<h2>用户登录</h2>
	
	<div class="login_tab">
		<!--<ul class="login_tab_tit">
			<li class="text-left log_nav_on">用户登录</li>
			  <li class="text-right">证书登录</li>
		</ul>-->
		<div class="login_list">
			<div class="login_item item_on">
			<form method="post" action="">
				<div onkeydown="keyLogin(event);">
					<p><img class="pic_log" src="<%=request.getContextPath()%>/images/user_bkg.png"><label for="zh">　</label><input type="text" id="userCode" name="userCode" placeholder="请输入您的员工编号"></p>
					<p><img class="pic_log" src="<%=request.getContextPath()%>/images/pas_bgk.png"><label for="mm">　</label><input type="password" id="password" name="password" autocomplete="off" placeholder="请输入您密码"></p>
					<%-- <div class="mark_tit">
						<div class="line"><img class="pic_log" src="<%=request.getContextPath()%>/images/mark_bgk.png"><label for="yzm">　</label><input type="text" id="verification" name="verification" placeholder="请输入验证码"></div>
						<span class="verification"><img  id="codeValidateImg"  onClick="javascript:flushValidateCode();"/></span>
						<div class="clear"></div>
					</div> --%>
					<div class="login_last"><a class="btn" id="login">登录</a></div>
				</div>
			</form>
		</div>
		<!-- <div class="login_item">
			<form>
				<div onkeydown="keyLogin(event);">
					<p><img src="<%=request.getContextPath()%>/images/user_bkg.png"><label for="zh">　</label><input type="text" id="userName" name="userName" placeholder="请输入您的证书号码"></p>
					<p><img src="<%=request.getContextPath()%>/images/pas_bgk.png"><label for="mm">　</label><input type="password" id="password" name="password" autocomplete="off" placeholder="请输入您密码"></p>
					<div class="login_last"><a class="btn" id="login">登录</a></div>
						
		
				</div>
			</form>
		</div>-->
		</div>
	</div>
</div>	
<p class="footer">版权所有&nbsp;&copy;&nbsp;:&nbsp;北京乾坤博远科技有限责任公司</p>
<script>
$(document).ready(function(){
    $(".login_tab_tit li").click(function(){
    $(".login_tab_tit li").eq($(this).index()).addClass("log_nav_on").siblings().removeClass('log_nav_on');
	$(".login_list .login_item").hide().eq($(this).index()).show();
    });
});
</script>
	<script type="text/javascript">
    	function keyLogin(event){
	  	  if (event.keyCode==13)   //回车键的键值为13
	  	     document.getElementById("login").click();  //调用登录按钮的登录事件
	  	}

		if(window !=top){  
			top.location.href = location.href;  
		} 
	</script>
</body>
</html>