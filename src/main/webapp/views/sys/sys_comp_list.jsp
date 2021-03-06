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
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/sysComp_list.css"> --%>
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
		
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_comp_list.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/utilx.js"></script>
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
				    <li class="active">单位信息</li>
				</ol>
			</div>

			<div class="mQuery">
				<form role="form" id="forms">
					<input type="hidden" id="pages" name="pages" value="${pages }">
                   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="inp_1 form-group">
						<label for="comNum">单位编号：</label>
						<input type="text" id="comNum" name="comNum" value="" class="form-control" placeholder="请输入公司编号">
					</div>
					<div class="inp_2 form-group">
						<label for="comName">单位名称：</label>
						<input type="text" id="comName" name="comName" value="" class="form-control" placeholder="请输入公司名称">
					</div>
					<div class="inp_3 form-group">
						<label for="comAbbre">单位简称：</label>
						<input type="text" id="comAbbre" name="comAbbre" value="" class="form-control" placeholder="请输公司简称">
					</div>
					<a class="btn btn-primary" id="seach"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</form>								
			</div>
			
			<div class="btnout">
                <a onclick="addCmp()" class="btn btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">新增</a>
                <a onclick="updateCmp()" class="btn btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
                <a onclick="deleteId()" class="btn btn-sm "><img alt="" src="<%=request.getContextPath()%>/images/btn-del.png">删除</a>
                <a class="btn btn-sm" id="seeMenu"><img alt="" src="<%=request.getContextPath()%>/images/btn-sear.png">查看</a>
            </div>

			<div class="mRbot" style="height: 68%;">				
                <div id="data_panel"></div>
                <table class="table table-bordered text-center" id="text">
					<thead>
						<tr>
							<th width="3%"><input type="checkbox" name="allSelect"></th>
							<th>公司编号</th>
							<th>公司名称</th>
							<th>简称</th>
							<th>电话</th>
							<th>邮箱</th>
							<th>地址</th>
							<th>备注</th>
						</tr>						
					</thead>
					<tbody id="myTable">					
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
						<td style="width:100px;">单位编码:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >单位名称:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >单位简称:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >单位电话:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >单位邮箱:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >单位备注:</td>
						<td class="check-content-right nostr"></td>
					</tr>
					<tr>
						<td >单位地址:</td>
						<td class="check-content-right nostr"></td>
					</tr>
				</tbody>
				
			</table>
			
		</div>
		<!-- 背景变暗 -->
		<div class="mask"></div>
	<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>

	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		getPathList();
	</script>
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
</body>
</html>