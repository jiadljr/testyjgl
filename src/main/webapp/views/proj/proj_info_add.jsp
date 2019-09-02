<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/proj_info_list.js"></script>
	
	<script type="text/javascript">
	</script>	
</head>
<body>

	<div class="mRight">
		<div class="mRpos">
			<ol class="breadcrumb">
			    <li><a href="#"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
			    <li><a href="#">管理体系</a></li>
			    <li class="active">项目管理</li>
			</ol>
		</div>
		<div class="mRbot mNewRbot">
			<div class="mRwell">
				<div class="mRwell-tit">添加项目信息</div>
				<form role="form" class="form-horizontal" id="form1" method="post">
					<input type="hidden" id="pages" name="pages" value="${pages }">
					<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
					<input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
					<input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
					<input type="hidden" id="projName" name="projName" value="${paramProjName }">
					<input type="hidden" id="projType" name="projType" value="${paramProjType }">
					<input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
					<input type="hidden" name="token" value="${token}" />
					<div class="mRwell-container">
						<div class="mRwell-con-center">
							<div class="form-group">
								<label for="projCode" class="col-sm-2">项目编号：</label>
								<div class="col-sm-10"><input type="text" id="projCode" name="projCode" class="form-control error_input" data-message="项目编号不得为空！"></div>
							</div>
							<div class="form-group">
								<label for="projName" class="col-sm-2">项目名称：</label>
								<div class="col-sm-10"><input type="text" id="projName" name="projName" class="form-control error_input" data-message="项目名称不得为空！"></div>
							</div>			
							<div class="form-group">
								<label for="projType" class="col-sm-2">项目类型：</label>
								<div class="col-sm-10">
									<select id="projType" name="projType" class="form-control">
										<option value="">请选择项目类型</option>
										<c:forEach items="${projTypeList }" var="projType">
										<option value="${projType.id }">${projType.name }</option>
									</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label for="projAmt" class="col-sm-2">项目金额：</label>
								<div class="col-sm-10">
									<input type="text" id="projAmt" name="projAmt" class="form-control error_input" style="width:120px;float:left;"><span style="width:40px; text-align:center;display:inline-block;" >元</span>
								</div>
							</div>
							<div class="form-group">
								<label for="projStartDate" class="col-sm-2">开始时间<span style="color:red">*</span></label>
								<div class="col-sm-10"><input type="text" id="startTime" name="startTime" class="form-control">
									<input size="16" type="text" value="2012-06-15 14:45" readonly class="form_datetime">
								</div>
							</div>
							<div class="form-group">
								<label for="projEndDate" class="col-sm-2">结束时间：</label>
								<div class="col-sm-10"><input type="text" id="endTime" name="endTime" class="form-control"></div>
							</div>
							<!-- 
							<div class="form-group">
								<label for="projManager" class="col-sm-2">项目经理：</label>
								<div class="col-sm-10">
									<input type="hidden" id="idManager" name="idManager" value=""/>
									<input type="text" id="projManager" name="projManager" class="form-control" readonly onclick="showManager()">
								</div>
							</div>
							<div class="form-group">
								<label for="projMember" class="col-sm-2">项目成员：</label>
								<div class="col-sm-10">
									<input type="hidden" id="idMember" name="idMember" value=""/>
									<input type="text" id="projMember" name="projMember" class="form-control" readonly onclick="showMember()">
								</div>
							</div> -->
							<div class="btnlist">
								<a href="javascript:addSave();" class="btn btn-primary btn-sm" id="saveProj" >确定</a>
								<a href="javascript:QkCannelInfo()" class="btn btn-sm" >取消</a>
							</div>
						</div>
					</div>
				</form>					
			</div>				 
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
			$("#startTime").datetimepicker({
				lang:'zh',
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
			$("#endTime").datetimepicker({
				lang:'cn',
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y-m-d',
			});
		})

		function addSave(){
			var formData = new formData($("#form1")[0]);
			$.ajax({
				type:"post",
				url:getPath() + "/saveProjInfo.do",
				data:formData,
				traditional: true,
				success:function(result){
					if(result == "succ"){
						location.href=getPath() + "/toProjPage.do";
					}
				},
				error:function(result){
					alert(321);
				}
			})
		}
		function showManager(){
			var url = getPath()+"/showManager.do";
			layer.open({
			    type: 2,
			    title: '人员信息',
			    scrollbar: true,
			    shadeClose: true,
			    area: ['570px', '370px'],
			    content: [url,'yes']
			  })
		}
		function showMember(){
			var idMember = $('#idMember').val();
			var projMember = $('#projMember').val();
			var url = getPath()+"/showMember.do?idMember="+idMember+"&projMember="+projMember;
			layer.open({
			    type: 2,
			    title: '人员信息',
			    scrollbar: true,
			    shadeClose: true,
			    area: ['570px', '452px'],
			    content: [url,'yes']
			  })
		}

	</script>
</body>
</html>