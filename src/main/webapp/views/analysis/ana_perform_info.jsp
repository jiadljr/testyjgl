<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>绩效统计</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/conAdd_menu.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/datetimepicker/jquery.datetimepicker.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/datetimepicker/build/jquery.datetimepicker.full.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/echars/echarts.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/js/ana_perform_info.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	
<style>	
a{text-decoration: none; }
.fixTableHeader .copy_box{width:15px; height:100%; float:left; box-sizing: border-box; border:1px solid #ccc; }
.fixTableHeader table{ float:left;}
.relTableBody { overflow-y:scroll; overflow-x:hidden;}
.mRnewbot{ height:100%; float:left; width:100%;}
.mRnewbot .mRtabs{  border-bottom: 1px solid #ccc;  width: 100%; height: 32px;}
.mRnewbot .mRtabs li{ position: relative; float:left; margin: 0 10px;  }
.mRnewbot .mRtabs li span{ height: 32px; display: block; line-height:32px; color:#ccc; cursor: pointer;}
.mRnewbot .mRtabs .nav_on span{  border-bottom: 1px solid #3eabe7; color:#3eabe7; }
.mRnewbot .mRtabs .nav_on i{border:4px solid #3eabe7; margin-left:-4px; border-left-color:#fff;border-right-color:#fff;border-bottom-color:#fff;  width:4px;position:absolute ; left: 50%; bottom: -8px; height:4px; display: block;  box-sizing: border-box; }
.mRnewbot .mRtablist { height:80%; position:relative;}
.mRnewbot .mRtablist .tabItem{ opacity:0; height:100%; position:absolute;width:100%;z-index:-1;}
.mRnewbot .mRtablist .showBlock{ opacity:1;z-index:2;}
.mRnewbot .mRtablist .tabItem .border_blue{box-shadow:1px 1px 8px #aaa; }
.mRnewbot .mRtablist .tabItem .title_bgk{background:#fbfbfb;color:#86b3e4; height:30px; border-bottom: 1px solid #e8e8e8; line-height:30px; margin:0;}
.mRnewbot .mRtablist .tabItem .tab_form .btn_sm{ width:44px; text-align:center;display: inline-block; font-size: 12px;font-weight: normal;line-height: 26px;height:26px;text-align: center;white-space: nowrap;vertical-align: middle;cursor: pointer;border: 1px solid transparent;border-radius: 4px;background:#3f90e6;color:#fff;}  
.mRnewbot .mRtablist .tabItem .tab_form span{ padding: 0 8px; height:30px; line-height:30px; font-size:12px;color:#5f5f5f;}
.mRnewbot .mRtablist .tabItem .tab_form input,.tab_form select{border:1px solid #aaa; border-radius:3px; padding:2px 6px; margin:5px 0; height:30px; line-height:26px;}
.mRnewbot .mRtablist .tabItem .tab_l_box{ width:40%; height:90%; float:left; margin:1%;}
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix{ height:39px; width:96%; margin: 0 auto;} 
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table{margin-bottom:0;}
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table th{ text-align:center;}
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table thead tr th:nth-child(1),
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table thead tr th:nth-child(2),
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table thead tr th:nth-child(6),
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table tbody tr td:nth-child(1),
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table tbody tr td:nth-child(2),
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_fix .table tbody tr td:nth-child(6){ width:50px;}
.mRnewbot .mRtablist .tabItem .tab_l_box .tab_rel{width:96%; height:60%; margin:0 auto;　}
.mRnewbot .mRtablist .tabItem .handle_box{width: 56%; height:43%; float:left; margin:1%; }
.mRnewbot .mRtablist .tabItem .handle_box #deal{ width:100%; height:100%;}
.mRnewbot .mRtablist .tabItem .accept_box{width: 56%; height:43%; float:left;margin:1%; }
.mRnewbot .mRtablist .tabItem .accept_box #accept{width:100%; height:100%;}
.mRnewbot .mRtablist .tabItem .ser_count_box,.mRnewbot .mRtablist .tabItem .by_time_box{ width:48% ;float:left; height:40%; margin:1%;}
.mRnewbot .mRtablist .tabItem .ech_box{ width:98%; height:48%; margin: 1%; float:left;}
.mRnewbot .mRtablist .tabItem .ser_count_box .full_box,.mRnewbot .mRtablist .tabItem .by_time_box .full_box{height:100%; width:100%;}
.mRnewbot .mRtablist .tabItem .ech_box .full_box{ height:100%; width:100%;}



</style>	
</head>
<body>
		<div class="mRight">
			<div class="mRpos">
				<ol class="breadcrumb">
				    <li><a href="javascript:;"><img alt="pos" src="<%=request.getContextPath()%>/images/pos.png">管理体系</a></li>
				    <li><a href="javascript:;">查询分析</a></li>
				    <li class="active">绩效统计</li>
				</ol>
			</div>
			<div class="mRnewbot">
				<ul class="mRtabs">
					<li class="nav_on"><span>总览</span><i></i></li>
					<li><span>个人</span><i></i></li>
					
				</ul>
				<div class="mRtablist">
					<div class="tabItem showBlock">
						<div class="tab_form">
							<form>
								<span>起止时间:</span>
								<input value="" type="text" id = "all_startTime" placeholder="请选择开始时间">
								<span>至</span>
								<input value="" type="text" id = "all_endTime" placeholder="请选择结束时间">
								<input value="查询" type="button" id="all_search" class="btn_sm">
							</form> 
						</div>
						<div class="handle_box border_blue">
							<p class="title_bgk">　处理数量统计</p>
							<div id="deal" ></div>
						</div>
						<div class="accept_box border_blue">
							<p class="title_bgk">　受理数量统计</p>
							<div id="accept" ></div>
						</div>
					</div>
					<div class="tabItem">
						<div class="tab_form">
							<form>
								<span  class="dateStart">起止时间:</span>
								<input value="" type="text" id="personal_startTime" placeholder="请选择开始时间">
								<span>至</span>
								<input value="" type="text" id="personal_endTime" placeholder="请选择结束时间">
								<select id = "dealId" >
									<option value="">请选择处理人</option>
									<c:forEach items="${userAll }" var="user">
									   <option value="${user.id }">${user.name }</option>
									</c:forEach>
								</select>
								<input value="查询" type="button" id="personal_search" class="btn_sm">
							</form> 
						</div>
						<div class="ser_count_box border_blue">
							<p class="title_bgk">　服务类型统计</p>
							<div id="serCount" class="full_box"></div>
						</div>
						<div class="by_time_box border_blue">
							<p class="title_bgk">　平均耗时统计</p>
							<div id="serCountByTime" class="full_box"></div>
						</div>
						<div class="ech_box border_blue">
							<p class="title_bgk">　事件处理统计</p>
							<div id="ech" class="full_box"></div>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		<script>

	$(function () {
		var obj=$('.fixTableHeader').width()-17+"px";
	
	
		$('.fixTableHeader table').css("width",obj);
		var adk=$('.ke_inner').width()-17+"px";
		
	
		$('.ke_inner table').css("width",adk);
		
	});
</script>
		<script>
$(document).ready(function(){
    $(".mRtabs li").click(function(){
    $(".mRtabs li").eq($(this).index()).addClass("nav_on").siblings().removeClass('nav_on');
	$(".mRtablist .tabItem").css({"opacity":0,"z-index":-1}).eq($(this).index()).css({"opacity":1,"z-index":2});
    });
});
</script>
	<script type="text/javascript">
		$(function(){
			$("#all_startTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#all_endTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#personal_startTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#personal_endTime").datetimepicker({
				lang:'zh',
				timepicker : false,
				format : 'Y-m-d',
				formatDate : 'Y-m-d',
			});
			$("#all_search").click(function(){
				daty();
			})
			$("#personal_search").click(function(){
				userCount();
			})
			$(".tag-time").click(function(){
				$(".tag-time").removeClass("active");
				$(".tag-time img").attr("src","<%=request.getContextPath()%>/images/point-black.png");
				$(this).addClass("active");
				$(this).find("img").attr("src","<%=request.getContextPath()%>/images/point-blue.png");
			})
			daty();
			userCount();
		})
	</script>
</body>
</html>