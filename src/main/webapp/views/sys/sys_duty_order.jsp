<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys_class.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.print.min.css" rel='stylesheet' media='print' >
	
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/lib/moment.min.js'></script>
<script src='<%=request.getContextPath()%>/plugins/fullcalendar-3.5.0/fullcalendar.min.js'></script>

<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style>
	#dutyUserText{width:100%; height:140px ;background:#fff; cursor:pointer;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;}
	#dutyUserText{width:100%; height:140px ;border-radius:6px ;border:1px solid #ccc;overflow-y:auto;background:#fff;}
	#dutyUserText a{overflow: hidden; text-overflow: ellipsis;white-space: nowrap;position:relative; display:inline-block; text-align:center; float:left; line-height:24px;height:24px; width:70px; margin:5px; background:#ff6900;color:#fff; }
	#dutyUserText a i{ display:none;}
	#dutyUserText a:hover{text-decoration: none; cursor: pointer;}
	#dutyUserText a span{ font-size:20px;z-index:99999; position:absolute;right:0; top:-3px; display:block; height:20px; width:20px;color:#fff; }
	.newOnClass{ width:100%; height:100%; position:relative;float:left; margin-bottom:20px;}
	.newOnClass .newClassAdd{ width:280px; float:left; margin-left:1%; height:100%;position:absolute;left:10px;top:0;}
	.newOnClass .newClassAdd .dataList{ width:280px; height:40px;line-height:40px ;overflow:hidden;}
	.newOnClass .newClassAdd .dataList  label {width: 110px; float: left;text-align: right;height: 40px;line-height: 40px;display: inline-block;}
    .newOnClass .newClassAdd .dataList  select{ width:80px; display:inline-block;float:left;margin-top:10px;}
	.newOnClass .newClassAdd .classList{position:relative; width:280px; height:40px;line-height:40pxoverflow:hidden;}
	.newOnClass .newClassAdd .classList  label {margin:0;width: 110px;float: left;text-align: right;height: 40px;line-height: 40px;display: inline-block;}
    .newOnClass .newClassAdd .classList  select{ width:170px; display:inline-block;float:left;margin-top:10px;}
	.newOnClass .newClassAdd ul{ width:280px; border:1px solid #ccc; height:500px; overflow-y:auto;overflow-x:hidden;}
    .newOnClass .newClassAdd ul li{ height:30px; line-height:30px;}
    .newOnClass .newClassAdd ul li .fa{ margin:0 1em; font-size:20px; color:#054ac8}
	#dutyGroup{z-index:99; position:absolute; right:-120px; top:5px;display:inline-block; padding:0 4px; height:24px; line-height:24px; margin:3px 10px;border-radius:3px;}
	#leftDiv{ width:220px; overflow-y:auto;overflow-x:hidden; height:300px; float:left; margin:5px; box-shadow:0 0 3px #aaa;}
	#leftDiv li { height:28px; float:left;width:100%; line-height:28px; text-indent: 2em;}
	#leftDiv li .hover{background:#3b85c7;color:#fff;} 
	#leftDiv li:hover a{background:#3b85c7;color:#fff; } 
	#leftDiv li a{ color:#666; display:block ; transition: all 0.2s; }
	.rightDuty{width:510px; height:300px; float:left;}
	.rightDuty .pList{ width:100%;height:40px; line-height:40px; overflow:hidden; float:left; }
	.rightDuty label{ width:140px; text-align:right; display:inline-block; float:left;}
	.rightDuty .pList input{ height:30px;float:left; width:200px; margin-top:5px;border:1px solid #f1f1f1;border-radius:3px; }

	.roleBox {width:100%; float:left;}
	.roleList{ width:360px; float:left;}
	.btnlist{ text-align:right; width:100%; height:30px; float:left; position:absolute; bottom:10px;} 
   	.btnlist a{  display:inline-block; padding:0 4px; height:24px; line-height:24px; margin:3px 10px;border-radius:3px;}
	.QkCalendar{ width:100%;height:370px; float: left;position: relative;}
    .QkCalendar .QkCalendarTit{ width: 100%; height: 30px;  margin: 0 auto;}
    .QkCalendar .QkCalendarTit select{ width: 100px; height: 24px; box-sizing: border-box;margin: 3px 0;border-radius: 3px; border: 1px solid #aaa;}
    .QkCalendar h4{ width: 100%; height: 30px; line-height: 30px; font-size: 18px; text-align: center;}
    .QkCalendar .week{height: 24px; width: 100%; line-height: 24px;}
    .QkCalendar .week li{ float: left; width: 14.28%;height: 24px; line-height: 24px; text-align: center; border: 1px solid #999; }
    .QkCalendar .dayList{height: 240px; width: 100%;}
    .QkCalendar .dayList li{ position: relative; float: left; width: 14.28%;border: 1px solid #ccc; box-sizing: border-box;  height:55px; line-height: 30px; text-align: center; }
    .QkCalendar i{ position: absolute; width: 20px;height: 20px; line-height:20px;text-align: center;cursor:pointer;font-style: normal; display: inline-block; background: #8c8c8c;color: #fff;border-radius:2px; }
    .QkCalendar .prev{ left: 50px; top:5px;}
    .QkCalendar .next{ right: 50px; top:5px;}
    .QkCalendar .dayList li span{ position: absolute; left: 5% ;bottom:5px; height: 20px; line-height: 20px; color: #00a2d4;width: 90%; display: inline-block;}
	.bnRankClass{ text-align:right; position:absolute; top:0; right:0;}
	.modelBlock{display:none; width:100%; height:100%;background:#6c6c6c; position:absolute; top:0 ;right:0; z-index:99999; opacity:0.1; }
</style>
</head>
<body>
 	<input type="hidden" id="spanHidden">

  <div id="myDiv">
     <ul id="leftDiv">
	      <c:forEach items="${selectDutyOrder }" var="order">
	      <li><a href="#" onclick="groupName(${order.id })">${order.groupName }</a></li>
		  </c:forEach>
     </ul>
     <div class="">
     	
     </div>
    <div class="rightDuty">
        <input type="hidden" id="dutyOrderId">
        <p class="pList"><label>值班名称组：</label><input type="text" id="groupName"></p>
             
        <div class="roleBox">
			<label for="role_name" >运维人员<span style="color:red;font-size:16px;">*</span>：</label>
				<div class="roleList" style="position:relative;">
					<div class="proEditBox" style="position:absolute;z-index:9999; width:100%; height:100%;"></div>
					<div id="dutyUserText"></div>
					<div class="modelBlock"></div>
					<select id="duty_user" name="duty_user"  class="form-control" multiple style="position:absolute;left:0;top:140px; width:360px; display:none;" size="5" >
						<c:forEach items="${userByRoleType }" var="oper">
							<option value = "${oper.id }">${oper.name }</option>
						</c:forEach>
					</select>				
				</div>
		</div>			
	</div>
	<input type="hidden" id="hidGroupId">
	<input type="hidden" id="hidGroupName">
	<input type="hidden" id="hidDutyUserTextId">
	<input type="hidden" id="hidDutyUserTextName">
	<div class="btnlist">
	<a id="newDutyOrder"   href="#" class="btn_blue">新建</a><!-- 清空填写项 -->
	<a id="updateCancelHide"   href="#" class="btn_green">编辑</a>
	<a href="#" id="deleteDutyOrder" class="btn_purple">删除</a>
	<a href="#" id="addDutyOrder" class="btn_green">保存</a>
	<a id="lastStep"   href="#" class="btn_red">取消</a><!-- 编辑后返回上一步 -->
	<a href="#" class="btn_red" id="cancel">关闭</a>
	</div>
  </div>

<script type="text/javascript">
$(function(){
	document.getElementById("groupName").disabled=true;
	$("#updateCancelHide").hide();
	$("#deleteDutyOrder").hide();
	$("#lastStep").hide();
	$("#addDutyOrder").hide();
	//$("#groupName").attr("readOnly","true").css("background","#ccc");
	$(".modelBlock").show();//不可修改人员列表
})
$("#leftDiv li a").click(function(){
	$("#leftDiv li a").removeClass("hover")
	$(this).addClass("hover")
})
$("#newDutyOrder").click(function(){
	document.getElementById("groupName").disabled=false;
	$("#leftDiv li a").removeClass("hover")
	$("#addDutyOrder").show();
	$(".ulDiv").show();
	$("#lastStep").show();
	$(".modelBlock").hide();
	$(".proEditBox").hide();
	$("#cancel").hide();
	$("#updateCancelHide").hide();
	$("#deleteDutyOrder").hide();
	$("#newDutyOrder").hide();
})
$("#updateCancelHide").click(function(){
	document.getElementById("groupName").disabled=false;
	$("#addDutyOrder").show();
	$(".modelBlock").hide();
	$(".proEditBox").hide();
	$(".ulDiv").show();
	$("#updateCancelHide").hide();
	$("#lastStep").show();
	$("#deleteDutyOrder").hide();
	$("#newDutyOrder").hide();
	$("#cancel").hide();
})
$("#lastStep").click(function(){
	$("#groupName").attr("disabled","true");
	$("#updateCancelHide").show();
	$(".proEditBox").show();
	$(".ulDiv").hide();
	$(".modelBlock").show();
	$("#newDutyOrder").show();
	$("#addDutyOrder").hide();
	$("#lastStep").hide();
	$("#cancel").show();
	$("#deleteDutyOrder").show();
	var hidGroupName = $("#hidGroupName").val();
	var hidGroupId = $("#hidGroupId").val();
	$("#groupName").val(hidGroupName);
	$("#dutyOrderId").val(hidGroupId);
	var hidDutyUserTextId = $("#hidDutyUserTextId").val();
	var hidDutyUserTextName = $("#hidDutyUserTextName").val();
	var idUser = hidDutyUserTextId.split(",");
	var nameUser = hidDutyUserTextName.split(",");
	var str="";
	$("#dutyUserText").empty();
	for(var i=0;i<idUser.length;i++){
		str+="<a href='#' title='"+nameUser[i]+"'>"+nameUser[i]+"<i>"+idUser[i]+"</i><span>×</span></a>"
		$.each($("#duty_user option"),function(){
			if($(this).val()==idUser[i]){
				$(this).addClass("bulingbuling");
				$(this).hide();
			}
		})
	}
	$("#dutyUserText").append(str);
	
	$("#dutyUserText a span").click(function(e){
		$(this).parent().remove();
		e.stopPropagation()
		var newidcode=$(this).prev().text();
		$.each($("#duty_user option"),function(){
			if($(this).val()==newidcode){
				$(this).removeClass("bulingbuling");
				$(this).show();
			}
		})
		
	})	
})

   $(function(){
	   $("#dutyUserText a span").click(function(e){
			$(this).parent().remove();
			e.stopPropagation()
			var newidcode=$(this).prev().text();
			$.each($("#duty_user option"),function(){
				if($(this).val()==newidcode){
					$(this).removeClass("bulingbuling");
					$(this).show();
				}
			})
			
		})
	   $("#cancel").click(function(){
		   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		   parent.layer.close(index);
		   window.parent.loadNewPage();
	   })
	   
   })
</script>
<script type="text/javascript">
			$("#dutyUserText").click(function(e){
				$(this).removeClass("error");
				$("#duty_user").show();
				$("#duty_user").click();
				if($("#duty_user option").length == $(".bulingbuling").length){
					$("#duty_user").hide()
				}else{
					$("#duty_user").show()
				}
				e.stopPropagation();
			})
			
			$("#duty_user option").click(function(e){
				var text=$(this).text();
				var textId=$(this).val();
				
				$(this).hide().addClass("bulingbuling");
				e.stopPropagation();
				if($("#duty_user option").length==$(".bulingbuling").length){
					$("#duty_user").hide()
				}else{
					$("#duty_user").show()
				}
			
				document.getElementById("dutyUserText").innerHTML+="<a href='#' title='"+text+"'>"+text+"<i>"+textId+"</i><span>×</span></a>";
				$("#dutyUserText a span").click(function(e){
					$(this).parent().remove();
					e.stopPropagation()
					var newidcode=$(this).prev().text();
					$.each($("#duty_user option"),function(){
						if($(this).val()==newidcode){
							$(this).removeClass("bulingbuling");
							$(this).show();
						}
					})
					
				})
				
			})
			$("#duty_user").blur(function(){
				$(this).hide();		
			})
			$("#duty_user").click(function(e){
				e.stopPropagation();
			})
			$(document).click(function(){
				$("#duty_user").hide()
			})
			var idUserGroup = "";
			var nameUserGroup = "";
			$("#groupName").focus(function(){
				$(this).removeClass("error")
			})
			
			$("#addDutyOrder").click(function(){
				if($("#groupName").val()==""){
					$("#groupName").addClass("error")
				}
				if($("#dutyUserText").html()==""){
					$("#dutyUserText").addClass("error")
				}
				if($("#groupName").hasClass("error")||$("#dutyUserText").hasClass("error")){
					return false;
				}
				
				$.each($("#dutyUserText a i"),function(){
					idUserGroup += $(this).text()+",";
					nameUserGroup += $(this).parent().attr("title")+",";
				})
				idUserGroup=idUserGroup.substring(0,idUserGroup.length-1);
				nameUserGroup=nameUserGroup.substring(0,nameUserGroup.length-1);
				var groupName = $("#groupName").val();
				var id = $("#dutyOrderId").val();
				$.ajax({
					type:"post",
					data:{"id":id,"groupName":groupName,"idUserGroup":idUserGroup,"nameUserGroup":nameUserGroup},
					url:getPath()+"/insertDutyOrder.do",
					success:function(result){
						if(result == ""){
						 window.location.reload();
						}else{
							$("#leftDiv").empty();
							var order = result.dutyOrder;
							var groName = "";
							for(var i = 0;i<order.length;i++){
								groName += "<li><a href='#' onclick='groupName("+order[i].id+")'>"+order[i].groupName+"</a></li>";
							}
							$("#leftDiv").append(groName);
							$("#dutyOrderId").val(id);
							var data = result.selectDutyOrderById;
							$("#groupName").val(data.groupName);
							var idUserGroup = data.idUserGroup;
							var nameUserGroup = data.nameUserGroup;
							var idUser = idUserGroup.split(",");
							var nameUser = nameUserGroup.split(",");
							var str="";
							$("#dutyUserText").empty();
							for(var i=0;i<idUser.length;i++){
								str+="<a href='#' title='"+nameUser[i]+"'>"+nameUser[i]+"<i>"+idUser[i]+"</i><span>×</span></a>"
								$.each($("#duty_user option"),function(){
									if($(this).val()==idUser[i]){
										$(this).addClass("bulingbuling");
										$(this).hide();
									}
								})
							}
							$("#dutyUserText").append(str);
							$("#dutyUserText a span").click(function(e){
								$(this).parent().remove();
								e.stopPropagation()
								var newidcode=$(this).prev().text();
								$.each($("#duty_user option"),function(){
									if($(this).val()==newidcode){
										$(this).removeClass("bulingbuling");
										$(this).show();
									}
								})
								
							})	
						}
						$("#groupName").attr("readOnly","true");
					}
				})
			})
			function groupName(id){
				$.ajax({
					type:"post",
					data:{"id":id},
					url:getPath()+"/selectDutyOrder.do",
					success:function(data){
						$("#hidGroupName").val(data.groupName);
						$("#dutyOrderId").val(id);
						$("#hidGroupId").val(id);
						$("#groupName").val(data.groupName);
						var idUserGroup = data.idUserGroup;
						var nameUserGroup = data.nameUserGroup;
						$("#hidDutyUserTextId").val(idUserGroup);
						$("#hidDutyUserTextName").val(nameUserGroup);
						var idUser = idUserGroup.split(",");
						var nameUser = nameUserGroup.split(",");
						var str="";
						$("#dutyUserText").empty();
						for(var i=0;i<idUser.length;i++){
							str+="<a href='#' title='"+nameUser[i]+"'>"+nameUser[i]+"<i>"+idUser[i]+"</i><span>×</span></a>"
							$.each($("#duty_user option"),function(){
								if($(this).val()==idUser[i]){
									$(this).addClass("bulingbuling");
									$(this).hide();
								}
							})
						}
						$("#dutyUserText").append(str);
						$("#dutyUserText a span").click(function(e){
							$(this).parent().remove();
							e.stopPropagation()
							var newidcode=$(this).prev().text();
							$.each($("#duty_user option"),function(){
								if($(this).val()==newidcode){
									$(this).removeClass("bulingbuling");
									$(this).show();
								}
							})
						})
					$("#updateCancelHide").show();
					$("#newDutyOrder").show();
					$("#addDutyOrder").hide();
					$("#cancel").show();
					$("#deleteDutyOrder").show();
					}
				})
			}
</script>
  <script>
        var datime=[""]

        $(function(){
        	$("#leftDiv li:eq(0) a").click()
            var nDate= new Date();
            var nYear=nDate.getFullYear();
            var nMonth=nDate.getMonth();
            $.each($("#yearSelect option"),function(){
                if($(this).text()==nYear){
                    $(this).attr("selected","selected");
                }
            })
            //新建按钮
		    $("#newDutyOrder").click(function(){
		    	$("#dutyOrderId").val("");
		    	$("#groupName").val("");
		    	$("#dutyUserText").text("");
		    	$("#duty_user option").removeClass();
		    	$("#duty_user option").css("display","");
		    })
		    //删除
		    $("#deleteDutyOrder").click(function(){
		    	var id = $("#dutyOrderId").val();	
		    	if(id==""||id.length==0){
		    		alert("请选择要删除的！");
		    		return false;
		    	}
		    	if(!confirm("确定删除这个排班组吗？")){
	    			return ;
	    		}
		    	var id = $("#dutyOrderId").val();
		    	$.ajax({
		    		type:"post",
		    		url:getPath()+"/deleteDutyOrder.do",
		    		data:{"id":id},
		    		success:function(){
		    			window.location.reload();
		    		}
		    	})
		    })
        });
        function QkCalendar(idName,n,data){
            var str="";
            var $idName= $("#"+idName);
            $idName.empty();
            $idName.attr("rel",n)
            var oDate= new Date();
            var iYear=oDate.getFullYear();
            var iMonth=oDate.getMonth();
            var today=oDate.getDate();
            oDate.setMonth(oDate.getMonth()+n,1);
            var year=oDate.getFullYear();
            var month=oDate.getMonth();
            var whatWeek=oDate.getDay();
            oDate.setMonth(month,0);
            var nextDate=oDate.getDate();
            oDate.setMonth(month+1,0);
            var allDate=oDate.getDate();
            $idName.addClass("QkCalendar");
            str+="<h4 id='claTit' rel='"+year+"' type='"+(month+1)+"'>"+year+"年"+(month+1)+"月</h4>";
            str+="<ul class='week'><li style='color: red'>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li style='color: red'>六</li></ul><ul id='dayList' class='dayList'></ul>";
            $idName.append(str);
            var dayStr="";
        for(var i=whatWeek-1;i>=0;i--){
            dayStr+="<li style='color:#aaa;'>"+(nextDate-i)+"</li>"
        }
        for(var k=1;k<allDate+1;k++){
            var list=(k-1)%data.length;
            if(k==today&&iYear==$("#claTit").attr("rel")&&iMonth+1==$("#claTit").attr("type")){
                dayStr+="<li style='background:orangered;color:#fff;'>"+k+"<span>"+data[list]+"</span></li>"
            }else{
                if((whatWeek+k)%7==0||(whatWeek+k)%7==1){

                    dayStr+="<li style='color:red;'>"+k+"<span>"+data[list]+"</span></li>"
                }else {
                    dayStr+="<li>"+k+"<span>"+data[list]+"</span></li>"
                }
            }
        }
        var num=42-(whatWeek+allDate);
        for(var j=0;j<num;j++){
            dayStr+="<li style='color:#aaa;'>"+(j+1)+"</li>"
        }
        $("#dayList").append(dayStr);
    }
    QkCalendar("calendarA",0,datime);


</script>
</body>
</html>