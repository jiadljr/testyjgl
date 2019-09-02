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
	body{overflow-x:hidden; overflow-y:auto;}
   table{width: 690px; margin:5px auto; line-height: 30px; hieght:30px; text-align: center; border-collapse: collapse;}
	table td{ border:1px solid #ccc; }
	.titBgd{ background:#f1f1f1;}
	.drFileItem{ width:70px; margin:5px; float:left; height:95px; text-align:center; float:left;}
	.drFileItem i{ font-size:40px; color:#ccc; }
	.drFileItem .drFileName{ font-size:12px; color:#333; width:70px; line-height:14px; overflow:hidden;  height:28px; display:inline-block;}
	.drFileItem .drDel{height:17px; line-height:17px; width:35px; text-align:center;display:inline-block; }
</style>
</head>
<body>
<input type="hidden" id="hidBeginTime" value="${check.planBeginTime }">
<input type="hidden" id="hidFinishTime" value="${check.planFinishTime }">
<input type="hidden" id="hidId" value="${check.id }">
   <table cellspacing="0" id="tableMy">
     <tr>
       <td class="titBgd">类型</td>
       <td>
       <c:if test="${check.workType eq '1'}">个人计划</c:if>
       <c:if test="${check.workType eq '2'}">部门计划</c:if>
       </td>
       <td class="titBgd">计划类型</td>
       <td>
       <c:if test="${check.planType eq '1'}">周计划</c:if>
       <c:if test="${check.planType eq '2'}">月计划</c:if>
       <c:if test="${check.planType eq '3'}">年计划</c:if>
       <c:if test="${check.planType eq '4'}">自定义</c:if>
       </td>
     </tr>
     <tr>
       <td  class="titBgd">计划状态</td>
       <td>
       <c:if test="${check.planState eq '1'}">进行中</c:if>
       <c:if test="${check.planState eq '2'}">未开始</c:if>
       <c:if test="${check.planState eq '3'}">完成</c:if>
       </td >
       <td  class="titBgd">创建时间</td>
       <td id="newDate">${check.extend1 }</td>
     </tr>
     <tr>
       <td  class="titBgd">开始日期</td>
       <td id="beginTime"></td>
       <td  class="titBgd">结束日期</td>
       <td id="finishTime"></td>
     </tr>
     <tr >
        <td  class="titBgd">内容</td>
        <td colspan="3" class="nostr" >${check.content }</td>
     </tr>
     <tr>
       <td  class="titBgd">附件</td>
       <td colspan="3" class="nostr">
       				<div id="fileListDiv" style="width:406.63px; float:left;" ><!-- 存放返回的文件集合 -->
					<c:forEach items="${selectPlanFile }" var="file">	
						<div title="${file.sfName }" class='drFileItem' id="${file.sfId }"><i class='fa fa-file'></i><br><a class='drFileName drFileNames' href='javascript:downFile(${file.sfId });'>${file.sfName }</a>
						</div>
					</c:forEach>
				    </div>
					</td>
     </tr>
     <tr >
       <td class="titBgd">推送</td>
       <td colspan="3" class="nostr">
       		${check.pushStaffName }
       </td>
     </tr>
   </table>
   <script type="text/javascript">
      $(function(){
    	  $("#beginTime").text(FormatDate($("#hidBeginTime").val()));
    	  $("#finishTime").text(FormatDate($("#hidFinishTime").val()));
    	  $("#updateBut").click(function(){
    		  var id = $("#hidId").val();
    		  location.href=getPath()+"/queryWorkPlanMod.do?id="+id;
    	  })
      })
      function downFile(fileId){
	  	$.ajax({
	  		data:{fileId: fileId},
	  		dataType : 'json',
	  		async: false,
	  		type : 'post',
	  		url : getPath() + "/exportDocLoad.do",
	  		success : function(result){
	  			if (result.data == "error") {
	  				alert("文件不存在!");
	  			}else {
	  				location.href = getPath() + "/downLoad.do?fileId=" + fileId;
	  			}
	  		}
	  	})
	  }
   </script>
</body>
</html>