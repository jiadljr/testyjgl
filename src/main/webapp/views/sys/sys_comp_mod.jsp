<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/sys_comp_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">单位信息</a></li>
				    <li class="active">修改单位</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot	">
				<div class="mRwell">
					<div class="mRwell-tit">修改单位信息</div>
					<form role="form" class="form-horizontal" id="form1" method="post">
						<input type="hidden" id="pages" name="pages" value="${pages }">
	                   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
	                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
	                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
	                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="form-group">
								<input type="hidden" id="compId" value="${sysCmp.id }" name="compId">
									<label for="code" class="col-sm-3">公司编号<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="code" name="meauCode" value="${sysCmp.code }" class="form-control" data-message="公司编号不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="name" class="col-sm-3">公司名称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="name" name="meauName" value="${sysCmp.name }"  class="form-control" data-message="公司名称不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="sub_name" class="col-sm-3">简称<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="sub_name" name="meauGrade" value="${sysCmp.subName }"  class="form-control" data-message="简称不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="tel" class="col-sm-3">电话<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="tel" name="meauUp" value="${sysCmp.tel }"  class="form-control telClass" data-message="电话不得为空！" ></div>
								</div>
								<div class="form-group">
									<label for="mail" class="col-sm-3">邮箱<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="mail" name="meauLink" value="${sysCmp.mail }"  class="form-control" data-message="邮箱不得为空！" ></div>
								</div>
								<div class="form-group">
									<label for="addr" class="col-sm-3">地址<span style="color:red;font-size:16px;">*</span>：</label>
									<div class="col-sm-9"><input type="text" id="addr" name="meauDesc" value="${sysCmp.addr }"  class="form-control" data-message="*地址不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="remark" class="col-sm-3">备注<span style="color:#fff;font-size:16px;">*</span>：</label>
									<div class="col-sm-9">
										<textarea class="form-control" id="remark" name="remark" data-message="*备注不得为空！">${sysCmp.remark }</textarea>
									</div>
								</div>
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="updata">确定</a>
									<a class="btn btn-sm" onclick="cancel()">取消</a>
								</div>
							</div>
						</div>
					</form>
				</div>				 
			</div>
		</div>
	<script type="text/javascript">
		up();
	</script>
</body>
</html>