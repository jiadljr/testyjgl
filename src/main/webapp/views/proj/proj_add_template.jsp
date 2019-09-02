<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<style>
		.addTemp{ width: 460px; height: 480px;  margin: 0 auto; background:#f1f3f4;}
		.addTemp .tempTit{ width: 460px; height:40px;margin:0 auto;}
		.addTemp .tempTit label{text-align:right; width:110px; height:40px; line-height:40px;display: inline-block;float: left;  font-size: 14px;}
		.addTemp .tempTit input{ width: 300px; height: 30px; float: left; display: inline-block; margin-top: 5px; border: 1px solid #ccc; border-radius: 3px;}
		.tempItem{border:1px solid #ccc; width: 450px; height: 350px; box-sizing: border-box;margin:5px auto; position: relative;}
		.tempItem .tempItemTit{ font-size:12px; background: #f1f3f4; display: inline-block;position: absolute; left: 5px; top:-9px;font-size: 14px; }
		.tempIcon{ width: 450px; height:24px; margin-top: 16px; line-height:24px;float:left; display:block;  }
		.tempIcon .fa {margin:0 3px; color:#3b85c7;cursor:pointer;}
		.tempInput{width: 440px; height: 290px; margin:5px auto; overflow-x: hidden; overflow-y: auto; background: #fff;}
		.tempInput li{ width: 100%; height: 30px; float: left;cursor:pointer; }
		.tempInput .hover{ background:#3b85c7;}
		.tempInput li input{ width: 90%; height: 20px; background: #fff;  display: inline-block; margin: 5px 5%; border: 1px solid #ccc; border-radius: 3px;}
		.tempBtn{ width:100%; height:40px;  margin:0 auto; text-align:right; border-bottom:2px solid #ccc;}
		.tempBtn a{  display:inline-block; padding:6px 12px;line-height:1; margin:5px 10px;border-radius:3px;}

	</style>
</head>
<body>
<div class="addTemp">
	<div class="tempTit">
		<label>模版名称：</label>
		<input value="" type="text" id="templateName"/>
	</div>
	<div class="tempItem">
		<span class="tempItemTit">阶段任务</span>
		<div class="tempIcon">
			<i class="fa fa-plus" id="addTemp" style="color:#7ac978"></i>
			<i class="fa fa-arrow-up" id="arrowUp" ></i>
			<i id="arrowDown" class="fa fa-arrow-down"></i>	
			<i class="fa fa-close" id="delTemp" style="color:red;"></i>
		</div>
		<ul class="tempInput">
			
		</ul>
	</div>
	<div class="tempBtn">
		<a href="#" class="btn_blue" id="tempSure">确认</a>	
		<a href="#" class="btn_red" onclick="operCancel()">取消</a>
	</div>	
</div>
<script type="text/javascript">
	$(function() {
		$("#addTemp").click(function(){
			$(".tempInput").append("<li><input type=\'text\' value=''/></li>");
			$("input").focus(function(){
				$(this).removeClass("error")
			})
			$(".tempInput li").click(function(){
			
				$(".tempInput li").removeClass("hover");
		
				$(this).addClass("hover")	
			})
			$(".tempInput li input").click(function(){
				
				$(".tempInput li").removeClass("hover");
		
				$(this).parent().addClass("hover")	
			})
		})
		$("#arrowUp").click(function(){
			var $prev=$(".tempInput .hover");
			if($prev.prev().length==0){
				alert("已经是第一个了，不能上移！！！")
			}else{
			
				var skr=$prev.prev().children().val();
				$prev.prev().remove();
				$prev.after("<li><input value='"+skr+"' type='text' /></li>")
			}
			$(".tempInput li").click(function(){
				$(".tempInput li").removeClass("hover");
				$(this).addClass("hover")	
			})
			$(".tempInput li input").click(function(){
				
				$(".tempInput li").removeClass("hover");
		
				$(this).parent().addClass("hover")	
			})
	
		})
			$("#delTemp").click(function(){
				$(".tempInput .hover").remove()
			})
		$("#arrowDown").click(function(){
			var $next=$(".tempInput .hover");
			if($next.next().length==0){
				alert("已经是最后一个了，不能下移！！！")
			}else{
				var str=$next.next().children().val();
				$next.next().remove();
				$next.before("<li><input value='"+str+"' type='text' /></li>")				
			}	
			$(".tempInput li").click(function(){
				$(".tempInput li").removeClass("hover");
				$(this).addClass("hover")	
			})
			$(".tempInput li input").click(function(){
				
				$(".tempInput li").removeClass("hover");
		
				$(this).parent().addClass("hover")	
			})
		})
		
		$("#tempSure").click(function(){
			var arr=[];
			var num=[];
			var i = 1;
			$.each($(".tempInput li input"),function(){
				num.push(i);
				arr.push($(this).val())
				i++;
				if(Trim($(this).val()) == ""){
					$(this).addClass("error");
				}
			})
			
			var templateName = $("#templateName").val();
			/**
				关闭
				window.parent.close();
				var index = parent.layer.getFrameIndex(window.name); 
				parent.layer.close(index);
			*/
			if(templateName == ""){
				alert("请填写模板名称");
				return;
			}
			
			if(arr.length == 0){
				alert("请添加阶段任务！");
				return;
			}
			
			if($(".tempInput li input").hasClass("error")){
				return;
			}
			
			var reg2 = new RegExp('^[^\\\\/：:*?"<>\?|]+$');
			var reg = new RegExp("[\"'“‘？]");
			if(reg.test(arr.toString()) || !reg2.test(arr.toString())){
				alert("任务名称不能包含下列字符\\ /:：*?\"<>|'“‘？");
				return;
			}
			
			$.ajax({
				url:getPath()+"/addTemplate.do",
				type:"post",
				dataType:"text",
				data:{"taskStr":arr.toString(),"numStr":num.toString(),"templateName":templateName},
				success:function(data){
					if(data == "success"){
						alert("新增成功！");
						window.parent.getTemplateName();
						var index = parent.layer.getFrameIndex(window.name); 
						parent.layer.close(index);
					}
					if(data == "nameError"){
						alert("任务名称重复，请重新填写！");
					}
				},
				error:function(){
					alert("新增失败！");
				}
			})
			
		})
	})
		$(".tempInput li").click(function(){
			$(".tempInput li").removeClass("hover");
			$(this).addClass("hover")	
		})
		$(".tempInput li input").click(function(){
			
			$(".tempInput li").removeClass("hover");
	
			$(this).parent().addClass("hover")	
		})
		//取消
		function operCancel(){
			var index = parent.layer.getFrameIndex(window.name); 
			parent.layer.close(index);
		}
</script>
</body>
</html>
