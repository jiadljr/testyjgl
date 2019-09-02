<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/audit_login.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/select/jquery.selectseach.min.js"></script>
<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
<script src="<%=request.getContextPath()%>/js/audit_ligin_list.js"></script>
<script src="<%=request.getContextPath()%>/plugins/select/jquery.selectseach.min.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>


</head>

<body>
<div class="mRight">
	<div class="mRpos">
		<ol class="breadcrumb">
		    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">当前位置</a></li>
		    <li><a href="javascript:;">审计管理</a></li>
		    <li class="active">日志</li>
		</ol>
	</div>
	<ul class="tabOn">
		<li class="cur" id="login">登录日志</li>
		<li id="oper">操作日志</li>
	</ul>
	

	<div class="mQuery-2 l_audit" style="height:90px" >
		<form action="" role="form" class="form-horizontal" method="post">
			<div class="form-group col-sm-4">
				<div  class="col-sm-3 loginTime text-right" style="padding-top:6px; line-height:0px;">
					<select id="loginTime" style="height:20px; margin:0 2px 0 0; float:right;">
						<option value="1" selected="selected">登入时间</option>
						<option value="2">登出时间</option>
					</select>　
				</div>
				<div class="col-sm-4">
					<input type="text" id="startTime" name="startTime" class="form-control" placeholder="请选择开始时间">
				</div>
				<div class="col-sm-1 text-center">至</div>
				<div class="col-sm-4">
					<input type="text" id="endTime" name="endTime" class="form-control" placeholder="请选择结束时间">
				</div>
			</div>
			<div class="form-group col-sm-8">
				<label for="staffContact" class="col-sm-2">员工编号:　</label>
				<div class="col-sm-2">
					<input type="text" id="staffContact" name="staffContact" class="form-control" placeholder="请输入编号">
				</div>
				<label for="staffName" class="col-sm-2">员工姓名:　</label>
				<div class="col-sm-2">
					<input type="text" id="staffName" name="staffname" class="form-control" placeholder="请输入姓名">
				</div>
			</div>
		
			<div class="form-group col-sm-4" style="margin-top:-10px">
				<label for="clientIP" class="col-sm-3">客户端IP:　</label>
				<div class="col-sm-9" >
					<input type="text" id="clientIP" name="clientIP"  class="form-control" onchange="clearService()" placeholder="请输入客户端IP">
				</div>
			</div>
			<div class="form-group col-sm-8" style="margin-top:-10px">
				<label for="clientMac" class="col-sm-2">客户端MAC:　</label>
				<div class="col-sm-5" >
					<input type="text" id="clientMac" name="clientMac"  class="form-control" onchange="clearDept()" placeholder="请输入客户端MAC">
				</div>
				<div class=" col-sm-1 text-right" >
					<a class="btn btn-primary" id="button_log_search" style="display:inline-block;margin:0 0 0 10px;"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</div>	
			</div>
							
		</form>									
	</div>
	<div class="mRbot l_audit" style="top:130px">
		<div class="fixTable">
			<table class="table table-bordered table-hover table1">
              	<thead>
              		<tr>
              		<th style="width:60px;">序号</th>
               		<th>员工编号</th>
               		<th style="width:80px">姓名</th>
               		<th>登入时间</th>
               		<th>登出时间</th>
               		<th>客户端IP</th>
               		<th>客户端MAC</th>
               		
              		</tr>                		
              	</thead>
              	
              		
              </table>
		</div>
		<div class="relTable">
			<table class="table text-center table-hover table-striped table-bordered">
				<tbody id="DeptTbody">						
				</tbody>
			</table>
		</div>			
        <div class="btn_out">
        	<a href="javascript:;" class="btn"><img alt="" src="<%=request.getContextPath()%>/images/outfile.png">导出</a>
        </div>      
	</div>
	<div class="mRpage l_audit" id="pageTable"></div>
	
	<div class="mQuery-2 o_audit" style="height:90px">
		<form action="" role="form" class="form-horizontal" method="post">
			<div class="form-group col-sm-4">
				<label for="staffContact" class="col-sm-3">操作时间:　</label>
				<div class="col-sm-4">
					<input type="text" id="startTimes" name="startTimes" class="form-control" placeholder="请选择开始时间">
				</div>
				<div class="col-sm-1 text-center">至</div>
				<div class="col-sm-4">
					<input type="text" id="endTimes" name="endTimes" class="form-control" placeholder="请选择结束时间">
				</div>
			</div>
			<div class="form-group col-sm-8">
				<label for="staffContact" class="col-sm-2">员工编号:　</label>
				<div class="col-sm-2">
					<input type="text" id="staffContacts" placeholder="请输入编号" name="staffContacts" class="form-control">
				</div>
				<label for="staffName" class="col-sm-2">员工姓名:　</label>
				<div class="col-sm-2">
					<input type="text" id="staffNames" placeholder="请输入姓名" name="staffnames" class="form-control">
				</div>
				<div class=" col-sm-1 text-right" >
					<a class="btn btn-primary" id="button_search" style="display:inline-block;margin:0 0 0 10px;"><img src="<%=request.getContextPath()%>/images/search.png">搜索</a>
				</div>	
			</div>
		
			<div class="form-group col-sm-4" style="margin-top:-10px">
				<label for="operationType" class="col-sm-3">操作类型:　</label>
				<div class="col-sm-3" >
					<select id="operationTypes" name="operationTypes" class="form-control">
						<option value="" selected="selected">请选择操作类型</option>
						<option value="1" >新增</option>
						<option value="2" >修改</option>
						<option value="3" >删除</option>
					</select>
				</div>
				<label for="operationTable" class="col-sm-3">操作表:　</label>
				<div class="col-sm-3" >
					<select id="operationTables" name="operationTables" class="form-control">
					    <option value="" selected="selected">请选择操作表</option>
						<option value="sys_user" >人员表</option>
						<option value="sys_file">文件表</option>
						<option value="sys_assets" >资产表</option>
						<option value="sys_asset_type" >资产类型表</option>
						<option value="sys_dept" >部门表</option>
						<option value="sys_cmpy" >单位信息表</option>
					</select>
				</div>
			</div>
		</form>							
	</div>
	<div class="mRbot o_audit" style="top:130px">
		<div class="fixTable">
			<table class="table table-bordered table-hover table1">
              	<thead>
              		<tr>
              		<th style="width:60px;">序号</th>
               		<th>员工编号</th>
               		<th style="width:80px">姓名</th>
               		<th>操作表</th>
               		<th style="width:80px">操作类型</th>
               		<th>操作时间</th>
              		</tr>                		
              	</thead>
              </table>
		</div>
		<div class="relTable">
			<table class="table text-center table-hover table-striped table-bordered">
				<tbody id="operTbody">
              	</tbody>
			</table>
		</div>			
        <div class="btn_out">
        	<a href="javascript:;" class="btn"><img alt="" src="<%=request.getContextPath()%>/images/outfile.png">导出</a>
        </div>      
	</div>
	<div class="mRpage o_audit" id="pageTables"></div>
	<div class="perpens" style="opacity:0;">
			<table class="table table-bordered table-hover table1">
              	<thead>
              		<tr>
              		<th style="width:60px;">序号</th>
               		<th>员工编号</th>
               		<th style="width:80px">姓名</th>
               		<th>操作表</th>
               		<th style="width:80px">操作类型</th>
               		<th>操作时间</th>
              		</tr>                		
              	</thead>
              </table>
		</div>
<script type="text/javascript">
	$(function(){
		$("#startTime").datetimepicker({
			lang:'zh',
			timepicker : false,
			format : 'Y-m-d',
			formatDate : 'Y-m-d',
		});
		$("#endTime").datetimepicker({
			lang:'zh-CN',
			timepicker : false,
			format : 'Y-m-d',
			formatDate : 'Y-m-d',
		});
		$("#startTimes").datetimepicker({
			lang:'zh',
			timepicker : false,
			format : 'Y-m-d',
			formatDate : 'Y-m-d',
		});
		$("#endTimes").datetimepicker({
			lang:'zh-CN',
			timepicker : false,
			format : 'Y-m-d',
			formatDate : 'Y-m-d',
		});
		
	})
	$('.o_audit').hide();
	$('.l_audit').show();
	$('#login').click(function(){
		$('.o_audit').hide();
		$(this).addClass('cur');
		$("#oper").removeClass('cur');
		$('.l_audit').show();
	})
	$('#oper').click(function(){
		$('.o_audit').show();
		$(this).addClass('cur');
		$("#login").removeClass('cur');
		$('.l_audit').hide();
	})
	
</script>

</div>
</body>
</html>
