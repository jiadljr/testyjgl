<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/prompt.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plugins/jQuery_tree/all.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<link  rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bootstrap/table/bootstrap-table.min.css" />
	<script src="<%=request.getContextPath()%>/bootstrap/table/bootstrap-table.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/layer/layer.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/jtree.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/uitool.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/plugins/jQuery_tree/js/tree_index.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page2.js"></script>

	
	<script src="<%=request.getContextPath()%>/js/proj_board_list.js"></script>
	<style type="text/css">
		body,html{overflow:auto;}
		table{width:200%;}
		table thead tr{background:yellow;}
		table thead th,table tbody td{border:1px solid #ccc; height:24px; text-align:center;}
	</style>
	
</head>
<body>
	<table >
		<thead>
		<tr>
			<th rowspan="2" width="60xp">编号</th>
			<th rowspan="2" width="90px">合同编号</th>
			<th rowspan="2" width="300px;">合同标题</th>
			<th rowspan="2" width="190px">对方单位</th>
			<th rowspan="2" width="90px">合同类型</th>
			<th rowspan="2" width="90px">履约情况</th>
			<th rowspan="2" width="90px">资金来源</th>
			<th rowspan="2" width="90px">付款方式</th>
			<th rowspan="2" width="140px">签署日期</th>
			<th rowspan="2" width="140px">合同金额 (元)</th>
			<th rowspan="2" width="140px">已结金额</th>
			<th rowspan="2" width="140px">未结金额</th>
			<th rowspan="2" width="140px">已结比例</th>
			<th colspan="8" width="">实际支付情况</th>		
		</tr>
		<tr>
			<th width="60px">编号</th>		
			<th width="60px">比例 %</th>		
			<th width="140px">支付金 额(元)</th>
			<th width="140px">支付时间</th>
			<th width="140px">支付条件</th>		
			<th width="140px">发票号</th>		
			<th>备注</th>		
			<th width="140px">登记人</th>
		</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
</body>
<script>
</script>
</html>