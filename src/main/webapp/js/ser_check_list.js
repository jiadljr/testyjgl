//树形插件
$(function(){
				var serviceUrl = getPath()+"/selectServiceTypeAll.do";
				var serviceOne;
				var serviceTwo;
				var serviceThree;
				$.ajax({
					url:serviceUrl,
					data:{},
					type:'post',
					async:false,
					success:function(result){
						serviceOne = result.serviceOne;
						serviceTwo = result.serviceTwo;
						serviceThree = result.serviceThree;
						$.each(serviceOne,function(i,one){
							var serviceType = "<li  class=\"serviceOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryServiceType("+serviceOne[i].id+",\'"+one.name+"\')\">";
							serviceType += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"serOne"+i+"\">";
							$.each(serviceTwo,function(j,two){
								if(serviceOne[i].id == serviceTwo[j].parentId){
									serviceType += "<li class=\"serviceTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryServiceType("+ serviceTwo[j].id + ",\'"+two.name+"\')\">";
									serviceType += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"serTwo"+j+"\">";	
									$.each(serviceThree,function(k,three){
										if (serviceTwo[j].id == serviceThree[k].parentId) {
											serviceType += "<li><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryServiceType("+serviceThree[k].id+",\'"+three.name+"\')\">";
											serviceType +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a>";
										}
									})
									serviceType+="</ul></li>"; 
								}
							})
							serviceType += "</ul></li>"; 
							$(".service_bzmenus_sub").append(serviceType);
							$("#serviceSidebar a").click(function(){
								$("#serviceSidebar a").css({"color":"#183152"});   
								$(this).css({"color":"red"});
								$("#openServiceTree").css({"color":"#183152"})
								 
							})
							 
						})
					}
				})
				removeServiceLi();
				$("#openServiceTree").click();
				$("#openServiceTree").css({"color":"#183152"})
				
				$("#serviceSearchTree").one("click",function(){
				$.each($(".collapsable"),function(){
					$(this).click();
				})
				$(".last_collapsable").click();
			})
		})
		   function showServiceTree(){
			$("#serviceTreeDiv").show();
			$("#serviceSidebar").show();
			$('body').bind("mousedown", onBodyDownService);
		}
		function onBodyDownService(event) { 
		   if (!(event.target.id == "menuBtn" || event.target.id == "serviceTreeDiv" || $(event.target).parents("#serviceTreeDiv").length > 0)) {  
			$("#serviceTreeDiv").hide();
			$("#serviceSidebar").hide();
		   }  
		}//服务类型结束
		function queryServiceType(id,name){
			$("#serviceId").val(id);///
			$("#serviceSearchTree").val(name);
			$("#serviceTreeDiv").hide();
			$("#serviceSidebar").hide();
		}
		function removeServiceLi() {
			if($(".serviceOne").length>0){
				for (var n = 0; n < $(".serviceOne").length; n++) {
					if ($("#serOne" + n + " li").length == 0) {
						$("#serOne" + n).remove();
					}
				}
			}
			if($(".serviceTwo").length>0){
				for (var m = 0; m < $(".serviceTwo").length; m++) {
					if ($("#serTwo" + m + " li").length == 0) {
						$("#serTwo" + m).remove();
					}
				}
			}
			
			$("#serviceTreeDiv a").click(function(){
				$("#serviceTreeDiv a").css({"color":"#183152"});
				$(this).css({"color":"red"});
				$("#openServiceTree").css({"color":"#183152"})
				 
			})
		}

//列表展示
function showCheckList(){
	$("#pageTable").empty();
	$('#pageTable').bPage({
		url : getPath()+"/selectCheckList.do",
		//开启异步处理模式
		asyncLoad : true,
		//关闭服务端页面模式
		serverSidePage : false,
		//数据自定义填充
		render : function(result){
			var checkList=result.checkList;
			var str="";
			var tbody = $('#tbody').empty();
			var pageNumber = result.pageNumber;
			var pageSize = result.pageSize;
			var totalPage = result.totalPage;
			var totalRow = result.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			$("#bPageDropList").val(pageSize);
			for (var i = 0; i < checkList.length; i++) {
				var check = checkList[i];
				var id=check.id;
				var code = check.eventCode;
				code=isnull(code);
				var name=check.eventName;
				name=isnull(name);
				//部门
				var extend1=check.extend1;
				extend1=isnull(extend1);
				var dateCreate=check.dateCreate;
				dateCreate=isnull(dateCreate);
				var dateAccept=check.dateAccept;
				dateAccept=isnull(dateAccept)
				var eventLevel=check.eventLevel;
				eventLevel = isnull(eventLevel);
				if (eventLevel == "1") {
					eventLevel="一级事件";
				}else if (eventLevel == "2"){
					eventLevel="二级事件";
				}if (eventLevel == "3"){
					eventLevel="三级事件";
				}
				var eventPriority=check.eventPriority;
				eventPriority=isnull(eventPriority);
				if (eventPriority=="1") {
					eventPriority="高";
				}else if (eventPriority=="2") {
					eventPriority="中";
				}else if (eventPriority=="3") {
					eventPriority="低";
				}
				str+="<tr><td>"+code+"<input type='hidden' value="+id+"/></td>"+
						"<td>"+name+"</td>"+
						"<td>"+extend1+"</td>"+
						"<td>"+getFormatDate(new Date(dateCreate), "yyyy-MM-dd hh:mm:ss")+"</td>"+
						"<td>"+getFormatDate(new Date(dateAccept), "yyyy-MM-dd hh:mm:ss")+"</td>"+
						"<td>"+eventPriority+"</td>"+
						"<td class='color'>"+eventLevel+"</td>"+
						"<td><a href='javascript:check("+id+")' class='btn btn-primary btn-sm' >查看</a></td>"+
						+"</tr>";
			}
		
			$("#tbody").append(str);
			var relTable=$('.relTable');
			
			var RH=$('.mRight').height()-182;
			$('.mRbot').height($('.mRight').height()-145);
	        var relTab=$('.relTable .table');
	        var thTab=$('.fixTable .table tr th');
	        var tdTab=$('.relTable .table tr:eq(0) td');
	        var len=thTab.length;
	        
	        relTable.height(RH);
	        var relTabHeight=relTab.height();
	       
	        for(var thI=0;thI<(len-1);thI++){
                  tdTab.eq(thI).width(thTab.eq(thI).width()) ;
  
              }
	        if(RH<relTabHeight){
	        	  for(var thI=0;thI<(len-1);thI++){
	                  tdTab.eq(thI).width(thTab.eq(thI).width()) ;
	  
	              }
	        	  

	            relTable.addClass('relAuto')
	        }else{
	            relTable.removeClass('relAuto')
	        }
			for(i=0;i<$(".color").length;i++){
				if($(".color").eq(i).text()=="一级事件"){
					$(".color").eq(i).parent().css("background-color","#ff4c00");
				}
				if($(".color").eq(i).text()=="二级事件"){
					$(".color").eq(i).parent().css("background-color","#ff9400");
				}
				if($(".color").eq(i).text()=="三级事件"){
					$(".color").eq(i).parent().css("background-color","#ffd040");
				}
				if($(".color").eq(i).text()=="四级事件"){
					$(".color").eq(i).parent().css("background-color","#0b5fa5");
				}
			}		
			$("#pages").val("");
		},
		params : function(){
			var pages = $("#pages").val();
			  var pagenumber = $("#pagenumber").val();
			  var pagesize = $("#pagesize").val();
			  var totalpage = $("#totalpage").val();
			  var totalrow = $("#totalrow").val();
			var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			var eventLevel=$("#eventLevel").val();
			var eventPriority=$("#eventPriority").val();
			var eventService=$("#serviceId").val();
			if($("#serviceSearchTree").val() == ""){
				eventService = "";
			}
			return {
				pages:pages,
	        	pagenumber:pagenumber,
	        	pagesize:pagesize,
	        	totalpage:totalpage,
	        	totalrow:totalrow,
				startTime : startTime,
				endTime : endTime,
				eventLevel : eventLevel,
				eventPriority : eventPriority,
				eventService : eventService,
			};
		}
	});
}
function check(id){
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href=getPath()+"/toCheck.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href=getPath()+"/toCheckList.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}

