//时间点的改变
function changeDate(select){
	if(select.value == "0"){
		//移除时间段
	}else if(select.value == "1"){
		//动态添加两个时间段
	}
}

//项目展示
function showProjControlList(){
	$('#paging').empty();
	$('#paging').bPage({
		url : getPath() + "/getProjControlList.do",
		// 开启异步处理模式
		asyncLoad : true,
		// 关闭服务端页面模式
		serverSidePage : false,
		// 数据自定义填充
		render : function(data) {
			var projControlBody = $("#projControlBody").empty();
			var projControlList = data.projControlList;
			//切换页数时候 将页数放入list隐藏域中
			var pageNumber = data.pageNumber;
			var pageSize = data.pageSize;
			var totalPage = data.totalPage;
			var totalRow = data.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			$("#bPageDropList").val(pageSize);
			$.each(projControlList,function(k, projControl) {
				var projCode = projControl.projCode;//项目Code
				var projName = projControl.projName;//项目名称
				var projManagerName = projControl.extend1;//项目负责人
				var dateStart = projControl.dateStart;//项目开始时间
				var dateEnd = projControl.dateEnd;//项目结束时间
				var projSpeed = projControl.projSpeed;//任务进度
				projSpeed=Number(projSpeed*100).toFixed(0);
				projSpeed+="%";
				
				
				var wranY="";
				if(projControl.pf  == 1){
					wranY="yShowWarn"//alert("超期啦！！");//
	            }else{
	            	wranY="yHideWarn"
	            }
				
				var projTd = "<tr><td style='text-align:left;position:relative;'>"
					+ "<a title='"+projName+"' href='javascript:void(0);' onclick = showTaskControl('"+projCode+"') class='btnHover' >"+projName+"</a><b class='iconClass'><i title='项目延期' class='"+wranY+" fa fa-warning'></i></b></td>"+
							"<td>" +projManagerName+ "</td>" +
							"<td>" +getFormatDate(new Date(dateStart),"yyyy-MM-dd")+ "</td>" +
							"<td>" +getFormatDate(new Date(dateEnd),"yyyy-MM-dd")+ "</td>" + 
							"<td class='schedule'>" +"<span>"+projSpeed+"</span><p><b style='width:"+projSpeed+"';></td></tr>";
				projControlBody.append(projTd);
			})
			var relTable=$('.relTable');
			var RH=$('body').height()-170;		     
			var relTable=$('.relTable');
			var relTab=$('.relTable .table');
			var thTab=$('.fixTable .table tr th');
			var tdTab=$('.relTable .table tr:eq(0) td');
			var len=thTab.length;			
			var relTabHeight=relTab.height();
			for(var thI=0;thI<(len);thI++){
				tdTab.eq(thI).width(thTab.eq(thI).width()) ;
			}
			if(RH<relTabHeight){
				for(var thI=0;thI<(len-1);thI++){
					tdTab.eq(thI).width(thTab.eq(thI).width()) ;	
				}		
			}
		},
		params : function() {
			
			var projName = $("#projName").val();
			var idManager = $("#idManager").val();
			var startTime = $("#startTime").val();
			//将参数传入后台
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			var pages = $("#pages").val();
			return {
				"projName":projName,
				"idManager":idManager,
				"startTime":startTime,
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
				"pages":pages,
			};
		}
	});
	$("#pages").val("");
}

//任务展示
function showProjTaskControlList(){

	$('#paging').empty();
	$('#paging').bPage({
		url : getPath() + "/getProjTaskControlList.do",
		// 开启异步处理模式
		asyncLoad : true,
		// 关闭服务端页面模式
		serverSidePage : false,
		// 数据自定义填充
		render : function(data) {
			var projControlBody = $("#projControlBody").empty();
			var projControlList = data.projTaskControlList;
			$.each(projControlList,function(k, projControl) {
				var projCode = projControl.extend1;//项目Code
				var projName = projControl.extend2;//项目名称
				var taskId = projControl.id;//任务id
				var taskName = projControl.nameTask;//任务名称
				var idHeadUser = projControl.extend3;//任务负责人
				var dateStart = projControl.dateStart;//任务开始时间
				var dateEnd = projControl.dateEnd;//任务结束时间
				var taskSpeed = projControl.taskSpeed;//任务进度
				taskSpeed=Number(taskSpeed*100).toFixed(0);
				taskSpeed+="%";
				//
				var warning="";
				var wranY="";
				
					
				if(projControl.taskPf == 1){
					warning="rShowWarn"//alert("超期啦！！");//
	            }else{
	            	warning="rHideWarn"
	            }
				if(projControl.projPf  == 1){
					wranY="yShowWarn"//alert("超期啦！！");//
	            }else{
	            	wranY="yHideWarn"
	            }
				
				var projTd = "<tr><td  style='text-align:left; position:relative;'>" +"<a title='"+taskName+"'  href='javascript:void(0);' onclick = controlTask('"+taskId+"') class='btnHover' >"+taskName+"</a><b class='iconClass'><i title='任务超期' class='"+warning+" fa fa-warning'></i></b></td>"
					+ "</td><td style='text-align:left;position:relative;'>"
					+ "<a title='"+projName+"' href='javascript:void(0);' onclick = controlProj('"+projCode+"') class='btnHover' >"+projName+"</a><b class='iconClass'><i title='项目延期' class='"+wranY+" fa fa-warning'></i></b></td>"+
							"<td>" +idHeadUser+ "</td>" +
							"<td>" +getFormatDate(new Date(dateStart),"yyyy-MM-dd")+ "</td>" +
							"<td>" +getFormatDate(new Date(dateEnd),"yyyy-MM-dd")+ "</td>" + 
							"<td class='schedule'>" +"<span>"+taskSpeed+"</span><p><b style='width:"+taskSpeed+"';></td></tr>";
				projControlBody.append(projTd);
			})
			var relTable=$('.relTable');			
			var RH=$('body').height()-210;		     
			var relTable=$('.relTable');
			var relTab=$('.relTable .table');
			var thTab=$('.fixTable .table tr th');
			var tdTab=$('.relTable .table tr:eq(0) td');
			var len=thTab.length;
			var relTabHeight=relTab.height();
			for(var thI=0;thI<(len);thI++){
				tdTab.eq(thI).width(thTab.eq(thI).width()) ;
			}

			if(RH<relTabHeight){
				for(var thI=0;thI<(len-1);thI++){
					tdTab.eq(thI).width(thTab.eq(thI).width()) ;	
				}		
			}
				
		},
		params : function() {
			var taskName = $("#taskName").val();
			var idTaskHead = $("#idTaskHead").val();
			var startTime = $("#startTime").val();
			var projCode = $("#projCode").val();
			
			return {
				"taskName":taskName,
				"idTaskHead":idTaskHead,
				"startTime":startTime,
				"projCode":projCode,
			};
		}
	});
}
//点击项目名称，跳转任务列表
function showTaskControl(projCode){
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath()+"/toProjTaskControl.do?projCode="+projCode+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow;
}


//项目展示
function controlProj(projCode){
	var jump_url  = getPath() + "/toUpdateProj.do?projCode="+projCode+"&state=projControl";
	layer.open({
	    type: 2,
	    title: '监控-项目查看',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['900px', '570px'],
	    content: [jump_url,'yes']

	  })
}
//任务查看
function controlTask(taskId){
	var jump_url = getPath()+"/toProjTaskDeal.do?taskId="+taskId+"&state="+"projControlTask";
	layer.open({
	    type: 2,
	    title: '监控-任务查看',
	    scrollbar: true,
	    shadeClose: false,
	    area: ['670px', '400px'],
	    content: [jump_url,'yes']

	  })
}
//导出
function exportCotrol(){
	
	var tLength = $("#projControlBody").find("tr").length ;
	if(tLength == 0){
		alert("没有可导出的数据");
		return;
	}
	var projName = $("#projName").val();
	var idManager = $("#idManager").val();
	var startTime = $("#startTime").val();
	location.href = getPath()+"/projControlExprot.do?projName="+projName+"&idManager="+idManager
					+"&startTime="+startTime;
}
function exportTaskCotrol(){
	
	var tLength = $("#projControlBody").find("tr").length ;
	if(tLength == 0){
		alert("没有可导出的数据");
		return;
	}
	var taskName = $("#taskName").val();
	var idTaskHead = $("#idTaskHead").val();
	var startTime = $("#startTime").val();
	var projCode = $("#projCode").val();
	location.href = getPath()+"/projTaskControlExprot.do?taskName="+taskName
					+"&idTaskHead="+idTaskHead+"&startTime="+startTime+"&projCode="+projCode;
}

function acancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/toProjControl.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}