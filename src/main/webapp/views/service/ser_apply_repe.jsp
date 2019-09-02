<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_apply_list.js"></script>	
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">修改申告</li>
				</ol>
			</div>

			<div class="btnout">
                <a class="btn btn-primary btn-sm" id="addApply"><img alt="" src="<%=request.getContextPath()%>/images/add-apply.png">添加申告</a>
            </div>

			<div class="mRbot mNewRbot">				
                <div class="mRwell">
					<div class="mRwell-tit">修改申告信息</div>
					<form role="form" class="form-horizontal" id="form1">
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
	                    <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
	                    <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
	                    <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<div class="mRwell-con-tit">申告人信息</div>
						<div class="mRwell-container">
							<div class="mRwell-con">								
								<div class="form-group">
									<label for="depart" class="col-sm-3">申告科室：</label>
									<div class="col-sm-8" style="position:relative">
										<input type="hidden" value="${selectById.idDept }" id="idDept" name="idDept">
										<input type="text" id="eventDept" name="eventDept" value="${septTel.name }"  onChange="deptChange()" class="form-control">
										<ul id="tree" class="ztree" style="width:100%;z-index:10000; overflow:auto;position:absolute;top:36px;background-color:#eee;display:none"></ul>
									</div>
								</div>
								<div class="form-group">
									<label for="person" class="col-sm-3">申告人：</label>
									<div class="col-sm-8">
										<select id="eventUser" name="eventUser" onChange="userChange()" class="form-control">
											<c:forEach var = "sfm" items = "${selectUser}">
											 <option value="${sfm.id }" <c:if test="${sfm.id eq selectById.idUserAplly}"> selected="selected"</c:if>>${sfm.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group">
									<label for="mode" class="col-sm-3">申告方式：</label>
									<div class="col-sm-8">
										<select id="eventWay" name="eventWay" class="form-control" data-message="申告方式不得为空！">
											<option value="1" <c:if test="${1 eq selectById.eventWay}"> selected="selected"</c:if>>运维系统</option>
											<option value="2" <c:if test="${2 eq selectById.eventWay}"> selected="selected"</c:if>>电话</option>
											<option value="3" <c:if test="${3 eq selectById.eventWay}"> selected="selected"</c:if>>微信</option>
											<option value="4" <c:if test="${4 eq selectById.eventWay}"> selected="selected"</c:if>>邮件</option>
										</select>
									</div>
								</div>								
							</div>
							<div class="mRwell-con">
								<div class="form-group" style="margin-bottom:75px">
									<label for="telephone" class="col-sm-3">固定电话：</label>
									<div class="col-sm-8"><input type="text" id="deptTel" name="deptTel" value="${septTel.tel }" class="form-control telClass" data-message="姓名不得为空！"></div>
								</div>
								<div class="form-group">
									<label for="contact" class="col-sm-3">联系方式：</label>
									<div class="col-sm-8"><input type="text" id="eventContact" name="eventContact" value="${selectById.eventContact }" class="form-control telClass" data-message="电话不得为空！" ></div>
								</div>
							</div>
						</div>
						<div class="mRwell-con-tit">事件信息</div>
						<div class="mRwell-container" style="height:230px;">
							<div class="mRwell-con-center" style="float:left;width:100%; ">
								<div class="form-group" style="width:60%;">
									<label for="tit" class="col-sm-2" style="width:24%;">事件标题：</label>
									<div class="col-sm-10" style="width:76%;" >
										<input type="text" id="eventName" name="eventName" class="form-control" data-message="事件标题不得为空！" >
									</div>
								</div>
								<div class="form-group" style="width:60%;">
									<label for="remark" class="col-sm-2"style="width:24%;">事件描述：</label>
									<div class="col-sm-10" style="width:76%;">
										<textarea id="eventDesc" name="eventDesc" class="form-control" data-message="事件描述不得为空！"></textarea>
									</div>
								</div>
								<div class="btnlist" style="text-align:center; width:100%; margin-top:20px;">
									<a class="btn btn-primary btn-sm" id="addConfirm" style="margin:0 10px; display:inline-block;">确定</a>
									<a class="btn btn-sm" onclick="cancel()" style="margin:0 10px; display:inline-block;">取消</a>
								</div>
							</div>
						</div>			
					</form>
				</div>
			</div>
		</div>
	
	<!-- 背景变暗 -->
	<div class="mask"></div>
	<script type="text/javascript">
		$(function(){
			$("#addConfirm").click(function(){
				if($("input,textarea").val()=="" || $("input,textarea").hasClass("error")){
					return false;
				}
				$.ajax({
					type:"post",
					url:getPath() + "/addEventInfo.do",
					data:$("#form1").serialize(),
					traditional: true,
					success:function(result){
						if(result==1){
							var pages = 1;
							var pageNumber = $("#pagenumber").val();
							var pageSize = $("#pagesize").val();
							var totalPage = $("#totalpage").val();
							var totalRow = $("#totalrow").val();
							location.href = getPath() + "/eventInfoList.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
						}else if(result == 0){
							alert("新增数据失败");
						}
					}
				})
			})
			$('#eventName').bind('input propertychange',function(){ //添加监听input值的改变事件
				   var tvalmum;
				     //统计input输入字段的长度
				   tvalnum = $(this).val().length;
				   //如果大于8个字直接进行字符串截取
				   if(tvalnum>50){
				     var tval = $(this).val();        
				     tval = tval.substring(0,50);        
				     $(this).val(tval);
				     alert('最多只能输入50个字符！'); 
				   }
				});
		})
	</script>
</body>
</html>