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
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/uploadify/webuploader.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/uploadify/webuploader.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/ser_apply_list.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">服务管理</a></li>
				    <li class="active">评价</li>
				</ol>
			</div>
			<div class="mRbot mNewRbot">
				<div class="mRwell">
					<div class="mRwell-tit">评价</div>
					<form  role="form" class="form-horizontal" id="form1" method="post">						
						<input type="hidden" id="pagenumber" name="pagenumber" value="${pageNumber }">
	                    <input type="hidden" id="pagesize" name="pagesize" value="${pageSize }">
	                    <input type="hidden" id="totalpage" name="totalpage" value="${totalPage }">
	                    <input type="hidden" id="totalrow" name="totalrow" value="${totalRow }">
						<div class="mRwell-container">
							<div class="mRwell-con-center">
								<div class="mRwell-center-tit">事件标题：<span id="eventName">${eventName }</span></div>
								<input type="hidden" id="idEvent" name="idEvent" value="${eventId }">
								<input type="hidden" id="userId" name="userId" value="${userIdList }">
								<c:forEach var = "assessMap" items = "${selectAssess}" >
								<div class="mRwell-wrap">								
									<div class="mRwell-center-per">
										<div class="center-con">
											处理人：<span>${assessMap.userName }</span>
										</div>
										<div class="center-con">
											岗位：<span>${assessMap.postName }</span>
										</div>
									</div>
									<div class="form-group">
										<label for="file_code" class="col-sm-2">服务态度：</label>
										<div class="col-sm-10 star star1">
										  <input type="hidden" id="service_${assessMap.userId }" name="service_${assessMap.userId }">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
										</div>
									</div>
									<div class="form-group star star2">
										<label for="file_code" class="col-sm-2">专业程度：</label>
										<div class="col-sm-10">
										    <input type="hidden" id="profess_${assessMap.userId }" name="profess_${assessMap.userId }">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
										</div>
									</div>
									<div class="form-group star star3">
										<label for="file_code" class="col-sm-2">处理时效：</label>
										<div class="col-sm-10">
										    <input type="hidden" id="eventAss_${assessMap.userId }" name="eventAss_${assessMap.userId }">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
											<img alt="pos" src="<%=request.getContextPath()%>/images/star-yellow.png">
										</div>
									</div>
									<div class="form-group">
										<label for="remark" class="col-sm-2">评价意见：</label>
										<div class="col-sm-10">
											<textarea class="form-control" id="remark_${assessMap.userId }" name="remark_${assessMap.userId }"></textarea>
										</div>
									</div>
								</div>		
								</c:forEach>								
								<div class="btnlist">
									<a class="btn btn-primary btn-sm" id="addConfirm">确定</a>
									<a class="btn btn-sm" onclick="cancel()">取消</a>
								</div>
							</div>
						</div>
					</form>
				</div>				 
			</div>
		</div>
		<script type="text/javascript">
			$(function(){
				//评价星级的实现
				$(".star img").click(function(){					
					if($(this).attr("src")=="<%=request.getContextPath()%>/images/star-yellow.png"){
						$(this).nextAll("img").attr("src","<%=request.getContextPath()%>/images/star-white.png").stop();
					}else{
						$(this).attr("src","<%=request.getContextPath()%>/images/star-yellow.png").stop();
						$(this).prevAll("img").attr("src","<%=request.getContextPath()%>/images/star-yellow.png").stop();
					}					
				})
				//获取评分
				$("#addConfirm").click(function(){ 
					//p1  服务态度评分        p2   专业程度评分      p3   处理时效评分
					var a=0,b=0,c=0,arr=[],p1=[],p2=[],p3=[];
					for(i=0;i<$(".star1 img").length;i++){
						
						if($(".star1 img").eq(i).attr("src")==$(".star1 img").eq(0).attr("src")){
							a++;
						}
						if($(".star2 img").eq(i).attr("src")==$(".star1 img").eq(0).attr("src")){
							b++;
						}
						if($(".star3 img").eq(i).attr("src")==$(".star1 img").eq(0).attr("src")){
							c++;
						}
						if((i+1)%5==0){
							arr.push(a);
							arr.push(b);
							arr.push(c);
						}
					}
					 for(var j=0;j<arr.length/3;j++){
						if(j == 0){
							p1.push(arr[0]);
							p2.push(arr[1]);
							p3.push(arr[2]);
						}else{
							p1.push(arr[3*j]-arr[3*j-3]);
							p2.push(arr[3*j+1]-arr[3*j-2]);
							p3.push(arr[3*j+2]-arr[3*j-1]);
						} 
					}
					 var userId = $("#userId").val();
					 var idUser =  userId.replace('[','').replace(']','').replace(' ','').replace(' ','');
					 var userAssesId = idUser.split(",");
					 var eventName = document.getElementById("eventName").innerText;
					 var idEvent = $("#idEvent").val();
					 var serviceAttitude = "";
					 for(var i = 0;i<p1.length;i++){
						for(var i = 0;i<userAssesId.length;i++){
							 $("#service_"+userAssesId[i]).val(p1[i]);
						}
					 }
					 var professionalism = "";
					 for(var i = 0;i<p2.length;i++){
						 for(var i = 0;i<userAssesId.length;i++){
							 $("#profess_"+userAssesId[i]).val(p2[i]);
						}
					 }
					 var eventAssess = "";
					 for(var i = 0;i<p3.length;i++){
						 for(var i = 0;i<userAssesId.length;i++){
							 $("#eventAss_"+userAssesId[i]).val(p3[i]);
						}
					 }
					 var url = "./updateDealAsses.do";
						var data = $("#form1").serialize();
						$.post(url,data,function(result){
							if(result.data == true){
								var pages = 1;
								var pageNumber = $("#pagenumber").val();
								var pageSize = $("#pagesize").val();
								var totalPage = $("#totalpage").val();
								var totalRow = $("#totalrow").val();
								location.href = getPath() + "/eventInfoList.do?pageNumber="+pageNumber+"&pageSize="+pageSize+"&totalPage="+totalPage+"&totalRow="+totalRow+"&pages="+pages;
							}else{
								alert("评价失败！");
							}
						}); 
					 
				})
			})
		</script>
</body>
</html>