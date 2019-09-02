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
	<script src="<%=request.getContextPath()%>/js/ser_check_list.js"></script>
	<style>	
	.box_shadow{box-shadow:0 0 5px rgba(0,0,0,.3);}
	.checkMes{ width:100%; height:92%;min-height:525px;}
	.text_hide{overflow: hidden;
text-overflow:ellipsis;
white-space: nowrap;}
	.checkMes .checkMes_left{position:relative;width:66%; height:100%; margin:0.5% 0.3%;border:1px solid #e2e0e0;float:left; }
	.checkMes .checkMes_left .checkMes_tit{text-indent:1.5em; width:100%; height:40px; line-height:40px; font-size:22px;color:#347fce;border-bottom:1px solid #b0ccf3; }
	.checkMes .checkMes_left form{ padding-top:50px; }
	.checkMes .checkMes_left .form-group{width:90%;}
	.checkMes .checkMes_left .form-group label{text-align:right;}
	 .btnlist{ width:100%; text-align:right; position:absolute;bottom:20px;}
	 .btnlist .btn{ color:#fff;padding:6px 14px; margin:0 15px; border-radius:6px;line-height:1;}
	.btnlist .btnClose{ background:#e2614e;}
	.checkMes .checkMes_right{ width:32%;height:100%; float:right; margin:0.5% 0.3%;border:1px solid #e2e0e0;float:left;}
	.checkMes .checkMes_right .checkMes_right_tit{text-align:center; width:100%; font-size:14px;color:#f7f7f7;height: 35px;line-height: 35px;background:#41b4f1;position: relative;}
	.checkMes .checkMes_right .checkMes_right_tit::before{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:2;width: 0;height: 0;border-left:35px solid #fff;border-bottom:35px solid transparent;}
	.checkMes .checkMes_right .checkMes_right_tit::after{box-sizing: border-box;content:" ";position: absolute;top:0;left:0;z-index:1;width: 0;height: 0;border-bottom:35px solid #3aa2d8;border-left:35px solid transparent;box-shadow:3px 3px 2px rgba(0,0,0,.3)}
	.checkMes .checkMes_right .checkMes_right_tit span{position:absolute;left:-5px;top:-5px ;width:40px; height:40px; display:block;background:#fff;}
	.checkMes .checkMes_right .accDept{width:100%; height:30px; line-height:30px; }
	.checkMes .checkMes_right .accDept label{width:30%; margin:0; height:30px; line-height:30px;font-size:12px;text-align:right;float:left;display:inline-block;}
	.checkMes .checkMes_right .accDept i{font-style:normal; width:60%;hieght:30px; line-height:30px;font-size:12px;text-align:left; float:left;display:inline-block;}
	.checkMes .checkMes_right .accDept_last textarea{width:60%; height:100px; float:left;margin-left:1%;}
		
	</style>	
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">首页</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">审核信息</li>
				</ol>
			</div>
			<div class="checkMes">
				<div class="checkMes_left">
					<p class="checkMes_tit">审核信息</p>
					<form role="form" class="form-horizontal" id="form1">
					   <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
	                   <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
	                   <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
	                   <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						
					   <input type="hidden" name="id" value="${eventOne.id }"/>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">受理人：</label>
							<div class="col-sm-9">
								${eventOne.accept_name }
							</div>
						</div>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">受理时间：</label>
							<div class="col-sm-9">
								<fmt:formatDate value="${eventOne.date_accept}" pattern="yyyy-MM-dd HH:mm:ss" />
							</div>
						</div>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">事件等级：</label>
							<div class="col-sm-9">
								<c:if test="${eventOne.event_level == 1}">一级事件</c:if>
								<c:if test="${eventOne.event_level == 2}">二级事件</c:if>
								<c:if test="${eventOne.event_level == 3}">三级事件</c:if>
								<c:if test="${eventOne.event_level == 4}">四级事件</c:if>
							</div>
						</div>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">事件源：</label>
							<div class="col-sm-9">
								${eventOne.event_source }
							</div>
						</div>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">服务类型：</label>
							<div class="col-sm-9">
								${eventOne.service_name }
							</div>
						</div>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">优先级：</label>
							<div class="col-sm-9">
								<c:if test="${eventOne.event_priority == 1}">高</c:if>
								<c:if test="${eventOne.event_priority == 2}">中</c:if>
								<c:if test="${eventOne.event_priority == 3}">低</c:if>
							</div>
						</div>
						<div class="form-group">
							<label for="deptLevel" class="col-sm-3">受理意见：</label>
							<div class="col-sm-9">
								<textarea  disabled="disabled" style=" vertical-align:top;" class="form-control">${eventOne.accept_desc }</textarea>
							</div>
						</div>
															
						<div class="btnlist">
							<!-- <a class="btn btn-primary btn-sm" id="addCheckConfirm" style="margin-left:10%">确定</a> -->
							<a class="btn btn-primary btn-sm" onclick="cancel()">取消</a>
						</div>
					</form>
				</div>
				<div class="checkMes_right">
					<p class="checkMes_right_tit">申告信息<span></span></p>
					<p class="accDept">
						<label>申告部门:　</label>
						<i class="text_hide">${eventOne.dept_name }</i>
					</p>
					<p class="accDept">
						<label>申告人:　</label>
						<i class="text_hide">${eventOne.aplly_name }</i>
					</p>
					<p class="accDept">
						<label>联系方式:　</label>
						<i class="text_hide">${eventOne.event_contact }</i>
					</p>
					<p class="accDept">
						<label>申告时间:　</label>
						<i class="text_hide">${eventOne.date_create}</i>
					</p>
					<div class="accDept accDept_last">
               			<label>事件描述:　</label>
               			<textarea disabled="disabled" style=" vertical-align:top;" class="form-control">${eventOne.date_create}</textarea>          
               		</div>
				</div>
			</div>

			
		</div>
	<script type="text/javascript">
		$(function(){
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