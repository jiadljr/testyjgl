$(function(){
	//修改
	$("#modConfirm").click(function(){
		var fileId = $("#fileId").val();
		var code=$("#file_code").val();
		var name=$("#file_name").val();
		var fileType=$("#file_type").val();
		var remark=$("#file_remark").val();
		var data = {"fileId":fileId,"code":code,"name":name,"fileType":fileType,"remark":remark};
		$.post(getPath()+"/modFileOk.do",data,function(){
			location.href = getPath()+"/seleDo.do?cid=2";
		})
	})
	
})
function showFile(){
	$("#pageTable").empty();
	$('#pageTable').bPage({
			url:getPath() + "/selectAll.do",
			asyncLoad : true,
		    serverSidePage : false,
			render:function(result){
				var pageNumber = result.pageNumber;
				var pageSize = result.pageSize;
				var totalPage = result.totalPage;
				var totalRow = result.totalRow;
				$("#pagenumber").val(pageNumber);
				$("#pagesize").val(pageSize);
				$("#totalpage").val(totalPage);
				$("#totalrow").val(totalRow);
				$("#tbody").empty();
				for(var i=0; i<result.selectAll.length;i++){
					var tr="";
					id=result.selectAll[i].id;
					code=result.selectAll[i].code;
					code=isnull(code);
					name=result.selectAll[i].name;
					name=isnull(name);
					extend1=result.selectAll[i].extend1;
					extend1=isnull(extend1);
					extend3=result.selectAll[i].extend3;
					extend3=isnull(extend3);
					extend2=result.selectAll[i].extend2;
					extend2=isnull(extend2);
					remark=result.selectAll[i].remark;
					remark=isnull(remark);
					tr="<tr >"
						+ "<td><input type='checkbox' name='item1'></td>"
						+ "<td style='display:none;'>"+id+"</td>"
						+ "<td>" + code +"</td>"
						+ "<td>" + name +"</td>"
						+ "<td>" + extend1 +"</td>"
						+ "<td>" + extend3 +"</td>"
						+ "<td>" + extend2 +"</td>"
						+ "<td>" + remark +"</td>"
						+ "<td><a class='btn btn-primary btn_new_small btn_blue' onClick='downFile(\""+id+"\")'>下载</a></td>";
					$("#tbody").append(tr);
				}
	  },
	  params : function(){
		  var pages = $("#pages").val();
		  var pagenumber = $("#pagenumber").val();
		  var pagesize = $("#pagesize").val();
		  var totalpage = $("#totalpage").val();
		  var totalrow = $("#totalrow").val();
		  var fileCode = $("#file_code").val();
			var fileName = $("#file_name").val();
			var fileType = $("#file_type").val();
			var fileUser = $("#file_user").val();
	        return {
	        	"pages":pages,
	        	"pagenumber":pagenumber,
	        	"pagesize":pagesize,
	        	"totalpage":totalpage,
	        	"totalrow":totalrow,
	        	"fileCode":fileCode,
	        	"fileName":fileName,
	        	"fileType":fileType,
	        	"fileUser":fileUser
	        };
	    }
})
$("#pages").val("");
}
//文件下载
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
function ChangeDateFormat(d){
	//将时间戳转为int类型，构造Date类型
	var date = new Date(parseInt(d.time,10));

	//月份得+1，且只有个位数时在前面+0
	var month = date.getMonth() + 1 < 10 ?"0" + (date.getMonth() + 1) : date.getMonth() + 1;

	//日期为个位数时在前面+0
	var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

	//getFullYear得到4位数的年份 ，返回一串字符串
	return date.getFullYear()+"-" +month +"-" +currentDate;
	}
function cancel(){
	var pages = 1;
	var pageNumber = $("#pagenumber").val();
	var pageSize = $("#pagesize").val();
	var totalPage = $("#totalpage").val();
	var totalRow = $("#totalrow").val();
	location.href = getPath() + "/seleDo.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
}
	