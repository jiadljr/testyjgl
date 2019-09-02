<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>第二版项目主体界面</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/personal_center.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
	.layer_data{ color:#999;font-size:12px; width:640px; min-height:400px; margin: 0 auto;border:1px solid #8f8d8d; }
	.layer_data h1{ margin:0; height:38px; line-height:38px;color:#fff;background:#3b85c7;font-size:18px;}
	.layer_data .layer_d_pic{width:120px; height:120px; float:left;margin:20px 14px;}
	.data_show{position:relative; width:480px; height:140px; float:right; margin:10px 10px 0 0;border:1px solid #ccc;}
	.data_show p span{ width:45%; height:20px; float:left; margin-left:4%;line-height:20px; font-size:12px; display:inline-block;}
	.data_show input{border:none; outline:none; height:14px; line-height:14px;}
	.data_mid{position:relative; width:480px; min-height:50px; float:right; margin:10px 10px 0 0;border:1px solid #ccc;}
	
	 .data_bot p span{ width:50%;  height:20px; line-height:20px; font-size:12px;}
	.data_mid ul{ height:60px; margin-top:10px;}
	.data_mid ul li{text-indent: 4em;display:inline-block;float:left;min-height:30px; line-height:30px; width:49%;}
	.data_mid ul .data_mid_list{ margin:0 auto;  width:98%;}

	.data_bot{position:relative; width:480px; height:80px; float:right; margin:10px 10px 0 0;border:1px solid #ccc;}
	 .data_bot p span{ width:50%; height:20px; line-height:20px; font-size:12px;}
		h3{ margin:0; padding:0;font-size:12px; position:absolute; height:14px; line-height:14px;top:-7px; left:20px; background:#fff;}
	.d_b_l_inner{width:68%; float:left;} 
	.d_b_r_inner{width:30%; float:left;margin-top:20px;color:red; font-size:12px; }
	.d_b_l_inner p{ margin:20px 0 0 10px;  }
	.data_show .edit_border_bottom{border-bottom:1px solid #999; }
	.btn_class{ display:none; margin-top:10px;height:30px; width:100%; float:right;text-align:right; }
	.btn_class input{border:none; line-height:1;padding:5px 8px; border-radius:4px; display:inline-block;margin:0 10px; }
	.btn_class #edit_ok{ background:#3b85c7; color:#fff;}
	.btn_class #edit_no{ background:#e2614e;color:#f7f7f7;}
	.data_show #edit_btn{background:#3b85c7; color:#fff;height:20px;width:43px;border-radius:3px; line-height:20px; display:inline-block; float:right; }
	.layer_data .tit_false{margin:0 20px; height:38px; line-height:38px;color:#fff;background:#3b85c7;font-size:18px;display:inline-block; float:right;cursor: pointer; }
	</style>

</head>
<body>
			<div class="mRight">
				
				<div class="layer_data">
					
					<div class="layer_d_pic"><img src="<%=request.getContextPath()%>/images/user_man.png" alt="图片不存在"></div>
					<div class="data_show">
						<h3><span id="edit_txt" style="height:14px;font-size:12px; line-height:14px; width:auto;">修改</span>个人消息</h3>
						<form action="" method="post" id="form1">
							<input type="hidden" id="userId" name="userId" value = "${user_id }"/>
							<p style="margin-top:20px;">
								<span>姓　名:${user.name }</span>
								<span>性　别:
								<c:if test="${user.sex == 1}">男</c:if>
								<c:if test="${user.sex == 2}">女</c:if>
								<input id="edit_btn" type="button" value="修改"/>
							</span>
							</p>
							<div class="edit_box">
								<p><span>手　机:<input value="${user.tel}" type="text"  id="userTel" name="userTel"/></span><span>微　信:<input value="${user.wechat}" type="text" id="userWechat" name="userWechat" /></span></p>
								<p><span>邮　箱:<input value="${user.mail}" type="text"  id="userMail" name="userMail"/></span></p>
							</div>
							
						</form>	
					</div>
					<div class="data_mid">
						<h3>工作消息</h3>
						<ul>
							<li>岗位:${user.extend2 }</li>
							<li>部门:${user.extend1 }</li>
							<li class="data_mid_list" >角色:${roleName }</li>
						</ul>
					</div>
					<div class="data_bot">
						<h3>账号信息</h3>
						<div class="d_b_l_inner">
							<P>上次修改密码时间:${passwordCreate }</P>
							<p style="margin-top:5px;">密码修改时间:${toDate }</p>
						</div>
						<div class="d_b_r_inner">
							*为了您的账户安全，请在账号失效前修改密码超过时限 请联系管理员
						</div>
					</div>
					<div class="btn_class">
						<input value="保存" type="button" id="edit_ok">
						<input value="取消" type="button" id="edit_no">
					</div>
				</div>
			</div>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/personal_center.js"></script>
	<script>
		$(function(){
			$('.edit_box input').attr("readonly",true);
			$('.btn_class').css("display","none");
			$('#edit_txt').css("display","none");
			$(".tit_false").click(function(){
				$(".layer_data").css("display","none");
			})
			$('#edit_btn').click(function(){
				$('.edit_box input').attr("readonly",false).addClass('edit_border_bottom');
				$('#edit_txt').css("display","inline-block");
				$('.btn_class').css("display","block");
			})
			$('#edit_ok').click(function(){
				$.ajax({
					url:getPath()+"/personalUpdate.do",
					data:$("#form1").serialize(),
					dataType:"json",
					type:"post",
					success:function(){
						if (result.data == "success") {
						}
					},
					error:function(data){
						//alert($.parseJSON(data.responseText).msg);
					}
				}) 
				$('.edit_box input').attr("readonly",true).removeClass('edit_border_bottom');
				$('#edit_txt').css("display","none");
				$('.btn_class').css("display","none");
			})
		  	$('#edit_no').click(function(){
				$('.edit_box input').attr("readonly",true).removeClass('edit_border_bottom');
				$('#edit_txt').css("display","none");
				$('.btn_class').css("display","none");
			})
		})
	</script>
</body>
</html>