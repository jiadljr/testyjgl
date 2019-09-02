function queryList(){
	$('#page2').empty();
	$('#page2').bPage({
		url :getPath()+"/selectDealNot.do",
		//开启异步处理模式
		asyncLoad : true,
		//关闭服务端页面模式
		serverSidePage : false,
		//数据自定义填充
		render : function(result){
				var table = $('#myTable');
				table.empty();
				var dealNot = result.selectDealNot;
				for (var i = 0; i < dealNot.length; i++) {
					var id=dealNot[i].id;
					var code=dealNot[i].eventCode;
					code=isnull(code);
					var name=dealNot[i].eventName;
					name=isnull(name);
					var depName=dealNot[i].extend1;
					depName=isnull(depName);
					var userName=dealNot[i].extend2;
					userName=isnull(userName);
					var eventContact=dealNot[i].eventContact;
					eventContact=isnull(eventContact);
					var dateCreate=dealNot[i].dateCreate;
					dateCreate=isnull(dateCreate);
					var eventPriority=dealNot[i].eventPriority;
					eventPriority=isnull(eventPriority);
					if(eventPriority == 1){
						eventPriority = "高级";
					}else if(eventPriority == 2){
						eventPriority = "中级";
					}else if(eventPriority == 3){
						eventPriority = "低级";
					}
					var eventLevel=dealNot[i].eventPriority;
					eventLevel=isnull(eventLevel);
					if(eventLevel == 1){
						eventLevel = "一级";
					}else if(eventLevel == 2){
						eventLevel = "二级";
					}else if(eventLevel == 3){
						eventLevel = "三级";
					}else if(eventLevel == 4){
						eventLevel = "四级";
					}
					table.append     
					 ('<tr>'+
							 '<td style="display:none;">'+ id +'</td>'+
						      '<td>'+ code +'</td>'+
						      '<td>'+ name +'</td>'+
						      '<td>'+ depName +'</td>'+
						      '<td>'+ userName +'</td>'+
						      '<td>'+ eventContact +'</td>'+
						      '<td>'+ dateCreate +'</td>'+
						      '<td>'+ eventPriority +'</td>' +
						      '<td>'+ eventLevel +'</td>'+
						      '<td>'+
						       '<a class="btn btn-primary btn-sm" onClick="receiving()">接单</a>'+
						      '</td>'+
						      '</tr>');
		     }
			},
		params : function(){
			var asManuDept = $("#asManuDept").val();
			var asModel = $("#asModel").val();
			var name = $("#name").val();
			var code = $("#code").val()
			return {
				asManuDept : asManuDept,
				asModel : asModel,
				name : name,
				code : code,
			};
		}
	});
}
function receiving(){
	window.location.href=getPath()+"/updateDealNot.do";
}