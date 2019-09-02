/**三个函数放到js里*/
function findParent(treeObj,node){
    treeObj.expandNode(node,true,true,true);//展开treeObj中所有节点并获得焦点
    var pNode = node.getParentNode();//获取父节点
    findNextNode(node);//后一个节点
    findPreNode(node);//前一个节点
    if(pNode != null){
        hiddenNodes.push(pNode);//
        findParent(treeObj,pNode);//递归查询上上一级父节点
    }
}
function findNextNode(node) {//获取节点的下一个节点
  var nextNode=node.getNextNode();
  if(nextNode!=null){
        if(nextNode.lvl==1){
            return;
        }
        hiddenNodes.push(nextNode);
        var children=nextNode.children;
        if(children){
            for(var i in children){
                hiddenNodes.push(children[i]);
            }
     }
        findNextNode(nextNode);//递归获取
  }
}

function findPreNode(node) {//获取节点的上一个节点
  var preNode=node.getPreNode();
  if(preNode!=null){
        if(preNode.lvl==1){
            return;
        }
     hiddenNodes.push(preNode);
        var children=preNode.children;//获取节点的子节点集合
        if(children){
            for(var i in children){
                hiddenNodes.push(children[i]);
            }
        }
        findPreNode(preNode);
  }
}
function deptUp(txtObj){
	 if (txtObj.value.length > 0) {
		 deptZtrees();
            var zTree = $.fn.zTree.getZTreeObj("tree");
            var nodeList = zTree.getNodesByParamFuzzy("name", txtObj.value);
            //将找到的nodelist节点更新至Ztree内
            $.fn.zTree.init($("#tree"), setting, nodeList);
        } else {
            //隐藏树
        	deptZtrees();                
        }         
}
function queryApplyList(){
	$("#page2").empty();
	$('#page2').bPage({
			url:getPath() + "/selectEventInfoAll.do",
			asyncLoad : true,
		    serverSidePage : false,
			render:function(result){
				$("#tbody").empty();
				var pageNumber = result.pageNumber;
				var pageSize = result.pageSize;
				var totalPage = result.totalPage;
				var totalRow = result.totalRow;
				$("#pagenumber").val(pageNumber);
				$("#pagesize").val(pageSize);
				$("#totalpage").val(totalPage);
				$("#totalrow").val(totalRow);
				for(var i=0; i<result.selectAll.length;i++){
					var tr="";
					id=result.selectAll[i].id;
					code=result.selectAll[i].eventCode;
					code=isnull(code);
					name=result.selectAll[i].eventName;
					name=isnull(name);
					extend3 = result.selectAll[i].extend3;
					extend3=isnull(extend3);
					extend2 = result.selectAll[i].extend2;
					extend2=isnull(extend2);
					extend1 = result.selectAll[i].extend1;
					extend1=isnull(extend1);
					eventDesc = result.selectAll[i].eventDesc;
					eventDesc=isnull(eventDesc);
					eventStatus = result.selectAll[i].eventStatus;
					eventStatus=isnull(eventStatus);
					if(eventStatus == 20){
						eventStatus = "待受理";
					}else if(eventStatus == 50){
						eventStatus = "待处理";
					}else if(eventStatus == 70){
						eventStatus = "待评价";
					}else if(eventStatus == 77){
						eventStatus = "已完成";
					}else if(eventStatus == 29){
						eventStatus = "已撤销";
					}
					eventContact = result.selectAll[i].eventContact;
					eventContact=isnull(eventContact);
					tr="<tr>"
						+ "<td style='display:none;'>"+id+"</td>"
						+ "<td>" + name +"</td>"
						+ "<td>" + extend2 +"</td>"
						+ "<td>" + extend1 +"</td>"
						+ "<td>" + extend3 +"</td>"
						+ "<td>" + eventContact +"</td>"
						+ "<td>" + eventStatus +"</td>"
						+ "<td>" + eventDesc +"</td>"
						+ "<td align='center' class='nostr'>";
						   if(eventStatus == "待受理"){
							tr += "<a class='btn btn-primary  btn_new_small btn_blue' id='seeApply' onClick='seeApply(\""+id+"\")'>查看</a>"+
             				"<a class='btn btn-primary  btn_new_small btn_yellow' id='modApply' onClick='modApply(\""+id+"\",\""+eventStatus+"\")'>修改</a>"+
            				"<a class='btn btn-primary  btn_new_small btn_red' onClick='deleteEventInfo(\""+id+"\",\""+eventStatus+"\")'>撤销</a>" +
            				"</td>";
						   }else if(eventStatus == "待处理"){
							   tr += "<a class='btn btn-primary  btn_new_small btn_blue' id='seeApply' onClick='seeApply(\""+id+"\")'>查看</a>"+
							   "</td>";
						   }else if(eventStatus == "已完成"){
							   tr += "<a class='btn btn-primary btn_new_small btn_blue' id='seeApply' onClick='seeApply(\""+id+"\")'>查看</a>"+
							   "</td>";
						   }else if(eventStatus == "待评价"){
							   tr += "<a class='btn btn-primary btn_new_small btn_blue' id='seeApply' onClick='seeApply(\""+id+"\")'>查看</a>"+
							   "<a class='btn btn-primary  btn_new_small btn_purple' onClick='appraise("+id+")'>评价</a>" +
							   "</td>";
						   }else if(eventStatus == "已撤销"){
							   tr += "<a class='btn btn-primary  btn_new_small btn_blue' id='seeApply' onClick='seeApply(\""+id+"\")'>查看</a>"+
							   "</td>";
						   }
						    	
								
					$("#tbody").append(tr);
				}
				var relTable=$('.relTable');
	
				var RH=$('.mRight').height()-217;
	
		        var relTab=$('.relTable .table');
		        var thTab=$('.fixTable .table tr th');
		        var tdTab=$('.relTable .table tr:eq(0) td');
		        var len=thTab.length;
		       
		        relTable.height(RH);
		        var relTabHeight=relTab.height();
		       
		        for(var thI=0;thI<(len-1);thI++){
	                  tdTab.eq(thI+1).width(thTab.eq(thI).width()) ;
	  
	              }
		        if(RH<relTabHeight){
		        	  for(var thI=0;thI<(len-1);thI++){
		                  tdTab.eq(thI+1).width(thTab.eq(thI).width()) ;
		  
		              }
		        	  

		            relTable.addClass('relAuto')
		        }else{
		            relTable.removeClass('relAuto')
		        }
				$("#pages").val("");
	  },
	  params : function(){
		  var pages = $("#pages").val();
		  var pagenumber = $("#pagenumber").val();
		  var pagesize = $("#pagesize").val();
		  var totalpage = $("#totalpage").val();
		  var totalrow = $("#totalrow").val();
		  var startTime = $("#startTime").val();
			var endTime = $("#endTime").val();
			var idDept = $("#idDept").val();
			var eventDept = $("#eventDept").val();
			if(eventDept == ""){
				idDept = "";
			}
			var tel = $("#tel").val();
			var eventStatus = $("#eventStatus").val();
	        return {
	        	"pages":pages,
	        	"pagenumber":pagenumber,
	        	"pagesize":pagesize,
	        	"totalpage":totalpage,
	        	"totalrow":totalrow,
	        	"startTime":startTime,
	        	"endTime":endTime,
	        	"idDept":idDept,
	        	"tel":tel,
	        	"eventStatus":eventStatus
	        };
	    }
})
}
function appraise(id){
	$("#idEvent").val(id);
	$('.eve_area').val('');
	$('.eve_area').text('');
	 layer.open({
         type: 1,
         title: '评价',
         shadeClose: false,
         area: ['463px', '295px'],
         content:$('.evaluate')
     });
	 $(".layui-layer-page").show();
	
}
function closeAppraise(){
	$(".layui-layer-page").hide();
	$(".layui-layer-shade").hide();
}
function deleteEventInfo(id,eventStatus){
	if(!confirm("取消后，事件被主动关闭，受理人无需再处理")){
		return;
	}
	var url=getPath()+"/deleteEventInfo.do";
	$.ajax({
        url :url,
        data : {"id" : id},
        dataType : 'json',
        type : "post",
        traditional: true,//属性在这里设置
        success : function(result) {
           $('#tbody').empty();
           queryApplyList();
        },error:function(err){
        	alert($.parseJSON(err.responseText).msg);
        }
    });
}
//文本域最大输入字符
function checkLength(obj,maxlength){
    if(obj.value.length > maxlength){
        obj.value = obj.value.substring(0,maxlength);
        alert("最多只允许输入1000个字符");
    }
}
//科室内容改变触发
//function deptChange(){
//	var id = $("#idDept").val();
//	document.getElementById("selectUser").options.length=0;
//	document.getElementById("callApply").value=""
//	$.ajax({
//		type:'post',
//		url:getPath()+"/selectDeptUser.do",
//		data:{"id":id},
//		dataType:"json",
//		success:function(result){
//			if(result.selectUser != null){
//				var thirdSelect = document.getElementById("selectUser");
//				for(var i=0;i<result.selectUser.length;i++){
//					thirdSelect.add(new Option(result.selectUser[i].name,result.selectUser[i].id));;
//				}
//				if ($('#selectUser').fireEvent)
//				{
//				$('#selectUser').fireEvent('onchange');
//				}
//				else
//				{
//				$('#selectUser').change();
//				}
//			}
//			$("#callApply").val(result.tel);
//		}
//	})
//}

function modApply(id,eventStatus){
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	//修改申告
		hump_url = getPath() + "/updateEventInfo.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
		layer.open({
			type:2,
			title:'修改信息',
			shadeClose:false,
			area:['652px','400px'],
			content:hump_url
		})
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/eventInfoList.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
function updateEvent(){
	$("#upConfirm").click(function(){
	
	if($(".error_null").val()==""||$("#eventDesc").val().length=='0'|| $(".error_null").hasClass("error")){
		
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
			type:"post",
			url:getPath()+"/updateEvent.do",
			data:$("#form1").serialize(),
			traditional: true,
			success:function(result){
				
				if(result==1){
					var pages = 1;
					var pageNumber = $("#pagenumber").val();
					var pageSize = $("#pagesize").val();
					var totalPage = $("#totalpage").val();
					var totalRow = $("#totalrow").val();
					
				  	window.parent.sunTion();
					var index = parent.layer.getFrameIndex(window.name); 
					parent.layer.close(index);
				
				}
			},error:function(err){
				alert($.parseJSON(err.responseText).msg);
			}
		})
	}
	})
}
function userChange() {
	$("#selInp").val("请选择申告人");
	$("#eventUser option").click(function(){
		$("#eventUser ").hide();
		
		$("#selInp").val($(this).text()).removeClass("error");
	})
	var userId = $("#eventUser").val();
	$.ajax({
		type : 'post',
		url : getPath() + "/userById.do",
		data : {
			"userId" : userId
		},
		dataType : "json",
		success : function(result) {
			if (result.cal != "" && result.cal != null) {
				$("#callApply").val(result.cal); 
			}else{
				$("#callApply").val(deptCall); 
			}
		}
	})
}

function userblur(){
	document.getElementById("eventDept").options.length = 0;
	var userName = $("#selInp").val();
	$.ajax({
		type:"post",
		url:getPath()+"/selectDeptByUserName.do",
		data:{"userName":userName},
		success:function(data){
			var thirdSelect = document.getElementById("eventDept");
			for(var i = 0;i<data.length;i++){
				thirdSelect.add(new Option(data[i].deptName,
						data[i].deptId));
			}
		}
	})
	$("#eventDept").show();
}
//查看
function seeApply(id){
//	location.href=getPath() +"/dateCreate.do"
	$("#seeApplyId").val(id);
	$("#name").empty();
	$("#eventContact").empty();
	$("#deptName").empty();
	$("#tel").empty();
	$("#eventName").empty();
	$("#eventDesc").empty();
	$.ajax({
		type:"post",
		url:getPath()+"/examine.do",
		data:{"id":id},
		success:function(result){
			var event=result.selectEventInfoAll;
			var user = result.userAccept;
			var dept = result.deptName;
			var dUserAll = result.dUserAll;
			
			var logStatus = result.logStatus;
			
			var applyName = "";
			var applyDate = "";
			var acceptName = "";
			var acceptDate = "";
			var dealName = "";
			var dealDate = "";
			var sureName = "";
			var sureDate = "";
			var assesName = "";
			var assesDate = "";
			var anewAssesName = "";
			var anewAssesDate = "";
			
			document.getElementById('name').innerHTML= user.name;
			document.getElementById('eventContact').innerHTML= event.eventContact;
			document.getElementById('deptName').innerHTML= dept.name;
			//document.getElementById('deptTel').innerHTML = dept.tel;
			document.getElementById('eventName').innerHTML= event.eventName;
			document.getElementById('eventDesc').innerHTML= event.eventDesc;
			$('#eventProcedure').empty();
			for (var i = 0; i < logStatus.length; i++) {
				var log=logStatus[i];
				if (log.eventStatus == "20") {
					applyName = log.extend1;
					applyDate = log.dateOper;
					document.getElementById('eventProcedure').innerHTML+= "<p>"+getFormatDate(new Date(applyDate), "yyyy-MM-dd hh:mm:ss")+" "+applyName+"发起申告,等待服务台受理"+"</p>"; 
				}else if(log.eventStatus == '50'){
					acceptName = log.extend1;
					acceptDate = log.dateOper;
					document.getElementById('eventProcedure').innerHTML += "<p>"+getFormatDate(new Date(acceptDate), "yyyy-MM-dd hh:mm:ss")+" "+acceptName+"受理完成,由"+dUserAll+"进行处理</p>";
				}else if(log.eventStatus == '70'){
					sureName = log.extend1;
					sureDate = log.dateOper;
					document.getElementById('eventProcedure').innerHTML +=	"<p>"+getFormatDate(new Date(sureDate), "yyyy-MM-dd hh:mm:ss")+" 处理完成,等待评价"+"</p>";
				}else if(log.eventStatus == '77'){
					document.getElementById('eventProcedure').innerHTML +=	"<p>"+getFormatDate(new Date(anewAssesDate), "yyyy-MM-dd hh:mm:ss")+" 事件完成已关闭"+"</p>";  
				}
			}	
		},error:function(err){
			alert($.parseJSON(err.responseText).msg);
		}
	})
	 layer.open({
         type: 1,
         title: '查看信息',
         shadeClose: false,
         area: ['463px', '400px'],
         content:$('.look_box'),
     
         })
      
     var adk=$('.look_box').height()+20;
}
function repetition(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	var id = $("#seeApplyId").val();
	location.href = getPath() + "/serviceRepe.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
}
//function deptBlur(){	
//	$("#tree").hide();
//}
function add0(m){return m<10?'0'+m:m }
function dateFmt(creatTime){
	var time = new Date(creatTime);
	var y = time.getFullYear();
	var m = time.getMonth()+1;
	var d = time.getDate();
	var h = time.getHours();
	var mm = time.getMinutes();
	var s = time.getSeconds();
	return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);

	return dateTimeStr;

	}
