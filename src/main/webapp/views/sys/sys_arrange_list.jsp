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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_arrange_list.js"></script>	
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<STYLE>
	#bPageList{ display:none;}
	#bPageInfo a{ border-left-width:1px; border-radius:4px 0 0 4px;}
	.layui-layer-page .layui-layer-title { color:#fff;background:#3b85c7; }
	.mQuery-2 .form-group{ margin:0;}
	</STYLE>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="#">管理体系</a></li>
				    <li class="active">排班管理</li>
				</ol>
			</div>
			<input type="hidden" id="pages" name="pages" value="${pages }">
			<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
			<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
			<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
			<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
			<div class="mQuery-2" style="background:#fff; border-bottom:1px solid #eceaea;">
				<form action="" role="form">
					
					<div class="form-group col-sm-3">
						<label for="role_code" class="col-sm-5" >运维人员姓名：</label>
						<div class="col-sm-7">
							<select id = "oper_id"  class = "form-control">
									<option value="">请选择运维人员</option>
							</select>
						</div>
					</div>
					<div class="form-group col-sm-3">
						<label for="roleName" class="col-sm-4">开始时间：</label>
						<div class="col-sm-8">
							<input type="text" id="duty_start_time" class="form-control" placeholder="请选择开始时间" >
						
						</div>
			
					</div>
					<div class="form-group col-sm-3">
						<label for="roleName" class="col-sm-4">结束时间：</label>
						<div class="col-sm-6">
							<input type="text" id="duty_end_time" class="form-control" placeholder="请选择结束时间" >						
						</div>
				
					</div>
					<a class="btn btn-primary" style="margin-top:0" id="button_search"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
						
				
					
						
					
										
				</form>								
			</div>
			
			<div class="btnout">
                <a class="btn btn-primary btn-sm " id="addDuty"><img alt="" src="<%=request.getContextPath()%>/images/btn-add.png">添加</a>
            	<a onclick="dutyExport()" class="btn btn-primary btn-sm " id=""><img alt="" src="<%=request.getContextPath()%>/images/btn-out.png">导出</a>
            </div>

			<div class="mRbot">				
                <table class="table table-bordered table-hover text-center">
                	<thead>
                		<tr>
	                		<th width="360px">运维人员姓名</th>
	                		<th>值班时段</th>
	                		<th width="100px">操作</th>
                		</tr>                		
                	</thead>
                	<tbody id="tbody">
                	</tbody>
                </table>	
			</div>

			<div class="mRpage" id="page2"></div>
		</div>
		<!-- 背景变暗 -->
		<div class="mask"></div>
			<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	
		<script>
	$(function(){
		$("#duty_start_time").datetimepicker({
			lang:'zh',
			timepicker : false,
			format : 'Y-m-d',
			formatDate : 'Y-m-d',
		});
		$("#duty_end_time").datetimepicker({
			lang:'zh-CN',
			timepicker : false,
			format : 'Y-m-d',
			formatDate : 'Y-m-d',
		});
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
		if(GetUrlStr('cid')=='3'){
			storage('删除成功');
			
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
			showArrangeList();
		})
	</script>
</body>
</html>