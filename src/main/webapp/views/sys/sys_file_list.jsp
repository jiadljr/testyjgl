<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_file_list.js"></script>	
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
		<style>
		#bPageList{ display:none;}
		#bPageInfo a{ border-left-width:1px; border-radius:4px 0 0 4px;}
	.layui-layer-page .layui-layer-title { color:#fff;background:#3b85c7; }
	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">管理体系</a></li>
				    <li class="active">文件管理</li>
				</ol>
			</div>

			<div class="mQuery">
				<form action="" role="form">
					<input type="hidden" id="pages" name="pages" value="${pages }">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="form-group">
						<label for="file_code">文件编码：</label>
						<input type="text" id="file_code" name="file_code" class="form-control" placeholder="请输入文件编码">
					</div>
					<div class="form-group">
						<label for="file_name">文件名称：</label>
						<input type="text" id="file_name" class="form-control" name="file_name" placeholder="请输入文件名称">
					</div>
					<div class="form-group">
						<label for="file_type">文件类型：</label>
						<div class="col-sm-2">
							<select id="file_type" name="file_type" class="form-control">
								<option value="">请选择文件类型</option>
								<c:forEach var = "fileTy" items = "${selectAll}">
							       <option value="${fileTy.id }">${fileTy.name }</option>
							    </c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="file_user">添加人：</label>
						<input type="text" id="file_user" name="file_user" class="form-control" placeholder="请输入添加人">
					</div>
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</form>								
			</div>
			
			<div class="btnout">
                <a type="button" class="btn btn-primary btn-sm " id="addFile"><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">新增</a>
                <a type="button" class="btn btn-primary btn-sm " id="updFile"><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
                <a type="button" class="btn btn-primary btn-sm " id="delFile"><img alt="" src="<%=request.getContextPath()%>/images/btn-del.png">删除</a>
                <a type="button" class="btn btn-primary btn-sm " id="checkFile"><img alt="" src="<%=request.getContextPath()%>/images/btn-sear.png">查看</a>
            </div>

			<div class="mRbot" style="height: 68%;">				
                <table class="table table-bordered text-center table-hover">
                	<thead>
                		<tr>
                			<th width="40px"><input type="checkbox" name="allSelect"></th>
	                		<th width="120px">文件编码</th>
	                		<th>文件名称</th>
	                		<th>文件类型</th>
	                		<th>添加日期</th>
	                		<th>添加人</th>
	                		<th width="30%">文件描述</th>
	                		<th width="100px">操作</th>
                		</tr>                		
                	</thead>
                	<tbody id="tbody">
                	</tbody>
                </table>	
			</div>
			<div class="mRpage" id="pageTable"></div>
		</div>
		<!-- 查看弹出框 -->
		<div class="check-win">
				<table class="table table-bordered text-center" style="margin-top:10px; width:90%; margin-left:auto; margin-right:auto;">
				<tbody>
					<tr>
						<td style="width:100px;">文件编号:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >文件名称:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >文件类型:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >添加时间:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >添加人:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >文件描述:</td>
						<td class="check-content-right"></td>
					</tr>
				</tbody>
				
			</table>
			
		</div>
		<!-- 背景变暗 -->
	
			<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	
		<script>
	$(function(){
		
		function GetUrlStr(name){
			  var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			   var r = window.location.search.substr(1).match(reg);
			   if(r!=null)
			   return unescape(r[2]);
			   return null;
		}
		function storage(npm){
			layer.open({
	            type:4,
	        	time:2000,
	            tipsMore: true,
	            title: '提示',
	            tips:[2,"#eee"],
	            closeBtn:0,
	            area: ['200px', '70px'],
	            shade: 0,
	            maxmin: false,
	            offset: 'rb',
	            content: ['<p style="color:#333"> 提示</p><p style="color:#666">　'+npm+'</p>', '#eveBox']
	        });
		}
		if(GetUrlStr('cid')=='1'){
			storage('添加成功');
			
		}
		if(GetUrlStr('cid')=='2'){
			storage('修改成功');
			
		}			
	})

	</script>
		
	<script type="text/javascript">
	$(function(){
		$("#top .collapse .nav li").eq(0).removeClass("active");
		$("#top .collapse .nav li").eq(3).addClass("active");
		$("#main .mLeft .list-group li").eq(0).removeClass("active");
		$("#main .mLeft .list-group li").eq(4).addClass("active");
		$(".openWin").hide();
		//页面加载
		showFile();
		//查看	
		$("#checkFile").click(function(){
			var ids=$(" tbody input:checked");
			if (ids.parents("tr").length==1) {
				var id=$(" tbody input:checked").parent().next().text();
				$.ajax({
				url:getPath()+"/checkFile.do",
				data:{"id":id},
				dataType:"json",
				type:"post",
				success:function(result){
					var right=$(".check-content-right");
					var file=result.sysFile;
					var code=file.code;
					code=isnull(code);
					var name=file.name;
					name=isnull(name);
					var extend1=file.extend1;
					extend1=isnull(extend1);
					var extend3=file.extend3;
					extend3=isnull(extend3);
					var extend2=file.extend2;
					extend2=isnull(extend2);
					var remark=file.remark;
					remark=isnull(remark);
					var array=[code,name,extend1,extend3,extend2,remark];
					for (var i = 0; i < right.length; i++) {
						right.eq(i).text(array[i]);	
					}
					 layer.open({
				         type: 1,
				         title: '查看信息',
				         shadeClose: false,
				         area: ['463px', '300px'],
				         content:$(".check-win").html()				     
			       })
				}
			})
			}else{
				alert("请勾选需要查看的单条数据！！！");
				return;
			}
		
		})
		
		$("#button_search").click(function(){
			$("#tbody").empty();
			showFile();
		})
		
		//修改
		$("#updFile").click(function(){
			var length=$("tbody input:checked").length;
			if(length==0){
				alert("请选择需要修改的数据！");
				return false;
			}
			if(length>1){
				alert("请选择单行数据进行修改！");
				return false;
			}
			var id=$(" tbody input:checked").parent().next().text();
			var pages = 1;
			var pageNumber = $("#pagenumber").val();
			var pageSize = $("#pagesize").val();
			var totalPage = $("#totalpage").val();
			var totalRow = $("#totalrow").val();
			location.href=getPath() + "/modFileBack.do?id="+id+"&pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
		})
		
		//删除
		$("#delFile").click(function(){
			if($("tbody input:checked").length==0){
				alert("请选择需要删除的数据！");
				return false;
			}
			if(!confirm("确定删除该数据吗？")){
				return;
			}
			var ids=$(" tbody input:checked");
			var _list = new Array();  
			for (var i = 0; i < ids.length; i++) {  
			    _list[i] = ids.eq(i).parent().next().text();  
			}
			var data={
					"id":_list,
			}
			$.ajax({
				type:"post",
				url:getPath() + "/delFile.do",
				data :data,
				traditional: true,//属性在这里设置
				success:function(){
					$("#tbody").empty();
					showFile();
				}
			})
		})
		//添加
		$("#addFile").click(function(){
			var pages = 1;
			var pageNumber = $("#pagenumber").val();
			var pageSize = $("#pagesize").val();
			var totalPage = $("#totalpage").val();
			var totalRow = $("#totalrow").val();
			location.href=getPath() + "/insertDo.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
		})
	})
	</script>
</body>
</html>