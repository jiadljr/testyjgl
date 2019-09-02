$(function(){

	var path=getPath();
	//查看
	$("#checkRole").click(function(){
		var ids=$(" tbody :checked");
		if (ids.parents("tr").length==1) {
			var id=$(" tbody :checked").next().val();
			$.ajax({
			url:getPath()+"/checkRole.do",
			data:{"id":id},
			dataType:"json",
			type:"post",
			success:function(result){
				var right=$(".check-content-right");
				var role=result;
				var code=role.code;
				code=isnull(code);
				var name=role.name;
				name=isnull(name);
				var remark=role.remark;
				remark=isnull(remark);
				var array=[code,name,remark];
				for (var i = 0; i < right.length; i++) {
					right.eq(i).text(array[i]);
				}
				 layer.open({
			         type: 1,
			         title: '查看信息',
			         shadeClose: false,
			         area: ['463px', '230px'],
			         content:$(".check-win").html()
			     
			       })
			}
		})
		}else{
			alert("请勾选需要查看的单条数据！！！");
			return;
		}
	
		//$(".check-win").show();
		//$(".mask").show();
	})
	//搜索
	$("#button_search").click(function(){
		showRole();
	})
	//去添加
	$("#addRole").click(function(){
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		window.location.href=path+"/toAddRole.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages
	})
	//添加
	/**
	 *多行非空验证 
	 * 逐个元素添加 class名 err_null
	 * 判断是否存在err_null
	 **/
		
	 $.each($(".error_null"),function(){
         if($(this).val()==""){
             $(this).addClass("err_null");
         }
     })
   
	$("#addConfirm").click(function(){
		$.each($(".error_null"),function(){
	         if($(this).val()!==""){
	             $(this).removeClass("err_null");
	         }
	     })
		if($(".error_null").val()=="" || $(".error_null").hasClass("error") || $(".error_null").hasClass("err_null")){
			$.each($("input"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;
		}else{
			var path=getPath();
			$.post(path+"/insertRole.do",$("#form1").serialize(),function(result){
				
				window.location.href=path+"/toRoleConfig.do?cid=1";
			})
		}
	})
	//去修改
	$("#updRole").click(function(){
		var ids=$(" tbody :checked").parents("tr");
		if (ids.length<1) {
			alert("请勾选需要修改的数据！！！");
			return;
		}
		if (ids.length > 1) {
			alert("请选择单条数据进行修改！！！");
			return;
		}
		var id=$(" tbody :checked").next().val();
		if (id=="") {
			return;
		}
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		window.location.href=path+"/toUpdRole.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
	})
	//修改
	$("#modConfirm").click(function(){
		if($("input").val()=="" || $("input").hasClass("error")){
			$.each($("input"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;
		}else{
			var path=getPath();
			$.post(path+"/updRole.do",$("#form1").serialize(),function(result){
			
				window.location.href=path+"/toRoleConfig.do?cid=2";
			})
		}
	})
	//删除
	$("#delRole").click(function(){
		var ids=$(" tbody :checked");
		if (ids.parents("tr").length<1) {
			alert("请勾选需要删除的数据！！！");
			return;
		}
		if(!confirm("确定删除该角色吗？")){
			return;
		}
		var _list=[];
		for (var i = 0; i < ids.length; i++) {
			_list[i]=ids.eq(i).next().val();
		}
		$.ajax({
            url : path+'/deleteByRoleKey.do',
            data : {"ids" : _list},
            dataType : 'json',
            type : "post",
            traditional: true,//属性在这里设置
            success : function(result) { 
            	var mass = result.data.mass;
            	if (mass == "success") {
            		showRole();
				}
            },
        });
	})
})

//插入数据
function showRole(){
	$('#page2').empty();
	$('#page2').bPage({
		url : getPath()+"/selectRole.do",
		//开启异步处理模式
		asyncLoad : true,
		//关闭服务端页面模式
		serverSidePage : false,
		//数据自定义填充
		render : function(result){
				var pageNumber = result.pageNumber;
				var pageSize = result.pageSize;
				var totalPage = result.totalPage;
				var totalRow = result.totalRow;
				$("#pagenumber").val(pageNumber);
				$("#pagesize").val(pageSize);
				$("#totalpage").val(totalPage);
				$("#totalrow").val(totalRow);
				var roles=result.roleAll;
				var tbody=$("#tbody").empty();
				for (var i = 0; i < roles.length; i++) {
					var role=roles[i];
					var id=role.id;
					id=isnull(id);
					var code=role.code;
					code=isnull(code);
					var name=role.name;
					name=isnull(name);
					var remark=role.remark;
					remark=isnull(remark);
					var tr="<tr>"+
						"<td><input type='checkbox' name='item1'><input type='hidden' name='id' value="+id+"></td>"+
						"<td>"+code+"</td>"+
						"<td>"+name+"</td>"+
						"<td>"+remark+"</td>"+
						"</tr>";
					tbody.append(tr);
				}
			},
		params : function(){
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			var roleCode=$("#roleCode").val();
			var roleName=$("#roleName").val();
			return {
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
				roleCode : roleCode,
				roleName : roleName,
			};
		}
	});
	$("#pages").val("");
}

function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/toRoleConfig.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}