$(function(){
	$(".telClass").keyup(function () {
        $(this).val($(this).val().replace(/[^0-9.-]/, ''));
    }).bind("paste", function () {  //CTR+V事件处理    
        $(this).val($(this).val().replace(/[^0-9.-]/, ''));
    }).css("ime-mode", "disabled"); //CSS设置输入法不可用 
	$(".telClass").attr("maxlength","13")
  $(".floatNum").blur(function(){
	 var REX=/^([1-9]\d*|0)(\.\d{1,2})?$/;
	if(!REX.test($(this).val())){
		$(this).addClass("error");
	}
  })
  $(".inputFa").blur(function(){
	  var neg=/[0-9]{10}-[0-9]{8}|[0-9]{12}-[0-9]{8}/;
	   
        if(neg.test($(this).val())==false&&$(this).val().length<=21)
        {
    		$(this).addClass("error");
        }
  })
	$(".intNumHundred").blur(function(){
        var reg=/^([1-9]\d{0,1}|100)$/;
        if(reg.test($(this).val())==false)
        {
    		$(this).addClass("error");
        }
    })
	//$("title").text("智能信息化运维管理系统");
	$(".openWin").hide();
	/*底部新消息的上划提示
	$(".new_mes").show(500).delay(500).hide(500);*/
	/*左侧侧边栏的动画效果*/
	var tranFlag=1;
	var mHeight=$("#main").height();
	$(".glyphicon-transfer").click(function(){
		if(tranFlag){
			$(".mLeft .list-group").hide();
			tranFlag=0;
			$(".mLeft").css("line-height",mHeight + "px").css("width","20px");
			$(".mRight").css("margin-left","20px");
			$(".mRight,.mRight .mRpreson,.mRight .mRbot").css("left","20px");			
		}else{
			$(".mLeft .list-group").show();
			tranFlag=1;
			$(".mLeft").css("line-height","").css("width","");
			$(".mRight").css("margin-left","");
			$(".mRight .mRbot,.mRight .mRpreson,.mRight .mRbot").css("left","");
		}
	})
	$("input,select,textarea").focus(function(){
		$(this).removeClass("error")
	})
	$(".error_null,textarea").focus(function(){
		var error = $(this).attr("data-message");
		if ($(this).val() == "" || $(this).val() == error) {
			$(this).val("");
		$(this).removeClass("error");
		}
	})
//	/*非空验证*/
//	$(".error_null,textarea").on("blur", function() {
//		if ($(this).val() == "") {
//			var error = $(this).attr("data-message");
//			$(this).val(error);
//			$(this).next().html(error)
//			$(this).addClass("error");
//		}
//	}).on("focus", function() {
//		var error = $(this).attr("data-message");
//		if ($(this).val() == "" || $(this).val() == error) {
//			$(this).val("");
//			$(this).removeClass("error");
//		}
//	})

	/*个人信息的下拉选项*/
	$(".memb").mouseover(function(){
		$(".memb .perdrow").stop().show();
	}).mouseout(function(){
		$(".memb .perdrow").fadeOut();
	})
	$(".memb .perdrow").mouseover(function(){
		$(this).stop().show();
	})

	/*新通知的下拉选项*/
	$(".news").hover(function(){
		$(".news .news_drow").show();
	},function(){
		$(".news .news_drow").hide();
	})
	$(".news .news_drow").mouseover(function(){
		$(this).stop().show();
	})

	/*table全部选择和全部取消的实现*/
	$("input[name=allSelect]").click(function(){
		$('input[name=item1]').prop('checked', $(this).prop('checked'));
	})
	$("table").on('click','input[name=item1]',function(){
		if(!$(this).prop("checked")){
			$("input[name=allSelect]").prop('checked',false);
		}
	})
	/*表单的title提示*/
	$("tbody td").mouseover(function(){
    	var x1=$(this).text();
    	$(this).attr("title",x1);
    	$(this).children("a").parents("td").attr("title","");
    	if($(this).children().length>0)
    		$(this).children().parents("td").attr("title","");
    }) 
    /**代理人授权*/
    $('#accreditProxy').click(function(){
		 layer.open({
			    type: 2,
			    title: '代理人授权',
			    shadeClose: false,
			    area: ['640px', '240px'],
				content:getPath()+'/toAccreditProxy.do'
		 })
		
	})
     /**取消代理人授权*/
    $('#distroyProxy').click(function(){
		 layer.open({
			    type: 2,
			    title: '取消代理人授权',
			    shadeClose: false,
			    area: ['640px', '140px'],
				content:getPath()+'/toDistroyProxy.do'
		 })
		
	})
})
function selectFunc(){
	alert($("input[name=allSelect]").val());
}
function GetUrlStr(name){
	  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	   var r = window.location.search.substr(1).match(reg);
	   if(r!=null)
	   return unescape(r[2]);
	   return null;
}