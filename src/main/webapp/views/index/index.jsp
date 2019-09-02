<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans2">
<head>
<meta charset="UTF-8">
<title>智能信息化运维管理系统</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon">

<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
<script src="<%=request.getContextPath()%>/js/index.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<script type="text/javascript">
		var menuClick = function(menuUrl) {
			$("#maincontent").attr('src',menuUrl);
		};
</script>
<style>
.treebox .null .ico{background:url("<%=request.getContextPath()%>/images/project.png");background-size:100%;}
.treebox .perWork .ico{background:url("<%=request.getContextPath()%>/images/perWork.png");background-size:100%;}
.nav_on {
	text-decoration: none;
	background-color: #283847;
}

.layui-layer-title {
	background: #3b85c7;
	color: #fff;
}

#top .topRight {
	width: 240px;
}

#personItem {
	width: 80px;
	height: 40px;
	border: 1px solid #c9c9c9;
	box-sizing: border-box;
	position: absolute;
	left: 0;
	top: 50px;
	z-index: 9999;
	display: none;
}

#personItem li {
	cursor: pointer;
	width: 80px;
	border-bottom: 1px solid #fff;
	box-sizing: border-box;
	color: #62a5dc;
	background: #f0f0f0;
	height: 20px;
	line-height: 20px;
	text-align: center;
}

#personItem li a {
	color: #62a5dc;
}

#MesList {
	background:#fdfcfd;
	max-height: 300px;
	overflow-y: auto;
	overflow-x: hidden;
	width: 228px;
	border: 1px solid #c9c9c9;
	box-sizing: border-box;
	position: absolute;
	right: 55px;
	top: 50px;
	z-index: 9999;
	display: none;
}
    
.onOffBox{width:130px; height: 30px; position: absolute;  top:10px; right:270px;z-index:9999;}
.onOffBox .BtnOF{ cursor: pointer; width:130px; height: 30px; position: relative; background: #007eff; border-radius: 5px;}
.onOffBox .BtnOF span{ color:#fff; height: 30px; width: 65px; line-height: 30px; float: left; display: inline-block; text-align: center;}
.onOffBox .BtnOF i{transition: all 0.2s;  border-radius: 3px; background: #fff;  display: inline-block; text-align: center; position:absolute;line-height: 20px; left: 65px; top:5px; height: 20px; width: 60px;}
.onOffBox .BtnOF i b{ display: inline-block; width: 4px ; height: 14px; margin: 3px 8px; background: #007eff; }
.onOffBox .onOffBoxOn{ background: #a7cce9;}
.onOffBox .onOffBoxOn i b{ background: #a7cce9;}
#MesList li {
	cursor: pointer;
	width: 228px;
	border-bottom: 1px solid #fff;
	box-sizing: border-box;

	
	height: 20px;
	line-height: 20px;
	text-align: left;
}
#MesList li a{	color: #505050;}
#MesList li a:hover{ color:#3f75fd;text-decoration:none;}
</style>
</head>
<body>
	<div id="top">
		<div class="topLeft">
			<img src="<%=request.getContextPath()%>/images/logo.png" alt="智能管理系统">
		</div>
	<div class="onOffBox">
	    <div class="BtnOF" rel="on" id="BtnOF">
	        <span>普通人员</span>
	        <i><b></b><b></b></i>
	        <span>服务台</span>
	    </div>
	</div>
		<div class="topRight" style="position: relative;">
		    <input type="hidden" id="deptId" value="${dep_id }">
			<input type="hidden" id="dutyNo" value="sadsa">
			<span id="picItem" style="display: inline-block; cursor: pointer;"><img
				src="<%=request.getContextPath()%>/images/mem.png" alt="管理员"
				style="cursor: pointer;"><span class="mem">${user_name }</span></span>
			<ul id="personItem">
				<!-- <li id="accreditProxy">授权代理人</li>
				<li id="distroyProxy">授权回收</li> -->
				<li id="personal">个人中心</li>
				<li id="editPassword">修改密码</li>
				<li><a href="javascript:logout()">退出</a></li>
			</ul>
			<span id="MesItem" onclick=""
				style="display: inline-block; cursor: pointer; position: relative;">
				<img src="<%=request.getContextPath()%>/images/horn.png" alt="消息"><span
				class="mes" id="dealSpan"></span>
			</span>
			<ul id="MesList">
			</ul>
		</div>
	</div>
	<div id="main">
		<div class="mLeft">
			<div class="treebox">
				<ul class="menu">

				</ul>
			</div>
		</div>
		<iframe id="maincontent" name="main" frameborder="0" marginheight="0"
			marginwidth="0" scrolling="auto" height="100%" width="88%" src=""></iframe>
	</div>
	<div id="tipsBox"
		style="position: absolute; width: 1px; height: 1px; right: 10px; bottom: 90px;"></div>
	<div id="foot">版权所有：北京乾坤博远科技有限责任公司</div>
	<input type="hidden" value="<%application.setAttribute("name",123);%>"
		id="acceptId">
	<div class="new_mes">
		<p>您有一条新消息需要处理</p>
	</div>
		<script src="<%=request.getContextPath()%>/js/easing.js"></script>
	<script type="text/javascript">
		$(function(){
			var deptId = $("#deptId").val();
			if(deptId != 29){
				$("#BtnOF").hide();
			}
			
			var url = "<%=request.getContextPath()%>/loadMenu.do";
			
			var path = "<%=request.getContextPath()%>";
			
			$.post(url,"",function(result){
			
				var map = result;
				var modList = map["mod"];
				var funList = map["fun"];
				var proxyDuty = map["proxyDuty"];
			
				$.each(modList, function(i,mode){
					var menu = "<li class='level1 "+mode.menuImg+"'>";
					menu += "<a href=\"#\">";
					menu += "<em class=\"ico ico"+i+"\"></em>"+mode.name+"<i></i></a>";
					menu += "<ul class=\"level2\">";
					$.each(funList, function(j,func){
						if(funList[j].parentId == modList[i].id){
							if("大屏展示" == func.name){
								menu += "<li><a href=\""+path+"/"+func.menuUrl+"\" target=\"_blank\">"+func.name+"</a></li>";
							}else{
								menu += "<li><a href=\"#\" onclick=\"menuClick('"+path+"/"+func.menuUrl+"')\">"+func.name+"</a></li>";
							}
						}
					});
					menu += "</ul></li>"
					$(".menu").append(menu);
					$(".level2 li a").click(function(){
						$(".level2 li a").css({"background":"#0f4679","text-decoration":"none"});
						$(this).css({"background":"#283847"});
						
						 
					})
			    });
				$(".menu").children().first().find('a').first().click();
				$(".menu").children().first().find('a').next().find('a').first().click();
			
			
			});
			$(".treebox").on("click",".level1>a",function(){
				$(this).addClass('current')   //给当前元素添加"current"样式
				.find('i').addClass('down')   //小箭头向下样式
				.parent().next().slideDown('slow','easeOutQuad')  //下一个元素显示
				.parent().siblings().children('a').removeClass('current')//父元素的兄弟元素的子元素去除"current"样式
				.find('i').removeClass('down').parent().next().slideUp('slow','easeOutQuad');//隐藏
				 return false; //阻止默认时间
			});
		})
         function logout(){
				if(confirm("确定退出登录？")){
					$.post("<%=request.getContextPath()%>/logout.do",function(){
						top.location.href ="<%=request.getContextPath()%>/toLogin.do";	
					})
				}
			}
	</script>
<script>
    $(function(){
    	var myName=<%=application.getAttribute("dutyYes")%>; 
    	var myUserId = <%=session.getAttribute("user_id")%>;
 	   if(myName != 0 && myName != null && myUserId == myName){
 		  $("#BtnOF").attr("rel","off");
	   }else if(myName == null){
		   $("#BtnOF").attr("rel","on");
	   }
 	
        if($("#BtnOF").attr('rel')=="on"){
            $("#BtnOF").find("i").css("left","65px");
            $("#BtnOF").removeClass("onOffBoxOn");
        }else{
       
            $("#BtnOF").find("i").css("left","5px");
            $("#BtnOF").addClass("onOffBoxOn");
        }
        $("#BtnOF").click(function(){
        	var myNames;
        	var mydutyName;
        	$.ajax({
        		type:"post",
        		url:getPath()+"/queryApplication.do",
        		dataType:"json",
                async: false,
        		success:function(data){
        			myNames = data.dutyYes;
        			mydutyName = data.dutyName;
        		}
        	})
        	if(myNames != myUserId && myNames != null && myNames != ""){
        		alert("当前服务台是"+" "+mydutyName);
        		return;
        	}
        	if (!confirm("确定切换吗？")) {
    			return false;
    		}
            var frontDesk = 0;
            var sessionDuty = 1;
            if($(this).attr('rel')=="on"){
            
                $(this).find("i").css("left","5px");
                $(this).attr("rel","off").addClass("onOffBoxOn");
            
                $.ajax({
                    type:"post",
                    dataType:"json",
                    async: false,
                    url:getPath()+"/updateSessionDuty.do?duty="+1,
                    success:function(){
                        frontDesk = 0;
                    }
                })
                location.reload()
            }else{
                $(this).find("i").css("left","65px");
                $(this).attr("rel","on").removeClass("onOffBoxOn");
             
                $.ajax({
                    type:"post",
                    dataType:"json",
                    async: false,
                    url:getPath()+"/updateSessionDuty.do?duty="+0,
                    success:function(){
                        sessionDuty = 1;
                    }
                })
             location.reload()

            }
        })
    })

</script>
	<script>
	$(function(){
		var IframeOnClick = {  
		    resolution: 200,  
		    iframes: [],  
		    interval: null,  
		    Iframe: function() {  
		        this.element = arguments[0];  
		        this.cb = arguments[1];   
		        this.hasTracked = false;  
		    },  
		    track: function(element, cb) {  
		        this.iframes.push(new this.Iframe(element, cb));  
		        if (!this.interval) {  
		            var _this = this;  
		            this.interval = setInterval(function() { _this.checkClick(); }, this.resolution);  
		        }  
		    },  
		    checkClick: function() {  
		        if (document.activeElement) {  
		            var activeElement = document.activeElement;  
		            for (var i in this.iframes) {  
		                if (activeElement === this.iframes[i].element) { // user is in this Iframe  
		                    if (this.iframes[i].hasTracked == false) {   
		                        this.iframes[i].cb.apply(window, []);   
		                        this.iframes[i].hasTracked = true;  
		                    }  
		                } else {  
		                    this.iframes[i].hasTracked = false;  
		                }  
		            }  
		        }  
		    }  
		};  

		IframeOnClick.track(document.getElementById("maincontent"), function() { 
	 	 	$("#personItem,#MesList").slideUp();
		}); 
		$("#picItem").on("click", function(e){
		    $("#personItem").slideDown();
		    $("#MesList").slideUp();

		    $(document).one("click", function(){
		        $("#personItem").slideUp();
		       
		    });
			
		    e.stopPropagation();
		});
     
		$("#personItem").on("click", function(e){
		    e.stopPropagation();
		});
		 
		$("#MesItem").on("click", function(e){
		    $("#MesList").slideDown();
		    $("#personItem").slideUp();

		    $(document).one("click", function(){
		        $("#MesList").slideUp();
		    });
			
		    e.stopPropagation();
		});
     
		$("#MesList").on("click", function(e){
		    e.stopPropagation();
		});
		$('#personal').click(function(){
			 layer.open({
				    type: 2,
				    title: '个人资料',
				    shadeClose: false,
				    area: ['640px', '440px'],
					content:'<%=request.getContextPath()%>/personalCenter.do'
			 })
			
		})
		$('#editPassword').click(function(){
			 layer.open({
				    type: 2,
				    title: '修改密码',
				    shadeClose: false,
				    area: ['640px', '310px'],
					content:'<%=request.getContextPath()%>/toPassword.do'
			 })
		})
	
		
	
	})
	</script>
	<script>
		$(function(){ 
			var bodyWidth=$(document.body).width()-160;
			$('#maincontent').width(bodyWidth);
			window.onresize=function(){
				var bodyWidth=$(document.body).width()-160;
				$('#maincontent').width(bodyWidth);
			}
			
		})
	</script>

	<script>
		var dayDate ;
	$(function (){
		function dutyDate(){
			 $.ajax({
				 type:"post",
				 url:getPath()+"/selectDutyDate.do",
				 success:function(data){
					 dayDate = data.dateDuty;
				 }
			 })
		 }
		var auditId = "";
		function audit(){
			$.ajax({
				type:"post",
				url:getPath()+"/selectAudit.do",
				dataType:"json",
				success:function(data){
					var selectAudit = data.selectAudit;
					for(var i = 0;i<selectAudit.length;i++){
						auditId += selectAudit[i].id+",";
					}
				}
			})
		}
	 function log(resp){
		 var userId="<%=session.getAttribute("user_id")%>"; 
				if (resp != "" && resp != null) {
					var userDeal = resp.split("!")[1];
					var respa = resp.split("!")[0];
					if(userDeal.indexOf(",")>-1){
					var check = userDeal.split(",")[0];
					var userDeals = userDeal.split("!")[1];
					}
					if (userDeal != undefined && userDeal != "") {
						if (userId == userDeal || userId == userDeals) {
							layer.open({
								type : 4,
								time : 5000,
								tipsMore : true,
								title : '提示',
								tips : [ 2, "#fff0a5" ],
								closeBtn : 0,
								area : [ '200px', '70px' ],
								shade : 0,
								maxmin : false,
								offset : 'rb',
								content : [
										'<p style="color:#333"> 提示</p><p style="color:#666">'
												+ '您有一条新的消息需要处理' + '</p>', '#tipsBox' ]
							});
						}else if(check == "审核"){
							if(auditId.indexOf(userId)>-1){
								layer.open({
									type : 4,
									time : 5000,
									tipsMore : true,
									title : '提示',
									tips : [ 2, "#fff0a5" ],
									closeBtn : 0,
									area : [ '200px', '70px' ],
									shade : 0,
									maxmin : false,
									offset : 'rb',
									content : [
											'<p style="color:#333"> 提示</p><p style="color:#666">'
													+ respa + '</p>', '#tipsBox' ]
								});
								}
						}else if(userDeal.indexOf(userId)>-1){
							layer.open({
								type : 4,
								time : 5000,
								tipsMore : true,
								title : '提示',
								tips : [ 2, "#fff0a5" ],
								closeBtn : 0,
								area : [ '200px', '70px' ],
								shade : 0,
								maxmin : false,
								offset : 'rb',
								content : [
										'<p style="color:#333"> 提示</p><p style="color:#666">'
												+ respa + '</p>', '#tipsBox' ]
							});
						}
					}else{
						if (dayDate != undefined && dayDate) {
							layer.open({
								type : 4,
								time : 5000,
								tipsMore : true,
								title : '提示',
								tips : [ 2, "#fff0a5" ],
								closeBtn : 0,
								area : [ '200px', '70px' ],
								shade : 0,
								maxmin : false,
								offset : 'rb',
								content : [
										'<p style="color:#333"> 提示</p><p style="color:#666">'
												+ resp + '</p>', '#tipsBox' ]
							});
						}
					}	
				}
			}
			// 去除缓存
			$.ajaxSetup({
				cache : false
			});

			function initGet() {
				$.get("getNextTime").success(function(resp) {
					log(resp);
				}).error(function() {
				}).done(initGet);
			}
			initGet();
			dutyDate();
			audit();
			document.getElementById('MesList').innerHTML = "";
			$.ajax({
						type : "post",
						url : getPath() + "/selectInformation.do",
						dataType : 'json',
						success : function(data) {
							//处理信息
							var dealEnd = data.selectDealEnd;
							//确定驳回的处理信息
							var anewDeal = data.selectMessageAnewDeal;
							//受理信息
							var acceptDuty = data.selectAcceptDuty;
							//审核信息
							var check = data.selectMessageCkeck;
							//审核信息总条数
							//var checkCount = data.selectMessageCkeckCount;
							//需要处理的总条数
							var countDealAll = data.countDealAll;
							//确定驳回的处理信息的总条数
							//var anewDealCount = data.selectMessageAnewDealCount;
							//需要受理的总条数
							var selectAcceptCount = data.selectAcceptCount;
							//评价信息
							var evaluate = data.selectEvaluate;
							//评价信息总条数
							//var evaluateCount = data.selectEvaluateCount;
							//消息总条数
							if (dayDate) {
								var count = countDealAll + selectAcceptCount;
							} else {
								var count = countDealAll;
							}
							document.getElementById('dealSpan').innerHTML = "消息："
									+ count;
							for (var i = 0; i < dealEnd.length; i++) {
								var dateRespon = dealEnd[i].dateRespon;
								var id = dealEnd[i].id
								var date = dateFmts(dateRespon);
								document.getElementById('MesList').innerHTML += "<li><a onClick='seeApply(\""
										+ id
										+ "\")'> 您有一条待处理信息   "
										+ date
										+ "</a></li>";
							}
							if (dayDate) {
								for (var i = 0; i < acceptDuty.length; i++) {
									var dateCreate = acceptDuty[i].dateCreate;
									var id = acceptDuty[i].id;
									var date = dateFmts(dateCreate);
									var eventName = acceptDuty[i].eventName
									document.getElementById('MesList').innerHTML += "<li><a onClick='acceptOnclick(\""
											+ id
											+ "\")'> 您有一条待受理信息   "
											+date
											+ "</a></li>";
								}
							}
						}
					})
		});
		window.setInterval(scroll, 60000);
		function scroll() {
			document.getElementById('MesList').innerHTML = "";
			$.ajax({
						type : "post",
						url : getPath() + "/selectInformation.do",
						dataType : 'json',
						success : function(data) {
							//处理信息
							var dealEnd = data.selectDealEnd;
							//确定驳回的处理信息
							var anewDeal = data.selectMessageAnewDeal;
							//受理信息
							var acceptDuty = data.selectAcceptDuty;
							//审核信息
							var check = data.selectMessageCkeck;
							//审核信息总条数
							var checkCount = data.selectMessageCkeckCount;
							//需要处理的总条数
							var countDealAll = data.countDealAll;
							//确定驳回的处理信息的总条数
							var anewDealCount = data.selectMessageAnewDealCount;
							//需要受理的总条数
							var selectAcceptCount = data.selectAcceptCount;
							//评价信息
							var evaluate = data.selectEvaluate;
							//评价信息总条数
							var evaluateCount = data.selectEvaluateCount;
							//消息总条数
							if (dayDate) {
								var count = countDealAll + selectAcceptCount;
							} else {
								var count = countDealAll;
							}
							document.getElementById('dealSpan').innerHTML = "消息："+ count;
							for (var i = 0; i < dealEnd.length; i++) {
								var dateRespon = dealEnd[i].dateRespon;
								var id = dealEnd[i].id
								var date = dateFmts(dateRespon);
								document.getElementById('MesList').innerHTML += "<li><a onClick='seeApply(\""
										+ id
										+ "\")'> 您有一条待处理信息   "
										+ date
										+ "</a></li>";
							}
							if (dayDate) {
								for (var i = 0; i < acceptDuty.length; i++) {
									var dateCreate = acceptDuty[i].dateCreate;
									var id = acceptDuty[i].id;
									var date = dateFmts(dateCreate);
									var eventName = acceptDuty[i].eventName
									document.getElementById('MesList').innerHTML += "<li><a onClick='acceptOnclick(\""
											+ id
											+ "\")'> 您有一条待受理信息   "
											+ date
											+ "</a></li>";
								}
							}
						}
					})
		}
		function add0(m) {
			return m < 10 ? '0' + m : m
		}
		function dateFmts(creatTime) {
			var time = new Date(creatTime);
			var y = time.getFullYear();
			var m = time.getMonth() + 1;
			var d = time.getDate();
			var h = time.getHours();
			var mm = time.getMinutes();
			var s = time.getSeconds();
			return add0(m) + '-' + add0(d) + ' ' + add0(h) + ':' + add0(mm);
			return dateTimeStr;
		}
		function seeApply(id) {
			return false;
			var jump_list = getPath()+"/dealEnd.do?id=" + id;
	    	 layer.open({
	    		    type: 2,
	    		    title: '处理页面',
	    		    shadeClose: false,
	    		    area: ['902px', '650px'],
	    		    content: jump_list
	    		  })
		}
		function acceptOnclick(id) {
			return false;
			var jump_list = getPath()+"/eventAccept.do?eventId=" + id;
	    	 layer.open({
	    		    type: 2,
	    		    title: '受理页面',
	    		    shadeClose: false,
	    		    area: ['902px', '650px'],
	    		    content: jump_list
	    		  })
		}
		function evaluates(id) {
			return false;
			var jump_list = getPath()+"/addAssess.do?id=" + id;
	    	 layer.open({
	    		    type: 2,
	    		    title: '审核页面',
	    		    shadeClose: false,
	    		    area: ['902px', '650px'],
	    		    content: jump_list
	    		  })
		}
		function audit(id) {
			return false;
			var jump_list = getPath()+"/checkEvent.do?eventId=" + id;
	    	 layer.open({
	    		    type: 2,
	    		    title: '确认页面',
	    		    shadeClose: false,
	    		    area: ['902px', '650px'],
	    		    content: jump_list
	    		  })
		}
		/* 
		 *禁止点击
		 *audit/evaluates/acceptOnclick/seeApply
		 *函数体内首行添加了 return false
		 *如果需要跳转删除return false; 2017/12/6
		 */
	</script>
	<script type="text/javascript">
		function balabala(){
			$(".null").children("a").click()
			$.each($(".null a"),function(){
				if($(this).text()=="我的任务"){
					$(this).click()
				}
			})
		}
		function planMenu(){
			$(".perWork").children("a").click()
			$.each($(".perWork a"),function(){
				if($(this).text()=="工作计划"){
					$(this).click()
				}
			})
		}
		function pushMenu(){
			$(".perWork").children("a").click()
			$.each($(".perWork a"),function(){
				if($(this).text()=="我的查阅"){
					$(this).click()
				}
			})
		}
	</script>
</body>