$(function(){
	
	/*页面的切换*/
	$("#top .collapse .nav li").eq(0).removeClass("active");
	$("#top .collapse .nav li").eq(3).addClass("active");
	$("#main .mLeft .list-group li").eq(0).removeClass("active");
	$("#main .mLeft .list-group li").eq(1).addClass("active");
	/*页签的使用*/
	$("#seach").click(function(){
		$('#myTable').empty();
	    getPathList();
	})
	
	$("#seeMenu").click(function(){
		if($("input:checked").length>1){
			alert("请选择单行进行查看");
		}
		if($("input:checked").length<1){
			alert("请选择要查看的内容");
		}
		if($("input:checked").length==1){
			var id=$("input:checked").parent().next().text();			
			var url=getPath() + "/selectById.do";
			$.ajax({
				 type:"post",
				 url:url,
				 data:{"id":id},
				 success:function(result){
					 var right=$(".check-content-right");
						var sysCmp=result.sysCmp;
						var code=sysCmp.code;
						code=isnull(code);
						var name=sysCmp.name;
						name=isnull(name);
						var subName=sysCmp.subName;
						subName=isnull(subName);
						var tel=sysCmp.tel;
						tel=isnull(tel);
						var mail=sysCmp.mail;
						mail=isnull(mail);
						var remark=sysCmp.remark;
						remark=isnull(remark);
						var addr=sysCmp.addr;
						addr=isnull(addr);
						var array=[sysCmp.code,sysCmp.name,sysCmp.subName,sysCmp.tel,sysCmp.mail,sysCmp.remark,sysCmp.addr];
						for (var i = 0; i < right.length; i++) {
							right.eq(i).text(array[i]);	
						}
						 layer.open({
					         type: 1,
					         title: '查看信息',
					         shadeClose: false,
					         area: ['463px', '340px'],
					         content:$(".check-win").html()
					     
					       })
				 },error:function(err){
					 alert($.parseJSON(err.responseText).msg);
				 }
			});
			
		}
	})
	$(".openWin button").click(function(){
		$(".openWin").hide();
	})
})
function getPathList(){
	$("#pageTable").empty();
	$('#pageTable').bPage({
		url:getPath()+"/sysCmpy.do",
		asyncLoad : true,
	    serverSidePage : false,
	    render :function(result){
	    	var pageNumber = result.pageNumber;
			var pageSize = result.pageSize;
			var totalPage = result.totalPage;
			var totalRow = result.totalRow;
			$("#pagenumber").val(pageNumber);
			$("#pagesize").val(pageSize);
			$("#totalpage").val(totalPage);
			$("#totalrow").val(totalRow);
			var coun = result.coun;
			$("#totalRow").val(coun);
			var table = $('#myTable');
			table.empty();
			for (var i = 0; i < result.copList.length; i++) {
				var id = result.copList[i].id;
				var code=result.copList[i].code;
				code=isnull(code);
				var name=result.copList[i].name;
				name=isnull(name);
				var subName=result.copList[i].subName;
				subName=isnull(subName);
				var tel=result.copList[i].tel;
				tel=isnull(tel);
				var mail=result.copList[i].mail;
				mail=isnull(mail);
				var remark=result.copList[i].remark;
				remark=isnull(remark);
				var addr=result.copList[i].addr;
				addr=isnull(addr);
				table.append     
				 ('<tr><td><input id="check" type="checkbox" name="item1"></td>'+
						 '<td style="display:none;">'+ id +'</td>'+
					      '<td>'+ code +'</td>'+
					      '<td>'+ name +'</td>'+
					      '<td>'+ subName +'</td>'+
					      '<td>'+ tel +'</td>'+
					      '<td>'+ mail +'</td>'+
					      '<td>'+ addr +'</td>'+
					      '<td>'+ remark +'</td></tr>');
	     }
		},
		params : function(){
			var pages = $("#pages").val();
			var pagenumber = $("#pagenumber").val();
			var pagesize = $("#pagesize").val();
		  	var totalpage = $("#totalpage").val();
		  	var totalrow = $("#totalrow").val();
			var comNum = $("#comNum").val();
			var comName = $("#comName").val();
			var comAbbre = $("#comAbbre").val();
	        return {
	        	"pages":pages,
	        	"pagenumber":pagenumber,
	        	"pagesize":pagesize,
	        	"totalpage":totalpage,
	        	"totalrow":totalrow,
	        	"comNum":comNum,
	        	"comName":comName,
	        	"comAbbre":comAbbre
	        };
	    }

	})
	$("#pages").val("");
}
//删除
function deleteId(){
	if($("tbody input:checked").length==0){
		alert("请选择需要删除的数据！");
		return false;
	}
	if(!confirm("确定删除该公司信息吗？")){
		return;
	}
	var ids=$(" tbody input:checked");
	var _list = new Array();  
	for (var i = 0; i < ids.length; i++) {
	    _list[i] = ids.eq(i).parent().next().text();  
	}
	var url=getPath()+"/deleteCmpy.do";
	$.ajax({
        url :url,
        data : {"id" : _list},
        dataType : 'json',
        type : "post",
        traditional: true,//属性在这里设置
        success : function(result) {
           $('#myTable').empty();
           getPathList();
        },error:function(err){
        	alert($.parseJSON(err.responseText).msg);
        }
    });
}
//新增
function addCmp(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href =getPath()+"/insertPage.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
function save(){
	//多行输入框判断非空提交验证！！！！
	 $.each($(".error_null"),function(){
         if($(this).val()==""){
             $(this).addClass("err_null");
         }
     })
   
	 //end!!````
	
	$("#insertComp").click(function(){
		$.each($(".error_null"),function(){
	         if($(this).val()!==""){
	             $(this).removeClass("err_null");
	         }
	     })
		if($(".error_null").val()==""|| $(".error_null").hasClass("error")||$('.error_null').hasClass('err_null')){
		
			$.each($(".error_null"),function(){
					if($(this).val()==""){
						var error=$(this).attr("data-message");
						$(this).val(error);
						$(this).addClass("error");
					}
				})
			return false;
		}
		$.ajax({
	        url :getPath()+"/insertCmpy.do",
	        data : $("#form1").serialize(),
	        dataType : 'json',
	        type : "post",
	        traditional: true,//属性在这里设置
	        success : function(result) {
	           location.href =getPath()+"/selectCmpy.do?cid=1";
	        },error:function(err){
	        	alert($.parseJSON(err.responseText).msg);
	        }
	    });
	})
}
//修改
function updateCmp(){
	var ids=$(" tbody :checked").parents("tr");
	if (ids.length<1) {
		alert("请勾选需要修改的数据！！！");
		return;
	}
	if (ids.length > 1) {
		alert("请选择单条数据进行修改！！！");
		return;
	}
	var id=$(" tbody :checked").parent().next().text();
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	window.location.href=getPath()+"/updateQuer.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
function up(){
	if($("input,textarea").val()=="" || $("input,textarea").hasClass("error")){
		return false;
	}
	$("#updata").click(function(){
		$.ajax({
	        url :getPath()+"/updateCmpy.do",
	        data : $("#form1").serialize(),
	        dataType : 'json',
	        type : "post",
	        traditional: true,//属性在这里设置
	        success : function(result) {
	           location.href =getPath()+"/selectCmpy.do?cid=2";
	        },error:function(err){
	        	alert($.parseJSON(err.responseText).msg);
	        }
	    });
	})
}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/selectCmpy.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}