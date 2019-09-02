<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">

<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
<style type="text/css">
	body{overflow-x:hidden; overflow-y:auto; }
   table{width: 690px; margin:5px auto; line-height: 30px; hieght:30px; text-align: center; border-collapse: collapse;}
	table td{ border:1px solid #ccc; font-size:14px;}
	.titBgd{ background:#f1f1f1;}
	.drFileItem{ width:70px; margin:5px; float:left; height:95px; text-align:center; float:left;}
	.drFileItem i{ font-size:40px; color:#ccc; }
	.drFileItem .drFileName{ font-size:12px; color:#333; width:70px; line-height:14px; overflow:hidden;  height:28px; display:inline-block;}
	.drFileItem .drDel{height:17px; line-height:17px; width:35px; text-align:center;display:inline-block; }
	</style>
</head>
<body>
 <table>
     <tr >
       <td class="titBgd">类型</td>
       <td>
                    工作汇报
       </td>
       <td class="titBgd">汇报类型</td>
       <td>
       <c:if test="${reportCheck.reportType eq '1'}">自定义</c:if>
       <c:if test="${reportCheck.reportType eq '2'}">日报</c:if>
       <c:if test="${reportCheck.reportType eq '3'}">周报</c:if>
       <c:if test="${reportCheck.reportType eq '4'}">月报</c:if>
       <c:if test="${reportCheck.reportType eq '5'}">年报</c:if>
       </td>
     </tr>
     <tr>
       <td class="titBgd">状态</td>
       <td colspan="3">
       <c:if test="${reportCheck.reportState eq '1'}">草稿</c:if>
       <c:if test="${reportCheck.reportState eq '2'}">完成</c:if>
       </td>

     </tr>
     <tr >
        <td class="titBgd">内容</td>
        <td colspan="3" class="nostr" >${reportCheck.reportContent }</td>
     </tr>
     <tr >
       <td class="titBgd">附件</td>
       <td colspan="3" class="nostr" >
       		<div id="fileListDiv" style="width:406.63px; float:left;" ><!-- 存放返回的文件集合 -->
				<c:forEach items="${selectPlanFile }" var="file">	
					<div title="${file.sfName }" class='drFileItem' id="${file.sfId }"><i class='fa fa-file'></i><br><a class='drFileName drFileNames' href='javascript:;'>${file.sfName }</a>
					</div>
				</c:forEach>
			</div>
       </td>
     </tr>
     <tr>
       <td class="titBgd">推送</td>
       <td colspan="3" class="nostr" >${reportCheck.reportStaffName }</td>
     </tr>
   </table>
</body>
</html>