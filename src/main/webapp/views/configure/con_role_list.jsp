<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/sys_role_list.css"> --%>
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/con_role_list.js"></script>	
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<STYLE>
	#bPageList{ display:none;}
	#bPageInfo a{ border-left-width:1px; border-radius:4px 0 0 4px;}
	.layui-layer-page .layui-layer-title { color:#fff;background:#3b85c7; }
	</STYLE>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">管理体系</a></li>
				    <li class="active">角色管理</li>
				</ol>
			</div>
			<input type="hidden" id="pages" name="pages" value="${pages }">
			<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
			<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
			<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
			<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
			<div class="mQuery">
				<form action="" role="form">
					
					<div class="form-group">
						<label for="role_code">角色编号：</label>
						<input type="text" id="roleCode" placeholder="请输入角色编号" class="form-control">
					</div>
					<div class="form-group">
						<label for="roleName">角色名称：</label>
						<input type="text" id="roleName" placeholder="请输入角色名称" class="form-control">
					</div>
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</form>								
			</div>
			
			<div class="btnout">
                <a class="btn btn-primary btn-sm " id="addRole"><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">添加</a>
                <a class="btn btn-primary btn-sm " id="delRole"><img alt="" src="<%=request.getContextPath()%>/images/btn-del.png">删除</a>
                <a class="btn btn-primary btn-sm " id="updRole"><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
                <a class="btn btn-primary btn-sm " id="checkRole"><img alt="" src="<%=request.getContextPath()%>/images/btn-sear.png">查看</a>
            </div>

			<div class="mRbot">				
                <table class="table table-bordered table-hover text-center">
                	<thead>
                		<tr>
                			<th width="3%"><input type="checkbox" name="allSelect"></th>
	                		<th width="120px">角色编号</th>
	                		<th width="360px">角色名称</th>
	                		<th>角色描述</th>
                		</tr>                		
                	</thead>
                	<tbody id="tbody">
                	</tbody>
                </table>	
			</div>

			<div class="mRpage" id="page2"></div>
		</div>
		<!-- 查看弹出框 -->
		<div class="check-win">
			<table class="table table-bordered text-center" style="margin-top:10px; width:90%; margin-left:auto; margin-right:auto;">
				<tbody>
					<tr>
						<td style="width:100px;">角色编码:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >角色名称:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >角色描述:</td>
						<td class="check-content-right nostr"></td>
					</tr>
				</tbody>
				
			</table>
		
		</div>
		<!-- 背景变暗 -->
		<div class="mask"></div>
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
			/*页面的切换*/
			$("#top .collapse .nav li").eq(0).removeClass("active");
			$("#top .collapse .nav li").eq(4).addClass("active");
			$("#main .mLeft .list-group li").eq(0).removeClass("active");
			$("#main .mLeft .list-group li").eq(3).addClass("active");
			showRole();
		})
	</script>
</body>
</html>