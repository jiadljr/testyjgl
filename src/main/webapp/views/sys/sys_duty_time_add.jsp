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
	#myDiv{ display:none;}
	#leftDiv{ width:220px; overflow-y:auto;overflow:hidden; height:300px; float:left; margin:5px; box-shadow:0 0 3px #aaa;}
	#leftDiv li { height:28px;width:100%; line-height:28px; text-indent: 2em;}
	#leftDiv li .hover{background:#3b85c7;color:#fff;} 
	#leftDiv li:hover a{background:#3b85c7;color:#fff; } 
	#leftDiv li a{ color:#666; display:block ; transition: all 0.2s; }
	.rightDuty{width:510px; height:300px; float:left;}
	.rightDuty .pList{ width:100%;height:40px; line-height:40px; overflow:hidden; float:left; }
	.rightDuty label{ width:140px; text-align:right; display:inline-block; float:left;}
	.rightDuty .pList input{ height:30px;float:left; width:200px; margin-top:5px;}
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
		.btnRankClass{ width:300px; text-align:right; position:absolute; top:35px; right:0;z-index:100;}
	
</style>
</head>
<body>
<div class="mRight">
	<div class="mRpos">
		<ol class="breadcrumb">
		    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
		    <li><a href="javascript:;">管理体系</a></li>
		    <li class="active">排班管理</li>
		</ol>
	</div>
	<div class="btnRankClass">	
		 <input type="hidden" id="token" name="token" value="${token}" />
         <a href="#" class="btn_green" id="deleteDuty"  onclick="addDutyOrderB()">保存</a>   
		 <a href="#" class="btn_red"id="qbutton">取消</a>
	</div>
	<div class="newOnClass">
		<div class="newClassAdd">
			<div class="dataList">
				<label>请选择日期：</label>
				<select id="yearSelect" style="margin-right:10px;">
			  		  <option>2018</option>
			  		  <option>2019</option>
			  		  <option>2020</option>
			  		  <option>2021</option>
			  		  <option>2022</option>
			  		  <option>2023</option>
			  		  <option>2024</option>
			  		  <option>2025</option>
			  		  
	  			 </select>
	  			 <select id="monthSelect">
	      
	   			</select>
			</div>
			<div class="classList"	>
				<input id="spanHidden" type="hidden">
				<label> 请选择值班组：</label>
				<select id="groupNameSelect" >
		 			<option value="">请选择值班组</option>
		   			<c:forEach items="${selectDutyOrder }" var="order">
						<option value = "${order.id }">${order.groupName }</option>
					</c:forEach>
 		 		</select>
 		 		<a href="javascript:layerEditClass();" id="dutyGroup" class="btn_blue" >编辑值班组</a>
			</div>
			<ul id="groupSpan">
			
			</ul>
		</div>
		<div class="workBox">
			<div class="calendarA" id="calendarA"></div>
			<div class="descWork">
				<p>值班说明</p>
				<textarea id="remark"></textarea>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
function loadNewPage(){
	location.reload();
}
$("#leftDiv li a").click(function(){
	$("#leftDiv li a").removeClass("hover")
	$(this).addClass("hover")
})

function layerEditClass(){
	 layer.open({
         type: 2,
         title: '编辑值班组',
         shadeClose: false,
         area: ['760px', '440px'],
         content:getPath()+"/addOrder.do",
     });
}


   $(function(){
	   selectMonth("");
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
	   qbut();
	   $("#yearSelect").change(function(){
		   selectMonth($("#yearSelect").val());
	   })
   })
   function qbut(){
	   $("#qbutton").click(function(){
		  location.href=getPath()+"/queryDutyList.do";
	   })
   }
   function selectMonth(dutyTime){
	   $.ajax({
		   type:"post",
		   url:getPath()+"/seleMonth.do",
		   data:{"dutyTime":dutyTime},
		   dataType:"json",
		   success:function(data){
			   $("#monthSelect").text("");
			   var selectMonth = "";
			   for(var i = 0; i <data.length;i++){
					   selectMonth += "<option>"+data[i]+"</option>"
						
			   }
			   $("#monthSelect").append(selectMonth);
		   
	          
		   }
	   })
   }
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
					success:function(){
						window.location.reload();
					}
				})
			})
			function groupName(id){
				$.ajax({
					type:"post",
					data:{"id":id},
					url:getPath()+"/selectDutyOrder.do",
					success:function(data){
						$("#dutyOrderId").val(id);
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
				})
			}
			function addDutyOrderB(){
				var yearSelect = $("#yearSelect").val();
				var monthSelect = $("#monthSelect").val();
				var spanHidden = $("#spanHidden").val();
				var remark = $("#remark").val();
				var token = $("#token").val();
				$.ajax({
					type:"post",
					data:{"token":token,"remark":remark,"yearSelect":yearSelect,"monthSelect":monthSelect,"spanHidden":spanHidden},
					url:getPath()+"/addDutyTime.do",
					success:function(){
						window.location.href = getPath()+"/queryDutyList.do";
					}
				})
			}
</script>
  <script>
        var datime=[""]

        $(function(){
            var nDate= new Date();
            var nYear=nDate.getFullYear();
            var nMonth=nDate.getMonth();
            $.each($("#yearSelect option"),function(){
                if($(this).text()==nYear){
                    $(this).attr("selected","selected");
                }
            })
			function runClass(){            	
				var id = $("#groupNameSelect").val();
				$("#groupSpan").html("");
			    var num2=$("#yearSelect option:selected").text()-0;
                var num1=(num2-nYear)*12;
                var num4=$("#monthSelect option:selected").text()-0;
                var num5=(num4-nMonth-1)+num1;
	            var arr=[];
	            if($("#groupNameSelect option:selected").val()==""){	            	
	            	arr=[""];
	            	QkCalendar("calendarA",num5,arr);
	            }else{
	            	$.ajax({
						type:"post",
						data:{"id":id},
						url:getPath()+"/selectDutyOrder.do",
						success:function(data){
							var idUserGroup = data.idUserGroup;
							var nameUserGroup = data.nameUserGroup;
							var nameUser = nameUserGroup.split(",");
							$("#spanHidden").val(idUserGroup);							
							var str="";
							for(var i = 0;i<nameUser.length;i++){
								str+="<li><i class='fa fa-user'></i> "+nameUser[i]+"</li>"
							}
							$("#groupSpan").append(str);
							arr=nameUser;
			            	QkCalendar("calendarA",num5,arr);
						}
					})
	            }
				
            }
            $("#yearSelect").change(function(){
            	runClass()
            });
            $("#monthSelect").change(function(){
            	runClass()
            });
			$("#groupNameSelect").change(function(){
				runClass()
			
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