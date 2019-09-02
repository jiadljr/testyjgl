$(function(){
	 $.each($(".error_null"),function(){
         if($(this).val()==""){
             $(this).addClass("err_null");
         }
     })
	$('#upPassword').click(function(){
		var userId = $("#userId").val();
		var oldPword = $("#old_pword").val();//原密码
		var newPword = $("#new_pword").val();// 新密码
		 $.each($(".error_null"),function(){
		        if($(this).val()!==""){
		            $(this).removeClass("err_null");
		        }
		    })
		    if($(".error_null").val()=="" || $(".error_null").hasClass("error")|| $(".error_null").hasClass("err_null")){
		 		$.each($("input"),function(){
		 			if($(this).val()==""){
		 				var error=$(this).attr("data-message");
		 				$(this).val(error);
		 				$(this).addClass("error");
		 			}
		 		})
		 		return false;
		 	}else{
		 		$.ajax({
					data : {
						userId : userId,
						oldPword : oldPword,
						newPword : newPword
					},
					dataType : 'json',
					type : 'post',
					url : getPath() + '/submitPassword.do',
					success : function(result) {
						if (result.data == "success") {
							alert("修改成功");
							$.post(getPath()+"/logout.do",function(){
								top.location.href =getPath()+"/toLogin.do";	
							})
						} else if (result.data == "oldError") {
							alert("原密码不正确");
						}
					}
				})
		 	}
	})
})
function sure(){
	var newPword = $("#new_pword").val();// 新密码
	var newAgainPword = $("#new_again_pword").val();// 再次输入新密码
	if (newPword != newAgainPword) {
		$('#new_again_pword').addClass('error');
		$('#double_in_out').html("两次输入密码不同！！！请重输入");
		return false;
	}
}
