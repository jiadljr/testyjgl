<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
    <script src='<%=request.getContextPath()%>/js/utilx.js'></script>
   	<style type="text/css">
   		p{float:left; width:100%; height:40px; line-height:40px;}
   		 p label{ width:20%; display:inline-block; float:left; text-align:right;}
   		 p input,p select{width:70%; display:inline-block; float:left; line-height:30px; height:30px; border:1px solid #ccc; border-radius:3px; margin-top:5px; box-sizing:border-box;}
   			.btnlist{ text-align:right; width:100%; height:30px; float:left; position:absolute; bottom:10px;} 
   	.btnlist a{  display:inline-block; padding:0 4px; height:24px; line-height:24px; margin:3px 10px;border-radius:3px;}
	 
   	</style>
</head>
<body>
<div class="">
	<p>
		<label>值班时间：</label>
		<input type="text" id="dutyTime" value="${parameter }" class="">
	</p>
	<p>
		<label for="nightDuty" >值班人员：</label>
		<select id="nightDuty" name="dutyMa" class=" selectpicker bla bla bli" data-message="夜班人员不得为空！">
			<c:forEach var = "sfm" items = "${userByRoleType}">
			<option value="${sfm.id }" <c:if test="${selectDutyIdUser.idUser eq sfm.id}"> selected="selected"</c:if>>${sfm.name }</option>
			</c:forEach>
		</select>
	</p>
	<p class="btnlist">
		<a href="#" class="btn_blue" id="confirm">确定</a>
		<a href="#" class="btn_red">取消</a>
	</p>

</div>


	 <script type="text/javascript">
	   $(function(){
		   $("#confirm").click(function(){
			  var dutyTime = $("#dutyTime").val();
			  var dutyUser = $("#nightDuty").val();
			  $.ajax({
				  type:"post",
				  data:{"dutyTime":dutyTime,"dutyUser":dutyUser},
				  url:getPath()+"/updateDutyTime.do",
				  success:function(data){
					  if(data == 0){
						  alert("修改失败，请查看当月是否排班");
					  }
					  var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					  parent.layer.close(index);
					  window.parent.loadNewPage()
				  }
			  })
		   })
		   $(".btn_red").click(function(){
			   var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
			   parent.layer.close(index);
		   })
	   })
    </script>
</body>
</html>