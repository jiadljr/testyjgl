<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>流程查看</title>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/images/favicon.ico" type="image/x-icon">
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/images_files/html2canvas.min.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/images_files/canvas2image.js"></script>
	<script src="<%=request.getContextPath()%>/plugins/images_files/base64.js"></script>
<style>
body{overflow-x:hidden;overflow-y:auto;}
*{margin:0;padding:0}
li{list-style:none;}
#process{ width:850px; margin:20px auto; min-height:440px;}
#process li{ word-wrap:break-word; word-break:break-all;width:240px;margin-right:50px; float:left;position:relative;box-sizing:border-box;text-align:left; line-height:20px; }
#process .tit{font-weight:bolder;height:30px;border-bottom:2px #3b85c7 solid; margin-bottom:10px; }
#process .li{ float:left;min-height:50px;border:1px solid #666; margin-bottom:40px;}
#process .rank{ width:100%;}
h4,.btnlist{ width:850px; margin:20px auto; height:30px;}
.btnlist a{ width:80px; height:30px; text-align:center; line-height:30px; color:#fff; background:#3b85cf; border-radius:2px;text-decoration: none;display:block;}
.line{ width:51px; height:1px; background:#666;position:absolute; left:239px;top:20px; }
.line2{top:23px;}
.line3{top:25px; left:-52px; }
.arrow{ position:absolute; left:125px; bottom:-40px;}
.arrow-inner{ display:block;position:relative; width:1px; height:40px; background:#666;}
.arrow-inner:after {  position:absolute;  font-size: 0;  line-height: 0;    border-width: 5px;border-top-bottom:5px;  border-top-top:5px;   border-color: #666;    border-bottom-width: 0;  border-style: dashed;   border-top-style: solid;  border-left-color: transparent;  border-right-color: transparent; left:-4px; top:37px; content:'' }
#process .rank:nth-last-child(1) .mid .arrow{display:none;}
#process .rank:nth-last-child(1) .mid{border-radius:6px;}
canvas{display:none;}
</style>
</head>
<body>

<div class="btnlist">
	<a href="#" id="btn">下载图片</a>
</div>
<h4 id="tit">${projTemlate.templateName }</h4>
<ul id="process">
	<li class="tit">
		阶段内容
	</li>
	<li class="tit">任务名称</li>
	<li class="tit" style="margin-right:0;">阶段成果/文档:</li>
	<c:forEach items="${templateTaskList }" var="templateTask">
	<li class="rank">
	<ul>
		<li class="li">${templateTask.phaseRemark }<span class="line"></span><span class="line line2"></span></li><!-- 左边 -->
		<li class="li mid">${templateTask.taskName }<span class="arrow"><span class="arrow-inner"></span></span></li><!-- 中边 -->
		
		<li class="li" style="margin-right:0;">${templateTask.phaseShow }<span class="line line3" ></span></li><!-- 右边 -->
	</ul>
	</li>
	</c:forEach>
</ul>

</body>
<script>
$("#process").height($("html").height())
$.each($(".mid"),function(){
	$(this).height($(this).parent().parent().height()-40)
})

function outPic(){
	var content = document.getElementById("process");
	   html2canvas(content).then(function(canvas) {
           document.body.appendChild(canvas);
       });
}
outPic();
var oBtn=document.getElementById("btn");
var oTit=document.getElementById("tit").innerHTML;
oBtn.onclick = function(){
	var oCanvas = document.getElementsByTagName("canvas")[0];
	var img_data1 = Canvas2Image.saveAsPNG(oCanvas, true).getAttribute('src');
	saveFile(img_data1, oTit);
}
// 保存文件函数
var saveFile = function(data, filename){
    var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
    save_link.href = data;
    save_link.download = filename;   
    var event = document.createEvent('MouseEvents');
    event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
    save_link.dispatchEvent(event);
};

// 保存文件函数

</script>
</html>
