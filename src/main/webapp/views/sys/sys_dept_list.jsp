<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/sysDept_list.css"> --%>
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>	
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>	
		<style>
		#bPageList{ display:none;}
		#bPageInfo a{ border-left-width:1px; border-radius:4px 0 0 4px;}sss
	.layui-layer-page .layui-layer-title { color:#fff;background:#3b85c7; }
	</style>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">管理体系</a></li>
				    <li class="active">部门信息</li>
				</ol>
			</div>

			<div class="mQuery">
				<form action="" role="form">
					<input type="hidden" id="pages" name="pages" value="${pages }">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<div class="inp_1 form-group">
						<label for="startTime">部门名称：</label>
						<input type="text" id="deptName" name="deptName" placeholder="请输入部门名称" class="form-control">
					</div>
					<div class="form-group">
						<label for="userDept">部门级别：</label>
						<div class="col-sm-2">
							<select id="deptLevel" name="deptLevel" class="form-control">
								<option value="">请选择部门等级</option>
								<option value="1">一级部门</option>
								<option value="2">二级部门</option>
								<option value="3">三级部门</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="userDept">上级部门：</label>
						<div class="col-sm-2">
							<select id="deptParent" name="deptParent" class="form-control">
								<option value="">请选择上级部门</option>
							</select>
						</div>
					</div>
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</form>								
			</div>
			
			<div class="btnout">
                <a class="btn btn-primary btn-sm " id="addDept"><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">新增</a>
                <a class="btn btn-primary btn-sm " id="updDept"><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
                <a class="btn btn-primary btn-sm " id="delDept"><img alt="" src="<%=request.getContextPath()%>/images/btn-del.png">删除</a>
                <a class="btn btn-primary btn-sm " id="checkDept"><img alt="" src="<%=request.getContextPath()%>/images/btn-sear.png">查看</a>
            </div>

			<div class="mRbot" style="height: 68%;">				
                <table class="table table-bordered table-hover text-center" style="width:99%;">
                	<thead>
                		<tr>
                			<th width="3%"><input type="checkbox" name="allSelect"></th>
	                		<th width="10%">部门编号</th>
	                		<th width="20%">部门名称</th>
	                		<th width="10%">部门等级</th>
	                		<th width="20%">所属部门</th>
	                		<th>部门描述</th>
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
						<td style="width:100px;">部门编号:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >部门名称:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >部门级别:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >上级部门:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td >部门描述:</td>
						<td class="check-content-right"></td>
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

	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_dept_list.js"></script>	
	<script type="text/javascript">
		$(function(){
			/*页面的切换*/
			$("#top .collapse .nav li").eq(0).removeClass("active");
			$("#top .collapse .nav li").eq(3).addClass("active");
			$("#main .mLeft .list-group li").eq(0).removeClass("active");
			$("#main .mLeft .list-group li").eq(2).addClass("active");
			//加载部门信息
			showDept();
		})
	</script>
</body>
</html>