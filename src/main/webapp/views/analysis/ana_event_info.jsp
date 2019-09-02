<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title></title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.bootstrap3.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/bPage/b.page.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/plugins/ztree/zTreeStyle.css">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>	
	
	<script src="<%=request.getContextPath()%>/bootstrap/js/bootstrap.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/index.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/bPage/b.page.js"></script>
	<script src="<%=request.getContextPath()%>/js/utilx.js"></script>
	<script src="<%=request.getContextPath()%>/js/ana_event_list.js"></script>
	<style>
	body{ overflow:auto;}
	*{padding:0;margin:0;}
	li{list-style:none;}
	 .event_look{ width: 640px; margin:0 auto; padding-bottom:20px;}
    .event_look_inner{ position: relative;  width:620px; margin:20px auto 0 auto ;}
	.table_tit{ font-weight:bolder;}
	.table tr td textarea{ width:100%; height:100%;resize:none;border:none; background:#fff;}
	#tab_deal_mes{width:620px; margin:20px auto 0 auto ;min-height:100px;}
	#tab_deal_mes ul{ width:78px; float:left; }
	#tab_deal_mes ul li{white-space:nowrap; overflow:hidden; text-overflow:ellipsis;background:#f9f9f9; box-sizing:border-box; text-align:center;line-height:37px; width:100%; height:37px; border:1px  solid #ccc; position:relative;}
	#tab_deal_mes .table{ margin-bottom:0;}
	#tab_deal_mes ul .cur{ background:#fff;}
	
   #tab_deal_mes ul .cur i{width:4px; height:36px; position:absolute;left:-1px;background:#41b4f1;}
    #tab_deal_mes div {display:none;margin:0; float:left;width:540px;}
    .event_look_inner .event_look_inner_tit{position: absolute;top: -10px; background: #fff; left: 20px; height: 20px; line-height: 20px ; padding: 0 3px;  display: inline-block;}
	 .event_look_inner td{ border:1px solid #000;}
	</style>
</head>
<body>
	
			
			<div class="event_look">
    <div class="event_look_inner" id="apply_mes">
        <p class="event_look_inner_tit">申告信息</p>
        <table class="table table-bordered">
            <tbody>
            <tr>
                <td class="table_tit">申告科室</td>
                <td>${eventInfoOne.extend1 }</td>
                <td class="table_tit" >时间</td>
                <td><fmt:formatDate value="${eventInfoOne.dateCreate }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
            </tr>
            <tr>
                <td class="table_tit">申告人</td>
                <td>${eventInfoOne.extend2 }</td>
                <td class="table_tit">联系电话</td>
                <td>${eventInfoOne.eventContact }</td>
            </tr>
            <tr>
                <td class="table_tit" >事件标题</td>
                <td colspan="3" class="nostr" style="line-height:20px;">${eventInfoOne.eventName }</td>

            </tr>
            <tr>
                <td  class="table_tit" >申告描述</td>
                <td class="nostr" colspan="3">${eventInfoOne.eventDesc }</td>

            </tr>
            </tbody>
        </table>
    </div>
    <div class="event_look_inner" id="accept_mes">
        <p class="event_look_inner_tit">受理信息</p>
        <table class="table  table-bordered">
            <tbody>
            <tr>
                <td class="table_tit">受理人</td>
                <td id="acc_mes_txt">${acceptList.extend1 }</td>
                <td class="table_tit">时间</td>
                <td><fmt:formatDate value="${acceptList.dateAccept }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
            </tr>
            <tr>
                <td class="table_tit" >事件等级</td>
                <td>
               		 <c:if test="${acceptList.eventLevel eq  1}">一级</c:if>
					 <c:if test="${acceptList.eventLevel eq  2}">二级</c:if>
					 <c:if test="${acceptList.eventLevel eq  3}">三级</c:if>
					 <c:if test="${acceptList.eventLevel eq  4}">四级</c:if>
				 </td>
                <td class="table_tit" >优先级</td>
                <td><c:if test="${acceptList.eventPriority == 1 }">高</c:if>
					<c:if test="${acceptList.eventPriority == 2 }">中</c:if>
					<c:if test="${acceptList.eventPriority == 3 }">低</c:if></td>
            </tr>
            <tr>
                <td class="table_tit" >事件源</td>
                <td>${acceptList.extend3}</td>
                <td class="table_tit" >服务类型</td>
                <td>${acceptList.extend2}</td>
            </tr>
            <tr>
                <td class="table_tit">受理描述</td>
                <td  colspan="3" class="nostr" >${acceptList.acceptDesc }</td>

            </tr>
            </tbody>
        </table>
    </div>
       <div id="tab_deal_mes">
     	<ul>
     	     <c:forEach items="${dealList }" var="deal">
     	
     		<li>${deal.dealName }<i></i><span></span></li>
     		   </c:forEach>
     	</ul>
  
   <c:forEach items="${dealList }" var="deal">

   
   <div class="event_look_inner" style="margin-bottom:20px;" id="in_mes_del"  >
        <p class="event_look_inner_tit">处理信息</p>
        <table class="table table-hover table-bordered">
            <tbody>
            <tr>
                <td   class="table_tit">处理人</td>
                <td>${deal.dealName }</td>
                <td    class="table_tit">时间</td>
               <td>	<fmt:formatDate value="${deal.dateDeal }" pattern="yyyy/MM/dd HH:mm:ss" /></td>
            </tr>

            <tr>
             	<td  class="table_tit">岗位</td>
               <td>	${deal.dealPost }</td>
                <td  class="table_tit">处理结果</td>
                <td >
                	<c:if test="${deal.dealStatus == 2 }">解决</c:if>
					<c:if test="${deal.dealStatus == 3 }">转派至${deal.reinName }</c:if>
				</td>
            </tr>
            <c:if test="${deal.dealStatus != 3 }">
	            <tr>
	                <td class="table_tit">涉及资产</td>
	                <td colspan="3" style="line-height:20px;">${deal.assetsName }</td>
	            </tr>
	            <tr>
	               <td class="table_tit">产生原因</td>
	               <td  colspan="3" class="nostr">${deal.eventCause }</td>
	
	        	</tr>
           	</c:if>
        	<c:if test="${deal.dealStatus == 3 }">
        		<tr>
	                <td  class="table_tit" >转派原因</td>
	                <td  class="nostr" colspan="3" style="line-height:20px;">${deal.dealDesc }</td>
	
	            </tr>
        	</c:if>
            <c:if test="${deal.dealStatus != 3 }">
	            <tr>
	                <td  class="table_tit" >处理描述</td>
	                <td  class="nostr" colspan="3" style="line-height:20px;">${deal.dealDesc }</td>
	
	            </tr>
           	</c:if>
           	<c:if test="${deal.dealStatus != 3 }">
	            <tr>
	                <td class="table_tit" >附件</td>
	                <td colspan="3" style="line-height:20px;"  class="nostr">
		                <c:forEach items="${deal.files }" var="file">									
							<a href="javascript:;" onclick="downFile(${file.fileId })">${file.fileName }</a>
						</c:forEach>
					</td>
	            </tr>
	        </c:if>
            </tbody>
        </table>
    </div>
    	</c:forEach>
 	   </div>
    <div class="event_look_inner" id="slove_mes">
        <p class="event_look_inner_tit">评价信息</p>
        <table class="table   table-bordered">
            <tbody>
            <tr>
                <td  class="table_tit">评价人</td>
                <td id="ass_peo">${assessList.extend1 }</td>
                <td  class="table_tit">时间</td>
                <td><fmt:formatDate value="${assessList.dateAssess}" pattern="yyyy/MM/dd HH:mm:ss" /></td>
            </tr>

            <tr>
                <td  class="table_tit">满意程度</td>
                <td colspan="3"  style="line-height:20px;">
                	<c:if test="${assessList.eventAssess == '1'}">失望</c:if>
                	<c:if test="${assessList.eventAssess == '2'}">不满</c:if>
                	<c:if test="${assessList.eventAssess == '3'}">一般</c:if>
                	<c:if test="${assessList.eventAssess == '4'}">满意</c:if>
                	<c:if test="${assessList.eventAssess == '5'}">优质</c:if>
                </td>

            </tr>
            <tr>
                <td  class="table_tit">评价描述</td>
                <td colspan="3" class="nostr">${assessList.assessDesc}</td>

            </tr>
            </tbody>
        </table>
    </div>
</div>

				
<script>
	$(function(){
		$('#tab_deal_mes').height($('#in_mes_del').height());
		if($("#acc_mes_txt").text()==''){
			$('#accept_mes').hide();
		}
		if($("#com_mes").text()==''){
			$('#confirm_mes').hide();
		}
	
		if($("#acc_mes_txt").text()==''){
			$('#accept_mes').hide();
		}
		if($("#ass_mes").text()==''){
			$('#assess_mes').hide();
		}
		if($("#ass_peo").text()==''){
			$('#slove_mes').hide();
		}
		if($("#tab_deal_mes ul li").length=='0'){
			$('#tab_deal_mes').hide();
		}
		var len=$('#tab_deal_mes li').length;
		if(len=='1'){
			 $("#tab_deal_mes ul").hide()
			 $("#tab_deal_mes div").show().width(620);
		}else{
			$("#tab_deal_mes div:eq(0)").show();
			 $("#tab_deal_mes li:eq(0)").addClass('cur');
		   $("#tab_deal_mes li").click(function(){
		        $("#tab_deal_mes li").eq($(this).index()).addClass("cur").siblings().removeClass('cur');
				$("#tab_deal_mes div").hide().eq($(this).index()).show();
	        });
		}
		
	})
</script>
</body>
</html>