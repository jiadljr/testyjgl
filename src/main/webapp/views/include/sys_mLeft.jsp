<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="mLeft">
	<ul class="list-group">
        <li class="list-group-item text-center active"><a href="<%=request.getContextPath()%>/seleDuty.do"><span class="glyphicon glyphicon-inbox" style="color: rgb(255, 140, 60); "></span>排班管理</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/selectCmpy.do"><span class="glyphicon glyphicon-phone-alt" style="color: rgb(255, 140, 60); "></span>单位信息</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/sysDepart.do"><span class="glyphicon glyphicon-hand-right" style="color: rgb(255, 140, 60); "></span>部门信息</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/sysUser.do"><span class="glyphicon glyphicon-film" style="color: rgb(255, 140, 60); "></span>人员信息</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/seleDo.do"><span class="glyphicon glyphicon-info-sign" style="color: rgb(255, 140, 60); "></span>文件管理</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/seleAssets.do"><span class="glyphicon glyphicon-info-sign" style="color: rgb(255, 140, 60); "></span>资产管理</a></li>
        <li class="list-group-item text-center"><a href="<%=request.getContextPath()%>/toSysAssetsType.do"><span class="glyphicon glyphicon-info-sign" style="color: rgb(255, 140, 60); "></span>资产类别</a></li>
    </ul>
    <hr>
    <div class="hidbtn text-right"><span class="glyphicon glyphicon-transfer" style="color: rgb(255, 140, 60); "></span></div>
</div>