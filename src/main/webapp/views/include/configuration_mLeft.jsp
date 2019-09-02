<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="mLeft">
	<ul class="list-group">
        <li class="list-group-item text-center active"><a href="<%=request.getContextPath() %>/toConfigureMenu.do"><span class="glyphicon glyphicon-inbox" style="color: rgb(255, 140, 60); "></span>菜单管理</a></li>
        <li class="list-group-item text-center"><a href=""><span class="glyphicon glyphicon-phone-alt" style="color: rgb(255, 140, 60); "></span>审计信息</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/toParamConfig.do"><span class="glyphicon glyphicon-hand-right" style="color: rgb(255, 140, 60); "></span>参数配置</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/toRoleConfig.do"><span class="glyphicon glyphicon-hand-right" style="color: rgb(255, 140, 60); "></span>角色管理</a></li>
    </ul>
    <hr>
    <div class="hidbtn text-right"><span class="glyphicon glyphicon-transfer" style="color: rgb(255, 140, 60); "></span></div>
</div>