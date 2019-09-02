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
		<script src="<%=request.getContextPath()%>/plugins/layer/layer.js" > </script>
     
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_apply_list.js"></script>	
	<style type="text/css">
	.ztree {
		padding: 0;
		border: 2px solid #CDD6D5;
		width:160px; height:150px; overflow:auto;
		}
		.btnHover:hover{text-decoration: none; color: #fff; opacity: 0.8;cursor: pointer;}
		 *{margin:0;padding:0;}
        .mRwell_box{
            width:650px;

            min-height: 260px;
            background: #f0f0f0;
        }
     
        label { margin: 0; font-weight: normal; text-align: right;}
        .mRwell_box .mRwell-con-tit-big{width:100%; height:24px; margin: 0; text-align: left; line-height: 24px; font-size: 12px; color: #fff;background: #3b85c7;}
        .mRwell-con-tit-big span{ float:right; display:inline-block; font-size:14px; margin-right:10px; height:24px; line-height:24px;cursor: pointer; }
       .mRwell_box .mRwell_inner{width:650px;box-shadow:0 5px 20px #d2d2d2;min-height: 320px; margin: 5px auto 7px auto;background: #fff; padding-top: 8px;}
        .mRwell_box  .mRwell_inner .mRwell_top{position: relative; width:640px;min-height: 305px; margin:0 auto; border: 1px solid #a2a0a0;box-sizing: border-box; }
        .mRwell_box  .mRwell_inner .mRwell_bot{position: relative; width: 640px ;min-height:210px ; margin: 8px 2px;border: 1px solid #a2a0a0;box-sizing: border-box;}
        .mRwell_box  .mRwell_inner .rel_tit{ display: inline-block; padding: 0 4px; background: #fff; color: #3d3d3d;position: absolute; left: 4px; top: -6px; font-size: 12px;height: 12px; line-height: 12px; }
    
 		.row_item{ height: 30px; line-height: 30px; margin-top:20px; }
        .row_item label{width:25%; height: 30px; line-height: 30px;font-size: 12px; float: left; color: #3d3d3d; }
        .row_item input{ width: 25%; height: 30px; line-height: 30px;font-size: 12px; float: left; border-radius:3px; border:1px solid #bbb;}
        .row_item .row_model{width: 25%; height: 30px; line-height: 30px;font-size: 12px; float: left; }
        .row_item .row_model input{ text-align:left;   width: 100%; height: 30px; line-height: 30px;font-size: 12px; float: left; }
        .row_item .row_txt{width:70%; height: 60px; line-height: 40px; float: left;}
        .row_item .row_txt textarea{width: 100%;resize:none; height: 100%;border-radius:4px; border-color:#bbb;}
        .row_item .row_tel{width: 20%;}

        .row_item select {width: 25%; float: left; height:30px; line-height:30px; border-radius:3px;}
        .row_item .deal_model{width: 20%; float: left; }
        .row_item .deal_model select{width: 100%;float: left}
        .row_item .deal_model select option{ text-align: center;}
        .btnLast{ float: right;}
        .btn_bass{ height: 20px; line-height: 20px; font-size: 12px; margin:0 8px; padding: 0 17px; display: inline-block;color: #fff;border-radius: 5px; }
        .btn_add{background: #3b85c7;}
        .btn_close{ background: #e2614e; }
        .btn_con{background: #6697cb;}
        .row_option {width: 20%; float: left; text-align: center;}
        .row_option input{width:100%; border:1px solid #a2a0a0;border-radius:2px;}
        #userApply{border:none; border-bottom:1px solid #3d3d3d;outline:none; width:25%;}
		
	</style>
</head>
<body>
<!-- 
	--修改三版弹出
	--剔除面包屑
	--改版盒子结构
	--操作逻辑不变 2017/11/28
 -->
	<form role="form" class="form-horizontal" id="form1">
   		<div class="mRwell_box edit_page">

        <input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
        <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
        <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
        <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
        <div class="mRwell_inner">
            <div class="mRwell_top">
                <span class="rel_tit">申告事件</span>

                <div class="row_item">
                    <label for="depart" >申告科室<span style="color:#fff;font-size:16px;">*</span>:　</label>
                    <div class="row_model"  style="position:relative">
                        <input type="hidden" id="id" name="id" value="${selectById.id }">
                        <input type="hidden" value="${selectById.idDept}" id="idDept" name="idDept">
                        <input type="text" id="eventDept" name="eventDept" value="${septTel.name }" readonly >
                        
                    </div>
                    
                </div>
                <div class="row_item">
                    <label for="person" >申告人<span style="color:#fff;font-size:16px;">*</span>:　</label>
                    <input type="hidden" id="userApply" name="userApply" value="${user_id }">
                   	<select id="eventUser" name="eventUser" disabled="disabled">
						<c:forEach var = "sfm" items = "${selectUser}">
							 <option value="${sfm.id }" <c:if test="${sfm.id eq selectById.idUserAplly}"> selected="selected"</c:if>>${sfm.name }</option>
						</c:forEach>
					</select>
                </div>

                <div class="row_item">
                    <label for="eventContact" >联系电话<span style="color:#fff;font-size:16px;">*</span>:　</label>
                    <input type="text" id="eventContact" name="eventContact" value="${selectById.eventContact }" class=" error_null telClass" data-message="电话不得为空" >
                </div>
                <div class="row_item">
                    <label for="tit" >事件标题<span style="color:red;font-size:16px;">*</span>:　</label>

					<input type="text" id="eventName" name="eventName" value="${selectById.eventName }" class=" error_null" data-message="事件标题不得为空！" >	
                </div>
                <div class="row_item">
                    <label for="remark" class=" ">事件描述<span style="color:red;font-size:16px;">*</span>:　</label>
                    <div class="row_txt">
                        <textarea id="eventDesc" name="eventDesc" class="" style="line-height:20px;" data-message="事件描述不得为空！">${selectById.eventDesc }</textarea>
                    </div>
                </div>
            </div>
        </div>
        <div class="btnLast" >
            <a class="btn_bass btn_add btnHover" id="upConfirm">确定</a>
            <a class="btn_bass btn_close btnHover" >取消</a>
        </div>
        <div class="form-group" style="display:none;">
        	<div class="form-group" style="margin-bottom:75px">
									<label for="telephone" class="col-sm-3">固定电话：</label>
									<div class="col-sm-8"><input type="text" id="deptTel" name="deptTel" value="${septTel.tel }" class="form-control telClass" data-message="姓名不得为空！"></div>
								</div>
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
</form>
<div id="eveBox" style="width:10px; height:10px; position: absolute; bottom: 80px; right: 0;"></div>
	<script type="text/javascript">
		$(function(){
			$('.btn_close').click(function(){
				var index=parent.layer.getFrameIndex(window.name); 
				parent.layer.close(index);
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
			updateEvent();
		})
	</script>
</body>
</html>