$(function(){
		$("#sidebar").hide();
		$("#searchTree").keyup(function(){
			if ($("#searchTree").val() != "") {
				$("#searchTreeClose").show();
				removeLi();
			} else {
				$("#searchTreeClose").hide();
				removeLi();
			}
			$('#a').colExpAll({
				clickType : 'search'
			});
			removeLi();
		})
		var url = getPath()+"/findAllDept.do";
		var deptOne;
		var deptTwo;
		var deptThree;
		var deptFour;
		$.ajax({
			url:url,
			data:{},
			type:'post',
			async:false,
			success:function(result){
			deptOne = result.deptOne;
			deptTwo = result.deptTwo;
			deptThree = result.deptThree;
			deptFour = result.deptFour;
			$.each(deptOne,function(i,one){
				var type = "<li  class=\"deptOne\"><a autobypy="+one.extend2 +"href=\"#\" onclick=\"queryType("+deptOne[i].id+",\'"+one.name+"\')\">";
					type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+one.name+"</a><ul id=\"one"+i+"\">";
				$.each(deptTwo,function(j,two){
					if(deptOne[i].id == deptTwo[j].parentId){
						type += "<li class=\"deptTwo\"><a autobypy="+two.extend2 +"href=\"#\" onclick=\"queryType("+ deptTwo[j].id + ",\'"+two.name+"\')\">";
						type += "<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+two.name+"</a><ul id=\"two"+j+"\">";	
						$.each(deptThree,function(k,three){
							if (deptTwo[j].id == deptThree[k].parentId) {
								type += "<li class=\"deptThree\"><a autobypy="+three.extend2 +"href=\"#\" onclick=\"queryType("+deptThree[k].id+",\'"+three.name+"\')\">";
								type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+three.name+"</a><ul id=\"three"+k+"\">";
								$.each(deptFour,function(m,four){
									if (deptThree[k].id == deptFour[m].parentId) {
										type += "<li><a autobypy="+four.extend2 +"href=\"#\" onclick=\"queryType("+deptFour[m].id+",\'"+four.name+"\')\">";
										type +="<span class=\"font_pos\"><i class=\"external_small\"></i></span>"+four.name+"</a>";
									}
								})
								type+="</ul></li>";
							}
						})
						type+="</ul></li>"; 
					}
				})
				type += "</ul></li>"; 
				$(".bzmenus_sub").append(type);
				$("#treeDiv a").click(function(){
					$("#treeDiv a").css({"color":"#183152"});
					$(this).css({"color":"red"});
					$("#openTree").css({"color":"#183152"})
					 
				})
				 
			})}
		})
		removeLi();
		$("#openTree").click();
		$("#openTree").css({"color":"#183152"})
		
		$("#searchTree").one("click",function(){
		$.each($(".collapsable"),function(){
			$(this).click();
		})
		$(".last_collapsable").click();
	})
})
function moreFile(){
	layer.open({
	    type: 2,
	    title: '批量导入',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '400px'],
	    content: ['views/proj/2.jsp','yes']
	 })
}
function queryType(id,name){
		$("#idDept").val(id);
		$("#searchTree").val(name);
		$("#treeDiv").hide();
		$("#sidebar").hide();
}
function showTree(){
	$("#treeDiv").show();
	$('body').bind("mousedown", onBodyDown);
	$("#sidebar").show();
}
function onBodyDown(event) {
   if (!(event.target.id == "menuBtn" || event.target.id == "treeDiv" || $(event.target).parents("#treeDiv").length > 0)) {  
	$("#treeDiv").hide();
	$("#sidebar").hide();
   }
}


function proEdit(){
	$("#onOverShow").show();
	$("#back").hide();
	$("#projMember option").click(function(e){
		e.stopPropagation();
	})
	$("#proEdit").hide();
	$("#proExit").show();
	$("#proSave").show();
	$(".proEditIn").css({"border":"1px solid #ccc","background":"#fff"});
	$(".proEditIn").removeAttr("disabled");
	$(".timeInput").css("width","28%");
	$(".blockEnter").hide();
	$(".proEditBox").hide();
	$(".proEditBtn").show();
}
function proExit(){
	location.reload();
}//保存添加
//我的项目任务列表：列表形式展示
function showProList(){
	var projStatus = $("#projStatus").val();
	var idTaskHead = $("#idTaskHead").val();
	var milestoneTask = $("#milestoneTask").val();
	var taskName = $("#taskName").val();
	var projCode = $("#projCode").val();
	var state = $("#state").val();
	$("#projBody").empty();
	$.ajax({
	     url:getPath() + "/projTaskList.do",
        data:{"idTaskHead":idTaskHead,"milestoneTask":milestoneTask,"taskName":taskName,"projCode":projCode,"state":state},
        type:'post',
        dataType:'json',
        async:false,
        success:function(data) {
        	var projTaskList = data.projTaskList;
			var arrData = JSON.stringify(projTaskList, null, null);
			arr=JSON.parse(arrData)
	
			var  str=""
	
	        for(var k=0;k<arr.length;k++){
	        	var schedule=arr[k].taskSpeed;
	        	schedule=Number(schedule*100).toFixed(0);
	        	schedule+="%";
	        	var flag="";
	        	if(arr[k].milestoneTask==0){
	        		flag="flagHide"
	        	}else{
	        		flag="flagShow"
	        	}
	        	var status = "";
				if(arr[k].taskStatus == "80"){
					status = "进行中";
				}else if(arr[k].taskStatus == "85"){
					status = "完成";
				}
				var warning=""
				if(arr[k].pf == 1){
					warning="rShowWarn"//alert("超期啦！！");//
	            }else{
	            	warning="rHideWarn"
	            }
	
				var taskNameA = "";
	        	if(state != "projControl"){
	        		taskNameA="<a title='"+arr[k].nameTask+"' href='javascript:void(0);' onclick = \"dealTask("+arr[k].id+","+projStatus+",'"+projCode+"')\" class='downLine' >"+arr[k].nameTask+"</a>";
	        	}else{
	        		taskNameA = arr[k].nameTask;
	        	}
	        	str+="<tr class='level1' type='"+arr[k].parentId+"' rel='"+arr[k].id+"'><td class='titTxt'><span class=''>+</span>"+taskNameA+"<b class='iconClass'><i title='任务超期' class='"+warning+" fa fa-warning'></i><i title='里程碑任务' class='"+flag+" fa fa-flag'></i></b></td><td>"+arr[k].extend2+"</td><td>"+getFormatDate(new Date(arr[k].dateStart), "yyyy-MM-dd")+"</td><td>"+getFormatDate(new Date(arr[k].dateEnd), "yyyy-MM-dd")+"</td><td>"+"<span id='taskStatusSpan"+arr[k].id+"'>"+status+"</span></td><td class='schedule'>" +"<span>"+schedule+"</span><p><b style='width:"+schedule+"';></td>";
            	if(state != "projControl"){
            		str += "<td class='nostr' style='position:relative;'><a id='proBtn"+arr[k].id+"' href='#'  onclick=\"showHoverUl("+arr[k].taskStatus+","+arr[k].id+",'"+arr[k].nameTask+"')\"; class='btn btnSlide btn_green' style='width:50px; display:inline-block; line-height:16px; height:16px; padding:0; border-radius:25px;'>···</a><ul class='btnClick'></ul></td>";
            	}
            	str += "</tr>";
	        }
			$("#projBody").append(str);
	    	$(".btnSlide").mouseover(function(){
				$(this).click(function(e){
					$(this).next().show();   					
				})
				$(this).click();
			})
        }
	})

}
//我的项目任务列表：树形结构展示
function showPrpList(){
	var projStatus = $("#projStatus").val();
	var idTaskHead = $("#idTaskHead").val();
	var milestoneTask = $("#milestoneTask").val();
	var taskName = $("#taskName").val();
	var projCode = $("#projCode").val();
	var state = $("#state").val();
   function GetList(id,arr) {
        var arrA=[];
        
      
        for (var i in arr) {
            if (arr[i].pId != id)
                arrA.push(arr[i]);
        }
        for(var k=arr.length-1;k>=0;k--){
        	var schedule=arr[k].taskSpeed;
        	schedule=Number(schedule*100).toFixed(0);
        	schedule+="%";
        	if(arr[k].level==null){
        		arr[k].level=1;
        	}
        	var flag="";
        	if(arr[k].milestoneTask==0){
        		flag="flagHide"
        	}else{
        		flag="flagShow"
        	}
        	var status = "";
			if(arr[k].taskStatus == "80"){
				status = "进行中";
			}else if(arr[k].taskStatus == "81"){
				status = "已超期";
			}else if(arr[k].taskStatus == "85"){
				status = "完成";
			}
        	if(arr[k].parentId==null||arr[k].parentId=="null"){
        		arr[k].parentId=0;
        	}

            if(arr[k].parentId==id){
            	var dateStart = arr[k].dateStart;
            	var dateEnd = arr[k].dateEnd;
            	
            	if (dateStart == null && dateStart == undefined) {
            		dateStart = "";
            	}else{
            		dateStart = getFormatDate(new Date(dateStart), "yyyy-MM-dd");
            	}
            	if (dateEnd == null && dateEnd == undefined) {
            		dateEnd = "";	
            	}else{
            		dateEnd = getFormatDate(new Date(dateEnd), "yyyy-MM-dd");
            	}
            	var warning=""
    				if(arr[k].pf == 1){
    					warning="rShowWarn"//alert("超期啦！！");//
    	            }else{
    	            	warning="rHideWarn"
    	            }
            
            	var taskNameA = "";
	        	if(state != "projControl"){
	        		taskNameA="<a href='javascript:void(0);' onclick = \"dealTask("+arr[k].id+","+projStatus+",'"+projCode+"')\" class='downLine' title='"+arr[k].nameTask+"'>"+arr[k].nameTask+"</a>";
	        	}else{
	        		taskNameA = arr[k].nameTask;
	        	}
	        	taskNameA = isnull(taskNameA);
            	var taskStr = "<tr class='level"+arr[k].level+"' type='"+arr[k].parentId+"' rel='"+arr[k].id+"'><td class='titTxt'><span class=''>+</span>"+taskNameA+"<b class='iconClass'><i  title='任务超期' class='"+warning+" fa fa-warning'></i><i  title='里程碑任务' class='"+flag+" fa fa-flag'></i></b></td><td>"+isnull(arr[k].extend2)+"</td><td>"+dateStart+"</td><td>"+dateEnd+"</td><td id='taskStatusSpan"+arr[k].id+"'>"+status+"</td><td class='schedule'>" +"<span>"+schedule+"</span><p><b style='width:"+schedule+"';></td>";
            	if(state != "projControl"){
            		taskStr += "<td class='nostr' style='position:relative;'><a id='proBtn"+arr[k].id+"' href='#'  onclick=\"showHoverUl("+arr[k].taskStatus+","+arr[k].id+",'"+arr[k].nameTask+"')\"; class='btn btnSlide btn_green' style='width:50px; display:inline-block; line-height:16px; height:16px; padding:0; border-radius:25px;'>···</a><ul class='btnClick'></ul></td>";
            	}
            	taskStr += "</tr>";
            	$("tr[rel='"+id+"']").after(taskStr);
                GetList(arr[k].id,arrA)
            }
        }
    }
	$.ajax({
	     url:getPath() + "/projTaskList.do",
         data:{"idTaskHead":idTaskHead,"milestoneTask":milestoneTask,"taskName":taskName,"projCode":projCode,"state":state},
         type:'post',
         dataType:'json',
         async:false,
         success:function(data) {
    		var projTaskList = data.projTaskList;
			var arrData = JSON.stringify(projTaskList, null, null);
			arrData=JSON.parse(arrData)
			$("#projBody").empty();	
			$("#projBody").append("<tr rel='0'></tr>");
	        GetList(0,arrData);
	    	$(".btnSlide").mouseover(function(){
				$(this).click(function(e){
					$(this).next().show();   					
				})
				$(this).click();
			})
            $.each($("#projBody tr"),function(){
            	
                if($(this).attr("rel")==$(this).nextAll().attr("type")){
                    if($(this).css("display")=="none"){
                        $("tr[type='"+$(this).attr("rel")+"']").hide();
                    }
                    $(this).children().children("span").addClass("showBtn")
                }else{
                    $(this).children().children("span").removeClass("showBtn")
                }
                if($(this).children().children("span").html()=="+"&&$(this).attr("rel")==$(this).nextAll().attr("type")){
                	  $("tr[type='"+$(this).attr("rel")+"']").hide();
                }else{
                	  $("tr[type='"+$(this).attr("rel")+"']").show();
                }
            })
            var $btn=$(".showBtn");
            $btn.click(function(){            
            	var $rel=$(this).parent().parent();
                var rel=$rel.attr("rel");
                if($(this).html()=="+"){
                	
                    $(this).html("-");
                    $("tr[type='"+rel+"']").show();
       
                    $.each($rel.prev().nextAll(),function(){
                    	
                        if($(this).children().children("span").html()=="-"&&$(this).css("display")!="none"){
                            $("tr[type='"+$(this).attr("rel")+"']").show();
                        }else{
                        	
                        }
                        if($(this).next().nextAll().attr("class")=="level1"){
                        	
                        	return;
                        }
                    })
       
                }else {
                
                    $(this).html("+");
                    $("tr[type='"+rel+"']").hide();
                    $.each($("#projBody tr"),function(){
		                if($(this).children().children("span").html()=="+"){
		               
		                	  $("tr[type='"+$(this).attr("rel")+"']").hide();
		                }else if($(this).children().children("span").html()=="-"&&$(this).css("display")!="none"){
		                	$("tr[type='"+$(this).attr("rel")+"']").show();
		                
		                }
                  })
                    $.each($rel.prev().nextAll(),function(){
                        if($(this).css("display")=="none"){
                            $("tr[type='"+$(this).attr("rel")+"']").hide();
                        }
                    })
          
                }
            })
    		
         }
	})
}
//我的项目编辑中任务列表的操作
function showHoverUl(TaskStatus,id,taskName){
	var projStatus = $("#projStatus").val();
	var str="";
	$(".btnClick").hide();
	$proBtn=$("#proBtn"+id)
	$proBtn.next().empty();
	$proBtn.next().show();
	$proBtn.next().css("display","block")
	if(projStatus != 96 && projStatus != 97){
		if ($("#taskStatusSpan"+id).html() != "完成") {
			str += "<li class='taskSon'><a href='javascript:addChildrenTask("+id+");' >添加子任务</a></li>";
		}
		
		str += "<li><a href='javascript:promptBox(2,"+id+");'>任务进度</a></li>"+
				  "<li><a href='#' onclick=\"taskTalk("+id+",'"+taskName+"')\" >任务记录</a></li>"+
				  "<li><a href='javascript:delTask("+id+");' >删除</a></li>"+
				  "<li><a href='javascript:editTask("+id+");'>编辑</a></li>";
		
	}
						
	$proBtn.next().append(str);
	
	var $li=$proBtn.next().find('li');
	
	$proBtn.next().width($li.length*60);

	$proBtn.next().mouseover(function(e){
		$(this).show();
		
		 e.stopPropagation();
	})
	$proBtn.next().mouseout(function(e){
		$(this).hide();
		
		 e.stopPropagation();
	})
	$proBtn.click(function(e){
		$(this).next().show();
		 e.stopPropagation();
	})
	
}
//任务进度
function promptBox(Mes,id,projStatus){
    var str="";
    str="<div class='promptWarp'></div>"+
        "<div class='promptBox' id='promptBox' onkeydown='promptEnter(event,"+id+","+Mes+");'>"+
           " <p>请输入进度：</p>"+
            " <input type='text' id='promptInput' />" +
            "<span>%</span>"+
            "<p id='promptTxt'></p>"+
            "<div class='btn-list'>"+
            "<a href='javascript:promptSure("+Mes+","+id+","+projStatus+")' class='promptSure'>确定</a>"+
            "<a href='javascript:promptHide();' class='promptExit'>取消</a></div></div>";
    $("body").append(str);

    $("#promptInput").val("").focus();
    $("#promptTxt").html("");
}
//进度提交
function promptSure(Mes,id,projStatus){
    var $num=$("#promptInput").val();
    $num=parseInt($num,10);
    if($num>=0&&$num<=100){    	
        promptHide();
        $("tr[rel='"+id+"'] .schedule").find("span").html($num+"%");
        $("tr[rel='"+id+"'] .schedule").find("b").css("width",$num+"%");
    }else if($num>100) {
        $("#promptInput").val("100");
    }else {
        $("#promptTxt").html("请输入0--100的数字！！！")
    }
    if($num == 100){
    	$("#taskStatusSpan"+id).html("完成");
    }else{
    	$("#taskStatusSpan"+id).html("进行中");
    }
    
    if($num>=0 && $num<=100){
    	//写入后台
        if (Mes == 1) {
        	$.ajax({
            	url:getPath()+"/updateProjSpeed.do",
        		data:{"projId":id,"projNum":$num,"projStatus":projStatus},
        		type:"post",
        		dataType:"text",
        		async: false,
        		success:function(suc){
        			if (suc == "succ") {
        				$("#pages").val(1);
        				showProjInfoList();
        			}
        		},
            })
    	}else if(Mes == 2){
    		$.ajax({
    	    	url:getPath()+"/updateTaskSpeed.do",
    			data:{"taskId":id,"taskNum":$num},
    			type:"post",
    			dataType:"text",
    			async: false,
    			success:function(suc){
    				if (suc == "succ") {
    					
    				}
    			},
    	    })
    	}
    }
}
function promptHide(){
    $(".promptWarp").remove();
    $(".promptBox").remove();
}
function promptEnter(event,id,Mes){
    if (event.keyCode==13)   //回车键的键值为13
        promptSure(Mes,id);  //调用登录按钮的登录事件
}
//任务记录
function taskTalk(taskId,taskName){
	var jump_url = getPath()+"/toProjTaskRecord.do?taskId="+taskId+"&taskName="+taskName;
	layer.open({
	    type: 2,
	    title: '任务记录',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '400px'],
	    content: [jump_url,'yes']
	  })
}
//添加
function addTask(){
	var projCode=$("#projCode").val();
	var jump_url=getPath()+"/toAddProjTask.do?projCode="+projCode;
	 layer.open({
		    type: 2,
		    title: '添加任务',
		    scrollbar: true,
		    shadeClose: false,
		    area: ['670px', '480px'],
		    content: [jump_url,'yes']

		  })
}
//取消添加
function QkCannelTask(){
	location.href=getPath()+"/toProjTaskPage.do";
}
//添加子任务
function addChildrenTask(taskId){
	var projCode=$("#projCode").val();
	var idProjManager=$("#idProjManager").val();
	var nameProjManager=$("#nameProjManager").val();
	var jump_url=getPath()+"/toAddProjTask.do?projCode="+projCode+"&taskId="+taskId+"&idProjManager="+idProjManager+"&nameProjManager="+nameProjManager;
	 layer.open({
		    type: 2,
		    title: '添加子任务',
		    scrollbar: true,
		    shadeClose: false,
		    area: ['670px', '480px'],
		    content: [jump_url,'yes']

		  })
}
//修改任务
function editTask(id){
	var projCode=$("#projCode").val();
	var jump_url = getPath()+"/toUpdateProjTask.do?id="+id+"&projCode="+projCode;
	layer.open({
	    type: 2,
	    title: '修改任务',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '480px'],
	    content: [jump_url,'yes']

	  })
}
//删除任务
function delTask(id){
	var flag = confirm("确定删除任务吗？");
	if(!flag){
		return;
	}

	$.ajax({
		url:getPath()+"/whetherProjTask.do",
		data:{'id':id},
		dataType:'text',
		type:'post',
		success:function(succ){
			if (succ == "success") {
				showPrpList();
			}else if(succ == "error"){
				if(!confirm("该任务下含有子任务，是否继续执行删除操作？？！")){
					return;
				}
				var ids = "";
				var idBuilder=id+",";
				var cssName=($("#projBody tr[rel='"+id+"']").attr("class")).substring(5)-0;
				$.each($("#projBody tr[rel='"+id+"']").nextAll(),function(){
					
					var attom=($(this).attr("class")).substring(5)-0;
					if(attom<=cssName){
						return false;
					}
					idBuilder+=$(this).attr("rel")+",";
					
				})
				//去点最后一个逗号
				idBuilder=idBuilder.substring(0,idBuilder.length-1)
				$.ajax({
					url:getPath()+"/deleteProjTask.do",
					data:{'ids':idBuilder},
					dataType:'text',
					type:'post',
					success:function(succ){
						if(succ == "success"){
							showPrpList();
						}
					},
					error:function(){
						alert("删除失败！")
					}
				})
			}
		},
		error:function(){
			
		}
	})
}

//项目编辑中的任务处理
function dealTask(taskId,projStatus,projCode){
	var jump_url = getPath()+"/toProjTaskDeal.do?taskId="+taskId+"&state="+"projTask"+"&projStatus="+projStatus+"&projCode="+projCode;
	layer.open({
	    type: 2,
	    title: '任务查看',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '400px'],
	    content: [jump_url,'yes']

	  })
}
function addSave(){
	//非空验证
	var projType = $("#projType option:selected").val();
	var projManager = $("#projManager option:selected").val();
	
	if(projType==""||projType==undefined){
		$("#projType").addClass("error");
	}
	if(projManager=="" || projManager==undefined){
		$("#projManager").addClass("error");
	}
	$("#projType").focus(function(){
		$(this).removeClass("error")
	})
	$("#projManager").focus(function(){
		$(this).removeClass("error")
	})
	/*var REX=/^([1-9]\d*|0)(\.\d{1,2})?$/;
	if(!REX.test($("#projAmt").val())){

		$("#projAmt").addClass("error");
	}*/
	if($("#projAmt").val() == ""){
		$("#projAmt").addClass("error");
	}
	$.each($(".needIn"),function(){
	
		if($(this).val()==""||$(this).val().length==0){
			$(this).addClass("error");
		}
	})
	
	$("#dutyUserText").click(function(){
		$(this).removeClass("error")
	})
	$("input,div").focus(function(){
		$(this).removeClass("error")
	})
	$.each($(".needIn"),function(){
		if($(this).hasClass("error")){
			return;
		}
	})
	if($(".needIn").hasClass("error")||$("#projManager").hasClass("error")||$("#projType").hasClass("error")){
		return;
	}
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if (new Date(startTime).getTime() > new Date(endTime).getTime()) {
		alert("开始时间不能大于结束时间");
		return;
	}
	//处理项目成员的参数
    var projMembers = [];
	$.each($("#dutyUserText a i"),function(){
		projMembers.push($(this).text());
	})
	projMembers.push($("#projManager").val());//将项目经理也加到成员中
	var newArr = [projMembers[0]];
	for (var i = 0; i < projMembers.length; i++) {
		if(newArr.indexOf(projMembers[i]) == -1){
			 newArr.push(projMembers[i]);
		}
	}
	$("#projMembers").val(newArr);
	
	var formData = new FormData($('#form1')[0]);
	var fileProjIds = $("#fileProjDiv").text();
	var fileIds = $("#fileDiv").text();
	formData.append("fileProjIds",fileProjIds);
	formData.append("fileIds",fileIds);
	//部门输入框的值如果为空，将隐藏域中idDept的值也清空
	if($("#searchTree").val() == ""){
		$("#idDept").val("");
	}
	$.ajax({
		type:"post",
		url:getPath() + "/saveProjInfo.do",
		data:formData,
		traditional: true,
		async: false,
		processData : false,
        contentType : false,
		success:function(result){
			if(result.succ == "succ"){
				$("#projCode").val(result.projCode);
				location.href = getPath()+"/toUpdateProj.do?projCode="+result.projCode;//跳转修改页面
			}
		},
		error:function(result){
			alert("添加失败");
		}
	})
}
//保存编辑
function updateSave(){
	//非空验证
	var projType = $("#projType option:selected").val();
	var projManager = $("#projManager option:selected").val();
	
	if(projType==""||projType==undefined){
		$("#projType").addClass("error");
	}
	if(projManager=="" || projManager==undefined){
		$("#projManager").addClass("error");
	}
	$("#projType").focus(function(){
		$(this).removeClass("error")
	})
	$("#projManager").focus(function(){
		$(this).removeClass("error")
	})
	var REX=/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if($("#projAmt").val().length>12){
		alert("请输入10亿以下金额")
		$("#projAmt").addClass("error");
	}
	if(!REX.test($("#projAmt").val())){
			
		$("#projAmt").addClass("error");
	}
	
	$.each($(".needIn"),function(){
	
		if($(this).val()==""||$(this).val().length==0){
			$(this).addClass("error");
		}
	})
	
	$("#dutyUserText").click(function(){
		$(this).removeClass("error")
	})
	$("input,div").focus(function(){
		$(this).removeClass("error")
	})
	
	$.each($(".needIn"),function(){
		if($(this).hasClass("error")){
			return;
		}
	})
	if($(".needIn").hasClass("error")||$("#projManager").hasClass("error")||$("#projType").hasClass("error")){
	
		return;
	}
	//判断日期是否
	var startTime = $("#startTime").val();
	var endTime = $("#endTime").val();
	if (new Date(startTime).getTime() > new Date(endTime).getTime()) {
		alert("开始时间不能大于结束时间");
		return;
	}
	//处理项目成员的参数
    var projMembers = [];
	$.each($("#dutyUserText a i"),function(){
		projMembers.push($(this).text());
	})
	projMembers.push($("#projManager").val());//将项目经理也加到成员中
	var newArr = [projMembers[0]];
	for (var i = 0; i < projMembers.length; i++) {
		if(newArr.indexOf(projMembers[i]) == -1){
			 newArr.push(projMembers[i]);
		}
	}
	$("#projMembers").val(newArr.join(","));
	var formData = new FormData($('#form1')[0]);
	var fileProjIds = $("#fileProjDiv").text();
	var fileIds = $("#fileDiv").text();
	formData.append("fileProjIds",fileProjIds);
	formData.append("fileIds",fileIds);
	//部门输入框的值如果为空，将隐藏域中idDept的值也清空
	if($("#searchTree").val() == ""){
		$("#idDept").val("");
	}
	$.ajax({
		type:"post",
		url:getPath() + "/updateProj.do",
		data:formData,
		traditional: true,
		async: false,
		processData : false,
        contentType : false,
		success:function(result){
			if(result == "unLike"){
				alert("数据过期，请刷新后重试！");
			}
			if(result == "succ"){
				proExit();
			}
			
		},
		error:function(result){
			alert("编辑失败");
		}
	})
}
//取消按钮
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var paramProjName = $("#paramProjName").val();
	var paramProjType = $("#paramProjType").val();
	var projControl = $("#projControl").val();
	var pStatus = $("#pStatus").val();
	location.href = getPath() + "/toProjPage.do?pageNumber="+pageNumber+
	"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages+"&paramProjName="+paramProjName+
	"&paramProjType="+paramProjType+"&pStatus="+pStatus+"&projControl="+projControl;
}
//移除文件
function remove(index){
    $('#file' + index + '').remove();
    $('#del'+index+'').remove();
}
//删除文件，将项目文件id存放在div中
function delFile(fileId,projFileId){
	$("#fileProjDiv").append(projFileId+",");
	$("#fileDiv").append(fileId+",");
	$("#"+fileId).remove();
}
//文件下载
//
function downFile(fileId){
	$.ajax({
		data:{fileId: fileId},
		dataType : 'json',
		async: false,
		type : 'post',
		url : getPath() + "/exportDocLoad.do",
		success : function(result){
			if (result.data == "error") {
				alert("文件不存在!");
			}else {
				location.href = getPath() + "/downLoad.do?fileId=" + fileId;
			}
		}
	})
}
//改变项目类型的说明
function changeTypeRemark(){
	var typeId = $("#projType option:selected").val();
	$.ajax({
		data:{"typeId": typeId},
		dataType : 'json',
		async: false,
		type : 'post',
		url : getPath() + "/changeProjTypeRemark.do",
		success : function(data){
			$(".anwser .txt").text(isnull(data.remark));
		}
	})
	
}