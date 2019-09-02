<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
	<div id="top">
		<div class="topLeft"><img src="<%=request.getContextPath()%>/images/logo.png" alt="智能管理系统"></div>
		<div class="topRight">
			<img src="<%=request.getContextPath()%>/images/mem.png" alt="管理员"><span class="mem">${user_name }</span>
			<img src="<%=request.getContextPath()%>/images/horn.png" alt="消息"><span class="mes">消息：20</span>	
		</div>
	</div>
	
	<div id="main">
		<div class="mLeft">
			<div class="treebox">
				<ul class="menu">
					<li class="level1">
						<a href="<%=request.getContextPath()%>/selectDealNot.do"><em class="ico ico1"></em>系统主页<i class="down"></i></a>
						<ul class="level2">
							<li><a href="<%=request.getContextPath()%>/selectDealNot.do">系统主页</a></li>
							<li><a href="javascript:;">服务台首页</a></li>
							<li><a href="javascript:;">运维人员主页</a></li>
							<li><a href="javascript:;">大屏展示</a></li>
							<li><a href="javascript:;">人员视图</a></li>
						</ul>
					</li>
					<li class="level1">
						<a href="<%=request.getContextPath()%>/eventInfoList.do"><em class="ico ico2"></em>服务管理<i></i></a>
						<ul class="level2">
							<li><a href="<%=request.getContextPath()%>/eventInfoList.do">申告</a></li>
							<li><a href="<%=request.getContextPath()%>/toEventInfoAccept.do">受理</a></li>
							<li><a href="<%=request.getContextPath()%>/queryDelNot.do">处理</a></li>
							<li><a href="<%=request.getContextPath()%>/toCheckList.do">审核</a></li>
							<li><a href="<%=request.getContextPath()%>/queryAssess.do">评价</a></li>
						</ul>
					</li>
					<li class="level1">
						<a href="<%=request.getContextPath()%>/views/query/query.jsp"><em class="ico ico3"></em>查询分析<i></i></a>
						<ul class="level2">
							<li><a href="<%=request.getContextPath()%>/toAnalysisEvent.do">事件查询</a></li>
							<li><a href="<%=request.getContextPath()%>/querySky.do">时间统计</a></li>
							<li><a href="javascript:;">科室统计</a></li>
							<li><a href="javascript:;">服务类型统计</a></li>
							<li><a href="javascript:;">绩效统计</a></li>
							<li><a href="javascript:;">满意度统计</a></li>
							<li><a href="javascript:;">资产统计</a></li>							
						</ul>
					</li>
					<li class="level1">
						<a href="<%=request.getContextPath()%>/seleDuty.do"><em class="ico ico4"></em>管理体系<i></i></a>
						<ul class="level2">
							<li><a href="<%=request.getContextPath()%>/seleDuty.do">排班管理</a></li>
							<li><a href="<%=request.getContextPath()%>/selectCmpy.do">单位信息</a></li>
							<li><a href="<%=request.getContextPath()%>/sysDepart.do">部门信息</a></li>
							<li><a href="<%=request.getContextPath()%>/sysUser.do">人员信息</a></li>
							<li><a href="<%=request.getContextPath()%>/seleDo.do">文件管理</a></li>
							<li><a href="<%=request.getContextPath()%>/seleAssets.do">资产管理</a></li>
							<li><a href="<%=request.getContextPath()%>/toSysAssetsType.do">资产类别</a></li>
						</ul>
					</li>
                     <li class="level1">
						<a href="<%=request.getContextPath() %>/toConfigureMenu.do"><em class="ico ico5"></em>配置管理<i></i></a>
						<ul class="level2">
							<li><a href="<%=request.getContextPath() %>/toConfigureMenu.do">菜单管理</a></li>
							<li><a href="javascript:;">审计信息</a></li>
							<li><a href="<%=request.getContextPath()%>/toParamConfig.do">参数配置</a></li>
							<li><a href="<%=request.getContextPath()%>/toRoleConfig.do">角色管理</a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
 	<div id="foot">
	        版权所有：智能信息化运维管理系统
	</div>
	<div class="new_mes">
		<p>您有一条新消息需要处理</p>
	</div>
	<script src="<%=request.getContextPath()%>/js/easing.js"></script>
	<script>
		$(function(){
			$(".treebox .level1>a").click(function(){
				$(this).addClass('current')   //给当前元素添加"current"样式
				.find('i').addClass('down')   //小箭头向下样式
				.parent().next().slideDown('slow','easeOutQuad')  //下一个元素显示
				.parent().siblings().children('a').removeClass('current')//父元素的兄弟元素的子元素去除"current"样式
				.find('i').removeClass('down').parent().next().slideUp('slow','easeOutQuad');//隐藏
				 return false; //阻止默认时间
			});
		})
	</script>
</body>
</html>