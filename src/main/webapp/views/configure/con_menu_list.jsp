<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<%-- <link rel="stylesheet" href="<%=request.getContextPath()%>/css/configure_menu.css"> --%>
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/select-bootstrap/bootstrap-select.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/con_menu_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>	
</head>
<style>
#bPageList{ display:none;}
	#bPageInfo a{ border-left-width:1px; border-radius:4px 0 0 4px;}
.layui-layer-page .layui-layer-title { color:#fff;background:#3b85c7; }
.author_jump{width:600px;height: 200px;z-index: 2000;border-radius:2px;position: absolute;top: 95px;left: 25%;background-color: #f8f8f8;box-shadow:3px 3px 20px 1px rgba(0,0,0,.3);display: none;}
.author_jump .author_tit{ padding:0 20px; height:42px; border-radius:2px 2px 0 0 ;line-height:42px;color:#fff;background:#3b85c7;}
.author_jump .author_tit span{ display:inline-block; float:right;  height:42px; line-height:42px;cursor: pointer;font-size:32px; }
.author_jump .form-group{ margin-top:10px;height:40px;}
.author_jump .form-group label{ height:34px; line-height:34px;}
.btn_list{ float: right;}
.btn_list .btn_bass{ height: 26px; line-height: 26px; font-size: 12px; margin:0 8px; padding: 0 17px; display: inline-block;color: #fff;border-radius: 5px; }
.btn_list .btn_bass:hover{ cursor: pointer; text-decoration: none;}
.btn_list .btn_add{background: #3b85c7; width:58px;padding:0;outline:0;border:0px;}

.btn_close{ background: #e2614e; }
</style>
<script type="text/javascript">
</script>
<body>
		<input type="hidden" id="pageNumber" value="65">
		<input type="hidden" id="pageSize" value="20">
		<input type="hidden" id="totalPage" value="6">
		<input type="hidden" id="totalRow" value="5">
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">配置管理</a></li>
				    <li class="active">菜单管理</li>
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
						<label for="menuName">菜单名称：</label>
						<input type="text" id="menuName" class="form-control" placeholder="请输入菜单名称">
					</div>
					<div class="form-group">
						<label for="up">上级菜单：</label>
						<div class="col-sm-2">
							<select id="meauUp" name="meauUp" class="form-control">
								<option value="">请选择上级菜单</option>
							</select>
						</div>
					</div>
					
					<%-- <div class="inp_3 form-group">
						<label for="stitle">菜单等级：</label>
						<input type="text" id="menuLevl" class="form-control" placeholder="请输入菜单等级" value="${menuLevl }">
					</div> --%>
					<div class="form-group">
						<label for="levl">菜单等级：</label>
						<div class="col-sm-2">
							<input type="hidden" id="level" name="level" value="${sysMenuOne.menuLevel }"/>
							<select id="menuLevel" name="menuLevel" class="form-control">
								<option value="" selected="selected">请选择菜单等级</option>
								<option value="1">一级菜单</option>
								<option value="2">二级菜单</option>
							</select>
						</div>
					</div>
					<a class="btn btn-primary" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</form>					
			</div>

			<div class="btnout">
                <a class="btn btn-primary btn-sm " id="addMenu"><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">添加</a>
                <a class="btn btn-primary btn-sm " id="deleteMenu"><img alt="" src="<%=request.getContextPath()%>/images/btn-del.png">删除</a>
                <a class="btn btn-primary btn-sm " id="updateMenu"><img alt="" src="<%=request.getContextPath()%>/images/btn-edit.png">修改</a>
                <a class="btn btn-primary btn-sm" id="seeMenu"><img alt="" src="<%=request.getContextPath()%>/images/btn-sear.png">查看</a>
                <a class="btn btn-primary btn-sm " id="authorMenu"><img alt="" src="<%=request.getContextPath()%>/images/btn-grant.png">授权</a>
            </div>

			<div class="mRbot" style="height: 68%;">				
				<table class="table table-bordered table-hover text-center">
			        <thead>
			          <tr>
			            <th width="3%"><input type="checkbox" name="allSelect"></th>
			            <th>菜单编号</th>
			            <th>菜单名称</th>
			            <th>菜单等级</th>
			            <th>上级菜单</th>
			            <th>菜单链接</th>
			            <th>菜单描述</th>			  
			          </tr>
			        </thead>
			        <tbody class="tbody" id="tbody">
			        </tbody>
		        </table> 
			</div>
			
			<div class="mRpage" id="page2">
			</div>
		</div>
	<!-- 查看弹出框 -->
		<div class="check-win">
			
			<table class="table text-center table-bordered" style="margin-top:20px; width:90%; margin-left:auto;margin-right:auto;">
				<tbody>
					<tr>
						<td style="width:100px;">菜单编码:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td style="width:100px;">菜单名称:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td style="width:100px;">菜单等级:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td style="width:100px;">上级菜单:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td style="width:100px;">菜单链接:</td>
						<td class="check-content-right"></td>
					</tr>
					<tr>
						<td style="width:100px;">菜单描述:</td>
						<td class="check-content-right"></td>
					</tr>
				</tbody>
			
			</table>
		
		</div>
		<!-- 背景变暗 -->
		<div class="mask"></div>
		<div class="author_jump">
			<p class="author_tit">菜单授权 <span id="btn_outline">×</span></p>
			<div class="form-group">
				<label class="col-sm-3 text-right">菜单名称</label>
				<div class="col-sm-8">
					<input type="hidden" id="menu_id" class="form-control" value="">
					<input type="text" id="menu_name" class="form-control" disabled >					
				</div>				
			</div>
			<div class="form-group">
				<label class="col-sm-3 text-right">角色权限</label>
				<div class="col-sm-8">
					<select id="sys_role" name="sys_role" class="form-control selectpicker bla bla bli" multiple data-live-search="true">
					</select>				
				</div>
			</div>
			<div class="btn_list">
		
		<input class="btn_bass btn_add" type="button" id="button_search_author" value="确定">
			
				<a class="btn_bass btn_close" id="button_cancel_author" style="margin-right:20px;">取消</a>		
			</div>
		</div>

	<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	
		<script>
	$(function(){
		$('.authorMenuOpen_title span').click(function(){
			$('.authorMenuOpen').hide();
		})
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
		$("#btn_outline").click(function(){
			
			$(".author_jump").fadeOut();
			$(".mask").fadeOut();
		})
		/*页面的切换*/
		$("#top .collapse .nav li").eq(0).removeClass("active");
		$("#top .collapse .nav li").eq(4).addClass("active");
		$("#sys_role").selectpicker({
		    'selectedText': '123'
		});
	})
	showMenu();
	</script>
</body>
</html>