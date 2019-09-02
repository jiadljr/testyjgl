		$(document).ready(function() {
//			flushValidateCode();//进入页面就刷新生成验证码
			$("#login").click(function(){
				login();
			})
		});

   		/* 刷新生成验证码 */
//		function flushValidateCode(){
//   			var ContextPath = $('#ContextPath').val();
//   			var validateImgObject = document.getElementById("codeValidateImg");
//   			validateImgObject.src = ContextPath+"/getSysManageLoginCode.do?time="+ Date();
//   		}
   		/**
   		 * 登录
   		 * */
   		function login(){
   				/*var verification = $("#verification").val();
   				if("" == $.trim(verification) || "undefined" == $.trim(verification)){
   					alert("请输入验证码！");
   					return;
   				}*/
   				var flags = true;
   				var userCode = $("#userCode").val();
   				if(userCode == null || userCode == ""){
   					alert("用户名不能为空！");
   					flags = false;
   				}
   				var password = $("#password").val();
   				if(password == null){
   					alert("密码不能为空！");
   					flags = false;
   				}
   				if(flags == false){
   					return ;
   				}
   				var path =getPath()+"/login.do";
   			    $.ajax({
   					type:"post",  
   					url:path,
   					async:false,
   					data:{"userCode":userCode,"password":password},
   					success:function(result){
   			    		if(result.state != 0 || result.data["error"] != null){
   			    			alert(result.data["error"]);
   			    			return;
   			    		}
   			    		
   			    		if(result.state == 0 && result.data["error"] == null){
   			    			window.location.href = getPath()+"/index.do";
   			    		}
   					},
   					error:function(result){
   						alert("系统繁忙，请稍后！");
   					}
   			}); 
   		}