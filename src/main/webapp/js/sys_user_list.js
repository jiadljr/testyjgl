var zNodes;
var setting;
$(function(){
		$("#body").click(function(){
		    $("#tree").toggle();
		});
	var path = getPath();
	//去添加界面
	$("#addUser").click(function(){
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		window.location.href=path+"/toAddUser.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
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
		if($(".error_null").val()=="" || $(".error_null").hasClass("error")|| $(".error_null").hasClass("err_null")){
			$.each($(".error_null"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;
		}else{
			var path=getPath();
			$.ajax({
				url:path+"/addUser.do",
				data:$("#form1").serialize(),
				dataType:"json",
				type:"post",
				async : false,
				success: function(){
					window.location.href=path+"/sysUser.do?cid=1";
				},
				error: function(result){
					alert($.parseJSON(result.responseText).msg);
				}
			})
		}
	})
	//去修改界面
	$("#updUser").click(function(){
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
		var pages = 1;
		var pageNumber = $("#pagenumber").val();
		var pageSize = $("#pagesize").val();
		var totalPage = $("#totalpage").val();
		var totalRow = $("#totalrow").val();
		window.location.href=path+"/toUpdUser.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
	})
	//修改
	$("#modConfirm").click(function(){
		$.each($(".error_null"),function(){
	         if($(this).val()!==""){
	             $(this).removeClass("err_null");
	         }
	     })
		if($(".error_null").val()=="" || $(".error_null").hasClass("error")|| $(".error_null").hasClass("err_null")){
			$.each($(".error_null"),function(){
				if($(this).val()==""){
					var error=$(this).attr("data-message");
					$(this).val(error);
					$(this).addClass("error");
				}
			})
			return false;
		}else{
			$.ajax({
				url:getPath()+"/updUser.do",
				data:$("#form1").serialize(),
				async : false,
				type:"post",
				success:function(result){
						window.location.href=path+"/sysUser.do?cid=2";
				},error:function(err){
					alert($.parseJSON(err.responseText).msg);
				}
			})
	}
})
	//查看
	$("#checkUser").click(function(){
		var ids=$(" tbody :checked");
		if (ids.parents("tr").length==1) {
			var id=$(" tbody :checked").next().val();
			$.ajax({
				url:getPath()+"/checkUser.do",
				data:{"id":id},
				dataType:"json",
				type:"post",
				success:function(result){
					var right=$(".check-content-right").length;
					var user=result.sysUserOne;
					var code=user.code;
				 	code=isnull(code);
					var name = user.name;
					name=isnull(name);
					var sex = user.sex;
					sex=isnull(sex);
					if(sex==1){
						sex="男";
					}else if(sex==2){
						sex="女";
					}
					var uuid = user.uuid;
					uuid=isnull(uuid);
					var wechat = user.wechat;
					wechat=isnull(wechat);
					var extend2 = user.extend2;
					extend2=isnull(extend2);
					var extend1 = user.extend1;
					extend1=isnull(extend1);
					var tel = user.tel;
					tel=isnull(tel);
					var cal = user.cal;
					cal=isnull(cal);
					var mail = user.mail;
					mail=isnull(mail);
					var address = user.address;
					address=isnull(address);
					var extend3 = user.extend3;
					extend3=isnull(extend3);
					var arr=[code,name,sex,uuid,extend2,extend1,extend3,tel,cal,wechat,mail,address];
					for(i=0;i<right;i++){						
						$(".check-content-right").eq(i).text(arr[i]);
					}
					 layer.open({
				         type: 1,
				         title: '查看信息',
				         shadeClose: false,
				         area: ['463px', '410px'],
				         content:$(".check-win").html()				     
			       })
				},error:function(err){
					alert($.parseJSON(err.responseText).msg);
				}
			})
		}else{
			alert("请勾选需要查看的单条数据！！！");
			return;
		}
		
	})	
	$("#button_search").click(function(){
		showUser();
	})
	//删除
	$("#delUser").click(function(){
		var ids=$(" tbody :checked");
		if (ids.parents("tr").length<1) {
			alert("请勾选需要删除的数据！！！");
			return;
		}
		if(!confirm("确定删除该人员吗？")){
			return;
		}
		var _list = [];  
		for (var i = 0; i < ids.length; i++) {  
		    _list[i] = ids.eq(i).next().val();  
		}
		$.ajax({
            url : path+'/deleteUser.do',
            data : {"ids" : _list},
            dataType : 'json',
            type : "post",
            traditional: true,//属性在这里设置
            success : function(result) {   
            		showUser();
            },error : function(result){
            	alert($.parseJSON(err.responseText).msg);
            }
        });
	
		
	})
	/*页面的切换*/
	$("#top .collapse .nav li").eq(0).removeClass("active");
	$("#top .collapse .nav li").eq(3).addClass("active");
	$("#main .mLeft .list-group li").eq(0).removeClass("active");
	$("#main .mLeft .list-group li").eq(3).addClass("active");
	/*
	 * 授权功能
	 */
	//角色:回显
	$("#authorUser").click(function(){
		$("#button_search_author").removeAttr("disabled");
		var ids=$(" tbody :checked").parents("tr");
		if (ids.length<1) {
			alert("请勾选需要授权的菜单！！！");
			return;
		}
		if (ids.length > 1) {
			alert("请选择单个菜单进行授权！！！");
			return;
		}
		var id=$(" tbody :checked").next().val();
		if (id=="") {
			return;
		}
		$("#sys_role").children("option").prop("selected",false);
	    $("#sys_role").selectpicker('refresh');
		var userName=$(" tbody :checked").parent().next().next().text();
		$("#user_name").val(userName);
		$("#user_id").val(id);
		$.post(path+"/selectByUser.do",{"userId":id},function(result){
			var str=result.data;
			str = str.substr(0,str.length-1);
			if (str!=null && str!=undefined) {
				var data=str.split(",");
				for (var i = 0; i < data.length; i++) {
					$("#sys_role").children("option[value=" + data[i] + "]").prop("selected",true);
				}
				$('#sys_role').selectpicker('refresh');
			    $('#sys_role').selectpicker('render');
			}
		})
	$(".author_jump").fadeIn();
		$(".mask").fadeIn();
	})
	// 授权:确定
	$("#button_search_author").click(function(){
		$("#button_search_author").attr("disabled","disabled");
		var userId=$("#user_id").val();
		var roleStr=$("#sys_role").val();
		if (roleStr==null || roleStr=="") {
			alert("请选择角色！");
			return false;
		}
		var roleStrs=String(roleStr);
		//alert(typeof(roleStr));
		$('#sys_role').selectpicker('refresh');
		$('#sys_role').selectpicker('render');
		$.post(path+"/updateRoleUser.do",{"userId":userId,"roleStr":roleStrs},function(result){
			showUser();
			$(".author_jump").fadeOut();
			$(".mask").fadeOut();
		})
	})
	//授权:取消
	$("#button_cancel_author").click(function(){
		$(".author_jump").fadeOut();
		$(".mask").fadeOut();
	})
})
//关键字搜索部门树
function deptZtrees() {
    $.fn.zTree.init($("#tree"), setting, zNodes);
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
//角色展示
function roleManu(roles){
	var str="";
	var sys_role=$("#sys_role");
	for (var i = 0; i < roles.length; i++) {
		var role=roles[i];
		str+="<option value=" +role.id+">"+role.name+"</option>";
		
	}
	sys_role.append(str);
	$('#sys_role').selectpicker('refresh');
    $('#sys_role').selectpicker('render');
}
//替换tbody内容
function showUser(){
	$('#page2').empty();
	$('#page2').bPage({
		url : getPath()+"/selectUserAll.do",
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
				var roleAll=result.roleAll;
				if (document.getElementById("sys_role").options.length == 0) {
					roleManu(roleAll);
				}
				//展示上级部门
				var deptAll = result.deptAll;
				//人员信息展示
				var users = result.userAll;
				var tbody = $('#tbody').empty();
				for(var i=0;i<users.length;i++){
					var user=users[i];
					 var ds=user.ds;
					 ds=isnull(ds);
					 if(ds != 1){
					 	var id=user.id;
					 	var code=user.code;
					 	code=isnull(code);
						var name = user.name;
						name=isnull(name);
						var sex = user.sex;
						sex=isnull(sex);
						if(sex==1){
							sex="男";
						}else if(sex==2){
							sex="女";
						}
						var uuid = user.uuid;
						uuid=isnull(uuid);
						var wechat = user.wechat;
						wechat=isnull(wechat);
						var extend2 = user.extend2;
						extend2=isnull(extend2);
						var extend1 = user.extend1;
						extend1=isnull(extend1);
						var tel = user.tel;
						tel=isnull(tel);
						var cal = user.cal;
						cal=isnull(cal);
						var mail = user.mail;
						mail=isnull(mail);
						var address = user.address;
						address=isnull(address);
						var extend3 = user.extend3;
						extend3=isnull(extend3);
						var tr="<tr>"+
							"<td><input type='checkbox' name='item1'><input type='hidden' name='id' value="+id+"></td>"+
							"<td>" + code +"</td>"+
							"<td>" + name +"</td>"+
							"<td>" + sex +"</td>"+
							"<td>" + uuid +"</td>"+
							"<td>" + extend2 +"</td>"+
							"<td>" + extend3 +"</td>"+
							"<td>" + tel +"</td>"+
							"<td>" + cal +"</td>"+
							"<td>" + mail +"</td>"+
							"</tr>";
						tbody.append(tr);
					 }
				}
			},
		params : function(){
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
			var totalpage = $("#totalpage").val();
			var totalrow = $("#totalrow").val();
			var userName=$("#userName").val();
			var userDept=$("#userDept").val();
			if($("#eventDept").val() == ""){
				userDept = "";
			}
			var cal=$("#cal").val();
			var perNum=$("#perNum").val();
			return {
				"pages":pages,
				"pagenumber":pagenumber,
				"pagesize":pagesize,
				"totalpage":totalpage,
				"totalrow":totalrow,
				userName : userName,
				userDept : userDept,
				cal : cal,
				perNum : perNum,
			};
		}
	});
	$("#pages").val("");
}


//通过部门动态改变电话信息
function changeCal(){
	var deptId=$("#userDept").val();
	$.ajax({
		type:"post",
		data:{deptId:deptId},
		dataType:'json',
		url:getPath()+"/selectDeptTel.do",
		success:function(data){
			var dTel=data.tel;
			$("#telUser").val(dTel);
			$("#userTel").val(dTel);
		},
		error:function(){
		}
		
	})
}

function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/sysUser.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}